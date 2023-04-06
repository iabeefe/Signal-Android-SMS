<<<<<<< HEAD
<<<<<<< HEAD
package org.thoughtcrime.securesms.messages

import org.whispersystems.signalservice.api.crypto.EnvelopeMetadata
import org.whispersystems.signalservice.internal.push.Content
import org.whispersystems.signalservice.internal.push.Envelope

data class TestMessage(
  val envelope: Envelope,
  val content: Content,
  val metadata: EnvelopeMetadata,
  val serverDeliveredTimestamp: Long
)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
package org.thoughtcrime.securesms.messages

import org.whispersystems.signalservice.api.crypto.EnvelopeMetadata
import org.whispersystems.signalservice.internal.push.SignalServiceProtos

data class TestMessage(
  val envelope: SignalServiceProtos.Envelope,
  val content: SignalServiceProtos.Content,
  val metadata: EnvelopeMetadata,
  val serverDeliveredTimestamp: Long
)
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
package org.thoughtcrime.securesms.messages

import org.whispersystems.signalservice.api.crypto.EnvelopeMetadata
import org.whispersystems.signalservice.internal.push.SignalServiceProtos

data class TestMessage(
  val envelope: SignalServiceProtos.Envelope,
  val content: SignalServiceProtos.Content,
  val metadata: EnvelopeMetadata,
  val serverDeliveredTimestamp: Long
)
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
