package org.thoughtcrime.securesms.maps;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button; // JW

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;

import org.signal.core.util.concurrent.ListenableFuture;
import org.signal.core.util.logging.Log;
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
import org.thoughtcrime.securesms.components.location.SignalMapView;
import org.thoughtcrime.securesms.providers.BlobProvider;
import org.thoughtcrime.securesms.util.BitmapUtil;
||||||| parent of 32b4182676 (Added extra options)
import org.thoughtcrime.securesms.components.location.SignalMapView;
import org.thoughtcrime.securesms.providers.BlobProvider;
import org.thoughtcrime.securesms.util.BitmapUtil;
=======
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
import org.thoughtcrime.securesms.components.location.SignalMapView;
import org.thoughtcrime.securesms.providers.BlobProvider;
import org.thoughtcrime.securesms.util.BitmapUtil;
=======
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
import org.thoughtcrime.securesms.components.location.SignalMapView;
import org.thoughtcrime.securesms.providers.BlobProvider;
import org.thoughtcrime.securesms.util.BitmapUtil;
=======
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
import org.thoughtcrime.securesms.components.location.SignalMapView;
import org.thoughtcrime.securesms.providers.BlobProvider;
import org.thoughtcrime.securesms.util.BitmapUtil;
=======
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
import org.thoughtcrime.securesms.components.location.SignalMapView;
import org.thoughtcrime.securesms.providers.BlobProvider;
import org.thoughtcrime.securesms.util.BitmapUtil;
=======
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
import org.thoughtcrime.securesms.components.location.SignalMapView;
import org.thoughtcrime.securesms.providers.BlobProvider;
import org.thoughtcrime.securesms.util.BitmapUtil;
=======
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
import org.thoughtcrime.securesms.components.location.SignalMapView;
import org.thoughtcrime.securesms.providers.BlobProvider;
import org.thoughtcrime.securesms.util.BitmapUtil;
=======
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
import org.thoughtcrime.securesms.components.location.SignalMapView;
import org.thoughtcrime.securesms.providers.BlobProvider;
import org.thoughtcrime.securesms.util.BitmapUtil;
=======
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of cdbbc46ede (Added extra options)
import org.thoughtcrime.securesms.components.location.SignalMapView;
import org.thoughtcrime.securesms.providers.BlobProvider;
import org.thoughtcrime.securesms.util.BitmapUtil;
=======
>>>>>>> cdbbc46ede (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
import org.thoughtcrime.securesms.components.location.SignalMapView;
import org.thoughtcrime.securesms.providers.BlobProvider;
import org.thoughtcrime.securesms.util.BitmapUtil;
=======
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
import org.thoughtcrime.securesms.components.location.SignalMapView;
import org.thoughtcrime.securesms.providers.BlobProvider;
import org.thoughtcrime.securesms.util.BitmapUtil;
=======
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
import org.thoughtcrime.securesms.components.location.SignalMapView;
import org.thoughtcrime.securesms.providers.BlobProvider;
import org.thoughtcrime.securesms.util.BitmapUtil;
=======
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
import org.thoughtcrime.securesms.components.location.SignalMapView;
import org.thoughtcrime.securesms.providers.BlobProvider;
import org.thoughtcrime.securesms.util.BitmapUtil;
=======
>>>>>>> 01ee01cd2c (Added extra options)
import org.thoughtcrime.securesms.util.DynamicNoActionBarTheme;
import org.thoughtcrime.securesms.util.DynamicTheme;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import org.thoughtcrime.securesms.util.MediaUtil;
import org.thoughtcrime.securesms.util.views.SimpleProgressDialog;
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of 701d234159 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 701d234159 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
import org.thoughtcrime.securesms.util.MediaUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
import org.thoughtcrime.securesms.util.concurrent.ListenableFuture;
import org.thoughtcrime.securesms.util.views.SimpleProgressDialog;
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
import org.thoughtcrime.securesms.util.MediaUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
import org.thoughtcrime.securesms.util.concurrent.ListenableFuture;
import org.thoughtcrime.securesms.util.views.SimpleProgressDialog;
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
import org.thoughtcrime.securesms.util.MediaUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
import org.thoughtcrime.securesms.util.concurrent.ListenableFuture;
import org.thoughtcrime.securesms.util.views.SimpleProgressDialog;
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
import org.thoughtcrime.securesms.util.MediaUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
import org.thoughtcrime.securesms.util.concurrent.ListenableFuture;
import org.thoughtcrime.securesms.util.views.SimpleProgressDialog;
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 01ee01cd2c (Added extra options)
||||||| parent of 76bcf0c877 (Added extra options)
import org.thoughtcrime.securesms.util.MediaUtil;
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
import org.thoughtcrime.securesms.util.concurrent.ListenableFuture;
import org.thoughtcrime.securesms.util.views.SimpleProgressDialog;
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 76bcf0c877 (Added extra options)
||||||| parent of 42175ada7f (Added extra options)
import org.thoughtcrime.securesms.util.MediaUtil;
import org.thoughtcrime.securesms.util.concurrent.ListenableFuture;
import org.thoughtcrime.securesms.util.views.SimpleProgressDialog;
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 42175ada7f (Added extra options)
||||||| parent of c58d43568c (Added extra options)
import org.thoughtcrime.securesms.util.MediaUtil;
import org.thoughtcrime.securesms.util.concurrent.ListenableFuture;
import org.thoughtcrime.securesms.util.views.SimpleProgressDialog;
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> c58d43568c (Added extra options)
||||||| parent of 17c88722b3 (Added extra options)
import org.thoughtcrime.securesms.util.MediaUtil;
import org.thoughtcrime.securesms.util.concurrent.ListenableFuture;
import org.thoughtcrime.securesms.util.views.SimpleProgressDialog;
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 17c88722b3 (Added extra options)
||||||| parent of cdbbc46ede (Added extra options)
import org.thoughtcrime.securesms.util.MediaUtil;
import org.thoughtcrime.securesms.util.concurrent.ListenableFuture;
import org.thoughtcrime.securesms.util.views.SimpleProgressDialog;
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> cdbbc46ede (Added extra options)
||||||| parent of 66c339aa35 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 66c339aa35 (Added extra options)
||||||| parent of 775ec008cc (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 775ec008cc (Added extra options)
||||||| parent of 6d8fef5835 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 6d8fef5835 (Added extra options)
||||||| parent of 246bbae757 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 246bbae757 (Added extra options)
||||||| parent of c5d82267d1 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> c5d82267d1 (Added extra options)
||||||| parent of 19863d0faa (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 19863d0faa (Added extra options)
||||||| parent of 55729c14e3 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 55729c14e3 (Added extra options)
||||||| parent of f050803628 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> f050803628 (Added extra options)
||||||| parent of 69c4403d63 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 69c4403d63 (Added extra options)
||||||| parent of 7d4bd94d26 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 7d4bd94d26 (Added extra options)
||||||| parent of efc40a1af7 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> efc40a1af7 (Added extra options)
||||||| parent of 36da7332d2 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 36da7332d2 (Added extra options)
||||||| parent of e4396c39f9 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> e4396c39f9 (Added extra options)
||||||| parent of e64c4c41bb (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> e64c4c41bb (Added extra options)
||||||| parent of e26890a182 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> e26890a182 (Added extra options)
||||||| parent of f611d03385 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> f611d03385 (Added extra options)
||||||| parent of 8a72cb26f4 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 8a72cb26f4 (Added extra options)
||||||| parent of 81d0aef821 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 81d0aef821 (Added extra options)
||||||| parent of 7fa5495175 (Added extra options)
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 7fa5495175 (Added extra options)
||||||| parent of 32b4182676 (Added extra options)
import org.thoughtcrime.securesms.util.MediaUtil;
import org.thoughtcrime.securesms.util.concurrent.ListenableFuture;
import org.thoughtcrime.securesms.util.views.SimpleProgressDialog;
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 32b4182676 (Added extra options)
||||||| parent of d8cd38511b (Added extra options)
import org.thoughtcrime.securesms.util.MediaUtil;
import org.thoughtcrime.securesms.util.concurrent.ListenableFuture;
import org.thoughtcrime.securesms.util.views.SimpleProgressDialog;
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> d8cd38511b (Added extra options)
||||||| parent of 664145031f (Added extra options)
import org.thoughtcrime.securesms.util.MediaUtil;
import org.thoughtcrime.securesms.util.concurrent.ListenableFuture;
import org.thoughtcrime.securesms.util.views.SimpleProgressDialog;
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 664145031f (Added extra options)
||||||| parent of 01ee01cd2c (Added extra options)
import org.thoughtcrime.securesms.util.MediaUtil;
import org.thoughtcrime.securesms.util.concurrent.ListenableFuture;
import org.thoughtcrime.securesms.util.views.SimpleProgressDialog;
=======
import org.thoughtcrime.securesms.util.TextSecurePreferences; // JW: added
>>>>>>> 01ee01cd2c (Added extra options)

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Allows selection of an address from a google map.
 * <p>
 * Based on https://github.com/suchoX/PlacePicker
 */
public final class PlacePickerActivity extends AppCompatActivity {

  private static final String TAG = Log.tag(PlacePickerActivity.class);

  // If it cannot load location for any reason, it defaults to the prime meridian.
  private static final LatLng PRIME_MERIDIAN = new LatLng(51.4779, -0.0015);
  private static final String ADDRESS_INTENT = "ADDRESS";
  private static final float  ZOOM           = 17.0f;

  private static final int                   ANIMATION_DURATION     = 250;
  private static final OvershootInterpolator OVERSHOOT_INTERPOLATOR = new OvershootInterpolator();
  public  static final String                KEY_CHAT_COLOR         = "chat_color";

  private final DynamicTheme dynamicTheme = new DynamicNoActionBarTheme();

  private SingleAddressBottomSheet bottomSheet;
  private Address                  currentAddress;
  private LatLng                   initialLocation;
  private LatLng                   currentLocation = new LatLng(0, 0);
  private AddressLookup            addressLookup;
  private GoogleMap                googleMap;
  // JW: added buttons
  private Button                   btnMapTypeNormal;
  private Button                   btnMapTypeSatellite;
  private Button                   btnMapTypeTerrain;

  public static void startActivityForResultAtCurrentLocation(@NonNull Fragment fragment, int requestCode, @ColorInt int chatColor) {
    fragment.startActivityForResult(new Intent(fragment.requireActivity(), PlacePickerActivity.class).putExtra(KEY_CHAT_COLOR, chatColor), requestCode);
  }

  public static AddressData addressFromData(@NonNull Intent data) {
    return data.getParcelableExtra(ADDRESS_INTENT);
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    dynamicTheme.onCreate(this);
    
    setContentView(R.layout.activity_place_picker);

    bottomSheet      = findViewById(R.id.bottom_sheet);
    View markerImage = findViewById(R.id.marker_image_view);
    View fab         = findViewById(R.id.place_chosen_button);
    // JW: add maptype buttons
    btnMapTypeNormal    = findViewById(R.id.btnMapTypeNormal);
    btnMapTypeSatellite = findViewById(R.id.btnMapTypeSatellite);
    btnMapTypeTerrain   = findViewById(R.id.btnMapTypeTerrain);

    ViewCompat.setBackgroundTintList(fab, ColorStateList.valueOf(getIntent().getIntExtra(KEY_CHAT_COLOR, Color.RED)));
    fab.setOnClickListener(v -> finishWithAddress());

    // JW: button event handlers
    btnMapTypeNormal.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(@NonNull View v) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        TextSecurePreferences.setGoogleMapType(getApplicationContext(), "normal");
      }
    });

    btnMapTypeSatellite.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(@NonNull View v) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        TextSecurePreferences.setGoogleMapType(getApplicationContext(), "satellite");
      }
    });

    btnMapTypeTerrain.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(@NonNull View v) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        TextSecurePreferences.setGoogleMapType(getApplicationContext(), "terrain");
      }
    });

    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)   == PackageManager.PERMISSION_GRANTED ||
        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
    {
      new LocationRetriever(this, this, location -> {
        setInitialLocation(new LatLng(location.getLatitude(), location.getLongitude()));
      }, () -> {
        Log.w(TAG, "Failed to get location.");
        setInitialLocation(PRIME_MERIDIAN);
      });
    } else {
      Log.w(TAG, "No location permissions");
      setInitialLocation(PRIME_MERIDIAN);
    }

    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
    if (mapFragment == null) throw new AssertionError("No map fragment");

    mapFragment.getMapAsync(googleMap -> {
      setMap(googleMap);
      if (DynamicTheme.isDarkTheme(this)) {
        try {
          boolean success = googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style));

          if (!success) {
            Log.e(TAG, "Style parsing failed.");
          }
        } catch (Resources.NotFoundException e) {
          Log.e(TAG, "Can't find style. Error: ", e);
        }
      }

      enableMyLocationButtonIfHaveThePermission(googleMap);

      googleMap.setOnCameraMoveStartedListener(i -> {
        markerImage.animate()
                   .translationY(-75f)
                   .setInterpolator(OVERSHOOT_INTERPOLATOR)
                   .setDuration(ANIMATION_DURATION)
                   .start();

        bottomSheet.hide();
      });

      googleMap.setOnCameraIdleListener(() -> {
        markerImage.animate()
                   .translationY(0f)
                   .setInterpolator(OVERSHOOT_INTERPOLATOR)
                   .setDuration(ANIMATION_DURATION)
                   .start();

        setCurrentLocation(googleMap.getCameraPosition().target);
      });
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    dynamicTheme.onResume(this);
  }

  private void setInitialLocation(@NonNull LatLng latLng) {
    initialLocation = latLng;

    moveMapToInitialIfPossible();
  }

  private void setMap(GoogleMap googleMap) {
    this.googleMap = googleMap;
    // JW: set maptype
    if (googleMap != null) {
      setGoogleMapType(googleMap);
    } else {
      // In case there is no Google maps installed:
      btnMapTypeNormal.setVisibility(View.GONE);
      btnMapTypeSatellite.setVisibility(View.GONE);
      btnMapTypeTerrain.setVisibility(View.GONE);
    }

    moveMapToInitialIfPossible();
  }

  // JW: set the maptype
  private void setGoogleMapType(GoogleMap googleMap) {
    String mapType = TextSecurePreferences.getGoogleMapType(getApplicationContext());

    if (googleMap != null) {
           if (mapType.equals("hybrid"))    { googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
      else if (mapType.equals("satellite")) { googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
      else if (mapType.equals("terrain"))   { googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
      else if (mapType.equals("none"))      { googleMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
      else                                  { googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    }
  }

  private void moveMapToInitialIfPossible() {
    if (initialLocation != null && googleMap != null) {
      Log.d(TAG, "Moving map to initial location");
      googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, ZOOM));
      setCurrentLocation(initialLocation);
    }
  }

  private void setCurrentLocation(LatLng location) {
    currentLocation = location;
    bottomSheet.showLoading();
    lookupAddress(location);
  }

  private void finishWithAddress() {
    Intent      returnIntent = new Intent();
    String      address      = currentAddress != null && currentAddress.getAddressLine(0) != null ? currentAddress.getAddressLine(0) : "";
    AddressData addressData  = new AddressData(currentLocation.latitude, currentLocation.longitude, address);

    returnIntent.putExtra(ADDRESS_INTENT, addressData);
    setResult(RESULT_OK, returnIntent);
    finish();
  }

  private void enableMyLocationButtonIfHaveThePermission(GoogleMap googleMap) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
        checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)   == PackageManager.PERMISSION_GRANTED ||
        checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
    {
      googleMap.setMyLocationEnabled(true);
    }
  }

  private void lookupAddress(@Nullable LatLng target) {
    if (addressLookup != null) {
      addressLookup.cancel(true);
    }
    addressLookup = new AddressLookup();
    addressLookup.execute(target);
  }

  @Override
  protected void onPause() {
    super.onPause();
    if (addressLookup != null) {
      addressLookup.cancel(true);
    }
  }

  @SuppressLint("StaticFieldLeak")
  private class AddressLookup extends AsyncTask<LatLng, Void, Address> {

    private final String TAG = Log.tag(AddressLookup.class);
    private final Geocoder geocoder;

    AddressLookup() {
      geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
    }

    @Override
    protected Address doInBackground(LatLng... latLngs) {
      if (latLngs.length == 0) return null;
      LatLng latLng = latLngs[0];
      if (latLng == null) return null;
      try {
        List<Address> result = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
        return !result.isEmpty() ? result.get(0) : null;
      } catch (IOException e) {
        Log.w(TAG, "Failed to get address from location", e);
        return null;
      }
    }

    @Override
    protected void onPostExecute(@Nullable Address address) {
      currentAddress = address;
      if (address != null) {
        bottomSheet.showResult(address.getLatitude(), address.getLongitude(), addressToShortString(address), addressToString(address));
      } else {
        bottomSheet.hide();
      }
    }
  }

  private static @NonNull String addressToString(@Nullable Address address) {
    return address != null ? address.getAddressLine(0) : "";
  }

  private static @NonNull String addressToShortString(@Nullable Address address) {
    if (address == null) return "";

    String   addressLine = address.getAddressLine(0);
    String[] split       = addressLine.split(",");

    if (split.length >= 3) {
      return split[1].trim() + ", " + split[2].trim();
    } else if (split.length == 2) {
      return split[1].trim();
    } else return split[0].trim();
  }
}
