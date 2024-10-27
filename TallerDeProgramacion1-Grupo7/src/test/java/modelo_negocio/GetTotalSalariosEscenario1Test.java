package modelo_negocio;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloNegocio.Empresa;

public class GetTotalSalariosEscenario1Test {
	
	Escenario2 escenario2 = new Escenario2();

	@Before
	public void setUp() throws Exception {
		escenario2.setup();
	}

	@After
	public void tearDown() throws Exception {
		escenario2.tearDown();
	}

	@Test
	public void test() {
		double total=0;
		total= Empresa.getInstance().getChoferes().get("1234567").getSueldoNeto() +
				Empresa.getInstance().getChoferes().get("1234568").getSueldoNeto() +
				Empresa.getInstance().getChoferes().get("11111111").getSueldoNeto() +
				Empresa.getInstance().getChoferes().get("22222222").getSueldoNeto();
		assertEquals("el salario total deveria ser ",total, Empresa.getInstance().getTotalSalarios(),0.0001);
	}

}
