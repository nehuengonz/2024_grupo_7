package modelo_negocio;

import excepciones.*;
import modeloDatos.Chofer;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.Constantes;
import util.Mensajes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CrearViajeEscenario3Test {
    Escenario2 escenario2 = new Escenario2();

    @Before
    public void setUp() throws Exception {
        escenario2.setup();
    }

    @After
    public void tearDown() throws Exception {
        escenario2.tearDown();
    }

}
