import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opc = 0;
        try{
            // Obtener registro RMI en el puerto especifico (localhost, 1099) host, puerto del servidor
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);

            // Crear instancia de la implementacion de la interfaz
            interfaz objetoRemoto = (interfaz) registro.lookup("ClienteRemoto");

            // menu de opciones
            System.out.println("1). Suma");
            System.out.println("2). Resta");
            System.out.println("3). Multiplicacion");
            System.out.println("4). Division");
            System.out.print("Ingrese la operacion que desea realizar\n> ");
            String sel = sc.nextLine();

            // Validacion si la opcion es un numero y si es mayor a 0 y menor a 5
            try {
                opc = Integer.parseInt(sel);
                if (opc < 1 || opc > 4) {
                    System.out.println(ConsoleStyles.FG_RED + "Opcion invalida. intentelo nuevamente!"+ ConsoleStyles.RESET);
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println(ConsoleStyles.FG_RED + "Ingrese un numero, intentelo nuevamente!"+ ConsoleStyles.RESET);
                return;
            }

            System.out.print("Ingrese el primer numero\n> ");
            String val_a = sc.nextLine();

            System.out.print("Ingrese el segundo numero\n> ");
            String val_b = sc.nextLine();

            // Validar si los numeros son validos
            Number a = obtenerNumero(val_a);
            Number b = obtenerNumero(val_b);

            if (a == null || b == null) {
                System.out.println(ConsoleStyles.FG_RED + "Uno o ambos valores no son validos. intentelo nuevamente!"+ ConsoleStyles.RESET);
                return;
            }


            switch (opc) {
                case 1:
                    double suma = objetoRemoto.suma(a.doubleValue(), b.doubleValue());

                    // Redondear a 2 decimales
                    suma = Math.round(suma * 100.0) / 100.0;

                    System.out.println(ConsoleStyles.FG_GREEN + "La suma es: "+ ConsoleStyles.RESET + suma);
                    break;
                case 2:
                    double resta = objetoRemoto.resta(a.doubleValue(), b.doubleValue());

                    // Redondear a 2 decimales
                    resta = Math.round(resta * 100.0) / 100.0;

                    System.out.println(ConsoleStyles.FG_GREEN + "La resta es: "+ ConsoleStyles.RESET + resta);
                    break;
                case 3:
                    double multiplicacion = objetoRemoto.multiplicacion(a.doubleValue(), b.doubleValue());

                    // Redondear a 2 decimales
                    multiplicacion = Math.round(multiplicacion * 100.0) / 100.0;

                    System.out.println(ConsoleStyles.FG_GREEN + "La multiplicacion es: "+ ConsoleStyles.RESET + multiplicacion);
                    break;
                case 4:
                    if (b.doubleValue() == 0) {
                        System.out.println(ConsoleStyles.FG_RED + "No se puede dividir entre 0"+ ConsoleStyles.RESET);
                        break;
                    }
                    double division = objetoRemoto.division(a.doubleValue(), b.doubleValue());

                    // Redondear a 2 decimales
                    division = Math.round(division * 100.0) / 100.0;

                    System.out.println(ConsoleStyles.FG_GREEN + "La division es: "+ ConsoleStyles.RESET + division);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close(); // Cerrar el scanner
        }
    }

    public static Number obtenerNumero(String mensaje) {
        try {
            // Intentar convertir a un entero
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