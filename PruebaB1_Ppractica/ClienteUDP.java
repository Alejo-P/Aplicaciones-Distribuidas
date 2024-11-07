import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.util.Scanner;

public class ClienteUDP{
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            DatagramSocket socket = new DatagramSocket();
            System.out.println("Cliente conectado al servidor");

            
            // Dirección IP y puerto del servidor
            // InetAddress direccionIP = InetAddress.getByName("localhost");
            InetAddress direccionIP = InetAddress.getByName("localhost");
            int puertoServidor = 3000; // Asegúrate de que sea el mismo puerto que usa el servidor
            
            // Enviar confirmacion al servidor
            byte[] bufferConectado = "Conexión establecida.".getBytes();
            DatagramPacket datagramaEstado = new DatagramPacket(bufferConectado, bufferConectado.length, direccionIP, puertoServidor);
            socket.send(datagramaEstado);
            
            while (true) {
                byte[] bufferEntrada = new byte[1024];
                DatagramPacket datagramaEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                socket.receive(datagramaEntrada);

                String mensajeServidor = new String(datagramaEntrada.getData(), 0, datagramaEntrada.getLength());
                
                System.out.println(mensajeServidor);
                String[] fragmento = mensajeServidor.split(",", 2);
                String tipoMensaje = fragmento[0];
                String mensaje = fragmento[1];

                if (tipoMensaje.equalsIgnoreCase("Estado")){
                    System.out.println(ConsoleStyles.FG_GREEN + "Estado del servidor:"  + ConsoleStyles.RESET + " " + mensaje);
                    if (mensaje.equalsIgnoreCase("Conexión cerrada.")) {
                        break;
                    }
                } else if (tipoMensaje.equalsIgnoreCase("Pregunta")){
                    System.out.println("*---------------------------------*");
                    System.out.println(ConsoleStyles.FG_BLUE + "Pregunta del servidor:" + ConsoleStyles.RESET + " " + mensaje);
                    System.out.println("Escribe " + ConsoleStyles.FG_YELLOW + ConsoleStyles.BOLD + "'exit'" + ConsoleStyles.RESET + " para salir.");
                    System.out.print(ConsoleStyles.FG_YELLOW + ConsoleStyles.BOLD  + "Escribe tu respuesta: " + ConsoleStyles.RESET);
                    String respuestaCliente = scanner.nextLine();
                    byte[] bufferSalida = respuestaCliente.getBytes();
                    DatagramPacket datagramaSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionIP, puertoServidor);
                    socket.send(datagramaSalida);
                    System.out.println("*---------------------------------*");
                } else if (tipoMensaje.equalsIgnoreCase("Respuesta")){
                    System.out.println(ConsoleStyles.FG_CYAN + "Respuesta del servidor:" + ConsoleStyles.RESET + " " + mensaje);
                } else {
                    System.out.println(ConsoleStyles.FG_RED + "Mensaje desconocido del servidor:" + ConsoleStyles.RESET + " " + mensaje);
                }
            }

            // Cerrar el socket
            socket.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}