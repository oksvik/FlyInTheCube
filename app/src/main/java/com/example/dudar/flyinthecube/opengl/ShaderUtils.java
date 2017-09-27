package com.example.dudar.flyinthecube.opengl;

import android.opengl.GLES20;

import static android.opengl.GLES20.glAttachShader;
import static android.opengl.GLES20.glCreateProgram;
import static android.opengl.GLES20.glLinkProgram;

/**
 * Created by dudar on 26.09.2017.
 */

public class ShaderUtils {

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    public static int createProgram(int vertexShaderId, int fragmentShaderId) {

        // create empty OpenGL ES Program
        final int programId = glCreateProgram();
        // add the vertex shader to program
        glAttachShader(programId, vertexShaderId);
        // add the fragment shader to program
        glAttachShader(programId, fragmentShaderId);
        // creates OpenGL ES program executables
        glLinkProgram(programId);

        return programId;
    }
}
