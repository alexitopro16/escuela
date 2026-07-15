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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "AULAS")
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AULA")
    private Long id;

    @Column(name = "NOMBRE", length = 100, unique = true, nullable = false)
    private String nombre;

    @Column(name = "CAPACIDAD", nullable = false)
    private Integer capacidad;

    @Builder.Default
    @OneToMany(mappedBy = "aula")
    private List<Grupo> grupos = new ArrayList<>();

    public void actualizar(String nombre, Integer capacidad) {
        validarDatos(nombre, capacidad);
        this.nombre = nombre.trim();
        this.capacidad = capacidad;
    }

    private void validarDatos(String nombre, Integer capacidad) {
        StringCustomUtils.validarTamanio(nombre, 1,100, "El nombre es requerido y debe tener entre 1 y 100 caracteres");
        if (capacidad==null || capacidad<=0)
            throw new IllegalArgumentException("La capacidad es requerida y debe ser positiva");
    }
}
