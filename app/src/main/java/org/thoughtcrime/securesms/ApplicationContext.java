/*
 * Copyright (C) 2013 Open Whisper Systems
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.thoughtcrime.securesms;

<<<<<<< HEAD
<<<<<<< HEAD
import android.app.Application;
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
import android.annotation.SuppressLint;
=======
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
import android.annotation.SuppressLint;
=======
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;

import com.bumptech.glide.Glide;
import com.google.android.gms.security.ProviderInstaller;

import org.conscrypt.ConscryptSignal;
import org.greenrobot.eventbus.EventBus;
import org.signal.aesgcmprovider.AesGcmProvider;
import org.signal.core.util.MemoryTracker;
import org.signal.core.util.concurrent.AnrDetector;
import org.signal.core.util.concurrent.SignalExecutors;
import org.signal.core.util.logging.AndroidLogger;
import org.signal.core.util.logging.Log;
import org.signal.core.util.logging.Scrubber;
import org.signal.core.util.tracing.Tracer;
import org.signal.glide.SignalGlideCodecs;
import org.signal.libsignal.protocol.logging.SignalProtocolLoggerProvider;
import org.signal.ringrtc.CallManager;
import org.thoughtcrime.securesms.apkupdate.ApkUpdateRefreshListener;
import org.thoughtcrime.securesms.avatar.AvatarPickerStorage;
import org.thoughtcrime.securesms.crypto.AttachmentSecretProvider;
import org.thoughtcrime.securesms.crypto.DatabaseSecretProvider;
import org.thoughtcrime.securesms.database.LogDatabase;
import org.thoughtcrime.securesms.database.SignalDatabase;
import org.thoughtcrime.securesms.database.SqlCipherLibraryLoader;
import org.thoughtcrime.securesms.dependencies.AppDependencies;
import org.thoughtcrime.securesms.dependencies.ApplicationDependencyProvider;
import org.thoughtcrime.securesms.emoji.EmojiSource;
import org.thoughtcrime.securesms.emoji.JumboEmoji;
import org.thoughtcrime.securesms.gcm.FcmFetchManager;
import org.thoughtcrime.securesms.jobs.AccountConsistencyWorkerJob;
import org.thoughtcrime.securesms.jobs.BuildExpirationConfirmationJob;
import org.thoughtcrime.securesms.jobs.CheckServiceReachabilityJob;
import org.thoughtcrime.securesms.jobs.DownloadLatestEmojiDataJob;
import org.thoughtcrime.securesms.jobs.EmojiSearchIndexDownloadJob;
import org.thoughtcrime.securesms.jobs.FcmRefreshJob;
import org.thoughtcrime.securesms.jobs.FontDownloaderJob;
import org.thoughtcrime.securesms.jobs.GroupRingCleanupJob;
import org.thoughtcrime.securesms.jobs.GroupV2UpdateSelfProfileKeyJob;
import org.thoughtcrime.securesms.jobs.InAppPaymentAuthCheckJob;
import org.thoughtcrime.securesms.jobs.InAppPaymentKeepAliveJob;
import org.thoughtcrime.securesms.jobs.LinkedDeviceInactiveCheckJob;
import org.thoughtcrime.securesms.jobs.MultiDeviceContactUpdateJob;
import org.thoughtcrime.securesms.jobs.PnpInitializeDevicesJob;
import org.thoughtcrime.securesms.jobs.PreKeysSyncJob;
import org.thoughtcrime.securesms.jobs.ProfileUploadJob;
import org.thoughtcrime.securesms.jobs.RefreshSvrCredentialsJob;
import org.thoughtcrime.securesms.jobs.RetrieveProfileJob;
import org.thoughtcrime.securesms.jobs.RetrieveRemoteAnnouncementsJob;
import org.thoughtcrime.securesms.jobs.StoryOnboardingDownloadJob;
import org.thoughtcrime.securesms.keyvalue.KeepMessagesDuration;
import org.thoughtcrime.securesms.keyvalue.SignalStore;
import org.thoughtcrime.securesms.logging.CustomSignalProtocolLogger;
import org.thoughtcrime.securesms.logging.PersistentLogger;
import org.thoughtcrime.securesms.messageprocessingalarm.RoutineMessageFetchReceiver;
import org.thoughtcrime.securesms.messages.GroupSendEndorsementInternalNotifier;
import org.thoughtcrime.securesms.migrations.ApplicationMigrations;
import org.thoughtcrime.securesms.mms.SignalGlideComponents;
import org.thoughtcrime.securesms.mms.SignalGlideModule;
import org.thoughtcrime.securesms.providers.BlobProvider;
import org.thoughtcrime.securesms.ratelimit.RateLimitUtil;
import org.thoughtcrime.securesms.recipients.Recipient;
import org.thoughtcrime.securesms.registration.util.RegistrationUtil;
import org.thoughtcrime.securesms.ringrtc.RingRtcLogger;
import org.thoughtcrime.securesms.service.AnalyzeDatabaseAlarmListener;
import org.thoughtcrime.securesms.service.DirectoryRefreshListener;
import org.thoughtcrime.securesms.service.KeyCachingService;
import org.thoughtcrime.securesms.service.LocalBackupListener;
import org.thoughtcrime.securesms.service.MessageBackupListener;
import org.thoughtcrime.securesms.service.RotateSenderCertificateListener;
import org.thoughtcrime.securesms.service.RotateSignedPreKeyListener;
import org.thoughtcrime.securesms.service.webrtc.ActiveCallManager;
import org.thoughtcrime.securesms.service.webrtc.AndroidTelecomUtil;
import org.thoughtcrime.securesms.storage.StorageSyncHelper;
import org.thoughtcrime.securesms.util.AppForegroundObserver;
import org.thoughtcrime.securesms.util.AppStartup;
import org.thoughtcrime.securesms.util.DynamicTheme;
<<<<<<< HEAD
<<<<<<< HEAD
import org.thoughtcrime.securesms.util.RemoteConfig;
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
import org.thoughtcrime.securesms.util.Environment;
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
import org.thoughtcrime.securesms.util.Environment;
=======
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
import org.thoughtcrime.securesms.util.FeatureFlags;
=======
import org.thoughtcrime.securesms.util.FeatureFlags;
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
import org.thoughtcrime.securesms.util.SignalLocalMetrics;
import org.thoughtcrime.securesms.util.SignalUncaughtExceptionHandler;
import org.thoughtcrime.securesms.util.TextSecurePreferences;
import org.thoughtcrime.securesms.util.Util;
import org.thoughtcrime.securesms.util.VersionTracker;
import org.thoughtcrime.securesms.util.dynamiclanguage.DynamicLanguageContextWrapper;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.exceptions.OnErrorNotImplementedException;
import io.reactivex.rxjava3.exceptions.UndeliverableException;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Unit;
import rxdogtag2.RxDogTag;

/**
 * Will be called once when the TextSecure process is created.
 *
 * We're using this as an insertion point to patch up the Android PRNG disaster,
 * to initialize the job manager, and to check for GCM registration freshness.
 *
 * @author Moxie Marlinspike
 */
public class ApplicationContext extends Application implements AppForegroundObserver.Listener {

  private static final String TAG = Log.tag(ApplicationContext.class);

  public static ApplicationContext getInstance(Context context) {
    return (ApplicationContext)context.getApplicationContext();
  }

  @Override
  public void onCreate() {
    Tracer.getInstance().start("Application#onCreate()");
    AppStartup.getInstance().onApplicationCreate();
    SignalLocalMetrics.ColdStart.start();

    long startTime = System.currentTimeMillis();

    super.onCreate();

    AppStartup.getInstance().addBlocking("sqlcipher-init", () -> {
                              SqlCipherLibraryLoader.load();
                              SignalDatabase.init(this,
                                                  DatabaseSecretProvider.getOrCreateDatabaseSecret(this),
                                                  AttachmentSecretProvider.getInstance(this).getOrCreateAttachmentSecret());
                            })
                            .addBlocking("signal-store", () -> SignalStore.init(this))
                            .addBlocking("logging", () -> {
                              initializeLogging();
                              Log.i(TAG, "onCreate()");
                            })
                            .addBlocking("app-dependencies", this::initializeAppDependencies)
                            .addBlocking("anr-detector", this::startAnrDetector)
                            .addBlocking("security-provider", this::initializeSecurityProvider)
                            .addBlocking("crash-handling", this::initializeCrashHandling)
                            .addBlocking("rx-init", this::initializeRx)
                            .addBlocking("event-bus", () -> EventBus.builder().logNoSubscriberMessages(false).installDefaultEventBus())
                            .addBlocking("scrubber", () -> Scrubber.setIdentifierHmacKeyProvider(() -> SignalStore.svr().getOrCreateMasterKey().deriveLoggingKey()))
                            .addBlocking("first-launch", this::initializeFirstEverAppLaunch)
                            .addBlocking("app-migrations", this::initializeApplicationMigrations)
                            .addBlocking("lifecycle-observer", () -> AppForegroundObserver.addListener(this))
                            .addBlocking("message-retriever", this::initializeMessageRetrieval)
                            .addBlocking("dynamic-theme", () -> DynamicTheme.setDefaultDayNightMode(this))
                            .addBlocking("proxy-init", () -> {
                              if (SignalStore.proxy().isProxyEnabled()) {
                                Log.w(TAG, "Proxy detected. Enabling Conscrypt.setUseEngineSocketByDefault()");
                                ConscryptSignal.setUseEngineSocketByDefault(true);
                              }
                            })
                            .addBlocking("blob-provider", this::initializeBlobProvider)
                            .addBlocking("remote-config", RemoteConfig::init)
                            .addBlocking("ring-rtc", this::initializeRingRtc)
                            .addBlocking("glide", () -> SignalGlideModule.setRegisterGlideComponents(new SignalGlideComponents()))
<<<<<<< HEAD
                            .addBlocking("tracer", this::initializeTracer)
                            .addNonBlocking(() -> RegistrationUtil.maybeMarkRegistrationComplete())
                            .addNonBlocking(() -> Glide.get(this))
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
                            .addNonBlocking(() -> GlideApp.get(this))
<<<<<<< HEAD
                            .addNonBlocking(this::checkIsGooglePayReady)
=======
                            .addNonBlocking(() -> GlideApp.get(this))
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
                            .addNonBlocking(this::checkIsGooglePayReady)
=======
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
                            .addNonBlocking(this::cleanAvatarStorage)
                            .addNonBlocking(this::initializeRevealableMessageManager)
                            .addNonBlocking(this::initializePendingRetryReceiptManager)
                            .addNonBlocking(this::initializeScheduledMessageManager)
                            .addNonBlocking(this::initializeFcmCheck)
                            .addNonBlocking(PreKeysSyncJob::enqueueIfNeeded)
                            .addNonBlocking(this::initializePeriodicTasks)
                            .addNonBlocking(this::initializeCircumvention)
                            .addNonBlocking(this::initializeCleanup)
                            .addNonBlocking(this::initializeGlideCodecs)
                            .addNonBlocking(StorageSyncHelper::scheduleRoutineSync)
                            .addNonBlocking(this::beginJobLoop)
                            .addNonBlocking(EmojiSource::refresh)
                            .addNonBlocking(() -> AppDependencies.getGiphyMp4Cache().onAppStart(this))
                            .addNonBlocking(AppDependencies::getBillingApi)
                            .addNonBlocking(this::ensureProfileUploaded)
<<<<<<< HEAD
                            .addNonBlocking(() -> AppDependencies.getExpireStoriesManager().scheduleIfNecessary())
                            .addPostRender(() -> AppDependencies.getDeletedCallEventManager().scheduleIfNecessary())
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
                            .addNonBlocking(() -> ApplicationDependencies.getExpireStoriesManager().scheduleIfNecessary())
<<<<<<< HEAD
=======
                            .addNonBlocking(() -> ApplicationDependencies.getExpireStoriesManager().scheduleIfNecessary())
                            .addPostRender(() -> ApplicationDependencies.getDeletedCallEventManager().scheduleIfNecessary())
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
                            .addPostRender(() -> ApplicationDependencies.getDeletedCallEventManager().scheduleIfNecessary())
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
                            .addPostRender(() -> RateLimitUtil.retryAllRateLimitedMessages(this))
                            .addPostRender(this::initializeExpiringMessageManager)
                            .addPostRender(this::initializeTrimThreadsByDateManager)
                            .addPostRender(RefreshSvrCredentialsJob::enqueueIfNecessary)
                            .addPostRender(() -> DownloadLatestEmojiDataJob.scheduleIfNecessary(this))
                            .addPostRender(EmojiSearchIndexDownloadJob::scheduleIfNecessary)
                            .addPostRender(() -> SignalDatabase.messageLog().trimOldMessages(System.currentTimeMillis(), RemoteConfig.retryRespondMaxAge()))
                            .addPostRender(() -> JumboEmoji.updateCurrentVersion(this))
                            .addPostRender(RetrieveRemoteAnnouncementsJob::enqueue)
                            .addPostRender(() -> AndroidTelecomUtil.registerPhoneAccount())
                            .addPostRender(() -> AppDependencies.getJobManager().add(new FontDownloaderJob()))
                            .addPostRender(CheckServiceReachabilityJob::enqueueIfNecessary)
                            .addPostRender(GroupV2UpdateSelfProfileKeyJob::enqueueForGroupsIfNecessary)
                            .addPostRender(StoryOnboardingDownloadJob.Companion::enqueueIfNeeded)
                            .addPostRender(PnpInitializeDevicesJob::enqueueIfNecessary)
<<<<<<< HEAD
                            .addPostRender(() -> AppDependencies.getExoPlayerPool().getPoolStats().getMaxUnreserved())
                            .addPostRender(() -> AppDependencies.getRecipientCache().warmUp())
                            .addPostRender(AccountConsistencyWorkerJob::enqueueIfNecessary)
                            .addPostRender(GroupRingCleanupJob::enqueue)
                            .addPostRender(LinkedDeviceInactiveCheckJob::enqueueIfNecessary)
                            .addPostRender(() -> ActiveCallManager.clearNotifications(this))
                            .addPostRender(() -> GroupSendEndorsementInternalNotifier.init())
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
                            .addPostRender(() -> ApplicationDependencies.getExoPlayerPool().getPoolStats().getMaxUnreserved())
                            .addPostRender(() -> ApplicationDependencies.getRecipientCache().warmUp())
=======
                            .addPostRender(() -> ApplicationDependencies.getExoPlayerPool().getPoolStats().getMaxUnreserved())
                            .addPostRender(() -> ApplicationDependencies.getRecipientCache().warmUp())
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
                            .execute();

    Log.d(TAG, "onCreate() took " + (System.currentTimeMillis() - startTime) + " ms");
    SignalLocalMetrics.ColdStart.onApplicationCreateFinished();
    Tracer.getInstance().end("Application#onCreate()");
  }

  @Override
  public void onForeground() {
    long startTime = System.currentTimeMillis();
    Log.i(TAG, "App is now visible.");

    AppDependencies.getFrameRateTracker().start();
    AppDependencies.getMegaphoneRepository().onAppForegrounded();
    AppDependencies.getDeadlockDetector().start();
    InAppPaymentKeepAliveJob.enqueueAndTrackTimeIfNecessary();
    FcmFetchManager.onForeground(this);
    startAnrDetector();

    SignalExecutors.BOUNDED.execute(() -> {
      InAppPaymentAuthCheckJob.enqueueIfNeeded();
      RemoteConfig.refreshIfNecessary();
      RetrieveProfileJob.enqueueRoutineFetchIfNecessary();
      executePendingContactSync();
      KeyCachingService.onAppForegrounded(this);
      AppDependencies.getShakeToReport().enable();
      checkBuildExpiration();
      MemoryTracker.start();

      long lastForegroundTime = SignalStore.misc().getLastForegroundTime();
      long currentTime        = System.currentTimeMillis();
      long timeDiff           = currentTime - lastForegroundTime;

      if (timeDiff < 0) {
        Log.w(TAG, "Time travel! The system clock has moved backwards. (currentTime: " + currentTime + " ms, lastForegroundTime: " + lastForegroundTime + " ms, diff: " + timeDiff + " ms)", true);
      }

      SignalStore.misc().setLastForegroundTime(currentTime);
    });

    Log.d(TAG, "onStart() took " + (System.currentTimeMillis() - startTime) + " ms");
  }

  @Override
  public void onBackground() {
    Log.i(TAG, "App is no longer visible.");
    KeyCachingService.onAppBackgrounded(this);
    AppDependencies.getMessageNotifier().clearVisibleThread();
    AppDependencies.getFrameRateTracker().stop();
    AppDependencies.getShakeToReport().disable();
    AppDependencies.getDeadlockDetector().stop();
    MemoryTracker.stop();
    AnrDetector.stop();
  }

  public void checkBuildExpiration() {
    if (Util.getTimeUntilBuildExpiry(SignalStore.misc().getEstimatedServerTime()) <= 0 && !SignalStore.misc().isClientDeprecated()) {
      Log.w(TAG, "Build potentially expired! Enqueing job to check.", true);
      AppDependencies.getJobManager().add(new BuildExpirationConfirmationJob());
    }
  }

  /**
   * Note: this is purposefully "started" twice -- once during application create, and once during foreground.
   * This is so we can capture ANR's that happen on boot before the foreground event.
   */
  private void startAnrDetector() {
    AnrDetector.start(TimeUnit.SECONDS.toMillis(5), RemoteConfig::internalUser, (dumps) -> {
      LogDatabase.getInstance(this).anrs().save(System.currentTimeMillis(), dumps);
      return Unit.INSTANCE;
    });
  }

  private void initializeSecurityProvider() {
    int aesPosition = Security.insertProviderAt(new AesGcmProvider(), 1);
    Log.i(TAG, "Installed AesGcmProvider: " + aesPosition);

    if (aesPosition < 0) {
      Log.e(TAG, "Failed to install AesGcmProvider()");
      throw new ProviderInitializationException();
    }

    int conscryptPosition = Security.insertProviderAt(ConscryptSignal.newProvider(), 2);
    Log.i(TAG, "Installed Conscrypt provider: " + conscryptPosition);

    if (conscryptPosition < 0) {
      Log.w(TAG, "Did not install Conscrypt provider. May already be present.");
    }
  }

  @VisibleForTesting
  protected void initializeLogging() {
    Log.initialize(RemoteConfig::internalUser, new AndroidLogger(), new PersistentLogger(this));

    SignalProtocolLoggerProvider.setProvider(new CustomSignalProtocolLogger());
    SignalProtocolLoggerProvider.initializeLogging(BuildConfig.LIBSIGNAL_LOG_LEVEL);

    SignalExecutors.UNBOUNDED.execute(() -> {
      Log.blockUntilAllWritesFinished();
      LogDatabase.getInstance(this).logs().trimToSize();
      LogDatabase.getInstance(this).crashes().trimToSize();
    });
  }

  private void initializeCrashHandling() {
    final Thread.UncaughtExceptionHandler originalHandler = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(new SignalUncaughtExceptionHandler(originalHandler));
  }

  private void initializeRx() {
    RxDogTag.install();
    RxJavaPlugins.setInitIoSchedulerHandler(schedulerSupplier -> Schedulers.from(SignalExecutors.BOUNDED_IO, true, false));
    RxJavaPlugins.setInitComputationSchedulerHandler(schedulerSupplier -> Schedulers.from(SignalExecutors.BOUNDED, true, false));
    RxJavaPlugins.setErrorHandler(e -> {
      boolean wasWrapped = false;
      while ((e instanceof UndeliverableException || e instanceof AssertionError || e instanceof OnErrorNotImplementedException) && e.getCause() != null) {
        wasWrapped = true;
        e = e.getCause();
      }

      if (wasWrapped && (e instanceof SocketException || e instanceof SocketTimeoutException || e instanceof InterruptedException)) {
        return;
      }

      Thread.UncaughtExceptionHandler uncaughtExceptionHandler = Thread.currentThread().getUncaughtExceptionHandler();
      if (uncaughtExceptionHandler == null) {
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
      }

      uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), e);
    });
  }

  private void initializeApplicationMigrations() {
    ApplicationMigrations.onApplicationCreate(this, AppDependencies.getJobManager());
  }

  public void initializeMessageRetrieval() {
    AppDependencies.getIncomingMessageObserver();
  }

  @VisibleForTesting
  void initializeAppDependencies() {
    if (!AppDependencies.isInitialized()) {
      Log.i(TAG, "Initializing AppDependencies.");
      AppDependencies.init(this, new ApplicationDependencyProvider(this));
    }
    AppForegroundObserver.begin();
  }

  private void initializeFirstEverAppLaunch() {
    if (TextSecurePreferences.getFirstInstallVersion(this) == -1) {
      if (!SignalDatabase.databaseFileExists(this) || VersionTracker.getDaysSinceFirstInstalled(this) < 365) {
        Log.i(TAG, "First ever app launch!");
        AppInitialization.onFirstEverAppLaunch(this);
      }

      Log.i(TAG, "Setting first install version to " + BuildConfig.CANONICAL_VERSION_CODE);
      TextSecurePreferences.setFirstInstallVersion(this, BuildConfig.CANONICAL_VERSION_CODE);
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
    } else if (!SignalStore.settings().getPassphraseDisabled() && VersionTracker.getDaysSinceFirstInstalled(this) < 90) {
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
||||||| parent of 775ec008cc (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of cdbbc46ede (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> cdbbc46ede (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
// JW: this code bluntly removes the password setting and makes password protected installs crash.
/*
>>>>>>> 76bcf0c877 (Added extra options)
    } else if (!TextSecurePreferences.isPasswordDisabled(this) && VersionTracker.getDaysSinceFirstInstalled(this) < 90) {
>>>>>>> 66c339aa35 (Added extra options)
      Log.i(TAG, "Detected a new install that doesn't have passphrases disabled -- assuming bad initialization.");
      AppInitialization.onRepairFirstEverAppLaunch(this);
    } else if (!SignalStore.settings().getPassphraseDisabled() && VersionTracker.getDaysSinceFirstInstalled(this) < 912) {
      Log.i(TAG, "Detected a not-recent install that doesn't have passphrases disabled -- disabling now.");
<<<<<<< HEAD
      SignalStore.settings().setPassphraseDisabled(true);
=======
      TextSecurePreferences.setPasswordDisabled(this, true);
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
*/
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
*/
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
*/
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
*/
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
*/
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
*/
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
*/
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
=======
*/
>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
*/
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
*/
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
*/
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
*/
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
*/
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
*/
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
*/
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
*/
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
*/
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
*/
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
*/
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
*/
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
*/
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
*/
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
*/
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
*/
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
*/
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
*/
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
*/
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
*/
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of cdbbc46ede (Added extra options)
=======
*/
>>>>>>> cdbbc46ede (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======
*/
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
=======
*/
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
=======
*/
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
=======
*/
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
=======
*/
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======
*/
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
*/
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
*/
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
*/
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
*/
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
*/
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
*/
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
*/
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
*/
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
*/
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
*/
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
*/
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
*/
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
*/
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
*/
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
*/
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
*/
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
*/
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
*/
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
*/
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
*/
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
*/
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
*/
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
*/
>>>>>>> 76bcf0c877 (Added extra options)
    }
  }

  private void initializeFcmCheck() {
    if (SignalStore.account().isRegistered()) {
      long lastSetTime = SignalStore.account().getFcmTokenLastSetTime();
      long nextSetTime = lastSetTime + TimeUnit.HOURS.toMillis(6);
      long now         = System.currentTimeMillis();

      if (SignalStore.account().getFcmToken() == null || nextSetTime <= now || lastSetTime > now) {
        AppDependencies.getJobManager().add(new FcmRefreshJob());
      }
    }
  }

  private void initializeExpiringMessageManager() {
    AppDependencies.getExpiringMessageManager().checkSchedule();
  }

  private void initializeRevealableMessageManager() {
    AppDependencies.getViewOnceMessageManager().scheduleIfNecessary();
  }

  private void initializePendingRetryReceiptManager() {
    AppDependencies.getPendingRetryReceiptManager().scheduleIfNecessary();
  }

  private void initializeScheduledMessageManager() {
    AppDependencies.getScheduledMessageManager().scheduleIfNecessary();
  }

  private void initializeTrimThreadsByDateManager() {
    KeepMessagesDuration keepMessagesDuration = SignalStore.settings().getKeepMessagesDuration();
    if (keepMessagesDuration != KeepMessagesDuration.FOREVER) {
      AppDependencies.getTrimThreadsByDateManager().scheduleIfNecessary();
    }
  }

  private void initializeTracer() {
    if (RemoteConfig.internalUser()) {
      Tracer.getInstance().setMaxBufferSize(35_000);
    }
  }

  private void initializePeriodicTasks() {
    RotateSignedPreKeyListener.schedule(this);
    DirectoryRefreshListener.schedule(this);
    LocalBackupListener.schedule(this);
    MessageBackupListener.schedule(this);
    RotateSenderCertificateListener.schedule(this);
    RoutineMessageFetchReceiver.startOrUpdateAlarm(this);
    AnalyzeDatabaseAlarmListener.schedule(this);

    if (BuildConfig.MANAGES_APP_UPDATES) {
      ApkUpdateRefreshListener.schedule(this);
    }
  }

  private void initializeRingRtc() {
    try {
      Map<String, String> fieldTrials = new HashMap<>();
      if (RemoteConfig.callingFieldTrialAnyAddressPortsKillSwitch()) {
        fieldTrials.put("RingRTC-AnyAddressPortsKillSwitch", "Enabled");
      }
      CallManager.initialize(this, new RingRtcLogger(), fieldTrials);
    } catch (UnsatisfiedLinkError e) {
      throw new AssertionError("Unable to load ringrtc library", e);
    }
  }

  @WorkerThread
  private void initializeCircumvention() {
    if (AppDependencies.getSignalServiceNetworkAccess().isCensored()) {
      try {
        ProviderInstaller.installIfNeeded(ApplicationContext.this);
      } catch (Throwable t) {
        Log.w(TAG, t);
      }
    }
  }

  private void ensureProfileUploaded() {
    if (SignalStore.account().isRegistered() && !SignalStore.registration().hasUploadedProfile() && !Recipient.self().getProfileName().isEmpty()) {
      Log.w(TAG, "User has a profile, but has not uploaded one. Uploading now.");
      AppDependencies.getJobManager().add(new ProfileUploadJob());
    }
  }

  private void executePendingContactSync() {
    if (TextSecurePreferences.needsFullContactSync(this)) {
      AppDependencies.getJobManager().add(new MultiDeviceContactUpdateJob(true));
    }
  }

  @VisibleForTesting
  protected void beginJobLoop() {
    AppDependencies.getJobManager().beginJobLoop();
  }

  @WorkerThread
  private void initializeBlobProvider() {
    BlobProvider.getInstance().initialize(this);
  }

  @WorkerThread
  private void cleanAvatarStorage() {
    AvatarPickerStorage.cleanOrphans(this);
  }

  @WorkerThread
  private void initializeCleanup() {
    int deleted = SignalDatabase.attachments().deleteAbandonedPreuploadedAttachments();
    Log.i(TAG, "Deleted " + deleted + " abandoned attachments.");
  }

  private void initializeGlideCodecs() {
    SignalGlideCodecs.setLogProvider(new org.signal.glide.Log.Provider() {
      @Override
      public void v(@NonNull String tag, @NonNull String message) {
        Log.v(tag, message);
      }

      @Override
      public void d(@NonNull String tag, @NonNull String message) {
        Log.d(tag, message);
      }

      @Override
      public void i(@NonNull String tag, @NonNull String message) {
        Log.i(tag, message);
      }

      @Override
      public void w(@NonNull String tag, @NonNull String message) {
        Log.w(tag, message);
      }

      @Override
      public void e(@NonNull String tag, @NonNull String message, @Nullable Throwable throwable) {
        Log.e(tag, message, throwable);
      }
    });
  }

  @Override
  protected void attachBaseContext(Context base) {
    DynamicLanguageContextWrapper.updateContext(base);
    super.attachBaseContext(base);
  }

  private static class ProviderInitializationException extends RuntimeException {
  }
}
