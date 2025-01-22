package com.eventour.eventour.dto;

public record UbicacionDTO(
        Long id,
        String nombre,
        String direccion,
        String ciudad,
        Double latitud,
        Double longitud
) {
}
