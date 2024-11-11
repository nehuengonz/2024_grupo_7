package controlador;

import modelo_negocio.Escenario2;
import persistencia.EmpresaDTO;
import persistencia.PersistenciaBIN;
import persistencia.UtilPersistencia;

import java.io.File;

public class ArchivoEscenario2 {

    Escenario2 escenario2;
    PersistenciaBIN persistencia;
    public ArchivoEscenario2() {
    }

    public void setUp() throws Exception {
        escenario2 = new Escenario2();
        escenario2.setup();

        EmpresaDTO empresa = UtilPersistencia.EmpresaDtoFromEmpresa();

        persistencia = new PersistenciaBIN();
        persistencia.abrirOutput("empresaIntegracion.bin");
        persistencia.escribir(empresa);
        persistencia.cerrarOutput();


        escenario2.tearDown();
    }


    public void tearDown() throws Exception {
        File archivo = new File("empresaIntegracion.bin");
        if(archivo.exists()){
            archivo.delete();
        }
        escenario2.tearDown();
    }
}
