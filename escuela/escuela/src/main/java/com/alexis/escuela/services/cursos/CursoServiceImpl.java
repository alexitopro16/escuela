package com.alexis.escuela.services.cursos;

import com.alexis.escuela.dto.curso.CursoRequest;
import com.alexis.escuela.dto.curso.CursoResponse;
import com.alexis.escuela.entities.Curso;
import com.alexis.escuela.mappers.CursoMapper;
import com.alexis.escuela.repositories.CursoRepository;
import com.alexis.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor // @RequiredArgsConstructor
@Transactional
@Slf4j
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CursoResponse> listar(){
        return cursoRepository
                .findAll()
                .stream()
                .map(cursoMapper::entidadAResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CursoResponse obtenerPorId(Long id) {
        return cursoMapper.entidadAResponse(obtenerCursoExcepcion(id));
    }

    @Override
    public CursoResponse registrar(CursoRequest request) {
        log.info("Registrando curso...");
        Curso curso = cursoMapper.requestAEntidad(request);
        cursoRepository.save(curso);
        log.info("Nuevo curso {} registrado", curso.getNombre());
        return cursoMapper.entidadAResponse(curso);
    }

    @Override
    public CursoResponse actualizar(CursoRequest request, Long id) {
        Curso curso = obtenerCursoExcepcion(id);
        log.info("Actualizando curso con id: {}", id);
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
        Curso curso = obtenerCursoExcepcion(id);
        cursoRepository.delete(curso);
        log.info("Curso con id: {} eliminado", id);
    }

    private Curso obtenerCursoExcepcion(Long id){
        return ServiceUtils.obtenerEntidadOException(cursoRepository, id, Curso.class);
    }
}