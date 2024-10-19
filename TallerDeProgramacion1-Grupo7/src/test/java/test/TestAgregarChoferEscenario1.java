package test;

import excepciones.ChoferRepetidoException;
import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestAgregarChoferEscenario1 {
    private Escenario2 escenario2 = new Escenario2();

    @Before
    public void setUp() throws Exception {
        escenario2.setup();
    }

    @After
    public void tearDown() {
        escenario2.teardown();
    }

    @Test
    public void testAgregarChoferExitoso() {
        Chofer nuevoChofer = new ChoferTemporario("7777777", "Nuevo Chofer");
        try {
            Empresa.getInstance().agregarChofer(nuevoChofer);
            assertEquals("El DNI del chofer agregado no es el esperado", "7777777", Empresa.getInstance().getChoferes().get("7777777").getDni());
        } catch (ChoferRepetidoException e) {
            fail("Se esperaba que el chofer se agregara exitosamente, pero ocurrió una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testAgregarChoferDuplicado() {
        try {
            Empresa.getInstance().agregarChofer(escenario2.getChofer1()); // Ya fue agregado en el escenario
            fail("Se esperaba una excepción ChoferRepetidoException");
        } catch (ChoferRepetidoException e) {
            fail("El chofer con DNI 111111 ya está registrado");
        }
    }


}
