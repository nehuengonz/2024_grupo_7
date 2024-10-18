package modelo_datos;

import modeloDatos.ChoferPermanente;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ChoferPermanenteTest {

    public ChoferPermanenteTest() {
    }

    @Before
    public void setUp() throws Exception {
        
    }

    @After
    public void tearDown() throws Exception {
        
    }

    @Test
    public void ChoferPermanenteConstructorTest() {
        ChoferPermanente choferPermanente = new ChoferPermanente("1234567","Facundo",2000,1);

        assertEquals("El dni del chofer no fue inicializado correctamente","1234567", choferPermanente.getDni());
        assertEquals("El nombre del chofer no fue inicializado correctamente", "Facundo", choferPermanente.getNombre());
        assertEquals("El anio de ingreso de chofer no fue inicializado correctamente",2000, choferPermanente.getAnioIngreso());
        assertEquals("la cantidad de hijos del chofer no fue inicializado correctamente", 1, choferPermanente.getCantidadHijos());
    }

    @Test
    public void ChoferPermanenteConstructor1900Test() {
        ChoferPermanente choferPermanente = new ChoferPermanente("1234589","Santiago",1900,0);

        assertEquals("El dni del chofer no fue inicializado correctamente","1234589", choferPermanente.getDni());
        assertEquals("El nombre del chofer no fue inicializado correctamente", "Facundo", choferPermanente.getNombre());
        assertEquals("El anio de ingreso de chofer no fue inicializado correctamente",1900, choferPermanente.getAnioIngreso());
        assertEquals("la cantidad de hijos del chofer no fue inicializado correctamente", 0, choferPermanente.getCantidadHijos());
    }

    @Test
    public void ChoferPermanenteConstructor3000Test() {
        ChoferPermanente choferPermanente = new ChoferPermanente("1234534","Florencia",3000,0);
        //fail("Enrealidad si bien cumple el contrato no deberia dejar crear");
        assertEquals("El dni del chofer no fue inicializado correctamente","1234534", choferPermanente.getDni());
        assertEquals("El nombre del chofer no fue inicializado correctamente", "Florencia", choferPermanente.getNombre());
        assertEquals("El anio de ingreso de chofer no fue inicializado correctamente",3000, choferPermanente.getAnioIngreso());
        assertEquals("la cantidad de hijos del chofer no fue inicializado correctamente", 0, choferPermanente.getCantidadHijos());
    }

    //TEST DE SUELDOS BRUTOS
    @Test
    public void getSueldoBrutoTest() {
        ChoferPermanente.setSueldoBasico(2000.0);
        ChoferPermanente choferPermanente = new ChoferPermanente("1234567","Facundo",2018,1);

        assertEquals("El sueldo bruto esta mal calculado",2740.0,choferPermanente.getSueldoBruto(),0.0001);
    }
    @Test
    public void getSueldoBrutoSinHijosTest() {
        ChoferPermanente.setSueldoBasico(2000.0);
        ChoferPermanente choferPermanente = new ChoferPermanente("1234567","Facundo",2018,0);

        assertEquals("El sueldo bruto esta mal calculado",2600.0,choferPermanente.getSueldoBruto(),0.0001);
    }

    @Test
    public void getSueldoBruto20aniosAntiguedadSinHijosTest() {
        ChoferPermanente.setSueldoBasico(2000.0);
        ChoferPermanente choferPermanente = new ChoferPermanente("1234567","Facundo",2000,0);

        assertEquals("El sueldo bruto 20 anios sin hijos esta mal calculado",4000.0,choferPermanente.getSueldoBruto(),0.0001);
    }

    @Test
    public void getSueldoBruto20aniosAntiguedadConHijosTest() {
        ChoferPermanente.setSueldoBasico(2000.0);
        ChoferPermanente choferPermanente = new ChoferPermanente("1234567","Facundo",2000,3);

        assertEquals("El sueldo bruto 20 anios con hijos esta mal calculado",4420.0,choferPermanente.getSueldoBruto(),0.0001);
    }

    @Test
    public void getSueldoBrutoSinAntiguedadSinHijosTest() {
        ChoferPermanente.setSueldoBasico(2000.0);
        ChoferPermanente choferPermanente = new ChoferPermanente("1234567","Facundo",2024,0);

        assertEquals("El sueldo bruto sin antiguedad sin hijos esta mal calculado",2000.0,choferPermanente.getSueldoBruto(),0.0001);
    }

    @Test
    public void getSueldoBrutoSinAntiguedadConHijosTest() {
        ChoferPermanente.setSueldoBasico(2000.0);
        ChoferPermanente choferPermanente = new ChoferPermanente("1234567","Facundo",2024,3);

        assertEquals("El sueldo bruto sin antiguedad con hijos esta mal calculado",2420.0,choferPermanente.getSueldoBruto(),0.0001);
    }

//    @Test
//    public void getSueldoBrutoAntiguedadAbsurdaTest() {
//        ChoferPermanente.setSueldoBasico(2000.0);
//        ChoferPermanente choferPermanente = new ChoferPermanente("1234567","Facundo",3000,0);
//
//        assertEquals("El sueldo bruto sin antiguedad con hijos esta mal calculado",2420.0,choferPermanente.getSueldoBruto(),0.0001);
//    }

    //TEST DE SUELDOS NETO
    @Test
    public void getSueldoNetoTest() {
        ChoferPermanente.setSueldoBasico(2000.0);
        ChoferPermanente choferPermanente = new ChoferPermanente("1234567","Facundo",2024,3);

        assertEquals("El sueldo neto sin antiguedad con hijos esta mal calculado",2420.0 * 0.86,choferPermanente.getSueldoNeto(),0.0001);
    }

    @Test
    public void getSueldoNetoSinHijosTest() {
        ChoferPermanente.setSueldoBasico(2000.0);
        ChoferPermanente choferPermanente = new ChoferPermanente("1234567","Facundo",2018,0);

        assertEquals("El sueldo neto sin hijos esta mal calculado",2600.0 * 0.86,choferPermanente.getSueldoNeto(),0.0001);
    }


    @Test
    public void getSueldoNeto20aniosAntiguedadSinHijosTest() {
        ChoferPermanente.setSueldoBasico(2000.0);
        ChoferPermanente choferPermanente = new ChoferPermanente("1234567","Facundo",2000,0);

        assertEquals("El sueldo neto 20 anios sin hijos esta mal calculado",4000.0 * 0.86,choferPermanente.getSueldoNeto(),0.0001);
    }

    @Test
    public void getSueldoNeto20aniosAntiguedadConHijosTest() {
        ChoferPermanente.setSueldoBasico(2000.0);
        ChoferPermanente choferPermanente = new ChoferPermanente("1234567","Facundo",2000,3);

        assertEquals("El sueldo neto 20 anios con hijos esta mal calculado",4420.0 * 0.86,choferPermanente.getSueldoNeto(),0.0001);
    }

    @Test
    public void getSueldoNetoSinAntiguedadSinHijosTest() {
        ChoferPermanente.setSueldoBasico(2000.0);
        ChoferPermanente choferPermanente = new ChoferPermanente("1234567","Facundo",2024,0);

        assertEquals("El sueldo neto sin antiguedad sin hijos esta mal calculado",2000.0 * 0.86,choferPermanente.getSueldoNeto(),0.0001);
    }

    @Test
    public void getSueldoNetoSinAntiguedadConHijosTest() {
        ChoferPermanente.setSueldoBasico(2000.0);
        ChoferPermanente choferPermanente = new ChoferPermanente("1234567","Facundo",2024,3);

        assertEquals("El sueldo neto sin antiguedad con hijos esta mal calculado",2420.0 * 0.86,choferPermanente.getSueldoNeto(),0.0001);
    }
}
