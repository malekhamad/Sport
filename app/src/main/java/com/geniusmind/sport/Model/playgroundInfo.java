package com.geniusmind.sport.Model;

import com.google.gson.annotations.SerializedName;

public class playgroundInfo {
    private int id ;
    @SerializedName("name")
    private String playground_name ;
    private String images ;
    @SerializedName("hour_cost")
    private String playground_price ;
    private String offer ;
    private String place ;
    private String longitude ;
    private String latitude ;
    private String opened ;
    private String closed ;


    public int getId() {
        return id;
    }

    public String getPlayground_name() {
        return playground_name;
    }

    public String getImages() {
        return images;
    }

    public String getPlayground_price() {
        return playground_price;
    }

    public String getOffer() {
        return offer;
    }

    public String getPlace() {
        return place;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getOpened() {
        return opened;
    }

    public String getClosed() {
        return closed;
    }

}
