package org.thoughtcrime.securesms.conversation

sealed class ConversationItemDisplayMode(val scheduleMessageMode: Boolean = false) {
  /** Normal rendering, used for normal bubbles in the conversation view */
  object Standard : ConversationItemDisplayMode()

  /** Smaller bubbles, often trimming text and shrinking images. Used for quote threads. */
  class Condensed(scheduleMessageMode: Boolean) : ConversationItemDisplayMode(scheduleMessageMode)

  /** Smaller bubbles, always singular bubbles, with a footer. Used for edit message history. */
  object EditHistory : ConversationItemDisplayMode()

  /** Smaller bubbles, no footers */
  EXTRA_CONDENSED,

  /** Smaller bubbles, no footers */
  EXTRA_CONDENSED,

  /** Smaller bubbles, no footers */
  EXTRA_CONDENSED,

  /** Less length restrictions. Used to show more info in message details. */
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
  object Detailed : ConversationItemDisplayMode()

  fun displayWallpaper(): Boolean {
    return this == Standard || this == Detailed
  }
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  DETAILED
=======
  DETAILED;

  fun displayWallpaper(): Boolean {
    return this == STANDARD || this == DETAILED
  }
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  DETAILED
=======
  DETAILED;

  fun displayWallpaper(): Boolean {
    return this == STANDARD || this == DETAILED
  }
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  DETAILED
=======
  DETAILED;

  fun displayWallpaper(): Boolean {
    return this == STANDARD || this == DETAILED
  }
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
}
