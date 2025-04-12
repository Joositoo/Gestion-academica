package org.example.gestionAcademica.service;

import org.example.gestionAcademica.controller.dto.ProfesorDto;
import org.example.gestionAcademica.controller.mapper.ProfesorMapper;
import org.example.gestionAcademica.modelo.Profesor;
import org.example.gestionAcademica.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    ProfesorRepository profesorRepository;
    ProfesorMapper profesorMapper;

    @Autowired
    public AuthService(ProfesorRepository profesorRepository, ProfesorMapper profesorMapper) {
        this.profesorRepository = profesorRepository;
        this.profesorMapper = profesorMapper;
    }

    public Optional<Profesor> getProfesorByEmailAndPassword(ProfesorDto profesorDto) {
        return profesorRepository.findProfesorByEmailAndPassword(profesorDto.getEmail(), profesorDto.getPassword());
    }

    public ProfesorDto getProfesorDtoByEmail(String email) {
        return profesorMapper.getDto(profesorRepository.findProfesorByEmail(email));
    }
}
