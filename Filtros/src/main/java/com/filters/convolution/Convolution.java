/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.filters.convolution;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author alex aldaco
 */
public class Convolution {
    /**
     * Filtro de convulsion sobre una imagen con una matriz y factor dados.
     * @param bi Es el buffer que contiene la imagen a filtrar.
     * @param matriz Es la matriz con la que se aplica el filtro.
     * @param factor Es el factor de contribución en la suma de pixeles.
     * @return Devuelve una imagen con el filtro de convulsion aplicado.
     */
    public static BufferedImage convolution(BufferedImage img, double[][] matriz, double factor, double bias) {
        //Se definen las variables auxiliares.
        int w = img.getWidth(), h = img.getHeight(), tam = matriz.length, rad = tam/2;
        // crea un buffer local para no alterar el buffer que recibe
        BufferedImage bi = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        double r = 0, g = 0, b = 0;
        // Se guardan los colores originales de los pixeles.
        Color[][] original = new Color[w][h];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) original[x][y] = new Color(img.getRGB(x, y));
        } // Se recorre la matriz para calcular los nuevos colores.
        for (int x = 0; x < w; x++) {
            // Se definen los limites horizontales de la matriz segun la posicion del pixel actual.
            int xi = (x < rad)? rad - x : 0, xf = ((w - x) <= rad)? rad + w - x : tam;
            for (int y = 0; y < h; y++, r = 0, g = 0, b = 0){
                // Se definen los limites verticales de la matriz segun la posicion del pixel actual.
                int yi = (y < rad)? rad - y : 0, yf = ((h - y) <= rad)? rad + h - y : tam;
                for (int i = xi, px = x - rad; i < xf; i++) {
                    for (int j = yi, py = y - rad; j < yf; j++) {
                        double val = matriz[i][j];
                        r += (original[px + i][py + j].getRed() * val);
                        g += (original[px + i][py + j].getGreen() * val);
                        b += (original[px + i][py + j].getBlue() * val);
                    }
                } r = r * factor + bias; g = g * factor + bias; b = b * factor + bias;
                bi.setRGB(x, y, new Color(normalize((int)r), normalize((int)g), normalize((int)b)).getRGB());
            }
        } return bi;
    }
    
    /**
     * Filtro de convolucion mediana de imagen con una matriz de 3x3.
     * @param bi Es el buffer que contiene la imagen a filtrar.
     * @return Devuelve una imagen con el filtro aplicado.
     */
    public static BufferedImage median(BufferedImage bi) {
        //Se definen las variables auxiliares.
        int w = bi.getWidth(), h = bi.getHeight(), tam = 3, rad = tam/2, r[], g[], b[];
        // Se guardan los colores originales de los pixeles.
        Color[][] original = new Color[w][h];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) original[x][y] = new Color(bi.getRGB(x, y));
        } // Se recorre la matriz para calcular los nuevos colores.
        for (int x = 0; x < w; x++) {
            // Se definen los limites horizontales de la matriz segun la posicion del pixel actual.
            int xi = (x < rad)? rad - x : 0, xf = ((w - x) <= rad)? rad + w - x : tam;
            for (int y = 0; y < h; y++){
                // Se definen los limites verticales de la matriz segun la posicion del pixel actual.
                int yi = (y < rad)? rad - y : 0, yf = ((h - y) <= rad)? rad + h - y : tam;
                // Se definen los arreglos auxiliares de color para calcular la mediana de cada componente.
                r = new int[(xf - xi) * (yf - yi)]; g = new int[r.length]; b = new int[r.length];
                for (int i = 0, px = x - rad; (i + xi) < xf; i++) {
                    for (int j = 0, py = y - rad; (j + yi) < yf; j++) {
                        r[j + (yf - yi) * i] = original[px + i + xi][py + j + yi].getRed();
                        g[j + (yf - yi) * i] = original[px + i + xi][py + j + yi].getGreen();
                        b[j + (yf - yi) * i] = original[px + i + xi][py + j + yi].getBlue();
                    }
                } bi.setRGB(x, y, new Color(mediana(r), mediana(g), mediana(b)).getRGB());
            }
        } return bi;
    }

    private static int mediana(int ... x) {
        int xn = x.length, m = -1;
        for (int i = 0, j = 0; i <= (xn / 2); i++, j = i) {
            for (int k = i + 1; k < xn; k++) { if(x[j] > x[k]) j = k; }
            m = x[j]; x[j] = x[i]; x[i] = m;
        } if((xn % 2) == 0) return (m + x[xn / 2 - 1]) / 2;
        return m;
    }
    
    private static int normalize(int n) { return (n < 0)? 0 : ((n < 256)? n : 255);}
}
