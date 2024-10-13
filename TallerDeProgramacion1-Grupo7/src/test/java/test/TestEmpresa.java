package test;

import static org.junit.Assert.*;

import excepciones.ChoferRepetidoException;
import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloNegocio.Empresa;
import util.Constantes;

public class TestEmpresa {
    private Empresa empresa;
    private Chofer chofer1;
    private Chofer chofer2;
    private Chofer chofer3; // Duplicado de chofer1

    @Before
    public void setUp() throws Exception {
        empresa = Empresa.getInstance();
    }

    @Test
    public void testAgregarChofer() throws ChoferRepetidoException {
        Chofer chofer1 = new ChoferTemporario("12345678", "Sofia P");
        empresa.agregarChofer(chofer1);

        assertTrue(empresa.getChoferes().containsValue(chofer1));
        assertTrue(empresa.getChoferesDesocupados().contains(chofer1));
    }

    @Test
    public void testAgregarChoferRepetido() throws ChoferRepetidoException {
        Chofer chofer1 = new ChoferTemporario("12345678", "Sofia P");

        empresa.agregarChofer(chofer1);
        empresa.agregarChofer(chofer1);
    }



}
