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
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios
	        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	        TestUtils.clickComponent(aceptarReg, robot);
	        //verifico los resultados
	        Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	        Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());
	    }
	  @Test
	  public void testLog_SoloNombre() {
		  robot.delay(TestUtils.getDelay());
		  //
		  JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      TestUtils.clickComponent(nombre, robot);
	      TestUtils.tipeaTexto("nombre", robot);
	      //
	      Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	      Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());   
	  }
	  @Test
	  public void testLog_SoloContrasenia() {
		  robot.delay(TestUtils.getDelay());
		  //
		  JTextField contrasenia = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      TestUtils.clickComponent(contrasenia, robot);
	      TestUtils.tipeaTexto("contrasenia", robot);
	      //
	      Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	      Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());   
	  }
	  @Test
	  public void testLog_UsuarioyContrasenia() {
		  robot.delay(TestUtils.getDelay());
		  //
		  JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JTextField contrasenia = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      TestUtils.clickComponent(nombre, robot);
	      TestUtils.tipeaTexto("nombre", robot);
	      TestUtils.clickComponent(contrasenia, robot);
	      TestUtils.tipeaTexto("contrasenia", robot);
	      //
	      Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	      Assert.assertTrue("El boton de login deberia estar hablitado", aceptarLog.isEnabled());   
	  } 
	  @Test
	  public void testLog_CompletoYquitoUsuario() {
		  robot.delay(TestUtils.getDelay());
		  //
		  JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JTextField contrasenia = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      TestUtils.clickComponent(nombre, robot);
	      TestUtils.tipeaTexto("nombre", robot);
	      TestUtils.clickComponent(contrasenia, robot);
	      TestUtils.tipeaTexto("contrasenia", robot);
	      TestUtils.clickComponent(nombre, robot);
	      TestUtils.borraJTextfield(nombre, robot);
	      //
	      Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	      Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled()); 
	  }
	  @Test
	  public void testLog_CompletoYquitoContrasenia() {
		  robot.delay(TestUtils.getDelay());
		  //
		  JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JTextField contrasenia = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      TestUtils.clickComponent(nombre, robot);
	      TestUtils.tipeaTexto("nombre", robot);
	      TestUtils.clickComponent(contrasenia, robot);
	      TestUtils.tipeaTexto("contrasenia", robot);
	      TestUtils.clickComponent(contrasenia, robot);
	      TestUtils.borraJTextfield(contrasenia, robot);
	      //
	      Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	      Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled()); 
	  }
	  @Test
	  public void testLog_CompletoYquitoUsuarioycontrasenia() {
		  robot.delay(TestUtils.getDelay());
		  //
		  JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JTextField contrasenia = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      TestUtils.clickComponent(nombre, robot);
	      TestUtils.tipeaTexto("nombre", robot);
	      TestUtils.clickComponent(contrasenia, robot);
	      TestUtils.tipeaTexto("contrasenia", robot);
	      TestUtils.clickComponent(nombre, robot);
	      TestUtils.borraJTextfield(nombre, robot);
	      TestUtils.clickComponent(contrasenia, robot);
	      TestUtils.borraJTextfield(contrasenia, robot);
	      //
	      Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	      Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled()); 
	  }
	  @Test
	  public void testReg_lleno() {
		  robot.delay(TestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  TestUtils.clickComponent(confirmarReg, robot);
		  JTextField nombredeusuario = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_USSER_NAME);
		  JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_PASSWORD);
		  JTextField confirm_pasword = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_CONFIRM_PASSWORD);
		  JTextField nombrereal = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_REAL_NAME);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);

		  //
		  TestUtils.clickComponent(nombredeusuario, robot);
	      TestUtils.tipeaTexto("nombredeusuario", robot);
	      TestUtils.clickComponent(password, robot);
	      TestUtils.tipeaTexto("password", robot);
	      TestUtils.clickComponent(confirm_pasword, robot);
	      TestUtils.tipeaTexto("password", robot);
	      TestUtils.clickComponent(nombrereal, robot);
	      TestUtils.tipeaTexto("nombrereal", robot);
	      Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testRegvacio() {
		  robot.delay(TestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  TestUtils.clickComponent(confirmarReg, robot);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testReg_solonombreusuario() {
		  robot.delay(TestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  TestUtils.clickComponent(confirmarReg, robot);
		  JTextField nombredeusuario = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_USSER_NAME);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		  TestUtils.clickComponent(nombredeusuario, robot);
	      TestUtils.tipeaTexto("nombredeusuario", robot);

	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testReg_solopassword() {
		  robot.delay(TestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  TestUtils.clickComponent(confirmarReg, robot);
		  JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_PASSWORD);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
	      TestUtils.clickComponent(password, robot);
	      TestUtils.tipeaTexto("password", robot);
	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testReg_soloconfirmpass() {
		  robot.delay(TestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  TestUtils.clickComponent(confirmarReg, robot);
		  JTextField confirm_pasword = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_CONFIRM_PASSWORD);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
	      TestUtils.clickComponent(confirm_pasword, robot);
	      TestUtils.tipeaTexto("password", robot);
	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testReg_solonombrereal() {
		  robot.delay(TestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  TestUtils.clickComponent(confirmarReg, robot);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		  JTextField nombrereal = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_REAL_NAME);
	      TestUtils.clickComponent(nombrereal, robot);
	      TestUtils.tipeaTexto("nombrereal", robot);
	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testReg_password_y_confirmpassword() {
		  robot.delay(TestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  TestUtils.clickComponent(confirmarReg, robot);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		  JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_PASSWORD);
		  JTextField confirm_pasword = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_CONFIRM_PASSWORD);
	      TestUtils.clickComponent(password, robot);
	      TestUtils.tipeaTexto("password", robot);
	      TestUtils.clickComponent(confirm_pasword, robot);
	      TestUtils.tipeaTexto("password", robot);
	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testReg_nombreusuarioypass() {
		  robot.delay(TestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  TestUtils.clickComponent(confirmarReg, robot);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		  JTextField nombredeusuario = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_USSER_NAME);
		  JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_PASSWORD);
		  TestUtils.clickComponent(nombredeusuario, robot);
	      TestUtils.tipeaTexto("nombredeusuario", robot);
	      TestUtils.clickComponent(password, robot);
	      TestUtils.tipeaTexto("password", robot);
	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testReg_nombreusuariopassyconfirmpass() {
		  robot.delay(TestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  TestUtils.clickComponent(confirmarReg, robot);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		  JTextField nombredeusuario = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_USSER_NAME);
		  JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_PASSWORD);
		  JTextField confirm_pasword = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_CONFIRM_PASSWORD);
		  TestUtils.clickComponent(nombredeusuario, robot);
	      TestUtils.tipeaTexto("nombredeusuario", robot);
	      TestUtils.clickComponent(password, robot);
	      TestUtils.tipeaTexto("password", robot);
	      TestUtils.clickComponent(confirm_pasword, robot);
	      TestUtils.tipeaTexto("password", robot);
	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testReg_passynombrereal() {
		  robot.delay(TestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  TestUtils.clickComponent(confirmarReg, robot);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		  JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_PASSWORD);
		  JTextField nombrereal = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_REAL_NAME);
	      TestUtils.clickComponent(password, robot);
	      TestUtils.tipeaTexto("password", robot);
	      TestUtils.clickComponent(nombrereal, robot);
	      TestUtils.tipeaTexto("nombrereal", robot);
	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testReg_confirmpass_y_nombreusuario() {
		  robot.delay(TestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  TestUtils.clickComponent(confirmarReg, robot);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		  JTextField nombredeusuario = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_USSER_NAME);
		  JTextField confirm_pasword = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_CONFIRM_PASSWORD);
		  TestUtils.clickComponent(nombredeusuario, robot);
	      TestUtils.tipeaTexto("nombredeusuario", robot);
	      TestUtils.clickComponent(confirm_pasword, robot);
	      TestUtils.tipeaTexto("password", robot);
	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
	  @Test
	  public void testReg_confirmpass_y_nombrereal() {
		  robot.delay(TestUtils.getDelay());
		  //
		  JButton confirmarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		  TestUtils.clickComponent(confirmarReg, robot);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		  JTextField confirm_pasword = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_CONFIRM_PASSWORD);
		  JTextField nombrereal = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_REAL_NAME);
	      TestUtils.clickComponent(confirm_pasword, robot);
	      TestUtils.tipeaTexto("password", robot);
	      TestUtils.clickComponent(nombrereal, robot);
	      TestUtils.tipeaTexto("nombrereal", robot);
	      Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	  }
} 
