package com.example.dudar.flyinthecube.opengl;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;

import com.example.dudar.flyinthecube.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.glUniform1i;
import static android.opengl.GLES20.glUniformMatrix4fv;


public class MainCube {

    private Context context;

    private FloatBuffer vertexData;

    private int mPositionLocation;
    private int mColorLocation;
    private int uMatrixLocation;
    private int uTextureUnitLocation;
    private int aTextureLocation;

    private int texture;

    private final static long TIME = 10000;

    // number of coordinates per vertex in this array
    private static final int COORDS_PER_VERTEX = 3;
    private static final int COORD_TEXTURE = 2;

    private static final int STRIDE = (COORDS_PER_VERTEX + COORD_TEXTURE) * 4;

    static float z1 = 0.6f, z4 = -0.3f;

    private static float textureCubeCoord[] = {
            //front side
            -0.5f, 0.1f, z1, 0, 0,
            -0.5f, -0.8f, z1, 0, 1,
            0.4f, 0.1f, z1, 1, 0,
            -0.5f, -0.8f, z1, 0, 1,
            0.4f, 0.1f, z1, 1, 0,
            0.4f, -0.8f, z1, 1, 1,

            // top side
            -0.5f, 0.1f, z4, 0, 0,
            -0.5f, 0.1f, z1, 0, 1,
            0.4f, 0.1f, z1, 1, 1,
            -0.5f, 0.1f, z4, 0, 0,
            0.4f, 0.1f, z1, 1, 1,
            0.4f, 0.1f, z4, 1, 0,

            //right side
            0.4f, 0.1f, z1, 0, 0,
            0.4f, -0.8f, z1, 0, 1,
            0.4f, -0.8f, z4, 1, 1,
            0.4f, 0.1f, z1, 0, 0,
            0.4f, -0.8f, z4, 1, 1,
            0.4f, 0.1f, z4, 1, 0,
    };

    // Set color with red, green, blue and alpha (opacity) values
    float color[] = {0.4f, 0.7f, 1.0f, 0.1f};
    float colorLine[] = {0.0f, 0.0f, 0.0f, 1.0f};

    private final int mProgram;
    private float[] mProjectionMatrix = new float[16];
    private float[] mViewMatrix = new float[16];
    private float[] mModelMatrix = new float[16];
    private float[] mMatrix = new float[16];

    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "uniform mat4 u_Matrix;" +
                    "void main() {" +
                    "  gl_Position = u_Matrix * vPosition;" +
                    "  gl_PointSize = 5.0;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    private final String vertexShaderWithTextureCode =
            "attribute vec4 vPosition;" +
                    "uniform mat4 u_Matrix;" +
                    "attribute vec2 a_Texture;" +
                    "varying vec2 v_Texture;" +
                    "void main() {" +
                    "  gl_Position = u_Matrix * vPosition;" +
                    "  v_Texture = a_Texture;" +
                    "}";

    private final String fragmentShaderWithTextureCode =
            "precision mediump float;" +
                    "uniform sampler2D u_TextureUnit;" +
                    "varying vec2 v_Texture;" +
                    "void main() {" +
                    "gl_FragColor = texture2D(u_TextureUnit, v_Texture);" +
                    "}";

    public MainCube(Context ctx) {
        context = ctx;

        int vertexShader = ShaderUtils.loadShader(GLES20.GL_VERTEX_SHADER,
                //vertexShaderCode);
                vertexShaderWithTextureCode);
        int fragmentShader = ShaderUtils.loadShader(GLES20.GL_FRAGMENT_SHADER,
                //fragmentShaderCode);
                fragmentShaderWithTextureCode);
        mProgram = ShaderUtils.createProgram(vertexShader, fragmentShader);
        // Add program to OpenGL ES environment
        GLES20.glUseProgram(mProgram);

        prepareData();
    }

    private void prepareData() {
        ByteBuffer bb = ByteBuffer.allocateDirect(
                 //allCoords.length * 4);
                textureCubeCoord.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexData = bb.asFloatBuffer();
        vertexData.put(textureCubeCoord);
        vertexData.position(0);
    }

    void bindData() {
        getLocations();

        vertexData.position(0);
        GLES20.glUniform4fv(mColorLocation, 1, color, 0);
        // Enable a handle to the elements vertices
        GLES20.glEnableVertexAttribArray(mPositionLocation);
        // Prepare the coordinate data
        GLES20.glVertexAttribPointer(mPositionLocation, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                STRIDE, vertexData);
        //position for texture coordinates
        vertexData.position(COORDS_PER_VERTEX);
        GLES20.glVertexAttribPointer(aTextureLocation,COORD_TEXTURE,GL_FLOAT,false,STRIDE,vertexData);
        GLES20.glEnableVertexAttribArray(aTextureLocation);

    }

    private void getLocations() {
        // get handle to fragment shader's vColor member
        mColorLocation = GLES20.glGetUniformLocation(mProgram, "vColor");
        // get handle to vertex shader's vPosition member
        mPositionLocation = GLES20.glGetAttribLocation(mProgram, "vPosition");
        //matrix
        uMatrixLocation = GLES20.glGetUniformLocation(mProgram, "u_Matrix");
        //texture
        uTextureUnitLocation = GLES20.glGetUniformLocation(mProgram, "u_TextureUnit");
        aTextureLocation = GLES20.glGetAttribLocation(mProgram, "a_Texture");
    }

    void draw() {
        // Draw the triangle
        drawTriangles();

        //coordinate axises
        //drawAxes();
        //draw moveable triangle
        //drawMoveable();
    }

    private void drawTriangles() {
        Matrix.setIdentityM(mModelMatrix, 0);
        setModelMatrix();
        bindMatrix();

        bindTexture(R.drawable.qudrbigblue);
        //GLES20.glUniform4fv(mColorLocation, 1, color, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);

        bindTexture(R.drawable.quadbigtop);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 6, 6);

        bindTexture(R.drawable.quadbigside);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 12, 6);
    }

    private void bindTexture(int textureId) {
        texture = TextureUtils.loadTexture(context, textureId);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture);
        glUniform1i(uTextureUnitLocation, 0);
    }

    void createProjectionMatrix(int width, int height) {
        float ratio;
        float left = -1.0f;
        float right = 1.0f;
        float bottom = -1.0f;
        float top = 1.0f;
        float near = 1.0f;
        float far = 8.0f;
        if (width > height) {
            ratio = (float) width / height;
            left *= ratio;
            right *= ratio;
        } else {
            ratio = (float) height / width;
            bottom *= ratio;
            top *= ratio;
        }

        Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far);
    }

    void createViewMatrix() {
        float eyeX = 1.5f;
        float eyeY = 0.6f;
        float eyeZ = 2.25f;

        float centerX = 0;
        float centerY = 0;
        float centerZ = 0;

        float upX = 0;
        float upY = 1;
        float upZ = 0;

        Matrix.setLookAtM(mViewMatrix, 0, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    private void setModelMatrix() {
        //смещение
        //Matrix.translateM(mModelMatrix, 0, 1, 0, 0);

        //сжатие Scale
        Matrix.scaleM(mModelMatrix, 0, 1.8f, 1.8f, 2f);

        //поворот Rotate
        //Matrix.rotateM(mModelMatrix, 0, 45, 0, 0, 1);

        //float angle = (float)(SystemClock.uptimeMillis() % TIME) / TIME * 360;
        //Matrix.translateM(mModelMatrix, 0, 2f, 0, 0);
        //Matrix.rotateM(mModelMatrix, 0, angle, 1, 0, 0);
    }

    void bindMatrix() {
        Matrix.multiplyMM(mMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);
        Matrix.multiplyMM(mMatrix, 0, mProjectionMatrix, 0, mMatrix, 0);
        glUniformMatrix4fv(uMatrixLocation, 1, false, mMatrix, 0);
    }

    private void drawAxes() {
        Matrix.setIdentityM(mModelMatrix, 0);
        bindMatrix();

        GLES20.glLineWidth(5);
        GLES20.glUniform4f(mColorLocation, 0.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glDrawArrays(GLES20.GL_LINES, 26, 2);
        GLES20.glUniform4f(mColorLocation, 1.0f, 0.0f, 1.0f, 1.0f);
        GLES20.glDrawArrays(GLES20.GL_LINES, 28, 2);
        GLES20.glUniform4f(mColorLocation, 1.0f, 0.5f, 0.0f, 1.0f);
    }

    private void drawMoveable() {
        Matrix.setIdentityM(mModelMatrix, 0);
        setModelMatrix();
        bindMatrix();

        GLES20.glUniform4f(mColorLocation, 0.0f, 1.0f, 0.0f, 1.0f);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 32, 3);
    }
}
