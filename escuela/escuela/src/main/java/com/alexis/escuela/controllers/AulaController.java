package com.alexis.escuela.controllers;

import com.alexis.escuela.dto.aula.AulaRequest;
import com.alexis.escuela.dto.aula.AulaResponse;
import com.alexis.escuela.services.aulas.AulaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aulas")
public class AulaController extends CommonController<AulaRequest, AulaResponse, AulaService>{

    public AulaController(AulaService service) {
        super(service);
    }
}


