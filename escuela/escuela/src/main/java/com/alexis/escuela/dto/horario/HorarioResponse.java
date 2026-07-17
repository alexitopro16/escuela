package com.alexis.escuela.dto.horario;

import com.alexis.escuela.dto.grupo.GrupoResumenResponse;

public record HorarioResponse(
        Long id,
        GrupoResumenResponse grupo,
        String horario
) {
}