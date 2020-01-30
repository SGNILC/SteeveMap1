package com.example.steevemap1;

import androidx.annotation.MainThread;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    FusedLocationProviderClient fL;
    double lon = 0;
    double lat = 0;
    LocationManager lm;
    LocationRequest lR;
    LocationCallback lC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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
        if (ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) +
                (ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.shouldShowRequestPermissionRationale(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
            ActivityCompat.shouldShowRequestPermissionRationale(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);

        } else {
            // Permission has already been granted

        }
        mMap = googleMap;

        fL = LocationServices.getFusedLocationProviderClient(this);
        fL.getLastLocation().addOnSuccessListener(MapsActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                lon = location.getLongitude();
                lat = location.getLatitude();


                if (location == null) {
                    lon = location.getLongitude();
                    lat = location.getLatitude();

//                    Toast.makeText(MapsActivity.this, "Your lon is " + l1 + " your lat is " + l2, Toast.LENGTH_LONG).show();


                } else {
                    String l1 = String.valueOf(lon);
                    String l2 = String.valueOf(lat);
                    lon = location.getLongitude();
                    lat = location.getLatitude();
                    Toast.makeText(MapsActivity.this, "Your lon is " + l1 + " your lat is " + l2, Toast.LENGTH_LONG).show();


                }

                LatLng lane = new LatLng(lon, lat);
                mMap.addMarker(new MarkerOptions().position(lane).title("Marker at Lane"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(lane));
            }
        });


    }
}
