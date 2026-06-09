package com.example.tfgtravelbackend.infraestructura.adaptadores.entrada;
import lombok.Data;

@Data
public class ReservaDTO {
    private String paqueteId;
    private String clienteNombre;
    private String apellidos;
    private String clienteEmail;
    private String documento;
    private String telefono;
    private Integer numeroPersonas;
}
