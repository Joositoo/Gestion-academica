package org.example.gestionAcademica.controller.mapper;

import org.example.gestionAcademica.controller.dto.CalificacionDto;
import org.example.gestionAcademica.modelo.Calificacion;
import org.springframework.stereotype.Service;

@Service
public class CalificacionMapper {
    private ModuloMapper moduloMapper;
    private AlumnoMapper alumnoMapper;

    public CalificacionMapper(ModuloMapper moduloMapper, AlumnoMapper alumnoMapper) {
        this.moduloMapper = moduloMapper;
        this.alumnoMapper = alumnoMapper;
    }

    public CalificacionDto getDto(Calificacion calificacion) {
        return new CalificacionDto(
                calificacion.getId(),
                moduloMapper.getDto(calificacion.getIdModulo()),
                alumnoMapper.getDto(calificacion.getIdAlumno()),
                calificacion.getRa1(),
                calificacion.getRa2(),
                calificacion.getRa3(),
                calificacion.getRa4(),
                calificacion.getRa5(),
                calificacion.getRa6(),
                calificacion.getRa7(),
                calificacion.getRa8(),
                calificacion.getRa8()
        );
    }
}
