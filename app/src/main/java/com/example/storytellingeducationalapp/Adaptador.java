package com.example.storytellingeducationalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter<Modelo> {
    public Adaptador(@NonNull Context context, ArrayList<Modelo> cuentos) {
        super(context, 0, cuentos);
    }

    public View getView(int posicion, View convertView, ViewGroup parent){
        Modelo modelo = getItem(posicion);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.celdacuentos, parent, false);
        }

        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
        Button btnPlay = (Button) convertView.findViewById(R.id.btnPlay);
        Button btnMore = (Button) convertView.findViewById(R.id.btnMore);
        ImageView imgStory = (ImageView) convertView.findViewById(R.id.imgStory);

        txtTitle.setText(modelo.txtTitle);
        //btnMore.setOnClickListener(modelo.btnPlay.getOn);
        imgStory.setImageDrawable(modelo.imgStory);


        return convertView;
    }
}