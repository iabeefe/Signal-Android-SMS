package org.thoughtcrime.securesms.util;


import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.attachments.Attachment;
import org.thoughtcrime.securesms.attachments.AttachmentId;
import org.thoughtcrime.securesms.attachments.DatabaseAttachment;
import org.thoughtcrime.securesms.database.NoSuchMessageException;
import org.thoughtcrime.securesms.database.MessageTable; // JW: added
import org.thoughtcrime.securesms.database.SignalDatabase;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.jobmanager.impl.NotInCallConstraint;
import org.thoughtcrime.securesms.jobs.MultiDeviceDeleteSyncJob;
import org.thoughtcrime.securesms.recipients.Recipient;

import java.util.Collections;
import java.util.Set;

public class AttachmentUtil {

  private static final String TAG = Log.tag(AttachmentUtil.class);

  @MainThread
  public static boolean isRestoreOnOpenPermitted(@NonNull Context context, @Nullable Attachment attachment) {
    if (attachment == null) {
      Log.w(TAG, "attachment was null, returning vacuous true");
      return true;
    }
    Set<String> allowedTypes = getAllowedAutoDownloadTypes(context);
    String      contentType  = attachment.contentType;

    if (MediaUtil.isImageType(contentType)) {
      return NotInCallConstraint.isNotInConnectedCall() && allowedTypes.contains(MediaUtil.getDiscreteMimeType(contentType));
    }
    return false;
  }
  
  @WorkerThread
  public static boolean isAutoDownloadPermitted(@NonNull Context context, @Nullable DatabaseAttachment attachment) {
    if (attachment == null) {
      Log.w(TAG, "attachment was null, returning vacuous true");
      return true;
    }

    if (!isFromTrustedConversation(context, attachment)) {
      Log.w(TAG, "Not allowing download due to untrusted conversation");
      return false;
    }

    Set<String> allowedTypes = getAllowedAutoDownloadTypes(context);
    String      contentType  = attachment.contentType;

    if (attachment.voiceNote ||
        (MediaUtil.isAudio(attachment) && TextUtils.isEmpty(attachment.fileName)) ||
        MediaUtil.isLongTextType(attachment.contentType) ||
        attachment.isSticker())
    {
      return true;
    } else if (attachment.videoGif) {
      boolean allowed = NotInCallConstraint.isNotInConnectedCall() && allowedTypes.contains("image");
      if (!allowed) {
        Log.w(TAG, "Not auto downloading. inCall: " + NotInCallConstraint.isNotInConnectedCall() + " allowedType: " + allowedTypes.contains("image"));
      }
      return allowed;
    } else if (isNonDocumentType(contentType)) {
      boolean allowed = NotInCallConstraint.isNotInConnectedCall() && allowedTypes.contains(MediaUtil.getDiscreteMimeType(contentType));
      if (!allowed) {
        Log.w(TAG, "Not auto downloading. inCall: " + NotInCallConstraint.isNotInConnectedCall() + " allowedType: " + allowedTypes.contains(MediaUtil.getDiscreteMimeType(contentType)));
      }
      return allowed;
    } else {
      boolean allowed = NotInCallConstraint.isNotInConnectedCall() && allowedTypes.contains("documents");
      if (!allowed) {
        Log.w(TAG, "Not auto downloading. inCall: " + NotInCallConstraint.isNotInConnectedCall() + " allowedType: " + allowedTypes.contains("documents"));
      }
      return allowed;
    }
  }

  /**
   * Deletes the specified attachment. If its the only attachment for its linked message, the entire
   * message is deleted.
   *
   * @return message record of deleted message if a message is deleted
   */
  @WorkerThread
  public static @Nullable MessageRecord deleteAttachment(@NonNull DatabaseAttachment attachment) {
    AttachmentId attachmentId    = attachment.attachmentId;
    long         mmsId           = attachment.mmsId;
    int          attachmentCount = SignalDatabase.attachments()
                                                 .getAttachmentsForMessage(mmsId)
                                                 .size();

    MessageRecord deletedMessageRecord = null;
    if (attachmentCount <= 1) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
      deletedMessageRecord = SignalDatabase.messages().getMessageRecordOrNull(mmsId);
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of cdbbc46ede (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> cdbbc46ede (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
      SignalDatabase.messages().deleteMessage(mmsId);
=======
      // JW: changed
      if (!TextSecurePreferences.isDeleteMediaOnly(context)) {
        SignalDatabase.messages().deleteMessage(mmsId);
      }  else {
        SignalDatabase.messages().deleteAttachmentsOnly(mmsId);
      }
>>>>>>> efc40a1af7 (Added extra options)
    } else {
      SignalDatabase.attachments().deleteAttachment(attachmentId);
      if (Recipient.self().getDeleteSyncCapability().isSupported()) {
        MultiDeviceDeleteSyncJob.enqueueAttachmentDelete(SignalDatabase.messages().getMessageRecordOrNull(mmsId), attachment);
      }
    }

    return deletedMessageRecord;
  }


  private static boolean isNonDocumentType(String contentType) {
    return
        MediaUtil.isImageType(contentType) ||
        MediaUtil.isVideoType(contentType) ||
        MediaUtil.isAudioType(contentType);
  }

  private static @NonNull Set<String> getAllowedAutoDownloadTypes(@NonNull Context context) {
    if      (NetworkUtil.isConnectedWifi(context))    return TextSecurePreferences.getWifiMediaDownloadAllowed(context);
    else if (NetworkUtil.isConnectedRoaming(context)) return TextSecurePreferences.getRoamingMediaDownloadAllowed(context);
    else if (NetworkUtil.isConnectedMobile(context))  return TextSecurePreferences.getMobileMediaDownloadAllowed(context);
    else                                              return Collections.emptySet();
  }

  @WorkerThread
  private static boolean isFromTrustedConversation(@NonNull Context context, @NonNull DatabaseAttachment attachment) {
    try {
      MessageRecord message = SignalDatabase.messages().getMessageRecord(attachment.mmsId);

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
      Recipient fromRecipient = message.getFromRecipient();
      Recipient toRecipient   = SignalDatabase.threads().getRecipientForThreadId(message.getThreadId());
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      Recipient individualRecipient = message.getRecipient();
      Recipient threadRecipient     = SignalDatabase.threads().getRecipientForThreadId(message.getThreadId());
=======
      Recipient fromRecipient = message.getFromRecipient();
      Recipient toRecipient   = message.getToRecipient();
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      Recipient individualRecipient = message.getRecipient();
      Recipient threadRecipient     = SignalDatabase.threads().getRecipientForThreadId(message.getThreadId());
=======
      Recipient fromRecipient = message.getFromRecipient();
      Recipient toRecipient   = message.getToRecipient();
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      Recipient individualRecipient = message.getRecipient();
      Recipient threadRecipient     = SignalDatabase.threads().getRecipientForThreadId(message.getThreadId());
=======
      Recipient fromRecipient = message.getFromRecipient();
      Recipient toRecipient   = message.getToRecipient();
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)

      if (toRecipient != null && toRecipient.isGroup()) {
        return toRecipient.isProfileSharing() || isTrustedIndividual(fromRecipient, message);
      } else {
        return isTrustedIndividual(fromRecipient, message);
      }
    } catch (NoSuchMessageException e) {
      Log.w(TAG, "Message could not be found! Assuming not a trusted contact.");
      return false;
    }
  }

  private static boolean isTrustedIndividual(@NonNull Recipient recipient, @NonNull MessageRecord message) {
    return recipient.isSystemContact()  ||
           recipient.isProfileSharing() ||
           message.isOutgoing()         ||
           recipient.isSelf()           ||
           recipient.isReleaseNotes();
    }
  }
