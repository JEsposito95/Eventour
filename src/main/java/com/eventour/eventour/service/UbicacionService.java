package com.eventour.eventour.service;

import com.eventour.eventour.dto.UbicacionDTO;
import com.eventour.eventour.model.Ubicacion;
import com.eventour.eventour.repository.UbicacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UbicacionService {

    private final UbicacionRepository ubicacionRepository;

    public UbicacionService (UbicacionRepository ubicacionRepository){
        this.ubicacionRepository = ubicacionRepository;
    }

    //Crear una ubicacion
    public UbicacionDTO crearUbicacion(UbicacionDTO ubicacionDTO){
        Ubicacion ubicacion = new Ubicacion(
                ubicacionDTO.nombre(),
                ubicacionDTO.direccion(),
                ubicacionDTO.ciudad(),
                ubicacionDTO.latitud(),
                ubicacionDTO.longitud()
        );
        Ubicacion saved = ubicacionRepository.save(ubicacion);
        return mapToDTO(saved);
    }

    //listar todas las ubicaciones
    public List<UbicacionDTO> listarUbicaciones(){
        return ubicacionRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    //obtener Ubicacion por ID
    public UbicacionDTO obtenerUbicacionPorId(Long id){
        Ubicacion ubicacion = ubicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ubicacion no encontrada por ID: "+ id));
        return mapToDTO(ubicacion);
    }

    public UbicacionDTO actualizarUbicacion(Long id, UbicacionDTO ubicacionDTO){
        Ubicacion ubicacion = ubicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ubicacion no encontrada por ID:" + id));

        ubicacion.setNombre(ubicacionDTO.nombre());
        ubicacion.setDireccion(ubicacionDTO.direccion());
        ubicacion.setCiudad(ubicacionDTO.ciudad());
        ubicacion.setLatitud(ubicacionDTO.latitud());
        ubicacion.setLongitud(ubicacionDTO.longitud());

        Ubicacion updated = ubicacionRepository.save(ubicacion);
        return mapToDTO(updated);

    }

    //eliminar ubicacion
    public void eliminarUbicacion(Long id) {
        if (!ubicacionRepository.existsById(id)) {
            throw new RuntimeException("Ubicación no encontrada con ID: " + id);
        }
        ubicacionRepository.deleteById(id);
    }

    //Mapear Ubicacion a UbicacionDTO
    private UbicacionDTO mapToDTO(Ubicacion ubicacion){
        return new UbicacionDTO(
                ubicacion.getId(),
                ubicacion.getNombre(),
                ubicacion.getDireccion(),
                ubicacion.getCiudad(),
                ubicacion.getLatitud(),
                ubicacion.getLongitud()
        );
    }






}
