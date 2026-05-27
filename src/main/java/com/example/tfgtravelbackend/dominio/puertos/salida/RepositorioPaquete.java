package com.example.tfgtravelbackend.dominio.puertos.salida;

import com.example.tfgtravelbackend.dominio.modelo.Paquete;

import java.util.List;
import java.util.Optional;

public interface RepositorioPaquete {
    List<Paquete> obtenerTodos();

    Optional<Paquete> buscarPorId(String id);

    Paquete guardar(Paquete paquete);

    void eliminarPorId(String id);

    List<Paquete> buscarPorDestino(String destino);

    List<Paquete> buscarPorPrecioMenorQue(Double precioMaximo);
}
