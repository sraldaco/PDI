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
public class Mosaic {
    
    /* Filtro de una imagen que aplica un mosaico de pixeles.
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
                //Se aplica el color promedio del mosaico.
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) bi.setRGB(i + x, j + y, new Color(r, g, b).getRGB());
                } y += n; r = 0; g = 0; b = 0;
            } x += m; y = 0;
        } return bi;
    }
}
