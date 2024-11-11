package test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.event.ActionEvent;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import modeloDatos.Cliente;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import modelo_negocio.Escenario6;
import util.Constantes;
import util.Mensajes;
import vista.DefaultOptionPane;
import vista.IOptionPane;
import vista.IVista;
import vista.Ventana;

public class IntegracionTest {
	
	private IVista vista;
    private Controlador controlador;
    private IOptionPane ventanaErrores;
    Escenario6 escenario6;
    
    public IntegracionTest() {
    }

	@Before
	public void setUp() throws Exception {
		vista = mock(Ventana.class);
		ventanaErrores = mock(DefaultOptionPane.class);
		when(vista.getOptionPane()).thenReturn(ventanaErrores);
		
        controlador = new Controlador();
        controlador.setVista(vista);
        
        escenario6 = new Escenario6();
        escenario6.setup();
	}

	@After
	public void tearDown() throws Exception {
		controlador = null;
        escenario6.teardown();
	}

	@Test
	public void test_clienteNuevoPedidoSinVehiculoDisponible() {
		try {
			// ** Inicio de sesion **
			when(vista.getPassword()).thenReturn("321");
	        when(vista.getUsserName()).thenReturn("thiago");
	        
	        controlador.actionPerformed(new ActionEvent(this, 1, Constantes.LOGIN));
	        
	        Assert.assertNotNull("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado());
	        Assert.assertEquals("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado().getNombreUsuario(), "thiago");
	        Assert.assertEquals("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado().getPass(), "321");
	        
	        // *** Intento de pedido que falla ***
	        when(vista.getCantidadPax()).thenReturn(3);
	        when(vista.isPedidoConMascota()).thenReturn(true);
	        when(vista.isPedidoConBaul()).thenReturn(true);
	        when(vista.getCantKm()).thenReturn(5);
	        when(vista.getTipoZona()).thenReturn(Constantes.ZONA_STANDARD);
	       
	        controlador.actionPerformed(new ActionEvent(this, 2, Constantes.NUEVO_PEDIDO));
	        
	        // Verificar que se muestra el mensaje de error
	        verify(ventanaErrores).ShowMessage(Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor());
	        
	        Cliente cliente = (Cliente) Empresa.getInstance().getClientes().get("thiago");
		    Assert.assertNull("No debería haberse creado un pedido sin disponibilidad",
		    		Empresa.getInstance().getPedidoDeCliente(cliente));
	      
		    // *** Deslogueo del cliente ***
	        controlador.actionPerformed(new ActionEvent(this,3,Constantes.CERRAR_SESION_CLIENTE));

	        Assert.assertNull("No se deslogueo al usuario", Empresa.getInstance().getUsuarioLogeado());
	       
		} catch (Exception e) {
			Assert.fail("Excepcion inesperada: " + e.getMessage());
		}
	}
	
	@Test
	public void test_clienteNuevoPedidoFinalizadoYCalificado() {
		try {
			// *** Inicio de sesion ***
			when(vista.getPassword()).thenReturn("123");
	        when(vista.getUsserName()).thenReturn("facundo");
	        controlador.actionPerformed(new ActionEvent(this, 1, Constantes.LOGIN));
	        
	        Assert.assertNotNull("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado());
	        Assert.assertEquals("Usuario logueado incorrectamente", 
	        		Empresa.getInstance().getUsuarioLogeado().getNombreUsuario(), "facundo");
	        Assert.assertEquals("Usuario logueado incorrectamente", 
	        		Empresa.getInstance().getUsuarioLogeado().getPass(), "123");
	        
	        // *** Creacion del pedido ***
	        when(vista.getCantidadPax()).thenReturn(3);
	        when(vista.isPedidoConMascota()).thenReturn(false);
	        when(vista.isPedidoConBaul()).thenReturn(false);
	        when(vista.getCantKm()).thenReturn(5);
	        when(vista.getTipoZona()).thenReturn(Constantes.ZONA_STANDARD);
	       
	        controlador.actionPerformed(new ActionEvent(this, 2, Constantes.NUEVO_PEDIDO));
	        
	        Cliente cliente = (Cliente) Empresa.getInstance().getClientes().get("facundo");
	        Assert.assertNotNull("El cliente logueado deberia tener un pedido",
		    		Empresa.getInstance().getPedidoDeCliente(cliente));
	        
	        // *** Inicio del viaje ***
	        when(vista.getChoferDisponibleSeleccionado()).thenReturn(Empresa.getInstance().getChoferesDesocupados().get(0));
	        when(vista.getVehiculoDisponibleSeleccionado()).thenReturn(Empresa.getInstance().getVehiculosDesocupados().get(0));
	        when(vista.getPedidoSeleccionado()).thenReturn(Empresa.getInstance().getPedidoDeCliente(cliente));
	        controlador.actionPerformed(new ActionEvent(this, 3, Constantes.NUEVO_VIAJE));
	        
	        Viaje viajeEmpezado = Empresa.getInstance().getViajesIniciados().get(cliente);
	        Assert.assertNotNull("El viaje no se inicio",viajeEmpezado);
	        
	        // *** Finalización del viaje ***
	        when(vista.getCalificacion()).thenReturn(2);
	        controlador.actionPerformed(new ActionEvent(this, 4, Constantes.CALIFICAR_PAGAR));
	        
	        boolean viajeFinalizado = Empresa.getInstance().getViajesTerminados().contains(viajeEmpezado);
	        Assert.assertTrue("El viaje debería haber finalizado correctamente", viajeFinalizado);
	        Assert.assertEquals("La calificación del viaje debería ser la asignada", 2, viajeEmpezado.getCalificacion());

	        
	    } catch (Exception e) {
			Assert.fail("Excepcion inesperada: " + e.getMessage());
		}
	}
	@Test
	public void test_clienteCalificacionSinViaje() {
	    try {
	        // *** Inicio de sesion ***
	        when(vista.getPassword()).thenReturn("4567");
	        when(vista.getUsserName()).thenReturn("nehuen");
	        
	        controlador.actionPerformed(new ActionEvent(this, 1, Constantes.LOGIN));
	        
	        Assert.assertNotNull("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado());
	        Assert.assertEquals("Usuario logueado incorrectamente", 
	                "nehuen", Empresa.getInstance().getUsuarioLogeado().getNombreUsuario());
	        Assert.assertEquals("Usuario logueado incorrectamente", 
	                "4567", Empresa.getInstance().getUsuarioLogeado().getPass());
	        
	    	// *** Creacion del pedido ***
	        when(vista.getCantidadPax()).thenReturn(3);
	        when(vista.isPedidoConMascota()).thenReturn(false);
	        when(vista.isPedidoConBaul()).thenReturn(false);
	        when(vista.getCantKm()).thenReturn(5);
	        when(vista.getTipoZona()).thenReturn(Constantes.ZONA_STANDARD);
	       
	        controlador.actionPerformed(new ActionEvent(this, 2, Constantes.NUEVO_PEDIDO));
	        
	        Cliente cliente = (Cliente) Empresa.getInstance().getClientes().get("nehuen");
	        Assert.assertNotNull("El cliente logueado deberia tener un pedido",
		    		Empresa.getInstance().getPedidoDeCliente(cliente));

	        // *** Intento de calificación sin un viaje existente ***
	        when(vista.getCalificacion()).thenReturn(3);
	        controlador.actionPerformed(new ActionEvent(this, 4, Constantes.CALIFICAR_PAGAR));
	        
	        // Verificar que el mensaje de error se muestra
	        verify(ventanaErrores).ShowMessage(Mensajes.CLIENTE_SIN_VIAJE_PENDIENTE.getValor());
	        
	    } catch (Exception e) {
	        Assert.fail("Excepción inesperada: " + e.getMessage());
	    }
	}
}
