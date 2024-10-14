package test;

import static org.junit.Assert.*;

import excepciones.*;
import modeloDatos.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloNegocio.Empresa;

import java.util.HashMap;

public class TestEmpresa {

    private Empresa empresa;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private Chofer chofer;

    @Before
    public void setUp() throws UsuarioYaExisteException, ChoferRepetidoException, VehiculoRepetidoException {
        empresa = Empresa.getInstance();

        cliente = new Cliente("Sofia1", "123456789", "Sofia Palladino");
        vehiculo = new Moto("AAA111");
        chofer = new ChoferTemporario("1111111", "Carlos P");

        empresa.agregarCliente("Sofia1", "123456789", "Sofia Palladino");
        empresa.agregarChofer(chofer);
        empresa.agregarVehiculo(vehiculo);
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
            assertEquals("El usuario Sofia1 ya existe.", e.getMessage());
        }
    }

    @Test
    public void testAgregarPedidoExitoso() throws ClienteNoExisteException, ClienteConViajePendienteException, SinVehiculoParaPedidoException, ClienteConPedidoPendienteException, UsuarioYaExisteException {
        Cliente cliente = empresa.getClientes().get("Sofia1");

        Pedido pedido = new Pedido(cliente, 1, false, false, 10, "ZONA_PELIGROSA");

        empresa.agregarPedido(pedido);

        assertTrue(empresa.getPedidos().containsKey(cliente));
        assertEquals(pedido, empresa.getPedidos().get(cliente));
    }

    @Test
    public void testAgregarPedidoNoCumple() throws ClienteNoExisteException, ClienteConViajePendienteException, SinVehiculoParaPedidoException, ClienteConPedidoPendienteException, UsuarioYaExisteException {
        Cliente cliente = empresa.getClientes().get("Sofia1");

        Pedido pedido = new Pedido(cliente, 5, true, true, 10, "ZONA_PELIGROSA");

        empresa.agregarPedido(pedido);

        assertTrue(empresa.getPedidos().containsKey(cliente));
        assertEquals(pedido, empresa.getPedidos().get(cliente));
    }


    @Test
    public void testAgregarChoferExitoso() throws ChoferRepetidoException {
        Chofer nuevoChofer = new ChoferTemporario("7777777", "Nuevo Chofer");
        empresa.agregarChofer(nuevoChofer);

        assertNotNull(empresa.getChoferes().get("7777777"));
        assertEquals("7777777", empresa.getChoferes().get("7777777").getDni());
    }

    @Test
    public void testAgregarChoferDuplicado() {
        try {
            empresa.agregarChofer(chofer); // Ya fue agregado en el setup
            fail("Se esperaba una excepción ChoferRepetidoException");
        } catch (ChoferRepetidoException e) {
            assertEquals("El chofer con DNI 111111 ya está registrado.", e.getMessage());
        }
    }

    @Test
    public void testAgregarVehiculoExitoso() throws VehiculoRepetidoException {
        Vehiculo nuevoVehiculo = new Moto("XYZ789");
        empresa.agregarVehiculo(nuevoVehiculo);
        assertNotNull(empresa.getVehiculos().get("XYZ789"));
        assertEquals("XYZ789", empresa.getVehiculos().get("XYZ789").getPatente());
    }

    @Test
    public void testAgregarVehiculoDuplicado() {
        try {
            empresa.agregarVehiculo(vehiculo); // Ya fue agregado en el setup
            fail("Se esperaba una excepción VehiculoRepetidoException");
        } catch (VehiculoRepetidoException e) {
            assertEquals("El vehículo con patente AAA111 ya está registrado.", e.getMessage());
        }
    }

    @Test
    public void testObtenerClientes() {
        HashMap<String, Cliente> clientes = empresa.getClientes();
        assertNotNull(clientes);
        assertTrue(clientes.containsKey("Sofia1"));
    }
}
