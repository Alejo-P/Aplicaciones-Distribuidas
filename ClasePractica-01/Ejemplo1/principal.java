package Ejemplo1;
public class principal {
    public static void main(String[] args) {

        // Instancia de los hilos
        hilo1 h = new hilo1();
        hilo2 h2 = new hilo2();

        // Inicio de los hilos
        h.start();
        h2.start();

        // Mensaje del hilo principal
        for (int i = 1; i <= 5; i++) {
            System.out.println("Hilo P " + i);
        }
    }
}