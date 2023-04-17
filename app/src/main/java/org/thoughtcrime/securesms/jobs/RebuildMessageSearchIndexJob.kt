package org.thoughtcrime.securesms.jobs

import org.signal.core.util.logging.Log
import org.thoughtcrime.securesms.database.SignalDatabase
import org.thoughtcrime.securesms.dependencies.AppDependencies
import org.thoughtcrime.securesms.jobmanager.Job
import org.thoughtcrime.securesms.jobmanager.impl.DataRestoreConstraint
import org.thoughtcrime.securesms.transport.RetryLaterException
import java.lang.Exception
<<<<<<< HEAD
import kotlin.time.Duration.Companion.seconds
||||||| parent of 1472ab38ad (Enable SMS sending also for unregistered numbers.)
=======
import java.lang.IllegalStateException
import kotlin.time.Duration.Companion.seconds
>>>>>>> 1472ab38ad (Enable SMS sending also for unregistered numbers.)

class RebuildMessageSearchIndexJob private constructor(params: Parameters) : BaseJob(params) {

  companion object {
    private val TAG = Log.tag(RebuildMessageSearchIndexJob::class.java)

    const val KEY = "RebuildMessageSearchIndexJob"

    fun enqueue() {
      AppDependencies.jobManager.add(RebuildMessageSearchIndexJob())
    }
  }

  private constructor() : this(
    Parameters.Builder()
      .setQueue("RebuildMessageSearchIndex")
<<<<<<< HEAD
      .addConstraint(DataRestoreConstraint.KEY)
      .setMaxAttempts(3)
||||||| parent of 1472ab38ad (Enable SMS sending also for unregistered numbers.)
=======
      .setMaxAttempts(3)
>>>>>>> 1472ab38ad (Enable SMS sending also for unregistered numbers.)
      .build()
  )

  override fun serialize(): ByteArray? = null

  override fun getFactoryKey(): String = KEY

  override fun onFailure() = Unit

  override fun onRun() {
    val success = SignalDatabase.messageSearch.rebuildIndex()

    if (!success) {
      Log.w(TAG, "Failed to rebuild search index. Resetting tables. That will enqueue another copy of this job as a side-effect.")
      SignalDatabase.messageSearch.fullyResetTables()
    }
  }

<<<<<<< HEAD
  override fun getNextRunAttemptBackoff(pastAttemptCount: Int, exception: Exception): Long {
    return 10.seconds.inWholeMilliseconds
  }

  override fun onShouldRetry(e: Exception): Boolean = e is RetryLaterException
||||||| parent of 1472ab38ad (Enable SMS sending also for unregistered numbers.)
  override fun onShouldRetry(e: Exception): Boolean = false
=======
  override fun getNextRunAttemptBackoff(pastAttemptCount: Int, exception: Exception): Long {
    return 10.seconds.inWholeMilliseconds
  }

  override fun onShouldRetry(e: Exception): Boolean = e is IllegalStateException
>>>>>>> 1472ab38ad (Enable SMS sending also for unregistered numbers.)

  class Factory : Job.Factory<RebuildMessageSearchIndexJob> {
    override fun create(parameters: Parameters, serializedData: ByteArray?): RebuildMessageSearchIndexJob {
      return RebuildMessageSearchIndexJob(parameters)
    }
  }
}
