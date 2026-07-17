package com.alexis.escuela.services.horario;

import com.alexis.escuela.dto.horario.HorarioRequest;
import com.alexis.escuela.dto.horario.HorarioResponse;
import com.alexis.escuela.entities.Grupo;
import com.alexis.escuela.entities.Horario;
import com.alexis.escuela.enums.DiaSemana;
import com.alexis.escuela.mappers.HorarioMapper;
import com.alexis.escuela.repositories.GrupoRepository;
import com.alexis.escuela.repositories.HorarioRepository;
import com.alexis.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class HorarioServiceImpl implements HorarioService {

    private final HorarioRepository horarioRepository;
    private final GrupoRepository grupoRepository;
    private final HorarioMapper horarioMapper;

    @Override
    @Transactional(readOnly = true)
    public List<HorarioResponse> listar() {
        return horarioRepository.findAll().stream()
                .map(horarioMapper::entidadAResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public HorarioResponse obtenerPorId(Long id) {
        Horario horario = ServiceUtils.obtenerEntidadOException(horarioRepository, id, Horario.class);
        return horarioMapper.entidadAResponse(horario);
    }


    @Override
    public HorarioResponse registrar(HorarioRequest request) {
        Grupo grupo = ServiceUtils.obtenerEntidadOException(grupoRepository, request.idGrupo(), Grupo.class);
        DiaSemana dia = DiaSemana.obtenerDiaSemanaPorDescripcion(request.dia());

        validarTraslapes(grupo.getAula().getId(), dia, request.horaInicio(), request.horaFin(), null);

        Horario horario = horarioMapper.requestAEntidad(request, grupo, dia);
        horarioRepository.save(horario);
        return horarioMapper.entidadAResponse(horario);
    }

    @Override
    public HorarioResponse actualizar(HorarioRequest request, Long id) {
        Horario horario = ServiceUtils.obtenerEntidadOException(horarioRepository, id, Horario.class);
        Grupo grupo = ServiceUtils.obtenerEntidadOException(grupoRepository, request.idGrupo(), Grupo.class);
        DiaSemana dia = DiaSemana.obtenerDiaSemanaPorDescripcion(request.dia());

        validarTraslapes(grupo.getAula().getId(), dia, request.horaInicio(), request.horaFin(), horario.getId());

        horario.actualizar(grupo, dia, request.horaInicio(), request.horaFin());
        return horarioMapper.entidadAResponse(horario);
    }

    @Override
    public void eliminar(Long id) {
        Horario horario = ServiceUtils.obtenerEntidadOException(horarioRepository, id, Horario.class);
        horarioRepository.delete(horario);
    }

    private void validarTraslapes(Long aulaId, DiaSemana dia, String horaInicio, String horaFin, Long horarioIdExcluido) {
        if (horaFin.compareTo(horaInicio) <= 0) {
            throw new IllegalArgumentException("La hora de fin debe ser posterior a la hora de inicio.");
        }

        List<Horario> horariosExistentes = horarioRepository.findByGrupoAulaIdAndDiaSemana(aulaId, dia);

        for (Horario existente : horariosExistentes) {
            if (horarioIdExcluido != null && existente.getId().equals(horarioIdExcluido)) {
                continue;
            }

            boolean seTraslapa = (horaInicio.compareTo(existente.getHoraFin()) < 0) && (horaFin.compareTo(existente.getHoraInicio()) > 0);

            if (seTraslapa) {
                throw new IllegalArgumentException("El horario se traslapa con otro existente en la misma aula y día.");
            }
        }
    }
}