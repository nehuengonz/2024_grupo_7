package gui;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import util.Constantes;
import vista.Ventana;

public class GuiTestEnabledDisabled {
	Robot robot;
	Controlador controlador;
	
	public GuiTestEnabledDisabled() {
		try
		{
			robot = new Robot();
		}catch (AWTException e) {
			
		}
	}
	 @Before
	    public void setUp() throws Exception
	    {
	        controlador = new Controlador();
	    }
	  @After
	    public void tearDown() throws Exception
	    {
		  Ventana ventana=(Ventana) controlador.getVista();
		  ventana.setVisible(false);
	    }
	  //no me encuentra el boton registrar
	  @Test
	    public void testLogVacio()
	    {
	        robot.delay(guiTestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios
	        JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
	        JButton aceptarLog = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	        guiTestUtils.clickComponent(aceptarReg, robot);
	        //verifico los resultados
	        Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	        Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());
	    }
	  @Test
	  public void testLogSoloNombre() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JTextField nombre = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
	      JButton aceptarLog = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      guiTestUtils.clickComponent(nombre, robot);
	      guiTestUtils.tipeaTexto("contrasenia", robot);
	      //
	      Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	      Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());   
	  }
	  @Test
	  public void testLogSoloContrasenia() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JTextField contrasenia = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
	      JButton aceptarLog = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      guiTestUtils.clickComponent(contrasenia, robot);
	      guiTestUtils.tipeaTexto("nombre", robot);
	      //
	      Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	      Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());   
	  }
	  @Test
	  public void testLogUsuarioyContrasenia() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JTextField nombre = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JTextField contrasenia = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
	      JButton aceptarLog = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      guiTestUtils.clickComponent(nombre, robot);
	      guiTestUtils.tipeaTexto("contrasenia", robot);
	      guiTestUtils.clickComponent(contrasenia, robot);
	      guiTestUtils.tipeaTexto("nombre", robot);
	      //
	      Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	      Assert.assertTrue("El boton de login deberia estar hablitado", aceptarLog.isEnabled());   
	  } 
}
