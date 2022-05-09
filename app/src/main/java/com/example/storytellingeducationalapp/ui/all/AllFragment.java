package com.example.storytellingeducationalapp.ui.all;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.storytellingeducationalapp.ui.adaptadores.AdaptadorListaCuentos;
import com.example.storytellingeducationalapp.ui.modelos.ModeloCuentos;
import com.example.storytellingeducationalapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AllFragment extends Fragment {

    private AllViewModel mViewModel;

    private ListView listView;
    private ArrayAdapter<String> listAdapter;
    private AdaptadorListaCuentos adaptadorListaCuentos;
    private ArrayList<ModeloCuentos> cuentos;

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
        cuentos = new ArrayList<ModeloCuentos>();

        Drawable img1 = getResources().getDrawable(R.drawable.leonraton);
        Drawable img2 = getResources().getDrawable(R.drawable.vaca);
        Drawable img3 = getResources().getDrawable(R.drawable.serpienteorangutan);
        Drawable img4 = getResources().getDrawable(R.drawable.zorroliebre);
        Drawable img5 = getResources().getDrawable(R.drawable.tortugacaiman);

        Button btnPlay = (Button) view.findViewById(R.id.btnCollection);
        Button btnMore = (Button) view.findViewById(R.id.btnMore);

        ModeloCuentos usuario1 = new ModeloCuentos("El León y el Ratón", btnPlay, btnMore, img1);
        ModeloCuentos usuario2 = new ModeloCuentos("La Vaca lechera", btnPlay, btnMore, img2);
        ModeloCuentos usuario3 = new ModeloCuentos("La Serpiente y el Orangután", btnPlay, btnMore, img3);
        ModeloCuentos usuario4 = new ModeloCuentos("El Zorro y la Liebre", btnPlay, btnMore, img4);
        ModeloCuentos usuario5 = new ModeloCuentos("La Tortuga y el Caiman", btnPlay, btnMore, img5);


        cuentos.add(usuario1);
        cuentos.add(usuario2);
        cuentos.add(usuario3);
        cuentos.add(usuario4);
        cuentos.add(usuario5);

        adaptadorListaCuentos = new AdaptadorListaCuentos( getActivity(), cuentos);
        listView.setAdapter(adaptadorListaCuentos);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AllViewModel.class);
        // TODO: Use the ViewModel
    }

}