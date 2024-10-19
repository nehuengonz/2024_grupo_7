package test;

import excepciones.VehiculoRepetidoException;
import modeloDatos.Moto;
import modeloDatos.Vehiculo;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestAgregarVehiculoEscenario2 {
    private Escenario2 escenario2 = new Escenario2();

    @Before
    public void setUp() throws Exception {
        escenario2.setup();
    }

    @After
    public void tearDown(){
        escenario2.teardown();
    }

    @Test
    public void testAgregarVehiculoExitoso() {
        Vehiculo nuevoVehiculo = new Moto("XYZ789");
        try {
            Empresa.getInstance().agregarVehiculo(nuevoVehiculo);
            assertEquals("La patente del vehículo agregado no es la esperada", "XYZ789", Empresa.getInstance().getVehiculos().get("XYZ789").getPatente());
        } catch (VehiculoRepetidoException e) {
            fail("Se esperaba que el vehículo se agregara exitosamente, pero ocurrió una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testAgregarVehiculoDuplicado() {
        try {
            Empresa.getInstance().agregarVehiculo(escenario2.getAuto1()); // Ya fue agregado en el escenario
            fail("Se esperaba una excepción VehiculoRepetidoException");
        } catch (VehiculoRepetidoException e) {
            fail("El vehiculo con patente abc123 ya está registrado");
        }
    }
}
