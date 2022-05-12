package com.example.sisagua.models;

public class Abonado {
    private String AboID;
    private String AboNombre;
    private String AboApellido;
    private String AboDomicilio;

    public Abonado(){
        AboID = "";
        AboApellido = "";
        AboNombre = "";
        AboDomicilio ="";
    }
    public Abonado(String aboID, String aboNombre, String aboApellido, String aboDomicilio) {
        AboID = aboID;
        AboNombre = aboNombre;
        AboApellido = aboApellido;
        AboDomicilio = aboDomicilio;
    }

    public String getAboID() {
        return AboID;
    }

    public void setAboID(String aboID) {
        AboID = aboID;
    }

    public String getAboNombre() {
        return AboNombre;
    }

    public void setAboNombre(String aboNombre) {
        AboNombre = aboNombre;
    }

    public String getAboApellido() {
        return AboApellido;
    }

    public void setAboApellido(String aboApellido) {
        AboApellido = aboApellido;
    }

    public String getAboDomicilio() {
        return AboDomicilio;
    }

    public void setAboDomicilio(String aboDomicilio) {
        AboDomicilio = aboDomicilio;
    }
}
