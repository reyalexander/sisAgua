package com.example.sisagua.activitys;

import android.content.Context;
import android.os.Bundle;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import com.example.sisagua.R;


public class RegisterActivity extends AppCompatActivity{
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i =new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(i);
        finish();
        return super.onOptionsItemSelected(item);
    }
}
