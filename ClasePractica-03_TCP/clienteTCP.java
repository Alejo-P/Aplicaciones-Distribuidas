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
            System.out.println("*---------------------------------*");
            System.out.println("Direccion IP del cliente: " + direccionIP);
            System.out.println("Puerto del cliente: " + puerto);
            System.out.println("*---------------------------------*");

            // Leer el primer mensaje de bienvenida del servidor
            System.out.println("Mensaje del servidor: " + bufferEntrada.readLine());

            // Ciclo para enviar y recibir preguntas del servidor
            while (true) {
                // Recibir la pregunta del servidor
                String pregunta = bufferEntrada.readLine();
                System.out.println("Pregunta del servidor: " + pregunta);

                // Revisar si el servidor ha terminado o cerrado la conexión
                if (pregunta == null || pregunta.equals("Gracias por participar.")) {
                    System.out.println("Conexión cerrada por el servidor.");
                    break;
                }

                // Enviar la respuesta del cliente
                System.out.println("Escribe tu respuesta (escribe 'exit' para salir):\n> ");
                String respuestaCliente = scanner.nextLine();
                if (respuestaCliente.equalsIgnoreCase("exit")) {
                    System.out.println("Cerrando conexión...");
                    break;
                }
                bufferSalida.println(respuestaCliente);

                // Leer la respuesta del servidor si la respuesta fue correcta o incorrecta
                String respuestaServidor = bufferEntrada.readLine();
                System.out.println("Respuesta del servidor: " + respuestaServidor);

                System.out.println("*---------------------------------*");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

