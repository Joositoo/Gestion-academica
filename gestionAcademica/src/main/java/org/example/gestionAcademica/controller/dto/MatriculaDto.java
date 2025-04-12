package org.example.gestionAcademica.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatriculaDto {
    private Integer id;

    @NotNull
    private CicloDto cicloDto;

    @NotNull
    private AlumnoDto alumnoDto;

    @NotNull
    private String nombreCiclo;

    @NotNull
    private String emailAlumno;

    public MatriculaDto(Integer id, CicloDto cicloDto, AlumnoDto alumnoDto) {
        this.id = id;
        this.cicloDto = cicloDto;
        this.alumnoDto = alumnoDto;
    }

    public MatriculaDto(String emailAlumno, String nombreCiclo) {
        this.emailAlumno = emailAlumno;
        this.nombreCiclo = nombreCiclo;
    }

    public MatriculaDto() {}

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

    public AlumnoDto getAlumnoDto() {
        return alumnoDto;
    }

    public void setAlumnoDto(AlumnoDto alumnoDto) {
        this.alumnoDto = alumnoDto;
    }

    public String getNombreCiclo() {
        return nombreCiclo;
    }

    public void setNombreCiclo(String nombreCiclo) {
        this.nombreCiclo = nombreCiclo;
    }

    public String getEmailAlumno() {
        return emailAlumno;
    }

    public void setEmailAlumno(String emailAlumno) {
        this.emailAlumno = emailAlumno;
    }
}
