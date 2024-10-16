package modelo_negocio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import excepciones.ChoferNoDisponibleException;
import excepciones.ClienteConViajePendienteException;
import excepciones.PedidoInexistenteException;
import excepciones.VehiculoNoDisponibleException;
import excepciones.VehiculoNoValidoException;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloNegocio.Empresa;
import test.EscenarioConViaje;
import util.Constantes;

public class TestEmpresa_CrearViaje {
	private EscenarioConViaje Escenario0=new EscenarioConViaje();
	private Empresa empresa;
	 Pedido pedido;
	 Chofer chofer;
	 Vehiculo vehiculo;
	 Cliente cliente;
	 //Pedido
    private static final int cantPasajeros=5;
    private static final boolean mascota=true;
    private static final boolean baul=true;
    private static final int km=100;
    private static final String zona=Constantes.ZONA_PELIGROSA;
    //chofer
    private static final int anioInicio=2015;
    private static final int CantHijos=0;
    //vehiculo
    private static final int CantPlazas=3;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Escenario0.setUp();
	}

	@After
	public void tearDown() throws Exception {
		Escenario0.tearDown();
	}

	//Se lanza si el pedido pasado como parametro no pertence al HashMap de pedidos
	@Test
	public void test_PedidoInexistenteException() {
	    // Instanciamos la empresa (si no es singleton, crea la instancia)
	    empresa =  empresa.getInstance();

	    // Configuramos los objetos necesarios
	    cliente = new Cliente("a", "a", "a");
	    pedido = new Pedido(cliente, cantPasajeros, mascota, baul, km, zona);
	    chofer = new ChoferPermanente("a", "a", anioInicio, CantHijos);
	    vehiculo = new Auto("patente", CantPlazas, mascota);
	    
	    try {
	        // Creamos un nuevo pedido que no ha sido agregado al HashMap de la empresa
	        Pedido pedidoInexistente = new Pedido(new Cliente("b", "b", "b"), 3, false, true, 20, Constantes.ZONA_STANDARD);

	        // Intentamos crear un viaje con el pedido inexistente, lo que debería lanzar PedidoInexistenteException
	        empresa.crearViaje(pedidoInexistente, chofer, vehiculo);

	        // Si llegamos aquí, significa que la excepción no fue lanzada, entonces fallamos el test
	        fail("Se esperaba la excepción PedidoInexistenteException pero no fue lanzada.");
	        
	    } catch (PedidoInexistenteException e) {
	        assertEquals("El pedido no existe en el sistema", e.getMessage());
	    }catch (ClienteConViajePendienteException e) {
	        fail("No se esperaba ClienteConViajePendienteException en esta prueba.");
	    }catch(VehiculoNoValidoException e) {
	    	fail("No se esperaba VehiculoNoValidoException en esta prueba.");
	    }catch(VehiculoNoDisponibleException e) {
	    	fail("No se esperaba VehiculoNoDisponibleException en esta prueba.");
	    }catch(ChoferNoDisponibleException e) {
	    	fail("No se esperaba ChoferNoDisponibleException en esta prueba.");
	    }
	}
	// Se lanza si el chofer no pertenece al ArrayList de choferesDisponibles
	@Test
    public void test_ChoferNoDisponibleException() {
        empresa = Empresa.getInstance();

        cliente = new Cliente("a", "a", "a");
        pedido = new Pedido(cliente, cantPasajeros, mascota, baul, km, zona);
        vehiculo = new Auto("patente", CantPlazas, mascota);

        try {
            Chofer choferInexistente = new ChoferPermanente("x", "x", anioInicio, CantHijos);

            empresa.crearViaje(pedido, choferInexistente, vehiculo);

            fail("Se esperaba la excepción ChoferNoDisponibleException pero no fue lanzada.");
        } catch (ChoferNoDisponibleException e) {
            assertEquals("El chofer no está disponible", e.getMessage());
        } catch (PedidoInexistenteException | VehiculoNoDisponibleException | VehiculoNoValidoException | ClienteConViajePendienteException e) {
            fail("No se esperaba ninguna otra excepción en esta prueba.");
        }
    }
	//Se lanza si el vehiculo no pertenece al ArrayList de vehiculosDisponibles
	@Test
    public void test_VehiculoNoDisponibleException() {
        empresa = Empresa.getInstance();

        cliente = new Cliente("a", "a", "a");
        pedido = new Pedido(cliente, cantPasajeros, mascota, baul, km, zona);
        chofer = new ChoferPermanente("a", "a", anioInicio, CantHijos);

        try {
            Vehiculo vehiculoInexistente = new Auto("otraPatente", CantPlazas, mascota);

            empresa.crearViaje(pedido, chofer, vehiculoInexistente);

            fail("Se esperaba la excepción VehiculoNoDisponibleException pero no fue lanzada.");
        } catch (VehiculoNoDisponibleException e) {
            assertEquals("El vehículo no está disponible", e.getMessage());
        } catch (PedidoInexistenteException | ChoferNoDisponibleException | VehiculoNoValidoException | ClienteConViajePendienteException e) {
            fail("No se esperaba ninguna otra excepción en esta prueba.");
        }
    }
	//Se lanza si el vehiculo no puede satisfacer el pedido
	@Test
    public void test_VehiculoNoValidoException() {
        empresa = Empresa.getInstance();

        cliente = new Cliente("a", "a", "a");
        pedido = new Pedido(cliente, cantPasajeros, mascota, baul, km, zona);
        chofer = new ChoferPermanente("a", "a", anioInicio, CantHijos);

        try {
            Vehiculo vehiculoInvalido = new Auto("patente", 1, false); // El auto no cumple con los requisitos del pedido

            empresa.crearViaje(pedido, chofer, vehiculoInvalido);

            fail("Se esperaba la excepción VehiculoNoValidoException pero no fue lanzada.");
        } catch (VehiculoNoValidoException e) {
            assertEquals("El vehículo no es adecuado para este pedido", e.getMessage());
        } catch (PedidoInexistenteException | ChoferNoDisponibleException | VehiculoNoDisponibleException | ClienteConViajePendienteException e) {
            fail("No se esperaba ninguna otra excepción en esta prueba.");
        }
    }
	//Se lanza si el Cliente esta realizando un Viaje
	@Test
    public void test_ClienteConViajePendienteException() {
        empresa = Empresa.getInstance();

        cliente = new Cliente("a", "a", "a");
        pedido = new Pedido(cliente, cantPasajeros, mascota, baul, km, zona);
        chofer = new ChoferPermanente("a", "a", anioInicio, CantHijos);
        vehiculo = new Auto("patente", CantPlazas, mascota);

        //como marco que un cliente esta realizando un viaje????

        try {
            empresa.crearViaje(pedido, chofer, vehiculo);

            fail("Se esperaba la excepción ClienteConViajePendienteException pero no fue lanzada.");
        } catch (ClienteConViajePendienteException e) {
            assertEquals("El cliente ya tiene un viaje pendiente", e.getMessage());
        } catch (PedidoInexistenteException | ChoferNoDisponibleException | VehiculoNoDisponibleException | VehiculoNoValidoException e) {
            fail("No se esperaba ninguna otra excepción en esta prueba.");
        }
    }
	
}
