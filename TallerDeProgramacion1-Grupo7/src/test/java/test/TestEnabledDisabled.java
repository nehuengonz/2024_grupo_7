package test;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;

import javax.swing.*;

import modeloDatos.Cliente;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import util.Constantes;
import vista.Ventana;

public class TestEnabledDisabled {
    Robot robot;
    Controlador controlador;
    String user, user2, password, password2;

    public TestEnabledDisabled() {
        try {
            robot = new Robot();
        } catch (AWTException e) {

        }
    }

    @Before
    public void setUp() throws Exception {
        controlador = new Controlador();
        user = "usuario1";
        password = "12345";
        user2 = "usuario2";
        password2 = "777";
        Empresa.getInstance().agregarCliente(user, password, "George");
    }

    @After
    public void tearDown() throws Exception {
        Ventana ventana = (Ventana) controlador.getVista();
        ventana.setVisible(false);
        Empresa.getInstance().getClientes().clear();
    }

    @Test
    public void testLog_Vacio() {
        robot.delay(TestUtils.getDelay());

        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);

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

        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("nombre", robot);
        //
        Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
        Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());
    }

    @Test
    public void testLog_SoloContrasenia() {
        robot.delay(TestUtils.getDelay());

        JTextField contrasenia = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);

        TestUtils.clickComponent(contrasenia, robot);
        TestUtils.tipeaTexto("contrasenia", robot);

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

        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("n", robot);
        TestUtils.clickComponent(contrasenia, robot);
        TestUtils.tipeaTexto("c", robot);

        robot.delay(500);

        Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
        Assert.assertTrue("El boton de login deberia estar hablitado", aceptarLog.isEnabled());
    }

    @Test
    public void testLog_CompletoYquitoUsuario() {
        robot.delay(TestUtils.getDelay());

        JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
        JTextField contrasenia = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);

        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("nombre", robot);
        TestUtils.clickComponent(contrasenia, robot);
        TestUtils.tipeaTexto("contrasenia", robot);
        TestUtils.clickComponent(nombre, robot);
        TestUtils.borraJTextField(nombre, robot);

        robot.delay(500);

        Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
        Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());
    }

    @Test
    public void testLog_CompletoYquitoContrasenia() {
        robot.delay(TestUtils.getDelay());

        JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
        JTextField contrasenia = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);

        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("nombre", robot);
        TestUtils.clickComponent(contrasenia, robot);
        TestUtils.tipeaTexto("contrasenia", robot);
        TestUtils.clickComponent(contrasenia, robot);
        TestUtils.borraJTextField(contrasenia, robot);

        robot.delay(500);

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
        TestUtils.borraJTextField(nombre, robot);
        TestUtils.clickComponent(contrasenia, robot);
        TestUtils.borraJTextField(contrasenia, robot);
        //
        Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
        Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());
    }

    @Test
    public void testReg_lleno() {
        robot.delay(TestUtils.getDelay());

        JButton confirmarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
        TestUtils.clickComponent(confirmarReg, robot);
        JTextField nombredeusuario = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_USSER_NAME);
        JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_PASSWORD);
        JTextField confirm_pasword = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_CONFIRM_PASSWORD);
        JTextField nombrereal = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_REAL_NAME);
        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);

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

        JButton confirmarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
        TestUtils.clickComponent(confirmarReg, robot);
        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);

        Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
    }

    @Test
    public void testReg_solonombreusuario() {
        robot.delay(TestUtils.getDelay());

        JButton confirmarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
        TestUtils.clickComponent(confirmarReg, robot);
        JTextField nombredeusuario = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_USSER_NAME);
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
        JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_PASSWORD);
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
        JTextField confirm_pasword = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_CONFIRM_PASSWORD);
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
        JTextField nombrereal = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_REAL_NAME);
        TestUtils.clickComponent(nombrereal, robot);
        TestUtils.tipeaTexto("nombrereal", robot);
        Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
    }

    @Test
    public void testReg_password_y_confirmpassword() {
        robot.delay(TestUtils.getDelay());

        JButton confirmarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
        TestUtils.clickComponent(confirmarReg, robot);
        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
        JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_PASSWORD);
        JTextField confirm_pasword = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_CONFIRM_PASSWORD);
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
        JTextField nombredeusuario = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_USSER_NAME);
        JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_PASSWORD);
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
        JTextField nombredeusuario = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_USSER_NAME);
        JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_PASSWORD);
        JTextField confirm_pasword = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_CONFIRM_PASSWORD);
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
        JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_PASSWORD);
        JTextField nombrereal = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_REAL_NAME);
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
        JTextField nombredeusuario = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_USSER_NAME);
        JTextField confirm_pasword = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_CONFIRM_PASSWORD);
        TestUtils.clickComponent(nombredeusuario, robot);
        TestUtils.tipeaTexto("nombredeusuario", robot);
        TestUtils.clickComponent(confirm_pasword, robot);
        TestUtils.tipeaTexto("password", robot);
        Assert.assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
    }

    @Test
    public void testReg_confirmpass_y_nombrereal() {
        robot.delay(TestUtils.getDelay());

        JButton confirmarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
        TestUtils.clickComponent(confirmarReg, robot);
        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
        JTextField confirm_pasword = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_CONFIRM_PASSWORD);
        JTextField nombrereal = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_REAL_NAME);
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
    public void testContienePedidoActual() {
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
        JRadioButton zonaStandard = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_STANDARD);
        JRadioButton zonaSinAsfaltar = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_SIN_ASFALTAR);
        JCheckBox mascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_MASCOTA);
        JCheckBox baul = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_BAUL);
        JButton nuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);
        JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_CLIENTE);

        JTextField pedido_o_viaje_actual = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PEDIDO_O_VIAJE_ACTUAL);
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
        Assert.assertFalse("El campo de pedido o viaje actual debería estar deshabilitado", pedido_o_viaje_actual.isEnabled());
        Assert.assertFalse("El campo de calificación debería estar deshabilitado", calificacion.isEnabled());
        Assert.assertTrue("El campo de valor del viaje debería estar vacio", text.isEmpty());

    }

    @Test
    public void testContieneViajeActual() {
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
        JRadioButton zonaStandard = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_STANDARD);
        JRadioButton zonaSinAsfaltar = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_SIN_ASFALTAR);
        JCheckBox mascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_MASCOTA);
        JCheckBox baul = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_BAUL);
        JButton nuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);
        JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_CLIENTE);

        JTextField pedido_o_viaje_actual = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PEDIDO_O_VIAJE_ACTUAL);
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
        Assert.assertFalse("El campo de pedido o viaje actual debería estar deshabilitado", pedido_o_viaje_actual.isEnabled());
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
        TestUtils.borraJTextField(dni, robot);
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
        TestUtils.borraJTextField(nombre, robot);
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
        TestUtils.borraJTextField(cantHijos,robot);

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
        TestUtils.borraJTextField(anioIngreso,robot);

        robot.delay(2000);

        Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado", cerrarSesion.isEnabled());
        Assert.assertFalse("El boton de aceptar chofer debería estar deshabilitado", aceptarChofer.isEnabled());
        Assert.assertTrue("El radio button temporario debería estar habilitado", temporario.isEnabled());
    }





}