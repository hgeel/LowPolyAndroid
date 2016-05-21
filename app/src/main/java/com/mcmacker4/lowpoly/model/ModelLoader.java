package com.mcmacker4.lowpoly.model;

import com.mcmacker4.lowpoly.util.BufferUtils;

import java.nio.FloatBuffer;

import static android.opengl.GLES30.*;

/**
 * Created by McMacker4 on 20/05/2016.
 */
public class ModelLoader {

    public static Model createModel(float[] vertices, float[] colors) {
        int vao = createVAO();
        glBindVertexArray(vao);
        storeInAttribute(0, 3, vertices);
        storeInAttribute(1, 3, colors);
        glBindVertexArray(0);
        return new Model(vao, vertices.length / 3);
    }

    private static void storeInAttribute(int index, int size, float[] data) {
        int vbo = createVBO();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        glBufferData(GL_ARRAY_BUFFER, data.length * 4, buffer, GL_STATIC_DRAW);
        glVertexAttribPointer(index, size, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(index);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    private static int createVAO() {
        int[] i = new int[1];
        glGenVertexArrays(1, i, 0);
        return i[0];
    }

    private static int createVBO() {
        int[] i = new int[1];
        glGenBuffers(1, i, 0);
        return i[0];
    }

}
