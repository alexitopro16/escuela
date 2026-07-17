package com.alexis.escuela.dto.alumno;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AlumnoRequest(
        @NotBlank(message="El nombre es requerido")
        @Size(min=5,max=50,message="El nombre es requerido y debe tener entre 5 y 50 caracteres")
        String nombre,

        @NotBlank(message="El apellido paterno es requerido")
        @Size(min=5,max=50,message="El apellido paterno es requerido y debe tener entre 5 y 50 caracteres")
        String apellidoPaterno,

        @NotBlank(message="El apellido materno es requerido")
        @Size(min=5,max=50,message="El apellido materno es requerido y debe tener entre 5 y 50 caracteres")
        String apellidoMaterno
) {
}
