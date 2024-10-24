package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistencia.EmpresaDTO;
import modeloDatos.*;
import util.Constantes;

import java.util.ArrayList;
import java.util.HashMap;

public class TestEmpresaDTO {

    private EmpresaDTO empresa;

    @Before
    public void setUp() {
        empresa = new EmpresaDTO();
    }

    @After
    public void tearDown() throws Exception {
        empresa = null;
    }

    @Test
    public void testSetYGetClientes() {
        HashMap<String, Cliente> clientes = new HashMap<>();
        Cliente cliente1 = new Cliente("Sofia123", "159753", "Sofia Erreguerena");
        clientes.put(cliente1.getNombreUsuario(), cliente1);

        empresa.setClientes(clientes);
        assertEquals(clientes, empresa.getClientes());
        assertEquals(cliente1, empresa.getClientes().get("Sofia123"));
    }

    @Test
    public void testSetYGetChoferesDesocupados() {
        ArrayList<Chofer> choferes = new ArrayList<>();
        Chofer chofer1 = new ChoferTemporario("14002456", "Facundo Delgado");
        choferes.add(chofer1);
        empresa.setChoferesDesocupados(choferes);
        assertEquals(choferes, empresa.getChoferesDesocupados());
        assertTrue(empresa.getChoferesDesocupados().contains(chofer1));
    }

    @Test
    public void testSetYGetVehiculosDesocupados() {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        Vehiculo vehiculo1 = new Auto("789", 4, true);
        vehiculos.add(vehiculo1);

        empresa.setVehiculosDesocupados(vehiculos);
        assertEquals(vehiculos, empresa.getVehiculosDesocupados());
        assertTrue(empresa.getVehiculosDesocupados().contains(vehiculo1));
    }

    @Test
    public void testSetAndGetPedidos() {
        HashMap<Cliente, Pedido> pedidos = new HashMap<>();
        Cliente cliente = new Cliente("JohnSnow", "123456", "Juan Nieves");
        Pedido pedido = new Pedido(cliente, 2, true, false, 10, Constantes.ZONA_STANDARD);
        pedidos.put(cliente, pedido);

        empresa.setPedidos(pedidos);
        assertEquals(pedidos, empresa.getPedidos());
        assertEquals(pedido, empresa.getPedidos().get(cliente));
    }

    @Test
    public void testSetAndGetViajesIniciados() {
        HashMap<Cliente, Viaje> viajesIniciados = new HashMap<>();
        Cliente cliente = new Cliente("JohnSnow", "123456", "Juan Nieves");
        Chofer chofer = new ChoferTemporario("14002456", "Facundo Delgado");
        Vehiculo vehiculo = new Auto("789", 4, true);
        Pedido pedido = new Pedido(cliente, 2, true, false, 10, Constantes.ZONA_STANDARD);
        Viaje viaje = new Viaje(pedido, chofer, vehiculo);

        viajesIniciados.put(cliente, viaje);

        empresa.setViajesIniciados(viajesIniciados);
        assertEquals(viajesIniciados, empresa.getViajesIniciados());
        assertEquals(viaje, empresa.getViajesIniciados().get(cliente));
    }

    @Test
    public void testSetAndGetViajesTerminados() {
        ArrayList<Viaje> viajesTerminados = new ArrayList<>();
        Cliente cliente = new Cliente("JohnSnow", "123456", "Juan Nieves");
        Chofer chofer = new ChoferTemporario("14002456", "Facundo Delgado");
        Vehiculo vehiculo = new Auto("789", 4, true);
        Pedido pedido = new Pedido(cliente, 2, true, false, 10, Constantes.ZONA_STANDARD);
        Viaje viaje = new Viaje(pedido, chofer, vehiculo);

        viajesTerminados.add(viaje);

        empresa.setViajesTerminados(viajesTerminados);
        assertEquals(viajesTerminados, empresa.getViajesTerminados());
        assertTrue(empresa.getViajesTerminados().contains(viaje));
    }

    @Test
    public void testSetAndGetUsuarioLogeado() {
        Usuario usuario = new Cliente("JohnSnow", "54321", "Wade Wilson");

        empresa.setUsuarioLogeado(usuario);
        assertEquals(usuario, empresa.getUsuarioLogeado());
    }
}
