package modelo_negocio;

import modeloDatos.Auto;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Constantes;

public class ValidarPedidoBaseTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void validarPedidoNoExisteVehiculoTest() {
        Pedido p = new Pedido(new Cliente("a","111","a a"),1,false,false,10,Constantes.ZONA_SIN_ASFALTAR);
        Assert.assertFalse("Se supone que los vehiculos disponibles esta vacio",Empresa.getInstance().validarPedido(p));
    }
}
