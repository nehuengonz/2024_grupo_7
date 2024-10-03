package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Cliente;
import modeloDatos.Usuario;

public class ClienteTest {
	
	Cliente cliente;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructorCliente() {
		cliente = new Cliente("wutang", "12345", "Thiago");
		assertNotNull("El constructor no fue creado exitosamente", cliente);
		
		assertEquals("Los valores de los atributos no son establecidos correctamente", "wutang", cliente.getNombreUsuario());
		assertEquals("Los valores de los atributos no son establecidos correctamente", "12345", cliente.getPass());
		assertEquals("Los valores de los atributos no son establecidos correctamente", "Thiago", cliente.getNombreReal());
	}
	
	

}
