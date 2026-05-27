package com.example.tfgtravelbackend.infraestructura.adaptadores.entrada;
import lombok.Data;

@Data
public class ReservaDTO {
    private String paqueteId;
    private String clienteNombre;
    private String clienteEmail;
    private Integer numeroPersonas;
}
