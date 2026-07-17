package com.alexis.escuela.dto.grupo;

import com.alexis.escuela.dto.aula.AulaResponse;
import com.alexis.escuela.dto.curso.CursoResponse;
import com.alexis.escuela.dto.datos.DatosHorario;
import com.alexis.escuela.dto.maestro.MaestroResponse;

import java.util.List;

public record GrupoResponse(
    Long id,
    CursoResponse curso,
    MaestroResponse maestro,
    AulaResponse aula,
    String periodo,
    List<DatosHorario> horarios
) {
}
