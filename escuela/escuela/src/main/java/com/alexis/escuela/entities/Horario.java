package com.alexis.escuela.entities;

import com.alexis.escuela.enums.DiaSemana;
import com.alexis.escuela.utils.StringCustomUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "HORARIOS")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HORARIO")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GRUPO", nullable = false)
    private Grupo grupo;

    @Enumerated(EnumType.STRING)
    @Column(name = "DIA", nullable = false)
    private DiaSemana diaSemana;

    @Column(name = "HORA_INICIO", nullable = false, length = 5)
    private String horaInicio;

    @Column(name = "HORA_FIN", nullable = false, length = 5)
    private String horaFin;

    public void actualizar(Grupo grupo, DiaSemana diaSemana, String horaInicio, String horaFin) {
        validarDatos(grupo, diaSemana, horaInicio, horaFin);
        this.grupo = grupo;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio.trim();
        this.horaFin = horaFin.trim();
    }

    private void validarDatos(Grupo grupo, DiaSemana diaSemana, String horaInicio, String horaFin) {
        if (grupo == null)
            throw new IllegalArgumentException("El grupo es requerido");

        if (diaSemana == null)
            throw new IllegalArgumentException("El día es requerido");

        StringCustomUtils.validarTamanio(horaInicio, 5, 5, "La hora de inicio debe tener el formato HH:mm");

        StringCustomUtils.validarTamanio(horaFin, 5, 5, "La hora de fin debe tener el formato HH:mm");

        StringCustomUtils.validarHora(horaInicio, "La hora de inicio no tiene un formato válido");

        StringCustomUtils.validarHora(horaFin, "La hora de fin no tiene un formato válido");

        if (horaFin.compareTo(horaInicio) <= 0)
            throw new IllegalArgumentException(
                    "La hora de fin debe ser mayor que la hora de inicio"
            );
    }

}
