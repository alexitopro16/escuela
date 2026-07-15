package com.alexis.escuela.dto.maestro;

import jakarta.validation.constraints.*;

public record MaestroRequest(
        @NotBlank(message="El nombre es requerido")
        @Size(min=4,max=50,message="El nombre es requerido y debe tener entre 4 y 50 caracteres")
        String nombre,

        @NotBlank(message="El apellido paterno es requerido")
        @Size(min=4,max=50,message="El apellido paterno es requerido y debe tener entre 4 y 50 caracteres")
        String apellidoPaterno,

        @NotBlank(message="El apellido materno es requerido")
        @Size(min=4,max=50,message="El apellido materno es requerido y debe tener entre 4 y 50 caracteres")
        String apellidoMaterno,

        @NotBlank(message="El email es requerido")
        @Size(min=8,max=100,message="El email es requerido y debe tener entre 8 y 100 caracteres")
        @Email(message = "El email debe tener un formato válido (ejemplo@dominio.com)")
        String email,

        @NotBlank(message="El teléfono es requerido")
        @Pattern(regexp = "^[0-9]{10}$", message="El teléfono es requerido y debe tener 10 dígitos")
        String telefono
) {
}
