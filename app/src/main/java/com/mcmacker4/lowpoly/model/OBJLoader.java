package com.mcmacker4.lowpoly.model;

import android.util.Log;

import org.joml.Vector2f;
import org.joml.Vector3f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by McMacker4 on 20/05/2016.
 */
public class OBJLoader {

    private static Pattern vertexPattern = Pattern.compile("^v (\\-?\\d+\\.\\d+) (\\-?\\d+\\.\\d+) (\\-?\\d+\\.\\d+)");
    private static Pattern normalPattern = Pattern.compile("^vn (\\-?\\d+\\.\\d+) (\\-?\\d+\\.\\d+) (\\-?\\d+\\.\\d+)");
    private static Pattern texCoordsPattern = Pattern.compile("^vt (\\d\\.\\d+) (\\d\\.\\d+)");
    private static Pattern facePattern = Pattern.compile("^f ((\\d*)/(\\d*)/(\\d*)) ((\\d*)/(\\d*)/(\\d*)) ((\\d*)/(\\d*)/(\\d*))");

    private static Vector<Vector3f> vertices = new Vector<>();
    private static Vector<Vector2f> texCoords = new Vector<>();
    private static Vector<Vector3f> normals = new Vector<>();
    private static Vector<Vector3f> ordVertices = new Vector<>();
    private static Vector<Vector2f> ordTexCoords = new Vector<>();
    private static Vector<Vector3f> ordNormals = new Vector<>();

    public static ModelData loadOBJData(InputStream inputStream) {
        reset();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                parseLine(line);
            }
            reader.close();
            return new ModelData(toArray3(vertices), toArray3(normals));
        } catch(IOException e) {
            Log.e("OBJLoader", e.getMessage(), e);
        }
        return null;
    }

    private static void parseLine(String line) {
        Matcher m;
        if((m = vertexPattern.matcher(line)).matches()) {
            vertices.add(new Vector3f(
                    Float.parseFloat(m.group(1)),
                    Float.parseFloat(m.group(2)),
                    Float.parseFloat(m.group(3))
            ));
        } else if((m = normalPattern.matcher(line)).matches()) {
            normals.add(new Vector3f(
                    Float.parseFloat(m.group(1)),
                    Float.parseFloat(m.group(2)),
                    Float.parseFloat(m.group(3))
            ));
        } else if((m = texCoordsPattern.matcher(line)).matches()) {
            texCoords.add(new Vector2f(
                    Float.parseFloat(m.group(1)),
                    Float.parseFloat(m.group(2))
            ));
        } else if((m = facePattern.matcher(line)).matches()) {
            parseVertex(m.group(2), m.group(3), m.group(4));
            parseVertex(m.group(6), m.group(7), m.group(8));
            parseVertex(m.group(10), m.group(11), m.group(12));
        }
    }

    private static void parseVertex(String n1, String n2, String n3) {
        ordVertices.add(vertices.get(Integer.parseInt(n1)));
        ordTexCoords.add(texCoords.get(Integer.parseInt(n1)));
        ordNormals.add(normals.get(Integer.parseInt(n1)));
    }

    private static float[] toArray3(List<Vector3f> list) {
        float[] array = new float[list.size() * 3];
        int pointer = 0;
        for(int i = 0; i < list.size(); i++) {
            Vector3f vector = list.get(i);
            array[pointer++] = vector.x;
            array[pointer++] = vector.y;
            array[pointer++] = vector.z;
        }
        return array;
    }

    private static float[] toArray2(List<Vector2f> list) {
        float[] array = new float[list.size() * 2];
        int pointer = 0;
        for(int i = 0; i < list.size(); i++) {
            Vector2f vector = list.get(i);
            array[pointer++] = vector.x;
            array[pointer++] = vector.y;
        }
        return array;
    }

    private static void reset() {
        vertices.clear();
        normals.clear();
        texCoords.clear();
        ordNormals.clear();
        ordTexCoords.clear();
        ordVertices.clear();
    }

}
