package com.geniusmind.sport.View;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.geniusmind.sport.Auth.GoogleAuth;
import com.geniusmind.sport.ContractClass;
import com.geniusmind.sport.Helper.Preferences;
import com.geniusmind.sport.Model.LoginCallback;
import com.geniusmind.sport.R;
import com.geniusmind.sport.ViewModel.LoginViewModel;
import com.geniusmind.sport.databinding.FragmentLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private FragmentLoginBinding loginBinding;
    LoginViewModel loginViewModel ;
    GoogleAuth googleAuth ;
    FirebaseAuth mAuth;

    public static Fragment getLoginFragment() {
       return new LoginFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        googleAuth = new GoogleAuth(getActivity());
        mAuth = FirebaseAuth.getInstance();
        loginBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false);
        loginBinding.textforget.setOnClickListener(this);
        loginBinding.signupLabel.setOnClickListener(this);
        loginBinding.loginButton.setOnClickListener(this);
        loginBinding.googleButton.setOnClickListener(this);

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

            case R.id.google_button:
                loginBinding.loginLinearProgress.setVisibility(View.VISIBLE);
                //Toast.makeText(getActivity(), "hello world", Toast.LENGTH_SHORT).show();
                Intent signInIntent =googleAuth.getClient(getActivity()).getSignInIntent();
                startActivityForResult(signInIntent, ContractClass.RC_SIGN_IN);
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
                                startActivity(new Intent(view.getContext(),BasicActvity.class));
                                getActivity().finish();
                            }

                            @Override
                            public void onShown(Snackbar sb) {
                                Preferences.setPreferences(view.getContext(), ContractClass.USER_FILE,ContractClass.USER_ID_KEY,user_id);
                                loginBinding.loginLinearProgress.setVisibility(View.GONE);
                            }
                        });
                        snackbar.show();

                    }else {

                       loginBinding.emailLoginLayout.setError(response.body().getErrorMsg());
                       loginBinding.passwordLoginLayout.setError(" ");
                       loginBinding.loginLinearProgress.setVisibility(View.GONE);

                    }
                }

                @Override
                public void onFailure(Call<LoginCallback> call, Throwable t) {
                    Log.i("call Failure",t.getMessage());
                    loginBinding.loginLinearProgress.setVisibility(View.GONE);

                }
            });

            }
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ContractClass.RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately

                // ...
            }


        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);



        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getActivity(), user.getEmail()+"\n"+user.getDisplayName()+"\n"+user.getUid(), Toast.LENGTH_SHORT).show();
                            loginBinding.loginLinearProgress.setVisibility(View.GONE);


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("loginGoogleFailure",task.getException().getMessage());

                        }

                        // ...
                    }
                });


    }

}
