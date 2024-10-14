package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import excepciones.ChoferRepetidoException;
import excepciones.ClienteConPedidoPendienteException;
import excepciones.ClienteConViajePendienteException;
import excepciones.ClienteNoExisteException;
import excepciones.SinVehiculoParaPedidoException;
import excepciones.UsuarioYaExisteException;
import excepciones.VehiculoRepetidoException;
import modeloDatos.Auto;
import modeloDatos.ChoferPermanente;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloNegocio.Empresa;
import util.Constantes;

public class EmpresaTest {
	
	private static EscenarioEmpresa escenario;
	
	private static Empresa empresa;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		empresa = Empresa.getInstance();
		escenario = new EscenarioEmpresa(empresa);
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		empresa.getChoferes().clear();
		empresa.getClientes().clear();
		empresa.getVehiculos().clear();
		empresa.getPedidos().clear();
	}

	@Test
	public void testAgregarChoferPermanenteNuevo() {
		ChoferPermanente ch1 = new ChoferPermanente("Pablo", "23593103", 2010, 3);
		try {
			empresa.agregarChofer(ch1);
		} catch (ChoferRepetidoException e) {
			fail("No debería haber tirado la excepción " + e.getMessage());
		} catch (Exception e) {
			fail("Crashea por una excepción que no es específica");
		}
		
	}
	
	@Test
	public void testAgregarChoferPermananteExistente() {
		ChoferPermanente ch1 = new ChoferPermanente("Pablo", "23593103", 2010, 3);
		ChoferPermanente ch2 = new ChoferPermanente("Hernan", "23593103", 2010, 3);
		try {
			empresa.agregarChofer(ch1);
			empresa.agregarChofer(ch2);
			fail("Debería haber tirado la excepción ChoferRepetidoException");
		} catch (ChoferRepetidoException e) {
			e.getMessage();
		} catch (Exception e) {
			fail("Crashea por una excepción que no es específica");
		}
	}
	@Test
	public void testAgregarChoferTemporarioNuevo() {
		ChoferTemporario ch1 = new ChoferTemporario("Pablo", "23593103");
		try {
			empresa.agregarChofer(ch1);
		} catch (ChoferRepetidoException e) {
			fail("No debería haber tirado la excepción " + e.getMessage());
		} catch (Exception e) {
			fail("Crashea por una excepción que no es específica");
		}
		
	}
	@Test
	public void testAgregarChoferTemporarioExistente() {
		ChoferTemporario ch1 = new ChoferTemporario("Pablo", "23593103");
		ChoferTemporario ch2 = new ChoferTemporario("Hernan", "23593103");
		try {
			empresa.agregarChofer(ch1);
			empresa.agregarChofer(ch2);
			fail("Debería haber tirado la excepción ChoferRepetidoException");
		} catch (ChoferRepetidoException e) {
			// Excepción esperada el test pasa
		} catch (Exception e) {
			fail("Crashea por una excepción que no es específica");
		}
	}
	@Test
	public void testAgregarClienteNuevo() {
		try {
			empresa.agregarCliente("usuarioNuevo", "password", "Pedro Alfonso");
		} catch(UsuarioYaExisteException e) {
			fail("No debería haber tirado " + e.getMessage());
		} catch (Exception e) {
			fail("Crashea por una excepción que no es específica");
		}
	}
	@Test
	public void testAgregarClienteExistente() {
		try {
			empresa.agregarCliente("usuarioNuevo", "password", "Pedro Alfonso");
			empresa.agregarCliente("usuarioNuevo", "password", "Pedro Alfonso");
			fail("Debería haber tirado UsuarioYaExisteException");
		} catch(UsuarioYaExisteException e) {
			// Excepción esperada el test pasa
		} catch (Exception e) {
			fail("Crashea por una excepción que no es específica");
		}
	}
	@Test
	public void testAgregarClienteDatosUnCaracter() {
		try {
			empresa.agregarCliente("u", "p", "g");
		} catch(UsuarioYaExisteException e) {
			fail("No debería haber tirado " + e.getMessage());
		} catch (Exception e) {
			fail("Crashea por una excepción que no se específica");
		}
	} 
	@Test
	public void testAgregarPedidoValido() {
		try {
			escenario.setearEscenario1();
			System.out.println(empresa.getClientes().containsKey("usuarioNuevo"));
			Cliente c = new Cliente("usuarioNuevo", "password", "Pedro Alfonso");
			Pedido p = new Pedido(c, 1, true, true, 2, Constantes.ZONA_PELIGROSA);
			empresa.agregarPedido(p);
		} catch (UsuarioYaExisteException e) {
			fail("No debería haber tirado " + e.getMessage());
		} catch (SinVehiculoParaPedidoException e) {
			fail("No debería haber tirado " + e.getMessage());
		} catch (ClienteConViajePendienteException e) {
			fail("No debería haber tirado " + e.getMessage());
		} catch (ClienteConPedidoPendienteException e) {
			fail("No debería haber tirado " + e.getMessage());
		} catch (ClienteNoExisteException e) {
			fail("No debería haber tirado " + e.getMessage());
		} catch (VehiculoRepetidoException e) {
			fail("No debería haber tirado " + e.getMessage());
		}
	}
	@Test
	public void testAgregarPedidoEmpresaNoCumple() {
		try {
			escenario.setearEscenario1();
			Cliente c = new Cliente("usuarioNuevo", "password", "Pedro Alfonso");
			Pedido p = new Pedido(c, 1, true, true, 10, Constantes.ZONA_PELIGROSA);
			empresa.agregarPedido(p);
			fail("Debería haber lanzado excepción SinVehiculoParaPedidoException");
		} catch (UsuarioYaExisteException e) {
			fail("No debería haber tirado " + e.getMessage());
		} catch (SinVehiculoParaPedidoException e) {
			// Debe ir por aquí
		} catch (ClienteConViajePendienteException e) {
			fail("No debería haber tirado " + e.getMessage());
		} catch (ClienteConPedidoPendienteException e) {
			fail("No debería haber tirado " + e.getMessage());
		} catch (ClienteNoExisteException e) {
			fail("No debería haber tirado " + e.getMessage());
		} catch (VehiculoRepetidoException e) {
			fail("No debería haber tirado " + e.getMessage());
		}
	}
}
