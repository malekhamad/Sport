package com.geniusmind.sport.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaygroundCallback {
    private String playground_name ;
    private List<String> images ;
    @SerializedName("hour_price")
    private String playground_price ;
    private String offer ;
    private String place ;
    private String longitude ;
    private String latitude ;
    private String opened ;
    @SerializedName("Closed")
    private String closed ;

    public PlaygroundCallback(String playground_name, List<String> images, String playground_price, String offer, String place, String longitude, String latitude, String opened, String closed) {
        this.playground_name = playground_name;
        this.images = images;
        this.playground_price = playground_price;
        this.offer = offer;
        this.place = place;
        this.longitude = longitude;
        this.latitude = latitude;
        this.opened = opened;
        this.closed = closed;
    }

    public String getPlayground_name() {
        return playground_name;
    }

    public List<String> getImages() {
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
