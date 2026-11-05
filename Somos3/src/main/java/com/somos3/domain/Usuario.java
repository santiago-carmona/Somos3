package com.somos3.domain;
import jakarta.persistence.*;
@Entity
@Table(name = "USUARIO")
public class Usuario {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false, unique = true, length = 150)
    private String correo;
    @Column(name = "contrasena", nullable = false, length = 255)
    private String contrasena;
    @Column(nullable = false, length = 25)
    private String rol = "CLIENTE";
    @Column
    private Boolean estado = true;
    public Usuario() {
    }
    public Integer getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getContrasena() {
    return this.contrasena;
    }
    public void setContrasena(String contrasena) {
    this.contrasena = contrasena;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public Boolean getEstado() {
        return estado;
    }
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
