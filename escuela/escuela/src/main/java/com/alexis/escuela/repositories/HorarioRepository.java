package com.alexis.escuela.repositories;

import com.alexis.escuela.entities.Horario;
import com.alexis.escuela.enums.DiaSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
    List<Horario> findByGrupoAulaIdAndDiaSemana(Long aulaId, DiaSemana dia);
}
