package com.example.tfgtravelbackend.infraestructura.adaptadores.entrada;

import com.example.tfgtravelbackend.dominio.modelo.Paquete;
import com.example.tfgtravelbackend.aplicacion.servicios.ServicioPaquete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paquetes")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorPaquete {

    @Autowired
    private ServicioPaquete servicioPaquete;

    @GetMapping
    public List<Paquete> obtenerTodos() {
        return servicioPaquete.obtenerTodosLosPaquetes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paquete> obtenerPorId(@PathVariable String id) {
        Optional<Paquete> paquete = servicioPaquete.buscarPaquetePorId(id);
        if (paquete.isPresent()) {
            return ResponseEntity.ok(paquete.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public List<Paquete> buscarPorDestino(@RequestParam(required = false) String destino) {
        return servicioPaquete.buscarPorDestino(destino);
    }

    @GetMapping("/filtrar")
    public List<Paquete> filtrarPorPrecio(@RequestParam(required = false) Double precioMaximo) {
        return servicioPaquete.buscarPorPrecioMaximo(precioMaximo);
    }

    @PostMapping
    public ResponseEntity<Paquete> crearPaquete(@RequestBody PaqueteDTO paqueteDTO) {
        Paquete nuevoPaquete = servicioPaquete.crearPaquete(
                paqueteDTO.getNombre(),
                paqueteDTO.getDestino(),
                paqueteDTO.getDescripcion(),
                paqueteDTO.getPrecio(),
                paqueteDTO.getDuracionDias(),
                paqueteDTO.getPlazasDisponibles(),
                paqueteDTO.getUrlImagen()
        );
        return new ResponseEntity<>(nuevoPaquete, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paquete> actualizarPaquete(@PathVariable String id, @RequestBody PaqueteDTO paqueteDTO) {
        Paquete paqueteActualizado = servicioPaquete.actualizarPaquete(
                id,
                paqueteDTO.getNombre(),
                paqueteDTO.getDestino(),
                paqueteDTO.getDescripcion(),
                paqueteDTO.getPrecio(),
                paqueteDTO.getDuracionDias(),
                paqueteDTO.getPlazasDisponibles(),
                paqueteDTO.getUrlImagen()
        );
        return ResponseEntity.ok(paqueteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaquete(@PathVariable String id) {
        servicioPaquete.eliminarPaquete(id);
        return ResponseEntity.noContent().build();
    }
}