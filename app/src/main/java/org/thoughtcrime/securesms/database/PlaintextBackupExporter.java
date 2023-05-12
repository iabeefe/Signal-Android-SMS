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
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
||||||| parent of 66c339aa35 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getIndividualRecipient().getSmsAddress().orElse("null"),
                                          record.getIndividualRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record));

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getIndividualRecipient().getSmsAddress().orElse("null"),
                                          record.getIndividualRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record));

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getIndividualRecipient().getSmsAddress().orElse("null"),
                                          record.getIndividualRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record));

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getIndividualRecipient().getSmsAddress().orElse("null"),
                                          record.getIndividualRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record));

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getIndividualRecipient().getSmsAddress().orElse("null"),
                                          record.getIndividualRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record));

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getIndividualRecipient().getSmsAddress().orElse("null"),
                                          record.getIndividualRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record));

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
package org.thoughtcrime.securesms.database;

import android.content.Context;

import org.signal.core.util.logging.Log;
import org.thoughtcrime.securesms.database.model.MessageRecord;
import org.thoughtcrime.securesms.database.model.MmsMessageRecord;
import org.thoughtcrime.securesms.util.FileUtilsJW;
import org.thoughtcrime.securesms.util.StorageUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.io.File;
import java.io.IOException;

public class PlaintextBackupExporter {
  private static final String TAG = Log.tag(PlaintextBackupExporter.class);

  private static final String FILENAME = "SignalPlaintextBackup.xml";
  private static final String ZIPFILENAME = "SignalPlaintextBackup.zip";

  public static void exportPlaintextToSd(Context context)
      throws NoExternalStorageException, IOException
  {
    exportPlaintext(context);
  }

  public static File getPlaintextExportFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), FILENAME);
  }

  private static File getPlaintextZipFile() throws NoExternalStorageException {
    return new File(StorageUtil.getBackupPlaintextDirectory(), ZIPFILENAME);
  }

  private static void exportPlaintext(Context context)
      throws NoExternalStorageException, IOException
  {
    MessageTable     messagetable = SignalDatabase.messages();
    int              count        = messagetable.getMessageCount();
    XmlBackup.Writer writer       = new XmlBackup.Writer(getPlaintextExportFile().getAbsolutePath(), count);

    MessageRecord record;

    MessageTable.MmsReader messagereader = null;
    int                    skip      = 0;
    int                    ROW_LIMIT = 500;

    do {
      if (messagereader != null)
        messagereader.close();

      messagereader = messagetable.mmsReaderFor(messagetable.getMessages(skip, ROW_LIMIT));

      try {
        while ((record = messagereader.getNext()) != null) {
          XmlBackup.XmlBackupItem item =
              new XmlBackup.XmlBackupItem(0,
                                          record.getFromRecipient().getSmsAddress().orElse("null"),
                                          record.getFromRecipient().getDisplayName(context),
                                          record.getDateReceived(),
                                          translateToSystemBaseType(record.getType()),
                                          null,
                                          record.getDisplayBody(context).toString(),
                                          null,
                                          1,
                                          record.getDeliveryStatus(),
                                          getTransportType(record),
                                          record.getToRecipient().getId().toLong());

          writer.writeItem(item);
        }
      }
      catch (Exception e) {
        Log.w(TAG, "messagereader.getNext() failed: " + e.getMessage());
      }

      skip += ROW_LIMIT;
    } while (messagereader.getCount() > 0);

    writer.close();

    if (TextSecurePreferences.isPlainBackupInZipfile(context)) {
      File test = new File(getPlaintextZipFile().getAbsolutePath());
      if (test.exists()) {
        test.delete();
      }
      FileUtilsJW.createEncryptedPlaintextZipfile(context, getPlaintextZipFile().getAbsolutePath(), getPlaintextExportFile().getAbsolutePath());
      getPlaintextExportFile().delete(); // Insecure, leaves possibly recoverable plaintext on device
      // FileUtilsJW.secureDelete(getPlaintextExportFile()); // much too slow
    }
  }

  private static String getTransportType(MessageRecord messageRecord) {
    String transportText = "-";
    if (messageRecord.isOutgoing() && messageRecord.isFailed()) {
      transportText = "-";
    } else if (messageRecord.isPending()) {
      transportText = "Pending";
    } else if (messageRecord.isPush()) {
      transportText = "Data";
    } else if (messageRecord.isMms()) {
      transportText = "MMS";
    } else {
      transportText = "SMS";
    }
    return transportText;
  }

  public static int translateToSystemBaseType(long type) {
    if (isInboxType(type)) return 1;
    else if (isOutgoingMessageType(type)) return 2;
    else if (isFailedMessageType(type)) return 5;

    return 1;
  }

  public static boolean isInboxType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_INBOX_TYPE;
  }

  public static boolean isOutgoingMessageType(long type) {
    for (long outgoingType : MessageTypes.OUTGOING_MESSAGE_TYPES) {
      if ((type & MessageTypes.BASE_TYPE_MASK) == outgoingType)
        return true;
    }

    return false;
  }

  public static boolean isFailedMessageType(long type) {
    return (type & MessageTypes.BASE_TYPE_MASK) == MessageTypes.BASE_SENT_FAILED_TYPE;
  }
}
>>>>>>> 69c4403d63 (Added extra options)
