/*
 * Copyright 2023 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrime.securesms.conversation

import android.text.SpannableString
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.core.view.MenuProvider
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
<<<<<<< HEAD
<<<<<<< HEAD
import org.signal.core.util.concurrent.LifecycleDisposable
import org.signal.core.util.logging.Log
||||||| parent of e5a36ea5ee (Bumped to upstream version 6.18.1.0-JW.)
=======
import org.signal.core.util.concurrent.LifecycleDisposable
>>>>>>> e5a36ea5ee (Bumped to upstream version 6.18.1.0-JW.)
||||||| parent of e5a36ea5ee (Bumped to upstream version 6.18.1.0-JW.)
=======
import org.signal.core.util.concurrent.LifecycleDisposable
>>>>>>> e5a36ea5ee (Bumped to upstream version 6.18.1.0-JW.)
import org.thoughtcrime.securesms.R
<<<<<<< HEAD
<<<<<<< HEAD
import org.thoughtcrime.securesms.messagerequests.MessageRequestState
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
import org.thoughtcrime.securesms.conversation.ConversationGroupViewModel.GroupActiveState
import org.thoughtcrime.securesms.conversation.ui.groupcall.GroupCallViewModel
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
import org.thoughtcrime.securesms.conversation.ConversationGroupViewModel.GroupActiveState
import org.thoughtcrime.securesms.conversation.ui.groupcall.GroupCallViewModel
=======
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
import org.thoughtcrime.securesms.database.ThreadTable
import org.thoughtcrime.securesms.keyvalue.SignalStore
<<<<<<< HEAD
import org.thoughtcrime.securesms.recipients.LiveRecipient
=======
import org.thoughtcrime.securesms.database.ThreadTable
import org.thoughtcrime.securesms.keyvalue.SignalStore
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
import org.thoughtcrime.securesms.recipients.LiveRecipient
=======
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
import org.thoughtcrime.securesms.recipients.Recipient

/**
 * Delegate object for managing the conversation options menu
 */
internal object ConversationOptionsMenu {

  private val TAG = Log.tag(ConversationOptionsMenu::class.java)

  /**
   * MenuProvider implementation for the conversation options menu.
   */
  class Provider(
<<<<<<< HEAD
<<<<<<< HEAD
    private val callback: Callback,
    private val lifecycleDisposable: LifecycleDisposable,
    var afterFirstRenderMode: Boolean = false
  ) : MenuProvider {

    private var createdPreRenderMenu = false
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    private val dependencies: Dependencies,
    private val optionsMenuProviderCallback: Callback,
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    private val dependencies: Dependencies,
    private val optionsMenuProviderCallback: Callback,
=======
    private val callback: Callback,
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    private val lifecycleDisposable: LifecycleDisposable
<<<<<<< HEAD
  ) : MenuProvider, Dependencies by dependencies {
=======
    private val callback: Callback,
    private val lifecycleDisposable: LifecycleDisposable
  ) : MenuProvider {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  ) : MenuProvider, Dependencies by dependencies {
=======
  ) : MenuProvider {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
<<<<<<< HEAD
      if (createdPreRenderMenu && !afterFirstRenderMode) {
        return
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      menu.clear()

      val (
        recipient,
        isPushAvailable,
        canShowAsBubble,
        isActiveGroup,
        isActiveV2Group,
        isInActiveGroup,
        hasActiveGroupCall,
        distributionType,
        threadId,
        isInMessageRequest,
        isInBubble
      ) = callback.getSnapshot()

      if (isInMessageRequest && (recipient != null) && !recipient.isBlocked) {
        if (isActiveGroup) {
          menuInflater.inflate(R.menu.conversation_message_requests_group, menu)
        }
=======
      menu.clear()

      val (
        recipient,
        isPushAvailable,
        canShowAsBubble,
        isActiveGroup,
        isActiveV2Group,
        isInActiveGroup,
        hasActiveGroupCall,
        distributionType,
        threadId,
        isInMessageRequest,
        isInBubble
      ) = callback.getSnapshot()

      if (isInMessageRequest && (recipient != null) && !recipient.isBlocked) {
        if (isActiveGroup) {
          menuInflater.inflate(R.menu.conversation_message_requests_group, menu)
        }
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      }

<<<<<<< HEAD
<<<<<<< HEAD
      menu.clear()

      val (
        recipient,
        isPushAvailable,
        canShowAsBubble,
        isActiveGroup,
        isActiveV2Group,
        isInActiveGroup,
        hasActiveGroupCall,
        distributionType,
        threadId,
        messageRequestState,
        isInBubble
      ) = callback.getSnapshot()

      if (recipient == null) {
        Log.w(TAG, "Recipient is null, no menu")
        return
      }

      if (!messageRequestState.isAccepted) {
        menuInflater.inflate(R.menu.conversation_message_request, menu)

        if (messageRequestState.isBlocked) {
          hideMenuItem(menu, R.id.menu_block)
          hideMenuItem(menu, R.id.menu_accept)
        } else {
          hideMenuItem(menu, R.id.menu_unblock)
        }

        if (messageRequestState.reportedAsSpam) {
          hideMenuItem(menu, R.id.menu_report_spam)
        }

        return
      }

      if (!afterFirstRenderMode) {
        createdPreRenderMenu = true
        if (recipient.isSelf) {
          return
        }

        menuInflater.inflate(R.menu.conversation_first_render, menu)

        if (recipient.isGroup) {
          hideMenuItem(menu, R.id.menu_call_secure)
          if (!isActiveV2Group) {
            hideMenuItem(menu, R.id.menu_video_secure)
          }
        } else if (!isPushAvailable) {
          hideMenuItem(menu, R.id.menu_call_secure)
          hideMenuItem(menu, R.id.menu_video_secure)
        }

        return
      }

      if (isPushAvailable) {
        if (recipient.expiresInSeconds > 0) {
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      if (viewModel.isPushAvailable) {
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      if (viewModel.isPushAvailable) {
=======
      if (isPushAvailable) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        if (recipient!!.expiresInSeconds > 0) {
=======
      if (isPushAvailable) {
        if (recipient!!.expiresInSeconds > 0) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
          if (!isInActiveGroup) {
            menuInflater.inflate(R.menu.conversation_expiring_on, menu)
          }
          callback.showExpiring(recipient)
        } else {
          if (!isInActiveGroup) {
            menuInflater.inflate(R.menu.conversation_expiring_off, menu)
          }
          callback.clearExpiring()
        }
      }

<<<<<<< HEAD
<<<<<<< HEAD
      if (!recipient.isGroup) {
        if (isPushAvailable) {
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      if (isSingleConversation()) {
        if (viewModel.isPushAvailable) {
=======
      if (recipient?.isGroup == false) {
        if (isPushAvailable) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      if (isSingleConversation()) {
        if (viewModel.isPushAvailable) {
=======
      if (recipient?.isGroup == false) {
        if (isPushAvailable) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
          menuInflater.inflate(R.menu.conversation_callable_secure, menu)
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
||||||| parent of b6f2d1016b (Bump upstream version to 6.15.3.0-JW)
        } else if (!recipient!!.isReleaseNotes && SignalStore.misc().smsExportPhase.allowSmsFeatures()) {
||||||| parent of b6f2d1016b (Bump upstream version to 6.15.3.0-JW)
        } else if (!recipient!!.isReleaseNotes && SignalStore.misc().smsExportPhase.allowSmsFeatures()) {
=======
        //} else if (!recipient!!.isReleaseNotes && SignalStore.misc().smsExportPhase.allowSmsFeatures()) {
        } else if (!recipient!!.isReleaseNotes) {
>>>>>>> b6f2d1016b (Bump upstream version to 6.15.3.0-JW)
||||||| parent of b6f2d1016b (Bump upstream version to 6.15.3.0-JW)
        } else if (!recipient!!.isReleaseNotes && SignalStore.misc().smsExportPhase.allowSmsFeatures()) {
=======
        //} else if (!recipient!!.isReleaseNotes && SignalStore.misc().smsExportPhase.allowSmsFeatures()) {
        } else if (!recipient!!.isReleaseNotes) {
>>>>>>> b6f2d1016b (Bump upstream version to 6.15.3.0-JW)
          menuInflater.inflate(R.menu.conversation_callable_insecure, menu)
=======
        //} else if (!recipient!!.isReleaseNotes && SignalStore.misc().smsExportPhase.allowSmsFeatures()) {
        } else if (!recipient!!.isReleaseNotes) {
          menuInflater.inflate(R.menu.conversation_callable_insecure, menu)
>>>>>>> b6f2d1016b (Bump upstream version to 6.15.3.0-JW)
        }
<<<<<<< HEAD
<<<<<<< HEAD
      } else if (recipient.isGroup) {
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      } else if (isGroupConversation()) {
=======
      } else if (recipient?.isGroup == true) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      } else if (isGroupConversation()) {
=======
      } else if (recipient?.isGroup == true) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        if (isActiveV2Group) {
          menuInflater.inflate(R.menu.conversation_callable_groupv2, menu)
          if (hasActiveGroupCall) {
            hideMenuItem(menu, R.id.menu_video_secure)
          }
<<<<<<< HEAD
<<<<<<< HEAD
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
          showGroupCallingTooltip()
=======
          callback.showGroupCallingTooltip()
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
          showGroupCallingTooltip()
=======
          callback.showGroupCallingTooltip()
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        }
        menuInflater.inflate(R.menu.conversation_group_options, menu)
<<<<<<< HEAD
<<<<<<< HEAD
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        if (!isPushGroupConversation()) {
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        if (!isPushGroupConversation()) {
=======
        if (!recipient.isPushGroup) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
          menuInflater.inflate(R.menu.conversation_mms_group_options, menu)
          if (distributionType == ThreadTable.DistributionTypes.BROADCAST) {
            menu.findItem(R.id.menu_distribution_broadcast).isChecked = true
          } else {
            menu.findItem(R.id.menu_distribution_conversation).isChecked = true
          }
        }
=======
        if (!recipient.isPushGroup) {
          menuInflater.inflate(R.menu.conversation_mms_group_options, menu)
          if (distributionType == ThreadTable.DistributionTypes.BROADCAST) {
            menu.findItem(R.id.menu_distribution_broadcast).isChecked = true
          } else {
            menu.findItem(R.id.menu_distribution_conversation).isChecked = true
          }
        }
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        menuInflater.inflate(R.menu.conversation_active_group_options, menu)
      }

      menuInflater.inflate(R.menu.conversation, menu)

<<<<<<< HEAD
<<<<<<< HEAD
      if (!recipient.isGroup && !isPushAvailable && !recipient.isReleaseNotes) {
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      if (isInMessageRequest() && !recipient!!.isBlocked) {
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      if (isInMessageRequest() && !recipient!!.isBlocked) {
=======
      if (isInMessageRequest && !recipient!!.isBlocked) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        hideMenuItem(menu, R.id.menu_conversation_settings)
      }

<<<<<<< HEAD
      if (isSingleConversation() && !viewModel.isPushAvailable && !recipient!!.isReleaseNotes) {
=======
      if (isInMessageRequest && !recipient!!.isBlocked) {
        hideMenuItem(menu, R.id.menu_conversation_settings)
      }

      if (recipient?.isGroup == false && !isPushAvailable && !recipient.isReleaseNotes) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      if (isSingleConversation() && !viewModel.isPushAvailable && !recipient!!.isReleaseNotes) {
=======
      if (recipient?.isGroup == false && !isPushAvailable && !recipient.isReleaseNotes) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        menuInflater.inflate(R.menu.conversation_insecure, menu)
      }

<<<<<<< HEAD
<<<<<<< HEAD
      if (recipient.isMuted) menuInflater.inflate(R.menu.conversation_muted, menu) else menuInflater.inflate(R.menu.conversation_unmuted, menu)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      if (recipient != null && recipient.isMuted) menuInflater.inflate(R.menu.conversation_muted, menu) else menuInflater.inflate(R.menu.conversation_unmuted, menu)
=======
      if (recipient?.isMuted == true) menuInflater.inflate(R.menu.conversation_muted, menu) else menuInflater.inflate(R.menu.conversation_unmuted, menu)
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      if (recipient != null && recipient.isMuted) menuInflater.inflate(R.menu.conversation_muted, menu) else menuInflater.inflate(R.menu.conversation_unmuted, menu)
=======
      if (recipient?.isMuted == true) menuInflater.inflate(R.menu.conversation_muted, menu) else menuInflater.inflate(R.menu.conversation_unmuted, menu)
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)

<<<<<<< HEAD
<<<<<<< HEAD
      if (!recipient.isGroup && recipient.contactUri == null && !recipient.isReleaseNotes && !recipient.isSelf && recipient.hasE164 && recipient.shouldShowE164) {
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      if (isSingleConversation() && getRecipient()!!.contactUri == null && !recipient!!.isReleaseNotes && !recipient.isSelf && recipient.hasE164()) {
=======
      if (recipient?.isGroup == false && recipient.contactUri == null && !recipient.isReleaseNotes && !recipient.isSelf && recipient.hasE164()) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      if (isSingleConversation() && getRecipient()!!.contactUri == null && !recipient!!.isReleaseNotes && !recipient.isSelf && recipient.hasE164()) {
=======
      if (recipient?.isGroup == false && recipient.contactUri == null && !recipient.isReleaseNotes && !recipient.isSelf && recipient.hasE164()) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        menuInflater.inflate(R.menu.conversation_add_to_contacts, menu)
      }

<<<<<<< HEAD
      if (recipient.isSelf) {
        if (isPushAvailable) {
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      if (recipient != null && recipient.isSelf) {
<<<<<<< HEAD
        if (viewModel.isPushAvailable) {
=======
      if (recipient != null && recipient.isSelf) {
        if (isPushAvailable) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        if (viewModel.isPushAvailable) {
=======
        if (isPushAvailable) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
          hideMenuItem(menu, R.id.menu_call_secure)
          hideMenuItem(menu, R.id.menu_video_secure)
        }
        hideMenuItem(menu, R.id.menu_mute_notifications)
      }

<<<<<<< HEAD
<<<<<<< HEAD
      if (recipient.isBlocked) {
        if (isPushAvailable) {
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      if (recipient != null && recipient.isBlocked) {
        if (viewModel.isPushAvailable) {
=======
      if (recipient?.isBlocked == true) {
        if (isPushAvailable) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      if (recipient != null && recipient.isBlocked) {
        if (viewModel.isPushAvailable) {
=======
      if (recipient?.isBlocked == true) {
        if (isPushAvailable) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
          hideMenuItem(menu, R.id.menu_call_secure)
          hideMenuItem(menu, R.id.menu_video_secure)
          hideMenuItem(menu, R.id.menu_expiring_messages)
          hideMenuItem(menu, R.id.menu_expiring_messages_off)
        }
        hideMenuItem(menu, R.id.menu_mute_notifications)
      }

<<<<<<< HEAD
<<<<<<< HEAD
      if (recipient.isReleaseNotes) {
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      if (recipient != null && recipient.isReleaseNotes) {
=======
      if (recipient?.isReleaseNotes == true) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      if (recipient != null && recipient.isReleaseNotes) {
=======
      if (recipient?.isReleaseNotes == true) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        hideMenuItem(menu, R.id.menu_add_shortcut)
      }

      hideMenuItem(menu, R.id.menu_group_recipients)

      if (isActiveV2Group) {
        hideMenuItem(menu, R.id.menu_mute_notifications)
        hideMenuItem(menu, R.id.menu_conversation_settings)
<<<<<<< HEAD
<<<<<<< HEAD
      } else if (recipient.isGroup) {
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      } else if (isGroupConversation()) {
=======
      } else if (recipient?.isGroup == true) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      } else if (isGroupConversation()) {
=======
      } else if (recipient?.isGroup == true) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        hideMenuItem(menu, R.id.menu_conversation_settings)
      }

      hideMenuItem(menu, R.id.menu_create_bubble)
      lifecycleDisposable += canShowAsBubble.subscribeBy(onNext = { yes: Boolean ->
        val item = menu.findItem(R.id.menu_create_bubble)
        if (item != null) {
          item.isVisible = yes && !isInBubble
        }
      })

      menu.findItem(R.id.menu_format_text_submenu).subMenu?.clearHeader()
      menu.findItem(R.id.edittext_bold).applyTitleSpan(MessageStyler.boldStyle())
      menu.findItem(R.id.edittext_italic).applyTitleSpan(MessageStyler.italicStyle())
      menu.findItem(R.id.edittext_strikethrough).applyTitleSpan(MessageStyler.strikethroughStyle())
      menu.findItem(R.id.edittext_monospace).applyTitleSpan(MessageStyler.monoStyle())

<<<<<<< HEAD
<<<<<<< HEAD
      callback.onOptionsMenuCreated(menu)
    }

    override fun onPrepareMenu(menu: Menu) {
      super.onPrepareMenu(menu)
      val formatText = menu.findItem(R.id.menu_format_text_submenu)
      if (formatText != null) {
        formatText.isVisible = callback.isTextHighlighted()
      }
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      optionsMenuProviderCallback.onOptionsMenuCreated(menu)
=======
      callback.onOptionsMenuCreated(menu)
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      optionsMenuProviderCallback.onOptionsMenuCreated(menu)
=======
      callback.onOptionsMenuCreated(menu)
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
      when (menuItem.itemId) {
<<<<<<< HEAD
<<<<<<< HEAD
        R.id.menu_call_secure -> callback.handleDial()
        R.id.menu_video_secure -> callback.handleVideo()
        R.id.menu_view_media -> callback.handleViewMedia()
        R.id.menu_add_shortcut -> callback.handleAddShortcut()
        R.id.menu_search -> callback.handleSearch()
        R.id.menu_add_to_contacts -> callback.handleAddToContacts()
        R.id.menu_group_recipients -> callback.handleDisplayGroupRecipients()
        R.id.menu_group_settings -> callback.handleManageGroup()
        R.id.menu_leave -> callback.handleLeavePushGroup()
        R.id.menu_invite -> callback.handleInviteLink()
        R.id.menu_mute_notifications -> callback.handleMuteNotifications()
        R.id.menu_unmute_notifications -> callback.handleUnmuteNotifications()
        R.id.menu_conversation_settings -> callback.handleConversationSettings()
        R.id.menu_expiring_messages_off, R.id.menu_expiring_messages -> callback.handleSelectMessageExpiration()
        R.id.menu_create_bubble -> callback.handleCreateBubble()
        R.id.home -> callback.handleGoHome()
        R.id.menu_block -> callback.handleBlock()
        R.id.menu_unblock -> callback.handleUnblock()
        R.id.menu_report_spam -> callback.handleReportSpam()
        R.id.menu_accept -> callback.handleMessageRequestAccept()
        R.id.menu_delete_chat -> callback.handleDeleteConversation()
        R.id.edittext_bold,
        R.id.edittext_italic,
        R.id.edittext_strikethrough,
        R.id.edittext_monospace,
        R.id.edittext_spoiler,
        R.id.edittext_clear_formatting -> callback.handleFormatText(menuItem.itemId)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        R.id.menu_call_secure -> optionsMenuProviderCallback.handleDial(getRecipient(), true)
        R.id.menu_video_secure -> optionsMenuProviderCallback.handleVideo(getRecipient())
        R.id.menu_call_insecure -> optionsMenuProviderCallback.handleDial(getRecipient(), false)
        R.id.menu_view_media -> optionsMenuProviderCallback.handleViewMedia()
        R.id.menu_add_shortcut -> optionsMenuProviderCallback.handleAddShortcut()
        R.id.menu_search -> optionsMenuProviderCallback.handleSearch()
        R.id.menu_add_to_contacts -> optionsMenuProviderCallback.handleAddToContacts()
        R.id.menu_group_recipients -> optionsMenuProviderCallback.handleDisplayGroupRecipients()
        R.id.menu_distribution_broadcast -> optionsMenuProviderCallback.handleDistributionBroadcastEnabled(menuItem)
        R.id.menu_distribution_conversation -> optionsMenuProviderCallback.handleDistributionConversationEnabled(menuItem)
        R.id.menu_group_settings -> optionsMenuProviderCallback.handleManageGroup()
        R.id.menu_leave -> optionsMenuProviderCallback.handleLeavePushGroup()
        R.id.menu_invite -> optionsMenuProviderCallback.handleInviteLink()
        R.id.menu_mute_notifications -> optionsMenuProviderCallback.handleMuteNotifications()
        R.id.menu_unmute_notifications -> optionsMenuProviderCallback.handleUnmuteNotifications()
        R.id.menu_conversation_settings -> optionsMenuProviderCallback.handleConversationSettings()
        R.id.menu_expiring_messages_off, R.id.menu_expiring_messages -> optionsMenuProviderCallback.handleSelectMessageExpiration()
        R.id.menu_create_bubble -> optionsMenuProviderCallback.handleCreateBubble()
        R.id.home -> optionsMenuProviderCallback.handleGoHome()
=======
        R.id.menu_call_secure -> callback.handleDial(true)
        R.id.menu_video_secure -> callback.handleVideo()
        R.id.menu_call_insecure -> callback.handleDial(false)
        R.id.menu_view_media -> callback.handleViewMedia()
        R.id.menu_add_shortcut -> callback.handleAddShortcut()
        R.id.menu_search -> callback.handleSearch()
        R.id.menu_add_to_contacts -> callback.handleAddToContacts()
        R.id.menu_group_recipients -> callback.handleDisplayGroupRecipients()
        R.id.menu_distribution_broadcast -> callback.handleDistributionBroadcastEnabled(menuItem)
        R.id.menu_distribution_conversation -> callback.handleDistributionConversationEnabled(menuItem)
        R.id.menu_group_settings -> callback.handleManageGroup()
        R.id.menu_leave -> callback.handleLeavePushGroup()
        R.id.menu_invite -> callback.handleInviteLink()
        R.id.menu_mute_notifications -> callback.handleMuteNotifications()
        R.id.menu_unmute_notifications -> callback.handleUnmuteNotifications()
        R.id.menu_conversation_settings -> callback.handleConversationSettings()
        R.id.menu_expiring_messages_off, R.id.menu_expiring_messages -> callback.handleSelectMessageExpiration()
        R.id.menu_create_bubble -> callback.handleCreateBubble()
        R.id.home -> callback.handleGoHome()
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        R.id.menu_call_secure -> optionsMenuProviderCallback.handleDial(getRecipient(), true)
        R.id.menu_video_secure -> optionsMenuProviderCallback.handleVideo(getRecipient())
        R.id.menu_call_insecure -> optionsMenuProviderCallback.handleDial(getRecipient(), false)
        R.id.menu_view_media -> optionsMenuProviderCallback.handleViewMedia()
        R.id.menu_add_shortcut -> optionsMenuProviderCallback.handleAddShortcut()
        R.id.menu_search -> optionsMenuProviderCallback.handleSearch()
        R.id.menu_add_to_contacts -> optionsMenuProviderCallback.handleAddToContacts()
        R.id.menu_group_recipients -> optionsMenuProviderCallback.handleDisplayGroupRecipients()
        R.id.menu_distribution_broadcast -> optionsMenuProviderCallback.handleDistributionBroadcastEnabled(menuItem)
        R.id.menu_distribution_conversation -> optionsMenuProviderCallback.handleDistributionConversationEnabled(menuItem)
        R.id.menu_group_settings -> optionsMenuProviderCallback.handleManageGroup()
        R.id.menu_leave -> optionsMenuProviderCallback.handleLeavePushGroup()
        R.id.menu_invite -> optionsMenuProviderCallback.handleInviteLink()
        R.id.menu_mute_notifications -> optionsMenuProviderCallback.handleMuteNotifications()
        R.id.menu_unmute_notifications -> optionsMenuProviderCallback.handleUnmuteNotifications()
        R.id.menu_conversation_settings -> optionsMenuProviderCallback.handleConversationSettings()
        R.id.menu_expiring_messages_off, R.id.menu_expiring_messages -> optionsMenuProviderCallback.handleSelectMessageExpiration()
        R.id.menu_create_bubble -> optionsMenuProviderCallback.handleCreateBubble()
        R.id.home -> optionsMenuProviderCallback.handleGoHome()
=======
        R.id.menu_call_secure -> callback.handleDial(true)
        R.id.menu_video_secure -> callback.handleVideo()
        R.id.menu_call_insecure -> callback.handleDial(false)
        R.id.menu_view_media -> callback.handleViewMedia()
        R.id.menu_add_shortcut -> callback.handleAddShortcut()
        R.id.menu_search -> callback.handleSearch()
        R.id.menu_add_to_contacts -> callback.handleAddToContacts()
        R.id.menu_group_recipients -> callback.handleDisplayGroupRecipients()
        R.id.menu_distribution_broadcast -> callback.handleDistributionBroadcastEnabled(menuItem)
        R.id.menu_distribution_conversation -> callback.handleDistributionConversationEnabled(menuItem)
        R.id.menu_group_settings -> callback.handleManageGroup()
        R.id.menu_leave -> callback.handleLeavePushGroup()
        R.id.menu_invite -> callback.handleInviteLink()
        R.id.menu_mute_notifications -> callback.handleMuteNotifications()
        R.id.menu_unmute_notifications -> callback.handleUnmuteNotifications()
        R.id.menu_conversation_settings -> callback.handleConversationSettings()
        R.id.menu_expiring_messages_off, R.id.menu_expiring_messages -> callback.handleSelectMessageExpiration()
        R.id.menu_create_bubble -> callback.handleCreateBubble()
        R.id.home -> callback.handleGoHome()
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
        else -> return false
      }

      return true
    }

    private fun hideMenuItem(menu: Menu, @IdRes menuItem: Int) {
      if (menu.findItem(menuItem) != null) {
        menu.findItem(menuItem).isVisible = false
      }
    }
<<<<<<< HEAD
<<<<<<< HEAD

    private fun MenuItem.applyTitleSpan(span: Any) {
      title = SpannableString(title).apply { setSpan(span, 0, length, MessageStyler.SPAN_FLAGS) }
    }
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)

    private fun isSingleConversation(): Boolean = getRecipient()?.isGroup == false

    private fun isGroupConversation(): Boolean = getRecipient()?.isGroup == true

    private fun isPushGroupConversation(): Boolean = getRecipient()?.isPushGroup == true
=======
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)

    private fun isSingleConversation(): Boolean = getRecipient()?.isGroup == false

    private fun isGroupConversation(): Boolean = getRecipient()?.isGroup == true

    private fun isPushGroupConversation(): Boolean = getRecipient()?.isPushGroup == true
=======
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  }

  /**
   * Data snapshot for building out menu state.
   */
<<<<<<< HEAD
<<<<<<< HEAD
  data class Snapshot(
    val recipient: Recipient?,
    val isPushAvailable: Boolean,
    val canShowAsBubble: Observable<Boolean>,
    val isActiveGroup: Boolean,
    val isActiveV2Group: Boolean,
    val isInActiveGroup: Boolean,
    val hasActiveGroupCall: Boolean,
    val distributionType: Int,
    val threadId: Long,
    val messageRequestState: MessageRequestState,
    val isInBubble: Boolean
  )
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  interface Dependencies {
    val liveRecipient: LiveRecipient?
    val viewModel: ConversationViewModel
    val groupViewModel: ConversationGroupViewModel
    val groupCallViewModel: GroupCallViewModel?
    val titleView: ConversationTitleView
    val distributionType: Int
    val threadId: Long
    fun isInMessageRequest(): Boolean
    fun showGroupCallingTooltip()
    fun isInBubble(): Boolean
  }
=======
  data class Snapshot(
    val recipient: Recipient?,
    val isPushAvailable: Boolean,
    val canShowAsBubble: Observable<Boolean>,
    val isActiveGroup: Boolean,
    val isActiveV2Group: Boolean,
    val isInActiveGroup: Boolean,
    val hasActiveGroupCall: Boolean,
    val distributionType: Int,
    val threadId: Long,
    val isInMessageRequest: Boolean,
    val isInBubble: Boolean
  )
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  interface Dependencies {
    val liveRecipient: LiveRecipient?
    val viewModel: ConversationViewModel
    val groupViewModel: ConversationGroupViewModel
    val groupCallViewModel: GroupCallViewModel?
    val titleView: ConversationTitleView
    val distributionType: Int
    val threadId: Long
    fun isInMessageRequest(): Boolean
    fun showGroupCallingTooltip()
    fun isInBubble(): Boolean
  }
=======
  data class Snapshot(
    val recipient: Recipient?,
    val isPushAvailable: Boolean,
    val canShowAsBubble: Observable<Boolean>,
    val isActiveGroup: Boolean,
    val isActiveV2Group: Boolean,
    val isInActiveGroup: Boolean,
    val hasActiveGroupCall: Boolean,
    val distributionType: Int,
    val threadId: Long,
    val isInMessageRequest: Boolean,
    val isInBubble: Boolean
  )
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)

  /**
   * Callbacks abstraction for the converstaion options menu
   */
  interface Callback {
<<<<<<< HEAD
<<<<<<< HEAD
    fun getSnapshot(): Snapshot
    fun isTextHighlighted(): Boolean

||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
    fun getSnapshot(): Snapshot

>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
    fun getSnapshot(): Snapshot

>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    fun onOptionsMenuCreated(menu: Menu)

<<<<<<< HEAD
<<<<<<< HEAD
    fun handleVideo()
    fun handleDial()
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    fun handleVideo(recipient: Recipient?)
    fun handleDial(recipient: Recipient?, isSecure: Boolean)
=======
    fun handleVideo()
    fun handleDial(isSecure: Boolean)
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    fun handleVideo(recipient: Recipient?)
    fun handleDial(recipient: Recipient?, isSecure: Boolean)
=======
    fun handleVideo()
    fun handleDial(isSecure: Boolean)
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    fun handleViewMedia()
    fun handleAddShortcut()
    fun handleSearch()
    fun handleAddToContacts()
    fun handleDisplayGroupRecipients()
    fun handleManageGroup()
    fun handleLeavePushGroup()
    fun handleInviteLink()
    fun handleMuteNotifications()
    fun handleUnmuteNotifications()
    fun handleConversationSettings()
    fun handleSelectMessageExpiration()
    fun handleCreateBubble()
    fun handleGoHome()
<<<<<<< HEAD
<<<<<<< HEAD
    fun showExpiring(recipient: Recipient)
    fun clearExpiring()
    fun handleFormatText(@IdRes id: Int)
    fun handleBlock()
    fun handleUnblock()
    fun handleReportSpam()
    fun handleMessageRequestAccept()
    fun handleDeleteConversation()
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
    fun showExpiring(recipient: Recipient)
    fun clearExpiring()
    fun showGroupCallingTooltip()
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
    fun showExpiring(recipient: Recipient)
    fun clearExpiring()
    fun showGroupCallingTooltip()
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  }
}
