package com.alexis.escuela.mappers;

import com.alexis.escuela.dto.datos.DatosHorario;
import com.alexis.escuela.dto.grupo.GrupoRequest;
import com.alexis.escuela.dto.grupo.GrupoResponse;
import com.alexis.escuela.entities.Aula;
import com.alexis.escuela.entities.Curso;
import com.alexis.escuela.entities.Grupo;
import com.alexis.escuela.entities.Maestro;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GrupoMapper {

    private final CursoMapper cursoMapper;
    private final MaestroMapper maestroMapper;
    private final AulaMapper aulaMapper;

    public Grupo requestAEntidad(GrupoRequest request, Curso curso, Maestro maestro, Aula aula) {
        if (request == null) return null;
        return Grupo.builder()
                .curso(curso)
                .maestro(maestro)
                .aula(aula)
                .periodo(request.periodo())
                .build();
    }

    public GrupoResponse entidadAResponse(Grupo entidad) {
        if (entidad == null) return null;

        List<DatosHorario> horarios = entidad.getHorarios().stream()
                .map(horario -> new DatosHorario(
                        horario.getDiaSemana().name(),
                        horario.getHoraInicio(),
                        horario.getHoraFin()))
                .toList();

        return new GrupoResponse(
                entidad.getId(),
                cursoMapper.entidadAResponse(entidad.getCurso()),
                maestroMapper.entidadAResponse(entidad.getMaestro()),
                aulaMapper.entidadAResponse(entidad.getAula()),
                entidad.getPeriodo(),
                horarios
        );
    }
}
