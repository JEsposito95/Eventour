package com.eventour.eventour.controller;

import com.eventour.eventour.dto.UbicacionDTO;
import com.eventour.eventour.service.UbicacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubicaciones")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    public UbicacionController(UbicacionService ubicacionService){
        this.ubicacionService = ubicacionService;
    }

    //crear ubicacion
    @PostMapping
    public ResponseEntity<UbicacionDTO> crearUbicacion(@RequestBody UbicacionDTO ubicacionDTO){
        UbicacionDTO nuevaUbicacion = ubicacionService.crearUbicacion(ubicacionDTO);
        return ResponseEntity.ok(nuevaUbicacion);
    }

    //listar todas las ubicaciones
    @GetMapping
    public ResponseEntity<List<UbicacionDTO>> listarUbicaciones(@PathVariable Long id){
        return ResponseEntity.ok(ubicacionService.listarUbicaciones());
    }

    //Obterner ubicacion por ID
    @GetMapping("/{id}")
    public ResponseEntity<UbicacionDTO> obtenerUbicacionPorId(@PathVariable Long id){
        return ResponseEntity.ok(ubicacionService.obtenerUbicacionPorId(id));
    }

    //Actualizar Ubicacion
    @PutMapping("/{id}")
    public ResponseEntity<UbicacionDTO> actualizarUbicacion(
            @PathVariable Long id,
            @RequestBody UbicacionDTO ubicacionDTO
    ){
        return ResponseEntity.ok(ubicacionService.actualizarUbicacion(id,ubicacionDTO));
    }

    //Eliminar ubicacion
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUbicacion(@PathVariable Long id){
        ubicacionService.eliminarUbicacion(id);
        return ResponseEntity.noContent().build();
    }


}
