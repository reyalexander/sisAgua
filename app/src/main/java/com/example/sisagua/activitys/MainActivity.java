package com.example.sisagua.activitys;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.sisagua.R;
import com.example.sisagua.database.SqliteClass;
import com.example.sisagua.dialogs.CommonDialogs;
import com.example.sisagua.models.Abonado;
import com.example.sisagua.models.Medidor;
import com.example.sisagua.network.InterfaceAPI;
import com.example.sisagua.network.RetrofitClientInstance;
import com.example.sisagua.utils.ConnectionDetector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.cardview.widget.CardView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    Context context;
    ConnectionDetector connectionDetector;
    static ProgressDialog dialog;

    String URL = "http://192.168.0.103:8080/";
    String token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmlja05hbWUiOiJKdWFuUCIsInVzZXJJZCI6MSwiaWF0IjoxNjA2ODQ5ODcyLCJleHAiOjE2MDcwMjI2NzJ9.SoVZwyIH20P9kLhllHRUn1QRQX-BQwMXFRrbtIwpw70";

    static Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
    static final InterfaceAPI api = retrofit.create(InterfaceAPI.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        CardView crd_new_check,crd_list_check,crd_lev;
        Button create_data, update_data;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        crd_new_check=(CardView) findViewById(R.id.ideaCard_new);
        crd_list_check=(CardView) findViewById(R.id.ideaCard_lista);

        create_data = (Button) findViewById(R.id.create_data);
        update_data = (Button) findViewById(R.id.update_data);

        create_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new loginTask().execute(true);
            }
        });

        crd_new_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewSuministroActivity.class);//FormNewCheckListActivity.class
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        crd_list_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListSuministrosActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_inner, menu);
        getMenuInflater().inflate(R.menu.menu_main,menu);
        //menu.findItem(R.id.action_profile).setVisible(false);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i =new Intent(MainActivity.this,LoginActivity.class);
        startActivity(i);
        finish();
        return super.onOptionsItemSelected(item);
    }

    class loginTask extends AsyncTask<Boolean, Void, String> {

        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(MainActivity.this, "", "Cargando...", true);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            dialog.dismiss();

            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
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

                // AREA MEDIDORES
                Call<List<Medidor>> getMedidores = api.getMedidores(token);
                Response<List<Medidor>> responseMedidores = getMedidores.execute();
                List<Medidor> listResponseMedidores = responseMedidores.body();

                for(Medidor medidor : listResponseMedidores){
                    medidor.setCodigo(medidor.getCodigo());
                    SqliteClass.getInstance(context).databasehelp.MedidorSql.addMedidor(medidor);
                }

                return "ok";

            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    }

}