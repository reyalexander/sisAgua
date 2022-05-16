package com.example.sisagua.models;

public class Abonado {
    private int idSQL;
    private int id;
    private String dni;
    private String nombres;
    private String apellidos;
    private String domicilio;

    public Abonado(){
        this.id=0;
        this.dni=this.nombres=this.apellidos=this.domicilio="";
    }

    public Abonado(int idsql,int id, String dni, String nombres, String apellidos, String domicilio) {
        this.idSQL =idsql;
        this.id = id;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.domicilio = domicilio;
    }

    public int getIdSQL() {
        return idSQL;
    }

    public void setIdSQL(int idSQL) {
        this.idSQL = idSQL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Abonado (String name, String lastname){
        this.nombres = name;
        this.apellidos = lastname;
    }

    public String toString(){
        return nombres + " " + apellidos;
    }
}
