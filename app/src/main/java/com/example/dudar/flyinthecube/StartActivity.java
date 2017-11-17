package com.example.dudar.flyinthecube;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by dudar on 25.10.2017.
 */

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.start_titel);
            actionBar.setLogo(R.mipmap.ic_fly);
        }
        findViewById(R.id.start_action_2d).setOnClickListener(this);
        findViewById(R.id.start_action_3d).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();

        switch (v.getId()) {
            case R.id.start_action_2d:
                i = new Intent(this, MainActivity.class);
                break;
            case R.id.start_action_3d:
                i = new Intent(this, Cube3DActivity.class);
                break;
        }
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_start, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_help:
                startActivity(new Intent(this,HelpActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
