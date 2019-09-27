package com.geniusmind.sport.Auth;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.geniusmind.sport.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class GoogleAuth {
    private Context context;
    private  GoogleSignInOptions gso;
    private  GoogleSignInClient googleSignInClient;
// ...
// Initialize Firebase Auth
    public GoogleAuth(Context context){
        this.context = context;

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
    }

    public  GoogleSignInClient getClient(Context context){
        return  GoogleSignIn.getClient(context, gso);
    }





}
