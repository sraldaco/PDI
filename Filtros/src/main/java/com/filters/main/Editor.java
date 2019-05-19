/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filters.main;

import com.filters.project.PhotoMosaic;
import com.filters.service.ImageToBase64;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author alex aldaco
 */
@ManagedBean(name="editor")
@SessionScoped
public class Editor implements Serializable {

    private static final long serialVersionUID = 1L;
  
    private String foto;
    private Part file;
    private BufferedImage img;
    private BufferedImage img2;
    private String filename;
    private String ext;

    /**
     * Creates a new instance of FiltrosControlador
     */
    public Editor() {
        
    }
    
    public String getFoto() {
        return foto;
    }

    public void setFoto (String foto) { this.foto = foto; }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public BufferedImage getImg2() {
        return img2;
    }

    public String getFilename () { return filename; }

    public String getExt () { return ext; }

    public void setImg(BufferedImage img) {
        this.img = img;
        generateB64();
    }
    
    public void uploadImage() throws IOException {
        FacesContext current = FacesContext.getCurrentInstance();
        try {
            InputStream input = file.getInputStream();
            img2 = img = ImageIO.read(input);
            filename  = getFileName(file);
            ext = FilenameUtils.getExtension(filename);
            generateB64();
            file = null;
            input = null;
        } catch (Exception ex) {
            current.addMessage(null, 
                new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "¡Algo salio mal!",
                    ex.getMessage()
                )
            );
        }
    }
    
    public void apply() {
        img2 = img;
    }
    
    public void reset() {
        img = img2;
        generateB64();
    }
    
    public void delete() throws IOException {
        img = img2 = null;
        foto = null;
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Filtros/");
    }
    
    public void generateB64() {
        foto  = ImageToBase64.codificar(img,ext);
    }
    
    private String getFileName(Part part) {
        String fileName = "";
        for (String cd : part.getHeader("content-disposition").split(";")) { 
            if (cd.trim().startsWith("filename")) { 
                fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix. } } return null; }
    } 
     
}
