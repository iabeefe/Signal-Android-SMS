@file:JvmName("RxExtensions")

package org.signal.core.util.concurrent

import android.annotation.SuppressLint
<<<<<<< HEAD
<<<<<<< HEAD
import androidx.lifecycle.LifecycleOwner
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
=======
import androidx.lifecycle.LifecycleOwner
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
=======
import androidx.lifecycle.LifecycleOwner
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
import io.reactivex.rxjava3.core.Single
<<<<<<< HEAD
<<<<<<< HEAD
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.Subject
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
=======
import io.reactivex.rxjava3.kotlin.subscribeBy
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
import java.lang.RuntimeException
=======
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.lang.RuntimeException
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)

/**
 * Throw an [InterruptedException] if a [Single.blockingGet] call is interrupted. This can
 * happen when being called by code already within an Rx chain that is disposed.
 *
 * [Single.blockingGet] is considered harmful and should not be used.
 */
@SuppressLint("UnsafeBlockingGet")
@Throws(InterruptedException::class)
fun <T : Any> Single<T>.safeBlockingGet(): T {
  try {
    return blockingGet()
  } catch (e: RuntimeException) {
    val cause = e.cause
    if (cause is InterruptedException) {
      throw cause
    } else {
      throw e
    }
  }
}
<<<<<<< HEAD
<<<<<<< HEAD

fun <T : Any> Flowable<T>.observe(viewLifecycleOwner: LifecycleOwner, onNext: (T) -> Unit) {
  val lifecycleDisposable = LifecycleDisposable()
  lifecycleDisposable.bindTo(viewLifecycleOwner)
  lifecycleDisposable += subscribeBy(onNext = onNext)
}

fun Completable.observe(viewLifecycleOwner: LifecycleOwner, onComplete: () -> Unit) {
  val lifecycleDisposable = LifecycleDisposable()
  lifecycleDisposable.bindTo(viewLifecycleOwner)
  lifecycleDisposable += subscribeBy(onComplete = onComplete)
}

fun <S : Subject<T>, T : Any> Observable<T>.subscribeWithSubject(
  subject: S,
  disposables: CompositeDisposable
): S {
  subscribeBy(
    onNext = subject::onNext,
    onError = subject::onError,
    onComplete = subject::onComplete
  ).addTo(disposables)

  return subject
}

fun <S : Subject<T>, T : Any> Single<T>.subscribeWithSubject(
  subject: S,
  disposables: CompositeDisposable
): S {
  subscribeBy(
    onSuccess = {
      subject.onNext(it)
      subject.onComplete()
    },
    onError = subject::onError
  ).addTo(disposables)

  return subject
}
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
=======

fun <T : Any> Flowable<T>.observe(viewLifecycleOwner: LifecycleOwner, onNext: (T) -> Unit) {
  val lifecycleDisposable = LifecycleDisposable()
  lifecycleDisposable.bindTo(viewLifecycleOwner)
  lifecycleDisposable += subscribeBy(onNext = onNext)
}

fun Completable.observe(viewLifecycleOwner: LifecycleOwner, onComplete: () -> Unit) {
  val lifecycleDisposable = LifecycleDisposable()
  lifecycleDisposable.bindTo(viewLifecycleOwner)
  lifecycleDisposable += subscribeBy(onComplete = onComplete)
}
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
=======

fun <T : Any> Flowable<T>.observe(viewLifecycleOwner: LifecycleOwner, onNext: (T) -> Unit) {
  val lifecycleDisposable = LifecycleDisposable()
  lifecycleDisposable.bindTo(viewLifecycleOwner)
  lifecycleDisposable += subscribeBy(onNext = onNext)
}

fun Completable.observe(viewLifecycleOwner: LifecycleOwner, onComplete: () -> Unit) {
  val lifecycleDisposable = LifecycleDisposable()
  lifecycleDisposable.bindTo(viewLifecycleOwner)
  lifecycleDisposable += subscribeBy(onComplete = onComplete)
}
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
