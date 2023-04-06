package org.thoughtcrime.securesms.util;

import androidx.annotation.NonNull;

import org.thoughtcrime.securesms.database.model.ServiceMessageId;
import org.thoughtcrime.securesms.recipients.RecipientId;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Sometimes a message that is referencing another message can arrive out of order. In these cases,
 * we want to temporarily hold on (i.e. keep a memory cache) to these messages and apply them after
 * we receive the referenced message.
 */
public final class EarlyMessageCache {

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
  private final LRUCache<ServiceMessageId, List<EarlyMessageCacheEntry>> cache = new LRUCache<>(100);
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
  private final LRUCache<ServiceMessageId, List<SignalServiceContent>> cache = new LRUCache<>(100);
=======
  private final LRUCache<ServiceMessageId, List<SignalServiceContent>>   cache   = new LRUCache<>(100);
  private final LRUCache<ServiceMessageId, List<EarlyMessageCacheEntry>> cacheV2 = new LRUCache<>(100);
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
  private final LRUCache<ServiceMessageId, List<SignalServiceContent>> cache = new LRUCache<>(100);
=======
  private final LRUCache<ServiceMessageId, List<SignalServiceContent>>   cache   = new LRUCache<>(100);
  private final LRUCache<ServiceMessageId, List<EarlyMessageCacheEntry>> cacheV2 = new LRUCache<>(100);
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
  private final LRUCache<ServiceMessageId, List<SignalServiceContent>> cache = new LRUCache<>(100);
=======
  private final LRUCache<ServiceMessageId, List<SignalServiceContent>>   cache   = new LRUCache<>(100);
  private final LRUCache<ServiceMessageId, List<EarlyMessageCacheEntry>> cacheV2 = new LRUCache<>(100);
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)

  /**
   * @param targetSender        The sender of the message this message depends on.
   * @param targetSentTimestamp The sent timestamp of the message this message depends on.
   */
  public synchronized void store(@NonNull RecipientId targetSender,
                                 long targetSentTimestamp,
                                 @NonNull EarlyMessageCacheEntry cacheEntry)
  {
    ServiceMessageId             messageId    = new ServiceMessageId(targetSender, targetSentTimestamp);
    List<EarlyMessageCacheEntry> envelopeList = cache.get(messageId);

    if (envelopeList == null) {
      envelopeList = new LinkedList<>();
    }

    envelopeList.add(cacheEntry);

    cache.put(messageId, envelopeList);
  }

  public synchronized void store(@NonNull RecipientId targetSender,
                                 long targetSentTimestamp,
                                 @NonNull EarlyMessageCacheEntry cacheEntry)
  {
    ServiceMessageId             messageId    = new ServiceMessageId(targetSender, targetSentTimestamp);
    List<EarlyMessageCacheEntry> envelopeList = cacheV2.get(messageId);

    if (envelopeList == null) {
      envelopeList = new LinkedList<>();
    }

    envelopeList.add(cacheEntry);

    cacheV2.put(messageId, envelopeList);
  }

  public synchronized void store(@NonNull RecipientId targetSender,
                                 long targetSentTimestamp,
                                 @NonNull EarlyMessageCacheEntry cacheEntry)
  {
    ServiceMessageId             messageId    = new ServiceMessageId(targetSender, targetSentTimestamp);
    List<EarlyMessageCacheEntry> envelopeList = cacheV2.get(messageId);

    if (envelopeList == null) {
      envelopeList = new LinkedList<>();
    }

    envelopeList.add(cacheEntry);

    cacheV2.put(messageId, envelopeList);
  }

  public synchronized void store(@NonNull RecipientId targetSender,
                                 long targetSentTimestamp,
                                 @NonNull EarlyMessageCacheEntry cacheEntry)
  {
    ServiceMessageId             messageId    = new ServiceMessageId(targetSender, targetSentTimestamp);
    List<EarlyMessageCacheEntry> envelopeList = cacheV2.get(messageId);

    if (envelopeList == null) {
      envelopeList = new LinkedList<>();
    }

    envelopeList.add(cacheEntry);

    cacheV2.put(messageId, envelopeList);
  }

  /**
   * Returns and removes any content that is dependent on the provided message id.
   *
   * @param sender        The sender of the message in question.
   * @param sentTimestamp The sent timestamp of the message in question.
   */
  public synchronized Optional<List<EarlyMessageCacheEntry>> retrieve(@NonNull RecipientId sender, long sentTimestamp) {
    return Optional.ofNullable(cache.remove(new ServiceMessageId(sender, sentTimestamp)));
  }

  public synchronized Optional<List<EarlyMessageCacheEntry>> retrieveV2(@NonNull RecipientId sender, long sentTimestamp) {
    return Optional.ofNullable(cacheV2.remove(new ServiceMessageId(sender, sentTimestamp)));
  }

  /**
   * Returns a collection of all of the {@link ServiceMessageId}s referenced in the cache at the moment of inquiry.
   * Caution: There is no guarantee that this list will be relevant for any amount of time afterwards.
   */
  public synchronized @NonNull Collection<ServiceMessageId> getAllReferencedIds() {
    Set<ServiceMessageId> allIds = new HashSet<>(cache.keySet());
    allIds.addAll(cacheV2.keySet());
    return allIds;
  }
}
