package com.eventour.eventour.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class BlogSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Lob
    private String contenido;

    private LocalDate fechaPublicacion;

    @ManyToOne
    private Evento evento;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] imagen; // Binario grande (almacenado como BLOB)

    public BlogSpot() {
    }

    public BlogSpot(Long id, String titulo, String contenido, LocalDate fechaPublicacion, Evento evento, byte[] imagen) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaPublicacion = fechaPublicacion;
        this.evento = evento;
        this.imagen = imagen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
