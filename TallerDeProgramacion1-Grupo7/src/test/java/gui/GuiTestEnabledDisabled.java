package gui;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import modeloDatos.Auto;
import modeloDatos.ChoferPermanente;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloNegocio.Empresa;
import util.Constantes;
import vista.Ventana;

public class GuiTestEnabledDisabled {
	Robot robot;
	Controlador controlador;
	Ventana ventana;
	String userCliente,passCliente,dniChofer,vehiculoPatente;
	Cliente cliente1;
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
	        ventana=(Ventana) controlador.getVista();
	        this.userCliente="usuario1";
	        this.passCliente="1234";
	        this.dniChofer="14007890";
	        this.vehiculoPatente="ASD123";

	     
	        
	    
	    }
	  @After
	    public void tearDown() throws Exception
	    {
		  Ventana ventana=(Ventana) controlador.getVista();
		  ventana.setVisible(false);
		  Empresa.getInstance().getChoferes().clear();
		  Empresa.getInstance().getClientes().clear();
		  Empresa.getInstance().getViajesIniciados().clear();
		  Empresa.getInstance().getVehiculos().clear();
		  Empresa.getInstance().getPedidos().clear();
		  
	    }
	  /*
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
	  */
	  /*
	  @Test
	  public void testBotonPagar_enabled() throws Exception {
		  robot.delay(TestUtils.getDelay());
		  // setup
		  Auto auto1=new Auto(this.vehiculoPatente,4,false);
	      ChoferTemporario chofer1=new ChoferTemporario(this.dniChofer,"pablo");
	      ChoferPermanente chofer2=new ChoferPermanente("11111111","paul",2000,1);
		  Empresa.getInstance().agregarChofer(chofer2);
	      Empresa.getInstance().agregarVehiculo(auto1);
	      Empresa.getInstance().agregarCliente(userCliente, passCliente, dniChofer);
	      Cliente cliente = Empresa.getInstance().getClientes().get(this.userCliente);
	      Pedido pedido= new Pedido(cliente,3,false,false,14,Constantes.ZONA_STANDARD);
	      Empresa.getInstance().agregarPedido(pedido);
	      Empresa.getInstance().crearViaje(pedido, chofer2, auto1);
	      Empresa.getInstance().setViajesIniciados(Empresa.getInstance().getViajesIniciados());
		  //
		  JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JTextField contrasenia = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      TestUtils.clickComponent(nombre, robot);
	      TestUtils.tipeaTexto(userCliente, robot);
	      TestUtils.clickComponent(contrasenia, robot);
	      TestUtils.tipeaTexto(passCliente, robot);
	      TestUtils.clickComponent(aceptarLog, robot);
	      //
	      robot.delay(TestUtils.getDelay());
	      //
	      JTextField calificacion = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CALIFICACION_DE_VIAJE);
	      JButton Pagar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICAR_PAGAR);
	      TestUtils.clickComponent(calificacion, robot);
	      TestUtils.tipeaTexto("4", robot);
	      robot.delay(2000);
	      
	      Assert.assertTrue("El boton de Pagar deberia estar habilitado", Pagar.isEnabled());
	  }
	  @Test
	  public void testBotonPagar_disabled_despues_dePagar() throws Exception {
		  robot.delay(TestUtils.getDelay());
		  // setup
		  Auto auto1=new Auto(this.vehiculoPatente,4,false);
	      ChoferTemporario chofer1=new ChoferTemporario(this.dniChofer,"pablo");
	      ChoferPermanente chofer2=new ChoferPermanente("11111111","paul",2000,1);
		  Empresa.getInstance().agregarChofer(chofer2);
	      Empresa.getInstance().agregarVehiculo(auto1);
	      Empresa.getInstance().agregarCliente(userCliente, passCliente, dniChofer);
	      Cliente cliente = Empresa.getInstance().getClientes().get(this.userCliente);
	      Pedido pedido= new Pedido(cliente,3,false,false,14,Constantes.ZONA_STANDARD);
	      Empresa.getInstance().agregarPedido(pedido);
	      Empresa.getInstance().crearViaje(pedido, chofer2, auto1);
	      Empresa.getInstance().setViajesIniciados(Empresa.getInstance().getViajesIniciados());
		  //
		  JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JTextField contrasenia = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      TestUtils.clickComponent(nombre, robot);
	      TestUtils.tipeaTexto(userCliente, robot);
	      TestUtils.clickComponent(contrasenia, robot);
	      TestUtils.tipeaTexto(passCliente, robot);
	      TestUtils.clickComponent(aceptarLog, robot);
	      //
	      robot.delay(TestUtils.getDelay());
	      //
	      JTextField calificacion = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CALIFICACION_DE_VIAJE);
	      JButton Pagar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICAR_PAGAR);
	      TestUtils.clickComponent(calificacion, robot);
	      TestUtils.tipeaTexto("4", robot);
	      TestUtils.clickComponent(Pagar, robot);
	      robot.delay(2000);
	      
	      Assert.assertFalse("El boton de Pagar deberia estar deshabilitado", Pagar.isEnabled());
	  }
	  @Test
	  public void testBotonPagar_disabled_antes_de_realizar_un_nuevopedido() throws Exception {
		  robot.delay(TestUtils.getDelay());
		  // setup
		  Auto auto1=new Auto(this.vehiculoPatente,4,false);
	      ChoferTemporario chofer1=new ChoferTemporario(this.dniChofer,"pablo");
	      ChoferPermanente chofer2=new ChoferPermanente("11111111","paul",2000,1);
		  Empresa.getInstance().agregarChofer(chofer2);
	      Empresa.getInstance().agregarVehiculo(auto1);
	      Empresa.getInstance().agregarCliente(userCliente, passCliente, dniChofer);
	      Cliente cliente = Empresa.getInstance().getClientes().get(this.userCliente);

		  //
		  JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JTextField contrasenia = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      TestUtils.clickComponent(nombre, robot);
	      TestUtils.tipeaTexto(userCliente, robot);
	      TestUtils.clickComponent(contrasenia, robot);
	      TestUtils.tipeaTexto(passCliente, robot);
	      TestUtils.clickComponent(aceptarLog, robot);
	      //
	      robot.delay(TestUtils.getDelay());
	      //
	      JTextField calificacion = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CALIFICACION_DE_VIAJE);
	      JButton Pagar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICAR_PAGAR);
	      TestUtils.clickComponent(calificacion, robot);
	      TestUtils.tipeaTexto("4", robot);
	      robot.delay(2000);
	      Assert.assertFalse("El Campo de texto Calificacion deberia estar deshabilitado", calificacion.isEnabled());
	      Assert.assertFalse("El boton de Pagar deberia estar deshabilitado", Pagar.isEnabled());
	  }
	  @Test
	  public void testBotonPagar_disabled_despues_de_realizar_un_nuevopedido() throws Exception {
		  robot.delay(TestUtils.getDelay());
		  // setup
		  Auto auto1=new Auto(this.vehiculoPatente,4,false);
	      ChoferTemporario chofer1=new ChoferTemporario(this.dniChofer,"pablo");
	      ChoferPermanente chofer2=new ChoferPermanente("11111111","paul",2000,1);
		  Empresa.getInstance().agregarChofer(chofer2);
	      Empresa.getInstance().agregarVehiculo(auto1);
	      Empresa.getInstance().agregarCliente(userCliente, passCliente, dniChofer);
	      Cliente cliente = Empresa.getInstance().getClientes().get(this.userCliente);
	      Pedido pedido= new Pedido(cliente,3,false,false,14,Constantes.ZONA_STANDARD);
	      Empresa.getInstance().agregarPedido(pedido);
		  //
		  JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JTextField contrasenia = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      TestUtils.clickComponent(nombre, robot);
	      TestUtils.tipeaTexto(userCliente, robot);
	      TestUtils.clickComponent(contrasenia, robot);
	      TestUtils.tipeaTexto(passCliente, robot);
	      TestUtils.clickComponent(aceptarLog, robot);
	      //
	      robot.delay(TestUtils.getDelay());
	      //
	      JTextField calificacion = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CALIFICACION_DE_VIAJE);
	      JButton Pagar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICAR_PAGAR);
	      TestUtils.clickComponent(calificacion, robot);
	      TestUtils.tipeaTexto("4", robot);
	      robot.delay(2000);
	      Assert.assertFalse("El Campo de texto Calificacion deberia estar deshabilitado", calificacion.isEnabled());
	      Assert.assertFalse("El boton de Pagar deberia estar deshabilitado", Pagar.isEnabled());
	  }
	  */
	  @Test
	  public void testAdminBotonNuevoViaje_enabled() throws Exception {
		  robot.delay(TestUtils.getDelay());
		  // setup
		  Auto auto1=new Auto(this.vehiculoPatente,4,false);
	      ChoferTemporario chofer1=new ChoferTemporario(this.dniChofer,"pablo");
	      ChoferPermanente chofer2=new ChoferPermanente("11111111","paul",2000,1);
		  Empresa.getInstance().agregarChofer(chofer2);
	      Empresa.getInstance().agregarVehiculo(auto1);
	      Empresa.getInstance().agregarCliente(userCliente, passCliente, dniChofer);
	      Cliente cliente = Empresa.getInstance().getClientes().get(this.userCliente);
	      Pedido pedido= new Pedido(cliente,3,false,false,14,Constantes.ZONA_STANDARD);
	      Empresa.getInstance().agregarPedido(pedido);
		  //
		  JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JTextField contrasenia = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      TestUtils.clickComponent(nombre, robot);
	      TestUtils.tipeaTexto("admin", robot);
	      TestUtils.clickComponent(contrasenia, robot);
	      TestUtils.tipeaTexto("admin", robot);
	      TestUtils.clickComponent(aceptarLog, robot);
	      //
	      robot.delay(1000);
	      //
	      JButton nuevoViaje   = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VIAJE);
	      JList lista_choferes =(JList) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_CHOFERES_LIBRES);
	      //cuando pruebo lista pedidos pendientes me lo toma como lista choferes totales wtfff
	      JList lista_pedidos  =(JList) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_PEDIDOS_PENDIENTES);
	      JList lista_vehiculos=(JList) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_VEHICULOS_DISPONIBLES);
	      
	      TestUtils.clickComponent(lista_pedidos, robot);
	      robot.delay(1000);
	      TestUtils.clickComponent(lista_choferes, robot);
	      robot.delay(1000);
	      TestUtils.clickComponent(lista_vehiculos, robot);
	      robot.delay(1000);
	      
	      //
	      Assert.assertTrue("El boton de nuevo viaje deberia estar habilitado", nuevoViaje.isEnabled());
	  }
	  @Test
	  public void testAdminBotonNuevoViaje_disabled_solo_clickChofer() throws Exception {
		  robot.delay(TestUtils.getDelay());
		  // setup
		  Auto auto1=new Auto(this.vehiculoPatente,4,false);
	      ChoferTemporario chofer1=new ChoferTemporario(this.dniChofer,"pablo");
	      ChoferPermanente chofer2=new ChoferPermanente("11111111","paul",2000,1);
		  Empresa.getInstance().agregarChofer(chofer2);
	      Empresa.getInstance().agregarVehiculo(auto1);
	      Empresa.getInstance().agregarCliente(userCliente, passCliente, dniChofer);
	      Cliente cliente = Empresa.getInstance().getClientes().get(this.userCliente);
	      Pedido pedido= new Pedido(cliente,3,false,false,14,Constantes.ZONA_STANDARD);
	      Empresa.getInstance().agregarPedido(pedido);
		  //
		  JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JTextField contrasenia = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      TestUtils.clickComponent(nombre, robot);
	      TestUtils.tipeaTexto("admin", robot);
	      TestUtils.clickComponent(contrasenia, robot);
	      TestUtils.tipeaTexto("admin", robot);
	      TestUtils.clickComponent(aceptarLog, robot);
	      //
	      robot.delay(1000);
	      //
	      JButton nuevoViaje   = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VIAJE);
	      JList lista_choferes =(JList) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_CHOFERES_LIBRES);
	      //cuando pruebo lista pedidos pendientes me lo toma como lista choferes totales wtfff
	      JList lista_pedidos  =(JList) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_PEDIDOS_PENDIENTES);
	      JList lista_vehiculos=(JList) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_VEHICULOS_DISPONIBLES);
	      
	      TestUtils.clickComponent(lista_choferes, robot);
	      robot.delay(1000);
	      
	      //
	      Assert.assertFalse("El boton de nuevo viaje deberia estar deshabilitado", nuevoViaje.isEnabled());
	  }
	  @Test
	  public void testAdminBotonNuevoViaje_disabled_solo_clickvehiculos() throws Exception {
		  robot.delay(TestUtils.getDelay());
		  // setup
		  Auto auto1=new Auto(this.vehiculoPatente,4,false);
	      ChoferTemporario chofer1=new ChoferTemporario(this.dniChofer,"pablo");
	      ChoferPermanente chofer2=new ChoferPermanente("11111111","paul",2000,1);
		  Empresa.getInstance().agregarChofer(chofer2);
	      Empresa.getInstance().agregarVehiculo(auto1);
	      Empresa.getInstance().agregarCliente(userCliente, passCliente, dniChofer);
	      Cliente cliente = Empresa.getInstance().getClientes().get(this.userCliente);
	      Pedido pedido= new Pedido(cliente,3,false,false,14,Constantes.ZONA_STANDARD);
	      Empresa.getInstance().agregarPedido(pedido);
		  //
		  JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JTextField contrasenia = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      TestUtils.clickComponent(nombre, robot);
	      TestUtils.tipeaTexto("admin", robot);
	      TestUtils.clickComponent(contrasenia, robot);
	      TestUtils.tipeaTexto("admin", robot);
	      TestUtils.clickComponent(aceptarLog, robot);
	      //
	      robot.delay(1000);
	      //
	      JButton nuevoViaje   = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VIAJE);
	      JList lista_choferes =(JList) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_CHOFERES_LIBRES);
	      //cuando pruebo lista pedidos pendientes me lo toma como lista choferes totales wtfff
	      JList lista_pedidos  =(JList) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_PEDIDOS_PENDIENTES);
	      JList lista_vehiculos=(JList) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_VEHICULOS_DISPONIBLES);
	      
	      TestUtils.clickComponent(lista_vehiculos, robot);
	      robot.delay(1000);
	      
	      //
	      Assert.assertFalse("El boton de nuevo viaje deberia estar deshabilitado", nuevoViaje.isEnabled());
	  }
	  
	  @Test
	  public void testAdminBotonNuevoViaje_disabled_solo_clickpedidos() throws Exception {
		  robot.delay(TestUtils.getDelay());
		  // setup
		  Auto auto1=new Auto(this.vehiculoPatente,4,false);
	      ChoferTemporario chofer1=new ChoferTemporario(this.dniChofer,"pablo");
	      ChoferPermanente chofer2=new ChoferPermanente("11111111","paul",2000,1);
		  Empresa.getInstance().agregarChofer(chofer2);
	      Empresa.getInstance().agregarVehiculo(auto1);
	      Empresa.getInstance().agregarCliente(userCliente, passCliente, dniChofer);
	      Cliente cliente = Empresa.getInstance().getClientes().get(this.userCliente);
	      Pedido pedido= new Pedido(cliente,3,false,false,14,Constantes.ZONA_STANDARD);
	      Empresa.getInstance().agregarPedido(pedido);
		  //
		  JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JTextField contrasenia = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      TestUtils.clickComponent(nombre, robot);
	      TestUtils.tipeaTexto("admin", robot);
	      TestUtils.clickComponent(contrasenia, robot);
	      TestUtils.tipeaTexto("admin", robot);
	      TestUtils.clickComponent(aceptarLog, robot);
	      //
	      robot.delay(1000);
	      //
	      JButton nuevoViaje   = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VIAJE);
	      JList lista_choferes =(JList) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_CHOFERES_LIBRES);
	      //cuando pruebo lista pedidos pendientes me lo toma como lista choferes totales wtfff
	      JList lista_pedidos  =(JList) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_PEDIDOS_PENDIENTES);
	      JList lista_vehiculos=(JList) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_VEHICULOS_DISPONIBLES);
	      
	      TestUtils.clickComponent(lista_pedidos, robot);
	      robot.delay(1000);
	      
	      //
	      Assert.assertFalse("El boton de nuevo viaje deberia estar deshabilitado", nuevoViaje.isEnabled());
	  }
	  @Test
	  public void testAdminBotonNuevoViaje_disabled_conjuntovacio() throws Exception {
		  robot.delay(TestUtils.getDelay());
		  //
		  JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		  JTextField contrasenia = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		  JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
	      JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
	      //completo los textfields
	      TestUtils.clickComponent(nombre, robot);
	      TestUtils.tipeaTexto("admin", robot);
	      TestUtils.clickComponent(contrasenia, robot);
	      TestUtils.tipeaTexto("admin", robot);
	      TestUtils.clickComponent(aceptarLog, robot);
	      //
	      robot.delay(1000);
	      //
	      JButton nuevoViaje   = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VIAJE);
	      JList lista_choferes =(JList) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_CHOFERES_LIBRES);
	      //cuando pruebo lista pedidos pendientes me lo toma como lista choferes totales wtfff
	      JList lista_pedidos  =(JList) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_PEDIDOS_PENDIENTES);
	      JList lista_vehiculos=(JList) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_VEHICULOS_DISPONIBLES);
	      
	      //
	      Assert.assertFalse("El boton de nuevo viaje deberia estar deshabilitado", nuevoViaje.isEnabled());
	  }
	  
} 
