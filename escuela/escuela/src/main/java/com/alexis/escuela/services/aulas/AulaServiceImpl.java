package com.alexis.escuela.services.aulas;

import com.alexis.escuela.dto.aula.AulaRequest;
import com.alexis.escuela.dto.aula.AulaResponse;
import com.alexis.escuela.entities.Aula;
import com.alexis.escuela.mappers.AulaMapper;
import com.alexis.escuela.repositories.AulaRepository;
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
public class AulaServiceImpl implements AulaService{

    private final AulaRepository aulaRepository;
    private final AulaMapper aulaMapper;

    @Override
    @Transactional(readOnly = true)
    public List<AulaResponse> listar(){
        return aulaRepository
                .findAll()
                .stream()
                .map(aulaMapper::entidadAResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AulaResponse obtenerPorId(Long id) {
        return aulaMapper.entidadAResponse(obtenerAulaExcepcion(id));
    }

    @Override
    public AulaResponse registrar(AulaRequest request) {
        log.info("Registrando aula...");
        Aula aula = aulaMapper.requestAEntidad(request);
        aulaRepository.save(aula);
        log.info("Nueva aula {} registrada", aula.getNombre());
        return aulaMapper.entidadAResponse(aula);
    }

    @Override
    public AulaResponse actualizar(AulaRequest request, Long id) {
        Aula aula = obtenerAulaExcepcion(id);
        log.info("Actualizando aula con id: {}", id);
        aula.actualizar(
                request.nombre(),
                request.capacidad()
        );
        aulaRepository.save(aula);
        log.info("Aula con id: {} actualizada", id);
        return aulaMapper.entidadAResponse(aula);
    }

    @Override
    public void eliminar(Long id) {
        Aula aula = obtenerAulaExcepcion(id);
        aulaRepository.delete(aula);
        log.info("Aula con id: {} eliminada", id);
    }

    private Aula obtenerAulaExcepcion(Long id){
        return ServiceUtils.obtenerEntidadOException(aulaRepository, id, Aula.class);
    }
}
