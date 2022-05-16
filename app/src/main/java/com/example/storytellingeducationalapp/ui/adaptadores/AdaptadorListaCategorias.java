package com.example.storytellingeducationalapp.ui.adaptadores;

import android.app.Activity;
import android.app.FragmentManager;
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

import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.storytellingeducationalapp.R;
import com.example.storytellingeducationalapp.ui.all.AllFragment;
import com.example.storytellingeducationalapp.ui.modelos.ModeloCategorias;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class AdaptadorListaCategorias extends BaseAdapter {

    private Activity context;
    private ArrayList<ModeloCategorias> categorias;

    public AdaptadorListaCategorias(Activity _context, ArrayList<ModeloCategorias> _categorias){
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
        btnCollection.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundle = new Bundle();
                bundle.putString("idCategoria", modeloCategorias.txtId);

                NavController navController = Navigation.findNavController(context, R.id.nav_host_fragment_content_drawer);
                navController.navigate(R.id.nav_all, bundle);

                /*
                AllFragment fr = new AllFragment();

                modeloCategorias.fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.nav_default_enter_anim,
                            R.anim.nav_default_exit_anim,
                            R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim)
                    .replace(R.id.nav_host_fragment_container, fr)
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();

                 */
            }
        });

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
