package br.com.futura.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HelloWorldTest {
	
	@Test
	public void testHelloWorld() {
		assertEquals(1, 1);
	}

}
