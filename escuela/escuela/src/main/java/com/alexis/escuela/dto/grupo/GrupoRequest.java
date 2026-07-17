package com.alexis.escuela.dto.grupo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record GrupoRequest(
    @NotNull(message = "El ID del curso es requerido")
    @Positive(message = "El ID del curso debe ser un número positivo")
    Long idCurso,

    @NotNull(message = "El ID del maestro es requerido")
    @Positive(message = "El ID del maestro debe ser un número positivo")
    Long idMaestro,

    @NotNull(message = "El ID del aula es requerida")
    @Positive(message = "El ID del aula debe ser un número positivo")
    Long idAula,

    @NotBlank(message = "El periodo es requerido")
    String periodo
) {
}
