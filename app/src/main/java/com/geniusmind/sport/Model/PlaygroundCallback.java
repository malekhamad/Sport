package com.geniusmind.sport.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PlaygroundCallback {
    @SerializedName("next_page_url")
    String nextPage ;

    @SerializedName("data")
    List<playgroundInfo>playgroundList;

    @SerializedName("")

    public String getNextPage() {
        return nextPage;
    }

    public List<playgroundInfo> getPlaygroundList() {
        return playgroundList;
    }
}
