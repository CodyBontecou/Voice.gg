package com.example.codybontecou.voice;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.champion.dto.ChampionList;
import net.rithms.riot.api.endpoints.static_data.constant.ChampionListTags;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by codybontecou on 3/10/18.
 */
class API_Riot_Async_Call extends AsyncTask<Void, Integer, net.rithms.riot.api.endpoints.static_data.dto.ChampionList> {
    private Champion champion;
    //    private String server_response;
//    private JSONArray mJsonArrayContacts = new JSONArray();
    API_Riot_Async_Call() {
    }
    @Override
    protected net.rithms.riot.api.endpoints.static_data.dto.ChampionList doInBackground(Void... voids) {
        ApiConfig config = new ApiConfig().setKey("RGAPI-f8decca9-eabb-4b98-a1ef-d9b222e6cad8");
        RiotApi api = new RiotApi(config);
        // First we need to request the summoner because we will need it's account ID
        // ############# IMPORTANT: MAKE SURE THE SUMMONER IS INGAME #####################
        Summoner summoner = null;
        try {
            summoner = api.getSummonerByName(Platform.NA, "anderz");
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
        try {
            net.rithms.riot.api.endpoints.static_data.dto.ChampionList championList = api.getDataChampionList(Platform.EUW, null, null, false, ChampionListTags.ALL);
            for (int i = 0; i < championList.getData().size(); i++) {
//                Champion champion = api.getDataChampion(Platform.EUW, championIDS.getChampions().get(i), null, null, ChampionTags.ALL);
                Log.d("ddd", String.valueOf(championList.getData()));
            }
        } catch (RiotApiException e) {
            e.printStackTrace();
        }
        net.rithms.riot.api.endpoints.static_data.dto.ChampionList championList = null;
        try {
            championList = api.getDataChampionList(Platform.NA, null, null, false, ChampionListTags.ALL);
        } catch (RiotApiException e) {
            Log.d("ddd", "Did not obtain champion list");
            e.printStackTrace();
        }
        Map<String, Champion> championByName = new HashMap<>();
        for (Champion champion : championList.getData().values() ) {
            championByName.put(champion.getName().toLowerCase(), champion);
        }
        String name = "kayle";
        Champion champion = championByName.get(name);
        if (champion != null) {
            Log.d("ddd","ID: " + champion.getId());
            Log.d("ddd","Name: " + champion.getName());
            Log.d("ddd","Stats: " + champion.getStats());
            Log.d("ddd","Passive: " + champion.getPassive());
            Log.d("ddd","Lore: " + champion.getLore());
            Log.d("ddd","Title: " + champion.getTitle());
            Log.d("ddd","Skins: " + champion.getSkins());
            Log.d("ddd","Spell (Q): " + champion.getSpells().get(0));
        } else {
            Log.d("ddd","Sorry I don't recognize that champion name.");
        }
        return null;
    }
}
//        try {
//            championIDS = api.getChampions(Platform.NA, false);
//            net.rithms.riot.api.endpoints.static_data.dto.ChampionList championList = api.getDataChampionList(Platform.EUW, null, null, false, ChampionListTags.ALL);
//            for (int i = 0; i < championList.getData().size(); i++) {
//////                Champion champion = api.getDataChampion(Platform.EUW, championIDS.getChampions().get(i), null, null, ChampionTags.ALL);
//                Log.d("ddd", String.valueOf(championList.getData()));
//            }
//
//        } catch (RiotApiException e) {
//            e.printStackTrace();
//        }
//        return championIDS;