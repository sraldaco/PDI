/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.filters.basic.controller;

import com.filters.basic.RGB;
import com.filters.main.Editor;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author alex aldaco
 */
@ManagedBean(name="rgb")
@SessionScoped
public class RGBController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private boolean r;
    private boolean g;
    private boolean b;
    
    @ManagedProperty(value="#{editor}")
    private Editor editor;
    
    public RGBController() {
        this.r = this.g = this.b = true;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }
    
    public boolean isR() {
        return r;
    }

    public void setR(boolean r) {
        this.r = r;
    }

    public boolean isG() {
        return g;
    }

    public void setG(boolean g) {
        this.g = g;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }
    
    public void aplicar(){
        editor.setImg(RGB.convert(r, g, b, editor.getImg2()));
    }
}
