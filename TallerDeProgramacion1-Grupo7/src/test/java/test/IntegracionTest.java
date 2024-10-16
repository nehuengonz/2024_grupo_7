package test;

import controlador.Controlador;
import modeloDatos.ChoferTemporario;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito.*;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
import util.Constantes;
import vista.IVista;
import vista.PanelLogin;
import vista.Ventana;

import java.io.File;
import java.util.EnumMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IntegracionTest {

    private IVista vista;
    private IPersistencia persistencia;
    private Controlador controlador;
    public IntegracionTest() {
    }

    @Before
    public void setUp() throws Exception {
        vista = mock(Ventana.class);
        persistencia = mock(PersistenciaBIN.class);
        controlador = new Controlador();
        when(vista.getPassword()).thenReturn("admin");
        when(vista.getUsserName()).thenReturn("admin");


    }

    @After
    public void tearDown() throws Exception {

        controlador = null;
        File archivo = new File("empresa.bin");
        if(archivo.exists()){
            archivo.delete();
        }

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
    public void login_y_nuevoChoferTemporario() {
        when(vista.getTipoChofer()).thenReturn(Constantes.TEMPORARIO);
        when(vista.getNombreChofer()).thenReturn("Facundo");
        when(vista.getDNIChofer()).thenReturn("123456789");

        controlador.setVista(vista);

        controlador.login();
        controlador.nuevoChofer();

        Assert.assertTrue("El chofer no fue agregado correctamente", Empresa.getInstance().getChoferes().containsKey("123456789"));
    }


}
