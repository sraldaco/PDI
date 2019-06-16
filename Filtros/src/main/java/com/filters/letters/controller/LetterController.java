/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.filters.letters.controller;

import com.filters.letters.Letter;
import com.filters.main.Editor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author alex aldaco
 */
@ManagedBean(name="letters")
@ViewScoped
public class LetterController implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    @ManagedProperty(value="#{editor}")
    private Editor editor;
    
    private Part file;
    private String fileName;
    private String text;
    private int size;
    private boolean sg; 
    
    public LetterController() {
        this.text = "MNH#QUAD0Y2$%+.";
        this.file = null;
        this.size = 10;
        this.sg = false;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        text = text.trim().toUpperCase();
        if ("".equals(text) || text == null) {
            
        } else {
            this.text = text;
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isSg() {
        return sg;
    }

    public void setSg(boolean sg) {
        this.sg = sg;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public void aplicar() {
        String[] chars = text.split("");
        List<String> letters = new ArrayList<>(Arrays.asList(chars));
        letters.add(" ");
        editor.setImg(Letter.apply(letters, "Cousine", size, sg, editor.getImg2()));
        //editor.setImg(Letter.applyBinary(size, editor.getImg2()));
    }

    public void sizeUp() {
        size += size < 24 ? 1 : 0;
    }
    
    public void sizeDown() {
        size -= size > 6 ? 1 : 0;
    }
    
    public void uploadFile() throws IOException {
        FacesContext current = FacesContext.getCurrentInstance();
        this.text = "";
        fileName = getFileNameL(file);
        InputStream input = file.getInputStream();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while ((line = br.readLine()) != null) {
               text += line.trim().toUpperCase();
            }
        } catch (Exception ex) {
            current.addMessage(null,
                new FacesMessage(null,"Algo salio mal", ex.getMessage()));
        }
    }
    
    private String getFileNameL(Part part) {
        String fileNameL = "";
        for (String cd : part.getHeader("content-disposition").split(";")) { 
            if (cd.trim().startsWith("filename")) { 
                fileNameL = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return fileNameL.substring(fileNameL.lastIndexOf('/') + 1).substring(fileNameL.lastIndexOf('\\') + 1); // MSIE fix. } } return null; }
    }
}