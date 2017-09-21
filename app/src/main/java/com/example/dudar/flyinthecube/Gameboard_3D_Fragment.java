package com.example.dudar.flyinthecube;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dudar on 18.09.2017.
 */

public class Gameboard_3D_Fragment extends Fragment {

    //Tag for logging
    private static final String TAG = Gameboard_3D_Fragment.class.getSimpleName();

    public Gameboard_3D_Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_3d_gameboard, container, false);
        return rootview;
    }
}
