package modelo_negocio;

import modeloNegocio.Empresa;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

public class GetTotalSalariosTest {
    @Test
    public void getSalariosTest() {
        try{
            Assert.assertEquals("",0.0, Empresa.getInstance().getTotalSalarios(),0.001);
        }catch (Exception e){
            fail("No deberia lanzar ninguna excepcion");
        }
    }
}
