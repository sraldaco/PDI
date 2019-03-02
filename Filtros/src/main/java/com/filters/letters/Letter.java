/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.filters.letters;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author alex aldaco
 */
public class Letter {
    public static  BufferedImage string(String text, int size, boolean sg,BufferedImage img){
        int sizeText = text.length();
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage bi = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bi.getGraphics();
        graphics.fillRect(0, 0, w, h);
        int count = 0;
        for(int i = 0; i<w; i+=size){
            for(int j = 0; j<h; j+=size){
                Color c = new Color(img.getRGB(i,j));
                if (sg) {
                    int p = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                    c = new Color(p,p,p);
                }
                graphics.setColor(c);
                graphics.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, size));
                graphics.drawString(text.charAt(count % sizeText)+"", i, j);
                count++;
            }
            count = 0;
        }
        return bi;
    }
    
    public static BufferedImage apply(List<String> chars, String font, int size, boolean sg,BufferedImage img){
        int w = img.getWidth();
        int h = img.getHeight();
        float cell = 255f / (float)chars.size();
        BufferedImage bi = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
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
              int count = 0;
              while((s = (float)(count + 1) * cell) < p) {
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
}

