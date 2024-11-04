package modelo_negocio;

import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GetPedidoyViajeClienteTest {
    Escenario3 escenario3;
    public GetPedidoyViajeClienteTest() {
    }

    @Before
    public void setUp() throws Exception {
        escenario3 = new Escenario3();

        escenario3.setup();
    }

    @After
    public void tearDown() throws Exception {
        escenario3.tearDown();
    }

    @Test
    public void getPedidoClienteTest() {
        Cliente c = Empresa.getInstance().getClientes().get("thiago");
        try{
            Pedido p = Empresa.getInstance().getPedidoDeCliente(c);
            Assert.assertNotNull("El pedido deberia existir",p);
            Assert.assertEquals(p.getCliente().getNombreUsuario(), c.getNombreUsuario());
        }catch (Exception e){
            Assert.fail("Hubo un error inesperado, el metodo no funciona como deberia");
        }

    }

    @Test
    public void getPedidoClienteNoExisteCLienteTest() {
        Cliente c = Empresa.getInstance().getClientes().get("facundo");
        try{
            Pedido p = Empresa.getInstance().getPedidoDeCliente(c);
            Assert.assertNull("El pedido no deberia existir",p);
        }catch (Exception e){
            Assert.fail("Hubo un error inesperado, el metodo no funciona como deberia");
        }
    }

    @Test
    public void getViajeClienteTest() {
        Cliente c = Empresa.getInstance().getClientes().get("facundo");
        try{
            Viaje v = Empresa.getInstance().getViajeDeCliente(c);
            Assert.assertNotNull("El viaje deberia existir",v);
            Assert.assertEquals(v.getPedido().getCliente().getNombreUsuario(), c.getNombreUsuario());
        }catch (Exception e){
            Assert.fail("Hubo un error inesperado, el metodo no funciona como deberia");
        }
    }

    @Test
    public void getViajeClienteNoTieneViajeTest() {
        Cliente c = Empresa.getInstance().getClientes().get("thiago");
        try{
            Viaje v = Empresa.getInstance().getViajeDeCliente(c);
            Assert.assertNull("El viaje no deberia existir",v);
        }catch (Exception e){
            Assert.fail("Hubo un error inesperado, el metodo no funciona como deberia");
        }
    }

}
