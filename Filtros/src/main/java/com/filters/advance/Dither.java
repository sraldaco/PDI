/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.filters.advance;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author alex aldaco
 */
public class Dither {
    // matriz ordenada
    private static final int[][] clustered = {{8,3,4},{6,1,2},{7,5,9}};
    // matriz dispersa
    private static final int[][] scattered = {{1,7,4},{5,8,3},{6,2,9}};
    
    /* Filtro de una imagen que aplica el efecto dither.
     * @param bi Es el buffer que controla la imagen.
     * @param mode: 0 aleatorio, 1 ordenado, 2 disperso
     * @return Devuelve el buffer con el dither aplicado.
     */
    public static BufferedImage apply(int mode, BufferedImage img){
        int tam = 3;
        //Recorre las imagenes y obtiene el color de la imagen original y la almacena en el destino.
        int w = img.getWidth(), h = img.getHeight(), x = 0, y = 0, m = 0, n = 0;
        // crea un buffer local para no alterar el buffer que recibe
        BufferedImage bi = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        // si el modo es diferente de aleatorio asignamos la matriz correspondiente
        int[][] mat = mode != 0 ? mode == 1 ? clustered : scattered : null; 
        while (x < w){
            m = ((x + tam) < w)? tam : w - x;
            while (y < h){
                n = ((y + tam) < h)? tam : h - y;
                // se recorre en áreas de 3x3 pixeles.
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++){ 
                        // obtiene los colores de la imagen en el área
                        Color c = new Color(img.getRGB(i + x, j + y));
                        int r = c.getRed(), g = c.getGreen(), b = c.getBlue();
                        // obtiene los valores de acuerdo al umbral en la posición de la matriz
                        // o en su caso de modo aleatorio
                        int threshold = mode != 0 ? mat[i][j] : ThreadLocalRandom.current().nextInt(0, 9);
                        r = threshold > (r / 28) ? 0 : 255;
                        g = threshold > (g / 28) ? 0 : 255;
                        b = threshold > (b / 28) ? 0 : 255;
                        //Se aplica el color correspondiente.
                        bi.setRGB(i + x, j + y, new Color(r, g, b).getRGB());
                    }
                } y += n;
            } x += m; y = 0;
        } return bi;
    }
    
    /* Filtro de una imagen que aplica el efecto dither difusión de error simple.
     * @param bi Es el buffer que controla la imagen.
     * @return Devuelve el buffer con el dither aplicado.
     */
    public static BufferedImage difusion(BufferedImage img){
        // alto y ancho de la imagen
        int w = img.getWidth(), h = img.getHeight();
        // crea un buffer local para no alterar el buffer que recibe
        BufferedImage bi = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        // difusor de error
        int dif = 0;
        for (int i = 0; i < w; i++) {
            for(int j = 0; j < h; j++){
                // obtiene los colores de la imagen en el pixel
                Color c = new Color(img.getRGB(i, j));
                int r = c.getRed(), g = c.getGreen(), b = c.getBlue();
                // se suma el difusor
                r += dif; g += dif; b += dif;
                // toma un rango corrector para el difuror siguiente
                r = r < 0 ? 0 : r > 255 ? 255 : r;
                g = g < 0 ? 0 : g > 255 ? 255 : g;
                b = b < 0 ? 0 : b > 255 ? 255 : b;
                int av = (r + g + b) / 3;
                r = r <= 127 ? 0 : 255;
                g = g <= 127 ? 0 : 255;
                b = b <= 127 ? 0 : 255;
                dif = av <= 127 ? av : - (255 - av);
                bi.setRGB(i, j, new Color(r, g, b).getRGB());
            }
        }
        return bi;
    }
}
