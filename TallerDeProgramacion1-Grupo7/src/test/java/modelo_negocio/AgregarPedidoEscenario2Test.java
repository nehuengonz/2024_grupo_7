package modelo_negocio;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.ClienteConPedidoPendienteException;
import excepciones.ClienteConViajePendienteException;
import excepciones.ClienteNoExisteException;
import excepciones.SinVehiculoParaPedidoException;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloNegocio.Empresa;
import util.Constantes;
import util.Mensajes;

public class AgregarPedidoEscenario2Test {
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
	public void testAgregarPedidoClienteConPedidoIniciado() {
		Cliente cliente = Empresa.getInstance().getClientes().get("thiago");
		Pedido p = new Pedido(cliente, 1, true, true, 2, Constantes.ZONA_PELIGROSA);
		try {
			Empresa.getInstance().agregarPedido(p);
			fail("Deberia tirar excepcion ClienteConPedidoPendienteException");
		} catch (ClienteConPedidoPendienteException ex) {
			// Debe ir por aquí
			assertEquals("La excepcion no tira el mensaje correcto", "Cliente con pedido pendiente"
					, Mensajes.CLIENTE_CON_PEDIDO_PENDIENTE.getValor());
		} catch (ClienteNoExisteException | SinVehiculoParaPedidoException
				| ClienteConViajePendienteException ex) {
			fail("No debería tirar excepcion " + ex.getMessage());
		}
	}
	@Test
	public void testAgregarPedidoClienteNoExiste() {
		Cliente cliente = new Cliente("usuarioNuevo", "123", "Usuario");
		Pedido p = new Pedido(cliente, 1, true, true, 2, Constantes.ZONA_PELIGROSA);
		try {
			Empresa.getInstance().agregarPedido(p);
			fail("Deberia tirar excepcion ClienteNoExisteException");
		} catch (ClienteNoExisteException ex) {
			// Debe ir por aquí
			assertEquals("La excepcion no tira el mensaje correcto", "EL Cliente no esta registrado"
					, Mensajes.CLIENTE_NO_EXISTE.getValor());
		} catch (ClienteConPedidoPendienteException | SinVehiculoParaPedidoException
				| ClienteConViajePendienteException ex) {
			fail("No debería tirar excepcion " + ex.getMessage());
		}
	}

}
