package com.geniusmind.sport.ViewModel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.geniusmind.sport.Model.ClientApi;
import com.geniusmind.sport.Model.LoginCallback;
import com.geniusmind.sport.Model.PostLoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    private String email ;
    private String password;

    public LoginViewModel(){
        this.email = "";
        this.password = "";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // to validate login information if empty or not .
    public int loginIsEmpty(String email , String password){
        if(email.isEmpty()){
            return 1;
        }
        if(password.isEmpty()){
            return 2;
        }
        return 0;
    }

    public Call<LoginCallback> getUserFromDatabase(){

        PostLoginService loginService = ClientApi.getInstance().create(PostLoginService.class);
        Call<LoginCallback>call= loginService.loginCall(this.email,this.password);

        return call;
    }


}
