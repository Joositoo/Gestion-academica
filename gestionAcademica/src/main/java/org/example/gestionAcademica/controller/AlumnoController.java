package org.example.gestionAcademica.controller;

import org.example.gestionAcademica.controller.dto.AlumnoDto;
import org.example.gestionAcademica.modelo.Alumno;
import org.example.gestionAcademica.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<List<AlumnoDto>> getAll(){
        return ResponseEntity.ok(alumnoService.getAlumnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoDto> getAlumnoById(@PathVariable int id){
        return ResponseEntity.ok(alumnoService.getAlumnoById(id));
    }

    @PostMapping//(consumes = "text/csv")
    public ResponseEntity<Alumno> saveAlumno(@RequestBody AlumnoDto alumnoDto/*, @RequestBody MultipartFile file*/){
        Alumno alumno = alumnoService.getAlumnoByDto(alumnoDto);
        alumnoService.saveAlumno(alumno);

        return ResponseEntity.ok(alumno);
    }

    @PutMapping("/{id}")
    public AlumnoDto updateAlumno(@PathVariable int id, @RequestBody AlumnoDto alumnoDto){
        alumnoService.updateAlumno(id, alumnoDto);
        return alumnoService.getAlumnoById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlumno(@PathVariable int id){
        alumnoService.deleteAlumnoById(id);
        return ResponseEntity.ok("Alumno con id " +id+" eliminado.");
    }
}
