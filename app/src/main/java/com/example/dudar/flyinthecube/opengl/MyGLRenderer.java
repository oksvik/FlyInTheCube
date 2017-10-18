package com.example.dudar.flyinthecube.opengl;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class MyGLRenderer implements GLSurfaceView.Renderer {

    MainCube myCube;
    Context context;

    MyGLRenderer(Context context) {
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig eglConfig) {
        // Set the background frame color
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);

        myCube = new MainCube(context);
        myCube.bindData();
        myCube.createViewMatrix();
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        myCube.createProjectionMatrix(width,height);
        myCube.bindMatrix();
    }

    @Override
    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT|GLES20.GL_DEPTH_BUFFER_BIT);
        myCube.draw();
    }


}
