package br.com.futura.api.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.futura.api.entity.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository repositorio;
	
	private static final String EMAIL = "before@test.com";
	
	@Before
	public void setUp() {
		Usuario u = new Usuario();
		u.setNome("Set up user");
		u.setLogin("before");
		u.setSenha("test");
		u.setEmail(EMAIL);
		
		repositorio.save(u);
	}
	
	@After
	public void tearDown() {
		repositorio.deleteAll();
	}
	
	@Test
	public void testSalvar() {
		Usuario u = new Usuario();
		u.setNome("Teste Fulano de Tal");
		u.setLogin("fulano");
		u.setSenha("123456");
		u.setEmail("fulano@teste.com");
		
		Usuario usuario = repositorio.save(u);
		
		assertNotNull(usuario);
	}
	
	@Test
	public void testFindByEmail() {
		Optional<Usuario> usuario = repositorio.findByEmail(EMAIL);
		
		assertTrue(usuario.isPresent());
		assertEquals(usuario.get().getEmail(), EMAIL);
		
		
		
		
	}

}
