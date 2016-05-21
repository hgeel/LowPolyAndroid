package com.mcmacker4.lowpoly.render;

import com.mcmacker4.lowpoly.Entity;
import com.mcmacker4.lowpoly.model.Model;

import static android.opengl.GLES30.*;

import java.util.HashMap;

/**
 * Created by McMacker4 on 21/05/2016.
 */
public class MasterRenderer {

    public static void render(Model model) {
        glBindVertexArray(model.getVao());
        glDrawArrays(GL_TRIANGLES, 0, model.getVertexCount());
        glBindVertexArray(0);
    }

}
