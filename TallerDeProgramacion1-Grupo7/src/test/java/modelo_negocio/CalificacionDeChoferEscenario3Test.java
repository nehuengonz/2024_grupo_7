package modelo_negocio;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.SinViajesException;
import modeloDatos.Chofer;
import modeloNegocio.Empresa;
import util.Mensajes;

public class CalificacionDeChoferEscenario3Test {
	
	Escenario3 escenario3 = new Escenario3();

	@Before
	public void setUp() throws Exception {
		escenario3.setup();
		Empresa.getInstance().login("facundo", "123");
	}

	@After
	public void tearDown() throws Exception {
		Empresa.getInstance().logout();
		escenario3.tearDown();
	}

	@Test
	public void calificacionDeChoferConUnViajeTest() {
        try {
			Empresa.getInstance().pagarYFinalizarViaje(5);
			// Obtengo a chofer con su dni, (podría ser con el chofer del escenario directamente)
			Chofer chofer = Empresa.getInstance().getChoferes().get("1234567");
			double calificacion = Empresa.getInstance().calificacionDeChofer(chofer);
			// Me obliga a poner un delta
			assertEquals("No calcula bien el promedio", 5.0, calificacion, 0);
		} catch (SinViajesException ex) {
			fail("No debería haber fallado, el cliente tiene viaje");
		} catch (Exception ex) {
			fail("No debería tirar al excepción " + ex.getMessage());
		}
	}
	
	@Test
	public void calificacionDeChoferConVariosViajesTest() {
		
	}
	
	@Test
	public void calificacionDeChoferSinViajes() {
		try {
			Chofer chofer = Empresa.getInstance().getChoferes().get("1234568");
			double calificacion = Empresa.getInstance().calificacionDeChofer(chofer);
			fail("Se esperaba excepcion SinViajeException");
		} catch (SinViajesException ex) {
			assertEquals(Mensajes.CHOFER_SIN_VIAJES.getValor(), ex.getMessage());
		} catch (Exception ex) {
			fail("Se lanzo una excepción que no es la esperada: " + ex.getMessage());
		}
	}
}
