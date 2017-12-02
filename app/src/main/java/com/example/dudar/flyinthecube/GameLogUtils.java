package com.example.dudar.flyinthecube;

import java.util.ArrayList;

/**
 * Created by dudar on 20.10.2017.
 */

public class GameLogUtils {

    private static ArrayList<String> gameLogArray = new ArrayList<String>();
    private static ArrayList<String> botLogArray = new ArrayList<String>();

    static ArrayList<String> getLogItems() {
        return gameLogArray;
    }

    static ArrayList<String> getBotLogItems() {
        return botLogArray;
    }

    static void addLogItem(String str) {
        gameLogArray.add(0, str);
        botLogArray.add(0,"");
    }

    static void addBotLogItem(String str) {
        botLogArray.add(0, str);
        gameLogArray.add(0,"");
    }

    static void clearLog() {
        gameLogArray.clear();
        botLogArray.clear();
    }
}
