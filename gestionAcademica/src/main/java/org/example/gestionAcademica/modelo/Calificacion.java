package org.example.gestionAcademica.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = "calificacion")
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_modulo", nullable = false)
    private Modulo idModulo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_alumno", nullable = false)
    private Alumno idAlumno;

    @Column(name = "RA1", precision = 5, scale = 2)
    private BigDecimal ra1;

    @Column(name = "RA2", precision = 5, scale = 2)
    private BigDecimal ra2;

    @Column(name = "RA3", precision = 5, scale = 2)
    private BigDecimal ra3;

    @Column(name = "RA4", precision = 5, scale = 2)
    private BigDecimal ra4;

    @Column(name = "RA5", precision = 5, scale = 2)
    private BigDecimal ra5;

    @Column(name = "RA6", precision = 5, scale = 2)
    private BigDecimal ra6;

    @Column(name = "RA7", precision = 5, scale = 2)
    private BigDecimal ra7;

    @Column(name = "RA8", precision = 5, scale = 2)
    private BigDecimal ra8;

    @Column(name = "RA9", precision = 5, scale = 2)
    private BigDecimal ra9;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Modulo getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Modulo idModulo) {
        this.idModulo = idModulo;
    }

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
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

}