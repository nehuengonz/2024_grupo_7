package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import util.Constantes;

public class VehiculoAutoTest {
	
	Vehiculo vehiculo;
	Cliente cliente;

	@Before
	public void setUp() throws Exception {
		vehiculo = new Auto("ABC 123", 4, true);
		cliente = new Cliente("wutang", "12345", "Thiago");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructorAuto() {
		assertNotNull("El constructor no fue creado exitosamente", vehiculo);
		
		assertEquals("Los valores de los atributos no son establecidos correctamente", "ABC 123", vehiculo.getPatente());
		assertEquals("Los valores de los atributos no son establecidos correctamente", 4, vehiculo.getCantidadPlazas());
		assertEquals("Los valores de los atributos no son establecidos correctamente", true, vehiculo.isMascota());
	}
	
	@Test
	public void testGetPuntajePedidoValidoConBaul() {
		Pedido pedido = new Pedido(cliente, 4, true, true, 10, Constantes.ZONA_PELIGROSA);
		assertEquals("El puntaje no se calcula correctamente", 160, (int) vehiculo.getPuntajePedido(pedido));
	}
	@Test
	public void testGetPuntajePedidoValidoSinBaul() {
		Pedido pedido = new Pedido(cliente, 4, true, false, 10, Constantes.ZONA_PELIGROSA);
		assertEquals("El puntaje no se calcula correctamente", 120, (int) vehiculo.getPuntajePedido(pedido));
	}
	@Test
	public void testGetPuntajePedidoInvalidoConBaul() {		
		Pedido pedido = new Pedido(cliente, 5, true, true, 10, Constantes.ZONA_PELIGROSA);
		assertNull("El método debería devolver null", vehiculo.getPuntajePedido(pedido));
	}
	@Test
	public void testGetPuntajePedidoInvalidoSinBaul() {
		Pedido pedido = new Pedido(cliente, 5, true, false, 10, Constantes.ZONA_PELIGROSA);
		assertNull("El método debería devolver null", vehiculo.getPuntajePedido(pedido));
	}

}
