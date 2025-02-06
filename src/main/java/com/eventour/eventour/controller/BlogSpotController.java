package com.eventour.eventour.controller;

import com.eventour.eventour.model.BlogSpot;
import com.eventour.eventour.model.CategoriaEvento;
import com.eventour.eventour.model.Evento;
import com.eventour.eventour.repository.BlogSpotRepository;
import com.eventour.eventour.service.BlogSpotService;
import com.eventour.eventour.service.EventoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/blogspots")
public class BlogSpotController {

    @Autowired
    private BlogSpotService blogSpotService;

    @Autowired
    private BlogSpotRepository blogSpotRepository;

    @Autowired
    private EventoService eventoService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BlogSpot> crearBlogSpot(
            @RequestParam String titulo,
            @RequestParam String contenido,
            @RequestParam LocalDate fechaPublicacion,
            @RequestParam Long eventoId,
            @RequestParam String categoria,
            @RequestPart("imagen")MultipartFile imagen
            ) throws IOException{
        Evento evento = eventoService.obtenerEventoPorId(eventoId);
        CategoriaEvento categoriaEvento = CategoriaEvento.valueOf(categoria);

        BlogSpot nuevoBlog = blogSpotService.crearBlogSpot(
                titulo,
                contenido,
                fechaPublicacion,
                evento,
                categoriaEvento,
                imagen.getBytes()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoBlog);
    }

    // Actualizar BlogSpot
    @PutMapping("/{id}")
    public ResponseEntity<BlogSpot> actualizarBlogSpot(
            @PathVariable Long id,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String contenido,
            @RequestParam(required = false) MultipartFile imagen) throws IOException {

        BlogSpot blogSpot = blogSpotService.actualizarBlogSpot(id, titulo, contenido, imagen);
        return ResponseEntity.ok(blogSpot);
    }

    // Obtener un BlogSpot por ID
    @GetMapping("/{id}")
    public ResponseEntity<BlogSpot> obtenerBlogSpotPorId(@PathVariable Long id) {
        BlogSpot blogSpot = blogSpotService.obtenerBlogSpotPorId(id);
        return ResponseEntity.ok(blogSpot);
    }

    // Listar todos los BlogSpots
    @GetMapping
    public ResponseEntity<List<BlogSpot>> listarBlogSpots() {
        List<BlogSpot> blogSpots = blogSpotService.listarBlogSpots();
        return ResponseEntity.ok(blogSpots);
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

    // Buscar por categoría
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<BlogSpot>> buscarPorCategoria(@PathVariable CategoriaEvento categoria) {
        List<BlogSpot> blogSpots = blogSpotService.buscarPorCategoria(categoria);
        return ResponseEntity.ok(blogSpots);
    }

    @GetMapping("/titulo")
    public ResponseEntity<List<BlogSpot>> buscarPorTitulo(@RequestParam String titulo) {
        List<BlogSpot> blogSpots = blogSpotService.buscarPorTitulo(titulo);
        return ResponseEntity.ok(blogSpots);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBlogSpot(@PathVariable Long id) {
        blogSpotService.eliminarBlogSpot(id);
        return ResponseEntity.noContent().build();
    }


}
