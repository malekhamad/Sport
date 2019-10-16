package com.geniusmind.sport.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geniusmind.sport.ContractClass;
import com.geniusmind.sport.Helper.ConvertImage;
import com.geniusmind.sport.Helper.Preferences;
import com.geniusmind.sport.Model.ApiInterface;
import com.geniusmind.sport.Model.ClientApi;
import com.geniusmind.sport.Model.ProfileCallback;
import com.geniusmind.sport.R;
import com.geniusmind.sport.ViewModel.ProfileViewModel;
import com.geniusmind.sport.databinding.ProfileFragmentBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    ProfileFragmentBinding profileBinding;
    private boolean empty;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        profileBinding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false);

        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        // TODO: Usemptye the ViewModel
        if (mViewModel.getProfileData().getUsername() == null) {
            setProfileData(getActivity());
        } else {
            profileBinding.profileUserImage.setImageBitmap(ConvertImage.encodeBase64(mViewModel.getProfileData().getImg_src()));
            profileBinding.profileUsername.setText(mViewModel.getProfileData().getUsername());
            profileBinding.profileTeamName.setText(mViewModel.getProfileData().getTeam_name());
            profileBinding.profileEmail.setText(mViewModel.getProfileData().getEmail());
            profileBinding.profileGovernorate.setText(mViewModel.getProfileData().getGovernorate());
            profileBinding.profileDate.setText(mViewModel.getProfileData().getBirth_date());
            profileBinding.profilePhone.setText(mViewModel.getProfileData().getPhone_number());
            profileBinding.profileProgressLayout.setVisibility(View.GONE);
            profileBinding.constraintProfile.setVisibility(View.VISIBLE);
        }

        return profileBinding.getRoot();
    }



    public void setProfileData(Context context) {
        String id = Preferences.getPreferences(context, ContractClass.USER_FILE, ContractClass.USER_ID_KEY, null);
        ApiInterface apiInterface = ClientApi.getInstance().create(ApiInterface.class);
        final Call<ProfileCallback> profileCallbackCall = apiInterface.profileCall(id);
        profileCallbackCall.enqueue(new Callback<ProfileCallback>() {
            @Override
            public void onResponse(Call<ProfileCallback> call, Response<ProfileCallback> response) {
               mViewModel.setProfileCallback(response.body());
                profileBinding.profileUserImage.setImageBitmap(ConvertImage.encodeBase64(response.body().getImg_src()));

                profileBinding.profileUsername.setText(response.body().getUsername());
                profileBinding.profileTeamName.setText(response.body().getTeam_name());
                profileBinding.profileEmail.setText(response.body().getEmail());
                profileBinding.profileGovernorate.setText(response.body().getGovernorate());
                profileBinding.profileDate.setText(response.body().getBirth_date());
                profileBinding.profilePhone.setText(response.body().getPhone_number());
                profileBinding.profileProgressLayout.setVisibility(View.GONE);
                profileBinding.constraintProfile.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ProfileCallback> call, Throwable t) {
                Log.e("profileFailure", t.getMessage());
            }
        });
    }


}
