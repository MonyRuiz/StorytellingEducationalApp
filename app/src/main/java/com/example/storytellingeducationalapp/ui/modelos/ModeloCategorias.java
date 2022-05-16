package com.example.storytellingeducationalapp.ui.modelos;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Button;

public class ModeloCategorias {

    public  String txtId;
    public String txtCategory;
    public Button btnCollection;
    public String imgCategory;
    public Activity activity;

    public ModeloCategorias(String txtStory, Button btnCollection, String imgCategory){
        this.txtCategory = txtStory;
        this.btnCollection = btnCollection;
        this.imgCategory = imgCategory;
    }

    public ModeloCategorias() {

    }

    public String getTxtCategory(){ return this.txtCategory; }

    public Button getBtnCollection(){
        return this.btnCollection;
    }

    public String getImgCategory(){
        return this.imgCategory;
    }

}
