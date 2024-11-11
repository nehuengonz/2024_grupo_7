package modelo_negocio;

import static org.junit.Assert.assertEquals;
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
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloNegocio.Empresa;
import util.Constantes;

public class TestEmpresa_CrearViaje_escenario4 {
	private Escenario4 Escenario0=new Escenario4();
	 Pedido pedido;
	 Chofer chofer;
	 Vehiculo vehiculo;
	 Cliente cliente;
	
    //vehiculo
    private static final int CantPlazas=2;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Escenario0.setup();
	}

	@After
	public void tearDown() throws Exception {
		Escenario0.tearDown();
	}

	//Se lanza si el pedido pasado como parametro no pertence al HashMap de pedidos
	@Test
	public void test_PedidoInexistenteException() {
	    // Configuramos los objetos necesarios
		cliente = Empresa.getInstance().getClientes().get("facundo");
	    pedido = Empresa.getInstance().getPedidoDeCliente(cliente);
	    vehiculo = Empresa.getInstance().getVehiculos().get("abc123");
        chofer=Empresa.getInstance().getChoferes().get("1234567");

	    try {
	        // Creamos un nuevo pedido que no ha sido agregado al HashMap de la empresa
	        Pedido pedidoInexistente = new Pedido(new Cliente("c", "c", "c"), 3, false, true, 20, Constantes.ZONA_STANDARD);
	        // Intentamos crear un viaje con el pedido inexistente, lo que debería lanzar PedidoInexistenteException
	        Empresa.getInstance().crearViaje(pedidoInexistente, chofer, vehiculo);
	        // Si llegamos aquí, significa que la excepción no fue lanzada, entonces fallamos el test
	        fail("Se esperaba la excepción PedidoInexistenteException pero no fue lanzada.");        
	    } catch (PedidoInexistenteException e) {
	        assertEquals("El Pedido no figura en la lista", e.getMessage());
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
    

        cliente = Empresa.getInstance().getClientes().get("facundo");
        pedido = Empresa.getInstance().getPedidoDeCliente(cliente);
        vehiculo = Empresa.getInstance().getVehiculos().get("abc123");
        chofer=Empresa.getInstance().getChoferes().get("1234567");
        try {
            //Chofer choferInexistente = new ChoferPermanente("x", "x", anioInicio, CantHijos);

            Empresa.getInstance().crearViaje(pedido, chofer, vehiculo);
           
            fail("Se esperaba la excepción ChoferNoDisponibleException pero no fue lanzada.");
        } catch (ChoferNoDisponibleException e) {
            assertEquals("El chofer no está disponible", e.getMessage());
        }catch (ClienteConViajePendienteException e) {
	        fail("No se esperaba ClienteConViajePendienteException en esta prueba.");
	    }catch(VehiculoNoValidoException e) {
	    	fail("No se esperaba VehiculoNoValidoException en esta prueba.");
	    }catch(VehiculoNoDisponibleException e) {
	    	fail("No se esperaba VehiculoNoDisponibleException en esta prueba.");
	    }catch (PedidoInexistenteException e) {
	    	fail("No se esperaba PedidoInexistenteException en esta prueba.");
	    }
    }
	//Se lanza si el vehiculo no pertenece al ArrayList de vehiculosDisponibles
	@Test
    public void test_VehiculoNoDisponibleException() {
        

		  cliente = Empresa.getInstance().getClientes().get("facundo");
	      pedido = Empresa.getInstance().getPedidoDeCliente(cliente);
	      vehiculo = Empresa.getInstance().getVehiculos().get("abc123");
	      chofer=Empresa.getInstance().getChoferes().get("1234567");
        try {
            Vehiculo vehiculoInexistente = new Auto("otraPatente", CantPlazas, true);

            Empresa.getInstance().crearViaje(pedido, chofer, vehiculoInexistente);

            fail("Se esperaba la excepción VehiculoNoDisponibleException pero no fue lanzada.");
        } catch (VehiculoNoDisponibleException e) {
            assertEquals("El vehiculo no esta disponible", e.getMessage());
        }catch (ChoferNoDisponibleException e) {
        	fail("No se esperaba ChoferNoDisponibleException en esta prueba.");
        }catch (ClienteConViajePendienteException e) {
	        fail("No se esperaba ClienteConViajePendienteException en esta prueba.");
	    }catch(VehiculoNoValidoException e) {
	    	fail("No se esperaba VehiculoNoValidoException en esta prueba.");
	    }catch (PedidoInexistenteException e) {
	    	fail("No se esperaba PedidoInexistenteException en esta prueba.");
	    }
    }
	//Se lanza si el vehiculo no puede satisfacer el pedido
	@Test
    public void test_VehiculoNoValidoException() {
       

		  cliente = Empresa.getInstance().getClientes().get("facundo");
	      pedido = Empresa.getInstance().getPedidoDeCliente(cliente);
	      vehiculo = Empresa.getInstance().getVehiculos().get("abc123");
	      chofer=Empresa.getInstance().getChoferes().get("1234567");
        try {
            Vehiculo vehiculoInvalido = new Auto("patente", 2, false); // El auto no cumple con los requisitos del pedido

            Empresa.getInstance().crearViaje(pedido, chofer, vehiculoInvalido);

            fail("Se esperaba la excepción VehiculoNoValidoException pero no fue lanzada.");
        } catch (VehiculoNoValidoException e) {
            assertEquals("El vehículo no es adecuado para este pedido", e.getMessage());
        }  catch (VehiculoNoDisponibleException e) {
        	fail("No se esperaba VehiculoNoDisponibleException en esta prueba.");
        }catch (ChoferNoDisponibleException e) {
        	fail("No se esperaba ChoferNoDisponibleException en esta prueba.");
        }catch (ClienteConViajePendienteException e) {
	        fail("No se esperaba ClienteConViajePendienteException en esta prueba.");
	    }catch (PedidoInexistenteException e) {
	    	fail("No se esperaba PedidoInexistenteException en esta prueba.");
	    }
    }
	//Se lanza si el Cliente esta realizando un Viaje
	@Test
    public void test_ClienteConViajePendienteException() {
    
		cliente = Empresa.getInstance().getClientes().get("facundo");
	    pedido = Empresa.getInstance().getPedidoDeCliente(cliente);
	    vehiculo = Empresa.getInstance().getVehiculos().get("abc123");
        chofer=Empresa.getInstance().getChoferes().get("1234567");
        try {
        	//Empresa.getInstance().crearViaje(pedido, chofer, vehiculo);
        	Empresa.getInstance().crearViaje(pedido,chofer,vehiculo);
            fail("Se esperaba la excepción ClienteConViajePendienteException pero no fue lanzada.");
        } catch (ClienteConViajePendienteException e) {
            assertEquals("El cliente ya tiene un viaje pendiente", e.getMessage());
        }  catch (VehiculoNoValidoException e) {
        	fail("No se esperaba VehiculoNoValidoException en esta prueba.");
        }  catch (VehiculoNoDisponibleException e) {
        	fail("No se esperaba VehiculoNoDisponibleException en esta prueba.");
        }catch (ChoferNoDisponibleException e) {
        	fail("No se esperaba ChoferNoDisponibleException en esta prueba.");
	    }catch (PedidoInexistenteException e) {
	    	fail("No se esperaba PedidoInexistenteException en esta prueba.");
	    }
    }
}
