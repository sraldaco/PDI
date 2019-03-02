/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.filters.letters.controller;

import com.filters.letters.Letter;
import com.filters.main.Editor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alex aldaco
 */
@ManagedBean(name="domino")
@ViewScoped
public class DominoController implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @ManagedProperty(value="#{editor}")
    private Editor editor;
    
    private String izqDer;
    private String color;
    private int size;
    private boolean sg;
    private String font;
    
    public DominoController() {
        this.izqDer = "I";
        this.color = "B";
        this.size = 12;
        this.sg = true;
        this.font = "Las Vegas Black Dominoes";
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public String getIzqDer() {
        return izqDer;
    }

    public void setIzqDer(String izqDer) {
        this.izqDer = izqDer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
        
    public void aplicar() {
        List<String> letters = getList(this.color, this.izqDer);
        editor.setImg(Letter.apply(letters, font, size, sg, editor.getImg2()));
    }
    
    private List<String> getList(String c, String d) {
        List<String> letters = new ArrayList<>();
        switch(c) {
            case "W" :
                if(d.equals("I")) {
                    letters = Stream.of("6","5","4","3","2","1","0")
                            .collect(Collectors.toList());
                } else if(d.equals("D"))  {
                    letters = Stream.of("^","%","$","#","@","!",")")
                            .collect(Collectors.toList()); 
                }
                break;
            case "B" :
                if(d.equals("I")) {
                    letters = Stream.of("0","1","2","3","4","5","6")
                            .collect(Collectors.toList()); 
                } else if(d.equals("D"))  {
                    letters = Stream.of(")","!","@","#","$","%","^")
                            .collect(Collectors.toList());
                }
                break;
            default : break;
        }
        letters.add(" ");
        return letters;
    }
    
    public void sizeUp() {
        size += size < 36 ? 1 : 0;
    }
    
    public void sizeDown() {
        size -= size > 6 ? 1 : 0;
    }
}
