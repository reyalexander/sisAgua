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
    public static final String AboIdSQL = "AboIdSQL";
    public static final String AboID = "AboID";
    public static final String AboNombre = "AboNombre";
    public static final String AboApellido = "AboApellido";
    public static final String AboDomicilio = "AboDomicilio";
    public static final String AboDNI = "AboDni";

    /* @TABLE_Medidor*/
    public static final String TABLE_MEDIDOR = "app_medidor";
    public static final String MedID = "MedID";
    public static final String MedIdSQL = "MedIdSQL";
    public static final String MedCodigo = "MedCodigo";
    public static final String MedAbonadoId = "MedAbonadoId";
    public static final String MedTipo = "MedTipo";
    public static final String MedlecturaActual = "MedlecturaActual";
    public static final String MedfechaActual = "MedfechaActual";

    /* @TABLE_Lectura*/
    public static final String TABLE_LECTURA = "app_lectura";
    public static final String LecID = "LecID";
    public static final String LecAbonadoId = "LecAbonadoId";
    public static final String LecMedidorId = "LecMedidorId";
    public static final String LecCiclo = "LecCiclo";
    public static final String LecActual = "LecActual";

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
        private static final String DATABASE_NAME = "app_sisagua.db";

        public AppAbonadoSql abonadoSql;
        public AppMedidorSql MedidorSql;
        //public AppLecturaSql LecturaSql;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            abonadoSql = new AppAbonadoSql();
            MedidorSql = new AppMedidorSql();
            //LecturaSql = new AppLecturaSql();
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            /* @TABLE_ABONADO */
            String CREATE_TABLE_ABONADO = "CREATE TABLE "+TABLE_ABONADO+ "("
                    + AboIdSQL + "  INTEGER PRIMARY KEY AUTOINCREMENT,"+ AboID + " INTEGER," +  AboDNI + " TEXT,"+ AboApellido + " TEXT,"
                    + AboNombre + " TEXT,"  + AboDomicilio + " TEXT)";
            /* @TABLE_ABONADO */
            String CREATE_TABLE_MEDIDOR = "CREATE TABLE "+TABLE_MEDIDOR+ "("
                    +MedIdSQL + "  INTEGER PRIMARY KEY AUTOINCREMENT,"+ MedID + " INTEGER," + MedCodigo + " TEXT,"+ MedTipo + " TEXT,"+ MedAbonadoId + " INTEGER,"
                    + MedlecturaActual + " DOUBLE,"+MedfechaActual + " TEXT)";
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
            onCreate(db);
        }

        public boolean checkDataBase(){
            File dbFile = new File(context.getDatabasePath(DATABASE_NAME).toString());
            return dbFile.exists();
        }
        public void deleteDataBase(){
            context.deleteDatabase(DATABASE_NAME);
        }

        /* @CLASS_ABONADOSQL */
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
                values.put(AboID, abonado.getId());
                values.put(AboDNI,abonado.getDni());
                values.put(AboApellido, abonado.getApellidos());
                values.put(AboNombre, abonado.getNombres());
                values.put(AboDomicilio, abonado.getDomicilio());
                db.insert(TABLE_ABONADO, null, values);
                db.close();
            }
            public Abonado getAbonado(String id){
                Abonado model = new Abonado();
                String selectQuery = "SELECT * FROM " + TABLE_ABONADO + " WHERE " + AboID + "='" + id + "'" ;
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                Cursor curso = db.rawQuery(selectQuery, null);
                if (curso.moveToFirst()) {
                    model.setIdSQL(curso.getInt(1));
                    model.setId(curso.getInt(2));
                    model.setDni(curso.getString(3));
                    model.setApellidos(curso.getString(4));
                    model.setNombres(curso.getString(5));
                    model.setDomicilio(curso.getString(6));
                }
                curso.close();
                db.close();
                return model;
            }
            public ArrayList<Abonado> getAllAbonados() {
                ArrayList<Abonado> models = new ArrayList<>();
                String selectQuery = "SELECT * FROM " + TABLE_ABONADO;
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                Cursor curso = db.rawQuery(selectQuery, null);
                if (curso.moveToFirst()) {

                    do{
                        Abonado model = new Abonado();
                        model.setIdSQL(curso.getInt(1));
                        model.setId(curso.getInt(1));
                        model.setDni(curso.getString(2));
                        model.setApellidos(curso.getString(3));
                        model.setNombres(curso.getString(4));
                        model.setDomicilio(curso.getString(5));
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
                values.put(MedID, contac.getId());
                values.put(MedCodigo,contac.getCodigo());
                values.put(MedTipo,contac.getTipo());
                values.put(MedAbonadoId,contac.getAbonadoId());
                values.put(MedlecturaActual,contac.getLecturaActual());
                values.put(MedfechaActual,contac.getFechaActual());
                db.insert(TABLE_MEDIDOR, null, values);
                db.close();
            }

            public ArrayList<Medidor> getAllMedidor() {
                ArrayList<Medidor> models = new ArrayList<>();
                String selectQuery = "SELECT * FROM " + TABLE_MEDIDOR;
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {
                    do{
                        Medidor model = new Medidor();
                        model.setIdSQL(cursor.getInt(1));
                        model.setId(cursor.getInt(2));
                        model.setCodigo(cursor.getString(3));
                        model.setTipo(cursor.getString(4));
                        model.setAbonadoId(cursor.getInt(5));
                        model.setLecturaActual(cursor.getDouble(6));
                        model.setFechaActual(cursor.getString(7));
                        models.add(model);
                    } while (cursor.moveToNext());

                }
                cursor.close();
                db.close();
                return models;
            }
            public ArrayList<Medidor> getMedidorAbonado(String id) {
                ArrayList<Medidor> models = new ArrayList<>();
                String selectQuery = "SELECT * FROM " + TABLE_MEDIDOR+" WHERE "+MedAbonadoId+ "='" + id + "'" ;
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {
                    do{
                        Medidor model = new Medidor();
                        model.setIdSQL(cursor.getInt(1));
                        model.setId(cursor.getInt(2));
                        model.setCodigo(cursor.getString(3));
                        model.setTipo(cursor.getString(4));
                        model.setAbonadoId(cursor.getInt(5));
                        model.setLecturaActual(cursor.getDouble(6));
                        model.setFechaActual(cursor.getString(7));
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
                    model.setIdSQL(cursor.getInt(1));
                    model.setId(cursor.getInt(2));
                    model.setCodigo(cursor.getString(3));
                    model.setTipo(cursor.getString(4));
                    model.setAbonadoId(cursor.getInt(5));
                    model.setLecturaActual(cursor.getDouble(6));
                    model.setFechaActual(cursor.getString(7));
                }
                cursor.close();
                db.close();
                return model;
            }
        }

        /* @CLASS_LECTURASQL */
        /*
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
                ArrayList<Lectura> models = new ArrayList<>();
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
                ArrayList<Lectura> models = new ArrayList<>();
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


        }*/
    }

}
