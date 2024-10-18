package modelo_negocio;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;

import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloNegocio.Empresa;
import util.Constantes;

public class EscenarioConViaje {

	Empresa empresa;
    Pedido pedido;
    Chofer chofer;
    Vehiculo vehiculo;
    Cliente cliente;
    //PARAMETROS INICIALES
    //Pedido
    private static final int cantPasajeros=5;
    private static final boolean mascota=true;
    private static final boolean baul=true;
    private static final int km=100;
    private static final String zona=Constantes.ZONA_PELIGROSA;
    //chofer
    private static final int anioInicio=2015;
    private static final int CantHijos=0;
    //vehiculo
    private static final int CantPlazas=3;
    
    //
    public EscenarioConViaje() {
    	
    }
    //
	@Before
	public void setUp() throws Exception {
		cliente= new Cliente("a","a","a");
		pedido = new Pedido(cliente,cantPasajeros,mascota,baul,km,zona);
		chofer = new ChoferPermanente("a","a",anioInicio,CantHijos);
		vehiculo = new Auto("patente",CantPlazas,mascota);
		empresa.agregarChofer(chofer);
		empresa.agregarCliente("a", "a", "a");
		empresa.agregarPedido(pedido);
		empresa.agregarVehiculo(vehiculo);
	}

	@After
	public void tearDown() throws Exception {
		cliente = null;
		pedido = null;
		chofer = null;
		vehiculo = null;
	}

}
