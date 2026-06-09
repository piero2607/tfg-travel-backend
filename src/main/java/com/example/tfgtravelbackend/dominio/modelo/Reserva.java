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
@Document(collection = "reservas")
public class Reserva {
    @Id
    private String id;
    private String paqueteId;
    private String clienteNombre;
    private String apellidos;
    private String clienteEmail;
    private String documento;
    private String telefono;
    private Integer numeroPersonas;
    private Double precioTotal;
    private String estado;
    private LocalDateTime fechaReserva;
    private LocalDateTime fechaExpiracion;

    // Constructor actualizado con todos los campos
    public Reserva(String paqueteId, String clienteNombre, String apellidos,
                   String clienteEmail, String documento, String telefono,
                   Integer numeroPersonas, Double precioTotal) {
        this.paqueteId = paqueteId;
        this.clienteNombre = clienteNombre;
        this.apellidos = apellidos;
        this.clienteEmail = clienteEmail;
        this.documento = documento;
        this.telefono = telefono;
        this.numeroPersonas = numeroPersonas;
        this.precioTotal = precioTotal;
        this.estado = "PENDIENTE";
        this.fechaReserva = LocalDateTime.now();
        this.fechaExpiracion = LocalDateTime.now().plusMinutes(30);
    }

    public void confirmar() {
        this.estado = "CONFIRMADA";
    }

    public void cancelar() {
        this.estado = "CANCELADA";
    }

    public boolean estaExpirada() {
        return LocalDateTime.now().isAfter(fechaExpiracion) && "PENDIENTE".equals(this.estado);
    }
}