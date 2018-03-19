package com.example.codybontecou.voice.apiAsyncTasks;

import android.graphics.Region;
import android.os.AsyncTask;
import android.util.Log;

import com.example.codybontecou.voice.globalVal.UserData;
import com.example.codybontecou.voice.globalVal.apiKey;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.static_data.dto.Stats;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

/**
 * Created by admin on 3/17/18.
 */

public class API_Riot_Get_Summoner_Stats_Async extends AsyncTask<Void, Integer, Void> {

    public interface AsyncResponse {
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;

    public API_Riot_Get_Summoner_Stats_Async(AsyncResponse delegate){
        this.delegate = delegate;
    }


    ApiConfig config = new ApiConfig().setKey(apiKey.getKey());
    RiotApi api = new RiotApi(config);

    @Override
    protected Void doInBackground(Void... voids) {
        Summoner summoner = null;

        try {
            summoner = api.getSummonerByName(Platform.NA, UserData.getUser());
            MatchList matchList = api.getMatchListByAccountId(Platform.NA, summoner.getAccountId());
            if (matchList.getMatches() != null) {
                for (MatchReference match : matchList.getMatches()) {
                  Log.d("ttt", "GameID: " + match.getGameId());

                }
            }
        } catch (RiotApiException e) {
            Log.d("ddd", "Summoner Not Found");
            e.printStackTrace();
        }

        try{

        }catch (Exception e){

        }

        if (summoner != null) {
            Log.d("ddd", "Name: " + summoner.getName());
            UserData.setUser(summoner.getName());
            Log.d("ddd", "Summoner ID: " + summoner.getId());
            UserData.setSummonerID(summoner.getId());
            Log.d("ddd", "Account ID: " + summoner.getAccountId());
            UserData.setAccountID(summoner.getAccountId());
            Log.d("ddd", "Summoner Level: " +  summoner.getSummonerLevel());
            UserData.setLevel(summoner.getSummonerLevel());
            Log.d("ddd", "Profile Icon ID: " + summoner.getProfileIconId());
            UserData.setIconID(summoner.getProfileIconId());
        }


        delegate.processFinish("done");
        return null;
    }

}
