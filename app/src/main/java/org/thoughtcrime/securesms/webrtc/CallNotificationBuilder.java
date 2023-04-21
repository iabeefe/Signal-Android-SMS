package org.thoughtcrime.securesms.webrtc;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

<<<<<<< HEAD
<<<<<<< HEAD
import androidx.annotation.IntDef;
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
import androidx.annotation.DrawableRes;
=======
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
import androidx.annotation.DrawableRes;
=======
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
import androidx.annotation.NonNull;
<<<<<<< HEAD
<<<<<<< HEAD
import androidx.annotation.Nullable;
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
import androidx.annotation.StringRes;
=======
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
import androidx.annotation.StringRes;
=======
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
import androidx.core.app.NotificationCompat;
import androidx.core.app.Person;

import org.signal.core.util.PendingIntentFlags;
import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.MainActivity;
import org.thoughtcrime.securesms.R;
<<<<<<< HEAD
import org.thoughtcrime.securesms.components.webrtc.v2.CallIntent;
||||||| parent of 2c7a921f07 (Bumped to upstream version 6.19.1.0-JW.)
import org.thoughtcrime.securesms.WebRtcCallActivity;
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies;
=======
import org.thoughtcrime.securesms.WebRtcCallActivity;
>>>>>>> 2c7a921f07 (Bumped to upstream version 6.19.1.0-JW.)
import org.thoughtcrime.securesms.notifications.NotificationChannels;
import org.thoughtcrime.securesms.recipients.Recipient;
import org.thoughtcrime.securesms.service.webrtc.WebRtcCallService;
<<<<<<< HEAD
import org.thoughtcrime.securesms.util.ConversationUtil;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
=======
import org.thoughtcrime.securesms.util.ConversationUtil;
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)

/**
 * Manages the state of the WebRtc items in the Android notification bar.
 *
 * @author Moxie Marlinspike
 */

public class CallNotificationBuilder {

  public static final int WEBRTC_NOTIFICATION         = 313388;
  public static final int WEBRTC_NOTIFICATION_RINGING = 313389;

  public static final int TYPE_INCOMING_RINGING    = 1;
  public static final int TYPE_OUTGOING_RINGING    = 2;
  public static final int TYPE_ESTABLISHED         = 3;
  public static final int TYPE_INCOMING_CONNECTING = 4;

<<<<<<< HEAD
<<<<<<< HEAD
  @IntDef(value = {
      TYPE_INCOMING_RINGING,
      TYPE_OUTGOING_RINGING,
      TYPE_ESTABLISHED,
      TYPE_INCOMING_CONNECTING
  })
  public @interface CallNotificationType {
  }
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
=======
  /**
   * This is the API level at which call style notifications will
   * properly pop over the screen and allow a user to answer a call.
   * <p>
   * Older API levels will still render a notification with the proper
   * actions, but since we want to ensure that they are able to answer
   * the call without having to open the shade, we fall back on launching
   * the activity (done so in SignalCallManager).
   */
  public static final int API_LEVEL_CALL_STYLE = 29;

>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  public static Notification getCallInProgressNotification(Context context, int type, Recipient recipient) {
    Intent contentIntent = new Intent(context, WebRtcCallActivity.class);
    contentIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    contentIntent.putExtra(WebRtcCallActivity.EXTRA_STARTED_FROM_FULLSCREEN, true);
=======
  /**
   * This is the API level at which call style notifications will
   * properly pop over the screen and allow a user to answer a call.
   * <p>
   * Older API levels will still render a notification with the proper
   * actions, but since we want to ensure that they are able to answer
   * the call without having to open the shade, we fall back on launching
   * the activity (done so in SignalCallManager).
   */
  public static final int API_LEVEL_CALL_STYLE = 29;

  public static Single<Notification> getCallInProgressNotification(Context context, int type, Recipient recipient) {
    Intent contentIntent = new Intent(context, WebRtcCallActivity.class);
    contentIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    contentIntent.putExtra(WebRtcCallActivity.EXTRA_STARTED_FROM_FULLSCREEN, true);
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)

  private enum LaunchCallScreenIntentState {
    CONTENT(null, 0),
    AUDIO(CallIntent.Action.ANSWER_AUDIO, 1),
    VIDEO(CallIntent.Action.ANSWER_VIDEO, 2);

    final @Nullable CallIntent.Action action;
    final           int               requestCode;

    LaunchCallScreenIntentState(@Nullable CallIntent.Action action, int requestCode) {
      this.action      = action;
      this.requestCode = requestCode;
    }
  }

  /**
   * This is the API level at which call style notifications will
   * properly pop over the screen and allow a user to answer a call.
   * <p>
   * Older API levels will still render a notification with the proper
   * actions, but since we want to ensure that they are able to answer
   * the call without having to open the shade, we fall back on launching
   * the activity (done so in SignalCallManager).
   */
  public static final int API_LEVEL_CALL_STYLE = 29;

  /**
   * Gets the Notification for the current in-progress call.
   *
   * @param context         Context, normally the service requesting this notification
   * @param type            The type of notification desired
   * @param recipient       The target of the call (group, call link, or 1:1 recipient)
   * @param isVideoCall     Whether the call is a video call
   * @param skipPersonIcon  Whether to skip loading the icon for a person, used to avoid blocking the UI thread on older apis.
   */
  public static Notification getCallInProgressNotification(
      Context context,
      @CallNotificationType int type,
      Recipient recipient,
      boolean isVideoCall,
      boolean skipPersonIcon
  ) {
    PendingIntent pendingIntent = getActivityPendingIntent(context, LaunchCallScreenIntentState.CONTENT);
    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, getNotificationChannel(type))
        .setSmallIcon(R.drawable.ic_call_secure_white_24dp)
        .setContentIntent(pendingIntent)
        .setOngoing(true)
        .setContentTitle(recipient.getDisplayName(context));

    if (type == TYPE_INCOMING_CONNECTING) {
      builder.setContentText(context.getString(R.string.CallNotificationBuilder_connecting));
      builder.setPriority(NotificationCompat.PRIORITY_MIN);
      builder.setContentIntent(null);
<<<<<<< HEAD
      return builder.build();
||||||| parent of 2c7a921f07 (Bumped to upstream version 6.19.1.0-JW.)
=======
      return Single.just(builder.build());
>>>>>>> 2c7a921f07 (Bumped to upstream version 6.19.1.0-JW.)
    } else if (type == TYPE_INCOMING_RINGING) {
<<<<<<< HEAD
      builder.setContentText(getIncomingCallContentText(context, recipient, isVideoCall));
      builder.setPriority(NotificationCompat.PRIORITY_HIGH);
      builder.setCategory(NotificationCompat.CATEGORY_CALL);
      builder.setFullScreenIntent(pendingIntent, true);
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      builder.setContentText(context.getString(recipient.isGroup() ? R.string.NotificationBarManager__incoming_signal_group_call : R.string.NotificationBarManager__incoming_signal_call));
<<<<<<< HEAD
      builder.addAction(getServiceNotificationAction(context, WebRtcCallService.denyCallIntent(context), R.drawable.ic_close_grey600_32dp, R.string.NotificationBarManager__decline_call));
      builder.addAction(getActivityNotificationAction(context, WebRtcCallActivity.ANSWER_ACTION, R.drawable.ic_phone_grey600_32dp, recipient.isGroup() ? R.string.NotificationBarManager__join_call : R.string.NotificationBarManager__answer_call));
=======
      builder.setContentText(context.getString(recipient.isGroup() ? R.string.NotificationBarManager__incoming_signal_group_call : R.string.NotificationBarManager__incoming_signal_call));
<<<<<<< HEAD
      builder.setStyle(NotificationCompat.CallStyle.forIncomingCall(
          ConversationUtil.buildPersonWithoutIcon(context, recipient),
          getServicePendingIntent(context, WebRtcCallService.denyCallIntent(context)),
          getActivityPendingIntent(context, WebRtcCallActivity.ANSWER_ACTION)
      ));
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      builder.addAction(getServiceNotificationAction(context, WebRtcCallService.denyCallIntent(context), R.drawable.ic_close_grey600_32dp, R.string.NotificationBarManager__decline_call));
      builder.addAction(getActivityNotificationAction(context, WebRtcCallActivity.ANSWER_ACTION, R.drawable.ic_phone_grey600_32dp, recipient.isGroup() ? R.string.NotificationBarManager__join_call : R.string.NotificationBarManager__answer_call));
=======
      builder.setStyle(NotificationCompat.CallStyle.forIncomingCall(
          ConversationUtil.buildPersonWithoutIcon(context, recipient),
          getServicePendingIntent(context, WebRtcCallService.denyCallIntent(context)),
          getActivityPendingIntent(context, WebRtcCallActivity.ANSWER_ACTION)
      ));
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)

<<<<<<< HEAD
<<<<<<< HEAD
      Person person = skipPersonIcon ? ConversationUtil.buildPersonWithoutIcon(context, recipient)
                                     : ConversationUtil.buildPerson(context.getApplicationContext(), recipient);

      builder.addPerson(person);

      if (deviceVersionSupportsIncomingCallStyle()) {
        builder.setStyle(NotificationCompat.CallStyle.forIncomingCall(
            person,
            WebRtcCallService.denyCallIntent(context),
            getActivityPendingIntent(context, isVideoCall ? LaunchCallScreenIntentState.VIDEO : LaunchCallScreenIntentState.AUDIO)
        ).setIsVideo(isVideoCall));
      }

      return builder.build();
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      if (callActivityRestricted()) {
        builder.setFullScreenIntent(pendingIntent, true);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setCategory(NotificationCompat.CATEGORY_CALL);
      }
=======
||||||| parent of 2c7a921f07 (Bumped to upstream version 6.19.1.0-JW.)
      builder.setStyle(NotificationCompat.CallStyle.forIncomingCall(
          ConversationUtil.buildPersonWithoutIcon(context, recipient),
          getServicePendingIntent(context, WebRtcCallService.denyCallIntent(context)),
          getActivityPendingIntent(context, WebRtcCallActivity.ANSWER_ACTION)
      ));

=======
>>>>>>> 2c7a921f07 (Bumped to upstream version 6.19.1.0-JW.)
      builder.setPriority(NotificationCompat.PRIORITY_HIGH);
      builder.setCategory(NotificationCompat.CATEGORY_CALL);
<<<<<<< HEAD
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of 2c7a921f07 (Bumped to upstream version 6.19.1.0-JW.)
=======

      return Single.fromCallable(() -> ConversationUtil.buildPerson(context, recipient))
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .map(person -> {
                     builder.setStyle(NotificationCompat.CallStyle.forIncomingCall(
                         person,
                         getServicePendingIntent(context, WebRtcCallService.denyCallIntent(context)),
                         getActivityPendingIntent(context, WebRtcCallActivity.ANSWER_ACTION)
                     ));
                     return builder.build();
                   });


>>>>>>> 2c7a921f07 (Bumped to upstream version 6.19.1.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      if (callActivityRestricted()) {
        builder.setFullScreenIntent(pendingIntent, true);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setCategory(NotificationCompat.CATEGORY_CALL);
      }
=======
      builder.setPriority(NotificationCompat.PRIORITY_HIGH);
      builder.setCategory(NotificationCompat.CATEGORY_CALL);
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    } else if (type == TYPE_OUTGOING_RINGING) {
      builder.setContentText(context.getString(R.string.NotificationBarManager__establishing_signal_call));
<<<<<<< HEAD
      builder.addAction(getServiceNotificationAction(context, WebRtcCallService.hangupIntent(context), R.drawable.symbol_phone_down_fill_24, R.string.NotificationBarManager__cancel_call));
      return builder.build();
||||||| parent of 2c7a921f07 (Bumped to upstream version 6.19.1.0-JW.)
      builder.addAction(getServiceNotificationAction(context, WebRtcCallService.hangupIntent(context), R.drawable.ic_call_end_grey600_32dp, R.string.NotificationBarManager__cancel_call));
=======
      builder.addAction(getServiceNotificationAction(context, WebRtcCallService.hangupIntent(context), R.drawable.ic_call_end_grey600_32dp, R.string.NotificationBarManager__cancel_call));
      return Single.just(builder.build());
>>>>>>> 2c7a921f07 (Bumped to upstream version 6.19.1.0-JW.)
    } else {
      builder.setContentText(getOngoingCallContentText(context, recipient, isVideoCall));
      builder.setOnlyAlertOnce(true);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
      builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
      builder.setCategory(NotificationCompat.CATEGORY_CALL);
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      builder.addAction(getServiceNotificationAction(context, WebRtcCallService.hangupIntent(context), R.drawable.ic_call_end_grey600_32dp, R.string.NotificationBarManager__end_call));
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      builder.addAction(getServiceNotificationAction(context, WebRtcCallService.hangupIntent(context), R.drawable.ic_call_end_grey600_32dp, R.string.NotificationBarManager__end_call));
=======
      builder.setStyle(NotificationCompat.CallStyle.forOngoingCall(
          ConversationUtil.buildPersonWithoutIcon(context, recipient),
          getServicePendingIntent(context, WebRtcCallService.hangupIntent(context))
      ));

      builder.setPriority(NotificationCompat.PRIORITY_HIGH);
      builder.setCategory(NotificationCompat.CATEGORY_CALL);
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    }
=======
      builder.setStyle(NotificationCompat.CallStyle.forOngoingCall(
          ConversationUtil.buildPersonWithoutIcon(context, recipient),
          getServicePendingIntent(context, WebRtcCallService.hangupIntent(context))
      ));

||||||| parent of 2c7a921f07 (Bumped to upstream version 6.19.1.0-JW.)
      builder.setStyle(NotificationCompat.CallStyle.forOngoingCall(
          ConversationUtil.buildPersonWithoutIcon(context, recipient),
          getServicePendingIntent(context, WebRtcCallService.hangupIntent(context))
      ));

=======
>>>>>>> 2c7a921f07 (Bumped to upstream version 6.19.1.0-JW.)
      builder.setPriority(NotificationCompat.PRIORITY_HIGH);
      builder.setCategory(NotificationCompat.CATEGORY_CALL);
<<<<<<< HEAD
    }
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of 2c7a921f07 (Bumped to upstream version 6.19.1.0-JW.)
    }
=======
>>>>>>> 2c7a921f07 (Bumped to upstream version 6.19.1.0-JW.)

<<<<<<< HEAD
      Person person = skipPersonIcon ? ConversationUtil.buildPersonWithoutIcon(context, recipient)
                                     : ConversationUtil.buildPerson(context.getApplicationContext(), recipient);

      builder.addPerson(person);

      if (deviceVersionSupportsIncomingCallStyle()) {
        builder.setStyle(NotificationCompat.CallStyle.forOngoingCall(
            person,
            WebRtcCallService.hangupIntent(context)
        ).setIsVideo(isVideoCall));
      }

      return builder.build();
    }
||||||| parent of 2c7a921f07 (Bumped to upstream version 6.19.1.0-JW.)
    return builder.build();
=======
      return Single.fromCallable(() -> ConversationUtil.buildPerson(context, recipient))
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .map(person -> {
                     builder.setStyle(NotificationCompat.CallStyle.forOngoingCall(
                         person,
                         getServicePendingIntent(context, WebRtcCallService.hangupIntent(context))
                     ));
                     return builder.build();
                   });
    }
>>>>>>> 2c7a921f07 (Bumped to upstream version 6.19.1.0-JW.)
  }

  public static int getNotificationId(int type) {
    if (deviceVersionSupportsIncomingCallStyle() && type == TYPE_INCOMING_RINGING) {
      return WEBRTC_NOTIFICATION_RINGING;
    } else {
      return WEBRTC_NOTIFICATION;
    }
  }

<<<<<<< HEAD
<<<<<<< HEAD
  public static @NonNull Notification getStartingNotification(@NonNull Context context) {
    return new NotificationCompat.Builder(context, NotificationChannels.getInstance().CALL_STATUS)
        .setSmallIcon(R.drawable.ic_call_secure_white_24dp)
        .setOngoing(true)
        .setContentTitle(context.getString(R.string.NotificationBarManager__starting_signal_call_service))
        .setPriority(NotificationCompat.PRIORITY_MIN)
        .build();
  }

||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  public static @NonNull Notification getStartingNotification(@NonNull Context context) {
    Intent contentIntent = new Intent(context, MainActivity.class);
    contentIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, contentIntent, PendingIntentFlags.mutable());

    return new NotificationCompat.Builder(context, NotificationChannels.getInstance().CALL_STATUS).setSmallIcon(R.drawable.ic_call_secure_white_24dp)
                                                                                                  .setContentIntent(pendingIntent)
                                                                                                  .setOngoing(true)
                                                                                                  .setContentTitle(context.getString(R.string.NotificationBarManager__starting_signal_call_service))
                                                                                                  .setPriority(NotificationCompat.PRIORITY_MIN)
                                                                                                  .build();
  }

=======
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  public static @NonNull Notification getStartingNotification(@NonNull Context context) {
    Intent contentIntent = new Intent(context, MainActivity.class);
    contentIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, contentIntent, PendingIntentFlags.mutable());

    return new NotificationCompat.Builder(context, NotificationChannels.getInstance().CALL_STATUS).setSmallIcon(R.drawable.ic_call_secure_white_24dp)
                                                                                                  .setContentIntent(pendingIntent)
                                                                                                  .setOngoing(true)
                                                                                                  .setContentTitle(context.getString(R.string.NotificationBarManager__starting_signal_call_service))
                                                                                                  .setPriority(NotificationCompat.PRIORITY_MIN)
                                                                                                  .build();
  }

=======
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  public static @NonNull Notification getStoppingNotification(@NonNull Context context) {
    Intent contentIntent = new Intent(context, MainActivity.class);
    contentIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, contentIntent, PendingIntentFlags.mutable());

    return new NotificationCompat.Builder(context, NotificationChannels.getInstance().CALL_STATUS).setSmallIcon(R.drawable.ic_call_secure_white_24dp)
                                                                                                  .setContentIntent(pendingIntent)
                                                                                                  .setOngoing(true)
                                                                                                  .setContentTitle(context.getString(R.string.NotificationBarManager__stopping_signal_call_service))
                                                                                                  .setPriority(NotificationCompat.PRIORITY_MIN)
                                                                                                  .build();
  }

  public static int getStartingStoppingNotificationId() {
    return WEBRTC_NOTIFICATION;
  }

  @SuppressWarnings("BooleanMethodIsAlwaysInverted")
  public static boolean isWebRtcNotification(int notificationId) {
    return notificationId == WEBRTC_NOTIFICATION || notificationId == WEBRTC_NOTIFICATION_RINGING;
  }

  private static @NonNull String getIncomingCallContentText(@NonNull Context context, @NonNull Recipient recipient, boolean isVideoCall) {
    if (recipient.isGroup()) {
      return context.getString(R.string.CallNotificationBuilder__incoming_signal_group_call);
    } else if (isVideoCall) {
      return context.getString(R.string.CallNotificationBuilder__incoming_signal_video_call);
    } else {
      return context.getString(R.string.CallNotificationBuilder__incoming_signal_voice_call);
    }
  }

  private static @NonNull String getOngoingCallContentText(@NonNull Context context, @NonNull Recipient recipient, boolean isVideoCall) {
    if (recipient.isGroup()) {
      return context.getString(R.string.CallNotificationBuilder__ongoing_signal_group_call);
    } else if (isVideoCall) {
      return context.getString(R.string.CallNotificationBuilder__ongoing_signal_video_call);
    } else {
      return context.getString(R.string.CallNotificationBuilder__ongoing_signal_voice_call);
    }
  }

  private static @NonNull String getNotificationChannel(int type) {
    if (type == TYPE_INCOMING_RINGING) {
      return NotificationChannels.getInstance().CALLS;
    } else {
      return NotificationChannels.getInstance().CALL_STATUS;
    }
  }

<<<<<<< HEAD
<<<<<<< HEAD
  private static NotificationCompat.Action getServiceNotificationAction(Context context, PendingIntent intent, int iconResId, int titleResId) {
    return new NotificationCompat.Action(iconResId, context.getString(titleResId), intent);
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  private static NotificationCompat.Action getServiceNotificationAction(Context context, Intent intent, int iconResId, int titleResId) {
    PendingIntent pendingIntent = Build.VERSION.SDK_INT >= 26 ? PendingIntent.getForegroundService(context, 0, intent, PendingIntentFlags.mutable())
                                                              : PendingIntent.getService(context, 0, intent, PendingIntentFlags.mutable());

    return new NotificationCompat.Action(iconResId, context.getString(titleResId), pendingIntent);
=======
  private static PendingIntent getServicePendingIntent(@NonNull Context context, @NonNull Intent intent) {
    return Build.VERSION.SDK_INT >= 26 ? PendingIntent.getForegroundService(context, 0, intent, PendingIntentFlags.mutable())
                                       : PendingIntent.getService(context, 0, intent, PendingIntentFlags.mutable());
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  private static NotificationCompat.Action getServiceNotificationAction(Context context, Intent intent, int iconResId, int titleResId) {
    PendingIntent pendingIntent = Build.VERSION.SDK_INT >= 26 ? PendingIntent.getForegroundService(context, 0, intent, PendingIntentFlags.mutable())
                                                              : PendingIntent.getService(context, 0, intent, PendingIntentFlags.mutable());

    return new NotificationCompat.Action(iconResId, context.getString(titleResId), pendingIntent);
=======
  private static PendingIntent getServicePendingIntent(@NonNull Context context, @NonNull Intent intent) {
    return Build.VERSION.SDK_INT >= 26 ? PendingIntent.getForegroundService(context, 0, intent, PendingIntentFlags.mutable())
                                       : PendingIntent.getService(context, 0, intent, PendingIntentFlags.mutable());
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  }

<<<<<<< HEAD
<<<<<<< HEAD
  private static PendingIntent getActivityPendingIntent(@NonNull Context context, @NonNull LaunchCallScreenIntentState launchCallScreenIntentState) {
    CallIntent.Builder builder = new CallIntent.Builder(context);
    builder.withAction(launchCallScreenIntentState.action);
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  private static NotificationCompat.Action getActivityNotificationAction(@NonNull Context context, @NonNull String action,
                                                                         @DrawableRes int iconResId, @StringRes int titleResId)
  {
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  private static NotificationCompat.Action getActivityNotificationAction(@NonNull Context context, @NonNull String action,
                                                                         @DrawableRes int iconResId, @StringRes int titleResId)
  {
=======
  private static NotificationCompat.Action getServiceNotificationAction(Context context, Intent intent, int iconResId, int titleResId) {
    return new NotificationCompat.Action(iconResId, context.getString(titleResId), getServicePendingIntent(context, intent));
  }

  private static PendingIntent getActivityPendingIntent(@NonNull Context context, @NonNull String action) {
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    Intent intent = new Intent(context, WebRtcCallActivity.class);
    intent.setAction(action);
=======
  private static NotificationCompat.Action getServiceNotificationAction(Context context, Intent intent, int iconResId, int titleResId) {
    return new NotificationCompat.Action(iconResId, context.getString(titleResId), getServicePendingIntent(context, intent));
  }

<<<<<<< HEAD
  private static PendingIntent getActivityPendingIntent(@NonNull Context context, @NonNull String action) {
    Intent intent = new Intent(context, WebRtcCallActivity.class);
    intent.setAction(action);
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)

<<<<<<< HEAD
    if (launchCallScreenIntentState == LaunchCallScreenIntentState.CONTENT) {
      builder.withIntentFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    }

    builder.withStartedFromFullScreen(launchCallScreenIntentState == LaunchCallScreenIntentState.CONTENT);
    builder.withEnableVideoIfAvailable(false);

    return PendingIntent.getActivity(context, launchCallScreenIntentState.requestCode, builder.build(), PendingIntentFlags.updateCurrent());
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntentFlags.mutable());

    return new NotificationCompat.Action(iconResId, context.getString(titleResId), pendingIntent);
=======
    return PendingIntent.getActivity(context, 0, intent, PendingIntentFlags.mutable());
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntentFlags.mutable());

    return new NotificationCompat.Action(iconResId, context.getString(titleResId), pendingIntent);
=======
    return PendingIntent.getActivity(context, 0, intent, PendingIntentFlags.mutable());
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  }

<<<<<<< HEAD
  private static boolean deviceVersionSupportsIncomingCallStyle() {
    return Build.VERSION.SDK_INT >= API_LEVEL_CALL_STYLE;
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  private static boolean callActivityRestricted() {
<<<<<<< HEAD
    return Build.VERSION.SDK_INT >= 29 && !ApplicationDependencies.getAppForegroundObserver().isForegrounded();
=======
  private static boolean callActivityRestricted() {
    return Build.VERSION.SDK_INT >= API_LEVEL_CALL_STYLE;
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    return Build.VERSION.SDK_INT >= 29 && !ApplicationDependencies.getAppForegroundObserver().isForegrounded();
=======
    return Build.VERSION.SDK_INT >= API_LEVEL_CALL_STYLE;
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  }
}
