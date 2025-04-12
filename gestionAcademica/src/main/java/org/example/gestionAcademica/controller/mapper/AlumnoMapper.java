package org.example.gestionAcademica.controller.mapper;

import org.example.gestionAcademica.controller.dto.AlumnoDto;
import org.example.gestionAcademica.modelo.Alumno;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Service
public class AlumnoMapper {
    private final ProfesorMapper profesorMapper;

    public AlumnoMapper(ProfesorMapper profesorMapper) {
        this.profesorMapper = profesorMapper;
    }

    public AlumnoDto getDto(Alumno alumno) {
        return new AlumnoDto(
                alumno.getId(),
                profesorMapper.getDto(alumno.getIdProfesor()),
                alumno.getNombre(),
                alumno.getApellidos(),
                alumno.getEmail());
    }

    public List<AlumnoDto> getAlumnoByFile(MultipartFile file){
        return new ArrayList<>();
    }
}
