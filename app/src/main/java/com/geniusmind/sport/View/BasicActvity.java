package com.geniusmind.sport.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.geniusmind.sport.ContractClass;
import com.geniusmind.sport.ContractUrl;
import com.geniusmind.sport.Helper.imageHelper;
import com.geniusmind.sport.Helper.Preferences;
import com.geniusmind.sport.Model.UserBasicCallback;
import com.geniusmind.sport.R;
import com.geniusmind.sport.ViewModel.BasicViewModel;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasicActvity extends BaseBasicFragment {

    @Override
    public Fragment getFragment() {
        // basic fragment is myaccountfragment to tested . . . ;
        return MyAccountFragment.getInstance(Preferences.getPreferences(this,ContractClass.USER_FILE,ContractClass.USER_ID_KEY,null));
    }

    @Override
    public void getFragmentAfterSelected(int id) {
        switch (id){
            case R.id.home_item:
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.playground_item:
               replaceFragment(PlaygroundFragment.getInstance());
                break;
            case R.id.find_team:
                Toast.makeText(this, "find team", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_profile:
                 replaceFragment(ProfileFragment.newInstance());
                break;
            case R.id.my_team:
                Toast.makeText(this, "my team", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "settting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                startActivity(new Intent(this,LoginActivity.class));
                Preferences.setPreferences(this,ContractClass.USER_FILE,ContractClass.USER_ID_KEY,null);
                this.finish();
                break;

        }

    }

    @Override
    public void getUserData(final BasicViewModel viewModel) {
       Call<UserBasicCallback> call = viewModel.getDataFromDatabase(this);
       call.enqueue(new Callback<UserBasicCallback>() {
           @Override
           public void onResponse(Call<UserBasicCallback> call, Response<UserBasicCallback> response) {
               String status = response.body().getStatus();
               String id = response.body().getId();
               String playerName = response.body().getPlayername();
               String teamName = response.body().getTeamname();
               String playerImage = response.body().getPlayerImage();
               String teamImage = response.body().getTeamImage();
               viewModel.setUserBasicInfo(new UserBasicCallback(status,id,playerName,teamName,playerImage,teamImage));


               View headerView = basicBinding.navigationDrawer.getHeaderView(0);
               CircleImageView imageView = headerView.findViewById(R.id.user_image_basic);
               TextView player_name = headerView.findViewById(R.id.player_name_header);
               TextView team_name = headerView.findViewById(R.id.team_name_header);
               player_name.setText(viewModel.getUserBasicInfo().getPlayername());
               team_name.setText(viewModel.getUserBasicInfo().getTeamname());
             //  imageView.setImageBitmap(imageHelper.encodeBase64(viewModel.getUserBasicInfo().getPlayerImage()));

               Log.i("image",ContractUrl.imageBaseUrl+""+viewModel.getUserBasicInfo().getPlayerImage());
               // use glide to load image view . . . . ;
               Glide.with(getApplicationContext())
                       .load(ContractUrl.imageBaseUrl+""+viewModel.getUserBasicInfo().getPlayerImage())
                       .centerCrop()
                       .into(imageView);
           }

           @Override
           public void onFailure(Call<UserBasicCallback> call, Throwable t) {
               Log.e("basic request failure !",t.getMessage());
           }
       });

    }

    public void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();

            manager.beginTransaction()
                    .replace(R.id.basic_container , fragment)
                    .commit();
        
    }
}
