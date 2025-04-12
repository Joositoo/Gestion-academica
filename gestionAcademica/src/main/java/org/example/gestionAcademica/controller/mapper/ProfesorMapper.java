package org.example.gestionAcademica.controller.mapper;

import org.example.gestionAcademica.controller.dto.ProfesorDto;
import org.example.gestionAcademica.modelo.Profesor;
import org.springframework.stereotype.Service;

@Service
public class ProfesorMapper {
    public ProfesorDto getDto(Profesor profesor) {
        return new ProfesorDto(
                profesor.getId(),
                profesor.getNombre(),
                profesor.getApellidos(),
                profesor.getEmail(),
                profesor.getPassword(),
                profesor.getRol());
    }
}
