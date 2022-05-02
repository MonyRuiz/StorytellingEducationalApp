package com.example.storytellingeducationalapp.ui.modelos;

import android.graphics.drawable.Drawable;
import android.widget.Button;

public class ModeloCuentos {
    public String txtTitle;
    public Button btnPlay;
    public Button btnMore;
    public Drawable imgStory;

    public ModeloCuentos(String txtStory, Button btnPlay, Button btnMore, Drawable imgStory){
        this.txtTitle = txtStory;
        this.btnPlay = btnPlay;
        this.btnMore = btnMore;
        this.imgStory = imgStory;
    }

    public String getTxtTitle(){ return this.txtTitle; }

    public Button getBtnPlay(){
        return this.btnPlay;
    }

    public Button getBtnMore(){ return  this.btnMore;}

    public Drawable getImgStory(){
        return this.imgStory;
    }
}
