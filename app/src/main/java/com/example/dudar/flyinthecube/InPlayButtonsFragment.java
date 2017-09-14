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
 * Created by dudar on 25.08.2017.
 */

public class InPlayButtonsFragment extends Fragment {

    OnPlayButtonClickListener myCallBack;

    //Tag for logging
    private static final String TAG = InPlayButtonsFragment.class.getSimpleName();

    interface OnPlayButtonClickListener{
        void onPlayClick();
        void onCheckClick();
        void onRestartClick();
    }



    public InPlayButtonsFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //This makes sure that the host activity has implemented the callback interface
        //if not, it throws an exception
        try {
            myCallBack = (OnPlayButtonClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnPlayButtonClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_inplay_buttons, container, false);

        Button playBtn = (Button) rootView.findViewById(R.id.play_btn);
        Button checkBtn = (Button) rootView.findViewById(R.id.check_btn);
        Button restartBtn = (Button) rootView.findViewById(R.id.restart_btn);
        //checkBtn.setVisibility(View.GONE);
        //restartBtn.setVisibility(View.GONE);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCallBack.onPlayClick();
            }
        });

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCallBack.onCheckClick();
            }
        });

        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCallBack.onRestartClick();
            }
        });

        return rootView;
    }

}
