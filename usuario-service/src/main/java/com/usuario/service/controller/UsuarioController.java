package com.usuario.service.controller;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.servicio.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
    private final UsuarioService service;


    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        List<Usuario> usuarios = service.getALl();
        if(usuarios.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(usuarios);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id){
        Usuario usuario = service.findById(id);
        if(usuario == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        Usuario user = service.save(usuario);
        return ResponseEntity.ok(user);
    }

    // TODO: Aca usamos RestTemplate para hacer peticiones a los otros servicios con GET
    @GetMapping("/carro/{usuarioId}")
    public ResponseEntity<List<Carro>> listarCarros(@PathVariable Long usuarioId){
        Usuario usuario = service.findById(usuarioId);
        if(usuario == null) return ResponseEntity.notFound().build();
        List<Carro> carros = service.getCarros(usuarioId);
        if(carros.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(carros);
    }
    @GetMapping("/moto/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotos(@PathVariable Long usuarioId){
        Usuario usuario = service.findById(usuarioId);
        if(usuario == null) return ResponseEntity.notFound().build();
        List<Moto> motos = service.getMotos(usuarioId);
        if(motos.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(motos);
    }

    // TODO: Aca usamos FeignClient para hacer peticiones a los otros servicios con POST
    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<Carro>guardarCarro(@PathVariable Long usuarioId, @RequestBody Carro carro){
        Usuario usuario = service.findById(usuarioId);
        if(usuario == null) return ResponseEntity.notFound().build();
        Carro nuevoCarro = service.saveCarro(usuarioId, carro);
        return ResponseEntity.ok(nuevoCarro);
    }

    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<Moto> guardarMoto(@PathVariable Long usuarioId, @RequestBody Moto moto){
        Usuario usuario = service.findById(usuarioId);
        if(usuario == null) return ResponseEntity.notFound().build();
        Moto nuevaMoto = service.saveMoto(usuarioId, moto);
        return ResponseEntity.ok(nuevaMoto);
    }
    @GetMapping("/vehiculos/{usuarioId}")
    public ResponseEntity<Map<String, Object>> listaDeVehiculos (@PathVariable Long usuarioId) {        
        Map<String, Object> resultado = service.getUsuarioAndVehiculos(usuarioId);
        return ResponseEntity.ok(resultado);
    }
}
