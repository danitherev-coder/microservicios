package com.carro.service.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carro.service.entidades.Carro;
import com.carro.service.repositorio.CarroRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarroService {
    private final CarroRepository repository;


    public List<Carro> getALl(){
        return repository.findAll();
    }

    public Carro findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Carro save(Carro carro) {
        return repository.save(carro);
    }

    public List<Carro> byUsuarioId(Long usuarioId){
        return repository.findByUsuarioId(usuarioId);
    }
}
