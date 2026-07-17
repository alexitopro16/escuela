package com.alexis.escuela.dto.calificacion;

import com.alexis.escuela.dto.inscripcion.InscripcionResponse;

import java.math.BigDecimal;

public record CalificacionResponse(
        Long id,
        InscripcionResponse inscripcion,
        BigDecimal calificacion,
        String fechaRegistro
) {
}