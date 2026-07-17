package com.alexis.escuela.controllers;


import com.alexis.escuela.dto.horario.HorarioRequest;
import com.alexis.escuela.dto.horario.HorarioResponse;
import com.alexis.escuela.services.horario.HorarioService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/horarios")
public class HorarioController extends CommonController<HorarioRequest, HorarioResponse, HorarioService> {

    public HorarioController(HorarioService service) {
        super(service);
    }

}