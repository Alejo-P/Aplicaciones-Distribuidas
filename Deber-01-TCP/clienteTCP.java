import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class clienteTCP {
    public static void main(String[] args) {
        try (
            Socket cliente = new Socket("localhost", 3000);
            BufferedReader bufferEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter bufferSalida = new PrintWriter(cliente.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in)
        ) {
            InetAddress direccionIP = cliente.getInetAddress();
            int puerto = cliente.getPort();
            System.out.println(ConsoleStyles.FG_YELLOW + ConsoleStyles.BOLD + "Direccion IP del servidor:" + ConsoleStyles.RESET + " " + direccionIP);
            System.out.println(ConsoleStyles.FG_YELLOW + ConsoleStyles.BOLD + "Puerto del servidor:" + ConsoleStyles.RESET + " " + puerto);

            while (true) {
                // Obtener la respueta del servidor
                String mensajeServidor = bufferEntrada.readLine();
                if (mensajeServidor == null) {
                    System.out.println(ConsoleStyles.FG_RED + ConsoleStyles.BOLD + "El servidor ha cerrado la conexión." + ConsoleStyles.RESET);
                    break;
                }

                String[] partes = mensajeServidor.split(",", 2);
                String tipoMensaje = partes[0].trim();
                String contenido = partes[1].trim();

                // Procesar el mensaje del servidor segun el tipo
                if (tipoMensaje.equalsIgnoreCase("Estado")) {
                    System.out.println(ConsoleStyles.FG_GREEN + "Estado del servidor:"  + ConsoleStyles.RESET + " " + contenido);
                    if (contenido.equalsIgnoreCase("Conexión cerrada.")) {
                        break;
                    }
                } else if (tipoMensaje.equalsIgnoreCase("Pregunta")) {
                    System.out.println("*---------------------------------*");
                    System.out.println(ConsoleStyles.FG_BLUE + "Pregunta del servidor:" + ConsoleStyles.RESET + " " + contenido);
                    System.out.println("Escribe " + ConsoleStyles.FG_YELLOW + ConsoleStyles.BOLD + "'exit'" + ConsoleStyles.RESET + " para salir.");
                    System.out.print(ConsoleStyles.FG_YELLOW + ConsoleStyles.BOLD  + "Escribe tu respuesta: " + ConsoleStyles.RESET);
                    String respuestaCliente = scanner.nextLine();
                    bufferSalida.println(respuestaCliente);
                    System.out.println("*---------------------------------*");
                } else if (tipoMensaje.equalsIgnoreCase("Respuesta")) {
                    System.out.println(ConsoleStyles.FG_CYAN + "Respuesta del servidor:" + ConsoleStyles.RESET + " " + contenido);
                } else {
                    System.out.println(ConsoleStyles.FG_PURPLE + "Mensaje del servidor:"+ ConsoleStyles.RESET + " " + contenido);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
