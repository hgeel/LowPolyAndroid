package com.mcmacker4.lowpoly.util;

/**
 * Created by McMacker4 on 20/05/2016.
 */
public class Resource<T> {

    T resource;

    public Resource(T resource) {
        this.resource = resource;
    }

    public T getResource() {
        return resource;
    }

}
