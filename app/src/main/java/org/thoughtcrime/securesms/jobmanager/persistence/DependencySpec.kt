<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
package org.thoughtcrime.securesms.jobmanager.persistence

import java.util.Locale

data class DependencySpec(
  val jobId: String,
  val dependsOnJobId: String,
  val isMemoryOnly: Boolean
) {
  override fun toString(): String {
    return String.format(Locale.US, "jobSpecId: JOB::%s | dependsOnJobSpecId: JOB::%s | memoryOnly: %b", jobId, dependsOnJobId, isMemoryOnly)
  }
}
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
package org.thoughtcrime.securesms.jobmanager.persistence

import java.util.Locale

class DependencySpec(
  val jobId: String,
  val dependsOnJobId: String,
  val isMemoryOnly: Boolean
) {
  override fun toString(): String {
    return String.format(Locale.US, "jobSpecId: JOB::%s | dependsOnJobSpecId: JOB::%s | memoryOnly: %b", jobId, dependsOnJobId, isMemoryOnly)
  }
}
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
package org.thoughtcrime.securesms.jobmanager.persistence

import java.util.Locale

class DependencySpec(
  val jobId: String,
  val dependsOnJobId: String,
  val isMemoryOnly: Boolean
) {
  override fun toString(): String {
    return String.format(Locale.US, "jobSpecId: JOB::%s | dependsOnJobSpecId: JOB::%s | memoryOnly: %b", jobId, dependsOnJobId, isMemoryOnly)
  }
}
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
package org.thoughtcrime.securesms.jobmanager.persistence

import java.util.Locale

class DependencySpec(
  val jobId: String,
  val dependsOnJobId: String,
  val isMemoryOnly: Boolean
) {
  override fun toString(): String {
    return String.format(Locale.US, "jobSpecId: JOB::%s | dependsOnJobSpecId: JOB::%s | memoryOnly: %b", jobId, dependsOnJobId, isMemoryOnly)
  }
}
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
