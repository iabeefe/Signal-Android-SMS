package org.thoughtcrime.securesms.components.settings.app.privacy

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.thoughtcrime.securesms.dependencies.AppDependencies
import org.thoughtcrime.securesms.keyvalue.SignalStore
import org.thoughtcrime.securesms.util.TextSecurePreferences
import org.thoughtcrime.securesms.util.livedata.Store

class PrivacySettingsViewModel(
  private val sharedPreferences: SharedPreferences,
  private val repository: PrivacySettingsRepository
) : ViewModel() {

  private val store = Store(getState())

  val state: LiveData<PrivacySettingsState> = store.stateLiveData

  fun refreshBlockedCount() {
    repository.getBlockedCount { count ->
      store.update { it.copy(blockedCount = count) }
      refresh()
    }
  }

  fun setReadReceiptsEnabled(enabled: Boolean) {
    sharedPreferences.edit().putBoolean(TextSecurePreferences.READ_RECEIPTS_PREF, enabled).apply()
    repository.syncReadReceiptState()
    refresh()
  }

  fun setTypingIndicatorsEnabled(enabled: Boolean) {
    sharedPreferences.edit().putBoolean(TextSecurePreferences.TYPING_INDICATORS, enabled).apply()
    repository.syncTypingIndicatorsState()
    refresh()
  }

  fun setScreenSecurityEnabled(enabled: Boolean) {
    sharedPreferences.edit().putBoolean(TextSecurePreferences.SCREEN_SECURITY_PREF, enabled).apply()
    refresh()
  }

  fun setIncognitoKeyboard(enabled: Boolean) {
    sharedPreferences.edit().putBoolean(TextSecurePreferences.INCOGNITO_KEYBORAD_PREF, enabled).apply()
    refresh()
  }

  fun togglePaymentLock(enable: Boolean) {
    SignalStore.payments.paymentLock = enable
    refresh()
  }

  fun setObsoletePasswordTimeoutEnabled(enabled: Boolean) {
    SignalStore.settings.passphraseTimeoutEnabled = enabled
    refresh()
  }

  fun setObsoletePasswordTimeout(minutes: Int) {
    SignalStore.settings.passphraseTimeout = minutes
    refresh()
  }

  // JW: added
  fun setPassphraseEnabled(enabled: Boolean) {
    sharedPreferences.edit().putBoolean(TextSecurePreferences.DISABLE_PASSPHRASE_PREF, !enabled).apply()
    sharedPreferences.edit().putBoolean("pref_enable_passphrase_temporary", enabled).apply()
    sharedPreferences.edit().putBoolean(TextSecurePreferences.SCREEN_LOCK, !enabled).apply()
    refresh()
  }

  // JW: added
  fun setOnlyScreenlockEnabled(enabled: Boolean) {
    sharedPreferences.edit().putBoolean(TextSecurePreferences.DISABLE_PASSPHRASE_PREF, true).apply()
    sharedPreferences.edit().putBoolean("pref_enable_passphrase_temporary", false).apply()
    sharedPreferences.edit().putBoolean(TextSecurePreferences.SCREEN_LOCK, enabled).apply()
    refresh()
  }

  // JW: added
  fun setNoLock() {
    sharedPreferences.edit().putBoolean(TextSecurePreferences.DISABLE_PASSPHRASE_PREF, true).apply()
    sharedPreferences.edit().putBoolean("pref_enable_passphrase_temporary", false).apply()
    sharedPreferences.edit().putBoolean(TextSecurePreferences.SCREEN_LOCK, false).apply()
    refresh()
  }

  // JW: added method.
  fun isPassphraseSelected(): Boolean {
    // Because this preference may be undefined when this app is first ran we also check if there is a passphrase
    // defined, if so, we assume passphrase protection:
    val myContext = ApplicationDependencies.getApplication()

    return TextSecurePreferences.isProtectionMethodPassphrase(myContext) ||
      TextSecurePreferences.getBooleanPreference(myContext, "pref_enable_passphrase_temporary", false) &&
      !TextSecurePreferences.isPasswordDisabled(myContext)
  }

  fun refresh() {
    store.update(this::updateState)
  }

  private fun getState(): PrivacySettingsState {
    return PrivacySettingsState(
      blockedCount = 0,
<<<<<<< HEAD
      readReceipts = TextSecurePreferences.isReadReceiptsEnabled(AppDependencies.application),
      typingIndicators = TextSecurePreferences.isTypingIndicatorsEnabled(AppDependencies.application),
      screenLock = SignalStore.settings.screenLockEnabled,
      screenLockActivityTimeout = SignalStore.settings.screenLockTimeout,
      screenSecurity = TextSecurePreferences.isScreenSecurityEnabled(AppDependencies.application),
      incognitoKeyboard = TextSecurePreferences.isIncognitoKeyboardEnabled(AppDependencies.application),
      paymentLock = SignalStore.payments.paymentLock,
      isObsoletePasswordEnabled = !SignalStore.settings.passphraseDisabled,
      isObsoletePasswordTimeoutEnabled = SignalStore.settings.passphraseTimeoutEnabled,
      obsoletePasswordTimeout = SignalStore.settings.passphraseTimeout,
      universalExpireTimer = SignalStore.settings.universalExpireTimer
=======
      readReceipts = TextSecurePreferences.isReadReceiptsEnabled(ApplicationDependencies.getApplication()),
      typingIndicators = TextSecurePreferences.isTypingIndicatorsEnabled(ApplicationDependencies.getApplication()),
      screenLock = TextSecurePreferences.isScreenLockEnabled(ApplicationDependencies.getApplication()),
      screenLockActivityTimeout = TextSecurePreferences.getScreenLockTimeout(ApplicationDependencies.getApplication()),
      screenSecurity = TextSecurePreferences.isScreenSecurityEnabled(ApplicationDependencies.getApplication()),
      incognitoKeyboard = TextSecurePreferences.isIncognitoKeyboardEnabled(ApplicationDependencies.getApplication()),
      paymentLock = SignalStore.paymentsValues().paymentLock,
      isObsoletePasswordEnabled = !TextSecurePreferences.isPasswordDisabled(ApplicationDependencies.getApplication()),
      isObsoletePasswordTimeoutEnabled = TextSecurePreferences.isPassphraseTimeoutEnabled(ApplicationDependencies.getApplication()),
      obsoletePasswordTimeout = TextSecurePreferences.getPassphraseTimeoutInterval(ApplicationDependencies.getApplication()),
      universalExpireTimer = SignalStore.settings().universalExpireTimer
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of cdbbc46ede (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> cdbbc46ede (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
      // JW: added
      ,
      isProtectionMethodPassphrase = TextSecurePreferences.isProtectionMethodPassphrase(ApplicationDependencies.getApplication())
>>>>>>> 17c88722b3 (Added extra options)
    )
  }

  private fun updateState(state: PrivacySettingsState): PrivacySettingsState {
    return getState().copy(blockedCount = state.blockedCount)
  }

  class Factory(
    private val sharedPreferences: SharedPreferences,
    private val repository: PrivacySettingsRepository
  ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
      return requireNotNull(modelClass.cast(PrivacySettingsViewModel(sharedPreferences, repository)))
    }
  }
}
