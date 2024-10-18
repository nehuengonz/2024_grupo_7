package modelo_negocio;

import excepciones.UsuarioYaExisteException;
import excepciones.VehiculoRepetidoException;
import modeloDatos.Auto;
import modeloNegocio.Empresa;

public class EscenarioEmpresa {
    public Empresa empresa;

    public EscenarioEmpresa(Empresa e){
    	empresa = e;
    }

    public void setearEscenario1() throws UsuarioYaExisteException, VehiculoRepetidoException{
		Auto v = new Auto("ABC 123", 4, true);
		empresa.agregarCliente("usuarioNuevo", "password", "Pedro Alfonso");
		empresa.agregarVehiculo(v);
    }

    public void tearDown(){
    }
    
    
}
