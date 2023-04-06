package org.thoughtcrime.securesms.calls.log

import org.signal.paging.PagedDataSource
<<<<<<< HEAD
import org.thoughtcrime.securesms.util.RemoteConfig
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import org.thoughtcrime.securesms.util.FeatureFlags
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)

class CallLogPagedDataSource(
  private val query: String?,
  private val filter: CallLogFilter,
  private val repository: CallRepository
) : PagedDataSource<CallLogRow.Id, CallLogRow> {

  private val hasFilter = filter == CallLogFilter.MISSED
<<<<<<< HEAD
  private val hasCallLinkRow = RemoteConfig.adHocCalling && filter == CallLogFilter.ALL && query.isNullOrEmpty()
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
  private val hasCallLinkRow = FeatureFlags.adHocCalling() && filter == CallLogFilter.ALL && query.isNullOrEmpty()
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)

<<<<<<< HEAD
  private var callEventsCount = 0
  private var callLinksCount = 0
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
  var callsCount = 0
=======
  private var callsCount = 0
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)

  override fun size(): Int {
<<<<<<< HEAD
    callEventsCount = repository.getCallsCount(query, filter)
    callLinksCount = repository.getCallLinksCount(query, filter)
    return callEventsCount + callLinksCount + hasFilter.toInt() + hasCallLinkRow.toInt()
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    callsCount = repository.getCallsCount(query, filter)
    return callsCount + (if (hasFilter) 1 else 0)
=======
    callsCount = repository.getCallsCount(query, filter)
    return callsCount + hasFilter.toInt() + hasCallLinkRow.toInt()
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
  }

<<<<<<< HEAD
  override fun load(start: Int, length: Int, totalSize: Int, cancellationSignal: PagedDataSource.CancellationSignal): MutableList<CallLogRow> {
    val callLogRows = mutableListOf<CallLogRow>()
    if (length <= 0) {
      return callLogRows
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
  override fun load(start: Int, length: Int, cancellationSignal: PagedDataSource.CancellationSignal): MutableList<CallLogRow> {
    val calls: MutableList<CallLogRow> = repository.getCalls(query, filter, start, length).toMutableList()

    if (calls.size < length && hasFilter) {
      calls.add(CallLogRow.ClearFilter)
=======
  override fun load(start: Int, length: Int, cancellationSignal: PagedDataSource.CancellationSignal): MutableList<CallLogRow> {
    val calls = mutableListOf<CallLogRow>()
    val callLimit = length - hasCallLinkRow.toInt()

    if (start == 0 && length >= 1 && hasCallLinkRow) {
      calls.add(CallLogRow.CreateCallLink)
    }

    calls.addAll(repository.getCalls(query, filter, start, callLimit).toMutableList())

    if (calls.size < length && hasFilter) {
      calls.add(CallLogRow.ClearFilter)
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    }

    val callLinkStart = if (hasCallLinkRow) 1 else 0
    val callEventStart = callLinkStart + callLinksCount
    val clearFilterStart = callEventStart + callEventsCount

    var remaining = length
    if (start < callLinkStart) {
      callLogRows.add(CallLogRow.CreateCallLink)
      remaining -= 1
    }

    if (start < callEventStart && remaining > 0) {
      val callLinks = repository.getCallLinks(
        query,
        filter,
        start,
        remaining
      )

      callLogRows.addAll(callLinks)

      remaining -= callLinks.size
    }

    if (start < clearFilterStart && remaining > 0) {
      val callEvents = repository.getCalls(
        query,
        filter,
        start - callLinksCount,
        remaining
      )

      callLogRows.addAll(callEvents)

      remaining -= callEvents.size
    }

    if (hasFilter && start <= clearFilterStart && remaining > 0) {
      callLogRows.add(CallLogRow.ClearFilter)
    }

    repository.onCallTabPageLoaded(callLogRows)
    return callLogRows
  }

  override fun getKey(data: CallLogRow): CallLogRow.Id = data.id

  override fun load(key: CallLogRow.Id?): CallLogRow = error("Not supported")

  private fun Boolean.toInt(): Int {
    return if (this) 1 else 0
  }

  interface CallRepository {
    fun getCallsCount(query: String?, filter: CallLogFilter): Int
    fun getCalls(query: String?, filter: CallLogFilter, start: Int, length: Int): List<CallLogRow>
    fun getCallLinksCount(query: String?, filter: CallLogFilter): Int
    fun getCallLinks(query: String?, filter: CallLogFilter, start: Int, length: Int): List<CallLogRow>
    fun onCallTabPageLoaded(pageData: List<CallLogRow>)
  }
}
