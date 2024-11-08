package test;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;

import javax.swing.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import junit.framework.Assert;
import modeloNegocio.Empresa;
import util.Constantes;
import vista.IOptionPane;
import vista.IVista;
import vista.Ventana;



//FIJARSE SI SE PUEDE HACER DE TAL MANERA DE USAR CONSTANTE.PANEL... 
public class testGUIPaneles {
    Robot robot;
    Controlador controlador;
    FalsoOptionPane op=new FalsoOptionPane();
    IVista vistaAct;
    Ventana ventana;
    String user, password;

    public testGUIPaneles() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() throws Exception {
        controlador = new Controlador();
        ventana=(Ventana) controlador.getVista();
        ventana.setOptionPane(op);
        vistaAct = controlador.getVista();
        user = "paul67";
        password = "1234";
        Empresa.getInstance().agregarCliente(user, password, "Paul McCartney");
    }

    @After
    public void tearDown() throws Exception {
        Ventana ventana = (Ventana) controlador.getVista();
        ventana.setVisible(false);
        Empresa.getInstance().getClientes().clear();
    }

    @Test
    public void testPanelLoginACliente(){
        robot.delay(TestUtils.getDelay());

        JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
        JTextField user = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);

        TestUtils.clickComponent(user, robot);
        TestUtils.tipeaTexto(this.user, robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto(this.password, robot);

        TestUtils.clickComponent(aceptarLog, robot);

        robot.delay(1000);

        Assert.assertEquals("El panel debería ser el de Cliente", this.vistaAct, controlador.getVista());
    }

    @Test
    public void testPanelLoginARegistro(){
        robot.delay(TestUtils.getDelay());

        JButton registrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
        TestUtils.clickComponent(registrar,robot);

        robot.delay(1000);

        Assert.assertEquals("El panel deberia ser el de Registro",this.vistaAct,controlador.getVista());
    }

    @Test
    public void testPanelLoginAadmin(){
        robot.delay(TestUtils.getDelay());

        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

        TestUtils.clickComponent(user, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(aceptarLog, robot);

        robot.delay(1000);

        Assert.assertEquals("El panel deberia ser el de Admin", this.vistaAct,controlador.getVista());
    }

    @Test
    public void testRegistroALogin(){
        robot.delay(TestUtils.getDelay());

        JButton registrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
        TestUtils.clickComponent(registrar,robot);
        JButton cancelar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_CANCELAR);
        TestUtils.clickComponent(cancelar,robot);

        robot.delay(1000);

        Assert.assertEquals("El panel deberia ser el de Login",this.vistaAct,controlador.getVista());
    }

    @Test
    public void testPanelAdminALogin(){
        robot.delay(TestUtils.getDelay());
        //obtengo las referencias a los componentes necesarios
        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

        TestUtils.clickComponent(user, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(aceptarLog, robot);
        JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CERRAR_SESION_ADMIN);
        TestUtils.clickComponent(cerrarSesion, robot);

        robot.delay(1000);

        Assert.assertEquals("El panel deberia ser el de Login",this.vistaAct,controlador.getVista());

    }

    @Test
    public void testPanelClienteaLogin(){
        robot.delay(TestUtils.getDelay());
        //obtengo las referencias a los componentes necesarios
        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);

        TestUtils.clickComponent(user, robot);
        TestUtils.tipeaTexto(this.user, robot);
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto(this.password, robot);
        TestUtils.clickComponent(aceptarLog, robot);
        JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CERRAR_SESION_CLIENTE);
        TestUtils.clickComponent(cerrarSesion, robot);

        robot.delay(1000);

        Assert.assertTrue("El panel deberia ser el de Login",this.vistaAct==controlador.getVista());
    }

}