package com.mcmacker4.lowpoly.util;

import android.content.res.AssetManager;
import android.renderscript.RSInvalidStateException;

import com.mcmacker4.lowpoly.model.OBJLoader;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by McMacker4 on 20/05/2016.
 */
public class ResourceLoader {

    private static boolean instanceCreated = false;

    private AssetManager assets;
    HashMap<String, Resource> resources = new HashMap<>();
    Thread assetLoader;

    public ResourceLoader(AssetManager assets) {
        if(instanceCreated) throw new RSInvalidStateException("Resources already loaded.");
        instanceCreated = true;
        this.assets = assets;
    }

    public void loadResources() {
        assetLoader = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OBJLoader.loadOBJData(assets.open("terrain.obj"));
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        });
        assetLoader.start();
    }

    public boolean assetsLoaded() {
        return !assetLoader.isAlive();
    }

}
