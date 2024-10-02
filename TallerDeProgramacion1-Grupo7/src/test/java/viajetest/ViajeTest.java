package viajetest;

import modeloDatos.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import util.Constantes;

public class ViajeTest {
    Viaje viajeSinExtras_Standard;
    Viaje viajeConExtras_Peligrosa;
    Viaje viajeConExtras_SinAsfaltar;
    Viaje viajeSinExtras_SinAsfaltar;
    public static void main(String[] args) {
        String[] args2 = { ViajeTest.class.getName() };
        JUnitCore.main(args2);
    }

    @Before
    public void setUp(){
        Viaje.setValorBase(5.0);

        Moto v = new Moto("abc123");
        Chofer c = new ChoferPermanente("1234567", "Facundo",2000, 2 );
        Cliente cliente = new Cliente("Roberto","123", "Roberto");
        Pedido p = new Pedido(cliente, 1, false, false, 10, Constantes.ZONA_STANDARD);

        this.viajeSinExtras_Standard = new Viaje(p,c,v);

        Auto v1 = new Auto("abc123",4,true);
        Pedido p1 = new Pedido(cliente, 4, true, true, 10, Constantes.ZONA_PELIGROSA);

        this.viajeConExtras_Peligrosa = new Viaje(p1,c,v1);

        Pedido p2 = new Pedido(cliente, 4, true, true, 10, Constantes.ZONA_SIN_ASFALTAR);

        this.viajeConExtras_SinAsfaltar = new Viaje(p2,c,v1);

        Pedido p3 = new Pedido(cliente, 1, false, false, 10, Constantes.ZONA_SIN_ASFALTAR);

        this.viajeSinExtras_SinAsfaltar = new Viaje(p3,c,v);
    }

    @After
    public void tearDown(){

        viajeSinExtras_Standard = null;
        viajeConExtras_Peligrosa = null;
    }

    @Test
    public void getValorSinExtras_StandardTest(){

        Assert.assertEquals("No se calcula bien el valor del viaje sin extras y estandard",10.5, viajeSinExtras_Standard.getValor(),0.0000001);
    }

    @Test
    public void getValorConExtras_PeligrosaTest(){

        Assert.assertEquals("No se calcula bien el valor del viaje con extras y zona peligrosa",33.5, viajeConExtras_Peligrosa.getValor(),0.0000001);
    }

    @Test
    public void getValorConExtras_SinAsfaltarTest(){

        Assert.assertEquals("No se calcula bien el valor del viaje con extras Sin Asfaltar",33.0,viajeConExtras_SinAsfaltar.getValor(),0.0000001);
    }

    @Test
    public void getValorSinExtras_SinAsfaltarTest(){

        Assert.assertEquals("No se calcula bien el valor del viaje sin extras Sin Asfaltar",13.5,viajeSinExtras_SinAsfaltar.getValor(),0.0000001);
    }

    @Test
    public void finalizarViajeTest(){
        viajeSinExtras_Standard.finalizarViaje(3);
        Assert.assertEquals("No se setea bien el valor de calificacion", viajeSinExtras_Standard.getCalificacion(),3);
    }


}
