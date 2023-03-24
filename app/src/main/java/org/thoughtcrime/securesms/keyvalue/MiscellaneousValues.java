package org.thoughtcrime.securesms.keyvalue;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.thoughtcrime.securesms.database.model.databaseprotos.PendingChangeNumberMetadata;
import org.thoughtcrime.securesms.jobmanager.impl.ChangeNumberConstraintObserver;

import java.util.Collections;
import java.util.List;

public final class MiscellaneousValues extends SignalStoreValues {

  private static final String LAST_PREKEY_REFRESH_TIME        = "last_prekey_refresh_time";
  private static final String MESSAGE_REQUEST_ENABLE_TIME     = "message_request_enable_time";
  private static final String LAST_PROFILE_REFRESH_TIME       = "misc.last_profile_refresh_time";
  private static final String USERNAME_SHOW_REMINDER          = "username.show.reminder";
  private static final String CLIENT_DEPRECATED               = "misc.client_deprecated";
  private static final String OLD_DEVICE_TRANSFER_LOCKED      = "misc.old_device.transfer.locked";
  private static final String HAS_EVER_HAD_AN_AVATAR          = "misc.has.ever.had.an.avatar";
  private static final String CHANGE_NUMBER_LOCK              = "misc.change_number.lock";
  private static final String PENDING_CHANGE_NUMBER_METADATA  = "misc.pending_change_number.metadata";
  private static final String CENSORSHIP_LAST_CHECK_TIME      = "misc.censorship.last_check_time";
  private static final String CENSORSHIP_SERVICE_REACHABLE    = "misc.censorship.service_reachable";
  private static final String LAST_GV2_PROFILE_CHECK_TIME     = "misc.last_gv2_profile_check_time";
  private static final String CDS_TOKEN                       = "misc.cds_token";
  private static final String CDS_BLOCKED_UNTIL               = "misc.cds_blocked_until";
  private static final String LAST_FCM_FOREGROUND_TIME        = "misc.last_fcm_foreground_time";
  private static final String LAST_FOREGROUND_TIME            = "misc.last_foreground_time";
  private static final String PNI_INITIALIZED_DEVICES         = "misc.pni_initialized_devices";
  private static final String SMS_PHASE_1_START_MS            = "misc.sms_export.phase_1_start.3";
  private static final String LINKED_DEVICES_REMINDER         = "misc.linked_devices_reminder";

  MiscellaneousValues(@NonNull KeyValueStore store) {
    super(store);
  }

  @Override
  void onFirstEverAppLaunch() {
    putLong(MESSAGE_REQUEST_ENABLE_TIME, 0);
  }

  @Override
  @NonNull List<String> getKeysToIncludeInBackup() {
    return Collections.singletonList(SMS_PHASE_1_START_MS);
  }

  public long getLastPrekeyRefreshTime() {
    return getLong(LAST_PREKEY_REFRESH_TIME, 0);
  }

  public void setLastPrekeyRefreshTime(long time) {
    putLong(LAST_PREKEY_REFRESH_TIME, time);
  }

  public long getMessageRequestEnableTime() {
    return getLong(MESSAGE_REQUEST_ENABLE_TIME, 0);
  }

  public long getLastProfileRefreshTime() {
    return getLong(LAST_PROFILE_REFRESH_TIME, 0);
  }

  public void setLastProfileRefreshTime(long time) {
    putLong(LAST_PROFILE_REFRESH_TIME, time);
  }

  public void hideUsernameReminder() {
    putBoolean(USERNAME_SHOW_REMINDER, false);
  }

  public boolean shouldShowUsernameReminder() {
    return getBoolean(USERNAME_SHOW_REMINDER, true);
  }

  public boolean isClientDeprecated() {
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
    return false; // JW
    //return getBoolean(CLIENT_DEPRECATED, false);
||||||| parent of efc40a1af7 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of cdbbc46ede (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> cdbbc46ede (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
    //return getBoolean(CLIENT_DEPRECATED, false);
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
    //return getBoolean(CLIENT_DEPRECATED, false);
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
    //return getBoolean(CLIENT_DEPRECATED, false);
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
    //return getBoolean(CLIENT_DEPRECATED, false);
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
    //return getBoolean(CLIENT_DEPRECATED, false);
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
    //return getBoolean(CLIENT_DEPRECATED, false);
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
    //return getBoolean(CLIENT_DEPRECATED, false);
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
    //return getBoolean(CLIENT_DEPRECATED, false);
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
    //return getBoolean(CLIENT_DEPRECATED, false);
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
    //return getBoolean(CLIENT_DEPRECATED, false);
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
    //return getBoolean(CLIENT_DEPRECATED, false);
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
    return getBoolean(CLIENT_DEPRECATED, false);
=======
    return false; // JW
    //return getBoolean(CLIENT_DEPRECATED, false);
>>>>>>> 775ec008cc (Added extra options)
  }

  public void markClientDeprecated() {
    putBoolean(CLIENT_DEPRECATED, true);
  }

  public void clearClientDeprecated() {
    putBoolean(CLIENT_DEPRECATED, false);
  }

  public boolean isOldDeviceTransferLocked() {
    return getBoolean(OLD_DEVICE_TRANSFER_LOCKED, false);
  }

  public void markOldDeviceTransferLocked() {
    putBoolean(OLD_DEVICE_TRANSFER_LOCKED, true);
  }

  public void clearOldDeviceTransferLocked() {
    putBoolean(OLD_DEVICE_TRANSFER_LOCKED, false);
  }

  public boolean hasEverHadAnAvatar() {
    return getBoolean(HAS_EVER_HAD_AN_AVATAR, false);
  }

  public void markHasEverHadAnAvatar() {
    putBoolean(HAS_EVER_HAD_AN_AVATAR, true);
  }

  public boolean isChangeNumberLocked() {
    return getBoolean(CHANGE_NUMBER_LOCK, false);
  }

  public void lockChangeNumber() {
    putBoolean(CHANGE_NUMBER_LOCK, true);
    ChangeNumberConstraintObserver.INSTANCE.onChange();
  }

  public void unlockChangeNumber() {
    putBoolean(CHANGE_NUMBER_LOCK, false);
    ChangeNumberConstraintObserver.INSTANCE.onChange();
  }

  public @Nullable PendingChangeNumberMetadata getPendingChangeNumberMetadata() {
    return getObject(PENDING_CHANGE_NUMBER_METADATA, null, PendingChangeNumberMetadataSerializer.INSTANCE);
  }

  /** Store pending new PNI data to be applied after successful change number */
  public void setPendingChangeNumberMetadata(@NonNull PendingChangeNumberMetadata metadata) {
    putObject(PENDING_CHANGE_NUMBER_METADATA, metadata, PendingChangeNumberMetadataSerializer.INSTANCE);
  }

  /** Clear pending new PNI data after confirmed successful or failed change number */
  public void clearPendingChangeNumberMetadata() {
    remove(PENDING_CHANGE_NUMBER_METADATA);
  }

  public long getLastCensorshipServiceReachabilityCheckTime() {
    return getLong(CENSORSHIP_LAST_CHECK_TIME, 0);
  }

  public void setLastCensorshipServiceReachabilityCheckTime(long value) {
    putLong(CENSORSHIP_LAST_CHECK_TIME, value);
  }

  public boolean isServiceReachableWithoutCircumvention() {
    return getBoolean(CENSORSHIP_SERVICE_REACHABLE, false);
  }

  public void setServiceReachableWithoutCircumvention(boolean value) {
    putBoolean(CENSORSHIP_SERVICE_REACHABLE, value);
  }

  public long getLastGv2ProfileCheckTime() {
    return getLong(LAST_GV2_PROFILE_CHECK_TIME, 0);
  }

  public void setLastGv2ProfileCheckTime(long value) {
    putLong(LAST_GV2_PROFILE_CHECK_TIME, value);
  }

  public @Nullable byte[] getCdsToken() {
    return getBlob(CDS_TOKEN, null);
  }

  public void setCdsToken(@Nullable byte[] token) {
    getStore().beginWrite()
              .putBlob(CDS_TOKEN, token)
              .commit();
  }

  /**
   * Marks the time at which we think the next CDS request will succeed. This should be taken from the service response.
   */
  public void setCdsBlockedUtil(long time) {
    putLong(CDS_BLOCKED_UNTIL, time);
  }

  /**
   * Indicates that a CDS request will never succeed at the current contact count.
   */
  public void markCdsPermanentlyBlocked() {
    putLong(CDS_BLOCKED_UNTIL, Long.MAX_VALUE);
  }

  /**
   * Clears any rate limiting state related to CDS.
   */
  public void clearCdsBlocked() {
    setCdsBlockedUtil(0);
  }

  /**
   * Whether or not we expect the next CDS request to succeed.
   */
  public boolean isCdsBlocked() {
    return getCdsBlockedUtil() > 0;
  }

  /**
   * This represents the next time we think we'll be able to make a successful CDS request. If it is before this time, we expect the request will fail
   * (assuming the user still has the same number of new E164s).
   */
  public long getCdsBlockedUtil() {
    return getLong(CDS_BLOCKED_UNTIL, 0);
  }

  public long getLastFcmForegroundServiceTime() {
    return getLong(LAST_FCM_FOREGROUND_TIME, 0);
  }

  public void setLastFcmForegroundServiceTime(long time) {
    putLong(LAST_FCM_FOREGROUND_TIME, time);
  }

  public long getLastForegroundTime() {
    return getLong(LAST_FOREGROUND_TIME, 0);
  }

  public void setLastForegroundTime(long time) {
    putLong(LAST_FOREGROUND_TIME, time);
  }

  public boolean hasPniInitializedDevices() {
    return getBoolean(PNI_INITIALIZED_DEVICES, false);
  }

  public void setPniInitializedDevices(boolean value) {
    putBoolean(PNI_INITIALIZED_DEVICES, value);
  }

  /*public void startSmsPhase1() {
    if (!getStore().containsKey(SMS_PHASE_1_START_MS)) {
      putLong(SMS_PHASE_1_START_MS, System.currentTimeMillis());
    }
  }

  public @NonNull SmsExportPhase getSmsExportPhase() {
    long now = System.currentTimeMillis();
    long phase1StartMs = getLong(SMS_PHASE_1_START_MS, now);
    return SmsExportPhase.getCurrentPhase(now - phase1StartMs);
  }

  public long getSmsPhase3Start() {
    long now = System.currentTimeMillis();
    long phase1StartMs = getLong(SMS_PHASE_1_START_MS, now);
    return phase1StartMs + SmsExportPhase.PHASE_3.getDuration();
  }
*/
  public void setShouldShowLinkedDevicesReminder(boolean value) {
    putBoolean(LINKED_DEVICES_REMINDER, value);
  }

  public boolean getShouldShowLinkedDevicesReminder() {
    return getBoolean(LINKED_DEVICES_REMINDER, false);
  }
}
