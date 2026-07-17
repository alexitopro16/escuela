package com.alexis.escuela.mappers;

import com.alexis.escuela.dto.grupo.GrupoResumenResponse;
import com.alexis.escuela.dto.horario.HorarioRequest;
import com.alexis.escuela.dto.horario.HorarioResponse;
import com.alexis.escuela.entities.Grupo;
import com.alexis.escuela.entities.Horario;
import com.alexis.escuela.enums.DiaSemana;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HorarioMapper {

    public Horario requestAEntidad(HorarioRequest request, Grupo grupo, DiaSemana dia) {
        return Horario.builder()
                .grupo(grupo)
                .diaSemana(dia)
                .horaInicio(request.horaInicio())
                .horaFin(request.horaFin())
                .build();
    }

    public HorarioResponse entidadAResponse(Horario entidad) {
        if (entidad == null) return null;

        Grupo grupoEntidad = entidad.getGrupo();

        GrupoResumenResponse grupoResumen = new GrupoResumenResponse(
                grupoEntidad.getCurso().getNombre(),
                String.join(" ", grupoEntidad.getMaestro().getNombre(), grupoEntidad.getMaestro().getApellidoPaterno()),
                grupoEntidad.getAula().getNombre(),
                grupoEntidad.getPeriodo()
        );

        String horarioFormateado = String.format("%s %s %s",
                entidad.getDiaSemana().getDescripcion(),
                entidad.getHoraInicio(),
                entidad.getHoraFin()
        );

        return new HorarioResponse(
                entidad.getId(),
                grupoResumen,
                horarioFormateado
        );
    }
}