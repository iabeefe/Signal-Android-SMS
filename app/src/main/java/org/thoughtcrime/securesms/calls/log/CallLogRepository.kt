package org.thoughtcrime.securesms.calls.log

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
<<<<<<< HEAD
import org.signal.core.util.concurrent.SignalExecutors
import org.signal.core.util.withinTransaction
import org.thoughtcrime.securesms.calls.links.UpdateCallLinkRepository
import org.thoughtcrime.securesms.database.CallLinkTable
||||||| parent of e5a36ea5ee (Bumped to upstream version 6.18.1.0-JW.)
=======
import org.signal.core.util.concurrent.SignalExecutors
>>>>>>> e5a36ea5ee (Bumped to upstream version 6.18.1.0-JW.)
import org.thoughtcrime.securesms.database.DatabaseObserver
import org.thoughtcrime.securesms.database.SignalDatabase
import org.thoughtcrime.securesms.dependencies.AppDependencies
import org.thoughtcrime.securesms.jobs.CallLogEventSendJob
import org.thoughtcrime.securesms.service.webrtc.links.CallLinkRoomId
import org.thoughtcrime.securesms.service.webrtc.links.UpdateCallLinkResult

class CallLogRepository(
  private val updateCallLinkRepository: UpdateCallLinkRepository = UpdateCallLinkRepository(),
  private val callLogPeekHelper: CallLogPeekHelper
) : CallLogPagedDataSource.CallRepository {

  override fun getCallsCount(query: String?, filter: CallLogFilter): Int {
    return SignalDatabase.calls.getCallsCount(query, filter)
  }

  override fun getCalls(query: String?, filter: CallLogFilter, start: Int, length: Int): List<CallLogRow> {
    return SignalDatabase.calls.getCalls(start, length, query, filter)
  }

<<<<<<< HEAD
  override fun getCallLinksCount(query: String?, filter: CallLogFilter): Int {
    return when (filter) {
      CallLogFilter.MISSED -> 0
      CallLogFilter.ALL, CallLogFilter.AD_HOC -> SignalDatabase.callLinks.getCallLinksCount(query)
    }
  }

  override fun getCallLinks(query: String?, filter: CallLogFilter, start: Int, length: Int): List<CallLogRow> {
    return when (filter) {
      CallLogFilter.MISSED -> emptyList()
      CallLogFilter.ALL, CallLogFilter.AD_HOC -> SignalDatabase.callLinks.getCallLinks(query, start, length)
    }
  }

  override fun onCallTabPageLoaded(pageData: List<CallLogRow>) {
    SignalExecutors.BOUNDED_IO.execute {
      callLogPeekHelper.onPageLoaded(pageData)
    }
  }

  fun markAllCallEventsRead() {
    SignalExecutors.BOUNDED_IO.execute {
      val latestCall = SignalDatabase.calls.getLatestCall() ?: return@execute
      SignalDatabase.calls.markAllCallEventsRead()
      AppDependencies.jobManager.add(CallLogEventSendJob.forMarkedAsRead(latestCall))
    }
  }

||||||| parent of e5a36ea5ee (Bumped to upstream version 6.18.1.0-JW.)
=======
  fun markAllCallEventsRead() {
    SignalExecutors.BOUNDED_IO.execute {
      SignalDatabase.messages.markAllCallEventsRead()
    }
  }

>>>>>>> e5a36ea5ee (Bumped to upstream version 6.18.1.0-JW.)
  fun listenForChanges(): Observable<Unit> {
    return Observable.create { emitter ->
      fun refresh() {
        emitter.onNext(Unit)
      }

      val databaseObserver = DatabaseObserver.Observer {
        refresh()
      }

<<<<<<< HEAD
      AppDependencies.databaseObserver.registerConversationListObserver(databaseObserver)
      AppDependencies.databaseObserver.registerCallUpdateObserver(databaseObserver)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
      val messageObserver = DatabaseObserver.MessageObserver {
        refresh()
      }

      ApplicationDependencies.getDatabaseObserver().registerConversationListObserver(databaseObserver)
      ApplicationDependencies.getDatabaseObserver().registerMessageUpdateObserver(messageObserver)
=======
      ApplicationDependencies.getDatabaseObserver().registerConversationListObserver(databaseObserver)
      ApplicationDependencies.getDatabaseObserver().registerCallUpdateObserver(databaseObserver)
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)

      emitter.setCancellable {
<<<<<<< HEAD
        AppDependencies.databaseObserver.unregisterObserver(databaseObserver)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
        ApplicationDependencies.getDatabaseObserver().unregisterObserver(databaseObserver)
        ApplicationDependencies.getDatabaseObserver().unregisterObserver(messageObserver)
=======
        ApplicationDependencies.getDatabaseObserver().unregisterObserver(databaseObserver)
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
      }
    }
  }

  fun deleteSelectedCallLogs(
<<<<<<< HEAD
<<<<<<< HEAD
    selectedCallRowIds: Set<Long>
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    selectedMessageIds: Set<Long>
=======
    selectedCallIds: Set<Long>
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    selectedCallIds: Set<Long>
=======
    selectedCallRowIds: Set<Long>
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  ): Completable {
    return Completable.fromAction {
<<<<<<< HEAD
<<<<<<< HEAD
      SignalDatabase.calls.deleteNonAdHocCallEvents(selectedCallRowIds)
    }.subscribeOn(Schedulers.io())
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
      SignalDatabase.messages.deleteCallUpdates(selectedMessageIds)
    }.observeOn(Schedulers.io())
=======
      SignalDatabase.calls.deleteCallEvents(selectedCallIds)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      SignalDatabase.calls.deleteCallEvents(selectedCallIds)
=======
      SignalDatabase.calls.deleteCallEvents(selectedCallRowIds)
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    }.observeOn(Schedulers.io())
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
  }

  fun deleteAllCallLogsExcept(
<<<<<<< HEAD
<<<<<<< HEAD
    selectedCallRowIds: Set<Long>,
    missedOnly: Boolean
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    selectedMessageIds: Set<Long>
=======
    selectedCallIds: Set<Long>
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    selectedCallIds: Set<Long>
=======
    selectedCallRowIds: Set<Long>
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  ): Completable {
    return Completable.fromAction {
<<<<<<< HEAD
<<<<<<< HEAD
      SignalDatabase.calls.deleteAllNonAdHocCallEventsExcept(selectedCallRowIds, missedOnly)
    }.subscribeOn(Schedulers.io())
  }

  /**
   * Delete all call events / unowned links and enqueue clear history job, and then
   * emit a clear history message.
   *
   * This explicitly drops failed call link revocations of call links, and those call links
   * will remain visible to the user. This is safe because the clear history sync message should
   * only clear local history and then poll link status from the server.
   */
  fun deleteAllCallLogsOnOrBeforeNow(): Single<Int> {
    return Single.fromCallable {
      SignalDatabase.rawDatabase.withinTransaction {
        val latestCall = SignalDatabase.calls.getLatestCall() ?: return@withinTransaction
        SignalDatabase.calls.deleteNonAdHocCallEventsOnOrBefore(latestCall.timestamp)
        SignalDatabase.callLinks.deleteNonAdminCallLinksOnOrBefore(latestCall.timestamp)
        AppDependencies.jobManager.add(CallLogEventSendJob.forClearHistory(latestCall))
      }

      SignalDatabase.callLinks.getAllAdminCallLinksExcept(emptySet())
    }.flatMap(this::deleteAndCollectResults).map { 0 }.subscribeOn(Schedulers.io())
  }

  /**
   * Deletes the selected call links. We DELETE those links we don't have admin keys for,
   * and revoke the ones we *do* have admin keys for. We then perform a cleanup step on
   * terminate to clean up call events.
   */
  fun deleteSelectedCallLinks(
    selectedCallRowIds: Set<Long>,
    selectedRoomIds: Set<CallLinkRoomId>
  ): Single<Int> {
    return Single.fromCallable {
      val allCallLinkIds = SignalDatabase.calls.getCallLinkRoomIdsFromCallRowIds(selectedCallRowIds) + selectedRoomIds
      SignalDatabase.callLinks.deleteNonAdminCallLinks(allCallLinkIds)
      SignalDatabase.callLinks.getAdminCallLinks(allCallLinkIds)
    }.flatMap(this::deleteAndCollectResults).subscribeOn(Schedulers.io())
  }

  /**
   * Deletes all but the selected call links. We DELETE those links we don't have admin keys for,
   * and revoke the ones we *do* have admin keys for. We then perform a cleanup step on
   * terminate to clean up call events.
   */
  fun deleteAllCallLinksExcept(
    selectedCallRowIds: Set<Long>,
    selectedRoomIds: Set<CallLinkRoomId>
  ): Single<Int> {
    return Single.fromCallable {
      val allCallLinkIds = SignalDatabase.calls.getCallLinkRoomIdsFromCallRowIds(selectedCallRowIds) + selectedRoomIds
      SignalDatabase.callLinks.deleteAllNonAdminCallLinksExcept(allCallLinkIds)
      SignalDatabase.callLinks.getAllAdminCallLinksExcept(allCallLinkIds)
    }.flatMap(this::deleteAndCollectResults).subscribeOn(Schedulers.io())
  }

  private fun deleteAndCollectResults(callLinksToRevoke: Set<CallLinkTable.CallLink>): Single<Int> {
    return Single.merge(
      callLinksToRevoke.map {
        updateCallLinkRepository.deleteCallLink(it.credentials!!)
      }
    ).reduce(0) { acc, current ->
      acc + (if (current is UpdateCallLinkResult.Delete) 0 else 1)
    }.doOnTerminate {
      SignalDatabase.calls.updateAdHocCallEventDeletionTimestamps()
    }.doOnDispose {
      SignalDatabase.calls.updateAdHocCallEventDeletionTimestamps()
    }
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
      SignalDatabase.messages.deleteAllCallUpdatesExcept(selectedMessageIds)
    }.observeOn(Schedulers.io())
=======
      SignalDatabase.calls.deleteAllCallEventsExcept(selectedCallIds)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      SignalDatabase.calls.deleteAllCallEventsExcept(selectedCallIds)
=======
      SignalDatabase.calls.deleteAllCallEventsExcept(selectedCallRowIds)
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    }.observeOn(Schedulers.io())
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
  }
}
