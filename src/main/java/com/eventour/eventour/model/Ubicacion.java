package com.eventour.eventour.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //anotacion para que el parametro pueda estar vacío al momento de crear el objeto.
    @Column(nullable = true)
    private String nombre;

    @Column(nullable = true)
    private String direccion;

    @Column(nullable = true)
    private String ciudad;

    @Column(nullable = true)
    private Double latitud;

    @Column(nullable = true)
    private Double longitud;


    //Relacion con evento (una Ubicación puede tener muchos eventos)
    @OneToMany(mappedBy = "ubicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Evento> eventos;

    public Ubicacion() {
    }

    public Ubicacion( String nombre, String direccion, String ciudad, Double latitud, Double longitud) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}
