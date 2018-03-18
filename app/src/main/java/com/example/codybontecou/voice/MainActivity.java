package com.example.codybontecou.voice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.codybontecou.voice.apiAsyncTasks.*;

import com.example.codybontecou.voice.globalVal.Prefs;
import com.example.codybontecou.voice.globalVal.apiKey;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String summoner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        summoner = Prefs.getPrefs(MainActivity.this).getString("owner", null).toString();

       API_Riot_Get_Summoner_Stats_Async summoner_stats_async = new API_Riot_Get_Summoner_Stats_Async();
       summoner_stats_async.execute();


        //API_Riot_Async_Call api_riot_async_call = new API_Riot_Async_Call();
        //api_riot_async_call.execute();

    }

    public void buttonClicked(View view) {
    }

}
