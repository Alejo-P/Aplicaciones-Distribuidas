import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            DatagramSocket socket = new DatagramSocket();
            System.out.println("Cliente conectado al servidor");

            InetAddress direccionIP = InetAddress.getByName("localhost");
            int puertoServidor = 3000;

            // Enviar confirmación inicial al servidor
            byte[] bufferConectado = "Conexión establecida.".getBytes();
            DatagramPacket datagramaInicial = new DatagramPacket(bufferConectado, bufferConectado.length, direccionIP, puertoServidor);
            socket.send(datagramaInicial);

            while (true) {
                byte[] bufferEntrada = new byte[1024];
                DatagramPacket datagramaEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);

                // Recibir datos del servidor
                socket.receive(datagramaEntrada);

                String mensajeServidor = new String(datagramaEntrada.getData(), 0, datagramaEntrada.getLength());
                
                System.out.println(mensajeServidor);
                String[] fragmento = mensajeServidor.split(",", 2);
                String tipoMensaje = fragmento[0];
                String mensaje = fragmento[1];

                if (tipoMensaje.equalsIgnoreCase("Estado")) {
                    System.out.println("Estado del servidor: " + mensaje);
                    if (mensaje.equalsIgnoreCase("Conexión cerrada.")) {
                        break;
                    }
                } else if (tipoMensaje.equalsIgnoreCase("Pregunta")) {
                    System.out.println("*---------------------------------*");
                    System.out.println("Pregunta del servidor: " + mensaje);
                    System.out.print("Escribe tu respuesta (o 'exit' para salir): ");
                    
                    String respuestaCliente = scanner.nextLine();
                    byte[] bufferSalida = respuestaCliente.getBytes();
                    DatagramPacket datagramaSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionIP, puertoServidor);
                    socket.send(datagramaSalida);
                    
                    System.out.println("*---------------------------------*");
                } else if (tipoMensaje.equalsIgnoreCase("Respuesta")) {
                    System.out.println("Respuesta del servidor: " + mensaje);
                } else {
                    System.out.println("Mensaje desconocido del servidor: " + mensaje);
                }
            }

            socket.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
