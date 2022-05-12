package com.example.sisagua.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sisagua.models.Abonado;
import com.example.sisagua.models.Medidor;
import com.example.sisagua.models.Lectura;

import java.io.File;
import java.lang.Integer;
import java.util.ArrayList;



public class SqliteClass {

    /* @TABLE_ABONADO*/
    public static final String TABLE_ABONADO = "app_abonado";
    public static final String AboID = "AboID";
    public static final String AboNombre = "AboNombre";
    public static final String AboApellido = "AboApellido";
    public static final String AboDomicilio = "AboDomicilio";

    /* @TABLE_Lectura*/
    public static final String TABLE_LECTURA = "app_lectura";
    public static final String LecID = "LecID";
    public static final String LecAbonadoId = "LecAbonadoId";
    public static final String LecMedidorId = "LecMedidorId";
    public static final String LecCiclo = "LecCiclo";
    public static final String LecActual = "LecActual";
    /* @TABLE_Medidor*/
    public static final String TABLE_MEDIDOR = "app_medidor";
    public static final String MedID = "MedID";
    public static final String MedCodigo = "MedCodigo";
    public static final String MedAbonadoId = "MedAbonadoId";

    public DatabaseHelper databasehelp;
    private static SqliteClass SqliteInstance = null;

    private SqliteClass(Context context) {
        databasehelp = new DatabaseHelper(context);
    }

    public static SqliteClass getInstance(Context context) {
        if (SqliteInstance == null) {
            SqliteInstance = new SqliteClass(context);
        }
        return SqliteInstance;
    }

    public class DatabaseHelper extends SQLiteOpenHelper {
        public Context context;
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "lectura.db";

        public AppAbonadoSql abonadoSql;
        public AppMedidorSql MedidorSql;
        public AppLecturaSql LecturaSql;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            abonadoSql = new AppAbonadoSql();
            MedidorSql = new AppMedidorSql();
            LecturaSql = new AppLecturaSql();
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            /* @TABLE_ABONADO */
            String CREATE_TABLE_ABONADO = "CREATE TABLE "+TABLE_ABONADO+ "("
                    + AboID + " INTEGER PRIMARY KEY," + AboApellido + " TEXT,"
                    + AboNombre + " TEXT,"  + AboDomicilio + " TEXT)";
            /* @TABLE_ABONADO */
            String CREATE_TABLE_MEDIDOR = "CREATE TABLE "+TABLE_MEDIDOR+ "("
                    + MedID + " INTEGER PRIMARY KEY," + MedAbonadoId + " INTEGER,"
                    + MedCodigo + " TEXT)";
            /* @TABLE_LECTURA */
            String CREATE_TABLE_LECTURA = "CREATE TABLE "+TABLE_LECTURA+ "("
                    + LecID + " INTEGER PRIMARY KEY AUTOINCREMENT," + LecAbonadoId + " INTEGER,"
                    + LecMedidorId + " INTEGER,"+LecCiclo+" DATE,"  + LecActual + " TEXT)";

            /* @EXECSQL_CREATE */
            db.execSQL(CREATE_TABLE_ABONADO);
            db.execSQL(CREATE_TABLE_MEDIDOR);
            db.execSQL(CREATE_TABLE_LECTURA);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            /* @EXECSQL_DROP */
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ABONADO);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDIDOR);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_LECTURA);
        }

        public boolean checkDataBase(){
            File dbFile = new File(context.getDatabasePath(DATABASE_NAME).toString());
            return dbFile.exists();
        }
        public void deleteDataBase(){
            context.deleteDatabase(DATABASE_NAME);
        }

        /* @CLASS_USERSQL */
        public class AppAbonadoSql {
            public AppAbonadoSql() {
            }
            public void deleteAbonados() {
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                db.delete(TABLE_ABONADO, null, null);
                db.close();
            }
            public void addAbonado(Abonado abonado) {
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(AboID, abonado.getAboID());
                values.put(AboApellido, abonado.getAboApellido());
                values.put(AboNombre, abonado.getAboNombre());
                values.put(AboDomicilio, abonado.getAboDomicilio());
                db.insert(TABLE_ABONADO, null, values);
                db.close();
            }
            public Abonado getAbonado(String id){
                Abonado model = new Abonado();
                String selectQuery = "SELECT * FROM " + TABLE_ABONADO + " WHERE " + AboID + "='" + id + "'" ;
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                Cursor curso = db.rawQuery(selectQuery, null);
                if (curso.moveToFirst()) {
                    model.setAboID(curso.getString(1));
                    model.setAboApellido(curso.getString(2));
                    model.setAboNombre(curso.getString(3));
                    model.setAboDomicilio(curso.getString(4));
                }
                curso.close();
                db.close();
                return model;
            }
            public ArrayList<Abonado> getAllAbonados() {
                ArrayList<Abonado> models = new ArrayList<Abonado>();
                String selectQuery = "SELECT * FROM " + TABLE_ABONADO;
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                Cursor curso = db.rawQuery(selectQuery, null);
                if (curso.moveToFirst()) {

                    do{
                        Abonado model = new Abonado();
                        model.setAboID(curso.getString(1));
                        model.setAboApellido(curso.getString(2));
                        model.setAboNombre(curso.getString(3));
                        model.setAboDomicilio(curso.getString(4));
                        models.add(model);
                    } while (curso.moveToNext());

                }
                curso.close();
                db.close();
                return models;
            }
        }

        /* @CLASS_MEDIDORSQL */
        public class AppMedidorSql {
            public void deleteMedidor() {
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                db.delete(TABLE_MEDIDOR, null, null);
                db.close();
            }

            public void addMedidor(Medidor contac) {
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(MedID, contac.getMedID());
                values.put(MedAbonadoId, contac.getMedAbonadoId());
                values.put(MedCodigo, contac.getMedCodigo());
                db.insert(TABLE_MEDIDOR, null, values);
                db.close();
            }

            public ArrayList<Medidor> getAllMedidor() {
                ArrayList<Medidor> models = new ArrayList<Medidor>();
                String selectQuery = "SELECT * FROM " + TABLE_MEDIDOR;
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {
                    do{
                        Medidor model = new Medidor();
                        model.setMedID(Integer.parseInt(cursor.getString(1)));
                        model.setMedAbonadoId(Integer.parseInt(cursor.getString(2)));
                        model.setMedCodigo(cursor.getString(3));
                        models.add(model);
                    } while (cursor.moveToNext());

                }
                cursor.close();
                db.close();
                return models;
            }
            public ArrayList<Medidor> getMedidorAbonado(String id) {
                ArrayList<Medidor> models = new ArrayList<Medidor>();
                String selectQuery = "SELECT * FROM " + TABLE_MEDIDOR+" WHERE "+MedAbonadoId+ "='" + id + "'" ;
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {
                    do{
                        Medidor model = new Medidor();
                        model.setMedID(Integer.parseInt(cursor.getString(1)));
                        model.setMedAbonadoId(Integer.parseInt(cursor.getString(2)));
                        model.setMedCodigo(cursor.getString(3));
                        models.add(model);
                    } while (cursor.moveToNext());

                }
                cursor.close();
                db.close();
                return models;
            }

            public Medidor getMedidor(Integer id){
                Medidor model = new Medidor();
                String selectQuery = "SELECT * FROM " + TABLE_MEDIDOR + " WHERE " + MedID + "='" + id + "'" ;
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {

                    model.setMedID(Integer.parseInt(cursor.getString(1)));
                    model.setMedAbonadoId(Integer.parseInt(cursor.getString(2)));
                    model.setMedCodigo(cursor.getString(3));
                }
                cursor.close();
                db.close();
                return model;
            }
        }

        /* @CLASS_LECTURASQL */
        public class AppLecturaSql {
            public void deleteLectura() {
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                db.delete(TABLE_LECTURA, null, null);
                db.close();
            }

            public void addLectura(Lectura contac) {
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(LecID, contac.getLecID());
                values.put(LecAbonadoId, contac.getLecAbonadoId());
                values.put(LecMedidorId, contac.getLecMedidorId());
                values.put(LecCiclo, contac.getLecCiclo());
                values.put(LecActual, contac.getLecActual());
                db.insert(TABLE_LECTURA, null, values);
                db.close();
            }

            public ArrayList<Lectura> getAllLectura() {
                ArrayList<Lectura> models = new ArrayList<Lectura>();
                String selectQuery = "SELECT * FROM " + TABLE_LECTURA;
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {
                    do{
                        Lectura model = new Lectura();
                        model.setLecID(Integer.parseInt(cursor.getString(1)));
                        model.setLecAbonadoId(Integer.parseInt(cursor.getString(2)));
                        model.setLecMedidorId(Integer.parseInt(cursor.getString(3)));
                        model.setLecCiclo(cursor.getString(4));
                        model.setLecActual(cursor.getString(5));
                        models.add(model);
                    } while (cursor.moveToNext());
                }
                cursor.close();
                db.close();
                return models;
            }
            public ArrayList<Lectura> getLecturaXAbonado(String id) {
                ArrayList<Lectura> models = new ArrayList<Lectura>();
                String selectQuery = "SELECT * FROM " + TABLE_LECTURA+" WHERE "+LecAbonadoId+ "='" + id + "'" ;
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {
                    do{
                        Lectura model = new Lectura();
                        model.setLecID(Integer.parseInt(cursor.getString(1)));
                        model.setLecAbonadoId(Integer.parseInt(cursor.getString(2)));
                        model.setLecMedidorId(Integer.parseInt(cursor.getString(3)));
                        model.setLecCiclo(cursor.getString(4));
                        model.setLecActual(cursor.getString(5));
                        models.add(model);
                    } while (cursor.moveToNext());

                }
                cursor.close();
                db.close();
                return models;
            }

            public Lectura getLectura(Integer id){
                Lectura model = new Lectura();
                String selectQuery = "SELECT * FROM " + TABLE_LECTURA + " WHERE " + LecID + "='" + id + "'" ;
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {

                    model.setLecID(Integer.parseInt(cursor.getString(1)));
                    model.setLecAbonadoId(Integer.parseInt(cursor.getString(2)));
                    model.setLecMedidorId(Integer.parseInt(cursor.getString(3)));
                    model.setLecCiclo(cursor.getString(4));
                    model.setLecActual(cursor.getString(5));
                }
                cursor.close();
                db.close();
                return model;
            }
        }
    }

}
