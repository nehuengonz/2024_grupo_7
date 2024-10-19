package modelo_negocio;

import excepciones.*;
import modeloDatos.*;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.Constantes;

import java.util.HashMap;

import static org.junit.Assert.fail;

public class CrearViajeEscenarioBaseTest {
    @Before
    public void setUp() throws Exception {
        Empresa.getInstance();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void crearViajeTodoValido(){
        Cliente cliente = new Cliente("franco","1111","Franco Colapinto");
        HashMap<String,Cliente> clientes = new HashMap<>();
        clientes.put(cliente.getNombreUsuario(),cliente);
        Empresa.getInstance().setClientes(clientes);

        ChoferPermanente chofer = new ChoferPermanente("12345678","Tomas",2019,2);
        Pedido pedido = new Pedido(cliente,3,true,false,10, Constantes.ZONA_SIN_ASFALTAR);
        Vehiculo vehiculo = new Auto("abc111",4,true);

        try {
            Empresa.getInstance().crearViaje(pedido,chofer,vehiculo);
        } catch (ChoferNoDisponibleException e) {
            fail("no tiene que tirar chofer no disponible");
        } catch (ClienteConViajePendienteException e) {
            fail("no tiene que tirar cliente con viaje pendiente");
        } catch (PedidoInexistenteException e) {
            fail("no tiene que tirar pedido inexistente");
        } catch (VehiculoNoDisponibleException e) {
            fail("no tiene que tirar vehiculo no disponible");
        } catch (VehiculoNoValidoException e) {
            fail("no tiene que tirar vehiculo no validos");
        }

    }
}
