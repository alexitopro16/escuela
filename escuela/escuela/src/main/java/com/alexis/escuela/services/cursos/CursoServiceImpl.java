package com.alexis.escuela.services.cursos;

import com.alexis.escuela.dto.curso.CursoRequest;
import com.alexis.escuela.dto.curso.CursoResponse;
import com.alexis.escuela.entities.Curso;
// Ya no necesitamos EntidadDuplicadaException ni DataIntegrityViolationException aquí
import com.alexis.escuela.mappers.CursoMapper;
import com.alexis.escuela.repositories.CursoRepository;
import com.alexis.escuela.repositories.GrupoRepository; // Importante para la validación de borrado
import com.alexis.escuela.exceptions.EntidadRelacionadaException; // Importante para la validación de borrado
import com.alexis.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final GrupoRepository grupoRepository; // Añadido para la validación de borrado
    private final CursoMapper cursoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CursoResponse> listar() {
        return cursoRepository.findAll().stream()
                .map(cursoMapper::entidadAResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CursoResponse obtenerPorId(Long id) {
        Curso curso = ServiceUtils.obtenerEntidadOException(cursoRepository, id, Curso.class);
        return cursoMapper.entidadAResponse(curso);
    }

    @Override
    public CursoResponse registrar(CursoRequest request) {
        log.info("Registrando curso...");

        // 1. VALIDACIÓN PROACTIVA
        if (cursoRepository.existsByNombreIgnoreCase(request.nombre())) {
            throw new IllegalArgumentException("Ya existe un curso con el nombre: " + request.nombre());
        }

        // 2. Si la validación pasa, creamos y guardamos
        Curso curso = cursoMapper.requestAEntidad(request);
        cursoRepository.save(curso);
        log.info("Nuevo curso {} registrado", curso.getNombre());
        return cursoMapper.entidadAResponse(curso);
    }

    @Override
    public CursoResponse actualizar(CursoRequest request, Long id) {
        log.info("Actualizando curso con id: {}", id);

        // 1. VALIDACIÓN PROACTIVA PARA ACTUALIZAR
        if (cursoRepository.existsByNombreIgnoreCaseAndIdNot(request.nombre(), id)) {
            throw new IllegalArgumentException("Ya existe otro curso con el nombre: " + request.nombre());
        }

        // 2. Si la validación pasa, actualizamos
        Curso curso = ServiceUtils.obtenerEntidadOException(cursoRepository, id, Curso.class);
        curso.actualizar(
                request.nombre(),
                request.descripcion(),
                request.creditos()
        );
        cursoRepository.save(curso);
        log.info("Curso con id: {} actualizado", id);
        return cursoMapper.entidadAResponse(curso);
    }

    @Override
    public void eliminar(Long id) {
        log.info("Eliminando curso con id: {}", id);

        // VALIDACIÓN PROACTIVA PARA BORRADO
        if (grupoRepository.existsByCursoId(id)) {
            throw new EntidadRelacionadaException("No se puede eliminar el curso ya que tiene grupos asignados");
        }

        Curso curso = ServiceUtils.obtenerEntidadOException(cursoRepository, id, Curso.class);
        cursoRepository.delete(curso);
        log.info("Curso con id: {} eliminado", id);
    }
}