package org.example.gestionAcademica.controller;

import org.example.gestionAcademica.controller.dto.AlumnoDto;
import org.example.gestionAcademica.modelo.Alumno;
import org.example.gestionAcademica.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<Alumno> saveOrUpdateAlumno(@RequestBody AlumnoDto alumnoDto/*, @RequestBody MultipartFile file*/){
        Alumno alumno = alumnoService.getAlumnoByDto(alumnoDto);
        alumnoService.saveOrUpdateAlumno(alumno);

        return ResponseEntity.ok(alumno);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable int id, @RequestBody AlumnoDto alumnoDto){
        AlumnoDto alumnoDto = alumnoService.getAlumnoById(id);
        return ResponseEntity.ok(alumno);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlumno(@PathVariable int id){
        alumnoService.deleteAlumnoById(id);
        return ResponseEntity.ok("Alumno con id " +id+" eliminado.");
    }
}
