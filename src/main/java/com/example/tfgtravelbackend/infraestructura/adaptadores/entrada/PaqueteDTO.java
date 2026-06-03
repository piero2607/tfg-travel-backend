package com.example.tfgtravelbackend.infraestructura.adaptadores.entrada;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PaqueteDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El destino es obligatorio")
    private String destino;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que cero")
    private Double precio;

    @NotNull(message = "La duración es obligatoria")
    @Min(value = 1, message = "La duración debe ser al menos 1 día")
    private Integer duracionDias;

    @NotNull(message = "Las plazas disponibles son obligatorias")
    @Positive(message = "Debe haber al menos una plaza disponible")
    private Integer plazasDisponibles;

    private String urlImagen;
}