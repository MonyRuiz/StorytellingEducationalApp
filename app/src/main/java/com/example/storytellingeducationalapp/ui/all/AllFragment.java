package com.example.storytellingeducationalapp.ui.all;

import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.storytellingeducationalapp.Adaptador;
import com.example.storytellingeducationalapp.AdaptadorBase;
import com.example.storytellingeducationalapp.Modelo;
import com.example.storytellingeducationalapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AllFragment extends Fragment {

    private AllViewModel mViewModel;

    private ListView listView;
    private ArrayAdapter<String> listAdapter;
    private Adaptador adaptador;
    private AdaptadorBase adaptadorBase;
    private ArrayList<Modelo> cuentos;

    public static AllFragment newInstance() {
        return new AllFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //listView = (ListView) container.findViewById(R.id.listStories);

        View view = inflater.inflate(R.layout.fragment_all, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) view.findViewById(R.id.listStories);
        cuentos = new ArrayList<Modelo>();

        Drawable img1 = getResources().getDrawable(R.drawable.foca);
        Drawable img2 = getResources().getDrawable(R.drawable.btnplay);
        Drawable img3 = getResources().getDrawable(R.drawable.categories);

        Button btnPlay = (Button) view.findViewById(R.id.btnPlay);
        Button btnMore = (Button) view.findViewById(R.id.btnMore);

        Modelo usuario1 = new Modelo("La foca", btnPlay, btnMore, img1);
        Modelo usuario2 = new Modelo("Un boton de Play", btnPlay, btnMore, img2);
        Modelo usuario3 = new Modelo("Un Categories", btnPlay, btnMore, img3);


        cuentos.add(usuario1);
        cuentos.add(usuario2);
        cuentos.add(usuario3);

        adaptadorBase = new AdaptadorBase( getActivity(), cuentos);
        listView.setAdapter(adaptadorBase);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AllViewModel.class);
        // TODO: Use the ViewModel
    }

}