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
    private boolean exclude;
    private List<String> chars;
    private String[] characters = {
        "0","1","2","3","4","5","6","7","8","9","#","$","%","&","@","=","+",
        "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q",
        "R","S","T","V","Y","Z"};
    
    public LetterController() {
        this.text = "MNH#QUAD0Y2$%+.";
        this.file = null;
        this.size = 10;
        this.sg = false;
        this.exclude = false;
        this.chars = new ArrayList<>();
        for (int i = 1; i < characters.length; i++) {
            chars.add(characters[i]);
        }
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
        this.text = text.trim().toUpperCase();
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

    public boolean isExclude() {
        return exclude;
    }

    public void setExclude(boolean exclude) {
        this.exclude = exclude;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public void aplicar() {
        if (exclude) {
            List<String> cText = chars;
            String[] aText = text.toUpperCase().split("");
            for(int i = 0; i < text.length(); i++ ) {
                String ch = aText[i];
                if (cText.contains(ch)) {
                    cText.remove(ch);
                }
            } 
            editor.setImg(Letter.apply(cText,"sans-serif", size, sg, editor.getImg2()));
        } else {
            editor.setImg(Letter.string(text, size, sg, editor.getImg2()));
        }
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
        fileName = getFileName(file);
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