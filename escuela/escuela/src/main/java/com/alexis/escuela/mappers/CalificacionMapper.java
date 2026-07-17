package com.alexis.escuela.mappers;

import com.alexis.escuela.dto.calificacion.CalificacionRequest;
import com.alexis.escuela.dto.calificacion.CalificacionResponse;
import com.alexis.escuela.entities.Calificacion;
import com.alexis.escuela.entities.Inscripcion;
import com.alexis.escuela.utils.StringCustomUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CalificacionMapper {

    private final InscripcionMapper inscripcionMapper;


    public Calificacion requestAEntidad(CalificacionRequest request, Inscripcion inscripcion) {
        return Calificacion.builder()
                .inscripcion(inscripcion)
                .calificacion(request.calificacion())
                .build();
    }


    public CalificacionResponse entidadAResponse(Calificacion entidad) {
        if (entidad == null) return null;

        var inscripcionResponse = inscripcionMapper.entidadAResponse(entidad.getInscripcion());

        return new CalificacionResponse(
                entidad.getId(),
                inscripcionResponse,
                entidad.getCalificacion(),
                StringCustomUtils.localDateAString(entidad.getFechaRegistro())
        );
    }
}