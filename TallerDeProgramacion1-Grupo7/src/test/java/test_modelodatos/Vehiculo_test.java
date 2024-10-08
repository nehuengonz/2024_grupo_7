package test_modelodatos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modeloDatos.Vehiculo;

public class Vehiculo_test {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructorVehiculo() {
		Vehiculo v1=new Vehiculo("nti100",9,false);
		assertNotNull("El constructor no fue creado exitosamente", v1);
		
		assertEquals("la cantidad de plazas no se a obtenido correctamente",9,v1.getCantidadPlazas());
		assertEquals("la patente no se a obtenido correctamente","nti100",v1.getPatente());
		assertEquals("la mascota no se a obtenido correctamente",false,v1.isMascota());
	}
	//FALTA METODO PUNTAJE DEL PEDIDO
}
