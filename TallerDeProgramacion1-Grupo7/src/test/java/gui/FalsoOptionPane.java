package gui;

import java.awt.Component;

import vista.IOptionPane;

public class FalsoOptionPane implements IOptionPane{
    private String mensaje = null;

    public FalsoOptionPane() {
        super();
    }


    public String getMensaje() {
        return mensaje;
    }

	@Override
	public void ShowMessage(String arg0) {
		this.mensaje = arg0;
		
	}
}
