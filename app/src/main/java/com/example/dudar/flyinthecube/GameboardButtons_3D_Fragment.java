package com.example.dudar.flyinthecube;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by dudar on 20.09.2017.
 */

public class GameboardButtons_3D_Fragment extends Fragment {

    //Tag for logging
    private static final String TAG = GameboardButtonsFragment.class.getSimpleName();

    Button upButton,downButton,leftButton,rightButton, backButton,forwardButton;

    public GameboardButtons_3D_Fragment() {
    }

    OnGameboard3DClickListener myCallBack;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myCallBack = (OnGameboard3DClickListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_gameboard_3d_buttons, container, false);

        final ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item,R.id.list_item_textview,GameLogUtils.getLogItems());
        ListView listGameLog = (ListView) rootView.findViewById(R.id.log_list_3d);
        listGameLog.setAdapter(itemAdapter);

        final ArrayAdapter<String> botAdapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item,R.id.list_item_textview,GameLogUtils.getBotLogItems());
        ListView listBotLog = (ListView) rootView.findViewById(R.id.log_list_3d_bot);
        listBotLog.setAdapter(botAdapter);


        upButton = (Button) rootView.findViewById(R.id.up_3d_btn);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableButtons();
                if (myCallBack.upClick()) {
                    itemAdapter.notifyDataSetChanged();
                    botAdapter.notifyDataSetChanged();
                    makeBotNextStep(botAdapter, itemAdapter);
                }
            }
        });
        downButton = (Button)rootView.findViewById(R.id.down_3d_btn);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableButtons();
                if (myCallBack.downClick()){
                    itemAdapter.notifyDataSetChanged();
                    botAdapter.notifyDataSetChanged();
                    makeBotNextStep(botAdapter, itemAdapter);
                }
            }
        });
        leftButton = (Button)rootView.findViewById(R.id.left_3d_btn);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableButtons();
                if (myCallBack.leftClick()){
                    itemAdapter.notifyDataSetChanged();
                    botAdapter.notifyDataSetChanged();
                    makeBotNextStep(botAdapter, itemAdapter);
                }
            }
        });
        rightButton = (Button)rootView.findViewById(R.id.right_3d_btn);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableButtons();
                if (myCallBack.rightClick()){
                    itemAdapter.notifyDataSetChanged();
                    botAdapter.notifyDataSetChanged();
                    makeBotNextStep(botAdapter, itemAdapter);

                }
            }
        });

        forwardButton = (Button) rootView.findViewById(R.id.frwd_3d_btn);
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableButtons();
                if(myCallBack.forwardClick()) {
                    itemAdapter.notifyDataSetChanged();
                    botAdapter.notifyDataSetChanged();
                    makeBotNextStep(botAdapter, itemAdapter);
                }
            }
        });
        backButton = (Button) rootView.findViewById(R.id.back_3d_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableButtons();
                if(myCallBack.backClick()) {
                    itemAdapter.notifyDataSetChanged();
                    botAdapter.notifyDataSetChanged();
                    makeBotNextStep(botAdapter, itemAdapter);
                }
            }
        });

        return rootView;

    }

    private void makeBotNextStep(final ArrayAdapter<String> botAdapter, final ArrayAdapter<String> itemAdapter) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // do something after 500 miliseconds
                myCallBack.makeBotStep();
                botAdapter.notifyDataSetChanged();
                itemAdapter.notifyDataSetChanged();
                enableButtons();
            }
        }, 500); //Time in milisecond
    }

    private void disableButtons() {
        upButton.setEnabled(false);
        downButton.setEnabled(false);
        leftButton.setEnabled(false);
        rightButton.setEnabled(false);
        backButton.setEnabled(false);
        forwardButton.setEnabled(false);
    }

    private void enableButtons(){
        upButton.setEnabled(true);
        downButton.setEnabled(true);
        leftButton.setEnabled(true);
        rightButton.setEnabled(true);
        backButton.setEnabled(true);
        forwardButton.setEnabled(true);
    }
}
