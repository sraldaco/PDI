/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.filters.advance.controller;

import com.filters.advance.WaterMark;
import com.filters.main.Editor;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alex aldaco
 */
@ManagedBean(name="watermark")
@ViewScoped
public class WaterMarkController implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    @ManagedProperty(value="#{editor}")
    private Editor editor;
    
    private String filter;
    
    public WaterMarkController() {
        this.filter = "remove";
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
    
    public void aplicar() {
        if (filter.equals("remove")) {
            editor.setImg(WaterMark.remove(editor.getImg2()));
            return;
        }
        editor.setImg(WaterMark.add(editor.getImg2()));
    }
}
