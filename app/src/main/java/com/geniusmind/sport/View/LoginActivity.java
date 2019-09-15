package com.geniusmind.sport.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.geniusmind.sport.R;


import static com.geniusmind.sport.View.RegisterFragment.getRegisterFragment;

public class LoginActivity extends BaseLoginFragment {


    @Override
    public Fragment getFragment() {
        // Todo : here we need add expression if user is exist then go to the main app ...;
      Fragment loginFragment = LoginFragment.getLoginFragment();
      Fragment registerFragment = com.geniusmind.sport.View.RegisterFragment.getRegisterFragment();



        return loginFragment ;
    }
}
