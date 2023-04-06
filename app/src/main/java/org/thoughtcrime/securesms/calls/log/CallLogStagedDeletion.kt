package org.thoughtcrime.securesms.calls.log

import androidx.annotation.MainThread
import io.reactivex.rxjava3.core.Single

/**
 * Encapsulates a single deletion action
 */
class CallLogStagedDeletion(
  private val filter: CallLogFilter,
  private val stateSnapshot: CallLogSelectionState,
  private val repository: CallLogRepository
) {

  private var isCommitted = false

  /**
   * Returns a Single<Int> which contains the number of failed call-link revocations.
   */
  @MainThread
  fun commit(): Single<Int> {
    if (isCommitted) {
      return Single.just(0)
    }

    isCommitted = true
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    val callRowIds = stateSnapshot.selected()
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    val messageIds = stateSnapshot.selected()
=======
    val callIds = stateSnapshot.selected()
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    val callIds = stateSnapshot.selected()
=======
    val callRowIds = stateSnapshot.selected()
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    val messageIds = stateSnapshot.selected()
=======
    val callIds = stateSnapshot.selected()
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
      .filterIsInstance<CallLogRow.Id.Call>()
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
      .map { it.children }
      .flatten()
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
      .map { it.messageId }
=======
      .map { it.callId }
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      .map { it.callId }
=======
      .map { it.children }
      .flatten()
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
      .map { it.messageId }
=======
      .map { it.callId }
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
      .toSet()

<<<<<<< HEAD
    val callLinkIds = stateSnapshot.selected()
      .filterIsInstance<CallLogRow.Id.CallLink>()
      .map { it.roomId }
      .toSet()

    return when {
      stateSnapshot is CallLogSelectionState.All && filter == CallLogFilter.ALL -> {
        repository.deleteAllCallLogsOnOrBeforeNow()
      }
      stateSnapshot is CallLogSelectionState.Excludes || stateSnapshot is CallLogSelectionState.All -> {
        repository.deleteAllCallLogsExcept(callRowIds, filter == CallLogFilter.MISSED).andThen(
          repository.deleteAllCallLinksExcept(callRowIds, callLinkIds)
        )
      }
      stateSnapshot is CallLogSelectionState.Includes -> {
        repository.deleteSelectedCallLogs(callRowIds).andThen(
          repository.deleteSelectedCallLinks(callRowIds, callLinkIds)
        )
      }
      else -> error("Unhandled state $stateSnapshot $filter")
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    if (stateSnapshot.isExclusionary()) {
      repository.deleteAllCallLogsExcept(callIds).subscribe()
    } else {
<<<<<<< HEAD
      repository.deleteSelectedCallLogs(messageIds).subscribe()
=======
    if (stateSnapshot.isExclusionary()) {
      repository.deleteAllCallLogsExcept(callRowIds).subscribe()
    } else {
<<<<<<< HEAD
      repository.deleteSelectedCallLogs(callIds).subscribe()
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      repository.deleteSelectedCallLogs(callIds).subscribe()
=======
      repository.deleteSelectedCallLogs(callRowIds).subscribe()
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
      repository.deleteSelectedCallLogs(messageIds).subscribe()
=======
      repository.deleteSelectedCallLogs(callIds).subscribe()
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    }
  }
}
