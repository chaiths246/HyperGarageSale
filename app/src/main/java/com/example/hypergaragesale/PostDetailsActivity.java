package com.example.hypergaragesale;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PostDetailsActivity extends AppCompatActivity {
    TextView prod_name;
    TextView prod_price;
    ImageView prod_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        prod_name = (TextView) findViewById(R.id.product_name);
        prod_price = (TextView) findViewById(R.id.product_price);
        prod_image = (ImageView) findViewById(R.id.postdetail_image);

        Intent i = getIntent();
        final String name = i.getExtras().getString("Name");
        final String pos = i.getExtras().getString("Position");

       final String image_path = i.getExtras().getString("Image");
        Bitmap bitmap = BitmapFactory.decodeFile(image_path);


        prod_image.setImageBitmap(bitmap);


        prod_name.setText("NAME :   " + name);
        prod_price.setText("POSITION : " + pos);
    }
}
