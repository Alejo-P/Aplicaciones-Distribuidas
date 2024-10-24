import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class hiloClienteServidorCom extends Thread {
    private Socket cliente;

    public hiloClienteServidorCom(Socket cliente) {
        this.cliente = cliente;
    }
    // Sobreescribir el método run
    public void run() {
        try (BufferedReader bufferEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter bufferSalida = new PrintWriter(cliente.getOutputStream(), true)) {

            // Mostrar la dirección IP y puerto del cliente
            System.out.println("Cliente conectado: " + cliente.getInetAddress() + ":" + cliente.getPort());
            bufferSalida.println("Conexión establecida con el servidor.");

            // Ciclo para recibir mensajes del cliente y enviar respuestas
            while (true) {
                // Leer el mensaje del cliente
                String mensajeRecibido = bufferEntrada.readLine();
                if (mensajeRecibido == null) {
                    System.out.println("El cliente ha cerrado la conexión.");
                    break;
                }

                System.out.println("Mensaje recibido del cliente: " + mensajeRecibido);
                
                // Procesar el mensaje recibido y enviar una respuesta
                String respuesta = "Mensaje recibido: " + mensajeRecibido; // Aquí puedes personalizar la respuesta.
                bufferSalida.println(respuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}    