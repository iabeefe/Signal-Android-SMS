<<<<<<< HEAD
/*
 * Copyright 2023 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrime.securesms.conversation.v2

import org.signal.paging.ObservablePagedData
import org.thoughtcrime.securesms.conversation.ConversationData
import org.thoughtcrime.securesms.conversation.v2.data.ConversationElementKey
import org.thoughtcrime.securesms.util.adapter.mapping.MappingModel

/**
 * Represents the content that will be displayed in the conversation
 * thread (recycler).
 */
class ConversationThreadState(
  val items: ObservablePagedData<ConversationElementKey, MappingModel<*>>,
  val meta: ConversationData
)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
=======
package org.thoughtcrime.securesms.conversation.v2

import org.signal.paging.ObservablePagedData
import org.thoughtcrime.securesms.conversation.ConversationData
import org.thoughtcrime.securesms.conversation.ConversationMessage
import org.thoughtcrime.securesms.database.model.MessageId

/**
 * Represents the content that will be displayed in the conversation
 * thread (recycler).
 */
class ConversationThreadState(
  val items: ObservablePagedData<MessageId, ConversationMessage>,
  val meta: ConversationData
)
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
