package com.geniusmind.sport.View;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.geniusmind.sport.ContractClass;
import com.geniusmind.sport.Helper.Preferences;
import com.geniusmind.sport.Model.LoginCallback;
import com.geniusmind.sport.R;
import com.geniusmind.sport.ViewModel.LoginViewModel;
import com.geniusmind.sport.databinding.FragmentLoginBinding;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private FragmentLoginBinding loginBinding;
    LoginViewModel loginViewModel ;


    public static Fragment getLoginFragment() {
       return new LoginFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loginBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false);
        loginBinding.textforget.setOnClickListener(this);
        loginBinding.signupLabel.setOnClickListener(this);
        loginBinding.loginButton.setOnClickListener(this);


        loginViewModel = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);






        return loginBinding.getRoot();
    }

    private boolean isempty(){
        // we need to clear error from layout before check the views . . . ;
        loginBinding.emailLoginLayout.setError(null);
        loginBinding.passwordLoginLayout.setError(null);

        String email = loginBinding.emailLogin.getText().toString();
        String password = loginBinding.passwordLogin.getText().toString();
        switch (loginViewModel.loginIsEmpty(email,password)){
            case 1:
                loginBinding.emailLoginLayout.setError(getString(R.string.email_required));
                return true;
            case 2:
                loginBinding.passwordLoginLayout.setError(getString(R.string.password_required));
                return true;
        }
        return false;
    }

    @Override
    public void onClick(final View view) {
        int id = view.getId();
        switch (id){
            case R.id.textforget:
                Toast.makeText(getActivity(), "textforget", Toast.LENGTH_SHORT).show();
                break;
            case R.id.signup_label:

                FragmentManager fragmentManager = getFragmentManager();
                Fragment loginFragment = RegisterFragment.getRegisterFragment();

                fragmentManager.beginTransaction()
                        .replace(R.id.login_container,loginFragment)
                        .commit();
                break;
            case R.id.login_button:
                loginBinding.emailLoginLayout.setError(null);
                loginBinding.passwordLoginLayout.setError(null);
            if(!isempty()){
                loginBinding.loginLinearProgress.setVisibility(View.VISIBLE);

                loginViewModel.setEmail(loginBinding.emailLogin.getText().toString());
                loginViewModel.setPassword(loginBinding.passwordLogin.getText().toString());

            loginViewModel.getUserFromDatabase().enqueue(new Callback<LoginCallback>() {
                @Override
                public void onResponse(Call<LoginCallback> call, Response<LoginCallback> response) {
                    String status = response.body().getStatus();
                    final String user_id = response.body().getId();
                    
                    if(status.equals("success")){
                        Snackbar snackbar = Snackbar
                                .make(view,getString(R.string.login_successful),Snackbar.LENGTH_SHORT);
                        snackbar.addCallback(new Snackbar.Callback(){
                            @Override
                            public void onDismissed(Snackbar transientBottomBar, int event) {
                                startActivity(new Intent(getActivity(),BasicActvity.class));
                                getActivity().finish();
                            }

                            @Override
                            public void onShown(Snackbar sb) {
                                Preferences.setPreferences(getActivity(), ContractClass.USER_FILE,ContractClass.USER_ID_KEY,user_id);
                                loginBinding.loginLinearProgress.setVisibility(View.GONE);
                            }
                        });
                        snackbar.show();

                    }else {

                       loginBinding.emailLoginLayout.setError("Your Email Or Password Incorrect !");
                       loginBinding.passwordLoginLayout.setError(" ");
                       loginBinding.loginLinearProgress.setVisibility(View.GONE);

                    }
                }

                @Override
                public void onFailure(Call<LoginCallback> call, Throwable t) {
                    Log.i("call Failure",t.getMessage());
                }
            });

            }
                break;
        }
    }


}
