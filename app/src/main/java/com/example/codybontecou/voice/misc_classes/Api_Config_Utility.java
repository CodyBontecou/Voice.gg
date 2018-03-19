package com.example.codybontecou.voice.misc_classes;

import com.example.codybontecou.voice.globalVal.apiKey;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;

/**
 * Created by codybontecou on 3/18/18.
 */


public class Api_Config_Utility {

    private final ApiConfig config = new ApiConfig().setKey(apiKey.getKey());
    private final RiotApi api = new RiotApi(config);

    public RiotApi getApi() {
        return api;
    }

    public ApiConfig getConfig() {
        return config;
    }
}
