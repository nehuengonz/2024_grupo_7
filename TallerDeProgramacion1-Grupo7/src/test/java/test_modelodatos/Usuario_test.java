package test_modelodatos;

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
	
	}

	@After
	public void tearDown() throws Exception {
	}
	//usuario no se puede instanciar
	@Test
	public void testgetNombreUsuario() {
		Usuario usuario = new Usuario("JuanPerez","123456");
		String nombre=usuario.getNombreUsuario();
	}

}
