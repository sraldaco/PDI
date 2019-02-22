/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.filters.basic;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author alex aldaco
 */
public class Contrast {
    /* Filtro que altera el contraste de una imagen.
     * @param level Es la intensidad del contraste.
     * @param hc es si se trata de alto contraste
     * @param img es el buffer de la imagen original.
     * @return Devuelve un buffer con el contraste modificado.
     */
    public static BufferedImage aplicar(boolean hc, int level, BufferedImage img){
        // saca la medida ancho y alto del buffer que recibe
        int w = img.getWidth(); int h = img.getHeight();
        // crea un buffer local para no alterar el buffer que recibe
        BufferedImage bi = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        // maneja un porcentaje respecto al nivel
        float lp = Math.abs(128f * ((float)level/100f));
        // recorre los pixeles del buffer 
        for(int i = 0; i<w; i++){
            for(int j = 0; j<h; j++){
                // obtiene el color del pixel
                Color c = new Color(img.getRGB(i, j));
                // se crean variables de rgb 
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                // sacamos un promedio
                int p = (r + g + b)/3;
                // si se requiere alto contraste
                if (hc) {
                    // si el balance es >= 127 se asigna blanco, de otro modo se asigna negro
                    int nc = p >= 127 ? 255 : 0;
                    // se crea el color con el balance
                    c = new Color(nc,nc, nc);
                // en otro caso    
                } else {
                    // si el nivel aumenta el contraste
                    if (level > 0) {
                        r = p >= 127 ? 
                            r + lp < 255 ? (int) (r + lp) : 255   :   
                            r - lp > 0 ?   (int) (r - lp) : 0;
                        g = p >= 127 ? 
                            g + lp < 255 ? (int) (g + lp) : 255   :   
                            g - lp > 0 ?   (int) (g - lp) : 0;
                        b = p >= 127 ? 
                            b + lp < 255 ? (int) (b + lp) : 255   :   
                            b - lp > 0 ?   (int) (b - lp) : 0;
                    // si el nivel reduce el contraste    
                    } else if (level < 0) {
                        r = p >= 127 ? 
                            r - lp > 127 ? (int) (r - lp) : 127   :   
                            r + lp < 127 ? (int) (r + lp) : 127;
                        g = p >= 127 ? 
                            g - lp > 127 ? (int) (g - lp) : 127   :   
                            g + lp < 127 ? (int) (g + lp) : 127;
                        b = p >= 127 ? 
                            b - lp > 127 ? (int) (b - lp) : 127   :   
                            b + lp < 127 ? (int) (b + lp) : 127;
                    }
                    c = new Color(r,g,b);
                }
                // se aplica el nuevo color al buffer local
                bi.setRGB(i,j,c.getRGB());
            }
        }
        return bi;
    }
}
