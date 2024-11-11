package modelo_negocio;

import excepciones.*;
import modeloDatos.*;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.Constantes;

import java.util.HashMap;

import static org.junit.Assert.fail;

public class CrearViajeEscenarioBaseTest {
    @Before
    public void setUp() throws Exception {
        Empresa.getInstance();
    }

    @After
    public void tearDown() throws Exception {

    }

}
