package test;

import controlador.Controlador;
import modeloDatos.*;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import persistencia.EmpresaDTO;
import persistencia.PersistenciaBIN;
import util.Constantes;
import util.Mensajes;
import vista.IVista;
import vista.Ventana;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Iterator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IntegracionEscenario2 {

    ArchivoEscenario2 archivoEscenario2;
    private IVista vista;
    private PersistenciaBIN persistencia;
    private Controlador controlador;
    private VentanaErrores ventanaErrores;

    @Before
    public void setUp() throws Exception {
        vista = mock(Ventana.class);

        persistencia = new PersistenciaBIN();
        ventanaErrores = new VentanaErrores();

        controlador = new Controlador();
        controlador.setPersistencia(persistencia);
        controlador.setVista(vista);
        controlador.setFileName("empresaIntegracion.bin");

        //esto es para los mensajes de error
        when(vista.getOptionPane()).thenReturn(ventanaErrores);
        archivoEscenario2 = new ArchivoEscenario2();

        archivoEscenario2.setUp();

    }

    @After
    public void tearDown() throws Exception {
        controlador = null;
        archivoEscenario2.tearDown();
    }

    @Test
    public void registroDeClienteRepetido()  {
        when(vista.getRegUsserName()).thenReturn("facundo");
        when(vista.getRegNombreReal()).thenReturn("Facundo Delgado");
        when(vista.getRegPassword()).thenReturn("123");
        when(vista.getRegConfirmPassword()).thenReturn("123");

        controlador.actionPerformed(new ActionEvent(this,1, Constantes.REG_BUTTON_REGISTRAR)); //apreta boton de registrar
        Assert.assertEquals("El mensaje de error no es el correcto", ventanaErrores.getMensajeError(), Mensajes.USUARIO_REPETIDO.getValor());
    }

    @Test
    public void loginAdmin_y_VehiculoRepetido()  {
        when(vista.getPassword()).thenReturn("admin");
        when(vista.getUsserName()).thenReturn("admin");

        when(vista.getTipoVehiculo()).thenReturn(Constantes.AUTO);
        when(vista.getPatente()).thenReturn("abc123");
        when(vista.getPlazas()).thenReturn(4);
        when(vista.isVehiculoAptoMascota()).thenReturn(true);

        controlador.actionPerformed(new ActionEvent(this,1,Constantes.LOGIN)); //login

        Assert.assertNotNull("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado());
        Assert.assertEquals("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado().getNombreUsuario(), "admin");
        Assert.assertEquals("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado().getPass(), "admin");

        controlador.actionPerformed(new ActionEvent(this,2,Constantes.NUEVO_VEHICULO)); //agregar chofer

        Assert.assertEquals("El mensaje de error no es el correcto", ventanaErrores.getMensajeError(), Mensajes.VEHICULO_YA_REGISTRADO.getValor());
    }

    @Test
    public void loginAdmin_y_ChoferRepetido(){
        when(vista.getPassword()).thenReturn("admin");
        when(vista.getUsserName()).thenReturn("admin");

        when(vista.getTipoChofer()).thenReturn(Constantes.PERMANENTE);
        when(vista.getNombreChofer()).thenReturn("Alberto");
        when(vista.getDNIChofer()).thenReturn("1234567");
        when(vista.getAnioChofer()).thenReturn(2020);
        when(vista.getHijosChofer()).thenReturn(1);

        controlador.actionPerformed(new ActionEvent(this,1,Constantes.LOGIN)); //login

        Assert.assertNotNull("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado());
        Assert.assertEquals("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado().getNombreUsuario(), "admin");
        Assert.assertEquals("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado().getPass(), "admin");

        controlador.actionPerformed(new ActionEvent(this,2,Constantes.NUEVO_CHOFER)); //agregar chofer

        Assert.assertEquals("El mensaje de error no es el correcto", ventanaErrores.getMensajeError(), Mensajes.CHOFER_YA_REGISTRADO.getValor());
    }

    @Test
    public void crearViajeConPedido() throws IOException, ClassNotFoundException {
        when(vista.getPassword()).thenReturn("admin");
        when(vista.getUsserName()).thenReturn("admin");

        controlador.actionPerformed(new ActionEvent(this,1,Constantes.LOGIN)); //login

        controlador.actionPerformed(new ActionEvent(this,1,Constantes.NUEVO_VIAJE));

        when(vista.getChoferDisponibleSeleccionado()).thenReturn(Empresa.getInstance().getChoferesDesocupados().get(0));
        when(vista.getVehiculoDisponibleSeleccionado()).thenReturn(Empresa.getInstance().getVehiculosDesocupados().get(0));
        when(vista.getPedidoSeleccionado()).thenReturn(
                Empresa.getInstance().getPedidoDeCliente(
                        Empresa.getInstance().getClientes().get("facundo")
                ));

        Viaje viajeEmpezado = Empresa.getInstance().getViajesIniciados().get(Empresa.getInstance().getClientes().get("facundo"));

        Assert.assertNotNull("El viaje no se inicio",viajeEmpezado);

        controlador.actionPerformed(new ActionEvent(this,3,Constantes.CERRAR_SESION_ADMIN)); //logout

        Assert.assertNull("No se deslogueo al usuario", Empresa.getInstance().getUsuarioLogeado());

        persistencia.abrirInput("empresa.bin");
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
    }

    @Test
    public void loginUsuario() {
        when(vista.getPassword()).thenReturn("facundo");
        when(vista.getUsserName()).thenReturn("123");

        controlador.actionPerformed(new ActionEvent(this,1,Constantes.LOGIN)); //login

        Assert.assertNotNull("El usuario se deberia haber logueado", Empresa.getInstance().getUsuarioLogeado());

        controlador.actionPerformed(new ActionEvent(this,1,Constantes.CERRAR_SESION_CLIENTE)); //logout

        Assert.assertNull("El usuario deberia haber cerrado sesion", Empresa.getInstance().getUsuarioLogeado());
    }
}