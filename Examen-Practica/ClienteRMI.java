import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClienteRMI {
    public static void main(String[] args) {
        try {
            // Obtiene una referencia al registro RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            
            // Obtiene una referencia al objeto remoto
            BMIRemoto obj = (BMIRemoto) registry.lookup("BMIRemoto");
            
            // Crea un objeto Scanner para leer de la entrada estándar
            Scanner scanner = new Scanner(System.in);
            
            // Pide al usuario que introduzca su peso
            System.out.print("Introduzca su peso en kilogramos: ");
            double peso = scanner.nextDouble();
            
            // Pide al usuario que introduzca su altura
            System.out.print("Introduzca su altura en metros: ");
            double altura = scanner.nextDouble();
            
            // Calcula el IMC
            double imc = obj.calculaIMC(peso, altura);
            
            // Muestra el IMC
            System.out.println("Su IMC es: " + imc);
        } catch (Exception e) {
            System.out.println("Cliente RMI falló: " + e.getMessage());
        }
    }
}
