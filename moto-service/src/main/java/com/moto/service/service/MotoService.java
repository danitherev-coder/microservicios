package com.moto.service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moto.service.entidades.Moto;
import com.moto.service.repositorio.MotoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MotoService {
    private final MotoRepository repository;

    public List<Moto> getALl() {
        return repository.findAll();
    }

    public Moto findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Moto save(Moto moto) {
        return repository.save(moto);
    }

    public List<Moto> byUsuarioId(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }
}
