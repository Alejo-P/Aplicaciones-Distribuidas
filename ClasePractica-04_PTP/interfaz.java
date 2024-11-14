import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interfaz extends Remote {
    // Metodos que el cliente puede llamar

    public String mensaje() throws RemoteException;
    public double operacion(double a, double b) throws RemoteException;
    
} 
