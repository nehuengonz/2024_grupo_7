package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Moto;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import util.Constantes;

public class TestMoto {
    Moto moto;
    Cliente cliente;

    @Before
    public void setUp() throws Exception {
        moto = new Moto("AAA 123"); // Se inicializa la moto
        cliente = new Cliente("sofii", "123456789", "Sofia");
    }

    @After
    public void tearDown() throws Exception {
        moto = null; // Limpiar referencia a moto
        cliente = null; // Limpiar referencia a cliente
    }

    // Tests para getPuntajePedido
    @Test
    public void testGetPuntajePedidoValido() {
        Pedido pedido = new Pedido(cliente, 1, false, false, 25, Constantes.ZONA_SIN_ASFALTAR); // Un pasajero sin uso de baúl y sin mascota
        assertEquals("El puntaje no se calcula correctamente", Integer.valueOf(1000), moto.getPuntajePedido(pedido));
    }

    @Test
    public void testGetPuntajePedidoConMascota() {
        Pedido pedido = new Pedido(cliente, 1, true, false, 25, Constantes.ZONA_SIN_ASFALTAR); // Un pasajero con mascota
        assertNull("El método debería devolver null porque no se permite mascota", moto.getPuntajePedido(pedido));
    }

    @Test
    public void testGetPuntajePedidoConBaul() {
        Pedido pedido = new Pedido(cliente, 1, false, true, 25, Constantes.ZONA_SIN_ASFALTAR); // Un pasajero con uso de baúl
        assertNull("El método debería devolver null porque se usa baúl", moto.getPuntajePedido(pedido));
    }

    @Test
    public void testGetPuntajePedidoConMasPasajeros() {
        Pedido pedido = new Pedido(cliente, 2, false, false, 25, Constantes.ZONA_SIN_ASFALTAR); // Más de un pasajero
        assertNull("El método debería devolver null porque no se puede satisfacer la necesidad", moto.getPuntajePedido(pedido));
    }

}
