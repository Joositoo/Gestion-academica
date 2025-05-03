package org.example.gestionAcademica.controller.mapper;

import org.example.gestionAcademica.controller.dto.MatriculaDto;
import org.example.gestionAcademica.modelo.Matricula;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatriculaMapper {
    private CicloMapper cicloMapper;
    private AlumnoMapper alumnoMapper;

    public MatriculaMapper(CicloMapper cicloMapper, AlumnoMapper alumnoMapper) {
        this.cicloMapper = cicloMapper;
        this.alumnoMapper = alumnoMapper;
    }

    public MatriculaDto getDto(Matricula matricula) {
        return new MatriculaDto(
                matricula.getId(),
                cicloMapper.getDto(matricula.getIdCiclo()),
                alumnoMapper.getDto(matricula.getIdAlumno())
        );
    }

    public List<MatriculaDto> getMatriculasByFile(MultipartFile file) {
        List<MatriculaDto> listaMatriculas = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String linea;
            boolean cabecera = false;
            while ((linea = br.readLine()) != null) {
                if (!cabecera) {
                    cabecera = true;
                    continue;
                }

                String[] datos = linea.split(",");

                if (datos.length == 2) {
                    MatriculaDto matriculaDto = new MatriculaDto();
                    matriculaDto.setEmailAlumno(datos[0].trim());
                    matriculaDto.setNombreCiclo(datos[1].trim());

                    listaMatriculas.add(matriculaDto);
                }
                else{
                    throw new RuntimeException("El archivo debe tener 2 columnas (emailAlumno , nombreCiclo)");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return listaMatriculas;
    }
}
