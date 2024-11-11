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



}
