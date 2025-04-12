package org.example.gestionAcademica.controller.mapper;

import org.example.gestionAcademica.controller.dto.MatriculaDto;
import org.example.gestionAcademica.modelo.Matricula;
import org.springframework.stereotype.Service;

@Service
public class MatriculaMapper {
    private CicloMapper cicloMapper;
    private AlumnoMapper alumnoMapper;

    public MatriculaMapper(CicloMapper cicloMapper, AlumnoMapper alumnoMapper) {
        this.cicloMapper = cicloMapper;
        this.alumnoMapper = alumnoMapper;
    }

    public MatriculaDto getDto(Matricula matricula) {
        return new MatriculaDto(
                matricula.getId(),
                cicloMapper.getDto(matricula.getIdCiclo()),
                alumnoMapper.getDto(matricula.getIdAlumno())
        );
    }
}
