package Ejemplo1;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.util.Scanner;

public class hiloServidor extends Thread {
    private DatagramSocket socket;
    private DatagramPacket datagramaEntrada;

    public hiloServidor(DatagramSocket socket, DatagramPacket datagramaEntrada) {
        this.socket = socket;
        this.datagramaEntrada = datagramaEntrada;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        // Mostrar la dirección IP y puerto del cliente
        InetAddress direccionIP = datagramaEntrada.getAddress();
        int puerto = datagramaEntrada.getPort();

        System.out.println("*---------------------------------*");
        System.out.println("Direccion IP del cliente: " + direccionIP);
        System.out.println("Puerto del cliente: " + puerto);

        // Mostrar los datos recibidos
        String mensajeRecibido = new String(datagramaEntrada.getData(), 0, datagramaEntrada.getLength());
        System.out.println("Datos recibidos: " + mensajeRecibido);

        // Enviar una respuesta al cliente
        System.out.println("Escribe un mensaje para el cliente\n> ");
        String mensajeRespuesta = scanner.nextLine();
        byte[] bufferSalida = mensajeRespuesta.getBytes();
        System.out.println("*---------------------------------*");

        try {
            DatagramPacket datagramaSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionIP, puerto);
            socket.send(datagramaSalida); // Enviar respuesta
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
