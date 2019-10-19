package com.geniusmind.sport.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.geniusmind.sport.Model.PlaygroundCallback;
import com.geniusmind.sport.Repository.PlaygroundRepository;

public class PlaygroundViewModel extends ViewModel {
    PlaygroundRepository playgroundRepository;
    LiveData<PlaygroundCallback>playground ;
    String page = "http://127.0.0.1:8002/api/playgrounds?page=1";

    public String getPage() {
        return page;
    }


    public PlaygroundViewModel(){
        playgroundRepository = new PlaygroundRepository();
    }

    public LiveData<PlaygroundCallback> getPlaygroundList(String page){
        String []playgroundPage = this.page.split("=");

        if(playgroundPage[1].equals(page)) {
            return playgroundRepository.getMutablePlaygroundList(page);
        }
        this.page = playground.getValue().getNextPage();
        return playground;
    }
}
