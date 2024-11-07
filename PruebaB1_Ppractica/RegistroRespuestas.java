import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistroRespuestas {
    private String nombreArchivo;

    public RegistroRespuestas(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void guardarRespuesta(int numeroMensaje, String respuestaCliente, InetAddress direccionIP, String pregunta) {
        try (FileWriter archivo = new FileWriter(nombreArchivo, true);
             PrintWriter escritor = new PrintWriter(archivo)) {
             
            String fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            escritor.println("Mensaje " + numeroMensaje + ":");
            escritor.println("Fecha y Hora: " + fechaHora);
            escritor.println("IP Origen: " + direccionIP.getHostAddress());
            escritor.println("Pregunta: " + pregunta + " Respuesta: " + respuestaCliente);
            escritor.println("---------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
