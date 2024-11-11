package modelo_negocio;

import excepciones.UsuarioYaExisteException;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TestAgregarClienteEscenario1 {
    private Escenario1 escenario1 = new Escenario1();

    @Before
    public void setUp() throws Exception {
        escenario1.setup();
    }

    @After
    public void tearDown(){
        escenario1.teardown();
    }

    @Test
    public void testAgregarClienteExitoso() {
        try {
            Empresa.getInstance().agregarCliente("Ignacio","123456","Ignacio Florezz");
        } catch (UsuarioYaExisteException e) {
            fail("Segun el escenario planteado el usuario ClienteNuevo existe en el sistema");
        }
        assertNotNull(Empresa.getInstance().getClientes().get("Ignacio"));
        assertEquals("Ignacio", Empresa.getInstance().getClientes().get("Ignacio").getNombreUsuario());
    }

    @Test
    public void testAgregarClienteRepetido() {
        try {
            Empresa.getInstance().agregarCliente("facundo","123","Facundo");
            fail("Se esperaba una excepción UsuarioYaExisteException");
        } catch (UsuarioYaExisteException e) {
            fail("El usuario ya existe");
        }
    }

}
