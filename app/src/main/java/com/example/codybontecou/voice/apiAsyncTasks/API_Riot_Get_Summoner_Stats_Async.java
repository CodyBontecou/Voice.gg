package com.example.codybontecou.voice.apiAsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.static_data.dto.Stats;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

import com.example.codybontecou.voice.MainActivity;
import com.example.codybontecou.voice.globalVal.Prefs;
import com.example.codybontecou.voice.globalVal.UserData;
import com.example.codybontecou.voice.globalVal.apiKey;

/**
 * Created by admin on 3/17/18.
 */

public class API_Riot_Get_Summoner_Stats_Async extends AsyncTask<Void, Integer, Void> {


    ApiConfig config = new ApiConfig().setKey(apiKey.getKey());
    RiotApi api = new RiotApi(config);

    @Override
    protected Void doInBackground(Void... voids) {
        Summoner summoner = null;

        try {
            summoner = api.getSummonerByName(Platform.NA, UserData.getUser());
        } catch (RiotApiException e) {
            Log.d("ddd", "Summoner Not Found");
            e.printStackTrace();
        }

        if (summoner != null) {
            Log.d("ddd", "Name: " + summoner.getName());
            Log.d("ddd", "Summoner ID: " + summoner.getId());
            Log.d("ddd", "Account ID: " + summoner.getAccountId());
            Log.d("ddd", "Summoner Level: " + summoner.getSummonerLevel());
            Log.d("ddd", "Profile Icon ID: " + summoner.getProfileIconId());
        }


        return null;
    }
}
