package com.api.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.api.data.DetalheUsuarioData;
import com.api.model.UsuarioModel;
import com.api.repository.UsuarioRepository;



@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {
	
	private final UsuarioRepository repository;
	
	public DetalheUsuarioServiceImpl(UsuarioRepository repository) {
		 this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {	
		Optional<UsuarioModel> usuario = repository.findByName(username);
		if (usuario.isEmpty()) {
			throw new UsernameNotFoundException("Usuário [" + username + "] não encontrado ");
		}
		
		return new DetalheUsuarioData(usuario);
	}

}
