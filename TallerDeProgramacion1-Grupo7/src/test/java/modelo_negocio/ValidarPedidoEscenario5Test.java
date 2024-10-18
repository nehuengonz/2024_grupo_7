package modelo_negocio;

import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class ValidarPedidoEscenario5Test {
    Escenario5 escenario5 = new Escenario5();
    @Before
    public void setUp() throws Exception {
        escenario5.setup();
    }

    @After
    public void tearDown() throws Exception {
        escenario5.teardown();
    }

    @Test
    public void pedidoValidoHayVehiculo() {
        HashMap<Cliente, Pedido> pedidos = Empresa.getInstance().getPedidos();
        Cliente cliente = Empresa.getInstance().getClientes().get("thiago");
        Pedido pedidoValidar = pedidos.get(cliente);

        Assert.assertTrue("hay vehiculos desocupados que estan disponibles",Empresa.getInstance().validarPedido(pedidoValidar));
    }

    @Test
    public void validarPedidosNoValidosTest() {

            HashMap<Cliente, Pedido> pedidos = Empresa.getInstance().getPedidos();
            Cliente cliente = Empresa.getInstance().getClientes().get("facundo");
            Pedido pedidoValidar = pedidos.get(cliente);

            Assert.assertFalse("No hay vehiculos cargados que satisfagan este pedido",Empresa.getInstance().validarPedido(pedidoValidar));

    }

    @Test
    public void pedidoValidoPeroVehiculoOcupado() {

        HashMap<Cliente, Pedido> pedidos = Empresa.getInstance().getPedidos();
        Cliente cliente = Empresa.getInstance().getClientes().get("sofi");
        Pedido pedidoValidar = pedidos.get(cliente);

        Assert.assertFalse("No hay vehiculos desocupados que satisfagan este pedido",Empresa.getInstance().validarPedido(pedidoValidar));

    }


}
