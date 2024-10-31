package test;

import controlador.Controlador;
import modeloDatos.*;
import modeloNegocio.Empresa;
import modelo_negocio.Escenario2;
import modelo_negocio.Escenario5;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import persistencia.EmpresaDTO;
import persistencia.PersistenciaBIN;
import util.Constantes;
import vista.IVista;
import vista.Ventana;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreacionDePedidosTest {
    private IVista vista;
    private PersistenciaBIN persistencia;
    private Controlador controlador;
    private VentanaErrores ventanaErrores;

    private Escenario2 escenario2;

    public CreacionDePedidosTest() {
    }

    @Before
    public void setUp() throws Exception {
        vista = mock(Ventana.class);
        persistencia = new PersistenciaBIN();
        ventanaErrores = new VentanaErrores();
        escenario2 = new Escenario2();
        escenario2.setup();
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

        escenario2.tearDown();

    }

    @Test
    public void crearViajeConPedido() throws IOException, ClassNotFoundException {
        when(vista.getPassword()).thenReturn("admin");
        when(vista.getUsserName()).thenReturn("admin");

        when(vista.getChoferDisponibleSeleccionado()).thenReturn(Empresa.getInstance().getChoferesDesocupados().get(0));
        when(vista.getVehiculoDisponibleSeleccionado()).thenReturn(Empresa.getInstance().getVehiculosDesocupados().get(0));
        when(vista.getPedidoSeleccionado()).thenReturn(
                Empresa.getInstance().getPedidoDeCliente(
                        Empresa.getInstance().getClientes().get("123")
                ));

        Pedido p = Empresa.getInstance().getPedidoDeCliente(Empresa.getInstance().getClientes().get("123"));
        Vehiculo v = Empresa.getInstance().getVehiculosDesocupados().get(0);
        Chofer chofer = Empresa.getInstance().getChoferesDesocupados().get(0);

        controlador.actionPerformed(new ActionEvent(this,1,Constantes.LOGIN)); //login

        controlador.actionPerformed(new ActionEvent(this,1,Constantes.NUEVO_VIAJE));

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


}
