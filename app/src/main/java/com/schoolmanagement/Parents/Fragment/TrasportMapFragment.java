package com.schoolmanagement.Parents.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.schoolmanagement.Other.Const;
import com.schoolmanagement.Other.CustomHeaderWithRelative;
import com.schoolmanagement.Other.Prefs;
import com.schoolmanagement.Parents.MAP.AppLocationService;
import com.schoolmanagement.Parents.MAP.GPSTracker;
import com.schoolmanagement.Parents.MAP.LocationAddress;
import com.schoolmanagement.R;

import java.util.List;

public class TrasportMapFragment extends Fragment  implements OnMapReadyCallback {

    RelativeLayout header;
    DrawerLayout drawer;
    String Select_inner;
    Activity activity;
    FragmentManager fragmentManager;
    private GoogleMap mgoogleMap;
    private Double lat;
    private Double lng;
    ImageView iv_myLocation;

    public TrasportMapFragment(Activity context, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer, String Select_inner) {
        this.activity = context;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
        this.Select_inner = Select_inner;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.parents_map_fragment, container, false);

        if(Select_inner=="0"){
            CustomHeaderWithRelative.setInnerToolbar(getActivity(), header, "Transport");
        }else {
            CustomHeaderWithRelative.setOuter(getActivity(), drawer, header, "Transport");
        }
        iv_myLocation = (ImageView) rootView.findViewById(R.id.iv_myLocation);

        initMap();

//        Location location = appLocationService.getLocation(LocationManager.GPS_PROVIDER);
//        if (location != null) {
//            double latitude = location.getLatitude();
//            double longitude = location.getLongitude();
//            getCompleteAddressString(latitude,longitude);
//
////                    Toast.makeText(Inquery_police_new.this, ""+latitude + longitude, Toast.LENGTH_SHORT).show();
//            //LocationAddress locationAddress = new LocationAddress();
//            // locationAddress.getAddressFromLocation(latitude, longitude,getApplicationContext(), new GeocoderHandler());
//        } else {
//            showSettingsAlert();
//        }
        return rootView;
    }



    private void initMap(){
        if(mgoogleMap == null){
            //SupportMapFragment fragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.googleMap);
            SupportMapFragment fragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.googleMap);
            if (fragment != null) {
                fragment.getMapAsync(this);
            }
        }
    }


    private void mapListener() {
        mgoogleMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {
//                mgoogleMap.getCameraPosition();
            }
        });

        mgoogleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
//                Utils.getInstance().d("onCameraMove");
            }
        });

        mgoogleMap.setOnCameraMoveCanceledListener(new GoogleMap.OnCameraMoveCanceledListener() {
            @Override
            public void onCameraMoveCanceled() {
//                Utils.getInstance().d("onCameraMoveCanceled");

            }
        });
        mgoogleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
//                Utils.getInstance().d("onCameraIdle");
            }
        });

        mgoogleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng latLng = new LatLng(lat, lng);
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(latLng)
                        .zoom(10)
                        .bearing(90)
                        .tilt(30)
                        .build();
                mgoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mgoogleMap = googleMap;
        mgoogleMap.getUiSettings().setMapToolbarEnabled(false);
        mgoogleMap.getUiSettings().setCompassEnabled(false);
        mgoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
//        if (ActivityCompat.checkSelfPermission(Inquery_police_new.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(Inquery_police_new.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
        mgoogleMap.setMyLocationEnabled(true);
        lat = 23.0312111;
        lng = 72.5606431;
        final GPSTracker gpsTracker = new GPSTracker(activity);
        if (gpsTracker.canGetLocation()) {
            Log.d("mytag", "in gps enabled");
            lat = gpsTracker.getLatitude();
            lng = gpsTracker.getLongitude();
            List<Address> addressList = null;
//            LocationAddress locationAddress = new LocationAddress();
//            locationAddress.getAddressFromLocation(lat, lng,
//                    getApplicationContext(), new GeocoderHandler());
            // getCompleteAddressString(lat,lng);
            Prefs.getPrefInstance().setValue(activity, Const.LAT, String.valueOf(lat));
            Prefs.getPrefInstance().setValue(activity, Const.LON, String.valueOf(lng));
            Log.d("mytag", "latlong" + lat + "  " + lng + " ");
        } else {
            gpsTracker.showSettingsAlert();
        }

        LatLng myLocation = new LatLng(lat, lng);
        String latlong = Prefs.getPrefInstance().getValue(activity, Const.LAT, "");
        String longl = Prefs.getPrefInstance().getValue(activity, Const.LON, "");
//        if (!latlong.isEmpty() && !longl.isEmpty()) {
//            if (CheckNetwork.isInternetAvailable(activity)) {
//                //  api();
//            } else {
//                Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            if (CheckNetwork.isInternetAvailable(activity)) {
//                //  api();
//            } else {
//                Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
//            }
//        }
//        if(marker != null){
//            marker.setPosition(myLocation);
//        } else {
//            marker = mgoogleMap.addMarker(new MarkerOptions().position(myLocation).title("My Location"));
//        }

        mgoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mgoogleMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
//                    mgoogleMap.animateCamera(CameraUpdateFactory.zoomTo(12));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(myLocation)
                .zoom(16)
                .bearing(90)
                .tilt(30)
                .build();
        mgoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mapListener();

        iv_myLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Location location = mgoogleMap.getMyLocation();
                LatLng latLng = new LatLng(lat, lng);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 18);
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(latLng)
                        .zoom(16)
                        .bearing(90)
                        .tilt(30)
                        .build();
                mgoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                LocationAddress locationAddress = new LocationAddress();

//                locationAddress.getAddressFromLocation(lat, lng,
//                        getApplicationContext(), new GeocoderHandler());
            }
        });

        MarkerOptions markerOpt = new MarkerOptions();
        markerOpt.position(new LatLng(Double.valueOf("23.0437731"), Double.valueOf("72.6433566")))
                .title("Location")
                .snippet("Nikol Ahmedabad")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus));
        mgoogleMap.addMarker(markerOpt);


        mgoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if (marker.getSnippet() != null) {
//                    Log.d("mytag", "useradd  marker.getSnippet();"+ marker.getSnippet());
                    new AlertDialog.Builder(activity)
                            .setCancelable(false)
                            .setMessage("Bus Location: Ahmedabad")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();


//                    pushFragment(new Fragment_Department(context, fragmentManager, lin_header, drawer, Integer.valueOf(marker.getSnippet())), "depart", true);
                    //call the intent here
                }
            }
        });

    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                activity);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        activity.startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }
}
