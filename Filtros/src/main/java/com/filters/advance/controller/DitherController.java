/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.filters.advance.controller;

import com.filters.advance.Dither;
import com.filters.main.Editor;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alex aldaco
 */
@ManagedBean(name="dither")
@ViewScoped
public class DitherController implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    @ManagedProperty(value="#{editor}")
    private Editor editor;
    
    private int mode;
    
    public DitherController() {
        this.mode = 0;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
    
    public void aplicar() {
        if (mode == 3)
            editor.setImg(Dither.difusion(editor.getImg2()));
        else
            editor.setImg(Dither.apply(mode,editor.getImg2()));
    }
}
