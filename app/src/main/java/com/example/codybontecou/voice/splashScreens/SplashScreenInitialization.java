package com.example.codybontecou.voice.splashScreens;

import android.content.Intent;
import android.os.Handler;
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
import com.example.codybontecou.voice.globalVal.Prefs;
import com.example.codybontecou.voice.globalVal.UserData;
import com.example.codybontecou.voice.globalVal.apiKey;

public class SplashScreenInitialization extends AppCompatActivity implements API_Riot_Get_Summoner_Stats_Async.AsyncResponse{

    private Button mSave;
    private EditText mEditText;
    private apiKey Key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_init);
        Key.setKey("RGAPI-7eceeb77-6c09-4d5b-b86e-e0fac87ef82e");

        //if (!Prefs.getPrefs(SplashScreenInitialization.this).getString("owner", "").isEmpty())
            //moveToMain();

        mSave = findViewById(R.id.save);
        mEditText = findViewById(R.id.name);

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditText.getText().length() == 0) {
                    Toast.makeText(SplashScreenInitialization.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    UserData.setUser(mEditText.getText().toString());
                    new API_Riot_Get_Summoner_Stats_Async(SplashScreenInitialization.this).execute();

                       }
            }
        });
    }
    private void moveToMain(){
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
