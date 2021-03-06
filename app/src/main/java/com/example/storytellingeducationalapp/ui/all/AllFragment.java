package com.example.storytellingeducationalapp.ui.all;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.storytellingeducationalapp.ui.adaptadores.AdaptadorListaCuentos;
import com.example.storytellingeducationalapp.ui.modelos.ModeloCuentos;
import com.example.storytellingeducationalapp.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class AllFragment extends Fragment {

    private AllViewModel mViewModel;

    private ListView listView;
    private AdaptadorListaCuentos adaptadorListaCuentos;
    private ArrayList<ModeloCuentos> cuentos;
    private EditText searchBar;
    private Button btnEnviar;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "http://naturalbeauty.ddns.net/SEAProject/API/Controller.php?obtener=cuentos";
    private JSONArray resultadoJsonArray;
    String respuesta;

    String busqueda;
    String idCategoria;

    public static AllFragment newInstance() {
        return new AllFragment();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            busqueda = getArguments().getString("busqueda");
            idCategoria = getArguments().getString("idCategoria");


            if (busqueda == null) {
                url = "http://naturalbeauty.ddns.net/SEAProject/API/Controller.php?obtener=buscarCuentosID&idCategoria=" + idCategoria;
            }else{
                url = "http://naturalbeauty.ddns.net/SEAProject/API/Controller.php?obtener=buscarCuentos&busqueda=" + busqueda;
            }
        }
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
        searchBar = (EditText) view.findViewById(R.id.SearchBar);
        btnEnviar = (Button) view.findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundle = new Bundle();
                bundle.putString("idCategoria", idCategoria);
                bundle.putString("busqueda", searchBar.getText().toString());

                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_drawer);
                navController.navigate(R.id.nav_all, bundle);
            }
        });

        mostrarLista();


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AllViewModel.class);
        // TODO: Use the ViewModel


    }

    private void mostrarLista(){
        sendAndRequestResponse();
        adaptadorListaCuentos = new AdaptadorListaCuentos( getActivity(), cuentos);
        listView.setAdapter(adaptadorListaCuentos);
    }


    private void sendAndRequestResponse() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                respuesta = response;
                //Toast.makeText(getActivity().getApplicationContext(),respuesta, Toast.LENGTH_LONG).show();//display the response on screen

                try {
                    JSONObject resultadoJSONObject = new JSONObject(respuesta);
                    resultadoJsonArray = resultadoJSONObject.getJSONArray("Data");
                }catch (JSONException e){
                    e.printStackTrace();
                }

                if(resultadoJsonArray != null){
                /*
                Establecer informacion al adapter
                Colocar el adapter en la lista
                * */
                    ModeloCuentos cuento = null;
                    for (int i = 0; i < resultadoJsonArray.length(); i++){
                        try {
                            JSONObject jsonObject = resultadoJsonArray.getJSONObject(i);
                            cuento = new ModeloCuentos();
                            cuento.txtId = (String)jsonObject.get("idCuento");
                            cuento.txtTitle = (String)jsonObject.get("nombreCuento");
                            cuento.txtDescription = (String)jsonObject.get("sinopsis");
                            cuento.imgStory = (String)jsonObject.get("imgPortada");
                            cuento.numPaginas = (String)jsonObject.get("numPaginas");

                            cuentos.add(cuento);
                        }catch (JSONException e){

                        }
                    }
                    listView.setAdapter(adaptadorListaCuentos);
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Error: El resultadoJsonArray esta vacio", Toast.LENGTH_LONG).show();//display the response on screen

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity().getApplicationContext(),"Error: "+error.toString(), Toast.LENGTH_LONG).show();//display the response on screen

            }
        });

        mRequestQueue.add(mStringRequest);
    }


}