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
@ManagedBean(name="findEdges")
@ViewScoped
public class FindEdgesController implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @ManagedProperty(value="#{editor}")
    private Editor editor;
    
    private double[][] m = {{-1,-1,-1},
                            {-1,8,-1},
                            {-1,-1,-1}};
    
    public FindEdgesController() {
        
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }
    
    public void aplicar() {
        editor.setImg(Convolution.convolution(editor.getImg2(), m, 1, 0));
    }

}