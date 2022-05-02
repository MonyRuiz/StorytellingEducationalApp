package com.example.storytellingeducationalapp.ui.modelos;

import android.graphics.drawable.Drawable;
import android.widget.Button;

public class ModeloCategorias {

    public String txtCategory;
    public Button btnCollection;
    public Drawable imgCategory;

    public ModeloCategorias(String txtStory, Button btnCollection, Drawable imgCategory){
        this.txtCategory = txtStory;
        this.btnCollection = btnCollection;
        this.imgCategory = imgCategory;
    }

    public String getTxtCategory(){ return this.txtCategory; }

    public Button getBtnCollection(){
        return this.btnCollection;
    }


    public Drawable getImgCategory(){
        return this.imgCategory;
    }

}
