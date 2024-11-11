package modelo_negocio;

import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Before;

public class EmpresaBaseTest {
    @Before
    public void setUp() throws Exception {
        Empresa.getInstance();
    }

    @After
    public void tearDown() throws Exception {
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
    }


}
