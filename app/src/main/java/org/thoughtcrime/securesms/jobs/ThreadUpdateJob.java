package org.thoughtcrime.securesms.jobs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.signal.core.util.ThreadUtil;
import org.thoughtcrime.securesms.database.SignalDatabase;
import org.thoughtcrime.securesms.dependencies.AppDependencies;
import org.thoughtcrime.securesms.jobmanager.JsonJobData;
import org.thoughtcrime.securesms.jobmanager.Job;

/**
 * A job that effectively debounces thread updates through a combination of having a max instance count
 * and sleeping at the end of the job to make sure it takes a minimum amount of time.
 */
public final class ThreadUpdateJob extends BaseJob {

  public static final String KEY = "ThreadUpdateJob";

  private static final String KEY_THREAD_ID = "thread_id";

<<<<<<< HEAD
<<<<<<< HEAD
  private static final long DEBOUNCE_INTERVAL = 500;
  private static final long DEBOUNCE_INTERVAL_WITH_BACKLOG = 3000;

||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
  private static final long DEBOUNCE_INTERVAL = 3000;

>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
  private static final long DEBOUNCE_INTERVAL = 3000;

>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  private final long threadId;

  private ThreadUpdateJob(long threadId) {
    this(new Parameters.Builder()
                       .setQueue("ThreadUpdateJob_" + threadId)
                       .setMaxInstancesForQueue(2)
                       .build(),
         threadId);
  }

  private ThreadUpdateJob(@NonNull Parameters  parameters, long threadId) {
    super(parameters);
    this.threadId = threadId;
  }

  public static void enqueue(long threadId) {
    SignalDatabase.runPostSuccessfulTransaction(KEY + threadId, () -> {
      AppDependencies.getJobManager().add(new ThreadUpdateJob(threadId));
    });
  }

  @Override
  public @Nullable byte[] serialize() {
    return new JsonJobData.Builder().putLong(KEY_THREAD_ID, threadId).serialize();
  }

  @Override
  public @NonNull String getFactoryKey() {
    return KEY;
  }

  @Override
  protected void onRun() throws Exception {
<<<<<<< HEAD
    SignalDatabase.threads().update(threadId, true, true);
    if (!AppDependencies.getIncomingMessageObserver().getDecryptionDrained()) {
      ThreadUtil.sleep(DEBOUNCE_INTERVAL_WITH_BACKLOG);
    }
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    SignalDatabase.threads().update(threadId, true);
<<<<<<< HEAD
    ThreadUtil.sleep(1000);
=======
    SignalDatabase.threads().update(threadId, true);
    ThreadUtil.sleep(DEBOUNCE_INTERVAL);
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    ThreadUtil.sleep(1000);
=======
    ThreadUtil.sleep(DEBOUNCE_INTERVAL);
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  }

  @Override
  protected boolean onShouldRetry(@NonNull Exception e) {
    return false;
  }

  @Override
  public void onFailure() {
  }

  public static final class Factory implements Job.Factory<ThreadUpdateJob> {
    @Override
    public @NonNull ThreadUpdateJob create(@NonNull Parameters parameters, @Nullable byte[] serializedData) {
      JsonJobData data = JsonJobData.deserialize(serializedData);
      return new ThreadUpdateJob(parameters, data.getLong(KEY_THREAD_ID));
    }
  }
}
