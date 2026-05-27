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
    private String clienteEmail;
    private Integer numeroPersonas;
    private Double precioTotal;
    private String estado;
    private LocalDateTime fechaReserva;
    private LocalDateTime fechaExpiracion;

    public Reserva(String paqueteId, String clienteNombre, String clienteEmail,
                   Integer numeroPersonas, Double precioTotal) {
        this.paqueteId = paqueteId;
        this.clienteNombre = clienteNombre;
        this.clienteEmail = clienteEmail;
        this.numeroPersonas = numeroPersonas;
        this.precioTotal = precioTotal;
        this.estado = "PENDIENTE";
        this.fechaReserva = LocalDateTime.now();
        this.fechaExpiracion = LocalDateTime.now().plusMinutes(30);
    }

    public void confirmar(){
        this.estado="Confirmada";
    }

    public void cancelar(){
        this.estado="Cancelada";
    }

    public boolean estaExpirada(){
        return LocalDateTime.now().isAfter(fechaExpiracion) && "Pendiente".equals(this.estado);
    }

}
