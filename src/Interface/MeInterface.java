package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MeInterface extends Remote{
    public String[][] calculate(String[][] matNotas, int cantEstudiantes, int cantNotas, int choice) throws RemoteException;
}
