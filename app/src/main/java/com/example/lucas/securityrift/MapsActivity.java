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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button b = (Button) findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapsActivity.this, Pop.class));
            }
        });

        Button menu = (Button) findViewById(R.id.menu);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lu = new Intent(MapsActivity.this, MenuActivity.class);
                startActivity(lu);
            }
        });

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        database.child("Pins").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List notes = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    int tipo = (int) noteDataSnapshot.child("Tipo").getValue(int.class);
                    double lat = (double) noteDataSnapshot.child("Lat").getValue();
                    double lng = (double) noteDataSnapshot.child("Long").getValue();
                    LatLng point = new LatLng(lat,lng);
                    String la = " ";

                    if(tipo == 1){
                        la = "Furto";
                    }

                    if(tipo == 2){
                        la = "Assalto";
                    }
                    if(tipo == 3){
                        la = "Abuso/Assedio";
                    }
                    if(tipo == 4){
                        la = "Agressao";
                    }
                    if(tipo == 5){
                        la = "Outros";
                    }

                    mMap.addMarker(new MarkerOptions()
                            .position(point)
                            .title(la)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                    //System.out.print(noteDataSnapshot.getKey());
                    //System.out.print("CC -> " + noteDataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //LatLngBounds UNICAMP = new LatLngBounds(
        //        new LatLng(-22.8175, -47.069722
        //        ), new LatLng(-22.8175, -47.069722
        //));
        //googleMap.setLatLngBoundsForCameraTarget(UNICAMP);
        //mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(UNICAMP,));
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(-22.8175, -47.069722) , 15.0f) );




    }


}
