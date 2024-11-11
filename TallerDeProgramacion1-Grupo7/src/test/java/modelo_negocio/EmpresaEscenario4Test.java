package modelo_negocio;

import org.junit.After;
import org.junit.Before;

public class EmpresaEscenario4Test {
    private Escenario4 escenario4;
    @Before
    public void setUp() throws Exception {
        escenario4 = new Escenario4();
        escenario4.setup();
    }

    @After
    public void tearDown() throws Exception {
        escenario4.tearDown();
    }
}
