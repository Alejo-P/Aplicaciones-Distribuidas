import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClienteTCP_GUI extends JFrame {
    private JTextArea textArea;
    private JTextField textField;
    private PrintWriter bufferSalida;
    private BufferedReader bufferEntrada;

    public ClienteTCP_GUI() {
        setTitle("Cliente TCP - Chat");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        textField = new JTextField();
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });
        add(textField, BorderLayout.SOUTH);

        iniciarConexion();
    }

    private void iniciarConexion() {
        try {
            Socket cliente = new Socket("localhost", 3000);
            bufferEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            bufferSalida = new PrintWriter(cliente.getOutputStream(), true);

            InetAddress direccionIP = cliente.getInetAddress();
            int puerto = cliente.getPort();
            mostrarMensaje("Conectado al servidor en IP: " + direccionIP + ", Puerto: " + puerto);

            // Hilo para escuchar mensajes del servidor
            new Thread(() -> {
                try {
                    String mensajeServidor;
                    while ((mensajeServidor = bufferEntrada.readLine()) != null) {
                        procesarMensajeServidor(mensajeServidor);
                    }
                } catch (Exception e) {
                    mostrarMensaje("Conexión cerrada por el servidor.");
                }
            }).start();

        } catch (Exception e) {
            mostrarMensaje("Error de conexión: " + e.getMessage());
        }
    }

    private void procesarMensajeServidor(String mensajeServidor) {
        String[] partes = mensajeServidor.split(",", 2);
        String tipoMensaje = partes[0].trim();
        String contenido = partes[1].trim();

        switch (tipoMensaje) {
            case "Estado":
                mostrarMensaje("Estado del servidor: " + contenido);
                if (contenido.equalsIgnoreCase("Conexión cerrada.")) {
                    textField.setEditable(false);
                }
                break;
            case "Pregunta":
                mostrarMensaje("Pregunta del servidor: " + contenido);
                mostrarMensaje("Escribe 'exit' para salir.");
                break;
            case "Respuesta":
                mostrarMensaje("Respuesta del servidor: " + contenido);
                break;
            default:
                mostrarMensaje("Mensaje del servidor: " + contenido);
                break;
        }
    }

    private void enviarMensaje() {
        String mensaje = textField.getText().trim();
        if (!mensaje.isEmpty()) {
            bufferSalida.println(mensaje);
            textField.setText("");
            if (mensaje.equalsIgnoreCase("exit")) {
                textField.setEditable(false);
            }
        }
    }

    private void mostrarMensaje(String mensaje) {
        SwingUtilities.invokeLater(() -> textArea.append(mensaje + "\n"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClienteTCP_GUI clienteGUI = new ClienteTCP_GUI();
            clienteGUI.setVisible(true);
        });
    }
}
