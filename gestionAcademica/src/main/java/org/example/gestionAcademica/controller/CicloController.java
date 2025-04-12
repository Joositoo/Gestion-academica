package org.example.gestionAcademica.controller;

import org.example.gestionAcademica.controller.dto.CicloDto;
import org.example.gestionAcademica.modelo.Ciclo;
import org.example.gestionAcademica.service.CicloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ciclos")
public class CicloController {
    @Autowired
    private CicloService cicloService;

    @GetMapping
    public ResponseEntity<Iterable<CicloDto>> getAll(){
        return ResponseEntity.ok(cicloService.getCiclos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CicloDto>> getCicloById(@PathVariable int id){
        return ResponseEntity.ok(Optional.ofNullable(cicloService.getCicloById(id)));
    }

    @PostMapping()
    public ResponseEntity<Ciclo> saveOrUpdateCiclo(@RequestBody Ciclo ciclo){
        cicloService.saveOrUpdateCiclo(ciclo);
        return ResponseEntity.ok(ciclo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCiclo(@PathVariable int id){
        cicloService.deleteCicloById(id);
        return ResponseEntity.ok("Ciclo con id " +id+" eliminado.");
    }
}
