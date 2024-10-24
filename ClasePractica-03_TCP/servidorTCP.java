import java.net.ServerSocket;
import java.net.Socket;

public class servidorTCP {
    public static int puerto = 3000;

    public static void main(String[] args) {
        try (ServerSocket socketServidor = new ServerSocket(puerto)) {
            System.out.println("Servidor TCP iniciado en el puerto " + puerto);

            while (true) {
                 // Esperar a que un cliente se conecte
                Socket cliente = socketServidor.accept();
                
                // Crear un hilo para manejar la respuesta al cliente
                hiloClienteServidor hilo = new hiloClienteServidor(cliente);
                hilo.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

