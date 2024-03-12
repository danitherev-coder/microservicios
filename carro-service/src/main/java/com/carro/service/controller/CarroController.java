package com.carro.service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carro.service.entidades.Carro;
import com.carro.service.servicio.CarroService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carro")
public class CarroController {
    private final CarroService service;

    @GetMapping
    public ResponseEntity<List<Carro>> listarCarros() {
        List<Carro> carros = service.getALl();
        if (carros.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(carros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> obtenerCarro(@PathVariable Long id) {
        Carro carro = service.findById(id);
        if (carro == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(carro);
    }

    @PostMapping
    public ResponseEntity<Carro> guardarCarro(@RequestBody Carro car) {
        System.out.println("Carro controller: " + car.getMarca());
        Carro carro = service.save(car);
        return ResponseEntity.ok(carro);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Carro>> obtenerCarrosPorUsuario(@PathVariable Long id) {
        List<Carro> carros = service.byUsuarioId(id);
        if (carros.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(carros);
    }
}
