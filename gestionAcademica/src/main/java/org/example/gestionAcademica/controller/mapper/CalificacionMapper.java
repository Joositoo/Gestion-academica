package org.example.gestionAcademica.controller.mapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.example.gestionAcademica.controller.dto.AlumnoDto;
import org.example.gestionAcademica.controller.dto.CalificacionDto;
import org.example.gestionAcademica.modelo.Calificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CalificacionMapper {
    private ModuloMapper moduloMapper;
    private AlumnoMapper alumnoMapper;

    @Autowired
    private Validator validator;

    public CalificacionMapper(ModuloMapper moduloMapper, AlumnoMapper alumnoMapper) {
        this.moduloMapper = moduloMapper;
        this.alumnoMapper = alumnoMapper;
    }

    public CalificacionDto getDto(Calificacion calificacion) {
        return new CalificacionDto(
                calificacion.getId(),
                moduloMapper.getDto(calificacion.getIdModulo()),
                alumnoMapper.getDto(calificacion.getIdAlumno()),
                calificacion.getRa1(),
                calificacion.getRa2(),
                calificacion.getRa3(),
                calificacion.getRa4(),
                calificacion.getRa5(),
                calificacion.getRa6(),
                calificacion.getRa7(),
                calificacion.getRa8(),
                calificacion.getRa9()
        );
    }

    public List<CalificacionDto> getCalificacionesByFile(MultipartFile file) {
        List<CalificacionDto> listaCalificaciones = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))){
            String linea;
            int numLinea = 1;
            boolean cabecera = true;
            while ((linea = br.readLine()) != null) {
                if (cabecera) {
                    cabecera = false;
                    continue;
                }

                String[] datos = linea.split(",");

                if (datos.length == 11) {
                    CalificacionDto calificacionDto = new CalificacionDto();
                    calificacionDto.setNombreModulo(datos[0].trim());
                    calificacionDto.setEmailAlumno(datos[1].trim());
                    calificacionDto.setRa1(parseBigDecimal(datos[2]));
                    calificacionDto.setRa2(parseBigDecimal(datos[3]));
                    calificacionDto.setRa3(parseBigDecimal(datos[4]));
                    calificacionDto.setRa4(parseBigDecimal(datos[5]));
                    calificacionDto.setRa5(parseBigDecimal(datos[6]));
                    calificacionDto.setRa6(parseBigDecimal(datos[7]));
                    calificacionDto.setRa7(parseBigDecimal(datos[8]));
                    calificacionDto.setRa8(parseBigDecimal(datos[9]));
                    calificacionDto.setRa9(parseBigDecimal(datos[10]));

                    Set<ConstraintViolation<CalificacionDto>> errores = validator.validate(calificacionDto);

                    if (!errores.isEmpty()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Error en línea " +numLinea+ ": ").append(numLinea).append(": ");

                        for (ConstraintViolation<CalificacionDto> error : errores) {
                            sb.append(error.getPropertyPath())
                                    .append(" - ")
                                    .append(error.getMessage())
                                    .append(". ");
                        }

                        throw new ConstraintViolationException(sb.toString(), errores);
                    }

                    listaCalificaciones.add(calificacionDto);
                }
                else{
                    throw new RuntimeException("El archivo debe tener 11 columnas (nombreModulo, emailAlumno, ra1, ra2, ra3, ra4, ra5, ra6, ra7, ra8, ra9)");
                }}
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return  listaCalificaciones;
    }

    private BigDecimal parseBigDecimal(String valor) {
        if (valor != null) {
            valor = valor.trim();
            if (!valor.equalsIgnoreCase("null") && !valor.isEmpty()) {
                return new BigDecimal(valor);
            }
        }
        return null;
    }
}
