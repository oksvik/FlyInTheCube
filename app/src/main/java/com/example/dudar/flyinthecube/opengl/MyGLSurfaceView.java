package com.example.dudar.flyinthecube.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRenderer myRenderer;

    public MyGLSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        myRenderer = new MyGLRenderer(context);
        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(myRenderer);

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

    }
}
