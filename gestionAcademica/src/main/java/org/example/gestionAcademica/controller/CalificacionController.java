package org.example.gestionAcademica.controller;

import jakarta.validation.Valid;
import org.example.gestionAcademica.controller.dto.CalificacionDto;
import org.example.gestionAcademica.controller.mapper.CalificacionMapper;
import org.example.gestionAcademica.modelo.Calificacion;
import org.example.gestionAcademica.service.CalificacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/calificaciones")
public class CalificacionController {
    @Autowired
    private CalificacionesService calificacionService;
    @Autowired
    private CalificacionMapper calificacionMapper;

    @GetMapping
    public ResponseEntity<List<CalificacionDto>> getAll(){
        return ResponseEntity.ok(calificacionService.getCalificaciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalificacionDto> getCalificacionById(@PathVariable int id){
        return ResponseEntity.ok(calificacionService.getCalificacionById(id));
    }

    /*@PostMapping()
    public ResponseEntity<Calificacion> saveCalificacion(@RequestBody CalificacionDto calificacionDto){
        Calificacion calificacion = calificacionService.getCalificacionByDto(calificacionDto);
        calificacionService.saveCalificacion(calificacion);
        return ResponseEntity.ok(calificacion);
    }*/

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<CalificacionDto> saveCalificacion(@RequestParam("file") MultipartFile file){
        List<CalificacionDto> listaCalificaciones = calificacionMapper.getCalificacionesByFile(file);

        if (calificacionService.validaLista(listaCalificaciones)){
            calificacionService.saveListaCalificaciones(listaCalificaciones);
            return calificacionService.getCalificacionesByLista(listaCalificaciones);
        }
        else{
            throw new RuntimeException("El archivo contiene emails de alumnos que no existen y/o de módulos que no existen y/0 calificaciones ya registrados");
        }
    }

    @PutMapping("/{id}")
    public CalificacionDto updateCalificacion(@PathVariable int id,@Valid @RequestBody CalificacionDto calificacionDto){
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
