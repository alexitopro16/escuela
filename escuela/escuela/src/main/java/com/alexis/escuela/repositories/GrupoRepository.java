package com.alexis.escuela.repositories;

import com.alexis.escuela.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    //SELECT COUNT(*) FROM GRUPOS WHERE ID_MAESTRO = ?
    boolean existsByMaestroId(Long idMaestro);
}
