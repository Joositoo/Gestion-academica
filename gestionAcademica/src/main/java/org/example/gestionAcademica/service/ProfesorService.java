package org.example.gestionAcademica.service;

import org.example.gestionAcademica.controller.dto.ProfesorDto;
import org.example.gestionAcademica.controller.mapper.ProfesorMapper;
import org.example.gestionAcademica.modelo.Profesor;
import org.example.gestionAcademica.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {
    private final ProfesorRepository profesoresRepository;
    private final ProfesorMapper profesorMapper;

    @Autowired
    public ProfesorService(ProfesorRepository profesoresRepository, ProfesorMapper profesorMapper) {
        this.profesoresRepository = profesoresRepository;
        this.profesorMapper = profesorMapper;
    }

    public List<ProfesorDto> getProfesores() {
        Iterable<Profesor> profesores = profesoresRepository.findAll();
        List<ProfesorDto> profesoresDto = new ArrayList<>();

        for (Profesor profesor : profesores) {
            profesoresDto.add(profesorMapper.getDto(profesor));
        }
        return profesoresDto;
    }

    public ProfesorDto getProfesorById(int id) {
        Optional<Profesor> profesor = profesoresRepository.findById(id);
        if (profesor.isPresent()){
            return profesorMapper.getDto(profesor.get());
        }
        throw new RuntimeException("Profesor no encontrado");
    }

    public Optional<Profesor> getProfesorByIdd(int id) {
        if (profesoresRepository.existsById(id)){
            Profesor profesor = profesoresRepository.findById(id).get();
            return Optional.of(profesor);
        }
        throw new RuntimeException("Profesor no encontrado");
    }

    public void saveOrUpdateprofesor(Profesor profesor) {
        profesoresRepository.save(profesor);
    }

    public void deleteProfesorById(int id) {
        if (profesoresRepository.existsById(id)){
            profesoresRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("Profesor no encontrado");
        }
    }
}
