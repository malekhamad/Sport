package com.geniusmind.sport.ViewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.geniusmind.sport.ContractClass;
import com.geniusmind.sport.Helper.Preferences;
import com.geniusmind.sport.Model.ApiInterface;
import com.geniusmind.sport.Model.ClientApi;
import com.geniusmind.sport.Model.UserBasicCallback;

import retrofit2.Call;

public class BasicViewModel extends ViewModel {
    private UserBasicCallback userBasicInfo ;


    public UserBasicCallback getUserBasicInfo() {
        return userBasicInfo;
    }

    public void setUserBasicInfo(UserBasicCallback userBasicInfo) {
        this.userBasicInfo = userBasicInfo;
    }


    public Call<UserBasicCallback> getDataFromDatabase(Context context){
        String id = Preferences.getPreferences(context, ContractClass.USER_FILE,ContractClass.USER_ID_KEY,null);
        ApiInterface basicService = ClientApi.getInstance().create(ApiInterface.class);
        Call<UserBasicCallback>callbackCall = basicService.basicCall(id);

        return callbackCall;
    }
}
