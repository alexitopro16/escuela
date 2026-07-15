package com.alexis.escuela.mappers;

import com.alexis.escuela.dto.datos.DatosCurso;
import com.alexis.escuela.dto.maestro.MaestroRequest;
import com.alexis.escuela.dto.maestro.MaestroResponse;
import com.alexis.escuela.entities.Grupo;
import com.alexis.escuela.entities.Maestro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MaestroMapper implements CommonMapper<MaestroRequest, MaestroResponse, Maestro>{

    private final CursoMapper cursoMapper;

    @Override
    public Maestro requestAEntidad(MaestroRequest request) {
        if (request==null) return null;
        return Maestro.builder()
                .nombre(request.nombre().trim())
                .apellidoPaterno(request.apellidoPaterno().trim())
                .apellidoMaterno(request.apellidoMaterno().trim())
                .email(request.email().trim())
                .telefono(request.telefono().trim())
                .build();
    }

    @Override
    public MaestroResponse entidadAResponse(Maestro entidad) {
        if (entidad==null) return null;
        List<DatosCurso> cursos = entidadADatosCurso(entidad);
        return new MaestroResponse(
                entidad.getId(),
                String.join(" ",
                        entidad.getNombre(),
                        entidad.getApellidoPaterno(),
                        entidad.getApellidoMaterno()),
                entidad.getEmail(),
                entidad.getTelefono(),
                cursos
        );
    }

    private List<DatosCurso> entidadADatosCurso(Maestro entidad){
        if (entidad == null) return List.of();
        return entidad.getGrupos().stream()
                .map(Grupo::getCurso)
                .map(cursoMapper::entidadADatosCurso)
                .toList();
        /*
        return entidad.getGrupos().stream()
                .map(grupo -> cursoMapper.entidadADatosCurso(grupo.getCurso()))
                .toList();
         */
    }
}
