package com.example.codybontecou.voice;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.static_data.constant.ChampionTags;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

import java.util.List;

/**
 * Created by codybontecou on 3/10/18.
 */

class API_Riot_Async_Call extends AsyncTask<Void, Integer, Summoner> {

    private Champion champion;
    private Context mContext;
//    private String server_response;
//    private JSONArray mJsonArrayContacts = new JSONArray();


    public API_Riot_Async_Call(Context context) {
        mContext = context;
    }

    @Override
    protected Summoner doInBackground(Void... voids) {
        ApiConfig config = new ApiConfig().setKey("RGAPI-916224bb-1746-47b6-9c0e-bdb5c0d1c4aa");
        RiotApi api = new RiotApi(config);

        // First we need to request the summoner because we will need it's account ID
        Summoner summoner = null;
        try {
            summoner = api.getSummonerByName(Platform.NA, "imaqtpie");
        } catch (RiotApiException e) {
            e.printStackTrace();
        }

        if (summoner != null) {
            Log.d("ddd", "Name: " + summoner.getName());
            Log.d("ddd", "Summoner ID: " + summoner.getId());
            Log.d("ddd", "Account ID: " + summoner.getAccountId());
            Log.d("ddd", "Summoner Level: " + summoner.getSummonerLevel());
            Log.d("ddd", "Profile Icon ID: " + summoner.getProfileIconId());
        }
//         Then we can use the account ID to request the summoner's match list
        MatchList matchList = null;
        List<MatchReference> matchReferences = null;
        try {
            if (summoner != null) {
                matchList = api.getMatchListByAccountId(Platform.NA, summoner.getAccountId());
                matchReferences = matchList.getMatches();
            }
        } catch (RiotApiException e) {
            e.printStackTrace();
        }


//            Log.d("ddd", String.valueOf(matchReferences.get(matchReferences.size()-1).getGameId()));
//            Log.d("ddd", String.valueOf(matchReferences.get(matchReferences.size()-1).getChampion()));
        try {
            champion = api.getDataChampion(Platform.EUW, matchReferences.get(0).getChampion(), null, null,
                    ChampionTags.ALL);
            Log.d("ddd", champion.getName());
            Log.d("ddd", champion.getPassive().toString());
        } catch (RiotApiException e) {
            e.printStackTrace();
        }


        // We can now iterate over the match list to access the data
        if (matchList != null && matchList.getMatches() != null) {
            for (MatchReference match : matchList.getMatches()) {
                System.out.println("GameID: " + match.getGameId());
            }
        }


        return summoner;
    }
}