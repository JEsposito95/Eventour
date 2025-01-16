package com.eventour.eventour.controller;

import com.eventour.eventour.dto.EventoDTO;
import com.eventour.eventour.model.Evento;
import com.eventour.eventour.repository.UbicacionRepository;
import com.eventour.eventour.service.EventoService;
import com.eventour.eventour.service.UbicacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService eventoService;
    private final UbicacionService ubicacionService;


    public EventoController(EventoService eventoService, UbicacionService ubicacionService){
        this.eventoService = eventoService;
        this.ubicacionService = ubicacionService;
    }


    // Listar todos los eventos
    @GetMapping
    public ResponseEntity<List<EventoDTO>> listarEventos() {
        List<EventoDTO> eventos = eventoService.listarEventos();
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Evento> crearEvento(@RequestBody EventoDTO eventoDTO){
        Evento nuevoEvento= eventoService.crearEvento(eventoDTO);
        return ResponseEntity.ok(nuevoEvento);
    }
}
