package com.example.sisagua.models;
import java.lang.Integer;

public class Lectura{
    private int LecID;
    private int LecAbonadoId;
    private int LecMedidorId;
    private String LecCiclo;
    private String LecActual;

    public Lectura(){
        LecID = 0;
        LecAbonadoId = 0;
        LecMedidorId = 0;
        LecCiclo = "";
        LecActual = "";
    }

    public Lectura(int lecID, int lecAbonadoId, int lecMedidorId, String lecCiclo, String lecActual) {
        LecID = lecID;
        LecAbonadoId = lecAbonadoId;
        LecMedidorId = lecMedidorId;
        LecCiclo = lecCiclo;
        LecActual = lecActual;
    }

    public int getLecID() {
        return LecID;
    }

    public void setLecID(int lecID) {
        LecID = lecID;
    }

    public int getLecAbonadoId() {
        return LecAbonadoId;
    }

    public void setLecAbonadoId(int lecAbonadoId) {
        LecAbonadoId = lecAbonadoId;
    }

    public int getLecMedidorId() {
        return LecMedidorId;
    }

    public void setLecMedidorId(int lecMedidorId) {
        LecMedidorId = lecMedidorId;
    }

    public String getLecCiclo() {
        return LecCiclo;
    }

    public void setLecCiclo(String lecCiclo) {
        LecCiclo = lecCiclo;
    }

    public String getLecActual() {
        return LecActual;
    }

    public void setLecActual(String lecActual) {
        LecActual = lecActual;
    }
}
