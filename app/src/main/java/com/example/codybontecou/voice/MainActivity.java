package com.example.codybontecou.voice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import globalVal.Prefs;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String summoner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        summoner = Prefs.getPrefs(MainActivity.this).getString("owner", null).toString();

        //API_Riot_Async_Call api_riot_async_call = new API_Riot_Async_Call();
        //api_riot_async_call.execute();

    }

    public void buttonClicked(View view) {
    }

}
