package test;

import vista.IOptionPane;

public class VentanaErrores implements IOptionPane {
    private String mensajeError;

    public VentanaErrores() {
    }

    @Override
    public void ShowMessage(String s) {
        mensajeError = s;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }
}
