package org.example.gestionAcademica.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlumnoDto {
    private Integer id;

    @NotNull
    private ProfesorDto profesorDto;

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
    private String emailProfesor;

    public AlumnoDto(Integer id, ProfesorDto profesorDto, String nombre, String apellidos, String email) {
        this.id = id;
        this.profesorDto = profesorDto;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
    }

    public AlumnoDto(String nombre, String apellidos, String email, String emailProfesor) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.emailProfesor = emailProfesor;
    }

    public AlumnoDto(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProfesorDto getProfesorDto() {
        return profesorDto;
    }

    public void setProfesorDto(ProfesorDto profesorDto) {
        this.profesorDto = profesorDto;
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

    public String getEmailProfesor() {
        return emailProfesor;
    }

    public void setEmailProfesor(String emailProfesor) {
        this.emailProfesor = emailProfesor;
    }
}
