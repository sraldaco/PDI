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
@ManagedBean(name="motionBlur")
@ViewScoped
public class MotionBlurController implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @ManagedProperty(value="#{editor}")
    private Editor editor;
    
    private double[][] m = {{1,0,0,0,0,0,0},
                            {0,1,0,0,0,0,0},
                            {0,0,1,0,0,0,0},
                            {0,0,0,1,0,0,0},
                            {0,0,0,0,1,0,0},
                            {0,0,0,0,0,1,0},
                            {0,0,0,0,0,0,1}};
    
    public MotionBlurController() {
        
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }
    
    public void aplicar() {
        editor.setImg(Convolution.convolution(editor.getImg2(), m, 1.0 / 7.0, 0));
    }

}
