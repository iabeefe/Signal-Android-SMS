package org.thoughtcrime.securesms.util;

import android.Manifest;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.annimon.stream.Stream; // JW: added

import org.thoughtcrime.securesms.BuildConfig;
import org.thoughtcrime.securesms.R;
import org.thoughtcrime.securesms.database.NoExternalStorageException;
<<<<<<< HEAD
import org.thoughtcrime.securesms.dependencies.AppDependencies;
import org.thoughtcrime.securesms.permissions.PermissionCompat;
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies;
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
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of cdbbc46ede (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> cdbbc46ede (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of cdbbc46ede (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> cdbbc46ede (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======
import org.thoughtcrime.securesms.keyvalue.SignalStore; // JW: added
>>>>>>> 83146b3342 (Added extra options)
import org.thoughtcrime.securesms.permissions.Permissions;
import org.thoughtcrime.securesms.util.UriUtils; // JW: added

import java.io.File;
import java.nio.file.Path; // JW: added
import java.util.List;
import java.util.Objects;

public class StorageUtil {

  private static final String PRODUCTION_PACKAGE_ID = "org.thoughtcrime.securesms";
  // JW: the different backup types
  private static final String BACKUPS = "Backups";
  private static final String FULL_BACKUPS = "FullBackups";
  private static final String PLAINTEXT_BACKUPS = "PlaintextBackups";

  // JW: split backup directories per type because otherwise some files might get unintentionally deleted
  public static File getBackupDirectory() throws NoExternalStorageException {
    if (Build.VERSION.SDK_INT >= 30) {
      // We don't add the separate "Backups" subdir for Android 11+ to not complicate things...
      return getBackupTypeDirectory("");
    } else {
      return getBackupTypeDirectory(BACKUPS);
    }
  }

  public static File getBackupPlaintextDirectory() throws NoExternalStorageException {
    return getBackupTypeDirectory(PLAINTEXT_BACKUPS);
  }

  public static File getRawBackupDirectory() throws NoExternalStorageException {
    return getBackupTypeDirectory(FULL_BACKUPS);
  }

  private static File getBackupTypeDirectory(String backupType) throws NoExternalStorageException {
    Context context = ApplicationDependencies.getApplication();
    File signal = null;
    if (Build.VERSION.SDK_INT < 30) {
      signal = getBackupBaseDirectory();
    } else {
      Uri backupDirectoryUri = SignalStore.settings().getSignalBackupDirectory();
      signal = new File(UriUtils.getFullPathFromTreeUri(context, backupDirectoryUri));
    }
    // For android 11+, if the last part ends with "Backups", remove that and add the backupType so
    // we still can use the Backups, FulBackups etc. subdirectories when the chosen backup folder
    // is a subdirectory called Backups.
    if (Build.VERSION.SDK_INT >= 30 && !backupType.equals("")) {
      Path selectedDir = signal.toPath();
      if (selectedDir.endsWith(BACKUPS)) {
        signal = selectedDir.getParent().toFile();
      }
    }
    File backups = new File(signal, backupType);

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    if (!backups.exists()) {
      if (!backups.mkdirs()) {
        throw new NoExternalStorageException("Unable to create backup directory...");
      }
    }

    return backups;
  }

  // JW: added. Returns storage dir on internal or removable storage
  private static File getStorage() throws NoExternalStorageException {
    Context context = ApplicationDependencies.getApplication();
    File storage = null;

    // We now check if the removable storage is prefered. If it is
    // and it is not available we fallback to internal storage.
    if (TextSecurePreferences.isBackupLocationRemovable(context)) {
      // For now we only support the application directory on the removable storage.
      if (Build.VERSION.SDK_INT >= 19) {
        File[] directories = context.getExternalFilesDirs(null);

        if (directories != null) {
          storage = Stream.of(directories)
                  .withoutNulls()
                  .filterNot(f -> f.getAbsolutePath().contains("emulated"))
                  .limit(1)
                  .findSingle()
                  .orElse(null);
        }
      }
    }
    if (storage == null) {
      storage = Environment.getExternalStorageDirectory();
    }
    return storage;
  }

  // JW: added method
  public static File getBackupBaseDirectory() throws NoExternalStorageException {
    File storage = getStorage();

    if (!storage.canWrite()) {
      throw new NoExternalStorageException();
    }

    File signal = new File(storage, "Signal");

    return signal;
  }

  public static File getOrCreateBackupDirectory() throws NoExternalStorageException {
    File storage = getStorage(); // JW: changed

    if (!storage.canWrite()) {
      throw new NoExternalStorageException();
    }

    File backups = getBackupDirectory();

    if (!backups.exists()) {
      if (!backups.mkdirs()) {
        throw new NoExternalStorageException("Unable to create backup directory...");
      }
    }

    return backups;
  }

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
  public static File getOrCreateBackupV2Directory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();

    if (!storage.canWrite()) {
      throw new NoExternalStorageException();
    }

    File backups = getBackupV2Directory();

    if (!backups.exists()) {
      if (!backups.mkdirs()) {
        throw new NoExternalStorageException("Unable to create backup directory...");
      }
    }

    return backups;
  }

  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

  public static File getBackupV2Directory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File backups  = new File(storage, "Signal");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(storage, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of cdbbc46ede (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> cdbbc46ede (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of cdbbc46ede (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> cdbbc46ede (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
  public static File getBackupDirectory() throws NoExternalStorageException {
    File storage = Environment.getExternalStorageDirectory();
    File signal  = new File(storage, "Signal");
    File backups = new File(signal, "Backups");

    //noinspection ConstantConditions
    if (BuildConfig.APPLICATION_ID.startsWith(PRODUCTION_PACKAGE_ID + ".")) {
      backups = new File(backups, BuildConfig.APPLICATION_ID.substring(PRODUCTION_PACKAGE_ID.length() + 1));
    }

    return backups;
  }

=======
>>>>>>> 83146b3342 (Added extra options)
  @RequiresApi(24)
  public static @NonNull String getDisplayPath(@NonNull Context context, @NonNull Uri uri) {
    String lastPathSegment = Objects.requireNonNull(uri.getLastPathSegment());
    String backupVolume    = lastPathSegment.replaceFirst(":.*", "");
    String backupName      = lastPathSegment.replaceFirst(".*:", "");

    StorageManager      storageManager = ServiceUtil.getStorageManager(context);
    List<StorageVolume> storageVolumes = storageManager.getStorageVolumes();
    StorageVolume       storageVolume  = null;

    for (StorageVolume volume : storageVolumes) {
      if (Objects.equals(volume.getUuid(), backupVolume)) {
        storageVolume = volume;
        break;
      }
    }

    if (storageVolume == null) {
      return backupName;
    } else {
      return context.getString(R.string.StorageUtil__s_s, storageVolume.getDescription(context), backupName);
    }
  }

<<<<<<< HEAD
=======
  public static File getBackupCacheDirectory(Context context) {
    // JW: changed.
    if (TextSecurePreferences.isBackupLocationRemovable(context)) {
      if (Build.VERSION.SDK_INT >= 19) {
        File[] directories = context.getExternalCacheDirs();

        if (directories != null) {
          File result = getNonEmulated(directories);
          if (result != null) return result;
        }
      }
    }
    return context.getExternalCacheDir();
  }

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
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of cdbbc46ede (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> cdbbc46ede (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of cdbbc46ede (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> cdbbc46ede (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======
  // JW: re-added
  private static @Nullable File getNonEmulated(File[] directories) {
    return Stream.of(directories)
            .withoutNulls()
            .filterNot(f -> f.getAbsolutePath().contains("emulated"))
            .limit(1)
            .findSingle()
            .orElse(null);
  }

>>>>>>> 83146b3342 (Added extra options)
  private static File getSignalStorageDir() throws NoExternalStorageException {
    final File storage = Environment.getExternalStorageDirectory();

    if (!storage.canWrite()) {
      throw new NoExternalStorageException();
    }

    return storage;
  }

  public static boolean canWriteInSignalStorageDir() {
    File storage;

    try {
      storage = getSignalStorageDir();
    } catch (NoExternalStorageException e) {
      return false;
    }

    return storage.canWrite();
  }

  public static boolean canWriteToMediaStore() {
    return Build.VERSION.SDK_INT > 28 ||
           Permissions.hasAll(AppDependencies.getApplication(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
  }

  public static boolean canReadAnyFromMediaStore() {
    return Permissions.hasAny(AppDependencies.getApplication(), PermissionCompat.forImagesAndVideos());
  }

  public static boolean canOnlyReadSelectedMediaStore() {
    return Build.VERSION.SDK_INT >= 34 &&
           Permissions.hasAll(AppDependencies.getApplication(), Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED) &&
           !Permissions.hasAny(AppDependencies.getApplication(), Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_MEDIA_VIDEO);
  }

  public static boolean canReadAllFromMediaStore() {
    return Permissions.hasAll(AppDependencies.getApplication(), PermissionCompat.forImagesAndVideos());
  }

  public static @NonNull Uri getVideoUri() {
    return MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
  }

  public static @NonNull Uri getAudioUri() {
    return MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
  }

  public static @NonNull Uri getImageUri() {
    return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
  }

  public static @NonNull Uri getDownloadUri() {
    if (Build.VERSION.SDK_INT < 29) {
      return getLegacyUri(Environment.DIRECTORY_DOWNLOADS);
    } else {
      return MediaStore.Downloads.EXTERNAL_CONTENT_URI;
    }
  }

  public static @NonNull Uri getLegacyUri(@NonNull String directory) {
    return Uri.fromFile(Environment.getExternalStoragePublicDirectory(directory));
  }

  public static @Nullable String getCleanFileName(@Nullable String fileName) {
    if (fileName == null) return null;

    fileName = fileName.replace('\u202D', '\uFFFD');
    fileName = fileName.replace('\u202E', '\uFFFD');

    return fileName;
  }
}
