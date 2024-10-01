package org.thoughtcrime.securesms.megaphone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.core.app.NotificationManagerCompat;

import com.annimon.stream.Stream;
import com.bumptech.glide.Glide;

import org.signal.core.util.MapUtil;
import org.signal.core.util.SetUtil;
import org.signal.core.util.TranslationDetection;
import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.R;
import org.thoughtcrime.securesms.components.settings.app.AppSettingsActivity;
import org.thoughtcrime.securesms.database.SignalDatabase;
import org.thoughtcrime.securesms.database.model.MegaphoneRecord;
import org.thoughtcrime.securesms.database.model.RemoteMegaphoneRecord;
import org.thoughtcrime.securesms.dependencies.AppDependencies;
import org.thoughtcrime.securesms.jobmanager.impl.NetworkConstraint;
import org.thoughtcrime.securesms.keyvalue.PhoneNumberPrivacyValues.PhoneNumberDiscoverabilityMode;
import org.thoughtcrime.securesms.keyvalue.SignalStore;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
||||||| 35807f725b
=======
<<<<<<< HEAD
||||||| 69e1146e2c
=======
<<<<<<< HEAD
//import org.thoughtcrime.securesms.keyvalue.SmsExportPhase;
=======
>>>>>>> 94387f59e83f9be48a18536ad0b22f950783b09e
>>>>>>> e80bceeb3a3de89c781e259a97c5b8344e20afe5
import org.thoughtcrime.securesms.keyvalue.protos.LeastActiveLinkedDevice;
<<<<<<< HEAD
||||||| parent of 55894bc674 ( Inital commit. Re-enable SMS sending. Remove SMS export megaphone.)
import org.thoughtcrime.securesms.keyvalue.SmsExportPhase;
=======
//import org.thoughtcrime.securesms.keyvalue.SmsExportPhase;
>>>>>>> 55894bc674 ( Inital commit. Re-enable SMS sending. Remove SMS export megaphone.)
<<<<<<< HEAD
||||||| parent of 55894bc674 ( Inital commit. Re-enable SMS sending. Remove SMS export megaphone.)
import org.thoughtcrime.securesms.keyvalue.SmsExportPhase;
=======
//import org.thoughtcrime.securesms.keyvalue.SmsExportPhase;
>>>>>>> 55894bc674 ( Inital commit. Re-enable SMS sending. Remove SMS export megaphone.)
||||||| 35807f725b
=======
||||||| 69e1146e2c
=======
>>>>>>> upstream/main
>>>>>>> 94387f59e83f9be48a18536ad0b22f950783b09e
>>>>>>> e80bceeb3a3de89c781e259a97c5b8344e20afe5
import org.thoughtcrime.securesms.lock.SignalPinReminderDialog;
import org.thoughtcrime.securesms.lock.SignalPinReminders;
import org.thoughtcrime.securesms.lock.v2.CreateSvrPinActivity;
import org.thoughtcrime.securesms.lock.v2.SvrMigrationActivity;
import org.thoughtcrime.securesms.notifications.NotificationChannels;
import org.thoughtcrime.securesms.notifications.TurnOnNotificationsBottomSheet;
import org.thoughtcrime.securesms.profiles.AvatarHelper;
import org.thoughtcrime.securesms.profiles.manage.EditProfileActivity;
import org.thoughtcrime.securesms.profiles.username.NewWaysToConnectDialogFragment;
import org.thoughtcrime.securesms.recipients.Recipient;
import org.thoughtcrime.securesms.storage.StorageSyncHelper;
import org.thoughtcrime.securesms.util.RemoteConfig;
import org.thoughtcrime.securesms.util.ServiceUtil;
import org.thoughtcrime.securesms.util.dynamiclanguage.DynamicLanguageContextWrapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Creating a new megaphone:
 * - Add an enum to {@link Event}
 * - Return a megaphone in {@link #forRecord(Context, MegaphoneRecord)}
 * - Include the event in {@link #buildDisplayOrder(Context, Map)}
 * <p>
 * Common patterns:
 * - For events that have a snooze-able recurring display schedule, use a {@link RecurringSchedule}.
 * - For events guarded by feature flags, set a {@link ForeverSchedule} with false in
 * {@link #buildDisplayOrder(Context, Map)}.
 * - For events that change, return different megaphones in {@link #forRecord(Context, MegaphoneRecord)}
 * based on whatever properties you're interested in.
 */
public final class Megaphones {

  private static final String TAG = Log.tag(Megaphones.class);

  private static final MegaphoneSchedule ALWAYS = new ForeverSchedule(true);
  private static final MegaphoneSchedule NEVER  = new ForeverSchedule(false);

  private static final Set<Event> DONATE_EVENTS                      = SetUtil.newHashSet(Event.BECOME_A_SUSTAINER, Event.DONATE_Q2_2022);
  private static final long       MIN_TIME_BETWEEN_DONATE_MEGAPHONES = TimeUnit.DAYS.toMillis(30);

  private Megaphones() {}

  @WorkerThread
  static @Nullable Megaphone getNextMegaphone(@NonNull Context context, @NonNull Map<Event, MegaphoneRecord> records) {
    long currentTime = System.currentTimeMillis();

    List<Megaphone> megaphones = Stream.of(buildDisplayOrder(context, records))
                                       .filter(e -> {
                                         MegaphoneRecord   record   = Objects.requireNonNull(records.get(e.getKey()));
                                         MegaphoneSchedule schedule = e.getValue();

                                         return !record.isFinished() && schedule.shouldDisplay(record.getSeenCount(), record.getLastSeen(), record.getFirstVisible(), currentTime);
                                       })
                                       .map(Map.Entry::getKey)
                                       .map(records::get)
                                       .map(record -> Megaphones.forRecord(context, record))
                                       .filterNot(Objects::isNull)
                                       .toList();

    if (megaphones.size() > 0) {
      return megaphones.get(0);
    } else {
      return null;
    }
  }

  /**
   * The megaphones we want to display *in priority order*. This is a {@link LinkedHashMap}, so order is preserved.
   * We will render the first applicable megaphone in this collection.
   * <p>
   * This is also when you would hide certain megaphones based on things like {@link RemoteConfig}.
   */
  private static Map<Event, MegaphoneSchedule> buildDisplayOrder(@NonNull Context context, @NonNull Map<Event, MegaphoneRecord> records) {
    return new LinkedHashMap<>() {{
      put(Event.PINS_FOR_ALL, new PinsForAllSchedule());
      put(Event.CLIENT_DEPRECATED, SignalStore.misc().isClientDeprecated() ? ALWAYS : NEVER);
      put(Event.NOTIFICATIONS, shouldShowNotificationsMegaphone(context) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(30)) : NEVER);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
||||||| 35807f725b
=======
<<<<<<< HEAD
||||||| 69e1146e2c
=======
<<<<<<< HEAD
      //put(Event.SMS_EXPORT, new SmsExportReminderSchedule(context));
      put(Event.BACKUP_SCHEDULE_PERMISSION, shouldShowBackupSchedulePermissionMegaphone(context) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(3)) : NEVER);
      put(Event.ONBOARDING, shouldShowOnboardingMegaphone(context) ? ALWAYS : NEVER);
      put(Event.TURN_OFF_CENSORSHIP_CIRCUMVENTION, shouldShowTurnOffCircumventionMegaphone() ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(7)) : NEVER);
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
=======
>>>>>>> 94387f59e83f9be48a18536ad0b22f950783b09e
>>>>>>> e80bceeb3a3de89c781e259a97c5b8344e20afe5
      put(Event.GRANT_FULL_SCREEN_INTENT, shouldShowGrantFullScreenIntentPermission(context) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(3)) : NEVER);
||||||| parent of 55894bc674 ( Inital commit. Re-enable SMS sending. Remove SMS export megaphone.)
      put(Event.SMS_EXPORT, new SmsExportReminderSchedule(context));
=======
      //put(Event.SMS_EXPORT, new SmsExportReminderSchedule(context));
>>>>>>> 55894bc674 ( Inital commit. Re-enable SMS sending. Remove SMS export megaphone.)
||||||| parent of 55894bc674 ( Inital commit. Re-enable SMS sending. Remove SMS export megaphone.)
      put(Event.SMS_EXPORT, new SmsExportReminderSchedule(context));
=======
      //put(Event.SMS_EXPORT, new SmsExportReminderSchedule(context));
>>>>>>> 55894bc674 ( Inital commit. Re-enable SMS sending. Remove SMS export megaphone.)
      put(Event.BACKUP_SCHEDULE_PERMISSION, shouldShowBackupSchedulePermissionMegaphone(context) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(3)) : NEVER);
      put(Event.ONBOARDING, shouldShowOnboardingMegaphone(context) ? ALWAYS : NEVER);
      put(Event.TURN_OFF_CENSORSHIP_CIRCUMVENTION, shouldShowTurnOffCircumventionMegaphone() ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(7)) : NEVER);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> f611d03385 (Added extra options)
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
      put(Event.LINKED_DEVICE_INACTIVE, shouldShowLinkedDeviceInactiveMegaphone() ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(3)): NEVER);
<<<<<<< HEAD
||||||| parent of 8a72cb26f4 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
||||||| parent of 66c339aa35 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> 66c339aa35 (Added extra options)
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
||||||| parent of 775ec008cc (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> 775ec008cc (Added extra options)
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
||||||| parent of 6d8fef5835 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> 6d8fef5835 (Added extra options)
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
||||||| parent of 246bbae757 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> 246bbae757 (Added extra options)
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
||||||| parent of c5d82267d1 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> c5d82267d1 (Added extra options)
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
||||||| parent of 19863d0faa (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> 19863d0faa (Added extra options)
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
||||||| parent of 55729c14e3 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> 55729c14e3 (Added extra options)
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
||||||| parent of f050803628 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> f050803628 (Added extra options)
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
||||||| parent of 69c4403d63 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> 69c4403d63 (Added extra options)
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
||||||| parent of 7d4bd94d26 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> 7d4bd94d26 (Added extra options)
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
||||||| parent of efc40a1af7 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> efc40a1af7 (Added extra options)
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of cdbbc46ede (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
||||||| parent of 36da7332d2 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> 36da7332d2 (Added extra options)
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> cdbbc46ede (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
||||||| parent of e4396c39f9 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> e4396c39f9 (Added extra options)
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
||||||| parent of e64c4c41bb (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> e64c4c41bb (Added extra options)
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
||||||| parent of e26890a182 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> e26890a182 (Added extra options)
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
||||||| parent of f611d03385 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
>>>>>>> f611d03385 (Added extra options)
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
      put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER);
      put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER);
=======
      //put(Event.DONATE_Q2_2022, shouldShowDonateMegaphone(context, Event.DONATE_Q2_2022, records) ? ShowForDurationSchedule.showForDays(7) : NEVER); // JW
      //put(Event.REMOTE_MEGAPHONE, shouldShowRemoteMegaphone(records) ? RecurringSchedule.every(TimeUnit.DAYS.toMillis(1)) : NEVER); // JW
>>>>>>> 6b57469a94 (Added extra options)
||||||| 69e1146e2c
=======
>>>>>>> upstream/main
>>>>>>> 94387f59e83f9be48a18536ad0b22f950783b09e
      put(Event.PIN_REMINDER, new SignalPinReminderSchedule());
      put(Event.SET_UP_YOUR_USERNAME, shouldShowSetUpYourUsernameMegaphone(records) ? ALWAYS : NEVER);

      // Feature-introduction megaphones should *probably* be added below this divider
      put(Event.ADD_A_PROFILE_PHOTO, shouldShowAddAProfilePhotoMegaphone(context) ? ALWAYS : NEVER);
      put(Event.PNP_LAUNCH, shouldShowPnpLaunchMegaphone() ? ALWAYS : NEVER);
    }};
  }

  private static boolean shouldShowLinkedDeviceInactiveMegaphone() {
    LeastActiveLinkedDevice device = SignalStore.misc().getLeastActiveLinkedDevice();
    if (device == null) {
      return false;
    }

    long expiringAt = device.lastActiveTimestamp + RemoteConfig.getLinkedDeviceLifespan();
    long expiringIn = Math.max(expiringAt - System.currentTimeMillis(), 0);

    return expiringIn < TimeUnit.DAYS.toMillis(7) && expiringIn > 0;
  }

  private static @Nullable Megaphone forRecord(@NonNull Context context, @NonNull MegaphoneRecord record) {
    switch (record.getEvent()) {
      case PINS_FOR_ALL:
        return buildPinsForAllMegaphone(record);
      case PIN_REMINDER:
        return buildPinReminderMegaphone(context);
      case CLIENT_DEPRECATED:
        return buildClientDeprecatedMegaphone(context);
      case ONBOARDING:
        return buildOnboardingMegaphone();
      case NOTIFICATIONS:
        return buildNotificationsMegaphone(context);
      case ADD_A_PROFILE_PHOTO:
        return buildAddAProfilePhotoMegaphone(context);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
||||||| 35807f725b
=======
<<<<<<< HEAD
>>>>>>> e80bceeb3a3de89c781e259a97c5b8344e20afe5
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of cdbbc46ede (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> cdbbc46ede (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                            // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                            // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                           // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                           // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                           // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 6b57469a94 (Added extra options)
<<<<<<< HEAD
||||||| parent of 66c339aa35 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                             // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                            // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                            // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                           // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                           // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
      case BECOME_A_SUSTAINER:
        return buildBecomeASustainerMegaphone(context);
      case DONATE_Q2_2022:
        return buildDonateQ2Megaphone(context);
=======
//      case BECOME_A_SUSTAINER:                           // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
>>>>>>> 6b57469a94 (Added extra options)
||||||| 35807f725b
=======
||||||| 69e1146e2c
=======
<<<<<<< HEAD
//      case BECOME_A_SUSTAINER:                           // JW I see these way too often
//        return buildBecomeASustainerMegaphone(context);
//      case DONATE_Q2_2022:
//        return buildDonateQ2Megaphone(context);
      case TURN_OFF_CENSORSHIP_CIRCUMVENTION:
        return buildTurnOffCircumventionMegaphone(context);
//      case REMOTE_MEGAPHONE:
//        return buildRemoteMegaphone(context);
      case BACKUP_SCHEDULE_PERMISSION:
        return buildBackupPermissionMegaphone(context);
      //case SMS_EXPORT:
      //  return buildSmsExportMegaphone(context);
=======
>>>>>>> 94387f59e83f9be48a18536ad0b22f950783b09e
>>>>>>> e80bceeb3a3de89c781e259a97c5b8344e20afe5
      case TURN_OFF_CENSORSHIP_CIRCUMVENTION:
        return buildTurnOffCircumventionMegaphone(context);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
      case LINKED_DEVICE_INACTIVE:
        return buildLinkedDeviceInactiveMegaphone(context);
      case REMOTE_MEGAPHONE:
        return buildRemoteMegaphone(context);
||||||| parent of 83146b3342 (Added extra options)
      case REMOTE_MEGAPHONE:
        return buildRemoteMegaphone(context);
=======
//      case REMOTE_MEGAPHONE:
//        return buildRemoteMegaphone(context);
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
      case REMOTE_MEGAPHONE:
        return buildRemoteMegaphone(context);
=======
//      case REMOTE_MEGAPHONE:
//        return buildRemoteMegaphone(context);
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
      case REMOTE_MEGAPHONE:
        return buildRemoteMegaphone(context);
=======
//      case REMOTE_MEGAPHONE:
//        return buildRemoteMegaphone(context);
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
      case REMOTE_MEGAPHONE:
        return buildRemoteMegaphone(context);
=======
//      case REMOTE_MEGAPHONE:
//        return buildRemoteMegaphone(context);
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
      case REMOTE_MEGAPHONE:
        return buildRemoteMegaphone(context);
=======
//      case REMOTE_MEGAPHONE:
//        return buildRemoteMegaphone(context);
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
      case REMOTE_MEGAPHONE:
        return buildRemoteMegaphone(context);
=======
//      case REMOTE_MEGAPHONE:
//        return buildRemoteMegaphone(context);
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
      case REMOTE_MEGAPHONE:
        return buildRemoteMegaphone(context);
=======
//      case REMOTE_MEGAPHONE:
//        return buildRemoteMegaphone(context);
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
      case REMOTE_MEGAPHONE:
        return buildRemoteMegaphone(context);
=======
//      case REMOTE_MEGAPHONE:
//        return buildRemoteMegaphone(context);
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
      case REMOTE_MEGAPHONE:
        return buildRemoteMegaphone(context);
=======
//      case REMOTE_MEGAPHONE:
//        return buildRemoteMegaphone(context);
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
      case REMOTE_MEGAPHONE:
        return buildRemoteMegaphone(context);
=======
//      case REMOTE_MEGAPHONE:
//        return buildRemoteMegaphone(context);
>>>>>>> 6b57469a94 (Added extra options)
      case BACKUP_SCHEDULE_PERMISSION:
        return buildBackupPermissionMegaphone(context);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
||||||| parent of 55894bc674 ( Inital commit. Re-enable SMS sending. Remove SMS export megaphone.)
      case SMS_EXPORT:
        return buildSmsExportMegaphone(context);
=======
      //case SMS_EXPORT:
      //  return buildSmsExportMegaphone(context);
>>>>>>> 55894bc674 ( Inital commit. Re-enable SMS sending. Remove SMS export megaphone.)
||||||| 35807f725b
=======
<<<<<<< HEAD
>>>>>>> e80bceeb3a3de89c781e259a97c5b8344e20afe5
||||||| parent of 55894bc674 ( Inital commit. Re-enable SMS sending. Remove SMS export megaphone.)
      case SMS_EXPORT:
        return buildSmsExportMegaphone(context);
=======
      //case SMS_EXPORT:
      //  return buildSmsExportMegaphone(context);
>>>>>>> 55894bc674 ( Inital commit. Re-enable SMS sending. Remove SMS export megaphone.)
||||||| 69e1146e2c
=======
>>>>>>> upstream/main
>>>>>>> 94387f59e83f9be48a18536ad0b22f950783b09e
      case SET_UP_YOUR_USERNAME:
        return buildSetUpYourUsernameMegaphone(context);
      case GRANT_FULL_SCREEN_INTENT:
        return buildGrantFullScreenIntentPermission(context);
      case PNP_LAUNCH:
        return buildPnpLaunchMegaphone();
      default:
        throw new IllegalArgumentException("Event not handled!");
    }
  }

  private static Megaphone buildLinkedDeviceInactiveMegaphone(Context context) {
    LeastActiveLinkedDevice device = SignalStore.misc().getLeastActiveLinkedDevice();
    if (device == null) {
      throw new IllegalStateException("No linked device to show");
    }

    long expiringAt   = device.lastActiveTimestamp + RemoteConfig.getLinkedDeviceLifespan();
    long expiringIn   = Math.max(expiringAt - System.currentTimeMillis(), 0);
    int  expiringDays = (int) TimeUnit.MILLISECONDS.toDays(expiringIn);

    return new Megaphone.Builder(Event.LINKED_DEVICE_INACTIVE, Megaphone.Style.BASIC)
        .setTitle(R.string.LinkedDeviceInactiveMegaphone_title)
        .setBody(context.getResources().getQuantityString(R.plurals.LinkedDeviceInactiveMegaphone_body, expiringDays, device.name, expiringDays))
        .setImage(R.drawable.ic_inactive_linked_device)
        .setActionButton(R.string.LinkedDeviceInactiveMegaphone_got_it_button_label, (megaphone, listener) -> {
          listener.onMegaphoneSnooze(Event.LINKED_DEVICE_INACTIVE);
        })
        .setSecondaryButton(R.string.LinkedDeviceInactiveMegaphone_dont_remind_button_label, (megaphone, listener) -> {
          listener.onMegaphoneCompleted(Event.LINKED_DEVICE_INACTIVE);
        })
        .build();
  }

  private static @NonNull Megaphone buildPinsForAllMegaphone(@NonNull MegaphoneRecord record) {
    if (PinsForAllSchedule.shouldDisplayFullScreen(record.getFirstVisible(), System.currentTimeMillis())) {
      return new Megaphone.Builder(Event.PINS_FOR_ALL, Megaphone.Style.FULLSCREEN)
          .enableSnooze(null)
          .setOnVisibleListener((megaphone, listener) -> {
            if (new NetworkConstraint.Factory(AppDependencies.getApplication()).create().isMet()) {
              listener.onMegaphoneNavigationRequested(SvrMigrationActivity.createIntent(), SvrMigrationActivity.REQUEST_NEW_PIN);
            }
          })
          .build();
    } else {
      return new Megaphone.Builder(Event.PINS_FOR_ALL, Megaphone.Style.BASIC)
          .setImage(R.drawable.kbs_pin_megaphone)
          .setTitle(R.string.KbsMegaphone__create_a_pin)
          .setBody(R.string.KbsMegaphone__pins_keep_information_thats_stored_with_signal_encrytped)
          .setActionButton(R.string.KbsMegaphone__create_pin, (megaphone, listener) -> {
            Intent intent = CreateSvrPinActivity.getIntentForPinCreate(AppDependencies.getApplication());

            listener.onMegaphoneNavigationRequested(intent, CreateSvrPinActivity.REQUEST_NEW_PIN);
          })
          .build();
    }
  }

  @SuppressWarnings("CodeBlock2Expr")
  private static @NonNull Megaphone buildPinReminderMegaphone(@NonNull Context context) {
    return new Megaphone.Builder(Event.PIN_REMINDER, Megaphone.Style.BASIC)
        .setTitle(R.string.Megaphones_verify_your_signal_pin)
        .setBody(R.string.Megaphones_well_occasionally_ask_you_to_verify_your_pin)
        .setImage(R.drawable.kbs_pin_megaphone)
        .setActionButton(R.string.Megaphones_verify_pin, (megaphone, controller) -> {
          SignalPinReminderDialog.show(controller.getMegaphoneActivity(), controller::onMegaphoneNavigationRequested, new SignalPinReminderDialog.Callback() {
            @Override
            public void onReminderDismissed(boolean includedFailure) {
              Log.i(TAG, "[PinReminder] onReminderDismissed(" + includedFailure + ")");
              if (includedFailure) {
                SignalStore.pin().onEntrySkipWithWrongGuess();
              }
            }

            @Override
            public void onReminderCompleted(@NonNull String pin, boolean includedFailure) {
              Log.i(TAG, "[PinReminder] onReminderCompleted(" + includedFailure + ")");
              if (includedFailure) {
                SignalStore.pin().onEntrySuccessWithWrongGuess(pin);
              } else {
                SignalStore.pin().onEntrySuccess(pin);
              }

              controller.onMegaphoneSnooze(Event.PIN_REMINDER);
              controller.onMegaphoneToastRequested(controller.getMegaphoneActivity().getString(SignalPinReminders.getReminderString(SignalStore.pin().getCurrentInterval())));
            }
          });
        })
        .build();
  }

  private static @NonNull Megaphone buildClientDeprecatedMegaphone(@NonNull Context context) {
    return new Megaphone.Builder(Event.CLIENT_DEPRECATED, Megaphone.Style.FULLSCREEN)
        .disableSnooze()
        .setOnVisibleListener((megaphone, listener) -> listener.onMegaphoneNavigationRequested(new Intent(context, ClientDeprecatedActivity.class)))
        .build();
  }

  private static @NonNull Megaphone buildOnboardingMegaphone() {
    return new Megaphone.Builder(Event.ONBOARDING, Megaphone.Style.ONBOARDING)
        .build();
  }

  private static @NonNull Megaphone buildNotificationsMegaphone(@NonNull Context context) {
    return new Megaphone.Builder(Event.NOTIFICATIONS, Megaphone.Style.BASIC)
        .setTitle(R.string.NotificationsMegaphone_turn_on_notifications)
        .setBody(R.string.NotificationsMegaphone_never_miss_a_message)
        .setImage(R.drawable.megaphone_notifications_64)
        .setActionButton(R.string.NotificationsMegaphone_turn_on, (megaphone, controller) -> {
          if (Build.VERSION.SDK_INT >= 26) {
            controller.onMegaphoneDialogFragmentRequested(TurnOnNotificationsBottomSheet.turnOnSystemNotificationsFragment(context));
          } else {
            controller.onMegaphoneNavigationRequested(AppSettingsActivity.notifications(context));
          }
        })
        .setSecondaryButton(R.string.NotificationsMegaphone_not_now, (megaphone, controller) -> controller.onMegaphoneSnooze(Event.NOTIFICATIONS))
        .build();
  }

  private static @NonNull Megaphone buildAddAProfilePhotoMegaphone(@NonNull Context context) {
    return new Megaphone.Builder(Event.ADD_A_PROFILE_PHOTO, Megaphone.Style.BASIC)
        .setTitle(R.string.AddAProfilePhotoMegaphone__add_a_profile_photo)
        .setImage(R.drawable.ic_add_a_profile_megaphone_image)
        .setBody(R.string.AddAProfilePhotoMegaphone__choose_a_look_and_color)
        .setActionButton(R.string.AddAProfilePhotoMegaphone__add_photo, (megaphone, listener) -> {
          listener.onMegaphoneNavigationRequested(EditProfileActivity.getIntentForAvatarEdit(context));
          listener.onMegaphoneCompleted(Event.ADD_A_PROFILE_PHOTO);
        })
        .setSecondaryButton(R.string.AddAProfilePhotoMegaphone__not_now, (megaphone, listener) -> {
          listener.onMegaphoneCompleted(Event.ADD_A_PROFILE_PHOTO);
        })
        .build();
  }

  private static @NonNull Megaphone buildTurnOffCircumventionMegaphone(@NonNull Context context) {
    return new Megaphone.Builder(Event.TURN_OFF_CENSORSHIP_CIRCUMVENTION, Megaphone.Style.BASIC)
        .setTitle(R.string.CensorshipCircumventionMegaphone_turn_off_censorship_circumvention)
        .setImage(R.drawable.ic_censorship_megaphone_64)
        .setBody(R.string.CensorshipCircumventionMegaphone_you_can_now_connect_to_the_signal_service)
        .setActionButton(R.string.CensorshipCircumventionMegaphone_turn_off, (megaphone, listener) -> {
          SignalStore.settings().setCensorshipCircumventionEnabled(false);
          listener.onMegaphoneSnooze(Event.TURN_OFF_CENSORSHIP_CIRCUMVENTION);
        })
        .setSecondaryButton(R.string.CensorshipCircumventionMegaphone_no_thanks, (megaphone, listener) -> {
          listener.onMegaphoneSnooze(Event.TURN_OFF_CENSORSHIP_CIRCUMVENTION);
        })
        .build();
  }

  private static @Nullable Megaphone buildRemoteMegaphone(@NonNull Context context) {
    RemoteMegaphoneRecord record = RemoteMegaphoneRepository.getRemoteMegaphoneToShow(System.currentTimeMillis());

    if (record == null) {
      Log.w(TAG, "No remote megaphone record when told to show one!");
      return null;
    }

    Megaphone.Builder builder = new Megaphone.Builder(Event.REMOTE_MEGAPHONE, Megaphone.Style.BASIC)
        .setTitle(record.getTitle())
        .setBody(record.getBody());

    if (record.getImageUri() != null) {
      builder.setImageRequestBuilder(Glide.with(context).asDrawable().load(record.getImageUri()));
    }

    if (record.hasPrimaryAction()) {
      //noinspection ConstantConditions
      builder.setActionButton(record.getPrimaryActionText(), (megaphone, controller) -> {
        RemoteMegaphoneRepository.getAction(Objects.requireNonNull(record.getPrimaryActionId()))
                                 .run(context, controller, record);
      });
    }

    if (record.hasSecondaryAction()) {
      //noinspection ConstantConditions
      builder.setSecondaryButton(record.getSecondaryActionText(), (megaphone, controller) -> {
        RemoteMegaphoneRepository.getAction(Objects.requireNonNull(record.getSecondaryActionId()))
                                 .run(context, controller, record);
      });
    }

    builder.setOnVisibleListener((megaphone, controller) -> {
      RemoteMegaphoneRepository.markShown(record.getUuid());
    });

    return builder.build();
  }

  @SuppressLint("InlinedApi")
  private static Megaphone buildBackupPermissionMegaphone(@NonNull Context context) {
    return new Megaphone.Builder(Event.BACKUP_SCHEDULE_PERMISSION, Megaphone.Style.BASIC)
        .setTitle(R.string.BackupSchedulePermissionMegaphone__cant_back_up_chats)
        .setImage(R.drawable.ic_cant_backup_megaphone)
        .setBody(R.string.BackupSchedulePermissionMegaphone__your_chats_are_no_longer_being_automatically_backed_up)
        .setActionButton(R.string.BackupSchedulePermissionMegaphone__back_up_chats, (megaphone, controller) -> {
          controller.onMegaphoneDialogFragmentRequested(new ReenableBackupsDialogFragment());
        })
        .setSecondaryButton(R.string.BackupSchedulePermissionMegaphone__not_now, (megaphone, controller) -> {
          controller.onMegaphoneSnooze(Event.BACKUP_SCHEDULE_PERMISSION);
        })
        .build();
  }

<<<<<<< HEAD
<<<<<<< HEAD
||||||| parent of 55894bc674 ( Inital commit. Re-enable SMS sending. Remove SMS export megaphone.)
  private static @NonNull Megaphone buildSmsExportMegaphone(@NonNull Context context) {
||||||| parent of 55894bc674 ( Inital commit. Re-enable SMS sending. Remove SMS export megaphone.)
  private static @NonNull Megaphone buildSmsExportMegaphone(@NonNull Context context) {
=======
  /*private static @NonNull Megaphone buildSmsExportMegaphone(@NonNull Context context) {
>>>>>>> 55894bc674 ( Inital commit. Re-enable SMS sending. Remove SMS export megaphone.)
    SmsExportPhase phase = SignalStore.misc().getSmsExportPhase();

    if (phase == SmsExportPhase.PHASE_1) {
      return new Megaphone.Builder(Event.SMS_EXPORT, Megaphone.Style.BASIC)
          .setTitle(R.string.SmsExportMegaphone__sms_support_going_away)
          .setImage(R.drawable.sms_megaphone)
          .setBody(R.string.SmsExportMegaphone__dont_worry_encrypted_signal_messages_will_continue_to_work)
          .setActionButton(R.string.SmsExportMegaphone__continue, (megaphone, controller) -> {
            controller.onMegaphoneSnooze(Event.SMS_EXPORT);
            controller.onMegaphoneNavigationRequested(SmsExportActivity.createIntent(context, true), SmsExportMegaphoneActivity.REQUEST_CODE);
          })
          .setSecondaryButton(R.string.Megaphones_remind_me_later, (megaphone, controller) -> controller.onMegaphoneSnooze(Event.SMS_EXPORT))
          .setOnVisibleListener((megaphone, controller) -> SignalStore.misc().startSmsPhase1())
          .build();
    } else {
      Megaphone.Builder builder = new Megaphone.Builder(Event.SMS_EXPORT, Megaphone.Style.FULLSCREEN)
          .setOnVisibleListener((megaphone, controller) -> {
            if (phase.isBlockingUi()) {
              SmsExportReminderSchedule.setShowPhase3Megaphone(false);
            }
            controller.onMegaphoneNavigationRequested(new Intent(context, SmsExportMegaphoneActivity.class), SmsExportMegaphoneActivity.REQUEST_CODE);
          });

      if (phase.isBlockingUi()) {
        builder.disableSnooze();
      }

      return builder.build();
    }
  }
<<<<<<< HEAD

=======
  /*private static @NonNull Megaphone buildSmsExportMegaphone(@NonNull Context context) {
    SmsExportPhase phase = SignalStore.misc().getSmsExportPhase();

    if (phase == SmsExportPhase.PHASE_1) {
      return new Megaphone.Builder(Event.SMS_EXPORT, Megaphone.Style.BASIC)
          .setTitle(R.string.SmsExportMegaphone__sms_support_going_away)
          .setImage(R.drawable.sms_megaphone)
          .setBody(R.string.SmsExportMegaphone__dont_worry_encrypted_signal_messages_will_continue_to_work)
          .setActionButton(R.string.SmsExportMegaphone__continue, (megaphone, controller) -> {
            controller.onMegaphoneSnooze(Event.SMS_EXPORT);
            controller.onMegaphoneNavigationRequested(SmsExportActivity.createIntent(context, true), SmsExportMegaphoneActivity.REQUEST_CODE);
          })
          .setSecondaryButton(R.string.Megaphones_remind_me_later, (megaphone, controller) -> controller.onMegaphoneSnooze(Event.SMS_EXPORT))
          .setOnVisibleListener((megaphone, controller) -> SignalStore.misc().startSmsPhase1())
          .build();
    } else {
      Megaphone.Builder builder = new Megaphone.Builder(Event.SMS_EXPORT, Megaphone.Style.FULLSCREEN)
          .setOnVisibleListener((megaphone, controller) -> {
            if (phase.isBlockingUi()) {
              SmsExportReminderSchedule.setShowPhase3Megaphone(false);
            }
            controller.onMegaphoneNavigationRequested(new Intent(context, SmsExportMegaphoneActivity.class), SmsExportMegaphoneActivity.REQUEST_CODE);
          });

      if (phase.isBlockingUi()) {
        builder.disableSnooze();
      }

      return builder.build();
    }
  }
*/
>>>>>>> 55894bc674 ( Inital commit. Re-enable SMS sending. Remove SMS export megaphone.)
<<<<<<< HEAD
||||||| parent of 55894bc674 ( Inital commit. Re-enable SMS sending. Remove SMS export megaphone.)

=======
*/
>>>>>>> 55894bc674 ( Inital commit. Re-enable SMS sending. Remove SMS export megaphone.)
||||||| 35807f725b
=======
||||||| 69e1146e2c
=======
<<<<<<< HEAD
  /*
  private static @NonNull Megaphone buildSmsExportMegaphone(@NonNull Context context) {
    SmsExportPhase phase = SignalStore.misc().getSmsExportPhase();

    Megaphone.Builder builder = new Megaphone.Builder(Event.SMS_EXPORT, Megaphone.Style.FULLSCREEN)
        .setOnVisibleListener((megaphone, controller) -> {
          if (phase.isBlockingUi()) {
            SmsExportReminderSchedule.setShowPhase3Megaphone(false);
          }
          controller.onMegaphoneNavigationRequested(new Intent(context, SmsExportMegaphoneActivity.class), SmsExportMegaphoneActivity.REQUEST_CODE);
        });

    if (phase.isBlockingUi()) {
      builder.disableSnooze();
    }

    return builder.build();
  }
*/
=======
>>>>>>> upstream/main
>>>>>>> 94387f59e83f9be48a18536ad0b22f950783b09e
>>>>>>> e80bceeb3a3de89c781e259a97c5b8344e20afe5
  public static @NonNull Megaphone buildSetUpYourUsernameMegaphone(@NonNull Context context) {
    return new Megaphone.Builder(Event.SET_UP_YOUR_USERNAME, Megaphone.Style.BASIC)
        .setTitle(R.string.NewWaysToConnectDialogFragment__new_ways_to_connect)
        .setBody(R.string.SetUpYourUsername__introducing_phone_number_privacy)
        .setImage(R.drawable.usernames_megaphone)
        .setActionButton(R.string.SetUpYourUsername__learn_more, (megaphone, controller) -> {
          controller.onMegaphoneDialogFragmentRequested(new NewWaysToConnectDialogFragment());
        })
        .setSecondaryButton(R.string.SetUpYourUsername__dismiss, (megaphone, controller) -> {
          controller.onMegaphoneCompleted(Event.SET_UP_YOUR_USERNAME);
        })
        .build();
  }

  public static @NonNull Megaphone buildPnpLaunchMegaphone() {
    return new Megaphone.Builder(Event.PNP_LAUNCH, Megaphone.Style.BASIC)
        .setTitle(R.string.PnpLaunchMegaphone_title)
        .setBody(R.string.PnpLaunchMegaphone_body)
        .setImage(R.drawable.usernames_megaphone)
        .setActionButton(R.string.PnpLaunchMegaphone_learn_more, (megaphone, controller) -> {
          controller.onMegaphoneDialogFragmentRequested(new NewWaysToConnectDialogFragment());
          controller.onMegaphoneCompleted(Event.PNP_LAUNCH);

          SignalStore.uiHints().setHasCompletedUsernameOnboarding(true);
          SignalDatabase.recipients().markNeedsSync(Recipient.self().getId());
          StorageSyncHelper.scheduleSyncForDataChange();
        })
        .setSecondaryButton(R.string.PnpLaunchMegaphone_dismiss, (megaphone, controller) -> {
          controller.onMegaphoneCompleted(Event.PNP_LAUNCH);

          SignalStore.uiHints().setHasCompletedUsernameOnboarding(true);
          SignalDatabase.recipients().markNeedsSync(Recipient.self().getId());
          StorageSyncHelper.scheduleSyncForDataChange();
        })
        .build();
  }

  @SuppressLint("NewApi")
  public static @NonNull Megaphone buildGrantFullScreenIntentPermission(@NonNull Context context) {
    return new Megaphone.Builder(Event.GRANT_FULL_SCREEN_INTENT, Megaphone.Style.BASIC)
        .setTitle(R.string.GrantFullScreenIntentPermission_megaphone_title)
        .setBody(R.string.GrantFullScreenIntentPermission_megaphone_body)
        .setImage(R.drawable.calling_64)
        .setActionButton(R.string.GrantFullScreenIntentPermission_megaphone_turn_on, (megaphone, controller) -> {
          controller.onMegaphoneDialogFragmentRequested(TurnOnNotificationsBottomSheet.turnOnFullScreenIntentFragment(context));
        })
        .setSecondaryButton(R.string.GrantFullScreenIntentPermission_megaphone_not_now, (megaphone, controller) -> {
          controller.onMegaphoneCompleted(Event.GRANT_FULL_SCREEN_INTENT);
        })
        .build();
  }

  private static boolean shouldShowOnboardingMegaphone(@NonNull Context context) {
    return SignalStore.onboarding().hasOnboarding(context);
  }

  private static boolean shouldShowTurnOffCircumventionMegaphone() {
    return AppDependencies.getSignalServiceNetworkAccess().isCensored() &&
           SignalStore.misc().isServiceReachableWithoutCircumvention();
  }

  private static boolean shouldShowNotificationsMegaphone(@NonNull Context context) {
    boolean shouldShow = !SignalStore.settings().isMessageNotificationsEnabled() ||
                         !NotificationChannels.getInstance().isMessageChannelEnabled() ||
                         !NotificationChannels.getInstance().isMessagesChannelGroupEnabled() ||
                         !NotificationChannels.getInstance().areNotificationsEnabled();
    if (shouldShow) {
      Locale locale = DynamicLanguageContextWrapper.getUsersSelectedLocale(context);
      if (!new TranslationDetection(context, locale)
          .textExistsInUsersLanguage(R.string.NotificationsMegaphone_turn_on_notifications,
                                     R.string.NotificationsMegaphone_never_miss_a_message,
                                     R.string.NotificationsMegaphone_turn_on,
                                     R.string.NotificationsMegaphone_not_now))
      {
        Log.i(TAG, "Would show NotificationsMegaphone but is not yet translated in " + locale);
        return false;
      }
    }
    return shouldShow;
  }

  private static boolean shouldShowAddAProfilePhotoMegaphone(@NonNull Context context) {
    if (SignalStore.misc().getHasEverHadAnAvatar()) {
      return false;
    }

    boolean hasAnAvatar = AvatarHelper.hasAvatar(context, Recipient.self().getId());
    if (hasAnAvatar) {
      SignalStore.misc().setHasEverHadAnAvatar(true);
      return false;
    }

    return true;
  }

  /**
   * Prompt megaphone 3 days after turning off phone number discovery when no username is set.
   */
  private static boolean shouldShowSetUpYourUsernameMegaphone(@NonNull Map<Event, MegaphoneRecord> records) {
    boolean                        hasUsername                    = SignalStore.account().isRegistered() && SignalStore.account().getUsername() != null;
    boolean                        hasCompleted                   = MapUtil.mapOrDefault(records, Event.SET_UP_YOUR_USERNAME, MegaphoneRecord::isFinished, false);
    long                           phoneNumberDiscoveryDisabledAt = SignalStore.phoneNumberPrivacy().getPhoneNumberDiscoverabilityModeTimestamp();
    PhoneNumberDiscoverabilityMode listingMode                    = SignalStore.phoneNumberPrivacy().getPhoneNumberDiscoverabilityMode();

    return !hasUsername &&
           listingMode == PhoneNumberDiscoverabilityMode.NOT_DISCOVERABLE &&
           !hasCompleted &&
           phoneNumberDiscoveryDisabledAt > 0 &&
           (System.currentTimeMillis() - phoneNumberDiscoveryDisabledAt) >= TimeUnit.DAYS.toMillis(3);
  }

  private static boolean shouldShowPnpLaunchMegaphone() {
    return TextUtils.isEmpty(SignalStore.account().getUsername()) && !SignalStore.uiHints().hasCompletedUsernameOnboarding();
  }

  private static boolean shouldShowGrantFullScreenIntentPermission(@NonNull Context context) {
    return Build.VERSION.SDK_INT >= 34 && !NotificationManagerCompat.from(context).canUseFullScreenIntent();
  }

  @WorkerThread
  private static boolean shouldShowRemoteMegaphone(@NonNull Map<Event, MegaphoneRecord> records) {
    boolean canShowLocalDonate = timeSinceLastDonatePrompt(Event.REMOTE_MEGAPHONE, records) > MIN_TIME_BETWEEN_DONATE_MEGAPHONES;
    return RemoteMegaphoneRepository.hasRemoteMegaphoneToShow(canShowLocalDonate);
  }

  private static boolean shouldShowBackupSchedulePermissionMegaphone(@NonNull Context context) {
    return Build.VERSION.SDK_INT >= 31 && SignalStore.settings().isBackupEnabled() && !ServiceUtil.getAlarmManager(context).canScheduleExactAlarms();
  }

  /**
   * Unfortunately lastSeen is only set today upon snoozing, which never happens to donate prompts.
   * So we use firstVisible as a proxy.
   */
  private static long timeSinceLastDonatePrompt(@NonNull Event excludeEvent, @NonNull Map<Event, MegaphoneRecord> records) {
    long lastSeenDonatePrompt = records.entrySet()
                                       .stream()
                                       .filter(e -> DONATE_EVENTS.contains(e.getKey()))
                                       .filter(e -> !e.getKey().equals(excludeEvent))
                                       .map(e -> e.getValue().getFirstVisible())
                                       .filter(t -> t > 0)
                                       .sorted()
                                       .findFirst()
                                       .orElse(0L);
    return System.currentTimeMillis() - lastSeenDonatePrompt;
  }


  public enum Event {
    PINS_FOR_ALL("pins_for_all"),
    PIN_REMINDER("pin_reminder"),
    CLIENT_DEPRECATED("client_deprecated"),
    ONBOARDING("onboarding"),
    NOTIFICATIONS("notifications"),
    ADD_A_PROFILE_PHOTO("add_a_profile_photo"),
    BECOME_A_SUSTAINER("become_a_sustainer"),
    DONATE_Q2_2022("donate_q2_2022"),
    TURN_OFF_CENSORSHIP_CIRCUMVENTION("turn_off_censorship_circumvention"),
    REMOTE_MEGAPHONE("remote_megaphone"),
    LINKED_DEVICE_INACTIVE("linked_device_inactive"),
    BACKUP_SCHEDULE_PERMISSION("backup_schedule_permission"),
    SET_UP_YOUR_USERNAME("set_up_your_username"),
    PNP_LAUNCH("pnp_launch"),
    GRANT_FULL_SCREEN_INTENT("grant_full_screen_intent");

    private final String key;

    Event(@NonNull String key) {
      this.key = key;
    }

    public @NonNull String getKey() {
      return key;
    }

    public static Event fromKey(@NonNull String key) {
      for (Event event : values()) {
        if (event.getKey().equals(key)) {
          return event;
        }
      }
      throw new IllegalArgumentException("No event for key: " + key);
    }

    public static boolean hasKey(@NonNull String key) {
      for (Event event : values()) {
        if (event.getKey().equals(key)) {
          return true;
        }
      }
      return false;
    }
  }
}
