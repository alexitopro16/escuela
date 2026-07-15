package com.alexis.escuela.dto.curso;

import jakarta.validation.constraints.*;

public record CursoRequest(
        @NotBlank(message="El nombre es requerido")
        @Size(min=5,max=100,message="El nombre es requerido y debe tener entre 5 y 100 caracteres")
        String nombre,

        @Size(min=5,max=200,message="La descripción es requerida y debe tener entre 5 y 200 caracteres")
        String descripcion,

        @NotNull(message = "Los créditos son requeridos")
        @Min(value = 1, message = "Los créditos mínimos son 1")
        @Max(value = 2, message = "Los créditos máximos son 10")
        Integer creditos
) { }
