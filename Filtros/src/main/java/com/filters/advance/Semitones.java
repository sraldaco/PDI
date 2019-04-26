package com.filters.advance;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Semitones {

    /* Filtro de una imagen que aplica un efecto semitono con matriz espiral.
     * @param bi Es el buffer que controla la imagen.
     * @param tam Es la cantidad de pixeles por lado de cada mosaico.
     * @return Devuelve el buffer con los pixeles en mosaicos.
     */
    public static BufferedImage aplicar(int tam, BufferedImage img){
        //Recorre las imagenes y obtiene el color de la imagen original y la almacena en el destino.
        int w = img.getWidth(), h = img.getHeight(), x = 0, y = 0, r = 0, g = 0, b = 0, m = 0, n = 0;
        // crea un buffer local para no alterar el buffer que recibe
        BufferedImage bi = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        while (x < w){
            m = ((x + tam) < w)? tam : w - x;
            while (y < h){
                n = ((y + tam) < h)? tam : h - y;
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++){
                        Color c = new Color(img.getRGB(i + x, j + y));
                        r += c.getRed(); g += c.getGreen(); b += c.getBlue();
                    }
                } r /= (m * n); g /= (m * n); b /= (m * n);
                // se saca el nivel de blanco
                float level = ((float)((r + g + b) /3) / 255f) * (float)(tam*tam);
                // se obtiene la matriz correspondiente
                int[][] matrix = getMatrix(tam, (int)level);
                //Se aplica el color correspondiente de la matriz.
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        int px = matrix[i][j];
                        bi.setRGB(i + x, j + y, new Color(px,px,px).getRGB());
                    }
                } y += n; r = 0; g = 0; b = 0;
            } x += m; y = 0;
        } return bi;
    }

    /*
     * @n tamaño de la matriz
     * @level cantidad de blanco
     * @return matrix, matriz de n*n en forma de espiral parcial.
     */
    private static int[][] getMatrix(int n, int level) {
        int init = 0, end = n-1, count = 1;
        int[][] matrix = new int[n][n];
        while (count <= n*n) {
            for (int i = init; i <= end; i++) {
                matrix[init][i] = count++ <= level ? 255 : 0;
            }
            for (int i = init+1; i <= end; i++) {
                matrix[i][end] = count++ <= level ? 255 : 0;
            }
            for (int i = end-1; i >= init; i--) {
                matrix[end][i] = count++ <= level ? 255 : 0;
            }
            for (int i = end-1; i >= init+1; i--) {
                matrix[i][init] = count++ <= level ? 255 : 0;
            }
            init++; end--;
        }
        return  matrix;
    }
}
