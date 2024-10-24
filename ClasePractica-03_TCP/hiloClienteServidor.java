import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.HashMap;

public class hiloClienteServidor extends Thread {
    // Atributo para el cliente
    private Socket cliente;

    // Diccionario de preguntas con sus respuestas
    private HashMap<String, String> preguntasRespuestas = new HashMap<String, String>() {
        {
            put("¿Cuál es la capital de Ecuador?", "Quito");
            put("¿Cuál es el río más largo del mundo?", "Amazonas");
            put("¿Cuál es el océano más grande del mundo?", "Pacifico");
            put("¿Proceso que realizan las plantas para obtener energía?", "Fotosintesis");
            put("¿Cuál es el planeta más grande del sistema solar?", "Jupiter");
            put("¿Cuál es el país más grande del mundo?", "Rusia");
        }
    };

    public hiloClienteServidor(Socket cliente) {
        this.cliente = cliente;
    }

    // Sobreescribir el método run
    public void run() {
        try (
            BufferedReader bufferEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter bufferSalida = new PrintWriter(cliente.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in)
        ) {

            // Mostrar la dirección IP y puerto del cliente
            InetAddress direccionIP = cliente.getInetAddress();
            int puerto = cliente.getPort();

            System.out.println("*---------------------------------*");
            System.out.println("Direccion IP del cliente: " + direccionIP);
            System.out.println("Puerto del cliente: " + puerto);

            // Enviar un mensaje inicial al cliente
            bufferSalida.println("Conexión establecida con el servidor.");

            // Enviar mensajes al cliente
            String mensajeEnviar = scanner.nextLine();
            bufferSalida.println(mensajeEnviar);

            // Iterar sobre las preguntas del HashMap
            for (String pregunta : preguntasRespuestas.keySet()) {
                bufferSalida.println(pregunta);  // Enviar pregunta al cliente
                String respuestaCliente = bufferEntrada.readLine();  // Leer la respuesta del cliente

                // Verificar si el cliente envía 'exit' para cerrar la conexión
                if (respuestaCliente.equalsIgnoreCase("exit")) {
                    System.out.println("El cliente ha cerrado la conexión.");
                    break;
                }

                // Obtener la respuesta correcta del HashMap
                String respuestaCorrecta = preguntasRespuestas.get(pregunta);

                // Validar la respuesta
                if (respuestaCliente.equalsIgnoreCase(respuestaCorrecta)) {
                    bufferSalida.println("Correcto.");
                    System.out.println("Respuesta correcta del cliente: " + respuestaCliente);
                } else {
                    bufferSalida.println("Incorrecto. La respuesta correcta es: " + respuestaCorrecta);
                    System.out.println("Respuesta incorrecta del cliente: " + respuestaCliente);
                }
                Thread.sleep(3000);  // Esperar 1 segundo antes de enviar la siguiente pregunta
            }

            // Cerrar la conexión después de las preguntas
            bufferSalida.println("Gracias por participar. ¡Adiós!");
            System.out.println("Cerrando conexión con el cliente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
