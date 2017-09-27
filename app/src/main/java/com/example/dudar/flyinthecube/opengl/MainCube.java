package com.example.dudar.flyinthecube.opengl;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by dudar on 26.09.2017.
 */

public class MainCube {

    private FloatBuffer vertexData;

    private int mPositionHandle;
    private int mColorHandle;

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    static float allCoords[] = {
            -0.8f, 0.1f, 0.0f,   // top left
            -0.8f, -0.8f, 0.0f,   // bottom left
            0.1f, 0.1f, 0.0f,   // bottom right
            -0.8f, -0.8f, 0.0f,   // bottom left
            0.1f, 0.1f, 0.0f,   // bottom right
            0.1f, -0.8f, 0.0f,  // top right

            -0.8f, -0.2f, 0.0f,   //first horizontal line
            0.1f, -0.2f, 0.0f,
            -0.8f, -0.5f, 0.0f,  //second horizontal line
            0.1f, -0.5f, 0.0f,

            -0.5f, 0.1f, 0.0f,
            -0.5f, -0.8f, 0.0f,
            -0.2f, 0.1f, 0.0f,
            -0.2f, -0.8f, 0.0f,
    };

    // Set color with red, green, blue and alpha (opacity) values
    float color[] = {0.4f, 0.7f, 1.0f, 0.1f};
    float colorLine[] = {1.0f, 1.0f, 1.0f, 1.0f};

    private final int mProgram;

    public MainCube() {

        int vertexShader = ShaderUtils.loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = ShaderUtils.loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);
        mProgram = ShaderUtils.createProgram(vertexShader, fragmentShader);
        // Add program to OpenGL ES environment
        //GLES20.glUseProgram(mProgram);

        prepareData();
    }

    private void prepareData() {
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                allCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexData = bb.asFloatBuffer();
        vertexData.put(allCoords);
        vertexData.position(0);
    }

    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    private final int vertexCount = allCoords.length / COORDS_PER_VERTEX;
    //private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    public void draw() {

        GLES20.glUseProgram(mProgram);

        bindData();

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);// vertexCount);
        //lines
        GLES20.glLineWidth(5);
        GLES20.glUniform4fv(mColorHandle, 1, colorLine, 0);
        GLES20.glDrawArrays(GLES20.GL_LINES, 6, 8);//vertexCount);
    }

    private void bindData() {
        // get handle to fragment shader's vColor member
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        // Set color for drawing the elements
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        // Enable a handle to the elements vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        // Prepare the coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                //vertexStride, vertexData);
                0, vertexData);
    }
}
