package com.geniusmind.sport.Model;

import com.google.gson.annotations.SerializedName;

public class LoginCallback {
    @SerializedName("status")
    private String status ;

    public String getErrorMsg() {
        return errorMsg;
    }

    @SerializedName("msg")
    private String errorMsg ;

    @SerializedName("id")
    private String id;


    public String getId() {
        return id;
    }



    public String getStatus() {
        return status;
    }
}
