package com.example.lucas.securityrift;

import android.app.Dialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

class pin_data {
    String txt;
    double lat;
    double lng;
    int tipo;

    public Map<String,Object> toMap(){
        HashMap<String,Object> result = new HashMap<>();
        result.put("Depoimento", txt);
        result.put("Lat", lat);
        result.put("Long", lng);
        result.put("Tipo", tipo);

        return result;
    }
}

public class pin extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMapLongClickListener {
    String ocorrencia = " ";
    private GoogleMap mMap;
    private double lat;
    private double lng;

    private void writeNewPost(String txt, double lat, double lng, int tipo) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        String key = database.child("Pins").push().getKey();
        pin_data novo = new pin_data();
        novo.txt = txt;
        novo.lat = lat;
        novo.lng = lng;
        novo.tipo = tipo;

        Map<String, Object> PinValues = novo.toMap();
        Map<String, Object> ChildUpdates = new HashMap<>();
        ChildUpdates.put("/Pins/" + key, PinValues);

        database.updateChildren(ChildUpdates);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapin);
        mapFragment.getMapAsync(this);

        final int tipo = getIntent().getIntExtra("tipo", -1);
        final String texto = getIntent().getStringExtra("texto");

        Button b4 = (Button) findViewById(R.id.volta);

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNewPost(texto, lat, lng, tipo);
                Intent la = new Intent(pin.this, MapsActivity.class);

                startActivity(la);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        googleMap.setOnMapLongClickListener(this);
        //LatLngBounds UNICAMP = new LatLngBounds(new LatLng(-22.8175, -47.069722), new LatLng(-22.8175, -47.069722));
        //googleMap.setLatLngBoundsForCameraTarget(UNICAMP);
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(-22.8175,-47.069722) , 15.0f) );
    }

    @Override
    public void onMapLongClick(LatLng point) {

        mMap.addMarker(new MarkerOptions()
                .position(point)
                    .title(ocorrencia)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        lat = point.latitude;
        lng = point.longitude;
    }
}
