package com.moto.service.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moto.service.entidades.Moto;

import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long>{
    List<Moto> findByUsuarioId(Long usuarioId);
}
