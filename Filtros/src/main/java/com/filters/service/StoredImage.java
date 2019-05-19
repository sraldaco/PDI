package com.filters.service;

import java.io.Serializable;

public class StoredImage implements Serializable {

    private static final long serialVersion = 1L;

    private CoordsColor coords;
    private String filename;

    public StoredImage(CoordsColor coords, String filename) {
        this.coords = coords;
        this.filename = filename;
    }

    public CoordsColor getCoords() {
        return coords;
    }

    public String getFilename () { return filename; }

    public String toString() {
        return coords + ": file -> " +  filename;
    }
}
