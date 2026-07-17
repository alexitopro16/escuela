package com.alexis.escuela.services.calificacion;

import com.alexis.escuela.dto.calificacion.CalificacionRequest;
import com.alexis.escuela.dto.calificacion.CalificacionResponse;
import com.alexis.escuela.entities.Calificacion;
import com.alexis.escuela.entities.Inscripcion;
import com.alexis.escuela.exceptions.EntidadDuplicadaException;
import com.alexis.escuela.mappers.CalificacionMapper;
import com.alexis.escuela.repositories.CalificacionRepository;
import com.alexis.escuela.repositories.InscripcionRepository;
import com.alexis.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CalificacionServiceImpl implements CalificacionService {

    private final CalificacionRepository calificacionRepository;
    private final InscripcionRepository inscripcionRepository;
    private final CalificacionMapper calificacionMapper;


    @Override
    @Transactional(readOnly = true)
    public List<CalificacionResponse> listar() {
        return calificacionRepository.findAll().stream()
                .map(calificacionMapper::entidadAResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CalificacionResponse obtenerPorId(Long id) {
        Calificacion calificacion = ServiceUtils.obtenerEntidadOException(calificacionRepository, id, Calificacion.class);
        return calificacionMapper.entidadAResponse(calificacion);
    }

    @Override
    public CalificacionResponse registrar(CalificacionRequest request) {
        Inscripcion inscripcion = ServiceUtils.obtenerEntidadOException(inscripcionRepository, request.idInscripcion(), Inscripcion.class);

        Calificacion calificacion = calificacionMapper.requestAEntidad(request, inscripcion);

        try {
            calificacionRepository.save(calificacion);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadDuplicadaException("Ya existe una calificación para esta inscripción.");
        }

        return calificacionMapper.entidadAResponse(calificacion);
    }

    @Override
    public CalificacionResponse actualizar(CalificacionRequest request, Long id) {
        Calificacion calificacion = ServiceUtils.obtenerEntidadOException(calificacionRepository, id, Calificacion.class);

        if (!calificacion.getInscripcion().getId().equals(request.idInscripcion())) {
            throw new IllegalArgumentException("No se puede cambiar la inscripción de una calificación.");
        }

        calificacion.actualizar(request.calificacion());

        return calificacionMapper.entidadAResponse(calificacion);
    }

    @Override
    public void eliminar(Long id) {
        Calificacion calificacion = ServiceUtils.obtenerEntidadOException(calificacionRepository, id, Calificacion.class);
        calificacionRepository.delete(calificacion);
    }
}