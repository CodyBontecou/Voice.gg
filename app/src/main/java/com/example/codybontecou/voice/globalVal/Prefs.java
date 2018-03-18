package com.example.codybontecou.voice.globalVal;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by honginpark on 2/26/18.
 */

//save key-value data in sharedpreference
public class Prefs {
    public static final String LASTCHECKED_MILLIS="last-check-millis";

    public static SharedPreferences getPrefs(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);

    }


}
