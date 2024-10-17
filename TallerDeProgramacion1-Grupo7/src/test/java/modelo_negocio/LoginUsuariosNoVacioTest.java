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

    public LoginUsuariosNoVacioTest() {
    }

    public static void main(String[] args) {
        String[] args2 = { Empresa.class.getName() };
        JUnitCore.main(args2);
    }

    @Before
    public void setUp() throws Exception {
        Empresa.getInstance();
        Empresa.getInstance().agregarCliente("Roberto", "1234", "Roberto Perez");
        Empresa.getInstance().agregarCliente("Fulgencio", "4321", "Fulgencio Garcia");
        Empresa.getInstance().agregarCliente("Facundo", "1234", "Facundo Delgado");
        Empresa.getInstance().agregarCliente("Lucas", "1234", "Lucas Warner");
    }

    @After
    public void tearDown() throws Exception {

    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Test
    public void loginClienteCorrecto(){
        try{
            Empresa.getInstance().login("Roberto", "1234");
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
