package Server;

import Implement.MeImplement;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MeServer {
  public static void main(String []args) throws RemoteException { 
    Registry reg = LocateRegistry.createRegistry(1099);
    MeImplement meImplement = new MeImplement (new String[0][0]);
    reg.rebind("MomentoE", meImplement);
    System.out.println("servidor iniciado");
  
  }
}
