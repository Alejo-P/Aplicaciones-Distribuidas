// Este es el código del cliente, que permanece igual
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

            // Ciclo para enviar y recibir mensajes del servidor
            while (true) {
                // Leer el mensaje del servidor (pregunta)
                String mensajeRecibido = bufferEntrada.readLine();
                if (mensajeRecibido == null || mensajeRecibido.equalsIgnoreCase("Gracias por participar. ¡Adiós!")) {
                    System.out.println("El servidor ha cerrado la conexión.");
                    break;
                }
                System.out.println("Pregunta del servidor: " + mensajeRecibido);

                // Enviar una respuesta al servidor
                System.out.println("Escribe tu respuesta (escribe 'exit' para salir):\n> ");
                String mensajeEnvio = scanner.nextLine();
                bufferSalida.println(mensajeEnvio);

                // Verificar si el cliente desea salir
                if (mensajeEnvio.equalsIgnoreCase("exit")) {
                    System.out.println("Cerrando conexión...");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

