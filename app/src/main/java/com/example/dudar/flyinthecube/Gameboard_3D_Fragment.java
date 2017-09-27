package com.example.dudar.flyinthecube;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dudar.flyinthecube.opengl.MyGLSurfaceView;

/**
 * Created by dudar on 18.09.2017.
 */

public class Gameboard_3D_Fragment extends Fragment {

    //Tag for logging
    private static final String TAG = Gameboard_3D_Fragment.class.getSimpleName();

    GLSurfaceView myGLView;

    public Gameboard_3D_Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myGLView = new MyGLSurfaceView(getActivity());

        //View rootview = inflater.inflate(R.layout.fragment_3d_gameboard, container, false);
        //return rootview;
        return myGLView;
    }
}
