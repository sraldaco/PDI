/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.filters.advance;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author alex aldaco
 */
public class Sepia {
    
    /* Filtro que aplica el efecto pintura al óleo.
     * @param img es el buffer de la imagen original.
     * @return Devuelve el buffer con el filtro aplicado.
     */
    public static BufferedImage apply(int depth, int intensity, BufferedImage img) {
        // saca la medida ancho y alto del buffer que recibe
        int w = img.getWidth(); int h = img.getHeight();
        // crea un buffer local para no alterar el buffer que recibe
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        // profundidad del tono sepia
        float sepiaDepth = (float)depth;
        // recorre los pixeles del buffer 
        for(int i = 0; i < w; i++){
            for(int j = 0; j < h; j++){
                // obtiene el color del pixel de la imagen
                Color c = new Color(img.getRGB(i, j));
                int r = c.getRed(), g = c.getGreen(), b = c.getBlue(); 
                // saca el promedio
                int av = (r + g + b) / 3;
                r = g = b = av;
                r = r + (int)(sepiaDepth * 1.8f) > 255 ? 255 : r + (int)(sepiaDepth * 1.8f);
                g = g + (int)sepiaDepth > 255 ? 255 : g + (int)sepiaDepth;
                b = b - intensity < 0 ? 0 : b - intensity;
                // se asigna el color en el pixel 
                bi.setRGB(i, j, new Color(r,g,b).getRGB());
            }
        }
        return bi;
    }
}

