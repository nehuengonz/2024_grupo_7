package controlador;

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
import vista.*;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

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
        controlador.setVista(vista);


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

    @Test
    public void testDelConstructor() {
        Controlador c = new Controlador();

        Assert.assertNotNull("No se creo bien la vista",c.getVista());
        Assert.assertNotNull("No se creo bien la persistencia",c.getPersistencia());
        Assert.assertEquals("El archivo no se nombro como en el contrato", c.getFileName(), "empresa.bin");
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
    public void loginAdmin_y_nuevoVehiculoMoto() throws IOException, ClassNotFoundException {
        when(vista.getPassword()).thenReturn("admin");
        when(vista.getUsserName()).thenReturn("admin");

        when(vista.getTipoVehiculo()).thenReturn(Constantes.MOTO);
        when(vista.getPatente()).thenReturn("abc123");

        controlador.actionPerformed(new ActionEvent(this,1,Constantes.LOGIN)); //login

        Assert.assertNotNull("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado());
        Assert.assertEquals("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado().getNombreUsuario(), "admin");
        Assert.assertEquals("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado().getPass(), "admin");

        controlador.actionPerformed(new ActionEvent(this,2,Constantes.NUEVO_VEHICULO)); //agregar chofer

        Moto moto = (Moto) Empresa.getInstance().getVehiculos().get("abc123");

        Assert.assertNotNull("Moto no fue agregada correctamente", moto);
        Assert.assertEquals("Moto no fue agregado correctamente", moto.getPatente(),"abc123");
        Assert.assertEquals("Moto no fue agregado correctamente", moto.getCantidadPlazas(),1);
        Assert.assertFalse("Moto no fue agregado correctamente", moto.isMascota());

        controlador.actionPerformed(new ActionEvent(this,3,Constantes.CERRAR_SESION_ADMIN)); //logout

        Assert.assertNull("No se deslogueo al usuario", Empresa.getInstance().getUsuarioLogeado());

        persistencia.abrirInput("empresa.bin");
        EmpresaDTO empresaLeida = (EmpresaDTO) persistencia.leer();
        persistencia.cerrarInput();

        Moto motoLeido = (Moto) empresaLeida.getVehiculos().get("abc123");

        Assert.assertEquals("Moto no fue persistido correctamente", motoLeido.getPatente(),"abc123");
    }

    @Test
    public void loginAdmin_y_nuevoVehiculoAuto() throws IOException, ClassNotFoundException {
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

        Auto auto = (Auto) Empresa.getInstance().getVehiculos().get("abc123");

        Assert.assertNotNull("auto no fue agregada correctamente", auto);
        Assert.assertEquals("auto no fue agregado correctamente", auto.getPatente(),"abc123");
        Assert.assertEquals("auto no fue agregado correctamente", auto.getCantidadPlazas(),4);
        Assert.assertTrue("auto no fue agregado correctamente", auto.isMascota());

        controlador.actionPerformed(new ActionEvent(this,3,Constantes.CERRAR_SESION_ADMIN)); //logout

        Assert.assertNull("No se deslogueo al usuario", Empresa.getInstance().getUsuarioLogeado());

        persistencia.abrirInput("empresa.bin");
        EmpresaDTO empresaLeida = (EmpresaDTO) persistencia.leer();
        persistencia.cerrarInput();

        Auto autoLeido = (Auto) empresaLeida.getVehiculos().get("abc123");

        Assert.assertEquals("Auto no fue persistido correctamente", autoLeido.getPatente(),"abc123");
        Assert.assertEquals("auto no fue agregado correctamente", autoLeido.getCantidadPlazas(),4);
        Assert.assertTrue("auto no fue agregado correctamente", autoLeido.isMascota());
    }

    @Test
    public void loginAdmin_y_nuevoVehiculoCombi() throws IOException, ClassNotFoundException {
        when(vista.getPassword()).thenReturn("admin");
        when(vista.getUsserName()).thenReturn("admin");

        when(vista.getTipoVehiculo()).thenReturn(Constantes.COMBI);
        when(vista.getPatente()).thenReturn("abc123");
        when(vista.getPlazas()).thenReturn(10);
        when(vista.isVehiculoAptoMascota()).thenReturn(false);

        controlador.actionPerformed(new ActionEvent(this,1,Constantes.LOGIN)); //login

        Assert.assertNotNull("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado());
        Assert.assertEquals("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado().getNombreUsuario(), "admin");
        Assert.assertEquals("Usuario logueado incorrectamente", Empresa.getInstance().getUsuarioLogeado().getPass(), "admin");

        controlador.actionPerformed(new ActionEvent(this,2,Constantes.NUEVO_VEHICULO)); //agregar chofer

        Combi combi = (Combi) Empresa.getInstance().getVehiculos().get("abc123");

        Assert.assertNotNull("combi no fue agregada correctamente", combi);
        Assert.assertEquals("combi no fue agregado correctamente", combi.getPatente(),"abc123");
        Assert.assertEquals("combi no fue agregado correctamente", combi.getCantidadPlazas(),10);
        Assert.assertFalse("combi no fue agregado correctamente", combi.isMascota());

        controlador.actionPerformed(new ActionEvent(this,3,Constantes.CERRAR_SESION_ADMIN)); //logout

        Assert.assertNull("No se deslogueo al usuario", Empresa.getInstance().getUsuarioLogeado());

        persistencia.abrirInput("empresa.bin");
        EmpresaDTO empresaLeida = (EmpresaDTO) persistencia.leer();
        persistencia.cerrarInput();

        Combi combiLeido = (Combi) empresaLeida.getVehiculos().get("abc123");

        Assert.assertEquals("combi no fue persistido correctamente", combiLeido.getPatente(),"abc123");
        Assert.assertEquals("combi no fue agregado correctamente", combiLeido.getCantidadPlazas(),10);
        Assert.assertFalse("combi no fue agregado correctamente", combiLeido.isMascota());
    }


    @Test
    public void registroDeCliente() throws IOException, ClassNotFoundException {
        when(vista.getRegUsserName()).thenReturn("facundo");
        when(vista.getRegNombreReal()).thenReturn("Facundo Delgado");
        when(vista.getRegPassword()).thenReturn("123");
        when(vista.getRegConfirmPassword()).thenReturn("123");

        controlador.actionPerformed(new ActionEvent(this,1,Constantes.REG_BUTTON_REGISTRAR)); //apreta boton de registrar

        Cliente cliente2 = Empresa.getInstance().getClientes().get("facundo");
        Assert.assertNotNull("Usuario registrado incorrectamente", cliente2);
        Assert.assertEquals("Usuario registrado incorrectamente", Empresa.getInstance().getClientes().get("facundo").getNombreUsuario(),"facundo");
        Assert.assertEquals("Usuario registrado incorrectamente", Empresa.getInstance().getClientes().get("facundo").getNombreReal(),"Facundo Delgado");
        Assert.assertEquals("Usuario registrado incorrectamente", Empresa.getInstance().getClientes().get("facundo").getPass(),"123");

        persistencia.abrirInput("empresa.bin");
        EmpresaDTO empresaLeida = (EmpresaDTO) persistencia.leer();
        persistencia.cerrarInput();

        Cliente cliente = empresaLeida.getClientes().get("facundo");
        Assert.assertNotNull("Usuario persistido incorrectamente", cliente);
        Assert.assertEquals("El usuario no fue persistido correctamente", cliente.getNombreUsuario(),"facundo");
        Assert.assertEquals("El usuario no fue persistido correctamente", cliente.getNombreReal(),"Facundo Delgado");
        Assert.assertEquals("El usuario no fue persistido correctamente", cliente.getPass(),"123");
    }

    @Test
    public void registroDeClienteMalConfirmContraseña()  {
        when(vista.getRegUsserName()).thenReturn("facundo");
        when(vista.getRegNombreReal()).thenReturn("Facundo Delgado");
        when(vista.getRegPassword()).thenReturn("123");
        when(vista.getRegConfirmPassword()).thenReturn("1234");

        controlador.actionPerformed(new ActionEvent(this,1,Constantes.REG_BUTTON_REGISTRAR)); //apreta boton de registrar
        Assert.assertEquals("El mensaje de error no es el correcto", ventanaErrores.getMensajeError(), Mensajes.PASS_NO_COINCIDE.getValor());
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
