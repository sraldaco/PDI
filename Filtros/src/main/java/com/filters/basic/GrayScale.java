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
public class GrayScale {
    /* Filtro que convierte una imagen a escala de grises.
     * @param img es el buffer de la imagen original.
     * @return Devuelve un buffer con el brillo modificado.
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
                // obtiene el balance en gris
                int gs = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                c = new Color(gs,gs,gs);
                // se aplica el balance en gris
                bi.setRGB(i,j,c.getRGB());
            }
        }
        return bi;
    }
}
