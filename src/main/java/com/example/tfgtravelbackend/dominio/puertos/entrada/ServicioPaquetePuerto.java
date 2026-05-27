package com.example.tfgtravelbackend.dominio.puertos.entrada;
import com.example.tfgtravelbackend.dominio.modelo.Paquete;
import java.util.List;
import java.util.Optional;

public interface ServicioPaquetePuerto {
    List<Paquete> obtenerTodosLosPaquetes();

    Optional<Paquete> buscarPaquetePorId(String id);

    Paquete crearPaquete(String nombre, String destino, String descripcion,
                         Double precio, Integer duracionDias,
                         Integer plazasDisponibles, String urlImagen);

    Paquete actualizarPaquete(String id, String nombre, String destino,
                              String descripcion, Double precio,
                              Integer duracionDias, Integer plazasDisponibles,
                              String urlImagen);

    void eliminarPaquete(String id);

    List<Paquete> buscarPorDestino(String destino);

    List<Paquete> buscarPorPrecioMaximo(Double precioMaximo);
}
