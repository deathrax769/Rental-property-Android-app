package com.example.sturent1;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentLocationMarker;
    public static final int REQUEST_LOCATION_CODE = 99;
    Button button4;
    Button menubutton;

    ArrayList<LatLng> arrayList = new ArrayList<LatLng>();
    LatLng sydney = new LatLng(-34, 151);
    LatLng Tamworth = new LatLng(-31, 150);
    LatLng Newcastle = new LatLng(-32, 151);
    LatLng Brisbane = new LatLng(-27, 153);
    LatLng Dubbo = new LatLng(-32, 148);

    ArrayList<String> title = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        //Initialize and assign varialbles
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        //set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.map);

        //Perform ItemSelectorListener
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.map:
                        break;

                    case R.id.listView:
                        Intent intent1 = new Intent(MapsActivity.this, ListView.class);
                        startActivity(intent1);
                        break;

                    case R.id.addProperty:
                        Intent intent2 = new Intent(MapsActivity.this, AddProperty.class);
                        startActivity(intent2);
                        break;
                    case R.id.studentProfile:
                        Intent intent3 = new Intent(MapsActivity.this, StudentProfile.class);
                        startActivity(intent3);
                        break;
                }
            }
        });

        menubutton = (Button) findViewById(R.id.menubutton);
        menubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuButton();
            }
        });

        arrayList.add(sydney);
        arrayList.add(Tamworth);
        arrayList.add(Newcastle);
        arrayList.add(Brisbane);
        arrayList.add(Dubbo);

        title.add("Sydney");
        title.add("Tamworth");
        title.add("Newcastle");
        title.add("Brisbane");
        title.add("Dubbo");



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        button4 = (Button) findViewById(R.id.button4);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MapsActivity.this, button4);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MapsActivity.this, "" + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });


    }

    public void openMenuButton(){
        Intent intent = new Intent(this,DetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){

            case REQUEST_LOCATION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

                        if (client == null){
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else {

                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
                }
                return;
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng itarsi = new LatLng(22, 77);
        LatLng jabalpur = new LatLng(23, 79);
        LatLng sehore = new LatLng(23, 77);
        LatLng mpnagar = new LatLng(23.2313, 77.4326);
        LatLng areracolony = new LatLng(23.2116, 77.4311);
        LatLng BU = new LatLng(23.196742, 77.449344);
        LatLng jahangirabad = new LatLng(23.2508, 77.4157);
        LatLng kolarroad = new LatLng(23.1719, 77.4169);
        LatLng shahpura = new LatLng(23.1901, 77.4243);
        LatLng ttnagar = new LatLng(23.2358, 77.3986);
        LatLng nehrunagar = new LatLng(23.2075, 77.3870);
        LatLng newmarket = new LatLng(23.2356, 77.4006);
        LatLng chunabhatti = new LatLng(23.2015, 77.4149);
        LatLng narayannagar = new LatLng(23.1998, 77.4465);
        LatLng sp = new LatLng(23.2000, 77.4465);

        mMap.addMarker(new MarkerOptions().position(itarsi).title("Marker in Itarsi")
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_baseline_accessibility_new_24)));
        mMap.addMarker(new MarkerOptions().position(jabalpur).title("Marker in Jabalpur")
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_baseline_accessibility_new_24)));
        mMap.addMarker(new MarkerOptions().position(sehore).title("Marker in Sehore")
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_baseline_accessibility_new_24)));
        mMap.addMarker(new MarkerOptions().position(mpnagar).title("PG in M.P Nagar")
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_sharp_home_24)));
        mMap.addMarker(new MarkerOptions().position(BU).title("Bungalow in BU")
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_baseline_house_24)));
        mMap.addMarker(new MarkerOptions().position(areracolony).title("Bungalow in Arera Colony")
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_baseline_house_24)));

        mMap.addMarker(new MarkerOptions().position(jahangirabad).title("Girl's House in Jahangirabad")
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_baseline_home_24)));

        mMap.addMarker(new MarkerOptions().position(kolarroad).title("PG in Kolar Road")
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_sharp_home_24)));

        mMap.addMarker(new MarkerOptions().position(shahpura).title("Bungalow in Shahpura")
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_baseline_house_24)));

        mMap.addMarker(new MarkerOptions().position(ttnagar).title("Bungalow in T.T Nagar")
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_baseline_house_24)));

        mMap.addMarker(new MarkerOptions().position(nehrunagar).title("Girl's House in Nehru Nagar")
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_baseline_home_24)));

        mMap.addMarker(new MarkerOptions().position(newmarket).title("PG in New Market")
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_sharp_home_24)));
        mMap.addMarker(new MarkerOptions().position(chunabhatti).title("Bungalow in Chunabhatti")
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_baseline_house_24)));
        mMap.addMarker(new MarkerOptions().position(narayannagar).title("Bungalow in Narayan Nagar")
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_baseline_house_24)));
        mMap.addMarker(new MarkerOptions().position(sp).title("PG in Surendra Palace")
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_sharp_home_24)));





        for (int i=0; i < arrayList.size();i++) {

            for (int j =0; j<title.size();j++) {

                mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title(String.valueOf(title.get(i))));
            }

        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String markertitle = marker.getTitle();

                Intent i = new Intent(MapsActivity.this, DetailsActivity.class);
                i.putExtra("title", markertitle);
                startActivity(i);


                return false;
            }
        });




        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);

        }


    }
    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable= ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds( 0, 0, vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap= Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    protected synchronized void buildGoogleApiClient() {
        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        client.connect();
    }
    @Override
    public void onLocationChanged(@NonNull Location location) {
        lastLocation = location;

        if (currentLocationMarker != null) {
            currentLocationMarker.remove();
        }

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Loaction");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        currentLocationMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(10));
        if (client != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
        }


    }

    public void onClick(View v) {

        if (v.getId() == R.id.B_search) {
            EditText tf_location = (EditText) findViewById(R.id.TF_location);
            String location = tf_location.getText().toString();
            List<Address> addressList = null;
            MarkerOptions mo = new MarkerOptions();
            if ( !location.equals("")) {
                Geocoder geocoder = new Geocoder(this);
                try {
                    addressList = geocoder.getFromLocationName(location, 5);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for (int i = 0;i < addressList.size(); i++) {
                    Address myAddress = addressList.get(i);
                    LatLng latlng = new LatLng(myAddress.getLatitude(), myAddress.getLongitude());
                    mo.position(latlng);
                    mo.title("Ur search result");
                    mMap.addMarker(mo);
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latlng));

                }


            }

        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }

    }

    public boolean checkLocationPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);

            }
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            }
            return false;
        }
        else
            return true;

    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}