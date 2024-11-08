package modelo_negocio;

import excepciones.PasswordErroneaException;
import excepciones.UsuarioNoExisteException;
import modeloNegocio.Empresa;
import org.junit.*;
import org.junit.runner.JUnitCore;
import util.Mensajes;

import static org.junit.Assert.*;

public class LoginEscenario2Test {
    Escenario2 escenario2;

    public LoginEscenario2Test() {
    }

    public static void main(String[] args) {
        String[] args2 = { Empresa.class.getName() };
        JUnitCore.main(args2);
    }

    @Before
    public void setUp() throws Exception {
        escenario2 = new Escenario2();
        escenario2.setup();
    }

    @After
    public void tearDown() throws Exception {
        escenario2.tearDown();
        Empresa.getInstance().setUsuarioLogeado(null);
    }

    @Test
    public void loginClienteCorrecto(){
        try{
            Empresa.getInstance().login("facundo", "123");
            assertEquals("facundo", Empresa.getInstance().getUsuarioLogeado().getNombreUsuario());
        } catch (UsuarioNoExisteException e) {
            fail("Segun el escenario planteado el usuario facundo existe");
        } catch (PasswordErroneaException e) {
            fail("La contrasenia proporcionada es la correcta");
        }
    }

    @Test
    public void loginClienteNombreCorrectoContraMal(){
        try{
            Empresa.getInstance().login("thiago", "1234");
            fail("la contrasenia que se proporciono no era la correcta");
        } catch (UsuarioNoExisteException e) {
            fail("El metodo no deberia arrojar esta excepcion");
        } catch (PasswordErroneaException e) {
            assertEquals("El mensaje de la excepcion no es correcto ",e.getMessage(), Mensajes.PASS_ERRONEO.getValor());
            assertEquals("El mensaje de la excepcion no es correcto ",e.getUsuarioPretendido(), "thiago");
            assertEquals("El mensaje de la excepcion no es correcto ",e.getPasswordPretendida(), "1234");
        }
    }

    @Test
    public void loginClienteUsuarioMal(){
        try{
            Empresa.getInstance().login("nehu", "4321");
            fail("el nombre de usuario no existe");
        } catch (UsuarioNoExisteException e) {
            assertEquals("El mensaje de la excepcion no es correcto ",e.getMessage(), Mensajes.USUARIO_DESCONOCIDO.getValor());
            assertEquals("El mensaje de la excepcion no es correcto ",e.getUsuarioPretendido(), "nehu");
        } catch (PasswordErroneaException e) {
            fail("El metodo no deberia arrojar esta excepcion");
        }
    }

    @Test
    public void loginClienteDespuesAdmin(){
        try{
            Empresa.getInstance().login("nehuen","4567");
            Empresa.getInstance().login("admin","admin");
            Assert.assertTrue("Se logueo un Administrador",Empresa.getInstance().isAdmin());
        } catch (UsuarioNoExisteException | PasswordErroneaException e) {
            fail("El metodo no deberia arrojar esta excepcion");
        }
    }



}
