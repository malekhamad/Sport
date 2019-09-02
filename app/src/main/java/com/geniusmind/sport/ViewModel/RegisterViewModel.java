package com.geniusmind.sport.ViewModel;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.geniusmind.sport.Model.UserRegister;

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
        }else if (TextUtils.isEmpty(userInformation.getPassword())){
            return 4;
        }else if (TextUtils.isEmpty(userInformation.getC_password())){
            return 5;
        }else if (TextUtils.isEmpty(userInformation.getDate_birth())){
            return 6;
        }else if(userInformation.getPhone_number() == 0){
            return 7;
        }else if(userInformation.getGovernorate().contains("governorate")
                || userInformation.getGovernorate().contains("محافظة")){
           return 8 ;
        }else if(TextUtils.isEmpty(userInformation.getImage_base64())){
            return 9 ;
        }else if(userInformation.getPassword().length() < 8){
            return 10;
        }else if(!userInformation.getPassword().equals(userInformation.getC_password())){
            return 11;
        }else if(String.valueOf(userInformation.getPhone_number()).length()<9){
            return 12;
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
    public int passDataToTheDatabase() {
        if(validateViews() ==0){
            Log.i("data","all data is complete");
            return 0;
        }
        return validateViews();
    }
}
