package test;

import modeloDatos.*;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
import persistencia.EmpresaDTO;

import java.io.File;
import java.io.IOException;

public class TestPersistencia {
    private IPersistencia persistencia;
    private EmpresaDTO empresa;

    @Before
    public void setUp() throws Exception {
        // Inicializar la persistencia y la empresa
        persistencia = new PersistenciaBIN();
        empresa = new EmpresaDTO();
        llenaEmpresa(empresa);  // Llenar la empresa con algunos datos de prueba
    }

    @After
    public void tearDown() {
        // Aquí puedes eliminar el archivo de prueba si lo deseas
        File archivo = new File("empresaPrueba.bin");
        if (archivo.exists()) {
            archivo.delete();
        }
    }

    @Test
    public void testCrearArchivo() {
        try {
            persistencia.abrirOutput("empresaPrueba.bin");
            File archivo = new File("empresaPrueba.bin");
            Assert.assertTrue("Debería existir el archivo de la empresa", archivo.exists());
            persistencia.cerrarOutput();  // Asegúrate de cerrar el output después
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
            Assert.fail("Error en la escritura vacía: " + e.getMessage());
        }
    }

    @Test
    public void testEscrituraConEmpresa() {
        try {
            persistencia.abrirOutput("empresaPrueba.bin");
            persistencia.escribir(empresa);
            persistencia.cerrarOutput();
        } catch (IOException e) {
            Assert.fail("Error en la escritura de la empresa: " + e.getMessage());
        }
    }

    @Test
    public void despersistirSinArchivo() {
        try {
            persistencia.abrirInput("archivoInexistente.bin");
            persistencia.leer(); // Intento de lectura
            Assert.fail("Debería dar error ya que no existe ese archivo");
        } catch (IOException e) {
            Assert.assertTrue("Debería lanzarse una FileNotFoundException", e instanceof java.io.FileNotFoundException);
        } catch (ClassNotFoundException e) {
            Assert.fail("Error inesperado: " + e.getMessage());
        }
    }

    @Test
    public void despersistirConArchivo() {
        try {
            persistencia.abrirOutput("empresaPrueba.bin");
            persistencia.escribir(empresa);
            persistencia.cerrarOutput();

            persistencia.abrirInput("empresaPrueba.bin");
            EmpresaDTO empresaLeida = (EmpresaDTO) persistencia.leer();
            persistencia.cerrarInput();

            Assert.assertNotNull("La empresa leída no debería ser nula", empresaLeida);

            Assert.assertEquals("La cantidad de clientes no coincide", this.empresa.getClientes().size(), empresaLeida.getClientes().size());

            for (String nombreUsuario : this.empresa.getClientes().keySet()) {
                Cliente clienteOriginal = this.empresa.getClientes().get(nombreUsuario);
                Cliente clienteLeido = empresaLeida.getClientes().get(nombreUsuario);
                Assert.assertEquals("El nombre de usuario de cliente no coincide", clienteOriginal.getNombreUsuario(),clienteLeido.getNombreUsuario());
                Assert.assertEquals("El nombre de cliente no coincide", clienteOriginal.getNombreReal(),clienteLeido.getNombreReal());
                Assert.assertEquals("La contrasenia de cliente no coincide", clienteOriginal.getPass(),clienteLeido.getPass());
            }

            Assert.assertEquals("La cantidad de vehiculos no coincide", this.empresa.getVehiculos().size(), empresaLeida.getVehiculos().size());

            for(String patente : this.empresa.getVehiculos().keySet()){
                Vehiculo vehiculoOriginal = this.empresa.getVehiculos().get(patente);
                Vehiculo vehiculoLeido = empresaLeida.getVehiculos().get(patente);
                Assert.assertEquals("La patente no coincide", vehiculoOriginal.getPatente(),vehiculoLeido.getPatente());
                Assert.assertEquals("La cantidad de plazas no coincide", vehiculoOriginal.getCantidadPlazas(),vehiculoLeido.getCantidadPlazas());
            }

            Assert.assertEquals("La cantidad de choferes no coincide", this.empresa.getChoferes().size(), empresaLeida.getChoferes().size());

            for(String dni : this.empresa.getChoferes().keySet()){
                Chofer choferOriginal = this.empresa.getChoferes().get(dni);
                Chofer choferLeido = empresaLeida.getChoferes().get(dni);
                Assert.assertEquals("La patente no coincide", choferOriginal.getDni(),choferLeido.getDni());
                Assert.assertEquals("El nombre del chofer no coincide", choferOriginal.getNombre(),choferLeido.getNombre());
                Assert.assertEquals("El sueldo bruto del chofer no coincide", choferOriginal.getSueldoBruto(),choferLeido.getSueldoBruto());
                Assert.assertEquals("El sueldo neto del chofer no coincide", choferOriginal.getSueldoNeto(),choferLeido.getSueldoNeto());
            }

        } catch (IOException | ClassNotFoundException e) {
            Assert.fail("No debería dar error ya que el archivo existe: " + e.getMessage());
        }
    }


    private void llenaEmpresa(EmpresaDTO empresa) {
        empresa.getClientes().put("Sofia123", new Cliente("Sofia123", "123456", "Sofia"));
        empresa.getChoferes().put("87654321", new ChoferTemporario("87654321", "Carlos P"));
        empresa.getVehiculos().put("ABC123", new Auto("ABC123", 4, true));
    }
}
