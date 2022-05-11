package com.api.controller;


import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.api.model.UsuarioModel;
import com.api.repository.UsuarioRepository;
import com.api.response.MeResponse;

import java.util.List;


@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;    
       
    
    public UsuarioController(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }
  
    @GetMapping("/me")
    public ResponseEntity<MeResponse> showUserLogged() {
      Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
      
      MeResponse usuario = new MeResponse();
      usuario.setName(autenticado.getName());
      
      return ResponseEntity.ok(usuario);
    }
   
    @GetMapping("/listAll")    
    public ResponseEntity<List<UsuarioModel>> listAll() {
        return ResponseEntity.ok(repository.findAll());
    }    
 
    @PostMapping("/register")
    public ResponseEntity<UsuarioModel> register(@RequestBody UsuarioModel usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        return ResponseEntity.ok(repository.save(usuario));
    }
    
}
