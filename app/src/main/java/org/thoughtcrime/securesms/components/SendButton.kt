package org.thoughtcrime.securesms.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import org.thoughtcrime.securesms.conversation.MessageSendType
import org.thoughtcrime.securesms.util.ViewUtil

/**
 * The send button you see in a conversation.
 * Also encapsulates the long-press menu that allows users to switch [MessageSendType]s.
 */
class SendButton(context: Context, attributeSet: AttributeSet?) : AppCompatImageButton(context, attributeSet), OnLongClickListener {

  private var scheduledSendListener: ScheduledSendListener? = null

  private var popupContainer: ViewGroup? = null

  init {
    setOnLongClickListener(this)
    ViewUtil.mirrorIfRtl(this, getContext())
    setImageResource(MessageSendType.SignalMessageSendType.buttonDrawableRes)
    contentDescription = context.getString(MessageSendType.SignalMessageSendType.titleRes)
  }

  fun setScheduledSendListener(listener: ScheduledSendListener?) {
    this.scheduledSendListener = listener
  }

  /**
   * Must be called with a view that is acceptable for determining the bounds of the popup selector.
   */
  fun setPopupContainer(container: ViewGroup) {
    popupContainer = container
  }

<<<<<<< HEAD
  private fun onSelectionChanged(newType: MessageSendType, isManualSelection: Boolean) {
    setImageResource(newType.buttonDrawableRes)
    contentDescription = context.getString(newType.titleRes)

    for (listener in listeners) {
      listener.onSendTypeChanged(newType, isManualSelection)
    }
  }

  fun showSendTypeMenu(): Boolean {
    return if (availableSendTypes.size == 1) {
      /*if (scheduledSendListener == null && !SignalStore.misc().smsExportPhase.allowSmsFeatures()) {
        Snackbar.make(snackbarContainer, R.string.InputPanel__sms_messaging_is_no_longer_supported_in_signal, Snackbar.LENGTH_SHORT).show()
      }*/
      false
    } else {
      showSendTypeContextMenu(false)
      true
    }
  }

=======
>>>>>>> upstream/main
  override fun onLongClick(v: View): Boolean {
    if (!isEnabled) {
      return false
    }

    val scheduleListener = scheduledSendListener
<<<<<<< HEAD
    if (availableSendTypes.size == 1) {
      return if (scheduleListener?.canSchedule() == true && selectedSendType.transportType != MessageSendType.TransportType.SMS) {
        scheduleListener.onSendScheduled()
        true
      }/* else if (!SignalStore.misc().smsExportPhase.allowSmsFeatures()) {
        Snackbar.make(snackbarContainer, R.string.InputPanel__sms_messaging_is_no_longer_supported_in_signal, Snackbar.LENGTH_SHORT).show()
        true
      }*/ else {
        false
      }
=======

    return if (scheduleListener?.canSchedule() == true) {
      scheduleListener.onSendScheduled()
      true
    } else {
      false
>>>>>>> upstream/main
    }
  }

  interface ScheduledSendListener {
    fun onSendScheduled()
    fun canSchedule(): Boolean
  }
}
