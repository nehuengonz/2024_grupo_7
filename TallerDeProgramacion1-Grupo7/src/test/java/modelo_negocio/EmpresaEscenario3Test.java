package modelo_negocio;

import org.junit.After;
import org.junit.Before;

public class EmpresaEscenario3Test {
    private Escenario3 escenario3;
    @Before
    public void setUp() throws Exception {
        escenario3 = new Escenario3();
        escenario3.setup();
    }

    @After
    public void tearDown() throws Exception {
        escenario3.tearDown();
    }
}
