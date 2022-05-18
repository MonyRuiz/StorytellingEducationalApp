package com.example.storytellingeducationalapp.ui.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.storytellingeducationalapp.R;
import com.example.storytellingeducationalapp.ui.modelos.ModeloCuentos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorListaCuentos extends BaseAdapter {
    private Activity context;
    private ArrayList<ModeloCuentos> cuentos;

    public AdaptadorListaCuentos(Activity _context, ArrayList<ModeloCuentos> _cuentos){
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
        ImageView imgStory = (ImageView) convertView.findViewById(R.id.imgStory);

        txtTitle.setText(modeloCuentos.txtTitle);

        btnMore.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundle = new Bundle();
                bundle.putString("idStory", modeloCuentos.txtId);
                bundle.putString("txtTitle", modeloCuentos.txtTitle);
                bundle.putString("txtDescription", modeloCuentos.txtDescription);
                bundle.putString("imgStory", modeloCuentos.imgStory);
                bundle.putString("numPaginas", modeloCuentos.numPaginas);
                bundle.putInt("numCuentos", cuentos.size());

                NavController navController = Navigation.findNavController(context, R.id.nav_host_fragment_content_drawer);
                navController.navigate(R.id.nav_info_story, bundle);

            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundle = new Bundle();
                bundle.putString("idStory", modeloCuentos.txtId);
                bundle.putInt("numPaginas", Integer.parseInt(modeloCuentos.numPaginas));
                bundle.putString("page", "1");

                NavController navController = Navigation.findNavController(context, R.id.nav_host_fragment_content_drawer);
                navController.navigate(R.id.nav_lectura_cuento, bundle);

            }
        });

        try {
            Picasso
                    .get()
                    .load(modeloCuentos.imgStory)
                    //.fit()
                    .into(imgStory);
        }catch (Exception exception){
            //Toast.makeText(context,"Error: "+exception.toString(), Toast.LENGTH_LONG).show();//display the response on screen
            Log.e("Adaptador: ",exception.toString());
        }

        return convertView;
    }

    public void actualizarLista(ArrayList<ModeloCuentos> nuevaLista){
        this.cuentos = nuevaLista;
        notifyDataSetChanged();
    }
}
