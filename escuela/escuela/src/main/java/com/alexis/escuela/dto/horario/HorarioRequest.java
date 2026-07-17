
package com.alexis.escuela.dto.horario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record HorarioRequest(
        @NotNull(message = "El ID del grupo es requerido")
        @Positive(message = "El ID del grupo debe ser un número positivo")
        Long idGrupo,

        @NotBlank(message = "El día es requerido")
        String dia,

        @NotBlank(message = "La hora de inicio es requerida")
        @Pattern(regexp = "^([01][0-9]|2[0-3]):[0-5][0-9]$", message = "El formato de la hora de inicio debe ser HH:mm")
        String horaInicio,

        @NotBlank(message = "La hora de fin es requerida")
        @Pattern(regexp = "^([01][0-9]|2[0-3]):[0-5][0-9]$", message = "El formato de la hora de fin debe ser HH:mm")
        String horaFin
) {
}