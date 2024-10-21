package modelo_datos;

import modeloDatos.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import util.Constantes;

public class ViajeTest {
    private Viaje viajeSinExtras_Standard;
    private Viaje viajeConMascota_Peligrosa;
    private Viaje viajeConBaul_SinAsfaltar;
    private Viaje viajeSinExtras_SinAsfaltar;
    private Viaje viajeSinExtras_Peligrosa;
    private Viaje viajeConMascota_Standard;

    public static void main(String[] args) {
        String[] args2 = { ViajeTest.class.getName() };
        JUnitCore.main(args2);
    }

    @Before
    public void setUp(){
        Viaje.setValorBase(5.0);

        Auto v = new Auto("abc123",4,true);
        Chofer c = new ChoferPermanente("1234567", "Facundo",2000, 2 );
        Cliente cliente = new Cliente("Roberto","123", "Roberto");

        //CASO BASE
        //Sin incrementos, zona standard. Caso base
        Pedido p = new Pedido(cliente, 1, false, false, 10, Constantes.ZONA_STANDARD);
        this.viajeSinExtras_Standard = new Viaje(p,c,v);

        //ZONAS
        //sin incrementos zona peligrosa
        Pedido p1 = new Pedido(cliente, 4, false, false, 10, Constantes.ZONA_PELIGROSA);
        this.viajeSinExtras_Peligrosa = new Viaje(p1,c,v);

        //Sin incrementos zona sin asfaltar
        Pedido p2 = new Pedido(cliente, 1, false, false, 10, Constantes.ZONA_SIN_ASFALTAR);
        this.viajeSinExtras_SinAsfaltar = new Viaje(p2,c,v);

        //CON MASCOTA
        Pedido p3 = new Pedido(cliente, 1, true, false, 10, Constantes.ZONA_STANDARD);
        this.viajeConMascota_Standard = new Viaje(p3,c,v);

        //COMBINACIONES
        //Zona peligrosa con mascota
        Pedido p4 = new Pedido(cliente, 4, true, false, 10, Constantes.ZONA_PELIGROSA);
        this.viajeConMascota_Peligrosa = new Viaje(p4,c,v);

        //Zona sin asfaltar con baul
        Pedido p5 = new Pedido(cliente, 4, false, true, 10, Constantes.ZONA_SIN_ASFALTAR);
        this.viajeConBaul_SinAsfaltar = new Viaje(p5,c,v);

    }

//    Viaje sin incrementos standard 10.5
//    Viaje sin incrementos peligrosa 17.0
//    Viaje sin incrementos sin asfaltar 13.5
//    Viaje con mascota standard 21.0
//    Viaje con mascota peligrosa 29.0
//    Viaje con baul sin asfaltar 21.0

    @After
    public void tearDown(){
        viajeSinExtras_Standard = null;
        viajeConMascota_Peligrosa = null;
        viajeSinExtras_Peligrosa = null;
        viajeConMascota_Standard = null;
        viajeSinExtras_SinAsfaltar = null;
        viajeConBaul_SinAsfaltar = null;
    }

    //CASO BASE
    //Sin incrementos, zona standard. Caso base
    @Test
    public void getValorSinIncrementos_StandardTest(){

        Assert.assertEquals("No se calcula bien el valor del viaje sin extras y estandard",10.5, viajeSinExtras_Standard.getValor(),0.0000001);
    }

    //Sin incrementos, zona peligrosa.
    @Test
    public void getValorSinIncrementos_PeligrosaTest(){

        Assert.assertEquals("No se calcula bien el valor del viaje sin incrementos zona peligrosa",17.0, viajeSinExtras_Peligrosa.getValor(),0.0000001);
    }

    //Sin incrementos zona sin asfaltar
    @Test
    public void getValorSinExtras_SinAsfaltarTest(){

        Assert.assertEquals("No se calcula bien el valor del viaje sin incrementos Sin Asfaltar",13.5,viajeSinExtras_SinAsfaltar.getValor(),0.0000001);
    }

    //CON MASCOTA
    @Test
    public void getValorConMascota_StandardTest(){

        Assert.assertEquals("No se calcula bien el valor del viaje con mascota estandar",21.0, viajeConMascota_Standard.getValor(),0.0000001);
    }

    //Zona sin asfaltar con mascota zona peligrosa
    @Test
    public void getValorConMascota_PeligrosaTest(){

        Assert.assertEquals("No se calcula bien el valor del viaje con mascota zona peligrosa",29.0, viajeConMascota_Peligrosa.getValor(),0.0000001);
    }

    //Zona sin asfaltar con baul
    @Test
    public void getValorConBaul_SinAsfaltarTest(){

        Assert.assertEquals("No se calcula bien el valor del viaje con baul sin asfaltar",21.0, viajeConBaul_SinAsfaltar.getValor(),0.0000001);
    }

    @Test
    public void finalizarViajeTest(){
        viajeSinExtras_Standard.finalizarViaje(3);
        Assert.assertEquals("No se setea bien el valor de calificacion", viajeSinExtras_Standard.getCalificacion(),3);
        Assert.assertTrue("No se setea bien el estado de finalizado", viajeSinExtras_Standard.isFinalizado());
    }


}
