package com.alexis.escuela.dto.inscripcion;

import com.alexis.escuela.dto.alumno.AlumnoResponse;
import com.alexis.escuela.dto.grupo.GrupoResponse;

import java.math.BigDecimal;

public record InscripcionResponse(

        Long id,
        AlumnoResponse alumno,
        GrupoResponse grupo,
        BigDecimal calificacion,
        String fechaInscripcion

) {
}
