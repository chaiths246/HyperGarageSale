package com.example.hypergaragesale;

import android.widget.ImageView;

import java.sql.Blob;

/**
 * Created by Taral on 3/12/2016.
 */
public class BrowsePosts {
    public String mTitle;
    public String mPrice;
    public byte[] mImage;

    public BrowsePosts (String title, String price,byte[] image) {
        this.mTitle = title;
        this.mPrice = price;
        this.mImage=image;
    }
}
