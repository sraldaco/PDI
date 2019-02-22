/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.filters.convolution.controller;

import com.filters.convolution.Convolution;
import com.filters.main.Editor;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alex aldaco
 */
@ManagedBean(name="emboss")
@ViewScoped
public class EmbossController implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    @ManagedProperty(value="#{editor}")
    private Editor editor;
    
    private int mode;
    
    public EmbossController() {
        this.mode = 1;
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
        double[][]matriz;
        if (mode > 0) {
            double[][]eV = {{-1,0,1},{-1,0,1},{-1,0,1}};
            matriz = eV;
        } else if (mode < 0) {
            double[][]eH = {{-1,-1,-1},{0,0,0},{1,1,1}};
            matriz = eH;
        } else  {
            double[][]e45 = {{-1,-1,0},{-1,0,1},{0,1,1}};
            matriz = e45;
        }
           
        editor.setImg(Convolution.convolution(editor.getImg2(), matriz, 1, 128));
    }

}
