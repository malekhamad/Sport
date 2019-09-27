package com.geniusmind.sport.Model;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.annotations.SerializedName;

public class UserBasicCallback {
    @SerializedName("status")
    private String status ;
    @SerializedName("id")
    private String id ;
    @SerializedName("username")
    private String playername;
    @SerializedName("team_name")
    private String teamname;
    @SerializedName("user_image")
    private String playerImage ;
    @SerializedName("team_image")
    private String teamImage ;

    public UserBasicCallback(String status, String id, String playername, String teamname, String playerImage, String teamImage) {
        this.status = status;
        this.id = id;
        this.playername = playername;
        this.teamname = teamname;
        this.playerImage = playerImage;
        this.teamImage = teamImage;
    }

    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public String getPlayername() {
        return playername;
    }

    public String getTeamname() {
        return teamname;
    }

    public String getPlayerImage() {
        return playerImage;
    }

    public String getTeamImage() {
        return teamImage;
    }
}
