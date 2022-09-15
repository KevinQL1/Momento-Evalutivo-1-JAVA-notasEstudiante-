package Implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;
import Interface.MeInterface;

public class MeImplement extends UnicastRemoteObject implements MeInterface {

    public String[][] notas;
    public float notaMayor;
    public float notaMenor;
    public float promedio;

    public MeImplement(String[][] notas) throws RemoteException {
        this.notas = notas;
        this.notaMayor = 0;
        this.notaMenor = 0;
        this.promedio = 0;
    }

    public String[][] calculateMayor(String[][] matNotaMayor, int cantEstudiantes, int cantNotas) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        for (int i = 0; i < cantEstudiantes; i++) {
            this.notaMayor = 0;
            for (int j = 1; j <= cantNotas; j++) {
                if (matNotaMayor[i][j] != null && matNotaMayor[i][j] != "") {
                    if (Float.compare(Float.parseFloat(matNotaMayor[i][j]), notaMayor) > 0) {
                        notaMayor = Float.parseFloat(matNotaMayor[i][j]);
                    }
                }

            }
            matNotaMayor[i][cantNotas + 1] = String.valueOf(df.format(notaMayor));
        }
        return matNotaMayor;
    }

    public String[][] calculateMenor(String[][] matNotaMenor, int cantEstudiantes, int cantNotas) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        for (int i = 0; i < cantEstudiantes; i++) {
            notaMenor = Float.parseFloat(matNotaMenor[i][1]);
            for (int j = 2; j <= cantNotas; j++) {
                if (matNotaMenor[i][j] != null && matNotaMenor[i][j] != "") {
                    if (Float.compare(Float.parseFloat(matNotaMenor[i][j]), notaMenor) < 0) {
                        notaMenor = Float.parseFloat(matNotaMenor[i][j]);
                    }
                }

            }
            matNotaMenor[i][cantNotas + 2] = String.valueOf(df.format(notaMenor));
        }
        return matNotaMenor;
    }

    public String[][] calculatePromedio(String[][] matPromedioEstudiante, int cantEstudiantes, int cantNotas) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);
        for (int i = 0; i < cantEstudiantes; i++) {
            float suma = 0;
            this.promedio = 0;
            for (int j = 1; j <= cantNotas; j++) {
                if (matPromedioEstudiante[i][j] != null && matPromedioEstudiante[i][j] != "") {
                    suma = Float.sum(suma, Float.parseFloat(matPromedioEstudiante[i][j]));
                }
            }
            promedio = suma / cantNotas;
            matPromedioEstudiante[i][cantNotas + 3] = String.valueOf(df.format(promedio));
        }
        return matPromedioEstudiante;
    }

    public String[][] calculate(String[][] matNotas, int cantEstudiantes, int cantNotas, int choice) throws RemoteException {
        switch (choice) {
            case 1:
                return calculateMayor(matNotas, cantEstudiantes, cantNotas);
            case 2:
                return calculateMenor(matNotas, cantEstudiantes, cantNotas);
            case 3:
                return calculatePromedio(matNotas, cantEstudiantes, cantNotas);
            default:
                System.out.println("ERROR");
                break;
        }
        return null;
    }

}
