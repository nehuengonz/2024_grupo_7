package controlador;

import modelo_negocio.Escenario3;
import persistencia.EmpresaDTO;
import persistencia.PersistenciaBIN;
import persistencia.UtilPersistencia;

import java.io.File;

public class ArchivoEscenario3 {

    Escenario3 escenario3;
    PersistenciaBIN persistencia;
    public ArchivoEscenario3() {
    }

    public void setUp() throws Exception {
        escenario3 = new Escenario3();
        escenario3.setup();

        EmpresaDTO empresa = UtilPersistencia.EmpresaDtoFromEmpresa();

        persistencia = new PersistenciaBIN();
        persistencia.abrirOutput("empresaIntegracion.bin");
        persistencia.escribir(empresa);
        persistencia.cerrarOutput();



        escenario3.tearDown();
    }

    public void tearDown() throws Exception {
        File archivo = new File("empresaIntegracion.bin");
        if(archivo.exists()){
            archivo.delete();
        }
        escenario3.tearDown();
    }
}
