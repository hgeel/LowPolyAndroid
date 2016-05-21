package com.mcmacker4.lowpoly;

import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * Created by McMacker4 on 21/05/2016.
 */
public class Entity {

    private Vector3f position;
    private Vector3f rotation;
    private float scale;

    public Entity(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Matrix4f getModelMatrix() {
        return new Matrix4f().translate(position)
                .rotateX(rotation.x)
                .rotateY(rotation.y)
                .rotateZ(rotation.z)
                .scale(scale);
    }

}
