import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class servidorTCP {
    public static int puerto = 3000;
    public static AtomicBoolean running = new AtomicBoolean(true); // Controla si el servidor sigue activo

    public static void main(String[] args) {
        try (ServerSocket socketServidor = new ServerSocket(puerto)) {
            System.out.println(ConsoleStyles.FG_GREEN + "Servidor TCP iniciado en el puerto " + puerto + ConsoleStyles.RESET);

            while (running.get()) {
                // Esperar a que un cliente se conecte
                Socket cliente = socketServidor.accept();

                // Crear un hilo para manejar la respuesta al cliente
                hiloClienteServidor hilo = new hiloClienteServidor(cliente);
                hilo.setOnClose(() -> {
                    System.out.println(ConsoleStyles.FG_RED + "El cliente ha cerrado la conexión." + ConsoleStyles.RESET);
                    running.set(false); // Cambiar el estado del servidor para que salga del bucle
                });
                hilo.start();
            }
            System.out.println(ConsoleStyles.FG_RED + ConsoleStyles.BOLD + "Servidor TCP cerrado." + ConsoleStyles.RESET);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
