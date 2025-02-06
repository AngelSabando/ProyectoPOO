package model;

import java.sql.Timestamp;
import java.util.Date;

public class Tramite {

    private int id;
    private int abogadoId;
    private String cliente;
    private String tipoTramite;
    private String descripcion;
    private String estado;
    private Timestamp fechaCreacion;
    private String documentos;
    private Date fechaInicio;
    private Date fechaEstimada;
    private Double precio;

    // Constructor completo
    public Tramite(int id, int abogadoId, String cliente, String tipoTramite,
            String descripcion, String estado, Timestamp fechaCreacion,
            String documentos, Date fechaInicio, Date fechaEstimada, Double precio) {
        this.id = id;
        this.abogadoId = abogadoId;
        this.cliente = cliente;
        this.tipoTramite = tipoTramite;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.documentos = documentos;
        this.fechaInicio = fechaInicio;
        this.fechaEstimada = fechaEstimada;
        this.precio = precio;
    }

    // Constructor simplificado para trámites judiciales (sin documentos ni fechas)
    public Tramite(int abogadoId, String cliente, String tipoTramite,
            String descripcion, String estado) {
        this.abogadoId = abogadoId;
        this.cliente = cliente;
        this.tipoTramite = tipoTramite;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public int getAbogadoId() {
        return abogadoId;
    }

    public String getCliente() {
        return cliente;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public String getDocumentos() {
        return documentos;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaEstimada() {
        return fechaEstimada;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAbogadoId(int abogadoId) {
        this.abogadoId = abogadoId;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setDocumentos(String documentos) {
        this.documentos = documentos;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaEstimada(Date fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
