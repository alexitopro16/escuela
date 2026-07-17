package com.alexis.escuela.mappers;

import com.alexis.escuela.dto.inscripcion.InscripcionResponse;
import com.alexis.escuela.entities.Alumno;
import com.alexis.escuela.entities.Grupo;
import com.alexis.escuela.entities.Inscripcion;
import com.alexis.escuela.utils.StringCustomUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class InscripcionMapper {

    private final AlumnoMapper alumnoMapper;
    private final GrupoMapper grupoMapper;


    public Inscripcion requestAEntidad(Alumno alumno, Grupo grupo) {
        return Inscripcion.builder()
                .alumno(alumno)
                .grupo(grupo)
                .fechaInscripcion(LocalDate.now())
                .build();
    }


    public InscripcionResponse entidadAResponse(Inscripcion entidad) {
        var alumnoResponse = alumnoMapper.entidadAResponse(entidad.getAlumno());
        var grupoResponse = grupoMapper.entidadAResponse(entidad.getGrupo());

        return new InscripcionResponse(
                entidad.getId(),
                alumnoResponse,
                grupoResponse,
                entidad.getCalificacion() != null ? entidad.getCalificacion().getCalificacion() : null,
                StringCustomUtils.localDateAString(entidad.getFechaInscripcion())
        );
    }
}