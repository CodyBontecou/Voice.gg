package com.example.codybontecou.voice.apiAsyncTasks;

import android.graphics.Region;
import android.os.AsyncTask;
import android.util.Log;

import com.example.codybontecou.voice.globalVal.UserData;
import com.example.codybontecou.voice.globalVal.apiKey;
import com.example.codybontecou.voice.misc_classes.Api_Config_Utility;

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


    private RiotApi api = new Api_Config_Utility().getApi();

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

        if (summoner != null) {
            UserData.setUser(summoner.getName());
            UserData.setSummonerID(summoner.getId());
            UserData.setAccountID(summoner.getAccountId());
            UserData.setLevel(summoner.getSummonerLevel());
            UserData.setIconID(summoner.getProfileIconId());
        }


        delegate.processFinish("done");
        return null;
    }

}
