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
public class Inverse {
    /* Filtro de una imagen que invierte los colores de una imagen.
     * @param img es el buffer de la imagen original.
     * @return Devuelve un buffer con los colores invertidos.
     */
    public static BufferedImage aplicar(BufferedImage img){
        // saca la medida ancho y alto del buffer que recibe
        int w = img.getWidth(); int h = img.getHeight();
        // crea un buffer local para no alterar el buffer que recibe
        BufferedImage bi = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        // recorre los pixeles del buffer 
        for(int i = 0; i<w; i++){
            for(int j = 0; j<h; j++){
                // obtiene el color del pixel
                Color c = new Color(img.getRGB(i, j));
                // invierte los colores
                c = new Color(0xFF - c.getRed(), 0xFF - c.getGreen(), 0xFF - c.getBlue());
                // asigna el color invertido en el pixel de buffer
                bi.setRGB(i,j,c.getRGB());
            }
        }
        return bi;
    }
}
