package org.example.gestionAcademica.controller;

import org.example.gestionAcademica.controller.dto.MatriculaDto;
import org.example.gestionAcademica.modelo.Matricula;
import org.example.gestionAcademica.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<List<MatriculaDto>> getAll(){
        return ResponseEntity.ok(matriculaService.getMatriculas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDto> getMatriculaById(@PathVariable int id){
        return ResponseEntity.ok(matriculaService.getMatriculaById(id));
    }

    @PostMapping()
    public ResponseEntity<Matricula> saveMatricula(@RequestBody MatriculaDto matriculaDto){
        Matricula matricula = matriculaService.getMatriculaByDto(matriculaDto);
        matriculaService.saveMatricula(matricula);
        return ResponseEntity.ok(matricula);
    }

    @PutMapping("/{id}")
    public MatriculaDto updateMatricula(@PathVariable int id, @RequestBody MatriculaDto matriculaDto){
        matriculaDto.setId(id);
        matriculaService.updateMatricula(id, matriculaDto);

        return matriculaService.getMatriculaById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMatricula(@PathVariable int id){
        matriculaService.deleteMatriculaById(id);
        return ResponseEntity.ok("Matricula con id " +id+" eliminada.");
    }
}
