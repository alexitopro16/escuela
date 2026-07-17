package com.alexis.escuela.services.grupo;

import com.alexis.escuela.dto.grupo.GrupoRequest;
import com.alexis.escuela.dto.grupo.GrupoResponse;
import com.alexis.escuela.entities.Aula;
import com.alexis.escuela.entities.Curso;
import com.alexis.escuela.entities.Grupo;
import com.alexis.escuela.entities.Maestro;
import com.alexis.escuela.exceptions.EntidadDuplicadaException; // Importado
import com.alexis.escuela.mappers.GrupoMapper;
import com.alexis.escuela.repositories.AulaRepository;
import com.alexis.escuela.repositories.CursoRepository;
import com.alexis.escuela.repositories.GrupoRepository;
import com.alexis.escuela.repositories.MaestroRepository;
import com.alexis.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException; // Importado
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class GrupoServiceImpl implements GrupoService {

    private final GrupoRepository grupoRepository;
    private final CursoRepository cursoRepository;
    private final MaestroRepository maestroRepository;
    private final AulaRepository aulaRepository;
    private final GrupoMapper grupoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<GrupoResponse> listar() {
        return grupoRepository.findAll().stream()
                .map(grupoMapper::entidadAResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public GrupoResponse obtenerPorId(Long id) {
        Grupo grupo = ServiceUtils.obtenerEntidadOException(grupoRepository, id, Grupo.class);
        return grupoMapper.entidadAResponse(grupo);
    }

    @Override
    public GrupoResponse registrar(GrupoRequest request) {
        Curso curso = ServiceUtils.obtenerEntidadOException(cursoRepository, request.idCurso(), Curso.class);
        Maestro maestro = ServiceUtils.obtenerEntidadOException(maestroRepository, request.idMaestro(), Maestro.class);
        Aula aula = ServiceUtils.obtenerEntidadOException(aulaRepository, request.idAula(), Aula.class);

        Grupo grupo = grupoMapper.requestAEntidad(request, curso, maestro, aula);

        try {
            grupoRepository.save(grupo);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadDuplicadaException("Ya existe un grupo con la misma combinación de curso, maestro, aula y periodo.");
        }

        return grupoMapper.entidadAResponse(grupo);
    }

    @Override
    public GrupoResponse actualizar(GrupoRequest request, Long id) {
        Grupo grupo = ServiceUtils.obtenerEntidadOException(grupoRepository, id, Grupo.class);
        Curso curso = ServiceUtils.obtenerEntidadOException(cursoRepository, request.idCurso(), Curso.class);
        Maestro maestro = ServiceUtils.obtenerEntidadOException(maestroRepository, request.idMaestro(), Maestro.class);
        Aula aula = ServiceUtils.obtenerEntidadOException(aulaRepository, request.idAula(), Aula.class);

        grupo.actualizar(curso, maestro, aula, request.periodo());

        try {
            grupoRepository.save(grupo);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadDuplicadaException("Ya existe un grupo con la misma combinación de curso, maestro, aula y periodo.");
        }

        return grupoMapper.entidadAResponse(grupo);
    }

    @Override
    public void eliminar(Long id) {
        Grupo grupo = ServiceUtils.obtenerEntidadOException(grupoRepository, id, Grupo.class);
        grupoRepository.delete(grupo);
    }
}