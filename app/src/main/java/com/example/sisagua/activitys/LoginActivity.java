package com.example.sisagua.activitys;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sisagua.R;
import com.example.sisagua.database.SqliteClass;
import com.example.sisagua.models.Abonado;
import com.example.sisagua.network.InterfaceAPI;
import com.example.sisagua.network.RetrofitClientInstance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
//import com.example.guardian.databases.SqliteClass;
//import com.example.guardian.utils.ConnectionDetector;


public class LoginActivity extends AppCompatActivity {
    private  final int REQUEST_ACCESS_FINE = 0;
    EditText et_user, et_pass;
    Button btLogin,btnRegister;
    Context context;
    Activity activity;
    ProgressDialog dialog;
    String username,password;

    String URL = "http://192.168.0.100:8080/";
    String token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmlja05hbWUiOiJKdWFuUCIsInVzZXJJZCI6MSwiaWF0IjoxNjA2ODQ5ODcyLCJleHAiOjE2MDcwMjI2NzJ9.SoVZwyIH20P9kLhllHRUn1QRQX-BQwMXFRrbtIwpw70";

    static Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
    static final InterfaceAPI api = retrofit.create(InterfaceAPI.class);

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context=this;

        et_user=(EditText) findViewById(R.id.et_username);
        et_pass=(EditText) findViewById(R.id.et_password);
        et_user.setText("admin123");
        et_pass.setText("joya123");
        username = "admin123";
        password = "joya123";

        btLogin = (Button) findViewById(R.id.bt_login);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et_user.getText().toString().length()==0){
                    et_user.setError("Ingrese Usuario");
                }else if (et_pass.getText().toString().length()==0){
                    et_pass.setError("Ingrese Contraseña");
                } else {
                    /*** Solo porque no esta en el server */
                    new loginTask().execute(true);
                }

            }
        });

        btnRegister=(Button) findViewById(R.id.bt_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

    }

    class loginTask extends AsyncTask<Boolean, Void, String> {
        String username, password;

        @Override
        protected void onPreExecute() {
            username = et_user.getText().toString();
            password = et_pass.getText().toString();
            dialog = ProgressDialog.show(LoginActivity.this, "", "Cargando...", true);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            dialog.dismiss();
            if(et_user.getText().length()>0 && et_pass.getText().length()>0){
                if(et_user.getText().toString().equals(username) && et_pass.getText().toString().equals(password)){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(context,"SisAgua : Usario y/o contraseña o contraseña incorrecta",Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(context,"SisAgua : Ingrese un usario y/o contraseña",Toast.LENGTH_LONG).show();
            }
            /*
            if(s.equals("ok")){
                //Intent intent = new Intent(LoginActivity.this, FormNewCheckListActivity.class);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            } else if(s.equals("nouser")){
                Toast.makeText(context,"Este Usuario no tiene permiso para acceder",Toast.LENGTH_LONG).show();

            }else {
                Toast.makeText(context,"Problemas para acceder al servidor",Toast.LENGTH_LONG).show();
            }

             */

            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(Boolean... booleans) {
            String result = "";

            try {
                // AREA ABONADOS
                Call<List<Abonado>> getAbonados = api.getAbonados(token);
                Response<List<Abonado>> responseAbonados = getAbonados.execute();
                List<Abonado> listResponseAbonados = responseAbonados.body();

                for(Abonado abonado : listResponseAbonados){
                    abonado.setNombres(abonado.getNombres());
                    SqliteClass.getInstance(context).databasehelp.abonadoSql.addAbonado(abonado);
                }

                return "ok";

            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}

