package org.thoughtcrime.securesms.components.settings.app.chats

<<<<<<< HEAD
=======
import android.app.Activity
import android.content.Intent
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import android.os.Build // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
import android.os.Build // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
import android.os.Build // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
import android.os.Build // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
import android.os.Build // JW: added
>>>>>>> c5d82267d1 (Added extra options)
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import org.thoughtcrime.securesms.R
import org.thoughtcrime.securesms.backup.BackupDialog // JW: added
import org.thoughtcrime.securesms.components.settings.DSLConfiguration
import org.thoughtcrime.securesms.components.settings.DSLSettingsFragment
import org.thoughtcrime.securesms.components.settings.DSLSettingsText
import org.thoughtcrime.securesms.components.settings.app.subscription.MessageBackupsCheckoutLauncher.createBackupsCheckoutLauncher
import org.thoughtcrime.securesms.components.settings.configure
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import org.thoughtcrime.securesms.util.RemoteConfig
=======
import org.thoughtcrime.securesms.service.LocalBackupListener // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences // JW: added
||||||| parent of 775ec008cc (Added extra options)
=======
import org.thoughtcrime.securesms.service.LocalBackupListener // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
import org.thoughtcrime.securesms.service.LocalBackupListener // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
import org.thoughtcrime.securesms.service.LocalBackupListener // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
import org.thoughtcrime.securesms.service.LocalBackupListener // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences // JW: added
>>>>>>> c5d82267d1 (Added extra options)
import org.thoughtcrime.securesms.exporter.flow.SmsExportActivity
import org.thoughtcrime.securesms.exporter.flow.SmsExportDialogs
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import org.thoughtcrime.securesms.keyvalue.SignalStore // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore // JW: added
>>>>>>> c5d82267d1 (Added extra options)
import org.thoughtcrime.securesms.util.adapter.mapping.MappingAdapter
import org.thoughtcrime.securesms.util.navigation.safeNavigate
import org.thoughtcrime.securesms.util.UriUtils // JW: added

class ChatsSettingsFragment : DSLSettingsFragment(R.string.preferences_chats__chats) {

  private lateinit var viewModel: ChatsSettingsViewModel
<<<<<<< HEAD
  private lateinit var checkoutLauncher: ActivityResultLauncher<Unit>
=======
  private lateinit var smsExportLauncher: ActivityResultLauncher<Intent>
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
  private val mapLabels by lazy { resources.getStringArray(R.array.pref_map_type_entries) } // JW: added
  private val mapValues by lazy { resources.getStringArray(R.array.pref_map_type_values) }  // JW: added
  private val groupAddLabels by lazy { resources.getStringArray(R.array.pref_group_add_entries) } // JW: added
  private val groupAddValues by lazy { resources.getStringArray(R.array.pref_group_add_values) }  // JW: added
  val CHOOSE_BACKUPS_LOCATION_REQUEST_CODE = 1201 // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
  private val mapLabels by lazy { resources.getStringArray(R.array.pref_map_type_entries) } // JW: added
  private val mapValues by lazy { resources.getStringArray(R.array.pref_map_type_values) }  // JW: added
  private val groupAddLabels by lazy { resources.getStringArray(R.array.pref_group_add_entries) } // JW: added
  private val groupAddValues by lazy { resources.getStringArray(R.array.pref_group_add_values) }  // JW: added
  val CHOOSE_BACKUPS_LOCATION_REQUEST_CODE = 1201 // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
  private val mapLabels by lazy { resources.getStringArray(R.array.pref_map_type_entries) } // JW: added
  private val mapValues by lazy { resources.getStringArray(R.array.pref_map_type_values) }  // JW: added
  private val groupAddLabels by lazy { resources.getStringArray(R.array.pref_group_add_entries) } // JW: added
  private val groupAddValues by lazy { resources.getStringArray(R.array.pref_group_add_values) }  // JW: added
  val CHOOSE_BACKUPS_LOCATION_REQUEST_CODE = 1201 // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
  private val mapLabels by lazy { resources.getStringArray(R.array.pref_map_type_entries) } // JW: added
  private val mapValues by lazy { resources.getStringArray(R.array.pref_map_type_values) }  // JW: added
  private val groupAddLabels by lazy { resources.getStringArray(R.array.pref_group_add_entries) } // JW: added
  private val groupAddValues by lazy { resources.getStringArray(R.array.pref_group_add_values) }  // JW: added
  val CHOOSE_BACKUPS_LOCATION_REQUEST_CODE = 1201 // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
  private val mapLabels by lazy { resources.getStringArray(R.array.pref_map_type_entries) } // JW: added
  private val mapValues by lazy { resources.getStringArray(R.array.pref_map_type_values) }  // JW: added
  private val groupAddLabels by lazy { resources.getStringArray(R.array.pref_group_add_entries) } // JW: added
  private val groupAddValues by lazy { resources.getStringArray(R.array.pref_group_add_values) }  // JW: added
  val CHOOSE_BACKUPS_LOCATION_REQUEST_CODE = 1201 // JW: added
>>>>>>> c5d82267d1 (Added extra options)

  override fun onResume() {
    super.onResume()
    viewModel.refresh()
  }

  @Suppress("ReplaceGetOrSet")
  override fun bindAdapter(adapter: MappingAdapter) {
    checkoutLauncher = createBackupsCheckoutLauncher {
      findNavController().safeNavigate(ChatsSettingsFragmentDirections.actionChatsSettingsFragmentToRemoteBackupsSettingsFragment().setBackupLaterSelected(it))
    }

    viewModel = ViewModelProvider(this).get(ChatsSettingsViewModel::class.java)

    viewModel.state.observe(viewLifecycleOwner) {
      adapter.submitList(getConfiguration(it).toMappingModelList())
    }
  }

  // JW: added
  override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
    super.onActivityResult(requestCode, resultCode, intent)

    if (intent != null && intent.data != null) {
      if (resultCode == Activity.RESULT_OK) {
        if (requestCode == CHOOSE_BACKUPS_LOCATION_REQUEST_CODE) {
          val backupUri = intent.data
          val takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
          SignalStore.settings().setSignalBackupDirectory(backupUri!!)
          context?.getContentResolver()?.takePersistableUriPermission(backupUri, takeFlags)
          TextSecurePreferences.setNextBackupTime(requireContext(), 0)
          LocalBackupListener.schedule(context)
          viewModel.setChatBackupLocationApi30(UriUtils.getFullPathFromTreeUri(context, backupUri))
        }
      }
    }
  }

  private fun getConfiguration(state: ChatsSettingsState): DSLConfiguration {
    return configure {
      switchPref(
        title = DSLSettingsText.from(R.string.preferences__generate_link_previews),
        summary = DSLSettingsText.from(R.string.preferences__retrieve_link_previews_from_websites_for_messages),
        isChecked = state.generateLinkPreviews,
        onClick = {
          viewModel.setGenerateLinkPreviewsEnabled(!state.generateLinkPreviews)
        }
      )

      switchPref(
        title = DSLSettingsText.from(R.string.preferences__pref_use_address_book_photos),
        summary = DSLSettingsText.from(R.string.preferences__display_contact_photos_from_your_address_book_if_available),
        isChecked = state.useAddressBook,
        onClick = {
          viewModel.setUseAddressBook(!state.useAddressBook)
        }
      )

      switchPref(
        title = DSLSettingsText.from(R.string.preferences__pref_keep_muted_chats_archived),
        summary = DSLSettingsText.from(R.string.preferences__muted_chats_that_are_archived_will_remain_archived),
        isChecked = state.keepMutedChatsArchived,
        onClick = {
          viewModel.setKeepMutedChatsArchived(!state.keepMutedChatsArchived)
        }
      )

      dividerPref()

      sectionHeaderPref(R.string.ChatsSettingsFragment__keyboard)

      switchPref(
        title = DSLSettingsText.from(R.string.preferences_advanced__use_system_emoji),
        isChecked = state.useSystemEmoji,
        onClick = {
          viewModel.setUseSystemEmoji(!state.useSystemEmoji)
        }
      )

      switchPref(
        title = DSLSettingsText.from(R.string.ChatsSettingsFragment__send_with_enter),
        isChecked = state.enterKeySends,
        onClick = {
          viewModel.setEnterKeySends(!state.enterKeySends)
        }
      )

      dividerPref()

      sectionHeaderPref(R.string.preferences_chats__backups)

      if (RemoteConfig.messageBackups || state.canAccessRemoteBackupsSettings) {
        clickPref(
          title = DSLSettingsText.from(R.string.RemoteBackupsSettingsFragment__signal_backups),
          summary = DSLSettingsText.from(if (state.canAccessRemoteBackupsSettings) R.string.arrays__enabled else R.string.arrays__disabled),
          onClick = {
            if (state.canAccessRemoteBackupsSettings) {
              Navigation.findNavController(requireView()).safeNavigate(R.id.action_chatsSettingsFragment_to_remoteBackupsSettingsFragment)
            } else {
              checkoutLauncher.launch(Unit)
            }
          }
        )
      }

      clickPref(
        title = DSLSettingsText.from(R.string.preferences_chats__chat_backups),
        summary = DSLSettingsText.from(if (state.localBackupsEnabled) R.string.arrays__enabled else R.string.arrays__disabled),
        onClick = {
          Navigation.findNavController(requireView()).safeNavigate(R.id.action_chatsSettingsFragment_to_backupsPreferenceFragment)
        }
      )

      // JW: added
      if (Build.VERSION.SDK_INT < 30) {
        switchPref(
          title = DSLSettingsText.from(R.string.preferences_chats__chat_backups_removable),
          summary = DSLSettingsText.from(R.string.preferences_chats__backup_chats_to_removable_storage),
          isChecked = state.chatBackupsLocation,
          onClick = {
            viewModel.setChatBackupLocation(!state.chatBackupsLocation)
          }
        )
      } else {
        val backupUri = SignalStore.settings().signalBackupDirectory
        val summaryText = UriUtils.getFullPathFromTreeUri(context, backupUri)

        clickPref(
          title = DSLSettingsText.from(R.string.preferences_chats__chat_backups_location_tap_to_change),
          summary = DSLSettingsText.from(summaryText),
          onClick = {
            BackupDialog.showChooseBackupLocationDialog(this@ChatsSettingsFragment, CHOOSE_BACKUPS_LOCATION_REQUEST_CODE)
            viewModel.setChatBackupLocationApi30(UriUtils.getFullPathFromTreeUri(context, backupUri))
          }
        )
      }

      // JW: added
      switchPref(
        title = DSLSettingsText.from(R.string.preferences_chats__chat_backups_zipfile),
        summary = DSLSettingsText.from(R.string.preferences_chats__backup_chats_to_encrypted_zipfile),
        isChecked = state.chatBackupZipfile,
        onClick = {
          viewModel.setChatBackupZipfile(!state.chatBackupZipfile)
        }
      )

      // JW: added
      switchPref(
        title = DSLSettingsText.from(R.string.preferences_chats__chat_backups_zipfile_plain),
        summary = DSLSettingsText.from(R.string.preferences_chats__backup_chats_to_encrypted_zipfile_plain),
        isChecked = state.chatBackupZipfilePlain,
        onClick = {
          viewModel.setChatBackupZipfilePlain(!state.chatBackupZipfilePlain)
        }
      )

      dividerPref()

      sectionHeaderPref(R.string.preferences_chats__control_message_deletion)

      // JW: added
      switchPref(
        title = DSLSettingsText.from(R.string.preferences_chats__chat_keep_view_once_messages),
        summary = DSLSettingsText.from(R.string.preferences_chats__keep_view_once_messages_summary),
        isChecked = state.keepViewOnceMessages,
        onClick = {
          viewModel.keepViewOnceMessages(!state.keepViewOnceMessages)
        }
      )

      // JW: added
      switchPref(
        title = DSLSettingsText.from(R.string.preferences_chats__chat_ignore_remote_delete),
        summary = DSLSettingsText.from(R.string.preferences_chats__chat_ignore_remote_delete_summary),
        isChecked = state.ignoreRemoteDelete,
        onClick = {
          viewModel.ignoreRemoteDelete(!state.ignoreRemoteDelete)
        }
      )

      // JW: added
      switchPref(
        title = DSLSettingsText.from(R.string.preferences_chats__delete_media_only),
        summary = DSLSettingsText.from(R.string.preferences_chats__delete_media_only_summary),
        isChecked = state.deleteMediaOnly,
        onClick = {
          viewModel.deleteMediaOnly(!state.deleteMediaOnly)
        }
      )

      dividerPref()

      sectionHeaderPref(R.string.preferences_chats__group_control)

      // JW: added
      radioListPref(
        title = DSLSettingsText.from(R.string.preferences_chats__who_can_add_you_to_groups),
        listItems = groupAddLabels,
        selected = groupAddValues.indexOf(state.whoCanAddYouToGroups),
        onSelected = {
          viewModel.setWhoCanAddYouToGroups(groupAddValues[it])
        }
      )

      dividerPref()

      sectionHeaderPref(R.string.preferences_chats__google_map_type)

      // JW: added
      radioListPref(
        title = DSLSettingsText.from(R.string.preferences__map_type),
        listItems = mapLabels,
        selected = mapValues.indexOf(state.googleMapType),
        onSelected = {
          viewModel.setGoogleMapType(mapValues[it])
        }
      )
    }
  }
}
