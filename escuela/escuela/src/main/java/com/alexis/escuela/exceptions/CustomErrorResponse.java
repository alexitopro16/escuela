package com.alexis.escuela.exceptions;

public record CustomErrorResponse(
        int codigo,
        String mensaje
) {
}
