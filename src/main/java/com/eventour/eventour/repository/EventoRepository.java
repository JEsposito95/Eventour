package com.eventour.eventour.repository;

import com.eventour.eventour.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Query("SELECT e FROM Evento e WHERE LOWER(e.titulo) LIKE LOWER(CONCAT('%', :categoria, '%')) OR LOWER(e.descripcion) LIKE LOWER(CONCAT('%', :categoria, '%'))")
    List<Evento> findByCategoria(@Param("categoria") String categoria);

    // Filtrar por rango de fechas
    @Query("SELECT e FROM Evento e WHERE e.fechaInicio >= :fechaInicio AND e.fechaFin <= :fechaFin")
    List<Evento> findByFechas(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);

    // Filtrar por ubicación
    @Query("SELECT e FROM Evento e WHERE e.ubicacion.id = :ubicacionId")
    List<Evento> findByUbicacion(@Param("ubicacionId") Long ubicacionId);

    // Combinar filtros (opcional)
    @Query("SELECT e FROM Evento e WHERE " +
            "(:categoria IS NULL OR LOWER(e.titulo) LIKE LOWER(CONCAT('%', :categoria, '%')) OR LOWER(e.descripcion) LIKE LOWER(CONCAT('%', :categoria, '%'))) AND " +
            "(:fechaInicio IS NULL OR e.fechaInicio >= :fechaInicio) AND " +
            "(:fechaFin IS NULL OR e.fechaFin <= :fechaFin) AND " +
            "(:ubicacionId IS NULL OR e.ubicacion.id = :ubicacionId)")
    List<Evento> findByFiltros(@Param("categoria") String categoria,
                               @Param("fechaInicio") LocalDate fechaInicio,
                               @Param("fechaFin") LocalDate fechaFin,
                               @Param("ubicacionId") Long ubicacionId);
}
