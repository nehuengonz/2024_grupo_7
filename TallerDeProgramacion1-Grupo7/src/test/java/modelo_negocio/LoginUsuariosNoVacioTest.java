package modelo_negocio;

import excepciones.PasswordErroneaException;
import excepciones.UsuarioNoExisteException;
import modeloNegocio.Empresa;
import org.junit.*;
import org.junit.runner.JUnitCore;
import test.EscenarioVacio;
import test.TestVacio;

import static org.junit.Assert.*;

public class LoginUsuariosNoVacioTest {
    Escenario2 escenario2;

    public LoginUsuariosNoVacioTest() {
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
    }

    @Test
    public void loginClienteCorrecto(){
        try{
            Empresa.getInstance().login("facundo", "123");
            assertEquals("Roberto", Empresa.getInstance().getUsuarioLogeado().getNombreUsuario());
        } catch (UsuarioNoExisteException e) {
            fail("Segun el escenario planteado el usuario Roberto existe");
        } catch (PasswordErroneaException e) {
            fail("La contrasenia proporcionada es la correcta");
        }
    }

    @Test
    public void loginClienteNombreCorrectoContraMal(){
        try{
            Empresa.getInstance().login("Fulgencio", "1234");
            fail("la contrasenia que se proporciono no era la correcta");
        } catch (UsuarioNoExisteException e) {
            fail("El metodo no deberia arrojar esta excepcion");
        } catch (PasswordErroneaException e) {
            assertNull(Empresa.getInstance().getUsuarioLogeado());
        }
    }

    @Test
    public void loginCliente8_1(){
        try{
            Empresa.getInstance().login("Fulgen", "4321");
            fail("el nombre de usuario no existe");
        } catch (UsuarioNoExisteException e) {
            assertNull(Empresa.getInstance().getUsuarioLogeado());
        } catch (PasswordErroneaException e) {
            fail("El metodo no deberia arrojar esta excepcion");
        }
    }

    @Test
    public void loginClienteDespuesAdmin(){
        try{
            Empresa.getInstance().login("Fulgencio", "4321");
            Empresa.getInstance().login("admin","admin");
            Assert.assertTrue("Se logueo un Administrador",Empresa.getInstance().isAdmin());
        } catch (UsuarioNoExisteException | PasswordErroneaException e) {
            fail("El metodo no deberia arrojar esta excepcion");
        }
    }



}
