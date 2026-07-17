package com.alexis.escuela.controllers;

import com.alexis.escuela.dto.inscripcion.InscripcionRequest;
import com.alexis.escuela.dto.inscripcion.InscripcionResponse;
import com.alexis.escuela.services.inscripcion.InscripcionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController extends CommonController<InscripcionRequest, InscripcionResponse, InscripcionService> {

    public InscripcionController(InscripcionService service) {
        super(service);
    }
}