/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.filters.advance;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author alex aldaco
 */
public class WaterMark {
    
    /* Filtro de una imagen que aplica altera los colores rgb de una imagen.
     * @param r,g,b son los colores que estarán disponibles en la imagen.
     * @param img es el buffer de la imagen original.
     * @return Devuelve un buffer con los colores seleccionados modificado.
     */
    public static BufferedImage remove(BufferedImage img){
        // saca la medida ancho y alto del buffer que recibe
        int w = img.getWidth(); int h = img.getHeight();
        // crea un buffer local para no alterar el buffer que recibe
        BufferedImage bi = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        // recorre los pixeles del buffer 
        for(int i = 0; i<w; i++){
            for(int j = 0; j<h; j++){
            // obtiene el color del pixel del buffer que recibe
            Color c = new Color(img.getRGB(i, j));
            // altera el color rgb del pixel según sean seleccionados 
            int r = c.getRed(); int g = c.getGreen(); int b = c.getBlue();
            float p = 0;
            if (r > g && r > b) {
                g = g + (r-g);
                b = b + (r-b);
                p = (float)(r+g+b)/3f;
                float av = getAverage(p);
                if(p <= 180f) {
                    r = g = b =  (int)(p*av);
                } else {
                    float pm = 255f - p;
                    r = g = b = (int)(p + pm*av);
                }
            }            
            c = new Color(r,g,b);
            // aplica los colores en el pixel del buffer local
            bi.setRGB(i,j,c.getRGB());
          }
        }
        // regresa el buffer local
        return bi;
    }
    
    private static float getAverage(float p) {
        if (p <= 18f || p > 237f) {
            return p <= 18f ? .40f : 1f;
        } else if (18f < p && p <= 36f || p <= 237f && p > 219f) {
            return p <= 36f ? .30f : .70f;
        } else if (36f < p && p <= 54f || p <= 219f && p > 201f) {
            return p <= 54f ? .20f : .40f;
        } else if (54f < p && p <= 72f || p <= 201f && p > 183f) {
            return p <= 72 ? .15f : .20f;
        } else if (72f < p && p <= 90f || p <= 183f && p > 165f) {
            return .10f;
        } else if (90f < p && p <= 108f || p <= 165f && p > 147f) {
            return .05f;
        } else {
            return 0f;
        }
    }
    
}
