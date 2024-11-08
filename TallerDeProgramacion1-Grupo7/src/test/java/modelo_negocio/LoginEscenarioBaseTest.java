package modelo_negocio;

import excepciones.PasswordErroneaException;
import excepciones.UsuarioNoExisteException;
import modeloNegocio.Empresa;
import org.junit.*;
import org.junit.runner.JUnitCore;
import util.Mensajes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LoginEscenarioBaseTest {
    private Empresa empresa;

    public LoginEscenarioBaseTest() {

    }

    public static void main(String[] args) {
        String[] args2 = { LoginEscenarioBaseTest.class.getName() };
        JUnitCore.main(args2);
    }

    @Before
    public void setUp() throws Exception {
        Empresa.getInstance();

    }

    @After
    public void tearDown() throws Exception {
        Empresa.getInstance().setUsuarioLogeado(null);
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
}
