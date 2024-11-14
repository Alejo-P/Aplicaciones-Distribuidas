import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interfaz extends Remote {
    
    // Metodos que el cliente puede llamar
    public String mensaje() throws RemoteException;

    // Oeraciones matematicas basicas
    public double suma(double a, double b) throws RemoteException;
    public double resta(double a, double b) throws RemoteException;
    public double multiplicacion(double a, double b) throws RemoteException;
    public double division(double a, double b) throws RemoteException;
} 
