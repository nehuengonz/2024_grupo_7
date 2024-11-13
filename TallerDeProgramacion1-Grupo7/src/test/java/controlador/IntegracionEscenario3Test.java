package controlador;

import modeloDatos.Cliente;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import persistencia.PersistenciaBIN;
import util.Constantes;
import util.Mensajes;
import vista.IVista;
import vista.Ventana;

import java.awt.event.ActionEvent;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class IntegracionEscenario3Test {

    private ArchivoEscenario3 archivoEscenario3;
    private IVista vista;
    private PersistenciaBIN persistencia;
    private Controlador controlador;
    private VentanaErrores ventanaErrores;

    @Before
    public void setUp() throws Exception {
        vista = mock(Ventana.class);

        archivoEscenario3 = new ArchivoEscenario3();
        archivoEscenario3.setUp();

        persistencia = new PersistenciaBIN();
        ventanaErrores = new VentanaErrores();

        controlador = new Controlador();
        controlador.setPersistencia(persistencia);
        controlador.setVista(vista);
        controlador.setFileName("empresaIntegracion.bin");
        controlador.leer();

        //esto es para los mensajes de error
        when(vista.getOptionPane()).thenReturn(ventanaErrores);

    }

    @After
    public void tearDown() throws Exception {
        controlador = null;
        archivoEscenario3.tearDown();
    }

    @Test
    public void crearViajeVehiculoNoDisponibleTest() {
        when(vista.getPassword()).thenReturn("admin");
        when(vista.getUsserName()).thenReturn("admin");

        when(vista.getChoferDisponibleSeleccionado()).thenReturn(Empresa.getInstance().getChoferesDesocupados().get(1));
        when(vista.getVehiculoDisponibleSeleccionado()).thenReturn(Empresa.getInstance().getVehiculos().get("abc123"));
        when(vista.getPedidoSeleccionado()).thenReturn(
                Empresa.getInstance().getPedidoDeCliente(
                        Empresa.getInstance().getClientes().get("thiago")
                ));

        controlador.actionPerformed(new ActionEvent(this,1, Constantes.LOGIN)); //login

        controlador.actionPerformed(new ActionEvent(this,2,Constantes.NUEVO_VIAJE));

        Assert.assertEquals("El mensaje de excepcion VEHICULO_NO_DISPONIBLE no es correcto", ventanaErrores.getMensajeError(), Mensajes.VEHICULO_NO_DISPONIBLE.getValor());

        controlador.actionPerformed(new ActionEvent(this,4,Constantes.CERRAR_SESION_ADMIN)); //logout
    }


    @Test
    public void crearViajeChoferNoDisponibleTest() {
        when(vista.getPassword()).thenReturn("admin");
        when(vista.getUsserName()).thenReturn("admin");

        when(vista.getChoferDisponibleSeleccionado()).thenReturn(Empresa.getInstance().getChoferes().get("1234567"));
        when(vista.getVehiculoDisponibleSeleccionado()).thenReturn(Empresa.getInstance().getVehiculosDesocupados().get(0));
        when(vista.getPedidoSeleccionado()).thenReturn(
                Empresa.getInstance().getPedidoDeCliente(
                        Empresa.getInstance().getClientes().get("thiago")
                ));

        controlador.actionPerformed(new ActionEvent(this,1, Constantes.LOGIN)); //login

        controlador.actionPerformed(new ActionEvent(this,2,Constantes.NUEVO_VIAJE));
        System.out.println(Empresa.getInstance().getPedidoDeCliente(
                Empresa.getInstance().getClientes().get("thiago")
        ));
        Assert.assertEquals("El mensaje de excepcion CHOFER_NO_DISPONIBLE no es correcto", ventanaErrores.getMensajeError(), Mensajes.CHOFER_NO_DISPONIBLE.getValor());

        controlador.actionPerformed(new ActionEvent(this,4,Constantes.CERRAR_SESION_ADMIN)); //logout
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
            Assert.assertEquals("El mensaje de error no es correcto",ventanaErrores.getMensajeError(),Mensajes.CLIENTE_SIN_VIAJE_PENDIENTE.getValor());

        } catch (Exception e) {
            Assert.fail("Excepción inesperada: " + e.getMessage());
        }
    }
}
