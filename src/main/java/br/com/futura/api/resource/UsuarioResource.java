package br.com.futura.api.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.futura.api.dto.UsuarioDTO;
import br.com.futura.api.entity.Usuario;
import br.com.futura.api.service.UsuarioService;
import br.com.futura.api.util.Response;

@RestController
@RequestMapping("usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@PostMapping
	public ResponseEntity<Response<UsuarioDTO>> criar(@Valid @RequestBody UsuarioDTO dto, BindingResult result) {
		
		Response<UsuarioDTO> response = new Response<UsuarioDTO>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		Usuario usuario = service.save(dto.convertDtoToEntity());
		
		response.setData(dto.convertEntityToDto(usuario));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}

}
