package com.example.sisagua.models;
import java.lang.Integer;

public class LecturaResponse{

    private int idSQL;
    private int id;
    private int medidorLecturaActual;
    private int medidorLecturaAnterior;
    private int numeroRecibo;
    private String nombreCompletoAbonado;
    private String fechaCreacion;
    private String fechaEmision;
    private String ciclo;
    private String codigoMedidor;
    private String codigoSuministro;
    private int consumo;
    private int importe;

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

    public int getMedidorLecturaActual() {
        return medidorLecturaActual;
    }

    public void setMedidorLecturaActual(int medidorLecturaActual) {
        this.medidorLecturaActual = medidorLecturaActual;
    }

    public int getMedidorLecturaAnterior() {
        return medidorLecturaAnterior;
    }

    public void setMedidorLecturaAnterior(int medidorLecturaAnterior) {
        this.medidorLecturaAnterior = medidorLecturaAnterior;
    }

    public int getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(int numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public String getNombreCompletoAbonado() {
        return nombreCompletoAbonado;
    }

    public void setNombreCompletoAbonado(String nombreCompletoAbonado) {
        this.nombreCompletoAbonado = nombreCompletoAbonado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getCodigoMedidor() {
        return codigoMedidor;
    }

    public void setCodigoMedidor(String codigoMedidor) {
        this.codigoMedidor = codigoMedidor;
    }

    public String getCodigoSuministro() {
        return codigoSuministro;
    }

    public void setCodigoSuministro(String codigoSuministro) {
        this.codigoSuministro = codigoSuministro;
    }

    public int getConsumo() {
        return consumo;
    }

    public void setConsumo(int consumo) {
        this.consumo = consumo;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }
}