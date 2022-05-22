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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.storytellingeducationalapp.R;
import com.example.storytellingeducationalapp.ui.modelos.ModeloCuentos;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InfoCuentoFragment extends Fragment {

    private InfoCuentoViewModel mViewModel;

    private ImageView imgPortada;
    private TextView txtTitle;
    private TextView txtDescription;
    private Button btnPlay;
    private Button btnPrevious;
    private Button btnNext;

    private String idStory;
    private int numCuentos;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "http://naturalbeauty.ddns.net/SEAProject/API/Controller.php?obtener=cuentos";
    private JSONArray resultadoJsonArray;
    String respuesta;

    public static InfoCuentoFragment newInstance() {
        return new InfoCuentoFragment();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idStory = getArguments().getString("idStory");
            url = "http://naturalbeauty.ddns.net/SEAProject/API/Controller.php?obtener=cuento&id="+idStory;

            numCuentos = getArguments().getInt("numCuentos");
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

        sendAndRequestResponse();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InfoCuentoViewModel.class);
        // TODO: Use the ViewModel
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

                if(resultadoJsonArray != null){try {
                            JSONObject jsonObject = resultadoJsonArray.getJSONObject(0);

                            txtTitle.setText((String)jsonObject.get("nombreCuento"));
                            txtDescription.setText((String)jsonObject.get("sinopsis"));

                            try {
                                Picasso
                                        .get()
                                        .load((String)jsonObject.get("imgPortada"))
                                        //.fit()
                                        .into(imgPortada);
                            }catch (Exception exception){
                                //Toast.makeText(context,"Error: "+exception.toString(), Toast.LENGTH_LONG).show();//display the response on screen
                                Log.e("Adaptador: ",exception.toString());
                            }

                            idStory = (String)jsonObject.get("idCuento");
                            String numPaginas = (String)jsonObject.get("numPaginas");

                            btnPlay.setOnClickListener(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("idStory", idStory);
                                    bundle.putString("page", "1");
                                    bundle.putInt("numPaginas", Integer.parseInt(numPaginas));
                                    bundle.putString("voice", "On");

                                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_drawer);
                                    navController.navigate(R.id.nav_lectura_cuento, bundle);

                                }
                            });

                            int ID = Integer.parseInt( idStory );
                            String prevID = ID == 1? Integer.toString(numCuentos) : Integer.toString(ID-1);
                            String nextID = ID == numCuentos? Integer.toString(1) : Integer.toString(ID+1);

                            btnPrevious.setOnClickListener(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("idStory", prevID);
                                    bundle.putInt("numCuentos", numCuentos);

                                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_drawer);
                                    navController.navigate(R.id.nav_info_story, bundle);

                                }
                            });

                            btnNext.setOnClickListener(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("idStory", nextID);
                                    bundle.putInt("numCuentos", numCuentos);

                                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_drawer);
                                    navController.navigate(R.id.nav_info_story, bundle);

                                }
                            });

                        }catch (JSONException e){

                        }
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