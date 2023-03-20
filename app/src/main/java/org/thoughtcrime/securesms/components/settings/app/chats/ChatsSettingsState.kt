package org.thoughtcrime.securesms.components.settings.app.chats

data class ChatsSettingsState(
  val generateLinkPreviews: Boolean,
  val useAddressBook: Boolean,
  val keepMutedChatsArchived: Boolean,
  val useSystemEmoji: Boolean,
  val enterKeySends: Boolean,
<<<<<<< HEAD
  val localBackupsEnabled: Boolean,
  val canAccessRemoteBackupsSettings: Boolean
=======
  val chatBackupsEnabled: Boolean,
  val useAsDefaultSmsApp: Boolean,
  val smsExportState: SmsExportState = SmsExportState.FETCHING
  // JW: added extra preferences
  ,
  val chatBackupsLocation: Boolean,
  val chatBackupsLocationApi30: String,
  val chatBackupZipfile: Boolean,
  val chatBackupZipfilePlain: Boolean,
  val keepViewOnceMessages: Boolean,
  val ignoreRemoteDelete: Boolean,
  val deleteMediaOnly: Boolean,
  val googleMapType: String,
  val whoCanAddYouToGroups: String
>>>>>>> 66c339aa35 (Added extra options)
)
