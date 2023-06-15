package org.thoughtcrime.securesms.components.location;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.signal.core.util.concurrent.ListenableFuture;
import org.signal.core.util.concurrent.SettableFuture;
import org.thoughtcrime.securesms.R;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
||||||| parent of 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of cdbbc46ede (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> cdbbc46ede (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 664145031f (Added extra options)
import org.thoughtcrime.securesms.util.concurrent.ListenableFuture;
import org.thoughtcrime.securesms.util.concurrent.SettableFuture;
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
import org.thoughtcrime.securesms.util.concurrent.ListenableFuture;
import org.thoughtcrime.securesms.util.concurrent.SettableFuture;
>>>>>>> 32b4182676 (Added extra options)

import java.util.concurrent.ExecutionException;
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
||||||| parent of 775ec008cc (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
import org.thoughtcrime.securesms.dependencies.ApplicationDependencies; // JW: added
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 7fa5495175 (Added extra options)
import org.thoughtcrime.securesms.util.concurrent.ListenableFuture;
import org.thoughtcrime.securesms.util.concurrent.SettableFuture;
>>>>>>> 66c339aa35 (Added extra options)

public class SignalMapView extends LinearLayout {

  private MapView   mapView;
  private ImageView imageView;
  private TextView  textView;

  public SignalMapView(Context context) {
    this(context, null);
  }

  public SignalMapView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initialize(context);
  }

  public SignalMapView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initialize(context);
  }

  private void initialize(Context context) {
    setOrientation(LinearLayout.VERTICAL);
    LayoutInflater.from(context).inflate(R.layout.signal_map_view, this, true);

    this.mapView   = findViewById(R.id.map_view);
    this.imageView = findViewById(R.id.image_view);
    this.textView  = findViewById(R.id.address_view);
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
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

||||||| parent of 32b4182676 (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of cdbbc46ede (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> cdbbc46ede (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of 83146b3342 (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 83146b3342 (Added extra options)
||||||| parent of 6eb21e79f1 (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 6eb21e79f1 (Added extra options)
||||||| parent of 39e2fc1d92 (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 39e2fc1d92 (Added extra options)
||||||| parent of 20d7d2eb03 (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 20d7d2eb03 (Added extra options)
||||||| parent of 6b57469a94 (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 6b57469a94 (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
  // JW: set the maptype
  public void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
=======
  // JW: set the maptype
  public static void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(ApplicationDependencies.getApplication());

    if (googleMap != null) {
      if      (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

>>>>>>> 664145031f (Added extra options)
  public ListenableFuture<Bitmap> display(final SignalPlace place) {
    final SettableFuture<Bitmap> future = new SettableFuture<>();

    this.imageView.setVisibility(View.GONE);
    this.textView.setText(place.getDescription());
    snapshot(place, mapView).addListener(new ListenableFuture.Listener<Bitmap>() {
      @Override
<<<<<<< HEAD
      public void onSuccess(Bitmap result) {
        future.set(result);
        imageView.setImageBitmap(result);
        imageView.setVisibility(View.VISIBLE);
      }

      @Override
      public void onFailure(ExecutionException e) {
        future.setException(e);
=======
      public void onMapReady(final GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLong(), 13));
        googleMap.addMarker(new MarkerOptions().position(place.getLatLong()));
        googleMap.setBuildingsEnabled(true);
        //googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        setGoogleMapType(googleMap); // JW: set maptype
        googleMap.getUiSettings().setAllGesturesEnabled(false);
        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
          @Override
          public void onMapLoaded() {
            googleMap.snapshot(new GoogleMap.SnapshotReadyCallback() {
              @Override
              public void onSnapshotReady(Bitmap bitmap) {
                future.set(bitmap);
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
                mapView.setVisibility(View.GONE);
                mapView.onPause();
                mapView.onDestroy();
              }
            });
          }
        });
>>>>>>> 66c339aa35 (Added extra options)
      }
    });

    return future;
  }

  public static ListenableFuture<Bitmap> snapshot(final LatLng place, @NonNull final MapView mapView) {
    final SettableFuture<Bitmap> future = new SettableFuture<>();
    mapView.onCreate(null);
    mapView.onStart();
    mapView.onResume();

    mapView.setVisibility(View.VISIBLE);

    mapView.getMapAsync(googleMap -> {
      googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 13));
      googleMap.addMarker(new MarkerOptions().position(place));
      googleMap.setBuildingsEnabled(true);
      //googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
      setGoogleMapType(googleMap); // JW: set maptype
      googleMap.getUiSettings().setAllGesturesEnabled(false);
      googleMap.setOnMapLoadedCallback(() -> googleMap.snapshot(bitmap -> {
        future.set(bitmap);
        mapView.setVisibility(View.GONE);
        mapView.onPause();
        mapView.onStop();
        mapView.onDestroy();
      }));
    });

    return future;
  }
  public static ListenableFuture<Bitmap> snapshot(final SignalPlace place, @NonNull final MapView mapView) {
    return snapshot(place.getLatLong(), mapView);
  }

}
