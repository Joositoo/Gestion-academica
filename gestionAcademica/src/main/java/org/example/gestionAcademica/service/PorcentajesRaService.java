package org.example.gestionAcademica.service;

import org.example.gestionAcademica.controller.dto.PorcentajesRaDto;
import org.example.gestionAcademica.controller.mapper.PorcentajesRaMapper;
import org.example.gestionAcademica.modelo.Modulo;
import org.example.gestionAcademica.modelo.PorcentajesRa;
import org.example.gestionAcademica.repository.ModuloRepository;
import org.example.gestionAcademica.repository.PorcentajesRaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PorcentajesRaService {
    private final PorcentajesRaRepository porcentajesRaRepository;
    private final PorcentajesRaMapper porcentajesRaMapper;
    private final ModuloRepository moduloRepository;

    @Autowired
    public PorcentajesRaService(PorcentajesRaRepository porcentajesRaRepository, PorcentajesRaMapper porcentajesRaMapper, ModuloRepository moduloRepository) {
        this.porcentajesRaRepository = porcentajesRaRepository;
        this.porcentajesRaMapper = porcentajesRaMapper;
        this.moduloRepository = moduloRepository;
    }

    public List<PorcentajesRaDto> getPorcentajesRa() {
        Iterable<PorcentajesRa> listaPorcentajes = porcentajesRaRepository.findAll();
        List<PorcentajesRaDto> porcentajesRa = new ArrayList<>();

        for (PorcentajesRa p : listaPorcentajes) {
            porcentajesRa.add(porcentajesRaMapper.getDto(p));
        }
        return porcentajesRa;
    }

    public PorcentajesRaDto getPorcentajesRaById(int id) {
        Optional<PorcentajesRa> optPorcentajesRa = porcentajesRaRepository.findById(id);
        if (porcentajesRaRepository.existsById(id)) {
            return porcentajesRaMapper.getDto(optPorcentajesRa.get());
        }
        else{
            throw new RuntimeException("No existen los porcentajes con id " +id);
        }
    }

    public void savePorcentajeRa(PorcentajesRa porcentajesRa) {
        porcentajesRaRepository.save(porcentajesRa);
    }

    public void updatePorcentajeRa(int id, PorcentajesRaDto porcentajesRaDto) {
        Optional<PorcentajesRa> optionalPorcentajesRa = porcentajesRaRepository.findById(id);
        if (optionalPorcentajesRa.isPresent()) {
            PorcentajesRa porcentajesRa = optionalPorcentajesRa.get();

            if (porcentajesRaDto.getNombreModulo() == null || porcentajesRaDto.getNombreModulo().isEmpty()) {
                throw new RuntimeException("El nombre del módulo no  puede ser nulo o estar vacío");
            }
            if (porcentajesRaDto.getRa1() != null && porcentajesRaDto.getRa1() != 0){
                porcentajesRa.setRa1(porcentajesRaDto.getRa1());
            }
            if (porcentajesRaDto.getRa2() != null && porcentajesRaDto.getRa2() != 0){
                porcentajesRa.setRa2(porcentajesRaDto.getRa2());
            }
            if (porcentajesRaDto.getRa3() != null  && porcentajesRaDto.getRa3() != 0){
                porcentajesRa.setRa3(porcentajesRaDto.getRa3());
            }
            if (porcentajesRaDto.getRa4() != null && porcentajesRaDto.getRa4() != 0){
                porcentajesRa.setRa4(porcentajesRaDto.getRa4());
            }
            if (porcentajesRaDto.getRa5() != null && porcentajesRaDto.getRa5() != 0){
                porcentajesRa.setRa5(porcentajesRaDto.getRa5());
            }
            if (porcentajesRaDto.getRa6() != null && porcentajesRaDto.getRa6() != 0){
                porcentajesRa.setRa6(porcentajesRaDto.getRa6());
            }
            if (porcentajesRaDto.getRa7() != null  && porcentajesRaDto.getRa7() != 0){
                porcentajesRa.setRa7(porcentajesRaDto.getRa7());
            }
            if (porcentajesRaDto.getRa8() != null  && porcentajesRaDto.getRa8() != 0){
                porcentajesRa.setRa8(porcentajesRaDto.getRa8());
            }
            if (porcentajesRaDto.getRa9() != null  && porcentajesRaDto.getRa9() != 0){
                porcentajesRa.setRa9(porcentajesRaDto.getRa9());
            }
            porcentajesRaRepository.save(porcentajesRa);
        }
        else{
            throw new RuntimeException("No existen los porcentajes con id " +id);
        }
    }

    public void deletePorcentajeRa(int id) {
        if (porcentajesRaRepository.existsById(id)) {
            porcentajesRaRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("No existen los porcentajes para este id");
        }
    }

    public PorcentajesRa getPorcentajesRaByNombreModulo(PorcentajesRa porcentajesRa) {
        Modulo modulo = moduloRepository.findModuloByNombre(porcentajesRa.getModulo().getNombre());
        return porcentajesRaRepository.findPorcentajesRaByModulo(modulo);
    }

    public boolean existeModulo(PorcentajesRaDto porcentajesRaDto) {
        if (moduloRepository.existsModuloByNombre(porcentajesRaDto.getNombreModulo())) {
            return true;
        }
        else{
            throw new RuntimeException("No existe el módulo");
        }
    }

    public boolean existePorcentaje(PorcentajesRaDto porcentajesRaDto) {
        Modulo modulo = moduloRepository.findModuloByNombre(porcentajesRaDto.getNombreModulo());

        return porcentajesRaRepository.existsPorcentajesRaByModulo(modulo);
    }
}
