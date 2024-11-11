package modelo_negocio;

import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class GetTotalSalariosEscenario2Test {
    private Escenario2 escenario2;
    @Before
    public void setUp() throws Exception {
        escenario2 = new Escenario2();
        escenario2.setup();
    }

    @After
    public void tearDown() throws Exception {
        escenario2.tearDown();
    }
    //Debe calcular el sueldo neto de todos los conductores aplicando una deducción del 14%. Sueldo Basico = 500000.0
    @Test
    public void getSalariosTest() {
        try{
            Assert.assertEquals("",2003800.0,Empresa.getInstance().getTotalSalarios(),0.001);
        }catch (Exception e){
            fail("No deberia lanzar ninguna excepcion");
        }
    }
}
