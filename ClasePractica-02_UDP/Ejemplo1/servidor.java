package Ejemplo1;

import java.net.DatagramSocket;
import java.net.DatagramPacket;

public class servidor {
    public static int puerto = 3000;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            while (true) {
                byte[] bufferEntrada = new byte[1024]; // 1 KB para recibir datos
                DatagramPacket datagramaEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);

                // Recibir los datos del cliente
                socket.receive(datagramaEntrada);

                // Crear un hilo para manejar la respuesta al cliente
                hiloServidor hilo = new hiloServidor(socket, datagramaEntrada);
                hilo.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
