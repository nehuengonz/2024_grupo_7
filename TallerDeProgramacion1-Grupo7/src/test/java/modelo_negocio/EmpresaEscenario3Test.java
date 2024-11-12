package modelo_negocio;

import excepciones.*;
import modeloDatos.*;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Constantes;
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
    public void creoViajeConViajePendiente() {
        Cliente c = Empresa.getInstance().getClientes().get("facundo");

        Pedido p = new Pedido(c, 1, false, false, 10, Constantes.ZONA_STANDARD);

        Vehiculo v = Empresa.getInstance().getVehiculos().get("pat333");

        Chofer chofer = Empresa.getInstance().getChoferes().get("1234568");

        try {
            Empresa.getInstance().agregarPedido(p);
            Empresa.getInstance().crearViaje(p, chofer, v);
            fail("no tiene que continuar la ejecucion");
        } catch (ChoferNoDisponibleException e) {
            fail("no tiene que tirar chofer no disponible");
        } catch (ClienteConViajePendienteException e) {
            assertEquals("El mensaje de la excepcion no es correcto ", e.getMessage(), Mensajes.CLIENTE_CON_VIAJE_PENDIENTE.getValor());
        } catch (PedidoInexistenteException e) {
            fail("no tiene que tirar pedido inexistente");
        } catch (VehiculoNoDisponibleException e) {
            fail("no tiene que tirar vehiculo no disponible");
        } catch (VehiculoNoValidoException e) {
            fail("no tiene que tirar vehiculo no valido");
        }catch (Exception e){
            fail("no tiene que tirar excepcion");
        }
    }

    @Test
    public void creoViajeConChoferNoDisponible(){
        Cliente c = Empresa.getInstance().getClientes().get("thiago");

        Pedido p = Empresa.getInstance().getPedidoDeCliente(c);

        Vehiculo v = Empresa.getInstance().getVehiculos().get("pat333");

        Chofer chofer = Empresa.getInstance().getChoferes().get("1234567");
        //Chofer chofer1 = Empresa.getInstance().getChoferes().get("1234568");

        try {
            Empresa.getInstance().crearViaje(p, chofer, v);
            fail("no tiene que continuar la ejecucion");
        } catch (ChoferNoDisponibleException e) {
            assertEquals("El mensaje de la excepcion no es correcto ", e.getMessage(), Mensajes.CHOFER_NO_DISPONIBLE.getValor());
            assertEquals("El chofer de la excepcion no es correcto ", e.getChofer().getDni(), chofer.getDni());
        } catch (ClienteConViajePendienteException e) {
            fail("no tiene que tirar viaje pendiente");
        } catch (PedidoInexistenteException e) {
            fail("no tiene que tirar pedido inexistente");
        } catch (VehiculoNoDisponibleException e) {
            fail("no tiene que tirar vehiculo no disponible");
        } catch (VehiculoNoValidoException e) {
            fail("no tiene que tirar vehiculo no valido");
        }
    }

    @Test
    public void creoViajeConVehiculoNoDisponible(){
        Cliente c = Empresa.getInstance().getClientes().get("thiago");
        Pedido p = Empresa.getInstance().getPedidoDeCliente(c);

        Vehiculo v = Empresa.getInstance().getVehiculos().get("abc123");

        Chofer chofer = Empresa.getInstance().getChoferes().get("1234568");

        try {
            Empresa.getInstance().crearViaje(p, chofer, v);

            fail("no tiene que continuar la ejecucion");
        } catch (ChoferNoDisponibleException e) {
            fail("no tiene que tirar chofer no disponible");
        } catch (ClienteConViajePendienteException e) {
            fail("no tiene que tirar viaje pendiente");
        } catch (PedidoInexistenteException e) {
            fail("no tiene que tirar pedido inexistente");
        } catch (VehiculoNoDisponibleException e) {
            assertEquals("El mensaje de la excepcion no es correcto ", e.getMessage(), Mensajes.VEHICULO_NO_DISPONIBLE.getValor());
            assertEquals("la patente de la excepcion no es correcta ", e.getVehiculo().getPatente(), v.getPatente());
        } catch (VehiculoNoValidoException e) {
            fail("no tiene que tirar vehiculo no valido");
        }
    }
    @Test
    public void pagarYFinalizarViajeTest() {
        try {
            Empresa.getInstance().login("facundo","123");
            Cliente clienteLogueado = Empresa.getInstance().getClientes().get("facundo");
            Viaje v = Empresa.getInstance().getViajeDeCliente(clienteLogueado);
            Empresa.getInstance().pagarYFinalizarViaje(5);


            Assert.assertEquals("No se guardo bien la calificacion del viaje",v.getCalificacion(),5);
        } catch (ClienteSinViajePendienteException e) {
            fail("El cliente tiene un viaje pendiente de terminar");
        } catch (Exception e){
            fail("No deberia arrojar la excepcion " + e.getMessage());
        }
    }

    @Test
    public void pagarYFinalizarViajeClienteSinViajeTest() {
        try {
            Empresa.getInstance().login("facundo","123");
            Empresa.getInstance().pagarYFinalizarViaje(5);
            Empresa.getInstance().pagarYFinalizarViaje(5);

            fail("El metodo no deberia continuar");

        } catch (ClienteSinViajePendienteException e) {
            Assert.assertEquals("El mensaje esta mal",e.getMessage(), Mensajes.CLIENTE_SIN_VIAJE_PENDIENTE.getValor());
        } catch (Exception e){
            fail("No deberia arrojar la excepcion " + e.getMessage());
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
            Empresa.getInstance().login("facundo","123");
            Empresa.getInstance().pagarYFinalizarViaje(5);
            // Obtengo a chofer con su dni, (podría ser con el chofer del escenario directamente)
            Chofer chofer = Empresa.getInstance().getChoferes().get("1234567");
            double calificacion = Empresa.getInstance().calificacionDeChofer(chofer);
            // Me obliga a poner un delta
            assertEquals("No calcula bien el promedio", 5.0, calificacion, 0.01);
        } catch (SinViajesException ex) {
            fail("No debería haber fallado, el cliente tiene viaje");
        } catch (Exception ex) {
            fail("No debería tirar al excepción " + ex.getMessage());
        }
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
