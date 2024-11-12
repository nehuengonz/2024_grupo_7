package modelo_negocio;

import excepciones.*;
import modeloDatos.*;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Constantes;
import util.Mensajes;

import java.util.ArrayList;
import java.util.HashMap;

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

    @Test
    public void testAgregarChoferPermanenteExitoso() {
        Chofer nuevoChofer = new ChoferPermanente("41990235", "Martin",2020,2);
        try {
            Empresa.getInstance().agregarChofer(nuevoChofer);
            assertEquals("El DNI del chofer agregado no es el esperado", "41990235", Empresa.getInstance().getChoferes().get("41990235").getDni());
        } catch (ChoferRepetidoException e) {
            fail("Se esperaba que el chofer se agregara exitosamente, pero ocurrió una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testAgregarChoferPermanenteDuplicado() {
        try {
            Empresa.getInstance().agregarChofer(escenario2.getChofer1()); // Ya fue agregado en el escenario
            fail("Se esperaba una excepción ChoferRepetidoException");
        } catch (ChoferRepetidoException e) {
            fail("El chofer con DNI 1234567 ya está registrado");
        }
    }

    @Test
    public void testAgregarChoferTemporarioExitoso() {
        Chofer nuevoChofer = new ChoferTemporario("7777777", "Constantino");
        try {
            Empresa.getInstance().agregarChofer(nuevoChofer);
            assertEquals("El DNI del chofer agregado no es el esperado", "7777777", Empresa.getInstance().getChoferes().get("7777777").getDni());
        } catch (ChoferRepetidoException e) {
            fail("Se esperaba que el chofer se agregara exitosamente, pero ocurrió una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testAgregarChoferTemporarioDuplicado() {
        try {
            Empresa.getInstance().agregarChofer(escenario2.getChofer3()); // Ya fue agregado en el escenario
            fail("Se esperaba una excepción ChoferRepetidoException");
        } catch (ChoferRepetidoException e) {
            fail("El chofer con DNI 11111111 ya está registrado");
        }
    }

    @Test
    public void testAgregarClienteExitoso() {
        try {
            Empresa.getInstance().agregarCliente("Ignacio","123456","Ignacio Florezz");
            assertNotNull(Empresa.getInstance().getClientes().get("Ignacio"));
            assertEquals("Ignacio", Empresa.getInstance().getClientes().get("Ignacio").getNombreUsuario());
        } catch (UsuarioYaExisteException e) {
            fail("Segun el escenario planteado el usuario ClienteNuevo existe en el sistema");
        }

    }

    @Test
    public void testAgregarClienteRepetido() {
        try {
            Empresa.getInstance().agregarCliente("facundo","123","Facundo");
            fail("Se esperaba una excepción UsuarioYaExisteException");
        } catch (UsuarioYaExisteException e) {
            assertEquals("No esta bien el mensaje",e.getMessage(), Mensajes.USUARIO_REPETIDO.getValor());
        }
    }

    @Test
    public void testAgregarVehiculoMotoExitoso() {
        Vehiculo nuevoVehiculo = new Moto("XYZ789");
        try {
            Empresa.getInstance().agregarVehiculo(nuevoVehiculo);
            assertEquals("La patente del vehículo agregado no es la esperada", "XYZ789", Empresa.getInstance().getVehiculos().get("XYZ789").getPatente());
        } catch (VehiculoRepetidoException e) {
            fail("Se esperaba que el vehículo se agregara exitosamente, pero ocurrió una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testAgregarVehiculoMotoDuplicado() {
        try {
            Empresa.getInstance().agregarVehiculo(Empresa.getInstance().getVehiculos().get("pat333")); // Ya fue agregado en el escenario
            fail("Se esperaba una excepción VehiculoRepetidoException");
        } catch (VehiculoRepetidoException e) {
            fail("El vehiculo Moto con patente pat333 ya está registrado");
        }
    }

    @Test
    public void testAgregarVehiculoAutoExitoso() {
        Vehiculo nuevoVehiculo = new Auto("ASD789",4,true);
        try {
            Empresa.getInstance().agregarVehiculo(nuevoVehiculo);
            assertEquals("La patente del vehículo agregado no es la esperada", "ASD789", Empresa.getInstance().getVehiculos().get("ASD789").getPatente());
        } catch (VehiculoRepetidoException e) {
            fail("Se esperaba que el vehículo se agregara exitosamente, pero ocurrió una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testAgregarVehiculoAutoDuplicado() {
        try {
            Empresa.getInstance().agregarVehiculo(Empresa.getInstance().getVehiculos().get("abc123")); // Ya fue agregado en el escenario
            fail("Se esperaba una excepción VehiculoRepetidoException");
        } catch (VehiculoRepetidoException e) {
            fail("El vehiculo con patente abc123 ya está registrado");
        }
    }

    @Test
    public void testAgregarVehiculoCombiExitoso() {
        Vehiculo nuevoVehiculo = new Combi("DDD789",6,false);
        try {
            Empresa.getInstance().agregarVehiculo(nuevoVehiculo);
            assertEquals("La patente del vehículo agregado no es la esperada", "DDD789", Empresa.getInstance().getVehiculos().get("DDD789").getPatente());
        } catch (VehiculoRepetidoException e) {
            fail("Se esperaba que el vehículo se agregara exitosamente, pero ocurrió una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testAgregarVehiculoCombiDuplicado() {
        try {
            Empresa.getInstance().agregarVehiculo(Empresa.getInstance().getVehiculos().get("combi222")); // Ya fue agregado en el escenario
            fail("Se esperaba una excepción VehiculoRepetidoException");
        } catch (VehiculoRepetidoException e) {
            fail("El vehiculo con patente combi222 ya está registrado");
        }
    }

    @Test
    public void testGetSalariosTotales() {
        double total=0;
        total= Empresa.getInstance().getChoferes().get("1234567").getSueldoNeto() +
                Empresa.getInstance().getChoferes().get("1234568").getSueldoNeto() +
                Empresa.getInstance().getChoferes().get("11111111").getSueldoNeto() +
                Empresa.getInstance().getChoferes().get("22222222").getSueldoNeto();
        assertEquals("el salario total deveria ser ",total, Empresa.getInstance().getTotalSalarios(),0.0001);
    }

    @Test
    public void getSalariosTest() {
        try{
            Assert.assertEquals("",2003800.0,Empresa.getInstance().getTotalSalarios(),0.001);
        }catch (Exception e){
            fail("No deberia lanzar ninguna excepcion");
        }
    }

    @Test
    public void testVehiculosOrdenadosPorPedido() {
        Cliente cliente = Empresa.getInstance().getClientes().get("facundo");
        Pedido pedido = Empresa.getInstance().getPedidos().get(cliente);

        ArrayList<Vehiculo> vehiculosOrdenados = Empresa.getInstance().vehiculosOrdenadosPorPedido(pedido);

        assertNotNull("La lista de vehículos ordenados no debería ser nula.", vehiculosOrdenados);

        assertEquals("Se esperaban 2 vehículos ordenados.", 2, vehiculosOrdenados.size());

        assertEquals("El vehículo con mayor puntaje debe ser ABC123.", "abc123", vehiculosOrdenados.get(0).getPatente());
        assertEquals("El segundo vehículo con mayor puntaje debe ser combi111.", "combi111", vehiculosOrdenados.get(1).getPatente());
    }

    @Test
    public void testVehiculosOrdenadosPorPedidoSinVehiculoHabilitado() {
        Cliente cliente = Empresa.getInstance().getClientes().get("facundo");
        Pedido pedido = Empresa.getInstance().getPedidos().get(cliente);

        ArrayList<Vehiculo> vehiculosOrdenados = Empresa.getInstance().vehiculosOrdenadosPorPedido(pedido);

        assertNotNull("La lista de vehículos ordenados no debería ser nula.", vehiculosOrdenados);

        assertEquals("Se esperaban 2 vehículos ordenados.", 2, vehiculosOrdenados.size());

        assertEquals("El vehículo con mayor puntaje debe ser ABC123.", "abc123", vehiculosOrdenados.get(0).getPatente());
        assertEquals("El segundo vehículo con mayor puntaje debe ser combi111.", "combi111", vehiculosOrdenados.get(1).getPatente());
    }

    @Test
    public void validarPedidoExisteVehiculoTest() {
        HashMap<Cliente, Pedido> pedidos = Empresa.getInstance().getPedidos();
        Cliente cliente = Empresa.getInstance().getClientes().get("facundo");
        Pedido pedidoValidar = pedidos.get(cliente);

        Assert.assertTrue("Se supone que existe un vehiculo que lo puede satisfacer",Empresa.getInstance().validarPedido(pedidoValidar));

        Cliente cliente1 = Empresa.getInstance().getClientes().get("nehuen");
        Pedido pedidoValidar1 = pedidos.get(cliente1);

        Assert.assertTrue("Se supone que existe un vehiculo que lo puede satisfacer",Empresa.getInstance().validarPedido(pedidoValidar1));

        Cliente cliente2 = Empresa.getInstance().getClientes().get("thiago");
        Pedido pedidoValidar2 = pedidos.get(cliente2);

        Assert.assertTrue("Se supone que existe un vehiculo que lo puede satisfacer",Empresa.getInstance().validarPedido(pedidoValidar2));
    }

    @Test
    public void testAgregarPedidoClienteConPedidoIniciado() {
        Cliente cliente = Empresa.getInstance().getClientes().get("thiago");
        Pedido p = new Pedido(cliente, 1, true, true, 2, Constantes.ZONA_PELIGROSA);
        try {
            Empresa.getInstance().agregarPedido(p);
            fail("Deberia tirar excepcion ClienteConPedidoPendienteException");
        } catch (ClienteConPedidoPendienteException ex) {
            // Debe ir por aquí
            assertEquals("La excepcion no tira el mensaje correcto", "Cliente con pedido pendiente"
                    , Mensajes.CLIENTE_CON_PEDIDO_PENDIENTE.getValor());
        } catch (ClienteNoExisteException | SinVehiculoParaPedidoException
                 | ClienteConViajePendienteException ex) {
            fail("No debería tirar excepcion " + ex.getMessage());
        }
    }
    @Test
    public void testAgregarPedidoClienteNoExiste() {
        Cliente cliente = new Cliente("usuarioNuevo", "123", "Usuario");
        Pedido p = new Pedido(cliente, 1, true, true, 2, Constantes.ZONA_PELIGROSA);
        try {
            Empresa.getInstance().agregarPedido(p);
            fail("Deberia tirar excepcion ClienteNoExisteException");
        } catch (ClienteNoExisteException ex) {
            // Debe ir por aquí
            assertEquals("La excepcion no tira el mensaje correcto", "EL Cliente no esta registrado"
                    , Mensajes.CLIENTE_NO_EXISTE.getValor());
        } catch (ClienteConPedidoPendienteException | SinVehiculoParaPedidoException
                 | ClienteConViajePendienteException ex) {
            fail("No debería tirar excepcion " + ex.getMessage());
        }
    }

}
