<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
package org.thoughtcrime.securesms.conversation.v2.groups

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.processors.PublishProcessor
import io.reactivex.rxjava3.schedulers.Schedulers
import org.signal.core.util.logging.Log
import org.thoughtcrime.securesms.conversation.v2.ConversationRecipientRepository
import org.thoughtcrime.securesms.dependencies.AppDependencies
import org.thoughtcrime.securesms.events.GroupCallPeekEvent
import org.thoughtcrime.securesms.util.rx.RxStore

/**
 * ViewModel which manages state associated with group calls.
 */
class ConversationGroupCallViewModel(
  recipientRepository: ConversationRecipientRepository
) : ViewModel() {

  companion object {
    private val TAG = Log.tag(ConversationGroupCallViewModel::class.java)
  }

  private val disposables = CompositeDisposable()
  private val store = RxStore(ConversationGroupCallState()).addTo(disposables)
  private val forcePeek = PublishProcessor.create<Unit>()

  val state: Flowable<ConversationGroupCallState> = store.stateFlowable.onBackpressureLatest().observeOn(AndroidSchedulers.mainThread())

  val hasOngoingGroupCallSnapshot: Boolean
    get() = store.state.ongoingCall

  init {
    recipientRepository
      .conversationRecipient
      .subscribeBy { recipient ->
        store.update { s: ConversationGroupCallState ->
          val activeV2Group = recipient.isPushV2Group && recipient.isActiveGroup
          s.copy(
            recipientId = recipient.id,
            activeV2Group = activeV2Group,
            ongoingCall = if (activeV2Group && s.recipientId == recipient.id) s.ongoingCall else false,
            hasCapacity = if (activeV2Group && s.recipientId == recipient.id) s.hasCapacity else false
          )
        }
      }
      .addTo(disposables)

    val filteredState = store.stateFlowable
      .filter { it.recipientId != null }
      .distinctUntilChanged { s -> s.activeV2Group }

    Flowable.combineLatest(forcePeek, filteredState) { _, s -> s }
      .subscribeOn(Schedulers.io())
      .onBackpressureLatest()
      .subscribeBy { s: ConversationGroupCallState ->
        if (s.recipientId != null && s.activeV2Group) {
          Log.i(TAG, "Peek call for ${s.recipientId}")
          AppDependencies.signalCallManager.peekGroupCall(s.recipientId)
        }
      }
      .addTo(disposables)
  }

  override fun onCleared() {
    disposables.clear()
  }

  fun onGroupCallPeekEvent(event: GroupCallPeekEvent) {
    store.update { s: ConversationGroupCallState ->
      if (s.recipientId != null && event.groupRecipientId == s.recipientId) {
        s.copy(ongoingCall = event.isOngoing, hasCapacity = event.callHasCapacity())
      } else {
        s
      }
    }
  }

  fun peekGroupCall() {
    forcePeek.onNext(Unit)
  }
}
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
=======
package org.thoughtcrime.securesms.conversation.v2.groups

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.processors.PublishProcessor
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.signal.core.util.logging.Log
import org.thoughtcrime.securesms.database.SignalDatabase
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies
import org.thoughtcrime.securesms.events.GroupCallPeekEvent
import org.thoughtcrime.securesms.recipients.Recipient

/**
 * ViewModel which manages state associated with group calls.
 */
class ConversationGroupCallViewModel(threadId: Long) : ViewModel() {

  companion object {
    private val TAG = Log.tag(ConversationGroupCallViewModel::class.java)
  }

  private val _isGroupActive: Subject<Boolean> = BehaviorSubject.createDefault(false)
  private val _hasOngoingGroupCall: Subject<Boolean> = BehaviorSubject.createDefault(false)
  private val _hasCapacity: Subject<Boolean> = BehaviorSubject.createDefault(false)
  private val _hasActiveGroupCall: BehaviorSubject<Boolean> = BehaviorSubject.create()
  private val _recipient: BehaviorSubject<Recipient> = BehaviorSubject.create()
  private val _groupCallPeekEventProcessor: PublishProcessor<GroupCallPeekEvent> = PublishProcessor.create()
  private val _peekRequestProcessor: PublishProcessor<Unit> = PublishProcessor.create()
  private val disposables = CompositeDisposable()

  val hasActiveGroupCall: Observable<Boolean> = _hasActiveGroupCall.observeOn(AndroidSchedulers.mainThread())
  val hasCapacity: Observable<Boolean> = _hasCapacity.observeOn(AndroidSchedulers.mainThread())

  val hasActiveGroupCallSnapshot: Boolean
    get() = _hasActiveGroupCall.value == true

  init {
    disposables += Observable
      .combineLatest(_isGroupActive, _hasActiveGroupCall) { a, b -> a && b }
      .subscribeBy(onNext = _hasActiveGroupCall::onNext)

    disposables += Single
      .fromCallable { SignalDatabase.threads.getRecipientForThreadId(threadId)!! }
      .subscribeOn(Schedulers.io())
      .filter { it.isPushV2Group }
      .flatMapObservable { Recipient.live(it.id).observable() }
      .subscribeBy(onNext = _recipient::onNext)

    disposables += _recipient
      .map { it.isActiveGroup }
      .distinctUntilChanged()
      .subscribeBy(onNext = _isGroupActive::onNext)

    disposables += _recipient
      .firstOrError()
      .subscribeBy(onSuccess = {
        peekGroupCall()
      })

    disposables += _groupCallPeekEventProcessor
      .onBackpressureLatest()
      .switchMap { event ->
        _recipient.firstElement().map { it.id }.filter { it == event.groupRecipientId }.map { event }.toFlowable()
      }
      .subscribeBy(onNext = {
        Log.i(TAG, "update UI with call event: ongoing call: " + it.isOngoing + " hasCapacity: " + it.callHasCapacity())
        _hasOngoingGroupCall.onNext(it.isOngoing)
        _hasCapacity.onNext(it.callHasCapacity())
      })

    disposables += _peekRequestProcessor
      .onBackpressureLatest()
      .switchMap {
        _recipient.firstOrError().map { it.id }.toFlowable()
      }
      .subscribeBy(onNext = { recipientId ->
        Log.i(TAG, "peek call for $recipientId")
        ApplicationDependencies.getSignalCallManager().peekGroupCall(recipientId)
      })
  }

  override fun onCleared() {
    disposables.clear()
  }

  @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
  fun onGroupCallPeekEvent(groupCallPeekEvent: GroupCallPeekEvent) {
    _groupCallPeekEventProcessor.onNext(groupCallPeekEvent)
  }

  fun peekGroupCall() {
    _peekRequestProcessor.onNext(Unit)
  }

  class Factory(private val threadId: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
      return modelClass.cast(ConversationGroupCallViewModel(threadId)) as T
    }
  }
}
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
=======
package org.thoughtcrime.securesms.conversation.v2.groups

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.processors.PublishProcessor
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.signal.core.util.logging.Log
import org.thoughtcrime.securesms.database.SignalDatabase
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies
import org.thoughtcrime.securesms.events.GroupCallPeekEvent
import org.thoughtcrime.securesms.recipients.Recipient

/**
 * ViewModel which manages state associated with group calls.
 */
class ConversationGroupCallViewModel(threadId: Long) : ViewModel() {

  companion object {
    private val TAG = Log.tag(ConversationGroupCallViewModel::class.java)
  }

  private val _isGroupActive: Subject<Boolean> = BehaviorSubject.createDefault(false)
  private val _hasOngoingGroupCall: Subject<Boolean> = BehaviorSubject.createDefault(false)
  private val _hasCapacity: Subject<Boolean> = BehaviorSubject.createDefault(false)
  private val _hasActiveGroupCall: BehaviorSubject<Boolean> = BehaviorSubject.create()
  private val _recipient: BehaviorSubject<Recipient> = BehaviorSubject.create()
  private val _groupCallPeekEventProcessor: PublishProcessor<GroupCallPeekEvent> = PublishProcessor.create()
  private val _peekRequestProcessor: PublishProcessor<Unit> = PublishProcessor.create()
  private val disposables = CompositeDisposable()

  val hasActiveGroupCall: Observable<Boolean> = _hasActiveGroupCall.observeOn(AndroidSchedulers.mainThread())
  val hasCapacity: Observable<Boolean> = _hasCapacity.observeOn(AndroidSchedulers.mainThread())

  val hasActiveGroupCallSnapshot: Boolean
    get() = _hasActiveGroupCall.value == true

  init {
    disposables += Observable
      .combineLatest(_isGroupActive, _hasActiveGroupCall) { a, b -> a && b }
      .subscribeBy(onNext = _hasActiveGroupCall::onNext)

    disposables += Single
      .fromCallable { SignalDatabase.threads.getRecipientForThreadId(threadId)!! }
      .subscribeOn(Schedulers.io())
      .filter { it.isPushV2Group }
      .flatMapObservable { Recipient.live(it.id).observable() }
      .subscribeBy(onNext = _recipient::onNext)

    disposables += _recipient
      .map { it.isActiveGroup }
      .distinctUntilChanged()
      .subscribeBy(onNext = _isGroupActive::onNext)

    disposables += _recipient
      .firstOrError()
      .subscribeBy(onSuccess = {
        peekGroupCall()
      })

    disposables += _groupCallPeekEventProcessor
      .onBackpressureLatest()
      .switchMap { event ->
        _recipient.firstElement().map { it.id }.filter { it == event.groupRecipientId }.map { event }.toFlowable()
      }
      .subscribeBy(onNext = {
        Log.i(TAG, "update UI with call event: ongoing call: " + it.isOngoing + " hasCapacity: " + it.callHasCapacity())
        _hasOngoingGroupCall.onNext(it.isOngoing)
        _hasCapacity.onNext(it.callHasCapacity())
      })

    disposables += _peekRequestProcessor
      .onBackpressureLatest()
      .switchMap {
        _recipient.firstOrError().map { it.id }.toFlowable()
      }
      .subscribeBy(onNext = { recipientId ->
        Log.i(TAG, "peek call for $recipientId")
        ApplicationDependencies.getSignalCallManager().peekGroupCall(recipientId)
      })
  }

  override fun onCleared() {
    disposables.clear()
  }

  @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
  fun onGroupCallPeekEvent(groupCallPeekEvent: GroupCallPeekEvent) {
    _groupCallPeekEventProcessor.onNext(groupCallPeekEvent)
  }

  fun peekGroupCall() {
    _peekRequestProcessor.onNext(Unit)
  }

  class Factory(private val threadId: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
      return modelClass.cast(ConversationGroupCallViewModel(threadId)) as T
    }
  }
}
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
=======
package org.thoughtcrime.securesms.conversation.v2.groups

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.processors.PublishProcessor
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.signal.core.util.logging.Log
import org.thoughtcrime.securesms.database.SignalDatabase
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies
import org.thoughtcrime.securesms.events.GroupCallPeekEvent
import org.thoughtcrime.securesms.recipients.Recipient

/**
 * ViewModel which manages state associated with group calls.
 */
class ConversationGroupCallViewModel(threadId: Long) : ViewModel() {

  companion object {
    private val TAG = Log.tag(ConversationGroupCallViewModel::class.java)
  }

  private val _isGroupActive: Subject<Boolean> = BehaviorSubject.createDefault(false)
  private val _hasOngoingGroupCall: Subject<Boolean> = BehaviorSubject.createDefault(false)
  private val _hasCapacity: Subject<Boolean> = BehaviorSubject.createDefault(false)
  private val _hasActiveGroupCall: BehaviorSubject<Boolean> = BehaviorSubject.create()
  private val _recipient: BehaviorSubject<Recipient> = BehaviorSubject.create()
  private val _groupCallPeekEventProcessor: PublishProcessor<GroupCallPeekEvent> = PublishProcessor.create()
  private val _peekRequestProcessor: PublishProcessor<Unit> = PublishProcessor.create()
  private val disposables = CompositeDisposable()

  val hasActiveGroupCall: Observable<Boolean> = _hasActiveGroupCall.observeOn(AndroidSchedulers.mainThread())
  val hasCapacity: Observable<Boolean> = _hasCapacity.observeOn(AndroidSchedulers.mainThread())

  val hasActiveGroupCallSnapshot: Boolean
    get() = _hasActiveGroupCall.value == true

  init {
    disposables += Observable
      .combineLatest(_isGroupActive, _hasActiveGroupCall) { a, b -> a && b }
      .subscribeBy(onNext = _hasActiveGroupCall::onNext)

    disposables += Single
      .fromCallable { SignalDatabase.threads.getRecipientForThreadId(threadId)!! }
      .subscribeOn(Schedulers.io())
      .filter { it.isPushV2Group }
      .flatMapObservable { Recipient.live(it.id).observable() }
      .subscribeBy(onNext = _recipient::onNext)

    disposables += _recipient
      .map { it.isActiveGroup }
      .distinctUntilChanged()
      .subscribeBy(onNext = _isGroupActive::onNext)

    disposables += _recipient
      .firstOrError()
      .subscribeBy(onSuccess = {
        peekGroupCall()
      })

    disposables += _groupCallPeekEventProcessor
      .onBackpressureLatest()
      .switchMap { event ->
        _recipient.firstElement().map { it.id }.filter { it == event.groupRecipientId }.map { event }.toFlowable()
      }
      .subscribeBy(onNext = {
        Log.i(TAG, "update UI with call event: ongoing call: " + it.isOngoing + " hasCapacity: " + it.callHasCapacity())
        _hasOngoingGroupCall.onNext(it.isOngoing)
        _hasCapacity.onNext(it.callHasCapacity())
      })

    disposables += _peekRequestProcessor
      .onBackpressureLatest()
      .switchMap {
        _recipient.firstOrError().map { it.id }.toFlowable()
      }
      .subscribeBy(onNext = { recipientId ->
        Log.i(TAG, "peek call for $recipientId")
        ApplicationDependencies.getSignalCallManager().peekGroupCall(recipientId)
      })
  }

  override fun onCleared() {
    disposables.clear()
  }

  @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
  fun onGroupCallPeekEvent(groupCallPeekEvent: GroupCallPeekEvent) {
    _groupCallPeekEventProcessor.onNext(groupCallPeekEvent)
  }

  fun peekGroupCall() {
    _peekRequestProcessor.onNext(Unit)
  }

  class Factory(private val threadId: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
      return modelClass.cast(ConversationGroupCallViewModel(threadId)) as T
    }
  }
}
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
