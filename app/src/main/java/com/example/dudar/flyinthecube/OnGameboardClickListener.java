package com.example.dudar.flyinthecube;

/**
 * Created by dudar on 27.08.2017.
 */

public interface OnGameboardClickListener {
    boolean upClick();
    boolean downClick();
    boolean leftClick();
    boolean rightClick();

    void makeBotStep();
}
