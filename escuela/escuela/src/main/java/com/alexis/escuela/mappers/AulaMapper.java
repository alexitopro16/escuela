package com.alexis.escuela.mappers;

import com.alexis.escuela.dto.aula.AulaRequest;
import com.alexis.escuela.dto.aula.AulaResponse;
import com.alexis.escuela.entities.Aula;
import org.springframework.stereotype.Component;

@Component
public class AulaMapper implements CommonMapper<AulaRequest, AulaResponse, Aula>{

    @Override
    public Aula requestAEntidad(AulaRequest request){
        if (request==null) return null;
        return Aula.builder()
                .nombre(request.nombre().trim())
                .capacidad(request.capacidad())
                .build();
    }

    @Override
    public AulaResponse entidadAResponse(Aula entidad){
        if (entidad==null) return null;
        return new AulaResponse(
                entidad.getId(),
                entidad.getNombre(),
                entidad.getCapacidad()
        );
    }
}
