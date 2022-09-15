package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import Interface.MeInterface;

public class MeClient {

    public static void main(String[] args) throws IOException, NotBoundException {
        int cantEstudiantes = 0;
        int cantNotas = 0;
        int choice = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese cantidad de estudiantes");
        cantEstudiantes = Integer.parseInt(br.readLine());
        System.out.println("Ingrese cantidad de notas por estudiante");
        cantNotas = Integer.parseInt(br.readLine());

        String[][] notas = new String[cantEstudiantes][cantNotas + 4];
        for (int i = 0; i < cantEstudiantes; i++) {
            System.out.println("Ingrese nombre del estudiante");
            notas[i][0] = br.readLine().toString();
            for (int j = 1; j <= cantNotas; j++) {
                notas[i][j] = String.valueOf(getRandomFloat());
            }
            notas[i][cantNotas + 3] = "0";
        }
        do {
            System.out.println("1. Calcular nota mayor de cada estudiante");
            System.out.println("2. Calcular nota menor de cada estudiante");
            System.out.println("3. Calcular promedio del estudiante");
            System.out.println("4. Salir");
            System.out.println("OPCION:");
            choice = Integer.parseInt(br.readLine());
            if (choice != 4) {
                try {
                    MeInterface meInterface = (MeInterface) Naming.lookup("MomentoE");
                    notas = meInterface.calculate(notas, cantEstudiantes, cantNotas, choice);
                    switch (choice) {
                        case 1:
                            for (int i = 0; i < cantEstudiantes; i++) {
                                System.out.println("La nota mayor del estudiante: " + notas[i][0].toString() + " es: " + notas[i][cantNotas + 1]);
                            }
                            break;
                        case 2:
                            for (int i = 0; i < cantEstudiantes; i++) {
                                System.out.println("La nota menor del estudiante: " + notas[i][0].toString() + " es: " + notas[i][cantNotas + 2]);
                            }
                            break;
                        case 3:
                            for (int i = 0; i < cantEstudiantes; i++) {
                                System.out.println("El promedio del estudiante: " + notas[i][0].toString() + " es: " + notas[i][cantNotas + 3]);
                            }
                            break;
                        default:
                            System.out.println("ERROR");
                            break;
                    }
                } catch (MalformedURLException | RemoteException ex) {
                    Logger.getLogger(MeClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (choice != 4);
    }

    public static float getRandomFloat() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);
        Random r = new Random();
        float random = 0 + r.nextFloat() * (5 - 0);
        System.out.println("Su nota es: " + df.format(random));
        return random;
    }

}
