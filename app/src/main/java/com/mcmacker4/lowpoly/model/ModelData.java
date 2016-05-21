package com.mcmacker4.lowpoly.model;

/**
 * Created by McMacker4 on 20/05/2016.
 */
public class ModelData {

    private float[] vertices;
    private float[] normals;

    public ModelData(float[] vertices, float[] normals) {
        this.normals = normals;
        this.vertices = vertices;
    }

    public float[] getNormals() {
        return normals;
    }

    public float[] getVertices() {
        return vertices;
    }
}
