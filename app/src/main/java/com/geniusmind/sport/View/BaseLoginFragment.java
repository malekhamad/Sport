package com.geniusmind.sport.View;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.geniusmind.sport.R;

public abstract class BaseLoginFragment extends AppCompatActivity {

    public abstract Fragment getFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.login_container);

        if(fragment == null){
            Fragment loginFragment = getFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.login_container,loginFragment)
                    .commit();
              }

    }
}
