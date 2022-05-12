package com.example.sisagua.models;

public class Medidor {
    private int  MedID ;
    private String MedCodigo ;
    private int MedAbonadoId ;

    public Medidor(){
        MedID = 0;
        MedCodigo = "";
        MedAbonadoId = 0;
    }

    public Medidor(int medID, String medCodigo, int medAbonadoId) {
        MedID = medID;
        MedCodigo = medCodigo;
        MedAbonadoId = medAbonadoId;
    }

    public int getMedID() {
        return MedID;
    }

    public void setMedID(int medID) {
        MedID = medID;
    }

    public String getMedCodigo() {
        return MedCodigo;
    }

    public void setMedCodigo(String medCodigo) {
        MedCodigo = medCodigo;
    }

    public int getMedAbonadoId() {
        return MedAbonadoId;
    }

    public void setMedAbonadoId(int medAbonadoId) {
        MedAbonadoId = medAbonadoId;
    }
}
