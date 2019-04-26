package com.filters.advance.controller;

import com.filters.advance.Semitones;
import com.filters.main.Editor;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name="semitones")
@ViewScoped
public class SemitonesController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ManagedProperty(value="#{editor}")
    private Editor editor;
    
    private int size;
    
    public SemitonesController() {
        this.size = 4;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public void aplicar() {
        if (size <= 0 ) {
            editor.reset();
            return;
        }
        editor.setImg(Semitones.aplicar(size, editor.getImg2()));
    }
}