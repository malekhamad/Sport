package com.geniusmind.sport.ViewModel;

import android.text.TextUtils;

import androidx.lifecycle.ViewModel;

import com.geniusmind.sport.Model.ApiInterface;
import com.geniusmind.sport.Model.ClientApi;
import com.geniusmind.sport.Model.LoginCallback;
import com.geniusmind.sport.Model.UserRegister;

import retrofit2.Call;

public class RegisterViewModel extends ViewModel {
    UserRegister userInformation ;
    public RegisterViewModel(){
        userInformation = new UserRegister();
    }

    public void setUserInformation(UserRegister userInformation){
        this.userInformation = userInformation ;
    }

    public UserRegister getUserInformation(){
        return this.userInformation ;
    }

    public int validateViews(){
        if(TextUtils.isEmpty(userInformation.getFname())){
            return 1;
        }else if(TextUtils.isEmpty(userInformation.getLname())){
            return 2;
        }else if (TextUtils.isEmpty(userInformation.getTeam_name())){
            return 3;
        }else if (TextUtils.isEmpty(userInformation.getEmail())){
            return 4;
        }else if (TextUtils.isEmpty(userInformation.getPassword())){
            return 5;
        }else if (TextUtils.isEmpty(userInformation.getC_password())){
            return 6;
        }else if (TextUtils.isEmpty(userInformation.getDate_birth())){
            return 7;
        }else if(TextUtils.isEmpty(userInformation.getPhone_number())){
            return 8;
        }else if(userInformation.getGovernorate().contains("governorate")
                || userInformation.getGovernorate().contains("محافظة")){
           return 9 ;
        }else if(TextUtils.isEmpty(userInformation.getImage_base64())){
            return 10 ;
        }else if(userInformation.getPassword().length() < 8){
            return 11;
        }else if(!userInformation.getPassword().equals(userInformation.getC_password())){
            return 12;
        }else if(String.valueOf(userInformation.getPhone_number()).length()<9){
            return 13;
        }

        return 0;
    }

    public void setBase64(String base64){
        this.userInformation.setImage_base64(base64);
    }

    public String getBase64(){
        return this.userInformation.getImage_base64();
    }


    // pass data to the database .. . ;
    public Call<LoginCallback> passDataToTheDatabase() {

            ApiInterface registerService = ClientApi.getInstance().create(ApiInterface.class);

            Call<LoginCallback> call = registerService.registerCall(userInformation.getImage_base64(), userInformation.getFname()+" "+ userInformation.getLname(), userInformation.getTeam_name(),
                    userInformation.getEmail(), userInformation.getPassword(), userInformation.getGovernorate(), userInformation.getDate_birth()
                    , userInformation.getPhone_number(), userInformation.getSex());

       return call;
    }
}
