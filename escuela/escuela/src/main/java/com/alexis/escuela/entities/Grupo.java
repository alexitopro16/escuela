package com.alexis.escuela.entities;

import com.alexis.escuela.utils.StringCustomUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "GRUPOS",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "GRUPO_CU_MA_AU_PE_UK",
                        columnNames = { "ID_CURSO", "ID_MAESTRO", "ID_AULA", "PERIODO" })
        }
)
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GRUPO")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CURSO", nullable = false)
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MAESTRO", nullable = false)
    private Maestro maestro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AULA", nullable = false)
    private Aula aula;

    @Column(name = "PERIODO", nullable = false, length = 20)
    private String periodo;

    @Builder.Default
    @OneToMany(mappedBy = "grupo")
    private List<Horario> horarios = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "grupo")
    private List<Inscripcion> inscripciones = new ArrayList<>();

    public void actualizar(Curso curso, Maestro maestro, Aula aula, String periodo) {
        validarDatos(curso, maestro, aula, periodo);

        this.curso = curso;
        this.maestro = maestro;
        this.aula = aula;
        this.periodo = periodo.trim();
    }

    private void validarDatos(Curso curso, Maestro maestro, Aula aula, String periodo) {
        if (curso == null)
            throw new IllegalArgumentException("El curso es requerido");

        if (maestro == null)
            throw new IllegalArgumentException("El maestro es requerido");

        if (aula == null)
            throw new IllegalArgumentException("El aula es requerida");

        StringCustomUtils.validarTamanio(periodo, 1,20, "El periodo es requerido y debe tener entre 1 y 20 caracteres");
    }
}
