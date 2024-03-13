package com.usuario.service.feignclient;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuario.service.modelos.Carro;
import java.util.List;

@FeignClient(name = "carro-service")
public interface CarroFeignClient {
    
    @PostMapping
    @LoadBalanced
    public Carro save(@RequestBody Carro carro);

    @GetMapping("/usuario/{usuarioId}")
    @LoadBalanced
    public List<Carro> getCarros(@PathVariable Long usuarioId);
}
