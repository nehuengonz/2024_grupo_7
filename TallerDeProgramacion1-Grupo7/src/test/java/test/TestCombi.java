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
        combi = null; // Limpiar referencia a combi
        cliente = null; // Limpiar referencia a cliente
    }

    @Test
    public void testConstructorValido() {
        Combi combiTest = new Combi("AAA 123", 10, true);
        assertEquals("La patente no se inicializa correctamente", "AAA 123", combiTest.getPatente());
        assertEquals("La cantidad de plazas no se inicializa correctamente", 10, combiTest.getCantidadPlazas());
        assertTrue("La mascota debe estar permitida", combiTest.isMascota());
    }

    // Tests de puntajes válidos
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
    public void testGetPuntajePedidoValidoConBaulConMascota() {
        Pedido pedido = new Pedido(cliente, 6, true, true, 25, Constantes.ZONA_SIN_ASFALTAR);
        assertEquals("El puntaje no se calcula correctamente con baúl y mascota", Integer.valueOf(160), combi.getPuntajePedido(pedido));
    }

    @Test
    public void testGetPuntajePedidoValidoConBaulSinMascota() {
        Pedido pedido = new Pedido(cliente, 6, false, true, 25, Constantes.ZONA_SIN_ASFALTAR);
        assertEquals("El puntaje no se calcula correctamente con baúl y sin mascota", Integer.valueOf(160), combi.getPuntajePedido(pedido));
    }

    @Test
    public void testGetPuntajePedidoInvalidoPorPasajerosExcedidos() {
        Pedido pedido = new Pedido(cliente, 11, false, false, 25, Constantes.ZONA_SIN_ASFALTAR); // Más pasajeros que capacidad.
        assertNull("El método debería devolver null", combi.getPuntajePedido(pedido));
    }

    @Test
    public void testGetPuntajePedidoInvalidoPorMascotaSinPermiso() {
        combi = new Combi("BBB 456", 10, false); // Combi que no permite mascotas
        Pedido pedido = new Pedido(cliente, 6, true, false, 25, Constantes.ZONA_SIN_ASFALTAR);
        assertNull("El método debería devolver null porque la combi no acepta mascotas", combi.getPuntajePedido(pedido));
    }

    @Test
    public void testGetPuntajePedidoConMenosPasajeros() {
        Pedido pedido = new Pedido(cliente, 4, true, false, 25, Constantes.ZONA_SIN_ASFALTAR); // Justo en el límite inferior
        assertNull("El método debería devolver null porque hay menos de 5 pasajeros", combi.getPuntajePedido(pedido));
    }

    @Test
    public void testGetPuntajePedidoConUnaMascotaSinPermiso() {
        combi = new Combi("EEE 123", 10, false); // Combi que no permite mascotas
        Pedido pedido = new Pedido(cliente, 6, true, false, 25, Constantes.ZONA_SIN_ASFALTAR); // Con mascota
        assertNull("El método debería devolver null porque la combi no acepta mascotas", combi.getPuntajePedido(pedido));
    }
}
