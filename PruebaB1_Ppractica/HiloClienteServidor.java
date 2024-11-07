import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class HiloClienteServidor extends Thread {
    private DatagramSocket socket;
    private DatagramPacket datagramaEntrada;
    private int puntos = 0;
    private List<String> preguntasAleatorias;
    
    // Diccionario de preguntas y respuestas
    private HashMap<String, String> preguntasRespuestas = new HashMap<String, String>() {{
        put("¿Cuál es la capital de Ecuador?", "Quito");
        put("¿Cuál es el río más largo del mundo?", "Amazonas");
        put("¿Cuál es el océano más grande del mundo?", "Pacífico");
        put("¿Proceso que realizan las plantas para obtener energía?", "Fotosíntesis");
        put("¿Cuál es el planeta más grande del sistema solar?", "Júpiter");
        put("¿Cuál es el país más grande del mundo?", "Rusia");
        put("¿Cuál es el animal más grande del mundo?", "Ballena Azul");
    }};

    public HiloClienteServidor(DatagramSocket socket, DatagramPacket datagramaEntrada) {
        this.socket = socket;
        this.datagramaEntrada = datagramaEntrada;
        this.preguntasAleatorias = new ArrayList<>(preguntasRespuestas.keySet());
        Collections.shuffle(this.preguntasAleatorias);
    }

    public void run() {
        try {
            InetAddress direccionIP = datagramaEntrada.getAddress();
            int puerto = datagramaEntrada.getPort();

            System.out.println(ConsoleStyles.FG_YELLOW + ConsoleStyles.BOLD + "Direccion IP del cliente:" + ConsoleStyles.RESET + " " + direccionIP);
            System.out.println(ConsoleStyles.FG_YELLOW + ConsoleStyles.BOLD + "Puerto del cliente:" + ConsoleStyles.RESET + " " + puerto);

            // Enviar mensaje inicial al cliente
            enviarMensaje("Estado, Conexión establecida.", direccionIP, puerto);
            
            // Iterar sobre las preguntas
            for (String pregunta : preguntasAleatorias) {
                System.out.println(ConsoleStyles.FG_BLUE + "Pregunta:" + ConsoleStyles.RESET + " " + pregunta);
                enviarMensaje("Pregunta, " + pregunta, direccionIP, puerto);

                // Recibir respuesta del cliente
                byte[] bufferEntrada = new byte[1024];
                DatagramPacket datagramaRespuesta = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                socket.receive(datagramaRespuesta);
                String respuestaCliente = new String(datagramaRespuesta.getData(), 0, datagramaRespuesta.getLength()).trim();

                if (respuestaCliente.equalsIgnoreCase("exit")) {
                    System.out.println(ConsoleStyles.FG_RED + "Conexión cerrada por el cliente." + ConsoleStyles.RESET);
                    enviarMensaje("Estado, Conexión cerrada por el cliente.", direccionIP, puerto);
                    break;
                }

                // Comprobar si la respuesta es correcta
                String respuestaCorrecta = preguntasRespuestas.get(pregunta);
                if (respuestaCliente.equalsIgnoreCase(respuestaCorrecta)) {
                    System.out.println(ConsoleStyles.FG_GREEN + "Respuesta correcta." + ConsoleStyles.RESET);
                    enviarMensaje("Respuesta, Correcto.", direccionIP, puerto);
                    puntos+= 4;
                } else {
                    System.out.println(ConsoleStyles.FG_RED + "Respuesta incorrecta." + ConsoleStyles.RESET);
                    enviarMensaje("Respuesta, Incorrecto. La respuesta correcta es: " + respuestaCorrecta, direccionIP, puerto);
                }

                Thread.sleep(3000); // Pausar 3 segundos entre preguntas
            }

            // Enviar puntuación final y despedida
            enviarMensaje("Respuesta, Has obtenido " + puntos + "/" + preguntasRespuestas.size() + " puntos.", direccionIP, puerto);
            enviarMensaje("Respuesta, Gracias por participar. ¡Adiós!", direccionIP, puerto);
            enviarMensaje("Estado, Conexión cerrada.", direccionIP, puerto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enviarMensaje(String mensaje, InetAddress direccionIP, int puerto) {
        try {
            byte[] bufferSalida = mensaje.getBytes();
            DatagramPacket datagramaSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionIP, puerto);
            socket.send(datagramaSalida);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
