package org.thoughtcrime.securesms.webrtc.audio

import android.content.Context
import android.media.AudioDeviceCallback
import android.media.AudioDeviceInfo
import android.media.AudioManager
import android.net.Uri
import androidx.annotation.RequiresApi
import org.signal.core.util.logging.Log
import org.thoughtcrime.securesms.recipients.RecipientId

/**
 * API 31 introduces new audio manager methods to handle audio routing, including to Bluetooth devices.
 * This is important because API 31 also introduces new, more restrictive bluetooth permissioning,
 * and the previous SignalAudioManager implementation would have required us to ask for (poorly labeled & scary) Bluetooth permissions.
 */
@RequiresApi(31)
class FullSignalAudioManagerApi31(context: Context, eventListener: EventListener?) : SignalAudioManager(context, eventListener) {
  private val TAG = "SignalAudioManager31"

  private var defaultAudioDevice: AudioDevice = AudioDevice.EARPIECE
  private var userSelectedAudioDevice: AudioDeviceInfo? = null
  private var savedAudioMode = AudioManager.MODE_INVALID
  private var savedIsSpeakerPhoneOn = false
  private var savedIsMicrophoneMute = false
  private var hasWiredHeadset = false

  private val deviceCallback = object : AudioDeviceCallback() {

    override fun onAudioDevicesAdded(addedDevices: Array<out AudioDeviceInfo>) {
      super.onAudioDevicesAdded(addedDevices)
      updateAudioDeviceState()
    }

    override fun onAudioDevicesRemoved(removedDevices: Array<out AudioDeviceInfo>) {
      super.onAudioDevicesRemoved(removedDevices)
      updateAudioDeviceState()
    }
  }

  override fun setDefaultAudioDevice(recipientId: RecipientId?, newDefaultDevice: AudioDevice, clearUserEarpieceSelection: Boolean) {
    Log.d(TAG, "setDefaultAudioDevice(): currentDefault: $defaultAudioDevice device: $newDefaultDevice clearUser: $clearUserEarpieceSelection")
    defaultAudioDevice = when (newDefaultDevice) {
      AudioDevice.SPEAKER_PHONE -> newDefaultDevice
      AudioDevice.EARPIECE -> {
        if (androidAudioManager.hasEarpiece(context)) {
          newDefaultDevice
        } else {
          AudioDevice.SPEAKER_PHONE
        }
      }
      else -> throw AssertionError("Invalid default audio device selection")
    }

    val userSelectedDeviceType: AudioDevice = userSelectedAudioDevice?.type?.let { AudioDeviceMapping.fromPlatformType(it) } ?: AudioDevice.NONE
    if (clearUserEarpieceSelection && userSelectedDeviceType == AudioDevice.EARPIECE) {
      Log.d(TAG, "Clearing user setting of earpiece")
      userSelectedAudioDevice = null
    }

    Log.d(TAG, "New default: $defaultAudioDevice userSelected: ${userSelectedAudioDevice?.id} of type ${userSelectedAudioDevice?.type}")
    updateAudioDeviceState()
  }

  override fun initialize() {
    if (state == State.UNINITIALIZED) {
      savedAudioMode = androidAudioManager.mode
      savedIsSpeakerPhoneOn = androidAudioManager.isSpeakerphoneOn
      savedIsMicrophoneMute = androidAudioManager.isMicrophoneMute
      hasWiredHeadset = androidAudioManager.isWiredHeadsetOn

      val focusedGained = androidAudioManager.requestCallAudioFocus()
      if (!focusedGained) {
        handler.postDelayed({ androidAudioManager.requestCallAudioFocus() }, 500)
      }

      setMicrophoneMute(false)

      updateAudioDeviceState()
      androidAudioManager.registerAudioDeviceCallback(deviceCallback, handler)
      state = State.PREINITIALIZED

      Log.d(TAG, "Initialized")
    }
  }

  override fun start() {
    incomingRinger.stop()
    outgoingRinger.stop()

    val focusedGained = androidAudioManager.requestCallAudioFocus()
    if (!focusedGained) {
      handler.postDelayed({ androidAudioManager.requestCallAudioFocus() }, 500)
    }

    state = State.RUNNING
    androidAudioManager.mode = AudioManager.MODE_IN_COMMUNICATION
    val volume: Float = androidAudioManager.ringVolumeWithMinimum()
    soundPool.play(connectedSoundId, volume, volume, 0, 0, 1.0f)

    Log.d(TAG, "Started")
  }

  override fun stop(playDisconnect: Boolean) {
    incomingRinger.stop()
    outgoingRinger.stop()

    if (playDisconnect && state != State.UNINITIALIZED) {
      val volume: Float = androidAudioManager.ringVolumeWithMinimum()
      soundPool.play(disconnectedSoundId, volume, volume, 0, 0, 1.0f)
    }
    androidAudioManager.unregisterAudioDeviceCallback(deviceCallback)
    if (state == State.UNINITIALIZED && userSelectedAudioDevice != null) {
      Log.d(
        TAG,
        "Stopping audio manager after selecting audio device but never initializing. " +
          "This indicates a service spun up solely to set audio device. " +
          "Therefore skipping audio device reset."
      )
    } else {
      androidAudioManager.clearCommunicationDevice()
      setSpeakerphoneOn(savedIsSpeakerPhoneOn)
      setMicrophoneMute(savedIsMicrophoneMute)
      androidAudioManager.mode = savedAudioMode
    }
    androidAudioManager.abandonCallAudioFocus()
    Log.d(TAG, "Abandoned audio focus for VOICE_CALL streams")
    state = State.UNINITIALIZED

    Log.d(TAG, "Stopped")
  }

  override fun selectAudioDevice(recipientId: RecipientId?, device: Int, isId: Boolean) {
    if (!isId) {
      throw IllegalArgumentException("Must supply a device address for API 31+.")
    }

    Log.d(TAG, "Selecting $device")

    userSelectedAudioDevice = androidAudioManager.availableCommunicationDevices.find { it.id == device }

    updateAudioDeviceState()
  }

  override fun startIncomingRinger(ringtoneUri: Uri?, vibrate: Boolean) {
    Log.i(TAG, "startIncomingRinger(): uri: ${if (ringtoneUri != null) "present" else "null"} vibrate: $vibrate")
    androidAudioManager.mode = AudioManager.MODE_RINGTONE
    setMicrophoneMute(false)
    setDefaultAudioDevice(recipientId = null, newDefaultDevice = AudioDevice.SPEAKER_PHONE, clearUserEarpieceSelection = false)
    incomingRinger.start(ringtoneUri, vibrate)
  }

  override fun startOutgoingRinger() {
    Log.i(TAG, "startOutgoingRinger(): currentDevice: $selectedAudioDevice")
    androidAudioManager.mode = AudioManager.MODE_IN_COMMUNICATION
    setMicrophoneMute(false)
    outgoingRinger.start(OutgoingRinger.Type.RINGING)
  }

  private fun setSpeakerphoneOn(on: Boolean) {
    if (androidAudioManager.isSpeakerphoneOn != on) {
      androidAudioManager.isSpeakerphoneOn = on
    }
  }

  private fun setMicrophoneMute(on: Boolean) {
    if (androidAudioManager.isMicrophoneMute != on) {
      androidAudioManager.isMicrophoneMute = on
    }
  }

  private fun updateAudioDeviceState() {
    handler.assertHandlerThread()

    val currentAudioDevice: AudioDeviceInfo? = androidAudioManager.communicationDevice

    val availableCommunicationDevices: List<AudioDeviceInfo> = androidAudioManager.availableCommunicationDevices
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    var candidate: AudioDeviceInfo? = userSelectedAudioDevice
    if (candidate != null && candidate.id != 0) {
      val result = androidAudioManager.setCommunicationDevice(candidate)
      if (result) {
        eventListener?.onAudioDeviceChanged(AudioDeviceMapping.fromPlatformType(candidate.type), availableCommunicationDevices.map { AudioDeviceMapping.fromPlatformType(it.type) }.toSet())
      } else {
        Log.w(TAG, "Failed to set ${candidate.id} of type ${candidate.type}as communication device.")
      }
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    availableCommunicationDevices.forEach { Log.d(TAG, "Detected communication device of type: ${it.type}") }
    val hasBluetoothHeadset = isBluetoothHeadsetConnected()
    hasWiredHeadset = availableCommunicationDevices.any { AudioDeviceMapping.fromPlatformType(it.type) == AudioDevice.WIRED_HEADSET }
    Log.i(
      TAG,
      "updateAudioDeviceState(): " +
        "wired: $hasWiredHeadset " +
        "bt: $hasBluetoothHeadset " +
        "available: $availableCommunicationDevices " +
        "selected: $selectedAudioDevice " +
        "userSelected: $userSelectedAudioDevice"
    )
    val audioDevices: MutableSet<AudioDevice> = mutableSetOf(AudioDevice.SPEAKER_PHONE)

    if (hasBluetoothHeadset) {
      audioDevices += AudioDevice.BLUETOOTH
    }

    if (hasWiredHeadset) {
      audioDevices += AudioDevice.WIRED_HEADSET
=======
    if (userSelectedAudioDevice != null) {
      androidAudioManager.communicationDevice = userSelectedAudioDevice
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of a4f383c27f (Bumped to upstream version 6.17.1.0-JW.)
=======
      eventListener?.onAudioDeviceChanged(AudioDeviceMapping.fromPlatformType(userSelectedAudioDevice!!.type), availableCommunicationDevices.map { AudioDeviceMapping.fromPlatformType(it.type) }.toSet())
>>>>>>> a4f383c27f (Bumped to upstream version 6.17.1.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    availableCommunicationDevices.forEach { Log.d(TAG, "Detected communication device of type: ${it.type}") }
    val hasBluetoothHeadset = isBluetoothHeadsetConnected()
    hasWiredHeadset = availableCommunicationDevices.any { AudioDeviceMapping.fromPlatformType(it.type) == AudioDevice.WIRED_HEADSET }
    Log.i(
      TAG,
      "updateAudioDeviceState(): " +
        "wired: $hasWiredHeadset " +
        "bt: $hasBluetoothHeadset " +
        "available: $availableCommunicationDevices " +
        "selected: $selectedAudioDevice " +
        "userSelected: $userSelectedAudioDevice"
    )
    val audioDevices: MutableSet<AudioDevice> = mutableSetOf(AudioDevice.SPEAKER_PHONE)

    if (hasBluetoothHeadset) {
      audioDevices += AudioDevice.BLUETOOTH
    }

    if (hasWiredHeadset) {
      audioDevices += AudioDevice.WIRED_HEADSET
=======
    if (userSelectedAudioDevice != null) {
      androidAudioManager.communicationDevice = userSelectedAudioDevice
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of a4f383c27f (Bumped to upstream version 6.17.1.0-JW.)
=======
      eventListener?.onAudioDeviceChanged(AudioDeviceMapping.fromPlatformType(userSelectedAudioDevice!!.type), availableCommunicationDevices.map { AudioDeviceMapping.fromPlatformType(it.type) }.toSet())
>>>>>>> a4f383c27f (Bumped to upstream version 6.17.1.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    availableCommunicationDevices.forEach { Log.d(TAG, "Detected communication device of type: ${it.type}") }
    val hasBluetoothHeadset = isBluetoothHeadsetConnected()
    hasWiredHeadset = availableCommunicationDevices.any { AudioDeviceMapping.fromPlatformType(it.type) == AudioDevice.WIRED_HEADSET }
    Log.i(
      TAG,
      "updateAudioDeviceState(): " +
        "wired: $hasWiredHeadset " +
        "bt: $hasBluetoothHeadset " +
        "available: $availableCommunicationDevices " +
        "selected: $selectedAudioDevice " +
        "userSelected: $userSelectedAudioDevice"
    )
    val audioDevices: MutableSet<AudioDevice> = mutableSetOf(AudioDevice.SPEAKER_PHONE)

    if (hasBluetoothHeadset) {
      audioDevices += AudioDevice.BLUETOOTH
    }

    if (hasWiredHeadset) {
      audioDevices += AudioDevice.WIRED_HEADSET
=======
    if (userSelectedAudioDevice != null) {
      androidAudioManager.communicationDevice = userSelectedAudioDevice
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    } else {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
      val searchOrder: List<AudioDevice> = listOf(AudioDevice.BLUETOOTH, AudioDevice.WIRED_HEADSET, defaultAudioDevice, AudioDevice.EARPIECE, AudioDevice.SPEAKER_PHONE, AudioDevice.NONE).distinct()
      for (deviceType in searchOrder) {
        candidate = availableCommunicationDevices.filterNot { it.productName.contains(" Watch", true) }.find { AudioDeviceMapping.fromPlatformType(it.type) == deviceType }
        if (candidate != null) {
          break
        }
      }

      when (candidate) {
        null -> {
          Log.e(TAG, "Tried to switch audio devices but could not find suitable device in list of types: ${availableCommunicationDevices.map { it.type }.joinToString()}")
          androidAudioManager.clearCommunicationDevice()
        }
        else -> {
          Log.d(TAG, "Switching to new device of type ${candidate.type} from ${currentAudioDevice?.type}")
          val result = androidAudioManager.setCommunicationDevice(candidate)
          if (result) {
            Log.w(TAG, "Succeeded in setting ${candidate.id} (type: ${candidate.type}) as communication device.")
            eventListener?.onAudioDeviceChanged(AudioDeviceMapping.fromPlatformType(candidate.type), availableCommunicationDevices.map { AudioDeviceMapping.fromPlatformType(it.type) }.toSet())
          } else {
            Log.w(TAG, "Failed to set ${candidate.id} as communication device.")
          }
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
      autoSwitchToWiredHeadset = true
      if (androidAudioManager.hasEarpiece(context)) {
        audioDevices += AudioDevice.EARPIECE
      }
    }

    if (!hasBluetoothHeadset && userSelectedAudioDevice == AudioDevice.BLUETOOTH) {
      userSelectedAudioDevice = AudioDevice.NONE
    }

    if (hasWiredHeadset && autoSwitchToWiredHeadset) {
      userSelectedAudioDevice = AudioDevice.WIRED_HEADSET
      autoSwitchToWiredHeadset = false
    }

    if (!hasWiredHeadset && userSelectedAudioDevice == AudioDevice.WIRED_HEADSET) {
      userSelectedAudioDevice = AudioDevice.NONE
    }

    if (!autoSwitchToBluetooth && !hasBluetoothHeadset) {
      autoSwitchToBluetooth = true
    }

    if (autoSwitchToBluetooth && hasBluetoothHeadset) {
      userSelectedAudioDevice = AudioDevice.BLUETOOTH
      autoSwitchToBluetooth = false
    }

    val deviceToSet: AudioDevice = when {
      audioDevices.contains(userSelectedAudioDevice) -> userSelectedAudioDevice
      audioDevices.contains(defaultAudioDevice) -> defaultAudioDevice
      else -> AudioDevice.SPEAKER_PHONE
    }

    if (deviceToSet != currentAudioDevice) {
      try {
        val chosenDevice: AudioDeviceInfo = availableCommunicationDevices.first { AudioDeviceMapping.getEquivalentPlatformTypes(deviceToSet).contains(it.type) }
        val result = androidAudioManager.setCommunicationDevice(chosenDevice)
        if (result) {
          Log.i(TAG, "Set active device to ID ${chosenDevice.id}, type ${chosenDevice.type}")
          currentAudioDevice = deviceToSet
          eventListener?.onAudioDeviceChanged(currentAudioDevice, availableCommunicationDevices.map { AudioDeviceMapping.fromPlatformType(it.type) }.toSet())
        } else {
          Log.w(TAG, "Setting device $chosenDevice failed.")
=======
      val excludedDevices = emptyList<String>() // TODO: pull this from somewhere. Preferences?
      val autoSelectableDevices = availableCommunicationDevices.filterNot { excludedDevices.contains(it.address) }
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      val excludedDevices = emptyList<String>() // TODO: pull this from somewhere. Preferences?
      val autoSelectableDevices = availableCommunicationDevices.filterNot { excludedDevices.contains(it.address) }
=======
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      var candidate: AudioDeviceInfo? = null
      val searchOrder: List<AudioDevice> = listOf(AudioDevice.BLUETOOTH, AudioDevice.WIRED_HEADSET, defaultAudioDevice, AudioDevice.EARPIECE, AudioDevice.SPEAKER_PHONE, AudioDevice.NONE).distinct()
      for (deviceType in searchOrder) {
        candidate = availableCommunicationDevices.find { AudioDeviceMapping.fromPlatformType(it.type) == deviceType }
        if (candidate != null) {
          break
        }
      }

      when (candidate) {
        null -> {
          Log.e(TAG, "Tried to switch audio devices but could not find suitable device in list of types: ${availableCommunicationDevices.map { it.type }.joinToString()}")
          androidAudioManager.clearCommunicationDevice()
        }
        else -> {
          Log.d(TAG, "Switching to new device of type ${candidate.type} from ${currentAudioDevice?.type}")
          androidAudioManager.communicationDevice = candidate
          eventListener?.onAudioDeviceChanged(AudioDeviceMapping.fromPlatformType(candidate.type), availableCommunicationDevices.map { AudioDeviceMapping.fromPlatformType(it.type) }.toSet())
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
      autoSwitchToWiredHeadset = true
      if (androidAudioManager.hasEarpiece(context)) {
        audioDevices += AudioDevice.EARPIECE
      }
    }

    if (!hasBluetoothHeadset && userSelectedAudioDevice == AudioDevice.BLUETOOTH) {
      userSelectedAudioDevice = AudioDevice.NONE
    }

    if (hasWiredHeadset && autoSwitchToWiredHeadset) {
      userSelectedAudioDevice = AudioDevice.WIRED_HEADSET
      autoSwitchToWiredHeadset = false
    }

    if (!hasWiredHeadset && userSelectedAudioDevice == AudioDevice.WIRED_HEADSET) {
      userSelectedAudioDevice = AudioDevice.NONE
    }

    if (!autoSwitchToBluetooth && !hasBluetoothHeadset) {
      autoSwitchToBluetooth = true
    }

    if (autoSwitchToBluetooth && hasBluetoothHeadset) {
      userSelectedAudioDevice = AudioDevice.BLUETOOTH
      autoSwitchToBluetooth = false
    }

    val deviceToSet: AudioDevice = when {
      audioDevices.contains(userSelectedAudioDevice) -> userSelectedAudioDevice
      audioDevices.contains(defaultAudioDevice) -> defaultAudioDevice
      else -> AudioDevice.SPEAKER_PHONE
    }

    if (deviceToSet != currentAudioDevice) {
      try {
        val chosenDevice: AudioDeviceInfo = availableCommunicationDevices.first { AudioDeviceMapping.getEquivalentPlatformTypes(deviceToSet).contains(it.type) }
        val result = androidAudioManager.setCommunicationDevice(chosenDevice)
        if (result) {
          Log.i(TAG, "Set active device to ID ${chosenDevice.id}, type ${chosenDevice.type}")
          currentAudioDevice = deviceToSet
          eventListener?.onAudioDeviceChanged(currentAudioDevice, availableCommunicationDevices.map { AudioDeviceMapping.fromPlatformType(it.type) }.toSet())
        } else {
          Log.w(TAG, "Setting device $chosenDevice failed.")
=======
      val excludedDevices = emptyList<String>() // TODO: pull this from somewhere. Preferences?
      val autoSelectableDevices = availableCommunicationDevices.filterNot { excludedDevices.contains(it.address) }
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      val excludedDevices = emptyList<String>() // TODO: pull this from somewhere. Preferences?
      val autoSelectableDevices = availableCommunicationDevices.filterNot { excludedDevices.contains(it.address) }
=======
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      var candidate: AudioDeviceInfo? = null
      val searchOrder: List<AudioDevice> = listOf(AudioDevice.BLUETOOTH, AudioDevice.WIRED_HEADSET, defaultAudioDevice, AudioDevice.EARPIECE, AudioDevice.SPEAKER_PHONE, AudioDevice.NONE).distinct()
      for (deviceType in searchOrder) {
        candidate = availableCommunicationDevices.find { AudioDeviceMapping.fromPlatformType(it.type) == deviceType }
        if (candidate != null) {
          break
        }
      }

      when (candidate) {
        null -> {
          Log.e(TAG, "Tried to switch audio devices but could not find suitable device in list of types: ${availableCommunicationDevices.map { it.type }.joinToString()}")
          androidAudioManager.clearCommunicationDevice()
        }
        else -> {
          Log.d(TAG, "Switching to new device of type ${candidate.type} from ${currentAudioDevice?.type}")
          androidAudioManager.communicationDevice = candidate
          eventListener?.onAudioDeviceChanged(AudioDeviceMapping.fromPlatformType(candidate.type), availableCommunicationDevices.map { AudioDeviceMapping.fromPlatformType(it.type) }.toSet())
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
      autoSwitchToWiredHeadset = true
      if (androidAudioManager.hasEarpiece(context)) {
        audioDevices += AudioDevice.EARPIECE
      }
    }

    if (!hasBluetoothHeadset && userSelectedAudioDevice == AudioDevice.BLUETOOTH) {
      userSelectedAudioDevice = AudioDevice.NONE
    }

    if (hasWiredHeadset && autoSwitchToWiredHeadset) {
      userSelectedAudioDevice = AudioDevice.WIRED_HEADSET
      autoSwitchToWiredHeadset = false
    }

    if (!hasWiredHeadset && userSelectedAudioDevice == AudioDevice.WIRED_HEADSET) {
      userSelectedAudioDevice = AudioDevice.NONE
    }

    if (!autoSwitchToBluetooth && !hasBluetoothHeadset) {
      autoSwitchToBluetooth = true
    }

    if (autoSwitchToBluetooth && hasBluetoothHeadset) {
      userSelectedAudioDevice = AudioDevice.BLUETOOTH
      autoSwitchToBluetooth = false
    }

    val deviceToSet: AudioDevice = when {
      audioDevices.contains(userSelectedAudioDevice) -> userSelectedAudioDevice
      audioDevices.contains(defaultAudioDevice) -> defaultAudioDevice
      else -> AudioDevice.SPEAKER_PHONE
    }

    if (deviceToSet != currentAudioDevice) {
      try {
        val chosenDevice: AudioDeviceInfo = availableCommunicationDevices.first { AudioDeviceMapping.getEquivalentPlatformTypes(deviceToSet).contains(it.type) }
        val result = androidAudioManager.setCommunicationDevice(chosenDevice)
        if (result) {
          Log.i(TAG, "Set active device to ID ${chosenDevice.id}, type ${chosenDevice.type}")
          currentAudioDevice = deviceToSet
          eventListener?.onAudioDeviceChanged(currentAudioDevice, availableCommunicationDevices.map { AudioDeviceMapping.fromPlatformType(it.type) }.toSet())
        } else {
          Log.w(TAG, "Setting device $chosenDevice failed.")
=======
      val excludedDevices = emptyList<String>() // TODO: pull this from somewhere. Preferences?
      val autoSelectableDevices = availableCommunicationDevices.filterNot { excludedDevices.contains(it.address) }
      var candidate: AudioDeviceInfo? = null
      val searchOrder: List<AudioDevice> = listOf(defaultAudioDevice) + AudioDeviceMapping.orderOfPreference.filterNot { it == defaultAudioDevice }
      for (deviceType in searchOrder) {
        candidate = autoSelectableDevices.find { AudioDeviceMapping.fromPlatformType(it.type) == deviceType }
        if (candidate != null) {
          break
        }
      }

      when (candidate) {
        null -> {
          Log.e(TAG, "Tried to switch audio devices but could not find suitable device in list of types: ${autoSelectableDevices.map { it.type }.joinToString()}")
          androidAudioManager.clearCommunicationDevice()
        }
        currentAudioDevice -> Log.d(TAG, "Request to switch to existing audio device ignored.")
        else -> {
          Log.d(TAG, "Switching to new device of type ${candidate.type} from ${currentAudioDevice?.type}")
          androidAudioManager.communicationDevice = candidate
          eventListener?.onAudioDeviceChanged(AudioDeviceMapping.fromPlatformType(candidate.type), availableCommunicationDevices.map { AudioDeviceMapping.fromPlatformType(it.type) }.toSet())
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        }
      }
    }
  }
}
