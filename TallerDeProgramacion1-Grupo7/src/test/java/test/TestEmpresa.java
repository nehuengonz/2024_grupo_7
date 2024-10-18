package test;

import static org.junit.Assert.*;

import excepciones.*;
import modeloDatos.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloNegocio.Empresa;

import java.util.ArrayList;
import java.util.HashMap;

public class TestEmpresa {

    private Empresa empresa;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private Chofer chofer, choferSinViajes;
    private Pedido pedido1,pedido2;
    private Viaje viaje1, viaje2;

    @Before
    public void setUp() throws ClienteNoExisteException, ClienteConViajePendienteException, SinVehiculoParaPedidoException, ClienteConPedidoPendienteException, UsuarioYaExisteException, ChoferRepetidoException, VehiculoRepetidoException {
        empresa = Empresa.getInstance();

        cliente = new Cliente("Sofia1", "123456789", "Sofia Palladino");
        vehiculo = new Moto("AAA111");
        chofer = new ChoferTemporario("1111111", "Carlos P");
        choferSinViajes = new ChoferTemporario("222222", "Laura E");
        empresa.agregarCliente("Sofia1", "123456789", "Sofia Palladino");

        empresa.agregarChofer(chofer);
        empresa.agregarChofer(choferSinViajes);
        empresa.agregarVehiculo(vehiculo);

        pedido1 = new Pedido(cliente, 2, false, false, 10, "ZONA_SEGURA");
        pedido2 = new Pedido(cliente, 1, false, false, 5, "ZONA_SEGURA");

        empresa.agregarPedido(pedido1);

        viaje1 = new Viaje(pedido1, chofer, vehiculo);
        empresa.getViajesIniciados().put(cliente,viaje1);


    }


    @After
    public void tearDown() {
        empresa.getClientes().clear();
        empresa.getChoferes().clear();
        empresa.getVehiculos().clear();
        empresa.getPedidos().clear();
    }

    @Test
    public void testAgregarClienteExitoso() {
        assertNotNull(empresa.getClientes().get("Sofia1"));
        assertEquals("Sofia Palladino", empresa.getClientes().get("Sofia1").getNombreReal());
    }

    @Test
    public void testAgregarClienteRepetido() {
        try {
            empresa.agregarCliente("Sofia1", "123456789", "Sofia Palladino");
            fail("Se esperaba una excepción UsuarioYaExisteException");
        } catch (UsuarioYaExisteException e) {
            assertEquals("El usuario Sofia1 ya existe", e.getMessage());
        }
    }

    @Test
    public void testAgregarPedidoExitoso() {
        Cliente cliente = empresa.getClientes().get("Sofia1");

        Pedido pedido = new Pedido(cliente, 1, false, false, 10, "ZONA_PELIGROSA");

        try {
            empresa.agregarPedido(pedido);

            assertTrue("El pedido no se encontró en la lista de pedidos", empresa.getPedidos().containsKey(cliente));
            assertEquals("El pedido agregado no es el esperado", pedido, empresa.getPedidos().get(cliente));

        } catch (ClienteNoExisteException e) {
            fail("Se esperaba que el cliente existiera, pero ocurrió una excepción: " + e.getMessage());
        } catch (ClienteConViajePendienteException e) {
            fail("Se esperaba que el pedido se agregara, pero el cliente tiene un viaje pendiente: " + e.getMessage());
        } catch (SinVehiculoParaPedidoException e) {
            fail("Se esperaba que el pedido se agregara, pero no hay vehículo disponible: " + e.getMessage());
        } catch (ClienteConPedidoPendienteException e) {
            fail("Se esperaba que el pedido se agregara, pero el cliente ya tiene un pedido pendiente: " + e.getMessage());
        }
    }


    @Test
    public void testAgregarPedidoNoCumple() {
        Cliente cliente = empresa.getClientes().get("Sofia1");
        Pedido pedido = new Pedido(cliente, 5, true, true, 10, "ZONA_PELIGROSA");
        try {
            empresa.agregarPedido(pedido);
            assertTrue("El pedido no se encontró en la lista de pedidos", empresa.getPedidos().containsKey(cliente));
            assertEquals("El pedido agregado no es el esperado", pedido, empresa.getPedidos().get(cliente));
        } catch (ClienteNoExisteException e) {
            fail("Se esperaba que el cliente existiera, pero ocurrió una excepción: " + e.getMessage());
        } catch (ClienteConViajePendienteException e) {
            fail("Se esperaba que el pedido se agregara, pero el cliente tiene un viaje pendiente: " + e.getMessage());
        } catch (SinVehiculoParaPedidoException e) {
            fail("Se esperaba que el pedido se agregara, pero no hay vehículo disponible: " + e.getMessage());
        } catch (ClienteConPedidoPendienteException e) {
            fail("Se esperaba que el pedido se agregara, pero el cliente ya tiene un pedido pendiente: " + e.getMessage());
        }
    }

    @Test
    public void testAgregarChoferExitoso() {
        Chofer nuevoChofer = new ChoferTemporario("7777777", "Nuevo Chofer");
        try {
            empresa.agregarChofer(nuevoChofer);
            assertEquals("El DNI del chofer agregado no es el esperado", "7777777", empresa.getChoferes().get("7777777").getDni());
        } catch (ChoferRepetidoException e) {
            fail("Se esperaba que el chofer se agregara exitosamente, pero ocurrió una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testAgregarChoferDuplicado() {
        try {
            empresa.agregarChofer(chofer); // Ya fue agregado en el setup
            fail("Se esperaba una excepción ChoferRepetidoException");
        } catch (ChoferRepetidoException e) {
            assertEquals("El chofer con DNI 111111 ya está registrado", e.getMessage());
        }
    }

    @Test
    public void testAgregarVehiculoExitoso() {
        Vehiculo nuevoVehiculo = new Moto("XYZ789");
        try {
            empresa.agregarVehiculo(nuevoVehiculo);
            assertNotNull("El vehículo no debe ser nulo después de ser agregado", empresa.getVehiculos().get("XYZ789"));
            assertEquals("La patente del vehículo agregado no es la esperada", "XYZ789", empresa.getVehiculos().get("XYZ789").getPatente());
        } catch (VehiculoRepetidoException e) {
            fail("Se esperaba que el vehículo se agregara exitosamente, pero ocurrió una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testAgregarVehiculoDuplicado() {
        try {
            empresa.agregarVehiculo(vehiculo); // Ya fue agregado en el setup
            fail("Se esperaba una excepción VehiculoRepetidoException");
        } catch (VehiculoRepetidoException e) {
            assertEquals("El vehiculo con patente AAA111 ya está registrado", e.getMessage());
        }
    }

    @Test
    public void testPagarYFinalizarViajeExistente() {
        try {
            empresa.pagarYFinalizarViaje(5);
        } catch (ClienteSinViajePendienteException e) {
            fail("No se esperaba una excepcion: " + e.getMessage());
        }

        Viaje viajeTerminando = empresa.getViajesIniciados().get(cliente);
        assertNull("Se esperaba que el viaje del cliente fuera null después de finalizar", viajeTerminando);
        assertTrue("El viaje terminado no se encuentra en la lista de viajes terminados", empresa.getViajesTerminados().contains(viaje1));
    }

    @Test
    public void testPagarYFinalizarViajeNoExistente() {
        try {
            empresa.pagarYFinalizarViaje(5);
            fail("Se esperaba una excepción al intentar finalizar un viaje que no existe"); // Este fail se ejecutará si no se lanza una excepción
        } catch (Exception e) {
            assertEquals("Se esperaba una excepción al intentar finalizar un viaje que no existe",
                    "El viaje no existe o ya ha sido finalizado", e.getMessage());
        }
    }

    @Test
    public void testObtenerClientes() {
        HashMap<String, Cliente> clientes = empresa.getClientes();
        assertNotNull(clientes);
        assertTrue(clientes.containsKey("Sofia1"));
    }

    @Test
    public void testCalificacionDeChoferSinViajes(){
        try {
             empresa.calificacionDeChofer(choferSinViajes);
             fail("Se esperaba que se lanzara una excepción SinViajesException");
        } catch (SinViajesException e) {
            assertNotNull("La excepción SinViajesException debería haber sido lanzada", e);
            assertEquals("El chofer no tiene viajes registrados", e.getMessage());
        }
    }



}
