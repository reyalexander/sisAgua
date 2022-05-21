package com.example.sisagua.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.sisagua.R;
import com.example.sisagua.database.SqliteClass;
import com.example.sisagua.activitys.LoginActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class CommonDialogs {

    public static void showLogoutDialog(final Activity activity, final Context context){
        new MaterialAlertDialogBuilder(activity)
                .setTitle("Salir")
                .setMessage("¿Está seguro de salir de la aplicación? Todos los datos no enviados serán eliminados.")
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedPref = context.getSharedPreferences("login_preferences", Context.MODE_PRIVATE);
                        SqliteClass.getInstance(context).databasehelp.abonadoSql.deleteAbonados();
                        //SqliteClass.getInstance(context).databasehelp.sectionSql.deleteUnitTable();

                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("logueado", "inactive");
                        editor.apply();
                        activity.finish();
                        Intent i = new Intent(activity, LoginActivity.class);
                        activity.startActivity(i);
                    }
                })
                .show();
    }


}
