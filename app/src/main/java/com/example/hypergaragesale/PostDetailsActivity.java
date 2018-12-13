package com.example.hypergaragesale;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class PostDetailsActivity extends FragmentActivity {
    TextView prod_name;
    TextView prod_price;
    ImageView prod_image;
    TextView prod_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        prod_name = (TextView) findViewById(R.id.product_name);
        prod_price = (TextView) findViewById(R.id.product_price);
        prod_image = (ImageView) findViewById(R.id.postdetail_image);
        prod_desc=(TextView)findViewById(R.id.product_desc);

        Intent i = getIntent();
        final String name = i.getExtras().getString("Name");
        final String pos = i.getExtras().getString("Price");
        final String lat = i.getExtras().getString("Latitide");
        final String lang = i.getExtras().getString("Langitude");
        final String desc = i.getExtras().getString("Description");
        byte[] byteArray = getIntent().getByteArrayExtra("Image");
        Bitmap  bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        prod_image.setImageBitmap(bmp);
        prod_name.setText("NAME :   " + name);
        prod_price.setText("Price : " + pos);
        prod_desc.setText(desc);

        double lat1 = Double.valueOf(lat);
        double lang1=Double.valueOf(lang);
        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(lat1, lang1, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()


        LatLng position = new LatLng(lat1, lang1);

        // Instantiating MarkerOptions class
        MarkerOptions options = new MarkerOptions();

        // Setting position for the MarkerOptions
        options.position(position);

        // Setting title for the MarkerOptions
        options.title("Position");

        // Setting snippet for the MarkerOptions
        options.snippet(address);

        // Getting Reference to SupportMapFragment of activity_map.xml
        SupportMapFragment fm = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);

        // Getting reference to google map
        GoogleMap googleMap = fm.getMap();

        // Adding Marker on the Google Map
        googleMap.addMarker(options);

        // Creating CameraUpdate object for position\
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));


    }
}
