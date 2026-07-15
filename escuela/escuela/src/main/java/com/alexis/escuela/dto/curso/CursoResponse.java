package com.alexis.escuela.dto.curso;

public record CursoResponse(
        Long id,
        String nombre,
        String descripcion,
        Integer creditos
) {}
