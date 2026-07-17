package com.alexis.escuela.controllers;

import com.alexis.escuela.dto.calificacion.CalificacionRequest;
import com.alexis.escuela.dto.calificacion.CalificacionResponse;
import com.alexis.escuela.services.calificacion.CalificacionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calificaciones")
public class CalificacionController extends CommonController<CalificacionRequest, CalificacionResponse, CalificacionService> {

    public CalificacionController(CalificacionService service) {
        super(service);
}}