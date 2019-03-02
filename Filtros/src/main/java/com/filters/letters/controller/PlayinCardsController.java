/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.filters.letters.controller;

import com.filters.letters.Letter;
import com.filters.main.Editor;
import java.io.Serializable;
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
@ManagedBean(name="playin")
@ViewScoped
public class PlayinCardsController implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    @ManagedProperty(value="#{editor}")
    private Editor editor;
    
    private int size;
    private boolean sg;
    private String font;

    public PlayinCardsController() {
        this.size = 6;
        this.sg = false;
        this.font = "PlayingCards";
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

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }
    
    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }
    
    public void aplicar() {
        List<String> letters = letters = Stream.of("M","L","K","J","I","H","G","F","E","D","C","B","A")
                            .collect(Collectors.toList()); 
        letters.add(" ");
        editor.setImg(Letter.apply(letters, font, size, sg, editor.getImg2()));
    }
    
    public void sizeUp() {
        size += size < 36 ? 1 : 0;
    }
    
    public void sizeDown() {
        size -= size > 6 ? 1 : 0;
    }
}
