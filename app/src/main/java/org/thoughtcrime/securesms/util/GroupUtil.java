package org.thoughtcrime.securesms.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;

import org.signal.core.util.StringUtil;
import org.signal.core.util.logging.Log;
import org.signal.libsignal.zkgroup.InvalidInputException;
import org.signal.libsignal.zkgroup.groups.GroupMasterKey;
import org.thoughtcrime.securesms.R;
import org.thoughtcrime.securesms.database.GroupTable;
import org.thoughtcrime.securesms.database.SignalDatabase;
import org.thoughtcrime.securesms.database.model.GroupRecord;
import org.thoughtcrime.securesms.groups.GroupId;
import org.thoughtcrime.securesms.messages.SignalServiceProtoUtil;
import org.thoughtcrime.securesms.mms.MessageGroupContext;
import org.thoughtcrime.securesms.recipients.Recipient;
import org.thoughtcrime.securesms.recipients.RecipientId;
import org.whispersystems.signalservice.api.messages.SignalServiceDataMessage;
import org.whispersystems.signalservice.api.messages.SignalServiceGroupV2;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import org.whispersystems.signalservice.internal.push.Content;
import org.whispersystems.signalservice.internal.push.GroupContextV2;
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import org.whispersystems.signalservice.internal.push.SignalServiceProtos;
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import org.whispersystems.signalservice.internal.push.SignalServiceProtos;
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
import org.whispersystems.signalservice.internal.push.SignalServiceProtos;
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)

import java.io.IOException;
import java.util.List;

public final class GroupUtil {

  private GroupUtil() {
  }

  private static final String TAG = Log.tag(GroupUtil.class);

  public static @Nullable GroupContextV2 getGroupContextIfPresent(@NonNull Content content) {
    if (content.dataMessage != null && SignalServiceProtoUtil.INSTANCE.getHasGroupContext(content.dataMessage)) {
      return content.dataMessage.groupV2;
    } else if (content.syncMessage != null                 &&
               content.syncMessage.sent != null &&
               content.syncMessage.sent.message != null &&
               SignalServiceProtoUtil.INSTANCE.getHasGroupContext(content.syncMessage.sent.message))
    {
      return content.syncMessage.sent.message.groupV2;
    } else if (content.storyMessage != null && SignalServiceProtoUtil.INSTANCE.isValid(content.storyMessage.group)) {
      return content.storyMessage.group;
    } else {
      return null;
    }
  }

<<<<<<< HEAD
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
  /**
   * Result may be a v1 or v2 GroupId.
   */
  public static @NonNull Optional<GroupId> idFromGroupContext(@NonNull Optional<SignalServiceGroupV2> groupContext) {
    if (groupContext.isPresent()) {
      return Optional.of(GroupId.v2(groupContext.get().getMasterKey()));
    } else {
      return Optional.empty();
    }
  }

=======

  public static @Nullable SignalServiceProtos.GroupContextV2 getGroupContextIfPresent(@NonNull SignalServiceProtos.Content content) {
    if (content.hasDataMessage() && SignalServiceProtoUtil.INSTANCE.getHasGroupContext(content.getDataMessage())) {
      return content.getDataMessage().getGroupV2();
    } else if (content.hasSyncMessage()                 &&
               content.getSyncMessage().hasSent() &&
               content.getSyncMessage().getSent().hasMessage() &&
               SignalServiceProtoUtil.INSTANCE.getHasGroupContext(content.getSyncMessage().getSent().getMessage()))
    {
      return content.getSyncMessage().getSent().getMessage().getGroupV2();
    } else if (content.hasStoryMessage() && SignalServiceProtoUtil.INSTANCE.isValid(content.getStoryMessage().getGroup())) {
      return content.getStoryMessage().getGroup();
    } else {
      return null;
    }
  }


  public static @Nullable SignalServiceProtos.GroupContextV2 getGroupContextIfPresent(@NonNull SignalServiceProtos.Content content) {
    if (content.hasDataMessage() && SignalServiceProtoUtil.INSTANCE.getHasGroupContext(content.getDataMessage())) {
      return content.getDataMessage().getGroupV2();
    } else if (content.hasSyncMessage()                 &&
               content.getSyncMessage().hasSent() &&
               content.getSyncMessage().getSent().hasMessage() &&
               SignalServiceProtoUtil.INSTANCE.getHasGroupContext(content.getSyncMessage().getSent().getMessage()))
    {
      return content.getSyncMessage().getSent().getMessage().getGroupV2();
    } else if (content.hasStoryMessage() && SignalServiceProtoUtil.INSTANCE.isValid(content.getStoryMessage().getGroup())) {
      return content.getStoryMessage().getGroup();
    } else {
      return null;
    }
  }


  public static @Nullable SignalServiceProtos.GroupContextV2 getGroupContextIfPresent(@NonNull SignalServiceProtos.Content content) {
    if (content.hasDataMessage() && SignalServiceProtoUtil.INSTANCE.getHasGroupContext(content.getDataMessage())) {
      return content.getDataMessage().getGroupV2();
    } else if (content.hasSyncMessage()                 &&
               content.getSyncMessage().hasSent() &&
               content.getSyncMessage().getSent().hasMessage() &&
               SignalServiceProtoUtil.INSTANCE.getHasGroupContext(content.getSyncMessage().getSent().getMessage()))
    {
      return content.getSyncMessage().getSent().getMessage().getGroupV2();
    } else if (content.hasStoryMessage() && SignalServiceProtoUtil.INSTANCE.isValid(content.getStoryMessage().getGroup())) {
      return content.getStoryMessage().getGroup();
    } else {
      return null;
    }
  }

  /**
   * Result may be a v1 or v2 GroupId.
   */
  public static @NonNull Optional<GroupId> idFromGroupContext(@NonNull Optional<SignalServiceGroupV2> groupContext) {
    if (groupContext.isPresent()) {
      return Optional.of(GroupId.v2(groupContext.get().getMasterKey()));
    } else {
      return Optional.empty();
    }
  }

>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
  public static @NonNull GroupMasterKey requireMasterKey(@NonNull byte[] masterKey) {
    try {
      return new GroupMasterKey(masterKey);
    } catch (InvalidInputException e) {
      throw new AssertionError(e);
    }
  }

  public static @NonNull GroupDescription getNonV2GroupDescription(@NonNull Context context, @Nullable String encodedGroup) {
    if (encodedGroup == null) {
      return new GroupDescription(context, null);
    }

    try {
      MessageGroupContext groupContext = new MessageGroupContext(encodedGroup, false);
      return new GroupDescription(context, groupContext);
    } catch (IOException e) {
      Log.w(TAG, e);
      return new GroupDescription(context, null);
    }
  }

  @WorkerThread
  public static void setDataMessageGroupContext(@NonNull Context context,
                                                @NonNull SignalServiceDataMessage.Builder dataMessageBuilder,
                                                @NonNull GroupId.Push groupId)
  {
    if (groupId.isV2()) {
      GroupTable                   groupDatabase     = SignalDatabase.groups();
      GroupRecord                  groupRecord       = groupDatabase.requireGroup(groupId);
      GroupTable.V2GroupProperties v2GroupProperties = groupRecord.requireV2GroupProperties();
      SignalServiceGroupV2            group             = SignalServiceGroupV2.newBuilder(v2GroupProperties.getGroupMasterKey())
                                                                              .withRevision(v2GroupProperties.getGroupRevision())
                                                                              .build();
      dataMessageBuilder.asGroupMessage(group);
    }
  }

  public static class GroupDescription {

    @NonNull  private final Context             context;
    @Nullable private final MessageGroupContext groupContext;
    @Nullable private final List<RecipientId>   members;

    GroupDescription(@NonNull Context context, @Nullable MessageGroupContext groupContext) {
      this.context      = context.getApplicationContext();
      this.groupContext = groupContext;

      if (groupContext == null) {
        this.members = null;
      } else {
        List<RecipientId> membersList = groupContext.getMembersListExcludingSelf();
        this.members = membersList.isEmpty() ? null : membersList;
      }
    }

    @WorkerThread
    public String toString(@NonNull Recipient sender) {
      StringBuilder description = new StringBuilder();
      description.append(context.getString(R.string.MessageRecord_s_updated_group, sender.getDisplayName(context)));

      if (groupContext == null) {
        return description.toString();
      }

      String title = StringUtil.isolateBidi(groupContext.getName());

      if (members != null && members.size() > 0) {
        description.append("\n");
        description.append(context.getResources().getQuantityString(R.plurals.GroupUtil_joined_the_group,
                                                                    members.size(), toString(members)));
      }

      if (!title.trim().isEmpty()) {
        if (members != null) description.append(" ");
        else                 description.append("\n");
        description.append(context.getString(R.string.GroupUtil_group_name_is_now, title));
      }

      return description.toString();
    }

    private String toString(List<RecipientId> recipients) {
      StringBuilder result = new StringBuilder();

      for (int i = 0; i < recipients.size(); i++) {
        result.append(Recipient.live(recipients.get(i)).get().getDisplayName(context));

      if (i != recipients.size() -1 )
        result.append(", ");
    }

    return result.toString();
    }
  }
}
