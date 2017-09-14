package com.example.dudar.flyinthecube;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dudar on 25.08.2017.
 */

public class PlayButtonFragment extends Fragment {

    //Tag for logging
    private static final String TAG = PlayButtonFragment.class.getSimpleName();

    public PlayButtonFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_button, container, false);

        return rootView;
    }
}
