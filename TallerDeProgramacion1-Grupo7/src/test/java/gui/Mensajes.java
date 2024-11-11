package gui;

public enum Mensajes {
	ACEPTAR("Aceptar"), AGREGAR("Agregar"), CONSULTAS("Consultas"), INGRESO("Ingreso"), NOMBRE("Nombre"),
    REGISTRAR("Registrar"), NOMBRE_USUARIO("Nombre de Usuario:"), TITULO("Ejemplo Test de GUI"),
    ERROR_PASS_CORTO("Contrase�a muy corta"), ERROR_NOMBRE_NULO("Nombre nulo"), ERROR_PASS_NULO("Contrase�a nula"),
    ERROR_CONFIRMACION_PASS_NULO("Confirmacion de contrase�a nula"), ERROR_NOMBRE_VACIO("Nombre de usuario vac�o"),
    ERROR_PASS_VACIO("Contrase�a vac�a"), ERROR_CONFIRMACION_PASS_VACIA("Confirmaci�n de contrase�a vac�a"),
    ERROR_CONFIRMACION_PASS_DIFERENTES("La contrase�a y su confirmai�n no coinciden"), ERROR_NOMBRE_EXISTENTE("Nombre de usuario ya existe"), ERROR_PASS_INCORRECTO("Contrase�a Incorrecta"), PASS("Contrase�a:"), CONFIRMACION_PASS("Confirmaci�n de Contrase�a"), LOGIN("Ingresar"), USUARIO_REGISTRADO("Nuevo usuario Registrado"), LOGIN_OK("Ingreso exitoso"), ERROR_USUARIO_INEXISTENTE("Usuario Inexistente");

    private String valor;

    private Mensajes(String valor)
    {
	this.valor = valor;
    }

    public String getValor()
    {
	return valor;
    }

    public void setValor(String valor)
    {
	this.valor = valor;
    }
   
}
