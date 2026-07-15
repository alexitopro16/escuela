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
        String apellidoMaterno,

        @NotBlank(message="El email es requerido")
        @Size(min=10,max=100,message="El email es requerido y debe tener entre 10 y 100 caracteres")
        String email,

        @NotBlank(message="La matricula es requerida")
        @Size(min=10,max=10,message="La matricula es requerida y debe tener 10 caracteres")
        String matricula,

        @NotBlank(message="La fecha de ingreso es requerida")
        String fechaIngreso
) {
}
