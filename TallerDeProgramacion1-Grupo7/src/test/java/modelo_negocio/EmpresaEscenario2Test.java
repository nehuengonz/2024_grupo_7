package modelo_negocio;

import excepciones.*;
import modeloDatos.Chofer;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Constantes;
import util.Mensajes;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class EmpresaEscenario2Test {
    private Escenario2 escenario2;
    @Before
    public void setUp() throws Exception {
        escenario2 = new Escenario2();
        escenario2.setup();
    }

    @After
    public void tearDown() throws Exception {
        escenario2.tearDown();
    }


    @Test
    public void loginClienteCorrecto(){
        try{
            Empresa.getInstance().login("facundo", "123");
            assertEquals("facundo", Empresa.getInstance().getUsuarioLogeado().getNombreUsuario());
        } catch (UsuarioNoExisteException e) {
            fail("Segun el escenario planteado el usuario facundo existe");
        } catch (PasswordErroneaException e) {
            fail("La contrasenia proporcionada es la correcta");
        }
    }

    @Test
    public void loginClienteNombreCorrectoContraMal(){
        try{
            Empresa.getInstance().login("thiago", "1234");
            fail("la contrasenia que se proporciono no era la correcta");
        } catch (UsuarioNoExisteException e) {
            fail("El metodo no deberia arrojar esta excepcion");
        } catch (PasswordErroneaException e) {
            assertEquals("El mensaje de la excepcion no es correcto ",e.getMessage(), Mensajes.PASS_ERRONEO.getValor());
            assertEquals("El mensaje de la excepcion no es correcto ",e.getUsuarioPretendido(), "thiago");
            assertEquals("El mensaje de la excepcion no es correcto ",e.getPasswordPretendida(), "1234");
        }
    }

    @Test
    public void loginClienteUsuarioMal(){
        try{
            Empresa.getInstance().login("nehu", "4321");
            fail("el nombre de usuario no existe");
        } catch (UsuarioNoExisteException e) {
            assertEquals("El mensaje de la excepcion no es correcto ",e.getMessage(), Mensajes.USUARIO_DESCONOCIDO.getValor());
            assertEquals("El mensaje de la excepcion no es correcto ",e.getUsuarioPretendido(), "nehu");
        } catch (PasswordErroneaException e) {
            fail("El metodo no deberia arrojar esta excepcion");
        }
    }

    @Test
    public void loginClienteDespuesAdmin(){
        try{
            Empresa.getInstance().login("nehuen","4567");
            Empresa.getInstance().login("admin","admin");
            Assert.assertTrue("Se logueo un Administrador",Empresa.getInstance().isAdmin());
        } catch (UsuarioNoExisteException | PasswordErroneaException e) {
            fail("El metodo no deberia arrojar esta excepcion");
        }
    }

    @Test
    public void logoutTest() {
        Empresa.getInstance().logout();

        assertNull("El no se deslogueo el administrador", Empresa.getInstance().getUsuarioLogeado());
    }

    @Test
    public void logoutUsuarioTest() {
        try {
            Empresa.getInstance().login("facundo","123");
            Empresa.getInstance().logout();

            assertNull("El no se deslogueo el usuario", Empresa.getInstance().getUsuarioLogeado());
        } catch (UsuarioNoExisteException | PasswordErroneaException e ) {
            fail("no deberia tirar ninguna excepcion");
        }
    }
    @Test
    public void creoViajeValido() {
        Cliente c = Empresa.getInstance().getClientes().get("facundo");
        Pedido p = Empresa.getInstance().getPedidoDeCliente(c);

        Vehiculo v = Empresa.getInstance().getVehiculos().get("abc123");

        Chofer chofer = Empresa.getInstance().getChoferes().get("1234567");

        try {
            Empresa.getInstance().crearViaje(p, chofer, v);
            assertEquals("El viaje se supone que es valido",Empresa.getInstance().getViajesIniciados().get(c).getPedido().getCliente().getNombreUsuario()
                    ,c.getNombreUsuario());
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

    @Test
    public void creoViajeConVehiculoNoValido() {
        Cliente c = Empresa.getInstance().getClientes().get("facundo");
        Pedido p = Empresa.getInstance().getPedidoDeCliente(c);

        Vehiculo v = Empresa.getInstance().getVehiculos().get("pat333");

        Chofer chofer = Empresa.getInstance().getChoferes().get("1234567");

        try {
            Empresa.getInstance().crearViaje(p, chofer, v);
            fail("no tiene que continuar la ejecucion");
        } catch (ChoferNoDisponibleException e) {
            fail("no tiene que tirar chofer no disponible");
        } catch (ClienteConViajePendienteException e) {
            fail("no tiene que tirar cliente con viaje pendiente");
        } catch (PedidoInexistenteException e) {
            fail("no tiene que tirar pedido inexistente");
        } catch (VehiculoNoDisponibleException e) {
            fail("no tiene que tirar vehiculo no disponible");
        } catch (VehiculoNoValidoException e) {
            assertEquals("El mensaje de la excepcion no es correcto ", e.getMessage(), Mensajes.VEHICULO_NO_VALIDO.getValor());
            assertEquals("El mensaje de la excepcion no es correcto no muestra vehiculo ", e.getVehiculo().getPatente(), "pat333");
            assertEquals("El mensaje de la excepcion no es correcto no muestra pedido", e.getPedido().getCliente().getNombreUsuario(), c.getNombreUsuario());
        }
    }

    @Test
    public void creoViajeConPedidoInexistente() {
        Cliente c = Empresa.getInstance().getClientes().get("facundo");
        Pedido p = new Pedido(c, 4, true, true, 10, Constantes.ZONA_STANDARD);

        Vehiculo v = Empresa.getInstance().getVehiculos().get("abc123");

        Chofer chofer = Empresa.getInstance().getChoferes().get("1234567");

        try {
            Empresa.getInstance().crearViaje(p, chofer, v);
            fail("no tiene que continuar la ejecucion");
        } catch (ChoferNoDisponibleException e) {
            fail("no tiene que tirar chofer no disponible");
        } catch (ClienteConViajePendienteException e) {
            fail("no tiene que tirar cliente con viaje pendiente");
        } catch (PedidoInexistenteException e) {
            assertEquals("El mensaje de la excepcion no es correcto ", e.getMessage(), Mensajes.PEDIDO_INEXISTENTE.getValor());
            assertEquals("El mensaje de la excepcion no es correcto no muestra pedido", e.getPedido().getCliente().getNombreUsuario(), c.getNombreUsuario());
        } catch (VehiculoNoDisponibleException e) {
            fail("no tiene que tirar vehiculo no disponible");
        } catch (VehiculoNoValidoException e) {
            fail("no tiene que tirar vehiculo no valido");
        }
    }

    @Test
    public void creoViajeConViajePendiente() throws ClienteNoExisteException, SinVehiculoParaPedidoException, ClienteConPedidoPendienteException {
        Cliente c = Empresa.getInstance().getClientes().get("thiago");
        Pedido p = Empresa.getInstance().getPedidoDeCliente(c);
        Pedido p2 = new Pedido(c, 4, true, true, 10, Constantes.ZONA_STANDARD);


        Vehiculo v = Empresa.getInstance().getVehiculos().get("pat333");
        Vehiculo v1 = Empresa.getInstance().getVehiculos().get("abc123");

        Chofer chofer = Empresa.getInstance().getChoferes().get("1234567");
        Chofer chofer1 = Empresa.getInstance().getChoferes().get("1234568");

        try {
            Empresa.getInstance().crearViaje(p, chofer, v);
            Empresa.getInstance().agregarPedido(p2);
            Empresa.getInstance().crearViaje(p2, chofer1, v1);
            fail("no tiene que continuar la ejecucion");
        } catch (ChoferNoDisponibleException e) {
            fail("no tiene que tirar chofer no disponible");
        } catch (ClienteConViajePendienteException e) {
            assertEquals("El mensaje de la excepcion no es correcto ", e.getMessage(), Mensajes.CLIENTE_CON_VIAJE_PENDIENTE.getValor());
        } catch (PedidoInexistenteException e) {
            fail("no tiene que tirar pedido inexistente");
        } catch (VehiculoNoDisponibleException e) {
            fail("no tiene que tirar vehiculo no disponible");
        } catch (VehiculoNoValidoException e) {
            fail("no tiene que tirar vehiculo no valido");
        }
    }

    @Test
    public void creoViajeConChoferNoDisponible(){
        Cliente c = Empresa.getInstance().getClientes().get("thiago");
        Pedido p = Empresa.getInstance().getPedidoDeCliente(c);

        Cliente c1 = Empresa.getInstance().getClientes().get("facundo");
        Pedido p1 = Empresa.getInstance().getPedidoDeCliente(c1);

        Vehiculo v = Empresa.getInstance().getVehiculos().get("pat333");
        Vehiculo v1 = Empresa.getInstance().getVehiculos().get("abc123");

        Chofer chofer = Empresa.getInstance().getChoferes().get("1234567");
        //Chofer chofer1 = Empresa.getInstance().getChoferes().get("1234568");

        try {
            Empresa.getInstance().crearViaje(p, chofer, v);
            Empresa.getInstance().crearViaje(p1, chofer, v1);
            fail("no tiene que continuar la ejecucion");
        } catch (ChoferNoDisponibleException e) {
            assertEquals("El mensaje de la excepcion no es correcto ", e.getMessage(), Mensajes.CHOFER_NO_DISPONIBLE.getValor());
            assertEquals("El chofer de la excepcion no es correcto ", e.getChofer().getDni(), chofer.getDni());
        } catch (ClienteConViajePendienteException e) {
            fail("no tiene que tirar viaje pendiente");
        } catch (PedidoInexistenteException e) {
            fail("no tiene que tirar pedido inexistente");
        } catch (VehiculoNoDisponibleException e) {
            fail("no tiene que tirar vehiculo no disponible");
        } catch (VehiculoNoValidoException e) {
            fail("no tiene que tirar vehiculo no valido");
        }
    }

    @Test
    public void creoViajeConVehiculoNoDisponible(){
        Cliente c = Empresa.getInstance().getClientes().get("thiago");
        Pedido p = Empresa.getInstance().getPedidoDeCliente(c);

        Cliente c1 = Empresa.getInstance().getClientes().get("facundo");
        Pedido p1 = Empresa.getInstance().getPedidoDeCliente(c1);

        Vehiculo v = Empresa.getInstance().getVehiculos().get("abc123");

        Chofer chofer = Empresa.getInstance().getChoferes().get("1234567");
        Chofer chofer1 = Empresa.getInstance().getChoferes().get("1234568");

        try {
            Empresa.getInstance().crearViaje(p, chofer, v);
            Empresa.getInstance().crearViaje(p1, chofer1, v);
            fail("no tiene que continuar la ejecucion");
        } catch (ChoferNoDisponibleException e) {
            fail("no tiene que tirar chofer no disponible");
        } catch (ClienteConViajePendienteException e) {
            fail("no tiene que tirar viaje pendiente");
        } catch (PedidoInexistenteException e) {
            fail("no tiene que tirar pedido inexistente");
        } catch (VehiculoNoDisponibleException e) {
            assertEquals("El mensaje de la excepcion no es correcto ", e.getMessage(), Mensajes.VEHICULO_NO_DISPONIBLE.getValor());
            assertEquals("la patente de la excepcion no es correcta ", e.getVehiculo().getPatente(), v.getPatente());
        } catch (VehiculoNoValidoException e) {
            fail("no tiene que tirar vehiculo no valido");
        }
    }

}
