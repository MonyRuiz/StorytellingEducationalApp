package com.example.storytellingeducationalapp.ui.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.storytellingeducationalapp.R;
import com.example.storytellingeducationalapp.ui.modelos.ModeloCuentos;

import java.util.ArrayList;

public class AdaptadorListaCuentos extends BaseAdapter {
    private Context context;
    private ArrayList<ModeloCuentos> cuentos;

    public AdaptadorListaCuentos(Context _context, ArrayList<ModeloCuentos> _cuentos){
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

        ModeloCuentos modeloCuentos = (ModeloCuentos)getItem(position);

        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtCategory);
        Button btnPlay = (Button) convertView.findViewById(R.id.btnCollection);
        Button btnMore = (Button) convertView.findViewById(R.id.btnMore);
        ImageView imgStory = (ImageView) convertView.findViewById(R.id.imgCategory);

        txtTitle.setText(modeloCuentos.txtTitle);
        //btnMore.setOnClickListener(modelo.btnPlay.getOn);
        imgStory.setImageDrawable(modeloCuentos.imgStory);


        return convertView;
    }

    public void actualizarLista(ArrayList<ModeloCuentos> nuevaLista){
        this.cuentos = nuevaLista;
        notifyDataSetChanged();
    }
}
