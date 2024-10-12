package Ejemplo3;
public class hilo extends Thread {

    // Método sincronizado para imprimir los números
    public synchronized void imprimir(String nombre) {
        for (int i = 1; i <= 5; i++) {
            System.out.println(nombre + ": "+ i);
        }
    }

    public void run() {
        imprimir("Hilo secundario");
    }
    
}
