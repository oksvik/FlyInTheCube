package com.example.dudar.flyinthecube;

/**
 * Created by dudar on 20.09.2017.
 */

public interface OnGameboard3DClickListener {
    boolean upClick();
    boolean downClick();
    boolean leftClick();
    boolean rightClick();

    boolean forwardClick();
    boolean backClick();

    void makeBotStep();
}

