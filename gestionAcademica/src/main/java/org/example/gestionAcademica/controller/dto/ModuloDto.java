package org.example.gestionAcademica.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModuloDto {
    private Integer id;

    @NotNull
    private CicloDto cicloDto;

    @NotNull
    private ProfesorDto profesorDto;

    @Size(max = 100)
    @NotNull
    private String nombre;

    @Size(max = 100)
    @NotNull
    private String emailProfesor;

    @Size(max = 100)
    @NotNull
    private String nombreCiclo;

    public ModuloDto(Integer id, CicloDto cicloDto, ProfesorDto profesorDto, String nombre) {
        this.id = id;
        this.cicloDto = cicloDto;
        this.profesorDto = profesorDto;
        this.nombre = nombre;
    }

    public ModuloDto(String nombreCiclo, String emailProfesor, String nombre) {
        this.nombreCiclo = nombreCiclo;
        this.emailProfesor = emailProfesor;
        this.nombre = nombre;
    }

    public ModuloDto() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CicloDto getCicloDto() {
        return cicloDto;
    }

    public void setCicloDto(CicloDto cicloDto) {
        this.cicloDto = cicloDto;
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

    public String getEmailProfesor() {
        return emailProfesor;
    }

    public void setEmailProfesor(String emailProfesor) {
        this.emailProfesor = emailProfesor;
    }

    public String getNombreCiclo() {
        return nombreCiclo;
    }

    public void setNombreCiclo(String nombreCiclo) {
        this.nombreCiclo = nombreCiclo;
    }
}
