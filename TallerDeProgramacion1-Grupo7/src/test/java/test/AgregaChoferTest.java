package test;

import excepciones.ChoferRepetidoException;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.ChoferTemporario;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import static org.junit.Assert.*;

public class AgregaChoferTest {
    private Empresa empresa;

    public AgregaChoferTest() {
    }

    public static void main(String[] args) {
        String[] args2 = { Empresa.class.getName() };
        JUnitCore.main(args2);
    }

    @Before
    public void setUp() throws Exception {
        Empresa.getInstance();
        Chofer chofer = new ChoferTemporario("1234567","Roberto") ;
        Chofer chofer2 = new ChoferPermanente("1234568","Juan",2024,2) ;
        Chofer chofer3 = new ChoferPermanente("1234569","Facundo",2024,3) ;

        Empresa.getInstance().agregarChofer(chofer);
        Empresa.getInstance().agregarChofer(chofer2);
        Empresa.getInstance().agregarChofer(chofer3);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void agregaChofer1_3 (){
        Chofer chofer = new ChoferPermanente("1234570","Marco",2022,2) ;

        try {
            Empresa.getInstance().agregarChofer(chofer);
            Assert.assertTrue("",Empresa.getInstance().getChoferes().containsKey("1234570"));
        } catch (ChoferRepetidoException e) {
            fail("No deberia arrjar esta excepcion porque no existe este chofer en la lista");
        }
    }

    @Test
    public void agregaChofer2 (){
        Chofer chofer = null;

        try {
            Empresa.getInstance().agregarChofer(chofer);
            fail("NO SE PUEDE AGREGAR UN CHOFER NULO");

        } catch (ChoferRepetidoException e) {
            fail("No deberia arrjar esta excepcion");
        }catch (Exception e) {
            fail("Deberia existir una excepcion especifica");
        }
    }

    @Test
    public void agregaChofer1_4 (){
        Chofer chofer = new ChoferPermanente("1234570","Marco",2022,2) ;

        try {
            Empresa.getInstance().agregarChofer(chofer);
            fail("Se agrega un chofer repetido");
        } catch (ChoferRepetidoException e) {
            Assert.assertTrue("",Empresa.getInstance().getChoferes().containsKey("1234570"));
        }
    }


}
