package gui;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import excepciones.ChoferRepetidoException;
import excepciones.ClienteConPedidoPendienteException;
import excepciones.ClienteConViajePendienteException;
import excepciones.ClienteNoExisteException;
import excepciones.SinVehiculoParaPedidoException;
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
	String user, user2, password, password2;
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
		this.userCliente="usuario";
		this.passCliente="1234";
		this.dniChofer="14007890";
		this.vehiculoPatente="ASD123";
		user = "usuario1";
		password = "12345";
		user2 = "usuario2";
		password2 = "777";
		Empresa.getInstance().agregarCliente(user, password, "George");
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
		robot.delay(TestUtils.getDelay());
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
		robot.delay(TestUtils.getDelay());
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
		robot.delay(TestUtils.getDelay());
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
	@Test
	public void testNuevoPedidoValido() {
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto(user, robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto(password, robot);

		TestUtils.clickComponent(aceptarLog, robot);

		JTextField cantPax = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_PAX);
		JTextField cantKm = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_KM);
		JRadioButton zonaPeligrosa = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_PELIGROSA);
		JCheckBox mascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_MASCOTA);
		JCheckBox baul = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_BAUL);
		JButton nuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_CLIENTE);

		JTextField calificacion = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
		JTextField valor_viaje = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.VALOR_VIAJE);

		TestUtils.clickComponent(cantPax, robot);
		TestUtils.tipeaTexto("2", robot);
		TestUtils.clickComponent(cantKm, robot);
		TestUtils.tipeaTexto("5", robot);
		TestUtils.clickComponent(zonaPeligrosa, robot);
		TestUtils.clickComponent(mascota, robot);
		TestUtils.clickComponent(baul, robot);

		String text=valor_viaje.getText();

		Assert.assertFalse("El JTextField de calificación debería estar deshabilitado", calificacion.isEnabled());
		Assert.assertTrue("El JTextField de valor viaje debería estar deshabilitado", text.isEmpty());
		Assert.assertTrue("El boton de cerrar sesion debería estar habilitado", cerrarSesion.isEnabled());
		Assert.assertTrue("El boton de nuevo pedido debería estar habilitado", nuevoPedido.isEnabled());

	}

	@Test
	public void testNuevoPedidoMasCantMaxPas() {
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto(user, robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto(password, robot);

		TestUtils.clickComponent(aceptarLog, robot);

		JTextField cantPax = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_PAX);
		JTextField cantKm = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_KM);
		JRadioButton zonaPeligrosa = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_PELIGROSA);
		JCheckBox mascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_MASCOTA);
		JCheckBox baul = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_BAUL);
		JButton nuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_CLIENTE);


		JTextField calificacion = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
		JTextField valor_viaje = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.VALOR_VIAJE);

		TestUtils.clickComponent(cantPax, robot);
		TestUtils.tipeaTexto("14", robot);
		TestUtils.clickComponent(cantKm, robot);
		TestUtils.tipeaTexto("5", robot);
		TestUtils.clickComponent(zonaPeligrosa, robot);
		TestUtils.clickComponent(mascota, robot);
		TestUtils.clickComponent(baul, robot);

		String text=valor_viaje.getText();

		Assert.assertFalse("El JTextField de calificación debería estar deshabilitado", calificacion.isEnabled());
		Assert.assertTrue("El JTextField de valor viaje debería estar deshabilitado", text.isEmpty());
		Assert.assertTrue("El boton de cerrar sesion debería estar habilitado", cerrarSesion.isEnabled());
		Assert.assertFalse("El boton de nuevo pedido debería estar deshabilitado", nuevoPedido.isEnabled());

	}

	@Test
	public void testNuevoPedidoKmNeg() {
		//

		//
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto(user, robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto(password, robot);

		TestUtils.clickComponent(aceptarLog, robot);

		JTextField cantPax = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_PAX);
		JTextField cantKm = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_KM);
		JRadioButton zonaPeligrosa = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_PELIGROSA);
		JCheckBox mascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_MASCOTA);
		JCheckBox baul = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_BAUL);
		JButton nuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_CLIENTE);


		JTextField calificacion = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
		JTextField valor_viaje = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.VALOR_VIAJE);

		TestUtils.clickComponent(cantPax, robot);
		TestUtils.tipeaTexto("5", robot);
		TestUtils.clickComponent(cantKm, robot);
		TestUtils.tipeaTexto("-5", robot);
		TestUtils.clickComponent(zonaPeligrosa, robot);
		TestUtils.clickComponent(mascota, robot);
		TestUtils.clickComponent(baul, robot);

		String text=valor_viaje.getText();

		Assert.assertFalse("El JTextField de calificación debería estar deshabilitado", calificacion.isEnabled());
		Assert.assertTrue("El JTextField de valor viaje debería estar deshabilitado", text.isEmpty());
		Assert.assertTrue("El boton de cerrar sesion debería estar habilitado", cerrarSesion.isEnabled());
		Assert.assertFalse("El boton de nuevo pedido debería estar deshabilitado", nuevoPedido.isEnabled());

	}

	@Test
	public void testContienePedidoActual() throws Exception {
		//
		Auto auto1=new Auto(this.vehiculoPatente,4,false);
		ChoferTemporario chofer1=new ChoferTemporario(this.dniChofer,"pablo");
		ChoferPermanente chofer2=new ChoferPermanente("11111111","paul",2000,1);
		Empresa.getInstance().agregarChofer(chofer2);
		Empresa.getInstance().agregarVehiculo(auto1);
		Empresa.getInstance().agregarCliente("b","b","b");
		Cliente cliente = Empresa.getInstance().getClientes().get("b");
		Pedido pedido= new Pedido(cliente,3,false,false,14,Constantes.ZONA_STANDARD);
		Empresa.getInstance().agregarPedido(pedido);
		//
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto("b", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("b", robot);

		TestUtils.clickComponent(aceptarLog, robot);
		robot.delay(1000);
		JTextField cantPax = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_PAX);
		JTextField cantKm = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_KM);
		JRadioButton zonaPeligrosa = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_PELIGROSA);
		JRadioButton zonaStandard = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_STANDARD);
		JRadioButton zonaSinAsfaltar = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_SIN_ASFALTAR);
		JCheckBox mascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_MASCOTA);
		JCheckBox baul = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_BAUL);
		JButton nuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_CLIENTE);

		JTextArea pedido_o_viaje_actual = (JTextArea) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PEDIDO_O_VIAJE_ACTUAL);
		JTextField calificacion = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
		JTextField valor_viaje = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.VALOR_VIAJE);

		String text=valor_viaje.getText();

		Assert.assertFalse("El campo de cantidad de pasajeros debería estar deshabilitado", cantPax.isEnabled());
		Assert.assertFalse("El campo de cantidad de kilómetros debería estar deshabilitado", cantKm.isEnabled());
		Assert.assertFalse("El botón de zona peligrosa debería estar deshabilitado", zonaPeligrosa.isEnabled());
		Assert.assertFalse("El botón de zona standard debería estar deshabilitado", zonaStandard.isEnabled());
		Assert.assertFalse("El botón de zona no asfaltada debería estar deshabilitado", zonaSinAsfaltar.isEnabled());

		Assert.assertFalse("El checkbox de mascota debería estar deshabilitado", mascota.isEnabled());
		Assert.assertFalse("El checkbox de baúl debería estar deshabilitado", baul.isEnabled());
		Assert.assertFalse("El botón de nuevo pedido debería estar deshabilitado", nuevoPedido.isEnabled());
		Assert.assertTrue("El botón de cerrar sesión debería estar habilitado", cerrarSesion.isEnabled());
		Assert.assertTrue("El campo de pedido o viaje actual debería estar habilitado", pedido_o_viaje_actual.isEnabled());
		Assert.assertFalse("El campo de calificación debería estar deshabilitado", calificacion.isEnabled());
		Assert.assertTrue("El campo de valor del viaje debería estar vacio", text.isEmpty());

	}

	@Test
	public void testContieneViajeActual() throws Exception {
		//
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
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto(userCliente, robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto(passCliente, robot);

		TestUtils.clickComponent(aceptarLog, robot);
		robot.delay(TestUtils.getDelay());
		JTextField cantPax = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_PAX);
		JTextField cantKm = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_KM);
		JRadioButton zonaPeligrosa = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_PELIGROSA);
		JRadioButton zonaStandard = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_STANDARD);
		JRadioButton zonaSinAsfaltar = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_SIN_ASFALTAR);
		JCheckBox mascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_MASCOTA);
		JCheckBox baul = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_BAUL);
		JButton nuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_CLIENTE);

		JTextArea pedido_o_viaje_actual = (JTextArea) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PEDIDO_O_VIAJE_ACTUAL);
		JTextField calificacion = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
		JTextField valor_viaje = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.VALOR_VIAJE);

		String text=valor_viaje.getText();

		Assert.assertFalse("El campo de cantidad de pasajeros debería estar deshabilitado", cantPax.isEnabled());
		Assert.assertFalse("El campo de cantidad de kilómetros debería estar deshabilitado", cantKm.isEnabled());
		Assert.assertFalse("El botón de zona peligrosa debería estar deshabilitado", zonaPeligrosa.isEnabled());
		Assert.assertFalse("El botón de zona standard debería estar deshabilitado", zonaStandard.isEnabled());
		Assert.assertFalse("El botón de zona no asfaltada debería estar deshabilitado", zonaSinAsfaltar.isEnabled());

		Assert.assertFalse("El checkbox de mascota debería estar deshabilitado", mascota.isEnabled());
		Assert.assertFalse("El checkbox de baúl debería estar deshabilitado", baul.isEnabled());
		Assert.assertFalse("El botón de nuevo pedido debería estar deshabilitado", nuevoPedido.isEnabled());
		Assert.assertTrue("El botón de cerrar sesión debería estar habilitado", cerrarSesion.isEnabled());
		Assert.assertTrue("El campo de calificación debería estar habilitado", calificacion.isEnabled());
		Assert.assertFalse("El campo de valor del viaje no debería estar vacio", text.isEmpty());
	}

	@Test
	public void testAdminAgregaChoferTemporario(){
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("admin", robot);

		TestUtils.clickComponent(aceptarLog, robot);

		JTextField dni = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.DNI_CHOFER);
		JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_CHOFER);
		JRadioButton permanente = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PERMANENTE);
		JRadioButton temporario = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.TEMPORARIO);
		JTextField cantHijos = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_CANT_HIJOS);
		JTextField anioIngreso = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_ANIO);
		JButton aceptarChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN);

		TestUtils.clickComponent(temporario, robot);
		TestUtils.clickComponent(dni, robot);
		TestUtils.tipeaTexto("1", robot);
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("a", robot);
		robot.delay(2000);


		Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado", cerrarSesion.isEnabled());
		Assert.assertTrue("El boton de aceptar chofer debería estar habilitado", aceptarChofer.isEnabled());
		Assert.assertFalse("El campo de cantidad de hijos debería estar deshabilitado", cantHijos.isEnabled());
		Assert.assertFalse("El campo anio ingreso debería estar deshabilitado", anioIngreso.isEnabled());
		Assert.assertTrue("El radio button permanente debería estar habilitado", permanente.isEnabled());


	}

	@Test
	public void testAdminAgregaChoferTemporarioSinDNI(){
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("admin", robot);

		TestUtils.clickComponent(aceptarLog, robot);

		JTextField dni = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.DNI_CHOFER);
		JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_CHOFER);
		JRadioButton permanente = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PERMANENTE);
		JRadioButton temporario = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.TEMPORARIO);
		JTextField cantHijos = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_CANT_HIJOS);
		JTextField anioIngreso = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_ANIO);
		JButton aceptarChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN);

		TestUtils.clickComponent(temporario, robot);
		TestUtils.clickComponent(dni, robot);
		TestUtils.tipeaTexto("1", robot);
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("a", robot);
		TestUtils.borraJTextfield(dni, robot);
		robot.delay(2000);

		Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado", cerrarSesion.isEnabled());
		Assert.assertFalse("El boton de aceptar chofer debería estar deshabilitado", aceptarChofer.isEnabled());
		Assert.assertFalse("El campo de cantidad de hijos debería estar deshabilitado", cantHijos.isEnabled());
		Assert.assertFalse("El campo anio ingreso debería estar deshabilitado", anioIngreso.isEnabled());
		Assert.assertTrue("El radio button permanente debería estar habilitado", permanente.isEnabled());

	}

	@Test
	public void testAdminAgregaChoferTemporarioSinNombre(){
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("admin", robot);

		TestUtils.clickComponent(aceptarLog, robot);

		JTextField dni = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.DNI_CHOFER);
		JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_CHOFER);
		JRadioButton permanente = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PERMANENTE);
		JRadioButton temporario = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.TEMPORARIO);
		JTextField cantHijos = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_CANT_HIJOS);
		JTextField anioIngreso = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_ANIO);
		JButton aceptarChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN);

		TestUtils.clickComponent(temporario, robot);
		TestUtils.clickComponent(dni, robot);
		TestUtils.tipeaTexto("1", robot);
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("a", robot);
		TestUtils.borraJTextfield(nombre, robot);
		robot.delay(2000);

		Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado", cerrarSesion.isEnabled());
		Assert.assertFalse("El boton de aceptar chofer debería estar deshabilitado", aceptarChofer.isEnabled());
		Assert.assertFalse("El campo de cantidad de hijos debería estar deshabilitado", cantHijos.isEnabled());
		Assert.assertFalse("El campo anio ingreso debería estar deshabilitado", anioIngreso.isEnabled());
		Assert.assertTrue("El radio button permanente debería estar habilitado", permanente.isEnabled());

	}

	@Test
	public void testAdminAgregaChoferPermanenteValido(){
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("admin", robot);

		TestUtils.clickComponent(aceptarLog, robot);

		JTextField dni = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.DNI_CHOFER);
		JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_CHOFER);
		JRadioButton permanente = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PERMANENTE);
		JRadioButton temporario = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.TEMPORARIO);
		JTextField cantHijos = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_CANT_HIJOS);
		JTextField anioIngreso = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_ANIO);
		JButton aceptarChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN);

		TestUtils.clickComponent(permanente, robot);
		TestUtils.clickComponent(dni, robot);
		TestUtils.tipeaTexto("1", robot);
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("a", robot);
		TestUtils.clickComponent(cantHijos, robot);
		TestUtils.tipeaTexto("0", robot);
		TestUtils.clickComponent(anioIngreso, robot);
		TestUtils.tipeaTexto("1900", robot);

		robot.delay(2000);

		Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado", cerrarSesion.isEnabled());
		Assert.assertTrue("El boton de aceptar chofer debería estar habilitado", aceptarChofer.isEnabled());
		Assert.assertTrue("El radio button temporario debería estar habilitado", temporario.isEnabled());

	}

	@Test
	public void testAdminAgregaChoferPermanenteInvCantHijos(){
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("admin", robot);

		TestUtils.clickComponent(aceptarLog, robot);

		JTextField dni = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.DNI_CHOFER);
		JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_CHOFER);
		JRadioButton permanente = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PERMANENTE);
		JRadioButton temporario = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.TEMPORARIO);
		JTextField cantHijos = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_CANT_HIJOS);
		JTextField anioIngreso = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_ANIO);
		JButton aceptarChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN);

		TestUtils.clickComponent(permanente, robot);
		TestUtils.clickComponent(dni, robot);
		TestUtils.tipeaTexto("1", robot);
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("a", robot);
		TestUtils.clickComponent(cantHijos, robot);
		TestUtils.tipeaTexto("-5", robot);
		TestUtils.clickComponent(anioIngreso, robot);
		TestUtils.tipeaTexto("3000", robot);

		robot.delay(2000);

		Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado", cerrarSesion.isEnabled());
		Assert.assertFalse("El boton de aceptar chofer debería estar deshabilitado", aceptarChofer.isEnabled());
		Assert.assertTrue("El radio button temporario debería estar habilitado", temporario.isEnabled());

	}

	@Test
	public void testAdminAgregaChoferPermanenteAniosInv(){
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("admin", robot);

		TestUtils.clickComponent(aceptarLog, robot);

		JTextField dni = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.DNI_CHOFER);
		JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_CHOFER);
		JRadioButton permanente = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PERMANENTE);
		JRadioButton temporario = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.TEMPORARIO);
		JTextField cantHijos = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_CANT_HIJOS);
		JTextField anioIngreso = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_ANIO);
		JButton aceptarChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN);

		TestUtils.clickComponent(permanente, robot);
		TestUtils.clickComponent(dni, robot);
		TestUtils.tipeaTexto("1", robot);
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("a", robot);
		TestUtils.clickComponent(cantHijos, robot);
		TestUtils.tipeaTexto("5", robot);
		TestUtils.clickComponent(anioIngreso, robot);
		TestUtils.tipeaTexto("3001", robot);

		robot.delay(2000);

		Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado", cerrarSesion.isEnabled());
		Assert.assertFalse("El boton de aceptar chofer debería estar deshabilitado", aceptarChofer.isEnabled());
		Assert.assertTrue("El radio button temporario debería estar habilitado", temporario.isEnabled());

	}

	@Test
	public void testAdminAgregaChoferPermanenteBlancoHijos(){
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("admin", robot);

		TestUtils.clickComponent(aceptarLog, robot);

		JTextField dni = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.DNI_CHOFER);
		JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_CHOFER);
		JRadioButton permanente = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PERMANENTE);
		JRadioButton temporario = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.TEMPORARIO);
		JTextField cantHijos = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_CANT_HIJOS);
		JTextField anioIngreso = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_ANIO);
		JButton aceptarChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN);

		TestUtils.clickComponent(permanente, robot);
		TestUtils.clickComponent(dni, robot);
		TestUtils.tipeaTexto("1", robot);
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("a", robot);
		TestUtils.clickComponent(cantHijos, robot);
		TestUtils.tipeaTexto("5", robot);
		TestUtils.clickComponent(anioIngreso, robot);
		TestUtils.tipeaTexto("3000", robot);
		TestUtils.borraJTextfield(cantHijos,robot);

		robot.delay(2000);

		Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado", cerrarSesion.isEnabled());
		Assert.assertFalse("El boton de aceptar chofer debería estar deshabilitado", aceptarChofer.isEnabled());
		Assert.assertTrue("El radio button temporario debería estar habilitado", temporario.isEnabled());

	}

	@Test
	public void testAdminAgregaChoferPermanenteBlancoAnios(){
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("admin", robot);

		TestUtils.clickComponent(aceptarLog, robot);

		JTextField dni = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.DNI_CHOFER);
		JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_CHOFER);
		JRadioButton permanente = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PERMANENTE);
		JRadioButton temporario = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.TEMPORARIO);
		JTextField cantHijos = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_CANT_HIJOS);
		JTextField anioIngreso = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_ANIO);
		JButton aceptarChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN);

		TestUtils.clickComponent(permanente, robot);
		TestUtils.clickComponent(dni, robot);
		TestUtils.tipeaTexto("1", robot);
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("a", robot);
		TestUtils.clickComponent(cantHijos, robot);
		TestUtils.tipeaTexto("5", robot);
		TestUtils.clickComponent(anioIngreso, robot);
		TestUtils.tipeaTexto("3000", robot);
		TestUtils.borraJTextfield(anioIngreso,robot);

		robot.delay(2000);

		Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado", cerrarSesion.isEnabled());
		Assert.assertFalse("El boton de aceptar chofer debería estar deshabilitado", aceptarChofer.isEnabled());
		Assert.assertTrue("El radio button temporario debería estar habilitado", temporario.isEnabled());
	}

	@Test
	public void testAdminAgregaVehiculoMotoValido(){
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("admin", robot);

		TestUtils.clickComponent(aceptarLog, robot);

		JTextField patente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
		JRadioButton moto = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.MOTO);
		JCheckBox mascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
		JTextField cantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
		JButton aceptarVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN);

		TestUtils.clickComponent(moto, robot);
		TestUtils.clickComponent(patente, robot);
		TestUtils.tipeaTexto("1", robot);

		robot.delay(2000);

		Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado", cerrarSesion.isEnabled());
		Assert.assertTrue("El boton de aceptar chofer debería estar habilitado", aceptarVehiculo.isEnabled());
		Assert.assertFalse("El checkbox de mascota deberia estar habilitado", mascota.isEnabled());
		Assert.assertFalse("El Textfield de cantidad de plazas deberia estar habilitado", cantPlazas.isEnabled());
		Assert.assertTrue("El boton de aceptar vehiculo debería estar habilitado", aceptarVehiculo.isEnabled());

	}

	@Test
	public void testAdminAgregaVehiculoMotoInvalido(){
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("admin", robot);

		TestUtils.clickComponent(aceptarLog, robot);

		JTextField patente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
		JRadioButton moto = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.MOTO);
		JCheckBox mascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
		JTextField cantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
		JButton aceptarVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN);

		TestUtils.clickComponent(moto, robot);
		TestUtils.clickComponent(patente, robot);
		TestUtils.tipeaTexto("1", robot);
		TestUtils.borraJTextfield(patente,robot);

		robot.delay(2000);

		Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado", cerrarSesion.isEnabled());
		Assert.assertFalse("El boton de aceptar chofer debería estar deshabilitado", aceptarVehiculo.isEnabled());
		Assert.assertFalse("El Textfield de cantidad de plazas deberia estar habilitado", cantPlazas.isEnabled());
		Assert.assertFalse("El boton de aceptar chofer debería estar deshabilitado", aceptarVehiculo.isEnabled());
		Assert.assertFalse("El checkbox de mascota deberia estar habilitado", mascota.isEnabled());

	}

	@Test
	public void testAdminAgregaVehiculoAutoValido(){
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("admin", robot);

		TestUtils.clickComponent(aceptarLog, robot);

		JTextField patente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
		JRadioButton auto = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.AUTO);
		JCheckBox mascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
		JTextField cantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
		JButton aceptarVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN);

		robot.delay(2000);

		TestUtils.clickComponent(auto, robot);
		TestUtils.clickComponent(mascota, robot);
		TestUtils.clickComponent(patente,robot);
		TestUtils.tipeaTexto("a",robot);
		TestUtils.clickComponent(cantPlazas,robot);
		TestUtils.tipeaTexto("3",robot);

		robot.delay(2000);

		Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado", cerrarSesion.isEnabled());
		Assert.assertTrue("El boton de aceptar chofer debería estar habilitado", aceptarVehiculo.isEnabled());
	}

	@Test
	public void testAdminAgregaVehiculoAutoPatenteInvalido(){
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("admin", robot);

		TestUtils.clickComponent(aceptarLog, robot);

		JRadioButton auto = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.AUTO);
		JCheckBox mascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
		JTextField cantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
		JButton aceptarVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN);

		robot.delay(2000);

		TestUtils.clickComponent(auto, robot);
		TestUtils.clickComponent(mascota, robot);
		TestUtils.clickComponent(cantPlazas,robot);
		TestUtils.tipeaTexto("4",robot);


		Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado", cerrarSesion.isEnabled());
		Assert.assertFalse("El boton de aceptar chofer debería estar deshabilitado", aceptarVehiculo.isEnabled());

	}

	@Test
	public void testAdminAgregaVehiculoAutoPlazaInvalida(){
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("admin", robot);

		TestUtils.clickComponent(aceptarLog, robot);

		JTextField patente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
		JRadioButton auto = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.AUTO);
		JCheckBox mascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
		JTextField cantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
		JButton aceptarVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN);

		robot.delay(2000);

		TestUtils.clickComponent(auto, robot);
		TestUtils.clickComponent(mascota, robot);
		TestUtils.clickComponent(patente,robot);
		TestUtils.tipeaTexto("a",robot);
		TestUtils.clickComponent(cantPlazas,robot);
		TestUtils.tipeaTexto("5",robot);

		Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado", cerrarSesion.isEnabled());
		Assert.assertFalse("El boton de aceptar chofer debería estar deshabilitado", aceptarVehiculo.isEnabled());

	}
	@Test
	public void testAdminAgregaVehiculoAutoPlazaInvalida_0(){
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("admin", robot);

		TestUtils.clickComponent(aceptarLog, robot);

		JTextField patente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
		JRadioButton auto = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.AUTO);
		JCheckBox mascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
		JTextField cantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
		JButton aceptarVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN);

		robot.delay(2000);

		TestUtils.clickComponent(auto, robot);
		TestUtils.clickComponent(mascota, robot);
		TestUtils.clickComponent(patente,robot);
		TestUtils.tipeaTexto("a",robot);
		TestUtils.clickComponent(cantPlazas,robot);
		TestUtils.tipeaTexto("0",robot);
		robot.delay(2000);
		Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado", cerrarSesion.isEnabled());
		Assert.assertFalse("El boton de aceptar chofer debería estar deshabilitado", aceptarVehiculo.isEnabled());
	}
	@Test
	public void testAdminAgregaVehiculoCombiValido(){
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("admin", robot);

		TestUtils.clickComponent(aceptarLog, robot);

		JTextField patente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
		JRadioButton combi = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.COMBI);
		JCheckBox mascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
		JTextField cantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
		JButton aceptarVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN);

		TestUtils.clickComponent(combi, robot);
		TestUtils.clickComponent(mascota, robot);
		TestUtils.clickComponent(patente,robot);
		TestUtils.tipeaTexto("a",robot);
		TestUtils.clickComponent(cantPlazas,robot);
		TestUtils.tipeaTexto("10",robot);

		robot.delay(2000);

		Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado", cerrarSesion.isEnabled());
		Assert.assertTrue("El boton de aceptar chofer debería estar habilitado", aceptarVehiculo.isEnabled());
	}

	@Test
	public void testAdminAgregaVehiculoCombiInvalidoPatente(){
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("admin", robot);

		TestUtils.clickComponent(aceptarLog, robot);

		JTextField patente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
		JRadioButton combi = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.COMBI);
		JCheckBox mascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
		JTextField cantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
		JButton aceptarVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN);

		TestUtils.clickComponent(combi, robot);
		TestUtils.clickComponent(mascota, robot);
		TestUtils.clickComponent(patente,robot);
		TestUtils.tipeaTexto("a",robot);
		TestUtils.clickComponent(cantPlazas,robot);
		TestUtils.tipeaTexto("5",robot);
		TestUtils.borraJTextfield(patente,robot);

		robot.delay(2000);

		Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado", cerrarSesion.isEnabled());
		Assert.assertFalse("El boton de aceptar chofer debería estar deshabilitado", aceptarVehiculo.isEnabled());
	}

	@Test
	public void testAdminAgregaVehiculoCombiInvalidoPlazas(){
		robot.delay(TestUtils.getDelay());

		JTextField contrasenia= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		JTextField usuario= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

		TestUtils.clickComponent(usuario, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("admin", robot);

		TestUtils.clickComponent(aceptarLog, robot);

		JTextField patente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
		JRadioButton combi = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.COMBI);
		JCheckBox mascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
		JTextField cantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
		JButton aceptarVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN);

		TestUtils.clickComponent(combi, robot);
		TestUtils.clickComponent(mascota, robot);
		TestUtils.clickComponent(patente,robot);
		TestUtils.tipeaTexto("a",robot);
		TestUtils.clickComponent(cantPlazas,robot);
		TestUtils.tipeaTexto("11",robot);

		robot.delay(2000);

		Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado", cerrarSesion.isEnabled());
		Assert.assertFalse("El boton de aceptar chofer debería estar deshabilitado", aceptarVehiculo.isEnabled());
	}
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
		Empresa.getInstance().agregarCliente("b", "b", "b");
		Cliente cliente = Empresa.getInstance().getClientes().get("b");

		//
		JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		JTextField contrasenia = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
		JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);
		//completo los textfields
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("b", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("b", robot);
		TestUtils.clickComponent(aceptarLog, robot);
		//
		robot.delay(1000);
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

		lista_pedidos.setSelectedIndex(0);
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
		lista_pedidos.setSelectedIndex(0);
		TestUtils.clickComponent(lista_pedidos, robot);
		robot.delay(3000);

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
		//
		Assert.assertFalse("El boton de nuevo viaje deberia estar deshabilitado", nuevoViaje.isEnabled());
	}

}
