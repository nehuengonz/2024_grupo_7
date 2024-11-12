package modelo_negocio;

import excepciones.ClienteConPedidoPendienteException;
import excepciones.ClienteConViajePendienteException;
import excepciones.ClienteNoExisteException;
import excepciones.SinVehiculoParaPedidoException;
import modeloDatos.Chofer;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Constantes;
import util.Mensajes;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class EmpresaEscenario4Test {
    private Escenario4 escenario4;
    @Before
    public void setUp() throws Exception {
        escenario4 = new Escenario4();
        escenario4.setup();
    }

    @After
    public void tearDown() throws Exception {
        escenario4.tearDown();
    }

    @Test
    public void getHistorialdeViajeChofer() {
        Chofer c = Empresa.getInstance().getChoferes().get("22222222");

        try {
            ArrayList<Viaje> viajes =  Empresa.getInstance().getHistorialViajeChofer(c);
            Assert.assertFalse("El chofer deberia tener un viaje", viajes.isEmpty());
            Assert.assertEquals("El chofer devuelto no es el mismo de la prueba",viajes.get(0).getChofer().getDni(),c.getDni());
        }catch (Exception e){
            fail("Hubo un error inesperado el metodo no funciona como deberia");
        }

    }

    @Test
    public void getHistorialdeViajeChoferNoTieneHistorial() {
        Chofer c = Empresa.getInstance().getChoferes().get("1234567");

        try {
            ArrayList<Viaje> viajes =  Empresa.getInstance().getHistorialViajeChofer(c);
            Assert.assertTrue("El chofer no deberia tener un viaje", viajes.isEmpty());
        }catch (Exception e){
            fail("Hubo un error inesperado el metodo no funciona como deberia");
        }
    }

    @Test
    public void getHistorialdeViajeCliente() {
        Cliente c = Empresa.getInstance().getClientes().get("thiago");

        try {
            ArrayList<Viaje> viajes =  Empresa.getInstance().getHistorialViajeCliente(c);
            Assert.assertFalse("El cliente deberia tener un viaje", viajes.isEmpty());
            Assert.assertEquals("El cliente devuelto no es el mismo de la prueba",
                    viajes.get(0).getPedido().getCliente().getNombreUsuario(),
                    c.getNombreUsuario());
        }catch (Exception e){
            fail("Hubo un error inesperado el metodo no funciona como deberia");
        }
    }

    @Test
    public void getHistorialdeViajeClienteNoTieneHistorial() {
        Cliente c = Empresa.getInstance().getClientes().get("facundo");

        try {
            ArrayList<Viaje> viajes =  Empresa.getInstance().getHistorialViajeCliente(c);
            Assert.assertTrue("El cliente no deberia tener un viaje en el historial", viajes.isEmpty());
        }catch (Exception e){
            fail("Hubo un error inesperado el metodo no funciona como deberia");
        }
    }

    @Test
    public void testAgregarPedidoValido() {
        Cliente cliente = Empresa.getInstance().getClientes().get("thiago");
        Pedido p = new Pedido(cliente, 1, true, true, 2, Constantes.ZONA_PELIGROSA);
        try {
            Empresa.getInstance().agregarPedido(p);
            assertNotNull("El pedido no es agregado correctamente", Empresa.getInstance().getPedidos().get(cliente));
        } catch (SinVehiculoParaPedidoException | ClienteNoExisteException | ClienteConViajePendienteException
                 | ClienteConPedidoPendienteException ex) {
            fail("No debería tirar excepción del tipo " + ex.getMessage());
        }
    }

    @Test
    public void testAgregarPedidoEmpresaNoCumple() {
        Cliente cliente = Empresa.getInstance().getClientes().get("thiago");
        Pedido p = new Pedido(cliente, 8, true, true, 10, Constantes.ZONA_PELIGROSA);
        try {
            Empresa.getInstance().agregarPedido(p);
            fail("Debería tirar excepcion SinVehiculoParaPedidoException");
        } catch (SinVehiculoParaPedidoException ex) {
            // Debe ir por aquí
            assertEquals("La excepcion no tira el mensaje correcto", "Ningun vehiculo puede satisfacer el pedido"
                    , Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor());
        } catch (ClienteNoExisteException | ClienteConViajePendienteException
                 | ClienteConPedidoPendienteException ex) {
            fail("No debería tirar excepcion " + ex.getMessage());
        }
    }

    @Test
    public void testAgregarPedidoClienteConViajeIniciado() {
        Cliente cliente = Empresa.getInstance().getClientes().get("facundo");
        Pedido p = new Pedido(cliente, 1, true, true, 2, Constantes.ZONA_PELIGROSA);
        try {
            Empresa.getInstance().agregarPedido(p);
            fail("Deberia tirar excepcion ClienteConViajePendienteException");
        } catch (ClienteConViajePendienteException ex) {
            // Debe ir por aquí
            assertEquals("La excepcion no tira el mensaje correcto", "Cliente con viaje pendiente"
                    , Mensajes.CLIENTE_CON_VIAJE_PENDIENTE.getValor());
        } catch (ClienteNoExisteException | SinVehiculoParaPedidoException
                 | ClienteConPedidoPendienteException ex) {
            fail("No debería tirar excepcion " + ex.getMessage());
        }
    }
}
