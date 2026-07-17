package com.alexis.escuela.dto.calificacion;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CalificacionRequest(
        @NotNull(message = "El ID de la inscripción es requerido")
        @Positive(message = "El ID de la inscripción debe ser un número positivo")
        Long idInscripcion,

        @NotNull(message = "La calificación es requerida")
        @DecimalMin(value = "0.0", message = "La calificación mínima es 0.0")
        @DecimalMax(value = "10.0", message = "La calificación máxima es 10.0")
        BigDecimal calificacion
) {
}