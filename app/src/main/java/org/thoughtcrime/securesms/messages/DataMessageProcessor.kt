<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
package org.thoughtcrime.securesms.messages

import android.content.Context
import android.text.TextUtils
import com.mobilecoin.lib.exceptions.SerializationException
import okio.ByteString.Companion.toByteString
import org.signal.core.util.Base64
import org.signal.core.util.Hex
import org.signal.core.util.concurrent.SignalExecutors
import org.signal.core.util.isNotEmpty
import org.signal.core.util.logging.Log
import org.signal.core.util.orNull
import org.signal.core.util.toOptional
import org.signal.libsignal.zkgroup.groups.GroupSecretParams
import org.signal.libsignal.zkgroup.receipts.ReceiptCredentialPresentation
import org.thoughtcrime.securesms.attachments.Attachment
import org.thoughtcrime.securesms.attachments.PointerAttachment
import org.thoughtcrime.securesms.attachments.TombstoneAttachment
import org.thoughtcrime.securesms.attachments.UriAttachment
import org.thoughtcrime.securesms.calls.links.CallLinks
import org.thoughtcrime.securesms.components.emoji.EmojiUtil
import org.thoughtcrime.securesms.contactshare.Contact
import org.thoughtcrime.securesms.contactshare.ContactModelMapper
import org.thoughtcrime.securesms.crypto.ProfileKeyUtil
import org.thoughtcrime.securesms.crypto.SecurityEvent
import org.thoughtcrime.securesms.database.AttachmentTable
import org.thoughtcrime.securesms.database.MessageTable.InsertResult
import org.thoughtcrime.securesms.database.MessageType
import org.thoughtcrime.securesms.database.NoSuchMessageException
import org.thoughtcrime.securesms.database.PaymentTable.PublicKeyConflictException
import org.thoughtcrime.securesms.database.SignalDatabase
import org.thoughtcrime.securesms.database.model.GroupRecord
import org.thoughtcrime.securesms.database.model.Mention
import org.thoughtcrime.securesms.database.model.MessageId
import org.thoughtcrime.securesms.database.model.MessageRecord
import org.thoughtcrime.securesms.database.model.MmsMessageRecord
import org.thoughtcrime.securesms.database.model.ParentStoryId
import org.thoughtcrime.securesms.database.model.ParentStoryId.DirectReply
import org.thoughtcrime.securesms.database.model.ParentStoryId.GroupReply
import org.thoughtcrime.securesms.database.model.ReactionRecord
import org.thoughtcrime.securesms.database.model.StickerRecord
import org.thoughtcrime.securesms.database.model.databaseprotos.BodyRangeList
import org.thoughtcrime.securesms.database.model.databaseprotos.GiftBadge
import org.thoughtcrime.securesms.database.model.toBodyRangeList
import org.thoughtcrime.securesms.dependencies.AppDependencies
import org.thoughtcrime.securesms.groups.BadGroupIdException
import org.thoughtcrime.securesms.groups.GroupId
import org.thoughtcrime.securesms.jobs.AttachmentDownloadJob
import org.thoughtcrime.securesms.jobs.DirectoryRefreshJob
import org.thoughtcrime.securesms.jobs.GroupCallPeekJob
import org.thoughtcrime.securesms.jobs.GroupV2UpdateSelfProfileKeyJob
import org.thoughtcrime.securesms.jobs.PaymentLedgerUpdateJob
import org.thoughtcrime.securesms.jobs.PaymentTransactionCheckJob
import org.thoughtcrime.securesms.jobs.ProfileKeySendJob
import org.thoughtcrime.securesms.jobs.PushProcessEarlyMessagesJob
import org.thoughtcrime.securesms.jobs.PushProcessMessageJob
import org.thoughtcrime.securesms.jobs.RefreshAttributesJob
import org.thoughtcrime.securesms.jobs.RetrieveProfileJob
import org.thoughtcrime.securesms.jobs.SendDeliveryReceiptJob
import org.thoughtcrime.securesms.jobs.TrimThreadJob
import org.thoughtcrime.securesms.jobs.protos.GroupCallPeekJobData
import org.thoughtcrime.securesms.keyvalue.SignalStore
import org.thoughtcrime.securesms.linkpreview.LinkPreview
import org.thoughtcrime.securesms.linkpreview.LinkPreviewUtil
import org.thoughtcrime.securesms.messages.MessageContentProcessor.Companion.debug
import org.thoughtcrime.securesms.messages.MessageContentProcessor.Companion.log
import org.thoughtcrime.securesms.messages.MessageContentProcessor.Companion.warn
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.expireTimerDuration
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.groupMasterKey
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.hasGroupContext
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.hasRemoteDelete
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isEndSession
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isExpirationUpdate
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isInvalid
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isMediaMessage
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isPaymentActivated
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isPaymentActivationRequest
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isStoryReaction
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.toPointer
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.toPointersWithinLimit
import org.thoughtcrime.securesms.mms.IncomingMessage
import org.thoughtcrime.securesms.mms.MmsException
import org.thoughtcrime.securesms.mms.QuoteModel
import org.thoughtcrime.securesms.mms.StickerSlide
import org.thoughtcrime.securesms.notifications.v2.ConversationId
import org.thoughtcrime.securesms.recipients.Recipient
import org.thoughtcrime.securesms.recipients.Recipient.HiddenState
import org.thoughtcrime.securesms.recipients.RecipientId
import org.thoughtcrime.securesms.recipients.RecipientUtil
import org.thoughtcrime.securesms.stickers.StickerLocator
import org.thoughtcrime.securesms.storage.StorageSyncHelper
import org.thoughtcrime.securesms.util.EarlyMessageCacheEntry
import org.thoughtcrime.securesms.util.LinkUtil
import org.thoughtcrime.securesms.util.MediaUtil
import org.thoughtcrime.securesms.util.MessageConstraintsUtil
import org.thoughtcrime.securesms.util.RemoteConfig
import org.thoughtcrime.securesms.util.SignalLocalMetrics
import org.thoughtcrime.securesms.util.TextSecurePreferences
import org.thoughtcrime.securesms.util.isStory
import org.whispersystems.signalservice.api.crypto.EnvelopeMetadata
import org.whispersystems.signalservice.api.payments.Money
import org.whispersystems.signalservice.api.push.ServiceId
import org.whispersystems.signalservice.api.push.ServiceId.ACI
import org.whispersystems.signalservice.api.util.Preconditions
import org.whispersystems.signalservice.internal.push.BodyRange
import org.whispersystems.signalservice.internal.push.Content
import org.whispersystems.signalservice.internal.push.DataMessage
import org.whispersystems.signalservice.internal.push.Envelope
import org.whispersystems.signalservice.internal.push.GroupContextV2
import org.whispersystems.signalservice.internal.push.Preview
import java.security.SecureRandom
import java.util.Optional
import java.util.UUID
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

object DataMessageProcessor {

  private const val BODY_RANGE_PROCESSING_LIMIT = 250

  fun process(
    context: Context,
    senderRecipient: Recipient,
    threadRecipient: Recipient,
    envelope: Envelope,
    content: Content,
    metadata: EnvelopeMetadata,
    receivedTime: Long,
    earlyMessageCacheEntry: EarlyMessageCacheEntry?,
    localMetrics: SignalLocalMetrics.MessageReceive?
  ) {
    val message: DataMessage = content.dataMessage!!
    val groupSecretParams = if (message.hasGroupContext) GroupSecretParams.deriveFromMasterKey(message.groupV2!!.groupMasterKey) else null
    val groupId: GroupId.V2? = if (groupSecretParams != null) GroupId.v2(groupSecretParams.publicParams.groupIdentifier) else null

    var groupProcessResult: MessageContentProcessor.Gv2PreProcessResult? = null
    if (groupId != null) {
      groupProcessResult = MessageContentProcessor.handleGv2PreProcessing(
        context = context,
        timestamp = envelope.timestamp!!,
        content = content,
        metadata = metadata,
        groupId = groupId,
        groupV2 = message.groupV2!!,
        senderRecipient = senderRecipient,
        groupSecretParams = groupSecretParams,
        serverGuid = envelope.serverGuid
      )

      if (groupProcessResult == MessageContentProcessor.Gv2PreProcessResult.IGNORE) {
        return
      }
      localMetrics?.onGv2Processed()
    }

    var insertResult: InsertResult? = null
    var messageId: MessageId? = null
    when {
      message.isInvalid -> handleInvalidMessage(context, senderRecipient.id, groupId, envelope.timestamp!!)
      message.isEndSession -> insertResult = handleEndSessionMessage(context, senderRecipient.id, envelope, metadata)
      message.isExpirationUpdate -> insertResult = handleExpirationUpdate(envelope, metadata, senderRecipient, threadRecipient.id, groupId, message.expireTimerDuration, message.expireTimerVersion, receivedTime, false)
      message.isStoryReaction -> insertResult = handleStoryReaction(context, envelope, metadata, message, senderRecipient.id, groupId)
      message.reaction != null -> messageId = handleReaction(context, envelope, message, senderRecipient.id, earlyMessageCacheEntry)
      message.hasRemoteDelete -> messageId = handleRemoteDelete(context, envelope, message, senderRecipient.id, earlyMessageCacheEntry)
      message.isPaymentActivationRequest -> insertResult = handlePaymentActivation(envelope, metadata, message, senderRecipient.id, receivedTime, isActivatePaymentsRequest = true, isPaymentsActivated = false)
      message.isPaymentActivated -> insertResult = handlePaymentActivation(envelope, metadata, message, senderRecipient.id, receivedTime, isActivatePaymentsRequest = false, isPaymentsActivated = true)
      message.payment != null -> insertResult = handlePayment(context, envelope, metadata, message, senderRecipient.id, receivedTime)
      message.storyContext != null -> insertResult = handleStoryReply(context, envelope, metadata, message, senderRecipient, groupId, receivedTime)
      message.giftBadge != null -> insertResult = handleGiftMessage(context, envelope, metadata, message, senderRecipient, threadRecipient.id, receivedTime)
      message.isMediaMessage -> insertResult = handleMediaMessage(context, envelope, metadata, message, senderRecipient, threadRecipient, groupId, receivedTime, localMetrics)
      message.body != null -> insertResult = handleTextMessage(context, envelope, metadata, message, senderRecipient, threadRecipient, groupId, receivedTime, localMetrics)
      message.groupCallUpdate != null -> handleGroupCallUpdateMessage(envelope, message, senderRecipient.id, groupId)
    }

    messageId = messageId ?: insertResult?.messageId?.let { MessageId(it) }

    if (groupId != null) {
      val unknownGroup = when (groupProcessResult) {
        MessageContentProcessor.Gv2PreProcessResult.GROUP_UP_TO_DATE -> threadRecipient.isUnknownGroup
        else -> SignalDatabase.groups.isUnknownGroup(groupId)
      }
      if (unknownGroup) {
        handleUnknownGroupMessage(envelope.timestamp!!, message.groupV2!!)
      }
    }

    if (message.profileKey.isNotEmpty()) {
      handleProfileKey(envelope.timestamp!!, message.profileKey!!.toByteArray(), senderRecipient)
    }

    if (groupId == null && senderRecipient.hiddenState == HiddenState.HIDDEN) {
      SignalDatabase.recipients.markHidden(senderRecipient.id, clearProfileKey = false, showMessageRequest = true)
    }

    if (metadata.sealedSender && messageId != null) {
      SignalExecutors.BOUNDED.execute { AppDependencies.jobManager.add(SendDeliveryReceiptJob(senderRecipient.id, message.timestamp!!, messageId)) }
    } else if (!metadata.sealedSender) {
      if (RecipientUtil.shouldHaveProfileKey(threadRecipient)) {
        Log.w(MessageContentProcessor.TAG, "Received an unsealed sender message from " + senderRecipient.id + ", but they should already have our profile key. Correcting.")

        if (groupId != null) {
          Log.i(MessageContentProcessor.TAG, "Message was to a GV2 group. Ensuring our group profile keys are up to date.")
          AppDependencies
            .jobManager
            .startChain(RefreshAttributesJob(false))
            .then(GroupV2UpdateSelfProfileKeyJob.withQueueLimits(groupId))
            .enqueue()
        } else if (!threadRecipient.isGroup) {
          Log.i(MessageContentProcessor.TAG, "Message was to a 1:1. Ensuring this user has our profile key.")
          val profileSendJob = ProfileKeySendJob.create(SignalDatabase.threads.getOrCreateThreadIdFor(threadRecipient), true)
          if (profileSendJob != null) {
            AppDependencies
              .jobManager
              .startChain(RefreshAttributesJob(false))
              .then(profileSendJob)
              .enqueue()
          }
        }
      }
    }

    if (insertResult != null && insertResult.threadWasNewlyCreated && !threadRecipient.isGroup && !threadRecipient.isSelf && !senderRecipient.isSystemContact) {
      val timeSinceLastSync = System.currentTimeMillis() - SignalStore.misc.lastCdsForegroundSyncTime
      if (timeSinceLastSync > RemoteConfig.cdsForegroundSyncInterval || timeSinceLastSync < 0) {
        log(envelope.timestamp!!, "New 1:1 chat. Scheduling a CDS sync to see if they match someone in our contacts.")
        AppDependencies.jobManager.add(DirectoryRefreshJob(false))
        SignalStore.misc.lastCdsForegroundSyncTime = System.currentTimeMillis()
      } else {
        warn(envelope.timestamp!!, "New 1:1 chat, but performed a CDS sync $timeSinceLastSync ms ago, which is less than our threshold. Skipping CDS sync.")
      }
    }

    localMetrics?.onPostProcessComplete()
    localMetrics?.complete(groupId != null)
  }

  private fun handleProfileKey(
    timestamp: Long,
    messageProfileKeyBytes: ByteArray,
    senderRecipient: Recipient
  ) {
    val messageProfileKey = ProfileKeyUtil.profileKeyOrNull(messageProfileKeyBytes)

    if (senderRecipient.isSelf) {
      if (ProfileKeyUtil.getSelfProfileKey() != messageProfileKey) {
        warn(timestamp, "Saw a sync message whose profile key doesn't match our records. Scheduling a storage sync to check.")
        StorageSyncHelper.scheduleSyncForDataChange()
      }
    } else if (messageProfileKey != null) {
      if (messageProfileKeyBytes.contentEquals(senderRecipient.profileKey)) {
        return
      }
      if (SignalDatabase.recipients.setProfileKey(senderRecipient.id, messageProfileKey)) {
        log(timestamp, "Profile key on message from " + senderRecipient.id + " didn't match our local store. It has been updated.")
        SignalDatabase.runPostSuccessfulTransaction {
          RetrieveProfileJob.enqueue(senderRecipient.id)
        }
      }
    } else {
      warn(timestamp.toString(), "Ignored invalid profile key seen in message")
    }
  }

  @Throws(BadGroupIdException::class)
  fun handleUnknownGroupMessage(timestamp: Long, groupContextV2: GroupContextV2) {
    log(timestamp, "Unknown group message.")
    warn(timestamp, "Received a GV2 message for a group we have no knowledge of -- attempting to fix this state.")
    SignalDatabase.groups.fixMissingMasterKey(groupContextV2.groupMasterKey)
  }

  private fun handleInvalidMessage(
    context: Context,
    sender: RecipientId,
    groupId: GroupId?,
    timestamp: Long
  ) {
    log(timestamp, "Invalid message.")

    val insertResult: InsertResult? = insertPlaceholder(sender, timestamp, groupId)
    if (insertResult != null) {
      SignalDatabase.messages.markAsInvalidMessage(insertResult.messageId)
      AppDependencies.messageNotifier.updateNotification(context, ConversationId.forConversation(insertResult.threadId))
    }
  }

  private fun handleEndSessionMessage(
    context: Context,
    senderRecipientId: RecipientId,
    envelope: Envelope,
    metadata: EnvelopeMetadata
  ): InsertResult? {
    log(envelope.timestamp!!, "End session message.")

    val incomingMessage = IncomingMessage(
      from = senderRecipientId,
      sentTimeMillis = envelope.timestamp!!,
      serverTimeMillis = envelope.serverTimestamp!!,
      receivedTimeMillis = System.currentTimeMillis(),
      isUnidentified = metadata.sealedSender,
      serverGuid = envelope.serverGuid,
      type = MessageType.END_SESSION
    )

    val insertResult: InsertResult? = SignalDatabase.messages.insertMessageInbox(incomingMessage).orNull()

    return if (insertResult != null) {
      AppDependencies.protocolStore.aci().deleteAllSessions(metadata.sourceServiceId.toString())
      SecurityEvent.broadcastSecurityUpdateEvent(context)
      AppDependencies.messageNotifier.updateNotification(context, ConversationId.forConversation(insertResult.threadId))
      insertResult
    } else {
      null
    }
  }

  /**
   * @param sideEffect True if the event is side effect of a different message, false if the message itself was an expiration update.
   * @throws StorageFailedException
   */
  @Throws(StorageFailedException::class)
  private fun handleExpirationUpdate(
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    senderRecipient: Recipient,
    threadRecipientId: RecipientId,
    groupId: GroupId.V2?,
    expiresIn: Duration,
    expireTimerVersion: Int?,
    receivedTime: Long,
    sideEffect: Boolean
  ): InsertResult? {
    log(envelope.timestamp!!, "Expiration update. Side effect: $sideEffect")

    if (groupId != null) {
      warn(envelope.timestamp!!, "Expiration update received for GV2. Ignoring.")
      return null
    }

    if (SignalDatabase.recipients.getExpiresInSeconds(threadRecipientId) == expiresIn.inWholeSeconds) {
      log(envelope.timestamp!!, "No change in message expiry for group. Ignoring.")
      return null
    }

    if (expireTimerVersion != null && expireTimerVersion < senderRecipient.expireTimerVersion) {
      log(envelope.timestamp!!, "Old expireTimerVersion. Received: $expireTimerVersion, Current: ${senderRecipient.expireTimerVersion}. Ignoring.")
      return null
    }

    try {
      val mediaMessage = IncomingMessage(
        type = MessageType.EXPIRATION_UPDATE,
        from = senderRecipient.id,
        sentTimeMillis = envelope.timestamp!! - if (sideEffect) 1 else 0,
        serverTimeMillis = envelope.serverTimestamp!!,
        receivedTimeMillis = receivedTime,
        expiresIn = expiresIn.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        serverGuid = envelope.serverGuid
      )

      val insertResult: InsertResult? = SignalDatabase.messages.insertMessageInbox(mediaMessage, -1).orNull()

      if (expireTimerVersion != null) {
        SignalDatabase.recipients.setExpireMessages(threadRecipientId, expiresIn.inWholeSeconds.toInt(), expireTimerVersion)
      } else {
        // TODO [expireVersion] After unsupported builds expire, we can remove this branch
        SignalDatabase.recipients.setExpireMessagesWithoutIncrementingVersion(threadRecipientId, expiresIn.inWholeSeconds.toInt())
      }

      if (insertResult != null) {
        return insertResult
      }
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    }

    return null
  }

  /**
   * Inserts an expiration update if the message timer doesn't match the thread timer.
   */
  @Throws(StorageFailedException::class)
  fun handlePossibleExpirationUpdate(
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    senderRecipient: Recipient,
    threadRecipient: Recipient,
    groupId: GroupId.V2?,
    expiresIn: Duration,
    expireTimerVersion: Int?,
    receivedTime: Long
  ) {
    if (threadRecipient.expiresInSeconds.toLong() != expiresIn.inWholeSeconds || ((expireTimerVersion ?: -1) > threadRecipient.expireTimerVersion)) {
      warn(envelope.timestamp!!, "Message expire time didn't match thread expire time. Handling timer update.")
      handleExpirationUpdate(envelope, metadata, senderRecipient, threadRecipient.id, groupId, expiresIn, expireTimerVersion, receivedTime, true)
    }
  }

  @Throws(StorageFailedException::class)
  private fun handleStoryReaction(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipientId: RecipientId,
    groupId: GroupId.V2?
  ): InsertResult? {
    log(envelope.timestamp!!, "Story reaction.")

    val storyContext = message.storyContext!!
    val emoji = message.reaction!!.emoji

    if (!EmojiUtil.isEmoji(emoji)) {
      warn(envelope.timestamp!!, "Story reaction text is not a valid emoji! Ignoring the message.")
      return null
    }

    val authorServiceId: ServiceId = ServiceId.parseOrThrow(storyContext.authorAci!!)
    val sentTimestamp = storyContext.sentTimestamp!!

    SignalDatabase.messages.beginTransaction()
    return try {
      val authorRecipientId = RecipientId.from(authorServiceId)
      val parentStoryId: ParentStoryId
      var quoteModel: QuoteModel? = null
      var expiresIn: Duration = 0L.seconds

      try {
        val storyId = SignalDatabase.messages.getStoryId(authorRecipientId, sentTimestamp).id

        if (groupId != null) {
          parentStoryId = GroupReply(storyId)
        } else if (SignalDatabase.storySends.canReply(senderRecipientId, sentTimestamp)) {
          val story = SignalDatabase.messages.getMessageRecord(storyId) as MmsMessageRecord
          var displayText = ""
          var bodyRanges: BodyRangeList? = null

          if (story.storyType.isTextStory) {
            displayText = story.body
            bodyRanges = story.messageRanges
          }

          parentStoryId = DirectReply(storyId)
          quoteModel = QuoteModel(sentTimestamp, authorRecipientId, displayText, false, story.slideDeck.asAttachments(), emptyList(), QuoteModel.Type.NORMAL, bodyRanges)
          expiresIn = message.expireTimerDuration
        } else {
          warn(envelope.timestamp!!, "Story has reactions disabled. Dropping reaction.")
          return null
        }
      } catch (e: NoSuchMessageException) {
        warn(envelope.timestamp!!, "Couldn't find story for reaction.", e)
        return null
      }

      val mediaMessage = IncomingMessage(
        type = MessageType.STORY_REACTION,
        from = senderRecipientId,
        sentTimeMillis = envelope.timestamp!!,
        serverTimeMillis = envelope.serverTimestamp!!,
        receivedTimeMillis = System.currentTimeMillis(),
        parentStoryId = parentStoryId,
        isStoryReaction = true,
        expiresIn = expiresIn.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        body = emoji,
        groupId = groupId,
        quote = quoteModel,
        serverGuid = envelope.serverGuid
      )

      val insertResult: InsertResult? = SignalDatabase.messages.insertMessageInbox(mediaMessage, -1).orNull()
      if (insertResult != null) {
        SignalDatabase.messages.setTransactionSuccessful()

        if (parentStoryId.isGroupReply()) {
          AppDependencies.messageNotifier.updateNotification(context, ConversationId.fromThreadAndReply(insertResult.threadId, parentStoryId as GroupReply))
        } else {
          AppDependencies.messageNotifier.updateNotification(context, ConversationId.forConversation(insertResult.threadId))
          TrimThreadJob.enqueueAsync(insertResult.threadId)
        }

        if (parentStoryId.isDirectReply()) {
          insertResult
        } else {
          null
        }
      } else {
        warn(envelope.timestamp!!, "Failed to insert story reaction")
        null
      }
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    } finally {
      SignalDatabase.messages.endTransaction()
    }
  }

  @Throws(StorageFailedException::class)
  fun handleReaction(
    context: Context,
    envelope: Envelope,
    message: DataMessage,
    senderRecipientId: RecipientId,
    earlyMessageCacheEntry: EarlyMessageCacheEntry?
  ): MessageId? {
    val reaction: DataMessage.Reaction = message.reaction!!

    log(envelope.timestamp!!, "Handle reaction for message " + reaction.targetSentTimestamp!!)

    val emoji: String? = reaction.emoji
    val isRemove: Boolean = reaction.remove ?: false
    val targetAuthorServiceId: ServiceId = ServiceId.parseOrThrow(reaction.targetAuthorAci!!)
    val targetSentTimestamp: Long = reaction.targetSentTimestamp!!

    if (targetAuthorServiceId.isUnknown) {
      warn(envelope.timestamp!!, "Reaction was to an unknown UUID! Ignoring the message.")
      return null
    }

    if (!EmojiUtil.isEmoji(emoji)) {
      warn(envelope.timestamp!!, "Reaction text is not a valid emoji! Ignoring the message.")
      return null
    }

    val targetAuthor = Recipient.externalPush(targetAuthorServiceId)
    val targetMessage = SignalDatabase.messages.getMessageFor(targetSentTimestamp, targetAuthor.id)
    if (targetMessage == null) {
      warn(envelope.timestamp!!, "[handleReaction] Could not find matching message! Putting it in the early message cache. timestamp: " + targetSentTimestamp + "  author: " + targetAuthor.id)
      if (earlyMessageCacheEntry != null) {
        AppDependencies.earlyMessageCache.store(targetAuthor.id, targetSentTimestamp, earlyMessageCacheEntry)
        PushProcessEarlyMessagesJob.enqueue()
      }
      return null
    }

    if (targetMessage.isRemoteDelete) {
      warn(envelope.timestamp!!, "[handleReaction] Found a matching message, but it's flagged as remotely deleted. timestamp: " + targetSentTimestamp + "  author: " + targetAuthor.id)
      return null
    }

    val targetThread = SignalDatabase.threads.getThreadRecord(targetMessage.threadId)
    if (targetThread == null) {
      warn(envelope.timestamp!!, "[handleReaction] Could not find a thread for the message! timestamp: " + targetSentTimestamp + "  author: " + targetAuthor.id)
      return null
    }

    val targetThreadRecipientId = targetThread.recipient.id
    val groupRecord = SignalDatabase.groups.getGroup(targetThreadRecipientId).orNull()
    if (groupRecord != null && !groupRecord.members.contains(senderRecipientId)) {
      warn(envelope.timestamp!!, "[handleReaction] Reaction author is not in the group! timestamp: " + targetSentTimestamp + "  author: " + targetAuthor.id)
      return null
    }

    if (groupRecord == null && senderRecipientId != targetThreadRecipientId && Recipient.self().id != senderRecipientId) {
      warn(envelope.timestamp!!, "[handleReaction] Reaction author is not a part of the 1:1 thread! timestamp: " + targetSentTimestamp + "  author: " + targetAuthor.id)
      return null
    }

    val targetMessageId = (targetMessage as? MmsMessageRecord)?.latestRevisionId ?: MessageId(targetMessage.id)

    if (isRemove) {
      SignalDatabase.reactions.deleteReaction(targetMessageId, senderRecipientId)
      AppDependencies.messageNotifier.updateNotification(context)
    } else {
      val reactionRecord = ReactionRecord(emoji!!, senderRecipientId, message.timestamp!!, System.currentTimeMillis())
      SignalDatabase.reactions.addReaction(targetMessageId, reactionRecord)
      AppDependencies.messageNotifier.updateNotification(context, ConversationId.fromMessageRecord(targetMessage))
    }

    return targetMessageId
  }

  fun handleRemoteDelete(context: Context, envelope: Envelope, message: DataMessage, senderRecipientId: RecipientId, earlyMessageCacheEntry: EarlyMessageCacheEntry?): MessageId? {
    val delete = message.delete!!

    log(envelope.timestamp!!, "Remote delete for message ${delete.targetSentTimestamp}")

    val targetSentTimestamp: Long = delete.targetSentTimestamp!!
    val targetMessage: MessageRecord? = SignalDatabase.messages.getMessageFor(targetSentTimestamp, senderRecipientId)

    return if (targetMessage != null && MessageConstraintsUtil.isValidRemoteDeleteReceive(targetMessage, senderRecipientId, envelope.serverTimestamp!!)) {
      SignalDatabase.messages.markAsRemoteDelete(targetMessage)
      if (targetMessage.isStory()) {
        SignalDatabase.messages.deleteRemotelyDeletedStory(targetMessage.id)
      }

      AppDependencies.messageNotifier.updateNotification(context, ConversationId.fromMessageRecord(targetMessage))

      MessageId(targetMessage.id)
    } else if (targetMessage == null) {
      warn(envelope.timestamp!!, "[handleRemoteDelete] Could not find matching message! timestamp: $targetSentTimestamp  author: $senderRecipientId")
      if (earlyMessageCacheEntry != null) {
        AppDependencies.earlyMessageCache.store(senderRecipientId, targetSentTimestamp, earlyMessageCacheEntry)
        PushProcessEarlyMessagesJob.enqueue()
      }

      null
    } else {
      warn(envelope.timestamp!!, "[handleRemoteDelete] Invalid remote delete! deleteTime: ${envelope.serverTimestamp!!}, targetTime: ${targetMessage.serverTimestamp}, deleteAuthor: $senderRecipientId, targetAuthor: ${targetMessage.fromRecipient.id}")
      null
    }
  }

  /**
   * @param isActivatePaymentsRequest True if payments activation request message.
   * @param isPaymentsActivated       True if payments activated message.
   * @throws StorageFailedException
   */
  @Throws(StorageFailedException::class)
  private fun handlePaymentActivation(
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipientId: RecipientId,
    receivedTime: Long,
    isActivatePaymentsRequest: Boolean,
    isPaymentsActivated: Boolean
  ): InsertResult? {
    log(envelope.timestamp!!, "Payment activation request: $isActivatePaymentsRequest activated: $isPaymentsActivated")
    Preconditions.checkArgument(isActivatePaymentsRequest || isPaymentsActivated)

    try {
      val mediaMessage = IncomingMessage(
        from = senderRecipientId,
        sentTimeMillis = envelope.timestamp!!,
        serverTimeMillis = envelope.serverTimestamp!!,
        receivedTimeMillis = receivedTime,
        expiresIn = message.expireTimerDuration.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        serverGuid = envelope.serverGuid,
        type = if (isActivatePaymentsRequest) MessageType.ACTIVATE_PAYMENTS_REQUEST else MessageType.PAYMENTS_ACTIVATED
      )

      return SignalDatabase.messages.insertMessageInbox(mediaMessage, -1).orNull()
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    }
    return null
  }

  @Throws(StorageFailedException::class)
  private fun handlePayment(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipientId: RecipientId,
    receivedTime: Long
  ): InsertResult? {
    log(envelope.timestamp!!, "Payment message.")

    if (message.payment?.notification?.mobileCoin?.receipt == null) {
      warn(envelope.timestamp!!, "Ignoring payment message without notification")
      return null
    }

    val paymentNotification = message.payment!!.notification!!
    val uuid = UUID.randomUUID()
    val queue = "Payment_" + PushProcessMessageJob.getQueueName(senderRecipientId)

    try {
      SignalDatabase.payments.createIncomingPayment(
        uuid,
        senderRecipientId,
        message.timestamp!!,
        paymentNotification.note ?: "",
        Money.MobileCoin.ZERO,
        Money.MobileCoin.ZERO,
        paymentNotification.mobileCoin!!.receipt!!.toByteArray(),
        true
      )

      val mediaMessage = IncomingMessage(
        from = senderRecipientId,
        body = uuid.toString(),
        sentTimeMillis = envelope.timestamp!!,
        serverTimeMillis = envelope.serverTimestamp!!,
        receivedTimeMillis = receivedTime,
        expiresIn = message.expireTimerDuration.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        serverGuid = envelope.serverGuid,
        type = MessageType.PAYMENTS_NOTIFICATION
      )

      val insertResult: InsertResult? = SignalDatabase.messages.insertMessageInbox(mediaMessage, -1).orNull()
      if (insertResult != null) {
        AppDependencies.messageNotifier.updateNotification(context, ConversationId.forConversation(insertResult.threadId))
        return insertResult
      }
    } catch (e: PublicKeyConflictException) {
      warn(envelope.timestamp!!, "Ignoring payment with public key already in database")
    } catch (e: SerializationException) {
      warn(envelope.timestamp!!, "Ignoring payment with bad data.", e)
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    } finally {
      SignalDatabase.runPostSuccessfulTransaction {
        AppDependencies.jobManager
          .startChain(PaymentTransactionCheckJob(uuid, queue))
          .then(PaymentLedgerUpdateJob.updateLedger())
          .enqueue()
      }
    }

    return null
  }

  @Throws(StorageFailedException::class)
  private fun handleStoryReply(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipient: Recipient,
    groupId: GroupId.V2?,
    receivedTime: Long
  ): InsertResult? {
    log(envelope.timestamp!!, "Story reply.")

    val storyContext: DataMessage.StoryContext = message.storyContext!!
    val authorServiceId: ServiceId = ServiceId.parseOrThrow(storyContext.authorAci!!)
    val sentTimestamp: Long = if (storyContext.sentTimestamp != null) {
      storyContext.sentTimestamp!!
    } else {
      warn(envelope.timestamp!!, "Invalid story reply, missing sentTimestamp")
      return null
    }

    SignalDatabase.messages.beginTransaction()
    return try {
      val storyAuthorRecipientId = RecipientId.from(authorServiceId)
      val selfId = Recipient.self().id
      val parentStoryId: ParentStoryId
      var quoteModel: QuoteModel? = null
      var expiresInMillis: Duration = 0L.seconds
      var storyMessageId: MessageId? = null

      try {
        if (selfId == storyAuthorRecipientId) {
          storyMessageId = SignalDatabase.storySends.getStoryMessageFor(senderRecipient.id, sentTimestamp)
        }

        if (storyMessageId == null) {
          storyMessageId = SignalDatabase.messages.getStoryId(storyAuthorRecipientId, sentTimestamp)
        }

        val story: MmsMessageRecord = SignalDatabase.messages.getMessageRecord(storyMessageId.id) as MmsMessageRecord
        var threadRecipient: Recipient = SignalDatabase.threads.getRecipientForThreadId(story.threadId)!!
        val groupRecord: GroupRecord? = SignalDatabase.groups.getGroup(threadRecipient.id).orNull()
        val groupStory: Boolean = groupRecord?.isActive ?: false

        if (!groupStory) {
          threadRecipient = senderRecipient
        }

        handlePossibleExpirationUpdate(envelope, metadata, senderRecipient, threadRecipient, groupId, message.expireTimerDuration, message.expireTimerVersion, receivedTime)

        if (message.hasGroupContext) {
          parentStoryId = GroupReply(storyMessageId.id)
        } else if (groupStory || SignalDatabase.storySends.canReply(senderRecipient.id, sentTimestamp)) {
          parentStoryId = DirectReply(storyMessageId.id)

          var displayText = ""
          var bodyRanges: BodyRangeList? = null
          if (story.storyType.isTextStory) {
            displayText = story.body
            bodyRanges = story.messageRanges
          }

          quoteModel = QuoteModel(sentTimestamp, storyAuthorRecipientId, displayText, false, story.slideDeck.asAttachments(), emptyList(), QuoteModel.Type.NORMAL, bodyRanges)
          expiresInMillis = message.expireTimerDuration
        } else {
          warn(envelope.timestamp!!, "Story has replies disabled. Dropping reply.")
          return null
        }
      } catch (e: NoSuchMessageException) {
        warn(envelope.timestamp!!, "Couldn't find story for reply.", e)
        return null
      }

      val bodyRanges: BodyRangeList? = message.bodyRanges.filter { it.mentionAci == null }.toList().toBodyRangeList()

      val mediaMessage = IncomingMessage(
        type = MessageType.NORMAL,
        from = senderRecipient.id,
        sentTimeMillis = envelope.timestamp!!,
        serverTimeMillis = envelope.serverTimestamp!!,
        receivedTimeMillis = System.currentTimeMillis(),
        parentStoryId = parentStoryId,
        expiresIn = expiresInMillis.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        body = message.body,
        groupId = groupId,
        quote = quoteModel,
        mentions = getMentions(message.bodyRanges),
        serverGuid = envelope.serverGuid,
        messageRanges = bodyRanges
      )

      val insertResult: InsertResult? = SignalDatabase.messages.insertMessageInbox(mediaMessage, -1).orNull()

      if (insertResult != null) {
        SignalDatabase.messages.setTransactionSuccessful()

        if (parentStoryId.isGroupReply()) {
          AppDependencies.messageNotifier.updateNotification(context, ConversationId.fromThreadAndReply(insertResult.threadId, parentStoryId as GroupReply))
        } else {
          AppDependencies.messageNotifier.updateNotification(context, ConversationId.forConversation(insertResult.threadId))
          TrimThreadJob.enqueueAsync(insertResult.threadId)
        }

        if (parentStoryId.isDirectReply()) {
          insertResult
        } else {
          null
        }
      } else {
        warn(envelope.timestamp!!, "Failed to insert story reply.")
        null
      }
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    } finally {
      SignalDatabase.messages.endTransaction()
    }
  }

  @Throws(StorageFailedException::class)
  private fun handleGiftMessage(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipient: Recipient,
    threadRecipientId: RecipientId,
    receivedTime: Long
  ): InsertResult? {
    log(message.timestamp!!, "Gift message.")

    val giftBadge: DataMessage.GiftBadge = message.giftBadge!!
    check(giftBadge.receiptCredentialPresentation != null)

    notifyTypingStoppedFromIncomingMessage(context, senderRecipient, threadRecipientId, metadata.sourceDeviceId)

    val token = ReceiptCredentialPresentation(giftBadge.receiptCredentialPresentation!!.toByteArray()).serialize()
    val dbGiftBadge = GiftBadge.Builder()
      .redemptionToken(token.toByteString())
      .redemptionState(GiftBadge.RedemptionState.PENDING)
      .build()

    val insertResult: InsertResult? = try {
      val mediaMessage = IncomingMessage(
        type = MessageType.NORMAL,
        from = senderRecipient.id,
        sentTimeMillis = envelope.timestamp!!,
        serverTimeMillis = envelope.serverTimestamp!!,
        receivedTimeMillis = receivedTime,
        expiresIn = message.expireTimerDuration.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        body = Base64.encodeWithPadding(dbGiftBadge.encode()),
        serverGuid = envelope.serverGuid,
        giftBadge = dbGiftBadge
      )

      SignalDatabase.messages.insertMessageInbox(mediaMessage, -1).orNull()
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    }

    return if (insertResult != null) {
      AppDependencies.messageNotifier.updateNotification(context, ConversationId.forConversation(insertResult.threadId))
      TrimThreadJob.enqueueAsync(insertResult.threadId)
      insertResult
    } else {
      null
    }
  }

  @Throws(StorageFailedException::class)
  private fun handleMediaMessage(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipient: Recipient,
    threadRecipient: Recipient,
    groupId: GroupId.V2?,
    receivedTime: Long,
    localMetrics: SignalLocalMetrics.MessageReceive?
  ): InsertResult? {
    log(envelope.timestamp!!, "Media message.")

    notifyTypingStoppedFromIncomingMessage(context, senderRecipient, threadRecipient.id, metadata.sourceDeviceId)

    val insertResult: InsertResult?

    SignalDatabase.messages.beginTransaction()
    try {
      val quote: QuoteModel? = getValidatedQuote(context, envelope.timestamp!!, message)
      val contacts: List<Contact> = getContacts(message)
      val linkPreviews: List<LinkPreview> = getLinkPreviews(message.preview, message.body ?: "", false)
      val mentions: List<Mention> = getMentions(message.bodyRanges.take(BODY_RANGE_PROCESSING_LIMIT))
      val sticker: Attachment? = getStickerAttachment(envelope.timestamp!!, message)
      val attachments: List<Attachment> = message.attachments.toPointersWithinLimit()
      val messageRanges: BodyRangeList? = if (message.bodyRanges.isNotEmpty()) message.bodyRanges.asSequence().take(BODY_RANGE_PROCESSING_LIMIT).filter { it.mentionAci == null }.toList().toBodyRangeList() else null

      handlePossibleExpirationUpdate(envelope, metadata, senderRecipient, threadRecipient, groupId, message.expireTimerDuration, message.expireTimerVersion, receivedTime)

      val mediaMessage = IncomingMessage(
        type = MessageType.NORMAL,
        from = senderRecipient.id,
        sentTimeMillis = envelope.timestamp!!,
        serverTimeMillis = envelope.serverTimestamp!!,
        receivedTimeMillis = receivedTime,
        expiresIn = message.expireTimerDuration.inWholeMilliseconds,
        isViewOnce = message.isViewOnce == true,
        isUnidentified = metadata.sealedSender,
        body = message.body?.ifEmpty { null },
        groupId = groupId,
        attachments = attachments + if (sticker != null) listOf(sticker) else emptyList(),
        quote = quote,
        sharedContacts = contacts,
        linkPreviews = linkPreviews,
        mentions = mentions,
        serverGuid = envelope.serverGuid,
        messageRanges = messageRanges
      )

      insertResult = SignalDatabase.messages.insertMessageInbox(mediaMessage, -1).orNull()
      if (insertResult != null) {
        SignalDatabase.messages.setTransactionSuccessful()
      }
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    } finally {
      SignalDatabase.messages.endTransaction()
    }
    localMetrics?.onInsertedMediaMessage()

    return if (insertResult != null) {
      SignalDatabase.runPostSuccessfulTransaction {
        if (insertResult.insertedAttachments != null) {
          val downloadJobs: List<AttachmentDownloadJob> = insertResult.insertedAttachments.mapNotNull { (attachment, attachmentId) ->
            if (attachment.isSticker) {
              if (attachment.transferState != AttachmentTable.TRANSFER_PROGRESS_DONE) {
                AttachmentDownloadJob(insertResult.messageId, attachmentId, true)
              } else {
                null
              }
            } else {
              AttachmentDownloadJob(insertResult.messageId, attachmentId, false)
            }
          }
          AppDependencies.jobManager.addAll(downloadJobs)
        }

        AppDependencies.messageNotifier.updateNotification(context, ConversationId.forConversation(insertResult.threadId))
        TrimThreadJob.enqueueAsync(insertResult.threadId)

        if (message.isViewOnce == true) {
          AppDependencies.viewOnceMessageManager.scheduleIfNecessary()
        }
      }

      insertResult
    } else {
      null
    }
  }

  @Throws(StorageFailedException::class)
  private fun handleTextMessage(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipient: Recipient,
    threadRecipient: Recipient,
    groupId: GroupId.V2?,
    receivedTime: Long,
    localMetrics: SignalLocalMetrics.MessageReceive?
  ): InsertResult? {
    log(envelope.timestamp!!, "Text message.")

    val body = message.body ?: ""

    handlePossibleExpirationUpdate(envelope, metadata, senderRecipient, threadRecipient, groupId, message.expireTimerDuration, message.expireTimerVersion, receivedTime)

    notifyTypingStoppedFromIncomingMessage(context, senderRecipient, threadRecipient.id, metadata.sourceDeviceId)

    val textMessage = IncomingMessage(
      type = MessageType.NORMAL,
      from = senderRecipient.id,
      sentTimeMillis = envelope.timestamp!!,
      serverTimeMillis = envelope.serverTimestamp!!,
      receivedTimeMillis = receivedTime,
      body = body,
      groupId = groupId,
      expiresIn = message.expireTimerDuration.inWholeMilliseconds,
      isUnidentified = metadata.sealedSender,
      serverGuid = envelope.serverGuid
    )

    val insertResult: InsertResult? = SignalDatabase.messages.insertMessageInbox(textMessage).orNull()
    localMetrics?.onInsertedTextMessage()

    return if (insertResult != null) {
      AppDependencies.messageNotifier.updateNotification(context, ConversationId.forConversation(insertResult.threadId))
      insertResult
    } else {
      null
    }
  }

  fun handleGroupCallUpdateMessage(
    envelope: Envelope,
    message: DataMessage,
    senderRecipientId: RecipientId,
    groupId: GroupId.V2?
  ) {
    log(envelope.timestamp!!, "Group call update message.")

    val groupCallUpdate: DataMessage.GroupCallUpdate = message.groupCallUpdate!!

    if (groupId == null) {
      warn(envelope.timestamp!!, "Invalid group for group call update message")
      return
    }

    val groupRecipientId = SignalDatabase.recipients.getOrInsertFromPossiblyMigratedGroupId(groupId)

    GroupCallPeekJob.enqueue(
      GroupCallPeekJobData(
        groupRecipientId.toLong(),
        senderRecipientId.toLong(),
        envelope.serverTimestamp!!
      )
    )
  }

  fun notifyTypingStoppedFromIncomingMessage(context: Context, senderRecipient: Recipient, threadRecipientId: RecipientId, device: Int) {
    val threadId = SignalDatabase.threads.getThreadIdIfExistsFor(threadRecipientId)

    if (threadId > 0 && TextSecurePreferences.isTypingIndicatorsEnabled(context)) {
      debug("Typing stopped on thread $threadId due to an incoming message.")
      AppDependencies.typingStatusRepository.onTypingStopped(threadId, senderRecipient, device, true)
    }
  }

  fun getMentions(mentionBodyRanges: List<BodyRange>): List<Mention> {
    return mentionBodyRanges
      .filter { it.mentionAci != null && it.start != null && it.length != null }
      .mapNotNull {
        val aci = ACI.parseOrNull(it.mentionAci)

        if (aci != null && !aci.isUnknown) {
          val id = Recipient.externalPush(aci).id
          Mention(id, it.start!!, it.length!!)
        } else {
          null
        }
      }
  }

  private fun insertPlaceholder(sender: RecipientId, timestamp: Long, groupId: GroupId?): InsertResult? {
    val textMessage = IncomingMessage(
      type = MessageType.NORMAL,
      from = sender,
      sentTimeMillis = timestamp,
      serverTimeMillis = -1,
      receivedTimeMillis = System.currentTimeMillis(),
      body = "",
      groupId = groupId
    )

    return SignalDatabase.messages.insertMessageInbox(textMessage).orNull()
  }

  fun getValidatedQuote(context: Context, timestamp: Long, message: DataMessage): QuoteModel? {
    val quote: DataMessage.Quote = message.quote ?: return null

    if (quote.id == null) {
      warn(timestamp, "Received quote without an ID! Ignoring...")
      return null
    }

    val authorId = Recipient.externalPush(ServiceId.parseOrThrow(quote.authorAci!!)).id
    var quotedMessage = SignalDatabase.messages.getMessageFor(quote.id!!, authorId) as? MmsMessageRecord

    if (quotedMessage != null && !quotedMessage.isRemoteDelete) {
      log(timestamp, "Found matching message record...")

      val attachments: MutableList<Attachment> = mutableListOf()
      val mentions: MutableList<Mention> = mutableListOf()

      quotedMessage = quotedMessage.withAttachments(SignalDatabase.attachments.getAttachmentsForMessage(quotedMessage.id))

      mentions.addAll(SignalDatabase.mentions.getMentionsForMessage(quotedMessage.id))

      if (quotedMessage.isViewOnce) {
        attachments.add(TombstoneAttachment(MediaUtil.VIEW_ONCE, true))
      } else {
        attachments += quotedMessage.slideDeck.asAttachments()

        if (attachments.isEmpty()) {
          attachments += quotedMessage
            .linkPreviews
            .filter { it.thumbnail.isPresent }
            .map { it.thumbnail.get() }
        }
      }

      if (quotedMessage.isPaymentNotification) {
        quotedMessage = SignalDatabase.payments.updateMessageWithPayment(quotedMessage) as MmsMessageRecord
      }

      val body = if (quotedMessage.isPaymentNotification) quotedMessage.getDisplayBody(context).toString() else quotedMessage.body

      return QuoteModel(
        quote.id!!,
        authorId,
        body,
        false,
        attachments,
        mentions,
        QuoteModel.Type.fromProto(quote.type),
        quotedMessage.messageRanges
      )
    } else if (quotedMessage != null) {
      warn(timestamp, "Found the target for the quote, but it's flagged as remotely deleted.")
    }

    warn(timestamp, "Didn't find matching message record...")
    return QuoteModel(
      quote.id!!,
      authorId,
      quote.text ?: "",
      true,
      quote.attachments.mapNotNull { PointerAttachment.forPointer(it).orNull() },
      getMentions(quote.bodyRanges),
      QuoteModel.Type.fromProto(quote.type),
      quote.bodyRanges.filter { it.mentionAci == null }.toBodyRangeList()
    )
  }

  fun getContacts(message: DataMessage): List<Contact> {
    return message.contact.map { ContactModelMapper.remoteToLocal(it) }
  }

  fun getLinkPreviews(previews: List<Preview>, body: String, isStoryEmbed: Boolean): List<LinkPreview> {
    if (previews.isEmpty()) {
      return emptyList()
    }

    val urlsInMessage = LinkPreviewUtil.findValidPreviewUrls(body)

    return previews
      .mapNotNull { preview ->
        val thumbnail: Attachment? = preview.image?.toPointer()
        val url: Optional<String> = preview.url.toOptional()
        val title: Optional<String> = preview.title.toOptional()
        val description: Optional<String> = preview.description.toOptional()
        val hasTitle = !TextUtils.isEmpty(title.orElse(""))
        val presentInBody = url.isPresent && urlsInMessage.containsUrl(url.get())
        val validDomain = url.isPresent && LinkUtil.isValidPreviewUrl(url.get())
        val isForCallLink = url.isPresent && CallLinks.isCallLink(url.get())

        if ((hasTitle || isForCallLink) && (presentInBody || isStoryEmbed) && validDomain) {
          val linkPreview = LinkPreview(url.get(), title.orElse(""), description.orElse(""), preview.date ?: 0, thumbnail.toOptional())
          linkPreview
        } else {
          warn(String.format("Discarding an invalid link preview. hasTitle: %b presentInBody: %b isStoryEmbed: %b validDomain: %b", hasTitle, presentInBody, isStoryEmbed, validDomain))
          null
        }
      }
  }

  fun getStickerAttachment(timestamp: Long, message: DataMessage): Attachment? {
    val sticker = message.sticker ?: return null

    if (sticker.packId == null || sticker.packKey == null || sticker.stickerId == null || sticker.data_ == null) {
      warn(timestamp, "Malformed sticker!")
      return null
    }

    val packId: String = Hex.toStringCondensed(sticker.packId!!.toByteArray())
    val packKey: String = Hex.toStringCondensed(sticker.packKey!!.toByteArray())
    val stickerId: Int = sticker.stickerId!!
    val emoji: String? = sticker.emoji

    val stickerLocator = StickerLocator(packId, packKey, stickerId, emoji)
    val stickerRecord: StickerRecord? = SignalDatabase.stickers.getSticker(stickerLocator.packId, stickerLocator.stickerId, false)

    return if (stickerRecord != null) {
      UriAttachment(
        stickerRecord.uri,
        stickerRecord.contentType,
        AttachmentTable.TRANSFER_PROGRESS_DONE,
        stickerRecord.size,
        StickerSlide.WIDTH,
        StickerSlide.HEIGHT,
        null,
        SecureRandom().nextLong().toString(),
        false,
        false,
        false,
        false,
        null,
        stickerLocator,
        null,
        null,
        null
      )
    } else {
      sticker.data_!!.toPointer(stickerLocator)
    }
  }
}
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
package org.thoughtcrime.securesms.messages

import android.content.Context
import android.text.TextUtils
import com.google.protobuf.ByteString
import com.mobilecoin.lib.exceptions.SerializationException
import org.signal.core.util.Hex
import org.signal.core.util.concurrent.SignalExecutors
import org.signal.core.util.logging.Log
import org.signal.core.util.orNull
import org.signal.core.util.toOptional
import org.signal.libsignal.zkgroup.receipts.ReceiptCredentialPresentation
import org.thoughtcrime.securesms.attachments.Attachment
import org.thoughtcrime.securesms.attachments.DatabaseAttachment
import org.thoughtcrime.securesms.attachments.PointerAttachment
import org.thoughtcrime.securesms.attachments.TombstoneAttachment
import org.thoughtcrime.securesms.attachments.UriAttachment
import org.thoughtcrime.securesms.components.emoji.EmojiUtil
import org.thoughtcrime.securesms.contactshare.Contact
import org.thoughtcrime.securesms.contactshare.ContactModelMapper
import org.thoughtcrime.securesms.crypto.ProfileKeyUtil
import org.thoughtcrime.securesms.crypto.SecurityEvent
import org.thoughtcrime.securesms.database.AttachmentTable
import org.thoughtcrime.securesms.database.MessageTable.InsertResult
import org.thoughtcrime.securesms.database.NoSuchMessageException
import org.thoughtcrime.securesms.database.PaymentTable.PublicKeyConflictException
import org.thoughtcrime.securesms.database.SignalDatabase
import org.thoughtcrime.securesms.database.SignalDatabase.Companion.reactions // JW
import org.thoughtcrime.securesms.database.model.GroupRecord
import org.thoughtcrime.securesms.database.model.MediaMmsMessageRecord
import org.thoughtcrime.securesms.database.model.Mention
import org.thoughtcrime.securesms.database.model.MessageId
import org.thoughtcrime.securesms.database.model.MessageRecord
import org.thoughtcrime.securesms.database.model.MmsMessageRecord
import org.thoughtcrime.securesms.database.model.ParentStoryId
import org.thoughtcrime.securesms.database.model.ParentStoryId.DirectReply
import org.thoughtcrime.securesms.database.model.ParentStoryId.GroupReply
import org.thoughtcrime.securesms.database.model.ReactionRecord
import org.thoughtcrime.securesms.database.model.databaseprotos.BodyRangeList
import org.thoughtcrime.securesms.database.model.databaseprotos.GiftBadge
import org.thoughtcrime.securesms.database.model.toBodyRangeList
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies
import org.thoughtcrime.securesms.groups.BadGroupIdException
import org.thoughtcrime.securesms.groups.GroupId
import org.thoughtcrime.securesms.jobs.AttachmentDownloadJob
import org.thoughtcrime.securesms.jobs.GroupCallPeekJob
import org.thoughtcrime.securesms.jobs.GroupV2UpdateSelfProfileKeyJob
import org.thoughtcrime.securesms.jobs.PaymentLedgerUpdateJob
import org.thoughtcrime.securesms.jobs.PaymentTransactionCheckJob
import org.thoughtcrime.securesms.jobs.ProfileKeySendJob
import org.thoughtcrime.securesms.jobs.PushProcessEarlyMessagesJob
import org.thoughtcrime.securesms.jobs.PushProcessMessageJob
import org.thoughtcrime.securesms.jobs.RefreshAttributesJob
import org.thoughtcrime.securesms.jobs.RetrieveProfileJob
import org.thoughtcrime.securesms.jobs.SendDeliveryReceiptJob
import org.thoughtcrime.securesms.jobs.TrimThreadJob
import org.thoughtcrime.securesms.linkpreview.LinkPreview
import org.thoughtcrime.securesms.linkpreview.LinkPreviewUtil
import org.thoughtcrime.securesms.messages.MessageContentProcessor.StorageFailedException
import org.thoughtcrime.securesms.messages.MessageContentProcessorV2.Companion.debug
import org.thoughtcrime.securesms.messages.MessageContentProcessorV2.Companion.log
import org.thoughtcrime.securesms.messages.MessageContentProcessorV2.Companion.warn
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.groupMasterKey
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.hasGroupContext
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.hasRemoteDelete
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isEndSession
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isExpirationUpdate
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isInvalid
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isMediaMessage
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isPaymentActivated
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isPaymentActivationRequest
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isStoryReaction
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.toPointer
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.toPointers
import org.thoughtcrime.securesms.mms.IncomingMediaMessage
import org.thoughtcrime.securesms.mms.MmsException
import org.thoughtcrime.securesms.mms.QuoteModel
import org.thoughtcrime.securesms.mms.StickerSlide
import org.thoughtcrime.securesms.notifications.v2.ConversationId
import org.thoughtcrime.securesms.notifications.v2.ConversationId.Companion.fromMessageRecord // JW
import org.thoughtcrime.securesms.recipients.Recipient
import org.thoughtcrime.securesms.recipients.RecipientId
import org.thoughtcrime.securesms.recipients.RecipientUtil
import org.thoughtcrime.securesms.sms.IncomingEncryptedMessage
import org.thoughtcrime.securesms.sms.IncomingEndSessionMessage
import org.thoughtcrime.securesms.sms.IncomingTextMessage
import org.thoughtcrime.securesms.stickers.StickerLocator
import org.thoughtcrime.securesms.storage.StorageSyncHelper
import org.thoughtcrime.securesms.util.Base64
import org.thoughtcrime.securesms.util.EarlyMessageCacheEntry
import org.thoughtcrime.securesms.util.LinkUtil
import org.thoughtcrime.securesms.util.MediaUtil
import org.thoughtcrime.securesms.util.MessageConstraintsUtil
import org.thoughtcrime.securesms.util.TextSecurePreferences
import org.thoughtcrime.securesms.util.isStory
import org.whispersystems.signalservice.api.crypto.EnvelopeMetadata
import org.whispersystems.signalservice.api.payments.Money
import org.whispersystems.signalservice.api.push.ServiceId
import org.whispersystems.signalservice.api.util.OptionalUtil.asOptional
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.BodyRange
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.Content
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.DataMessage
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.Envelope
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.GroupContextV2
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.Preview
import java.security.SecureRandom
import java.util.Optional
import java.util.UUID
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

object DataMessageProcessor {

  fun process(
    context: Context,
    senderRecipient: Recipient,
    threadRecipient: Recipient,
    envelope: Envelope,
    content: Content,
    metadata: EnvelopeMetadata,
    receivedTime: Long,
    earlyMessageCacheEntry: EarlyMessageCacheEntry?
  ) {
    val message: DataMessage = content.dataMessage
    val groupId: GroupId.V2? = if (message.hasGroupContext) GroupId.v2(message.groupV2.groupMasterKey) else null

    if (groupId != null) {
      if (MessageContentProcessorV2.handleGv2PreProcessing(context, envelope.timestamp, content, metadata, groupId, message.groupV2, senderRecipient)) {
        return
      }
    }

    var messageId: MessageId? = null
    when {
      message.isInvalid -> handleInvalidMessage(context, senderRecipient.id, metadata.sourceDeviceId, groupId, envelope.timestamp)
      message.isEndSession -> messageId = handleEndSessionMessage(context, senderRecipient.id, envelope, metadata)
      message.isExpirationUpdate -> messageId = handleExpirationUpdate(envelope, metadata, senderRecipient.id, threadRecipient.id, groupId, message.expireTimer.seconds, receivedTime, false)
      message.isStoryReaction -> messageId = handleStoryReaction(context, envelope, metadata, message, senderRecipient.id, groupId)
      message.hasReaction() -> messageId = handleReaction(context, envelope, message, senderRecipient.id, earlyMessageCacheEntry)
      message.hasRemoteDelete -> messageId = handleRemoteDelete(context, envelope, message, senderRecipient.id, earlyMessageCacheEntry)
      message.isPaymentActivationRequest -> messageId = handlePaymentActivation(envelope, metadata, message, senderRecipient.id, receivedTime, isActivatePaymentsRequest = true, isPaymentsActivated = false)
      message.isPaymentActivated -> messageId = handlePaymentActivation(envelope, metadata, message, senderRecipient.id, receivedTime, isActivatePaymentsRequest = false, isPaymentsActivated = true)
      message.hasPayment() -> messageId = handlePayment(context, envelope, metadata, message, senderRecipient.id, receivedTime)
      message.hasStoryContext() -> messageId = handleStoryReply(context, envelope, metadata, message, senderRecipient.id, groupId, receivedTime)
      message.hasGiftBadge() -> messageId = handleGiftMessage(context, envelope, metadata, message, senderRecipient, threadRecipient.id, receivedTime)
      message.isMediaMessage -> messageId = handleMediaMessage(context, envelope, metadata, message, senderRecipient, threadRecipient.id, groupId, receivedTime)
      message.hasBody() -> messageId = handleTextMessage(context, envelope, metadata, message, senderRecipient, threadRecipient.id, groupId, receivedTime)
      message.hasGroupCallUpdate() -> handleGroupCallUpdateMessage(envelope, message, senderRecipient.id, groupId)
    }

    if (groupId != null && SignalDatabase.groups.isUnknownGroup(groupId)) {
      handleUnknownGroupMessage(envelope.timestamp, message.groupV2)
    }

    if (message.hasProfileKey()) {
      handleProfileKey(envelope.timestamp, message.profileKey.toByteArray(), senderRecipient)
    }

    if (metadata.sealedSender && messageId != null) {
      SignalExecutors.BOUNDED.execute { ApplicationDependencies.getJobManager().add(SendDeliveryReceiptJob(senderRecipient.id, message.timestamp, messageId)) }
    } else if (!metadata.sealedSender) {
      if (RecipientUtil.shouldHaveProfileKey(threadRecipient)) {
        Log.w(MessageContentProcessorV2.TAG, "Received an unsealed sender message from " + senderRecipient.id + ", but they should already have our profile key. Correcting.")

        if (groupId != null) {
          Log.i(MessageContentProcessorV2.TAG, "Message was to a GV2 group. Ensuring our group profile keys are up to date.")
          ApplicationDependencies
            .getJobManager()
            .startChain(RefreshAttributesJob(false))
            .then(GroupV2UpdateSelfProfileKeyJob.withQueueLimits(groupId))
            .enqueue()
        } else if (!threadRecipient.isGroup) {
          Log.i(MessageContentProcessorV2.TAG, "Message was to a 1:1. Ensuring this user has our profile key.")
          val profileSendJob = ProfileKeySendJob.create(SignalDatabase.threads.getOrCreateThreadIdFor(threadRecipient), true)
          if (profileSendJob != null) {
            ApplicationDependencies
              .getJobManager()
              .startChain(RefreshAttributesJob(false))
              .then(profileSendJob)
              .enqueue()
          }
        }
      }
    }
  }

  private fun handleProfileKey(
    timestamp: Long,
    messageProfileKeyBytes: ByteArray,
    senderRecipient: Recipient
  ) {
    val messageProfileKey = ProfileKeyUtil.profileKeyOrNull(messageProfileKeyBytes)

    if (senderRecipient.isSelf) {
      if (ProfileKeyUtil.getSelfProfileKey() != messageProfileKey) {
        warn(timestamp, "Saw a sync message whose profile key doesn't match our records. Scheduling a storage sync to check.")
        StorageSyncHelper.scheduleSyncForDataChange()
      }
    } else if (messageProfileKey != null) {
      if (SignalDatabase.recipients.setProfileKey(senderRecipient.id, messageProfileKey)) {
        log(timestamp, "Profile key on message from " + senderRecipient.id + " didn't match our local store. It has been updated.")
        ApplicationDependencies.getJobManager().add(RetrieveProfileJob.forRecipient(senderRecipient.id))
      }
    } else {
      warn(timestamp.toString(), "Ignored invalid profile key seen in message")
    }
  }

  @Throws(BadGroupIdException::class)
  fun handleUnknownGroupMessage(timestamp: Long, groupContextV2: GroupContextV2) {
    log(timestamp, "Unknown group message.")
    warn(timestamp, "Received a GV2 message for a group we have no knowledge of -- attempting to fix this state.")
    SignalDatabase.groups.fixMissingMasterKey(groupContextV2.groupMasterKey)
  }

  private fun handleInvalidMessage(
    context: Context,
    sender: RecipientId,
    senderDevice: Int,
    groupId: GroupId?,
    timestamp: Long
  ) {
    log(timestamp, "Invalid message.")

    val insertResult: InsertResult? = insertPlaceholder(sender, senderDevice, timestamp, groupId)
    if (insertResult != null) {
      SignalDatabase.messages.markAsInvalidMessage(insertResult.messageId)
      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
    }
  }

  private fun handleEndSessionMessage(
    context: Context,
    senderRecipientId: RecipientId,
    envelope: Envelope,
    metadata: EnvelopeMetadata
  ): MessageId? {
    log(envelope.timestamp, "End session message.")

    val incomingTextMessage = IncomingTextMessage(
      senderRecipientId,
      metadata.sourceDeviceId,
      envelope.timestamp,
      envelope.serverTimestamp,
      System.currentTimeMillis(),
      "",
      Optional.empty(),
      0,
      metadata.sealedSender,
      envelope.serverGuid
    )

    val insertResult: InsertResult? = SignalDatabase.messages.insertMessageInbox(IncomingEndSessionMessage(incomingTextMessage)).orNull()

    return if (insertResult != null) {
      ApplicationDependencies.getProtocolStore().aci().deleteAllSessions(metadata.sourceServiceId.toString())
      SecurityEvent.broadcastSecurityUpdateEvent(context)
      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
      MessageId(insertResult.messageId)
    } else {
      null
    }
  }

  /**
   * @param sideEffect True if the event is side effect of a different message, false if the message itself was an expiration update.
   * @throws StorageFailedException
   */
  @Throws(StorageFailedException::class)
  private fun handleExpirationUpdate(
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    senderRecipientId: RecipientId,
    threadRecipientId: RecipientId,
    groupId: GroupId.V2?,
    expiresIn: Duration,
    receivedTime: Long,
    sideEffect: Boolean
  ): MessageId? {
    log(envelope.timestamp, "Expiration update. Side effect: $sideEffect")

    if (groupId != null) {
      warn(envelope.timestamp, "Expiration update received for GV2. Ignoring.")
      return null
    }

    if (SignalDatabase.recipients.getExpiresInSeconds(threadRecipientId) == expiresIn.inWholeSeconds) {
      log(envelope.timestamp, "No change in message expiry for group. Ignoring.")
      return null
    }

    try {
      val mediaMessage = IncomingMediaMessage(
        from = senderRecipientId,
        sentTimeMillis = envelope.timestamp - if (sideEffect) 1 else 0,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = receivedTime,
        expiresIn = expiresIn.inWholeMilliseconds,
        isExpirationUpdate = true,
        isUnidentified = metadata.sealedSender,
        serverGuid = envelope.serverGuid,
        isPushMessage = true
      )

      val insertResult: InsertResult? = SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()
      SignalDatabase.recipients.setExpireMessages(threadRecipientId, expiresIn.inWholeSeconds.toInt())

      if (insertResult != null) {
        return MessageId(insertResult.messageId)
      }
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    }

    return null
  }

  /**
   * Inserts an expiration update if the message timer doesn't match the thread timer.
   */
  @Throws(StorageFailedException::class)
  fun handlePossibleExpirationUpdate(
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    senderRecipientId: RecipientId,
    threadRecipientId: RecipientId,
    groupId: GroupId.V2?,
    expiresIn: Duration,
    receivedTime: Long
  ) {
    if (SignalDatabase.recipients.getExpiresInSeconds(threadRecipientId) != expiresIn.inWholeSeconds) {
      warn(envelope.timestamp, "Message expire time didn't match thread expire time. Handling timer update.")
      handleExpirationUpdate(envelope, metadata, senderRecipientId, threadRecipientId, groupId, expiresIn, receivedTime, true)
    }
  }

  @Throws(StorageFailedException::class)
  private fun handleStoryReaction(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipientId: RecipientId,
    groupId: GroupId.V2?
  ): MessageId? {
    log(envelope.timestamp, "Story reaction.")

    val emoji = message.reaction.emoji
    if (!EmojiUtil.isEmoji(emoji)) {
      warn(envelope.timestamp, "Story reaction text is not a valid emoji! Ignoring the message.")
      return null
    }

    val authorServiceId: ServiceId = ServiceId.parseOrThrow(message.storyContext.authorUuid)
    val sentTimestamp = message.storyContext.sentTimestamp

    SignalDatabase.messages.beginTransaction()
    return try {
      val authorRecipientId = RecipientId.from(authorServiceId)
      val parentStoryId: ParentStoryId
      var quoteModel: QuoteModel? = null
      var expiresIn: Duration = 0L.seconds

      try {
        val storyId = SignalDatabase.messages.getStoryId(authorRecipientId, sentTimestamp).id

        if (groupId != null) {
          parentStoryId = GroupReply(storyId)
        } else if (SignalDatabase.storySends.canReply(senderRecipientId, sentTimestamp)) {
          val story = SignalDatabase.messages.getMessageRecord(storyId) as MmsMessageRecord
          var displayText = ""
          var bodyRanges: BodyRangeList? = null

          if (story.storyType.isTextStory) {
            displayText = story.body
            bodyRanges = story.messageRanges
          }

          parentStoryId = DirectReply(storyId)
          quoteModel = QuoteModel(sentTimestamp, authorRecipientId, displayText, false, story.slideDeck.asAttachments(), emptyList(), QuoteModel.Type.NORMAL, bodyRanges)
          expiresIn = message.expireTimer.seconds
        } else {
          warn(envelope.timestamp, "Story has reactions disabled. Dropping reaction.")
          return null
        }
      } catch (e: NoSuchMessageException) {
        warn(envelope.timestamp, "Couldn't find story for reaction.", e)
        return null
      }

      val mediaMessage = IncomingMediaMessage(
        from = senderRecipientId,
        sentTimeMillis = envelope.timestamp,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = System.currentTimeMillis(),
        parentStoryId = parentStoryId,
        isStoryReaction = true,
        expiresIn = expiresIn.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        body = emoji,
        groupId = groupId,
        quote = quoteModel,
        serverGuid = envelope.serverGuid
      )

      val insertResult: InsertResult? = SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()
      if (insertResult != null) {
        SignalDatabase.messages.setTransactionSuccessful()

        if (parentStoryId.isGroupReply()) {
          ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.fromThreadAndReply(insertResult.threadId, parentStoryId as GroupReply))
        } else {
          ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
          TrimThreadJob.enqueueAsync(insertResult.threadId)
        }

        if (parentStoryId.isDirectReply()) {
          MessageId(insertResult.messageId)
        } else {
          null
        }
      } else {
        warn(envelope.timestamp, "Failed to insert story reaction")
        null
      }
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    } finally {
      SignalDatabase.messages.endTransaction()
    }
  }

  @Throws(StorageFailedException::class)
  fun handleReaction(
    context: Context,
    envelope: Envelope,
    message: DataMessage,
    senderRecipientId: RecipientId,
    earlyMessageCacheEntry: EarlyMessageCacheEntry?
  ): MessageId? {
    log(envelope.timestamp, "Handle reaction for message " + message.reaction.targetSentTimestamp)

    val emoji: String = message.reaction.emoji
    val isRemove: Boolean = message.reaction.remove
    val targetAuthorServiceId: ServiceId = ServiceId.parseOrThrow(message.reaction.targetAuthorUuid)
    val targetSentTimestamp = message.reaction.targetSentTimestamp

    if (!EmojiUtil.isEmoji(emoji)) {
      warn(envelope.timestamp, "Reaction text is not a valid emoji! Ignoring the message.")
      return null
    }

    val targetAuthor = Recipient.externalPush(targetAuthorServiceId)
    val targetMessage = SignalDatabase.messages.getMessageFor(targetSentTimestamp, targetAuthor.id)
    if (targetMessage == null) {
      warn(envelope.timestamp, "[handleReaction] Could not find matching message! Putting it in the early message cache. timestamp: " + targetSentTimestamp + "  author: " + targetAuthor.id)
      if (earlyMessageCacheEntry != null) {
        ApplicationDependencies.getEarlyMessageCache().store(targetAuthor.id, targetSentTimestamp, earlyMessageCacheEntry)
        PushProcessEarlyMessagesJob.enqueue()
      }
      return null
    }

    if (targetMessage.isRemoteDelete) {
      warn(envelope.timestamp, "[handleReaction] Found a matching message, but it's flagged as remotely deleted. timestamp: " + targetSentTimestamp + "  author: " + targetAuthor.id)
      return null
    }

    val targetThread = SignalDatabase.threads.getThreadRecord(targetMessage.threadId)
    if (targetThread == null) {
      warn(envelope.timestamp, "[handleReaction] Could not find a thread for the message! timestamp: " + targetSentTimestamp + "  author: " + targetAuthor.id)
      return null
    }

    val targetThreadRecipientId = targetThread.recipient.id
    val groupRecord = SignalDatabase.groups.getGroup(targetThreadRecipientId).orNull()
    if (groupRecord != null && !groupRecord.members.contains(senderRecipientId)) {
      warn(envelope.timestamp, "[handleReaction] Reaction author is not in the group! timestamp: " + targetSentTimestamp + "  author: " + targetAuthor.id)
      return null
    }

    if (groupRecord == null && senderRecipientId != targetThreadRecipientId && Recipient.self().id != senderRecipientId) {
      warn(envelope.timestamp, "[handleReaction] Reaction author is not a part of the 1:1 thread! timestamp: " + targetSentTimestamp + "  author: " + targetAuthor.id)
      return null
    }

    val targetMessageId = (targetMessage as? MediaMmsMessageRecord)?.latestRevisionId ?: MessageId(targetMessage.id)

    if (isRemove) {
      SignalDatabase.reactions.deleteReaction(targetMessageId, senderRecipientId)
      ApplicationDependencies.getMessageNotifier().updateNotification(context)
    } else {
      val reactionRecord = ReactionRecord(emoji, senderRecipientId, message.timestamp, System.currentTimeMillis())
      SignalDatabase.reactions.addReaction(targetMessageId, reactionRecord)
      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.fromMessageRecord(targetMessage), false)
    }

    return targetMessageId
  }

  // JW: add a reaction to a message. Thanks ClauZ for the implementation
  fun setMessageReaction(context: Context, message: DataMessage, targetMessage: MessageRecord?, reaction: String) {
    if (targetMessage != null) {
      val reactionEmoji = EmojiUtil.getCanonicalRepresentation(reaction)
      val targetMessageId = MessageId(targetMessage.id)
      val reactionRecord = ReactionRecord(reactionEmoji, Recipient.self().id, message.timestamp, System.currentTimeMillis())
      reactions.addReaction(targetMessageId, reactionRecord)
      ApplicationDependencies.getMessageNotifier().updateNotification(context, fromMessageRecord(targetMessage), false)
    }
  }

  fun handleRemoteDelete(context: Context, envelope: Envelope, message: DataMessage, senderRecipientId: RecipientId, earlyMessageCacheEntry: EarlyMessageCacheEntry?): MessageId? {
    log(envelope.timestamp, "Remote delete for message ${message.delete.targetSentTimestamp}")

    val targetSentTimestamp: Long = message.delete.targetSentTimestamp
    val targetMessage: MessageRecord? = SignalDatabase.messages.getMessageFor(targetSentTimestamp, senderRecipientId)

    // JW: set a reaction to indicate the message was attempted to be remote deleted. Sender is myself, emoji is an exclamation.
    if (TextSecurePreferences.isIgnoreRemoteDelete(context)) { setMessageReaction(context, message, targetMessage, "\u2757"); return null; }

    return if (targetMessage != null && MessageConstraintsUtil.isValidRemoteDeleteReceive(targetMessage, senderRecipientId, envelope.serverTimestamp)) {
      SignalDatabase.messages.markAsRemoteDelete(targetMessage.id)
      if (targetMessage.isStory()) {
        SignalDatabase.messages.deleteRemotelyDeletedStory(targetMessage.id)
      }

      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.fromMessageRecord(targetMessage), false)

      MessageId(targetMessage.id)
    } else if (targetMessage == null) {
      warn(envelope.timestamp, "[handleRemoteDelete] Could not find matching message! timestamp: $targetSentTimestamp  author: $senderRecipientId")
      if (earlyMessageCacheEntry != null) {
        ApplicationDependencies.getEarlyMessageCache().store(senderRecipientId, targetSentTimestamp, earlyMessageCacheEntry)
        PushProcessEarlyMessagesJob.enqueue()
      }

      null
    } else {
      warn(envelope.timestamp, "[handleRemoteDelete] Invalid remote delete! deleteTime: ${envelope.serverTimestamp}, targetTime: ${targetMessage.serverTimestamp}, deleteAuthor: $senderRecipientId, targetAuthor: ${targetMessage.fromRecipient.id}")
      null
    }
  }

  /**
   * @param isActivatePaymentsRequest True if payments activation request message.
   * @param isPaymentsActivated       True if payments activated message.
   * @throws StorageFailedException
   */
  @Throws(StorageFailedException::class)
  private fun handlePaymentActivation(
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipientId: RecipientId,
    receivedTime: Long,
    isActivatePaymentsRequest: Boolean,
    isPaymentsActivated: Boolean
  ): MessageId? {
    log(envelope.timestamp, "Payment activation request: $isActivatePaymentsRequest activated: $isPaymentsActivated")
    try {
      val mediaMessage = IncomingMediaMessage(
        from = senderRecipientId,
        sentTimeMillis = envelope.timestamp,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = receivedTime,
        expiresIn = message.expireTimer.seconds.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        serverGuid = envelope.serverGuid,
        isActivatePaymentsRequest = isActivatePaymentsRequest,
        isPaymentsActivated = isPaymentsActivated
      )

      val insertResult: InsertResult? = SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()

      if (insertResult != null) {
        return MessageId(insertResult.messageId)
      }
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    }
    return null
  }

  @Throws(StorageFailedException::class)
  private fun handlePayment(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipientId: RecipientId,
    receivedTime: Long
  ): MessageId? {
    log(envelope.timestamp, "Payment message.")

    if (!message.payment.notification.mobileCoin.hasReceipt()) {
      warn(envelope.timestamp, "Ignoring payment message without notification")
      return null
    }

    val paymentNotification = message.payment.notification
    val uuid = UUID.randomUUID()
    val queue = "Payment_" + PushProcessMessageJob.getQueueName(senderRecipientId)

    try {
      SignalDatabase.payments.createIncomingPayment(
        uuid,
        senderRecipientId,
        message.timestamp,
        paymentNotification.note,
        Money.MobileCoin.ZERO,
        Money.MobileCoin.ZERO,
        paymentNotification.mobileCoin.receipt.toByteArray(),
        true
      )

      val mediaMessage = IncomingMediaMessage(
        from = senderRecipientId,
        body = uuid.toString(),
        sentTimeMillis = envelope.timestamp,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = receivedTime,
        expiresIn = message.expireTimer.seconds.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        serverGuid = envelope.serverGuid,
        isPushMessage = true,
        isPaymentsNotification = true
      )

      val insertResult: InsertResult? = SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()
      if (insertResult != null) {
        val messageId = MessageId(insertResult.messageId)
        ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
        return messageId
      }
    } catch (e: PublicKeyConflictException) {
      warn(envelope.timestamp, "Ignoring payment with public key already in database")
    } catch (e: SerializationException) {
      warn(envelope.timestamp, "Ignoring payment with bad data.", e)
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    } finally {
      ApplicationDependencies.getJobManager()
        .startChain(PaymentTransactionCheckJob(uuid, queue))
        .then(PaymentLedgerUpdateJob.updateLedger())
        .enqueue()
    }

    return null
  }

  @Throws(StorageFailedException::class)
  private fun handleStoryReply(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipientId: RecipientId,
    groupId: GroupId.V2?,
    receivedTime: Long
  ): MessageId? {
    log(envelope.timestamp, "Story reply.")

    val authorServiceId: ServiceId = ServiceId.parseOrThrow(message.storyContext.authorUuid)
    val sentTimestamp = message.storyContext.sentTimestamp

    SignalDatabase.messages.beginTransaction()
    return try {
      val storyAuthorRecipientId = RecipientId.from(authorServiceId)
      val selfId = Recipient.self().id
      val parentStoryId: ParentStoryId
      var quoteModel: QuoteModel? = null
      var expiresInMillis: Duration = 0L.seconds
      var storyMessageId: MessageId? = null

      try {
        if (selfId == storyAuthorRecipientId) {
          storyMessageId = SignalDatabase.storySends.getStoryMessageFor(senderRecipientId, sentTimestamp)
        }

        if (storyMessageId == null) {
          storyMessageId = SignalDatabase.messages.getStoryId(storyAuthorRecipientId, sentTimestamp)
        }

        val story: MmsMessageRecord = SignalDatabase.messages.getMessageRecord(storyMessageId.id) as MmsMessageRecord
        var threadRecipientId: RecipientId = SignalDatabase.threads.getRecipientForThreadId(story.threadId)!!.id
        val groupRecord: GroupRecord? = SignalDatabase.groups.getGroup(threadRecipientId).orNull()
        val groupStory: Boolean = groupRecord?.isActive ?: false

        if (!groupStory) {
          threadRecipientId = senderRecipientId
        }

        handlePossibleExpirationUpdate(envelope, metadata, senderRecipientId, threadRecipientId, groupId, message.expireTimer.seconds, receivedTime)

        if (message.hasGroupContext) {
          parentStoryId = GroupReply(storyMessageId.id)
        } else if (groupStory || SignalDatabase.storySends.canReply(senderRecipientId, sentTimestamp)) {
          parentStoryId = DirectReply(storyMessageId.id)

          var displayText = ""
          var bodyRanges: BodyRangeList? = null
          if (story.storyType.isTextStory) {
            displayText = story.body
            bodyRanges = story.messageRanges
          }

          quoteModel = QuoteModel(sentTimestamp, storyAuthorRecipientId, displayText, false, story.slideDeck.asAttachments(), emptyList(), QuoteModel.Type.NORMAL, bodyRanges)
          expiresInMillis = message.expireTimer.seconds
        } else {
          warn(envelope.timestamp, "Story has replies disabled. Dropping reply.")
          return null
        }
      } catch (e: NoSuchMessageException) {
        warn(envelope.timestamp, "Couldn't find story for reply.", e)
        return null
      }

      val bodyRanges: BodyRangeList? = message.bodyRangesList.filter { it.hasStyle() }.toList().toBodyRangeList()

      val mediaMessage = IncomingMediaMessage(
        from = senderRecipientId,
        sentTimeMillis = envelope.timestamp,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = System.currentTimeMillis(),
        parentStoryId = parentStoryId,
        expiresIn = expiresInMillis.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        body = message.body,
        groupId = groupId,
        quote = quoteModel,
        mentions = getMentions(message.bodyRangesList),
        serverGuid = envelope.serverGuid,
        messageRanges = bodyRanges
      )

      val insertResult: InsertResult? = SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()

      if (insertResult != null) {
        SignalDatabase.messages.setTransactionSuccessful()

        if (parentStoryId.isGroupReply()) {
          ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.fromThreadAndReply(insertResult.threadId, parentStoryId as GroupReply))
        } else {
          ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
          TrimThreadJob.enqueueAsync(insertResult.threadId)
        }

        if (parentStoryId.isDirectReply()) {
          MessageId.fromNullable(insertResult.messageId)
        } else {
          null
        }
      } else {
        warn(envelope.timestamp, "Failed to insert story reply.")
        null
      }
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    } finally {
      SignalDatabase.messages.endTransaction()
    }
  }

  @Throws(StorageFailedException::class)
  private fun handleGiftMessage(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipient: Recipient,
    threadRecipientId: RecipientId,
    receivedTime: Long
  ): MessageId? {
    log(message.timestamp, "Gift message.")

    check(message.giftBadge.hasReceiptCredentialPresentation())

    notifyTypingStoppedFromIncomingMessage(context, senderRecipient, threadRecipientId, metadata.sourceDeviceId)

    val token = ReceiptCredentialPresentation(message.giftBadge.receiptCredentialPresentation.toByteArray()).serialize()
    val giftBadge = GiftBadge.newBuilder()
      .setRedemptionToken(ByteString.copyFrom(token))
      .setRedemptionState(GiftBadge.RedemptionState.PENDING)
      .build()

    val insertResult: InsertResult? = try {
      val mediaMessage = IncomingMediaMessage(
        from = senderRecipient.id,
        sentTimeMillis = envelope.timestamp,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = receivedTime,
        expiresIn = message.expireTimer.seconds.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        body = Base64.encodeBytes(giftBadge.toByteArray()),
        serverGuid = envelope.serverGuid,
        giftBadge = giftBadge
      )

      SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    }

    return if (insertResult != null) {
      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
      TrimThreadJob.enqueueAsync(insertResult.threadId)
      MessageId(insertResult.messageId)
    } else {
      null
    }
  }

  @Throws(StorageFailedException::class)
  private fun handleMediaMessage(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipient: Recipient,
    threadRecipientId: RecipientId,
    groupId: GroupId.V2?,
    receivedTime: Long
  ): MessageId? {
    log(envelope.timestamp, "Media message.")

    notifyTypingStoppedFromIncomingMessage(context, senderRecipient, threadRecipientId, metadata.sourceDeviceId)

    val insertResult: InsertResult?
    val viewOnce: Boolean = if (TextSecurePreferences.isKeepViewOnceMessages(context)) false else message.isViewOnce // JW

    SignalDatabase.messages.beginTransaction()
    try {
      val quote: QuoteModel? = getValidatedQuote(context, envelope.timestamp, message)
      val contacts: List<Contact> = getContacts(message)
      val linkPreviews: List<LinkPreview> = getLinkPreviews(message.previewList, message.body ?: "", false)
      val mentions: List<Mention> = getMentions(message.bodyRangesList)
      val sticker: Attachment? = getStickerAttachment(envelope.timestamp, message)
      val attachments: List<Attachment> = message.attachmentsList.toPointers()
      val messageRanges: BodyRangeList? = if (message.bodyRangesCount > 0) message.bodyRangesList.filter { it.hasStyle() }.toList().toBodyRangeList() else null

      handlePossibleExpirationUpdate(envelope, metadata, senderRecipient.id, threadRecipientId, groupId, message.expireTimer.seconds, receivedTime)

      val mediaMessage = IncomingMediaMessage(
        from = senderRecipient.id,
        sentTimeMillis = envelope.timestamp,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = receivedTime,
        expiresIn = message.expireTimer.seconds.inWholeMilliseconds,
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
        isViewOnce = viewOnce, // JW
||||||| parent of f050803628 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 7fa5495175 (Added extra options)
        isUnidentified = metadata.sealedSender,
        body = message.body.ifEmpty { null },
        groupId = groupId,
        attachments = attachments + if (sticker != null) listOf(sticker) else emptyList(),
        quote = quote,
        sharedContacts = contacts,
        linkPreviews = linkPreviews,
        mentions = mentions,
        serverGuid = envelope.serverGuid,
        messageRanges = messageRanges,
        isPushMessage = true
      )

      insertResult = SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()
      if (insertResult != null) {
        SignalDatabase.messages.setTransactionSuccessful()
      }
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    } finally {
      SignalDatabase.messages.endTransaction()
    }

    return if (insertResult != null) {
      val allAttachments = SignalDatabase.attachments.getAttachmentsForMessage(insertResult.messageId)
      val stickerAttachments = allAttachments.filter { it.isSticker }.toList()
      val attachments = allAttachments.filterNot { it.isSticker }.toList()

      forceStickerDownloadIfNecessary(context, insertResult.messageId, stickerAttachments)

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
      for (attachment in attachments) {
        ApplicationDependencies.getJobManager().add(AttachmentDownloadJob(insertResult.messageId, attachment.attachmentId, false))
      }

      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
      TrimThreadJob.enqueueAsync(insertResult.threadId)

      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
        ApplicationDependencies.getViewOnceMessageManager().scheduleIfNecessary()
||||||| parent of e64c4c41bb (Added extra options)
        if (message.isViewOnce) {
||||||| parent of e26890a182 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of cdbbc46ede (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> cdbbc46ede (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
        if (message.isViewOnce) {
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
>>>>>>> 7fa5495175 (Added extra options)
          ApplicationDependencies.getViewOnceMessageManager().scheduleIfNecessary()
        }
=======
      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
          ApplicationDependencies.getViewOnceMessageManager().scheduleIfNecessary()
        }
>>>>>>> e64c4c41bb (Added extra options)
      }

      MessageId(insertResult.messageId)
    } else {
      null
    }
  }

  @Throws(StorageFailedException::class)
  private fun handleTextMessage(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipient: Recipient,
    threadRecipientId: RecipientId,
    groupId: GroupId.V2?,
    receivedTime: Long
  ): MessageId? {
    log(envelope.timestamp, "Text message.")

    val body = if (message.hasBody()) message.body else ""

    handlePossibleExpirationUpdate(envelope, metadata, senderRecipient.id, threadRecipientId, groupId, message.expireTimer.seconds, receivedTime)

    notifyTypingStoppedFromIncomingMessage(context, senderRecipient, threadRecipientId, metadata.sourceDeviceId)

    val textMessage = IncomingTextMessage(
      senderRecipient.id,
      metadata.sourceDeviceId,
      envelope.timestamp,
      envelope.serverTimestamp,
      receivedTime,
      body,
      Optional.ofNullable(groupId),
      message.expireTimer.seconds.inWholeMilliseconds,
      metadata.sealedSender,
      envelope.serverGuid
    )

    val insertResult: InsertResult? = SignalDatabase.messages.insertMessageInbox(IncomingEncryptedMessage(textMessage, body)).orNull()

    return if (insertResult != null) {
      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
      MessageId(insertResult.messageId)
    } else {
      null
    }
  }

  fun handleGroupCallUpdateMessage(
    envelope: Envelope,
    message: DataMessage,
    senderRecipientId: RecipientId,
    groupId: GroupId.V2?
  ) {
    log(envelope.timestamp, "Group call update message.")

    if (groupId == null || !message.hasGroupCallUpdate()) {
      warn(envelope.timestamp, "Invalid group for group call update message")
      return
    }

    val groupRecipientId = SignalDatabase.recipients.getOrInsertFromPossiblyMigratedGroupId(groupId)

    SignalDatabase.calls.insertOrUpdateGroupCallFromExternalEvent(
      groupRecipientId,
      senderRecipientId,
      envelope.serverTimestamp,
      if (message.groupCallUpdate.hasEraId()) message.groupCallUpdate.eraId else null
    )

    GroupCallPeekJob.enqueue(groupRecipientId)
  }

  fun notifyTypingStoppedFromIncomingMessage(context: Context, senderRecipient: Recipient, threadRecipientId: RecipientId, device: Int) {
    val threadId = SignalDatabase.threads.getThreadIdIfExistsFor(threadRecipientId)

    if (threadId > 0 && TextSecurePreferences.isTypingIndicatorsEnabled(context)) {
      debug("Typing stopped on thread $threadId due to an incoming message.")
      ApplicationDependencies.getTypingStatusRepository().onTypingStopped(threadId, senderRecipient, device, true)
    }
  }

  fun getMentions(mentionBodyRanges: List<BodyRange>): List<Mention> {
    return mentionBodyRanges
      .filter { it.hasMentionUuid() }
      .mapNotNull {
        val serviceId = ServiceId.parseOrNull(it.mentionUuid)

        if (serviceId != null) {
          val id = Recipient.externalPush(serviceId).id
          Mention(id, it.start, it.length)
        } else {
          null
        }
      }
  }

  fun forceStickerDownloadIfNecessary(context: Context, messageId: Long, stickerAttachments: List<DatabaseAttachment>) {
    if (stickerAttachments.isEmpty()) {
      return
    }

    val stickerAttachment = stickerAttachments[0]
    if (stickerAttachment.transferState != AttachmentTable.TRANSFER_PROGRESS_DONE) {
      val downloadJob = AttachmentDownloadJob(messageId, stickerAttachment.attachmentId, true)
      try {
        downloadJob.setContext(context)
        downloadJob.doWork()
      } catch (e: Exception) {
        warn("Failed to download sticker inline. Scheduling.")
        ApplicationDependencies.getJobManager().add(downloadJob)
      }
    }
  }

  private fun insertPlaceholder(sender: RecipientId, senderDevice: Int, timestamp: Long, groupId: GroupId?): InsertResult? {
    val textMessage = IncomingTextMessage(
      sender,
      senderDevice,
      timestamp,
      -1,
      System.currentTimeMillis(),
      "",
      groupId.asOptional(),
      0,
      false,
      null
    )
    return SignalDatabase.messages.insertMessageInbox(IncomingEncryptedMessage(textMessage, "")).orNull()
  }

  fun getValidatedQuote(context: Context, timestamp: Long, message: DataMessage): QuoteModel? {
    if (!message.hasQuote()) {
      return null
    }

    val quote: DataMessage.Quote = message.quote

    if (quote.id <= 0) {
      warn(timestamp, "Received quote without an ID! Ignoring...")
      return null
    }

    val authorId = Recipient.externalPush(ServiceId.parseOrThrow(quote.authorUuid)).id
    var quotedMessage = SignalDatabase.messages.getMessageFor(quote.id, authorId) as? MediaMmsMessageRecord

    if (quotedMessage != null && !quotedMessage.isRemoteDelete) {
      log(timestamp, "Found matching message record...")

      val attachments: MutableList<Attachment> = mutableListOf()
      val mentions: MutableList<Mention> = mutableListOf()

      quotedMessage = quotedMessage.withAttachments(context, SignalDatabase.attachments.getAttachmentsForMessage(quotedMessage.id))

      mentions.addAll(SignalDatabase.mentions.getMentionsForMessage(quotedMessage.id))

      if (quotedMessage.isViewOnce) {
        attachments.add(TombstoneAttachment(MediaUtil.VIEW_ONCE, true))
      } else {
        attachments += quotedMessage.slideDeck.asAttachments()

        if (attachments.isEmpty()) {
          attachments += quotedMessage
            .linkPreviews
            .filter { it.thumbnail.isPresent }
            .map { it.thumbnail.get() }
        }
      }

      if (quotedMessage.isPaymentNotification) {
        quotedMessage = SignalDatabase.payments.updateMessageWithPayment(quotedMessage) as MediaMmsMessageRecord
      }

      val body = if (quotedMessage.isPaymentNotification) quotedMessage.getDisplayBody(context).toString() else quotedMessage.body

      return QuoteModel(
        quote.id,
        authorId,
        body,
        false,
        attachments,
        mentions,
        QuoteModel.Type.fromProto(quote.type),
        quotedMessage.messageRanges
      )
    } else if (quotedMessage != null) {
      warn(timestamp, "Found the target for the quote, but it's flagged as remotely deleted.")
    }

    warn(timestamp, "Didn't find matching message record...")
    return QuoteModel(
      quote.id,
      authorId,
      quote.text,
      true,
      quote.attachmentsList.mapNotNull { PointerAttachment.forPointer(it).orNull() },
      getMentions(quote.bodyRangesList),
      QuoteModel.Type.fromProto(quote.type),
      quote.bodyRangesList.filterNot { it.hasMentionUuid() }.toBodyRangeList()
    )
  }

  fun getContacts(message: DataMessage): List<Contact> {
    return message.contactList.map { ContactModelMapper.remoteToLocal(it) }
  }

  fun getLinkPreviews(previews: List<Preview>, body: String, isStoryEmbed: Boolean): List<LinkPreview> {
    if (previews.isEmpty()) {
      return emptyList()
    }

    val urlsInMessage = LinkPreviewUtil.findValidPreviewUrls(body)

    return previews
      .mapNotNull { preview ->
        val thumbnail: Attachment? = preview.image.toPointer()
        val url: Optional<String> = preview.url.toOptional()
        val title: Optional<String> = preview.title.toOptional()
        val description: Optional<String> = preview.description.toOptional()
        val hasTitle = !TextUtils.isEmpty(title.orElse(""))
        val presentInBody = url.isPresent && urlsInMessage.containsUrl(url.get())
        val validDomain = url.isPresent && LinkUtil.isValidPreviewUrl(url.get())

        if (hasTitle && (presentInBody || isStoryEmbed) && validDomain) {
          val linkPreview = LinkPreview(url.get(), title.orElse(""), description.orElse(""), preview.date, thumbnail.toOptional())
          linkPreview
        } else {
          warn(String.format("Discarding an invalid link preview. hasTitle: %b presentInBody: %b isStoryEmbed: %b validDomain: %b", hasTitle, presentInBody, isStoryEmbed, validDomain))
          null
        }
      }
  }

  fun getStickerAttachment(timestamp: Long, message: DataMessage): Attachment? {
    if (!message.hasSticker()) {
      return null
    }

    val sticker = message.sticker
    if (!(message.sticker.hasPackId() && message.sticker.hasPackKey() && message.sticker.hasStickerId() && message.sticker.hasData())) {
      warn(timestamp, "Malformed sticker!")
      return null
    }

    val packId = Hex.toStringCondensed(sticker.packId.toByteArray())
    val packKey = Hex.toStringCondensed(sticker.packKey.toByteArray())
    val stickerId = sticker.stickerId
    val emoji = sticker.emoji
    val stickerLocator = StickerLocator(packId, packKey, stickerId, emoji)

    val stickerRecord = SignalDatabase.stickers.getSticker(stickerLocator.packId, stickerLocator.stickerId, false)

    return if (stickerRecord != null) {
      UriAttachment(
        stickerRecord.uri,
        stickerRecord.contentType,
        AttachmentTable.TRANSFER_PROGRESS_DONE,
        stickerRecord.size,
        StickerSlide.WIDTH,
        StickerSlide.HEIGHT,
        null,
        SecureRandom().nextLong().toString(),
        false,
        false,
        false,
        false,
        null,
        stickerLocator,
        null,
        null,
        null
      )
    } else {
      sticker.data.toPointer(stickerLocator)
    }
  }
}
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
package org.thoughtcrime.securesms.messages

import android.content.Context
import android.text.TextUtils
import com.google.protobuf.ByteString
import com.mobilecoin.lib.exceptions.SerializationException
import org.signal.core.util.Hex
import org.signal.core.util.concurrent.SignalExecutors
import org.signal.core.util.logging.Log
import org.signal.core.util.orNull
import org.signal.core.util.toOptional
import org.signal.libsignal.zkgroup.receipts.ReceiptCredentialPresentation
import org.thoughtcrime.securesms.attachments.Attachment
import org.thoughtcrime.securesms.attachments.DatabaseAttachment
import org.thoughtcrime.securesms.attachments.PointerAttachment
import org.thoughtcrime.securesms.attachments.TombstoneAttachment
import org.thoughtcrime.securesms.attachments.UriAttachment
import org.thoughtcrime.securesms.components.emoji.EmojiUtil
import org.thoughtcrime.securesms.contactshare.Contact
import org.thoughtcrime.securesms.contactshare.ContactModelMapper
import org.thoughtcrime.securesms.crypto.ProfileKeyUtil
import org.thoughtcrime.securesms.crypto.SecurityEvent
import org.thoughtcrime.securesms.database.AttachmentTable
import org.thoughtcrime.securesms.database.MessageTable.InsertResult
import org.thoughtcrime.securesms.database.NoSuchMessageException
import org.thoughtcrime.securesms.database.PaymentTable.PublicKeyConflictException
import org.thoughtcrime.securesms.database.SignalDatabase
import org.thoughtcrime.securesms.database.SignalDatabase.Companion.reactions // JW
import org.thoughtcrime.securesms.database.model.GroupRecord
import org.thoughtcrime.securesms.database.model.MediaMmsMessageRecord
import org.thoughtcrime.securesms.database.model.Mention
import org.thoughtcrime.securesms.database.model.MessageId
import org.thoughtcrime.securesms.database.model.MessageRecord
import org.thoughtcrime.securesms.database.model.MmsMessageRecord
import org.thoughtcrime.securesms.database.model.ParentStoryId
import org.thoughtcrime.securesms.database.model.ParentStoryId.DirectReply
import org.thoughtcrime.securesms.database.model.ParentStoryId.GroupReply
import org.thoughtcrime.securesms.database.model.ReactionRecord
import org.thoughtcrime.securesms.database.model.databaseprotos.BodyRangeList
import org.thoughtcrime.securesms.database.model.databaseprotos.GiftBadge
import org.thoughtcrime.securesms.database.model.toBodyRangeList
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies
import org.thoughtcrime.securesms.groups.BadGroupIdException
import org.thoughtcrime.securesms.groups.GroupId
import org.thoughtcrime.securesms.jobs.AttachmentDownloadJob
import org.thoughtcrime.securesms.jobs.GroupCallPeekJob
import org.thoughtcrime.securesms.jobs.GroupV2UpdateSelfProfileKeyJob
import org.thoughtcrime.securesms.jobs.PaymentLedgerUpdateJob
import org.thoughtcrime.securesms.jobs.PaymentTransactionCheckJob
import org.thoughtcrime.securesms.jobs.ProfileKeySendJob
import org.thoughtcrime.securesms.jobs.PushProcessEarlyMessagesJob
import org.thoughtcrime.securesms.jobs.PushProcessMessageJob
import org.thoughtcrime.securesms.jobs.RefreshAttributesJob
import org.thoughtcrime.securesms.jobs.RetrieveProfileJob
import org.thoughtcrime.securesms.jobs.SendDeliveryReceiptJob
import org.thoughtcrime.securesms.jobs.TrimThreadJob
import org.thoughtcrime.securesms.linkpreview.LinkPreview
import org.thoughtcrime.securesms.linkpreview.LinkPreviewUtil
import org.thoughtcrime.securesms.messages.MessageContentProcessor.StorageFailedException
import org.thoughtcrime.securesms.messages.MessageContentProcessorV2.Companion.debug
import org.thoughtcrime.securesms.messages.MessageContentProcessorV2.Companion.log
import org.thoughtcrime.securesms.messages.MessageContentProcessorV2.Companion.warn
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.groupMasterKey
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.hasGroupContext
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.hasRemoteDelete
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isEndSession
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isExpirationUpdate
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isInvalid
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isMediaMessage
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isPaymentActivated
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isPaymentActivationRequest
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isStoryReaction
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.toPointer
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.toPointers
import org.thoughtcrime.securesms.mms.IncomingMediaMessage
import org.thoughtcrime.securesms.mms.MmsException
import org.thoughtcrime.securesms.mms.QuoteModel
import org.thoughtcrime.securesms.mms.StickerSlide
import org.thoughtcrime.securesms.notifications.v2.ConversationId
import org.thoughtcrime.securesms.notifications.v2.ConversationId.Companion.fromMessageRecord // JW
import org.thoughtcrime.securesms.recipients.Recipient
import org.thoughtcrime.securesms.recipients.RecipientId
import org.thoughtcrime.securesms.recipients.RecipientUtil
import org.thoughtcrime.securesms.sms.IncomingEncryptedMessage
import org.thoughtcrime.securesms.sms.IncomingEndSessionMessage
import org.thoughtcrime.securesms.sms.IncomingTextMessage
import org.thoughtcrime.securesms.stickers.StickerLocator
import org.thoughtcrime.securesms.storage.StorageSyncHelper
import org.thoughtcrime.securesms.util.Base64
import org.thoughtcrime.securesms.util.EarlyMessageCacheEntry
import org.thoughtcrime.securesms.util.LinkUtil
import org.thoughtcrime.securesms.util.MediaUtil
import org.thoughtcrime.securesms.util.MessageConstraintsUtil
import org.thoughtcrime.securesms.util.TextSecurePreferences
import org.thoughtcrime.securesms.util.isStory
import org.whispersystems.signalservice.api.crypto.EnvelopeMetadata
import org.whispersystems.signalservice.api.payments.Money
import org.whispersystems.signalservice.api.push.ServiceId
import org.whispersystems.signalservice.api.util.OptionalUtil.asOptional
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.BodyRange
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.Content
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.DataMessage
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.Envelope
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.GroupContextV2
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.Preview
import java.security.SecureRandom
import java.util.Optional
import java.util.UUID
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

object DataMessageProcessor {

  fun process(
    context: Context,
    senderRecipient: Recipient,
    threadRecipient: Recipient,
    envelope: Envelope,
    content: Content,
    metadata: EnvelopeMetadata,
    receivedTime: Long,
    earlyMessageCacheEntry: EarlyMessageCacheEntry?
  ) {
    val message: DataMessage = content.dataMessage
    val groupId: GroupId.V2? = if (message.hasGroupContext) GroupId.v2(message.groupV2.groupMasterKey) else null

    if (groupId != null) {
      if (MessageContentProcessorV2.handleGv2PreProcessing(context, envelope.timestamp, content, metadata, groupId, message.groupV2, senderRecipient)) {
        return
      }
    }

    var messageId: MessageId? = null
    when {
      message.isInvalid -> handleInvalidMessage(context, senderRecipient.id, metadata.sourceDeviceId, groupId, envelope.timestamp)
      message.isEndSession -> messageId = handleEndSessionMessage(context, senderRecipient.id, envelope, metadata)
      message.isExpirationUpdate -> messageId = handleExpirationUpdate(envelope, metadata, senderRecipient.id, threadRecipient.id, groupId, message.expireTimer.seconds, receivedTime, false)
      message.isStoryReaction -> messageId = handleStoryReaction(context, envelope, metadata, message, senderRecipient.id, groupId)
      message.hasReaction() -> messageId = handleReaction(context, envelope, message, senderRecipient.id, earlyMessageCacheEntry)
      message.hasRemoteDelete -> messageId = handleRemoteDelete(context, envelope, message, senderRecipient.id, earlyMessageCacheEntry)
      message.isPaymentActivationRequest -> messageId = handlePaymentActivation(envelope, metadata, message, senderRecipient.id, receivedTime, isActivatePaymentsRequest = true, isPaymentsActivated = false)
      message.isPaymentActivated -> messageId = handlePaymentActivation(envelope, metadata, message, senderRecipient.id, receivedTime, isActivatePaymentsRequest = false, isPaymentsActivated = true)
      message.hasPayment() -> messageId = handlePayment(context, envelope, metadata, message, senderRecipient.id, receivedTime)
      message.hasStoryContext() -> messageId = handleStoryReply(context, envelope, metadata, message, senderRecipient.id, groupId, receivedTime)
      message.hasGiftBadge() -> messageId = handleGiftMessage(context, envelope, metadata, message, senderRecipient, threadRecipient.id, receivedTime)
      message.isMediaMessage -> messageId = handleMediaMessage(context, envelope, metadata, message, senderRecipient, threadRecipient.id, groupId, receivedTime)
      message.hasBody() -> messageId = handleTextMessage(context, envelope, metadata, message, senderRecipient, threadRecipient.id, groupId, receivedTime)
      message.hasGroupCallUpdate() -> handleGroupCallUpdateMessage(envelope, message, senderRecipient.id, groupId)
    }

    if (groupId != null && SignalDatabase.groups.isUnknownGroup(groupId)) {
      handleUnknownGroupMessage(envelope.timestamp, message.groupV2)
    }

    if (message.hasProfileKey()) {
      handleProfileKey(envelope.timestamp, message.profileKey.toByteArray(), senderRecipient)
    }

    if (metadata.sealedSender && messageId != null) {
      SignalExecutors.BOUNDED.execute { ApplicationDependencies.getJobManager().add(SendDeliveryReceiptJob(senderRecipient.id, message.timestamp, messageId)) }
    } else if (!metadata.sealedSender) {
      if (RecipientUtil.shouldHaveProfileKey(threadRecipient)) {
        Log.w(MessageContentProcessorV2.TAG, "Received an unsealed sender message from " + senderRecipient.id + ", but they should already have our profile key. Correcting.")

        if (groupId != null) {
          Log.i(MessageContentProcessorV2.TAG, "Message was to a GV2 group. Ensuring our group profile keys are up to date.")
          ApplicationDependencies
            .getJobManager()
            .startChain(RefreshAttributesJob(false))
            .then(GroupV2UpdateSelfProfileKeyJob.withQueueLimits(groupId))
            .enqueue()
        } else if (!threadRecipient.isGroup) {
          Log.i(MessageContentProcessorV2.TAG, "Message was to a 1:1. Ensuring this user has our profile key.")
          val profileSendJob = ProfileKeySendJob.create(SignalDatabase.threads.getOrCreateThreadIdFor(threadRecipient), true)
          if (profileSendJob != null) {
            ApplicationDependencies
              .getJobManager()
              .startChain(RefreshAttributesJob(false))
              .then(profileSendJob)
              .enqueue()
          }
        }
      }
    }
  }

  private fun handleProfileKey(
    timestamp: Long,
    messageProfileKeyBytes: ByteArray,
    senderRecipient: Recipient
  ) {
    val messageProfileKey = ProfileKeyUtil.profileKeyOrNull(messageProfileKeyBytes)

    if (senderRecipient.isSelf) {
      if (ProfileKeyUtil.getSelfProfileKey() != messageProfileKey) {
        warn(timestamp, "Saw a sync message whose profile key doesn't match our records. Scheduling a storage sync to check.")
        StorageSyncHelper.scheduleSyncForDataChange()
      }
    } else if (messageProfileKey != null) {
      if (SignalDatabase.recipients.setProfileKey(senderRecipient.id, messageProfileKey)) {
        log(timestamp, "Profile key on message from " + senderRecipient.id + " didn't match our local store. It has been updated.")
        ApplicationDependencies.getJobManager().add(RetrieveProfileJob.forRecipient(senderRecipient.id))
      }
    } else {
      warn(timestamp.toString(), "Ignored invalid profile key seen in message")
    }
  }

  @Throws(BadGroupIdException::class)
  fun handleUnknownGroupMessage(timestamp: Long, groupContextV2: GroupContextV2) {
    log(timestamp, "Unknown group message.")
    warn(timestamp, "Received a GV2 message for a group we have no knowledge of -- attempting to fix this state.")
    SignalDatabase.groups.fixMissingMasterKey(groupContextV2.groupMasterKey)
  }

  private fun handleInvalidMessage(
    context: Context,
    sender: RecipientId,
    senderDevice: Int,
    groupId: GroupId?,
    timestamp: Long
  ) {
    log(timestamp, "Invalid message.")

    val insertResult: InsertResult? = insertPlaceholder(sender, senderDevice, timestamp, groupId)
    if (insertResult != null) {
      SignalDatabase.messages.markAsInvalidMessage(insertResult.messageId)
      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
    }
  }

  private fun handleEndSessionMessage(
    context: Context,
    senderRecipientId: RecipientId,
    envelope: Envelope,
    metadata: EnvelopeMetadata
  ): MessageId? {
    log(envelope.timestamp, "End session message.")

    val incomingTextMessage = IncomingTextMessage(
      senderRecipientId,
      metadata.sourceDeviceId,
      envelope.timestamp,
      envelope.serverTimestamp,
      System.currentTimeMillis(),
      "",
      Optional.empty(),
      0,
      metadata.sealedSender,
      envelope.serverGuid
    )

    val insertResult: InsertResult? = SignalDatabase.messages.insertMessageInbox(IncomingEndSessionMessage(incomingTextMessage)).orNull()

    return if (insertResult != null) {
      ApplicationDependencies.getProtocolStore().aci().deleteAllSessions(metadata.sourceServiceId.toString())
      SecurityEvent.broadcastSecurityUpdateEvent(context)
      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
      MessageId(insertResult.messageId)
    } else {
      null
    }
  }

  /**
   * @param sideEffect True if the event is side effect of a different message, false if the message itself was an expiration update.
   * @throws StorageFailedException
   */
  @Throws(StorageFailedException::class)
  private fun handleExpirationUpdate(
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    senderRecipientId: RecipientId,
    threadRecipientId: RecipientId,
    groupId: GroupId.V2?,
    expiresIn: Duration,
    receivedTime: Long,
    sideEffect: Boolean
  ): MessageId? {
    log(envelope.timestamp, "Expiration update. Side effect: $sideEffect")

    if (groupId != null) {
      warn(envelope.timestamp, "Expiration update received for GV2. Ignoring.")
      return null
    }

    if (SignalDatabase.recipients.getExpiresInSeconds(threadRecipientId) == expiresIn.inWholeSeconds) {
      log(envelope.timestamp, "No change in message expiry for group. Ignoring.")
      return null
    }

    try {
      val mediaMessage = IncomingMediaMessage(
        from = senderRecipientId,
        sentTimeMillis = envelope.timestamp - if (sideEffect) 1 else 0,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = receivedTime,
        expiresIn = expiresIn.inWholeMilliseconds,
        isExpirationUpdate = true,
        isUnidentified = metadata.sealedSender,
        serverGuid = envelope.serverGuid,
        isPushMessage = true
      )

      val insertResult: InsertResult? = SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()
      SignalDatabase.recipients.setExpireMessages(threadRecipientId, expiresIn.inWholeSeconds.toInt())

      if (insertResult != null) {
        return MessageId(insertResult.messageId)
      }
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    }

    return null
  }

  /**
   * Inserts an expiration update if the message timer doesn't match the thread timer.
   */
  @Throws(StorageFailedException::class)
  fun handlePossibleExpirationUpdate(
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    senderRecipientId: RecipientId,
    threadRecipientId: RecipientId,
    groupId: GroupId.V2?,
    expiresIn: Duration,
    receivedTime: Long
  ) {
    if (SignalDatabase.recipients.getExpiresInSeconds(threadRecipientId) != expiresIn.inWholeSeconds) {
      warn(envelope.timestamp, "Message expire time didn't match thread expire time. Handling timer update.")
      handleExpirationUpdate(envelope, metadata, senderRecipientId, threadRecipientId, groupId, expiresIn, receivedTime, true)
    }
  }

  @Throws(StorageFailedException::class)
  private fun handleStoryReaction(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipientId: RecipientId,
    groupId: GroupId.V2?
  ): MessageId? {
    log(envelope.timestamp, "Story reaction.")

    val emoji = message.reaction.emoji
    if (!EmojiUtil.isEmoji(emoji)) {
      warn(envelope.timestamp, "Story reaction text is not a valid emoji! Ignoring the message.")
      return null
    }

    val authorServiceId: ServiceId = ServiceId.parseOrThrow(message.storyContext.authorUuid)
    val sentTimestamp = message.storyContext.sentTimestamp

    SignalDatabase.messages.beginTransaction()
    return try {
      val authorRecipientId = RecipientId.from(authorServiceId)
      val parentStoryId: ParentStoryId
      var quoteModel: QuoteModel? = null
      var expiresIn: Duration = 0L.seconds

      try {
        val storyId = SignalDatabase.messages.getStoryId(authorRecipientId, sentTimestamp).id

        if (groupId != null) {
          parentStoryId = GroupReply(storyId)
        } else if (SignalDatabase.storySends.canReply(senderRecipientId, sentTimestamp)) {
          val story = SignalDatabase.messages.getMessageRecord(storyId) as MmsMessageRecord
          var displayText = ""
          var bodyRanges: BodyRangeList? = null

          if (story.storyType.isTextStory) {
            displayText = story.body
            bodyRanges = story.messageRanges
          }

          parentStoryId = DirectReply(storyId)
          quoteModel = QuoteModel(sentTimestamp, authorRecipientId, displayText, false, story.slideDeck.asAttachments(), emptyList(), QuoteModel.Type.NORMAL, bodyRanges)
          expiresIn = message.expireTimer.seconds
        } else {
          warn(envelope.timestamp, "Story has reactions disabled. Dropping reaction.")
          return null
        }
      } catch (e: NoSuchMessageException) {
        warn(envelope.timestamp, "Couldn't find story for reaction.", e)
        return null
      }

      val mediaMessage = IncomingMediaMessage(
        from = senderRecipientId,
        sentTimeMillis = envelope.timestamp,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = System.currentTimeMillis(),
        parentStoryId = parentStoryId,
        isStoryReaction = true,
        expiresIn = expiresIn.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        body = emoji,
        groupId = groupId,
        quote = quoteModel,
        serverGuid = envelope.serverGuid
      )

      val insertResult: InsertResult? = SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()
      if (insertResult != null) {
        SignalDatabase.messages.setTransactionSuccessful()

        if (parentStoryId.isGroupReply()) {
          ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.fromThreadAndReply(insertResult.threadId, parentStoryId as GroupReply))
        } else {
          ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
          TrimThreadJob.enqueueAsync(insertResult.threadId)
        }

        if (parentStoryId.isDirectReply()) {
          MessageId(insertResult.messageId)
        } else {
          null
        }
      } else {
        warn(envelope.timestamp, "Failed to insert story reaction")
        null
      }
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    } finally {
      SignalDatabase.messages.endTransaction()
    }
  }

  @Throws(StorageFailedException::class)
  fun handleReaction(
    context: Context,
    envelope: Envelope,
    message: DataMessage,
    senderRecipientId: RecipientId,
    earlyMessageCacheEntry: EarlyMessageCacheEntry?
  ): MessageId? {
    log(envelope.timestamp, "Handle reaction for message " + message.reaction.targetSentTimestamp)

    val emoji: String = message.reaction.emoji
    val isRemove: Boolean = message.reaction.remove
    val targetAuthorServiceId: ServiceId = ServiceId.parseOrThrow(message.reaction.targetAuthorUuid)
    val targetSentTimestamp = message.reaction.targetSentTimestamp

    if (!EmojiUtil.isEmoji(emoji)) {
      warn(envelope.timestamp, "Reaction text is not a valid emoji! Ignoring the message.")
      return null
    }

    val targetAuthor = Recipient.externalPush(targetAuthorServiceId)
    val targetMessage = SignalDatabase.messages.getMessageFor(targetSentTimestamp, targetAuthor.id)
    if (targetMessage == null) {
      warn(envelope.timestamp, "[handleReaction] Could not find matching message! Putting it in the early message cache. timestamp: " + targetSentTimestamp + "  author: " + targetAuthor.id)
      if (earlyMessageCacheEntry != null) {
        ApplicationDependencies.getEarlyMessageCache().store(targetAuthor.id, targetSentTimestamp, earlyMessageCacheEntry)
        PushProcessEarlyMessagesJob.enqueue()
      }
      return null
    }

    if (targetMessage.isRemoteDelete) {
      warn(envelope.timestamp, "[handleReaction] Found a matching message, but it's flagged as remotely deleted. timestamp: " + targetSentTimestamp + "  author: " + targetAuthor.id)
      return null
    }

    val targetThread = SignalDatabase.threads.getThreadRecord(targetMessage.threadId)
    if (targetThread == null) {
      warn(envelope.timestamp, "[handleReaction] Could not find a thread for the message! timestamp: " + targetSentTimestamp + "  author: " + targetAuthor.id)
      return null
    }

    val targetThreadRecipientId = targetThread.recipient.id
    val groupRecord = SignalDatabase.groups.getGroup(targetThreadRecipientId).orNull()
    if (groupRecord != null && !groupRecord.members.contains(senderRecipientId)) {
      warn(envelope.timestamp, "[handleReaction] Reaction author is not in the group! timestamp: " + targetSentTimestamp + "  author: " + targetAuthor.id)
      return null
    }

    if (groupRecord == null && senderRecipientId != targetThreadRecipientId && Recipient.self().id != senderRecipientId) {
      warn(envelope.timestamp, "[handleReaction] Reaction author is not a part of the 1:1 thread! timestamp: " + targetSentTimestamp + "  author: " + targetAuthor.id)
      return null
    }

    val targetMessageId = (targetMessage as? MediaMmsMessageRecord)?.latestRevisionId ?: MessageId(targetMessage.id)

    if (isRemove) {
      SignalDatabase.reactions.deleteReaction(targetMessageId, senderRecipientId)
      ApplicationDependencies.getMessageNotifier().updateNotification(context)
    } else {
      val reactionRecord = ReactionRecord(emoji, senderRecipientId, message.timestamp, System.currentTimeMillis())
      SignalDatabase.reactions.addReaction(targetMessageId, reactionRecord)
      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.fromMessageRecord(targetMessage), false)
    }

    return targetMessageId
  }

  // JW: add a reaction to a message. Thanks ClauZ for the implementation
  fun setMessageReaction(context: Context, message: DataMessage, targetMessage: MessageRecord?, reaction: String) {
    if (targetMessage != null) {
      val reactionEmoji = EmojiUtil.getCanonicalRepresentation(reaction)
      val targetMessageId = MessageId(targetMessage.id)
      val reactionRecord = ReactionRecord(reactionEmoji, Recipient.self().id, message.timestamp, System.currentTimeMillis())
      reactions.addReaction(targetMessageId, reactionRecord)
      ApplicationDependencies.getMessageNotifier().updateNotification(context, fromMessageRecord(targetMessage), false)
    }
  }

  fun handleRemoteDelete(context: Context, envelope: Envelope, message: DataMessage, senderRecipientId: RecipientId, earlyMessageCacheEntry: EarlyMessageCacheEntry?): MessageId? {
    log(envelope.timestamp, "Remote delete for message ${message.delete.targetSentTimestamp}")

    val targetSentTimestamp: Long = message.delete.targetSentTimestamp
    val targetMessage: MessageRecord? = SignalDatabase.messages.getMessageFor(targetSentTimestamp, senderRecipientId)

    // JW: set a reaction to indicate the message was attempted to be remote deleted. Sender is myself, emoji is an exclamation.
    if (TextSecurePreferences.isIgnoreRemoteDelete(context)) { setMessageReaction(context, message, targetMessage, "\u2757"); return null; }

    return if (targetMessage != null && MessageConstraintsUtil.isValidRemoteDeleteReceive(targetMessage, senderRecipientId, envelope.serverTimestamp)) {
      SignalDatabase.messages.markAsRemoteDelete(targetMessage.id)
      if (targetMessage.isStory()) {
        SignalDatabase.messages.deleteRemotelyDeletedStory(targetMessage.id)
      }

      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.fromMessageRecord(targetMessage), false)

      MessageId(targetMessage.id)
    } else if (targetMessage == null) {
      warn(envelope.timestamp, "[handleRemoteDelete] Could not find matching message! timestamp: $targetSentTimestamp  author: $senderRecipientId")
      if (earlyMessageCacheEntry != null) {
        ApplicationDependencies.getEarlyMessageCache().store(senderRecipientId, targetSentTimestamp, earlyMessageCacheEntry)
        PushProcessEarlyMessagesJob.enqueue()
      }

      null
    } else {
      warn(envelope.timestamp, "[handleRemoteDelete] Invalid remote delete! deleteTime: ${envelope.serverTimestamp}, targetTime: ${targetMessage.serverTimestamp}, deleteAuthor: $senderRecipientId, targetAuthor: ${targetMessage.fromRecipient.id}")
      null
    }
  }

  /**
   * @param isActivatePaymentsRequest True if payments activation request message.
   * @param isPaymentsActivated       True if payments activated message.
   * @throws StorageFailedException
   */
  @Throws(StorageFailedException::class)
  private fun handlePaymentActivation(
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipientId: RecipientId,
    receivedTime: Long,
    isActivatePaymentsRequest: Boolean,
    isPaymentsActivated: Boolean
  ): MessageId? {
    log(envelope.timestamp, "Payment activation request: $isActivatePaymentsRequest activated: $isPaymentsActivated")
    try {
      val mediaMessage = IncomingMediaMessage(
        from = senderRecipientId,
        sentTimeMillis = envelope.timestamp,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = receivedTime,
        expiresIn = message.expireTimer.seconds.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        serverGuid = envelope.serverGuid,
        isActivatePaymentsRequest = isActivatePaymentsRequest,
        isPaymentsActivated = isPaymentsActivated
      )

      val insertResult: InsertResult? = SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()

      if (insertResult != null) {
        return MessageId(insertResult.messageId)
      }
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    }
    return null
  }

  @Throws(StorageFailedException::class)
  private fun handlePayment(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipientId: RecipientId,
    receivedTime: Long
  ): MessageId? {
    log(envelope.timestamp, "Payment message.")

    if (!message.payment.notification.mobileCoin.hasReceipt()) {
      warn(envelope.timestamp, "Ignoring payment message without notification")
      return null
    }

    val paymentNotification = message.payment.notification
    val uuid = UUID.randomUUID()
    val queue = "Payment_" + PushProcessMessageJob.getQueueName(senderRecipientId)

    try {
      SignalDatabase.payments.createIncomingPayment(
        uuid,
        senderRecipientId,
        message.timestamp,
        paymentNotification.note,
        Money.MobileCoin.ZERO,
        Money.MobileCoin.ZERO,
        paymentNotification.mobileCoin.receipt.toByteArray(),
        true
      )

      val mediaMessage = IncomingMediaMessage(
        from = senderRecipientId,
        body = uuid.toString(),
        sentTimeMillis = envelope.timestamp,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = receivedTime,
        expiresIn = message.expireTimer.seconds.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        serverGuid = envelope.serverGuid,
        isPushMessage = true,
        isPaymentsNotification = true
      )

      val insertResult: InsertResult? = SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()
      if (insertResult != null) {
        val messageId = MessageId(insertResult.messageId)
        ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
        return messageId
      }
    } catch (e: PublicKeyConflictException) {
      warn(envelope.timestamp, "Ignoring payment with public key already in database")
    } catch (e: SerializationException) {
      warn(envelope.timestamp, "Ignoring payment with bad data.", e)
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    } finally {
      ApplicationDependencies.getJobManager()
        .startChain(PaymentTransactionCheckJob(uuid, queue))
        .then(PaymentLedgerUpdateJob.updateLedger())
        .enqueue()
    }

    return null
  }

  @Throws(StorageFailedException::class)
  private fun handleStoryReply(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipientId: RecipientId,
    groupId: GroupId.V2?,
    receivedTime: Long
  ): MessageId? {
    log(envelope.timestamp, "Story reply.")

    val authorServiceId: ServiceId = ServiceId.parseOrThrow(message.storyContext.authorUuid)
    val sentTimestamp = message.storyContext.sentTimestamp

    SignalDatabase.messages.beginTransaction()
    return try {
      val storyAuthorRecipientId = RecipientId.from(authorServiceId)
      val selfId = Recipient.self().id
      val parentStoryId: ParentStoryId
      var quoteModel: QuoteModel? = null
      var expiresInMillis: Duration = 0L.seconds
      var storyMessageId: MessageId? = null

      try {
        if (selfId == storyAuthorRecipientId) {
          storyMessageId = SignalDatabase.storySends.getStoryMessageFor(senderRecipientId, sentTimestamp)
        }

        if (storyMessageId == null) {
          storyMessageId = SignalDatabase.messages.getStoryId(storyAuthorRecipientId, sentTimestamp)
        }

        val story: MmsMessageRecord = SignalDatabase.messages.getMessageRecord(storyMessageId.id) as MmsMessageRecord
        var threadRecipientId: RecipientId = SignalDatabase.threads.getRecipientForThreadId(story.threadId)!!.id
        val groupRecord: GroupRecord? = SignalDatabase.groups.getGroup(threadRecipientId).orNull()
        val groupStory: Boolean = groupRecord?.isActive ?: false

        if (!groupStory) {
          threadRecipientId = senderRecipientId
        }

        handlePossibleExpirationUpdate(envelope, metadata, senderRecipientId, threadRecipientId, groupId, message.expireTimer.seconds, receivedTime)

        if (message.hasGroupContext) {
          parentStoryId = GroupReply(storyMessageId.id)
        } else if (groupStory || SignalDatabase.storySends.canReply(senderRecipientId, sentTimestamp)) {
          parentStoryId = DirectReply(storyMessageId.id)

          var displayText = ""
          var bodyRanges: BodyRangeList? = null
          if (story.storyType.isTextStory) {
            displayText = story.body
            bodyRanges = story.messageRanges
          }

          quoteModel = QuoteModel(sentTimestamp, storyAuthorRecipientId, displayText, false, story.slideDeck.asAttachments(), emptyList(), QuoteModel.Type.NORMAL, bodyRanges)
          expiresInMillis = message.expireTimer.seconds
        } else {
          warn(envelope.timestamp, "Story has replies disabled. Dropping reply.")
          return null
        }
      } catch (e: NoSuchMessageException) {
        warn(envelope.timestamp, "Couldn't find story for reply.", e)
        return null
      }

      val bodyRanges: BodyRangeList? = message.bodyRangesList.filter { it.hasStyle() }.toList().toBodyRangeList()

      val mediaMessage = IncomingMediaMessage(
        from = senderRecipientId,
        sentTimeMillis = envelope.timestamp,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = System.currentTimeMillis(),
        parentStoryId = parentStoryId,
        expiresIn = expiresInMillis.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        body = message.body,
        groupId = groupId,
        quote = quoteModel,
        mentions = getMentions(message.bodyRangesList),
        serverGuid = envelope.serverGuid,
        messageRanges = bodyRanges
      )

      val insertResult: InsertResult? = SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()

      if (insertResult != null) {
        SignalDatabase.messages.setTransactionSuccessful()

        if (parentStoryId.isGroupReply()) {
          ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.fromThreadAndReply(insertResult.threadId, parentStoryId as GroupReply))
        } else {
          ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
          TrimThreadJob.enqueueAsync(insertResult.threadId)
        }

        if (parentStoryId.isDirectReply()) {
          MessageId.fromNullable(insertResult.messageId)
        } else {
          null
        }
      } else {
        warn(envelope.timestamp, "Failed to insert story reply.")
        null
      }
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    } finally {
      SignalDatabase.messages.endTransaction()
    }
  }

  @Throws(StorageFailedException::class)
  private fun handleGiftMessage(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipient: Recipient,
    threadRecipientId: RecipientId,
    receivedTime: Long
  ): MessageId? {
    log(message.timestamp, "Gift message.")

    check(message.giftBadge.hasReceiptCredentialPresentation())

    notifyTypingStoppedFromIncomingMessage(context, senderRecipient, threadRecipientId, metadata.sourceDeviceId)

    val token = ReceiptCredentialPresentation(message.giftBadge.receiptCredentialPresentation.toByteArray()).serialize()
    val giftBadge = GiftBadge.newBuilder()
      .setRedemptionToken(ByteString.copyFrom(token))
      .setRedemptionState(GiftBadge.RedemptionState.PENDING)
      .build()

    val insertResult: InsertResult? = try {
      val mediaMessage = IncomingMediaMessage(
        from = senderRecipient.id,
        sentTimeMillis = envelope.timestamp,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = receivedTime,
        expiresIn = message.expireTimer.seconds.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        body = Base64.encodeBytes(giftBadge.toByteArray()),
        serverGuid = envelope.serverGuid,
        giftBadge = giftBadge
      )

      SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    }

    return if (insertResult != null) {
      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
      TrimThreadJob.enqueueAsync(insertResult.threadId)
      MessageId(insertResult.messageId)
    } else {
      null
    }
  }

  @Throws(StorageFailedException::class)
  private fun handleMediaMessage(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipient: Recipient,
    threadRecipientId: RecipientId,
    groupId: GroupId.V2?,
    receivedTime: Long
  ): MessageId? {
    log(envelope.timestamp, "Media message.")

    notifyTypingStoppedFromIncomingMessage(context, senderRecipient, threadRecipientId, metadata.sourceDeviceId)

    val insertResult: InsertResult?
    val viewOnce: Boolean = if (TextSecurePreferences.isKeepViewOnceMessages(context)) false else message.isViewOnce // JW

    SignalDatabase.messages.beginTransaction()
    try {
      val quote: QuoteModel? = getValidatedQuote(context, envelope.timestamp, message)
      val contacts: List<Contact> = getContacts(message)
      val linkPreviews: List<LinkPreview> = getLinkPreviews(message.previewList, message.body ?: "", false)
      val mentions: List<Mention> = getMentions(message.bodyRangesList)
      val sticker: Attachment? = getStickerAttachment(envelope.timestamp, message)
      val attachments: List<Attachment> = message.attachmentsList.toPointers()
      val messageRanges: BodyRangeList? = if (message.bodyRangesCount > 0) message.bodyRangesList.filter { it.hasStyle() }.toList().toBodyRangeList() else null

      handlePossibleExpirationUpdate(envelope, metadata, senderRecipient.id, threadRecipientId, groupId, message.expireTimer.seconds, receivedTime)

      val mediaMessage = IncomingMediaMessage(
        from = senderRecipient.id,
        sentTimeMillis = envelope.timestamp,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = receivedTime,
        expiresIn = message.expireTimer.seconds.inWholeMilliseconds,
        isViewOnce = viewOnce, // JW
||||||| parent of c5d82267d1 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
        isViewOnce = message.isViewOnce,
=======
        isViewOnce = viewOnce, // JW
>>>>>>> 55729c14e3 (Added extra options)
        isUnidentified = metadata.sealedSender,
        body = message.body.ifEmpty { null },
        groupId = groupId,
        attachments = attachments + if (sticker != null) listOf(sticker) else emptyList(),
        quote = quote,
        sharedContacts = contacts,
        linkPreviews = linkPreviews,
        mentions = mentions,
        serverGuid = envelope.serverGuid,
        messageRanges = messageRanges,
        isPushMessage = true
      )

      insertResult = SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()
      if (insertResult != null) {
        SignalDatabase.messages.setTransactionSuccessful()
      }
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    } finally {
      SignalDatabase.messages.endTransaction()
    }

    return if (insertResult != null) {
      val allAttachments = SignalDatabase.attachments.getAttachmentsForMessage(insertResult.messageId)
      val stickerAttachments = allAttachments.filter { it.isSticker }.toList()
      val attachments = allAttachments.filterNot { it.isSticker }.toList()

      forceStickerDownloadIfNecessary(context, insertResult.messageId, stickerAttachments)

      for (attachment in attachments) {
        ApplicationDependencies.getJobManager().add(AttachmentDownloadJob(insertResult.messageId, attachment.attachmentId, false))
      }

      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
      TrimThreadJob.enqueueAsync(insertResult.threadId)

      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
        ApplicationDependencies.getViewOnceMessageManager().scheduleIfNecessary()
      }

      MessageId(insertResult.messageId)
    } else {
      null
    }
  }

  @Throws(StorageFailedException::class)
  private fun handleTextMessage(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipient: Recipient,
    threadRecipientId: RecipientId,
    groupId: GroupId.V2?,
    receivedTime: Long
  ): MessageId? {
    log(envelope.timestamp, "Text message.")

    val body = if (message.hasBody()) message.body else ""

    handlePossibleExpirationUpdate(envelope, metadata, senderRecipient.id, threadRecipientId, groupId, message.expireTimer.seconds, receivedTime)

    notifyTypingStoppedFromIncomingMessage(context, senderRecipient, threadRecipientId, metadata.sourceDeviceId)

    val textMessage = IncomingTextMessage(
      senderRecipient.id,
      metadata.sourceDeviceId,
      envelope.timestamp,
      envelope.serverTimestamp,
      receivedTime,
      body,
      Optional.ofNullable(groupId),
      message.expireTimer.seconds.inWholeMilliseconds,
      metadata.sealedSender,
      envelope.serverGuid
    )

    val insertResult: InsertResult? = SignalDatabase.messages.insertMessageInbox(IncomingEncryptedMessage(textMessage, body)).orNull()

    return if (insertResult != null) {
      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
      MessageId(insertResult.messageId)
    } else {
      null
    }
  }

  fun handleGroupCallUpdateMessage(
    envelope: Envelope,
    message: DataMessage,
    senderRecipientId: RecipientId,
    groupId: GroupId.V2?
  ) {
    log(envelope.timestamp, "Group call update message.")

    if (groupId == null || !message.hasGroupCallUpdate()) {
      warn(envelope.timestamp, "Invalid group for group call update message")
      return
    }

    val groupRecipientId = SignalDatabase.recipients.getOrInsertFromPossiblyMigratedGroupId(groupId)

    SignalDatabase.calls.insertOrUpdateGroupCallFromExternalEvent(
      groupRecipientId,
      senderRecipientId,
      envelope.serverTimestamp,
      if (message.groupCallUpdate.hasEraId()) message.groupCallUpdate.eraId else null
    )

    GroupCallPeekJob.enqueue(groupRecipientId)
  }

  fun notifyTypingStoppedFromIncomingMessage(context: Context, senderRecipient: Recipient, threadRecipientId: RecipientId, device: Int) {
    val threadId = SignalDatabase.threads.getThreadIdIfExistsFor(threadRecipientId)

    if (threadId > 0 && TextSecurePreferences.isTypingIndicatorsEnabled(context)) {
      debug("Typing stopped on thread $threadId due to an incoming message.")
      ApplicationDependencies.getTypingStatusRepository().onTypingStopped(threadId, senderRecipient, device, true)
    }
  }

  fun getMentions(mentionBodyRanges: List<BodyRange>): List<Mention> {
    return mentionBodyRanges
      .filter { it.hasMentionUuid() }
      .mapNotNull {
        val serviceId = ServiceId.parseOrNull(it.mentionUuid)

        if (serviceId != null) {
          val id = Recipient.externalPush(serviceId).id
          Mention(id, it.start, it.length)
        } else {
          null
        }
      }
  }

  fun forceStickerDownloadIfNecessary(context: Context, messageId: Long, stickerAttachments: List<DatabaseAttachment>) {
    if (stickerAttachments.isEmpty()) {
      return
    }

    val stickerAttachment = stickerAttachments[0]
    if (stickerAttachment.transferState != AttachmentTable.TRANSFER_PROGRESS_DONE) {
      val downloadJob = AttachmentDownloadJob(messageId, stickerAttachment.attachmentId, true)
      try {
        downloadJob.setContext(context)
        downloadJob.doWork()
      } catch (e: Exception) {
        warn("Failed to download sticker inline. Scheduling.")
        ApplicationDependencies.getJobManager().add(downloadJob)
      }
    }
  }

  private fun insertPlaceholder(sender: RecipientId, senderDevice: Int, timestamp: Long, groupId: GroupId?): InsertResult? {
    val textMessage = IncomingTextMessage(
      sender,
      senderDevice,
      timestamp,
      -1,
      System.currentTimeMillis(),
      "",
      groupId.asOptional(),
      0,
      false,
      null
    )
    return SignalDatabase.messages.insertMessageInbox(IncomingEncryptedMessage(textMessage, "")).orNull()
  }

  fun getValidatedQuote(context: Context, timestamp: Long, message: DataMessage): QuoteModel? {
    if (!message.hasQuote()) {
      return null
    }

    val quote: DataMessage.Quote = message.quote

    if (quote.id <= 0) {
      warn(timestamp, "Received quote without an ID! Ignoring...")
      return null
    }

    val authorId = Recipient.externalPush(ServiceId.parseOrThrow(quote.authorUuid)).id
    var quotedMessage = SignalDatabase.messages.getMessageFor(quote.id, authorId) as? MediaMmsMessageRecord

    if (quotedMessage != null && !quotedMessage.isRemoteDelete) {
      log(timestamp, "Found matching message record...")

      val attachments: MutableList<Attachment> = mutableListOf()
      val mentions: MutableList<Mention> = mutableListOf()

      quotedMessage = quotedMessage.withAttachments(context, SignalDatabase.attachments.getAttachmentsForMessage(quotedMessage.id))

      mentions.addAll(SignalDatabase.mentions.getMentionsForMessage(quotedMessage.id))

      if (quotedMessage.isViewOnce) {
        attachments.add(TombstoneAttachment(MediaUtil.VIEW_ONCE, true))
      } else {
        attachments += quotedMessage.slideDeck.asAttachments()

        if (attachments.isEmpty()) {
          attachments += quotedMessage
            .linkPreviews
            .filter { it.thumbnail.isPresent }
            .map { it.thumbnail.get() }
        }
      }

      if (quotedMessage.isPaymentNotification) {
        quotedMessage = SignalDatabase.payments.updateMessageWithPayment(quotedMessage) as MediaMmsMessageRecord
      }

      val body = if (quotedMessage.isPaymentNotification) quotedMessage.getDisplayBody(context).toString() else quotedMessage.body

      return QuoteModel(
        quote.id,
        authorId,
        body,
        false,
        attachments,
        mentions,
        QuoteModel.Type.fromProto(quote.type),
        quotedMessage.messageRanges
      )
    } else if (quotedMessage != null) {
      warn(timestamp, "Found the target for the quote, but it's flagged as remotely deleted.")
    }

    warn(timestamp, "Didn't find matching message record...")
    return QuoteModel(
      quote.id,
      authorId,
      quote.text,
      true,
      quote.attachmentsList.mapNotNull { PointerAttachment.forPointer(it).orNull() },
      getMentions(quote.bodyRangesList),
      QuoteModel.Type.fromProto(quote.type),
      quote.bodyRangesList.filterNot { it.hasMentionUuid() }.toBodyRangeList()
    )
  }

  fun getContacts(message: DataMessage): List<Contact> {
    return message.contactList.map { ContactModelMapper.remoteToLocal(it) }
  }

  fun getLinkPreviews(previews: List<Preview>, body: String, isStoryEmbed: Boolean): List<LinkPreview> {
    if (previews.isEmpty()) {
      return emptyList()
    }

    val urlsInMessage = LinkPreviewUtil.findValidPreviewUrls(body)

    return previews
      .mapNotNull { preview ->
        val thumbnail: Attachment? = preview.image.toPointer()
        val url: Optional<String> = preview.url.toOptional()
        val title: Optional<String> = preview.title.toOptional()
        val description: Optional<String> = preview.description.toOptional()
        val hasTitle = !TextUtils.isEmpty(title.orElse(""))
        val presentInBody = url.isPresent && urlsInMessage.containsUrl(url.get())
        val validDomain = url.isPresent && LinkUtil.isValidPreviewUrl(url.get())

        if (hasTitle && (presentInBody || isStoryEmbed) && validDomain) {
          val linkPreview = LinkPreview(url.get(), title.orElse(""), description.orElse(""), preview.date, thumbnail.toOptional())
          linkPreview
        } else {
          warn(String.format("Discarding an invalid link preview. hasTitle: %b presentInBody: %b isStoryEmbed: %b validDomain: %b", hasTitle, presentInBody, isStoryEmbed, validDomain))
          null
        }
      }
  }

  fun getStickerAttachment(timestamp: Long, message: DataMessage): Attachment? {
    if (!message.hasSticker()) {
      return null
    }

    val sticker = message.sticker
    if (!(message.sticker.hasPackId() && message.sticker.hasPackKey() && message.sticker.hasStickerId() && message.sticker.hasData())) {
      warn(timestamp, "Malformed sticker!")
      return null
    }

    val packId = Hex.toStringCondensed(sticker.packId.toByteArray())
    val packKey = Hex.toStringCondensed(sticker.packKey.toByteArray())
    val stickerId = sticker.stickerId
    val emoji = sticker.emoji
    val stickerLocator = StickerLocator(packId, packKey, stickerId, emoji)

    val stickerRecord = SignalDatabase.stickers.getSticker(stickerLocator.packId, stickerLocator.stickerId, false)

    return if (stickerRecord != null) {
      UriAttachment(
        stickerRecord.uri,
        stickerRecord.contentType,
        AttachmentTable.TRANSFER_PROGRESS_DONE,
        stickerRecord.size,
        StickerSlide.WIDTH,
        StickerSlide.HEIGHT,
        null,
        SecureRandom().nextLong().toString(),
        false,
        false,
        false,
        false,
        null,
        stickerLocator,
        null,
        null,
        null
      )
    } else {
      sticker.data.toPointer(stickerLocator)
    }
  }
}
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
package org.thoughtcrime.securesms.messages

import android.content.Context
import android.text.TextUtils
import com.google.protobuf.ByteString
import com.mobilecoin.lib.exceptions.SerializationException
import org.signal.core.util.Hex
import org.signal.core.util.concurrent.SignalExecutors
import org.signal.core.util.logging.Log
import org.signal.core.util.orNull
import org.signal.core.util.toOptional
import org.signal.libsignal.zkgroup.receipts.ReceiptCredentialPresentation
import org.thoughtcrime.securesms.attachments.Attachment
import org.thoughtcrime.securesms.attachments.DatabaseAttachment
import org.thoughtcrime.securesms.attachments.PointerAttachment
import org.thoughtcrime.securesms.attachments.TombstoneAttachment
import org.thoughtcrime.securesms.attachments.UriAttachment
import org.thoughtcrime.securesms.components.emoji.EmojiUtil
import org.thoughtcrime.securesms.contactshare.Contact
import org.thoughtcrime.securesms.contactshare.ContactModelMapper
import org.thoughtcrime.securesms.crypto.ProfileKeyUtil
import org.thoughtcrime.securesms.crypto.SecurityEvent
import org.thoughtcrime.securesms.database.AttachmentTable
import org.thoughtcrime.securesms.database.MessageTable.InsertResult
import org.thoughtcrime.securesms.database.NoSuchMessageException
import org.thoughtcrime.securesms.database.PaymentTable.PublicKeyConflictException
import org.thoughtcrime.securesms.database.SignalDatabase
import org.thoughtcrime.securesms.database.SignalDatabase.Companion.reactions // JW
import org.thoughtcrime.securesms.database.model.GroupRecord
import org.thoughtcrime.securesms.database.model.MediaMmsMessageRecord
import org.thoughtcrime.securesms.database.model.Mention
import org.thoughtcrime.securesms.database.model.MessageId
import org.thoughtcrime.securesms.database.model.MessageRecord
import org.thoughtcrime.securesms.database.model.MmsMessageRecord
import org.thoughtcrime.securesms.database.model.ParentStoryId
import org.thoughtcrime.securesms.database.model.ParentStoryId.DirectReply
import org.thoughtcrime.securesms.database.model.ParentStoryId.GroupReply
import org.thoughtcrime.securesms.database.model.ReactionRecord
import org.thoughtcrime.securesms.database.model.databaseprotos.BodyRangeList
import org.thoughtcrime.securesms.database.model.databaseprotos.GiftBadge
import org.thoughtcrime.securesms.database.model.toBodyRangeList
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies
import org.thoughtcrime.securesms.groups.BadGroupIdException
import org.thoughtcrime.securesms.groups.GroupId
import org.thoughtcrime.securesms.jobs.AttachmentDownloadJob
import org.thoughtcrime.securesms.jobs.GroupCallPeekJob
import org.thoughtcrime.securesms.jobs.GroupV2UpdateSelfProfileKeyJob
import org.thoughtcrime.securesms.jobs.PaymentLedgerUpdateJob
import org.thoughtcrime.securesms.jobs.PaymentTransactionCheckJob
import org.thoughtcrime.securesms.jobs.ProfileKeySendJob
import org.thoughtcrime.securesms.jobs.PushProcessEarlyMessagesJob
import org.thoughtcrime.securesms.jobs.PushProcessMessageJob
import org.thoughtcrime.securesms.jobs.RefreshAttributesJob
import org.thoughtcrime.securesms.jobs.RetrieveProfileJob
import org.thoughtcrime.securesms.jobs.SendDeliveryReceiptJob
import org.thoughtcrime.securesms.jobs.TrimThreadJob
import org.thoughtcrime.securesms.linkpreview.LinkPreview
import org.thoughtcrime.securesms.linkpreview.LinkPreviewUtil
import org.thoughtcrime.securesms.messages.MessageContentProcessor.StorageFailedException
import org.thoughtcrime.securesms.messages.MessageContentProcessorV2.Companion.debug
import org.thoughtcrime.securesms.messages.MessageContentProcessorV2.Companion.log
import org.thoughtcrime.securesms.messages.MessageContentProcessorV2.Companion.warn
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.groupMasterKey
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.hasGroupContext
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.hasRemoteDelete
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isEndSession
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isExpirationUpdate
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isInvalid
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isMediaMessage
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isPaymentActivated
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isPaymentActivationRequest
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.isStoryReaction
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.toPointer
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil.toPointers
import org.thoughtcrime.securesms.mms.IncomingMediaMessage
import org.thoughtcrime.securesms.mms.MmsException
import org.thoughtcrime.securesms.mms.QuoteModel
import org.thoughtcrime.securesms.mms.StickerSlide
import org.thoughtcrime.securesms.notifications.v2.ConversationId
import org.thoughtcrime.securesms.notifications.v2.ConversationId.Companion.fromMessageRecord // JW
import org.thoughtcrime.securesms.recipients.Recipient
import org.thoughtcrime.securesms.recipients.RecipientId
import org.thoughtcrime.securesms.recipients.RecipientUtil
import org.thoughtcrime.securesms.sms.IncomingEncryptedMessage
import org.thoughtcrime.securesms.sms.IncomingEndSessionMessage
import org.thoughtcrime.securesms.sms.IncomingTextMessage
import org.thoughtcrime.securesms.stickers.StickerLocator
import org.thoughtcrime.securesms.storage.StorageSyncHelper
import org.thoughtcrime.securesms.util.Base64
import org.thoughtcrime.securesms.util.EarlyMessageCacheEntry
import org.thoughtcrime.securesms.util.LinkUtil
import org.thoughtcrime.securesms.util.MediaUtil
import org.thoughtcrime.securesms.util.MessageConstraintsUtil
import org.thoughtcrime.securesms.util.TextSecurePreferences
import org.thoughtcrime.securesms.util.isStory
import org.whispersystems.signalservice.api.crypto.EnvelopeMetadata
import org.whispersystems.signalservice.api.payments.Money
import org.whispersystems.signalservice.api.push.ServiceId
import org.whispersystems.signalservice.api.util.OptionalUtil.asOptional
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.BodyRange
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.Content
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.DataMessage
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.Envelope
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.GroupContextV2
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.Preview
import java.security.SecureRandom
import java.util.Optional
import java.util.UUID
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

object DataMessageProcessor {

  fun process(
    context: Context,
    senderRecipient: Recipient,
    threadRecipient: Recipient,
    envelope: Envelope,
    content: Content,
    metadata: EnvelopeMetadata,
    receivedTime: Long,
    earlyMessageCacheEntry: EarlyMessageCacheEntry?
  ) {
    val message: DataMessage = content.dataMessage
    val groupId: GroupId.V2? = if (message.hasGroupContext) GroupId.v2(message.groupV2.groupMasterKey) else null

    if (groupId != null) {
      if (MessageContentProcessorV2.handleGv2PreProcessing(context, envelope.timestamp, content, metadata, groupId, message.groupV2, senderRecipient)) {
        return
      }
    }

    var messageId: MessageId? = null
    when {
      message.isInvalid -> handleInvalidMessage(context, senderRecipient.id, metadata.sourceDeviceId, groupId, envelope.timestamp)
      message.isEndSession -> messageId = handleEndSessionMessage(context, senderRecipient.id, envelope, metadata)
      message.isExpirationUpdate -> messageId = handleExpirationUpdate(envelope, metadata, senderRecipient.id, threadRecipient.id, groupId, message.expireTimer.seconds, receivedTime, false)
      message.isStoryReaction -> messageId = handleStoryReaction(context, envelope, metadata, message, senderRecipient.id, groupId)
      message.hasReaction() -> messageId = handleReaction(context, envelope, message, senderRecipient.id, earlyMessageCacheEntry)
      message.hasRemoteDelete -> messageId = handleRemoteDelete(context, envelope, message, senderRecipient.id, earlyMessageCacheEntry)
      message.isPaymentActivationRequest -> messageId = handlePaymentActivation(envelope, metadata, message, senderRecipient.id, receivedTime, isActivatePaymentsRequest = true, isPaymentsActivated = false)
      message.isPaymentActivated -> messageId = handlePaymentActivation(envelope, metadata, message, senderRecipient.id, receivedTime, isActivatePaymentsRequest = false, isPaymentsActivated = true)
      message.hasPayment() -> messageId = handlePayment(context, envelope, metadata, message, senderRecipient.id, receivedTime)
      message.hasStoryContext() -> messageId = handleStoryReply(context, envelope, metadata, message, senderRecipient.id, groupId, receivedTime)
      message.hasGiftBadge() -> messageId = handleGiftMessage(context, envelope, metadata, message, senderRecipient, threadRecipient.id, receivedTime)
      message.isMediaMessage -> messageId = handleMediaMessage(context, envelope, metadata, message, senderRecipient, threadRecipient.id, groupId, receivedTime)
      message.hasBody() -> messageId = handleTextMessage(context, envelope, metadata, message, senderRecipient, threadRecipient.id, groupId, receivedTime)
      message.hasGroupCallUpdate() -> handleGroupCallUpdateMessage(envelope, message, senderRecipient.id, groupId)
    }

    if (groupId != null && SignalDatabase.groups.isUnknownGroup(groupId)) {
      handleUnknownGroupMessage(envelope.timestamp, message.groupV2)
    }

    if (message.hasProfileKey()) {
      handleProfileKey(envelope.timestamp, message.profileKey.toByteArray(), senderRecipient)
    }

    if (metadata.sealedSender && messageId != null) {
      SignalExecutors.BOUNDED.execute { ApplicationDependencies.getJobManager().add(SendDeliveryReceiptJob(senderRecipient.id, message.timestamp, messageId)) }
    } else if (!metadata.sealedSender) {
      if (RecipientUtil.shouldHaveProfileKey(threadRecipient)) {
        Log.w(MessageContentProcessorV2.TAG, "Received an unsealed sender message from " + senderRecipient.id + ", but they should already have our profile key. Correcting.")

        if (groupId != null) {
          Log.i(MessageContentProcessorV2.TAG, "Message was to a GV2 group. Ensuring our group profile keys are up to date.")
          ApplicationDependencies
            .getJobManager()
            .startChain(RefreshAttributesJob(false))
            .then(GroupV2UpdateSelfProfileKeyJob.withQueueLimits(groupId))
            .enqueue()
        } else if (!threadRecipient.isGroup) {
          Log.i(MessageContentProcessorV2.TAG, "Message was to a 1:1. Ensuring this user has our profile key.")
          val profileSendJob = ProfileKeySendJob.create(SignalDatabase.threads.getOrCreateThreadIdFor(threadRecipient), true)
          if (profileSendJob != null) {
            ApplicationDependencies
              .getJobManager()
              .startChain(RefreshAttributesJob(false))
              .then(profileSendJob)
              .enqueue()
          }
        }
      }
    }
  }

  private fun handleProfileKey(
    timestamp: Long,
    messageProfileKeyBytes: ByteArray,
    senderRecipient: Recipient
  ) {
    val messageProfileKey = ProfileKeyUtil.profileKeyOrNull(messageProfileKeyBytes)

    if (senderRecipient.isSelf) {
      if (ProfileKeyUtil.getSelfProfileKey() != messageProfileKey) {
        warn(timestamp, "Saw a sync message whose profile key doesn't match our records. Scheduling a storage sync to check.")
        StorageSyncHelper.scheduleSyncForDataChange()
      }
    } else if (messageProfileKey != null) {
      if (SignalDatabase.recipients.setProfileKey(senderRecipient.id, messageProfileKey)) {
        log(timestamp, "Profile key on message from " + senderRecipient.id + " didn't match our local store. It has been updated.")
        ApplicationDependencies.getJobManager().add(RetrieveProfileJob.forRecipient(senderRecipient.id))
      }
    } else {
      warn(timestamp.toString(), "Ignored invalid profile key seen in message")
    }
  }

  @Throws(BadGroupIdException::class)
  fun handleUnknownGroupMessage(timestamp: Long, groupContextV2: GroupContextV2) {
    log(timestamp, "Unknown group message.")
    warn(timestamp, "Received a GV2 message for a group we have no knowledge of -- attempting to fix this state.")
    SignalDatabase.groups.fixMissingMasterKey(groupContextV2.groupMasterKey)
  }

  private fun handleInvalidMessage(
    context: Context,
    sender: RecipientId,
    senderDevice: Int,
    groupId: GroupId?,
    timestamp: Long
  ) {
    log(timestamp, "Invalid message.")

    val insertResult: InsertResult? = insertPlaceholder(sender, senderDevice, timestamp, groupId)
    if (insertResult != null) {
      SignalDatabase.messages.markAsInvalidMessage(insertResult.messageId)
      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
    }
  }

  private fun handleEndSessionMessage(
    context: Context,
    senderRecipientId: RecipientId,
    envelope: Envelope,
    metadata: EnvelopeMetadata
  ): MessageId? {
    log(envelope.timestamp, "End session message.")

    val incomingTextMessage = IncomingTextMessage(
      senderRecipientId,
      metadata.sourceDeviceId,
      envelope.timestamp,
      envelope.serverTimestamp,
      System.currentTimeMillis(),
      "",
      Optional.empty(),
      0,
      metadata.sealedSender,
      envelope.serverGuid
    )

    val insertResult: InsertResult? = SignalDatabase.messages.insertMessageInbox(IncomingEndSessionMessage(incomingTextMessage)).orNull()

    return if (insertResult != null) {
      ApplicationDependencies.getProtocolStore().aci().deleteAllSessions(metadata.sourceServiceId.toString())
      SecurityEvent.broadcastSecurityUpdateEvent(context)
      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
      MessageId(insertResult.messageId)
    } else {
      null
    }
  }

  /**
   * @param sideEffect True if the event is side effect of a different message, false if the message itself was an expiration update.
   * @throws StorageFailedException
   */
  @Throws(StorageFailedException::class)
  private fun handleExpirationUpdate(
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    senderRecipientId: RecipientId,
    threadRecipientId: RecipientId,
    groupId: GroupId.V2?,
    expiresIn: Duration,
    receivedTime: Long,
    sideEffect: Boolean
  ): MessageId? {
    log(envelope.timestamp, "Expiration update. Side effect: $sideEffect")

    if (groupId != null) {
      warn(envelope.timestamp, "Expiration update received for GV2. Ignoring.")
      return null
    }

    if (SignalDatabase.recipients.getExpiresInSeconds(threadRecipientId) == expiresIn.inWholeSeconds) {
      log(envelope.timestamp, "No change in message expiry for group. Ignoring.")
      return null
    }

    try {
      val mediaMessage = IncomingMediaMessage(
        from = senderRecipientId,
        sentTimeMillis = envelope.timestamp - if (sideEffect) 1 else 0,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = receivedTime,
        expiresIn = expiresIn.inWholeMilliseconds,
        isExpirationUpdate = true,
        isUnidentified = metadata.sealedSender,
        serverGuid = envelope.serverGuid,
        isPushMessage = true
      )

      val insertResult: InsertResult? = SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()
      SignalDatabase.recipients.setExpireMessages(threadRecipientId, expiresIn.inWholeSeconds.toInt())

      if (insertResult != null) {
        return MessageId(insertResult.messageId)
      }
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    }

    return null
  }

  /**
   * Inserts an expiration update if the message timer doesn't match the thread timer.
   */
  @Throws(StorageFailedException::class)
  fun handlePossibleExpirationUpdate(
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    senderRecipientId: RecipientId,
    threadRecipientId: RecipientId,
    groupId: GroupId.V2?,
    expiresIn: Duration,
    receivedTime: Long
  ) {
    if (SignalDatabase.recipients.getExpiresInSeconds(threadRecipientId) != expiresIn.inWholeSeconds) {
      warn(envelope.timestamp, "Message expire time didn't match thread expire time. Handling timer update.")
      handleExpirationUpdate(envelope, metadata, senderRecipientId, threadRecipientId, groupId, expiresIn, receivedTime, true)
    }
  }

  @Throws(StorageFailedException::class)
  private fun handleStoryReaction(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipientId: RecipientId,
    groupId: GroupId.V2?
  ): MessageId? {
    log(envelope.timestamp, "Story reaction.")

    val emoji = message.reaction.emoji
    if (!EmojiUtil.isEmoji(emoji)) {
      warn(envelope.timestamp, "Story reaction text is not a valid emoji! Ignoring the message.")
      return null
    }

    val authorServiceId: ServiceId = ServiceId.parseOrThrow(message.storyContext.authorUuid)
    val sentTimestamp = message.storyContext.sentTimestamp

    SignalDatabase.messages.beginTransaction()
    return try {
      val authorRecipientId = RecipientId.from(authorServiceId)
      val parentStoryId: ParentStoryId
      var quoteModel: QuoteModel? = null
      var expiresIn: Duration = 0L.seconds

      try {
        val storyId = SignalDatabase.messages.getStoryId(authorRecipientId, sentTimestamp).id

        if (groupId != null) {
          parentStoryId = GroupReply(storyId)
        } else if (SignalDatabase.storySends.canReply(senderRecipientId, sentTimestamp)) {
          val story = SignalDatabase.messages.getMessageRecord(storyId) as MmsMessageRecord
          var displayText = ""
          var bodyRanges: BodyRangeList? = null

          if (story.storyType.isTextStory) {
            displayText = story.body
            bodyRanges = story.messageRanges
          }

          parentStoryId = DirectReply(storyId)
          quoteModel = QuoteModel(sentTimestamp, authorRecipientId, displayText, false, story.slideDeck.asAttachments(), emptyList(), QuoteModel.Type.NORMAL, bodyRanges)
          expiresIn = message.expireTimer.seconds
        } else {
          warn(envelope.timestamp, "Story has reactions disabled. Dropping reaction.")
          return null
        }
      } catch (e: NoSuchMessageException) {
        warn(envelope.timestamp, "Couldn't find story for reaction.", e)
        return null
      }

      val mediaMessage = IncomingMediaMessage(
        from = senderRecipientId,
        sentTimeMillis = envelope.timestamp,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = System.currentTimeMillis(),
        parentStoryId = parentStoryId,
        isStoryReaction = true,
        expiresIn = expiresIn.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        body = emoji,
        groupId = groupId,
        quote = quoteModel,
        serverGuid = envelope.serverGuid
      )

      val insertResult: InsertResult? = SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()
      if (insertResult != null) {
        SignalDatabase.messages.setTransactionSuccessful()

        if (parentStoryId.isGroupReply()) {
          ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.fromThreadAndReply(insertResult.threadId, parentStoryId as GroupReply))
        } else {
          ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
          TrimThreadJob.enqueueAsync(insertResult.threadId)
        }

        if (parentStoryId.isDirectReply()) {
          MessageId(insertResult.messageId)
        } else {
          null
        }
      } else {
        warn(envelope.timestamp, "Failed to insert story reaction")
        null
      }
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    } finally {
      SignalDatabase.messages.endTransaction()
    }
  }

  @Throws(StorageFailedException::class)
  fun handleReaction(
    context: Context,
    envelope: Envelope,
    message: DataMessage,
    senderRecipientId: RecipientId,
    earlyMessageCacheEntry: EarlyMessageCacheEntry?
  ): MessageId? {
    log(envelope.timestamp, "Handle reaction for message " + message.reaction.targetSentTimestamp)

    val emoji: String = message.reaction.emoji
    val isRemove: Boolean = message.reaction.remove
    val targetAuthorServiceId: ServiceId = ServiceId.parseOrThrow(message.reaction.targetAuthorUuid)
    val targetSentTimestamp = message.reaction.targetSentTimestamp

    if (!EmojiUtil.isEmoji(emoji)) {
      warn(envelope.timestamp, "Reaction text is not a valid emoji! Ignoring the message.")
      return null
    }

    val targetAuthor = Recipient.externalPush(targetAuthorServiceId)
    val targetMessage = SignalDatabase.messages.getMessageFor(targetSentTimestamp, targetAuthor.id)
    if (targetMessage == null) {
      warn(envelope.timestamp, "[handleReaction] Could not find matching message! Putting it in the early message cache. timestamp: " + targetSentTimestamp + "  author: " + targetAuthor.id)
      if (earlyMessageCacheEntry != null) {
        ApplicationDependencies.getEarlyMessageCache().store(targetAuthor.id, targetSentTimestamp, earlyMessageCacheEntry)
        PushProcessEarlyMessagesJob.enqueue()
      }
      return null
    }

    if (targetMessage.isRemoteDelete) {
      warn(envelope.timestamp, "[handleReaction] Found a matching message, but it's flagged as remotely deleted. timestamp: " + targetSentTimestamp + "  author: " + targetAuthor.id)
      return null
    }

    val targetThread = SignalDatabase.threads.getThreadRecord(targetMessage.threadId)
    if (targetThread == null) {
      warn(envelope.timestamp, "[handleReaction] Could not find a thread for the message! timestamp: " + targetSentTimestamp + "  author: " + targetAuthor.id)
      return null
    }

    val targetThreadRecipientId = targetThread.recipient.id
    val groupRecord = SignalDatabase.groups.getGroup(targetThreadRecipientId).orNull()
    if (groupRecord != null && !groupRecord.members.contains(senderRecipientId)) {
      warn(envelope.timestamp, "[handleReaction] Reaction author is not in the group! timestamp: " + targetSentTimestamp + "  author: " + targetAuthor.id)
      return null
    }

    if (groupRecord == null && senderRecipientId != targetThreadRecipientId && Recipient.self().id != senderRecipientId) {
      warn(envelope.timestamp, "[handleReaction] Reaction author is not a part of the 1:1 thread! timestamp: " + targetSentTimestamp + "  author: " + targetAuthor.id)
      return null
    }

    val targetMessageId = (targetMessage as? MediaMmsMessageRecord)?.latestRevisionId ?: MessageId(targetMessage.id)

    if (isRemove) {
      SignalDatabase.reactions.deleteReaction(targetMessageId, senderRecipientId)
      ApplicationDependencies.getMessageNotifier().updateNotification(context)
    } else {
      val reactionRecord = ReactionRecord(emoji, senderRecipientId, message.timestamp, System.currentTimeMillis())
      SignalDatabase.reactions.addReaction(targetMessageId, reactionRecord)
      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.fromMessageRecord(targetMessage), false)
    }

    return targetMessageId
  }

  // JW: add a reaction to a message. Thanks ClauZ for the implementation
  fun setMessageReaction(context: Context, message: DataMessage, targetMessage: MessageRecord?, reaction: String) {
    if (targetMessage != null) {
      val reactionEmoji = EmojiUtil.getCanonicalRepresentation(reaction)
      val targetMessageId = MessageId(targetMessage.id)
      val reactionRecord = ReactionRecord(reactionEmoji, Recipient.self().id, message.timestamp, System.currentTimeMillis())
      reactions.addReaction(targetMessageId, reactionRecord)
      ApplicationDependencies.getMessageNotifier().updateNotification(context, fromMessageRecord(targetMessage), false)
    }
  }

  fun handleRemoteDelete(context: Context, envelope: Envelope, message: DataMessage, senderRecipientId: RecipientId, earlyMessageCacheEntry: EarlyMessageCacheEntry?): MessageId? {
    log(envelope.timestamp, "Remote delete for message ${message.delete.targetSentTimestamp}")

    val targetSentTimestamp: Long = message.delete.targetSentTimestamp
    val targetMessage: MessageRecord? = SignalDatabase.messages.getMessageFor(targetSentTimestamp, senderRecipientId)

    // JW: set a reaction to indicate the message was attempted to be remote deleted. Sender is myself, emoji is an exclamation.
    if (TextSecurePreferences.isIgnoreRemoteDelete(context)) { setMessageReaction(context, message, targetMessage, "\u2757"); return null; }

    return if (targetMessage != null && MessageConstraintsUtil.isValidRemoteDeleteReceive(targetMessage, senderRecipientId, envelope.serverTimestamp)) {
      SignalDatabase.messages.markAsRemoteDelete(targetMessage.id)
      if (targetMessage.isStory()) {
        SignalDatabase.messages.deleteRemotelyDeletedStory(targetMessage.id)
      }

      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.fromMessageRecord(targetMessage), false)

      MessageId(targetMessage.id)
    } else if (targetMessage == null) {
      warn(envelope.timestamp, "[handleRemoteDelete] Could not find matching message! timestamp: $targetSentTimestamp  author: $senderRecipientId")
      if (earlyMessageCacheEntry != null) {
        ApplicationDependencies.getEarlyMessageCache().store(senderRecipientId, targetSentTimestamp, earlyMessageCacheEntry)
        PushProcessEarlyMessagesJob.enqueue()
      }

      null
    } else {
      warn(envelope.timestamp, "[handleRemoteDelete] Invalid remote delete! deleteTime: ${envelope.serverTimestamp}, targetTime: ${targetMessage.serverTimestamp}, deleteAuthor: $senderRecipientId, targetAuthor: ${targetMessage.fromRecipient.id}")
      null
    }
  }

  /**
   * @param isActivatePaymentsRequest True if payments activation request message.
   * @param isPaymentsActivated       True if payments activated message.
   * @throws StorageFailedException
   */
  @Throws(StorageFailedException::class)
  private fun handlePaymentActivation(
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipientId: RecipientId,
    receivedTime: Long,
    isActivatePaymentsRequest: Boolean,
    isPaymentsActivated: Boolean
  ): MessageId? {
    log(envelope.timestamp, "Payment activation request: $isActivatePaymentsRequest activated: $isPaymentsActivated")
    try {
      val mediaMessage = IncomingMediaMessage(
        from = senderRecipientId,
        sentTimeMillis = envelope.timestamp,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = receivedTime,
        expiresIn = message.expireTimer.seconds.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        serverGuid = envelope.serverGuid,
        isActivatePaymentsRequest = isActivatePaymentsRequest,
        isPaymentsActivated = isPaymentsActivated
      )

      val insertResult: InsertResult? = SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()

      if (insertResult != null) {
        return MessageId(insertResult.messageId)
      }
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    }
    return null
  }

  @Throws(StorageFailedException::class)
  private fun handlePayment(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipientId: RecipientId,
    receivedTime: Long
  ): MessageId? {
    log(envelope.timestamp, "Payment message.")

    if (!message.payment.notification.mobileCoin.hasReceipt()) {
      warn(envelope.timestamp, "Ignoring payment message without notification")
      return null
    }

    val paymentNotification = message.payment.notification
    val uuid = UUID.randomUUID()
    val queue = "Payment_" + PushProcessMessageJob.getQueueName(senderRecipientId)

    try {
      SignalDatabase.payments.createIncomingPayment(
        uuid,
        senderRecipientId,
        message.timestamp,
        paymentNotification.note,
        Money.MobileCoin.ZERO,
        Money.MobileCoin.ZERO,
        paymentNotification.mobileCoin.receipt.toByteArray(),
        true
      )

      val mediaMessage = IncomingMediaMessage(
        from = senderRecipientId,
        body = uuid.toString(),
        sentTimeMillis = envelope.timestamp,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = receivedTime,
        expiresIn = message.expireTimer.seconds.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        serverGuid = envelope.serverGuid,
        isPushMessage = true,
        isPaymentsNotification = true
      )

      val insertResult: InsertResult? = SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()
      if (insertResult != null) {
        val messageId = MessageId(insertResult.messageId)
        ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
        return messageId
      }
    } catch (e: PublicKeyConflictException) {
      warn(envelope.timestamp, "Ignoring payment with public key already in database")
    } catch (e: SerializationException) {
      warn(envelope.timestamp, "Ignoring payment with bad data.", e)
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    } finally {
      ApplicationDependencies.getJobManager()
        .startChain(PaymentTransactionCheckJob(uuid, queue))
        .then(PaymentLedgerUpdateJob.updateLedger())
        .enqueue()
    }

    return null
  }

  @Throws(StorageFailedException::class)
  private fun handleStoryReply(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipientId: RecipientId,
    groupId: GroupId.V2?,
    receivedTime: Long
  ): MessageId? {
    log(envelope.timestamp, "Story reply.")

    val authorServiceId: ServiceId = ServiceId.parseOrThrow(message.storyContext.authorUuid)
    val sentTimestamp = message.storyContext.sentTimestamp

    SignalDatabase.messages.beginTransaction()
    return try {
      val storyAuthorRecipientId = RecipientId.from(authorServiceId)
      val selfId = Recipient.self().id
      val parentStoryId: ParentStoryId
      var quoteModel: QuoteModel? = null
      var expiresInMillis: Duration = 0L.seconds
      var storyMessageId: MessageId? = null

      try {
        if (selfId == storyAuthorRecipientId) {
          storyMessageId = SignalDatabase.storySends.getStoryMessageFor(senderRecipientId, sentTimestamp)
        }

        if (storyMessageId == null) {
          storyMessageId = SignalDatabase.messages.getStoryId(storyAuthorRecipientId, sentTimestamp)
        }

        val story: MmsMessageRecord = SignalDatabase.messages.getMessageRecord(storyMessageId.id) as MmsMessageRecord
        var threadRecipientId: RecipientId = SignalDatabase.threads.getRecipientForThreadId(story.threadId)!!.id
        val groupRecord: GroupRecord? = SignalDatabase.groups.getGroup(threadRecipientId).orNull()
        val groupStory: Boolean = groupRecord?.isActive ?: false

        if (!groupStory) {
          threadRecipientId = senderRecipientId
        }

        handlePossibleExpirationUpdate(envelope, metadata, senderRecipientId, threadRecipientId, groupId, message.expireTimer.seconds, receivedTime)

        if (message.hasGroupContext) {
          parentStoryId = GroupReply(storyMessageId.id)
        } else if (groupStory || SignalDatabase.storySends.canReply(senderRecipientId, sentTimestamp)) {
          parentStoryId = DirectReply(storyMessageId.id)

          var displayText = ""
          var bodyRanges: BodyRangeList? = null
          if (story.storyType.isTextStory) {
            displayText = story.body
            bodyRanges = story.messageRanges
          }

          quoteModel = QuoteModel(sentTimestamp, storyAuthorRecipientId, displayText, false, story.slideDeck.asAttachments(), emptyList(), QuoteModel.Type.NORMAL, bodyRanges)
          expiresInMillis = message.expireTimer.seconds
        } else {
          warn(envelope.timestamp, "Story has replies disabled. Dropping reply.")
          return null
        }
      } catch (e: NoSuchMessageException) {
        warn(envelope.timestamp, "Couldn't find story for reply.", e)
        return null
      }

      val bodyRanges: BodyRangeList? = message.bodyRangesList.filter { it.hasStyle() }.toList().toBodyRangeList()

      val mediaMessage = IncomingMediaMessage(
        from = senderRecipientId,
        sentTimeMillis = envelope.timestamp,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = System.currentTimeMillis(),
        parentStoryId = parentStoryId,
        expiresIn = expiresInMillis.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        body = message.body,
        groupId = groupId,
        quote = quoteModel,
        mentions = getMentions(message.bodyRangesList),
        serverGuid = envelope.serverGuid,
        messageRanges = bodyRanges
      )

      val insertResult: InsertResult? = SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()

      if (insertResult != null) {
        SignalDatabase.messages.setTransactionSuccessful()

        if (parentStoryId.isGroupReply()) {
          ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.fromThreadAndReply(insertResult.threadId, parentStoryId as GroupReply))
        } else {
          ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
          TrimThreadJob.enqueueAsync(insertResult.threadId)
        }

        if (parentStoryId.isDirectReply()) {
          MessageId.fromNullable(insertResult.messageId)
        } else {
          null
        }
      } else {
        warn(envelope.timestamp, "Failed to insert story reply.")
        null
      }
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    } finally {
      SignalDatabase.messages.endTransaction()
    }
  }

  @Throws(StorageFailedException::class)
  private fun handleGiftMessage(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipient: Recipient,
    threadRecipientId: RecipientId,
    receivedTime: Long
  ): MessageId? {
    log(message.timestamp, "Gift message.")

    check(message.giftBadge.hasReceiptCredentialPresentation())

    notifyTypingStoppedFromIncomingMessage(context, senderRecipient, threadRecipientId, metadata.sourceDeviceId)

    val token = ReceiptCredentialPresentation(message.giftBadge.receiptCredentialPresentation.toByteArray()).serialize()
    val giftBadge = GiftBadge.newBuilder()
      .setRedemptionToken(ByteString.copyFrom(token))
      .setRedemptionState(GiftBadge.RedemptionState.PENDING)
      .build()

    val insertResult: InsertResult? = try {
      val mediaMessage = IncomingMediaMessage(
        from = senderRecipient.id,
        sentTimeMillis = envelope.timestamp,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = receivedTime,
        expiresIn = message.expireTimer.seconds.inWholeMilliseconds,
        isUnidentified = metadata.sealedSender,
        body = Base64.encodeBytes(giftBadge.toByteArray()),
        serverGuid = envelope.serverGuid,
        giftBadge = giftBadge
      )

      SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    }

    return if (insertResult != null) {
      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
      TrimThreadJob.enqueueAsync(insertResult.threadId)
      MessageId(insertResult.messageId)
    } else {
      null
    }
  }

  @Throws(StorageFailedException::class)
  private fun handleMediaMessage(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipient: Recipient,
    threadRecipientId: RecipientId,
    groupId: GroupId.V2?,
    receivedTime: Long
  ): MessageId? {
    log(envelope.timestamp, "Media message.")

    notifyTypingStoppedFromIncomingMessage(context, senderRecipient, threadRecipientId, metadata.sourceDeviceId)

    val insertResult: InsertResult?
    val viewOnce: Boolean = if (TextSecurePreferences.isKeepViewOnceMessages(context)) false else message.isViewOnce // JW

    SignalDatabase.messages.beginTransaction()
    try {
      val quote: QuoteModel? = getValidatedQuote(context, envelope.timestamp, message)
      val contacts: List<Contact> = getContacts(message)
      val linkPreviews: List<LinkPreview> = getLinkPreviews(message.previewList, message.body ?: "", false)
      val mentions: List<Mention> = getMentions(message.bodyRangesList)
      val sticker: Attachment? = getStickerAttachment(envelope.timestamp, message)
      val attachments: List<Attachment> = message.attachmentsList.toPointers()
      val messageRanges: BodyRangeList? = if (message.bodyRangesCount > 0) message.bodyRangesList.filter { it.hasStyle() }.toList().toBodyRangeList() else null

      handlePossibleExpirationUpdate(envelope, metadata, senderRecipient.id, threadRecipientId, groupId, message.expireTimer.seconds, receivedTime)

      val mediaMessage = IncomingMediaMessage(
        from = senderRecipient.id,
        sentTimeMillis = envelope.timestamp,
        serverTimeMillis = envelope.serverTimestamp,
        receivedTimeMillis = receivedTime,
        expiresIn = message.expireTimer.seconds.inWholeMilliseconds,
        isViewOnce = viewOnce, // JW
        isUnidentified = metadata.sealedSender,
        body = message.body.ifEmpty { null },
        groupId = groupId,
        attachments = attachments + if (sticker != null) listOf(sticker) else emptyList(),
        quote = quote,
        sharedContacts = contacts,
        linkPreviews = linkPreviews,
        mentions = mentions,
        serverGuid = envelope.serverGuid,
        messageRanges = messageRanges,
        isPushMessage = true
      )

      insertResult = SignalDatabase.messages.insertSecureDecryptedMessageInbox(mediaMessage, -1).orNull()
      if (insertResult != null) {
        SignalDatabase.messages.setTransactionSuccessful()
      }
    } catch (e: MmsException) {
      throw StorageFailedException(e, metadata.sourceServiceId.toString(), metadata.sourceDeviceId)
    } finally {
      SignalDatabase.messages.endTransaction()
    }

    return if (insertResult != null) {
      val allAttachments = SignalDatabase.attachments.getAttachmentsForMessage(insertResult.messageId)
      val stickerAttachments = allAttachments.filter { it.isSticker }.toList()
      val attachments = allAttachments.filterNot { it.isSticker }.toList()

      forceStickerDownloadIfNecessary(context, insertResult.messageId, stickerAttachments)

      for (attachment in attachments) {
        ApplicationDependencies.getJobManager().add(AttachmentDownloadJob(insertResult.messageId, attachment.attachmentId, false))
      }

      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
      TrimThreadJob.enqueueAsync(insertResult.threadId)

      // JW: add a [1] reaction to indicate the message was sent as viewOnce.
      if (TextSecurePreferences.isKeepViewOnceMessages(context) && message.isViewOnce) {
        val targetMessage = SignalDatabase.messages.getMessageRecordOrNull(insertResult.messageId)
        setMessageReaction(context, message, targetMessage, "\u0031\uFE0F\u20E3")
      }
      if (viewOnce) { // JW
        ApplicationDependencies.getViewOnceMessageManager().scheduleIfNecessary()
      }

      MessageId(insertResult.messageId)
    } else {
      null
    }
  }

  @Throws(StorageFailedException::class)
  private fun handleTextMessage(
    context: Context,
    envelope: Envelope,
    metadata: EnvelopeMetadata,
    message: DataMessage,
    senderRecipient: Recipient,
    threadRecipientId: RecipientId,
    groupId: GroupId.V2?,
    receivedTime: Long
  ): MessageId? {
    log(envelope.timestamp, "Text message.")

    val body = if (message.hasBody()) message.body else ""

    handlePossibleExpirationUpdate(envelope, metadata, senderRecipient.id, threadRecipientId, groupId, message.expireTimer.seconds, receivedTime)

    notifyTypingStoppedFromIncomingMessage(context, senderRecipient, threadRecipientId, metadata.sourceDeviceId)

    val textMessage = IncomingTextMessage(
      senderRecipient.id,
      metadata.sourceDeviceId,
      envelope.timestamp,
      envelope.serverTimestamp,
      receivedTime,
      body,
      Optional.ofNullable(groupId),
      message.expireTimer.seconds.inWholeMilliseconds,
      metadata.sealedSender,
      envelope.serverGuid
    )

    val insertResult: InsertResult? = SignalDatabase.messages.insertMessageInbox(IncomingEncryptedMessage(textMessage, body)).orNull()

    return if (insertResult != null) {
      ApplicationDependencies.getMessageNotifier().updateNotification(context, ConversationId.forConversation(insertResult.threadId))
      MessageId(insertResult.messageId)
    } else {
      null
    }
  }

  fun handleGroupCallUpdateMessage(
    envelope: Envelope,
    message: DataMessage,
    senderRecipientId: RecipientId,
    groupId: GroupId.V2?
  ) {
    log(envelope.timestamp, "Group call update message.")

    if (groupId == null || !message.hasGroupCallUpdate()) {
      warn(envelope.timestamp, "Invalid group for group call update message")
      return
    }

    val groupRecipientId = SignalDatabase.recipients.getOrInsertFromPossiblyMigratedGroupId(groupId)

    SignalDatabase.calls.insertOrUpdateGroupCallFromExternalEvent(
      groupRecipientId,
      senderRecipientId,
      envelope.serverTimestamp,
      if (message.groupCallUpdate.hasEraId()) message.groupCallUpdate.eraId else null
    )

    GroupCallPeekJob.enqueue(groupRecipientId)
  }

  fun notifyTypingStoppedFromIncomingMessage(context: Context, senderRecipient: Recipient, threadRecipientId: RecipientId, device: Int) {
    val threadId = SignalDatabase.threads.getThreadIdIfExistsFor(threadRecipientId)

    if (threadId > 0 && TextSecurePreferences.isTypingIndicatorsEnabled(context)) {
      debug("Typing stopped on thread $threadId due to an incoming message.")
      ApplicationDependencies.getTypingStatusRepository().onTypingStopped(threadId, senderRecipient, device, true)
    }
  }

  fun getMentions(mentionBodyRanges: List<BodyRange>): List<Mention> {
    return mentionBodyRanges
      .filter { it.hasMentionUuid() }
      .mapNotNull {
        val serviceId = ServiceId.parseOrNull(it.mentionUuid)

        if (serviceId != null) {
          val id = Recipient.externalPush(serviceId).id
          Mention(id, it.start, it.length)
        } else {
          null
        }
      }
  }

  fun forceStickerDownloadIfNecessary(context: Context, messageId: Long, stickerAttachments: List<DatabaseAttachment>) {
    if (stickerAttachments.isEmpty()) {
      return
    }

    val stickerAttachment = stickerAttachments[0]
    if (stickerAttachment.transferState != AttachmentTable.TRANSFER_PROGRESS_DONE) {
      val downloadJob = AttachmentDownloadJob(messageId, stickerAttachment.attachmentId, true)
      try {
        downloadJob.setContext(context)
        downloadJob.doWork()
      } catch (e: Exception) {
        warn("Failed to download sticker inline. Scheduling.")
        ApplicationDependencies.getJobManager().add(downloadJob)
      }
    }
  }

  private fun insertPlaceholder(sender: RecipientId, senderDevice: Int, timestamp: Long, groupId: GroupId?): InsertResult? {
    val textMessage = IncomingTextMessage(
      sender,
      senderDevice,
      timestamp,
      -1,
      System.currentTimeMillis(),
      "",
      groupId.asOptional(),
      0,
      false,
      null
    )
    return SignalDatabase.messages.insertMessageInbox(IncomingEncryptedMessage(textMessage, "")).orNull()
  }

  fun getValidatedQuote(context: Context, timestamp: Long, message: DataMessage): QuoteModel? {
    if (!message.hasQuote()) {
      return null
    }

    val quote: DataMessage.Quote = message.quote

    if (quote.id <= 0) {
      warn(timestamp, "Received quote without an ID! Ignoring...")
      return null
    }

    val authorId = Recipient.externalPush(ServiceId.parseOrThrow(quote.authorUuid)).id
    var quotedMessage = SignalDatabase.messages.getMessageFor(quote.id, authorId) as? MediaMmsMessageRecord

    if (quotedMessage != null && !quotedMessage.isRemoteDelete) {
      log(timestamp, "Found matching message record...")

      val attachments: MutableList<Attachment> = mutableListOf()
      val mentions: MutableList<Mention> = mutableListOf()

      quotedMessage = quotedMessage.withAttachments(context, SignalDatabase.attachments.getAttachmentsForMessage(quotedMessage.id))

      mentions.addAll(SignalDatabase.mentions.getMentionsForMessage(quotedMessage.id))

      if (quotedMessage.isViewOnce) {
        attachments.add(TombstoneAttachment(MediaUtil.VIEW_ONCE, true))
      } else {
        attachments += quotedMessage.slideDeck.asAttachments()

        if (attachments.isEmpty()) {
          attachments += quotedMessage
            .linkPreviews
            .filter { it.thumbnail.isPresent }
            .map { it.thumbnail.get() }
        }
      }

      if (quotedMessage.isPaymentNotification) {
        quotedMessage = SignalDatabase.payments.updateMessageWithPayment(quotedMessage) as MediaMmsMessageRecord
      }

      val body = if (quotedMessage.isPaymentNotification) quotedMessage.getDisplayBody(context).toString() else quotedMessage.body

      return QuoteModel(
        quote.id,
        authorId,
        body,
        false,
        attachments,
        mentions,
        QuoteModel.Type.fromProto(quote.type),
        quotedMessage.messageRanges
      )
    } else if (quotedMessage != null) {
      warn(timestamp, "Found the target for the quote, but it's flagged as remotely deleted.")
    }

    warn(timestamp, "Didn't find matching message record...")
    return QuoteModel(
      quote.id,
      authorId,
      quote.text,
      true,
      quote.attachmentsList.mapNotNull { PointerAttachment.forPointer(it).orNull() },
      getMentions(quote.bodyRangesList),
      QuoteModel.Type.fromProto(quote.type),
      quote.bodyRangesList.filterNot { it.hasMentionUuid() }.toBodyRangeList()
    )
  }

  fun getContacts(message: DataMessage): List<Contact> {
    return message.contactList.map { ContactModelMapper.remoteToLocal(it) }
  }

  fun getLinkPreviews(previews: List<Preview>, body: String, isStoryEmbed: Boolean): List<LinkPreview> {
    if (previews.isEmpty()) {
      return emptyList()
    }

    val urlsInMessage = LinkPreviewUtil.findValidPreviewUrls(body)

    return previews
      .mapNotNull { preview ->
        val thumbnail: Attachment? = preview.image.toPointer()
        val url: Optional<String> = preview.url.toOptional()
        val title: Optional<String> = preview.title.toOptional()
        val description: Optional<String> = preview.description.toOptional()
        val hasTitle = !TextUtils.isEmpty(title.orElse(""))
        val presentInBody = url.isPresent && urlsInMessage.containsUrl(url.get())
        val validDomain = url.isPresent && LinkUtil.isValidPreviewUrl(url.get())

        if (hasTitle && (presentInBody || isStoryEmbed) && validDomain) {
          val linkPreview = LinkPreview(url.get(), title.orElse(""), description.orElse(""), preview.date, thumbnail.toOptional())
          linkPreview
        } else {
          warn(String.format("Discarding an invalid link preview. hasTitle: %b presentInBody: %b isStoryEmbed: %b validDomain: %b", hasTitle, presentInBody, isStoryEmbed, validDomain))
          null
        }
      }
  }

  fun getStickerAttachment(timestamp: Long, message: DataMessage): Attachment? {
    if (!message.hasSticker()) {
      return null
    }

    val sticker = message.sticker
    if (!(message.sticker.hasPackId() && message.sticker.hasPackKey() && message.sticker.hasStickerId() && message.sticker.hasData())) {
      warn(timestamp, "Malformed sticker!")
      return null
    }

    val packId = Hex.toStringCondensed(sticker.packId.toByteArray())
    val packKey = Hex.toStringCondensed(sticker.packKey.toByteArray())
    val stickerId = sticker.stickerId
    val emoji = sticker.emoji
    val stickerLocator = StickerLocator(packId, packKey, stickerId, emoji)

    val stickerRecord = SignalDatabase.stickers.getSticker(stickerLocator.packId, stickerLocator.stickerId, false)

    return if (stickerRecord != null) {
      UriAttachment(
        stickerRecord.uri,
        stickerRecord.contentType,
        AttachmentTable.TRANSFER_PROGRESS_DONE,
        stickerRecord.size,
        StickerSlide.WIDTH,
        StickerSlide.HEIGHT,
        null,
        SecureRandom().nextLong().toString(),
        false,
        false,
        false,
        false,
        null,
        stickerLocator,
        null,
        null,
        null
      )
    } else {
      sticker.data.toPointer(stickerLocator)
    }
  }
}
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
