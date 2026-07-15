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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="CURSOS")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CURSO")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100, unique = true)
    private String nombre;

    @Column(name = "DESCRIPCION", length = 200)
    private  String descripcion;

    @Column(name = "CREDITOS", nullable = false)
    private Integer creditos;

    @Builder.Default
    @OneToMany(mappedBy = "curso")
    private List<Grupo> grupos = new ArrayList<>();

    public void actualizar(String nombre, String descripcion, Integer creditos) {
        validarDatos(nombre, descripcion, creditos);
        this.nombre = nombre.trim();
        this.descripcion = descripcion.trim();
        this.creditos = creditos;
    }

    private void validarDatos(String nombre, String descripcion, Integer creditos) {
        StringCustomUtils.validarTamanio(nombre, 5,100, "El nombre es requerido y debe tener entre 5 y 100 caracteres");

        if (creditos!=null)
            StringCustomUtils.validarTamanio(descripcion, 5,200, "La descripción es requerida y debe tener entre 5 y 200 caracteres");

        if (creditos==null || creditos<=0)
            throw new IllegalArgumentException("Los creditos son requeridos y deben ser positivos");
    }
}
