package com.moto.service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.service.entidades.Moto;
import com.moto.service.service.MotoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/moto")
public class MotoController {
    private final MotoService service;

    @GetMapping
    public ResponseEntity<List<Moto>> listarMotos() {
        List<Moto> motos = service.getALl();
        if (motos.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(motos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> obtenerMoto(@PathVariable Long id) {
        Moto moto = service.findById(id);
        if (moto == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(moto);
    }

    @PostMapping
    public ResponseEntity<Moto> guardarMoto(@RequestBody Moto motocicleta) {
        Moto moto = service.save(motocicleta);
        return ResponseEntity.ok(moto);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Moto>> obtenerMotosPorUsuario(@PathVariable Long id) {
        List<Moto> motos = service.byUsuarioId(id);
        if (motos.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(motos);
    }

}
