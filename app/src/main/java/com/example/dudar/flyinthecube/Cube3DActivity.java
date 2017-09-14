package com.example.dudar.flyinthecube;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by dudar on 14.09.2017.
 */

public class Cube3DActivity extends AppCompatActivity implements InPlayButtonsFragment.OnPlayButtonClickListener{

    Button playBtn, checkBtn, restartBtn;
    int rowPos, colPos;
    TextView gameoverTxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cube3d);



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

    }

    @Override
    public void onCheckClick() {

    }

    @Override
    public void onRestartClick() {

    }
}

