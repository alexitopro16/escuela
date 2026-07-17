package com.alexis.escuela.services.maestros;

import com.alexis.escuela.dto.maestro.MaestroRequest;
import com.alexis.escuela.dto.maestro.MaestroResponse;
import com.alexis.escuela.entities.Maestro;
import com.alexis.escuela.exceptions.EntidadRelacionadaException;
import com.alexis.escuela.mappers.MaestroMapper;
import com.alexis.escuela.repositories.GrupoRepository;
import com.alexis.escuela.repositories.MaestroRepository;
import com.alexis.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class MaestroServiceImpl implements MaestroService {

    private final MaestroRepository maestroRepository;
    private final MaestroMapper maestroMapper;
    private final GrupoRepository grupoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MaestroResponse> listar() {
        log.info("Listando todos los maestros");
        return maestroRepository.findAll().stream()
                .map(maestroMapper::entidadAResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MaestroResponse obtenerPorId(Long id) {
        Maestro maestro = ServiceUtils.obtenerEntidadOException(maestroRepository, id, Maestro.class);
        return maestroMapper.entidadAResponse(maestro);
    }

    @Override
    public MaestroResponse registrar(MaestroRequest request) {
        log.info("Registrando maestro...");
        validarDatosUnicos(request);
        Maestro maestro = maestroMapper.requestAEntidad(request);
        maestroRepository.save(maestro);
        log.info("Nuevo maestro {} registrado", maestro.getNombre());
        return maestroMapper.entidadAResponse(maestro);
    }

    @Override
    public MaestroResponse actualizar(MaestroRequest request, Long id) {
        log.info("Actualizando maestro con id: {}", id);
        Maestro maestro = ServiceUtils.obtenerEntidadOException(maestroRepository, id, Maestro.class);
        validarDatosUnicosActualizar(request, id);
        maestro.actualizar(
                request.nombre(),
                request.apellidoPaterno(),
                request.apellidoMaterno(),
                request.email(),
                request.telefono()
        );
        maestroRepository.save(maestro);
        log.info("Maestro con id: {} actualizado", id);
        return maestroMapper.entidadAResponse(maestro);
    }

    @Override
    public void eliminar(Long id) {
        Maestro maestro = ServiceUtils.obtenerEntidadOException(maestroRepository, id, Maestro.class);
        log.info("Eliminando maestro con id: {}", id);

        if (grupoRepository.existsByMaestroId(id))
            throw new EntidadRelacionadaException("No se puede eliminar al maestro ya que tiene grupos asignados");

        maestroRepository.delete(maestro);
        log.info("Maestro con id: {} eliminado", id);
    }

    private void validarDatosUnicos(MaestroRequest request) {
        log.info("Validando email único...");
        if (maestroRepository.existsByEmailIgnoreCase(request.email()))
            throw new IllegalArgumentException("Ya existe un maestro registrado con el email: " + request.email());
        log.info("Validando teléfono único...");
        if (maestroRepository.existsByTelefono(request.telefono()))
            throw new IllegalArgumentException("Ya existe un maestro registrado con el teléfono: " + request.telefono());
    }

    private void validarDatosUnicosActualizar(MaestroRequest request, Long id) {
        log.info("Validando cambio en email único...");
        if (maestroRepository.existsByEmailIgnoreCaseAndIdNot(request.email(), id))
            throw new IllegalArgumentException("Ya existe un maestro registrado con el email: " + request.email());
        log.info("Validando cambio en teléfono único...");
        if (maestroRepository.existsByTelefonoAndIdNot(request.telefono(), id))
            throw new IllegalArgumentException("Ya existe un maestro registrado con el teléfono: " + request.telefono());
    }
}