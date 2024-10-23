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

public class AgregarPedidoEscenario4Test {
	
	Escenario4 escenario4 = new Escenario4();

	@Before
	public void setUp() throws Exception {
		escenario4.setup();
	}

	@After
	public void tearDown() throws Exception {
		escenario4.tearDown();
	}

	@Test
	public void testAgregarPedidoValido() {
		Cliente cliente = Empresa.getInstance().getClientes().get("thiago");
		Pedido p = new Pedido(cliente, 1, true, true, 2, Constantes.ZONA_PELIGROSA);
		try {
			Empresa.getInstance().agregarPedido(p);
			assertNotNull("El pedido no es agregado correctamente", Empresa.getInstance().getPedidos().get(cliente));
		} catch (SinVehiculoParaPedidoException | ClienteNoExisteException | ClienteConViajePendienteException
				| ClienteConPedidoPendienteException ex) {
			fail("No debería tirar excepción del tipo " + ex.getMessage());
		}
	}
	
	@Test
	public void testAgregarPedidoEmpresaNoCumple() {
		Cliente cliente = Empresa.getInstance().getClientes().get("thiago");
		Pedido p = new Pedido(cliente, 8, true, true, 10, Constantes.ZONA_PELIGROSA);
		try {
			Empresa.getInstance().agregarPedido(p);
			fail("Debería tirar excepcion SinVehiculoParaPedidoException");
		} catch (SinVehiculoParaPedidoException ex) {
			// Debe ir por aquí
			assertEquals("La excepcion no tira el mensaje correcto", "Ningun vehiculo puede satisfacer el pedido"
					, Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor());
		} catch (ClienteNoExisteException | ClienteConViajePendienteException
				| ClienteConPedidoPendienteException ex) {
			fail("No debería tirar excepcion " + ex.getMessage());
		}
	}
	
	@Test
	public void testAgregarPedidoClienteConViajeIniciado() {
		Cliente cliente = Empresa.getInstance().getClientes().get("facundo");
		System.out.println(Empresa.getInstance().getViajesIniciados());
		Pedido p = new Pedido(cliente, 1, true, true, 2, Constantes.ZONA_PELIGROSA);
		try {
			Empresa.getInstance().agregarPedido(p);
			fail("Deberia tirar excepcion ClienteConViajePendienteException");
		} catch (ClienteConViajePendienteException ex) {
			// Debe ir por aquí
			assertEquals("La excepcion no tira el mensaje correcto", "Cliente con viaje pendiente"
					, Mensajes.CLIENTE_CON_VIAJE_PENDIENTE.getValor());
		} catch (ClienteNoExisteException | SinVehiculoParaPedidoException
				| ClienteConPedidoPendienteException ex) {
			fail("No debería tirar excepcion " + ex.getMessage());
		}
	}
	@Test
	public void testAgregarPedidoClienteConPedidoIniciado() {
		Cliente cliente = Empresa.getInstance().getClientes().get("facundo");
		System.out.println(Empresa.getInstance().getViajesIniciados());
		Pedido p = new Pedido(cliente, 1, true, true, 2, Constantes.ZONA_PELIGROSA);
		try {
			Empresa.getInstance().agregarPedido(p);
			fail("Deberia tirar excepcion ClienteConViajePendienteException");
		} catch (ClienteConViajePendienteException ex) {
			// Debe ir por aquí
			assertEquals("La excepcion no tira el mensaje correcto", "Cliente con viaje pendiente"
					, Mensajes.CLIENTE_CON_VIAJE_PENDIENTE.getValor());
		} catch (ClienteNoExisteException | SinVehiculoParaPedidoException
				| ClienteConPedidoPendienteException ex) {
			fail("No debería tirar excepcion " + ex.getMessage());
		}
	}
}
