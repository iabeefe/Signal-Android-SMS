package org.thoughtcrime.securesms.components.settings.app.internal

import org.signal.ringrtc.CallManager
import org.thoughtcrime.securesms.emoji.EmojiFiles

data class InternalSettingsState(
  val seeMoreUserDetails: Boolean,
  val shakeToReport: Boolean,
  val gv2forceInvites: Boolean,
  val gv2ignoreP2PChanges: Boolean,
  val allowCensorshipSetting: Boolean,
  val forceWebsocketMode: Boolean,
  val callingServer: String,
  val callingAudioProcessingMethod: CallManager.AudioProcessingMethod,
  val callingDataMode: CallManager.DataMode,
  val callingDisableTelecom: Boolean,
  val callingEnableOboeAdm: Boolean,
  val useBuiltInEmojiSet: Boolean,
  val emojiVersion: EmojiFiles.Version?,
  val removeSenderKeyMinimium: Boolean,
  val delayResends: Boolean,
  val disableStorageService: Boolean,
  val canClearOnboardingState: Boolean,
<<<<<<< HEAD
<<<<<<< HEAD
  val pnpInitialized: Boolean,
  val useConversationItemV2ForMedia: Boolean,
  val hasPendingOneTimeDonation: Boolean,
  val hevcEncoding: Boolean,
  val newCallingUi: Boolean
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  val pnpInitialized: Boolean
=======
  val pnpInitialized: Boolean,
  val useConversationFragmentV2: Boolean
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  val pnpInitialized: Boolean
=======
  val pnpInitialized: Boolean,
  val useConversationFragmentV2: Boolean
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
)
