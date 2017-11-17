package com.example.dudar.flyinthecube;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Cube3DActivity extends AppCompatActivity implements InPlayButtonsFragment.OnPlayButtonClickListener, OnGameboard3DClickListener {

    ImageButton playBtn, checkBtn, restartBtn;
    int rowPos, colPos, deepPos;
    int flyPosition;
    TextView gameoverTxt;
    TableLayout tableLayout;

    int tableRowNumber, tableColumnNumber;

    static final int FIRST_BLUE_CUBE_LEVEL = 0;
    static final int SECOND_GREEN_CUBE_LEVEL = 1;
    static final int THIRD_ORANGE_CUBE_LEVEL = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cube3d);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        Gameboard_3D_Fragment gbFragment = new Gameboard_3D_Fragment();
        fragmentManager.beginTransaction()
                .add(R.id.gameboard_container_3d, gbFragment)
                .commit();

        rowPos = 1;
        colPos = 1;
        deepPos = 1;
        flyPosition = 13;

        tableRowNumber = 3;
        tableColumnNumber = 3;

        playBtn = (ImageButton) findViewById(R.id.play_btn);
        checkBtn = (ImageButton) findViewById(R.id.check_btn);
        restartBtn = (ImageButton) findViewById(R.id.restart_btn);
        checkBtn.setVisibility(View.GONE);
        restartBtn.setVisibility(View.GONE);

        gameoverTxt = (TextView) findViewById(R.id.gameover_text_3d);
        gameoverTxt.setVisibility(View.GONE);

        tableLayout = (TableLayout) findViewById(R.id.table3d);
        paintTable(tableLayout, flyPosition);
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

        switch (id) {
            case R.id.action_2d:
                Intent newActivity = new Intent(this, MainActivity.class);
                startActivity(newActivity);
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
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
        tableLayout.removeAllViews();
        GameLogUtils.clearLog();
    }

    @Override
    public void onCheckClick() {

        checkBtn.setVisibility(View.GONE);
        restartBtn.setVisibility(View.VISIBLE);
        playBtn.setVisibility(View.GONE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Gameboard_3D_Fragment gbFragment = new Gameboard_3D_Fragment();
        fragmentManager.beginTransaction()
                .add(R.id.gameboard_container_3d, gbFragment)
                .commit();

        paintTable(tableLayout, flyPosition);

    }

    private void paintTable(TableLayout tableLayout, int flyPosition) {
        int cubeLevel = flyPosition / 9;
        int colorResId;
        int positionInLevel;

        switch (cubeLevel) {
            case FIRST_BLUE_CUBE_LEVEL:
                colorResId = R.drawable.qudr1blue;
                //positionInLevel = flyPosition;
                break;
            case SECOND_GREEN_CUBE_LEVEL:
                colorResId = R.drawable.quad1green;
                //positionInLevel = flyPosition % 9;
                break;
            case THIRD_ORANGE_CUBE_LEVEL:
                colorResId = R.drawable.quad1orange;
                //positionInLevel = flyPosition % 9;
                break;
            default:
                colorResId = R.drawable.quad1green;
                //positionInLevel = flyPosition % 9;
                break;
        }

        for (int i = 0; i < tableRowNumber; i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < tableColumnNumber; j++) {
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(colorResId);
                tableRow.addView(imageView, j);
            }
            tableLayout.addView(tableRow, i);
        }

        positionInLevel = flyPosition %9;
        int rowItemWithFly = positionInLevel / 3;
        int columnItemWithFly = positionInLevel % 3;
        TableRow rowWithFly = (TableRow) tableLayout.getChildAt(rowItemWithFly);
        ImageView imgForFly = (ImageView) rowWithFly.getChildAt(columnItemWithFly);
        imgForFly.setImageResource(R.drawable.my_fly1);

    }

    @Override
    public void onRestartClick() {

        checkBtn.setVisibility(View.GONE);
        restartBtn.setVisibility(View.GONE);
        playBtn.setVisibility(View.VISIBLE);

        rowPos = 1;
        colPos = 1;
        deepPos = 1;
        flyPosition = 13;
        gameoverTxt.setVisibility(View.GONE);

        onPlayClick();
    }

    @Override
    public void upClick() {
        rowPos--;
        GameLogUtils.addLogItem(getResources().getString(R.string.game_action_up));
        if (checkBounds())
            flyPosition -= 3;
    }

    private boolean checkBounds() {
        if (rowPos >= 3 || rowPos < 0 || colPos >= 3 || colPos < 0 || deepPos >= 3 || deepPos < 0) {
            endGame();
            return false;
        }
        return true;
    }

    private void endGame() {
        gameoverTxt.setVisibility(View.VISIBLE);
        checkBtn.setVisibility(View.GONE);
    }

    @Override
    public void downClick() {
        rowPos++;
        GameLogUtils.addLogItem(getResources().getString(R.string.game_action_down));
        if (checkBounds())
            flyPosition += 3;
    }

    @Override
    public void leftClick() {
        colPos--;
        GameLogUtils.addLogItem(getResources().getString(R.string.game_action_left));
        if (checkBounds())
            flyPosition -= 1;
    }

    @Override
    public void rightClick() {
        colPos++;
        GameLogUtils.addLogItem(getResources().getString(R.string.game_action_right));
        if (checkBounds())
            flyPosition += 1;
    }

    @Override
    public void forwardClick() {
        deepPos++;
        GameLogUtils.addLogItem(getResources().getString(R.string.game_action_forward));
        if (checkBounds())
            flyPosition += 9;
    }

    @Override
    public void backClick() {
        deepPos--;
        GameLogUtils.addLogItem(getResources().getString(R.string.game_action_back));
        if (checkBounds())
            flyPosition -= 9;
    }
}

