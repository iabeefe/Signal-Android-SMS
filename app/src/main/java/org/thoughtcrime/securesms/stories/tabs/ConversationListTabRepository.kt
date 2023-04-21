package org.thoughtcrime.securesms.stories.tabs

import io.reactivex.rxjava3.core.Flowable
import org.thoughtcrime.securesms.database.RxDatabaseObserver
import org.thoughtcrime.securesms.database.SignalDatabase
import org.thoughtcrime.securesms.recipients.Recipient

class ConversationListTabRepository {

  fun getNumberOfUnreadMessages(): Flowable<Long> {
    return RxDatabaseObserver.conversationList.map { SignalDatabase.threads.getUnreadMessageCount() }
  }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
  fun getNumberOfUnseenStories(): Flowable<Long> {
    return RxDatabaseObserver.conversationList.map {
      SignalDatabase
        .messages
        .getUnreadStoryThreadRecipientIds()
        .map { Recipient.resolved(it) }
        .filterNot { it.shouldHideStory }
        .size
        .toLong()
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  fun getNumberOfUnreadMessages(): Observable<Long> {
    return Observable.create<Long> {
      fun refresh() {
        it.onNext(SignalDatabase.threads.getUnreadMessageCount())
      }

      val listener = DatabaseObserver.Observer {
        refresh()
      }

      ApplicationDependencies.getDatabaseObserver().registerConversationListObserver(listener)
      it.setCancellable { ApplicationDependencies.getDatabaseObserver().unregisterObserver(listener) }

      refresh()
    }.subscribeOn(Schedulers.io())
  }

  fun getNumberOfUnseenStories(): Observable<Long> {
    return Observable.create<Long> { emitter ->
      fun refresh() {
        emitter.onNext(SignalDatabase.messages.getUnreadStoryThreadRecipientIds().map { Recipient.resolved(it) }.filterNot { it.shouldHideStory() }.size.toLong())
      }

      val listener = DatabaseObserver.Observer {
        refresh()
      }

      ApplicationDependencies.getDatabaseObserver().registerConversationListObserver(listener)
      emitter.setCancellable { ApplicationDependencies.getDatabaseObserver().unregisterObserver(listener) }
      refresh()
    }.subscribeOn(Schedulers.io())
  }

  fun getHasFailedOutgoingStories(): Observable<Boolean> {
    return Observable.create<Boolean> { emitter ->
      fun refresh() {
        emitter.onNext(SignalDatabase.messages.hasFailedOutgoingStory())
      }

      val listener = DatabaseObserver.Observer {
        refresh()
      }

      ApplicationDependencies.getDatabaseObserver().registerConversationListObserver(listener)
      emitter.setCancellable { ApplicationDependencies.getDatabaseObserver().unregisterObserver(listener) }
      refresh()
    }.subscribeOn(Schedulers.io())
  }

  fun getNumberOfUnseenCalls(): Observable<Long> {
    return Observable.create { emitter ->
      fun refresh() {
        emitter.onNext(SignalDatabase.messages.getUnreadMisedCallCount())
      }

      val listener = DatabaseObserver.Observer {
        refresh()
      }

      ApplicationDependencies.getDatabaseObserver().registerConversationListObserver(listener)
      emitter.setCancellable { ApplicationDependencies.getDatabaseObserver().unregisterObserver(listener) }
      refresh()
=======
  fun getNumberOfUnseenStories(): Flowable<Long> {
    return RxDatabaseObserver.conversationList.map {
      SignalDatabase
        .messages
        .getUnreadStoryThreadRecipientIds()
        .map { Recipient.resolved(it) }
        .filterNot { it.shouldHideStory() }
        .size
        .toLong()
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  fun getNumberOfUnreadMessages(): Observable<Long> {
    return Observable.create<Long> {
      fun refresh() {
        it.onNext(SignalDatabase.threads.getUnreadMessageCount())
      }

      val listener = DatabaseObserver.Observer {
        refresh()
      }

      ApplicationDependencies.getDatabaseObserver().registerConversationListObserver(listener)
      it.setCancellable { ApplicationDependencies.getDatabaseObserver().unregisterObserver(listener) }

      refresh()
    }.subscribeOn(Schedulers.io())
  }

  fun getNumberOfUnseenStories(): Observable<Long> {
    return Observable.create<Long> { emitter ->
      fun refresh() {
        emitter.onNext(SignalDatabase.messages.getUnreadStoryThreadRecipientIds().map { Recipient.resolved(it) }.filterNot { it.shouldHideStory() }.size.toLong())
      }

      val listener = DatabaseObserver.Observer {
        refresh()
      }

      ApplicationDependencies.getDatabaseObserver().registerConversationListObserver(listener)
      emitter.setCancellable { ApplicationDependencies.getDatabaseObserver().unregisterObserver(listener) }
      refresh()
    }.subscribeOn(Schedulers.io())
  }

  fun getHasFailedOutgoingStories(): Observable<Boolean> {
    return Observable.create<Boolean> { emitter ->
      fun refresh() {
        emitter.onNext(SignalDatabase.messages.hasFailedOutgoingStory())
      }

      val listener = DatabaseObserver.Observer {
        refresh()
      }

      ApplicationDependencies.getDatabaseObserver().registerConversationListObserver(listener)
      emitter.setCancellable { ApplicationDependencies.getDatabaseObserver().unregisterObserver(listener) }
      refresh()
    }.subscribeOn(Schedulers.io())
  }

  fun getNumberOfUnseenCalls(): Observable<Long> {
    return Observable.create { emitter ->
      fun refresh() {
        emitter.onNext(SignalDatabase.messages.getUnreadMisedCallCount())
      }

      val listener = DatabaseObserver.Observer {
        refresh()
      }

      ApplicationDependencies.getDatabaseObserver().registerConversationListObserver(listener)
      emitter.setCancellable { ApplicationDependencies.getDatabaseObserver().unregisterObserver(listener) }
      refresh()
=======
  fun getNumberOfUnseenStories(): Flowable<Long> {
    return RxDatabaseObserver.conversationList.map {
      SignalDatabase
        .messages
        .getUnreadStoryThreadRecipientIds()
        .map { Recipient.resolved(it) }
        .filterNot { it.shouldHideStory() }
        .size
        .toLong()
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  fun getNumberOfUnreadMessages(): Observable<Long> {
    return Observable.create<Long> {
      fun refresh() {
        it.onNext(SignalDatabase.threads.getUnreadMessageCount())
      }

      val listener = DatabaseObserver.Observer {
        refresh()
      }

      ApplicationDependencies.getDatabaseObserver().registerConversationListObserver(listener)
      it.setCancellable { ApplicationDependencies.getDatabaseObserver().unregisterObserver(listener) }

      refresh()
    }.subscribeOn(Schedulers.io())
  }

  fun getNumberOfUnseenStories(): Observable<Long> {
    return Observable.create<Long> { emitter ->
      fun refresh() {
        emitter.onNext(SignalDatabase.messages.getUnreadStoryThreadRecipientIds().map { Recipient.resolved(it) }.filterNot { it.shouldHideStory() }.size.toLong())
      }

      val listener = DatabaseObserver.Observer {
        refresh()
      }

      ApplicationDependencies.getDatabaseObserver().registerConversationListObserver(listener)
      emitter.setCancellable { ApplicationDependencies.getDatabaseObserver().unregisterObserver(listener) }
      refresh()
    }.subscribeOn(Schedulers.io())
  }

  fun getHasFailedOutgoingStories(): Observable<Boolean> {
    return Observable.create<Boolean> { emitter ->
      fun refresh() {
        emitter.onNext(SignalDatabase.messages.hasFailedOutgoingStory())
      }

      val listener = DatabaseObserver.Observer {
        refresh()
      }

      ApplicationDependencies.getDatabaseObserver().registerConversationListObserver(listener)
      emitter.setCancellable { ApplicationDependencies.getDatabaseObserver().unregisterObserver(listener) }
      refresh()
    }.subscribeOn(Schedulers.io())
  }

  fun getNumberOfUnseenCalls(): Observable<Long> {
    return Observable.create { emitter ->
      fun refresh() {
        emitter.onNext(SignalDatabase.messages.getUnreadMisedCallCount())
      }

      val listener = DatabaseObserver.Observer {
        refresh()
      }

      ApplicationDependencies.getDatabaseObserver().registerConversationListObserver(listener)
      emitter.setCancellable { ApplicationDependencies.getDatabaseObserver().unregisterObserver(listener) }
      refresh()
=======
  fun getNumberOfUnseenStories(): Flowable<Long> {
    return RxDatabaseObserver.conversationList.map {
      SignalDatabase
        .messages
        .getUnreadStoryThreadRecipientIds()
        .map { Recipient.resolved(it) }
        .filterNot { it.shouldHideStory() }
        .size
        .toLong()
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    }
  }
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD

  fun getHasFailedOutgoingStories(): Flowable<Boolean> {
    return RxDatabaseObserver.conversationList.map { SignalDatabase.messages.hasFailedOutgoingStory() }
  }

  fun getNumberOfUnseenCalls(): Flowable<Long> {
    return RxDatabaseObserver.conversationList.map { SignalDatabase.calls.getUnreadMissedCallCount() }
  }
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
=======

  fun getHasFailedOutgoingStories(): Flowable<Boolean> {
    return RxDatabaseObserver.conversationList.map { SignalDatabase.messages.hasFailedOutgoingStory() }
  }

  fun getNumberOfUnseenCalls(): Flowable<Long> {
    return RxDatabaseObserver.conversationList.map { SignalDatabase.messages.getUnreadMisedCallCount() }
  }
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
=======

  fun getHasFailedOutgoingStories(): Flowable<Boolean> {
    return RxDatabaseObserver.conversationList.map { SignalDatabase.messages.hasFailedOutgoingStory() }
  }

  fun getNumberOfUnseenCalls(): Flowable<Long> {
    return RxDatabaseObserver.conversationList.map { SignalDatabase.messages.getUnreadMisedCallCount() }
  }
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
=======

  fun getHasFailedOutgoingStories(): Flowable<Boolean> {
    return RxDatabaseObserver.conversationList.map { SignalDatabase.messages.hasFailedOutgoingStory() }
  }

  fun getNumberOfUnseenCalls(): Flowable<Long> {
    return RxDatabaseObserver.conversationList.map { SignalDatabase.messages.getUnreadMisedCallCount() }
  }
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
}
