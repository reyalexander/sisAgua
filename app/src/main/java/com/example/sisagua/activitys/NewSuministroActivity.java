package com.example.sisagua.activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sisagua.R;
import com.example.sisagua.database.SqliteClass;
import com.example.sisagua.models.Abonado;
import com.example.sisagua.models.Medidor;
import com.example.sisagua.network.InterfaceAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewSuministroActivity extends AppCompatActivity{
    String URL = "http://192.168.0.101:8080/";
    String token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmlja05hbWUiOiJKdWFuUCIsInVzZXJJZCI6MSwiaWF0IjoxNjA2ODQ5ODcyLCJleHAiOjE2MDcwMjI2NzJ9.SoVZwyIH20P9kLhllHRUn1QRQX-BQwMXFRrbtIwpw70";
    String sup = "0";

    EditText txt_input_abonados;
    TextView tv_data;
    Spinner spnr_medidor,spnr_abonado;
    Button bt_add_suministro;
    Context context = this;
    Activity activity = this;

    List<Medidor> getMedidoresCodigo = new ArrayList<>();
    List<Abonado> getAbonadosNames= new ArrayList<>();


    ArrayList<Abonado> abonados = new ArrayList<Abonado>();
    Medidor medidor = new Medidor();
    /*
    ArrayList<String> nameAbonados = new ArrayList<String>();
    ArrayList<String> dniAbonados = new ArrayList<String>();

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_suministro);

        txt_input_abonados = (EditText) findViewById(R.id.txt_input_abonados);
        spnr_medidor = (Spinner) findViewById(R.id.spnr_medidor);
        spnr_abonado = (Spinner) findViewById(R.id.spnr_abonados);
        System.out.println("Hola mundoooooooo xdXD");
        getAbonados();
        getMedidores();

        txt_input_abonados.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                /*
                System.out.println("Hola pedo pendejosss");
                System.out.println(abonadoModels);
                TextView contador = (TextView) findViewById(R.id.texto_contador);
                String tamanoString = String.valueOf(s.length());
                contador.setText(s.toString());
                System.out.println(s +"   "+tamanoString);
                 */

                getAbonados();
                System.out.println("");
            }
        });


    }
    /*
    private void getAbonados(){


        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        InterfaceAPI interfaceAPI = retrofit.create(InterfaceAPI.class);
        Call<List<Abonado>> call = interfaceAPI.getAbonados(token);
        call.enqueue(new Callback<List<Abonado>>() {
            @Override
            public void onResponse(Call<List<Abonado>>call, Response<List<Abonado>> response) {
                try {
                    if (!response.isSuccessful()){
                        tv_data.setText("Nombres: " + response.code());
                        return;
                    }

                    List<Abonado> abonadoList = response.body();
                    for(Abonado abonado : abonadoList){
                        String content = "";
                        content += "Nombres: " + abonado.getNombres() + " ";
                        content += "" + abonado.getApellidos() + "\n\n";
                        tv_data.append(content);
                    }
                }catch (Exception ex){
                    Toast.makeText(NewSuministroActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Abonado>> call, Throwable t) {
                Toast.makeText(NewSuministroActivity.this,"Error de conexion", Toast.LENGTH_SHORT);
            }

        });
    }

     */
    private void getAbonados(){
        ArrayAdapter<Abonado> adapter = new ArrayAdapter<Abonado>(this, android.R.layout.simple_spinner_item,getAbonadosNames);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        InterfaceAPI interfaceAPI = retrofit.create(InterfaceAPI.class);
        Call<List<Abonado>> call = interfaceAPI.getAbonados(token);
        call.enqueue(new Callback<List<Abonado>>() {
            @Override
            public void onResponse(Call<List<Abonado>>call, Response<List<Abonado>> response) {
                Log.i("Response", response.body().toString());
                if (response.isSuccessful()) {
                    for (Abonado postabonado : response.body()){
                        String name = postabonado.getNombres();
                        String lastname = postabonado.getApellidos();
                        Abonado abonado = new Abonado(name , lastname);
                        getAbonadosNames.add(abonado);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnr_abonado.setAdapter(adapter);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Abonado>> call, Throwable t) {
                Toast.makeText(NewSuministroActivity.this,"Error de conexion", Toast.LENGTH_SHORT);
            }

        });
    }

    private void getMedidores(){
        ArrayAdapter<Medidor> adapter = new ArrayAdapter<Medidor>(this, android.R.layout.simple_spinner_item,getMedidoresCodigo);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        InterfaceAPI interfaceAPI = retrofit.create(InterfaceAPI.class);
        Call<List<Medidor>> call = interfaceAPI.getMedidores(token);
        call.enqueue(new Callback<List<Medidor>>() {
            @Override
            public void onResponse(Call<List<Medidor>>call, Response<List<Medidor>> response) {
                Log.i("Response", response.body().toString());
                if (response.isSuccessful()) {
                    for (Medidor post : response.body()){
                        String code = post.getCodigo();
                        Medidor medidor = new Medidor(code);
                        getMedidoresCodigo.add(medidor);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnr_medidor.setAdapter(adapter);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Medidor>> call, Throwable t) {
                Toast.makeText(NewSuministroActivity.this,"Error de conexion", Toast.LENGTH_SHORT);
            }

        });
    }
}
