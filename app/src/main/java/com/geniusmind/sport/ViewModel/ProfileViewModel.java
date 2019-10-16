package com.geniusmind.sport.ViewModel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.geniusmind.sport.ContractClass;
import com.geniusmind.sport.Helper.Preferences;
import com.geniusmind.sport.Model.ApiInterface;
import com.geniusmind.sport.Model.ClientApi;
import com.geniusmind.sport.Model.ProfileCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {
   private ProfileCallback profileCallback ;

   public ProfileViewModel(){
       profileCallback = new ProfileCallback();

    }

    public ProfileCallback getProfileData(){
       return this.profileCallback;
    }

    public void setProfileCallback(ProfileCallback profileCallback) {
        this.profileCallback = profileCallback;
    }
}
