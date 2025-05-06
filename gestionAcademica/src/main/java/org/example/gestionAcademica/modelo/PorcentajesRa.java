package org.example.gestionAcademica.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "porcentajes_ra")
public class PorcentajesRa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modulo")
    private Modulo modulo;

    @Column(name = "ra1")
    private Byte ra1;

    @Column(name = "ra2")
    private Byte ra2;

    @Column(name = "ra3")
    private Byte ra3;

    @Column(name = "ra4")
    private Byte ra4;

    @Column(name = "ra5")
    private Byte ra5;

    @Column(name = "ra6")
    private Byte ra6;

    @Column(name = "ra7")
    private Byte ra7;

    @Column(name = "ra8")
    private Byte ra8;

    @Column(name = "ra9")
    private Byte ra9;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo idModulo) {
        this.modulo = idModulo;
    }

    public Byte getRa1() {
        return ra1;
    }

    public void setRa1(Byte ra1) {
        this.ra1 = ra1;
    }

    public Byte getRa2() {
        return ra2;
    }

    public void setRa2(Byte ra2) {
        this.ra2 = ra2;
    }

    public Byte getRa3() {
        return ra3;
    }

    public void setRa3(Byte ra3) {
        this.ra3 = ra3;
    }

    public Byte getRa4() {
        return ra4;
    }

    public void setRa4(Byte ra4) {
        this.ra4 = ra4;
    }

    public Byte getRa5() {
        return ra5;
    }

    public void setRa5(Byte ra5) {
        this.ra5 = ra5;
    }

    public Byte getRa6() {
        return ra6;
    }

    public void setRa6(Byte ra6) {
        this.ra6 = ra6;
    }

    public Byte getRa7() {
        return ra7;
    }

    public void setRa7(Byte ra7) {
        this.ra7 = ra7;
    }

    public Byte getRa8() {
        return ra8;
    }

    public void setRa8(Byte ra8) {
        this.ra8 = ra8;
    }

    public Byte getRa9() {
        return ra9;
    }

    public void setRa9(Byte ra9) {
        this.ra9 = ra9;
    }

}