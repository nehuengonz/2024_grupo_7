package choferTest;

import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ChoferTest {

    public ChoferTest() {
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void constructorChoferTest() {
        Chofer chofer = new ChoferPermanente("1234567", "Facundo", 2000, 2);

        assertEquals("El dni del chofer no fue inicializado correctamente","1234567", chofer.getDni());
        assertEquals("El nombre del chofer no fue inicializado correctamente", "Facundo", chofer.getNombre());
    }

    @Test
    public void setSueldoBasicoTest() {
        Chofer.setSueldoBasico(2000.0);

        assertEquals("El sueldo basico no se setea bien",2000.0,Chofer.getSueldoBasico(),0.0001);
    }


}
