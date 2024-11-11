package test;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;

import javax.swing.*;

import modeloDatos.Auto;
import modeloDatos.ChoferTemporario;
import modeloNegocio.Empresa;
import modeloDatos.Cliente;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import junit.framework.Assert;
import util.Constantes;
import util.Mensajes;
import vista.IOptionPane;
import vista.Ventana;


public class testGUIMensajes {
    Robot robot;
    Controlador controlador;
    FalsoOptionPane op=new FalsoOptionPane();

    String userCliente,passCliente,dniChofer,vehiculoPatente;
    Cliente cliente1;


    public testGUIMensajes()
    {
        try
        {
            robot = new Robot();
        } catch (AWTException e)
        {
        }
    }

    @Before
    public void setUp() throws Exception {
        controlador = new Controlador();
        this.userCliente="usuario1";
        this.passCliente="1234";
        this.dniChofer="14007890";
        this.vehiculoPatente="ASD123";

        Auto auto1=new Auto(this.vehiculoPatente,4,false);
        ChoferTemporario chofer1=new ChoferTemporario(this.dniChofer,"pablo");

        Empresa.getInstance().agregarCliente("usuario1","1234","juan");
        Empresa.getInstance().agregarChofer(chofer1);
        Empresa.getInstance().agregarVehiculo(auto1);
    }


    @Test //este metodo lo hice para los usuarios clientes, no lo testee para admin porque es la misma ventana tanto para uno como para otro
    public void testContraIncorrecta() {
        robot.delay(TestUtils.getDelay());
        JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
        JTextField user = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);

        TestUtils.clickComponent(user, robot);
        TestUtils.tipeaTexto(this.userCliente, robot);

        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("111", robot);

        TestUtils.clickComponent(aceptarLog, robot);

        Assert.assertEquals("Mensaje incorrecto, deberia decir " + Mensajes.PASS_ERRONEO.getValor(), Mensajes.PASS_ERRONEO.getValor(), op.getMensaje());
    }

    @Test
    public void testUserIncorrecta() {
        robot.delay(TestUtils.getDelay());
        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

        TestUtils.clickComponent(user, robot);
        TestUtils.tipeaTexto("sofia999", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto(this.passCliente, robot);
        TestUtils.clickComponent(aceptarLog, robot);

        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.USUARIO_DESCONOCIDO.getValor(),Mensajes.USUARIO_DESCONOCIDO.getValor(),op.getMensaje());
    }

    @Test
    public void testRegClienteRepetido()
    {
        robot.delay(TestUtils.getDelay());
        JButton registrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
        TestUtils.clickComponent(registrar,robot);
        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_USSER_NAME);
        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_PASSWORD);
        JTextField confirmPassword= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_CONFIRM_PASSWORD);
        JTextField nombreReal= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_REAL_NAME);
        JButton aceptarRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_REGISTRAR);
        JButton cancelarRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_CANCELAR);
        TestUtils.clickComponent(user, robot);
        TestUtils.tipeaTexto(this.userCliente, robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("1234578", robot);
        TestUtils.clickComponent(confirmPassword, robot);
        TestUtils.tipeaTexto("1234578", robot);
        TestUtils.clickComponent(nombreReal, robot);
        TestUtils.tipeaTexto("Guybrush Threepwood", robot);
        TestUtils.clickComponent(aceptarRegistrar, robot);
        //verifico los resultados
        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.USUARIO_REPETIDO.getValor(),Mensajes.USUARIO_REPETIDO.getValor(),op.getMensaje());
    }

    @Test
    public void testRegClienteConfirmacionContraInvalida()
    {
        robot.delay(TestUtils.getDelay());
        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_USSER_NAME);
        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_PASSWORD);
        JTextField confirmPassword= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_CONFIRM_PASSWORD);
        JTextField nombreReal= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_REAL_NAME);
        JButton aceptarRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_REGISTRAR);
        JButton cancelarRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_CANCELAR);

        TestUtils.clickComponent(user, robot);
        TestUtils.tipeaTexto("DeadPool11", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("1234578", robot);
        TestUtils.clickComponent(confirmPassword, robot);
        TestUtils.tipeaTexto("1111111", robot);
        TestUtils.clickComponent(nombreReal, robot);
        TestUtils.tipeaTexto("Wade Wilson", robot);
        TestUtils.clickComponent(aceptarRegistrar, robot);
        //verifico los resultados
        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.PASS_NO_COINCIDE.getValor(),Mensajes.PASS_NO_COINCIDE.getValor(),op.getMensaje());

    }

    @Test
    public void testClienteRealizaPedidoSinVehiculoDisp() {
        robot.delay(TestUtils.getDelay());

        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

        TestUtils.clickComponent(user, robot);
        TestUtils.tipeaTexto(userCliente, robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto(passCliente, robot);

        TestUtils.clickComponent(aceptarLog, robot);

        JTextField cantPax= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CANT_PAX);
        JTextField cantKm= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CANT_KM);
        JRadioButton zonaPeligrosa= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.ZONA_PELIGROSA);
        JCheckBox mascota= (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CHECK_MASCOTA);
        JButton nuevoPedido=(JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NUEVO_PEDIDO);

        TestUtils.clickComponent(cantPax, robot);
        TestUtils.tipeaTexto("10", robot);
        TestUtils.clickComponent(cantKm, robot);
        TestUtils.tipeaTexto("33", robot);
        TestUtils.clickComponent(zonaPeligrosa, robot);
        TestUtils.clickComponent(mascota, robot);
        TestUtils.clickComponent(nuevoPedido, robot);

        //verifico los resultados
        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor(),Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor(),op.getMensaje());

    }

    @Test
    public void testRegistroChoferRepetido() {
        robot.delay(TestUtils.getDelay());

        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

        TestUtils.clickComponent(user, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("admin", robot);

        TestUtils.clickComponent(aceptarLog, robot);

        JTextField dni= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.DNI_CHOFER);
        JTextField nombre= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_CHOFER);
        JRadioButton permanente= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PERMANENTE);
        JTextField cantHijos= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CH_CANT_HIJOS);
        JTextField anioIngreso= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CH_ANIO);
        JButton aceptarChofer=(JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NUEVO_CHOFER);

        TestUtils.clickComponent(dni, robot);
        TestUtils.tipeaTexto(dniChofer, robot);
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("Paul", robot);
        TestUtils.clickComponent(permanente, robot);
        TestUtils.clickComponent(cantHijos, robot);
        TestUtils.tipeaTexto("2", robot);
        TestUtils.clickComponent(anioIngreso,robot);
        TestUtils.tipeaTexto("2000", robot);
        TestUtils.clickComponent(aceptarChofer,robot);

        //verifico los resultados
        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.CHOFER_YA_REGISTRADO.getValor(),Mensajes.CHOFER_YA_REGISTRADO.getValor(),op.getMensaje());

    }

    @Test
    public void testRegistroVehiculoRepetido(){
        robot.delay(TestUtils.getDelay());

        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

        TestUtils.clickComponent(user, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("admin", robot);

        TestUtils.clickComponent(aceptarLog, robot);

        JTextField patente= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PATENTE);
        JRadioButton auto= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.AUTO);
        JCheckBox mascota= (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
        JTextField cantPlazas=(JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CANTIDAD_PLAZAS);

        robot.delay(6000);

        JButton aceptarVehiculo=(JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NUEVO_VEHICULO);

        TestUtils.clickComponent(patente, robot);
        TestUtils.tipeaTexto(this.vehiculoPatente, robot);
        TestUtils.clickComponent(auto, robot);
        TestUtils.clickComponent(cantPlazas, robot);
        TestUtils.tipeaTexto("4", robot);
        TestUtils.clickComponent(aceptarVehiculo,robot);

        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.VEHICULO_YA_REGISTRADO.getValor(),Mensajes.VEHICULO_YA_REGISTRADO.getValor(),op.getMensaje());

    }

}
