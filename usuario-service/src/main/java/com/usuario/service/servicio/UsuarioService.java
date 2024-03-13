package com.usuario.service.servicio;


import com.usuario.service.entidades.Usuario;
import com.usuario.service.feignclient.CarroFeignClient;
import com.usuario.service.feignclient.MotoFeignClient;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.repositorio.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final RestTemplate restTemplate;
    private final CarroFeignClient carroFeignClient;
    private final MotoFeignClient motoFeignClient;

    //TODO: Usamos RestTemplate para hacer peticiones a los otros servicios con GET
    public List<Carro> getCarros(Long usuarioId){
        List<Carro> carros = restTemplate.getForObject("http://localhost:8002/api/v1/carro/usuario/"+usuarioId, List.class);
        return carros;
    }
    public List<Moto> getMotos(Long usuarioId){
        List<Moto> motos = restTemplate.getForObject("http://localhost:8003/api/v1/moto/usuario/"+usuarioId, List.class);
        return motos;
    }

    // TODO: Usamos FeignClient para hacer peticiones a los otros servicios con POST
    public Carro saveCarro(Long usuarioId, Carro carro){
        System.out.println("Usuario ID FEIGN: " + usuarioId);
        System.out.println("Carro: " + carro);
        carro.setUsuarioId(usuarioId);
        Carro nuevoCarro = carroFeignClient.save(carro);        
        return nuevoCarro;
    }

    public Moto saveMoto(Long usuarioId, Moto moto){
        moto.setUsuarioId(usuarioId);
        Moto nuevaMoto = motoFeignClient.save(moto);
        return nuevaMoto;
    }

    public Map<String, Object> getUsuarioAndVehiculos(Long usuarioId){
        System.out.println("Usuario ID: " + usuarioId);
        Map<String, Object> resultado = new HashMap<>();
        Usuario usuario = repository.findById(usuarioId).orElse(null);
        if(usuario == null) resultado.put("Mensaje", "EL usuario no existe");
        resultado.put("Usuario", usuario);

        // Lista de carros
        List<Carro> carros = carroFeignClient.getCarros(usuarioId);
        System.out.println("Carros: " + carros);
        if(carros.isEmpty()) resultado.put("Carros", "El usuario no tiene carros");
        resultado.put("Carros", carros);

        // Lista de motos
        List<Moto> motos = motoFeignClient.getMotos(usuarioId);
        System.out.println("Motos: " + motos);
        if(motos.isEmpty()) resultado.put("Motos", "El usuario no tiene motos");
        resultado.put("Motos", motos);

        return resultado;
    }

    // ESTAS SON LAS RUTAS NORMALES DEL USUARIO XD SIN MICROSERVICIOS

    public List<Usuario> getALl(){
        return repository.findAll();
    }

    public Usuario findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Usuario save(Usuario user) {
        return repository.save(user);
    }
}
