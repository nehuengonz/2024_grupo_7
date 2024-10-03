package test;

import org.junit.*;
import org.junit.runner.JUnitCore;
import static org.junit.Assert.*;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import util.Constantes;


public class PedidoTest {
	
	private Pedido pedido;
	private Cliente cliente;
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
    	cliente = new Cliente("wutang", "12345", "Thiago");
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
    public void testConstructorPedido1(){
    	this.pedido = new Pedido(cliente, 1, true, true, 10, Constantes.ZONA_PELIGROSA);
    	assertNotNull("El pedido no fue creado correctamente",this.pedido);
    }
    @Test
    public void testConstructorPedido2(){
    	this.pedido = new Pedido(cliente, 10, false, false, 5, Constantes.ZONA_SIN_ASFALTAR);
    	assertNotNull("El pedido no fue creado correctamente", this.pedido);
    }
    @Test
    public void testConstructorPedido3(){
    	this.pedido = new Pedido(cliente, 5, false, true, 0, Constantes.ZONA_STANDARD);
    	assertNotNull("El pedido no fue creado correctamente", this.pedido);
    }
    
    @Test
    public void testGetCliente() {
    	this.pedido = new Pedido(cliente, 1, true, true, 10, Constantes.ZONA_PELIGROSA);
    	assertEquals("No devuelve correctamente el cliente", cliente, pedido.getCliente());
    }
    
    @Test
    public void testIsMascota() {
    	this.pedido = new Pedido(cliente, 1, true, true, 10, Constantes.ZONA_PELIGROSA);
    	assertTrue("No devuelve correctamente la mascota", pedido.isMascota());
    }
    @Test
    public void testGetZona() {
    	this.pedido = new Pedido(cliente, 1, true, true, 10, Constantes.ZONA_PELIGROSA);
    	assertEquals("No devuelve correctamente la zona", Constantes.ZONA_PELIGROSA, pedido.getZona());
    }
}
