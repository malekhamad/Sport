package com.geniusmind.sport.View;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.geniusmind.sport.Model.PlaygroundCallback;
import com.geniusmind.sport.Model.playgroundInfo;
import com.geniusmind.sport.R;
import com.geniusmind.sport.ViewModel.PlaygroundViewModel;
import com.geniusmind.sport.databinding.FragmentPlaygroundBinding;
import com.geniusmind.sport.databinding.PlaygroundCardBinding;
import com.geniusmind.sport.databinding.ProfileFragmentBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaygroundFragment extends Fragment {
    FragmentPlaygroundBinding playgroundBinding;
    List<PlaygroundCallback> infoList;
    PlaygroundRecyclerAdapter playgroundAdapter;
    PlaygroundViewModel playgroundViewModel;


    // to create object from this fragment . . . ;
    public static Fragment getInstance() {
        return new PlaygroundFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

/*        infoList = new ArrayList<>();

        infoList.add(new PlaygroundCallback("Al-Yarmouk", new ArrayList<String>()
                , "20", "2", "Amman-istiqlal", "7738434", "4349898", "10pm", "2am"));
        infoList.add(new PlaygroundCallback("Al-Yarmouk2", new ArrayList<String>()
                , "30", "2", "Amman-istiqlal2", "7738434", "4349898", "10pm", "2am"));
        infoList.add(new PlaygroundCallback("Al-Yarmouk3", new ArrayList<String>()
                , "25", "2", "Amman-istiqlal", "7738434", "4349898", "10pm", "2am"));
        infoList.add(new PlaygroundCallback("Al-Yarmouk4", new ArrayList<String>()
                , "22", "2", "Amman-istiqlal", "7738434", "4349898", "10pm", "2am"));*/

        // Inflate the layout for this fragment
        playgroundBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_playground, container, false);
        // create instance from PlaygroundRecyclerAdapter . . . ;
        playgroundAdapter = new PlaygroundRecyclerAdapter(playgroundBinding.getRoot().getContext());

        playgroundBinding.playgroundRecyclerView.setLayoutManager(new LinearLayoutManager(playgroundBinding.getRoot().getContext()));
        playgroundBinding.playgroundRecyclerView.setHasFixedSize(true);


        // create instance from PlaygroundViewModel . . . ;
        playgroundViewModel = ViewModelProviders.of(getActivity()).get(PlaygroundViewModel.class);
        playgroundBinding.playgroundRecyclerView.setAdapter(playgroundAdapter);
        getPlayground();

        return playgroundBinding.getRoot();
    }


    // create view holder for recycler view ...... ;
    private class PlaygroundViewHolder extends RecyclerView.ViewHolder {
        PlaygroundCardBinding cardBinding;

        public PlaygroundViewHolder(@NonNull PlaygroundCardBinding cardBinding) {
            super(cardBinding.getRoot());
            this.cardBinding = cardBinding;

        }
    }

    // crete adapter for recycler view . . . .;
    public class PlaygroundRecyclerAdapter extends RecyclerView.Adapter<PlaygroundViewHolder> {
        List<playgroundInfo> playgroundList = new ArrayList<>();
        Context context;

        public PlaygroundRecyclerAdapter(Context context) {
            this.context = context;
        }

        @NonNull
        @Override
        public PlaygroundViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            PlaygroundCardBinding cardBinding = DataBindingUtil.inflate(LayoutInflater.from(context)
                    , R.layout.playground_card, parent, false
            );
            return new PlaygroundViewHolder(cardBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull PlaygroundViewHolder holder, int position) {
            playgroundInfo playgroundInformation = playgroundList.get(position);

            holder.cardBinding.playgroundName.setText(playgroundInformation.getPlayground_name());
            holder.cardBinding.playgroundPlace.setText(playgroundInformation.getPlace());
            holder.cardBinding.playgroundRating.setRating(3.0f);
            holder.cardBinding.hourPrice.setText(playgroundInformation.getPlayground_price()+"/JD");
            holder.cardBinding.playgroundImage.setImageResource(R.drawable.add_photo);
        }

        @Override
        public int getItemCount() {
            return playgroundList.size();
        }


        public void setPlaygroundList(PlaygroundCallback playgroundList) {

            for(int i = 0 ; i<playgroundList.getPlaygroundList().size();i++){
                this.playgroundList.add(playgroundList.getPlaygroundList().get(i));
            }
            notifyDataSetChanged();
        }
    }


    /// getAllPlayground Method  using livedata . . . ;;
    public void getPlayground() {
        String page = playgroundViewModel.getPage();
        if(page == null){
            // dismiss scroll bar witout any changes . . . ;
        }else {

            String[] pages = page.split("=");
            page = pages[1];

            playgroundViewModel.getPlaygroundList(page).observe(getActivity(), new Observer<PlaygroundCallback>() {
                @Override
                public void onChanged(PlaygroundCallback playgroundCallbacks) {
                    playgroundAdapter.setPlaygroundList(playgroundCallbacks);
                }
            });

        }

    }
}