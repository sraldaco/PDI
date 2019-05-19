package com.filters.service;

import java.io.Serializable;

public class CoordsColor implements Serializable {

    private static final long serialVersion = 1L;

    private int red;
    private int green;
    private int blue;

    public CoordsColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed () { return red; }

    public int getGreen () { return green; }

    public int getBlue () { return blue; }

    public int getDistance () {
        return (int) Math.sqrt((double) (red*red + green*green + blue*blue));
    }

    public double getDistance (CoordsColor coords) {
        int cRed = coords.getRed();
        double r = (red + cRed) / 2d;
        double dR = red - cRed;
        double dG = green - coords.getGreen();
        double dB = blue - coords.getBlue();
        double arg1 = (2d + r / 256d) * (dR * dR);
        double arg2 = 4d * (dG * dG);
        double arg3 = (2 + (255d - r) / 256d) * (dB * dB);
        return Math.sqrt(arg1 + arg2 + arg3);
    }

    public String toString() {
        return "rgb(" + red + "," + green + "," + blue + ")";
    }
}
