<<<<<<< HEAD
package org.thoughtcrime.securesms.keyvalue

import org.thoughtcrime.securesms.dependencies.ApplicationDependencies
import org.thoughtcrime.securesms.util.Util
import kotlin.time.Duration.Companion.days
/*
enum class SmsExportPhase(val duration: Long) {
  PHASE_1(0.days.inWholeMilliseconds),
  PHASE_2(21.days.inWholeMilliseconds),
  PHASE_3(51.days.inWholeMilliseconds);

  fun allowSmsFeatures(): Boolean {
    return Util.isDefaultSmsProvider(ApplicationDependencies.getApplication())
  }

  fun isSmsSupported(): Boolean {
    return this != PHASE_3
  }

  fun isFullscreen(): Boolean {
    return this.ordinal > PHASE_1.ordinal
  }

  fun isBlockingUi(): Boolean {
    return this == PHASE_3
  }

  companion object {
    @JvmStatic
    fun getCurrentPhase(duration: Long): SmsExportPhase {
      return values().findLast { duration >= it.duration }!!
    }
  }
}
*/
||||||| 69e1146e2c
=======
package org.thoughtcrime.securesms.keyvalue
/*
enum class SmsExportPhase(val duration: Long) {
  PHASE_3(0);

  fun allowSmsFeatures(): Boolean {
    return false
  }

  fun isSmsSupported(): Boolean {
    return false
  }

  fun isBlockingUi(): Boolean {
    return true
  }

  companion object {
    @JvmStatic
    fun getCurrentPhase(): SmsExportPhase {
      return PHASE_3
    }
  }
}
*/
>>>>>>> 94387f59e83f9be48a18536ad0b22f950783b09e
