package org.example.gestionAcademica.controller.mapper;

import org.example.gestionAcademica.controller.dto.ModuloDto;
import org.example.gestionAcademica.modelo.Modulo;
import org.springframework.stereotype.Service;

@Service
public class ModuloMapper {
    private CicloMapper cicloMapper;
    private ProfesorMapper profesorMapper;

    public ModuloMapper(CicloMapper cicloMapper, ProfesorMapper profesorMapper) {
        this.cicloMapper = cicloMapper;
        this.profesorMapper = profesorMapper;
    }
    public ModuloDto getDto(Modulo modulo) {
        return new ModuloDto(
                modulo.getId(),
                cicloMapper.getDto(modulo.getIdCiclo()),
                profesorMapper.getDto(modulo.getIdProfesor()),
                modulo.getNombre()
        );
    }
}
