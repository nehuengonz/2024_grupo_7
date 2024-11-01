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
	    public void testLog_Vacio()
	    {
	        robot.delay(guiTestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios
	        JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	        JButton aceptarLog = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	        guiTestUtils.clickComponent(aceptarReg, robot);
	        //verifico los resultados
	        Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	        Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());
	    }
	  @Test
	  public void testLog_SoloNombre() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JTextField nombre = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      guiTestUtils.clickComponent(nombre, robot);
	      guiTestUtils.tipeaTexto("nombre", robot);
	      //
	      Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	      Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());   
	  }
	  @Test
	  public void testLog_SoloContrasenia() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JTextField contrasenia = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      guiTestUtils.clickComponent(contrasenia, robot);
	      guiTestUtils.tipeaTexto("contrasenia", robot);
	      //
	      Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	      Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());   
	  }
	  @Test
	  public void testLog_UsuarioyContrasenia() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JTextField nombre = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JTextField contrasenia = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      guiTestUtils.clickComponent(nombre, robot);
	      guiTestUtils.tipeaTexto("nombre", robot);
	      guiTestUtils.clickComponent(contrasenia, robot);
	      guiTestUtils.tipeaTexto("contrasenia", robot);
	      //
	      Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	      Assert.assertTrue("El boton de login deberia estar hablitado", aceptarLog.isEnabled());   
	  } 
	  @Test
	  public void testLog_CompletoYquitoUsuario() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JTextField nombre = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JTextField contrasenia = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      guiTestUtils.clickComponent(nombre, robot);
	      guiTestUtils.tipeaTexto("nombre", robot);
	      guiTestUtils.clickComponent(contrasenia, robot);
	      guiTestUtils.tipeaTexto("contrasenia", robot);
	      guiTestUtils.clickComponent(nombre, robot);
	      guiTestUtils.borraJTextfield(nombre, robot);
	      //
	      Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	      Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled()); 
	  }
	  @Test
	  public void testLog_CompletoYquitoContrasenia() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JTextField nombre = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JTextField contrasenia = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      guiTestUtils.clickComponent(nombre, robot);
	      guiTestUtils.tipeaTexto("nombre", robot);
	      guiTestUtils.clickComponent(contrasenia, robot);
	      guiTestUtils.tipeaTexto("contrasenia", robot);
	      guiTestUtils.clickComponent(contrasenia, robot);
	      guiTestUtils.borraJTextfield(contrasenia, robot);
	      //
	      Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	      Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled()); 
	  }
	  @Test
	  public void testLog_CompletoYquitoUsuarioycontrasenia() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JTextField nombre = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JTextField contrasenia = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      guiTestUtils.clickComponent(nombre, robot);
	      guiTestUtils.tipeaTexto("nombre", robot);
	      guiTestUtils.clickComponent(contrasenia, robot);
	      guiTestUtils.tipeaTexto("contrasenia", robot);
	      guiTestUtils.clickComponent(nombre, robot);
	      guiTestUtils.borraJTextfield(nombre, robot);
	      guiTestUtils.clickComponent(contrasenia, robot);
	      guiTestUtils.borraJTextfield(contrasenia, robot);
	      //
	      Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	      Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled()); 
	  }
	  @Test
	  public void testReg_lleno() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  guiTestUtils.clickComponent(confirmarReg, robot);
		  JTextField nombredeusuario = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_USSER_NAME);
		  JTextField password = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_PASSWORD);
		  JTextField confirm_pasword = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_CONFIRM_PASSWORD);
		  JTextField nombrereal = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_REAL_NAME);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);

		  //
		  guiTestUtils.clickComponent(nombredeusuario, robot);
	      guiTestUtils.tipeaTexto("nombredeusuario", robot);
	      guiTestUtils.clickComponent(password, robot);
	      guiTestUtils.tipeaTexto("password", robot);
	      guiTestUtils.clickComponent(confirm_pasword, robot);
	      guiTestUtils.tipeaTexto("password", robot);
	      guiTestUtils.clickComponent(nombrereal, robot);
	      guiTestUtils.tipeaTexto("nombrereal", robot);
	      Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testRegvacio() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  guiTestUtils.clickComponent(confirmarReg, robot);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testReg_solonombreusuario() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  guiTestUtils.clickComponent(confirmarReg, robot);
		  JTextField nombredeusuario = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_USSER_NAME);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		  guiTestUtils.clickComponent(nombredeusuario, robot);
	      guiTestUtils.tipeaTexto("nombredeusuario", robot);

	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testReg_solopassword() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  guiTestUtils.clickComponent(confirmarReg, robot);
		  JTextField password = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_PASSWORD);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
	      guiTestUtils.clickComponent(password, robot);
	      guiTestUtils.tipeaTexto("password", robot);
	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testReg_soloconfirmpass() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  guiTestUtils.clickComponent(confirmarReg, robot);
		  JTextField confirm_pasword = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_CONFIRM_PASSWORD);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
	      guiTestUtils.clickComponent(confirm_pasword, robot);
	      guiTestUtils.tipeaTexto("password", robot);
	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testReg_solonombrereal() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  guiTestUtils.clickComponent(confirmarReg, robot);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		  JTextField nombrereal = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_REAL_NAME);
	      guiTestUtils.clickComponent(nombrereal, robot);
	      guiTestUtils.tipeaTexto("nombrereal", robot);
	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testReg_password_y_confirmpassword() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  guiTestUtils.clickComponent(confirmarReg, robot);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		  JTextField password = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_PASSWORD);
		  JTextField confirm_pasword = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_CONFIRM_PASSWORD);
	      guiTestUtils.clickComponent(password, robot);
	      guiTestUtils.tipeaTexto("password", robot);
	      guiTestUtils.clickComponent(confirm_pasword, robot);
	      guiTestUtils.tipeaTexto("password", robot);
	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testReg_nombreusuarioypass() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  guiTestUtils.clickComponent(confirmarReg, robot);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		  JTextField nombredeusuario = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_USSER_NAME);
		  JTextField password = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_PASSWORD);
		  guiTestUtils.clickComponent(nombredeusuario, robot);
	      guiTestUtils.tipeaTexto("nombredeusuario", robot);
	      guiTestUtils.clickComponent(password, robot);
	      guiTestUtils.tipeaTexto("password", robot);
	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testReg_nombreusuariopassyconfirmpass() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  guiTestUtils.clickComponent(confirmarReg, robot);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		  JTextField nombredeusuario = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_USSER_NAME);
		  JTextField password = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_PASSWORD);
		  JTextField confirm_pasword = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_CONFIRM_PASSWORD);
		  guiTestUtils.clickComponent(nombredeusuario, robot);
	      guiTestUtils.tipeaTexto("nombredeusuario", robot);
	      guiTestUtils.clickComponent(password, robot);
	      guiTestUtils.tipeaTexto("password", robot);
	      guiTestUtils.clickComponent(confirm_pasword, robot);
	      guiTestUtils.tipeaTexto("password", robot);
	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testReg_passynombrereal() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  guiTestUtils.clickComponent(confirmarReg, robot);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		  JTextField password = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_PASSWORD);
		  JTextField nombrereal = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_REAL_NAME);
	      guiTestUtils.clickComponent(password, robot);
	      guiTestUtils.tipeaTexto("password", robot);
	      guiTestUtils.clickComponent(nombrereal, robot);
	      guiTestUtils.tipeaTexto("nombrereal", robot);
	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testReg_confirmpass_y_nombreusuario() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  guiTestUtils.clickComponent(confirmarReg, robot);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		  JTextField nombredeusuario = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_USSER_NAME);
		  JTextField confirm_pasword = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_CONFIRM_PASSWORD);
		  guiTestUtils.clickComponent(nombredeusuario, robot);
	      guiTestUtils.tipeaTexto("nombredeusuario", robot);
	      guiTestUtils.clickComponent(confirm_pasword, robot);
	      guiTestUtils.tipeaTexto("password", robot);
	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testReg_confirmpass_y_nombrereal() {
		  robot.delay(guiTestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  guiTestUtils.clickComponent(confirmarReg, robot);
		  JButton aceptarReg = (JButton) guiTestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		  JTextField confirm_pasword = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_CONFIRM_PASSWORD);
		  JTextField nombrereal = (JTextField) guiTestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_REAL_NAME);
	      guiTestUtils.clickComponent(confirm_pasword, robot);
	      guiTestUtils.tipeaTexto("password", robot);
	      guiTestUtils.clickComponent(nombrereal, robot);
	      guiTestUtils.tipeaTexto("nombrereal", robot);
	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
} 
