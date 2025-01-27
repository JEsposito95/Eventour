package com.eventour.eventour.repository;

import com.eventour.eventour.model.BlogSpot;
import com.eventour.eventour.model.CategoriaEvento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogSpotRepository extends JpaRepository< BlogSpot, Long > {

    List<BlogSpot> findByCategoria(CategoriaEvento categoria);

    List<BlogSpot> findByTituloContainingIgnoreCase(String titulo);
}
