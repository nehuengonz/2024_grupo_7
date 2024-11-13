package controlador;

import java.io.File;

import modeloNegocio.Empresa;
import modelo_negocio.Escenario6;
import persistencia.EmpresaDTO;
import persistencia.PersistenciaBIN;
import persistencia.UtilPersistencia;

public class ArchivoEscenario6 {
	Escenario6 escenario6;
	PersistenciaBIN persistencia;
	
	public ArchivoEscenario6() {
	}
	
	public void setUp() throws Exception {
        escenario6 = new Escenario6();
        escenario6.setup();

        EmpresaDTO empresa = UtilPersistencia.EmpresaDtoFromEmpresa();

        persistencia = new PersistenciaBIN();
        persistencia.abrirOutput("empresaIntegracion.bin");
        persistencia.escribir(empresa);
        persistencia.cerrarOutput();


        escenario6.teardown();
    }

    public void tearDown() throws Exception {
        File archivo = new File("empresaIntegracion.bin");
        if(archivo.exists()){
            archivo.delete();
        }
        escenario6.teardown();
    }

}
