package br.com.futura.api.service;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.futura.api.entity.Usuario;
import br.com.futura.api.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@MockBean
	UsuarioRepository repository;
	
	@Autowired
	UsuarioService service;
	
	@Before
	public void setUp() {
		BDDMockito.given(repository.findByEmail(Mockito.anyString()))
			.willReturn(Optional.of(new Usuario()));
	}
	
	@Test
	public void testFindByEmail() {
		Optional<Usuario> usuario = service.findByEmail("email@teste.com");
		
		assertTrue(usuario.isPresent());
	}

}
