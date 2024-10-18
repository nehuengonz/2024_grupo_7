package modelo_datos;

import modeloDatos.*;
import org.junit.*;
import org.junit.runner.JUnitCore;
import modelo_datos.EscenarioVacio;
import modelo_datos.TestVacio;
import util.Constantes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ViajeConstructorTest {
    Chofer c;
    Pedido p;
    Vehiculo v;

    public static void main(String[] args) {
        String[] args2 = { ViajeConstructorTest.class.getName() };
        JUnitCore.main(args2);
    }

    @Before
    public void setUp(){
        v = new Moto("abc123");
        c = new ChoferPermanente("1234567", "Facundo",2000, 2 );
        Cliente cliente = new Cliente("Roberto","123", "Roberto");
        p = new Pedido(cliente, 1, false, false, 10, Constantes.ZONA_STANDARD);

    }

    @After
    public void tearDown(){
        v = null;
        c = null;
        p = null;
    }

    @BeforeClass
    public static void setUpBeforeClass(){

    }

    @AfterClass
    public static void tearDownAfterClass(){
    }


    @Test
    public void testConstructorViaje(){

        Viaje viaje = new Viaje(p,c,v);

        assertEquals("El pedido no fue inicializado correctamente", p, viaje.getPedido());
        assertEquals("El chofer no fue inicializado correctamente", c, viaje.getChofer());
        assertEquals("El vehiculo no fue inicializado correctamente", v, viaje.getVehiculo());

        assertEquals("El atributo calificacion no fue inicializado correctamente",0,viaje.getCalificacion());
        assertFalse("El atributo finalizado no fue inicializado correctamente", viaje.isFinalizado());
    }


    @Test
    public void testSetValorBase(){

        Viaje.setValorBase(10.0);
        assertEquals("El atributo calificacion no fue inicializado correctamente",10.0,Viaje.getValorBase(),0.00001);
    }

}
