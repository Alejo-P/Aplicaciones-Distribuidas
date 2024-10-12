package Ejemplo1;
public class hilo1 extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Hilo A " + i);
        }
    }
}