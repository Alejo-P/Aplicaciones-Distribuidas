import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opc = 0;

        try {
            // Obtener registro RMI en el puerto específico (localhost, 1099) host, puerto del servidor
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);

            // Crear instancia de la implementación de la interfaz
            interfaz objetoRemoto = (interfaz) registro.lookup("ClienteRemoto");

            while (true) { // Bucle para repetir el menú hasta que se elija "Salir"
                // Menú de opciones
                System.out.println(ConsoleStyles.FG_BLUE + "Calculadora RMI" + ConsoleStyles.RESET);
                System.out.println(ConsoleStyles.FG_YELLOW + "Seleccione una operación:" + ConsoleStyles.RESET);
                System.out.println(ConsoleStyles.FG_GREEN + "1)" + ConsoleStyles.RESET + " Suma");
                System.out.println(ConsoleStyles.FG_GREEN + "2)" + ConsoleStyles.RESET + " Resta");
                System.out.println(ConsoleStyles.FG_GREEN + "3)" + ConsoleStyles.RESET + " Multiplicación");
                System.out.println(ConsoleStyles.FG_GREEN + "4)" + ConsoleStyles.RESET + " División");
                System.out.println(ConsoleStyles.FG_RED + "5)" + ConsoleStyles.RESET + " Salir");
                System.out.print("Ingrese la operación que desea realizar\n> ");
                String sel = sc.nextLine();

                // Validación si la opción es un número y si está entre 1 y 5
                try {
                    opc = Integer.parseInt(sel);
                    if (opc < 1 || opc > 5) {
                        System.out.println(ConsoleStyles.FG_RED + "Opción inválida, inténtelo nuevamente." + ConsoleStyles.RESET);
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(ConsoleStyles.FG_RED + "Opción inválida, inténtelo nuevamente." + ConsoleStyles.RESET);
                    continue;
                }

                if (opc == 5) {
                    System.out.println(ConsoleStyles.FG_RED + "Saliendo..." + ConsoleStyles.RESET);
                    break; // Salir del bucle y finalizar el programa
                }

                System.out.print("Ingrese el primer número\n> ");
                String val_a = sc.nextLine();
                System.out.print("Ingrese el segundo número\n> ");
                String val_b = sc.nextLine();

                // Validar si los números son válidos
                Number a = obtenerNumero(val_a);
                Number b = obtenerNumero(val_b);

                if (a == null || b == null) {
                    System.out.println(ConsoleStyles.FG_RED + "Valores inválidos, inténtelo nuevamente." + ConsoleStyles.RESET);
                    continue;
                }

                switch (opc) {
                    case 1:
                        double suma = objetoRemoto.suma(a.doubleValue(), b.doubleValue());
                        suma = Math.round(suma * 100.0) / 100.0;
                        System.out.println(ConsoleStyles.FG_GREEN + "Resultado:" + ConsoleStyles.RESET + " La suma es: " + suma);
                        break;
                        case 2:
                        double resta = objetoRemoto.resta(a.doubleValue(), b.doubleValue());
                        resta = Math.round(resta * 100.0) / 100.0;
                        System.out.println(ConsoleStyles.FG_GREEN + "Resultado:" + ConsoleStyles.RESET + " La resta es: " + resta);
                        break;
                        case 3:
                        double multiplicacion = objetoRemoto.multiplicacion(a.doubleValue(), b.doubleValue());
                        multiplicacion = Math.round(multiplicacion * 100.0) / 100.0;
                        System.out.println(ConsoleStyles.FG_GREEN + "Resultado:" + ConsoleStyles.RESET + " La multiplicacion es: " + multiplicacion);
                        break;
                        case 4:
                        if (b.doubleValue() == 0) {
                            System.out.println("No se puede dividir entre 0");
                            break;
                        }
                        double division = objetoRemoto.division(a.doubleValue(), b.doubleValue());
                        System.out.println(ConsoleStyles.FG_GREEN + "Resultado:" + ConsoleStyles.RESET + " La division es: " + division);
                        division = Math.round(division * 100.0) / 100.0;
                        break;
                    default:
                        break;
                    
                    }
                System.out.println("-".repeat(50));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close(); // Cerrar el scanner
        }
    }

    public static Number obtenerNumero(String mensaje) {
        try {
            return Integer.parseInt(mensaje);
        } catch (NumberFormatException e1) {
            try {
                return Float.parseFloat(mensaje);
            } catch (NumberFormatException e2) {
                System.out.println("Error: el valor no es un número válido.");
                return null;
            }
        }
    }
}
