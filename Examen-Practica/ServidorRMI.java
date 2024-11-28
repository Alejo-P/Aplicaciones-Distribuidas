import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorRMI {
    public static void main(String[] args) {
        try {
            // Crea un objeto remoto
            BMIRemotoImpl obj = new BMIRemotoImpl();
            
            // Registra el objeto remoto en el registro RMI
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("BMIRemoto", obj);
            
            System.out.println("Servidor RMI listo");
        } catch (Exception e) {
            System.out.println("Servidor RMI falló: " + e.getMessage());
        }
    }
}
