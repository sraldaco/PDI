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
public class RGB {
    /* Filtro de una imagen que aplica altera los colores rgb de una imagen.
     * @param r,g,b son los colores que estarán disponibles en la imagen.
     * @param img es el buffer de la imagen original.
     * @return Devuelve un buffer con los colores seleccionados modificado.
     */
    public static BufferedImage convert(boolean r, boolean g, boolean b, BufferedImage img){
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
            c = new Color(r ? c.getRed() : 0, g ? c.getGreen() : 0, b ? c.getBlue() : 0);
            // aplica los colores en el pixel del buffer local
            bi.setRGB(i,j,c.getRGB());
          }
        }
        // regresa el buffer local
        return bi;
    }
}
