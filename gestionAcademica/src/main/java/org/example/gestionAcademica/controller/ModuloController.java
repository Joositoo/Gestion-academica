package org.example.gestionAcademica.controller;

import org.example.gestionAcademica.controller.dto.ModuloDto;
import org.example.gestionAcademica.modelo.Modulo;
import org.example.gestionAcademica.service.ModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modulos")
public class ModuloController {
    @Autowired
    private ModuloService moduloService;

    @GetMapping
    public ResponseEntity<List<ModuloDto>> getAll(){
        return ResponseEntity.ok(moduloService.getModulos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuloDto> getModuloById(@PathVariable int id){
        return ResponseEntity.ok(moduloService.getModuloById(id));
    }

    @PostMapping()
    public ResponseEntity<Modulo> saveOrUpdateModulo(@RequestBody ModuloDto moduloDto){
        Modulo modulo = moduloService.getModuloByDto(moduloDto);
        moduloService.saveOrUpdateModulo(modulo);
        return ResponseEntity.ok(modulo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteModulo(@PathVariable int id){
        moduloService.deleteModuloById(id);
        return ResponseEntity.ok("Modulo con id " +id+" eliminado.");
    }
}
