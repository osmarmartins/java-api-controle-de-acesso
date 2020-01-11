package br.com.futura.api.resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.futura.api.dto.UsuarioDTO;
import br.com.futura.api.entity.Usuario;
import br.com.futura.api.service.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UsuarioResourceTest {

	private static final Long ID = 1L; 
	private static final String NOME = "Fulano de Tal";
	private static final String LOGIN = "fulano";
	private static final String SENHA = "123456";
	private static final String EMAIL = "fulano@teste.com";
	private static final String URI = "/usuarios";
	
	@MockBean
	private UsuarioService service;
	
	@Autowired
	private MockMvc mvc;

	@Test
	public void testSave() throws Exception {
		
		BDDMockito.given(service.save(Mockito.any(Usuario.class))).willReturn(getMockUsuario());
		
		mvc.perform(MockMvcRequestBuilders.post(URI)
			.content(getJsonPayload(ID, EMAIL, NOME, SENHA, LOGIN))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.data.id").value(ID))
		.andExpect(jsonPath("$.data.nome").value(NOME))
		.andExpect(jsonPath("$.data.login").value(LOGIN))
		.andExpect(jsonPath("$.data.senha").value(SENHA))
		.andExpect(jsonPath("$.data.email").value(EMAIL));
	}
	
	@Test
	public void testSaveInvalidUser() throws JsonProcessingException, Exception {
		
		mvc.perform(MockMvcRequestBuilders.post(URI).content(getJsonPayload(ID, "email", NOME, SENHA, LOGIN))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors[0]").value("e-mail inválido!"));
		
	}
	
	public Usuario getMockUsuario() {
		Usuario u = new Usuario();
		u.setId(ID);
		u.setNome(NOME);
		u.setLogin(LOGIN);
		u.setSenha(SENHA);
		u.setEmail(EMAIL);
		
		return u;
	}
	
	public String getJsonPayload(Long id, String email, String name, String password, String login) throws JsonProcessingException {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId(id);
		dto.setEmail(email);
		dto.setNome(name);
		dto.setSenha(password);
		dto.setLogin(login);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}
	
}
