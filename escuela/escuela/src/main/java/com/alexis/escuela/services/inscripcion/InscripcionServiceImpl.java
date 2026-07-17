package com.alexis.escuela.services.inscripcion;

import com.alexis.escuela.dto.inscripcion.InscripcionRequest;
import com.alexis.escuela.dto.inscripcion.InscripcionResponse;
import com.alexis.escuela.entities.Alumno;
import com.alexis.escuela.entities.Grupo;
import com.alexis.escuela.entities.Inscripcion;
import com.alexis.escuela.exceptions.EntidadDuplicadaException;
import com.alexis.escuela.mappers.InscripcionMapper;
import com.alexis.escuela.repositories.AlumnoRepository;
import com.alexis.escuela.repositories.GrupoRepository;
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
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final AlumnoRepository alumnoRepository;
    private final GrupoRepository grupoRepository;
    private final InscripcionMapper inscripcionMapper;

    @Override
    @Transactional(readOnly = true)
    public List<InscripcionResponse> listar() {
        return inscripcionRepository.findAll().stream()
                .map(inscripcionMapper::entidadAResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public InscripcionResponse obtenerPorId(Long id) {
        Inscripcion inscripcion = ServiceUtils.obtenerEntidadOException(inscripcionRepository, id, Inscripcion.class);
        return inscripcionMapper.entidadAResponse(inscripcion);
    }

    @Override
    public InscripcionResponse registrar(InscripcionRequest request) {
        Alumno alumno = ServiceUtils.obtenerEntidadOException(alumnoRepository, request.idAlumno(), Alumno.class);
        Grupo grupo = ServiceUtils.obtenerEntidadOException(grupoRepository, request.idGrupo(), Grupo.class);

        Inscripcion inscripcion = inscripcionMapper.requestAEntidad(alumno, grupo);

        try {
            inscripcionRepository.save(inscripcion);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadDuplicadaException("El alumno ya está inscrito en este grupo.");
        }

        return inscripcionMapper.entidadAResponse(inscripcion);
    }

    @Override
    public InscripcionResponse actualizar(InscripcionRequest request, Long id) {
        Inscripcion inscripcion = ServiceUtils.obtenerEntidadOException(inscripcionRepository, id, Inscripcion.class);
        Alumno alumno = ServiceUtils.obtenerEntidadOException(alumnoRepository, request.idAlumno(), Alumno.class);
        Grupo grupo = ServiceUtils.obtenerEntidadOException(grupoRepository, request.idGrupo(), Grupo.class);

        inscripcion.actualizar(alumno, grupo);

        return inscripcionMapper.entidadAResponse(inscripcion);
    }

    @Override
    public void eliminar(Long id) {
        Inscripcion inscripcion = ServiceUtils.obtenerEntidadOException(inscripcionRepository, id, Inscripcion.class);
        inscripcionRepository.delete(inscripcion);
    }
}
