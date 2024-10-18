package Ejemplo7;

public class hilo1 implements Runnable {
    // Imprimir letras minusculas
    public void run() {
        for (char i = 'a'; i <= 'z'; i++) {
            System.out.print(i + " ");
        }
    }
}