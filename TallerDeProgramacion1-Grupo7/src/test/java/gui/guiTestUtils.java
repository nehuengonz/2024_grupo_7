package gui;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class guiTestUtils {

	private static int delay = 20;
	public static void setDelay(int delay) {
		  guiTestUtils.delay = delay;
	}
	public static int getDelay() {
		return delay;
	}
	public static Component getComponentForName(Component parent, String name) {
		 Component retorno = null;
	        if (parent.getName() != null && parent.getName().equals(name))
	            retorno = parent;
	        else
	        {
	            if (parent instanceof Container)
	            {
	                Component[] hijos = ((Container) parent).getComponents();
	                int i = 0;
	                while (i < hijos.length && retorno == null)
	                {
	                    retorno = getComponentForName(hijos[i], name);
	                    i++;
	                }
	            }
	        }
	        return retorno;
	}
	public static Point getCentro(Component component) {
		Point retorno = null;
		if (component != null) {
			retorno = component.getLocationOnScreen();
		}
		retorno.x +=component.getWidth()/2;
		retorno.y += component.getHeight()/2;
		return retorno;
	}
	@SuppressWarnings("deprecation")
	public static void clickComponent(Component component, Robot robot) {
        Point punto = guiTestUtils.getCentro(component);
        robot.mouseMove(punto.x, punto.y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(getDelay());
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(getDelay());
	}
	public static void tipeaTexto(String texto, Robot robot) {
		 String mayusculas = texto.toUpperCase();
	        char letras[] = mayusculas.toCharArray();
	        for (int i = 0; i < letras.length; i++)
	        {
	            robot.delay(getDelay());
	            if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z')
	                robot.keyPress(KeyEvent.VK_SHIFT);
	            robot.keyPress(letras[i]);
	            robot.delay(getDelay());
	            robot.keyRelease(letras[i]);
	            if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z')
	                robot.keyRelease(KeyEvent.VK_SHIFT);

	        }
	}
	@SuppressWarnings("deprecation")
	public static void borraJTextfield(JTextField jtextfield,Robot robot) {
		Point punto = null;
        if (jtextfield != null)
        {
            robot.delay(getDelay());
            punto = jtextfield.getLocationOnScreen();
            robot.mouseMove(punto.x, punto.y);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(getDelay());
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(getDelay());
            while (!jtextfield.getText().isEmpty())
            {
                robot.delay(getDelay());
                robot.keyPress(KeyEvent.VK_DELETE);
                robot.delay(getDelay());
                robot.keyRelease(KeyEvent.VK_DELETE);
            }
        }
	}
}


