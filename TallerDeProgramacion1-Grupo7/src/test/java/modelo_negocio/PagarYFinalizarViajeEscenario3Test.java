package modelo_negocio;

import excepciones.ClienteSinViajePendienteException;
import modeloDatos.Cliente;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Constantes;
import util.Mensajes;

import java.util.HashMap;

import static org.junit.Assert.fail;

public class PagarYFinalizarViajeEscenario3Test {
    Escenario3 escenario3 = new Escenario3();

    @Before
    public void setUp() throws Exception {

        escenario3.setup();
        Empresa.getInstance().login("facundo","123");
    }

    @After
    public void tearDown() throws Exception {
        Empresa.getInstance().logout();
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




}
