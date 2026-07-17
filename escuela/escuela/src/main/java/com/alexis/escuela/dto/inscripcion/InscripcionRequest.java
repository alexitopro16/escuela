package com.alexis.escuela.dto.inscripcion;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record InscripcionRequest(

    @NotNull(message = "El ID del alumno es requerido")
    @Positive(message = "El ID del alumno debe ser un número positivo")
    Long idAlumno,


    @NotNull(message = "El ID del grupo es requerido")
    @Positive(message = "El ID del grupo debe ser un número positivo")
    Long idGrupo)

{
}