package sg.edu.rp.c346.demomap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);

                    LatLng CausewayPoint = new LatLng(1.436065, 103.786263);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(CausewayPoint, 18));



                    Marker cp = map.addMarker(new
                            MarkerOptions()
                            .position(CausewayPoint)
                            .title("Causeway Point")
                            .snippet("Shopping after class")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                }
            }
        });


        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);
        btn3 = (Button)findViewById(R.id.button3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                    UiSettings uiCompass = map.getUiSettings();
                    uiCompass.setCompassEnabled(true);

                    UiSettings uiZoom = map.getUiSettings();
                    uiZoom.setZoomControlsEnabled(true);

                    LatLng CausewayPoint = new LatLng(1.436065, 103.786263);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(CausewayPoint, 18));



                    Marker cp = map.addMarker(new
                            MarkerOptions()
                            .position(CausewayPoint)
                            .title("Causeway Point")
                            .snippet("Shopping after class")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

                    UiSettings uiCompass = map.getUiSettings();
                    uiCompass.setCompassEnabled(true);

                    UiSettings uiZoom = map.getUiSettings();
                    uiZoom.setZoomControlsEnabled(true);

                    int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION);

                    if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                        map.setMyLocationEnabled(true);
                    } else {
                        Log.e("GMap - Permission", "GPS access has not been granted");
                    }

                    LatLng home = new LatLng(1.397901, 103.7495409);

                    Marker house = map.addMarker(new
                            MarkerOptions()
                            .position(home)
                            .title("Home")
                            .snippet("Go home after class")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

                    UiSettings uiCompass = map.getUiSettings();
                    uiCompass.setCompassEnabled(true);

                    UiSettings uiZoom = map.getUiSettings();
                    uiZoom.setZoomControlsEnabled(true);

                    int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION);

                    if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                        map.setMyLocationEnabled(true);
                    } else {
                        Log.e("GMap - Permission", "GPS access has not been granted");
                    }
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION);

                    if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                        map.setMyLocationEnabled(true);
                    } else {
                        Log.e("GMap - Permission", "GPS access has not been granted");
                    }
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
