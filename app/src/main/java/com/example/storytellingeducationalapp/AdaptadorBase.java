package com.example.storytellingeducationalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.storytellingeducationalapp.ui.all.AllFragment;

import java.util.ArrayList;

public class AdaptadorBase extends BaseAdapter {
    private Context context;
    private ArrayList<Modelo> cuentos;

    public AdaptadorBase(Context _context, ArrayList<Modelo> _cuentos){
        this.context = _context;
        this.cuentos = _cuentos;
    }

    @Override
    public int getCount() {
        return this.cuentos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.cuentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(this.context)
                    .inflate(R.layout.celdacuentos, parent, false);
        }

        Modelo modelo = (Modelo)getItem(position);

        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
        Button btnPlay = (Button) convertView.findViewById(R.id.btnPlay);
        Button btnMore = (Button) convertView.findViewById(R.id.btnMore);
        ImageView imgStory = (ImageView) convertView.findViewById(R.id.imgStory);

        txtTitle.setText(modelo.txtTitle);
        //btnMore.setOnClickListener(modelo.btnPlay.getOn);
        imgStory.setImageDrawable(modelo.imgStory);


        return convertView;
    }

    public void actualizarLista(ArrayList<Modelo> nuevaLista){
        this.cuentos = nuevaLista;
        notifyDataSetChanged();
    }
}
