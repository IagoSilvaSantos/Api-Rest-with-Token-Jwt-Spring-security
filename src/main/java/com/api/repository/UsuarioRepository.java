package com.api.repository;



import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.api.model.UsuarioModel;



public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {

    public Optional<UsuarioModel> findByName(String name);   
}