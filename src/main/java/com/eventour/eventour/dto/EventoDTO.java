package com.eventour.eventour.dto;

import com.eventour.eventour.model.Evento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EventoDTO(
        Long id,
        String titulo,
        String descripcion,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        BigDecimal precio,
        String imagen,
        Evento.EstadoEvento estado,
        Long ubicacionId
) {
}
