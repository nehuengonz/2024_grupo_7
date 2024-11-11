package modelo_negocio;

import excepciones.*;
import modeloDatos.*;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Constantes;
import util.Mensajes;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class EmpresaBaseTest {
    @Before
    public void setUp() throws Exception {
        Empresa.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        Empresa.getInstance().setUsuarioLogeado(null);
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
    }


    @Test
    public void testLoginAdmin(){
        try {
            Empresa.getInstance().login("admin","admin");
            Assert.assertTrue("Se logueo un Administrador",Empresa.getInstance().isAdmin());
        } catch (UsuarioNoExisteException e) {
            fail("No deberia tirar este error porque el admin ya deberia estar contemplado");
        } catch (PasswordErroneaException e) {
            fail("No deberia tirar este error porque la contasenia admin ya deberia estar contemplado");
        }
    }

    @Test
    public void testLoginAdminMalUsuario(){
        try {
            Empresa.getInstance().login("a","admin");
            fail("Se logueo un Administrador");
        } catch (UsuarioNoExisteException e) {
            assertEquals("El mensaje de la excepcion no es correcto ",e.getMessage(), Mensajes.USUARIO_DESCONOCIDO.getValor());
            assertEquals("El mensaje de la excepcion no es correcto ",e.getUsuarioPretendido(), "a");
        } catch (PasswordErroneaException e) {
            fail("No deberia tirar este error");
        }
    }

    @Test
    public void testLoginAdminMalContra(){
        try {
            Empresa.getInstance().login("admin","123");
            fail("Se logueo un Administrador");
        } catch (UsuarioNoExisteException e) {
            fail("No deberia tirar este error");
        } catch (PasswordErroneaException e) {
            assertEquals("El mensaje de la excepcion no es correcto ",e.getMessage(), Mensajes.PASS_ERRONEO.getValor());
            assertEquals("El mensaje de la excepcion no es correcto ",e.getUsuarioPretendido(), "admin");
            assertEquals("El mensaje de la excepcion no es correcto ",e.getPasswordPretendida(), "123");
        }
    }

    @Test
    public void testLoginUsuarioNoExiste(){
        try {
            Empresa.getInstance().login("a","123");
            fail("Se logueo un Cliente");
        } catch (UsuarioNoExisteException e) {
            assertEquals("El mensaje de la excepcion no es correcto ",e.getMessage(), Mensajes.USUARIO_DESCONOCIDO.getValor());
            assertEquals("El mensaje de la excepcion no es correcto ",e.getUsuarioPretendido(), "a");

        } catch (PasswordErroneaException e) {
            fail("No deberia tirar este error");
        }
    }


    @Test
    public void testLoginAdmin_2_1(){
        try {
            Empresa.getInstance().login("ADMIN","admin");
            fail("Se logueo un Administrador");
        } catch (UsuarioNoExisteException e) {
            assertEquals("El mensaje de la excepcion no es correcto ",e.getMessage(), Mensajes.USUARIO_DESCONOCIDO.getValor());
            assertEquals("El mensaje de la excepcion no es correcto ",e.getUsuarioPretendido(), "ADMIN");
        } catch (PasswordErroneaException e) {
            fail("No deberia tirar este error");
        }
    }

    @Test
    public void testLoginAdmin_2(){
        try {
            Empresa.getInstance().login("Admin","admin");
            fail("Se logueo un Administrador");
        } catch (UsuarioNoExisteException e) {
            assertEquals("El mensaje de la excepcion no es correcto ",e.getMessage(), Mensajes.USUARIO_DESCONOCIDO.getValor());
            assertEquals("El mensaje de la excepcion no es correcto ",e.getUsuarioPretendido(), "Admin");

        } catch (PasswordErroneaException e) {
            fail("No deberia tirar este error");
        }
    }

    @Test
    public void testLoginAdmin_6(){
        try {
            Empresa.getInstance().login("admin","Admin");
            fail("Se logueo un Administrador");
        } catch (UsuarioNoExisteException e) {
            fail("No deberia tirar este error");
        } catch (PasswordErroneaException e) {
            Assert.assertNull(Empresa.getInstance().getUsuarioLogeado());

        }
    }


    @Test
    public void testLoginAdmin_6_1(){
        try {
            Empresa.getInstance().login("admin","ADMIN");
            fail("Se logueo un Administrador");
        } catch (UsuarioNoExisteException e) {
            fail("No deberia tirar este error");
        } catch (PasswordErroneaException e) {
            Assert.assertNull(Empresa.getInstance().getUsuarioLogeado());
        }
    }

    @Test
    public void logoutAdminTest() {
        try {
            Empresa.getInstance().login("admin","admin");
            Empresa.getInstance().logout();

            assertNull("El no se deslogueo el administrador", Empresa.getInstance().getUsuarioLogeado());
        } catch (UsuarioNoExisteException | PasswordErroneaException e ) {
            fail("no deberia tirar ninguna excepcion");
        }
    }

    @Test
    public void logoutUsuarioTest() {
        try {
            Empresa.getInstance().login("facundo","123");
            Empresa.getInstance().logout();

            assertNull("No se deslogueo al usuario", Empresa.getInstance().getUsuarioLogeado());
        } catch (UsuarioNoExisteException | PasswordErroneaException e ) {
            fail("no deberia tirar ninguna excepcion");
        }
    }
    @Test
    public void crearViajeTodoValido(){
        Cliente cliente = new Cliente("franco","1111","Franco Colapinto");
        HashMap<String,Cliente> clientes = new HashMap<>();
        clientes.put(cliente.getNombreUsuario(),cliente);
        Empresa.getInstance().setClientes(clientes);

        ChoferPermanente chofer = new ChoferPermanente("12345678","Tomas",2019,2);
        Pedido pedido = new Pedido(cliente,3,true,false,10, Constantes.ZONA_SIN_ASFALTAR);
        Vehiculo vehiculo = new Auto("abc111",4,true);

        try {
            Empresa.getInstance().crearViaje(pedido,chofer,vehiculo);
        } catch (ChoferNoDisponibleException e) {
            fail("no tiene que tirar chofer no disponible");
        } catch (ClienteConViajePendienteException e) {
            fail("no tiene que tirar cliente con viaje pendiente");
        } catch (PedidoInexistenteException e) {
            fail("no tiene que tirar pedido inexistente");
        } catch (VehiculoNoDisponibleException e) {
            fail("no tiene que tirar vehiculo no disponible");
        } catch (VehiculoNoValidoException e) {
            fail("no tiene que tirar vehiculo no validos");
        }

    }
}
