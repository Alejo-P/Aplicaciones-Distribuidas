import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClienteRMI {
    static Scanner scanner = new Scanner(System.in);
    private static double imc = 0;

    private static Registry registry;
    private static BMIRemoto bmi;
    public static void main(String[] args) {
        try {
            // Localiza el registro RMI en el servidor
            registry = LocateRegistry.getRegistry("localhost", 1099);
            
            // Busca el objeto remoto
            bmi = (BMIRemoto) registry.lookup("BMIRemoto");
            
            
            // Menu
            int opcion = menu();
            while (opcion != 3) {
                System.out.println("");
                switch (opcion) {
                    case 1:
                        calcularIMC();
                        break;
                    case 2:
                        categoriaIMC();
                        break;
                }
                opcion = menu();
            }
        } catch (Exception e) {
            System.out.println("Cliente RMI falló: " + e.getMessage());
        }
    }

    public static int menu() {
        System.out.println(ConsoleStyles.FG_GREEN + "------Calculadora de IMC------" + ConsoleStyles.RESET);
        System.out.println("1." + ConsoleStyles.FG_CYAN + " Calcular IMC" + ConsoleStyles.RESET);
        System.out.println("2." + ConsoleStyles.FG_CYAN + " Categoria de IMC" + ConsoleStyles.RESET);
        System.out.println("3." + ConsoleStyles.FG_RED + " Salir" + ConsoleStyles.RESET);
        System.out.print(ConsoleStyles.FG_YELLOW + "Seleccione una opcion: " + ConsoleStyles.RESET);
        
        int opcion = scanner.nextInt();
        while (opcion < 1 || opcion > 3) {
            System.out.println(ConsoleStyles.FG_RED + "Opcion invalida" + ConsoleStyles.RESET);
            System.out.print(ConsoleStyles.FG_YELLOW + "Seleccione una opcion: " + ConsoleStyles.RESET);
            opcion = scanner.nextInt();
        }
        return opcion;
    }

    public static void calcularIMC() {
        try {
            System.out.print(ConsoleStyles.FG_YELLOW + "Introduzca su peso en kilogramos: " + ConsoleStyles.RESET);
            double peso = scanner.nextDouble();
            System.out.print(ConsoleStyles.FG_YELLOW + "Introduzca su altura en metros: " + ConsoleStyles.RESET);
            double altura = scanner.nextDouble();
            imc = bmi.calculaIMC(peso, altura);
            imc = Math.round(imc * 100.0) / 100.0;
            System.out.println(ConsoleStyles.FG_GREEN + "Su IMC es: " + ConsoleStyles.RESET + imc);
        } catch (Exception e) {
            System.out.println(ConsoleStyles.BG_RED + "Error: " + ConsoleStyles.RESET + e.getMessage());
        }
    }

    public static void categoriaIMC() {
        try {
            if (imc == 0) {
                System.out.println(ConsoleStyles.FG_RED + "Primero calcule su IMC" + ConsoleStyles.RESET);
                return;
            }
            String categoria = bmi.categoriaIMC(imc);
            System.out.println(ConsoleStyles.FG_GREEN + "Categoria de IMC: " + ConsoleStyles.RESET + categoria);
        } catch (Exception e) {
            System.out.println(ConsoleStyles.BG_RED + "Error: " + ConsoleStyles.RESET + e.getMessage());
        }
    }
}
