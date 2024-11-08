package modelo_datos;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Administrador;

public class TestAdministrador {

    private Administrador admin;

    @Before
    public void setUp() {
        admin = Administrador.getInstance();
    }

    @Test
    public void testGetInstance() {
        Administrador admin2 = Administrador.getInstance();
        assertSame("Se deben obtener la misma instancia", admin, admin2);
    }

    @Test
    public void testPass() {
        assertEquals("admin", admin.getPass());
    }


}
