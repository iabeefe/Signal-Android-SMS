<<<<<<< HEAD
package org.signal.util

import okio.ByteString.Companion.toByteString
import org.signal.core.util.Base64
import org.signal.libsignal.internal.Native
import org.signal.libsignal.internal.NativeHandleGuard
import org.signal.libsignal.metadata.certificate.CertificateValidator
import org.signal.libsignal.metadata.certificate.SenderCertificate
import org.signal.libsignal.metadata.certificate.ServerCertificate
import org.signal.libsignal.protocol.SessionBuilder
import org.signal.libsignal.protocol.SignalProtocolAddress
import org.signal.libsignal.protocol.ecc.Curve
import org.signal.libsignal.protocol.ecc.ECKeyPair
import org.signal.libsignal.protocol.ecc.ECPublicKey
import org.signal.libsignal.protocol.groups.GroupSessionBuilder
import org.signal.libsignal.protocol.message.SenderKeyDistributionMessage
import org.signal.libsignal.protocol.state.PreKeyBundle
import org.signal.libsignal.protocol.state.PreKeyRecord
import org.signal.libsignal.protocol.state.SignedPreKeyRecord
import org.whispersystems.signalservice.api.SignalServiceAccountDataStore
import org.whispersystems.signalservice.api.SignalSessionLock
import org.whispersystems.signalservice.api.crypto.ContentHint
import org.whispersystems.signalservice.api.crypto.EnvelopeContent
import org.whispersystems.signalservice.api.crypto.SealedSenderAccess
import org.whispersystems.signalservice.api.crypto.SignalGroupSessionBuilder
import org.whispersystems.signalservice.api.crypto.SignalServiceCipher
import org.whispersystems.signalservice.api.crypto.UnidentifiedAccess
import org.whispersystems.signalservice.api.push.DistributionId
import org.whispersystems.signalservice.api.push.ServiceId.ACI
import org.whispersystems.signalservice.api.push.SignalServiceAddress
import org.whispersystems.signalservice.internal.push.Content
import org.whispersystems.signalservice.internal.push.DataMessage
import org.whispersystems.signalservice.internal.push.Envelope
import org.whispersystems.signalservice.internal.push.OutgoingPushMessage
import org.whispersystems.signalservice.internal.util.Util
import java.util.Optional
import java.util.UUID
import java.util.concurrent.locks.ReentrantLock
import kotlin.random.Random

/**
 * An in-memory signal client that can encrypt and decrypt messages.
 *
 * Has a single prekey bundle that can be used to initialize a session with another client.
 */
class SignalClient {
  companion object {
    private val trustRoot: ECKeyPair = Curve.generateKeyPair()
  }

  private val lock = TestSessionLock()

  private val aci: ACI = ACI.from(UUID.randomUUID())

  private val store: SignalServiceAccountDataStore = InMemorySignalServiceAccountDataStore()

  private var prekeyIndex = 0

  private val unidentifiedAccessKey: ByteArray = Util.getSecretBytes(32)

  private val senderCertificate: SenderCertificate = createCertificateFor(
    trustRoot = trustRoot,
    uuid = aci.rawUuid,
    e164 = "+${Random.nextLong(1111111111L, 9999999999L)}",
    deviceId = 1,
    identityKey = store.identityKeyPair.publicKey.publicKey,
    expires = Long.MAX_VALUE
  )

  private val cipher = SignalServiceCipher(SignalServiceAddress(aci), 1, store, lock, CertificateValidator(trustRoot.publicKey))

  /**
   * Sets up sessions using the [to] client's [preKeyBundles]. Note that you can only initialize a client up to 1,000 times because that's how many prekeys we have.
   */
  fun initializeSession(to: SignalClient) {
    val address = SignalProtocolAddress(to.aci.toString(), 1)
    SessionBuilder(store, address).process(to.createPreKeyBundle())
  }

  fun initializedGroupSession(distributionId: DistributionId): SenderKeyDistributionMessage {
    val self = SignalProtocolAddress(aci.toString(), 1)
    return SignalGroupSessionBuilder(lock, GroupSessionBuilder(store)).create(self, distributionId.asUuid())
  }

  fun encryptUnsealedSender(to: SignalClient): Envelope {
    val sentTimestamp = System.currentTimeMillis()

    val content = Content(
      dataMessage = DataMessage(
        body = "Test Message",
        timestamp = sentTimestamp
      )
    )

    val outgoingPushMessage: OutgoingPushMessage = cipher.encrypt(
      SignalProtocolAddress(to.aci.toString(), 1),
      SealedSenderAccess.NONE,
      EnvelopeContent.encrypted(content, ContentHint.RESENDABLE, Optional.empty())
    )

    val encryptedContent: ByteArray = Base64.decode(outgoingPushMessage.content)

    return Envelope(
      sourceServiceId = aci.toString(),
      sourceDevice = 1,
      destinationServiceId = to.aci.toString(),
      timestamp = sentTimestamp,
      serverTimestamp = sentTimestamp,
      serverGuid = UUID.randomUUID().toString(),
      type = Envelope.Type.fromValue(outgoingPushMessage.type),
      urgent = true,
      content = encryptedContent.toByteString()
    )
  }

  fun encryptSealedSender(to: SignalClient): Envelope {
    val sentTimestamp = System.currentTimeMillis()

    val content = Content(
      dataMessage = DataMessage(
        body = "Test Message",
        timestamp = sentTimestamp
      )
    )

    val outgoingPushMessage: OutgoingPushMessage = cipher.encrypt(
      SignalProtocolAddress(to.aci.toString(), 1),
      SealedSenderAccess.forIndividual(UnidentifiedAccess(to.unidentifiedAccessKey, senderCertificate.serialized, false)),
      EnvelopeContent.encrypted(content, ContentHint.RESENDABLE, Optional.empty())
    )

    val encryptedContent: ByteArray = Base64.decode(outgoingPushMessage.content)

    return Envelope(
      sourceServiceId = aci.toString(),
      sourceDevice = 1,
      destinationServiceId = to.aci.toString(),
      timestamp = sentTimestamp,
      serverTimestamp = sentTimestamp,
      serverGuid = UUID.randomUUID().toString(),
      type = Envelope.Type.fromValue(outgoingPushMessage.type),
      urgent = true,
      content = encryptedContent.toByteString()
    )
  }

  fun multiEncryptSealedSender(distributionId: DistributionId, others: List<SignalClient>, groupId: Optional<ByteArray>): ByteArray {
    val sentTimestamp = System.currentTimeMillis()

    val content = Content(
      dataMessage = DataMessage(
        body = "Test Message",
        timestamp = sentTimestamp
      )
    )
    val destinations = others.map { bob ->
      SignalProtocolAddress(bob.aci.toString(), 1)
    }

    return cipher.encryptForGroup(distributionId, destinations, null, senderCertificate, content.encode(), ContentHint.DEFAULT, groupId)
  }

  fun decryptMessage(envelope: Envelope) {
    cipher.decrypt(envelope, System.currentTimeMillis())
  }

  private fun createPreKeyBundle(): PreKeyBundle {
    val prekeyId = prekeyIndex++
    val preKeyRecord = PreKeyRecord(prekeyId, Curve.generateKeyPair())
    val signedPreKeyPair = Curve.generateKeyPair()
    val signedPreKeySignature = Curve.calculateSignature(store.identityKeyPair.privateKey, signedPreKeyPair.publicKey.serialize())

    store.storePreKey(prekeyId, preKeyRecord)
    store.storeSignedPreKey(prekeyId, SignedPreKeyRecord(prekeyId, System.currentTimeMillis(), signedPreKeyPair, signedPreKeySignature))

    return PreKeyBundle(prekeyId, prekeyId, prekeyId, preKeyRecord.keyPair.publicKey, prekeyId, signedPreKeyPair.publicKey, signedPreKeySignature, store.identityKeyPair.publicKey)
  }
}

private fun createCertificateFor(trustRoot: ECKeyPair, uuid: UUID, e164: String, deviceId: Int, identityKey: ECPublicKey, expires: Long): SenderCertificate {
  val serverKey: ECKeyPair = Curve.generateKeyPair()
  NativeHandleGuard(serverKey.publicKey).use { serverPublicGuard ->
    NativeHandleGuard(trustRoot.privateKey).use { trustRootPrivateGuard ->
      val serverCertificate = ServerCertificate(Native.ServerCertificate_New(1, serverPublicGuard.nativeHandle(), trustRootPrivateGuard.nativeHandle()))
      NativeHandleGuard(identityKey).use { identityGuard ->
        NativeHandleGuard(serverCertificate).use { serverCertificateGuard ->
          NativeHandleGuard(serverKey.privateKey).use { serverPrivateGuard ->
            return SenderCertificate(Native.SenderCertificate_New(uuid.toString(), e164, deviceId, identityGuard.nativeHandle(), expires, serverCertificateGuard.nativeHandle(), serverPrivateGuard.nativeHandle()))
          }
        }
      }
    }
  }
}

private class TestSessionLock : SignalSessionLock {
  val lock = ReentrantLock()

  override fun acquire(): SignalSessionLock.Lock {
    lock.lock()
    return SignalSessionLock.Lock { lock.unlock() }
  }
}
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
package org.signal.util

import com.google.protobuf.ByteString
import org.signal.libsignal.internal.Native
import org.signal.libsignal.internal.NativeHandleGuard
import org.signal.libsignal.metadata.certificate.CertificateValidator
import org.signal.libsignal.metadata.certificate.SenderCertificate
import org.signal.libsignal.metadata.certificate.ServerCertificate
import org.signal.libsignal.protocol.SessionBuilder
import org.signal.libsignal.protocol.SignalProtocolAddress
import org.signal.libsignal.protocol.ecc.Curve
import org.signal.libsignal.protocol.ecc.ECKeyPair
import org.signal.libsignal.protocol.ecc.ECPublicKey
import org.signal.libsignal.protocol.state.PreKeyBundle
import org.signal.libsignal.protocol.state.PreKeyRecord
import org.signal.libsignal.protocol.state.SignedPreKeyRecord
import org.whispersystems.signalservice.api.SignalServiceAccountDataStore
import org.whispersystems.signalservice.api.SignalSessionLock
import org.whispersystems.signalservice.api.crypto.ContentHint
import org.whispersystems.signalservice.api.crypto.EnvelopeContent
import org.whispersystems.signalservice.api.crypto.SignalServiceCipher
import org.whispersystems.signalservice.api.crypto.UnidentifiedAccess
import org.whispersystems.signalservice.api.push.ServiceId
import org.whispersystems.signalservice.api.push.SignalServiceAddress
import org.whispersystems.signalservice.internal.push.OutgoingPushMessage
import org.whispersystems.signalservice.internal.push.SignalServiceProtos
import org.whispersystems.signalservice.internal.util.Util
import org.whispersystems.util.Base64
import java.util.Optional
import java.util.UUID
import java.util.concurrent.locks.ReentrantLock
import kotlin.random.Random

/**
 * An in-memory signal client that can encrypt and decrypt messages.
 *
 * Has a single prekey bundle that can be used to initialize a session with another client.
 */
class SignalClient {
  companion object {
    private val trustRoot: ECKeyPair = Curve.generateKeyPair()
  }

  private val serviceId: ServiceId = ServiceId.from(UUID.randomUUID())

  private val store: SignalServiceAccountDataStore = InMemorySignalServiceAccountDataStore()

  private val preKeyBundle: PreKeyBundle = let {
    val preKeyRecord = PreKeyRecord(1, Curve.generateKeyPair())
    val signedPreKeyPair = Curve.generateKeyPair()
    val signedPreKeySignature = Curve.calculateSignature(store.identityKeyPair.privateKey, signedPreKeyPair.publicKey.serialize())

    store.storePreKey(1, preKeyRecord)
    store.storeSignedPreKey(1, SignedPreKeyRecord(1, System.currentTimeMillis(), signedPreKeyPair, signedPreKeySignature))

    PreKeyBundle(1, 1, 1, preKeyRecord.keyPair.publicKey, 1, signedPreKeyPair.publicKey, signedPreKeySignature, store.identityKeyPair.publicKey)
  }

  private val unidentifiedAccessKey: ByteArray = Util.getSecretBytes(32)

  private val senderCertificate: SenderCertificate = createCertificateFor(
    trustRoot = trustRoot,
    uuid = serviceId.uuid(),
    e164 = "+${Random.nextLong(1111111111L, 9999999999L)}",
    deviceId = 1,
    identityKey = store.identityKeyPair.publicKey.publicKey,
    expires = Long.MAX_VALUE
  )

  private val cipher = SignalServiceCipher(SignalServiceAddress(serviceId), 1, store, TestSessionLock(), CertificateValidator(trustRoot.publicKey))

  /**
   * Sets up sessions using the [to] client's [preKeyBundle]. Note that you can only initialize a client once
   * since we currently only make a single prekey bundle.
   */
  fun initializeSession(to: SignalClient) {
    val address = SignalProtocolAddress(to.serviceId.toString(), 1)
    SessionBuilder(store, address).process(to.preKeyBundle)
  }

  fun encryptUnsealedSender(to: SignalClient): SignalServiceProtos.Envelope {
    val sentTimestamp = System.currentTimeMillis()

    val message = SignalServiceProtos.DataMessage.newBuilder()
      .setBody("Test Message")
      .setTimestamp(sentTimestamp)
      .build()

    val content = SignalServiceProtos.Content.newBuilder()
      .setDataMessage(message)
      .build()

    val outgoingPushMessage: OutgoingPushMessage = cipher.encrypt(
      SignalProtocolAddress(to.serviceId.toString(), 1),
      Optional.empty(),
      EnvelopeContent.encrypted(content, ContentHint.RESENDABLE, Optional.empty())
    )

    val encryptedContent: ByteArray = Base64.decode(outgoingPushMessage.content)

    return SignalServiceProtos.Envelope.newBuilder()
      .setSourceUuid(serviceId.toString())
      .setSourceDevice(1)
      .setDestinationUuid(to.serviceId.toString())
      .setTimestamp(sentTimestamp)
      .setServerTimestamp(sentTimestamp)
      .setServerGuid(UUID.randomUUID().toString())
      .setType(SignalServiceProtos.Envelope.Type.valueOf(outgoingPushMessage.type))
      .setUrgent(true)
      .setContent(ByteString.copyFrom(encryptedContent))
      .build()
  }

  fun encryptSealedSender(to: SignalClient): SignalServiceProtos.Envelope {
    val sentTimestamp = System.currentTimeMillis()

    val message = SignalServiceProtos.DataMessage.newBuilder()
      .setBody("Test Message")
      .setTimestamp(sentTimestamp)
      .build()

    val content = SignalServiceProtos.Content.newBuilder()
      .setDataMessage(message)
      .build()

    val outgoingPushMessage: OutgoingPushMessage = cipher.encrypt(
      SignalProtocolAddress(to.serviceId.toString(), 1),
      Optional.of(UnidentifiedAccess(to.unidentifiedAccessKey, senderCertificate.serialized)),
      EnvelopeContent.encrypted(content, ContentHint.RESENDABLE, Optional.empty())
    )

    val encryptedContent: ByteArray = Base64.decode(outgoingPushMessage.content)

    return SignalServiceProtos.Envelope.newBuilder()
      .setSourceUuid(serviceId.toString())
      .setSourceDevice(1)
      .setDestinationUuid(to.serviceId.toString())
      .setTimestamp(sentTimestamp)
      .setServerTimestamp(sentTimestamp)
      .setServerGuid(UUID.randomUUID().toString())
      .setType(SignalServiceProtos.Envelope.Type.valueOf(outgoingPushMessage.type))
      .setUrgent(true)
      .setContent(ByteString.copyFrom(encryptedContent))
      .build()
  }

  fun decryptMessage(envelope: SignalServiceProtos.Envelope) {
    cipher.decrypt(envelope, System.currentTimeMillis())
  }
}

private fun createCertificateFor(trustRoot: ECKeyPair, uuid: UUID, e164: String, deviceId: Int, identityKey: ECPublicKey, expires: Long): SenderCertificate {
  val serverKey: ECKeyPair = Curve.generateKeyPair()
  NativeHandleGuard(serverKey.publicKey).use { serverPublicGuard ->
    NativeHandleGuard(trustRoot.privateKey).use { trustRootPrivateGuard ->
      val serverCertificate = ServerCertificate(Native.ServerCertificate_New(1, serverPublicGuard.nativeHandle(), trustRootPrivateGuard.nativeHandle()))
      NativeHandleGuard(identityKey).use { identityGuard ->
        NativeHandleGuard(serverCertificate).use { serverCertificateGuard ->
          NativeHandleGuard(serverKey.privateKey).use { serverPrivateGuard ->
            return SenderCertificate(Native.SenderCertificate_New(uuid.toString(), e164, deviceId, identityGuard.nativeHandle(), expires, serverCertificateGuard.nativeHandle(), serverPrivateGuard.nativeHandle()))
          }
        }
      }
    }
  }
}

private class TestSessionLock : SignalSessionLock {
  val lock = ReentrantLock()

  override fun acquire(): SignalSessionLock.Lock {
    lock.lock()
    return SignalSessionLock.Lock { lock.unlock() }
  }
}
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
