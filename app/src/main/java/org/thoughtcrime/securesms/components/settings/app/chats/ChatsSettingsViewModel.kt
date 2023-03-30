package org.thoughtcrime.securesms.components.settings.app.chats

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import org.thoughtcrime.securesms.backup.v2.BackupRepository
import org.thoughtcrime.securesms.dependencies.AppDependencies
import org.thoughtcrime.securesms.keyvalue.SignalStore
import org.thoughtcrime.securesms.util.BackupUtil
import org.thoughtcrime.securesms.util.ConversationUtil
import org.thoughtcrime.securesms.util.TextSecurePreferences // JW: added
import org.thoughtcrime.securesms.util.ThrottledDebouncer
<<<<<<< HEAD
=======
import org.thoughtcrime.securesms.util.Util
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import org.thoughtcrime.securesms.util.UriUtils // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
import org.thoughtcrime.securesms.util.UriUtils // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
import org.thoughtcrime.securesms.util.UriUtils // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
import org.thoughtcrime.securesms.util.UriUtils // JW: added
>>>>>>> 246bbae757 (Added extra options)
import org.thoughtcrime.securesms.util.livedata.Store

class ChatsSettingsViewModel @JvmOverloads constructor(
  private val repository: ChatsSettingsRepository = ChatsSettingsRepository()
) : ViewModel() {

  private val refreshDebouncer = ThrottledDebouncer(500L)

  private val store: Store<ChatsSettingsState> = Store(
    ChatsSettingsState(
<<<<<<< HEAD
      generateLinkPreviews = SignalStore.settings.isLinkPreviewsEnabled,
      useAddressBook = SignalStore.settings.isPreferSystemContactPhotos,
      keepMutedChatsArchived = SignalStore.settings.shouldKeepMutedChatsArchived(),
      useSystemEmoji = SignalStore.settings.isPreferSystemEmoji,
      enterKeySends = SignalStore.settings.isEnterKeySends,
      localBackupsEnabled = SignalStore.settings.isBackupEnabled && BackupUtil.canUserAccessBackupDirectory(AppDependencies.application),
      canAccessRemoteBackupsSettings = SignalStore.backup.areBackupsEnabled
=======
      generateLinkPreviews = SignalStore.settings().isLinkPreviewsEnabled,
      useAddressBook = SignalStore.settings().isPreferSystemContactPhotos,
      keepMutedChatsArchived = SignalStore.settings().shouldKeepMutedChatsArchived(),
      useSystemEmoji = SignalStore.settings().isPreferSystemEmoji,
      enterKeySends = SignalStore.settings().isEnterKeySends,
      chatBackupsEnabled = SignalStore.settings().isBackupEnabled && BackupUtil.canUserAccessBackupDirectory(ApplicationDependencies.getApplication()),
      useAsDefaultSmsApp = Util.isDefaultSmsProvider(ApplicationDependencies.getApplication())
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
      // JW: added
      ,
      chatBackupsLocation = TextSecurePreferences.isBackupLocationRemovable(ApplicationDependencies.getApplication()),
      chatBackupsLocationApi30 = UriUtils.getFullPathFromTreeUri(ApplicationDependencies.getApplication(), SignalStore.settings().signalBackupDirectory),
      chatBackupZipfile = TextSecurePreferences.isRawBackupInZipfile(ApplicationDependencies.getApplication()),
      chatBackupZipfilePlain = TextSecurePreferences.isPlainBackupInZipfile(ApplicationDependencies.getApplication()),
      keepViewOnceMessages = TextSecurePreferences.isKeepViewOnceMessages(ApplicationDependencies.getApplication()),
      ignoreRemoteDelete = TextSecurePreferences.isIgnoreRemoteDelete(ApplicationDependencies.getApplication()),
      deleteMediaOnly = TextSecurePreferences.isDeleteMediaOnly(ApplicationDependencies.getApplication()),
      googleMapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication()),
      whoCanAddYouToGroups = TextSecurePreferences.whoCanAddYouToGroups(ApplicationDependencies.getApplication())
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
      // JW: added
      ,
      chatBackupsLocation = TextSecurePreferences.isBackupLocationRemovable(ApplicationDependencies.getApplication()),
      chatBackupsLocationApi30 = UriUtils.getFullPathFromTreeUri(ApplicationDependencies.getApplication(), SignalStore.settings().signalBackupDirectory),
      chatBackupZipfile = TextSecurePreferences.isRawBackupInZipfile(ApplicationDependencies.getApplication()),
      chatBackupZipfilePlain = TextSecurePreferences.isPlainBackupInZipfile(ApplicationDependencies.getApplication()),
      keepViewOnceMessages = TextSecurePreferences.isKeepViewOnceMessages(ApplicationDependencies.getApplication()),
      ignoreRemoteDelete = TextSecurePreferences.isIgnoreRemoteDelete(ApplicationDependencies.getApplication()),
      deleteMediaOnly = TextSecurePreferences.isDeleteMediaOnly(ApplicationDependencies.getApplication()),
      googleMapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication()),
      whoCanAddYouToGroups = TextSecurePreferences.whoCanAddYouToGroups(ApplicationDependencies.getApplication())
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
      // JW: added
      ,
      chatBackupsLocation = TextSecurePreferences.isBackupLocationRemovable(ApplicationDependencies.getApplication()),
      chatBackupsLocationApi30 = UriUtils.getFullPathFromTreeUri(ApplicationDependencies.getApplication(), SignalStore.settings().signalBackupDirectory),
      chatBackupZipfile = TextSecurePreferences.isRawBackupInZipfile(ApplicationDependencies.getApplication()),
      chatBackupZipfilePlain = TextSecurePreferences.isPlainBackupInZipfile(ApplicationDependencies.getApplication()),
      keepViewOnceMessages = TextSecurePreferences.isKeepViewOnceMessages(ApplicationDependencies.getApplication()),
      ignoreRemoteDelete = TextSecurePreferences.isIgnoreRemoteDelete(ApplicationDependencies.getApplication()),
      deleteMediaOnly = TextSecurePreferences.isDeleteMediaOnly(ApplicationDependencies.getApplication()),
      googleMapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication()),
      whoCanAddYouToGroups = TextSecurePreferences.whoCanAddYouToGroups(ApplicationDependencies.getApplication())
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
      // JW: added
      ,
      chatBackupsLocation = TextSecurePreferences.isBackupLocationRemovable(ApplicationDependencies.getApplication()),
      chatBackupsLocationApi30 = UriUtils.getFullPathFromTreeUri(ApplicationDependencies.getApplication(), SignalStore.settings().signalBackupDirectory),
      chatBackupZipfile = TextSecurePreferences.isRawBackupInZipfile(ApplicationDependencies.getApplication()),
      chatBackupZipfilePlain = TextSecurePreferences.isPlainBackupInZipfile(ApplicationDependencies.getApplication()),
      keepViewOnceMessages = TextSecurePreferences.isKeepViewOnceMessages(ApplicationDependencies.getApplication()),
      ignoreRemoteDelete = TextSecurePreferences.isIgnoreRemoteDelete(ApplicationDependencies.getApplication()),
      deleteMediaOnly = TextSecurePreferences.isDeleteMediaOnly(ApplicationDependencies.getApplication()),
      googleMapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication()),
      whoCanAddYouToGroups = TextSecurePreferences.whoCanAddYouToGroups(ApplicationDependencies.getApplication())
>>>>>>> 246bbae757 (Added extra options)
    )
  )

  val state: LiveData<ChatsSettingsState> = store.stateLiveData

  private val disposable = Single.fromCallable { BackupRepository.canAccessRemoteBackupSettings() }
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribeBy { canAccessRemoteBackupSettings ->
      store.update { it.copy(canAccessRemoteBackupsSettings = canAccessRemoteBackupSettings) }
    }

  override fun onCleared() {
    disposable.dispose()
  }

  fun setGenerateLinkPreviewsEnabled(enabled: Boolean) {
    store.update { it.copy(generateLinkPreviews = enabled) }
    SignalStore.settings.isLinkPreviewsEnabled = enabled
    repository.syncLinkPreviewsState()
  }

  fun setUseAddressBook(enabled: Boolean) {
    store.update { it.copy(useAddressBook = enabled) }
    refreshDebouncer.publish { ConversationUtil.refreshRecipientShortcuts() }
    SignalStore.settings.isPreferSystemContactPhotos = enabled
    repository.syncPreferSystemContactPhotos()
  }

  fun setKeepMutedChatsArchived(enabled: Boolean) {
    store.update { it.copy(keepMutedChatsArchived = enabled) }
    SignalStore.settings.setKeepMutedChatsArchived(enabled)
    repository.syncKeepMutedChatsArchivedState()
  }

  fun setUseSystemEmoji(enabled: Boolean) {
    store.update { it.copy(useSystemEmoji = enabled) }
    SignalStore.settings.isPreferSystemEmoji = enabled
  }

  fun setEnterKeySends(enabled: Boolean) {
    store.update { it.copy(enterKeySends = enabled) }
    SignalStore.settings.isEnterKeySends = enabled
  }

  fun refresh() {
    val backupsEnabled = SignalStore.settings.isBackupEnabled && BackupUtil.canUserAccessBackupDirectory(AppDependencies.application)
    val remoteBackupsEnabled = SignalStore.backup.areBackupsEnabled

    if (store.state.localBackupsEnabled != backupsEnabled ||
      store.state.canAccessRemoteBackupsSettings != remoteBackupsEnabled
    ) {
      store.update { it.copy(localBackupsEnabled = backupsEnabled, canAccessRemoteBackupsSettings = remoteBackupsEnabled) }
    }
    // JW: added. This is required to update the UI for settings that are not in the
    // Signal store but in the shared preferences.
    store.update { getState().copy() }
  }

  // JW: added
  fun setChatBackupLocation(enabled: Boolean) {
    TextSecurePreferences.setBackupLocationRemovable(ApplicationDependencies.getApplication(), enabled)
    TextSecurePreferences.setBackupLocationChanged(ApplicationDependencies.getApplication(), true) // Used in BackupUtil.getAllBackupsNewestFirst()
    refresh()
  }

  // JW: added
  fun setChatBackupLocationApi30(value: String) {
    refresh()
  }

  // JW: added
  fun setChatBackupZipfile(enabled: Boolean) {
    TextSecurePreferences.setRawBackupZipfile(ApplicationDependencies.getApplication(), enabled)
    refresh()
  }

  // JW: added
  fun setChatBackupZipfilePlain(enabled: Boolean) {
    TextSecurePreferences.setPlainBackupZipfile(ApplicationDependencies.getApplication(), enabled)
    refresh()
  }

  // JW: added
  fun keepViewOnceMessages(enabled: Boolean) {
    TextSecurePreferences.setKeepViewOnceMessages(ApplicationDependencies.getApplication(), enabled)
    refresh()
  }

  // JW: added
  fun ignoreRemoteDelete(enabled: Boolean) {
    TextSecurePreferences.setIgnoreRemoteDelete(ApplicationDependencies.getApplication(), enabled)
    refresh()
  }

  // JW: added
  fun deleteMediaOnly(enabled: Boolean) {
    TextSecurePreferences.setDeleteMediaOnly(ApplicationDependencies.getApplication(), enabled)
    refresh()
  }

  // JW: added
  fun setGoogleMapType(mapType: String) {
    TextSecurePreferences.setGoogleMapType(ApplicationDependencies.getApplication(), mapType)
    refresh()
  }

  // JW: added
  fun setWhoCanAddYouToGroups(adder: String) {
    TextSecurePreferences.setWhoCanAddYouToGroups(ApplicationDependencies.getApplication(), adder)
    refresh()
  }

  // JW: added
  private fun getState() = ChatsSettingsState(
    generateLinkPreviews = SignalStore.settings().isLinkPreviewsEnabled,
    useAddressBook = SignalStore.settings().isPreferSystemContactPhotos,
    keepMutedChatsArchived = SignalStore.settings().shouldKeepMutedChatsArchived(),
    useSystemEmoji = SignalStore.settings().isPreferSystemEmoji,
    enterKeySends = SignalStore.settings().isEnterKeySends,
    chatBackupsEnabled = SignalStore.settings().isBackupEnabled,
    useAsDefaultSmsApp = Util.isDefaultSmsProvider(ApplicationDependencies.getApplication()),
    chatBackupsLocationApi30 = UriUtils.getFullPathFromTreeUri(ApplicationDependencies.getApplication(), SignalStore.settings().signalBackupDirectory),
    chatBackupsLocation = TextSecurePreferences.isBackupLocationRemovable(ApplicationDependencies.getApplication()),
    chatBackupZipfile = TextSecurePreferences.isRawBackupInZipfile(ApplicationDependencies.getApplication()),
    chatBackupZipfilePlain = TextSecurePreferences.isPlainBackupInZipfile(ApplicationDependencies.getApplication()),
    keepViewOnceMessages = TextSecurePreferences.isKeepViewOnceMessages(ApplicationDependencies.getApplication()),
    ignoreRemoteDelete = TextSecurePreferences.isIgnoreRemoteDelete(ApplicationDependencies.getApplication()),
    deleteMediaOnly = TextSecurePreferences.isDeleteMediaOnly(ApplicationDependencies.getApplication()),
    googleMapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication()),
    whoCanAddYouToGroups = TextSecurePreferences.whoCanAddYouToGroups(ApplicationDependencies.getApplication())
  )
}
