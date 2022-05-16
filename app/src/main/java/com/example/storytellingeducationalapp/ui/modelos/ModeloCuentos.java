package com.example.storytellingeducationalapp.ui.modelos;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.widget.Button;

public class ModeloCuentos {
    public String txtId;
    public String txtTitle;
    public String txtDescription;
    public Button btnPlay;
    public Button btnMore;
    public String imgStory;

    public ModeloCuentos(String txtStory, String txtDescription, Button btnPlay, Button btnMore, String imgStory){
        this.txtTitle = txtStory;
        this.txtDescription = txtDescription;
        this.btnPlay = btnPlay;
        this.btnMore = btnMore;
        this.imgStory = imgStory;
    }

    public ModeloCuentos() {

    }

    public String getTxtTitle(){ return this.txtTitle; }

    public Button getBtnPlay(){
        return this.btnPlay;
    }

    public Button getBtnMore(){ return  this.btnMore;}

    public String getImgStory(){
        return this.imgStory;
    }
}
