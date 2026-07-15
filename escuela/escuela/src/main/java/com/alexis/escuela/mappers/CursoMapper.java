package com.alexis.escuela.mappers;

import com.alexis.escuela.dto.curso.CursoRequest;
import com.alexis.escuela.dto.curso.CursoResponse;
import com.alexis.escuela.dto.datos.DatosCurso;
import com.alexis.escuela.entities.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper implements CommonMapper<CursoRequest, CursoResponse, Curso>{

    @Override
    public Curso requestAEntidad(CursoRequest request){
        if (request==null) return null;
        String descripcion = request.descripcion() != null
                ? request.descripcion().trim() : null;
        return Curso.builder()
                .nombre(request.nombre().trim())
                .descripcion(descripcion)
                .creditos(request.creditos())
                .build();
    }

    @Override
    public CursoResponse entidadAResponse(Curso entidad){
        if (entidad==null) return null;
        String descripcion = entidad.getDescripcion() == null
                ? "Sin descripción" : entidad.getDescripcion();
        return new CursoResponse(
                entidad.getId(),
                entidad.getNombre(),
                descripcion,
                entidad.getCreditos()
        );
    }

    public DatosCurso entidadADatosCurso(Curso entidad){
        if (entidad==null) return null;
        String descripcion = entidad.getDescripcion() == null
                ? "Sin descripción" : entidad.getDescripcion();
        return new DatosCurso(
                entidad.getNombre(),
                descripcion,
                entidad.getCreditos()
        );
    }
}
