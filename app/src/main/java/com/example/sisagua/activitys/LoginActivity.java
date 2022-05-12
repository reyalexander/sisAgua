package com.example.sisagua.activitys;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sisagua.R;
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
}

