package com.geniusmind.sport.View;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.geniusmind.sport.Model.UserBasicCallback;
import com.geniusmind.sport.R;
import com.geniusmind.sport.ViewModel.BasicViewModel;
import com.geniusmind.sport.databinding.ActivityBasicBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public abstract class BaseBasicFragment extends AppCompatActivity  {
    ActivityBasicBinding basicBinding ;
    ActionBarDrawerToggle drawerToggle;
    BasicViewModel basicViewModel ;

    public abstract Fragment getFragment();

    public abstract void getFragmentAfterSelected(int id);


    public abstract void getUserData(BasicViewModel viewModel);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     basicBinding = DataBindingUtil.setContentView(this,R.layout.activity_basic);

     basicViewModel= ViewModelProviders.of(this).get(BasicViewModel.class);
     getUserData(basicViewModel);









     basicBinding.navigationBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
         @Override
         public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
             int id = menuItem.getItemId();
            getFragmentAfterSelected(id);
             return false;
         }
     });

     drawerToggle = new ActionBarDrawerToggle(this,basicBinding.drawerLayout,basicBinding.toolbarBasic
                                               ,R.string.open_drawer,R.string.close_drawer);

     basicBinding.drawerLayout.addDrawerListener(drawerToggle);
     drawerToggle.syncState();



     basicBinding.navigationDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
         @Override
         public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
             int id = menuItem.getItemId();
             getFragmentAfterSelected(id);
             return false;
         }
     });


        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.basic_container);

        if(fragment == null){
            Fragment f = getFragment() ;
            manager.beginTransaction()
                    .add(R.id.basic_container , f)
                    .commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
