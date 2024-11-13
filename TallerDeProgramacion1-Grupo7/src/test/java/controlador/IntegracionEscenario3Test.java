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

        Assert.assertEquals("El mensaje de excepcion VEHICULO_NO_DISPONIBLE no es correcto", Mensajes.VEHICULO_NO_DISPONIBLE.getValor(), ventanaErrores.getMensajeError());

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
        Assert.assertEquals("El mensaje de excepcion CHOFER_NO_DISPONIBLE no es correcto", Mensajes.CHOFER_NO_DISPONIBLE.getValor(), ventanaErrores.getMensajeError());

        controlador.actionPerformed(new ActionEvent(this,4,Constantes.CERRAR_SESION_ADMIN)); //logout
    }
}
