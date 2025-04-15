package org.example.gestionAcademica.controller.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProfesorDto {
    private Integer id;

    @Size(max = 100)
    @NotNull
    private String nombre;

    @Size(max = 150)
    @NotNull
    private String apellidos;

    @Size(max = 255)
    @NotNull
    private String email;

    @Size(max = 255)
    @NotNull
    private String password;

    @NotNull
    @Lob
    private String rol;

    public ProfesorDto(Integer id, String nombre, String apellidos, String email, String password, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public ProfesorDto(String nombre, String apellidos, String email, String password, String rol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public ProfesorDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public ProfesorDto(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
