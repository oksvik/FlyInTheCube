package com.example.dudar.flyinthecube;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by dudar on 25.08.2017.
 */

public class GameboardButtonsFragment extends Fragment{

    //Tag for logging
    private static final String TAG = GameboardButtonsFragment.class.getSimpleName();

    OnGameboardClickListener myCallBack;

    public GameboardButtonsFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myCallBack = (OnGameboardClickListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_gameboard_buttons, container, false);

        final ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item,R.id.list_item_textview,GameLogUtils.getLogItems());
        ListView listGameLog = (ListView) rootView.findViewById(R.id.log_list_2d);
        listGameLog.setAdapter(itemAdapter);

        Button upButton = (Button) rootView.findViewById(R.id.up_btn);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCallBack.upClick();
                itemAdapter.notifyDataSetChanged();
            }
        });
        Button downButton = (Button)rootView.findViewById(R.id.down_btn);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCallBack.downClick();
                itemAdapter.notifyDataSetChanged();
            }
        });
        Button leftButton = (Button)rootView.findViewById(R.id.left_btn);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCallBack.leftClick();
                itemAdapter.notifyDataSetChanged();
            }
        });
        Button rightButton = (Button)rootView.findViewById(R.id.right_btn);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCallBack.rightClick();
                itemAdapter.notifyDataSetChanged();
            }
        });

        return rootView;
    }


}
