package com.eventour.eventour.service;

import com.eventour.eventour.dto.EventoDTO;
import com.eventour.eventour.model.Evento;
import com.eventour.eventour.model.Ubicacion;
import com.eventour.eventour.repository.EventoRepository;
import com.eventour.eventour.repository.UbicacionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    private final UbicacionRepository ubicacionRepository;

    public EventoService (EventoRepository eventoRepository, UbicacionRepository ubicacionRepository){
        this.eventoRepository = eventoRepository;
        this.ubicacionRepository = ubicacionRepository;

    }
    public Evento obtenerEventoPorId(Long eventoId){
        return  eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado por ID: "+ eventoId));

    }

    public List<EventoDTO> listarEventos() {
        List<Evento> eventos = eventoRepository.findAll();
        // Mapear cada Evento a EventoDTO
        return eventos.stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<Evento> filtrarPorCategoria(String categoria) {
        return eventoRepository.findByCategoria(categoria);
    }

    public List<Evento> filtrarPorFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return eventoRepository.findByFechas(fechaInicio, fechaFin);
    }

    public List<Evento> filtrarPorUbicacion(Long ubicacionId) {
        return eventoRepository.findByUbicacion(ubicacionId);
    }

    public List<Evento> filtrarEventos(String categoria, LocalDate fechaInicio, LocalDate fechaFin, Long ubicacionId) {
        return eventoRepository.findByFiltros(categoria, fechaInicio, fechaFin, ubicacionId);
    }

    public Evento crearEvento(EventoDTO eventoDTO){
        validarEvento(eventoDTO);

        //Mapear dto a entidad
        Ubicacion ubicacion = ubicacionRepository.findById(eventoDTO.ubicacionId())
                .orElseThrow(() -> new IllegalArgumentException("Ubicación no encontrada"));

        Evento evento = new Evento(
                eventoDTO.titulo(),
                eventoDTO.descripcion(),
                eventoDTO.fechaInicio(),
                eventoDTO.fechaFin(),
                eventoDTO.precio(),
                eventoDTO.imagen(),
                eventoDTO.estado(),
                ubicacion,
                eventoDTO.categoriaEvento()
        );

        return eventoRepository.save(evento);
    }

    public Evento actualizarEvento(Long id, EventoDTO eventoDTO) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));

        evento.setTitulo(eventoDTO.titulo());
        evento.setDescripcion(eventoDTO.descripcion());
        evento.setFechaInicio(eventoDTO.fechaInicio());
        evento.setFechaFin(eventoDTO.fechaFin());
        evento.setPrecio(eventoDTO.precio());
        evento.setImagen(eventoDTO.imagen());
        evento.setEstado(eventoDTO.estado());

        if (evento.getUbicacion().getId() != eventoDTO.ubicacionId()) {
            Ubicacion ubicacion = ubicacionRepository.findById(eventoDTO.ubicacionId())
                    .orElseThrow(() -> new IllegalArgumentException("Ubicación no encontrada"));
            evento.setUbicacion(ubicacion);
        }

        return eventoRepository.save(evento);
    }

    public void eliminarEvento(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new IllegalArgumentException("El evento no existe");
        }
        eventoRepository.deleteById(id);
    }

    private void validarEvento(EventoDTO eventoDTO) {
        if (eventoDTO.titulo() == null || eventoDTO.titulo().isBlank()) {
            throw new IllegalArgumentException("El título del evento es obligatorio");
        }
        if (eventoDTO.fechaInicio() == null || eventoDTO.fechaInicio().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio debe ser futura");
        }
        if (eventoDTO.fechaFin() != null && eventoDTO.fechaFin().isBefore(eventoDTO.fechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la de inicio");
        }
        if (eventoDTO.precio() == null || eventoDTO.precio().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio debe ser mayor o igual a 0");
        }
    }

    private EventoDTO mapToDTO(Evento evento) {
        return new EventoDTO(
                evento.getId(),
                evento.getTitulo(),
                evento.getDescripcion(),
                evento.getFechaInicio(),
                evento.getFechaFin(),
                evento.getPrecio(),
                evento.getImagen(),
                evento.getEstado(),
                evento.getUbicacion().getId(),
                evento.getCategoria()
        );
    }
}
