/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.filters.project;

import com.filters.service.CoordsColor;
import com.filters.service.MatchFile;
import com.filters.service.Store;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex aldaco
 */
public class PhotoMosaic {

    public static BufferedImage create (int tam, int blend, int scale, BufferedImage img) {
        // nivel de blend
        float opacity = 1f - ((float)blend/100f);
        //Recorre las imagenes y obtiene el color de la imagen original y la almacena en el destino.
        int w = img.getWidth(), h = img.getHeight(), x = 0, y = 0, m = 0, n = 0;
        // crea un buffer local para no alterar el buffer que recibe
        BufferedImage bi = new BufferedImage(w * scale,h * scale,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = bi.createGraphics();
        graphic.drawImage(img, 0, 0, w * scale, h * scale, null);
        graphic.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        while (x < w){
            m = ((x + tam) < w)? tam : w - x;
            while (y < h){
                n = ((y + tam) < h)? tam : h - y;
                int r = 0, g = 0, b = 0;
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++){
                        Color c = new Color(img.getRGB(i + x, j + y));
                        r += c.getRed(); g += c.getGreen(); b += c.getBlue();
                    }
                }
                int size = m * n == 0 ? 1 : m * n;
                r /= size; g /= size; b /= size;
                BufferedImage thumb = getThumb(new CoordsColor(r, g, b));
                graphic.drawImage(thumb, x * scale, y * scale, tam * scale, tam * scale, null);
                y += n;
            } x += m; y = 0;
        }
        graphic.dispose(); return bi;
    }

    private static BufferedImage getThumb (CoordsColor coords) {
        BufferedImage thumb = null;
        try {
            String file = Store.getPath() + "thumbs/" + MatchFile.Search(coords);
            thumb = ImageIO.read(new File(file));
        } catch (Exception ex) {
            Logger.getLogger(PhotoMosaic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return thumb;
    }
}
