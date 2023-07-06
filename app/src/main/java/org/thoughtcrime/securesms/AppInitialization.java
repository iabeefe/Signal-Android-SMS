package org.thoughtcrime.securesms;

import android.content.Context;

import androidx.annotation.NonNull;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.dependencies.AppDependencies;
import org.thoughtcrime.securesms.jobmanager.JobManager;
import org.thoughtcrime.securesms.jobs.EmojiSearchIndexDownloadJob;
import org.thoughtcrime.securesms.jobs.StickerPackDownloadJob;
import org.thoughtcrime.securesms.keyvalue.SignalStore;
import org.thoughtcrime.securesms.migrations.ApplicationMigrations;
import org.thoughtcrime.securesms.stickers.BlessedPacks;
import org.thoughtcrime.securesms.util.TextSecurePreferences;
import org.thoughtcrime.securesms.util.Util;

/**
 * Rule of thumb: if there's something you want to do on the first app launch that involves
 * persisting state to the database, you'll almost certainly *also* want to do it post backup
 * restore, since a backup restore will wipe the current state of the database.
 */
public final class AppInitialization {

  private static final String TAG = Log.tag(AppInitialization.class);

  private AppInitialization() {}

  public static void onFirstEverAppLaunch(@NonNull Context context) {
    Log.i(TAG, "onFirstEverAppLaunch()");

    TextSecurePreferences.setAppMigrationVersion(context, ApplicationMigrations.CURRENT_VERSION);
    TextSecurePreferences.setJobManagerVersion(context, JobManager.CURRENT_VERSION);
    TextSecurePreferences.setLastVersionCode(context, Util.getCanonicalVersionCode());
    TextSecurePreferences.setHasSeenStickerIntroTooltip(context, true);
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
    SignalStore.settings().setPassphraseDisabled(true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> c58d43568c (Added extra options)
    TextSecurePreferences.setReadReceiptsEnabled(context, true);
    TextSecurePreferences.setTypingIndicatorsEnabled(context, true);
    TextSecurePreferences.setHasSeenWelcomeScreen(context, false);
    AppDependencies.getMegaphoneRepository().onFirstEverAppLaunch();
    SignalStore.onFirstEverAppLaunch();
    AppDependencies.getJobManager().add(StickerPackDownloadJob.forInstall(BlessedPacks.ZOZO.getPackId(), BlessedPacks.ZOZO.getPackKey(), false));
    AppDependencies.getJobManager().add(StickerPackDownloadJob.forInstall(BlessedPacks.BANDIT.getPackId(), BlessedPacks.BANDIT.getPackKey(), false));
    AppDependencies.getJobManager().add(StickerPackDownloadJob.forInstall(BlessedPacks.DAY_BY_DAY.getPackId(), BlessedPacks.DAY_BY_DAY.getPackKey(), false));
    AppDependencies.getJobManager().add(StickerPackDownloadJob.forReference(BlessedPacks.SWOON_HANDS.getPackId(), BlessedPacks.SWOON_HANDS.getPackKey()));
    AppDependencies.getJobManager().add(StickerPackDownloadJob.forReference(BlessedPacks.SWOON_FACES.getPackId(), BlessedPacks.SWOON_FACES.getPackKey()));
  }

  public static void onPostBackupRestore(@NonNull Context context) {
    Log.i(TAG, "onPostBackupRestore()");

    AppDependencies.getMegaphoneRepository().onFirstEverAppLaunch();
    SignalStore.onPostBackupRestore();
    SignalStore.onFirstEverAppLaunch();
    SignalStore.onboarding().clearAll();
    TextSecurePreferences.onPostBackupRestore(context);
    SignalStore.settings().setPassphraseDisabled(true);
    AppDependencies.getJobManager().add(StickerPackDownloadJob.forInstall(BlessedPacks.ZOZO.getPackId(), BlessedPacks.ZOZO.getPackKey(), false));
    AppDependencies.getJobManager().add(StickerPackDownloadJob.forInstall(BlessedPacks.BANDIT.getPackId(), BlessedPacks.BANDIT.getPackKey(), false));
    AppDependencies.getJobManager().add(StickerPackDownloadJob.forInstall(BlessedPacks.DAY_BY_DAY.getPackId(), BlessedPacks.DAY_BY_DAY.getPackKey(), false));
    AppDependencies.getJobManager().add(StickerPackDownloadJob.forReference(BlessedPacks.SWOON_HANDS.getPackId(), BlessedPacks.SWOON_HANDS.getPackKey()));
    AppDependencies.getJobManager().add(StickerPackDownloadJob.forReference(BlessedPacks.SWOON_FACES.getPackId(), BlessedPacks.SWOON_FACES.getPackKey()));
    EmojiSearchIndexDownloadJob.scheduleImmediately();
  }

  /**
   * Temporary migration method that does the safest bits of {@link #onFirstEverAppLaunch(Context)}
   */
  public static void onRepairFirstEverAppLaunch(@NonNull Context context) {
    Log.w(TAG, "onRepairFirstEverAppLaunch()");

    TextSecurePreferences.setAppMigrationVersion(context, ApplicationMigrations.CURRENT_VERSION);
    TextSecurePreferences.setJobManagerVersion(context, JobManager.CURRENT_VERSION);
    TextSecurePreferences.setLastVersionCode(context, Util.getCanonicalVersionCode());
    TextSecurePreferences.setHasSeenStickerIntroTooltip(context, true);
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
    SignalStore.settings().setPassphraseDisabled(true);
    AppDependencies.getMegaphoneRepository().onFirstEverAppLaunch();
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
||||||| parent of 775ec008cc (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
    TextSecurePreferences.setPasswordDisabled(context, true);
=======
    // TextSecurePreferences.setPasswordDisabled(context, true); // JW: don't do this
>>>>>>> c58d43568c (Added extra options)
    ApplicationDependencies.getMegaphoneRepository().onFirstEverAppLaunch();
>>>>>>> 66c339aa35 (Added extra options)
    SignalStore.onFirstEverAppLaunch();
    AppDependencies.getJobManager().add(StickerPackDownloadJob.forInstall(BlessedPacks.ZOZO.getPackId(), BlessedPacks.ZOZO.getPackKey(), false));
    AppDependencies.getJobManager().add(StickerPackDownloadJob.forInstall(BlessedPacks.BANDIT.getPackId(), BlessedPacks.BANDIT.getPackKey(), false));
    AppDependencies.getJobManager().add(StickerPackDownloadJob.forInstall(BlessedPacks.DAY_BY_DAY.getPackId(), BlessedPacks.DAY_BY_DAY.getPackKey(), false));
    AppDependencies.getJobManager().add(StickerPackDownloadJob.forReference(BlessedPacks.SWOON_HANDS.getPackId(), BlessedPacks.SWOON_HANDS.getPackKey()));
    AppDependencies.getJobManager().add(StickerPackDownloadJob.forReference(BlessedPacks.SWOON_FACES.getPackId(), BlessedPacks.SWOON_FACES.getPackKey()));
  }
}
