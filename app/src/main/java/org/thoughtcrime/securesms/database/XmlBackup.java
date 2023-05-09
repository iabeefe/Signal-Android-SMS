package org.thoughtcrime.securesms.database;

import android.text.TextUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlBackup {

  private static final String PROTOCOL       = "protocol";
  private static final String ADDRESS        = "address";
  private static final String CONTACT_NAME   = "contact_name";
  private static final String DATE           = "date";
  private static final String READABLE_DATE  = "readable_date";
  private static final String TYPE           = "type";
  private static final String SUBJECT        = "subject";
  private static final String BODY           = "body";
  private static final String SERVICE_CENTER = "service_center";
  private static final String READ           = "read";
  private static final String STATUS         = "status";
  private static final String TOA            = "toa";
  private static final String SC_TOA         = "sc_toa";
  private static final String LOCKED         = "locked";
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
||||||| parent of 66c339aa35 (Added extra options)
=======
  private static final String TRANSPORT      = "transport"; // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
  private static final String TRANSPORT      = "transport"; // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
  private static final String TRANSPORT      = "transport"; // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
  private static final String TRANSPORT      = "transport"; // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  private static final String TRANSPORT      = "transport"; // JW: added
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of c5d82267d1 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======
  private static final String TRANSPORT      = "transport"; // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
  private static final String TRANSPORT      = "transport"; // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
  private static final String TRANSPORT      = "transport"; // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
  private static final String TRANSPORT      = "transport"; // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
  private static final String TRANSPORT      = "transport"; // JW: added
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of c5d82267d1 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
=======
  private static final String TRANSPORT      = "transport";
  private static final String RECIPIENT      = "torecipient";
>>>>>>> 701d234159 (Added extra options)

  private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");

  private final XmlPullParser parser;

  public XmlBackup(String path) throws XmlPullParserException, FileNotFoundException {
    this.parser = XmlPullParserFactory.newInstance().newPullParser();
    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
    parser.setInput(new FileInputStream(path), null);
  }

  public XmlBackupItem getNext() throws IOException, XmlPullParserException {
    while (parser.next() != XmlPullParser.END_DOCUMENT) {
      if (parser.getEventType() != XmlPullParser.START_TAG) {
        continue;
      }

      String name = parser.getName();

      if (!name.equalsIgnoreCase("sms")) {
        continue;
      }

      int attributeCount = parser.getAttributeCount();

      if (attributeCount <= 0) {
        continue;
      }

      XmlBackupItem item = new XmlBackupItem();

      for (int i=0;i<attributeCount;i++) {
        String attributeName = parser.getAttributeName(i);

        if      (attributeName.equals(PROTOCOL      )) item.protocol      = Integer.parseInt(parser.getAttributeValue(i));
        else if (attributeName.equals(ADDRESS       )) item.address       = parser.getAttributeValue(i);
        else if (attributeName.equals(CONTACT_NAME  )) item.contactName   = parser.getAttributeValue(i);
        else if (attributeName.equals(DATE          )) item.date          = Long.parseLong(parser.getAttributeValue(i));
        else if (attributeName.equals(READABLE_DATE )) item.readableDate  = parser.getAttributeValue(i);
        else if (attributeName.equals(TYPE          )) item.type          = Integer.parseInt(parser.getAttributeValue(i));
        else if (attributeName.equals(SUBJECT       )) item.subject       = parser.getAttributeValue(i);
        else if (attributeName.equals(BODY          )) item.body          = parser.getAttributeValue(i);
        else if (attributeName.equals(SERVICE_CENTER)) item.serviceCenter = parser.getAttributeValue(i);
        else if (attributeName.equals(READ          )) item.read          = Integer.parseInt(parser.getAttributeValue(i));
        else if (attributeName.equals(STATUS        )) item.status        = Integer.parseInt(parser.getAttributeValue(i));
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
||||||| parent of 66c339aa35 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i); // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i); // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i); // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i); // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i); // JW: added
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of c5d82267d1 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i); // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i); // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i); // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i); // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i); // JW: added
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of c5d82267d1 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
=======
        else if (attributeName.equals(TRANSPORT     )) item.transport     = parser.getAttributeValue(i);
        else if (attributeName.equals(RECIPIENT     )) item.torecipient   = Long.parseLong(parser.getAttributeValue(i));
>>>>>>> 701d234159 (Added extra options)
      }
      return item;
    }
    return null;
  }

  public static class XmlBackupItem {
    private int    protocol;
    private String address;
    private String contactName;
    private long   date;
    private String readableDate;
    private int    type;
    private String subject;
    private String body;
    private String serviceCenter;
    private int    read;
    private int    status;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    private String transport;
    private long torecipient;
||||||| parent of 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
    private String transport; // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
    private String transport; // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
    private String transport; // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======
    private String transport; // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
    private String transport; // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
    private String transport; // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
    private String transport; // JW: added
>>>>>>> 246bbae757 (Added extra options)

=======
    private String transport; // JW: added

>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    private String transport; // JW: added

=======
    private String transport;
    private long torecipient;
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of c5d82267d1 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    private String transport; // JW: added

=======
    private String transport;
    private long torecipient;
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of c5d82267d1 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)

=======
    private String transport;
    private long torecipient;
>>>>>>> 701d234159 (Added extra options)
    public XmlBackupItem() {}

    public XmlBackupItem(int protocol, String address, String contactName, long date, int type,
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
||||||| parent of 66c339aa35 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
<<<<<<< HEAD
                         String transport) // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
<<<<<<< HEAD
                         String transport) // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport) // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport) // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
                         String transport) // JW: added
=======
                         String transport, long torecipient)
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of c5d82267d1 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport) // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport) // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport) // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport) // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
                         String transport) // JW: added
=======
                         String transport, long torecipient)
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of c5d82267d1 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
                         String subject, String body, String serviceCenter, int read, int status)
=======
                         String subject, String body, String serviceCenter, int read, int status,
                         String transport, long torecipient)
>>>>>>> 701d234159 (Added extra options)
    {
      this.protocol      = protocol;
      this.address       = address;
      this.contactName   = contactName;
      this.date          = date;
      this.readableDate  = dateFormatter.format(date);
      this.type          = type;
      this.subject       = subject;
      this.body          = body;
      this.serviceCenter = serviceCenter;
      this.read          = read;
      this.status        = status;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
      this.transport     = transport;
      this.torecipient   = torecipient;
||||||| parent of 66c339aa35 (Added extra options)
=======
      this.transport     = transport; // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
      this.transport     = transport; // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
      this.transport     = transport; // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
      this.transport     = transport; // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      this.transport     = transport; // JW: added
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of c5d82267d1 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======
      this.transport     = transport; // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
      this.transport     = transport; // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
      this.transport     = transport; // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
      this.transport     = transport; // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      this.transport     = transport; // JW: added
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of c5d82267d1 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
=======
      this.transport     = transport;
      this.torecipient   = torecipient;
>>>>>>> 701d234159 (Added extra options)
    }

    public int getProtocol() {
      return protocol;
    }

    public String getAddress() {
      return address;
    }

    public String getContactName() {
      return contactName;
    }

    public long getDate() {
      return date;
    }

    public String getReadableDate() {
      return readableDate;
    }

    public int getType() {
      return type;
    }

    public String getSubject() {
      return subject;
    }

    public String getBody() {
      return body;
    }

    public String getServiceCenter() {
      return serviceCenter;
    }

    public int getRead() {
      return read;
    }

    public int getStatus() {
      return status;
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

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
||||||| parent of 66c339aa35 (Added extra options)
=======

<<<<<<< HEAD
    public String getTransport() { return transport; } // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======

<<<<<<< HEAD
    public String getTransport() { return transport; } // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======

    public String getTransport() { return transport; } // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======

    public String getTransport() { return transport; } // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    public String getTransport() { return transport; } // JW: added
=======
    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of c5d82267d1 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======

    public String getTransport() { return transport; } // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======

    public String getTransport() { return transport; } // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======

    public String getTransport() { return transport; } // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======

    public String getTransport() { return transport; } // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
    public String getTransport() { return transport; } // JW: added
=======
    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of c5d82267d1 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
=======

    public String getTransport() { return transport; }
    public long getRecipient() { return torecipient; }
>>>>>>> 701d234159 (Added extra options)
  }

  public static class Writer {

    private static final String  XML_HEADER      = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>";
    private static final String  CREATED_BY      = "<!-- File Created By Signal -->";
    private static final String  OPEN_TAG_SMSES  = "<smses count=\"%d\">";
    private static final String  CLOSE_TAG_SMSES = "</smses>";
    private static final String  OPEN_TAG_SMS    = " <sms ";
    private static final String  CLOSE_EMPTYTAG  = "/>";
    private static final String  OPEN_ATTRIBUTE  = "=\"";
    private static final String  CLOSE_ATTRIBUTE = "\" ";

    private static final Pattern PATTERN         = Pattern.compile("[^\u0020-\uD7FF]");

    private final BufferedWriter bufferedWriter;

    public Writer(String path, int count) throws IOException {
      bufferedWriter = new BufferedWriter(new FileWriter(path, false));

      bufferedWriter.write(XML_HEADER);
      bufferedWriter.newLine();
      bufferedWriter.write(CREATED_BY);
      bufferedWriter.newLine();
      bufferedWriter.write(String.format(Locale.ROOT, OPEN_TAG_SMSES, count));
    }

    public void writeItem(XmlBackupItem item) throws IOException {
      StringBuilder stringBuilder = new StringBuilder();

      stringBuilder.append(OPEN_TAG_SMS);
      appendAttribute(stringBuilder, PROTOCOL, item.getProtocol());
      appendAttribute(stringBuilder, ADDRESS, escapeXML(item.getAddress()));
      appendAttribute(stringBuilder, CONTACT_NAME, escapeXML(item.getContactName()));
      appendAttribute(stringBuilder, DATE, item.getDate());
      appendAttribute(stringBuilder, READABLE_DATE, item.getReadableDate());
      appendAttribute(stringBuilder, TYPE, item.getType());
      appendAttribute(stringBuilder, SUBJECT, escapeXML(item.getSubject()));
      appendAttribute(stringBuilder, BODY, escapeXML(item.getBody()));
      appendAttribute(stringBuilder, TOA, "null");
      appendAttribute(stringBuilder, SC_TOA, "null");
      appendAttribute(stringBuilder, SERVICE_CENTER, item.getServiceCenter());
      appendAttribute(stringBuilder, READ, item.getRead());
      appendAttribute(stringBuilder, STATUS, item.getStatus());
      appendAttribute(stringBuilder, LOCKED, 0);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
||||||| parent of 66c339aa35 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport()); // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport()); // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport()); // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport()); // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport()); // JW: added
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of c5d82267d1 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport()); // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport()); // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport()); // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport()); // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of d983349636 (Bumped to upstream version 6.19.0.0-JW.)
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport()); // JW: added
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> d983349636 (Bumped to upstream version 6.19.0.0-JW.)
||||||| parent of c5d82267d1 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
=======
      appendAttribute(stringBuilder, TRANSPORT, item.getTransport());
      appendAttribute(stringBuilder, RECIPIENT, item.getRecipient());
>>>>>>> 701d234159 (Added extra options)
      stringBuilder.append(CLOSE_EMPTYTAG);

      bufferedWriter.newLine();
      bufferedWriter.write(stringBuilder.toString());
    }

    private <T> void appendAttribute(StringBuilder stringBuilder, String name, T value) {
      stringBuilder.append(name).append(OPEN_ATTRIBUTE).append(value).append(CLOSE_ATTRIBUTE);
    }

    public void close() throws IOException {
      bufferedWriter.newLine();
      bufferedWriter.write(CLOSE_TAG_SMSES);
      bufferedWriter.close();
    }

    private String escapeXML(String s) {
      if (TextUtils.isEmpty(s)) return s;

      Matcher matcher = PATTERN.matcher( s.replace("&",  "&amp;")
                                          .replace("<",  "&lt;")
                                          .replace(">",  "&gt;")
                                          .replace("\"", "&quot;")
                                          .replace("'",  "&apos;"));
      StringBuffer st = new StringBuffer();

      while (matcher.find()) {
        String escaped="";
        for (char ch: matcher.group(0).toCharArray()) {
          escaped += ("&#" + ((int) ch) + ";");
        }
        matcher.appendReplacement(st, escaped);
      }
      matcher.appendTail(st);
      return st.toString();
    }

  }
}
