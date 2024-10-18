package modelo_negocio;

import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.EscenarioVacio;

import java.util.HashMap;

public class ValidarPedidoEscenario2Test {
    private Escenario2 escenario2 = new Escenario2();

    @Before
    public void setUp() throws Exception {
        escenario2.setup();
    }

    @After
    public void tearDown() throws Exception {
        escenario2.tearDown();
    }

    @Test
    public void validarPedidoExisteVehiculoTest() {
        HashMap<Cliente, Pedido> pedidos = Empresa.getInstance().getPedidos();
        Cliente cliente = Empresa.getInstance().getClientes().get("facundo");
        Pedido pedidoValidar = pedidos.get(cliente);

        Assert.assertTrue("Se supone que existe un vehiculo que lo puede satisfacer",Empresa.getInstance().validarPedido(pedidoValidar));

        Cliente cliente1 = Empresa.getInstance().getClientes().get("nehuen");
        Pedido pedidoValidar1 = pedidos.get(cliente1);

        Assert.assertTrue("Se supone que existe un vehiculo que lo puede satisfacer",Empresa.getInstance().validarPedido(pedidoValidar1));

        Cliente cliente2 = Empresa.getInstance().getClientes().get("thiago");
        Pedido pedidoValidar2 = pedidos.get(cliente2);

        Assert.assertTrue("Se supone que existe un vehiculo que lo puede satisfacer",Empresa.getInstance().validarPedido(pedidoValidar2));
    }

    
}
