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
                float av;
                if(p <= 220f) {
                    av = p < 45f ? .1f : 
                         p < 80f ? .2f : 
                         p < 120f ? .4f : 
                         p < 150f ? .7f : 
                         p < 170f ? .90f :  1f;
                    r = g = b =  (int)(p*av);
                } else {
                    float pm = 255f - p;
                    av = p < 230 ? 0f: 1f; 
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
    
   
}
