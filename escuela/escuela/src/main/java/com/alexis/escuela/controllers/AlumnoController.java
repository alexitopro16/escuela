package com.alexis.escuela.controllers;

import com.alexis.escuela.dto.alumno.AlumnoRequest;
import com.alexis.escuela.dto.alumno.AlumnoResponse;
import com.alexis.escuela.services.Alumnos.AlumnoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController extends CommonController<AlumnoRequest, AlumnoResponse, AlumnoService> {
    public AlumnoController(AlumnoService service) {
        super(service);
    }

}
