package test;

import org.junit.*;
import org.junit.runner.JUnitCore;
import static org.junit.Assert.*;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import util.Constantes;


public class PedidoTest {
	
	private Pedido pedido;
    //private EscenarioVacio escenarioVacio = new EscenarioVacio();

    public PedidoTest() {
    }

    public static void main(String[] args) {
        String[] args2 = { PedidoTest.class.getName() };
        JUnitCore.main(args2);
    }

    @Before
    public void setUp() throws Exception {
        //escenarioVacio.setUp();
    }

    @After
    public void tearDown() throws Exception {
        //escenarioVacio.tearDown();
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }


    @Test
    public void testConstructorPrueba1(){
    	Cliente cliente = new Cliente("wutang", "12345", "Thiago");
    	this.pedido = new Pedido(cliente, 1, true, true, 10, Constantes.ZONA_PELIGROSA);
    	assertNotNull("El pedido no fue creado correctamente",this.pedido);
    }
    @Test
    public void testConstructorPrueba2(){
    	Cliente cliente = new Cliente("wutang", "12345", "Thiago");
    	this.pedido = new Pedido(cliente, 10, false, false, 5, Constantes.ZONA_SIN_ASFALTAR);
    	assertNotNull("El pedido no fue creado correctamente", this.pedido);
    }
    @Test
    public void testConstructorPrueba3(){
    	Cliente cliente = new Cliente("wutang", "12345", "Thiago");
    	this.pedido = new Pedido(cliente, 5, false, true, 0, Constantes.ZONA_STANDARD);
    	assertNotNull("El pedido no fue creado correctamente", this.pedido);
    }
    
    @Test
    public void testConstructorPrueba4(){
    	this.pedido = new Pedido(null, 5, true, true, 5, Constantes.ZONA_PELIGROSA);
    	assertNull("El pedido no fue creado correctamente", this.pedido);
    }
    
    @Test
    public void testConstructorPrueba5(){
    	Cliente cliente = new Cliente("wutang", "12345", "Thiago");
    	this.pedido = new Pedido(cliente, 0, true, true, 5, Constantes.ZONA_PELIGROSA);
    }
    
    @Test
    public void testConstructorPrueba6(){
    	Cliente cliente = new Cliente("wutang", "12345", "Thiago");
    	this.pedido = new Pedido(cliente, 15, true, true, 5, Constantes.ZONA_PELIGROSA);
    }
    @Test
    public void testConstructorPrueba7(){
    	Cliente cliente = new Cliente("wutang", "12345", "Thiago");
    	this.pedido = new Pedido(cliente, 5, true, true, -5, Constantes.ZONA_PELIGROSA);
    }
    @Test
    public void testConstructorPrueba8(){
    	Cliente cliente = new Cliente("wutang", "12345", "Thiago");
    	this.pedido = new Pedido(cliente, 5, true, true, 5, "Zona Peligrosa");
    }
}
