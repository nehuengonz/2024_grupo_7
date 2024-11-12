package modelo_negocio;

import excepciones.ClienteSinViajePendienteException;
import excepciones.SinViajesException;
import modeloDatos.Chofer;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Mensajes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class EmpresaEscenario3Test {
    private Escenario3 escenario3;
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
    public void pagarYFinalizarViajeTest() {
        try {

            Cliente clienteLogueado = Empresa.getInstance().getClientes().get(Empresa.getInstance().getUsuarioLogeado().getNombreUsuario());
            Viaje v = Empresa.getInstance().getViajeDeCliente(clienteLogueado);
            Empresa.getInstance().pagarYFinalizarViaje(5);


            Assert.assertEquals("No se guardo bien la calificacion del viaje",v.getCalificacion(),5);
        } catch (ClienteSinViajePendienteException e) {
            fail("El cliente tiene un viaje pendiente de terminar");
        }
    }

    @Test
    public void pagarYFinalizarViajeClienteSinViajeTest() {
        try {
            Empresa.getInstance().pagarYFinalizarViaje(5);
            Empresa.getInstance().pagarYFinalizarViaje(5);

            fail("El metodo no deberia continuar");

        } catch (ClienteSinViajePendienteException e) {
            Assert.assertEquals("El mensaje esta mal",e.getMessage(), Mensajes.CLIENTE_SIN_VIAJE_PENDIENTE.getValor());
        }
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


    @Test
    public void calificacionDeChoferConUnViajeTest() {
        try {
            Empresa.getInstance().pagarYFinalizarViaje(5);
            // Obtengo a chofer con su dni, (podría ser con el chofer del escenario directamente)
            Chofer chofer = Empresa.getInstance().getChoferes().get("1234567");
            double calificacion = Empresa.getInstance().calificacionDeChofer(chofer);
            // Me obliga a poner un delta
            assertEquals("No calcula bien el promedio", 5.0, calificacion, 0);
        } catch (SinViajesException ex) {
            fail("No debería haber fallado, el cliente tiene viaje");
        } catch (Exception ex) {
            fail("No debería tirar al excepción " + ex.getMessage());
        }
    }

    @Test
    public void calificacionDeChoferConVariosViajesTest() {

    }

    @Test
    public void calificacionDeChoferSinViajes() {
        try {
            Chofer chofer = Empresa.getInstance().getChoferes().get("1234568");
            double calificacion = Empresa.getInstance().calificacionDeChofer(chofer);
            fail("Se esperaba excepcion SinViajeException");
        } catch (SinViajesException ex) {
            assertEquals(Mensajes.CHOFER_SIN_VIAJES.getValor(), ex.getMessage());
        } catch (Exception ex) {
            fail("Se lanzo una excepción que no es la esperada: " + ex.getMessage());
        }
    }


}
