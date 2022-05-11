package com.example.storytellingeducationalapp.ui.categories;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.storytellingeducationalapp.R;
//import com.example.storytellingeducationalapp.databinding.FragmentCategoriesBinding;
import com.example.storytellingeducationalapp.ui.adaptadores.AdaptadorListaCategorias;
import com.example.storytellingeducationalapp.ui.adaptadores.AdaptadorListaCuentos;
import com.example.storytellingeducationalapp.ui.modelos.ModeloCategorias;
import com.example.storytellingeducationalapp.ui.modelos.ModeloCuentos;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesFragment extends Fragment {

    private CategoriesViewModel categoriesViewModel;

    private ListView listView;
    private AdaptadorListaCategorias adaptadorListaCategorias;
    private ArrayList<ModeloCategorias> categorias;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "http://naturalbeauty.ddns.net/SEAProject/API/Controller.php?obtener=categorias";
    private JSONArray resultadoJsonArray;
    String respuesta;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoriesViewModel =
                new ViewModelProvider(this).get(CategoriesViewModel.class);


        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) view.findViewById(R.id.listCategories);
        categorias = new ArrayList<ModeloCategorias>();

        mostrarLista();
    }

    private void mostrarLista(){
        sendAndRequestResponse();
        adaptadorListaCategorias = new AdaptadorListaCategorias( getActivity(), categorias);
        listView.setAdapter(adaptadorListaCategorias);
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
                    ModeloCategorias categoria = null;
                    for (int i = 0; i < resultadoJsonArray.length(); i++){
                        try {
                            JSONObject jsonObject = resultadoJsonArray.getJSONObject(i);
                            categoria = new ModeloCategorias();
                            categoria.txtCategory = (String)jsonObject.get("nombreCategoria");
                            categoria.imgCategory = (String)jsonObject.get("urlImagen");

                            categorias.add(categoria);
                        }catch (JSONException e){

                        }
                    }
                    listView.setAdapter(adaptadorListaCategorias);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }
}