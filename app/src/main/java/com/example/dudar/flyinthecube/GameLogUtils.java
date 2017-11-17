package com.example.dudar.flyinthecube;

import java.util.ArrayList;

/**
 * Created by dudar on 20.10.2017.
 */

public class GameLogUtils {

    private static ArrayList<String> gameLogArray = new ArrayList<String>();

    public static ArrayList<String> getLogItems ()
    {
        return gameLogArray;
    }

    public static void addLogItem (String str)
    {
        gameLogArray.add(0,str);
    }

    public static void clearLog(){gameLogArray.clear();}
}
