package com.alexis.escuela.controllers;

import com.alexis.escuela.dto.curso.CursoRequest;
import com.alexis.escuela.dto.curso.CursoResponse;
import com.alexis.escuela.services.cursos.CursoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cursos")
public class CursoController extends CommonController<CursoRequest, CursoResponse, CursoService>{


    public CursoController(CursoService service) {
        super(service);
    }
}
