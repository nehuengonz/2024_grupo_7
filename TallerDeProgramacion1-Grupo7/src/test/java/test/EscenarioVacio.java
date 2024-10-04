package test;

public class EscenarioVacio {
    public Object sistema;

    public EscenarioVacio(){
    }

    public void setUp(){
        sistema = new Object();
    }

    public void tearDown(){
        sistema = null;
    }
}
