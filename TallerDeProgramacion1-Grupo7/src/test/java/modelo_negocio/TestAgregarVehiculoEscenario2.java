package modelo_negocio;

import excepciones.VehiculoRepetidoException;
import modeloDatos.Auto;
import modeloDatos.Combi;
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
    public void testAgregarVehiculoMotoExitoso() {
        Vehiculo nuevoVehiculo = new Moto("XYZ789");
        try {
            Empresa.getInstance().agregarVehiculo(nuevoVehiculo);
            assertEquals("La patente del vehículo agregado no es la esperada", "XYZ789", Empresa.getInstance().getVehiculos().get("XYZ789").getPatente());
        } catch (VehiculoRepetidoException e) {
            fail("Se esperaba que el vehículo se agregara exitosamente, pero ocurrió una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testAgregarVehiculoMotoDuplicado() {
        try {
            Empresa.getInstance().agregarVehiculo(escenario2.getMoto1()); // Ya fue agregado en el escenario
            fail("Se esperaba una excepción VehiculoRepetidoException");
        } catch (VehiculoRepetidoException e) {
            fail("El vehiculo Moto con patente pat333 ya está registrado");
        }
    }

    @Test
    public void testAgregarVehiculoAutoExitoso() {
        Vehiculo nuevoVehiculo = new Auto("ASD789",4,true);
        try {
            Empresa.getInstance().agregarVehiculo(nuevoVehiculo);
            assertEquals("La patente del vehículo agregado no es la esperada", "ASD789", Empresa.getInstance().getVehiculos().get("ASD789").getPatente());
        } catch (VehiculoRepetidoException e) {
            fail("Se esperaba que el vehículo se agregara exitosamente, pero ocurrió una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testAgregarVehiculoAutoDuplicado() {
        try {
            Empresa.getInstance().agregarVehiculo(escenario2.getAuto1()); // Ya fue agregado en el escenario
            fail("Se esperaba una excepción VehiculoRepetidoException");
        } catch (VehiculoRepetidoException e) {
            fail("El vehiculo con patente abc123 ya está registrado");
        }
    }

    @Test
    public void testAgregarVehiculoCombiExitoso() {
        Vehiculo nuevoVehiculo = new Combi("DDD789",6,false);
        try {
            Empresa.getInstance().agregarVehiculo(nuevoVehiculo);
            assertEquals("La patente del vehículo agregado no es la esperada", "DDD789", Empresa.getInstance().getVehiculos().get("DDD789").getPatente());
        } catch (VehiculoRepetidoException e) {
            fail("Se esperaba que el vehículo se agregara exitosamente, pero ocurrió una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testAgregarVehiculoCombiDuplicado() {
        try {
            Empresa.getInstance().agregarVehiculo(escenario2.getCombi1()); // Ya fue agregado en el escenario
            fail("Se esperaba una excepción VehiculoRepetidoException");
        } catch (VehiculoRepetidoException e) {
            fail("El vehiculo con patente combi222 ya está registrado");
        }
    }

}
