/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.filters.project.controller;

import com.filters.main.Editor;
import com.filters.project.PhotoMosaic;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.io.File;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alex aldaco
 */
@ManagedBean(name="photomosaic")
@ViewScoped
public class PhotoMosaicController implements Serializable {

    private static final long serialVersionUID = 1L;

    private int size;
    private int blend;
    private int scale;
    
    @ManagedProperty(value="#{editor}")
    private Editor editor;

    @org.jetbrains.annotations.Contract(pure = true)
    public PhotoMosaicController() {
        this.size = 6;
        this.blend = 10;
        this.scale = 4;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public int getSize () { return size;}

    public void setSize (int size) { this.size = size; }

    public int getBlend () { return blend; }

    public void setBlend (int blend) { this.blend = blend; }

    public int getScale () { return scale; }

    public void setScale (int scale) { this.scale = scale; }

    public void aplicar() {
        //Store.saveRangeIndex ();
        BufferedImage bi = PhotoMosaic.create(size, blend, scale, editor.getImg2());
        String fileName = editor.getFilename();
        String path =  FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/output/" + fileName);
        String ext = editor.getExt();
        try {
            File outputfile = new File(path);
            ImageIO.write(bi, ext, outputfile);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", ex.getMessage())
            );
        }

        editor.setFoto("/resources/output/" + fileName);
    }
    

}
