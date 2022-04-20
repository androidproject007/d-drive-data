package com.schoolmanagement.Transport.Fragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.google.android.gms.maps.model.PolylineOptions;
import com.schoolmanagement.Other.Const;
import com.schoolmanagement.Other.Prefs;
import com.schoolmanagement.Parents.MAP.AppLocationService;
import com.schoolmanagement.Parents.MAP.DirectionParse;
import com.schoolmanagement.Parents.MAP.GPSTracker;
import com.schoolmanagement.Parents.MAP.LocationAddress;
import com.schoolmanagement.R;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrasportMapNavigationFragment extends Fragment  implements OnMapReadyCallback {

    RelativeLayout header;
    DrawerLayout drawer;
    int Select_inner;
    Activity activity;
    FragmentManager fragmentManager;
    private GoogleMap mgoogleMap;
    private Double lat;
    private Double lng;
    ImageView iv_myLocation;

    public TrasportMapNavigationFragment(Activity context, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer, int Select_inner) {
        this.activity = context;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
        this.Select_inner = Select_inner;
    }

    public TrasportMapNavigationFragment(Activity homeTransportActivity, FragmentManager supportFragmentManager) {
        this.activity = homeTransportActivity;
        this.fragmentManager = supportFragmentManager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.transport_map_navigation, container, false);

//        if(Select_inner==0){
//            CustomHeaderWithRelative.setInnerToolbar(getActivity(), header, "Attandance");
//        }else {
        //CustomHeaderWithRelative.setOuter(getActivity(), drawer, header, "Transport");

         iv_myLocation = (ImageView) rootView.findViewById(R.id.iv_myLocation);
         initMap();
//        Location location = appLocationService.getLocation(LocationManager.GPS_PROVIDER);
//
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
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googleMap);
                mapFragment.getMapAsync(TrasportMapNavigationFragment.this);
            //SupportMapFragment fragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.googleMap);
//            if (fragment != null) {
//                fragment.getMapAsync(this);
//            }
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
                        .zoom(12)
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

      LatLng  latLng = new LatLng(23.0437731,72.6433566);
        googleMap.addPolyline(new PolylineOptions()
                .add(myLocation,
                        latLng)
                .width(5)
                .color(Color.BLUE));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mgoogleMap!=null){
            mgoogleMap.clear();
          //  mgoogleMap==null;
        }

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


    private String getRequesturl(LatLng c_latLng, LatLng latLng) {
        String str_org = "origin="+c_latLng.latitude+","+c_latLng.longitude;
        Log.i("Origin:",str_org);

        String str_dest = "destination="+latLng.latitude+","+latLng.longitude;
        Log.i("Destination:",str_dest);

        String sensor = "sensor=false";

        String mode = "mode=driving";

        String param = str_org+"&"+str_dest+"&"+sensor+"&"+mode;
        Log.i("Param:",param);

        String output = "json";

        //String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+param+"&key="+"AIzaSyAzsaYsOeEnry_dTlo3p8EIWgRIG4Lgeg0";
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+param+"&key="+"AIzaSyDSzgXgOX9M0Q7SL1oR74JG6USU-9B_l9s";
        Log.i("FINAL URL:",url);
        return url;
    }

    public String requestDirection(String requrl) throws IOException {
        String responseString = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try{
            URL url = new URL(requrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();
            String line = "";

            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line);
            }

            responseString = stringBuffer.toString();
            bufferedReader.close();
            inputStreamReader.close();



        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(inputStream!= null)
            {
                inputStream.close();
            }
            httpURLConnection.disconnect();
        }
        return responseString;
    }



    public class TaskRequestDirection extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... strings) {
            String responseString = "";
            try {
                responseString = requestDirection(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return responseString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            TaskParse taskParse = new TaskParse();
            taskParse.execute(s);
        }
    }

    public class TaskParse extends AsyncTask<String, Void, List<List<HashMap<String, String>>>>
    {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings) {
            JSONObject jsonObject = null;
            List<List<HashMap<String, String>>> routes = null;
            try{
                jsonObject = new JSONObject(strings[0]);
                DirectionParse directionParse = new DirectionParse();
                routes = directionParse.parse(jsonObject);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists) {
            super.onPostExecute(lists);
            ArrayList points = null;
            PolylineOptions polylineOptions = null;
            for (List<HashMap<String, String>> paths:lists)
            {
                points = new ArrayList();
                polylineOptions = new PolylineOptions();

                for(HashMap<String, String> point : paths)
                {
                    double lat = Double.parseDouble(point.get("lat"));
                    double lon = Double.parseDouble(point.get("lon"));

                    points.add(new LatLng(lat,lon));
                }

                polylineOptions.addAll(points);
                polylineOptions.width(15);
                polylineOptions.color(Color.BLUE);
                polylineOptions.geodesic(true);
            }
            mgoogleMap.addPolyline(polylineOptions);
        }
    }

}
