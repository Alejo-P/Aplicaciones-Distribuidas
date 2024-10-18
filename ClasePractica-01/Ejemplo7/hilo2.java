package Ejemplo7;

public class hilo2 implements Runnable {
    // Imprimir letras mayusculas
    public void run() {
        for (char i = 'A'; i <= 'Z'; i++) {
            System.out.print(i + " ");
        }
    }
}