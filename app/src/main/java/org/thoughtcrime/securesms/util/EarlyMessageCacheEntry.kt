<<<<<<< HEAD
package org.thoughtcrime.securesms.util

import org.whispersystems.signalservice.api.crypto.EnvelopeMetadata
import org.whispersystems.signalservice.internal.push.Content
import org.whispersystems.signalservice.internal.push.Envelope

/**
 * The tuple of information needed to process a message. Used to in [EarlyMessageCache]
 * to store potentially out-of-order messages.
 */
data class EarlyMessageCacheEntry(
  val envelope: Envelope,
  val content: Content,
  val metadata: EnvelopeMetadata,
  val serverDeliveredTimestamp: Long
)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
package org.thoughtcrime.securesms.util

import org.whispersystems.signalservice.api.crypto.EnvelopeMetadata
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.Content
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.Envelope

/**
 * The tuple of information needed to process a message. Used to in [EarlyMessageCache]
 * to store potentially out-of-order messages.
 */
data class EarlyMessageCacheEntry(
  val envelope: Envelope,
  val content: Content,
  val metadata: EnvelopeMetadata,
  val serverDeliveredTimestamp: Long
)
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
