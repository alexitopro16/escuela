package com.alexis.escuela.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "CALIFICACIONES")
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CALIFICACION")
    private Long id;

    @Column(name = "CALIFICACION", nullable = false)
    private BigDecimal calificacion;

    @Builder.Default
    @Column(name = "FECHA_REGISTRO")
    private LocalDate fechaRegistro = LocalDate.now();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INSCRIPCION", nullable = false, unique = true)
    private Inscripcion inscripcion;

    public void actualizar(BigDecimal calificacion) {
        validarDatos(calificacion);
        this.calificacion = calificacion;
        this.fechaRegistro = LocalDate.now();
    }

    private void validarDatos(BigDecimal calificacion) {
        if (calificacion == null || calificacion.compareTo(BigDecimal.ZERO) < 0 || calificacion.compareTo(BigDecimal.TEN) > 0)
            throw new IllegalArgumentException("La calificacion es requerida y debe estar entre 0 y 10");
    }
}