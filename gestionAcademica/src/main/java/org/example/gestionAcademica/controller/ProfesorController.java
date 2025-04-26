package org.example.gestionAcademica.controller;

import org.example.gestionAcademica.controller.dto.ProfesorDto;
import org.example.gestionAcademica.controller.mapper.ProfesorMapper;
import org.example.gestionAcademica.modelo.Profesor;
import org.example.gestionAcademica.repository.ProfesorRepository;
import org.example.gestionAcademica.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/profesores")
public class ProfesorController {
    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private ProfesorMapper profesorMapper;
    @Autowired
    private ProfesorRepository profesorRepository;

    @GetMapping
    public ResponseEntity<List<ProfesorDto>> getAll(){
        return ResponseEntity.ok(profesorService.getProfesores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDto> getProfesorById(@PathVariable int id){
        Optional<Profesor> profesorOptional = profesorService.getProfesorById(id);

        if (profesorOptional.isPresent()) {
            ProfesorDto dto = profesorMapper.getDto(profesorOptional.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<ProfesorDto> saveProfesor(@RequestBody Profesor profesor){
        profesorService.saveProfesor(profesor);
        Profesor profesorCreado = profesorRepository.findProfesorByEmail(profesor.getEmail());
        return ResponseEntity.ok(profesorMapper.getDto(profesorCreado));
    }

    @PutMapping("/{id}")
    public Optional<Profesor> updateProfesor(@PathVariable int id, @RequestBody ProfesorDto profesorDto){
        profesorDto.setId(id);
        profesorService.updateProfesor(id, profesorDto);
        return profesorService.getProfesorById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfesor(@PathVariable int id){
        profesorService.deleteProfesorById(id);
        return ResponseEntity.ok("Profesor con id " +id+" eliminado.");
    }
}
