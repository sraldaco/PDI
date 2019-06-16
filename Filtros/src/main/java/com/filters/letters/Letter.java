/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.filters.letters;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author alex aldaco
 */
public class Letter {

    public static BufferedImage apply(List<String> chars, String font, int size, boolean sg,BufferedImage img){
        int w = img.getWidth();
        int h = img.getHeight();
        float cell = 255f / (float) chars.size();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bi.getGraphics();
        graphics.fillRect(0, 0, w, h);
        graphics.setFont(new Font(font, Font.TRUETYPE_FONT, size));
        for(int i = 0; i<w; i+=size){
            for(int j = 0; j<h; j+=size){
              Color c = new Color(img.getRGB(i,j));
              float p = (float)(c.getRed() + c.getGreen() + c.getBlue()) /3f;
              if (sg) {
                  c = new Color((int)p,(int)p,(int)p);
              }
              String letter = "";
              float s = 0f;
              int count = 1;
              while((s = (float)count * cell) < p) {
                  count++;
              }
              count = count > chars.size() -1 ? chars.size() -1 : count;
              letter = chars.get(count);
              graphics.setColor(c);
              graphics.drawString(letter, i, j);
            }
        }
        return bi;
    }

    // Binary 01 Matrix Style
    /*public static BufferedImage applyBinary(int tam, BufferedImage img){
        //Recorre las imagenes y obtiene el color de la imagen original y la almacena en el destino.
        int w = img.getWidth(), h = img.getHeight(), x = 0, y = 0, r = 0, g = 0, b = 0, m = 0, n = 0;
        // crea un buffer local para no alterar el buffer que recibe
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bi.createGraphics();
        graphics.drawImage(img, 0, 0, null);
        graphics.setFont(new Font("Cousine", Font.BOLD, tam));
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
                String letter = ThreadLocalRandom.current().nextInt(0, 2) == 0 ? "0" : "1";
                letter = ThreadLocalRandom.current().nextInt(0, 101) <= 80 ? letter : " ";
                graphics.setColor(getColor(r, g, b));
                graphics.drawString(letter, x, y);
                y += n; r = 0; g = 0; b = 0;
            } x += m; y = 0;
        }  graphics.dispose(); return bi;
    }

    private static Color getColor(int r, int g, int b) {
        int v = (r + g + b) / 3 <= 127 ? 20 : - 20;
        r = r + v < 0 ? 0 : r + v > 255 ? 255 : r + v;
        g = g + v < 0 ? 0 : g + v > 255 ? 255 : g + v;
        b = b + v < 0 ? 0 : b + v > 255 ? 255 : b + v;
        return new Color(r, g, b);
    }*/
}

