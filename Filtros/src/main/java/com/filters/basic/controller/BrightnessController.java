/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.filters.basic.controller;

import com.filters.basic.Brightness;
import com.filters.main.Editor;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alex aldaco
 */
@ManagedBean(name="brightness")
@ViewScoped
public class BrightnessController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int level;
    @ManagedProperty(value="#{editor}")
    private Editor editor;
    
    public BrightnessController() {
        level= 0;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    public void aplicar() {
        editor.setImg(Brightness.aplicar(level, editor.getImg2()));
    }
}
