package com.labmanuales.agrum.pilotolabmanuales;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import android.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by diego on 5/09/16.
 */
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    Marker marcador;
    double lat = 0.0;
    double lng = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i("MapActivity", "onMapReady");
        map = googleMap;

        map.setMapType(googleMap.MAP_TYPE_NORMAL);
        map.getUiSettings().setZoomControlsEnabled(true);
        miUbicacion();

    }

    private void agregarMarcador(double lat, double lng){
        Log.i("MapActivity", "agregarMarcador");
        LatLng coordenadas = new LatLng(lat,lng);
        CameraUpdate miubicacion = CameraUpdateFactory.newLatLngZoom(coordenadas,15);
        if(marcador != null) marcador.remove();
        marcador = map.addMarker(new MarkerOptions()
        .position(coordenadas)
        .title("Mi Posicion")
        );
        map.animateCamera(miubicacion);

    }

    private void actualizarUbicacion(Location location){
        //Log.i("MapActivity", "actualizarUbicacion location: "+location.getLatitude()+" - "+location.getLongitude());
        if(location !=null){
            lat = location.getLatitude();
            lng = location.getLatitude();
            agregarMarcador(lat,lng);
        }
    }

    LocationListener locListenr = new LocationListener() {


        @Override
        public void onLocationChanged(Location location) {
            Log.i("MapActivity", "LocationListener");
            actualizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    private void miUbicacion(){
        Log.i("MapActivity", "miUbicacion");
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            Log.i("MapActivity", "Pasoooo");

            return;

        }else {
        }
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,15000,0,locListenr);
    }

}
