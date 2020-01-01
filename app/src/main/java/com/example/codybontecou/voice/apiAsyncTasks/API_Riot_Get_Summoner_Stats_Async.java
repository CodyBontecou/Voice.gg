package com.example.codybontecou.voice.apiAsyncTasks;

import android.graphics.Region;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.codybontecou.voice.globalVal.UserData;
import com.example.codybontecou.voice.globalVal.apiKey;
import com.example.codybontecou.voice.misc_classes.Api_Config_Utility;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.match.dto.Participant;
import net.rithms.riot.api.endpoints.match.dto.TeamStats;
import net.rithms.riot.api.endpoints.spectator.dto.CurrentGameInfo;
import net.rithms.riot.api.endpoints.spectator.dto.CurrentGameParticipant;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.static_data.dto.Stats;
import net.rithms.riot.api.endpoints.static_data.dto.SummonerSpell;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.api.request.ratelimit.RateLimitException;
import net.rithms.riot.constant.Platform;

import java.util.List;

/**
 * Created by admin on 3/17/18.
 */

public class API_Riot_Get_Summoner_Stats_Async extends AsyncTask<Void, Integer, Void> {

    public interface AsyncResponse {
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;

    public API_Riot_Get_Summoner_Stats_Async(AsyncResponse delegate) {
        this.delegate = delegate;
    }


    private RiotApi api = new Api_Config_Utility().getApi();

    @Override
    protected Void doInBackground(Void... voids) {
        Summoner summoner = null;
        TeamStats teamStats = null;
        Champion champion = null;
        CurrentGameInfo currentGameInfo;
        List<CurrentGameParticipant> currentGameParticipantList;
        SummonerSpell summonerSpell1, summonerSpell2;


        try {
            summoner = api.getSummonerByName(Platform.NA, UserData.getUser());
            currentGameInfo = api.getActiveGameBySummoner(Platform.NA, summoner.getId());
            currentGameParticipantList = currentGameInfo.getParticipants();
            for (int i = 0; i < currentGameParticipantList.size(); i++) {
                summonerSpell1 = api.getDataSummonerSpell(Platform.NA, currentGameParticipantList.get(i).getSpell1Id());
                summonerSpell2 = api.getDataSummonerSpell(Platform.NA, currentGameParticipantList.get(i).getSpell2Id());
                Log.d("ddd", String.valueOf(currentGameParticipantList.get(i)));
                Log.d("ddd", "Summoner spell 1: " + summonerSpell1 +
                        ". It's cooldown: " + summonerSpell1.getCooldown() +
                        " Summoner spell 2: " + summonerSpell2);
            }
        } catch (RiotApiException e) {
            if (e.equals(RateLimitException.RATE_LIMITED)) {
                Log.d("ddd", "Rate limited exceeded");
            }
//            Log.d("ddd", "Summoner Not Found");
            e.printStackTrace();
        }

        if (summoner != null) {
            UserData.setUser(summoner.getName());
            UserData.setSummonerID(summoner.getId());
            UserData.setAccountID(summoner.getAccountId());
            UserData.setLevel(summoner.getSummonerLevel());
            UserData.setIconID(summoner.getProfileIconId());
        }
        if (champion != null) {
            UserData.setChampionName(champion.getName());
        } else {
            Log.d("ddd", "Sorry I don't recognize that champion name.");
        }


        delegate.processFinish("done");
        return null;
    }

}
