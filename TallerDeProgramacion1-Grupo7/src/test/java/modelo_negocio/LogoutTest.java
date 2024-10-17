package modelo_negocio;

import excepciones.PasswordErroneaException;
import excepciones.UsuarioNoExisteException;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class LogoutTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void logoutTest() {
            Empresa.getInstance().logout();

            assertNull("El no se deslogueo el administrador", Empresa.getInstance().getUsuarioLogeado());
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


}
