/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.filters.advance.controller;

import com.filters.advance.Oleo;
import com.filters.main.Editor;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alex aldaco
 */
@ManagedBean(name="oleo")
@ViewScoped
public class OleoController implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    @ManagedProperty(value="#{editor}")
    private Editor editor;
    
    private int radio;
    private boolean sg;
    
    public OleoController() {
        this.radio = 5;
        this.sg = false;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    public boolean isSg() {
        return sg;
    }

    public void setSg(boolean sg) {
        this.sg = sg;
    }
    
    public void aplicar() {
        editor.setImg(Oleo.apply(sg,radio,editor.getImg2()));
    }
}
