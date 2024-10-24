package Ejemplo1;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.util.Scanner;

public class cliente {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String seguir;
            DatagramSocket socket = new DatagramSocket();
            System.out.println("Cliente conectado al servidor");
            
            // Dirección IP y puerto del servidor
            // InetAddress direccionIP = InetAddress.getByName("localhost");
            InetAddress direccionIP = InetAddress.getByName("172.29.39.157");
            int puertoServidor = 5000; // Asegúrate de que sea el mismo puerto que usa el servidor
            
            do {
                System.out.print("Escribe un mensaje para el servidor\n> ");
                // Enviar mensaje al servidor
                String mensajeEnvio = scanner.nextLine();
                byte[] bufferSalida = mensajeEnvio.getBytes();
                DatagramPacket datagramaSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionIP, puertoServidor);
                socket.send(datagramaSalida); // Enviar el datagrama

                // Preparar para recibir la respuesta del servidor
                byte[] bufferEntrada = new byte[1024];
                DatagramPacket datagramaEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                socket.receive(datagramaEntrada); // Recibir la respuesta

                // Mostrar los datos recibidos
                String mensajeRecibido = new String(datagramaEntrada.getData(), 0, datagramaEntrada.getLength());
                System.out.println("Datos recibidos del servidor: " + mensajeRecibido);
                System.out.println("Quiere enviar otro mensaje? (s/n)");
                seguir = scanner.nextLine().toLowerCase();
            } while (seguir.equals("s"));

            // Cerrar el socket
            socket.close();
            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
