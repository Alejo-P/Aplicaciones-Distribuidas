import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class clienteTCPCom {

    public static void main(String[] args) {
        try (
            Socket cliente = new Socket("172.29.39.157", 1234);
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
                // Leer la entrada del usuario
                System.out.println("Escribe un mensaje para el servidor (escribe 'exit' para salir):");
                String mensajeCliente = scanner.nextLine();
                // Enviar el mensaje al servidor
                bufferSalida.println(mensajeCliente);
                if (mensajeCliente.equalsIgnoreCase("exit")) {
                    System.out.println("Cerrando conexión...");
                    break;
                }

                // Leer la respuesta del servidor
                String respuestaServidor = bufferEntrada.readLine();
                if (respuestaServidor == null) {
                    System.out.println("El servidor ha cerrado la conexión.");
                    break;
                }
                System.out.println("Respuesta del servidor: " + respuestaServidor);
                System.out.println("*---------------------------------*");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

