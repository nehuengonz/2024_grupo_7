package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Combi;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import util.Constantes;

public class TestCombi {
    Combi combi;
    Cliente cliente;

    @Before
    public void setUp() throws Exception {
        combi = new Combi("AAA 123", 10, true); // Se inicializa la combi
        cliente = new Cliente("sofii", "123456789", "Sofia");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetPuntajePedidoValidoConMascota() {
        Pedido pedido = new Pedido(cliente, 6, true, false, 25, Constantes.ZONA_SIN_ASFALTAR);
        assertEquals("El puntaje no se calcula correctamente", Integer.valueOf(60), combi.getPuntajePedido(pedido));
    }

    @Test
    public void testGetPuntajePedidoValidoSinMascota() {
        Pedido pedido = new Pedido(cliente, 6, false, false, 25, Constantes.ZONA_SIN_ASFALTAR);
        assertEquals("El puntaje no se calcula correctamente", Integer.valueOf(60), combi.getPuntajePedido(pedido));
    }

    @Test
    public void testGetPuntajePedidoInvalidoPorPasajerosExcedidos() {
        Pedido pedido = new Pedido(cliente, 11, false, false, 25, Constantes.ZONA_SIN_ASFALTAR); // Más pasajeros que capacidad.
        assertNull("El método debería devolver null", combi.getPuntajePedido(pedido));
    }

    @Test
    public void testGetPuntajePedidoValidoConBaulConMascota() {
        Pedido pedido = new Pedido(cliente, 6, true, true, 25, Constantes.ZONA_SIN_ASFALTAR);
        assertEquals("El puntaje no se calcula correctamente con baúl y mascota", Integer.valueOf(160), combi.getPuntajePedido(pedido));
    }

    @Test
    public void testGetPuntajePedidoValidoConBaulSinMascota() {
        Pedido pedido = new Pedido(cliente, 6, false, true, 25, Constantes.ZONA_SIN_ASFALTAR);
        assertEquals("El puntaje no se calcula correctamente con baúl y sin mascota", Integer.valueOf(160), combi.getPuntajePedido(pedido));
    }
}
