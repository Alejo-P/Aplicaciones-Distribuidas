import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ServidorUDP {
    public static int puerto = 3000;
    public static int puntos = 0;
    private static List<String> preguntasAleatorias;
    private static HashMap<String, String> preguntasRespuestas = new HashMap<String, String>() {{
        put("¿Cuál es la capital de Ecuador?", "Quito");
        put("¿Cuál es el río más largo del mundo?", "Amazonas");
        put("¿Cuál es el océano más grande del mundo?", "Pacifico");
        put("¿Proceso que realizan las plantas para obtener energía?", "Fotosintesis");
        put("¿Cuál es el planeta más grande del sistema solar?", "Jupiter");
        put("¿Cuál es el país más grande del mundo?", "Rusia");
        put("¿Cuál es el animal más grande del mundo?", "Ballena Azul");
    }};
    
    private static RegistroRespuestas registro = new RegistroRespuestas("registro_respuestas.txt");

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            byte[] bufferEntrada = new byte[1024];
            DatagramPacket datagramaEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
            System.out.println("Servidor escuchando en el puerto: " + puerto);

            // Esperar a que el cliente se conecte
            socket.receive(datagramaEntrada);
            InetAddress direccionIP = datagramaEntrada.getAddress();
            int puertoCliente = datagramaEntrada.getPort();

            preguntasAleatorias = new ArrayList<>(preguntasRespuestas.keySet());
            Collections.shuffle(preguntasAleatorias);

            int numeroMensaje = 1; 

            for (String pregunta : preguntasAleatorias) {
                String respuestaCorrecta = preguntasRespuestas.get(pregunta);

                enviarMensaje(socket, "Pregunta, " + pregunta, direccionIP, puertoCliente);

                // Recibir la respuesta del cliente
                socket.receive(datagramaEntrada);
                String respuestaCliente = new String(datagramaEntrada.getData(), 0, datagramaEntrada.getLength()).trim();

                // Guardar la respuesta en el archivo
                registro.guardarRespuesta(numeroMensaje++, respuestaCliente, direccionIP, pregunta);

                if (respuestaCliente.equalsIgnoreCase("exit")) {
                    enviarMensaje(socket, "Estado, Conexión cerrada por el cliente.", direccionIP, puertoCliente);
                    break;
                }

                if (respuestaCliente.equalsIgnoreCase(respuestaCorrecta)) {
                    enviarMensaje(socket, "Respuesta, Correcto.", direccionIP, puertoCliente);
                    puntos += 4;
                } else {
                    enviarMensaje(socket, "Respuesta, Incorrecto. La respuesta correcta es: " + respuestaCorrecta, direccionIP, puertoCliente);
                }
            }

            enviarMensaje(socket, "Respuesta, Has obtenido " + puntos + "/" + (preguntasRespuestas.size() * 4) + " puntos.", direccionIP, puertoCliente);
            enviarMensaje(socket, "Estado, Conexión cerrada.", direccionIP, puertoCliente);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void enviarMensaje(DatagramSocket socket, String mensaje, InetAddress direccionIP, int puerto) {
        try {
            byte[] bufferSalida = mensaje.getBytes();
            DatagramPacket datagramaSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionIP, puerto);
            socket.send(datagramaSalida);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
