package modelo_negocio;

import modeloDatos.Chofer;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import org.junit.*;
import test.Escenario4;

import java.util.ArrayList;

import static org.junit.Assert.fail;

public class GetHistorialesEscenario4Test {
    Escenario4 escenario4;

    public GetHistorialesEscenario4Test() {

    }

    @Before
    public void setUp() throws Exception {
        escenario4 = new Escenario4();
        escenario4.setup();
    }

    @After
    public void tearDown() throws Exception {
        escenario4.tearDown();
    }

    @Test
    public void getHistorialdeViajeChofer() {
        Chofer c = Empresa.getInstance().getChoferes().get("22222222");

        try {
            ArrayList<Viaje> viajes =  Empresa.getInstance().getHistorialViajeChofer(c);
            Assert.assertFalse("El chofer deberia tener un viaje", viajes.isEmpty());
            Assert.assertEquals("El chofer devuelto no es el mismo de la prueba",viajes.get(0).getChofer().getDni(),c.getDni());
        }catch (Exception e){
            fail("Hubo un error inesperado el metodo no funciona como deberia");
        }

    }

    @Test
    public void getHistorialdeViajeChoferNoTieneHistorial() {
        Chofer c = Empresa.getInstance().getChoferes().get("1234567");

        try {
            ArrayList<Viaje> viajes =  Empresa.getInstance().getHistorialViajeChofer(c);
            Assert.assertTrue("El chofer deberia tener un viaje", viajes.isEmpty());
        }catch (Exception e){
            fail("Hubo un error inesperado el metodo no funciona como deberia");
        }
    }


}
