/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.filters.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;

/**
 *
 * @author alex aldaco
 */
public class ImageToBufferedImage {
    public static BufferedImage convertir(Part file) throws IOException {
        BufferedImage b = ImageIO.read(file.getInputStream());
        return b;
    }

}
