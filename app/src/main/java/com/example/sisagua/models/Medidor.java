package com.example.sisagua.models;

public class Medidor {
    private int  id;
    private String codigo;
    private String tipo;
    private int abonadoId;
    private double lecturaActual;
    private String fechaActual;

    public Medidor(){
        this.id=this.abonadoId=0;
        this.lecturaActual=0;
        this.codigo=this.tipo=this.fechaActual="";
    }

    public Medidor(int id, String codigo, String tipo, int abonadoId, double lecturaActual, String fechaActual) {
        this.id = id;
        this.codigo = codigo;
        this.tipo = tipo;
        this.abonadoId = abonadoId;
        this.lecturaActual = lecturaActual;
        this.fechaActual = fechaActual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAbonadoId() {
        return abonadoId;
    }

    public void setAbonadoId(int abonadoId) {
        this.abonadoId = abonadoId;
    }

    public double getLecturaActual() {
        return lecturaActual;
    }

    public void setLecturaActual(double lecturaActual) {
        this.lecturaActual = lecturaActual;
    }

    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }
}
