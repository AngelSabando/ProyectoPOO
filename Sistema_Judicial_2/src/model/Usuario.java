package model;

public class Usuario {

    private int id;
    private String nombre;
    private String usuario;
    private String contraseña;
    private String rol;
    private String correo;
    private String celular;
    private String fechaCreacion;

//    public Usuario(int id, String nombre, String usuario, String contraseña, String rol) {
//        this.id = id;
//        this.nombre = nombre;
//        this.usuario = usuario;
//        this.contraseña = contraseña;
//        this.rol = rol;
//    }

    public Usuario(int id, String nombre, String usuario, String contraseña, String rol, String correo, String celular, String fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
        this.correo = correo;
        this.celular = celular;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the fechaCreacion
     */
    public String getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
