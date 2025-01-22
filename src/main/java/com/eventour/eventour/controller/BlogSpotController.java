package com.eventour.eventour.controller;

import com.eventour.eventour.model.BlogSpot;
import com.eventour.eventour.repository.BlogSpotRepository;
import com.eventour.eventour.service.BlogSpotService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/blogspots")
public class BlogSpotController {

    @Autowired
    private BlogSpotService blogSpotService;

    @Autowired
    private BlogSpotRepository blogSpotRepository;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BlogSpot> crearBlogSpot(
            @RequestParam String titulo,
            @RequestParam String contenido,
            @RequestPart("imagen")MultipartFile imagen
            ) throws IOException{
        BlogSpot nuevoBlog = blogSpotService.crearBlogSpot(titulo, contenido, imagen);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoBlog);
    }

    // Endpoint para obtener una imagen asociada a un BlogSpot
    @GetMapping("/{id}/imagen")
    public ResponseEntity<byte[]> obtenerImagen(@PathVariable Long id) {
        BlogSpot blogSpot = blogSpotRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BlogSpot no encontrado"));

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Ajusta según el formato
                .body(blogSpot.getImagen());
    }
}
