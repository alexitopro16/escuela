package com.alexis.escuela.repositories;

import com.alexis.escuela.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    boolean existsByAlumnoId(Long alumnoId);
    boolean existsByGrupoId(Long grupoId);
}
