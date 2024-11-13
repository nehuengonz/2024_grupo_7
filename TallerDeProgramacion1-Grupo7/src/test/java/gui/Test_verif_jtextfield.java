package gui;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import junit.framework.Assert;
import modeloDatos.Auto;
import modeloDatos.ChoferPermanente;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloNegocio.Empresa;
import util.Constantes;
import util.Mensajes;
import vista.IOptionPane;
import vista.Ventana;

public class Test_verif_jtextfield {
	   Robot robot;
	    Controlador controlador;
	    FalsoOptionPane op=new FalsoOptionPane();
	    Ventana ventana;
	    String userCliente,passCliente,dniChofer,vehiculoPatente;
	    Cliente cliente1;
	
	 public Test_verif_jtextfield()
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
        ventana=(Ventana) controlador.getVista();
        //falto el setoptionpane de la ventana
        ventana.setOptionPane((IOptionPane) op);
        this.userCliente="usuario1";
        this.passCliente="1234";
        this.dniChofer="14007890";
        this.vehiculoPatente="ASD123";
	}

	@After
	public void tearDown() throws Exception {
		 Ventana ventana=(Ventana) controlador.getVista();
	        ventana.setVisible(false);
	        Empresa.getInstance().getChoferes().clear();
	        Empresa.getInstance().getClientes().clear();
	        Empresa.getInstance().getViajesIniciados().clear();
	        Empresa.getInstance().getVehiculos().clear();
	        Empresa.getInstance().getPedidos().clear();
	}

	@SuppressWarnings("deprecation")
	@Test
    public void testregistrovehiculo_correctamente(){
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

        robot.delay(1000);

        JButton aceptarVehiculo=(JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NUEVO_VEHICULO);

        TestUtils.clickComponent(patente, robot);
        TestUtils.tipeaTexto(this.vehiculoPatente, robot);
        TestUtils.clickComponent(auto, robot);
        TestUtils.clickComponent(cantPlazas, robot);
        TestUtils.tipeaTexto("4", robot);
        TestUtils.clickComponent(mascota,robot);
        TestUtils.clickComponent(aceptarVehiculo,robot);
        robot.delay(TestUtils.getDelay());
        Assert.assertEquals("el campo patente deberia estar vacio","",patente.getText());
        Assert.assertEquals("el campo cantidad de plazas deberia estar vacio","",cantPlazas.getText());
    }
	
	@SuppressWarnings("deprecation")
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

        robot.delay(TestUtils.getDelay());

        TestUtils.clickComponent(dni, robot);
        TestUtils.tipeaTexto(dniChofer, robot);
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("Paul", robot);
        TestUtils.clickComponent(permanente, robot);
        TestUtils.clickComponent(cantHijos, robot);
        TestUtils.tipeaTexto("1", robot);
        TestUtils.clickComponent(anioIngreso,robot);
        TestUtils.tipeaTexto("2000", robot);
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(aceptarChofer,robot);
        robot.delay(TestUtils.getDelay());
        //verifico los resultados
        Assert.assertEquals("el campo dni deberia estar vacio","",dni.getText());
        Assert.assertEquals("el campo nombre deberia estar vacio","",nombre.getText());
        Assert.assertEquals("el campo cantHijos deberia estar vacio","",cantHijos.getText());
        Assert.assertEquals("el campo anioIngreso deberia estar vacio","",anioIngreso.getText());
    }
	@Test
    public void testClienteRealizaPedido_verificoTextFieldVacios() throws Exception {
        robot.delay(TestUtils.getDelay());
        //
        // setup
        Auto auto1=new Auto(this.vehiculoPatente,4,false);
        ChoferTemporario chofer1=new ChoferTemporario(this.dniChofer,"pablo");
        ChoferPermanente chofer2=new ChoferPermanente("11111111","paul",2000,1);
        Empresa.getInstance().agregarChofer(chofer2);
        Empresa.getInstance().agregarVehiculo(auto1);
        Empresa.getInstance().agregarCliente("a", "a", "a");
        Empresa.getInstance().agregarCliente(userCliente, passCliente, dniChofer);
        Cliente cliente = Empresa.getInstance().getClientes().get("a");
        Pedido pedido= new Pedido(cliente,3,false,false,14,Constantes.ZONA_STANDARD);
        Empresa.getInstance().agregarPedido(pedido);
        Empresa.getInstance().crearViaje(pedido, chofer2, auto1);
        Empresa.getInstance().setViajesIniciados(Empresa.getInstance().getViajesIniciados());
        //
        JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
        JTextField contrasenia = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);

        //completo los textfields
        robot.delay(1000);
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto(this.userCliente, robot);
        TestUtils.clickComponent(contrasenia, robot);
        TestUtils.tipeaTexto(this.passCliente, robot);
        TestUtils.clickComponent(aceptarLog, robot);


        JTextField cantPax= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CANT_PAX);
        JTextField cantKm= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CANT_KM);
        JRadioButton zonaPeligrosa= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.ZONA_PELIGROSA);
        JCheckBox mascota= (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CHECK_MASCOTA);
        JButton nuevoPedido=(JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NUEVO_PEDIDO);

        robot.delay(1000);

        TestUtils.clickComponent(cantPax, robot);
        TestUtils.tipeaTexto("10", robot);
        TestUtils.clickComponent(cantKm, robot);
        TestUtils.tipeaTexto("33", robot);
        TestUtils.clickComponent(zonaPeligrosa, robot);

        TestUtils.clickComponent(nuevoPedido, robot);
        robot.delay(1000);

        //verifico los resultados
        Assert.assertEquals("el campo cantidad depasajeros deberia estar vacio","",cantPax.getText());
        Assert.assertEquals("el campo nombre deberia estar vacio","",cantKm.getText());

    }
}
