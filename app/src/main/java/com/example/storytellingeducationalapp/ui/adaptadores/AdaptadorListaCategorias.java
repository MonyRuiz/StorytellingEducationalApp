package com.example.storytellingeducationalapp.ui.adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.storytellingeducationalapp.R;
import com.example.storytellingeducationalapp.ui.modelos.ModeloCategorias;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class AdaptadorListaCategorias extends BaseAdapter {

    private Context context;
    private ArrayList<ModeloCategorias> categorias;

    public AdaptadorListaCategorias(Context _context, ArrayList<ModeloCategorias> _categorias){
        this.context = _context;
        this.categorias = _categorias;
    }

    @Override
    public int getCount() {
        return this.categorias.size();
    }

    @Override
    public Object getItem(int position) {
        return this.categorias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(this.context)
                    .inflate(R.layout.celdacategorias, parent, false);
        }

        ModeloCategorias modeloCategorias = (ModeloCategorias) getItem(position);

        TextView txtCategory = (TextView) convertView.findViewById(R.id.txtCategory);
        Button btnCollection = (Button) convertView.findViewById(R.id.btnCollection);
        ImageView imgCategory = (ImageView) convertView.findViewById(R.id.imgStory);

        txtCategory.setText(modeloCategorias.txtCategory);
        //btnMore.setOnClickListener(modelo.btnPlay.getOn);

        try {
            Picasso
                    .get()
                    .load(modeloCategorias.imgCategory)
                    //.fit()
                    .into(imgCategory);
        }catch (Exception exception){
            //Toast.makeText(context,"Error: "+exception.toString(), Toast.LENGTH_LONG).show();//display the response on screen
            Log.e("Adaptador: ",exception.toString());
        }

        return convertView;
    }

    public void actualizarLista(ArrayList<ModeloCategorias> nuevaLista){
        this.categorias = nuevaLista;
        notifyDataSetChanged();
    }


}
