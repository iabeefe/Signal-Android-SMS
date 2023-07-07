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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
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
||||||| parent of 775ec008cc (Added extra options)
=======
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
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
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
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
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
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
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
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
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
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
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
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
=======
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
>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
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
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
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
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
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
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
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
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
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
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
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
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
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
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
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
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
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
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
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
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
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
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
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
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
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
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
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
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
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
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
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
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
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
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
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
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
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
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
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
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of cdbbc46ede (Added extra options)
=======
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
>>>>>>> cdbbc46ede (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======
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
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
=======
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
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
=======
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
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
=======
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
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
=======
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
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======
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
||||||| parent of 775ec008cc (Added extra options)
=======
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
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
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
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
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
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
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
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
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
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
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
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
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
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
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
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
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
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
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
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
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
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
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
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
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
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
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
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
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
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
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
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
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
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
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
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
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
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
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
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
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
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
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
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
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
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
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
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
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
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
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
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======
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
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
=======
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
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
=======
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
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
=======
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
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
=======
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
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======
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
||||||| parent of 775ec008cc (Added extra options)
=======
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
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
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
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
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
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
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
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
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
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
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
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
=======
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
>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
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
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
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
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
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
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
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
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
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
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
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
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
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
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
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
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
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
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
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
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
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
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
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
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
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
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
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
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
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
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
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
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
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
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
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
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
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
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
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
>>>>>>> 17c88722b3 (Added extra options)
)
