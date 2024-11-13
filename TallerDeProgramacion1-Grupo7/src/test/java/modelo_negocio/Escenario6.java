package modelo_negocio;
//escenario donde se encuentran con vehiculos invalidos para pedidos con mascota

import modeloDatos.*;
import modeloNegocio.Empresa;
import util.Constantes;

import java.util.ArrayList;
import java.util.HashMap;


public class Escenario6 {

    public void setup() throws Exception {
        Empresa.getInstance();

        Chofer chofer1=new ChoferPermanente("1234567","Roberto",2020,0);
        Chofer chofer2=new ChoferPermanente("1234568","Alberto",2019,3);
        Chofer chofer3=new ChoferTemporario("11111111","Javier");
        Chofer chofer4=new ChoferTemporario("22222222","Sergio");

        Vehiculo auto1=new Auto("dfg456",3,false);
        Vehiculo moto1=new Moto("pat333");
        Vehiculo combi1=new Combi("combi222",10,false);

        Empresa.getInstance().agregarCliente("facundo","123","Facundo");
        Empresa.getInstance().agregarCliente("thiago","321","Thiago");
        Empresa.getInstance().agregarCliente("nehuen","4567","Nehuen");

        Cliente cliente1 = Empresa.getInstance().getClientes().get("facundo");


        Empresa.getInstance().agregarChofer(chofer1);
        Empresa.getInstance().agregarChofer(chofer2);
        Empresa.getInstance().agregarChofer(chofer3);
        Empresa.getInstance().agregarChofer(chofer4);

        Empresa.getInstance().agregarVehiculo(auto1);
        Empresa.getInstance().agregarVehiculo(moto1);
        Empresa.getInstance().agregarVehiculo(combi1);

        ArrayList<Chofer> chofdesocupados= new ArrayList<>();
        chofdesocupados.add(chofer1);
        chofdesocupados.add(chofer2);
        chofdesocupados.add(chofer3);
        chofdesocupados.add(chofer4);
        Empresa.getInstance().setChoferesDesocupados(chofdesocupados);

        ArrayList<Vehiculo> vehdesocupados= new ArrayList<>();
        vehdesocupados.add(auto1);
        vehdesocupados.add(moto1);
        vehdesocupados.add(combi1);
        Empresa.getInstance().setVehiculosDesocupados(vehdesocupados);

    }

    public void teardown() {
        Empresa.getInstance().getHistorialViajeCliente(Empresa.getInstance().getClientes().get("facundo")).clear();
        Empresa.getInstance().getHistorialViajeCliente(Empresa.getInstance().getClientes().get("thiago")).clear();
        Empresa.getInstance().getHistorialViajeCliente(Empresa.getInstance().getClientes().get("nehuen")).clear();
        Empresa.getInstance().getChoferes().clear();
        Empresa.getInstance().getVehiculos().clear();
        Empresa.getInstance().getVehiculosDesocupados().clear();
        Empresa.getInstance().getChoferesDesocupados().clear();
        Empresa.getInstance().getClientes().clear();
        Empresa.getInstance().getPedidos().clear();
    }

}
