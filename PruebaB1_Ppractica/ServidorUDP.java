import java.net.DatagramSocket;
import java.util.concurrent.atomic.AtomicBoolean;
import java.net.DatagramPacket;

public class ServidorUDP {
    public static int puerto = 3000;
    public static AtomicBoolean ejecutar = new AtomicBoolean(true);

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            System.out.println(ConsoleStyles.FG_GREEN + "Servidor escuchando en el puerto:" + ConsoleStyles.RESET + puerto);

            while (ejecutar.get()) {
                byte[] bufferEntrada = new byte[1024]; // 1 KB para recibir datos
                DatagramPacket datagramaEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);

                // Recibir datos del cliente
                socket.receive(datagramaEntrada);

                // Crear un hilo para manejar la respuesta al cliente
                HiloClienteServidor hilo = new HiloClienteServidor(socket, datagramaEntrada);
                hilo.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
