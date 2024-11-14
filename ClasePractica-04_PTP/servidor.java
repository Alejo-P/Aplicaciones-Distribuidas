import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class servidor {
    public static void main(String[] args) {
        // Crear el objeto remoto
        try {
            interfaz objetoREmoto = new implementacion();
            // Crear  y obtener registro RMI en un puerto especifico
            Registry registro = LocateRegistry.createRegistry(1099);
            // Registrar el objeto remoto en el registro (nombre, objeto)
            registro.rebind("Clienteremoto", objetoREmoto);
            
        } catch (Exception e) { // Capturar cualquier excepcion
            e.printStackTrace();
        }

    }
}
