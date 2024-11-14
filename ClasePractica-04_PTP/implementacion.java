import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

// Clase debe extender de UnicastRemoteObject para que pueda ser un objeto remoto
public class implementacion extends UnicastRemoteObject implements interfaz {
    
    // Constructor de la clase
    public implementacion() throws RemoteException {
        super();
    }
    
    // Implementar los metodos de la interfaz
    @Override
    public String mensaje() throws RemoteException {
        return "Mensaje soy marcelo el objeto remoto";
    }

    
    @Override
    public double suma(double a, double b) throws RemoteException {
        return a + b;
    }
    
    @Override
    public double resta(double a, double b) throws RemoteException {
        return a - b;
    }
    
    @Override
    public double multiplicacion(double a, double b) throws RemoteException {
        return a * b;
    }
    
    @Override
    public double division(double a, double b) throws RemoteException {
        if (b == 0) {
            return 0;
        }
        
        return a / b;
    }
}
