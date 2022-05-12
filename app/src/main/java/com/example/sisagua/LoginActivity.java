package com.example.sisagua;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sisagua.R;
//import com.example.guardian.databases.SqliteClass;
//import com.example.guardian.utils.ConnectionDetector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity {
    private  final int REQUEST_ACCESS_FINE = 0;
    EditText et_user, et_pass;
    Button btLogin,btnRegister;
    Context context;
    Activity activity;
    ProgressDialog dialog;
    String username,password;
    //ConnectionDetector cd;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        context=this;
       // protocol=new Protocol();
       // cd=new ConnectionDetector(context);

        //Utils.enableLocation(LoginActivity.this,context);

        //Utils.enableMessage(LoginActivity.this,context);
        //Utils.enableReadStorage(LoginActivity.this,context);
        //Utils.enableWriteStorage(LoginActivity.this,context);
        //Utils.enableReadPhoneState(LoginActivity.this,context);
        //Utils.enableCallPhone(LoginActivity.this,context);

        /** Mantener sesión activa **/
        SharedPreferences sharedPref = getSharedPreferences("login_preferences",Context.MODE_PRIVATE);
        String logueado=sharedPref.getString("logueado","inactive");
        if(logueado.equals("active")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        }

        et_user=(EditText) findViewById(R.id.et_username);
        et_pass=(EditText) findViewById(R.id.et_password);
        //et_user.setText("admin");
        //et_pass.setText("P4ssw0rd!");

        btLogin = (Button) findViewById(R.id.bt_login);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et_user.getText().length()>0 && et_pass.getText().length()>0){
                    /*if (cd.isConnectingToInternet()) {
                        //new loginTask().execute(true);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(context,"Tranki : Su dispositivo no cuenta con conexión a internet.",Toast.LENGTH_LONG).show();
                    }*/
                }else {
                    Toast.makeText(context,"Tranki : Ingrese un usario y/o contraseña",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnRegister=(Button) findViewById(R.id.bt_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

    }
}

