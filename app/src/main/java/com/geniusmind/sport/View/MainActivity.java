package com.geniusmind.sport.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.geniusmind.sport.ContractClass;
import com.geniusmind.sport.ContractUrl;
import com.geniusmind.sport.Helper.Preferences;
import com.geniusmind.sport.R;

public class MainActivity extends AppCompatActivity {
private final int Splash_time = 2000;
String userId = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userId = Preferences.getPreferences(this, ContractClass.USER_FILE,ContractClass.USER_ID_KEY,null);
        // Todo :  remove the line below after test . . . ;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            if(userId == null) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }else {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
              finish();
            }
        },Splash_time);

    }
}
