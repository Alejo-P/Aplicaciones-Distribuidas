package Ejemplo2;

public class hiloA extends Thread {
    public void imprimir(int valor) {
        for (int i = 1; i <= valor; i++) {
            System.out.println("Hilo A " + i);
        }
    }
    
    public void run() {
        imprimir(5);
    }
}
