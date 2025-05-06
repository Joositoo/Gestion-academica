package org.example.gestionAcademica.controller.dto;

public class PorcentajesRaDto {
    private int id;

    private String nombreModulo;

    private ModuloDto moduloDto;

    private Byte ra1;

    private Byte ra2;

    private Byte ra3;

    private Byte ra4;

    private Byte ra5;

    private Byte ra6;

    private Byte ra7;

    private Byte ra8;

    private Byte ra9;

    public PorcentajesRaDto(String nombreModulo, Byte ra1, Byte ra2, Byte ra3, Byte ra4, Byte ra5, Byte ra6, Byte ra7, Byte ra8, Byte ra9) {
        this.nombreModulo = nombreModulo;
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

    public PorcentajesRaDto(int id, ModuloDto moduloDto,  Byte ra1, Byte ra2, Byte ra3, Byte ra4, Byte ra5, Byte ra6, Byte ra7, Byte ra8, Byte ra9) {
        this.id = id;
        this.moduloDto = moduloDto;
        this.ra1 = ra1;
        this.ra2 = ra2;
        this.ra3 = ra3;
        this.ra4 = ra4;
        this.ra5 = ra5;
        this.ra6 = ra6;
        this.ra7 = ra7;
        this.ra8 = ra8;
        this.ra9 = ra9;

        this.nombreModulo = moduloDto.getNombre();
    }

    public PorcentajesRaDto(ModuloDto moduloDto, Byte ra1, Byte ra2, Byte ra3, Byte ra4, Byte ra5, Byte ra6, Byte ra7, Byte ra8, Byte ra9) {
        this.moduloDto = moduloDto;
        this.ra1 = ra1;
        this.ra2 = ra2;
        this.ra3 = ra3;
        this.ra4 = ra4;
        this.ra5 = ra5;
        this.ra6 = ra6;
        this.ra7 = ra7;
        this.ra8 = ra8;
        this.ra9 = ra9;

        this.nombreModulo = moduloDto.getNombre();
    }

    public PorcentajesRaDto() {}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public ModuloDto getModuloDto() {
        return moduloDto;
    }

    public void setModuloDto(ModuloDto moduloDto) {
        this.moduloDto = moduloDto;
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

    public Byte getRa8() {
        return ra8;
    }

    public void setRa8(Byte ra8) {
        this.ra8 = ra8;
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

    public Byte getRa9() {
        return ra9;
    }

    public void setRa9(Byte ra9) {
        this.ra9 = ra9;
    }
}
