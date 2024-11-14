import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class principal {

    public static void main(String[] args) {
        try{
            // Obtener registro RMI en el puerto especifico (localhost, 1099) host, puerto del servidor
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);

            // Crear instancia de la implementacion de la interfaz
            interfaz objetoRemoto = (interfaz) registro.lookup("Clienteremoto");

            // Llamar a los metodos remotos
            String mensaje = objetoRemoto.mensaje();
            double operacion = objetoRemoto.operacion(10, 20);

            // Imprimir los resultados
            System.out.println(ConsoleStyles.FG_GREEN + "Mensaje: "+ ConsoleStyles.RESET + mensaje);
            System.out.println(ConsoleStyles.FG_GREEN + "Operacion: "+ ConsoleStyles.RESET + operacion);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}