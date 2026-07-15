package com.alexis.escuela.dto.alumno;

import com.alexis.escuela.dto.datos.DatosCalificacion;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record AlumnoResponse(
        Long id,
        String nombre,
        String apellidoPaterno,
        String apellidoMaterno,
        String email,
        String matricula,
        List<DatosCalificacion> calificaciones,
        BigDecimal promedio,

        LocalDate fechaIngreso
) { }
