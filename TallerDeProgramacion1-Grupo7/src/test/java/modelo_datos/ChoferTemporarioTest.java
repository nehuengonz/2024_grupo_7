package modelo_datos;

import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.ChoferTemporario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChoferTemporarioTest {
    public ChoferTemporarioTest() {
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void choferTemporarioConstructorTest() {
        ChoferTemporario chofer = new ChoferTemporario("1234567", "Facundo");

        assertEquals("El dni del chofer no fue inicializado correctamente","1234567", chofer.getDni());
        assertEquals("El nombre del chofer no fue inicializado correctamente", "Facundo", chofer.getNombre());
    }

    @Test
    public void getSueldoBrutoChoferTemporarioTest() {
        Chofer.setSueldoBasico(2000.0);
        ChoferTemporario chofer = new ChoferTemporario("1234567", "Facundo");

        assertEquals("El sueldo basico no se setea bien",2000.0,chofer.getSueldoBruto(),0.0001);
    }

    @Test
    public void getSueldoNetoChoferTemporarioTest() {
        Chofer.setSueldoBasico(2000.0);
        ChoferTemporario chofer = new ChoferTemporario("1234567", "Facundo");

        assertEquals("El sueldo basico no se setea bien",2000.0 * 0.86,chofer.getSueldoNeto(),0.0001);
    }
}
