package org.example.gestionAcademica.repository;

import org.example.gestionAcademica.modelo.Calificacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionRepository extends CrudRepository<Calificacion, Integer> {
}
