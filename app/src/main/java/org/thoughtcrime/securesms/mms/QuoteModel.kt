package org.thoughtcrime.securesms.mms

import org.thoughtcrime.securesms.attachments.Attachment
import org.thoughtcrime.securesms.database.model.Mention
import org.thoughtcrime.securesms.database.model.databaseprotos.BodyRangeList
import org.thoughtcrime.securesms.recipients.RecipientId
import org.whispersystems.signalservice.api.messages.SignalServiceDataMessage
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import org.whispersystems.signalservice.internal.push.DataMessage
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import org.whispersystems.signalservice.internal.push.SignalServiceProtos
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import org.whispersystems.signalservice.internal.push.SignalServiceProtos
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import org.whispersystems.signalservice.internal.push.SignalServiceProtos
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)

class QuoteModel(
  val id: Long,
  val author: RecipientId,
  val text: String,
  val isOriginalMissing: Boolean,
  val attachments: List<Attachment>,
  mentions: List<Mention>?,
  val type: Type,
  val bodyRanges: BodyRangeList?
) {
  val mentions: List<Mention>

  init {
    this.mentions = mentions ?: emptyList()
  }

  enum class Type(val code: Int, val dataMessageType: SignalServiceDataMessage.Quote.Type) {

    NORMAL(0, SignalServiceDataMessage.Quote.Type.NORMAL),
    GIFT_BADGE(1, SignalServiceDataMessage.Quote.Type.GIFT_BADGE);

    companion object {
      @JvmStatic
      fun fromCode(code: Int): Type {
        for (value in values()) {
          if (value.code == code) {
            return value
          }
        }
        throw IllegalArgumentException("Invalid code: $code")
      }

      @JvmStatic
      fun fromDataMessageType(dataMessageType: SignalServiceDataMessage.Quote.Type): Type {
        for (value in values()) {
          if (value.dataMessageType === dataMessageType) {
            return value
          }
        }
        return NORMAL
      }
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD

      fun fromProto(type: DataMessage.Quote.Type?): Type {
        return if (type == DataMessage.Quote.Type.GIFT_BADGE) {
          GIFT_BADGE
        } else {
          NORMAL
        }
      }
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======

      fun fromProto(type: SignalServiceProtos.DataMessage.Quote.Type): Type {
        return if (type == SignalServiceProtos.DataMessage.Quote.Type.GIFT_BADGE) {
          GIFT_BADGE
        } else {
          NORMAL
        }
      }
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======

      fun fromProto(type: SignalServiceProtos.DataMessage.Quote.Type): Type {
        return if (type == SignalServiceProtos.DataMessage.Quote.Type.GIFT_BADGE) {
          GIFT_BADGE
        } else {
          NORMAL
        }
      }
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======

      fun fromProto(type: SignalServiceProtos.DataMessage.Quote.Type): Type {
        return if (type == SignalServiceProtos.DataMessage.Quote.Type.GIFT_BADGE) {
          GIFT_BADGE
        } else {
          NORMAL
        }
      }
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    }
  }
}
