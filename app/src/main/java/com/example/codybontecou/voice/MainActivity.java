package com.example.codybontecou.voice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codybontecou.voice.apiAsyncTasks.API_Riot_Get_Summoner_Stats_Async;
import com.example.codybontecou.voice.globalVal.UserData;
import com.example.codybontecou.voice.splashScreens.SplashScreenInitialization;

public class MainActivity extends AppCompatActivity{

    TextView summonerName;
    TextView summonerLevel;
    TextView summonerChampion;
    TextView summonerWins;
    TextView summonerLoses;
    TextView summonerSpell1;
    TextView summonerSpell2;

    String summoner;
    String[] contacts={"Hongin Park", "James Brown"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayAdapter testListView = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, contacts);
//
//        ListView listView = findViewById(R.id.summonerInfo);
//        listView.setAdapter(testListView);

        summoner = UserData.getUser();


       //API_Riot_Get_Summoner_Stats_Async summoner_stats_async = new API_Riot_Get_Summoner_Stats_Async;
       //new API_Riot_Get_Summoner_Stats_Async(this).execute();

        summonerName = findViewById(R.id.summonerName);
        summonerLevel = findViewById(R.id.summonerLevel);
        summonerChampion = findViewById(R.id.summonerChampion);

        try{
            summonerName.setText(UserData.User);
            summonerLevel.setText(""+UserData.getLevel());
            summonerChampion.setText(UserData.getChampionName());
        }catch(Exception e){
            Log.d("ddd", "main Loaded before Api stat call completed");
            Toast.makeText(this, "Unable To Load Summoner Data", Toast.LENGTH_LONG).show();
        }


    }

    public void buttonClicked(View view) {
//        new API_Riot_Get_Summoner_Stats_Async((API_Riot_Get_Summoner_Stats_Async.AsyncResponse) MainActivity.this).execute();
    }


}
