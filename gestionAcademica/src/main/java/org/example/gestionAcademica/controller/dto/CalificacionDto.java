package org.example.gestionAcademica.controller.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CalificacionDto {
    private Integer id;

    @NotNull
    private ModuloDto moduloDto;

    @NotNull
    private AlumnoDto alumnoDto;


    private BigDecimal ra1;

    private BigDecimal ra2;

    private BigDecimal ra3;

    private BigDecimal ra4;

    private BigDecimal ra5;

    private BigDecimal ra6;

    private BigDecimal ra7;

    private BigDecimal ra8;

    private BigDecimal ra9;

    @NotNull
    private String nombreModulo;

    @NotNull
    private String emailAlumno;

    public CalificacionDto(Integer id, ModuloDto moduloDto, AlumnoDto alumnoDto, BigDecimal ra1, BigDecimal ra2, BigDecimal ra3, BigDecimal ra4, BigDecimal ra5, BigDecimal ra6, BigDecimal ra7, BigDecimal ra8, BigDecimal ra9) {
        this.id = id;
        this.moduloDto = moduloDto;
        this.alumnoDto = alumnoDto;
        this.ra1 = ra1;
        this.ra2 = ra2;
        this.ra3 = ra3;
        this.ra4 = ra4;
        this.ra5 = ra5;
        this.ra6 = ra6;
        this.ra7 = ra7;
        this.ra8 = ra8;
        this.ra9 = ra9;
    }

    public CalificacionDto(String nombreModulo, String emailAlumno, BigDecimal ra9, BigDecimal ra8, BigDecimal ra7, BigDecimal ra6, BigDecimal ra5, BigDecimal ra4, BigDecimal ra3, BigDecimal ra2, BigDecimal ra1) {
        this.nombreModulo = nombreModulo;
        this.emailAlumno = emailAlumno;
        this.ra9 = ra9;
        this.ra8 = ra8;
        this.ra7 = ra7;
        this.ra6 = ra6;
        this.ra5 = ra5;
        this.ra4 = ra4;
        this.ra3 = ra3;
        this.ra2 = ra2;
        this.ra1 = ra1;
    }

    public CalificacionDto() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ModuloDto getModuloDto() {
        return moduloDto;
    }

    public void setModuloDto(ModuloDto moduloDto) {
        this.moduloDto = moduloDto;
    }

    public AlumnoDto getAlumnoDto() {
        return alumnoDto;
    }

    public void setAlumnoDto(AlumnoDto alumnoDto) {
        this.alumnoDto = alumnoDto;
    }

    public BigDecimal getRa1() {
        return ra1;
    }

    public void setRa1(BigDecimal ra1) {
        this.ra1 = ra1;
    }

    public BigDecimal getRa2() {
        return ra2;
    }

    public void setRa2(BigDecimal ra2) {
        this.ra2 = ra2;
    }

    public BigDecimal getRa3() {
        return ra3;
    }

    public void setRa3(BigDecimal ra3) {
        this.ra3 = ra3;
    }

    public BigDecimal getRa4() {
        return ra4;
    }

    public void setRa4(BigDecimal ra4) {
        this.ra4 = ra4;
    }

    public BigDecimal getRa5() {
        return ra5;
    }

    public void setRa5(BigDecimal ra5) {
        this.ra5 = ra5;
    }

    public BigDecimal getRa6() {
        return ra6;
    }

    public void setRa6(BigDecimal ra6) {
        this.ra6 = ra6;
    }

    public BigDecimal getRa7() {
        return ra7;
    }

    public void setRa7(BigDecimal ra7) {
        this.ra7 = ra7;
    }

    public BigDecimal getRa8() {
        return ra8;
    }

    public void setRa8(BigDecimal ra8) {
        this.ra8 = ra8;
    }

    public BigDecimal getRa9() {
        return ra9;
    }

    public void setRa9(BigDecimal ra9) {
        this.ra9 = ra9;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public String getEmailAlumno() {
        return emailAlumno;
    }

    public void setEmailAlumno(String emailAlumno) {
        this.emailAlumno = emailAlumno;
    }
}
