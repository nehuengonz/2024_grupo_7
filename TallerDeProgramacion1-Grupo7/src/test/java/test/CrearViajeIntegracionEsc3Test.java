package test;

import controlador.Controlador;
import modeloDatos.Pedido;
import modeloNegocio.Empresa;
import modelo_negocio.Escenario2;
import modelo_negocio.Escenario3;
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
import java.io.File;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CrearViajeIntegracionEsc3Test {
    private IVista vista;
    private PersistenciaBIN persistencia;
    private Controlador controlador;
    private VentanaErrores ventanaErrores;

    private Escenario3 escenario3;

    public CrearViajeIntegracionEsc3Test() {
    }

    @Before
    public void setUp() throws Exception {
        vista = mock(Ventana.class);
        persistencia = new PersistenciaBIN();
        ventanaErrores = new VentanaErrores();
        escenario3 = new Escenario3();
        escenario3.setup();
        controlador = new Controlador();
        controlador.setPersistencia(persistencia);
        controlador.setVista(vista);

        when(vista.getPassword()).thenReturn("admin");
        when(vista.getUsserName()).thenReturn("admin");

        //esto es para los mensajes de error
        when(vista.getOptionPane()).thenReturn(ventanaErrores);

    }

    @After
    public void tearDown() throws Exception {

        controlador = null;
        File archivo = new File("empresa.bin");
        if(archivo.exists()){
            archivo.delete();
        }

        escenario3.tearDown();

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

        Assert.assertEquals("El mensaje de excepcion CHOFER_NO_DISPONIBLE no es correcto", ventanaErrores.getMensajeError(), Mensajes.CHOFER_NO_DISPONIBLE.getValor());

        controlador.actionPerformed(new ActionEvent(this,4,Constantes.CERRAR_SESION_ADMIN)); //logout
    }
}
