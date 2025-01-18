package com.eventour.eventour.service;

import com.eventour.eventour.model.BlogSpot;
import com.eventour.eventour.repository.BlogSpotRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class BlogSpotService {

    @Autowired
    private BlogSpotRepository blogSpotRepository;

    public BlogSpot crearBlogSpot (String titulo, String contenido, MultipartFile imagen) {
        BlogSpot blogSpot = new BlogSpot();
        blogSpot.setTitulo(titulo);
        blogSpot.setContenido(contenido);
        blogSpot.setFechaPublicacion(LocalDate.now());

        if ((imagen != null && !imagen.isEmpty())){
            try {
                blogSpot.setImagen(imagen.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return blogSpotRepository.save(blogSpot);
    }

    // Actualizar un BlogSpot existente
    public BlogSpot actualizarBlogSpot(Long id, String titulo, String contenido, MultipartFile imagen) throws IOException {
        BlogSpot blogSpot = blogSpotRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BlogSpot no encontrado"));

        if (titulo != null) {
            blogSpot.setTitulo(titulo);
        }
        if (contenido != null) {
            blogSpot.setContenido(contenido);
        }
        if (imagen != null && !imagen.isEmpty()) {
            blogSpot.setImagen(imagen.getBytes());
        }
        return blogSpotRepository.save(blogSpot);
    }


    public void eliminarBlogSpot(Long id) {
        BlogSpot blogSpot = blogSpotRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BlogSpot no encontrado"));
        blogSpotRepository.delete(blogSpot);
    }

    // Obtener un BlogSpot por ID
    public BlogSpot obtenerBlogSpotPorId(Long id) {
        return blogSpotRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BlogSpot no encontrado"));
    }

    // Listar todos los BlogSpots
    public List<BlogSpot> listarBlogSpots() {
        return blogSpotRepository.findAll();
    }
}
