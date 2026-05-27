package com.example.tfgtravelbackend.infraestructura.adaptadores.entrada;
import lombok.Data;

@Data
public class PaqueteDTO {
    private String nombre;
    private String destino;
    private String descripcion;
    private Double precio;
    private Integer duracionDias;
    private Integer plazasDisponibles;
    private String urlImagen;
}

