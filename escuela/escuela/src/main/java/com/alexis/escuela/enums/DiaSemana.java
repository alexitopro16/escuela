package com.alexis.escuela.enums;

import com.alexis.escuela.exceptions.RecursoNoEncontradoException;
import com.alexis.escuela.utils.StringCustomUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DiaSemana {

    LUNES("Lunes"),
    MARTES("Martes"),
    MIERCOLES("Miércoles"),
    JUEVES("Jueves"),
    VIERNES("Viernes"),
    SABADO("Sábado");

    private final String descripcion;

    public static DiaSemana obtenerDiaSemanaPorDescripcion(String descripcion){
        StringCustomUtils.validarNoVacio(descripcion, "La descripción es requerida");
        String descripciónNormalizada = StringCustomUtils.quitarAcentos(descripcion.trim());
        for (DiaSemana diaSemana : values()){
            if (StringCustomUtils.quitarAcentos(diaSemana.descripcion).equalsIgnoreCase(descripciónNormalizada))
                return diaSemana;
        }
        throw new RecursoNoEncontradoException("No existe un día con la descripción: " + descripcion);
    }
}