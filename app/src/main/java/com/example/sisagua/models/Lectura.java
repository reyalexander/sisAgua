package com.example.sisagua.models;
import java.lang.Integer;

public class Lectura{

    private int id;
    private int lecturaActual;
    private int aboId;
    private int cicloId;
    private int medidorId;


    public Lectura(){
        lecturaActual = 0;
        aboId = 0;
        cicloId = 0;
        medidorId = 0;
    }

    public Lectura(int lecturaActual) {
        this.lecturaActual = lecturaActual;
    }


    public int getIdSQL() {
        return id;
    }

    public void setIdSQL(int idSQL) {
        this.id = idSQL;
    }

    public int getLecturaActual() {
        return lecturaActual;
    }

    public void setLecturaActual(int lecturaActual) {
        this.lecturaActual = lecturaActual;
    }

    public int getAboId() {
        return aboId;
    }

    public void setAboId(int aboId) {
        this.aboId = aboId;
    }

    public int getCicloId() {
        return cicloId;
    }

    public void setCicloId(int cicloId) {
        this.cicloId = cicloId;
    }

    public int getMedidorId() {
        return medidorId;
    }

    public void setMedidorId(int medidorId) {
        this.medidorId = medidorId;
    }

    @Override
    public String toString() {
        return "Lectura{" +
                "id=" + id +
                ", lecturaActual=" + lecturaActual +
                ", aboId=" + aboId +
                ", cicloId=" + cicloId +
                ", medidorId=" + medidorId +
                '}';
    }
}

