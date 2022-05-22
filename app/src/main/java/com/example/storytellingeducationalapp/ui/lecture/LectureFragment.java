package com.example.storytellingeducationalapp.ui.lecture;

import androidx.lifecycle.ViewModelProvider;

import android.media.MediaPlayer;
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

import com.amplifyframework.core.Amplify;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LectureFragment extends Fragment {

    private LectureViewModel mViewModel;

    private Button btnAltavoz;
    private ImageView imgCuento;
    private TextView txtEnglish;
    private TextView txtSpanish;
    private TextView txtNum1;
    private TextView txtNum2;
    private Button btnPrev;
    private Button btnNxt;

    private String idStory;
    private String page;
    private int numPaginas;
    private String voice = "On";
    private String urlImg;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    String respuesta;
    private JSONArray resultadoJsonArray;
    private String urlAPI;

    private final MediaPlayer mp = new MediaPlayer();
    InputStream data;

    public static LectureFragment newInstance() {
        return new LectureFragment();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idStory = getArguments().getString("idStory");
            page = getArguments().getString("page");
            numPaginas = getArguments().getInt("numPaginas");
            voice = getArguments().getString("voice");
            urlAPI = "http://naturalbeauty.ddns.net/SEAProject/API/Controller.php?obtener=pagina&idCuento="+idStory+"&pagina="+page;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lectura_cuento, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAltavoz = (Button) view.findViewById(R.id.btnAltavoz);
        imgCuento = (ImageView) view.findViewById(R.id.imgCuento);
        txtEnglish = (TextView) view.findViewById(R.id.txtEnglish);
        txtSpanish = (TextView) view.findViewById(R.id.txtSpanish);
        txtNum1 = (TextView) view.findViewById(R.id.txtnum1);
        txtNum2 = (TextView) view.findViewById(R.id.txtnum2);
        btnPrev = (Button) view.findViewById(R.id.btnPrev);
        btnNxt = (Button) view.findViewById(R.id.btnNxt);

        txtNum1.setText(page);
        txtNum2.setText( Integer.toString(numPaginas) );

        if(!voice.equals("On")){
            btnAltavoz.setBackgroundResource(R.drawable.altavoz_mute);
        }

        int paginaActual = Integer.parseInt(page);

        if (paginaActual == 1){
            btnNxt.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mp.stop();

                    Bundle bundle = new Bundle();
                    bundle.putString("idStory", idStory);
                    bundle.putString("page", Integer.toString(paginaActual+1));
                    bundle.putInt("numPaginas", numPaginas);
                    bundle.putString("voice", voice);

                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_drawer);
                    navController.navigate(R.id.nav_lectura_cuento, bundle);

                }
            });
        }else if (paginaActual == numPaginas){
            btnNxt.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mp.stop();

                    Bundle bundle = new Bundle();
                    bundle.putString("idStory", idStory);
                    bundle.putString("page", Integer.toString(paginaActual+1));
                    bundle.putInt("numPaginas", numPaginas);
                    bundle.putString("voice", voice);

                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_drawer);
                    navController.navigate(R.id.nav_all);

                }
            });

            btnPrev.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mp.stop();

                    Bundle bundle = new Bundle();
                    bundle.putString("idStory", idStory);
                    bundle.putString("page", Integer.toString(paginaActual-1));
                    bundle.putInt("numPaginas", numPaginas);
                    bundle.putString("voice", voice);

                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_drawer);
                    navController.navigate(R.id.nav_lectura_cuento, bundle);

                }
            });
        }else{
            btnNxt.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mp.stop();

                    Bundle bundle = new Bundle();
                    bundle.putString("idStory", idStory);
                    bundle.putString("page", Integer.toString(paginaActual+1));
                    bundle.putInt("numPaginas", numPaginas);
                    bundle.putString("voice", voice);

                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_drawer);
                    navController.navigate(R.id.nav_lectura_cuento, bundle);

                }
            });

            btnPrev.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mp.stop();

                    Bundle bundle = new Bundle();
                    bundle.putString("idStory", idStory);
                    bundle.putString("page", Integer.toString(paginaActual-1));
                    bundle.putInt("numPaginas", numPaginas);
                    bundle.putString("voice", voice);

                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_drawer);
                    navController.navigate(R.id.nav_lectura_cuento, bundle);

                }
            });
        }

        btnAltavoz.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(voice.equals("On")){
                    voice = "Off";
                    btnAltavoz.setBackgroundResource(R.drawable.altavoz_mute);
                    mp.pause();
                }else{
                    voice = "On";
                    btnAltavoz.setBackgroundResource(R.drawable.altavoz);
                    mp.start();
                }

            }
        });

        sendAndRequestResponse();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LectureViewModel.class);
        // TODO: Use the ViewModel
    }

    private void sendAndRequestResponse() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, urlAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                respuesta = response;
                //Toast.makeText(getActivity().getApplicationContext(),respuesta, Toast.LENGTH_LONG).show();//display the response on screen

                try {
                    JSONObject resultadoJSONObject = new JSONObject(respuesta);
                    resultadoJsonArray = resultadoJSONObject.getJSONArray("Data");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (resultadoJsonArray != null) {

                    try {
                        JSONObject jsonObject = resultadoJsonArray.getJSONObject(0);
                        txtEnglish.setText((String) jsonObject.get("textoIng"));
                        txtSpanish.setText((String) jsonObject.get("textoEsp"));
                        urlImg = (String)jsonObject.get("urlImagen");

                        try {
                            Picasso
                                    .get()
                                    .load(urlImg)
                                    //.fit()
                                    .into(imgCuento);
                        } catch (Exception exception) {
                            //Toast.makeText(getActivity(),"Error: "+exception.toString(), Toast.LENGTH_LONG).show();//display the response on screen
                            Log.e("Adaptador: ", exception.toString());
                        }

                        readStory(txtEnglish.getText().toString());
                    } catch (JSONException e) {

                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Error: El resultadoJsonArray esta vacio", Toast.LENGTH_LONG).show();//display the response on screen

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

    private void readStory(String text){
        Amplify.Predictions.convertTextToSpeech(
                text,
                result -> playAudio(result.getAudioData()),
                error -> Log.e("MyAmplifyApp", "Conversion failed", error)
        );

    }

    private void playAudio(InputStream data) {
        File mp3File = new File(getActivity().getCacheDir(), "audio.mp3");
        this.data = data;

        try (OutputStream out = new FileOutputStream(mp3File)) {
            byte[] buffer = new byte[8 * 1_024];
            int bytesRead;
            while ((bytesRead = data.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            mp.reset();

            if(voice.equals("On")) {
                mp.setOnPreparedListener(MediaPlayer::start);
            }else{
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        mp.pause();
                    }
                });
            }

            mp.setDataSource(new FileInputStream(mp3File).getFD());
            mp.prepareAsync();

        } catch (IOException error) {
            Log.e("MyAmplifyApp", "Error writing audio file", error);
        }

    }

}