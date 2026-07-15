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
@Table(name = "MAESTROS")
public class Maestro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MAESTRO")
    private Long id;

    @Column(name = "NOMBRE", length = 50, nullable = false)
    private String nombre;

    @Column(name = "APELLIDO_PATERNO", length = 50, nullable = false)
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO", length = 50, nullable = false)
    private String apellidoMaterno;

    @Column(name = "EMAIL", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "TELEFONO", length = 10, nullable = false, unique = true)
    private String telefono;

    @Builder.Default
    @OneToMany(mappedBy = "maestro")
    private List<Grupo> grupos = new ArrayList<>();

    public void actualizar(String nombre, String apellidoPaterno,
                           String apellidoMaterno, String email, String telefono) {

        validarDatos(nombre, apellidoPaterno, apellidoMaterno, email, telefono);

        this.nombre = nombre.trim();
        this.apellidoPaterno = apellidoPaterno.trim();
        this.apellidoMaterno = apellidoMaterno.trim();
        this.email = email.trim();
        this.telefono = telefono.trim();
    }

    private void validarDatos(String nombre, String apellidoPaterno,
                              String apellidoMaterno, String email, String telefono) {

        StringCustomUtils.validarTamanio(nombre.trim(), 4,50,
                "El nombre es requerido y debe tener entre 4 y 50 caracteres");
        StringCustomUtils.validarTamanio(apellidoPaterno.trim(), 4,50,
                "El nombre es requerido y debe tener entre 4 y 50 caracteres");
        StringCustomUtils.validarTamanio(apellidoMaterno.trim(), 4,50,
                "El nombre es requerido y debe tener entre 4 y 50 caracteres");
        StringCustomUtils.validarTamanio(email.trim(), 8,100,
                "El nombre es requerido y debe tener entre 8 y 100 caracteres");
        StringCustomUtils.validarTamanio(telefono.trim(), 10,10,
                "El nombre es requerido y debe tener 10 caracteres");
        StringCustomUtils.validarTelefono(telefono);
    }

}
