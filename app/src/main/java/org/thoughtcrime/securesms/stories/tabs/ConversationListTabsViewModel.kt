package org.thoughtcrime.securesms.stories.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import org.thoughtcrime.securesms.stories.Stories
import org.thoughtcrime.securesms.util.rx.RxStore

class ConversationListTabsViewModel(repository: ConversationListTabRepository) : ViewModel() {
  private val store = RxStore(ConversationListTabsState())

  val stateSnapshot: ConversationListTabsState
    get() = store.state

  val state: Flowable<ConversationListTabsState> = store.stateFlowable.distinctUntilChanged().observeOn(AndroidSchedulers.mainThread())
  val disposables = CompositeDisposable()

  private val internalTabClickEvents: Subject<ConversationListTab> = PublishSubject.create()
  val tabClickEvents: Observable<ConversationListTab> = internalTabClickEvents.filter { Stories.isFeatureEnabled() }

  init {
<<<<<<< HEAD
<<<<<<< HEAD
    disposables += performStoreUpdate(repository.getNumberOfUnreadMessages()) { unreadChats, state ->
      state.copy(unreadMessagesCount = unreadChats)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    disposables += repository.getNumberOfUnreadMessages().subscribe { unreadChats ->
      store.update { it.copy(unreadMessagesCount = unreadChats) }
=======
    disposables += store.update(repository.getNumberOfUnreadMessages()) { unreadChats, state ->
      state.copy(unreadMessagesCount = unreadChats)
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    disposables += repository.getNumberOfUnreadMessages().subscribe { unreadChats ->
      store.update { it.copy(unreadMessagesCount = unreadChats) }
=======
    disposables += store.update(repository.getNumberOfUnreadMessages()) { unreadChats, state ->
      state.copy(unreadMessagesCount = unreadChats)
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    }

<<<<<<< HEAD
<<<<<<< HEAD
    disposables += performStoreUpdate(repository.getNumberOfUnseenCalls()) { unseenCalls, state ->
      state.copy(unreadCallsCount = unseenCalls)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    disposables += repository.getNumberOfUnseenCalls().subscribe { unseenCalls ->
      store.update { it.copy(unreadCallsCount = unseenCalls) }
=======
    disposables += store.update(repository.getNumberOfUnseenCalls()) { unseenCalls, state ->
      state.copy(unreadCallsCount = unseenCalls)
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    disposables += repository.getNumberOfUnseenCalls().subscribe { unseenCalls ->
      store.update { it.copy(unreadCallsCount = unseenCalls) }
=======
    disposables += store.update(repository.getNumberOfUnseenCalls()) { unseenCalls, state ->
      state.copy(unreadCallsCount = unseenCalls)
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    }

<<<<<<< HEAD
<<<<<<< HEAD
    disposables += performStoreUpdate(repository.getNumberOfUnseenStories()) { unseenStories, state ->
      state.copy(unreadStoriesCount = unseenStories)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    disposables += repository.getNumberOfUnseenStories().subscribe { unseenStories ->
      store.update { it.copy(unreadStoriesCount = unseenStories) }
=======
    disposables += store.update(repository.getNumberOfUnseenStories()) { unseenStories, state ->
      state.copy(unreadStoriesCount = unseenStories)
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    disposables += repository.getNumberOfUnseenStories().subscribe { unseenStories ->
      store.update { it.copy(unreadStoriesCount = unseenStories) }
=======
    disposables += store.update(repository.getNumberOfUnseenStories()) { unseenStories, state ->
      state.copy(unreadStoriesCount = unseenStories)
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    }

<<<<<<< HEAD
<<<<<<< HEAD
    disposables += performStoreUpdate(repository.getHasFailedOutgoingStories()) { hasFailedStories, state ->
      state.copy(hasFailedStory = hasFailedStories)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    disposables += repository.getHasFailedOutgoingStories().subscribe { hasFailedStories ->
      store.update { it.copy(hasFailedStory = hasFailedStories) }
=======
    disposables += store.update(repository.getHasFailedOutgoingStories()) { hasFailedStories, state ->
      state.copy(hasFailedStory = hasFailedStories)
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    disposables += repository.getHasFailedOutgoingStories().subscribe { hasFailedStories ->
      store.update { it.copy(hasFailedStory = hasFailedStories) }
=======
    disposables += store.update(repository.getHasFailedOutgoingStories()) { hasFailedStories, state ->
      state.copy(hasFailedStory = hasFailedStories)
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    }
  }

  override fun onCleared() {
    disposables.clear()
  }

  fun onChatsSelected() {
    internalTabClickEvents.onNext(ConversationListTab.CHATS)
    performStoreUpdate { it.copy(tab = ConversationListTab.CHATS) }
  }

  fun onCallsSelected() {
    internalTabClickEvents.onNext(ConversationListTab.CALLS)
    performStoreUpdate { it.copy(tab = ConversationListTab.CALLS) }
  }

  fun onStoriesSelected() {
    internalTabClickEvents.onNext(ConversationListTab.STORIES)
    performStoreUpdate { it.copy(tab = ConversationListTab.STORIES) }
  }

  fun onSearchOpened() {
    performStoreUpdate { it.copy(visibilityState = it.visibilityState.copy(isSearchOpen = true)) }
  }

  fun onSearchClosed() {
    performStoreUpdate { it.copy(visibilityState = it.visibilityState.copy(isSearchOpen = false)) }
  }

  fun onMultiSelectStarted() {
    performStoreUpdate { it.copy(visibilityState = it.visibilityState.copy(isMultiSelectOpen = true)) }
  }

  fun onMultiSelectFinished() {
    performStoreUpdate { it.copy(visibilityState = it.visibilityState.copy(isMultiSelectOpen = false)) }
  }

  fun isShowingArchived(isShowingArchived: Boolean) {
    performStoreUpdate { it.copy(visibilityState = it.visibilityState.copy(isShowingArchived = isShowingArchived)) }
  }

  private fun performStoreUpdate(fn: (ConversationListTabsState) -> ConversationListTabsState) {
    store.update {
      fn(it.copy(prevTab = it.tab))
    }
  }

  private fun <T : Any> performStoreUpdate(flowable: Flowable<T>, fn: (T, ConversationListTabsState) -> ConversationListTabsState): Disposable {
    return store.update(flowable) { t, state ->
      fn(t, state.copy(prevTab = state.tab))
    }
  }

  class Factory(private val repository: ConversationListTabRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
      return modelClass.cast(ConversationListTabsViewModel(repository)) as T
    }
  }
}
