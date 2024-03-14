package com.usuario.service.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuario.service.modelos.Carro;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.List;

@FeignClient(value = "carro-service")
public interface CarroFeignClient {
    
    @CircuitBreaker(name = "carrosCB", fallbackMethod = "fallbackForSave")
    @PostMapping("/carro") 
    public Carro save(@RequestBody Carro carro);  

    @CircuitBreaker(name = "carrosCB", fallbackMethod = "fallbackForGetCarros")
    @GetMapping("/carro/usuario/{usuarioId}")        
    public List<Carro> getCarros(@PathVariable Long usuarioId);


    private ResponseEntity<String> fallBackGetCarros(@PathVariable Long usuarioId, RuntimeException ex){
        return new ResponseEntity<>("El usuario: " + usuarioId + " tiene los carros en el taller", HttpStatus.OK);
    }

    private ResponseEntity<String> fallBackSaveCarros(@PathVariable Long usuarioId, @RequestBody Carro carro, RuntimeException ex){
        return new ResponseEntity<>("El usuario: " + usuarioId + " no pudo guardar el carro", HttpStatus.OK);
    }
}
