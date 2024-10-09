package test;

import modeloDatos.*;
import org.junit.Assert;
import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
import persistencia.EmpresaDTO;

public class TestPersistencia {
    private IPersistencia persistencia = new PersistenciaBIN();
    private EmpresaDTO empresa = new EmpresaDTO();

    @Before
    public void setUp() throws Exception {
        llenaEmpresa(this.empresa);
    }

    @After
    public void tearDown() throws Exception {
        File archivo = new File("empresaPrueba.bin");
        if (archivo.exists()) {
            archivo.delete();
        }
    }

    @Test
    public void testCrearArchivo() {
        try {
            persistencia.abrirOutput("empresaPrueba.bin");
            persistencia.escribir(empresa);
            persistencia.cerrarOutput();
            File archivo = new File("empresaPrueba.bin");
            Assert.assertTrue("Debería existir el archivo de la empresa", archivo.exists());
        } catch (IOException e) {
            Assert.fail("No debería fallar: " + e.getMessage());
        }
    }

    @Test
    public void testEscrituraEmpresaVacia() {
        try {
            persistencia.abrirOutput("empresaPrueba.bin");
            EmpresaDTO empresaVacia = new EmpresaDTO();
            persistencia.escribir(empresaVacia);
            persistencia.cerrarOutput();
        } catch (IOException e) {
            Assert.fail("Error en la escritura vacía");
        }
    }

    @Test
    public void testEscrituraConEmpresa() {
        try {
            persistencia.abrirOutput("empresaPrueba.bin");
            persistencia.escribir(this.empresa);
            persistencia.cerrarOutput();
        } catch (IOException e) {
            Assert.fail("Error en la escritura de la empresa");
        }
    }

    @Test
    public void despersistirSinArchivo() {
        try {
            persistencia.abrirInput("archivoInexistente.bin");
            EmpresaDTO empresaLeida = (EmpresaDTO) persistencia.leer();
            Assert.fail("Debería dar error ya que no existe ese archivo");
        } catch (IOException | ClassNotFoundException e) {
            Assert.fail("Error inesperado: " + e.getMessage());
        }
    }

    @Test
    public void despersistirConArchivo() {
        try {
            persistencia.abrirInput("empresaPrueba.bin");
            EmpresaDTO empresaLeida = (EmpresaDTO) persistencia.leer();
            persistencia.cerrarInput();

            Assert.assertNotNull("La empresa no debería ser nula", empresaLeida);
            Assert.assertEquals("La cantidad de clientes no coincide", this.empresa.getClientes().size(), empresaLeida.getClientes().size());

            for (String dni : this.empresa.getClientes().keySet()) {
                Cliente clienteOriginal = this.empresa.getClientes().get(dni);
                Cliente clienteLeido = empresaLeida.getClientes().get(dni);
                Assert.assertEquals("El cliente leido no coincide", clienteOriginal, clienteLeido);
            }

        } catch (IOException | ClassNotFoundException e) {
            Assert.fail("No debería dar error ya que el archivo existe: " + e.getMessage());
        }
    }


    private void llenaEmpresa(EmpresaDTO empresa) {
        empresa.getClientes().put("12345678", new Cliente("Sofia123", "123456", "Sofia"));
        empresa.getChoferes().put("87654321", new ChoferTemporario("87654321", "Carlos P"));
        empresa.getVehiculos().put("ABC123", new Auto("ABC123", 4, true));
    }
}
