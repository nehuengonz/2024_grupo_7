package persistenciaTest;

import modeloDatos.*;
import modeloNegocio.Empresa;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
import persistencia.EmpresaDTO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class TestPersistencia {
    private IPersistencia persistencia;
    private EmpresaDTO empresa;

    @Before
    public void setUp() throws Exception {
        persistencia = new PersistenciaBIN();
        empresa = new EmpresaDTO();
        llenaEmpresa(empresa);

    }

    @After
    public void tearDown() {
        File archivo = new File("empresaPrueba.bin");
        if (archivo.exists()) {
            archivo.delete();
        }
    }

    @Test
    public void testCrearArchivo() {
        try {
            persistencia.abrirOutput("empresaPrueba.bin");
            File archivo = new File("empresaPrueba.bin");
            Assert.assertTrue("Debería existir el archivo de la empresa", archivo.exists());
            persistencia.cerrarOutput();  // Asegúrate de cerrar el output después
        } catch (IOException e) {
            Assert.fail("No debería fallar: " + e.getMessage());
        }
    }

    @Test
    public void testEscrituraEmpresaVacia() {
        try {
            persistencia.abrirOutput("empresaPrueba.bin");
            EmpresaDTO empresaVacia = new EmpresaDTO();
            persistencia.escribir(empresaVacia);
            persistencia.cerrarOutput();
        } catch (IOException e) {
            Assert.fail("Error en la escritura vacía: " + e.getMessage());
        }
    }

    @Test
    public void testEscrituraConEmpresa() {
        try {
            persistencia.abrirOutput("empresaPrueba.bin");
            persistencia.escribir(empresa);
            persistencia.cerrarOutput();
        } catch (IOException e) {
            Assert.fail("Error en la escritura de la empresa: " + e.getMessage());
        }
    }

    @Test
    public void despersistirSinArchivo() {
        try {
            persistencia.abrirInput("archivoInexistente.bin");
            persistencia.leer(); // Intento de lectura
            Assert.fail("Debería dar error ya que no existe ese archivo");
        } catch (IOException e) {
            Assert.assertTrue("Debería lanzarse una FileNotFoundException", e instanceof java.io.FileNotFoundException);
        } catch (ClassNotFoundException e) {
            Assert.fail("Error inesperado: " + e.getMessage());
        }
    }

    @Test
    public void despersistirConArchivo() {
        try {
            persistencia.abrirOutput("empresaPrueba.bin");
            persistencia.escribir(empresa);
            persistencia.cerrarOutput();

            persistencia.abrirInput("empresaPrueba.bin");
            EmpresaDTO empresaLeida = (EmpresaDTO) persistencia.leer();
            persistencia.cerrarInput();


            Assert.assertNotNull("La empresa leída no debería ser nula", empresaLeida);

            Assert.assertEquals("La cantidad de clientes no coincide", this.empresa.getClientes().size(), empresaLeida.getClientes().size());
            for (String nombreUsuario : this.empresa.getClientes().keySet()) {
                Cliente clienteOriginal = this.empresa.getClientes().get(nombreUsuario);
                Cliente clienteLeido = empresaLeida.getClientes().get(nombreUsuario);
                Assert.assertNotNull("El cliente leído no debería ser nulo", clienteLeido);
                Assert.assertEquals("El nombre de usuario de cliente no coincide", clienteOriginal.getNombreUsuario(),clienteLeido.getNombreUsuario());
                Assert.assertEquals("El nombre de cliente no coincide", clienteOriginal.getNombreReal(),clienteLeido.getNombreReal());
                Assert.assertEquals("La contrasenia de cliente no coincide", clienteOriginal.getPass(),clienteLeido.getPass());
            }

            Assert.assertEquals("La cantidad de vehiculos no coincide", this.empresa.getVehiculos().size(), empresaLeida.getVehiculos().size());
            for(String patente : this.empresa.getVehiculos().keySet()){
                Vehiculo vehiculoOriginal = this.empresa.getVehiculos().get(patente);
                Vehiculo vehiculoLeido = empresaLeida.getVehiculos().get(patente);
                Assert.assertEquals("La patente no coincide", vehiculoOriginal.getPatente(),vehiculoLeido.getPatente());
                Assert.assertEquals("La cantidad de plazas no coincide", vehiculoOriginal.getCantidadPlazas(),vehiculoLeido.getCantidadPlazas());
            }

            Assert.assertEquals("La cantidad de choferes no coincide", this.empresa.getChoferes().size(), empresaLeida.getChoferes().size());
            for(String dni : this.empresa.getChoferes().keySet()){
                Chofer choferOriginal = this.empresa.getChoferes().get(dni);
                Chofer choferLeido = empresaLeida.getChoferes().get(dni);
                Assert.assertNotNull("El chofer leído no debería ser nulo", choferLeido);
                Assert.assertEquals("El DNI del chofer no coincide", choferOriginal.getDni(),choferLeido.getDni());
                Assert.assertEquals("El nombre del chofer no coincide", choferOriginal.getNombre(),choferLeido.getNombre());
                Assert.assertEquals("El sueldo bruto del chofer no coincide", choferOriginal.getSueldoBruto(),choferLeido.getSueldoBruto(),0.001);
                Assert.assertEquals("El sueldo neto del chofer no coincide", choferOriginal.getSueldoNeto(),choferLeido.getSueldoNeto(),0.001);
            }

            Assert.assertEquals("La cantidad de pedidos no coincide", this.empresa.getPedidos().size(), empresaLeida.getPedidos().size());

            for (Cliente clienteLeido : empresaLeida.getPedidos().keySet()) {
                Pedido pedidoLeido = empresaLeida.getPedidos().get(clienteLeido);

                Iterator<Cliente> iteradorClientesOriginales = this.empresa.getPedidos().keySet().iterator();
                Cliente clienteOriginalEncontrado = null;

                while (iteradorClientesOriginales.hasNext() && clienteOriginalEncontrado == null) {
                    Cliente clienteOriginal = iteradorClientesOriginales.next();
                    if (clienteOriginal.getNombreUsuario().equals(clienteLeido.getNombreUsuario())) {
                        clienteOriginalEncontrado = clienteOriginal;
                    }
                }

                Assert.assertNotNull("No se encontró un Cliente persistido (" + clienteLeido.getNombreUsuario() + ") en la empresa Original", clienteOriginalEncontrado);

                Pedido pedidoOriginal = this.empresa.getPedidos().get(clienteOriginalEncontrado);

                System.out.println("Pedidos originales: " + this.empresa.getPedidos());
                System.out.println("Pedidos leídos: " + empresaLeida.getPedidos());

                Assert.assertNotNull("El pedido original no debería ser nulo", pedidoOriginal);
                Assert.assertEquals("El cliente del pedido no coincide", pedidoOriginal.getCliente().getNombreUsuario(), pedidoLeido.getCliente().getNombreUsuario());
                Assert.assertEquals("El km del pedido no coincide", pedidoOriginal.getKm(), pedidoLeido.getKm());
                Assert.assertEquals("La zona del pedido no coincide", pedidoOriginal.getZona(), pedidoLeido.getZona());
                Assert.assertEquals("La cantidad de pasajeros del pedido no coincide", pedidoOriginal.getCantidadPasajeros(), pedidoLeido.getCantidadPasajeros());
                Assert.assertEquals("Mascota no coincide en pedido", pedidoOriginal.isMascota(), pedidoLeido.isMascota());
                Assert.assertEquals("Baul no coincide en pedido", pedidoOriginal.isBaul(), pedidoLeido.isBaul());
            }

            Assert.assertEquals("La cantidad de viajes iniciados no coincide", this.empresa.getViajesIniciados().size(), empresaLeida.getViajesIniciados().size());
            for (Cliente clienteLeido : empresaLeida.getViajesIniciados().keySet()) {
                Viaje viajeLeido = empresaLeida.getViajesIniciados().get(clienteLeido);

                Iterator<Cliente> iteradorClientesOriginales = this.empresa.getViajesIniciados().keySet().iterator();
                Cliente clienteOriginalEncontrado = null;

                while (iteradorClientesOriginales.hasNext() && clienteOriginalEncontrado == null) {
                    Cliente clienteOriginal = iteradorClientesOriginales.next();
                    if (clienteOriginal.getNombreUsuario().equals(clienteLeido.getNombreUsuario())) {
                        clienteOriginalEncontrado = clienteOriginal;
                    }
                }
                Assert.assertNotNull("No se encontró un Cliente persistido (" + clienteLeido.getNombreUsuario() + ") en la empresa Original", clienteOriginalEncontrado);
                Viaje viajeOriginal = this.empresa.getViajesIniciados().get(clienteOriginalEncontrado);

                Assert.assertNotNull("El viaje original no debería ser nulo", viajeOriginal);
                Assert.assertEquals("El chofer del viaje no coincide", viajeOriginal.getChofer().getDni(), viajeLeido.getChofer().getDni());
                Assert.assertEquals("El pedido del viaje no coincide", viajeOriginal.getPedido().getCliente().getNombreReal(), viajeLeido.getPedido().getCliente().getNombreReal());
                Assert.assertEquals("El valor del viaje no coincide", viajeOriginal.getValor(), viajeLeido.getValor(),0.0001);
                Assert.assertEquals("El vehículo del viaje no coincide", viajeOriginal.getVehiculo().getPatente(), viajeLeido.getVehiculo().getPatente());
                Assert.assertEquals("La calificación del viaje no coincide", viajeOriginal.getCalificacion(), viajeLeido.getCalificacion());
            }

            Assert.assertEquals("La cantidad de viajes terminados no coincide", this.empresa.getViajesTerminados().size(), empresaLeida.getViajesTerminados().size());

            for (Viaje viajeLeido : empresaLeida.getViajesTerminados()) {

                Iterator<Viaje> iteradorViajesOriginales = this.empresa.getViajesTerminados().iterator();
                Viaje viajeOriginalEncontrado = null;

                while (iteradorViajesOriginales.hasNext() && viajeOriginalEncontrado == null) {
                    Viaje viajeOriginal = iteradorViajesOriginales.next();
                    if (viajeOriginal.getPedido().getCliente().getNombreUsuario().equals(viajeLeido.getPedido().getCliente().getNombreUsuario())) {
                        viajeOriginalEncontrado = viajeOriginal;
                    }
                }

                Assert.assertNotNull("No se encontró un viaje equivalente para el cliente (" + viajeLeido.getPedido().getCliente().getNombreUsuario() + ")", viajeOriginalEncontrado);

                Assert.assertEquals("El chofer del viaje no coincide", viajeOriginalEncontrado.getChofer().getDni(), viajeLeido.getChofer().getDni());
                Assert.assertEquals("El pedido del viaje no coincide", viajeOriginalEncontrado.getPedido().getCliente().getNombreUsuario(),
                                                                                    viajeLeido.getPedido().getCliente().getNombreUsuario());
                Assert.assertEquals("El valor del viaje no coincide", viajeOriginalEncontrado.getValor(), viajeLeido.getValor(),0.001);
                Assert.assertEquals("El vehículo del viaje no coincide", viajeOriginalEncontrado.getVehiculo().getPatente(), viajeLeido.getVehiculo().getPatente());
                Assert.assertEquals("La calificación del viaje no coincide", viajeOriginalEncontrado.getCalificacion(), viajeLeido.getCalificacion());
            }


            Assert.assertEquals("La cantidad de choferes desocupados no coincide", this.empresa.getChoferesDesocupados().size(), empresaLeida.getChoferesDesocupados().size());
            for (Chofer choferLeido : empresaLeida.getChoferesDesocupados()) {

                Iterator<Chofer> iteradorChoferesOriginales = this.empresa.getChoferesDesocupados().iterator();
                Chofer choferOriginalEncontrado = null;

                while (iteradorChoferesOriginales.hasNext() && choferOriginalEncontrado == null) {
                    Chofer choferOriginal = iteradorChoferesOriginales.next();
                    if (choferOriginal.getDni().equals(choferLeido.getDni())) {
                        choferOriginalEncontrado = choferOriginal;
                    }
                }

                Assert.assertNotNull("No se encontró un chofer desocupado equivalente con DNI (" + choferLeido.getDni() + ")", choferOriginalEncontrado);

                Assert.assertEquals("El DNI del chofer no coincide", choferOriginalEncontrado.getDni(), choferLeido.getDni());
                Assert.assertEquals("El nombre del chofer no coincide", choferOriginalEncontrado.getNombre(), choferLeido.getNombre());
            }

            Assert.assertEquals("La cantidad de vehículos desocupados no coincide", this.empresa.getVehiculosDesocupados().size(), empresaLeida.getVehiculosDesocupados().size());
            for (Vehiculo vehiculoLeido : empresaLeida.getVehiculosDesocupados()) {

                Iterator<Vehiculo> iteradorVehiculosOriginales = this.empresa.getVehiculosDesocupados().iterator();
                Vehiculo vehiculoOriginalEncontrado = null;

                while (iteradorVehiculosOriginales.hasNext() && vehiculoOriginalEncontrado == null) {
                    Vehiculo vehiculoOriginal = iteradorVehiculosOriginales.next();
                    if (vehiculoOriginal.getPatente().equals(vehiculoLeido.getPatente())) {
                        vehiculoOriginalEncontrado = vehiculoOriginal;
                    }
                }

                Assert.assertNotNull("No se encontró un vehículo desocupado equivalente con patente (" + vehiculoLeido.getPatente() + ")", vehiculoOriginalEncontrado);

                Assert.assertEquals("La patente del vehículo no coincide", vehiculoOriginalEncontrado.getPatente(), vehiculoLeido.getPatente());
            }

        } catch (IOException | ClassNotFoundException e) {
            Assert.fail("No debería dar error ya que el archivo existe: " + e.getMessage());
        }
    }


    public void llenaEmpresa(EmpresaDTO empresa) {
        // Agregar cliente
        Cliente cliente1 = new Cliente("Sofia", "123456", "Sofia123");
        empresa.getClientes().put(cliente1.getNombreUsuario(), cliente1);
        System.out.println("Cliente agregado: " + cliente1.getNombreUsuario());

        // Agregar chofer
        Chofer chofer1 = new ChoferTemporario("87654321", "Carlos P");
        empresa.getChoferes().put(chofer1.getDni(), chofer1);
        System.out.println("Chofer agregado: " + chofer1.getDni());

        // Agregar vehículo
        Vehiculo vehiculo1 = new Auto("ABC123", 4, true);
        empresa.getVehiculos().put(vehiculo1.getPatente(), vehiculo1);
        System.out.println("Vehículo agregado: " + vehiculo1.getPatente());

        // Agregar chofer y vehículo a la lista de desocupados
        empresa.getChoferesDesocupados().add(chofer1);
        empresa.getVehiculosDesocupados().add(vehiculo1);
        System.out.println("Chofer y vehículo agregados a la lista desocupados.");

        // Crear y agregar pedido
        Pedido pedido1 = new Pedido(cliente1, 3, true, false, 10, "ZONA_PELIGROSA");
        empresa.getPedidos().put(cliente1, pedido1); // Aquí aseguramos que el pedido se almacene
        System.out.println("Pedido agregado para cliente: " + cliente1.getNombreUsuario());

        // Iniciar viaje
        Viaje viaje1 = new Viaje(pedido1, chofer1, vehiculo1);
        empresa.getViajesIniciados().put(cliente1, viaje1);
        System.out.println("Viaje iniciado para cliente: " + cliente1.getNombreUsuario());

        // Agregar viaje terminado
        empresa.getViajesTerminados().add(new Viaje(pedido1, chofer1, vehiculo1));
        System.out.println("Viaje terminado agregado.");

        // Comprobación de pedido
        Pedido pedidoRecuperado = empresa.getPedidos().get(cliente1);
        if (pedidoRecuperado == null) {
            System.out.println("El pedido es null para el cliente: " + cliente1.getNombreUsuario());
        } else {
            System.out.println("Pedido recuperado: " + pedidoRecuperado);
        }
    }
}
