package com.eventour.eventour.controller;

import com.eventour.eventour.model.Evento;
import com.eventour.eventour.service.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService){
        this.eventoService = eventoService;
    }

    @GetMapping
    public List<Evento> listarEventos(){
        return eventoService.listarEventos();
    }

    @PostMapping
    public ResponseEntity<Evento> crearEvento(@RequestBody Evento evento){
        Evento nuevoEvento= eventoService.crearEvento(evento);
        return ResponseEntity.ok(nuevoEvento);
    }
}
