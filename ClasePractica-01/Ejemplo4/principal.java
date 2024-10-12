package Ejemplo4;

public class principal {
    public static void main(String[] args) {
        // Crear 11 hilos
        for (int i = 1; i <= 10; i++) {
            hilos h = new hilos(i);
            h.start();
        }
    }
}
