package com.example.tfgtravelbackend.infraestructura.adaptadores.salida;

import com.example.tfgtravelbackend.dominio.modelo.Paquete;
import com.example.tfgtravelbackend.dominio.puertos.salida.RepositorioPaquete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

interface PaqueteMongoRepository extends MongoRepository<Paquete, String> {
    // Búsqueda parcial (contiene) sin distinguir mayúsculas
    List<Paquete> findByDestinoContainingIgnoreCase(String destino);
    List<Paquete> findByPrecioLessThan(Double precioMaximo);
}

@Component
public class RepositorioPaqueteMongo implements RepositorioPaquete {

    @Autowired
    private PaqueteMongoRepository repository;

    @Override
    public List<Paquete> obtenerTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Paquete> buscarPorId(String id) {
        return repository.findById(id);
    }

    @Override
    public Paquete guardar(Paquete paquete) {
        return repository.save(paquete);
    }

    @Override
    public void eliminarPorId(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Paquete> buscarPorDestino(String destino) {
        if (destino == null || destino.trim().isEmpty()) {
            return repository.findAll();
        }
        return repository.findByDestinoContainingIgnoreCase(destino);
    }

    @Override
    public List<Paquete> buscarPorPrecioMenorQue(Double precioMaximo) {
        return repository.findByPrecioLessThan(precioMaximo);
    }
}