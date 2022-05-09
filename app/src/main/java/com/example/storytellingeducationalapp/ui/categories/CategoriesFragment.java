package com.example.storytellingeducationalapp.ui.categories;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.storytellingeducationalapp.R;
//import com.example.storytellingeducationalapp.databinding.FragmentCategoriesBinding;
import com.example.storytellingeducationalapp.ui.adaptadores.AdaptadorListaCategorias;
import com.example.storytellingeducationalapp.ui.modelos.ModeloCategorias;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CategoriesFragment extends Fragment {

    private CategoriesViewModel categoriesViewModel;
   // private FragmentCategoriesBinding binding;

    private ListView listView;
    private ArrayAdapter<String> listAdapter;
    private AdaptadorListaCategorias adaptadorListaCategorias;
    private ArrayList<ModeloCategorias> categorias;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoriesViewModel =
                new ViewModelProvider(this).get(CategoriesViewModel.class);

        /*binding = FragmentCategoriesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();*/

        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) view.findViewById(R.id.listCategories);
        categorias = new ArrayList<ModeloCategorias>();

        Drawable img1 = getResources().getDrawable(R.drawable.fairytales);
        Drawable img2 = getResources().getDrawable(R.drawable.fable);
        Drawable img3 = getResources().getDrawable(R.drawable.history);
        Drawable img4 = getResources().getDrawable(R.drawable.suspense);
        Drawable img5 = getResources().getDrawable(R.drawable.farm);

        Button btnPlay = (Button) view.findViewById(R.id.btnCollection);

        ModeloCategorias usuario1 = new ModeloCategorias("Fairy Tales", btnPlay, img1);
        ModeloCategorias usuario2 = new ModeloCategorias("Fable", btnPlay,  img2);
        ModeloCategorias usuario3 = new ModeloCategorias("History", btnPlay,  img3);
        ModeloCategorias usuario4 = new ModeloCategorias("Suspense", btnPlay,  img4);
        ModeloCategorias usuario5 = new ModeloCategorias("Farm", btnPlay,  img5);


        categorias.add(usuario1);
        categorias.add(usuario2);
        categorias.add(usuario3);
        categorias.add(usuario4);
        categorias.add(usuario5);

        adaptadorListaCategorias = new AdaptadorListaCategorias( getActivity(), categorias);
        listView.setAdapter(adaptadorListaCategorias);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }
}