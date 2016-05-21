package com.mcmacker4.lowpoly;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by McMacker4 on 19/05/2016.
 */
public class GameView extends GLSurfaceView {

    private Renderer renderer;

    public GameView(Context context) {
        super(context);
        setEGLContextClientVersion(3);
        renderer = new GameRenderer(context.getAssets());
        setRenderer(renderer);
    }

}
