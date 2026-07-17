package com.alexis.escuela.controllers;

import com.alexis.escuela.dto.grupo.GrupoRequest;
import com.alexis.escuela.dto.grupo.GrupoResponse;
import com.alexis.escuela.services.grupo.GrupoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/grupos")
public class GrupoController extends CommonController<GrupoRequest, GrupoResponse, GrupoService> {
    public GrupoController(GrupoService service) {
        super(service);
    }
}
