package com.eventour.eventour.service;

import com.eventour.eventour.model.Evento;
import com.eventour.eventour.repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService (EventoRepository eventoRepository){
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> listarEventos(){
        return eventoRepository.findAll();
    }

    public Evento crearEvento(Evento evento){
        return eventoRepository.save(evento);
    }
}
