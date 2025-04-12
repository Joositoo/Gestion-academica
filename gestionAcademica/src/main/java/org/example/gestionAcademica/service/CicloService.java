package org.example.gestionAcademica.service;

import org.example.gestionAcademica.controller.dto.CicloDto;
import org.example.gestionAcademica.controller.mapper.CicloMapper;
import org.example.gestionAcademica.modelo.Ciclo;
import org.example.gestionAcademica.repository.CicloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CicloService {
    private final CicloRepository cicloRepository;
    private final CicloMapper cicloMapper;

    @Autowired
    public CicloService(CicloRepository cicloRepository, CicloMapper cicloMapper) {
        this.cicloRepository = cicloRepository;
        this.cicloMapper = cicloMapper;
    }

    public Iterable<CicloDto> getCiclos() {
        Iterable<Ciclo> ciclos = cicloRepository.findAll();
        List<CicloDto> ciclosDto = new ArrayList<>();

        for (Ciclo ciclo : ciclos) {
            ciclosDto.add(cicloMapper.getDto(ciclo));
        }
        return ciclosDto;
    }

    public CicloDto getCicloById(int id) {
        Optional<Ciclo> ciclo = cicloRepository.findById(id);
        if (ciclo.isPresent()){
            return cicloMapper.getDto(ciclo.get());
        }
        throw new RuntimeException("No existe el ciclo con el id: " + id);
    }

    public void saveOrUpdateCiclo(Ciclo ciclo) {
        cicloRepository.save(ciclo);
    }

    public void deleteCicloById(int id) {
        if (cicloRepository.existsById(id)){
            cicloRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("No existe el ciclo con el id: " + id);
        }
    }
}
