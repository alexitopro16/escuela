package com.alexis.escuela.controllers;

import com.alexis.escuela.dto.maestro.MaestroRequest;
import com.alexis.escuela.dto.maestro.MaestroResponse;
import com.alexis.escuela.services.maestros.MaestroService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/maestros")
public class MaestroController extends CommonController<MaestroRequest, MaestroResponse, MaestroService>{

    public MaestroController(MaestroService service) {
        super(service);
    }
}
