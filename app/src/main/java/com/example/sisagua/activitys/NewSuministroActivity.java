package com.example.sisagua.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sisagua.R;
import com.example.sisagua.database.SqliteClass;
import com.example.sisagua.models.Abonado;
import com.example.sisagua.models.Lectura;
import com.example.sisagua.models.LecturaResponse;
import com.example.sisagua.models.Medidor;
import com.example.sisagua.network.InterfaceAPI;
import com.example.sisagua.network.RetrofitClientInstance;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.ext.LexicalHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.widget.AdapterView.OnItemSelectedListener;

public class NewSuministroActivity extends AppCompatActivity{
    String URL = "http://192.168.0.8:8080/";
    String token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmlja05hbWUiOiJKdWFuUCIsInVzZXJJZCI6MSwiaWF0IjoxNjA2ODQ5ODcyLCJleHAiOjE2MDcwMjI2NzJ9.SoVZwyIH20P9kLhllHRUn1QRQX-BQwMXFRrbtIwpw70";

    EditText txt_input_abonados, et_lectura;
    TextView tv_data;
    Spinner spnr_medidor,spnr_abonado;
    Button btnFire, btnCancel;

    Context context = this;
    Activity activity = this;

    List<Medidor> getMedidoresCodigo = new ArrayList<>();
    List<Abonado> getAbonadosNames= new ArrayList<>();
    int MedidorE,  AbonadoE;

    Medidor medidor = new Medidor();
    Abonado abonado = new Abonado();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_suministro);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //getMedidores();
        //getAbonados();

        txt_input_abonados = (EditText) findViewById(R.id.txt_input_abonados);
        spnr_medidor = (Spinner) findViewById(R.id.spnr_medidor);
        spnr_abonado = (Spinner) findViewById(R.id.spnr_abonados);
        spnr_abonado.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Abonado item =(Abonado)(parent.getItemAtPosition(position));
                AbonadoE= item.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnr_medidor.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Medidor item =(Medidor)(parent.getItemAtPosition(position));
                MedidorE = item.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        et_lectura = (EditText) findViewById(R.id.et_lectura);
        btnFire = (Button) findViewById(R.id.btn_ok);
        btnCancel = (Button) findViewById(R.id.btn_cancel);

        getAbonados();
        getMedidores();
        //CallRetrofit();


        txt_input_abonados.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                System.out.println("Hola pedo pendejosss");
                TextView contador = (TextView) findViewById(R.id.texto_contador);
                String tamanoString = String.valueOf(s.length());
                contador.setText(s.toString());
                System.out.println(s +"   "+tamanoString);
                System.out.println("");
            }
        });


        btnFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                postLecturas(createLectura());
                Toast.makeText(NewSuministroActivity.this,"Guardado Exitosamente!", Toast.LENGTH_SHORT);
                Intent intent = new Intent(NewSuministroActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
                /*
                Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create()).build();
                InterfaceAPI interfaceAPI = retrofit.create(InterfaceAPI.class);
                Call<Lectura> call = interfaceAPI.postLecturas(token, lectura);
                call.enqueue(new Callback<Lectura>() {
                    @Override
                    public void onResponse(Call<Lectura> call, Response<Lectura> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(NewSuministroActivity.this,"Guardado Exitosamente!", Toast.LENGTH_SHORT);
                        }else {
                            Toast.makeText(NewSuministroActivity.this,"Guardado Exitosamente!!", Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onFailure(Call<Lectura> call, Throwable t) {
                        Toast.makeText(NewSuministroActivity.this,"Guardado Exitosamente!!!", Toast.LENGTH_SHORT);
                    }
                });
                */

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewSuministroActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });


    }

    /*
    Funcionalidad para utilizar el boton de ir atras
     */
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i =new Intent(NewSuministroActivity.this,MainActivity.class);
        startActivity(i);
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(context, "Envio Exitoso", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    public Lectura createLectura(){

        Lectura lectura = new Lectura();

        String lect = et_lectura.getText().toString();
        int lecActual = Integer.parseInt(lect);
        lectura.setLecturaActual(lecActual);
        Calendar c1 = Calendar.getInstance();
        lectura.setCicloId(c1.get(Calendar.MONTH)+1);
        lectura.setAboId(AbonadoE);
        lectura.setMedidorId(MedidorE);
        //lectura.setMedidorId(medidor.getId());

        return lectura;
    }

    public void postLecturas(Lectura lectura){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceAPI interfaceAPI = retrofit.create(InterfaceAPI.class);
        Call<Lectura> call = interfaceAPI.postLecturas(token, lectura);
        call.enqueue(new Callback<Lectura>() {
            @Override
            public void onResponse(Call<Lectura> call, Response<Lectura> response) {
                if(response.isSuccessful()){
                    Toast.makeText(NewSuministroActivity.this,"Guardado Exitosamente!", Toast.LENGTH_SHORT);
                }else {
                    Toast.makeText(NewSuministroActivity.this,"Guardado Exitosamente!!", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<Lectura> call, Throwable t) {
                Toast.makeText(NewSuministroActivity.this,"Guardado Exitosamente!!!", Toast.LENGTH_SHORT);
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
                        abonado.setId(postabonado.getId());
                        abonado.setIdSQL(postabonado.getIdSQL());
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
                        medidor.setId(post.getId());
                        medidor.setIdSQL(post.getIdSQL());
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
