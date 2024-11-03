package test;

import modeloNegocio.Empresa;
import modelo_negocio.Escenario2;
import modelo_negocio.Escenario3;
import org.junit.After;
import org.junit.Before;
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
