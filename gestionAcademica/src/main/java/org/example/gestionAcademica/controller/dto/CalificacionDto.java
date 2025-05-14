package org.example.gestionAcademica.controller.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class CalificacionDto {
    private Integer id;

    private ModuloDto moduloDto;

    private AlumnoDto alumnoDto;

    @Min(value = 0, message = "ra1 no puede ser menor que 0")
    @Max(value = 10, message = "ra1 no puede ser mayor que 10")
    private BigDecimal ra1;

    @Min(value = 0, message = "ra2 no puede ser menor que 0")
    @Max(value = 10, message = "ra2 no puede ser mayor que 10")
    private BigDecimal ra2;

    @Min(value = 0, message = "ra3 no puede ser menor que 0")
    @Max(value = 10, message = "ra3 no puede ser mayor que 10")
    private BigDecimal ra3;

    @Min(value = 0, message = "ra4 no puede ser menor que 0")
    @Max(value = 10, message = "ra4 no puede ser mayor que 10")
    private BigDecimal ra4;

    @Min(value = 0, message = "ra5 no puede ser menor que 0")
    @Max(value = 10, message = "ra5 no puede ser mayor que 10")
    private BigDecimal ra5;

    @Min(value = 0, message = "ra6 no puede ser menor que 0")
    @Max(value = 10, message = "ra6 no puede ser mayor que 10")
    private BigDecimal ra6;

    @Min(value = 0, message = "ra7 no puede ser menor que 0")
    @Max(value = 10, message = "ra7 no puede ser mayor que 10")
    private BigDecimal ra7;

    @Min(value = 0, message = "ra8 no puede ser menor que 0")
    @Max(value = 10, message = "ra8 no puede ser mayor que 10")
    private BigDecimal ra8;

    @Min(value = 0, message = "ra9 no puede ser menor que 0")
    @Max(value = 10, message = "ra9 no puede ser mayor que 10")
    private BigDecimal ra9;

    @Size(max = 100)
    @NotNull(message = "El nombre del módulo no puede ser nulo")
    @NotEmpty(message = "El nombre del módulo no puede estar vacío")
    @Pattern(regexp = "^[a-zA-ZñÑ ]{3,}$", message = "El nombre solo debe contener letras y espacios")    private String nombreModulo;

    @Size(max = 255)
    @NotNull(message = "El correo no puede ser nulo")
    @NotEmpty(message = "El correo no puede estar vacío")
    @Pattern(regexp = "^[\\wñÑ._%+-]{5,30}@gmail\\.com$", message = "El email debe ser una dirección de Gmail válida y contar con mínimo 5 y máximo 30 caracteres antes del dominio")
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

    public CalificacionDto(String nombreModulo, String emailAlumno, BigDecimal ra1, BigDecimal ra2, BigDecimal ra3, BigDecimal ra4, BigDecimal ra5, BigDecimal ra6, BigDecimal ra7, BigDecimal ra8, BigDecimal ra9) {
        this.nombreModulo = nombreModulo;
        this.emailAlumno = emailAlumno;
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
