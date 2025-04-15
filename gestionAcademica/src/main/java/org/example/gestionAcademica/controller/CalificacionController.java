package org.example.gestionAcademica.controller;

import org.example.gestionAcademica.controller.dto.CalificacionDto;
import org.example.gestionAcademica.modelo.Calificacion;
import org.example.gestionAcademica.service.CalificacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calificaciones")
public class CalificacionController {
    @Autowired
    private CalificacionesService calificacionService;

    @GetMapping
    public ResponseEntity<List<CalificacionDto>> getAll(){
        return ResponseEntity.ok(calificacionService.getCalificaciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalificacionDto> getCalificacionById(@PathVariable int id){
        return ResponseEntity.ok(calificacionService.getCalificacionById(id));
    }

    @PostMapping()
    public ResponseEntity<Calificacion> saveCalificacion(@RequestBody CalificacionDto calificacionDto){
        Calificacion calificacion = calificacionService.getCalificacionByDto(calificacionDto);
        calificacionService.saveCalificacion(calificacion);
        return ResponseEntity.ok(calificacion);
    }

    @PutMapping("/{id}")
    public CalificacionDto updateCalificacion(@PathVariable int id, @RequestBody CalificacionDto calificacionDto){
        calificacionDto.setId(id);
        calificacionService.updateCalificacion(id, calificacionDto);

        return calificacionService.getCalificacionById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCalificacion(@PathVariable int id){
        calificacionService.deleteCalificacionById(id);
        return ResponseEntity.ok("Calificacion con id " +id+" eliminado.");
    }
}
