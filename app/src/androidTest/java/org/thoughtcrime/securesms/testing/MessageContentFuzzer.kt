<<<<<<< HEAD
package org.thoughtcrime.securesms.testing

import okio.ByteString
import okio.ByteString.Companion.toByteString
import org.signal.core.util.Base64
import org.thoughtcrime.securesms.database.AttachmentTable
import org.thoughtcrime.securesms.groups.GroupId
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.buildWith
import org.thoughtcrime.securesms.messages.TestMessage
import org.thoughtcrime.securesms.recipients.Recipient
import org.thoughtcrime.securesms.recipients.RecipientId
import org.whispersystems.signalservice.api.crypto.EnvelopeMetadata
import org.whispersystems.signalservice.api.util.UuidUtil
import org.whispersystems.signalservice.internal.push.AttachmentPointer
import org.whispersystems.signalservice.internal.push.BodyRange
import org.whispersystems.signalservice.internal.push.Content
import org.whispersystems.signalservice.internal.push.DataMessage
import org.whispersystems.signalservice.internal.push.EditMessage
import org.whispersystems.signalservice.internal.push.Envelope
import org.whispersystems.signalservice.internal.push.GroupContextV2
import org.whispersystems.signalservice.internal.push.SyncMessage
import java.util.UUID
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.time.Duration.Companion.days

/**
 * Random but deterministic fuzzer for create various message content protos.
 */
object MessageContentFuzzer {

  private val mediaTypes = listOf("image/png", "image/jpeg", "image/heic", "image/heif", "image/avif", "image/webp", "image/gif", "audio/aac", "audio/*", "video/mp4", "video/*", "text/x-vcard", "text/x-signal-plain", "application/x-signal-view-once", "*/*", "application/octet-stream")
  private val emojis = listOf("😂", "❤️", "🔥", "😍", "👀", "🤔", "🙏", "👍", "🤷", "🥺")

  private val random = Random(1)

  /**
   * Create an [Envelope].
   */
  fun envelope(timestamp: Long, serverGuid: UUID = UUID.randomUUID()): Envelope {
    return Envelope.Builder()
      .timestamp(timestamp)
      .serverTimestamp(timestamp + 5)
      .serverGuid(serverGuid.toString())
      .build()
  }

  /**
   * Create metadata to match an [Envelope].
   */
  fun envelopeMetadata(source: RecipientId, destination: RecipientId, sourceDeviceId: Int = 1, groupId: GroupId.V2? = null): EnvelopeMetadata {
    return EnvelopeMetadata(
      sourceServiceId = Recipient.resolved(source).requireServiceId(),
      sourceE164 = null,
      sourceDeviceId = sourceDeviceId,
      sealedSender = true,
      groupId = groupId?.decodedId,
      destinationServiceId = Recipient.resolved(destination).requireServiceId()
    )
  }

  /**
   * Create a random text message that will contain a body but may also contain
   * - An expire timer value
   * - Bold style body ranges
   */
  fun fuzzTextMessage(sentTimestamp: Long? = null, groupContextV2: GroupContextV2? = null, allowExpireTimeChanges: Boolean = true): Content {
    return Content.Builder()
      .dataMessage(
        DataMessage.Builder().buildWith {
          timestamp = sentTimestamp
          body = string()
          if (allowExpireTimeChanges && random.nextBoolean()) {
            expireTimer = random.nextInt(0..28.days.inWholeSeconds.toInt())
          }
          if (random.nextBoolean()) {
            bodyRanges(
              listOf(
                BodyRange.Builder().buildWith {
                  start = 0
                  length = 1
                  style = BodyRange.Style.BOLD
                }
              )
            )
          }
          if (groupContextV2 != null) {
            groupV2 = groupContextV2
          }
        }
      )
      .build()
  }

  /**
   * Create an edit message.
   */
  fun editTextMessage(targetTimestamp: Long, editedDataMessage: DataMessage): Content {
    return Content.Builder()
      .editMessage(
        EditMessage.Builder().buildWith {
          targetSentTimestamp = targetTimestamp
          dataMessage = editedDataMessage
        }
      )
      .build()
  }

  /**
   * Create a sync sent text message for the given [DataMessage].
   */
  fun syncSentTextMessage(
    textMessage: DataMessage,
    deliveredTo: List<RecipientId>,
    recipientUpdate: Boolean = false
  ): Content {
    return Content
      .Builder()
      .syncMessage(
        SyncMessage.Builder().buildWith {
          sent = SyncMessage.Sent.Builder().buildWith {
            timestamp = textMessage.timestamp
            message = textMessage
            isRecipientUpdate = recipientUpdate
            unidentifiedStatus(
              deliveredTo.map {
                SyncMessage.Sent.UnidentifiedDeliveryStatus.Builder().buildWith {
                  destinationServiceId = Recipient.resolved(it).requireServiceId().toString()
                  unidentified = true
                }
              }
            )
          }
        }
      ).build()
  }

  /**
   * Create a sync reads message for the given [RecipientId] and message timestamp pairings.
   */
  fun syncReadsMessage(timestamps: List<Pair<RecipientId, Long>>): Content {
    return Content
      .Builder()
      .syncMessage(
        SyncMessage.Builder().buildWith {
          read = timestamps.map { (senderId, timestamp) ->
            SyncMessage.Read.Builder().buildWith {
              this.senderAci = Recipient.resolved(senderId).requireAci().toString()
              this.timestamp = timestamp
            }
          }
        }
      ).build()
  }

  fun syncDeleteForMeMessage(allDeletes: List<DeleteForMeSync>): Content {
    return Content
      .Builder()
      .syncMessage(
        SyncMessage(
          deleteForMe = SyncMessage.DeleteForMe(
            messageDeletes = allDeletes.map { (conversationId, conversationDeletes) ->
              val conversation = Recipient.resolved(conversationId)
              SyncMessage.DeleteForMe.MessageDeletes(
                conversation = if (conversation.isGroup) {
                  SyncMessage.DeleteForMe.ConversationIdentifier(threadGroupId = conversation.requireGroupId().decodedId.toByteString())
                } else {
                  SyncMessage.DeleteForMe.ConversationIdentifier(threadServiceId = conversation.requireAci().toString())
                },

                messages = conversationDeletes.map { (author, timestamp) ->
                  SyncMessage.DeleteForMe.AddressableMessage(
                    authorServiceId = Recipient.resolved(author).requireAci().toString(),
                    sentTimestamp = timestamp
                  )
                }
              )
            }
          )
        )
      ).build()
  }

  fun syncDeleteForMeConversation(allDeletes: List<DeleteForMeSync>): Content {
    return Content
      .Builder()
      .syncMessage(
        SyncMessage(
          deleteForMe = SyncMessage.DeleteForMe(
            conversationDeletes = allDeletes.map { delete ->
              val conversation = Recipient.resolved(delete.conversationId)
              SyncMessage.DeleteForMe.ConversationDelete(
                conversation = if (conversation.isGroup) {
                  SyncMessage.DeleteForMe.ConversationIdentifier(threadGroupId = conversation.requireGroupId().decodedId.toByteString())
                } else {
                  SyncMessage.DeleteForMe.ConversationIdentifier(threadServiceId = conversation.requireAci().toString())
                },

                mostRecentMessages = delete.messages.map { (author, timestamp) ->
                  SyncMessage.DeleteForMe.AddressableMessage(
                    authorServiceId = Recipient.resolved(author).requireAci().toString(),
                    sentTimestamp = timestamp
                  )
                },

                mostRecentNonExpiringMessages = delete.nonExpiringMessages.map { (author, timestamp) ->
                  SyncMessage.DeleteForMe.AddressableMessage(
                    authorServiceId = Recipient.resolved(author).requireAci().toString(),
                    sentTimestamp = timestamp
                  )
                },

                isFullDelete = delete.isFullDelete
              )
            }
          )
        )
      ).build()
  }

  fun syncDeleteForMeLocalOnlyConversation(conversations: List<RecipientId>): Content {
    return Content
      .Builder()
      .syncMessage(
        SyncMessage(
          deleteForMe = SyncMessage.DeleteForMe(
            localOnlyConversationDeletes = conversations.map { conversationId ->
              val conversation = Recipient.resolved(conversationId)
              SyncMessage.DeleteForMe.LocalOnlyConversationDelete(
                conversation = if (conversation.isGroup) {
                  SyncMessage.DeleteForMe.ConversationIdentifier(threadGroupId = conversation.requireGroupId().decodedId.toByteString())
                } else {
                  SyncMessage.DeleteForMe.ConversationIdentifier(threadServiceId = conversation.requireAci().toString())
                }
              )
            }
          )
        )
      ).build()
  }

  fun syncDeleteForMeAttachment(conversationId: RecipientId, message: Pair<RecipientId, Long>, uuid: UUID?, digest: ByteArray?, plainTextHash: String?): Content {
    val conversation = Recipient.resolved(conversationId)

    return Content
      .Builder()
      .syncMessage(
        SyncMessage(
          deleteForMe = SyncMessage.DeleteForMe(
            attachmentDeletes = listOf(
              SyncMessage.DeleteForMe.AttachmentDelete(
                conversation = if (conversation.isGroup) {
                  SyncMessage.DeleteForMe.ConversationIdentifier(threadGroupId = conversation.requireGroupId().decodedId.toByteString())
                } else {
                  SyncMessage.DeleteForMe.ConversationIdentifier(threadServiceId = conversation.requireAci().toString())
                },
                targetMessage = SyncMessage.DeleteForMe.AddressableMessage(
                  authorServiceId = Recipient.resolved(message.first).requireAci().toString(),
                  sentTimestamp = message.second
                ),
                uuid = uuid?.let { UuidUtil.toByteString(it) },
                fallbackDigest = digest?.toByteString(),
                fallbackPlaintextHash = plainTextHash?.let { Base64.decodeOrNull(it)?.toByteString() }
              )
            )
          )
        )
      ).build()
  }

  /**
   * Create a random media message that may be:
   * - A text body
   * - A text body with a quote that references an existing message
   * - A text body with a quote that references a non existing message
   * - A message with 0-2 attachment pointers and may contain a text body
   */
  fun fuzzMediaMessageWithBody(quoteAble: List<TestMessage> = emptyList()): Content {
    return Content.Builder()
      .dataMessage(
        DataMessage.Builder().buildWith {
          if (random.nextBoolean()) {
            body = string()
          }

          if (random.nextBoolean() && quoteAble.isNotEmpty()) {
            body = string()
            val quoted = quoteAble.random(random)
            quote = DataMessage.Quote.Builder().buildWith {
              id = quoted.envelope.timestamp
              authorAci = quoted.metadata.sourceServiceId.toString()
              text = quoted.content.dataMessage?.body
              attachments(quoted.content.dataMessage?.attachments ?: emptyList())
              bodyRanges(quoted.content.dataMessage?.bodyRanges ?: emptyList())
              type = DataMessage.Quote.Type.NORMAL
            }
          }

          if (random.nextFloat() < 0.1 && quoteAble.isNotEmpty()) {
            val quoted = quoteAble.random(random)
            quote = DataMessage.Quote.Builder().buildWith {
              id = random.nextLong(quoted.envelope.timestamp!! - 1000000, quoted.envelope.timestamp!!)
              authorAci = quoted.metadata.sourceServiceId.toString()
              text = quoted.content.dataMessage?.body
            }
          }

          if (random.nextFloat() < 0.25) {
            val total = random.nextInt(1, 2)
            attachments((0..total).map { attachmentPointer() })
          }
        }
      )
      .build()
  }

  /**
   * Creates a random media message that contains no traditional media content. It may be:
   * - A reaction to a prior message
   */
  fun fuzzMediaMessageNoContent(previousMessages: List<TestMessage> = emptyList()): Content {
    return Content.Builder()
      .dataMessage(
        DataMessage.Builder().buildWith {
          if (random.nextFloat() < 0.25) {
            val reactTo = previousMessages.random(random)
            reaction = DataMessage.Reaction.Builder().buildWith {
              emoji = emojis.random(random)
              remove = false
              targetAuthorAci = reactTo.metadata.sourceServiceId.toString()
              targetSentTimestamp = reactTo.envelope.timestamp
            }
          }
        }
      ).build()
  }

  /**
   * Create a random media message that contains a sticker.
   */
  fun fuzzStickerMediaMessage(sentTimestamp: Long? = null, groupContextV2: GroupContextV2? = null): Content {
    return Content.Builder()
      .dataMessage(
        DataMessage.Builder().buildWith {
          timestamp = sentTimestamp
          sticker = DataMessage.Sticker.Builder().buildWith {
            packId = byteString(length = 24)
            packKey = byteString(length = 128)
            stickerId = random.nextInt()
            data_ = attachmentPointer()
            emoji = emojis.random(random)
          }
          groupV2 = groupContextV2
        }
      ).build()
  }

  /**
   * Generate a random [String].
   */
  fun string(length: Int = 10, allowNullString: Boolean = false): String {
    var string = ""

    if (allowNullString && random.nextBoolean()) {
      return string
    }

    for (i in 0 until length) {
      string += random.nextInt(65..90).toChar()
    }
    return string
  }

  /**
   * Generate a random [ByteString].
   */
  fun byteString(length: Int = 512): ByteString {
    return random.nextBytes(length).toByteString()
  }

  /**
   * Generate a random [AttachmentPointer].
   */
  fun attachmentPointer(): AttachmentPointer {
    return AttachmentPointer.Builder().run {
      cdnKey = string()
      contentType = mediaTypes.random(random)
      key = byteString()
      size = random.nextInt(1024 * 1024 * 50)
      thumbnail = byteString()
      digest = byteString()
      fileName = string()
      flags = 0
      width = random.nextInt(until = 1024)
      height = random.nextInt(until = 1024)
      caption = string(allowNullString = true)
      blurHash = string()
      uploadTimestamp = random.nextLong()
      cdnNumber = 2

      build()
    }
  }

  /**
   * Creates a server delivered timestamp that is always later than the envelope and server "received" timestamp.
   */
  fun fuzzServerDeliveredTimestamp(envelopeTimestamp: Long): Long {
    return envelopeTimestamp + 10
  }

  data class DeleteForMeSync(
    val conversationId: RecipientId,
    val messages: List<Pair<RecipientId, Long>>,
    val nonExpiringMessages: List<Pair<RecipientId, Long>> = emptyList(),
    val isFullDelete: Boolean = true,
    val attachments: List<Pair<Long, AttachmentTable.SyncAttachmentId>> = emptyList()
  ) {
    constructor(conversationId: RecipientId, vararg messages: Pair<RecipientId, Long>) : this(conversationId, messages.toList())
  }
}
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
package org.thoughtcrime.securesms.testing

import com.google.protobuf.ByteString
import org.signal.core.util.logging.Log
import org.thoughtcrime.securesms.database.model.toProtoByteString
import org.thoughtcrime.securesms.messages.TestMessage
import org.thoughtcrime.securesms.recipients.Recipient
import org.thoughtcrime.securesms.recipients.RecipientId
import org.whispersystems.signalservice.api.crypto.EnvelopeMetadata
import org.whispersystems.signalservice.api.util.UuidUtil
import org.whispersystems.signalservice.internal.push.SignalServiceProtos
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.AttachmentPointer
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.Content
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.DataMessage
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.Envelope
import java.util.UUID
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.functions
import kotlin.reflect.jvm.jvmErasure
import kotlin.time.Duration.Companion.days

object MessageContentFuzzer {

  private val mediaTypes = listOf("image/png", "image/jpeg", "image/heic", "image/heif", "image/avif", "image/webp", "image/gif", "audio/aac", "audio/*", "video/mp4", "video/*", "text/x-vcard", "text/x-signal-plain", "application/x-signal-view-once", "*/*", "application/octet-stream")
  private val emojis = listOf("😂", "❤️", "🔥", "😍", "👀", "🤔", "🙏", "👍", "🤷", "🥺")

  private val random = Random(1)

  fun envelope(timestamp: Long): Envelope {
    return Envelope.newBuilder()
      .setTimestamp(timestamp)
      .setServerTimestamp(timestamp + 5)
      .setServerGuidBytes(UuidUtil.toByteString(UUID.randomUUID()))
      .build()
  }

  fun fuzzTextMessage(): Content {
    return Content.newBuilder()
      .setDataMessage(
        DataMessage.newBuilder().run {
          body = string()
          if (random.nextBoolean()) {
            expireTimer = random.nextInt(0..28.days.inWholeSeconds.toInt())
          }
          if (random.nextBoolean()) {
            addBodyRanges(
              SignalServiceProtos.BodyRange.newBuilder().run {
                start = 0
                length = 1
                style = SignalServiceProtos.BodyRange.Style.BOLD
                build()
              }
            )
          }
          build()
        }
      )
      .build()
  }

  fun fuzzMediaMessageWithBody(quoteAble: List<TestMessage> = emptyList()): Content {
    return Content.newBuilder()
      .setDataMessage(
        DataMessage.newBuilder().run {
          if (random.nextBoolean()) {
            body = string()
          }

          if (random.nextBoolean() && quoteAble.isNotEmpty()) {
            body = string()
            val quoted = quoteAble.random(random)
            quote = DataMessage.Quote.newBuilder().run {
              id = quoted.envelope.timestamp
              authorUuid = quoted.metadata.sourceServiceId.toString()
              text = quoted.content.dataMessage.body
              addAllAttachments(quoted.content.dataMessage.attachmentsList)
              addAllBodyRanges(quoted.content.dataMessage.bodyRangesList)
              type = DataMessage.Quote.Type.NORMAL
              build()
            }
          }

          if (random.nextFloat() < 0.1 && quoteAble.isNotEmpty()) {
            val quoted = quoteAble.random(random)
            quote = DataMessage.Quote.newBuilder().run {
              id = random.nextLong(quoted.envelope.timestamp - 1000000, quoted.envelope.timestamp)
              authorUuid = quoted.metadata.sourceServiceId.toString()
              text = quoted.content.dataMessage.body
              build()
            }
          }

          if (random.nextFloat() < 0.25) {
            val total = random.nextInt(1, 2)
            (0..total).forEach { _ -> addAttachments(attachmentPointer()) }
          }

          build()
        }
      )
      .build()
  }

  fun fuzzMediaMessageNoContent(previousMessages: List<TestMessage> = emptyList()): Content {
    return Content.newBuilder()
      .setDataMessage(
        DataMessage.newBuilder().run {
          if (random.nextFloat() < 0.25) {
            val reactTo = previousMessages.random(random)
            reaction = DataMessage.Reaction.newBuilder().run {
              emoji = emojis.random(random)
              remove = false
              targetAuthorUuid = reactTo.metadata.sourceServiceId.toString()
              targetSentTimestamp = reactTo.envelope.timestamp
              build()
            }
          }

          build()
        }
      ).build()
  }

  fun fuzzMediaMessageNoText(previousMessages: List<TestMessage> = emptyList()): Content {
    return Content.newBuilder()
      .setDataMessage(
        DataMessage.newBuilder().run {
          if (random.nextFloat() < 0.9) {
            sticker = DataMessage.Sticker.newBuilder().run {
              packId = byteString(length = 24)
              packKey = byteString(length = 128)
              stickerId = random.nextInt()
              data = attachmentPointer()
              emoji = emojis.random(random)
              build()
            }
          }
          build()
        }
      ).build()
  }

  fun string(length: Int = 10, allowNullString: Boolean = false): String {
    var string = ""

    if (allowNullString && random.nextBoolean()) {
      return string
    }

    for (i in 0 until length) {
      string += random.nextInt(65..90).toChar()
    }
    return string
  }

  fun byteString(length: Int = 512): ByteString {
    return random.nextBytes(512).toProtoByteString()
  }

  fun attachmentPointer(): SignalServiceProtos.AttachmentPointer {
    return SignalServiceProtos.AttachmentPointer.newBuilder().run {
      cdnKey = string()
      contentType = mediaTypes.random(random)
      key = byteString()
      size = random.nextInt(1024 * 1024 * 50)
      thumbnail = byteString()
      digest = byteString()
      fileName = string()
      flags = 0
      width = random.nextInt(until = 1024)
      height = random.nextInt(until = 1024)
      caption = string(allowNullString = true)
      blurHash = string()
      uploadTimestamp = random.nextLong()
      cdnNumber = 1

      build()
    }
  }

  fun fuzzServerDeliveredTimestamp(envelopeTimestamp: Long): Long {
    return envelopeTimestamp + 10
  }

  fun fuzzMetadata(source: RecipientId, destination: RecipientId): EnvelopeMetadata {
    return EnvelopeMetadata(
      sourceServiceId = Recipient.resolved(source).requireServiceId(),
      sourceE164 = null,
      sourceDeviceId = 1,
      sealedSender = true,
      groupId = null,
      destinationServiceId = Recipient.resolved(destination).requireServiceId()
    )
  }

  fun <T : Any> fuzzProto(protoClazz: KClass<T>) {
    val newBuilder: Any = protoClazz.declaredFunctions.first { it.name == "newBuilder" }.call()!!

    val setters: List<KFunction<*>> = newBuilder::class.functions.filter { it.name.startsWith("set") && !it.name.contains("Bytes") }

    for (setter in setters) {
      val type = setter.parameters[1].type.jvmErasure
      when {
        type == String::class -> setter.call(newBuilder, string())
        type == Int::class -> setter.call(newBuilder, random.nextInt())
        type == Long::class -> setter.call(newBuilder, random.nextLong())
        type == AttachmentPointer::class -> setter.call(newBuilder, attachmentPointer())
        type == Boolean::class -> setter.call(newBuilder, random.nextBoolean())
//        type.superclasses.contains(EnumLite::class) ->
        else -> Log.e("CODY", "WHAT!?!?!?! ${setter.parameters[1].type.jvmErasure}")
      }
    }

    Log.e("CODY", newBuilder::class.functions.first { it.name == "build" }.call(newBuilder).toString())
  }
}
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
