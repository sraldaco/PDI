package com.filters.service;

import com.filters.project.PhotoMosaic;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Store {
    private static final String rootPath = System.getProperty("user.dir");
    private static final String path = rootPath+ "/../files/";
    private static final String imagesPath = path + "/images/";
    private static final String thumbsPath = path + "/thumbs/";

    public static String getPath() { return path; }

    public static String getRootPath() { return rootPath; }

    private static List<String> getListFiles(String path) {
        List<String> filesInFolder = new ArrayList<>();
        try {
            filesInFolder = Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .filter(x -> x.toString().endsWith(".jpg"))
                    .map(x -> x.toString())
                    .collect(Collectors.toList());
        } catch(Exception ex) {
            Logger.getLogger(PhotoMosaic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filesInFolder;
    }

    private static Color getAverageColor(BufferedImage thumb) {
        int w = thumb.getWidth(), h = thumb.getHeight();
        int r = 0, g = 0, b = 0, size = w * h;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Color c = new Color(thumb.getRGB(i,j));
                r += c.getRed(); g += c.getGreen(); b += c.getBlue();
            }
        }
        r = r / size; g = g / size; b = b / size;
        return new Color(r,g,b);
    }

    public static void updateThumbs() {
        List<String> files = getListFiles(imagesPath);
        File destinationDir = new File(thumbsPath);
        try {
            System.out.println("Generando miniaturas de imagen. Esto puede tardar unos minutos");
            for (String file : files) {
                System.out.println("Thumb for " + file);
                Thumbnails.of(file)
                        .crop(Positions.CENTER)
                        .size(100, 100)
                        .keepAspectRatio(true)
                        .toFiles(destinationDir, Rename.NO_CHANGE);
            }
            System.out.println("Se han creado todas las miniaturas");
        } catch (Exception ex) {
            Logger.getLogger(PhotoMosaic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updateDBImages() {
        List<String> files = getListFiles(thumbsPath);
        List<StoredImage> storedImages = getStoredImageList(files);
        CoordsColor c = new CoordsColor(87,100,150);
        saveFileStoredList (storedImages);
    }

    private static void saveFileStoredList (List<StoredImage> storedImages) {
        try {
            FileOutputStream fos = new FileOutputStream(path + "/imagelist.imx");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(storedImages);
            oos.close();
        } catch (Exception ex) {
            Logger.getLogger(PhotoMosaic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static  List<StoredImage> getFileStoredList () {
        List<StoredImage> storedImages = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(path + "/imagelist.imx");
            ObjectInputStream ois = new ObjectInputStream(fis);
            storedImages = (List<StoredImage>) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            Logger.getLogger(PhotoMosaic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return storedImages;
    }

    public static void saveRangeIndex () {
        List<StoredImage> storedImages = getFileStoredList();
        int[] data = new int[9];
        int d1 = 0, d2 = 0, d3 = 0, d4 = 0, d5 = 0, d6 = 0, d7 = 0, d8 = 0, d9 = 0;
        for (StoredImage store : storedImages) {
            int distance = store.getCoords().getDistance();
            if (distance <= 50)  d1++;
            else if (distance <= 100) d2++;
            else if (distance <= 150) d3++;
            else if (distance <= 200) d4++;
            else if (distance <= 250) d5++;
            else if (distance <= 300) d6++;
            else if (distance <= 350) d7++;
            else if (distance <= 400) d8++;
            else d9++;
        }
        d2 += d1; d3 += d2; d4 += d3; d5 += d4; d6 += d5; d7 += d6; d8 += d7; d9 += d8;
        data[0] = d1; data[1] = d2; data[2] = d3; data[3] = d4; data[4] = d5;
        data[5] = d6; data[6] = d7; data[7] = d8; data[8] = d9;
        try {
            FileOutputStream fos = new FileOutputStream(path + "/index.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
        } catch (Exception ex) {
            Logger.getLogger(PhotoMosaic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int[] getRangeIndex() {
        int[] index = null;
        try {
            FileInputStream fis = new FileInputStream(path + "/index.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            index = (int[]) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            Logger.getLogger(PhotoMosaic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return index;
    }

    private static List<StoredImage> getStoredImageList (List<String> files) {
        List<StoredImage> storedImages = new ArrayList<>();
        try {
            for (String file : files) {
                BufferedImage thumb = ImageIO.read(new File (file));
                Color c = getAverageColor(thumb);
                String fileName = file.replace(path + "thumbs/","");
                CoordsColor coords = new CoordsColor(c.getRed(),c.getGreen(),c.getBlue());
                storedImages.add(new StoredImage(coords,fileName));
            }
        } catch (Exception ex) {
            Logger.getLogger(PhotoMosaic.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collections.sort(storedImages,
                (StoredImage imgi,  StoredImage imgj) ->
                        new Integer(imgi.getCoords().getDistance()).compareTo(imgj.getCoords().getDistance()));
        return storedImages;
    }
}
