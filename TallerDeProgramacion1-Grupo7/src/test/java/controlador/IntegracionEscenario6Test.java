package controlador;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.event.ActionEvent;
import java.util.Iterator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Cliente;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import persistencia.EmpresaDTO;
import persistencia.PersistenciaBIN;
import util.Constantes;
import util.Mensajes;
import vista.DefaultOptionPane;
import vista.IOptionPane;
import vista.IVista;
import vista.Ventana;

public class IntegracionEscenario6Test {
	
	private ArchivoEscenario6 archivoEscenario6;
	private PersistenciaBIN persistencia;
	private IVista vista;
    private Controlador controlador;
    private VentanaErrores ventanaErrores;

	@Before
	public void setUp() throws Exception {
		vista = mock(Ventana.class);
		ventanaErrores = new VentanaErrores();
		when(vista.getOptionPane()).thenReturn(ventanaErrores);

		archivoEscenario6 = new ArchivoEscenario6();
		archivoEscenario6.setUp();
		
        persistencia = new PersistenciaBIN();
		
		controlador = new Controlador();
        controlador.setVista(vista);
        controlador.setPersistencia(persistencia);
        controlador.setFileName("empresaIntegracion.bin");
        controlador.leer();

	}

	@After
	public void tearDown() throws Exception {
		controlador = null;
		archivoEscenario6.tearDown();
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
			Assert.assertEquals("El mensaje de error no es correcto", Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor(),ventanaErrores.getMensajeError());
	        
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
	        
	        // ** Se verifica que se asigne bien en el pedido **
	        Assert.assertEquals("La cantidad de pasajeros no se asigna correctamente", 3,
	        		Empresa.getInstance().getPedidos().get(cliente).getCantidadPasajeros());
            Assert.assertFalse("La condicion de mascota no se asigna correctamente", Empresa.getInstance().getPedidos().get(cliente).isMascota());
            Assert.assertFalse("La condicion de baul no se asigna correctamente", Empresa.getInstance().getPedidos().get(cliente).isBaul());
	        Assert.assertEquals("Los km no se asignan correctamente", 5,
	        		Empresa.getInstance().getPedidos().get(cliente).getKm());
	        Assert.assertEquals("La Zona no se asignan correctamente", Constantes.ZONA_STANDARD,
	        		Empresa.getInstance().getPedidos().get(cliente).getZona());
	        
	        Assert.assertNotNull("El cliente logueado deberia tener un pedido",
		    		Empresa.getInstance().getPedidoDeCliente(cliente));
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
	        
	        controlador.actionPerformed(new ActionEvent(this,3,Constantes.CERRAR_SESION_CLIENTE));

	        Assert.assertNull("No se deslogueo al usuario", Empresa.getInstance().getUsuarioLogeado());
	        
	        // ** Se verifica que se persista correctamente **
	        persistencia.abrirInput(controlador.getFileName());
	        EmpresaDTO empresaLeida = (EmpresaDTO) persistencia.leer();
	        persistencia.cerrarInput();

	        for (Cliente clienteLeido : empresaLeida.getViajesIniciados().keySet()) {
	            Viaje viajeLeido = empresaLeida.getViajesIniciados().get(clienteLeido);

	            Iterator<Cliente> iteradorClientesOriginales = Empresa.getInstance().getViajesIniciados().keySet().iterator();
	            Cliente clienteOriginalEncontrado = null;

	            while (iteradorClientesOriginales.hasNext() && clienteOriginalEncontrado == null) {
	                Cliente clienteOriginal = iteradorClientesOriginales.next();
	                if (clienteOriginal.getNombreUsuario().equals(clienteLeido.getNombreUsuario())) {
	                    clienteOriginalEncontrado = clienteOriginal;
	                }
	            }
	            Assert.assertNotNull("No se encontró un Cliente persistido (" + clienteLeido.getNombreUsuario() + ") en la empresa Original", clienteOriginalEncontrado);
	            Viaje viajeOriginal = Empresa.getInstance().getViajesIniciados().get(clienteOriginalEncontrado);

	            Assert.assertNotNull("El viaje original no debería ser nulo", viajeOriginal);
	            Assert.assertEquals("El chofer del viaje no coincide", viajeOriginal.getChofer().getDni(), viajeLeido.getChofer().getDni());
	            Assert.assertEquals("El pedido del viaje no coincide", viajeOriginal.getPedido().getCliente().getNombreReal(), viajeLeido.getPedido().getCliente().getNombreReal());
	            Assert.assertEquals("El valor del viaje no coincide", viajeOriginal.getValor(), viajeLeido.getValor(), 0.0001);
	            Assert.assertEquals("El vehículo del viaje no coincide", viajeOriginal.getVehiculo().getPatente(), viajeLeido.getVehiculo().getPatente());
	            Assert.assertEquals("La calificación del viaje no coincide", viajeOriginal.getCalificacion(), viajeLeido.getCalificacion());
	        }
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
			Assert.assertEquals("El mensaje de error no es correcto",Mensajes.CLIENTE_SIN_VIAJE_PENDIENTE.getValor(), ventanaErrores.getMensajeError());
	        
	    } catch (Exception e) {
	        Assert.fail("Excepción inesperada: " + e.getMessage());
	    }
	}

}
