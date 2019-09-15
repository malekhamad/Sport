package com.geniusmind.sport.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.geniusmind.sport.ContractClass;
import com.geniusmind.sport.Helper.Preferences;
import com.geniusmind.sport.R;
import com.google.android.material.navigation.NavigationView;

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
                Toast.makeText(this, "playgrouond", Toast.LENGTH_SHORT).show();
                break;
            case R.id.find_team:
                Toast.makeText(this, "find team", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_profile:
                Toast.makeText(this, "my profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_team:
                Toast.makeText(this, "my team", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "settting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;

        }

    }

}
