package com.geniusmind.sport.View;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geniusmind.sport.R;
import com.geniusmind.sport.databinding.FragmentMyAccountBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment {
   FragmentMyAccountBinding myAccountBinding;
   private String user_id = null;
    public static Fragment getInstance(String user_id) {
        Bundle bundle = new Bundle();
        bundle.putString("user_id",user_id);
        MyAccountFragment myAccountFragment = new MyAccountFragment();
        myAccountFragment.setArguments(bundle);
        return myAccountFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user_id = (String)getArguments().getSerializable("user_id");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        myAccountBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_my_account,container,false);
       myAccountBinding.userIdFragment.setText(user_id);



        return myAccountBinding.getRoot();
    }

}
