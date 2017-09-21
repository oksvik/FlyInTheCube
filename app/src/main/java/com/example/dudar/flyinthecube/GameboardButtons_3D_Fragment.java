package com.example.dudar.flyinthecube;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by dudar on 20.09.2017.
 */

public class GameboardButtons_3D_Fragment extends Fragment {

    //Tag for logging
    private static final String TAG = GameboardButtonsFragment.class.getSimpleName();

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

        Button upButton = (Button) rootView.findViewById(R.id.up_3d_btn);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCallBack.upClick();
            }
        });
        Button downButton = (Button)rootView.findViewById(R.id.down_3d_btn);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCallBack.downClick();
            }
        });
        Button leftButton = (Button)rootView.findViewById(R.id.left_3d_btn);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCallBack.leftClick();
            }
        });
        Button rightButton = (Button)rootView.findViewById(R.id.right_3d_btn);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCallBack.rightClick();
            }
        });

        Button forwardButton = (Button) rootView.findViewById(R.id.frwd_3d_btn);
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCallBack.forwardClick();
            }
        });
        Button backButton = (Button) rootView.findViewById(R.id.back_3d_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCallBack.backClick();
            }
        });

        return rootView;

    }
}
