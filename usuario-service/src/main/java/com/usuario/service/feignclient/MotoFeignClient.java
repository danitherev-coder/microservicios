package com.usuario.service.feignclient;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuario.service.modelos.Moto;
import java.util.List;

@FeignClient(name = "moto-service", url = "http://localhost:8003/moto")
public interface MotoFeignClient {

    @PostMapping
    @LoadBalanced
    public Moto save(@RequestBody Moto moto);

    @GetMapping("/usuario/{usuarioId}")
    @LoadBalanced
    public List<Moto> getMotos(@PathVariable Long usuarioId);
    
}
