import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class hiloClienteServidor extends Thread {
    // Atributo para el cliente
    private Socket cliente;
    private String[] listaPreguntas = {
        "¿Cuál es tu nombre?",
        "¿Cuál es tu edad?",
        "¿Cuál es tu color favorito?",
        "¿Cuál es tu comida favorita?",
        "¿Cuál es tu película favorita?"
    };

    public hiloClienteServidor(Socket cliente) {
        this.cliente = cliente;
    }

    // Sobreescribir el método run
    public void run() {
        try (
            BufferedReader bufferEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter bufferSalida = new PrintWriter(cliente.getOutputStream(), true)
        ) {

            // Mostrar la dirección IP y puerto del cliente
            InetAddress direccionIP = cliente.getInetAddress();
            int puerto = cliente.getPort();

            System.out.println("*---------------------------------*");
            System.out.println("Direccion IP del cliente: " + direccionIP);
            System.out.println("Puerto del cliente: " + puerto);

            // Enviar un mensaje inicial al cliente
            bufferSalida.println("Conexión establecida con el servidor.");

            // Bucle para enviar preguntas al cliente y recibir respuestas
            for (String pregunta : listaPreguntas) {
                bufferSalida.println(pregunta);  // Enviar pregunta al cliente
                String respuestaCliente = bufferEntrada.readLine();  // Leer la respuesta del cliente

                // Verificar si el cliente envía 'exit' para cerrar la conexión
                if (respuestaCliente.equalsIgnoreCase("exit")) {
                    System.out.println("El cliente ha cerrado la conexión.");
                    break;
                }

                System.out.println("Respuesta del cliente: " + respuestaCliente);
            }

            // Cerrar la conexión después de las preguntas
            bufferSalida.println("Gracias por participar. ¡Adiós!");
            System.out.println("Cerrando conexión con el cliente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
