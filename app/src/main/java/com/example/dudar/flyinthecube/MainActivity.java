package com.example.dudar.flyinthecube;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements InPlayButtonsFragment.OnPlayButtonClickListener, OnGameboardClickListener {

    ImageButton playBtn;
    ImageButton checkBtn, restartBtn;
    int rowPos, colPos;
    TextView gameoverTxt;
    static final int TABLE_SIZE = 5;

    public static final String ROW_ITEM_WITH_FLY = "row_item";
    public static final String COLUMN_ITEM_WITH_FLY = "column_item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Load the saved state if there is one
        /*if (savedInstanceState != null) {
            rowPos = savedInstanceState.getInt(ROW_ITEM_WITH_FLY);
            colPos = savedInstanceState.getInt(COLUMN_ITEM_WITH_FLY);
        }*/
        FragmentManager fragmentManager = getSupportFragmentManager();
        GameboardFragment gbFragment = new GameboardFragment();
        gbFragment.setRowItemWithFly(2);
        gbFragment.setColumnItemWithFly(2);
        rowPos = 2;
        colPos = 2;
        fragmentManager.beginTransaction()
                .add(R.id.gameboard_container, gbFragment)
                .commit();

       playBtn = (ImageButton) findViewById(R.id.play_btn);
        checkBtn = (ImageButton) findViewById(R.id.check_btn);
        restartBtn = (ImageButton) findViewById(R.id.restart_btn);
        checkBtn.setVisibility(View.GONE);
        restartBtn.setVisibility(View.GONE);

        gameoverTxt = (TextView) findViewById(R.id.gameover_text);
        gameoverTxt.setVisibility(View.GONE);


    }

    @Override
    public void onPlayClick() {
        checkBtn.setVisibility(View.VISIBLE);
        restartBtn.setVisibility(View.VISIBLE);
        playBtn.setVisibility(View.GONE);
        GameboardButtonsFragment newFragment = new GameboardButtonsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.gameboard_container, newFragment)
                .commit();
        GameLogUtils.clearLog();
    }

    @Override
    public void onCheckClick() {
        checkBtn.setVisibility(View.GONE);
        restartBtn.setVisibility(View.VISIBLE);
        playBtn.setVisibility(View.GONE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        GameboardFragment newFragment = new GameboardFragment();
        newFragment.setColumnItemWithFly(colPos);
        newFragment.setRowItemWithFly(rowPos);
        fragmentManager.beginTransaction()
                .replace(R.id.gameboard_container, newFragment)
                .commit();

    }

    @Override
    public void onRestartClick() {
        checkBtn.setVisibility(View.GONE);
        restartBtn.setVisibility(View.GONE);
        playBtn.setVisibility(View.VISIBLE);
        rowPos = 2;
        colPos = 2;
        gameoverTxt.setVisibility(View.GONE);

        onPlayClick();
    }

    @Override
    public void upClick() {
        rowPos--;
        GameLogUtils.addLogItem(getResources().getString(R.string.game_action_up));
        checkBounds();
    }

    private void checkBounds() {
        if(rowPos>=5 || rowPos<0 || colPos >=5 ||colPos <0) {
            endGame();
        }
    }

    private void endGame() {
         gameoverTxt.setVisibility(View.VISIBLE);
        checkBtn.setVisibility(View.GONE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        GameboardFragment newFragment = new GameboardFragment();
        newFragment.setRowItemWithFly(2);
        newFragment.setColumnItemWithFly(5);
        fragmentManager.beginTransaction()
                .replace(R.id.gameboard_container, newFragment)
                .commit();
    }

    @Override
    public void downClick() {
        rowPos++;
        GameLogUtils.addLogItem(getResources().getString(R.string.game_action_down));
        checkBounds();
    }

    @Override
    public void leftClick() {
        colPos--;
        GameLogUtils.addLogItem(getResources().getString(R.string.game_action_left));
        checkBounds();
    }

    @Override
    public void rightClick() {
        colPos++;
        GameLogUtils.addLogItem(getResources().getString(R.string.game_action_right));
        checkBounds();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(ROW_ITEM_WITH_FLY, rowPos);
        outState.putInt(COLUMN_ITEM_WITH_FLY, colPos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_2d).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_3d:
                Intent newActivity = new Intent(this,Cube3DActivity.class);
                startActivity(newActivity);
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            default:
                return true;
        }
    }
}
