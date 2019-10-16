package com.geniusmind.sport.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.geniusmind.sport.Model.PlaygroundCallback;
import com.geniusmind.sport.Repository.PlaygroundRepository;

import java.util.List;

public class PlaygroundViewModel extends ViewModel {
    PlaygroundRepository playgroundRepository;

    public PlaygroundViewModel(){
        playgroundRepository = new PlaygroundRepository();
    }

    public LiveData<List<PlaygroundCallback>> getPlaygroundList(String id){
        return playgroundRepository.getMutablePlaygroundList(id);
    }
}
