package com.example.sisagua.activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sisagua.R;
import com.example.sisagua.models.Abonado;

import java.util.ArrayList;

public class NewSuministroActivity extends AppCompatActivity{

    EditText txt_input_abonados, spnr_medidor;
    Button bt_add_suministro;
    Context context = this;
    Activity activity = this;

    ArrayList<Abonado> abonadoModels = new ArrayList<Abonado>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_suministro);
    }
}
