package br.com.futura.api.resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@ActiveProfiles("test")
public class UsuarioResourceTest {

	private static final String NOME = "Fulano de Tal";
	private static final String LOGIN = "fulano";
	private static final String SENHA = "123456";
	private static final String EMAIL = "fulano@teste.com";
	private static final String URI = "/usuarios";
	
	@MockBean
	private UsuarioService service;
	
	@Autowired
	private MockMvc mvc;

	public void testSave() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post(URI).content(getJsonPayLoad())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
	}
	
	public Usuario getMockUsuario() {
		Usuario u = new Usuario();
		u.setNome(NOME);
		u.setLogin(LOGIN);
		u.setSenha(SENHA);
		u.setEmail(EMAIL);
		
		return u;
	}
	
	public String getJsonPayLoad() throws JsonProcessingException {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setNome(NOME);
		dto.setLogin(LOGIN);
		dto.setSenha(SENHA);
		dto.setEmail(EMAIL);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}
	
}
