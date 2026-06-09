package com.example.tfgtravelbackend.dominio.modelo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "paquetes")
public class Paquete {
    @Id
    private String id;
    private String nombre;
    private String destino;
    private String descripcion;
    private Double precio;
    private Integer duracionDias;
    private Integer plazasDisponibles;
    private String urlImagen;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    public Paquete(String nombre, String destino, String descripcion,
                   Double precio, Integer duracionDias, Integer plazasDisponibles,
                   String urlImagen, LocalDateTime fechaCreacion,
                   LocalDateTime fechaActualizacion) {
        this.nombre = nombre;
        this.destino = destino;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracionDias = duracionDias;
        this.plazasDisponibles = plazasDisponibles;
        this.urlImagen = urlImagen;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Paquete(String nombre, String destino, String descripcion, Double precio, Integer duracionDias, Integer plazasDisponibles, String urlImagen) {
        this.nombre = nombre;
        this.destino = destino;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracionDias = duracionDias;
        this.plazasDisponibles = plazasDisponibles;
        this.urlImagen = urlImagen;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    public void reducirPlazas(int cantidad){
        if (this.plazasDisponibles>=cantidad){
            this.plazasDisponibles-=cantidad;
            this.fechaActualizacion=LocalDateTime.now();
        }else{
            throw new RuntimeException("No hay suficientes plazas disponibles.");
        }
    }

}
