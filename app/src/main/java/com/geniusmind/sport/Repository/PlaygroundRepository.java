package com.geniusmind.sport.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.geniusmind.sport.Model.ApiInterface;
import com.geniusmind.sport.Model.ClientApi;
import com.geniusmind.sport.Model.PlaygroundCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaygroundRepository {
private static final String TAG = "PlaygroundRepository";

MutableLiveData<PlaygroundCallback>mutablePlayground = new MutableLiveData<>();

public PlaygroundRepository(){

    }

    public MutableLiveData<PlaygroundCallback> getMutablePlaygroundList(String page){
        ApiInterface apiInterface = ClientApi.getInstance().create(ApiInterface.class);

        Call<PlaygroundCallback>call = apiInterface.playgroundCall(page);

        call.enqueue(new Callback<PlaygroundCallback>() {
            @Override
            public void onResponse(Call<PlaygroundCallback> call, Response<PlaygroundCallback> response) {
                PlaygroundCallback playgrounds = response.body();
                if(playgrounds.getNextPage() != null) {
                    mutablePlayground.setValue(playgrounds);
                }
            }

            @Override
            public void onFailure(Call<PlaygroundCallback> call, Throwable t) {
                Log.e(TAG,t.getMessage());
            }
        });
        return mutablePlayground;
    }

}
