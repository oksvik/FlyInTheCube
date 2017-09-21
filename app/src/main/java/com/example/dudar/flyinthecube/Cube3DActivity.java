package com.example.dudar.flyinthecube;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by dudar on 14.09.2017.
 */

public class Cube3DActivity extends AppCompatActivity implements InPlayButtonsFragment.OnPlayButtonClickListener, OnGameboard3DClickListener{

    Button playBtn, checkBtn, restartBtn;
    int rowPos, colPos, deepPos;
    TextView gameoverTxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cube3d);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Gameboard_3D_Fragment gbFragment = new Gameboard_3D_Fragment();
        fragmentManager.beginTransaction()
                .add(R.id.gameboard_container_3d, gbFragment)
                .commit();

        rowPos = 1;
        colPos = 1;
        deepPos = 1;

        playBtn = (Button) findViewById(R.id.play_btn);
        checkBtn = (Button) findViewById(R.id.check_btn);
        restartBtn = (Button) findViewById(R.id.restart_btn);
        checkBtn.setVisibility(View.GONE);
        restartBtn.setVisibility(View.GONE);

        gameoverTxt = (TextView) findViewById(R.id.gameover_text_3d);
        gameoverTxt.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_3d).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_2d:
                Intent newActivity = new Intent(this,MainActivity.class);
                startActivity(newActivity);
                return true;
            default:
                return true;
        }
    }

    @Override
    public void onPlayClick() {
        checkBtn.setVisibility(View.VISIBLE);
        restartBtn.setVisibility(View.VISIBLE);
        playBtn.setVisibility(View.GONE);

        GameboardButtons_3D_Fragment newFragment = new GameboardButtons_3D_Fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.gameboard_container_3d, newFragment)
                .commit();

    }

    @Override
    public void onCheckClick() {

        checkBtn.setVisibility(View.GONE);
        restartBtn.setVisibility(View.VISIBLE);
        playBtn.setVisibility(View.GONE);

    }

    @Override
    public void onRestartClick() {

        checkBtn.setVisibility(View.GONE);
        restartBtn.setVisibility(View.GONE);
        playBtn.setVisibility(View.VISIBLE);

        rowPos = 1;
        colPos = 1;
        deepPos = 1;
        gameoverTxt.setVisibility(View.GONE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Gameboard_3D_Fragment newFragment = new Gameboard_3D_Fragment();
        fragmentManager.beginTransaction()
                .replace(R.id.gameboard_container_3d, newFragment)
                .commit();
    }

    @Override
    public void upClick() {
        rowPos--;
        checkBounds();
    }

    private void checkBounds() {
        if(rowPos>=3 || rowPos<0 || colPos >=3 ||colPos <0 || deepPos >=3 || deepPos <0) {
            endGame();
        }
    }

    private void endGame() {
        gameoverTxt.setVisibility(View.VISIBLE);
        checkBtn.setVisibility(View.GONE);
    }

    @Override
    public void downClick() {
        rowPos++;
        checkBounds();
    }

    @Override
    public void leftClick() {
        colPos--;
        checkBounds();
    }

    @Override
    public void rightClick() {
        colPos++;
        checkBounds();
    }

    @Override
    public void forwardClick() {
        deepPos++;
        checkBounds();
    }

    @Override
    public void backClick() {
        deepPos--;
        checkBounds();
    }
}

