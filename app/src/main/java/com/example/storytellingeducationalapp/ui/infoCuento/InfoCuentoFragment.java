package com.example.storytellingeducationalapp.ui.infoCuento;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.storytellingeducationalapp.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class InfoCuentoFragment extends Fragment {

    private InfoCuentoViewModel mViewModel;

    private ImageView imgPortada;
    private TextView txtTitle;
    private TextView txtDescription;
    private Button btnPlay;
    private Button btnPrevious;
    private Button btnNext;

    private String idStory;
    private String title;
    private String description;
    private String imgStory;

    public static InfoCuentoFragment newInstance() {
        return new InfoCuentoFragment();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idStory = getArguments().getString("idStory");
            title = getArguments().getString("txtTitle");
            description = getArguments().getString("txtDescription");
            imgStory = getArguments().getString("imgStory");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_info_cuento, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        txtDescription = (TextView) view.findViewById(R.id.txtDescription);
        imgPortada = (ImageView) view.findViewById(R.id.imgPortada);
        btnPlay = (Button) view.findViewById(R.id.btnPlay);
        btnPrevious = (Button) view.findViewById(R.id.btnPrevious) ;
        btnNext = (Button) view.findViewById(R.id.btnNext);

        txtTitle.setText(title);
        txtDescription.setText(description);

        try {
            Picasso
                    .get()
                    .load(imgStory)
                    //.fit()
                    .into(imgPortada);
        }catch (Exception exception){
            //Toast.makeText(context,"Error: "+exception.toString(), Toast.LENGTH_LONG).show();//display the response on screen
            Log.e("Adaptador: ",exception.toString());
        }

        btnPlay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundle = new Bundle();
                bundle.putString("idStory", idStory);

                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_drawer);
                navController.navigate(R.id.nav_lectura_cuento, bundle);

            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {/*
                Bundle bundle = new Bundle();
                bundle.putString("idStory", modeloCuentos.txtId);
                bundle.putString("txtTitle", modeloCuentos.txtTitle);
                bundle.putString("txtDescription", modeloCuentos.txtDescription);
                bundle.putString("imgStory", modeloCuentos.imgStory);

                NavController navController = Navigation.findNavController(context, R.id.nav_host_fragment_content_drawer);
                navController.navigate(R.id.nav_info_story, bundle);
*/
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {/*
                Bundle bundle = new Bundle();
                bundle.putString("idStory", modeloCuentos.txtId);
                bundle.putString("txtTitle", modeloCuentos.txtTitle);
                bundle.putString("txtDescription", modeloCuentos.txtDescription);
                bundle.putString("imgStory", modeloCuentos.imgStory);

                NavController navController = Navigation.findNavController(context, R.id.nav_host_fragment_content_drawer);
                navController.navigate(R.id.nav_info_story, bundle);
*/
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InfoCuentoViewModel.class);
        // TODO: Use the ViewModel
    }

}