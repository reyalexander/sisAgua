package com.example.sisagua.activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.sisagua.R;
import com.example.sisagua.models.Abonado;
import com.example.sisagua.network.InterfaceAPI;
import com.example.sisagua.utils.ConnectionDetector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.cardview.widget.CardView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    Context context;
    ConnectionDetector connectionDetector;
    static ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        CardView crd_new_check,crd_list_check,crd_lev;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        crd_new_check=(CardView) findViewById(R.id.ideaCard_new);
        crd_list_check=(CardView) findViewById(R.id.ideaCard_lista);

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
    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_update){
            if(connectionDetector.isConnectingToInternet()){
                new UpdateInformation().execute(true);
            } else {
                Toast.makeText(context,"El equipo no tiene conexión a internet",Toast.LENGTH_LONG).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

     */
    /*
    class UpdateInformation extends AsyncTask<Boolean, Void, String>{
        @Override
        protected void onPreExecute(){
            dialog = ProgressDialog.show(context, "", "Loading...", true);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s){
            dialog.dismiss();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Boolean... booleans){
            String token = "";
            try {

                Call<List<Abonado>> getAbonados = api.getAbonados();
                Response<List<Abonado>> responseAbonados = getAbonados.execute();
                List<Abonado> listResponseAbonados = responseAbonados.body();
                /*
                for(Abonado abonado : listResponseAbonados){
                    SqliteClass.getInstance(context).databasehelp.abonadoSql.addAbonado(abonado);
                }




            }catch (IOException e){
                e.printStackTrace();
            }
            return "";
        }

    }*/
}