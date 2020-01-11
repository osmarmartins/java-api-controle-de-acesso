package br.com.futura.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.futura.api.entity.Usuario;
import lombok.Data;

@Data
public class UsuarioDTO {
	
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String login;

	@Length(min = 3, message = "Senha deve conter no mínimo 3 caracteres")
	private String senha;
	
	@Email(message = "e-mail inválido!")
	private String email;

	// TODO Usar ModelMapper
	public Usuario convertDtoToEntity() {
		Usuario usuario = new Usuario();
		usuario.setId(this.id);
		usuario.setNome(this.nome);
		usuario.setLogin(this.login);
		usuario.setEmail(this.email);
		usuario.setSenha(this.senha);
		return usuario;
	}

	public UsuarioDTO convertEntityToDto(Usuario usuario) {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId(usuario.getId());
		dto.setNome(usuario.getNome());
		dto.setLogin(usuario.getLogin());
		dto.setSenha(usuario.getSenha());
		dto.setEmail(usuario.getEmail());
		return dto;
	}

}
