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
import modeloDatos.Cliente;
import modeloNegocio.Empresa;
import util.Constantes;
import util.Mensajes;
import vista.IOptionPane;
import vista.Ventana;

public class testConjuntovacio {
	   Robot robot;
	    Controlador controlador;
	    FalsoOptionPane op=new FalsoOptionPane();
	    Ventana ventana;
	    String userCliente,passCliente,dniChofer,vehiculoPatente;
	    Cliente cliente1;
	
	 public testConjuntovacio()
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

}
