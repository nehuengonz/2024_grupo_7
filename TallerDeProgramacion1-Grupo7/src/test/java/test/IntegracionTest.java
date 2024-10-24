package test;

import controlador.Controlador;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.ChoferTemporario;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito.*;
import persistencia.EmpresaDTO;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
import util.Constantes;
import util.Mensajes;
import vista.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IntegracionTest {

    private IVista vista;
    private PersistenciaBIN persistencia;
    private Controlador controlador;
    private VentanaErrores ventanaErrores;
    public IntegracionTest() {
    }

    @Before
    public void setUp() throws Exception {
        vista = mock(Ventana.class);
        persistencia = new PersistenciaBIN();
        ventanaErrores = new VentanaErrores();

        controlador = new Controlador();
        controlador.setPersistencia(persistencia);

        when(vista.getOptionPane()).thenReturn(ventanaErrores);

    }

    @After
    public void tearDown() throws Exception {

        controlador = null;
        File archivo = new File("empresa.bin");
        if(archivo.exists()){
            archivo.delete();
        }

        Empresa.getInstance().getHistorialViajeCliente(Empresa.getInstance().getClientes().get("facundo")).clear();
        Empresa.getInstance().getHistorialViajeCliente(Empresa.getInstance().getClientes().get("thiago")).clear();
        Empresa.getInstance().getHistorialViajeCliente(Empresa.getInstance().getClientes().get("nehuen")).clear();
        Empresa.getInstance().getChoferes().clear();
        Empresa.getInstance().getVehiculos().clear();
        Empresa.getInstance().getVehiculosDesocupados().clear();
        Empresa.getInstance().getChoferesDesocupados().clear();
        Empresa.getInstance().getClientes().clear();
        Empresa.getInstance().getPedidos().clear();
        Empresa.getInstance().getViajesIniciados().clear();
        Empresa.getInstance().getViajesTerminados().clear();

    }

    /*
            String tipo = this.vista.getTipoChofer()
            String nombre = this.vista.getNombreChofer()
            String dni = this.vista.getDNIChofer();
            Si tipo== Constantes.TEMPORARIO se agrega un chofer temporario con los parametros "dni" y "nombre"
            Si tipo== Constantes.PERMANENTE
            int anio = this.vista.getAnioChofer()
            int hijos = this.vista.getHijosChofer()
             */
    @Test
    public void loginAdmin_y_nuevoChoferTemporario() throws IOException, ClassNotFoundException {
        when(vista.getPassword()).thenReturn("admin");
        when(vista.getUsserName()).thenReturn("admin");

        when(vista.getTipoChofer()).thenReturn(Constantes.TEMPORARIO);
        when(vista.getNombreChofer()).thenReturn("Alberto");
        when(vista.getDNIChofer()).thenReturn("123456789");

        controlador.setVista(vista);
        controlador.actionPerformed(new ActionEvent(this,1,Constantes.LOGIN)); //login

        Assert.assertNotNull("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado());
        Assert.assertEquals("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado().getNombreUsuario(), "admin");
        Assert.assertEquals("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado().getPass(), "admin");

        controlador.actionPerformed(new ActionEvent(this,2,Constantes.NUEVO_CHOFER)); //agregar chofer

        ChoferTemporario choferTemporario = (ChoferTemporario) Empresa.getInstance().getChoferes().get("123456789");

        Assert.assertNotNull("El chofer no fue agregado correctamente", choferTemporario);
        Assert.assertEquals("El chofer no fue agregado correctamente", choferTemporario.getNombre(),"Alberto");
        Assert.assertEquals("El chofer no fue agregado correctamente", choferTemporario.getDni(),"123456789");

        controlador.actionPerformed(new ActionEvent(this,3,Constantes.CERRAR_SESION_ADMIN)); //logout

        Assert.assertNull("No se deslogueo al usuario", Empresa.getInstance().getUsuarioLogeado());

        persistencia.abrirInput("empresa.bin");
        EmpresaDTO empresaLeida = (EmpresaDTO) persistencia.leer();
        persistencia.cerrarInput();

        ChoferTemporario choferTemporarioLeido = (ChoferTemporario) empresaLeida.getChoferes().get("123456789");

        Assert.assertEquals("El chofer no fue persistido correctamente", choferTemporarioLeido.getNombre(),"Alberto");
        Assert.assertEquals("El chofer no fue persistido correctamente", choferTemporarioLeido.getDni(),"123456789");
    }

    @Test
    public void loginAdmin_y_nuevoChoferPermanente() throws IOException, ClassNotFoundException {
        when(vista.getPassword()).thenReturn("admin");
        when(vista.getUsserName()).thenReturn("admin");

        when(vista.getTipoChofer()).thenReturn(Constantes.PERMANENTE);
        when(vista.getNombreChofer()).thenReturn("Alberto");
        when(vista.getDNIChofer()).thenReturn("123456789");
        when(vista.getAnioChofer()).thenReturn(2020);
        when(vista.getHijosChofer()).thenReturn(1);

        controlador.setVista(vista);
        controlador.actionPerformed(new ActionEvent(this,1,Constantes.LOGIN)); //login

        Assert.assertNotNull("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado());
        Assert.assertEquals("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado().getNombreUsuario(), "admin");
        Assert.assertEquals("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado().getPass(), "admin");

        controlador.actionPerformed(new ActionEvent(this,2,Constantes.NUEVO_CHOFER)); //agregar chofer

        ChoferPermanente chofer = (ChoferPermanente) Empresa.getInstance().getChoferes().get("123456789");

        Assert.assertNotNull("El chofer no fue agregado correctamente", chofer);
        Assert.assertEquals("El chofer no fue agregado correctamente", chofer.getNombre(),"Alberto");
        Assert.assertEquals("El chofer no fue agregado correctamente", chofer.getDni(),"123456789");
        Assert.assertEquals("El chofer no fue agregado correctamente", chofer.getCantidadHijos(),1);
        Assert.assertEquals("El chofer no fue agregado correctamente", chofer.getAnioIngreso(),2020);

        controlador.actionPerformed(new ActionEvent(this,3,Constantes.CERRAR_SESION_ADMIN)); //logout

        Assert.assertNull("No se deslogueo al usuario", Empresa.getInstance().getUsuarioLogeado());

        persistencia.abrirInput("empresa.bin");
        EmpresaDTO empresaLeida = (EmpresaDTO) persistencia.leer();
        persistencia.cerrarInput();

        ChoferPermanente choferLeido = (ChoferPermanente) empresaLeida.getChoferes().get("123456789");

        Assert.assertEquals("El chofer no fue persistido correctamente", choferLeido.getNombre(),"Alberto");
        Assert.assertEquals("El chofer no fue persistido correctamente", choferLeido.getDni(),"123456789");
        Assert.assertEquals("El chofer no fue agregado correctamente", choferLeido.getCantidadHijos(),1);
        Assert.assertEquals("El chofer no fue agregado correctamente", choferLeido.getAnioIngreso(),2020);
    }

    @Test
    public void loginAdmin_y_ChoferRepetido(){
        when(vista.getPassword()).thenReturn("admin");
        when(vista.getUsserName()).thenReturn("admin");

        when(vista.getTipoChofer()).thenReturn(Constantes.PERMANENTE);
        when(vista.getNombreChofer()).thenReturn("Alberto");
        when(vista.getDNIChofer()).thenReturn("123456789");
        when(vista.getAnioChofer()).thenReturn(2020);
        when(vista.getHijosChofer()).thenReturn(1);

        controlador.setVista(vista);
        controlador.actionPerformed(new ActionEvent(this,1,Constantes.LOGIN)); //login

        Assert.assertNotNull("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado());
        Assert.assertEquals("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado().getNombreUsuario(), "admin");
        Assert.assertEquals("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado().getPass(), "admin");

        controlador.actionPerformed(new ActionEvent(this,2,Constantes.NUEVO_CHOFER)); //agregar chofer

        ChoferPermanente chofer = (ChoferPermanente) Empresa.getInstance().getChoferes().get("123456789");

        Assert.assertNotNull("El chofer no fue agregado correctamente", chofer);
        Assert.assertEquals("El chofer no fue agregado correctamente", chofer.getNombre(),"Alberto");
        Assert.assertEquals("El chofer no fue agregado correctamente", chofer.getDni(),"123456789");
        Assert.assertEquals("El chofer no fue agregado correctamente", chofer.getCantidadHijos(),1);
        Assert.assertEquals("El chofer no fue agregado correctamente", chofer.getAnioIngreso(),2020);

        controlador.actionPerformed(new ActionEvent(this,2,Constantes.NUEVO_CHOFER)); //agregar chofer

        Assert.assertEquals("El mensaje de error no es el correcto", ventanaErrores.getMensajeError(), Mensajes.CHOFER_YA_REGISTRADO.getValor());
    }

    

}
