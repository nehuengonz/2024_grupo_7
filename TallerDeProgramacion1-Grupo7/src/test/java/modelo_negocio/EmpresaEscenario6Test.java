package modelo_negocio;

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

public class EmpresaEscenario6Test {
    private final Escenario6 escenario6 = new Escenario6();;
    @Before
    public void setUp() throws Exception {
        escenario6.setup();
    }

    @After
    public void tearDown() throws Exception {
        escenario6.teardown();
    }


    @Test
    public void testVehiculosOrdenadosPorPedidoSinVehiculoHabilitado() {
        Cliente cliente = Empresa.getInstance().getClientes().get("facundo");
        Pedido pedido = Empresa.getInstance().getPedidos().get(cliente);

        ArrayList<Vehiculo> vehiculosOrdenados = Empresa.getInstance().vehiculosOrdenadosPorPedido(pedido);

        assertNotNull("La lista de vehículos ordenados no debería ser nula.", vehiculosOrdenados);

        assertEquals("Se esperaban 0 vehículos ordenados.", 0, vehiculosOrdenados.size());
    }
}
