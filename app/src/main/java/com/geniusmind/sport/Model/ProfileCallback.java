package com.geniusmind.sport.Model;

import com.google.gson.annotations.SerializedName;

public class ProfileCallback {

    private String username ;
    @SerializedName("user_image")
    private String img_src;
    private String team_name ;
    private String email ;
    private String governorate ;
    private String birth_date ;
    private String phone_number ;
    private String verified ;


    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getGovernorate() {
        return governorate;
    }

    public void setGovernorate(String governorate) {
        this.governorate = governorate;
    }



    public String getUsername() {
        return username;
    }

    public String getImg_src() {
        return img_src;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
