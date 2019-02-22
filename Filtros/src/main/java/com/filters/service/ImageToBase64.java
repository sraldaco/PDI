/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filters.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

/**
 *
 * @author alex aldaco
 */
public class ImageToBase64 {
    public static String codificar(BufferedImage image, String type) {
        String base64Encoded = "data:image/" + type + ";base64,";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();            
            base64Encoded += Base64.getEncoder().encodeToString(imageBytes);
            bos.close();
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
        return base64Encoded;
    }
}