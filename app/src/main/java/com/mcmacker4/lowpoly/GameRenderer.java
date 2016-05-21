package com.mcmacker4.lowpoly;

import android.content.res.AssetManager;
import android.opengl.GLSurfaceView;

import com.mcmacker4.lowpoly.render.MasterRenderer;
import com.mcmacker4.lowpoly.util.ResourceLoader;

import org.joml.Matrix4f;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES30.*;

/**
 * Created by McMacker4 on 19/05/2016.
 */
public class GameRenderer implements GLSurfaceView.Renderer {

    Matrix4f projectionMatrix = new Matrix4f();
    ResourceLoader rsLoader;

    public GameRenderer(AssetManager assets) {
        rsLoader = new ResourceLoader(assets);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glClearColor(0.3f, 0.6f, 1.0f, 1.0f);
        rsLoader.loadResources();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0, 0, width, height);
        projectionMatrix.setPerspective((float) Math.toRadians(80f), (float) width / height, 0.1f, 100f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}
