package test_modelodatos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modeloDatos.Usuario;
import test.EscenarioVacio;

public class Usuario_test {
	private EscenarioVacio Escenario=new EscenarioVacio();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Escenario.setUp();
	}

	@After
	public void tearDown() throws Exception {
		Escenario.tearDown();
	}
	@Test
	public void testConstructorUsuario() {
		Usuario usuario = new Usuario("JuanPerez","123456");
		assertNotNull("El constructor no fue creado exitosamente", usuario);
	}

	//usuario no se puede instanciar
	@Test
	public void testgetNombreUsuario() {
		Usuario usuario = new Usuario("JuanPerez","123456");
		String nombre=usuario.getNombreUsuario();
		
		assertEquals("el nombre de usuario no se a obtenido correctamente","JuanPerez",nombre);
	}
	@Test
	public void testgetPass() {
		Usuario usuario = new Usuario("JuanPerez","123456");
		String pass=usuario.getPass();
		
		assertEquals("la Contraseña no se a obtenido correctamente","123456",pass);
	}

	
}
