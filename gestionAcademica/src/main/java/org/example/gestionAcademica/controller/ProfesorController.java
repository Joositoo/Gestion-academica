package org.example.gestionAcademica.controller;

import org.example.gestionAcademica.controller.dto.ProfesorDto;
import org.example.gestionAcademica.controller.mapper.ProfesorMapper;
import org.example.gestionAcademica.modelo.Profesor;
import org.example.gestionAcademica.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {
    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private ProfesorMapper profesorMapper;

    @GetMapping
    public ResponseEntity<List<ProfesorDto>> getAll(){
        return ResponseEntity.ok(profesorService.getProfesores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDto> getProfesorById(@PathVariable int id){
        return ResponseEntity.ok(profesorService.getProfesorById(id));
    }

    @PostMapping()
    public ResponseEntity<Profesor> saveOrUpdateProfesor(@RequestBody Profesor profesor){
        profesorService.saveOrUpdateprofesor(profesor);
        return ResponseEntity.ok(profesor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfesor(@PathVariable int id){
        profesorService.deleteProfesorById(id);
        return ResponseEntity.ok("Profesor con id " +id+" eliminado.");
    }
}
