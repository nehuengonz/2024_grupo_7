package persistenciaTest;

import org.junit.Before;
import org.junit.Test;
import persistencia.EmpresaDTO;
import persistencia.PersistenciaBIN;
import util.Mensajes;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class PersistenciaTest {
    PersistenciaBIN persistenciaBIN;
    EmpresaDTO empresaDTO;
    public PersistenciaTest() {
    }

    @Before
    public void setUp() throws Exception {
        empresaDTO = new EmpresaDTO();
        this.persistenciaBIN = new PersistenciaBIN();
        File archivo = new File("Empresa.bin");
        if(archivo.exists()){
            archivo.delete();
        }
    }

    @Test
    public void crearArchivoTest() {
        try{
            File archivo = new File("Empresa.bin");
            this.persistenciaBIN.abrirOutput("Empresa.bin");
            this.persistenciaBIN.escribir(empresaDTO);
            assertTrue("El archivo no se creo correctamente", archivo.exists());
        }catch (IOException e) {
            fail("el archivo deberia existir");
        }
    }

    @Test
    public void persistirVacioTest() {
        try{
            File archivo = new File("Empresa.bin");
            this.persistenciaBIN.abrirOutput("Empresa.bin");

            this.persistenciaBIN.escribir(empresaDTO);
            this.persistenciaBIN.cerrarOutput();
            this.persistenciaBIN.abrirInput("Empresa.bin");
            EmpresaDTO despersistido = (EmpresaDTO) this.persistenciaBIN.leer();
            this.persistenciaBIN.cerrarOutput();
            assertEquals("Deberian ser la misma instancia", this.empresaDTO, despersistido);
        }catch (IOException e) {
            fail("el archivo deberia existir");
        } catch (ClassNotFoundException e) {
            fail("La clase EmpresaDTO existe y deberia ser leido con ese tipo");
        }
    }

    @Test
    public void despersistirSinArchivoTest() {
        try{
            EmpresaDTO despersistido = (EmpresaDTO) this.persistenciaBIN.leer();
            fail("el no archivo deberia existir");
        }catch (IOException e) {
            assertEquals("EL mensaje no es correcto", e.getMessage(),"FileNotFoundException");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
