package com.example.tfgtravelbackend.infraestructura.adaptadores.salida;
import com.example.tfgtravelbackend.dominio.modelo.Reserva;
import com.example.tfgtravelbackend.dominio.puertos.salida.RepositorioReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

interface ReservaMongoRepository extends MongoRepository<Reserva,String>{
    List<Reserva> findByClienteEmail(String email);
    List<Reserva> findByEstadoAndFechaExpiracionBefore(String estado, LocalDateTime fecha);
}

@Component
public class RepositorioReservaMongo implements RepositorioReserva {
    @Autowired
    private ReservaMongoRepository repository;

    @Override
    public List<Reserva> obtenerTodas() {
        return repository.findAll();
    }

    @Override
    public Optional<Reserva> buscarPorId(String id) {
        return repository.findById(id);
    }
    @Override
    public List<Reserva> buscarPorEmail(String email) {
        return repository.findByClienteEmail(email);
    }

    @Override
    public Reserva guardar(Reserva reserva) {
        return repository.save(reserva);
    }

    @Override
    public void eliminarPorId(String id) {
        repository.deleteById(id);
    }
    @Override
    public List<Reserva> buscarReservasExpiradas() {
        return repository.findByEstadoAndFechaExpiracionBefore("PENDIENTE", LocalDateTime.now());
    }
}
