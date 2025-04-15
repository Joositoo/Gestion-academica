package org.example.gestionAcademica.controller.mapper;

import org.example.gestionAcademica.controller.dto.AlumnoDto;
import org.example.gestionAcademica.modelo.Alumno;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class AlumnoMapper {
    private final ProfesorMapper profesorMapper;

    public AlumnoMapper(ProfesorMapper profesorMapper) {
        this.profesorMapper = profesorMapper;
    }

    public AlumnoDto getDto(Alumno alumno) {
        return new AlumnoDto(
                alumno.getId(),
                profesorMapper.getDto(alumno.getIdProfesor()),
                alumno.getNombre(),
                alumno.getApellidos(),
                alumno.getEmail());
    }

    public List<AlumnoDto> getAlumnosByFile(MultipartFile file) {
        List<AlumnoDto> listaAlumnos = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))){
            String linea;
            boolean cabecera = true;
            while ((linea = br.readLine()) != null) {
                if (cabecera) {
                    cabecera = false;
                    continue;
                }

                String[] datos = linea.split(",");

                if (datos.length == 4) {
                    AlumnoDto alumnoDto = new AlumnoDto();
                    alumnoDto.setNombre(datos[0]);
                    alumnoDto.setApellidos(datos[1]);
                    alumnoDto.setEmail(datos[2]);
                    alumnoDto.setEmailProfesor(datos[3]);

                    listaAlumnos.add(alumnoDto);
                }
                else{
                    throw new RuntimeException("El archivo debe tener 4 columnas (nombre, apellidos, email, emailProfesor)");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return listaAlumnos;
    }
}
