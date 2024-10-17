package modelo_negocio;

import java.util.ArrayList;

import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Combi;
import modeloDatos.Moto;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloNegocio.Empresa;
import util.Constantes;

public class Escenario2 {



    public Escenario2() {

    }

    public void setup() throws Exception {
        Empresa.getInstance();

        Cliente cliente1=new Cliente("facundo","123","Facundo");
        Cliente cliente2=new Cliente("thiago","321","Thiago");
        Cliente cliente3=new Cliente("nehuen","4567","Nehuen");

        ChoferPermanente chofer1=new ChoferPermanente("1234567","Roberto",2020,0);
        ChoferPermanente chofer2=new ChoferPermanente("1234568","Alberto",2019,3);
        ChoferTemporario chofer3=new ChoferTemporario("11111111","Javier");
        ChoferTemporario chofer4=new ChoferTemporario("22222222","Sergio");

        Vehiculo auto1=new Auto("abc123",4,true);
        Vehiculo auto2=new Auto("dfg456",3,false);
        Vehiculo moto1=new Moto("pat333");
        Vehiculo combi1=new Combi("combi222",10,false);
        Vehiculo combi2=new Combi("combi111",10,true);

        Empresa.getInstance().agregarCliente("facundo","123","Facundo");
        Empresa.getInstance().agregarCliente("thiago","321","Thiago");
        Empresa.getInstance().agregarCliente("nehuen","4567","Nehuen");


        Empresa.getInstance().agregarChofer(chofer1);
        Empresa.getInstance().agregarChofer(chofer2);
        Empresa.getInstance().agregarChofer(chofer3);
        Empresa.getInstance().agregarChofer(chofer4);

        Empresa.getInstance().agregarVehiculo(auto1);
        Empresa.getInstance().agregarVehiculo(auto2);
        Empresa.getInstance().agregarVehiculo(moto1);
        Empresa.getInstance().agregarVehiculo(combi1);
        Empresa.getInstance().agregarVehiculo(combi2);

        Pedido pedido1=new Pedido(cliente1,3,true,true,10,Constantes.ZONA_PELIGROSA);
        Pedido pedido2=new Pedido(cliente2,1,false,false,3,Constantes.ZONA_STANDARD);
        Pedido pedido3=new Pedido(cliente3,8,false,true,1,Constantes.ZONA_PELIGROSA);

        Empresa.getInstance().agregarPedido(pedido1);
        Empresa.getInstance().agregarPedido(pedido2);
        Empresa.getInstance().agregarPedido(pedido3);

        ArrayList<Chofer> chofdesocupados= new ArrayList<>();
        chofdesocupados.add(chofer1);
        chofdesocupados.add(chofer2);
        chofdesocupados.add(chofer3);
        chofdesocupados.add(chofer4);
        Empresa.getInstance().setChoferesDesocupados(chofdesocupados);


        ArrayList<Vehiculo> vehdesocupados= new ArrayList<>();
        vehdesocupados.add(auto1);
        vehdesocupados.add(auto2);
        vehdesocupados.add(moto1);
        vehdesocupados.add(combi1);
        vehdesocupados.add(combi2);
        Empresa.getInstance().setVehiculosDesocupados(vehdesocupados);

    }

    public void tearDown() {
        Empresa.getInstance().getChoferes().clear();
        Empresa.getInstance().getVehiculos().clear();
        Empresa.getInstance().getClientes().clear();
        Empresa.getInstance().getPedidos().clear();

    }
}