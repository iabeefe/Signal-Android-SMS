package org.thoughtcrime.securesms.database

import android.content.Context
import android.database.Cursor
import androidx.annotation.Discouraged
import androidx.core.content.contentValuesOf
import org.signal.core.util.IntSerializer
import org.signal.core.util.Serializer
import org.signal.core.util.SqlUtil
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import org.signal.core.util.count
import org.signal.core.util.delete
import org.signal.core.util.deleteAll
import org.signal.core.util.exists
import org.signal.core.util.flatten
import org.signal.core.util.insertInto
import org.signal.core.util.isAbsent
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import org.signal.core.util.delete
import org.signal.core.util.flatten
import org.signal.core.util.insertInto
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import org.signal.core.util.delete
import org.signal.core.util.flatten
import org.signal.core.util.insertInto
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import org.signal.core.util.delete
import org.signal.core.util.insertInto
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
import org.signal.core.util.logging.Log
import org.signal.core.util.readToList
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import org.signal.core.util.readToMap
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
import org.signal.core.util.readToMap
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
import org.signal.core.util.readToMap
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
import org.signal.core.util.readToSingleLong
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import org.signal.core.util.readToSingleLong
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import org.signal.core.util.readToSingleLong
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import org.signal.core.util.readToSingleLong
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
import org.signal.core.util.readToSingleObject
import org.signal.core.util.requireBoolean
import org.signal.core.util.requireLong
import org.signal.core.util.requireNonNullString
import org.signal.core.util.requireObject
import org.signal.core.util.requireString
import org.signal.core.util.select
import org.signal.core.util.toInt
import org.signal.core.util.toSingleLine
import org.signal.core.util.update
import org.signal.core.util.withinTransaction
import org.signal.ringrtc.CallId
import org.signal.ringrtc.CallManager.RingUpdate
import org.thoughtcrime.securesms.calls.log.CallLogFilter
import org.thoughtcrime.securesms.calls.log.CallLogRow
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import org.thoughtcrime.securesms.database.model.GroupCallUpdateDetailsUtil
import org.thoughtcrime.securesms.database.model.MessageId
import org.thoughtcrime.securesms.dependencies.AppDependencies
import org.thoughtcrime.securesms.jobs.CallSyncEventJob
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import org.thoughtcrime.securesms.database.model.GroupCallUpdateDetailsUtil
import org.thoughtcrime.securesms.database.model.MessageId
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import org.thoughtcrime.securesms.database.model.GroupCallUpdateDetailsUtil
import org.thoughtcrime.securesms.database.model.MessageId
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies
<<<<<<< HEAD
<<<<<<< HEAD
=======
import org.thoughtcrime.securesms.database.model.GroupCallUpdateDetailsUtil
import org.thoughtcrime.securesms.database.model.MessageId
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies
import org.thoughtcrime.securesms.jobs.CallSyncEventJob
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import org.thoughtcrime.securesms.jobs.CallSyncEventJob
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import org.thoughtcrime.securesms.jobs.CallSyncEventJob
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
import org.thoughtcrime.securesms.recipients.Recipient
import org.thoughtcrime.securesms.recipients.RecipientId
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import org.thoughtcrime.securesms.service.webrtc.links.CallLinkRoomId
import org.whispersystems.signalservice.api.push.ServiceId.ACI
import org.whispersystems.signalservice.internal.push.SyncMessage.CallEvent
import java.util.UUID
import java.util.concurrent.TimeUnit
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import org.whispersystems.signalservice.api.push.ServiceId
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import org.whispersystems.signalservice.api.push.ServiceId
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.SyncMessage.CallEvent
<<<<<<< HEAD
<<<<<<< HEAD
=======
import org.whispersystems.signalservice.api.push.ServiceId
import org.whispersystems.signalservice.internal.push.SignalServiceProtos.SyncMessage.CallEvent
import java.util.UUID
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
import java.util.concurrent.TimeUnit
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import java.util.UUID
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
import java.util.concurrent.TimeUnit
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import java.util.UUID
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)

/**
 * Contains details for each 1:1 call.
 */
class CallTable(context: Context, databaseHelper: SignalDatabase) : DatabaseTable(context, databaseHelper), RecipientIdDatabaseReference {

  companion object {
    private val TAG = Log.tag(CallTable::class.java)
    private val TIME_WINDOW = TimeUnit.HOURS.toMillis(4)

<<<<<<< HEAD
    const val TABLE_NAME = "call"
    const val ID = "_id"
    const val CALL_ID = "call_id"
    const val MESSAGE_ID = "message_id"
    const val PEER = "peer"
    const val TYPE = "type"
    const val DIRECTION = "direction"
    const val EVENT = "event"
    const val TIMESTAMP = "timestamp"
    const val RINGER = "ringer"
    const val DELETION_TIMESTAMP = "deletion_timestamp"
    const val READ = "read"
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    private const val TABLE_NAME = "call"
    private const val ID = "_id"
    private const val CALL_ID = "call_id"
    private const val MESSAGE_ID = "message_id"
    private const val PEER = "peer"
    private const val CALL_LINK = "call_link"
    private const val TYPE = "type"
    private const val DIRECTION = "direction"
    private const val EVENT = "event"
<<<<<<< HEAD
<<<<<<< HEAD
=======
    private const val TABLE_NAME = "call"
    private const val ID = "_id"
    private const val CALL_ID = "call_id"
    private const val MESSAGE_ID = "message_id"
    private const val PEER = "peer"
    private const val CALL_LINK = "call_link"
    private const val TYPE = "type"
    private const val DIRECTION = "direction"
    private const val EVENT = "event"
    private const val TIMESTAMP = "timestamp"
    private const val RINGER = "ringer"
    private const val DELETION_TIMESTAMP = "deletion_timestamp"
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
    private const val TIMESTAMP = "timestamp"
    private const val RINGER = "ringer"
    private const val DELETION_TIMESTAMP = "deletion_timestamp"
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
    private const val TIMESTAMP = "timestamp"
    private const val RINGER = "ringer"
    private const val DELETION_TIMESTAMP = "deletion_timestamp"
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    /**
     * Whether a given call event was joined by the local user
     *
     * Used to determine if a group call in the "GENERIC_GROUP_CALL" state is to be
     * displayed as a missed call in the ui
     */
    const val LOCAL_JOINED = "local_joined"

    /**
     * Whether a given call event is currently considered active.
     *
     * Used to determine if a group call in the "GENERIC_GROUP_CALL" state is to be
     * displayed as a missed call in the ui
     */
    const val GROUP_CALL_ACTIVE = "group_call_active"

    //language=sql
    const val CREATE_TABLE = """
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
    //language=sql
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
    //language=sql
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    val CREATE_TABLE = """
=======
    //language=sql
    val CREATE_TABLE = """
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
      CREATE TABLE $TABLE_NAME (
        $ID INTEGER PRIMARY KEY,
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        $CALL_ID INTEGER NOT NULL,
        $MESSAGE_ID INTEGER DEFAULT NULL REFERENCES ${MessageTable.TABLE_NAME} (${MessageTable.ID}) ON DELETE SET NULL,
        $PEER INTEGER NOT NULL REFERENCES ${RecipientTable.TABLE_NAME} (${RecipientTable.ID}) ON DELETE CASCADE,
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        $CALL_ID INTEGER NOT NULL UNIQUE,
<<<<<<< HEAD
<<<<<<< HEAD
        $MESSAGE_ID INTEGER NOT NULL REFERENCES ${MessageTable.TABLE_NAME} (${MessageTable.ID}) ON DELETE CASCADE,
        $PEER INTEGER NOT NULL REFERENCES ${RecipientTable.TABLE_NAME} (${RecipientTable.ID}) ON DELETE CASCADE,
=======
        $CALL_ID INTEGER NOT NULL UNIQUE,
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        $CALL_ID INTEGER NOT NULL UNIQUE,
=======
        $CALL_ID INTEGER NOT NULL,
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        $CALL_ID INTEGER NOT NULL UNIQUE,
=======
        $CALL_ID INTEGER NOT NULL,
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        $MESSAGE_ID INTEGER DEFAULT NULL REFERENCES ${MessageTable.TABLE_NAME} (${MessageTable.ID}) ON DELETE SET NULL,
        $PEER INTEGER DEFAULT NULL REFERENCES ${RecipientTable.TABLE_NAME} (${RecipientTable.ID}) ON DELETE CASCADE,
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
        $CALL_LINK INTEGER DEFAULT NULL REFERENCES ${CallLinkTable.TABLE_NAME} (${CallLinkTable.ID}) ON DELETE CASCADE,
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        $MESSAGE_ID INTEGER NOT NULL REFERENCES ${MessageTable.TABLE_NAME} (${MessageTable.ID}) ON DELETE CASCADE,
        $PEER INTEGER NOT NULL REFERENCES ${RecipientTable.TABLE_NAME} (${RecipientTable.ID}) ON DELETE CASCADE,
=======
        $MESSAGE_ID INTEGER DEFAULT NULL REFERENCES ${MessageTable.TABLE_NAME} (${MessageTable.ID}) ON DELETE SET NULL,
        $PEER INTEGER DEFAULT NULL REFERENCES ${RecipientTable.TABLE_NAME} (${RecipientTable.ID}) ON DELETE CASCADE,
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
        $CALL_LINK INTEGER DEFAULT NULL REFERENCES ${CallLinkTable.TABLE_NAME} (${CallLinkTable.ID}) ON DELETE CASCADE,
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        $MESSAGE_ID INTEGER NOT NULL REFERENCES ${MessageTable.TABLE_NAME} (${MessageTable.ID}) ON DELETE CASCADE,
        $PEER INTEGER NOT NULL REFERENCES ${RecipientTable.TABLE_NAME} (${RecipientTable.ID}) ON DELETE CASCADE,
=======
        $MESSAGE_ID INTEGER DEFAULT NULL REFERENCES ${MessageTable.TABLE_NAME} (${MessageTable.ID}) ON DELETE SET NULL,
        $PEER INTEGER DEFAULT NULL REFERENCES ${RecipientTable.TABLE_NAME} (${RecipientTable.ID}) ON DELETE CASCADE,
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        $TYPE INTEGER NOT NULL,
        $DIRECTION INTEGER NOT NULL,
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        $EVENT INTEGER NOT NULL,
        $TIMESTAMP INTEGER NOT NULL,
        $RINGER INTEGER DEFAULT NULL,
        $DELETION_TIMESTAMP INTEGER DEFAULT 0,
        $READ INTEGER DEFAULT 1,
        $LOCAL_JOINED INTEGER DEFAULT 0,
        $GROUP_CALL_ACTIVE INTEGER DEFAULT 0,
        UNIQUE ($CALL_ID, $PEER) ON CONFLICT FAIL
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        $EVENT INTEGER NOT NULL
=======
        $EVENT INTEGER NOT NULL,
        $TIMESTAMP INTEGER NOT NULL,
        $RINGER INTEGER DEFAULT NULL,
<<<<<<< HEAD
        $DELETION_TIMESTAMP INTEGER DEFAULT 0
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        $DELETION_TIMESTAMP INTEGER DEFAULT 0
=======
        $DELETION_TIMESTAMP INTEGER DEFAULT 0,
        UNIQUE ($CALL_ID, $PEER, $CALL_LINK) ON CONFLICT FAIL,
        CHECK (($PEER IS NULL AND $CALL_LINK IS NOT NULL) OR ($PEER IS NOT NULL AND $CALL_LINK IS NULL))
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        $EVENT INTEGER NOT NULL
=======
        $EVENT INTEGER NOT NULL,
        $TIMESTAMP INTEGER NOT NULL,
        $RINGER INTEGER DEFAULT NULL,
<<<<<<< HEAD
        $DELETION_TIMESTAMP INTEGER DEFAULT 0
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        $DELETION_TIMESTAMP INTEGER DEFAULT 0
=======
        $DELETION_TIMESTAMP INTEGER DEFAULT 0,
        UNIQUE ($CALL_ID, $PEER, $CALL_LINK) ON CONFLICT FAIL,
        CHECK (($PEER IS NULL AND $CALL_LINK IS NOT NULL) OR ($PEER IS NOT NULL AND $CALL_LINK IS NULL))
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        $EVENT INTEGER NOT NULL
=======
        $EVENT INTEGER NOT NULL,
        $TIMESTAMP INTEGER NOT NULL,
        $RINGER INTEGER DEFAULT NULL,
        $DELETION_TIMESTAMP INTEGER DEFAULT 0
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
      )
    """

    val CREATE_INDEXES = arrayOf(
      "CREATE INDEX call_call_id_index ON $TABLE_NAME ($CALL_ID)",
      "CREATE INDEX call_message_id_index ON $TABLE_NAME ($MESSAGE_ID)",
      "CREATE INDEX call_peer_index ON $TABLE_NAME ($PEER)"
    )
  }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
  fun markAllCallEventsRead(timestamp: Long = Long.MAX_VALUE) {
    writableDatabase.update(TABLE_NAME)
      .values(READ to ReadState.serialize(ReadState.READ))
      .where("$TIMESTAMP <= ?", timestamp)
      .run()

    notifyConversationListListeners()
  }

  fun markAllCallEventsWithPeerBeforeTimestampRead(peer: RecipientId, timestamp: Long): Call? {
    val latestCallAsOfTimestamp = writableDatabase.withinTransaction { db ->
      val updated = db.update(TABLE_NAME)
        .values(READ to ReadState.serialize(ReadState.READ))
        .where("$PEER = ? AND $TIMESTAMP <= ?", peer.toLong(), timestamp)
        .run()

      if (updated == 0) {
        null
      } else {
        db.select()
          .from(TABLE_NAME)
          .where("$PEER = ? AND $TIMESTAMP <= ?", peer.toLong(), timestamp)
          .orderBy("$TIMESTAMP DESC")
          .limit(1)
          .run()
          .readToSingleObject(Call.Deserializer)
      }
    }

    notifyConversationListListeners()
    return latestCallAsOfTimestamp
  }

  fun getUnreadMissedCallCount(): Long {
    return readableDatabase
      .count()
      .from(TABLE_NAME)
      .where("$EVENT = ? AND $READ = ?", Event.serialize(Event.MISSED), ReadState.serialize(ReadState.UNREAD))
      .run()
      .readToSingleLong()
  }

  fun insertOneToOneCall(callId: Long, timestamp: Long, peer: RecipientId, type: Type, direction: Direction, event: Event) {
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
  fun insertCall(callId: Long, timestamp: Long, peer: RecipientId, type: Type, direction: Direction, event: Event) {
=======
  fun insertOneToOneCall(callId: Long, timestamp: Long, peer: RecipientId, type: Type, direction: Direction, event: Event) {
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
  fun insertCall(callId: Long, timestamp: Long, peer: RecipientId, type: Type, direction: Direction, event: Event) {
=======
  fun insertOneToOneCall(callId: Long, timestamp: Long, peer: RecipientId, type: Type, direction: Direction, event: Event) {
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
  fun insertCall(callId: Long, timestamp: Long, peer: RecipientId, type: Type, direction: Direction, event: Event) {
=======
  fun insertOneToOneCall(callId: Long, timestamp: Long, peer: RecipientId, type: Type, direction: Direction, event: Event) {
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    val messageType: Long = Call.getMessageType(type, direction, event)

    writableDatabase.withinTransaction {
<<<<<<< HEAD
<<<<<<< HEAD
      val result = SignalDatabase.messages.insertCallLog(peer, messageType, timestamp, direction == Direction.OUTGOING)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      val result = SignalDatabase.messages.insertCallLog(peer, messageType, timestamp)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      val result = SignalDatabase.messages.insertCallLog(peer, messageType, timestamp)
=======
      val result = SignalDatabase.messages.insertCallLog(peer, messageType, timestamp, direction == Direction.OUTGOING)
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)

=======
      val result = SignalDatabase.messages.insertCallLog(peer, messageType, timestamp, direction == Direction.OUTGOING)

>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      val values = contentValuesOf(
        CALL_ID to callId,
        MESSAGE_ID to result.messageId,
        PEER to peer.serialize(),
        TYPE to Type.serialize(type),
        DIRECTION to Direction.serialize(direction),
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        EVENT to Event.serialize(event),
        TIMESTAMP to timestamp,
        READ to ReadState.serialize(ReadState.UNREAD)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        EVENT to Event.serialize(event)
=======
        EVENT to Event.serialize(event),
        TIMESTAMP to timestamp
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        EVENT to Event.serialize(event)
=======
        EVENT to Event.serialize(event),
        TIMESTAMP to timestamp
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        EVENT to Event.serialize(event)
=======
        EVENT to Event.serialize(event),
        TIMESTAMP to timestamp
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
      )

      writableDatabase.insert(TABLE_NAME, null, values)
    }

<<<<<<< HEAD
    AppDependencies.messageNotifier.updateNotification(context)
    AppDependencies.databaseObserver.notifyCallUpdateObservers()
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    ApplicationDependencies.getMessageNotifier().updateNotification(context)
<<<<<<< HEAD
<<<<<<< HEAD
=======
    ApplicationDependencies.getMessageNotifier().updateNotification(context)
    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)

    Log.i(TAG, "Inserted call: $callId type: $type direction: $direction event:$event")
  }

  fun updateOneToOneCall(callId: Long, event: Event): Call? {
    return writableDatabase.withinTransaction {
      writableDatabase
        .update(TABLE_NAME)
        .values(
          EVENT to Event.serialize(event),
          READ to ReadState.serialize(ReadState.UNREAD)
        )
        .where("$CALL_ID = ?", callId)
        .run()

      val call = readableDatabase
        .select()
        .from(TABLE_NAME)
        .where("$CALL_ID = ?", callId)
        .run()
        .readToSingleObject(Call.Deserializer)

      if (call != null) {
        Log.i(TAG, "Updated call: $callId event: $event")

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        if (call.messageId == null) {
          Log.w(TAG, "Call does not have an associated message id! No message to update.")
        } else {
          SignalDatabase.messages.updateCallLog(call.messageId, call.messageType)
        }

        AppDependencies.messageNotifier.updateNotification(context)
        AppDependencies.databaseObserver.notifyCallUpdateObservers()
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        SignalDatabase.messages.updateCallLog(call.messageId, call.messageType)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        SignalDatabase.messages.updateCallLog(call.messageId, call.messageType)
=======
        SignalDatabase.messages.updateCallLog(call.messageId!!, call.messageType)
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        SignalDatabase.messages.updateCallLog(call.messageId, call.messageType)
=======
        SignalDatabase.messages.updateCallLog(call.messageId!!, call.messageType)
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        ApplicationDependencies.getMessageNotifier().updateNotification(context)
<<<<<<< HEAD
<<<<<<< HEAD
=======
        SignalDatabase.messages.updateCallLog(call.messageId!!, call.messageType)
        ApplicationDependencies.getMessageNotifier().updateNotification(context)
        ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
        ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
        ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
      }

      call
    }
  }

<<<<<<< HEAD
<<<<<<< HEAD
  fun getCallById(callId: Long, recipientId: RecipientId): Call? {
    val query = getCallSelectionQuery(callId, recipientId)

||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  fun getCallById(callId: Long): Call? {
=======
  fun getCallById(callId: Long, conversationId: CallConversationId): Call? {
    val query = getCallSelectionQuery(callId, conversationId)

>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  fun getCallById(callId: Long): Call? {
=======
  fun getCallById(callId: Long, conversationId: CallConversationId): Call? {
    val query = getCallSelectionQuery(callId, conversationId)

>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    return readableDatabase
      .select()
      .from(TABLE_NAME)
      .where(query.where, query.whereArgs)
      .run()
      .readToSingleObject(Call.Deserializer)
  }

  fun getCallByMessageId(messageId: Long): Call? {
    return readableDatabase
      .select()
      .from(TABLE_NAME)
      .where("$MESSAGE_ID = ?", messageId)
      .run()
      .readToSingleObject(Call.Deserializer)
  }

  fun getCalls(messageIds: Collection<Long>): Map<Long, Call> {
    val queries = SqlUtil.buildCollectionQuery(MESSAGE_ID, messageIds)
    val maps = queries.map { query ->
      readableDatabase
        .select()
        .from(TABLE_NAME)
        .where("$EVENT != ${Event.serialize(Event.DELETE)} AND ${query.where}", query.whereArgs)
        .run()
        .readToMap { c -> c.requireLong(MESSAGE_ID) to Call.deserialize(c) }
    }
<<<<<<< HEAD
<<<<<<< HEAD

    return maps.flatten()
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    return calls
=======

    return maps.flatten()
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    return calls
=======

    return maps.flatten()
  }

<<<<<<< HEAD
  /**
   * @param callRowIds The CallTable.ID collection to query
   *
   * @return a map of raw MessageId -> Call
   */
  fun getCallsByRowIds(callRowIds: Collection<Long>): Map<Long, Call> {
    val queries = SqlUtil.buildCollectionQuery(ID, callRowIds)

    val maps = queries.map { query ->
      readableDatabase
        .select()
        .from(TABLE_NAME)
        .where("$EVENT != ${Event.serialize(Event.DELETE)} AND ${query.where}", query.whereArgs)
        .run()
        .readToMap { c -> c.requireLong(MESSAGE_ID) to Call.deserialize(c) }
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
  private fun getCallsCursor(isCount: Boolean, offset: Int, limit: Int, searchTerm: String?, filter: CallLogFilter): Cursor {
    val filterClause = when (filter) {
      CallLogFilter.ALL -> SqlUtil.buildQuery("")
      CallLogFilter.MISSED -> SqlUtil.buildQuery("$EVENT == ${Event.serialize(Event.MISSED)}")
=======
  fun getOldestDeletionTimestamp(): Long {
    return writableDatabase
      .select(DELETION_TIMESTAMP)
      .from(TABLE_NAME)
      .where("$DELETION_TIMESTAMP > 0")
      .orderBy("$DELETION_TIMESTAMP DESC")
      .limit(1)
      .run()
      .readToSingleLong(0L)
  }

  fun deleteCallEventsDeletedBefore(threshold: Long) {
    writableDatabase
      .delete(TABLE_NAME)
      .where("$DELETION_TIMESTAMP <= ?", threshold)
      .run()
  }

  /**
   * If a non-ad-hoc call has been deleted from the message database, then we need to
   * set its deletion_timestamp to now.
   */
  fun updateCallEventDeletionTimestamps() {
    val where = "$TYPE != ? AND $DELETION_TIMESTAMP = 0 AND $MESSAGE_ID IS NULL"
    val type = Type.serialize(Type.AD_HOC_CALL)

    val toSync = writableDatabase.withinTransaction { db ->
      val result = db
        .select()
        .from(TABLE_NAME)
        .where(where, type)
        .run()
        .readToList {
          Call.deserialize(it)
        }
        .toSet()

      db
        .update(TABLE_NAME)
        .values(DELETION_TIMESTAMP to System.currentTimeMillis())
        .where(where, type)
        .run()

      result
    }

    CallSyncEventJob.enqueueDeleteSyncEvents(toSync)
    ApplicationDependencies.getDeletedCallEventManager().scheduleIfNecessary()
    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
  }

  // region Group / Ad-Hoc Calling

  fun deleteGroupCall(call: Call) {
    checkIsGroupOrAdHocCall(call)

    writableDatabase.withinTransaction { db ->
      db
        .update(TABLE_NAME)
        .values(
          EVENT to Event.serialize(Event.DELETE),
          DELETION_TIMESTAMP to System.currentTimeMillis()
        )
        .where("$CALL_ID = ?", call.callId)
        .run()

      if (call.messageId != null) {
        SignalDatabase.messages.deleteMessage(call.messageId)
      }
    }

    ApplicationDependencies.getMessageNotifier().updateNotification(context)
    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
    Log.d(TAG, "Marked group call event for deletion: ${call.callId}")
  }

  fun insertDeletedGroupCallFromSyncEvent(
    callId: Long,
    recipientId: RecipientId?,
    direction: Direction,
    timestamp: Long
  ) {
    val type = if (recipientId != null) Type.GROUP_CALL else Type.AD_HOC_CALL

    writableDatabase
      .insertInto(TABLE_NAME)
      .values(
        CALL_ID to callId,
        MESSAGE_ID to null,
        PEER to recipientId?.toLong(),
        EVENT to Event.serialize(Event.DELETE),
        TYPE to Type.serialize(type),
        DIRECTION to Direction.serialize(direction),
        TIMESTAMP to timestamp,
        DELETION_TIMESTAMP to System.currentTimeMillis()
      )
      .run()

    ApplicationDependencies.getDeletedCallEventManager().scheduleIfNecessary()
  }

  fun acceptIncomingGroupCall(call: Call) {
    checkIsGroupOrAdHocCall(call)
    check(call.direction == Direction.INCOMING)

    val newEvent = when (call.event) {
      Event.RINGING, Event.MISSED, Event.DECLINED -> Event.ACCEPTED
      Event.GENERIC_GROUP_CALL -> Event.JOINED
      else -> {
        Log.d(TAG, "Call in state ${call.event} cannot be transitioned by ACCEPTED")
        return
      }
    }

    writableDatabase
      .update(TABLE_NAME)
      .values(EVENT to Event.serialize(newEvent))
      .run()

    ApplicationDependencies.getMessageNotifier().updateNotification(context)
    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
    Log.d(TAG, "Transitioned group call ${call.callId} from ${call.event} to $newEvent")
  }

  fun insertAcceptedGroupCall(
    callId: Long,
    recipientId: RecipientId?,
    direction: Direction,
    timestamp: Long
  ) {
    val type = if (recipientId != null) Type.GROUP_CALL else Type.AD_HOC_CALL
    val event = if (direction == Direction.OUTGOING) Event.OUTGOING_RING else Event.JOINED
    val ringer = if (direction == Direction.OUTGOING) Recipient.self().id.toLong() else null

    writableDatabase.withinTransaction { db ->
      val messageId: MessageId? = if (recipientId != null) {
        SignalDatabase.messages.insertGroupCall(
          groupRecipientId = recipientId,
          sender = Recipient.self().id,
          timestamp,
          "",
          emptyList(),
          false
        )
      } else {
        null
      }

      db
        .insertInto(TABLE_NAME)
        .values(
          CALL_ID to callId,
          MESSAGE_ID to messageId?.id,
          PEER to recipientId?.toLong(),
          EVENT to Event.serialize(event),
          TYPE to Type.serialize(type),
          DIRECTION to Direction.serialize(direction),
          TIMESTAMP to timestamp,
          RINGER to ringer
        )
        .run()
    }

    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
  }

  fun insertOrUpdateGroupCallFromExternalEvent(
    groupRecipientId: RecipientId,
    sender: RecipientId,
    timestamp: Long,
    messageGroupCallEraId: String?
  ) {
    insertOrUpdateGroupCallFromLocalEvent(
      groupRecipientId,
      sender,
      timestamp,
      messageGroupCallEraId,
      emptyList(),
      false
    )
  }

  fun insertOrUpdateGroupCallFromLocalEvent(
    groupRecipientId: RecipientId,
    sender: RecipientId,
    timestamp: Long,
    peekGroupCallEraId: String?,
    peekJoinedUuids: Collection<UUID>,
    isCallFull: Boolean
  ) {
    writableDatabase.withinTransaction {
      if (peekGroupCallEraId.isNullOrEmpty()) {
        Log.w(TAG, "Dropping local call event with null era id.")
        return@withinTransaction
      }

      val callId = CallId.fromEra(peekGroupCallEraId).longValue()
      val call = getCallById(callId)
      val messageId: MessageId = if (call != null) {
        if (call.event == Event.DELETE) {
          Log.d(TAG, "Dropping group call update for deleted call.")
          return@withinTransaction
        }

        if (call.type != Type.GROUP_CALL) {
          Log.d(TAG, "Dropping unsupported update message for non-group-call call.")
          return@withinTransaction
        }

        if (call.messageId == null) {
          Log.d(TAG, "Dropping group call update for call without an attached message.")
          return@withinTransaction
        }

        SignalDatabase.messages.updateGroupCall(
          call.messageId,
          peekGroupCallEraId,
          peekJoinedUuids,
          isCallFull
        )
      } else {
        SignalDatabase.messages.insertGroupCall(
          groupRecipientId,
          sender,
          timestamp,
          peekGroupCallEraId,
          peekJoinedUuids,
          isCallFull
        )
      }

      insertCallEventFromGroupUpdate(
        callId,
        messageId,
        sender,
        groupRecipientId,
        timestamp
      )
    }
  }

  private fun insertCallEventFromGroupUpdate(
    callId: Long,
    messageId: MessageId?,
    sender: RecipientId,
    groupRecipientId: RecipientId,
    timestamp: Long
  ) {
    if (messageId != null) {
      val call = getCallById(callId)
      if (call == null) {
        val direction = if (sender == Recipient.self().id) Direction.OUTGOING else Direction.INCOMING

        writableDatabase
          .insertInto(TABLE_NAME)
          .values(
            CALL_ID to callId,
            MESSAGE_ID to messageId.id,
            PEER to groupRecipientId.toLong(),
            EVENT to Event.serialize(Event.GENERIC_GROUP_CALL),
            TYPE to Type.serialize(Type.GROUP_CALL),
            DIRECTION to Direction.serialize(direction),
            TIMESTAMP to timestamp,
            RINGER to null
          )
          .run()

        Log.d(TAG, "Inserted new call event from group call update message. Call Id: $callId")
      } else {
        if (timestamp < call.timestamp) {
          setTimestamp(callId, timestamp)
          Log.d(TAG, "Updated call event timestamp for call id $callId")
        }

        if (call.messageId == null) {
          setMessageId(callId, messageId)
          Log.d(TAG, "Updated call event message id for newly inserted group call state: $callId")
        }
      }
    } else {
      Log.d(TAG, "Skipping call event processing for null era id.")
    }

    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
  }

  /**
   * Since this does not alter the call table, we can simply pass this directly through to the old handler.
   */
  fun updateGroupCallFromPeek(
    threadId: Long,
    peekGroupCallEraId: String?,
    peekJoinedUuids: Collection<UUID>,
    isCallFull: Boolean
  ): Boolean {
    val sameEraId = SignalDatabase.messages.updatePreviousGroupCall(threadId, peekGroupCallEraId, peekJoinedUuids, isCallFull)
    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
    return sameEraId
  }

  fun insertOrUpdateGroupCallFromRingState(
    ringId: Long,
    groupRecipientId: RecipientId,
    ringerRecipient: RecipientId,
    dateReceived: Long,
    ringState: RingUpdate
  ) {
    handleGroupRingState(ringId, groupRecipientId, ringerRecipient, dateReceived, ringState)
  }

  fun insertOrUpdateGroupCallFromRingState(
    ringId: Long,
    groupRecipientId: RecipientId,
    ringerUUID: UUID,
    dateReceived: Long,
    ringState: RingUpdate
  ) {
    val ringerRecipient = Recipient.externalPush(ServiceId.from(ringerUUID))
    handleGroupRingState(ringId, groupRecipientId, ringerRecipient.id, dateReceived, ringState)
  }

  fun isRingCancelled(ringId: Long): Boolean {
    val call = getCallById(ringId) ?: return false
    return call.event != Event.RINGING
  }

  private fun handleGroupRingState(
    ringId: Long,
    groupRecipientId: RecipientId,
    ringerRecipient: RecipientId,
    dateReceived: Long,
    ringState: RingUpdate
  ) {
    val call = getCallById(ringId)
    if (call != null) {
      if (call.event == Event.DELETE) {
        Log.d(TAG, "Ignoring ring request for $ringId since its event has been deleted.")
        return
      }

      when (ringState) {
        RingUpdate.REQUESTED -> {
          when (call.event) {
            Event.GENERIC_GROUP_CALL -> updateEventFromRingState(ringId, Event.RINGING, ringerRecipient)
            Event.JOINED -> updateEventFromRingState(ringId, Event.ACCEPTED, ringerRecipient)
            else -> Log.w(TAG, "Received a REQUESTED ring event while in ${call.event}. Ignoring.")
          }
        }
        RingUpdate.EXPIRED_REQUEST, RingUpdate.CANCELLED_BY_RINGER -> {
          when (call.event) {
            Event.GENERIC_GROUP_CALL, Event.RINGING -> updateEventFromRingState(ringId, Event.MISSED, ringerRecipient)
            Event.OUTGOING_RING -> Log.w(TAG, "Received an expiration or cancellation while in OUTGOING_RING state. Ignoring.")
            else -> Unit
          }
        }
        RingUpdate.BUSY_LOCALLY, RingUpdate.BUSY_ON_ANOTHER_DEVICE -> {
          when (call.event) {
            Event.JOINED -> updateEventFromRingState(ringId, Event.ACCEPTED)
            Event.GENERIC_GROUP_CALL, Event.RINGING -> updateEventFromRingState(ringId, Event.MISSED)
            else -> Log.w(TAG, "Received a busy event we can't process. Ignoring.")
          }
        }
        RingUpdate.ACCEPTED_ON_ANOTHER_DEVICE -> {
          updateEventFromRingState(ringId, Event.ACCEPTED)
        }
        RingUpdate.DECLINED_ON_ANOTHER_DEVICE -> {
          when (call.event) {
            Event.RINGING, Event.MISSED -> updateEventFromRingState(ringId, Event.DECLINED)
            Event.OUTGOING_RING -> Log.w(TAG, "Received DECLINED_ON_ANOTHER_DEVICE while in OUTGOING_RING state.")
            else -> Unit
          }
        }
      }
    } else {
      val event: Event = when (ringState) {
        RingUpdate.REQUESTED -> Event.RINGING
        RingUpdate.EXPIRED_REQUEST -> Event.MISSED
        RingUpdate.ACCEPTED_ON_ANOTHER_DEVICE -> {
          Log.w(TAG, "Missed original ring request for $ringId")
          Event.ACCEPTED
        }
        RingUpdate.DECLINED_ON_ANOTHER_DEVICE -> {
          Log.w(TAG, "Missed original ring request for $ringId")
          Event.DECLINED
        }
        RingUpdate.BUSY_LOCALLY, RingUpdate.BUSY_ON_ANOTHER_DEVICE -> {
          Log.w(TAG, "Missed original ring request for $ringId")
          Event.MISSED
        }
        RingUpdate.CANCELLED_BY_RINGER -> {
          Log.w(TAG, "Missed original ring request for $ringId")
          Event.MISSED
        }
      }

      createEventFromRingState(ringId, groupRecipientId, ringerRecipient, event, dateReceived)
    }

    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
  }

  private fun updateEventFromRingState(
    callId: Long,
    event: Event,
    ringerRecipient: RecipientId
  ) {
    writableDatabase
      .update(TABLE_NAME)
      .values(
        EVENT to Event.serialize(event),
        RINGER to ringerRecipient.serialize()
      )
      .where("$CALL_ID = ?", callId)
      .run()

    Log.d(TAG, "Updated ring state to $event")
  }

  private fun updateEventFromRingState(
    callId: Long,
    event: Event
  ) {
    writableDatabase
      .update(TABLE_NAME)
      .values(
        EVENT to Event.serialize(event)
      )
      .where("$CALL_ID = ?", callId)
      .run()

    Log.d(TAG, "Updated ring state to $event")
  }

  private fun createEventFromRingState(
    callId: Long,
    groupRecipientId: RecipientId,
    ringerRecipient: RecipientId,
    event: Event,
    timestamp: Long
  ) {
    val direction = if (ringerRecipient == Recipient.self().id) Direction.OUTGOING else Direction.INCOMING

    writableDatabase.withinTransaction { db ->
      val messageId = SignalDatabase.messages.insertGroupCall(
        groupRecipientId = groupRecipientId,
        sender = ringerRecipient,
        timestamp = timestamp,
        eraId = "",
        joinedUuids = emptyList(),
        isCallFull = false
      )

      db
        .insertInto(TABLE_NAME)
        .values(
          CALL_ID to callId,
          MESSAGE_ID to messageId.id,
          PEER to groupRecipientId.toLong(),
          EVENT to Event.serialize(event),
          TYPE to Type.serialize(Type.GROUP_CALL),
          DIRECTION to Direction.serialize(direction),
          TIMESTAMP to timestamp,
          RINGER to ringerRecipient.toLong()
        )
        .run()
    }

    Log.d(TAG, "Inserted a new call event for $callId with event $event")
  }

  fun setTimestamp(callId: Long, timestamp: Long) {
    writableDatabase.withinTransaction { db ->
      val call = getCallById(callId)
      if (call == null || call.event == Event.DELETE) {
        Log.d(TAG, "Refusing to update deleted call event.")
        return@withinTransaction
      }

      db
        .update(TABLE_NAME)
        .values(TIMESTAMP to timestamp)
        .where("$CALL_ID = ?", callId)
        .run()

      if (call.messageId != null) {
        SignalDatabase.messages.updateCallTimestamps(call.messageId, timestamp)
      }
    }

    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
  }

  private fun setMessageId(callId: Long, messageId: MessageId) {
    writableDatabase
      .update(TABLE_NAME)
      .values(MESSAGE_ID to messageId.id)
      .where("$CALL_ID = ?", callId)
      .run()
  }

  fun deleteCallEvents(callIds: Set<Long>) {
    val messageIds = getMessageIds(callIds)
    SignalDatabase.messages.deleteCallUpdates(messageIds)
    updateCallEventDeletionTimestamps()
  }

  fun deleteAllCallEventsExcept(callIds: Set<Long>) {
    val messageIds = getMessageIds(callIds)
    SignalDatabase.messages.deleteAllCallUpdatesExcept(messageIds)
    updateCallEventDeletionTimestamps()
  }

  @Discouraged("Using this method is generally considered an error. Utilize other deletion methods instead of this.")
  fun deleteAllCalls() {
    Log.w(TAG, "Deleting all calls from the local database.")
    writableDatabase
      .delete(TABLE_NAME)
      .run()
  }

  private fun getMessageIds(callIds: Set<Long>): Set<Long> {
    val queries = SqlUtil.buildCollectionQuery(
      CALL_ID,
      callIds,
      "$MESSAGE_ID NOT NULL AND"
    )

    return queries.map { query ->
      readableDatabase.select(MESSAGE_ID).from(TABLE_NAME).where(query.where, query.whereArgs).run().readToList {
        it.requireLong(MESSAGE_ID)
      }
    }.flatten().toSet()
  }

  private fun checkIsGroupOrAdHocCall(call: Call) {
    check(call.type == Type.GROUP_CALL || call.type == Type.AD_HOC_CALL)
  }

  // endregion

  private fun getCallsCursor(isCount: Boolean, offset: Int, limit: Int, searchTerm: String?, filter: CallLogFilter): Cursor {
    val filterClause = when (filter) {
      CallLogFilter.ALL -> SqlUtil.buildQuery("$EVENT != ${Event.serialize(Event.DELETE)}")
      CallLogFilter.MISSED -> SqlUtil.buildQuery("$EVENT == ${Event.serialize(Event.MISSED)}")
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    }

    return maps.flatten()
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  }

<<<<<<< HEAD
  /**
   * @param callRowIds The CallTable.ID collection to query
   *
   * @return a map of raw MessageId -> Call
   */
  fun getCallsByRowIds(callRowIds: Collection<Long>): Map<Long, Call> {
    val queries = SqlUtil.buildCollectionQuery(ID, callRowIds)

    val maps = queries.map { query ->
      readableDatabase
        .select()
        .from(TABLE_NAME)
        .where("$EVENT != ${Event.serialize(Event.DELETE)} AND ${query.where}", query.whereArgs)
        .run()
        .readToMap { c -> c.requireLong(MESSAGE_ID) to Call.deserialize(c) }
    }

    return maps.flatten()
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  }

<<<<<<< HEAD
  /**
   * @param callRowIds The CallTable.ID collection to query
   *
   * @return a map of raw MessageId -> Call
   */
  fun getCallsByRowIds(callRowIds: Collection<Long>): Map<Long, Call> {
    val queries = SqlUtil.buildCollectionQuery(ID, callRowIds)

    val maps = queries.map { query ->
      readableDatabase
        .select()
        .from(TABLE_NAME)
        .where("$EVENT != ${Event.serialize(Event.DELETE)} AND ${query.where}", query.whereArgs)
        .run()
        .readToMap { c -> c.requireLong(MESSAGE_ID) to Call.deserialize(c) }
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
  fun getOldestDeletionTimestamp(): Long {
    return writableDatabase
      .select(DELETION_TIMESTAMP)
      .from(TABLE_NAME)
      .where("$DELETION_TIMESTAMP > 0")
      .orderBy("$DELETION_TIMESTAMP DESC")
      .limit(1)
      .run()
      .readToSingleLong(0L)
  }

  fun deleteCallEventsDeletedBefore(threshold: Long): Int {
    return writableDatabase
      .delete(TABLE_NAME)
      .where("$DELETION_TIMESTAMP > 0 AND $DELETION_TIMESTAMP <= ?", threshold)
      .run()
  }

  /**
   * If a non-ad-hoc call has been deleted from the message database, then we need to
   * set its deletion_timestamp to now.
   */
  fun updateCallEventDeletionTimestamps() {
    val where = "$TYPE != ? AND $DELETION_TIMESTAMP = 0 AND $MESSAGE_ID IS NULL"
    val type = Type.serialize(Type.AD_HOC_CALL)

    val toSync = writableDatabase.withinTransaction { db ->
      val result = db
        .select()
        .from(TABLE_NAME)
        .where(where, type)
        .run()
        .readToList {
          Call.deserialize(it)
        }
        .toSet()

      db
        .update(TABLE_NAME)
        .values(
          EVENT to Event.serialize(Event.DELETE),
          DELETION_TIMESTAMP to System.currentTimeMillis()
        )
        .where(where, type)
        .run()

      result
    }

    CallSyncEventJob.enqueueDeleteSyncEvents(toSync)
    ApplicationDependencies.getDeletedCallEventManager().scheduleIfNecessary()
    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
  }

  // region Group / Ad-Hoc Calling

  fun deleteGroupCall(call: Call) {
    checkIsGroupOrAdHocCall(call)

    writableDatabase.withinTransaction { db ->
      db
        .update(TABLE_NAME)
        .values(
          EVENT to Event.serialize(Event.DELETE),
          DELETION_TIMESTAMP to System.currentTimeMillis()
        )
        .where("$CALL_ID = ? AND $PEER = ?", call.callId, call.peer)
        .run()

      if (call.messageId != null) {
        SignalDatabase.messages.deleteMessage(call.messageId)
      }
    }

    ApplicationDependencies.getMessageNotifier().updateNotification(context)
    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
    Log.d(TAG, "Marked group call event for deletion: ${call.callId}")
  }

  fun insertDeletedGroupCallFromSyncEvent(
    callId: Long,
    recipientId: RecipientId?,
    direction: Direction,
    timestamp: Long
  ) {
    val type = if (recipientId != null) Type.GROUP_CALL else Type.AD_HOC_CALL

    writableDatabase
      .insertInto(TABLE_NAME)
      .values(
        CALL_ID to callId,
        MESSAGE_ID to null,
        PEER to recipientId?.toLong(),
        EVENT to Event.serialize(Event.DELETE),
        TYPE to Type.serialize(type),
        DIRECTION to Direction.serialize(direction),
        TIMESTAMP to timestamp,
        DELETION_TIMESTAMP to System.currentTimeMillis()
      )
      .run()

    ApplicationDependencies.getDeletedCallEventManager().scheduleIfNecessary()
  }

  fun acceptIncomingGroupCall(call: Call) {
    checkIsGroupOrAdHocCall(call)
    check(call.direction == Direction.INCOMING)

    val newEvent = when (call.event) {
      Event.RINGING, Event.MISSED, Event.DECLINED -> Event.ACCEPTED
      Event.GENERIC_GROUP_CALL -> Event.JOINED
      else -> {
        Log.d(TAG, "Call in state ${call.event} cannot be transitioned by ACCEPTED")
        return
      }
    }

    writableDatabase
      .update(TABLE_NAME)
      .values(EVENT to Event.serialize(newEvent))
      .run()

    ApplicationDependencies.getMessageNotifier().updateNotification(context)
    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
    Log.d(TAG, "Transitioned group call ${call.callId} from ${call.event} to $newEvent")
  }

  fun insertAcceptedGroupCall(
    callId: Long,
    recipientId: RecipientId?,
    direction: Direction,
    timestamp: Long
  ) {
    val type = if (recipientId != null) Type.GROUP_CALL else Type.AD_HOC_CALL
    val event = if (direction == Direction.OUTGOING) Event.OUTGOING_RING else Event.JOINED
    val ringer = if (direction == Direction.OUTGOING) Recipient.self().id.toLong() else null

    writableDatabase.withinTransaction { db ->
      val messageId: MessageId? = if (recipientId != null) {
        SignalDatabase.messages.insertGroupCall(
          groupRecipientId = recipientId,
          sender = Recipient.self().id,
          timestamp,
          "",
          emptyList(),
          false
        )
      } else {
        null
      }

      db
        .insertInto(TABLE_NAME)
        .values(
          CALL_ID to callId,
          MESSAGE_ID to messageId?.id,
          PEER to recipientId?.toLong(),
          EVENT to Event.serialize(event),
          TYPE to Type.serialize(type),
          DIRECTION to Direction.serialize(direction),
          TIMESTAMP to timestamp,
          RINGER to ringer
        )
        .run()
    }

    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
  }

  fun insertOrUpdateGroupCallFromExternalEvent(
    groupRecipientId: RecipientId,
    sender: RecipientId,
    timestamp: Long,
    messageGroupCallEraId: String?
  ) {
    insertOrUpdateGroupCallFromLocalEvent(
      groupRecipientId,
      sender,
      timestamp,
      messageGroupCallEraId,
      emptyList(),
      false
    )
  }

  fun insertOrUpdateGroupCallFromLocalEvent(
    groupRecipientId: RecipientId,
    sender: RecipientId,
    timestamp: Long,
    peekGroupCallEraId: String?,
    peekJoinedUuids: Collection<UUID>,
    isCallFull: Boolean
  ) {
    writableDatabase.withinTransaction {
      if (peekGroupCallEraId.isNullOrEmpty()) {
        Log.w(TAG, "Dropping local call event with null era id.")
        return@withinTransaction
      }

      val callId = CallId.fromEra(peekGroupCallEraId).longValue()
      val call = getCallById(callId, CallConversationId.Peer(groupRecipientId))
      val messageId: MessageId = if (call != null) {
        if (call.event == Event.DELETE) {
          Log.d(TAG, "Dropping group call update for deleted call.")
          return@withinTransaction
        }

        if (call.type != Type.GROUP_CALL) {
          Log.d(TAG, "Dropping unsupported update message for non-group-call call.")
          return@withinTransaction
        }

        if (call.messageId == null) {
          Log.d(TAG, "Dropping group call update for call without an attached message.")
          return@withinTransaction
        }

        SignalDatabase.messages.updateGroupCall(
          call.messageId,
          peekGroupCallEraId,
          peekJoinedUuids,
          isCallFull
        )
      } else {
        SignalDatabase.messages.insertGroupCall(
          groupRecipientId,
          sender,
          timestamp,
          peekGroupCallEraId,
          peekJoinedUuids,
          isCallFull
        )
      }

      insertCallEventFromGroupUpdate(
        callId,
        messageId,
        sender,
        groupRecipientId,
        timestamp
      )
    }
  }

  private fun insertCallEventFromGroupUpdate(
    callId: Long,
    messageId: MessageId?,
    sender: RecipientId,
    groupRecipientId: RecipientId,
    timestamp: Long
  ) {
    val conversationId = CallConversationId.Peer(groupRecipientId)
    if (messageId != null) {
      val call = getCallById(callId, conversationId)
      if (call == null) {
        val direction = if (sender == Recipient.self().id) Direction.OUTGOING else Direction.INCOMING

        writableDatabase
          .insertInto(TABLE_NAME)
          .values(
            CALL_ID to callId,
            MESSAGE_ID to messageId.id,
            PEER to groupRecipientId.toLong(),
            EVENT to Event.serialize(Event.GENERIC_GROUP_CALL),
            TYPE to Type.serialize(Type.GROUP_CALL),
            DIRECTION to Direction.serialize(direction),
            TIMESTAMP to timestamp,
            RINGER to null
          )
          .run()

        Log.d(TAG, "Inserted new call event from group call update message. Call Id: $callId")
      } else {
        if (timestamp < call.timestamp) {
          setTimestamp(callId, conversationId, timestamp)
          Log.d(TAG, "Updated call event timestamp for call id $callId")
        }

        if (call.messageId == null) {
          setMessageId(callId, messageId)
          Log.d(TAG, "Updated call event message id for newly inserted group call state: $callId")
        }
      }
    } else {
      Log.d(TAG, "Skipping call event processing for null era id.")
    }

    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
  }

  /**
   * Since this does not alter the call table, we can simply pass this directly through to the old handler.
   */
  fun updateGroupCallFromPeek(
    threadId: Long,
    peekGroupCallEraId: String?,
    peekJoinedUuids: Collection<UUID>,
    isCallFull: Boolean
  ): Boolean {
    val sameEraId = SignalDatabase.messages.updatePreviousGroupCall(threadId, peekGroupCallEraId, peekJoinedUuids, isCallFull)
    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
    return sameEraId
  }

  fun insertOrUpdateGroupCallFromRingState(
    ringId: Long,
    groupRecipientId: RecipientId,
    ringerRecipient: RecipientId,
    dateReceived: Long,
    ringState: RingUpdate
  ) {
    handleGroupRingState(ringId, groupRecipientId, ringerRecipient, dateReceived, ringState)
  }

  fun insertOrUpdateGroupCallFromRingState(
    ringId: Long,
    groupRecipientId: RecipientId,
    ringerUUID: UUID,
    dateReceived: Long,
    ringState: RingUpdate
  ) {
    val ringerRecipient = Recipient.externalPush(ServiceId.from(ringerUUID))
    handleGroupRingState(ringId, groupRecipientId, ringerRecipient.id, dateReceived, ringState)
  }

  fun isRingCancelled(ringId: Long, groupRecipientId: RecipientId): Boolean {
    val call = getCallById(ringId, CallConversationId.Peer(groupRecipientId)) ?: return false
    return call.event != Event.RINGING
  }

  private fun handleGroupRingState(
    ringId: Long,
    groupRecipientId: RecipientId,
    ringerRecipient: RecipientId,
    dateReceived: Long,
    ringState: RingUpdate
  ) {
    Log.d(TAG, "Processing group ring state update for $ringId in state $ringState")

    val call = getCallById(ringId, CallConversationId.Peer(groupRecipientId))
    if (call != null) {
      if (call.event == Event.DELETE) {
        Log.d(TAG, "Ignoring ring request for $ringId since its event has been deleted.")
        return
      }

      when (ringState) {
        RingUpdate.REQUESTED -> {
          when (call.event) {
            Event.GENERIC_GROUP_CALL -> updateEventFromRingState(ringId, Event.RINGING, ringerRecipient)
            Event.JOINED -> updateEventFromRingState(ringId, Event.ACCEPTED, ringerRecipient)
            else -> Log.w(TAG, "Received a REQUESTED ring event while in ${call.event}. Ignoring.")
          }
        }
        RingUpdate.EXPIRED_REQUEST, RingUpdate.CANCELLED_BY_RINGER -> {
          when (call.event) {
            Event.GENERIC_GROUP_CALL, Event.RINGING -> updateEventFromRingState(ringId, Event.MISSED, ringerRecipient)
            Event.OUTGOING_RING -> Log.w(TAG, "Received an expiration or cancellation while in OUTGOING_RING state. Ignoring.")
            else -> Unit
          }
        }
        RingUpdate.BUSY_LOCALLY, RingUpdate.BUSY_ON_ANOTHER_DEVICE -> {
          when (call.event) {
            Event.JOINED -> updateEventFromRingState(ringId, Event.ACCEPTED)
            Event.GENERIC_GROUP_CALL, Event.RINGING -> updateEventFromRingState(ringId, Event.MISSED)
            else -> Log.w(TAG, "Received a busy event we can't process. Ignoring.")
          }
        }
        RingUpdate.ACCEPTED_ON_ANOTHER_DEVICE -> {
          updateEventFromRingState(ringId, Event.ACCEPTED)
        }
        RingUpdate.DECLINED_ON_ANOTHER_DEVICE -> {
          when (call.event) {
            Event.RINGING, Event.MISSED -> updateEventFromRingState(ringId, Event.DECLINED)
            Event.OUTGOING_RING -> Log.w(TAG, "Received DECLINED_ON_ANOTHER_DEVICE while in OUTGOING_RING state.")
            else -> Unit
          }
        }
      }
    } else {
      val event: Event = when (ringState) {
        RingUpdate.REQUESTED -> Event.RINGING
        RingUpdate.EXPIRED_REQUEST -> Event.MISSED
        RingUpdate.ACCEPTED_ON_ANOTHER_DEVICE -> {
          Log.w(TAG, "Missed original ring request for $ringId")
          Event.ACCEPTED
        }
        RingUpdate.DECLINED_ON_ANOTHER_DEVICE -> {
          Log.w(TAG, "Missed original ring request for $ringId")
          Event.DECLINED
        }
        RingUpdate.BUSY_LOCALLY, RingUpdate.BUSY_ON_ANOTHER_DEVICE -> {
          Log.w(TAG, "Missed original ring request for $ringId")
          Event.MISSED
        }
        RingUpdate.CANCELLED_BY_RINGER -> {
          Log.w(TAG, "Missed original ring request for $ringId")
          Event.MISSED
        }
      }

      createEventFromRingState(ringId, groupRecipientId, ringerRecipient, event, dateReceived)
    }

    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
  }

  private fun updateEventFromRingState(
    callId: Long,
    event: Event,
    ringerRecipient: RecipientId
  ) {
    writableDatabase
      .update(TABLE_NAME)
      .values(
        EVENT to Event.serialize(event),
        RINGER to ringerRecipient.serialize()
      )
      .where("$CALL_ID = ?", callId)
      .run()

    Log.d(TAG, "Updated ring state to $event")
  }

  private fun updateEventFromRingState(
    callId: Long,
    event: Event
  ) {
    writableDatabase
      .update(TABLE_NAME)
      .values(
        EVENT to Event.serialize(event)
      )
      .where("$CALL_ID = ?", callId)
      .run()

    Log.d(TAG, "Updated ring state to $event")
  }

  private fun createEventFromRingState(
    callId: Long,
    groupRecipientId: RecipientId,
    ringerRecipient: RecipientId,
    event: Event,
    timestamp: Long
  ) {
    val direction = if (ringerRecipient == Recipient.self().id) Direction.OUTGOING else Direction.INCOMING

    writableDatabase.withinTransaction { db ->
      val messageId = SignalDatabase.messages.insertGroupCall(
        groupRecipientId = groupRecipientId,
        sender = ringerRecipient,
        timestamp = timestamp,
        eraId = "",
        joinedUuids = emptyList(),
        isCallFull = false
      )

      db
        .insertInto(TABLE_NAME)
        .values(
          CALL_ID to callId,
          MESSAGE_ID to messageId.id,
          PEER to groupRecipientId.toLong(),
          EVENT to Event.serialize(event),
          TYPE to Type.serialize(Type.GROUP_CALL),
          DIRECTION to Direction.serialize(direction),
          TIMESTAMP to timestamp,
          RINGER to ringerRecipient.toLong()
        )
        .run()
    }

    Log.d(TAG, "Inserted a new group ring event for $callId with event $event")
  }

  fun setTimestamp(callId: Long, conversationId: CallConversationId, timestamp: Long) {
    writableDatabase.withinTransaction { db ->
      val call = getCallById(callId, conversationId)
      if (call == null || call.event == Event.DELETE) {
        Log.d(TAG, "Refusing to update deleted call event.")
        return@withinTransaction
      }

      db
        .update(TABLE_NAME)
        .values(TIMESTAMP to timestamp)
        .where("$CALL_ID = ?", callId)
        .run()

      if (call.messageId != null) {
        SignalDatabase.messages.updateCallTimestamps(call.messageId, timestamp)
      }
    }

    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
  }

  private fun setMessageId(callId: Long, messageId: MessageId) {
    writableDatabase
      .update(TABLE_NAME)
      .values(MESSAGE_ID to messageId.id)
      .where("$CALL_ID = ?", callId)
      .run()
  }

  fun deleteCallEvents(callRowIds: Set<Long>) {
    val messageIds = getMessageIds(callRowIds)
    SignalDatabase.messages.deleteCallUpdates(messageIds)
    updateCallEventDeletionTimestamps()
  }

  fun deleteAllCallEventsExcept(callRowIds: Set<Long>) {
    val messageIds = getMessageIds(callRowIds)
    SignalDatabase.messages.deleteAllCallUpdatesExcept(messageIds)
    updateCallEventDeletionTimestamps()
  }

  @Discouraged("Using this method is generally considered an error. Utilize other deletion methods instead of this.")
  fun deleteAllCalls() {
    Log.w(TAG, "Deleting all calls from the local database.")
    writableDatabase
      .delete(TABLE_NAME)
      .run()
  }

  private fun getCallSelectionQuery(callId: Long, conversationId: CallConversationId): SqlUtil.Query {
    return when (conversationId) {
      is CallConversationId.CallLink -> SqlUtil.Query("$CALL_ID = ? AND $CALL_LINK = ?", SqlUtil.buildArgs(callId, conversationId.callLinkId))
      is CallConversationId.Peer -> SqlUtil.Query("$CALL_ID = ? AND $PEER = ?", SqlUtil.buildArgs(callId, conversationId.recipientId))
    }
  }

  private fun getMessageIds(callRowIds: Set<Long>): Set<Long> {
    val queries = SqlUtil.buildCollectionQuery(
      ID,
      callRowIds,
      "$MESSAGE_ID NOT NULL AND"
    )

    return queries.map { query ->
      readableDatabase.select(MESSAGE_ID).from(TABLE_NAME).where(query.where, query.whereArgs).run().readToList {
        it.requireLong(MESSAGE_ID)
      }
    }.flatten().toSet()
  }

  private fun checkIsGroupOrAdHocCall(call: Call) {
    check(call.type == Type.GROUP_CALL || call.type == Type.AD_HOC_CALL)
  }

  // endregion

>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
  private fun getCallsCursor(isCount: Boolean, offset: Int, limit: Int, searchTerm: String?, filter: CallLogFilter): Cursor {
    val filterClause: SqlUtil.Query = when (filter) {
<<<<<<< HEAD
      CallLogFilter.ALL -> SqlUtil.buildQuery("$EVENT != ${Event.serialize(Event.DELETE)}")
      CallLogFilter.MISSED -> SqlUtil.buildQuery("$EVENT == ${Event.serialize(Event.MISSED)}")
=======
  fun getOldestDeletionTimestamp(): Long {
    return writableDatabase
      .select(DELETION_TIMESTAMP)
      .from(TABLE_NAME)
      .where("$DELETION_TIMESTAMP > 0")
      .orderBy("$DELETION_TIMESTAMP DESC")
      .limit(1)
      .run()
      .readToSingleLong(0L)
  }

  fun deleteCallEventsDeletedBefore(threshold: Long): Int {
    return writableDatabase
      .delete(TABLE_NAME)
      .where("$DELETION_TIMESTAMP > 0 AND $DELETION_TIMESTAMP <= ?", threshold)
      .run()
  }

  /**
   * If a non-ad-hoc call has been deleted from the message database, then we need to
   * set its deletion_timestamp to now.
   */
  fun updateCallEventDeletionTimestamps() {
    val where = "$TYPE != ? AND $DELETION_TIMESTAMP = 0 AND $MESSAGE_ID IS NULL"
    val type = Type.serialize(Type.AD_HOC_CALL)

    val toSync = writableDatabase.withinTransaction { db ->
      val result = db
        .select()
        .from(TABLE_NAME)
        .where(where, type)
        .run()
        .readToList {
          Call.deserialize(it)
        }
        .toSet()

      db
        .update(TABLE_NAME)
        .values(
          EVENT to Event.serialize(Event.DELETE),
          DELETION_TIMESTAMP to System.currentTimeMillis()
        )
        .where(where, type)
        .run()

      result
    }

<<<<<<< HEAD
    CallSyncEventJob.enqueueDeleteSyncEvents(toSync)
    ApplicationDependencies.getDeletedCallEventManager().scheduleIfNecessary()
    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
  }

  // region Group / Ad-Hoc Calling

  fun deleteGroupCall(call: Call) {
    checkIsGroupOrAdHocCall(call)

    writableDatabase.withinTransaction { db ->
      db
        .update(TABLE_NAME)
        .values(
          EVENT to Event.serialize(Event.DELETE),
          DELETION_TIMESTAMP to System.currentTimeMillis()
        )
        .where("$CALL_ID = ? AND $PEER = ?", call.callId, call.peer)
        .run()

      if (call.messageId != null) {
        SignalDatabase.messages.deleteMessage(call.messageId)
      }
    }

    ApplicationDependencies.getMessageNotifier().updateNotification(context)
    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
    Log.d(TAG, "Marked group call event for deletion: ${call.callId}")
  }

  fun insertDeletedGroupCallFromSyncEvent(
    callId: Long,
    recipientId: RecipientId?,
    direction: Direction,
    timestamp: Long
  ) {
    val type = if (recipientId != null) Type.GROUP_CALL else Type.AD_HOC_CALL

    writableDatabase
      .insertInto(TABLE_NAME)
      .values(
        CALL_ID to callId,
        MESSAGE_ID to null,
        PEER to recipientId?.toLong(),
        EVENT to Event.serialize(Event.DELETE),
        TYPE to Type.serialize(type),
        DIRECTION to Direction.serialize(direction),
        TIMESTAMP to timestamp,
        DELETION_TIMESTAMP to System.currentTimeMillis()
      )
      .run()

    ApplicationDependencies.getDeletedCallEventManager().scheduleIfNecessary()
  }

  fun acceptIncomingGroupCall(call: Call) {
    checkIsGroupOrAdHocCall(call)
    check(call.direction == Direction.INCOMING)

    val newEvent = when (call.event) {
      Event.RINGING, Event.MISSED, Event.DECLINED -> Event.ACCEPTED
      Event.GENERIC_GROUP_CALL -> Event.JOINED
      else -> {
        Log.d(TAG, "Call in state ${call.event} cannot be transitioned by ACCEPTED")
        return
      }
    }

    writableDatabase
      .update(TABLE_NAME)
      .values(EVENT to Event.serialize(newEvent))
      .run()

    ApplicationDependencies.getMessageNotifier().updateNotification(context)
    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
    Log.d(TAG, "Transitioned group call ${call.callId} from ${call.event} to $newEvent")
  }

  fun insertAcceptedGroupCall(
    callId: Long,
    recipientId: RecipientId?,
    direction: Direction,
    timestamp: Long
  ) {
    val type = if (recipientId != null) Type.GROUP_CALL else Type.AD_HOC_CALL
    val event = if (direction == Direction.OUTGOING) Event.OUTGOING_RING else Event.JOINED
    val ringer = if (direction == Direction.OUTGOING) Recipient.self().id.toLong() else null

    writableDatabase.withinTransaction { db ->
      val messageId: MessageId? = if (recipientId != null) {
        SignalDatabase.messages.insertGroupCall(
          groupRecipientId = recipientId,
          sender = Recipient.self().id,
          timestamp,
          "",
          emptyList(),
          false
        )
      } else {
        null
      }

      db
        .insertInto(TABLE_NAME)
        .values(
          CALL_ID to callId,
          MESSAGE_ID to messageId?.id,
          PEER to recipientId?.toLong(),
          EVENT to Event.serialize(event),
          TYPE to Type.serialize(type),
          DIRECTION to Direction.serialize(direction),
          TIMESTAMP to timestamp,
          RINGER to ringer
        )
        .run()
    }

    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
  }

  fun insertOrUpdateGroupCallFromExternalEvent(
    groupRecipientId: RecipientId,
    sender: RecipientId,
    timestamp: Long,
    messageGroupCallEraId: String?
  ) {
    insertOrUpdateGroupCallFromLocalEvent(
      groupRecipientId,
      sender,
      timestamp,
      messageGroupCallEraId,
      emptyList(),
      false
    )
  }

  fun insertOrUpdateGroupCallFromLocalEvent(
    groupRecipientId: RecipientId,
    sender: RecipientId,
    timestamp: Long,
    peekGroupCallEraId: String?,
    peekJoinedUuids: Collection<UUID>,
    isCallFull: Boolean
  ) {
    writableDatabase.withinTransaction {
      if (peekGroupCallEraId.isNullOrEmpty()) {
        Log.w(TAG, "Dropping local call event with null era id.")
        return@withinTransaction
      }

      val callId = CallId.fromEra(peekGroupCallEraId).longValue()
      val call = getCallById(callId, CallConversationId.Peer(groupRecipientId))
      val messageId: MessageId = if (call != null) {
        if (call.event == Event.DELETE) {
          Log.d(TAG, "Dropping group call update for deleted call.")
          return@withinTransaction
        }

        if (call.type != Type.GROUP_CALL) {
          Log.d(TAG, "Dropping unsupported update message for non-group-call call.")
          return@withinTransaction
        }

        if (call.messageId == null) {
          Log.d(TAG, "Dropping group call update for call without an attached message.")
          return@withinTransaction
        }

        SignalDatabase.messages.updateGroupCall(
          call.messageId,
          peekGroupCallEraId,
          peekJoinedUuids,
          isCallFull
        )
      } else {
        SignalDatabase.messages.insertGroupCall(
          groupRecipientId,
          sender,
          timestamp,
          peekGroupCallEraId,
          peekJoinedUuids,
          isCallFull
        )
      }

      insertCallEventFromGroupUpdate(
        callId,
        messageId,
        sender,
        groupRecipientId,
        timestamp
      )
    }
  }

  private fun insertCallEventFromGroupUpdate(
    callId: Long,
    messageId: MessageId?,
    sender: RecipientId,
    groupRecipientId: RecipientId,
    timestamp: Long
  ) {
    val conversationId = CallConversationId.Peer(groupRecipientId)
    if (messageId != null) {
      val call = getCallById(callId, conversationId)
      if (call == null) {
        val direction = if (sender == Recipient.self().id) Direction.OUTGOING else Direction.INCOMING

        writableDatabase
          .insertInto(TABLE_NAME)
          .values(
            CALL_ID to callId,
            MESSAGE_ID to messageId.id,
            PEER to groupRecipientId.toLong(),
            EVENT to Event.serialize(Event.GENERIC_GROUP_CALL),
            TYPE to Type.serialize(Type.GROUP_CALL),
            DIRECTION to Direction.serialize(direction),
            TIMESTAMP to timestamp,
            RINGER to null
          )
          .run()

        Log.d(TAG, "Inserted new call event from group call update message. Call Id: $callId")
      } else {
        if (timestamp < call.timestamp) {
          setTimestamp(callId, conversationId, timestamp)
          Log.d(TAG, "Updated call event timestamp for call id $callId")
        }

        if (call.messageId == null) {
          setMessageId(callId, messageId)
          Log.d(TAG, "Updated call event message id for newly inserted group call state: $callId")
        }
      }
    } else {
      Log.d(TAG, "Skipping call event processing for null era id.")
    }

    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
  }

  /**
   * Since this does not alter the call table, we can simply pass this directly through to the old handler.
   */
  fun updateGroupCallFromPeek(
    threadId: Long,
    peekGroupCallEraId: String?,
    peekJoinedUuids: Collection<UUID>,
    isCallFull: Boolean
  ): Boolean {
    val sameEraId = SignalDatabase.messages.updatePreviousGroupCall(threadId, peekGroupCallEraId, peekJoinedUuids, isCallFull)
    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
    return sameEraId
  }

  fun insertOrUpdateGroupCallFromRingState(
    ringId: Long,
    groupRecipientId: RecipientId,
    ringerRecipient: RecipientId,
    dateReceived: Long,
    ringState: RingUpdate
  ) {
    handleGroupRingState(ringId, groupRecipientId, ringerRecipient, dateReceived, ringState)
  }

  fun insertOrUpdateGroupCallFromRingState(
    ringId: Long,
    groupRecipientId: RecipientId,
    ringerUUID: UUID,
    dateReceived: Long,
    ringState: RingUpdate
  ) {
    val ringerRecipient = Recipient.externalPush(ServiceId.from(ringerUUID))
    handleGroupRingState(ringId, groupRecipientId, ringerRecipient.id, dateReceived, ringState)
  }

  fun isRingCancelled(ringId: Long, groupRecipientId: RecipientId): Boolean {
    val call = getCallById(ringId, CallConversationId.Peer(groupRecipientId)) ?: return false
    return call.event != Event.RINGING
  }

  private fun handleGroupRingState(
    ringId: Long,
    groupRecipientId: RecipientId,
    ringerRecipient: RecipientId,
    dateReceived: Long,
    ringState: RingUpdate
  ) {
    Log.d(TAG, "Processing group ring state update for $ringId in state $ringState")

    val call = getCallById(ringId, CallConversationId.Peer(groupRecipientId))
    if (call != null) {
      if (call.event == Event.DELETE) {
        Log.d(TAG, "Ignoring ring request for $ringId since its event has been deleted.")
        return
      }

      when (ringState) {
        RingUpdate.REQUESTED -> {
          when (call.event) {
            Event.GENERIC_GROUP_CALL -> updateEventFromRingState(ringId, Event.RINGING, ringerRecipient)
            Event.JOINED -> updateEventFromRingState(ringId, Event.ACCEPTED, ringerRecipient)
            else -> Log.w(TAG, "Received a REQUESTED ring event while in ${call.event}. Ignoring.")
          }
        }
        RingUpdate.EXPIRED_REQUEST, RingUpdate.CANCELLED_BY_RINGER -> {
          when (call.event) {
            Event.GENERIC_GROUP_CALL, Event.RINGING -> updateEventFromRingState(ringId, Event.MISSED, ringerRecipient)
            Event.OUTGOING_RING -> Log.w(TAG, "Received an expiration or cancellation while in OUTGOING_RING state. Ignoring.")
            else -> Unit
          }
        }
        RingUpdate.BUSY_LOCALLY, RingUpdate.BUSY_ON_ANOTHER_DEVICE -> {
          when (call.event) {
            Event.JOINED -> updateEventFromRingState(ringId, Event.ACCEPTED)
            Event.GENERIC_GROUP_CALL, Event.RINGING -> updateEventFromRingState(ringId, Event.MISSED)
            else -> Log.w(TAG, "Received a busy event we can't process. Ignoring.")
          }
        }
        RingUpdate.ACCEPTED_ON_ANOTHER_DEVICE -> {
          updateEventFromRingState(ringId, Event.ACCEPTED)
        }
        RingUpdate.DECLINED_ON_ANOTHER_DEVICE -> {
          when (call.event) {
            Event.RINGING, Event.MISSED -> updateEventFromRingState(ringId, Event.DECLINED)
            Event.OUTGOING_RING -> Log.w(TAG, "Received DECLINED_ON_ANOTHER_DEVICE while in OUTGOING_RING state.")
            else -> Unit
          }
        }
      }
    } else {
      val event: Event = when (ringState) {
        RingUpdate.REQUESTED -> Event.RINGING
        RingUpdate.EXPIRED_REQUEST -> Event.MISSED
        RingUpdate.ACCEPTED_ON_ANOTHER_DEVICE -> {
          Log.w(TAG, "Missed original ring request for $ringId")
          Event.ACCEPTED
        }
        RingUpdate.DECLINED_ON_ANOTHER_DEVICE -> {
          Log.w(TAG, "Missed original ring request for $ringId")
          Event.DECLINED
        }
        RingUpdate.BUSY_LOCALLY, RingUpdate.BUSY_ON_ANOTHER_DEVICE -> {
          Log.w(TAG, "Missed original ring request for $ringId")
          Event.MISSED
        }
        RingUpdate.CANCELLED_BY_RINGER -> {
          Log.w(TAG, "Missed original ring request for $ringId")
          Event.MISSED
        }
      }

      createEventFromRingState(ringId, groupRecipientId, ringerRecipient, event, dateReceived)
    }

    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
  }

  private fun updateEventFromRingState(
    callId: Long,
    event: Event,
    ringerRecipient: RecipientId
  ) {
    writableDatabase
      .update(TABLE_NAME)
      .values(
        EVENT to Event.serialize(event),
        RINGER to ringerRecipient.serialize()
      )
      .where("$CALL_ID = ?", callId)
      .run()

    Log.d(TAG, "Updated ring state to $event")
  }

  private fun updateEventFromRingState(
    callId: Long,
    event: Event
  ) {
    writableDatabase
      .update(TABLE_NAME)
      .values(
        EVENT to Event.serialize(event)
      )
      .where("$CALL_ID = ?", callId)
      .run()

    Log.d(TAG, "Updated ring state to $event")
  }

  private fun createEventFromRingState(
    callId: Long,
    groupRecipientId: RecipientId,
    ringerRecipient: RecipientId,
    event: Event,
    timestamp: Long
  ) {
    val direction = if (ringerRecipient == Recipient.self().id) Direction.OUTGOING else Direction.INCOMING

    writableDatabase.withinTransaction { db ->
      val messageId = SignalDatabase.messages.insertGroupCall(
        groupRecipientId = groupRecipientId,
        sender = ringerRecipient,
        timestamp = timestamp,
        eraId = "",
        joinedUuids = emptyList(),
        isCallFull = false
      )

      db
        .insertInto(TABLE_NAME)
        .values(
          CALL_ID to callId,
          MESSAGE_ID to messageId.id,
          PEER to groupRecipientId.toLong(),
          EVENT to Event.serialize(event),
          TYPE to Type.serialize(Type.GROUP_CALL),
          DIRECTION to Direction.serialize(direction),
          TIMESTAMP to timestamp,
          RINGER to ringerRecipient.toLong()
        )
        .run()
    }

    Log.d(TAG, "Inserted a new group ring event for $callId with event $event")
  }

  fun setTimestamp(callId: Long, conversationId: CallConversationId, timestamp: Long) {
    writableDatabase.withinTransaction { db ->
      val call = getCallById(callId, conversationId)
      if (call == null || call.event == Event.DELETE) {
        Log.d(TAG, "Refusing to update deleted call event.")
        return@withinTransaction
      }

      db
        .update(TABLE_NAME)
        .values(TIMESTAMP to timestamp)
        .where("$CALL_ID = ?", callId)
        .run()

      if (call.messageId != null) {
        SignalDatabase.messages.updateCallTimestamps(call.messageId, timestamp)
      }
    }

    ApplicationDependencies.getDatabaseObserver().notifyCallUpdateObservers()
  }

  private fun setMessageId(callId: Long, messageId: MessageId) {
    writableDatabase
      .update(TABLE_NAME)
      .values(MESSAGE_ID to messageId.id)
      .where("$CALL_ID = ?", callId)
      .run()
  }

  fun deleteCallEvents(callRowIds: Set<Long>) {
    val messageIds = getMessageIds(callRowIds)
    SignalDatabase.messages.deleteCallUpdates(messageIds)
    updateCallEventDeletionTimestamps()
  }

  fun deleteAllCallEventsExcept(callRowIds: Set<Long>) {
    val messageIds = getMessageIds(callRowIds)
    SignalDatabase.messages.deleteAllCallUpdatesExcept(messageIds)
    updateCallEventDeletionTimestamps()
  }

  @Discouraged("Using this method is generally considered an error. Utilize other deletion methods instead of this.")
  fun deleteAllCalls() {
    Log.w(TAG, "Deleting all calls from the local database.")
    writableDatabase
      .delete(TABLE_NAME)
      .run()
  }

  private fun getCallSelectionQuery(callId: Long, conversationId: CallConversationId): SqlUtil.Query {
    return when (conversationId) {
      is CallConversationId.CallLink -> SqlUtil.Query("$CALL_ID = ? AND $CALL_LINK = ?", SqlUtil.buildArgs(callId, conversationId.callLinkId))
      is CallConversationId.Peer -> SqlUtil.Query("$CALL_ID = ? AND $PEER = ?", SqlUtil.buildArgs(callId, conversationId.recipientId))
    }
  }

  private fun getMessageIds(callRowIds: Set<Long>): Set<Long> {
    val queries = SqlUtil.buildCollectionQuery(
      ID,
      callRowIds,
      "$MESSAGE_ID NOT NULL AND"
    )

    return queries.map { query ->
      readableDatabase.select(MESSAGE_ID).from(TABLE_NAME).where(query.where, query.whereArgs).run().readToList {
        it.requireLong(MESSAGE_ID)
      }
    }.flatten().toSet()
  }

  private fun checkIsGroupOrAdHocCall(call: Call) {
    check(call.type == Type.GROUP_CALL || call.type == Type.AD_HOC_CALL)
  }

  // endregion

  private fun getCallsCursor(isCount: Boolean, offset: Int, limit: Int, searchTerm: String?, filter: CallLogFilter): Cursor {
    val filterClause: SqlUtil.Query = when (filter) {
<<<<<<< HEAD
      CallLogFilter.ALL -> SqlUtil.buildQuery("$EVENT != ${Event.serialize(Event.DELETE)}")
      CallLogFilter.MISSED -> SqlUtil.buildQuery("$EVENT == ${Event.serialize(Event.MISSED)}")
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    }

<<<<<<< HEAD
    return maps.flatten()
  }

  fun getOldestDeletionTimestamp(): Long {
    return writableDatabase
      .select(DELETION_TIMESTAMP)
      .from(TABLE_NAME)
      .where("$DELETION_TIMESTAMP > 0")
      .orderBy("$DELETION_TIMESTAMP DESC")
      .limit(1)
      .run()
      .readToSingleLong(0L)
  }

  fun deleteCallEventsDeletedBefore(threshold: Long): Int {
    return writableDatabase
      .delete(TABLE_NAME)
      .where("$DELETION_TIMESTAMP > 0 AND $DELETION_TIMESTAMP <= ?", threshold)
      .run()
  }

  fun getCallLinkRoomIdsFromCallRowIds(callRowIds: Set<Long>): Set<CallLinkRoomId> {
    return SqlUtil.buildCollectionQuery("$TABLE_NAME.$ID", callRowIds).map { query ->
      //language=sql
      val statement = """
        SELECT ${CallLinkTable.ROOM_ID} FROM $TABLE_NAME
        INNER JOIN ${CallLinkTable.TABLE_NAME} ON ${CallLinkTable.TABLE_NAME}.${CallLinkTable.RECIPIENT_ID} = $PEER
        WHERE $TYPE = ${Type.serialize(Type.AD_HOC_CALL)} AND ${query.where}
      """.toSingleLine()

      readableDatabase.query(statement, query.whereArgs).readToList {
        CallLinkRoomId.DatabaseSerializer.deserialize(it.requireNonNullString(CallLinkTable.ROOM_ID))
      }
    }.flatten().toSet()
  }

  /**
   * If a call link has been revoked, or if we do not have a CallLink table entry for an AD_HOC_CALL type
   * event, we mark it deleted.
   */
  fun updateAdHocCallEventDeletionTimestamps(skipSync: Boolean = false) {
    //language=sql
    val statement = """
      UPDATE $TABLE_NAME
      SET $DELETION_TIMESTAMP = ${System.currentTimeMillis()}, $EVENT = ${Event.serialize(Event.DELETE)}
      WHERE $TYPE = ${Type.serialize(Type.AD_HOC_CALL)}
      AND (
        (NOT EXISTS (SELECT 1 FROM ${CallLinkTable.TABLE_NAME} WHERE ${CallLinkTable.RECIPIENT_ID} = $PEER))
        OR
        (SELECT ${CallLinkTable.REVOKED} FROM ${CallLinkTable.TABLE_NAME} WHERE ${CallLinkTable.RECIPIENT_ID} = $PEER)
      )
      RETURNING *
    """.toSingleLine()

    val toSync = writableDatabase.query(statement).readToList {
      Call.deserialize(it)
    }.toSet()

    if (!skipSync) {
      CallSyncEventJob.enqueueDeleteSyncEvents(toSync)
    }

    AppDependencies.deletedCallEventManager.scheduleIfNecessary()
    AppDependencies.databaseObserver.notifyCallUpdateObservers()
  }

  /**
   * If a non-ad-hoc call has been deleted from the message database, then we need to
   * set its deletion_timestamp to now.
   */
  fun updateCallEventDeletionTimestamps(skipSync: Boolean = false) {
    val where = "$TYPE != ? AND $DELETION_TIMESTAMP = 0 AND $MESSAGE_ID IS NULL"
    val type = Type.serialize(Type.AD_HOC_CALL)

    val toSync = writableDatabase.withinTransaction { db ->
      val result = db
        .select()
        .from(TABLE_NAME)
        .where(where, type)
        .run()
        .readToList {
          Call.deserialize(it)
        }
        .toSet()

      db
        .update(TABLE_NAME)
        .values(
          EVENT to Event.serialize(Event.DELETE),
          DELETION_TIMESTAMP to System.currentTimeMillis()
        )
        .where(where, type)
        .run()

      result
    }

    if (!skipSync) {
      CallSyncEventJob.enqueueDeleteSyncEvents(toSync)
    }

    AppDependencies.deletedCallEventManager.scheduleIfNecessary()
    AppDependencies.databaseObserver.notifyCallUpdateObservers()
  }

  /**
   * Marks the given call event DELETED. This deletes the associated message, but
   * keeps the call event around for several hours to ensure out of order messages
   * do not bring it back.
   */
  fun markCallDeletedFromSyncEvent(call: Call) {
    val filter: SqlUtil.Query = getCallSelectionQuery(call.callId, call.peer)

    writableDatabase.withinTransaction { db ->
      db
        .update(TABLE_NAME)
        .values(
          EVENT to Event.serialize(Event.DELETE),
          DELETION_TIMESTAMP to System.currentTimeMillis()
        )
        .where(filter.where, filter.whereArgs)
        .run()

      if (call.messageId != null) {
        SignalDatabase.messages.deleteMessage(call.messageId)
      }
    }

    AppDependencies.messageNotifier.updateNotification(context)
    AppDependencies.databaseObserver.notifyCallUpdateObservers()
    Log.d(TAG, "Marked call event for deletion: ${call.callId}")
  }

  /**
   * Inserts a call event in the DELETED state with the corresponding data.
   * Deleted calls are kept around for several hours to ensure they don't reappear
   * due to out of order messages.
   */
  fun insertDeletedCallFromSyncEvent(
    callId: Long,
    recipientId: RecipientId,
    type: Type,
    direction: Direction,
    timestamp: Long
  ) {
    writableDatabase
      .insertInto(TABLE_NAME)
      .values(
        CALL_ID to callId,
        MESSAGE_ID to null,
        PEER to recipientId.toLong(),
        EVENT to Event.serialize(Event.DELETE),
        TYPE to Type.serialize(type),
        DIRECTION to Direction.serialize(direction),
        TIMESTAMP to timestamp,
        DELETION_TIMESTAMP to System.currentTimeMillis()
      )
      .run(SQLiteDatabase.CONFLICT_ABORT)

    AppDependencies.deletedCallEventManager.scheduleIfNecessary()
    Log.d(TAG, "Inserted deleted call event: $callId, $type, $direction, $timestamp")
  }

  // region Group / Ad-Hoc Calling

  fun acceptIncomingGroupCall(call: Call) {
    checkIsGroupOrAdHocCall(call)

    val newEvent = when (call.event) {
      Event.RINGING, Event.MISSED, Event.MISSED_NOTIFICATION_PROFILE, Event.DECLINED -> Event.ACCEPTED
      Event.GENERIC_GROUP_CALL -> Event.JOINED
      else -> {
        Log.d(TAG, "[acceptIncomingGroupCall] Call in state ${call.event} cannot be transitioned by ACCEPTED")
        return
      }
    }

    writableDatabase
      .update(TABLE_NAME)
      .values(EVENT to Event.serialize(newEvent))
      .where("$CALL_ID = ?", call.callId)
      .run()

    AppDependencies.messageNotifier.updateNotification(context)
    AppDependencies.databaseObserver.notifyCallUpdateObservers()
    Log.d(TAG, "[acceptIncomingGroupCall] Transitioned group call ${call.callId} from ${call.event} to $newEvent")
  }

  fun acceptOutgoingGroupCall(call: Call) {
    checkIsGroupOrAdHocCall(call)

    val newEvent = when (call.event) {
      Event.GENERIC_GROUP_CALL, Event.JOINED -> Event.OUTGOING_RING
      Event.RINGING, Event.MISSED, Event.MISSED_NOTIFICATION_PROFILE, Event.DECLINED, Event.ACCEPTED -> {
        Log.w(TAG, "[acceptOutgoingGroupCall] This shouldn't have been an outgoing ring because the call already existed!")
        Event.ACCEPTED
      }

      else -> {
        Log.d(TAG, "[acceptOutgoingGroupCall] Call in state ${call.event} cannot be transitioned by ACCEPTED")
        return
      }
    }

    writableDatabase
      .update(TABLE_NAME)
      .values(EVENT to Event.serialize(newEvent), DIRECTION to Direction.serialize(Direction.OUTGOING))
      .where("$CALL_ID = ?", call.callId)
      .run()

    AppDependencies.messageNotifier.updateNotification(context)
    AppDependencies.databaseObserver.notifyCallUpdateObservers()
    Log.d(TAG, "[acceptOutgoingGroupCall] Transitioned group call ${call.callId} from ${call.event} to $newEvent")
  }

  fun declineIncomingGroupCall(call: Call) {
    checkIsGroupOrAdHocCall(call)
    check(call.direction == Direction.INCOMING)

    val newEvent = when (call.event) {
      Event.GENERIC_GROUP_CALL, Event.RINGING, Event.MISSED, Event.MISSED_NOTIFICATION_PROFILE -> Event.DECLINED
      Event.JOINED -> Event.ACCEPTED
      else -> {
        Log.d(TAG, "Call in state ${call.event} cannot be transitioned by DECLINED")
        return
      }
    }

    writableDatabase
      .update(TABLE_NAME)
      .values(EVENT to Event.serialize(newEvent))
      .where("$CALL_ID = ?", call.callId)
      .run()

    AppDependencies.messageNotifier.updateNotification(context)
    AppDependencies.databaseObserver.notifyCallUpdateObservers()
    Log.d(TAG, "Transitioned group call ${call.callId} from ${call.event} to $newEvent")
  }

  fun insertAcceptedGroupCall(
    callId: Long,
    recipientId: RecipientId,
    direction: Direction,
    timestamp: Long
  ) {
    val recipient = Recipient.resolved(recipientId)
    val type = if (recipient.isCallLink) Type.AD_HOC_CALL else Type.GROUP_CALL
    val event = if (direction == Direction.OUTGOING) Event.OUTGOING_RING else Event.JOINED
    val ringer = if (direction == Direction.OUTGOING) Recipient.self().id.toLong() else null

    writableDatabase.withinTransaction { db ->
      val messageId: MessageId? = if (type == Type.GROUP_CALL) {
        SignalDatabase.messages.insertGroupCall(
          groupRecipientId = recipientId,
          sender = Recipient.self().id,
          timestamp,
          "",
          emptyList(),
          false,
          false
        )
      } else {
        null
      }

      db
        .insertInto(TABLE_NAME)
        .values(
          CALL_ID to callId,
          MESSAGE_ID to messageId?.id,
          PEER to recipientId.toLong(),
          EVENT to Event.serialize(event),
          TYPE to Type.serialize(type),
          DIRECTION to Direction.serialize(direction),
          TIMESTAMP to timestamp,
          RINGER to ringer,
          LOCAL_JOINED to true
        )
        .run(SQLiteDatabase.CONFLICT_ABORT)
    }

    AppDependencies.databaseObserver.notifyCallUpdateObservers()
  }

  fun insertDeclinedGroupCall(
    callId: Long,
    recipientId: RecipientId,
    timestamp: Long
  ) {
    val recipient = Recipient.resolved(recipientId)
    val type = if (recipient.isCallLink) Type.AD_HOC_CALL else Type.GROUP_CALL

    writableDatabase.withinTransaction { db ->
      val messageId: MessageId? = if (type == Type.GROUP_CALL) {
        SignalDatabase.messages.insertGroupCall(
          groupRecipientId = recipientId,
          sender = Recipient.self().id,
          timestamp,
          "",
          emptyList(),
          false,
          false
        )
      } else {
        null
      }

      db
        .insertInto(TABLE_NAME)
        .values(
          CALL_ID to callId,
          MESSAGE_ID to messageId?.id,
          PEER to recipientId.toLong(),
          EVENT to Event.serialize(Event.DECLINED),
          TYPE to Type.serialize(type),
          DIRECTION to Direction.serialize(Direction.INCOMING),
          TIMESTAMP to timestamp,
          RINGER to null,
          LOCAL_JOINED to false
        )
        .run(SQLiteDatabase.CONFLICT_ABORT)
    }

    AppDependencies.databaseObserver.notifyCallUpdateObservers()
  }

  fun insertOrUpdateAdHocCallFromObserveEvent(
    callRecipient: Recipient,
    timestamp: Long,
    callId: Long
  ) {
    handleCallLinkUpdate(callRecipient, timestamp, CallId(callId), Direction.INCOMING)
  }

  fun insertAdHocCallFromObserveEvent(
    callRecipient: Recipient,
    timestamp: Long,
    eraId: String
  ): Boolean {
    return handleCallLinkUpdate(callRecipient, timestamp, CallId.fromEra(eraId), Direction.INCOMING, skipTimestampUpdate = true)
  }

  fun insertOrUpdateGroupCallFromLocalEvent(
    groupRecipientId: RecipientId,
    sender: RecipientId,
    timestamp: Long,
    peekGroupCallEraId: String?,
    peekJoinedUuids: Collection<UUID>,
    isCallFull: Boolean
  ) {
    val recipient = Recipient.resolved(groupRecipientId)
    if (recipient.isCallLink) {
      handleCallLinkUpdate(recipient, timestamp, peekGroupCallEraId?.let { CallId.fromEra(it) })
    } else {
      handleGroupUpdate(recipient, sender, timestamp, peekGroupCallEraId, peekJoinedUuids, isCallFull)
    }
  }

  private fun handleGroupUpdate(
    groupRecipient: Recipient,
    sender: RecipientId,
    timestamp: Long,
    peekGroupCallEraId: String?,
    peekJoinedUuids: Collection<UUID>,
    isCallFull: Boolean
  ) {
    check(groupRecipient.isPushV2Group)
    writableDatabase.withinTransaction {
      if (peekGroupCallEraId.isNullOrEmpty()) {
        Log.w(TAG, "Dropping local call event with null era id.")
        return@withinTransaction
      }

      val callId = CallId.fromEra(peekGroupCallEraId).longValue()
      val call = getCallById(callId, groupRecipient.id)
      val messageId: MessageId = if (call != null) {
        if (call.event == Event.DELETE) {
          Log.d(TAG, "Dropping group call update for deleted call.")
          return@withinTransaction
        }

        if (call.type != Type.GROUP_CALL) {
          Log.d(TAG, "Dropping unsupported update message for non-group-call call.")
          return@withinTransaction
        }

        if (call.messageId == null) {
          Log.d(TAG, "Dropping group call update for call without an attached message.")
          return@withinTransaction
        }

        SignalDatabase.messages.updateGroupCall(
          call.messageId,
          peekGroupCallEraId,
          peekJoinedUuids,
          isCallFull,
          call.event == Event.RINGING
        )
      } else {
        SignalDatabase.messages.insertGroupCall(
          groupRecipient.id,
          sender,
          timestamp,
          peekGroupCallEraId,
          peekJoinedUuids,
          isCallFull,
          false
        )
      }

      insertCallEventFromGroupUpdate(
        callId = callId,
        messageId = messageId,
        sender = sender,
        groupRecipientId = groupRecipient.id,
        timestamp = timestamp,
        didLocalUserJoin = peekJoinedUuids.contains(Recipient.self().requireServiceId().rawUuid),
        isGroupCallActive = peekJoinedUuids.isNotEmpty()
      )
    }
  }

  /**
   * @return Whether or not a new row was inserted.
   */
  private fun handleCallLinkUpdate(
    callLinkRecipient: Recipient,
    timestamp: Long,
    callId: CallId?,
    direction: Direction = Direction.OUTGOING,
    skipTimestampUpdate: Boolean = false
  ): Boolean {
    check(callLinkRecipient.isCallLink)

    if (callId == null) {
      return false
    }

    val didInsert = writableDatabase.withinTransaction { db ->
      val exists = db.exists(TABLE_NAME)
        .where("$PEER = ? AND $CALL_ID = ?", callLinkRecipient.id.serialize(), callId.longValue())
        .run()

      if (exists && !skipTimestampUpdate) {
        val updated = db.update(TABLE_NAME)
          .values(TIMESTAMP to timestamp)
          .where("$PEER = ? AND $CALL_ID = ? AND $TIMESTAMP < ?", callLinkRecipient.id.serialize(), callId.longValue(), timestamp)
          .run() > 0

        if (updated) {
          Log.d(TAG, "Updated call event for call link. Call Id: $callId")
          AppDependencies.databaseObserver.notifyCallUpdateObservers()
        }

        false
      } else if (!exists) {
        db.insertInto(TABLE_NAME)
          .values(
            CALL_ID to callId.longValue(),
            MESSAGE_ID to null,
            PEER to callLinkRecipient.id.toLong(),
            EVENT to Event.serialize(Event.GENERIC_GROUP_CALL),
            TYPE to Type.serialize(Type.AD_HOC_CALL),
            DIRECTION to Direction.serialize(direction),
            TIMESTAMP to timestamp,
            RINGER to null
          ).run(SQLiteDatabase.CONFLICT_ABORT)

        Log.d(TAG, "Inserted new call event for call link. Call Id: $callId")
        AppDependencies.databaseObserver.notifyCallUpdateObservers()

        true
      } else false
    }

    return didInsert
  }

  private fun insertCallEventFromGroupUpdate(
    callId: Long,
    messageId: MessageId?,
    sender: RecipientId,
    groupRecipientId: RecipientId,
    timestamp: Long,
    didLocalUserJoin: Boolean,
    isGroupCallActive: Boolean
  ) {
    if (messageId != null) {
      val call = getCallById(callId, groupRecipientId)
      if (call == null) {
        val direction = if (sender == Recipient.self().id) Direction.OUTGOING else Direction.INCOMING

        writableDatabase
          .insertInto(TABLE_NAME)
          .values(
            CALL_ID to callId,
            MESSAGE_ID to messageId.id,
            PEER to groupRecipientId.toLong(),
            EVENT to Event.serialize(Event.GENERIC_GROUP_CALL),
            TYPE to Type.serialize(Type.GROUP_CALL),
            DIRECTION to Direction.serialize(direction),
            TIMESTAMP to timestamp,
            RINGER to null,
            LOCAL_JOINED to didLocalUserJoin,
            GROUP_CALL_ACTIVE to isGroupCallActive
          )
          .run(SQLiteDatabase.CONFLICT_ABORT)

        Log.d(TAG, "Inserted new call event from group call update message. Call Id: $callId")
      } else {
        if (timestamp < call.timestamp) {
          setTimestamp(callId, groupRecipientId, timestamp)
          Log.d(TAG, "Updated call event timestamp for call id $callId")
        }

        if (call.messageId == null) {
          setMessageId(callId, messageId)
          Log.d(TAG, "Updated call event message id for newly inserted group call state: $callId")
        }

        updateGroupCallState(call, didLocalUserJoin, isGroupCallActive)
      }
    } else {
      Log.d(TAG, "Skipping call event processing for null era id.")
    }

    AppDependencies.databaseObserver.notifyCallUpdateObservers()
  }

  /**
   * Update necessary call info from peek
   */
  fun updateGroupCallFromPeek(
    threadId: Long,
    peekGroupCallEraId: String?,
    peekJoinedUuids: Collection<UUID>,
    isCallFull: Boolean
  ) {
    val callId = peekGroupCallEraId?.let { CallId.fromEra(it) }
    val recipientId = SignalDatabase.threads.getRecipientIdForThreadId(threadId)
    val call = if (callId != null && recipientId != null) {
      getCallById(callId.longValue(), recipientId)
    } else {
      null
    }

    SignalDatabase.messages.updatePreviousGroupCall(
      threadId = threadId,
      peekGroupCallEraId = peekGroupCallEraId,
      peekJoinedUuids = peekJoinedUuids,
      isCallFull = isCallFull,
      isRingingOnLocalDevice = call?.event == Event.RINGING
    )

    if (call != null) {
      updateGroupCallState(call, peekJoinedUuids)
      AppDependencies.databaseObserver.notifyCallUpdateObservers()
    }
  }

  fun insertOrUpdateGroupCallFromRingState(
    ringId: Long,
    groupRecipientId: RecipientId,
    ringerRecipient: RecipientId,
    dateReceived: Long,
    ringState: RingUpdate
  ) {
    handleGroupRingState(ringId, groupRecipientId, ringerRecipient, dateReceived, ringState)
  }

  @JvmOverloads
  fun insertOrUpdateGroupCallFromRingState(
    ringId: Long,
    groupRecipientId: RecipientId,
    ringerAci: ACI,
    dateReceived: Long,
    ringState: RingUpdate,
    dueToNotificationProfile: Boolean = false
  ) {
    val ringerRecipient = Recipient.externalPush(ringerAci)
    handleGroupRingState(ringId, groupRecipientId, ringerRecipient.id, dateReceived, ringState, dueToNotificationProfile)
  }

  fun isRingCancelled(ringId: Long, groupRecipientId: RecipientId): Boolean {
    val call = getCallById(ringId, groupRecipientId) ?: return false
    return call.event != Event.RINGING && call.event != Event.GENERIC_GROUP_CALL
  }

  /**
   * @return whether or not a change is detected.
   */
  private fun updateGroupCallState(
    call: Call,
    peekJoinedUuids: Collection<UUID>
  ): Boolean {
    return updateGroupCallState(
      call,
      peekJoinedUuids.contains(Recipient.self().requireServiceId().rawUuid),
      peekJoinedUuids.isNotEmpty()
    )
  }

  /**
   * @return Whether or not a change was detected
   */
  private fun updateGroupCallState(
    call: Call,
    hasLocalUserJoined: Boolean,
    isGroupCallActive: Boolean
  ): Boolean {
    val localJoined = call.didLocalUserJoin || hasLocalUserJoined

    return writableDatabase.update(TABLE_NAME)
      .values(
        LOCAL_JOINED to localJoined,
        GROUP_CALL_ACTIVE to isGroupCallActive
      )
      .where(
        "$CALL_ID = ? AND $PEER = ? AND ($LOCAL_JOINED != ? OR $GROUP_CALL_ACTIVE != ?)",
        call.callId,
        call.peer.toLong(),
        localJoined.toInt(),
        isGroupCallActive.toInt()
      )
      .run() > 0
  }

  private fun handleGroupRingState(
    ringId: Long,
    groupRecipientId: RecipientId,
    ringerRecipient: RecipientId,
    dateReceived: Long,
    ringState: RingUpdate,
    dueToNotificationProfile: Boolean = false
  ) {
    writableDatabase.withinTransaction {
      Log.d(TAG, "Processing group ring state update for $ringId in state $ringState")

      val call = getCallById(ringId, groupRecipientId)
      if (call != null) {
        if (call.event == Event.DELETE) {
          Log.d(TAG, "Ignoring ring request for $ringId since its event has been deleted.")
          return@withinTransaction
        }

        when (ringState) {
          RingUpdate.REQUESTED -> {
            when (call.event) {
              Event.GENERIC_GROUP_CALL -> updateEventFromRingState(ringId, Event.RINGING, ringerRecipient)
              Event.JOINED -> updateEventFromRingState(ringId, Event.ACCEPTED, ringerRecipient)
              else -> Log.w(TAG, "Received a REQUESTED ring event while in ${call.event}. Ignoring.")
            }
          }

          RingUpdate.EXPIRED_REQUEST, RingUpdate.CANCELLED_BY_RINGER -> {
            when (call.event) {
              Event.GENERIC_GROUP_CALL, Event.RINGING -> updateEventFromRingState(ringId, if (dueToNotificationProfile) Event.MISSED_NOTIFICATION_PROFILE else Event.MISSED, ringerRecipient)
              Event.JOINED -> updateEventFromRingState(ringId, Event.ACCEPTED, ringerRecipient)
              Event.OUTGOING_RING -> Log.w(TAG, "Received an expiration or cancellation while in OUTGOING_RING state. Ignoring.")
              else -> Unit
            }
          }

          RingUpdate.BUSY_LOCALLY -> {
            when (call.event) {
              Event.JOINED -> updateEventFromRingState(ringId, Event.ACCEPTED)
              Event.GENERIC_GROUP_CALL, Event.RINGING -> updateEventFromRingState(ringId, Event.MISSED)
              else -> {
                updateEventFromRingState(ringId, call.event, ringerRecipient)
                Log.w(TAG, "Received a busy event we can't process. Updating ringer only.")
              }
            }
          }

          RingUpdate.BUSY_ON_ANOTHER_DEVICE -> {
            when (call.event) {
              Event.JOINED -> updateEventFromRingState(ringId, Event.ACCEPTED)
              Event.GENERIC_GROUP_CALL, Event.RINGING -> updateEventFromRingState(ringId, Event.MISSED)
              else -> Log.w(TAG, "Received a busy event we can't process. Ignoring.")
            }
          }

          RingUpdate.ACCEPTED_ON_ANOTHER_DEVICE -> {
            updateEventFromRingState(ringId, Event.ACCEPTED)
          }

          RingUpdate.DECLINED_ON_ANOTHER_DEVICE -> {
            when (call.event) {
              Event.RINGING, Event.MISSED, Event.MISSED_NOTIFICATION_PROFILE, Event.GENERIC_GROUP_CALL -> updateEventFromRingState(ringId, Event.DECLINED)
              Event.JOINED -> updateEventFromRingState(ringId, Event.ACCEPTED)
              Event.OUTGOING_RING -> Log.w(TAG, "Received DECLINED_ON_ANOTHER_DEVICE while in OUTGOING_RING state.")
              else -> Unit
            }
          }
        }
      } else {
        val event: Event = when (ringState) {
          RingUpdate.REQUESTED -> Event.RINGING
          RingUpdate.EXPIRED_REQUEST -> if (dueToNotificationProfile) Event.MISSED_NOTIFICATION_PROFILE else Event.MISSED
          RingUpdate.ACCEPTED_ON_ANOTHER_DEVICE -> {
            Log.w(TAG, "Missed original ring request for $ringId")
            Event.ACCEPTED
          }

          RingUpdate.DECLINED_ON_ANOTHER_DEVICE -> {
            Log.w(TAG, "Missed original ring request for $ringId")
            Event.DECLINED
          }

          RingUpdate.BUSY_LOCALLY, RingUpdate.BUSY_ON_ANOTHER_DEVICE -> {
            Log.w(TAG, "Missed original ring request for $ringId")
            Event.MISSED
          }

          RingUpdate.CANCELLED_BY_RINGER -> {
            Log.w(TAG, "Missed original ring request for $ringId")
            Event.MISSED
          }
        }

        createEventFromRingState(ringId, groupRecipientId, ringerRecipient, event, dateReceived)
      }
    }

    AppDependencies.databaseObserver.notifyCallUpdateObservers()
  }

  private fun updateEventFromRingState(
    callId: Long,
    event: Event,
    ringerRecipient: RecipientId
  ) {
    writableDatabase
      .update(TABLE_NAME)
      .values(
        EVENT to Event.serialize(event),
        RINGER to ringerRecipient.serialize()
      )
      .where("$CALL_ID = ?", callId)
      .run()

    Log.d(TAG, "Updated ring state to $event")
  }

  private fun updateEventFromRingState(
    callId: Long,
    event: Event
  ) {
    writableDatabase
      .update(TABLE_NAME)
      .values(
        EVENT to Event.serialize(event)
      )
      .where("$CALL_ID = ?", callId)
      .run()

    Log.d(TAG, "Updated ring state to $event")
  }

  private fun createEventFromRingState(
    callId: Long,
    groupRecipientId: RecipientId,
    ringerRecipient: RecipientId,
    event: Event,
    timestamp: Long
  ) {
    val direction = if (ringerRecipient == Recipient.self().id) Direction.OUTGOING else Direction.INCOMING

    val recipient = Recipient.resolved(groupRecipientId)
    check(recipient.isPushV2Group)

    writableDatabase.withinTransaction { db ->
      val messageId = SignalDatabase.messages.insertGroupCall(
        groupRecipientId = groupRecipientId,
        sender = ringerRecipient,
        timestamp = timestamp,
        eraId = "",
        joinedUuids = emptyList(),
        isCallFull = false,
        isIncomingGroupCallRingingOnLocalDevice = event == Event.RINGING
      )

      db
        .insertInto(TABLE_NAME)
        .values(
          CALL_ID to callId,
          MESSAGE_ID to messageId.id,
          PEER to groupRecipientId.toLong(),
          EVENT to Event.serialize(event),
          TYPE to Type.serialize(Type.GROUP_CALL),
          DIRECTION to Direction.serialize(direction),
          TIMESTAMP to timestamp,
          RINGER to ringerRecipient.toLong()
        )
        .run(SQLiteDatabase.CONFLICT_ABORT)
    }

    Log.d(TAG, "Inserted a new group ring event for $callId with event $event")
  }

  fun setTimestamp(callId: Long, recipientId: RecipientId, timestamp: Long) {
    writableDatabase.withinTransaction { db ->
      val call = getCallById(callId, recipientId)
      if (call == null || call.event == Event.DELETE) {
        Log.d(TAG, "Refusing to update deleted call event.")
        return@withinTransaction
      }

      db
        .update(TABLE_NAME)
        .values(TIMESTAMP to timestamp)
        .where("$CALL_ID = ?", callId)
        .run()

      if (call.messageId != null) {
        SignalDatabase.messages.updateCallTimestamps(call.messageId, timestamp)
      }
    }

    AppDependencies.databaseObserver.notifyCallUpdateObservers()
  }

  private fun setMessageId(callId: Long, messageId: MessageId) {
    writableDatabase
      .update(TABLE_NAME)
      .values(MESSAGE_ID to messageId.id)
      .where("$CALL_ID = ?", callId)
      .run()
  }

  /**
   * Gets the most recent timestamp from the [TIMESTAMP] column
   */
  fun getLatestCall(): Call? {
    val statement = """
      SELECT * FROM $TABLE_NAME ORDER BY $TIMESTAMP DESC LIMIT 1
    """.trimIndent()

    return readableDatabase.query(statement).readToSingleObject { Call.deserialize(it) }
  }

  fun deleteNonAdHocCallEventsOnOrBefore(timestamp: Long) {
    val messageIdsOnOrBeforeTimestamp = """
      SELECT $MESSAGE_ID FROM $TABLE_NAME WHERE $TIMESTAMP <= $timestamp AND $MESSAGE_ID IS NOT NULL
    """.trimIndent()

    writableDatabase.withinTransaction { db ->
      db.delete(MessageTable.TABLE_NAME)
        .where("${MessageTable.ID} IN ($messageIdsOnOrBeforeTimestamp)")
        .run()

      updateCallEventDeletionTimestamps(skipSync = true)
    }
  }

  fun deleteNonAdHocCallEvents(callRowIds: Set<Long>) {
    val messageIds = getMessageIds(callRowIds)
    SignalDatabase.messages.deleteCallUpdates(messageIds)
    updateCallEventDeletionTimestamps()
  }

  fun deleteAllNonAdHocCallEventsExcept(callRowIds: Set<Long>, missedOnly: Boolean) {
    val callFilter = if (missedOnly) {
      "($EVENT = ${Event.serialize(Event.MISSED)} OR $EVENT = ${Event.serialize(Event.MISSED_NOTIFICATION_PROFILE)}) AND $DELETION_TIMESTAMP = 0"
    } else {
      "$DELETION_TIMESTAMP = 0"
    }

    if (callRowIds.isEmpty()) {
      val threadIds = writableDatabase.withinTransaction { db ->
        val ids = db.select(MessageTable.THREAD_ID)
          .from(MessageTable.TABLE_NAME)
          .where(
            """
            ${MessageTable.ID} IN (
              SELECT $MESSAGE_ID FROM $TABLE_NAME
              WHERE $callFilter
            )
          """.toSingleLine()
          )
          .run()
          .readToList { it.requireLong(MessageTable.THREAD_ID) }

        db.delete(MessageTable.TABLE_NAME)
          .where(
            """
            ${MessageTable.ID} IN (
              SELECT $MESSAGE_ID FROM $TABLE_NAME
              WHERE $callFilter
            )
          """.toSingleLine()
          )
          .run()

        ids.toSet()
      }

      threadIds.forEach {
        SignalDatabase.threads.update(
          threadId = it,
          unarchive = false,
          allowDeletion = true
        )
      }

      notifyConversationListeners(threadIds)
      notifyConversationListListeners()
      updateCallEventDeletionTimestamps()
    } else {
      writableDatabase.withinTransaction { db ->
        SqlUtil.buildCollectionQuery(
          column = ID,
          values = callRowIds,
          prefix = "$callFilter AND",
          collectionOperator = SqlUtil.CollectionOperator.NOT_IN
        ).forEach { query ->
          val messageIds = db.select(MESSAGE_ID)
            .from(TABLE_NAME)
            .where(query.where, query.whereArgs)
            .run()
            .readToList { it.requireLong(MESSAGE_ID) }
            .toSet()
          SignalDatabase.messages.deleteCallUpdates(messageIds)
          updateCallEventDeletionTimestamps()
        }
      }
    }
  }

  @Discouraged("Using this method is generally considered an error. Utilize other deletion methods instead of this.")
  fun deleteAllCalls() {
    Log.w(TAG, "Deleting all calls from the local database.")
    writableDatabase.deleteAll(TABLE_NAME)
  }

  private fun getCallSelectionQuery(callId: Long, recipientId: RecipientId): SqlUtil.Query {
    return SqlUtil.Query("$CALL_ID = ? AND $PEER = ?", SqlUtil.buildArgs(callId, recipientId))
  }

  private fun getMessageIds(callRowIds: Set<Long>): Set<Long> {
    val queries = SqlUtil.buildCollectionQuery(
      ID,
      callRowIds,
      "$MESSAGE_ID NOT NULL AND"
    )

    return queries.map { query ->
      readableDatabase.select(MESSAGE_ID).from(TABLE_NAME).where(query.where, query.whereArgs).run().readToList {
        it.requireLong(MESSAGE_ID)
      }
    }.flatten().toSet()
  }

  private fun checkIsGroupOrAdHocCall(call: Call) {
    check(call.type == Type.GROUP_CALL || call.type == Type.AD_HOC_CALL)
  }

  // endregion

  private fun getCallsCursor(isCount: Boolean, offset: Int, limit: Int, searchTerm: String?, filter: CallLogFilter): Cursor {
    val isMissedGenericGroupCall = "$EVENT = ${Event.serialize(Event.GENERIC_GROUP_CALL)} AND $LOCAL_JOINED = ${false.toInt()} AND $GROUP_CALL_ACTIVE = ${false.toInt()}"
    val filterClause: SqlUtil.Query = when (filter) {
      CallLogFilter.ALL -> SqlUtil.buildQuery("$DELETION_TIMESTAMP = 0")
      CallLogFilter.MISSED -> SqlUtil.buildQuery("$TYPE != ${Type.serialize(Type.AD_HOC_CALL)} AND $DIRECTION == ${Direction.serialize(Direction.INCOMING)} AND ($EVENT = ${Event.serialize(Event.MISSED)} OR $EVENT = ${Event.serialize(Event.MISSED_NOTIFICATION_PROFILE)} OR $EVENT = ${Event.serialize(Event.NOT_ACCEPTED)} OR $EVENT = ${Event.serialize(Event.DECLINED)} OR ($isMissedGenericGroupCall)) AND $DELETION_TIMESTAMP = 0")
      CallLogFilter.AD_HOC -> SqlUtil.buildQuery("$TYPE = ${Type.serialize(Type.AD_HOC_CALL)} AND $DELETION_TIMESTAMP = 0")
||||||| parent of e5a36ea5ee (Bumped to upstream version 6.18.1.0-JW.)
      CallLogFilter.ALL -> SqlUtil.buildQuery("$EVENT != ${Event.serialize(Event.DELETE)}")
      CallLogFilter.MISSED -> SqlUtil.buildQuery("$EVENT == ${Event.serialize(Event.MISSED)}")
=======
      CallLogFilter.ALL -> SqlUtil.buildQuery("$DELETION_TIMESTAMP = 0")
      CallLogFilter.MISSED -> SqlUtil.buildQuery("$EVENT = ${Event.serialize(Event.MISSED)} AND $DELETION_TIMESTAMP = 0")
>>>>>>> e5a36ea5ee (Bumped to upstream version 6.18.1.0-JW.)
||||||| parent of e5a36ea5ee (Bumped to upstream version 6.18.1.0-JW.)
      CallLogFilter.ALL -> SqlUtil.buildQuery("$EVENT != ${Event.serialize(Event.DELETE)}")
      CallLogFilter.MISSED -> SqlUtil.buildQuery("$EVENT == ${Event.serialize(Event.MISSED)}")
=======
      CallLogFilter.ALL -> SqlUtil.buildQuery("$DELETION_TIMESTAMP = 0")
      CallLogFilter.MISSED -> SqlUtil.buildQuery("$EVENT = ${Event.serialize(Event.MISSED)} AND $DELETION_TIMESTAMP = 0")
>>>>>>> e5a36ea5ee (Bumped to upstream version 6.18.1.0-JW.)
    }

    val queryClause: SqlUtil.Query = if (!searchTerm.isNullOrEmpty()) {
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    val queryClause = if (!searchTerm.isNullOrEmpty()) {
=======
    val queryClause: SqlUtil.Query = if (!searchTerm.isNullOrEmpty()) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    val queryClause = if (!searchTerm.isNullOrEmpty()) {
=======
    val queryClause: SqlUtil.Query = if (!searchTerm.isNullOrEmpty()) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      val glob = SqlUtil.buildCaseInsensitiveGlobPattern(searchTerm)
      val selection =
        """
        ${RecipientTable.TABLE_NAME}.${RecipientTable.BLOCKED} = ? AND ${RecipientTable.TABLE_NAME}.${RecipientTable.HIDDEN} = ? AND
        (
          sort_name GLOB ? OR 
          ${RecipientTable.TABLE_NAME}.${RecipientTable.USERNAME} GLOB ? OR 
          ${RecipientTable.TABLE_NAME}.${RecipientTable.E164} GLOB ? OR 
          ${RecipientTable.TABLE_NAME}.${RecipientTable.EMAIL} GLOB ?
        )
        """
      SqlUtil.buildQuery(selection, 0, 0, glob, glob, glob, glob)
    } else {
<<<<<<< HEAD
      SqlUtil.buildQuery(
        """
        ${RecipientTable.TABLE_NAME}.${RecipientTable.BLOCKED} = ? AND ${RecipientTable.TABLE_NAME}.${RecipientTable.HIDDEN} = ?
      """,
        0,
        0
      )
    }

||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      SqlUtil.buildQuery("")
    }

<<<<<<< HEAD
    val whereClause = filterClause and queryClause
    val where = if (whereClause.where.isNotEmpty()) {
      "WHERE ${whereClause.where}"
    } else {
      ""
    }

=======
      SqlUtil.buildQuery("")
    }

>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    val whereClause = filterClause and queryClause
    val where = if (whereClause.where.isNotEmpty()) {
      "WHERE ${whereClause.where}"
    } else {
      ""
    }

=======
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    val offsetLimit = if (limit > 0) {
      "LIMIT $offset,$limit"
    } else {
      ""
    }

<<<<<<< HEAD
<<<<<<< HEAD
    val projection = if (isCount) {
      "COUNT(*) OVER() as count,"
    } else {
      "p.$ID, p.$TIMESTAMP, $EVENT, $DIRECTION, $PEER, p.$TYPE, $CALL_ID, $MESSAGE_ID, $RINGER, $LOCAL_JOINED, $GROUP_CALL_ACTIVE, children, in_period, ${MessageTable.BODY},"
    }

    // Group call events by those we consider missed or not missed to build out our call log aggregation.
    val eventTypeSubQuery = """
      ($TABLE_NAME.$EVENT = c.$EVENT AND (
        $TABLE_NAME.$EVENT = ${Event.serialize(Event.MISSED)} OR 
        $TABLE_NAME.$EVENT = ${Event.serialize(Event.MISSED_NOTIFICATION_PROFILE)} OR
        $TABLE_NAME.$EVENT = ${Event.serialize(Event.NOT_ACCEPTED)} OR
        $TABLE_NAME.$EVENT = ${Event.serialize(Event.DECLINED)} OR
        ($TABLE_NAME.$isMissedGenericGroupCall)
      )) OR (
        $TABLE_NAME.$EVENT != ${Event.serialize(Event.MISSED)} AND 
        c.$EVENT != ${Event.serialize(Event.MISSED)} AND 
        $TABLE_NAME.$EVENT != ${Event.serialize(Event.MISSED_NOTIFICATION_PROFILE)} AND 
        c.$EVENT != ${Event.serialize(Event.MISSED_NOTIFICATION_PROFILE)} AND
        $TABLE_NAME.$EVENT != ${Event.serialize(Event.NOT_ACCEPTED)} AND
        c.$EVENT != ${Event.serialize(Event.NOT_ACCEPTED)} AND
        $TABLE_NAME.$EVENT != ${Event.serialize(Event.DECLINED)} AND
        c.$EVENT != ${Event.serialize(Event.DECLINED)} AND
        (NOT ($TABLE_NAME.$isMissedGenericGroupCall)) AND
        (NOT (c.$isMissedGenericGroupCall))
      )
      """

||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
    val projection = if (isCount) {
      "COUNT(*),"
    } else {
      "p.$ID, p.$TIMESTAMP, $EVENT, $DIRECTION, $PEER, p.$TYPE, $CALL_ID, $MESSAGE_ID, $RINGER, children, in_period, ${MessageTable.DATE_RECEIVED}, ${MessageTable.BODY},"
    }

>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
    val projection = if (isCount) {
      "COUNT(*),"
    } else {
      "p.$ID, p.$TIMESTAMP, $EVENT, $DIRECTION, $PEER, p.$TYPE, $CALL_ID, $MESSAGE_ID, $RINGER, children, in_period, ${MessageTable.DATE_RECEIVED}, ${MessageTable.BODY},"
    }

>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    //language=sql
    val statement = """
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
      SELECT $projection
        LOWER(
          COALESCE(
            NULLIF(${GroupTable.TABLE_NAME}.${GroupTable.TITLE}, ''),
            NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.NICKNAME_JOINED_NAME}, ''),
            NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.NICKNAME_GIVEN_NAME}, ''),
            NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.SYSTEM_JOINED_NAME}, ''),
            NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.SYSTEM_GIVEN_NAME}, ''),
            NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.PROFILE_JOINED_NAME}, ''),
            NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.PROFILE_GIVEN_NAME}, ''),
            NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.USERNAME}, '')
          )
        ) AS sort_name
      FROM (
        WITH cte AS (
          SELECT
            $ID, $TIMESTAMP, $EVENT, $DIRECTION, $PEER, $TYPE, $CALL_ID, $MESSAGE_ID, $RINGER, $LOCAL_JOINED, $GROUP_CALL_ACTIVE,
            (
              SELECT
                $ID
              FROM
                $TABLE_NAME
              WHERE
                $TABLE_NAME.$DIRECTION = c.$DIRECTION
                AND $TABLE_NAME.$PEER = c.$PEER
                AND $TABLE_NAME.$TIMESTAMP - $TIME_WINDOW <= c.$TIMESTAMP
                AND $TABLE_NAME.$TIMESTAMP >= c.$TIMESTAMP
                AND ($eventTypeSubQuery)
                AND ${filterClause.where}
              ORDER BY
                $TIMESTAMP DESC
            ) as parent,
            (
              SELECT
                group_concat($ID)
              FROM
                $TABLE_NAME
              WHERE
                $TABLE_NAME.$DIRECTION = c.$DIRECTION
                AND $TABLE_NAME.$PEER = c.$PEER
                AND c.$TIMESTAMP - $TIME_WINDOW <= $TABLE_NAME.$TIMESTAMP
                AND c.$TIMESTAMP >= $TABLE_NAME.$TIMESTAMP
                AND ($eventTypeSubQuery)
                AND ${filterClause.where}
            ) as children,
            (
              SELECT
                group_concat($ID)
              FROM
                $TABLE_NAME
              WHERE
                c.$TIMESTAMP - $TIME_WINDOW <= $TABLE_NAME.$TIMESTAMP
                AND c.$TIMESTAMP >= $TABLE_NAME.$TIMESTAMP
                AND ${filterClause.where}
            ) as in_period
          FROM
            $TABLE_NAME c
          WHERE ${filterClause.where}
          ORDER BY
            $TIMESTAMP DESC
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
      SELECT
      ${if (isCount) "COUNT(*)," else "$TABLE_NAME.*, ${MessageTable.DATE_RECEIVED}, ${MessageTable.BODY},"}
      LOWER(
        COALESCE(
          NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.SYSTEM_JOINED_NAME}, ''),
          NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.SYSTEM_GIVEN_NAME}, ''),
          NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.PROFILE_JOINED_NAME}, ''),
          NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.PROFILE_GIVEN_NAME}, ''),
          NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.USERNAME}, '')
=======
      SELECT
      ${if (isCount) "COUNT(*)," else "$TABLE_NAME.*, ${MessageTable.DATE_RECEIVED}, ${MessageTable.BODY},"}
      LOWER(
        COALESCE(
          NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.SYSTEM_JOINED_NAME}, ''),
          NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.SYSTEM_GIVEN_NAME}, ''),
          NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.PROFILE_JOINED_NAME}, ''),
          NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.PROFILE_GIVEN_NAME}, ''),
          NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.USERNAME}, '')
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      SELECT
      ${if (isCount) "COUNT(*)," else "$TABLE_NAME.*, ${MessageTable.DATE_RECEIVED}, ${MessageTable.BODY},"}
      LOWER(
        COALESCE(
          NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.SYSTEM_JOINED_NAME}, ''),
          NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.SYSTEM_GIVEN_NAME}, ''),
          NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.PROFILE_JOINED_NAME}, ''),
          NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.PROFILE_GIVEN_NAME}, ''),
          NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.USERNAME}, '')
=======
      SELECT $projection
        LOWER(
          COALESCE(
            NULLIF(${GroupTable.TABLE_NAME}.${GroupTable.TITLE}, ''),
            NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.SYSTEM_JOINED_NAME}, ''),
            NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.SYSTEM_GIVEN_NAME}, ''),
            NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.PROFILE_JOINED_NAME}, ''),
            NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.PROFILE_GIVEN_NAME}, ''),
            NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.USERNAME}, '')
          )
        ) AS sort_name
      FROM (
        WITH cte AS (
          SELECT
            $ID, $TIMESTAMP, $EVENT, $DIRECTION, $PEER, $TYPE, $CALL_ID, $MESSAGE_ID, $RINGER,
            (
              SELECT
                $ID
              FROM
                $TABLE_NAME
              WHERE
                $TABLE_NAME.$DIRECTION = c.$DIRECTION
                AND $TABLE_NAME.$PEER = c.$PEER
                AND $TABLE_NAME.$TIMESTAMP - $TIME_WINDOW <= c.$TIMESTAMP
                AND $TABLE_NAME.$TIMESTAMP >= c.$TIMESTAMP
                AND ${filterClause.where}
              ORDER BY
                $TIMESTAMP DESC
            ) as parent,
            (
              SELECT
                group_concat($ID)
              FROM
                $TABLE_NAME
              WHERE
                $TABLE_NAME.$DIRECTION = c.$DIRECTION
                AND $TABLE_NAME.$PEER = c.$PEER
                AND c.$TIMESTAMP - $TIME_WINDOW <= $TABLE_NAME.$TIMESTAMP
                AND c.$TIMESTAMP >= $TABLE_NAME.$TIMESTAMP
                AND ${filterClause.where}
            ) as children,
            (
              SELECT
                group_concat($ID)
              FROM
                $TABLE_NAME
              WHERE
                c.$TIMESTAMP - $TIME_WINDOW <= $TABLE_NAME.$TIMESTAMP
                AND c.$TIMESTAMP >= $TABLE_NAME.$TIMESTAMP
                AND ${filterClause.where}
            ) as in_period
          FROM
            $TABLE_NAME c
          WHERE ${filterClause.where}
          ORDER BY
            $TIMESTAMP DESC
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      SELECT
      ${if (isCount) "COUNT(*)," else "$TABLE_NAME.*, ${MessageTable.DATE_RECEIVED}, ${MessageTable.BODY},"}
      LOWER(
        COALESCE(
          NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.SYSTEM_JOINED_NAME}, ''),
          NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.SYSTEM_GIVEN_NAME}, ''),
          NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.PROFILE_JOINED_NAME}, ''),
          NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.PROFILE_GIVEN_NAME}, ''),
          NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.USERNAME}, '')
=======
      SELECT $projection
        LOWER(
          COALESCE(
            NULLIF(${GroupTable.TABLE_NAME}.${GroupTable.TITLE}, ''),
            NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.SYSTEM_JOINED_NAME}, ''),
            NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.SYSTEM_GIVEN_NAME}, ''),
            NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.PROFILE_JOINED_NAME}, ''),
            NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.PROFILE_GIVEN_NAME}, ''),
            NULLIF(${RecipientTable.TABLE_NAME}.${RecipientTable.USERNAME}, '')
          )
        ) AS sort_name
      FROM (
        WITH cte AS (
          SELECT
            $ID, $TIMESTAMP, $EVENT, $DIRECTION, $PEER, $TYPE, $CALL_ID, $MESSAGE_ID, $RINGER,
            (
              SELECT
                $ID
              FROM
                $TABLE_NAME
              WHERE
                $TABLE_NAME.$DIRECTION = c.$DIRECTION
                AND $TABLE_NAME.$PEER = c.$PEER
                AND $TABLE_NAME.$TIMESTAMP - $TIME_WINDOW <= c.$TIMESTAMP
                AND $TABLE_NAME.$TIMESTAMP >= c.$TIMESTAMP
                AND ${filterClause.where}
              ORDER BY
                $TIMESTAMP DESC
            ) as parent,
            (
              SELECT
                group_concat($ID)
              FROM
                $TABLE_NAME
              WHERE
                $TABLE_NAME.$DIRECTION = c.$DIRECTION
                AND $TABLE_NAME.$PEER = c.$PEER
                AND c.$TIMESTAMP - $TIME_WINDOW <= $TABLE_NAME.$TIMESTAMP
                AND c.$TIMESTAMP >= $TABLE_NAME.$TIMESTAMP
                AND ${filterClause.where}
            ) as children,
            (
              SELECT
                group_concat($ID)
              FROM
                $TABLE_NAME
              WHERE
                c.$TIMESTAMP - $TIME_WINDOW <= $TABLE_NAME.$TIMESTAMP
                AND c.$TIMESTAMP >= $TABLE_NAME.$TIMESTAMP
                AND ${filterClause.where}
            ) as in_period
          FROM
            $TABLE_NAME c
          WHERE ${filterClause.where}
          ORDER BY
            $TIMESTAMP DESC
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        )
<<<<<<< HEAD
<<<<<<< HEAD
        SELECT
          *,
          CASE
            WHEN LAG (parent, 1, 0) OVER (
              ORDER BY
                $TIMESTAMP DESC
            ) != parent THEN $ID
            ELSE parent
          END true_parent
        FROM
          cte
      ) p
      INNER JOIN ${RecipientTable.TABLE_NAME} ON ${RecipientTable.TABLE_NAME}.${RecipientTable.ID} = $PEER
      LEFT JOIN ${MessageTable.TABLE_NAME} ON ${MessageTable.TABLE_NAME}.${MessageTable.ID} = $MESSAGE_ID
      LEFT JOIN ${GroupTable.TABLE_NAME} ON ${GroupTable.TABLE_NAME}.${GroupTable.RECIPIENT_ID} = ${RecipientTable.TABLE_NAME}.${RecipientTable.ID}
      WHERE true_parent = p.$ID 
        AND CASE 
          WHEN p.$TYPE = ${Type.serialize(Type.AD_HOC_CALL)} THEN EXISTS (SELECT * FROM ${CallLinkTable.TABLE_NAME} WHERE ${CallLinkTable.RECIPIENT_ID} = $PEER AND ${CallLinkTable.ROOT_KEY} NOT NULL) 
          ELSE 1
        END 
        ${if (queryClause.where.isNotEmpty()) "AND ${queryClause.where}" else ""}
      GROUP BY CASE WHEN p.type = 4 THEN p.peer ELSE p._id END
      ORDER BY p.$TIMESTAMP DESC
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      ) AS sort_name
      FROM $TABLE_NAME
      INNER JOIN ${RecipientTable.TABLE_NAME} ON ${RecipientTable.TABLE_NAME}.${RecipientTable.ID} = $TABLE_NAME.$PEER
      INNER JOIN ${MessageTable.TABLE_NAME} ON ${MessageTable.TABLE_NAME}.${MessageTable.ID} = $TABLE_NAME.$MESSAGE_ID
      $where
      ORDER BY ${MessageTable.TABLE_NAME}.${MessageTable.DATE_RECEIVED} DESC
=======
        SELECT
          *,
          CASE
            WHEN LAG (parent, 1, 0) OVER (
              ORDER BY
                $TIMESTAMP DESC
            ) != parent THEN $ID
            ELSE parent
          END true_parent
        FROM
          cte
      ) p
      INNER JOIN ${RecipientTable.TABLE_NAME} ON ${RecipientTable.TABLE_NAME}.${RecipientTable.ID} = $PEER
      INNER JOIN ${MessageTable.TABLE_NAME} ON ${MessageTable.TABLE_NAME}.${MessageTable.ID} = $MESSAGE_ID
      LEFT JOIN ${GroupTable.TABLE_NAME} ON ${GroupTable.TABLE_NAME}.${GroupTable.RECIPIENT_ID} = ${RecipientTable.TABLE_NAME}.${RecipientTable.ID}
      WHERE true_parent = p.$ID ${if (queryClause.where.isNotEmpty()) "AND ${queryClause.where}" else ""}
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      ) AS sort_name
      FROM $TABLE_NAME
      INNER JOIN ${RecipientTable.TABLE_NAME} ON ${RecipientTable.TABLE_NAME}.${RecipientTable.ID} = $TABLE_NAME.$PEER
      INNER JOIN ${MessageTable.TABLE_NAME} ON ${MessageTable.TABLE_NAME}.${MessageTable.ID} = $TABLE_NAME.$MESSAGE_ID
      $where
      ORDER BY ${MessageTable.TABLE_NAME}.${MessageTable.DATE_RECEIVED} DESC
=======
        SELECT
          *,
          CASE
            WHEN LAG (parent, 1, 0) OVER (
              ORDER BY
                $TIMESTAMP DESC
            ) != parent THEN $ID
            ELSE parent
          END true_parent
        FROM
          cte
      ) p
      INNER JOIN ${RecipientTable.TABLE_NAME} ON ${RecipientTable.TABLE_NAME}.${RecipientTable.ID} = $PEER
      INNER JOIN ${MessageTable.TABLE_NAME} ON ${MessageTable.TABLE_NAME}.${MessageTable.ID} = $MESSAGE_ID
      LEFT JOIN ${GroupTable.TABLE_NAME} ON ${GroupTable.TABLE_NAME}.${GroupTable.RECIPIENT_ID} = ${RecipientTable.TABLE_NAME}.${RecipientTable.ID}
      WHERE true_parent = p.$ID ${if (queryClause.where.isNotEmpty()) "AND ${queryClause.where}" else ""}
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      $offsetLimit
    """

<<<<<<< HEAD
<<<<<<< HEAD
    return readableDatabase.query(
      statement,
      queryClause.whereArgs
    )
  }

  fun getLatestRingingCalls(): List<Call> {
    return readableDatabase.select()
      .from(TABLE_NAME)
      .where("$EVENT = ?", Event.serialize(Event.RINGING))
      .limit(10)
      .orderBy(TIMESTAMP)
      .run()
      .readToList {
        Call.deserialize(it)
      }
  }

  fun markRingingCallsAsMissed() {
    writableDatabase.withinTransaction { db ->
      val messageIds: List<Long> = db.select(MESSAGE_ID)
        .from(TABLE_NAME)
        .where("$EVENT = ? AND $MESSAGE_ID != NULL", Event.serialize(Event.RINGING))
        .run()
        .readToList { it.requireLong(MESSAGE_ID) }

      db.update(TABLE_NAME)
        .values(EVENT to Event.serialize(Event.MISSED))
        .where("$EVENT = ?", Event.serialize(Event.RINGING))
        .run()

      SignalDatabase.messages.clearIsRingingOnLocalDeviceFlag(messageIds)
    }
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    return readableDatabase.query(statement, whereClause.whereArgs)
=======
    return readableDatabase.query(
      statement,
      queryClause.whereArgs
    )
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    return readableDatabase.query(statement, whereClause.whereArgs)
=======
    return readableDatabase.query(
      statement,
      queryClause.whereArgs
    )
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  }

  fun getCallsCount(searchTerm: String?, filter: CallLogFilter): Int {
    return getCallsCursor(true, 0, 0, searchTerm, filter).use {
      if (it.moveToFirst()) {
        it.getInt(0)
      } else {
        0
      }
    }
  }

  fun getCalls(offset: Int, limit: Int, searchTerm: String?, filter: CallLogFilter): List<CallLogRow.Call> {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    return getCallsCursor(false, offset, limit, searchTerm, filter).readToList { cursor ->
      val call = Call.deserialize(cursor)
      val groupCallDetails = GroupCallUpdateDetailsUtil.parse(cursor.requireString(MessageTable.BODY))

      val children = cursor.requireNonNullString("children")
        .split(',')
        .map { it.toLong() }
        .toSet()

      val inPeriod = cursor.requireNonNullString("in_period")
        .split(',')
        .map { it.toLong() }
        .sortedDescending()
        .toSet()

      val actualChildren = inPeriod.takeWhile { children.contains(it) }
      val peer = Recipient.resolved(call.peer)

      val canUserBeginCall = if (peer.isGroup) {
        val record = SignalDatabase.groups.getGroup(peer.id)

        !record.isAbsent() &&
          record.get().isActive &&
          (!record.get().isAnnouncementGroup || record.get().memberLevel(Recipient.self()) == GroupTable.MemberLevel.ADMINISTRATOR)
      } else {
        true
      }

||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    return getCallsCursor(false, offset, limit, searchTerm, filter).readToList {
      val call = Call.deserialize(it)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    return getCallsCursor(false, offset, limit, searchTerm, filter).readToList {
      val call = Call.deserialize(it)
=======
    return getCallsCursor(false, offset, limit, searchTerm, filter).readToList { cursor ->
      val call = Call.deserialize(cursor)
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      val recipient = Recipient.resolved(call.peer)
<<<<<<< HEAD
      val date = it.requireLong(MessageTable.DATE_RECEIVED)
<<<<<<< HEAD
<<<<<<< HEAD
=======
    return getCallsCursor(false, offset, limit, searchTerm, filter).readToList {
      val call = Call.deserialize(it)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    return getCallsCursor(false, offset, limit, searchTerm, filter).readToList {
      val call = Call.deserialize(it)
=======
    return getCallsCursor(false, offset, limit, searchTerm, filter).readToList { cursor ->
      val call = Call.deserialize(cursor)
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      val recipient = Recipient.resolved(call.peer)
      val date = cursor.requireLong(MessageTable.DATE_RECEIVED)
      val groupCallDetails = GroupCallUpdateDetailsUtil.parse(cursor.requireString(MessageTable.BODY))

<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of e5a36ea5ee (Bumped to upstream version 6.18.1.0-JW.)
=======
      Log.d(TAG, "${cursor.requireNonNullString("in_period")}")

      val children = cursor.requireNonNullString("children")
        .split(',')
        .map { it.toLong() }
        .toSet()

      val inPeriod = cursor.requireNonNullString("in_period")
        .split(',')
        .map { it.toLong() }
        .sortedDescending()
        .toSet()

      val actualChildren = inPeriod.takeWhile { children.contains(it) }

>>>>>>> e5a36ea5ee (Bumped to upstream version 6.18.1.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
      val groupCallDetails = GroupCallUpdateDetailsUtil.parse(it.requireString(MessageTable.BODY))
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      val date = it.requireLong(MessageTable.DATE_RECEIVED)
      val groupCallDetails = GroupCallUpdateDetailsUtil.parse(it.requireString(MessageTable.BODY))
=======
      val date = cursor.requireLong(MessageTable.DATE_RECEIVED)
      val groupCallDetails = GroupCallUpdateDetailsUtil.parse(cursor.requireString(MessageTable.BODY))
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)

>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of e5a36ea5ee (Bumped to upstream version 6.18.1.0-JW.)
=======
      Log.d(TAG, "${cursor.requireNonNullString("in_period")}")

      val children = cursor.requireNonNullString("children")
        .split(',')
        .map { it.toLong() }
        .toSet()

      val inPeriod = cursor.requireNonNullString("in_period")
        .split(',')
        .map { it.toLong() }
        .sortedDescending()
        .toSet()

      val actualChildren = inPeriod.takeWhile { children.contains(it) }

>>>>>>> e5a36ea5ee (Bumped to upstream version 6.18.1.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
      val groupCallDetails = GroupCallUpdateDetailsUtil.parse(it.requireString(MessageTable.BODY))

>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
      CallLogRow.Call(
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        record = call,
        date = call.timestamp,
        peer = peer,
        groupCallState = CallLogRow.GroupCallState.fromDetails(groupCallDetails),
        children = actualChildren.toSet(),
        searchQuery = searchTerm,
        callLinkPeekInfo = AppDependencies.signalCallManager.peekInfoSnapshot[peer.id],
        canUserBeginCall = canUserBeginCall
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        call = call,
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        call = call,
=======
        record = call,
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        peer = recipient,
<<<<<<< HEAD
<<<<<<< HEAD
        date = date
=======
        call = call,
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        call = call,
=======
        record = call,
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        peer = recipient,
        date = date,
<<<<<<< HEAD
        groupCallState = CallLogRow.GroupCallState.fromDetails(groupCallDetails)
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        groupCallState = CallLogRow.GroupCallState.fromDetails(groupCallDetails)
=======
        groupCallState = CallLogRow.GroupCallState.fromDetails(groupCallDetails),
<<<<<<< HEAD
        children = cursor.requireNonNullString("children")
          .split(',')
          .map { it.toLong() }
          .toSet()
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of e5a36ea5ee (Bumped to upstream version 6.18.1.0-JW.)
        children = cursor.requireNonNullString("children")
          .split(',')
          .map { it.toLong() }
          .toSet()
=======
        children = actualChildren.toSet()
>>>>>>> e5a36ea5ee (Bumped to upstream version 6.18.1.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        date = date
=======
        date = date,
<<<<<<< HEAD
        groupCallState = CallLogRow.GroupCallState.fromDetails(groupCallDetails)
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        groupCallState = CallLogRow.GroupCallState.fromDetails(groupCallDetails)
=======
        groupCallState = CallLogRow.GroupCallState.fromDetails(groupCallDetails),
<<<<<<< HEAD
        children = cursor.requireNonNullString("children")
          .split(',')
          .map { it.toLong() }
          .toSet()
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of e5a36ea5ee (Bumped to upstream version 6.18.1.0-JW.)
        children = cursor.requireNonNullString("children")
          .split(',')
          .map { it.toLong() }
          .toSet()
=======
        children = actualChildren.toSet()
>>>>>>> e5a36ea5ee (Bumped to upstream version 6.18.1.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        date = date
=======
        date = date,
        groupCallState = CallLogRow.GroupCallState.fromDetails(groupCallDetails)
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
      )
    }
  }

  override fun remapRecipient(fromId: RecipientId, toId: RecipientId) {
    writableDatabase
      .update(TABLE_NAME)
      .values(PEER to toId.serialize())
      .where("$PEER = ?", fromId)
      .run()
  }

  /**
   * @param isGroupCallActive - Whether the group call currently contains users. Only valid for group calls.
   * @param didLocalUserJoin   - Determines whether the local user joined this call. Only valid for group calls.
   */
  data class Call(
    val callId: Long,
    val peer: RecipientId,
    val type: Type,
    val direction: Direction,
    val event: Event,
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    val messageId: Long?,
    val timestamp: Long,
    val ringerRecipient: RecipientId?,
    val isGroupCallActive: Boolean,
    val didLocalUserJoin: Boolean
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    val messageId: Long
=======
    val messageId: Long?,
    val timestamp: Long,
    val ringerRecipient: RecipientId?
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    val messageId: Long
=======
    val messageId: Long?,
    val timestamp: Long,
    val ringerRecipient: RecipientId?
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    val messageId: Long
=======
    val messageId: Long?,
    val timestamp: Long,
    val ringerRecipient: RecipientId?
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
  ) {
    val messageType: Long = getMessageType(type, direction, event)

    val isDisplayedAsMissedCallInUi = isDisplayedAsMissedCallInUi(this)

    companion object Deserializer : Serializer<Call, Cursor> {

      private fun isDisplayedAsMissedCallInUi(call: Call): Boolean {
        return call.direction == Direction.INCOMING && (call.event in Event.DISPLAY_AS_MISSED_CALL || (call.event == Event.GENERIC_GROUP_CALL && !call.didLocalUserJoin && !call.isGroupCallActive))
      }

      fun getMessageType(type: Type, direction: Direction, event: Event): Long {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        if (type == Type.GROUP_CALL || type == Type.AD_HOC_CALL) {
          return MessageTypes.GROUP_CALL_TYPE
        }

        return if (direction == Direction.INCOMING && event.isMissedCall()) {
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
        if (type == Type.GROUP_CALL) {
          return MessageTypes.GROUP_CALL_TYPE
        }

        if (type == Type.AD_HOC_CALL) {
          error("Ad-Hoc calls are not linked to messages.")
        }

>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
        if (type == Type.GROUP_CALL) {
          return MessageTypes.GROUP_CALL_TYPE
        }

        if (type == Type.AD_HOC_CALL) {
          error("Ad-Hoc calls are not linked to messages.")
        }

>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        return if (direction == Direction.INCOMING && event == Event.MISSED) {
=======
        if (type == Type.GROUP_CALL) {
          return MessageTypes.GROUP_CALL_TYPE
        }

        if (type == Type.AD_HOC_CALL) {
          error("Ad-Hoc calls are not linked to messages.")
        }

        return if (direction == Direction.INCOMING && event == Event.MISSED) {
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
          if (type == Type.VIDEO_CALL) MessageTypes.MISSED_VIDEO_CALL_TYPE else MessageTypes.MISSED_AUDIO_CALL_TYPE
        } else if (direction == Direction.INCOMING) {
          if (type == Type.VIDEO_CALL) MessageTypes.INCOMING_VIDEO_CALL_TYPE else MessageTypes.INCOMING_AUDIO_CALL_TYPE
        } else {
          if (type == Type.VIDEO_CALL) MessageTypes.OUTGOING_VIDEO_CALL_TYPE else MessageTypes.OUTGOING_AUDIO_CALL_TYPE
        }
      }

      override fun serialize(data: Call): Cursor {
        throw UnsupportedOperationException()
      }

      override fun deserialize(data: Cursor): Call {
        return Call(
          callId = data.requireLong(CALL_ID),
          peer = RecipientId.from(data.requireLong(PEER)),
          type = data.requireObject(TYPE, Type.Serializer),
          direction = data.requireObject(DIRECTION, Direction.Serializer),
          event = data.requireObject(EVENT, Event.Serializer),
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
          messageId = data.requireLong(MESSAGE_ID).takeIf { it > 0L },
          timestamp = data.requireLong(TIMESTAMP),
          ringerRecipient = data.requireLong(RINGER).let {
            if (it > 0) {
              RecipientId.from(it)
            } else {
              null
            }
          },
          isGroupCallActive = data.requireBoolean(GROUP_CALL_ACTIVE),
          didLocalUserJoin = data.requireBoolean(LOCAL_JOINED)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
          messageId = data.requireLong(MESSAGE_ID)
=======
          messageId = data.requireLong(MESSAGE_ID).takeIf { it > 0L },
          timestamp = data.requireLong(TIMESTAMP),
          ringerRecipient = data.requireLong(RINGER).let {
            if (it > 0) {
              RecipientId.from(it)
            } else {
              null
            }
          }
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
          messageId = data.requireLong(MESSAGE_ID)
=======
          messageId = data.requireLong(MESSAGE_ID).takeIf { it > 0L },
          timestamp = data.requireLong(TIMESTAMP),
          ringerRecipient = data.requireLong(RINGER).let {
            if (it > 0) {
              RecipientId.from(it)
            } else {
              null
            }
          }
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
          messageId = data.requireLong(MESSAGE_ID)
=======
          messageId = data.requireLong(MESSAGE_ID).takeIf { it > 0L },
          timestamp = data.requireLong(TIMESTAMP),
          ringerRecipient = data.requireLong(RINGER).let {
            if (it > 0) {
              RecipientId.from(it)
            } else {
              null
            }
          }
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        )
      }
    }
  }

  enum class Type(private val code: Int) {
    AUDIO_CALL(0),
    VIDEO_CALL(1),
    GROUP_CALL(3),
    AD_HOC_CALL(4);

    companion object Serializer : IntSerializer<Type> {
      override fun serialize(data: Type): Int = data.code

      override fun deserialize(data: Int): Type {
        return when (data) {
          AUDIO_CALL.code -> AUDIO_CALL
          VIDEO_CALL.code -> VIDEO_CALL
          GROUP_CALL.code -> GROUP_CALL
          AD_HOC_CALL.code -> AD_HOC_CALL
          else -> throw IllegalArgumentException("Unknown type $data")
        }
      }

      @JvmStatic
      fun from(type: CallEvent.Type?): Type? {
        return when (type) {
          null, CallEvent.Type.UNKNOWN_TYPE -> null
          CallEvent.Type.AUDIO_CALL -> AUDIO_CALL
          CallEvent.Type.VIDEO_CALL -> VIDEO_CALL
          CallEvent.Type.GROUP_CALL -> GROUP_CALL
          CallEvent.Type.AD_HOC_CALL -> AD_HOC_CALL
        }
      }
    }
  }

  enum class Direction(private val code: Int) {
    INCOMING(0),
    OUTGOING(1);

    companion object Serializer : IntSerializer<Direction> {
      override fun serialize(data: Direction): Int = data.code

      override fun deserialize(data: Int): Direction {
        return when (data) {
          INCOMING.code -> INCOMING
          OUTGOING.code -> OUTGOING
          else -> throw IllegalArgumentException("Unknown type $data")
        }
      }

      @JvmStatic
      fun from(direction: CallEvent.Direction?): Direction? {
        return when (direction) {
          null, CallEvent.Direction.UNKNOWN_DIRECTION -> null
          CallEvent.Direction.INCOMING -> INCOMING
          CallEvent.Direction.OUTGOING -> OUTGOING
        }
      }
    }
  }

<<<<<<< HEAD
<<<<<<< HEAD
  enum class ReadState(private val code: Int) {
    UNREAD(0),
    READ(1);

    companion object Serializer : IntSerializer<ReadState> {
      override fun serialize(data: ReadState): Int {
        return data.code
      }

      override fun deserialize(data: Int): ReadState {
        return ReadState.values().first { it.code == data }
      }
    }
  }

||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
  sealed interface CallConversationId {
    data class Peer(val recipientId: RecipientId) : CallConversationId
    data class CallLink(val callLinkId: Int) : CallConversationId
  }

>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
  sealed interface CallConversationId {
    data class Peer(val recipientId: RecipientId) : CallConversationId
    data class CallLink(val callLinkId: Int) : CallConversationId
  }

>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  enum class Event(private val code: Int) {
    /**
     * 1:1 Calls only.
     */
    ONGOING(0),

    /**
     * 1:1 and Group Calls.
     *
     * Group calls: You accepted a ring.
     */
    ACCEPTED(1),

    /**
     * 1:1 Calls only.
     */
    NOT_ACCEPTED(2),
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD

    /**
     * 1:1 and Group/Ad-Hoc Calls.
     *
     * Group calls: The remote ring has expired or was cancelled by the ringer.
     */
    MISSED(3),

    /**
     * 1:1 and Group/Ad-Hoc Calls.
     *
     * Call was auto-declined due to a notification profile.
     */
    MISSED_NOTIFICATION_PROFILE(10),

    /**
     * 1:1 and Group/Ad-Hoc Calls.
     */
    DELETE(4),

    /**
     * Group/Ad-Hoc Calls only.
     *
     * Initial state.
     */
    GENERIC_GROUP_CALL(5),

    /**
     * Group Calls: User has joined the group call.
     */
    JOINED(6),

    /**
     * Group Calls: If a ring was requested by another user.
     */
    RINGING(7),

    /**
     * Group Calls: If you declined a ring.
     */
    DECLINED(8),

    /**
     * Group Calls: If you are ringing a group.
     */
    OUTGOING_RING(9); // Next is 11

    fun isMissedCall(): Boolean {
      return this == MISSED || this == MISSED_NOTIFICATION_PROFILE
    }
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    MISSED(3);
=======

    /**
     * 1:1 and Group/Ad-Hoc Calls.
     *
     * Group calls: The remote ring has expired or was cancelled by the ringer.
     */
    MISSED(3),

    /**
     * 1:1 and Group/Ad-Hoc Calls.
     */
    DELETE(4),

    /**
     * Group/Ad-Hoc Calls only.
     *
     * Initial state.
     */
    GENERIC_GROUP_CALL(5),

    /**
     * Group Calls: User has joined the group call.
     */
    JOINED(6),

    /**
     * Group Calls: If a ring was requested by another user.
     */
    RINGING(7),

    /**
     * Group Calls: If you declined a ring.
     */
    DECLINED(8),

    /**
     * Group Calls: If you are ringing a group.
     */
    OUTGOING_RING(9);
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    MISSED(3);
=======

    /**
     * 1:1 and Group/Ad-Hoc Calls.
     *
     * Group calls: The remote ring has expired or was cancelled by the ringer.
     */
    MISSED(3),

    /**
     * 1:1 and Group/Ad-Hoc Calls.
     */
    DELETE(4),

    /**
     * Group/Ad-Hoc Calls only.
     *
     * Initial state.
     */
    GENERIC_GROUP_CALL(5),

    /**
     * Group Calls: User has joined the group call.
     */
    JOINED(6),

    /**
     * Group Calls: If a ring was requested by another user.
     */
    RINGING(7),

    /**
     * Group Calls: If you declined a ring.
     */
    DECLINED(8),

    /**
     * Group Calls: If you are ringing a group.
     */
    OUTGOING_RING(9);
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    MISSED(3);
=======

    /**
     * 1:1 and Group/Ad-Hoc Calls.
     *
     * Group calls: The remote ring has expired or was cancelled by the ringer.
     */
    MISSED(3),

    /**
     * 1:1 and Group/Ad-Hoc Calls.
     */
    DELETE(4),

    /**
     * Group/Ad-Hoc Calls only.
     *
     * Initial state.
     */
    GENERIC_GROUP_CALL(5),

    /**
     * Group Calls: User has joined the group call.
     */
    JOINED(6),

    /**
     * Group Calls: If a ring was requested by another user.
     */
    RINGING(7),

    /**
     * Group Calls: If you declined a ring.
     */
    DECLINED(8),

    /**
     * Group Calls: If you are ringing a group.
     */
    OUTGOING_RING(9);
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)

    companion object Serializer : IntSerializer<Event> {

      val DISPLAY_AS_MISSED_CALL = listOf(
        MISSED,
        MISSED_NOTIFICATION_PROFILE,
        DECLINED,
        NOT_ACCEPTED
      )

      override fun serialize(data: Event): Int = data.code

      override fun deserialize(data: Int): Event {
        return values().firstOrNull {
          it.code == data
        } ?: throw IllegalArgumentException("Unknown event $data")
      }

      @JvmStatic
      fun from(event: CallEvent.Event?): Event? {
        return when (event) {
          null, CallEvent.Event.UNKNOWN_ACTION, CallEvent.Event.OBSERVED -> null
          CallEvent.Event.ACCEPTED -> ACCEPTED
          CallEvent.Event.NOT_ACCEPTED -> NOT_ACCEPTED
          CallEvent.Event.DELETE -> DELETE
        }
      }
    }
  }
}
