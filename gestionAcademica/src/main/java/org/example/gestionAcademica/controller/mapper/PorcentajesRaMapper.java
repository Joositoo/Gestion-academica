package org.example.gestionAcademica.controller.mapper;

import org.example.gestionAcademica.controller.dto.PorcentajesRaDto;
import org.example.gestionAcademica.modelo.PorcentajesRa;
import org.example.gestionAcademica.repository.ModuloRepository;
import org.springframework.stereotype.Service;

@Service
public class PorcentajesRaMapper {
    private ModuloMapper moduloMapper;
    private ModuloRepository moduloRepository;

    public PorcentajesRaMapper(ModuloMapper moduloMapper, ModuloRepository moduloRepository) {
        this.moduloMapper = moduloMapper;
        this.moduloRepository = moduloRepository;
    }

    public PorcentajesRaDto getDto (PorcentajesRa porcentajesRa) {
        return new PorcentajesRaDto(
                porcentajesRa.getId(),
                moduloMapper.getDto(porcentajesRa.getModulo()),
                porcentajesRa.getRa1(),
                porcentajesRa.getRa2(),
                porcentajesRa.getRa3(),
                porcentajesRa.getRa4(),
                porcentajesRa.getRa5(),
                porcentajesRa.getRa6(),
                porcentajesRa.getRa7(),
                porcentajesRa.getRa8(),
                porcentajesRa.getRa9()
        );
    }

    public PorcentajesRa getPorcentajesByDto(PorcentajesRaDto porcentajesRaDto) {
        PorcentajesRa porcentajesRa = new PorcentajesRa();
        porcentajesRa.setModulo(moduloRepository.findModuloByNombre(porcentajesRaDto.getNombreModulo()));
        porcentajesRa.setRa1(porcentajesRaDto.getRa1());
        porcentajesRa.setRa2(porcentajesRaDto.getRa2());
        porcentajesRa.setRa3(porcentajesRaDto.getRa3());
        porcentajesRa.setRa4(porcentajesRaDto.getRa4());
        porcentajesRa.setRa5(porcentajesRaDto.getRa5());
        porcentajesRa.setRa6(porcentajesRaDto.getRa6());
        porcentajesRa.setRa7(porcentajesRaDto.getRa7());
        porcentajesRa.setRa8(porcentajesRaDto.getRa8());
        porcentajesRa.setRa9(porcentajesRaDto.getRa9());

        return porcentajesRa;
    }
}
