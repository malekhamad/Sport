package com.geniusmind.sport.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.geniusmind.sport.Model.ApiInterface;
import com.geniusmind.sport.Model.ClientApi;
import com.geniusmind.sport.Model.PlaygroundCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaygroundRepository {
private static final String TAG = "PlaygroundRepository";

MutableLiveData<List<PlaygroundCallback>>mutablePlaygroundList = new MutableLiveData<>();

public PlaygroundRepository(){

    }

    public MutableLiveData<List<PlaygroundCallback>> getMutablePlaygroundList(String id){
        ApiInterface apiInterface = ClientApi.getInstance().create(ApiInterface.class);

        Call<List<PlaygroundCallback>>call = apiInterface.playgroundCall(id);

        call.enqueue(new Callback<List<PlaygroundCallback>>() {
            @Override
            public void onResponse(Call<List<PlaygroundCallback>> call, Response<List<PlaygroundCallback>> response) {
                List<PlaygroundCallback> playgroundList = response.body();
                if(playgroundList != null || playgroundList.size() != 0) {
                    mutablePlaygroundList.setValue(playgroundList);
                }
            }

            @Override
            public void onFailure(Call<List<PlaygroundCallback>> call, Throwable t) {
                Log.e(TAG,t.getMessage());
            }
        });
        return mutablePlaygroundList;
    }

}
