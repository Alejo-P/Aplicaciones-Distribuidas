import java.net.ServerSocket;
import java.net.Socket;

public class servidorTCPCom {
    public static int puerto = 3000;

    public static void main(String[] args) {
        try (ServerSocket socketServidor = new ServerSocket(puerto)) {
            System.out.println("Servidor TCP iniciado en el puerto " + puerto);

            while (true) {
                // Esperar a que un cliente se conecte
                Socket cliente = socketServidor.accept();

                // Crear un hilo para manejar la comunicación con el cliente
                hiloClienteServidorCom hilo = new hiloClienteServidorCom(cliente);
                hilo.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}