package test;

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


        Empresa.getInstance().getHistorialViajeCliente(Empresa.getInstance().getClientes().get("facundo")).clear();
        Empresa.getInstance().getHistorialViajeCliente(Empresa.getInstance().getClientes().get("thiago")).clear();
        Empresa.getInstance().getHistorialViajeCliente(Empresa.getInstance().getClientes().get("nehuen")).clear();
        Empresa.getInstance().getChoferes().clear();
        Empresa.getInstance().getVehiculos().clear();
        Empresa.getInstance().getVehiculosDesocupados().clear();
        Empresa.getInstance().getChoferesDesocupados().clear();
        Empresa.getInstance().getClientes().clear();
        Empresa.getInstance().getPedidos().clear();
        Empresa.getInstance().getViajesIniciados().clear();
        Empresa.getInstance().getViajesTerminados().clear();
    }

    public void tearDown() throws Exception {
        File archivo = new File("empresaIntegracion.bin");
        if(archivo.exists()){
            archivo.delete();
        }
    }

}
