package org.thoughtcrime.securesms.conversation;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;

import org.signal.core.util.StreamUtil;
import org.signal.core.util.concurrent.SignalExecutors;
import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.MessageTable;
import org.thoughtcrime.securesms.database.SignalDatabase;
import org.thoughtcrime.securesms.database.ThreadTable;
import org.thoughtcrime.securesms.database.model.GroupRecord;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.dependencies.AppDependencies;
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
=======
import org.thoughtcrime.securesms.database.model.MessageRecord;
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
=======
import org.thoughtcrime.securesms.database.model.MessageRecord;
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies;
=======
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies;
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
import org.thoughtcrime.securesms.jobs.MultiDeviceViewedUpdateJob;
import org.thoughtcrime.securesms.keyvalue.SignalStore;
import org.thoughtcrime.securesms.mms.PartAuthority;
import org.thoughtcrime.securesms.mms.TextSlide;
import org.thoughtcrime.securesms.recipients.Recipient;
import org.thoughtcrime.securesms.recipients.RecipientUtil;
<<<<<<< HEAD
import org.thoughtcrime.securesms.util.MessageRecordUtil;
import org.whispersystems.signalservice.api.push.ServiceId;
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
import org.thoughtcrime.securesms.util.BubbleUtil;
import org.thoughtcrime.securesms.util.ConversationUtil;
import org.thoughtcrime.securesms.util.MessageRecordUtil;
import org.thoughtcrime.securesms.util.Util;
=======
import org.thoughtcrime.securesms.util.BubbleUtil;
import org.thoughtcrime.securesms.util.ConversationUtil;
import org.thoughtcrime.securesms.util.MessageRecordUtil;
import org.thoughtcrime.securesms.util.Util;
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
=======
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
=======
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
import io.reactivex.rxjava3.core.Observable;
=======
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ConversationRepository {

  private static final String TAG = Log.tag(ConversationRepository.class);

  private final Context  context;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
  public ConversationRepository() {
    this.context = AppDependencies.getApplication();
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  ConversationRepository() {
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  ConversationRepository() {
=======
  public ConversationRepository() {
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  ConversationRepository() {
=======
  public ConversationRepository() {
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    this.context = ApplicationDependencies.getApplication();
  }

  @WorkerThread
  boolean canShowAsBubble(long threadId) {
    if (Build.VERSION.SDK_INT >= ConversationUtil.CONVERSATION_SUPPORT_VERSION) {
      Recipient recipient = SignalDatabase.threads().getRecipientForThreadId(threadId);

      return recipient != null && BubbleUtil.canBubble(context, recipient.getId(), threadId);
    } else {
      return false;
    }
=======
  public ConversationRepository() {
    this.context = ApplicationDependencies.getApplication();
  }

  @WorkerThread
  boolean canShowAsBubble(long threadId) {
    if (Build.VERSION.SDK_INT >= ConversationUtil.CONVERSATION_SUPPORT_VERSION) {
      Recipient recipient = SignalDatabase.threads().getRecipientForThreadId(threadId);

      return recipient != null && BubbleUtil.canBubble(context, recipient.getId(), threadId);
    } else {
      return false;
    }
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  }

  @WorkerThread
  public @NonNull ConversationData getConversationData(long threadId, @NonNull Recipient conversationRecipient, int jumpToPosition) {
    ThreadTable.ConversationMetadata    metadata                       = SignalDatabase.threads().getConversationMetadata(threadId);
    int                                 threadSize                     = SignalDatabase.messages().getMessageCountForThread(threadId);
    long                                lastSeen                       = metadata.getLastSeen();
    int                                 lastSeenPosition               = 0;
    long                                lastScrolled                   = metadata.getLastScrolled();
    int                                 lastScrolledPosition           = 0;
    boolean                             isMessageRequestAccepted       = RecipientUtil.isMessageRequestAccepted(context, threadId);
    boolean                             isConversationHidden           = RecipientUtil.isRecipientHidden(threadId);
    ConversationData.MessageRequestData messageRequestData             = new ConversationData.MessageRequestData(isMessageRequestAccepted, isConversationHidden);
    boolean                             showUniversalExpireTimerUpdate = false;

    if (lastSeen > 0) {
      lastSeenPosition = SignalDatabase.messages().getMessagePositionOnOrAfterTimestamp(threadId, lastSeen);
    }

    if (lastSeenPosition <= 0) {
      lastSeen = 0;
    }

    if (lastSeen == 0 && lastScrolled > 0) {
      lastScrolledPosition = SignalDatabase.messages().getMessagePositionOnOrAfterTimestamp(threadId, lastScrolled);
    }

    if (!isMessageRequestAccepted) {
      boolean isGroup                             = false;
      boolean recipientIsKnownOrHasGroupsInCommon = false;
      if (conversationRecipient.isGroup()) {
        Optional<GroupRecord> group = SignalDatabase.groups().getGroup(conversationRecipient.getId());
        if (group.isPresent()) {
          List<Recipient> recipients = Recipient.resolvedList(group.get().getMembers());
          for (Recipient recipient : recipients) {
            if ((recipient.isProfileSharing() || recipient.getHasGroupsInCommon()) && !recipient.isSelf()) {
              recipientIsKnownOrHasGroupsInCommon = true;
              break;
            }
          }
        }
        isGroup = true;
      } else if (conversationRecipient.getHasGroupsInCommon()) {
        recipientIsKnownOrHasGroupsInCommon = true;
      }
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
      messageRequestData = new ConversationData.MessageRequestData(isMessageRequestAccepted, isConversationHidden, recipientIsKnownOrHasGroupsInCommon, isGroup);
    }

    List<ServiceId> groupMemberAcis;
    if (conversationRecipient.isPushV2Group()) {
      groupMemberAcis = conversationRecipient.getParticipantAcis();
    } else {
      groupMemberAcis = Collections.emptyList();
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      messageRequestData = new ConversationData.MessageRequestData(isMessageRequestAccepted, recipientIsKnownOrHasGroupsInCommon, isGroup);
=======
      messageRequestData = new ConversationData.MessageRequestData(isMessageRequestAccepted, isConversationHidden, recipientIsKnownOrHasGroupsInCommon, isGroup);
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      messageRequestData = new ConversationData.MessageRequestData(isMessageRequestAccepted, recipientIsKnownOrHasGroupsInCommon, isGroup);
=======
      messageRequestData = new ConversationData.MessageRequestData(isMessageRequestAccepted, isConversationHidden, recipientIsKnownOrHasGroupsInCommon, isGroup);
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      messageRequestData = new ConversationData.MessageRequestData(isMessageRequestAccepted, recipientIsKnownOrHasGroupsInCommon, isGroup);
=======
      messageRequestData = new ConversationData.MessageRequestData(isMessageRequestAccepted, isConversationHidden, recipientIsKnownOrHasGroupsInCommon, isGroup);
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    }

    if (SignalStore.settings().getUniversalExpireTimer() != 0 &&
        conversationRecipient.getExpiresInSeconds() == 0 &&
        !conversationRecipient.isGroup() &&
        conversationRecipient.isRegistered() &&
        SignalDatabase.messages().canSetUniversalTimer(threadId))
    {
      showUniversalExpireTimerUpdate = true;
    }

    return new ConversationData(conversationRecipient, threadId, lastSeen, lastSeenPosition, lastScrolledPosition, jumpToPosition, threadSize, messageRequestData, showUniversalExpireTimerUpdate, metadata.getUnreadCount(), groupMemberAcis);
  }

  public void markGiftBadgeRevealed(long messageId) {
    SignalExecutors.BOUNDED_IO.execute(() -> {
      List<MessageTable.MarkedMessageInfo> markedMessageInfo = SignalDatabase.messages().setOutgoingGiftsRevealed(Collections.singletonList(messageId));
      if (!markedMessageInfo.isEmpty()) {
        Log.d(TAG, "Marked gift badge revealed. Sending view sync message.");
        MultiDeviceViewedUpdateJob.enqueue(
            markedMessageInfo.stream()
                             .map(MessageTable.MarkedMessageInfo::getSyncMessageId)
                             .collect(Collectors.toList()));
      }
    });
  }

  @NonNull
  public Single<ConversationMessage> resolveMessageToEdit(@NonNull ConversationMessage message) {
    return Single.fromCallable(() -> {
                   MessageRecord messageRecord = message.getMessageRecord();
                   if (MessageRecordUtil.hasTextSlide(messageRecord)) {
                     TextSlide textSlide = MessageRecordUtil.requireTextSlide(messageRecord);
                     if (textSlide.getUri() == null) {
                       return message;
                     }

<<<<<<< HEAD
                     try (InputStream stream = PartAuthority.getAttachmentStream(context, textSlide.getUri())) {
                       String body = StreamUtil.readFullyAsString(stream);
                       return ConversationMessage.ConversationMessageFactory.createWithUnresolvedData(context, messageRecord, body, message.getThreadRecipient());
                     } catch (IOException e) {
                       Log.w(TAG, "Failed to read text slide data.");
                     }
                   }
                   return message;
                 }).subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread());
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      if (recipient.isPushGroup()) {
        Log.i(TAG, "Push group recipient...");
        registeredState = RecipientTable.RegisteredState.REGISTERED;
      } else {
        Log.i(TAG, "Checking through resolved recipient");
        registeredState = recipient.getRegistered();
      }

      Log.i(TAG, "Resolved registered state: " + registeredState);
      boolean signalEnabled = Recipient.self().isRegistered();

      if (registeredState == RecipientTable.RegisteredState.UNKNOWN) {
        try {
          Log.i(TAG, "Refreshing directory for user: " + recipient.getId().serialize());
          registeredState = ContactDiscovery.refresh(context, recipient, false);
        } catch (IOException e) {
          Log.w(TAG, e);
        }
      }

      long threadId = SignalDatabase.threads().getThreadIdIfExistsFor(recipient.getId());

      boolean hasUnexportedInsecureMessages = threadId != -1 && SignalDatabase.messages().getUnexportedInsecureMessagesCount(threadId) > 0;

      Log.i(TAG, "Returning registered state...");
      return new ConversationSecurityInfo(recipient.getId(),
                                          registeredState == RecipientTable.RegisteredState.REGISTERED && signalEnabled,
                                          Util.isDefaultSmsProvider(context),
                                          true,
                                          hasUnexportedInsecureMessages);
    }).subscribeOn(Schedulers.io());
  }

  @NonNull
  public Single<ConversationMessage> resolveMessageToEdit(@NonNull ConversationMessage message) {
    return Single.fromCallable(() -> {
                   MessageRecord messageRecord = message.getMessageRecord();
                   if (MessageRecordUtil.hasTextSlide(messageRecord)) {
                     TextSlide textSlide = MessageRecordUtil.requireTextSlide(messageRecord);
                     if (textSlide.getUri() == null) {
                       return message;
                     }

                     try (InputStream stream = PartAuthority.getAttachmentStream(context, textSlide.getUri())) {
                       String body = StreamUtil.readFullyAsString(stream);
                       return ConversationMessage.ConversationMessageFactory.createWithUnresolvedData(context, messageRecord, body);
                     } catch (IOException e) {
                       Log.w(TAG, "Failed to read text slide data.");
                     }
                   }
                   return message;
                 }).subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread());
  }

  Observable<Integer> getUnreadCount(long threadId, long afterTime) {
    if (threadId <= -1L || afterTime <= 0L) {
      return Observable.just(0);
    }

    return Observable.<Integer> create(emitter -> {

      DatabaseObserver.Observer listener = () -> emitter.onNext(SignalDatabase.messages().getIncomingMeaningfulMessageCountSince(threadId, afterTime));

      ApplicationDependencies.getDatabaseObserver().registerConversationObserver(threadId, listener);
      emitter.setCancellable(() -> ApplicationDependencies.getDatabaseObserver().unregisterObserver(listener));

      listener.onChanged();
    }).subscribeOn(Schedulers.io());
  }

  public void insertSmsExportUpdateEvent(Recipient recipient) {
    SignalExecutors.BOUNDED.execute(() -> {
      long threadId = SignalDatabase.threads().getThreadIdIfExistsFor(recipient.getId());

      if (threadId == -1 || !Util.isDefaultSmsProvider(context)) {
        return;
      }

      if (RecipientUtil.isSmsOnly(threadId, recipient) && (!recipient.isMmsGroup() || Util.isDefaultSmsProvider(context))) {
        SignalDatabase.messages().insertSmsExportMessage(recipient.getId(), threadId);
      }
    });
  }

  public void setConversationMuted(@NonNull RecipientId recipientId, long until) {
    SignalExecutors.BOUNDED.execute(() -> SignalDatabase.recipients().setMuted(recipientId, until));
  }

  public void setConversationDistributionType(long threadId, int distributionType) {
    SignalExecutors.BOUNDED.execute(() -> SignalDatabase.threads().setDistributionType(threadId, distributionType));
=======
      if (recipient.isPushGroup()) {
        Log.i(TAG, "Push group recipient...");
        registeredState = RecipientTable.RegisteredState.REGISTERED;
      } else {
        Log.i(TAG, "Checking through resolved recipient");
        registeredState = recipient.getRegistered();
      }

      Log.i(TAG, "Resolved registered state: " + registeredState);
      boolean signalEnabled = Recipient.self().isRegistered();

      if (registeredState == RecipientTable.RegisteredState.UNKNOWN) {
        try {
          Log.i(TAG, "Refreshing directory for user: " + recipient.getId().serialize());
          registeredState = ContactDiscovery.refresh(context, recipient, false);
        } catch (IOException e) {
          Log.w(TAG, e);
        }
      }

      long threadId = SignalDatabase.threads().getThreadIdIfExistsFor(recipient.getId());

      boolean hasUnexportedInsecureMessages = threadId != -1 && SignalDatabase.messages().getUnexportedInsecureMessagesCount(threadId) > 0;

      Log.i(TAG, "Returning registered state...");
      return new ConversationSecurityInfo(recipient.getId(),
                                          registeredState == RecipientTable.RegisteredState.REGISTERED && signalEnabled,
                                          Util.isDefaultSmsProvider(context),
                                          true,
                                          hasUnexportedInsecureMessages);
    }).subscribeOn(Schedulers.io());
  }

  @NonNull
  public Single<ConversationMessage> resolveMessageToEdit(@NonNull ConversationMessage message) {
    return Single.fromCallable(() -> {
                   MessageRecord messageRecord = message.getMessageRecord();
                   if (MessageRecordUtil.hasTextSlide(messageRecord)) {
                     TextSlide textSlide = MessageRecordUtil.requireTextSlide(messageRecord);
                     if (textSlide.getUri() == null) {
                       return message;
                     }

                     try (InputStream stream = PartAuthority.getAttachmentStream(context, textSlide.getUri())) {
                       String body = StreamUtil.readFullyAsString(stream);
                       return ConversationMessage.ConversationMessageFactory.createWithUnresolvedData(context, messageRecord, body);
                     } catch (IOException e) {
                       Log.w(TAG, "Failed to read text slide data.");
                     }
                   }
                   return message;
                 }).subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread());
  }

  Observable<Integer> getUnreadCount(long threadId, long afterTime) {
    if (threadId <= -1L || afterTime <= 0L) {
      return Observable.just(0);
    }

    return Observable.<Integer> create(emitter -> {

      DatabaseObserver.Observer listener = () -> emitter.onNext(SignalDatabase.messages().getIncomingMeaningfulMessageCountSince(threadId, afterTime));

      ApplicationDependencies.getDatabaseObserver().registerConversationObserver(threadId, listener);
      emitter.setCancellable(() -> ApplicationDependencies.getDatabaseObserver().unregisterObserver(listener));

      listener.onChanged();
    }).subscribeOn(Schedulers.io());
  }

  public void insertSmsExportUpdateEvent(Recipient recipient) {
    SignalExecutors.BOUNDED.execute(() -> {
      long threadId = SignalDatabase.threads().getThreadIdIfExistsFor(recipient.getId());

      if (threadId == -1 || !Util.isDefaultSmsProvider(context)) {
        return;
      }

      if (RecipientUtil.isSmsOnly(threadId, recipient) && (!recipient.isMmsGroup() || Util.isDefaultSmsProvider(context))) {
        SignalDatabase.messages().insertSmsExportMessage(recipient.getId(), threadId);
      }
    });
  }

  public void setConversationMuted(@NonNull RecipientId recipientId, long until) {
    SignalExecutors.BOUNDED.execute(() -> SignalDatabase.recipients().setMuted(recipientId, until));
  }

  public void setConversationDistributionType(long threadId, int distributionType) {
    SignalExecutors.BOUNDED.execute(() -> SignalDatabase.threads().setDistributionType(threadId, distributionType));
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  }
}
