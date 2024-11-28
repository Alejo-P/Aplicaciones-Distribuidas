import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BMIRemoto extends Remote {
    
    // Metodos remotos para ña calculadora de IMC
    public double calculaIMC(double peso, double altura) throws RemoteException;
    
    // Metodo remoto para la categoria del IMC
    public String categoriaIMC(double imc) throws RemoteException;
}