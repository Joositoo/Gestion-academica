package org.example.gestionAcademica.service;

import org.example.gestionAcademica.controller.dto.CalificacionDto;
import org.example.gestionAcademica.controller.mapper.CalificacionMapper;
import org.example.gestionAcademica.modelo.Alumno;
import org.example.gestionAcademica.modelo.Calificacion;
import org.example.gestionAcademica.modelo.Matricula;
import org.example.gestionAcademica.modelo.Modulo;
import org.example.gestionAcademica.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CalificacionesService {
    private final CalificacionRepository calificacionesRepository;
    private final CalificacionMapper calificacionMapper;
    private final ModuloRepository moduloRepository;
    private final AlumnoRepository alumnoRepository;
    private final MatriculaRepository matriculaRepository;

    @Autowired
    public CalificacionesService(CalificacionRepository calificacionesRepository, CalificacionMapper calificacionMapper, ModuloRepository moduloRepository, AlumnoRepository alumnoRepository, CicloRepository cicloRepository, MatriculaRepository matriculaRepository) {
        this.calificacionesRepository = calificacionesRepository;
        this.calificacionMapper = calificacionMapper;
        this.moduloRepository = moduloRepository;
        this.alumnoRepository = alumnoRepository;
        this.matriculaRepository = matriculaRepository;
    }

    public List<CalificacionDto> getCalificaciones() {
        Iterable<Calificacion> calificaciones = calificacionesRepository.findAll();
        List<CalificacionDto> calificacionesDto = new ArrayList<>();

        for (Calificacion calificacion : calificaciones) {
            calificacionesDto.add(calificacionMapper.getDto(calificacion));
        }

        return calificacionesDto;
    }

    public CalificacionDto getCalificacionById(int id) {
        Optional<Calificacion> calificacion = calificacionesRepository.findById(id);
        if (calificacion.isPresent()) {
            return calificacionMapper.getDto(calificacion.get());
        }
        throw new RuntimeException("Calificacion no encontrada");
    }

    public void saveCalificacion(Calificacion calificaciones) {
        calificacionesRepository.save(calificaciones);
    }

    public void updateCalificacion(int id, CalificacionDto calificacionDto) {
        Calificacion calificacion = calificacionesRepository.findById(id).get();

        if (calificacionDto.getEmailAlumno() != null && calificacionDto.getNombreModulo() != null) {
            if (moduloRepository.existsModuloByNombre(calificacionDto.getNombreModulo()) && alumnoRepository.existsAlumnoByEmail(calificacionDto.getEmailAlumno())) {
                calificacion.setIdAlumno(alumnoRepository.findAlumnoByEmail(calificacionDto.getEmailAlumno()));
                calificacion.setIdModulo(moduloRepository.findModuloByNombre(calificacionDto.getNombreModulo()));
                calificacion.setRa1(calificacionDto.getRa1());
                calificacion.setRa2(calificacionDto.getRa2());
                calificacion.setRa3(calificacionDto.getRa3());
                calificacion.setRa4(calificacionDto.getRa4());
                calificacion.setRa5(calificacionDto.getRa5());
                calificacion.setRa6(calificacionDto.getRa6());
                calificacion.setRa7(calificacionDto.getRa7());
                calificacion.setRa8(calificacionDto.getRa8());
                calificacion.setRa9(calificacionDto.getRa9());
            }
            else{
                throw new RuntimeException("Modulo y/o alumno no encontrado");
            }
        }

        calificacionesRepository.save(calificacion);
    }

    public void deleteCalificacionById(int id) {
        if (calificacionesRepository.existsById(id)){
            calificacionesRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("Calificacion no encontrada");
        }
    }

    public Calificacion getCalificacionByDto(CalificacionDto calificacionDto) {
        Alumno alumno = alumnoRepository.findAlumnoByEmail(calificacionDto.getEmailAlumno());
        Modulo modulo = moduloRepository.findModuloByNombre(calificacionDto.getNombreModulo());
        if (!calificacionesRepository.existsCalificacionByIdAlumnoAndIdModulo(alumno, modulo)){
            if(moduloRepository.existsModuloByNombre(calificacionDto.getNombreModulo()) && alumnoRepository.existsAlumnoByEmail(calificacionDto.getEmailAlumno())){
                Calificacion calificacion = new Calificacion();
                calificacion.setIdAlumno(alumnoRepository.findAlumnoByEmail(calificacionDto.getEmailAlumno()));
                calificacion.setIdModulo(moduloRepository.findModuloByNombre(calificacionDto.getNombreModulo()));
                calificacion.setRa1(calificacionDto.getRa1());
                calificacion.setRa2(calificacionDto.getRa2());
                calificacion.setRa3(calificacionDto.getRa3());
                calificacion.setRa4(calificacionDto.getRa4());
                calificacion.setRa5(calificacionDto.getRa5());
                calificacion.setRa6(calificacionDto.getRa6());
                calificacion.setRa7(calificacionDto.getRa7());
                calificacion.setRa8(calificacionDto.getRa8());
                calificacion.setRa9(calificacionDto.getRa9());

                return calificacion;
            }
            else{
                throw new RuntimeException("Alumno y/o módulo no encontrado");
            }
        }
        else{
            throw new RuntimeException("La calificación ya existe en el sistema");
        }

    }
}