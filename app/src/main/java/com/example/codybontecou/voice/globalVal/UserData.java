package com.example.codybontecou.voice.globalVal;

/**
 * Created by admin on 3/17/18.
 */

public final class UserData {

    public static String User;
    public static long SummonerID;
    public static long AccountID;
    public static int Level;
    public static int IconID;
    public static String championName;
    public static String lane;
    public String summonerSpell1;
    public String summonerSpell2;


    public static void setUser(String user) {
        User = user;
    }

    public static String getUser() {
        return User;
    }

    public static void setSummonerID(long summonerID) {
        SummonerID = summonerID;
    }

    public static long getSummonerID() {
        return SummonerID;
    }

    public static void setAccountID(long accountID) {
        AccountID = accountID;
    }

    public static long getAccountID() {
        return AccountID;
    }

    public static void setLevel(int level) {
        Level = level;
    }

    public static int getLevel() {
        return Level;
    }

    public static void setIconID(int iconID) {
        IconID = iconID;
    }

    public static int getIconID() {
        return IconID;
    }

    public static String getChampionName() {
        return championName;
    }

    public static void setChampionName(String championName) {
        UserData.championName = championName;
    }

    public static String getLane() {
        return lane;
    }

    public static void setLane(String lane) {
        UserData.lane = lane;
    }

    public String getSummonerSpell1() {
        return summonerSpell1;
    }

    public void setSummonerSpell1(String summonerSpell1) {
        this.summonerSpell1 = summonerSpell1;
    }

    public String getSummonerSpell2() {
        return summonerSpell2;
    }

    public void setSummonerSpell2(String summonerSpell2) {
        this.summonerSpell2 = summonerSpell2;
    }
}
