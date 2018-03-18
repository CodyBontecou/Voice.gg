package com.example.codybontecou.voice.globalVal;

/**
 * Created by admin on 3/17/18.
 */

// Since Class if final and key is static no imports are necessary across classes. use 'apiKey.get();' only

public final class apiKey {

    public static String Key;

    public static String getKey() {
        return Key;
    }

    public static void setKey(String key) {
        Key = key;
    }
}
