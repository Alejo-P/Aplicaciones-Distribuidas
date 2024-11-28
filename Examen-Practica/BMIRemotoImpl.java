import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class BMIRemotoImpl extends UnicastRemoteObject implements BMIRemoto {
    
    public BMIRemotoImpl() throws RemoteException {
        super();
    }
    
    public double calculaIMC(double peso, double altura) throws RemoteException {
        return peso / (altura * altura);
    }
    
}
