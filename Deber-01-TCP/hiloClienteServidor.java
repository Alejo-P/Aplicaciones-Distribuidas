import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class hiloClienteServidor extends Thread {
    private Socket cliente;
    private int puntos = 0;
    private Runnable onClose;
    private List<String> preguntasAleatorias;

    // Diccionario de preguntas y respuestas
    private HashMap<String, String> preguntasRespuestas = new HashMap<String, String>() {{
        put("¿Cuál es la capital de Ecuador?", "Quito");
        put("¿Cuál es el río más largo del mundo?", "Amazonas");
        put("¿Cuál es el océano más grande del mundo?", "Pacifico");
        put("¿Proceso que realizan las plantas para obtener energía?", "Fotosintesis");
        put("¿Cuál es el planeta más grande del sistema solar?", "Jupiter");
        put("¿Cuál es el país más grande del mundo?", "Rusia");
        put("¿Cuál es el animal más grande del mundo?", "Ballena Azul");
    }};

    public hiloClienteServidor(Socket cliente) {
        this.cliente = cliente;
        this.preguntasAleatorias = new ArrayList<>(preguntasRespuestas.keySet());
        Collections.shuffle(this.preguntasAleatorias);
    }

    public void run() {
        try (
            BufferedReader bufferEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter bufferSalida = new PrintWriter(cliente.getOutputStream(), true)
        ) {
            InetAddress direccionIP = cliente.getInetAddress();
            int puerto = cliente.getPort();
            System.out.println(ConsoleStyles.FG_YELLOW + ConsoleStyles.BOLD + "Direccion IP del cliente:" + ConsoleStyles.RESET + " " + direccionIP);
            System.out.println(ConsoleStyles.FG_YELLOW + ConsoleStyles.BOLD + "Puerto del cliente:" + ConsoleStyles.RESET + " " + puerto);

            // Enviar un mensaje inicial al cliente
            bufferSalida.println("Estado, Conexión establecida con el servidor.");

            // Iterar sobre las preguntas
            for (String pregunta : preguntasAleatorias) {
                bufferSalida.println("Pregunta, " + pregunta);
                System.out.println(ConsoleStyles.FG_BLUE + "Pregunta enviada al cliente:" + ConsoleStyles.RESET + " " + pregunta);

                String respuestaCliente = bufferEntrada.readLine();

                if (respuestaCliente == null || respuestaCliente.equalsIgnoreCase("exit")) {
                    System.out.println(ConsoleStyles.FG_RED + "El cliente ha cerrado la conexión." + ConsoleStyles.RESET);
                    break;
                }

                // Verificar si la respuesta del cliente es correcta y enviar la respuesta al cliente
                String respuestaCorrecta = preguntasRespuestas.get(pregunta);
                if (respuestaCliente.equalsIgnoreCase(respuestaCorrecta)) {
                    System.out.println(ConsoleStyles.FG_GREEN + "Respuesta correcta del cliente:" + ConsoleStyles.RESET + " " + respuestaCliente);
                    bufferSalida.println("Respuesta, Correcto.");
                    puntos++;
                } else {
                    System.out.println(ConsoleStyles.FG_RED + "Respuesta incorrecta del cliente:" + ConsoleStyles.RESET + " " + respuestaCliente);
                    bufferSalida.println("Respuesta, Incorrecto. La respuesta correcta es: " + respuestaCorrecta);
                }

                // Esperar 3 segundos antes de enviar la siguiente pregunta
                Thread.sleep(3000);
            }

            bufferSalida.println("Respuesta, Has obtenido " + puntos + "/" + preguntasRespuestas.size() + " puntos.");
            bufferSalida.println("Respuesta, Gracias por participar. ¡Adiós!");
            bufferSalida.println("Estado, Conexión cerrada.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cliente.close();
                if (onClose != null) {
                    onClose.run();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setOnClose(Runnable onClose) {
        this.onClose = onClose;
    }
}
