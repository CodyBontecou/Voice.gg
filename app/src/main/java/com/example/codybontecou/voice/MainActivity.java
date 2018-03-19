package com.example.codybontecou.voice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codybontecou.voice.globalVal.UserData;

public class MainActivity extends AppCompatActivity{

    TextView summonerName;
    TextView summonerLevel;
    TextView summonerWins;
    TextView summonerLoses;

    String summoner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        summoner = UserData.getUser();


       //API_Riot_Get_Summoner_Stats_Async summoner_stats_async = new API_Riot_Get_Summoner_Stats_Async;
       //new API_Riot_Get_Summoner_Stats_Async(this).execute();

        summonerName = findViewById(R.id.summonerName);
        summonerLevel = findViewById(R.id.summonerLevel);

        try{
            summonerName.setText(UserData.User);
            summonerLevel.setText(""+UserData.getLevel());
        }catch(Exception e){
            Log.d("ddd", "main Loaded before Api stat call completed");
            Toast.makeText(this, "Unable To Load Summoner Data", Toast.LENGTH_LONG).show();
        }


    }

    public void buttonClicked(View view) {

    }


}
