package br.com.futura.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.futura.api.entity.Usuario;
import br.com.futura.api.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository repositorio;

	@Override
	public Usuario save(Usuario usuario) {
		return repositorio.save(usuario);
	}

	@Override
	public Optional<Usuario> findByEmail(String email) {
		return repositorio.findByEmail(email);
	}
	
	

}
