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
public class Oleo {
    
    /* Filtro que aplica el efecto pintura al óleo.
     * @param img es el buffer de la imagen original.
     * @return Devuelve el buffer con el filtro aplicado.
     */
    public static BufferedImage apply(boolean sg, int size, BufferedImage img) {
        // saca la medida ancho y alto del buffer que recibe
        int w = img.getWidth(); int h = img.getHeight();
        // crea un buffer local para no alterar el buffer que recibe
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        // recorre los pixeles del buffer 
        for(int i = 0; i < w; i++){
            for(int j = 0; j < h; j++){
                // define un radio de exploración de tamaño size
                int x = i + size > w ? w : i + size; 
                int y = j + size > h ? h : j + size; 
                // guarda los colores en un radio determinado y la cantidad de veces que aparece
                HashMap<Color,Integer> freq = new HashMap<>();
                // recorre el radio determinado
                for (int n = i; n < x; n++) {
                    for (int m = j; m < y; m++) {
                        // obtiene el color en la posición del radio de exploración
                        Color c = new Color(img.getRGB(n, m));
                        // si no está registrado se registra
                        if (!freq.containsKey(c)) freq.put(c, 1);
                        // si ya está registrado se incrementa en uno su valor
                        else freq.replace(c, freq.get(c) + 1);
                    }
                }
                Color ci = new Color(0,0,0);
                int selected = 0;
                // obtiene el color que más veces aparece en el radio de exploración
                for (Entry<Color,Integer> entry : freq.entrySet()) 
                    if (entry.getValue() > selected) ci = entry.getKey();
                if (sg) {
                    int av = (ci.getRed() + ci.getGreen() + ci.getBlue()) / 3;
                    ci = new Color(av, av, av);
                }
                // se asigna el color en el pixel 
                bi.setRGB(i, j, ci.getRGB());
            }
        }
        return bi;
    }
}

