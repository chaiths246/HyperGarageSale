package com.example.hypergaragesale;

import android.widget.ImageView;

import java.sql.Blob;

/**
 * Created by Taral on 3/12/2016.
 */
public class BrowsePosts {
    public String mTitle;
    public String mPrice;
    public String mDescription;
    public byte[] mImage;
    public String latitude;
    public String langitude;


    public BrowsePosts(String mTitle, String mPrice, String description, byte[] mImage, String latitude, String langitude) {
        this.mTitle = mTitle;
        this.mPrice = mPrice;
        this.mDescription = description;
        this.mImage = mImage;
        this.latitude = latitude;

        this.langitude = langitude;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public byte[] getmImage() {
        return mImage;
    }

    public void setmImage(byte[] mImage) {
        this.mImage = mImage;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLangitude() {
        return langitude;
    }

    public void setLangitude(String langitude) {
        this.langitude = langitude;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
