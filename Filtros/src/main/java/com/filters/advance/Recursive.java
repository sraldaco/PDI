/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.filters.advance;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author alex aldaco
 */
public class Recursive {
    
    /* Filtro que aplica el efecto de imagen recursiva.
     * @param img es el buffer de la imagen original.
     * @param sg indica si se requiere una salida en escala de grises.
     * @param size es el porcentaje de reducción de la imagen.
     * @return Devuelve el buffer con el filtro aplicado.
     */
    public static BufferedImage apply(boolean sg, float size, float brightness, BufferedImage img) {
        // saca la medida ancho y alto del buffer que recibe
        int w = img.getWidth(), h = img.getHeight(), x = 0, y = 0, m = 0, n = 0;
        // medidas escaladas
        int wa = (int) ((float)w * size);
        int ha = (int) ((float)h * size);
        // crea un buffer local para no alterar el buffer que recibe
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        BufferedImage mbi = new BufferedImage(wa, ha, BufferedImage.TYPE_INT_RGB);
        Graphics2D gr = mbi.createGraphics();
        gr.drawImage(img, 0, 0, wa, ha, null);
        gr.dispose();
        // recorre los pixeles del buffer 
        while (x < w){
            m = ((x + wa) < w)? wa : w - x;
            while (y < h){
                n = ((y + ha) < h)? ha : h - y;
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++){
                        Color c = new Color(img.getRGB(i + x, j + y));
                        Color mc = new Color(mbi.getRGB(i, j));
                        Color proc = getColor(c, mc, brightness);
                        if (sg) {
                            int r = proc.getRed(), g = proc.getGreen(), b = proc.getBlue();
                            int av = (r + g + b) / 3;
                            proc = new Color(av, av, av);
                        }
                        bi.setRGB(i + x, j + y, proc.getRGB());
                    }
                } y += n;
            } x += m; y = 0;
        } return bi;
    }
    
    private static Color getColor(Color c, Color mc, float brightness){
        float r = c.getRed(), g = c.getGreen(), b = c.getBlue();
        float mr = mc.getRed(), mg = mc.getGreen(), mb = mc.getBlue();
        // suma de colores
        float sumc = (r + g + b), summc = (mr + mg + mb);
        float range = summc / 255f;
        
        if (sumc > summc) {
            range = -1 + range;
        }
        r = r + r * brightness * range;
        g = g + g * brightness * range;
        b = b + b * brightness * range;
        r = r > 255 ? 255 : r < 0 ? 0 : r;
        g = g > 255 ? 255 : g < 0 ? 0 : g;
        b = b > 255 ? 255 : b < 0 ? 0 : b;
        return new Color((int)r,(int)g,(int)b);
    }
}
