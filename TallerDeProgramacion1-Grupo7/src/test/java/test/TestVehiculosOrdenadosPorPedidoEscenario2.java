package test;

import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestVehiculosOrdenadosPorPedidoEscenario2 {
    private Escenario2 escenario2 = new Escenario2();

    @Before
    public void setUp() throws Exception {
        escenario2.setup();
    }

    @After
    public void tearDown(){
        escenario2.teardown();
    }

    @Test
    public void testVehiculosOrdenadosPorPedido() {
        Cliente cliente = Empresa.getInstance().getClientes().get("facundo");
        Pedido pedido = Empresa.getInstance().getPedidos().get(cliente);

        ArrayList<Vehiculo> vehiculosOrdenados = Empresa.getInstance().vehiculosOrdenadosPorPedido(pedido);

        assertNotNull("La lista de vehículos ordenados no debería ser nula.", vehiculosOrdenados);

        assertEquals("Se esperaban 2 vehículos ordenados.", 2, vehiculosOrdenados.size());

        assertEquals("El vehículo con mayor puntaje debe ser ABC123.", "abc123", vehiculosOrdenados.get(0).getPatente());
        assertEquals("El segundo vehículo con mayor puntaje debe ser combi111.", "combi111", vehiculosOrdenados.get(1).getPatente());
    }

    
}
