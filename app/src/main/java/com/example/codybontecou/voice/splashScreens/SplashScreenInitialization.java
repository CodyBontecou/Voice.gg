package com.example.codybontecou.voice.splashScreens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.codybontecou.voice.MainActivity;
import com.example.codybontecou.voice.R;

import com.example.codybontecou.voice.apiAsyncTasks.API_Riot_Get_Summoner_Stats_Async;
import com.example.codybontecou.voice.globalVal.UserData;
import com.example.codybontecou.voice.globalVal.apiKey;

public class SplashScreenInitialization extends AppCompatActivity implements API_Riot_Get_Summoner_Stats_Async.AsyncResponse {

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_init);

        //if (!Prefs.getPrefs(SplashScreenInitialization.this).getString("owner", "").isEmpty())
        //moveToMain();

        Button mSave = findViewById(R.id.save);
        mEditText = findViewById(R.id.name);

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditText.getText().length() == 0) {
                    Toast.makeText(SplashScreenInitialization.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                } else {
                    UserData.setUser(mEditText.getText().toString().toLowerCase());
                    new API_Riot_Get_Summoner_Stats_Async(SplashScreenInitialization.this).execute();

                }
            }
        });
    }

    private void moveToMain() {
        Intent intent = new Intent(SplashScreenInitialization.this, MainActivity.class);
        SplashScreenInitialization.this.startActivity(intent);
        //SplashScreenInitialization.this.finish();
    }

    @Override
    public void processFinish(String output) {
        Log.d("ddd", "processFinish: stat async call");
        moveToMain();
    }
}
