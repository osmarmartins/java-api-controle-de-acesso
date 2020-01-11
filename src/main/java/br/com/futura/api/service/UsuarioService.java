package br.com.futura.api.service;

import java.util.Optional;

import br.com.futura.api.entity.Usuario;

public interface UsuarioService {
	
	Usuario save(Usuario usuario);
	
	Optional<Usuario> findByEmail(String email);
	
}
