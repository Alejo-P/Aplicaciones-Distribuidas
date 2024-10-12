package Ejemplo2;

public class principal {
    public static void imprimir(int valor) {
        for (int i = 1; i <= valor; i++) {
            System.out.println("Hilo Principal " + i);
        }
    }

    public static void main(String[] args) {


        // instancia del hilo
        hiloA h = new hiloA();

        // Inicio del hilo
        h.start();

        // Mensaje del hilo principal
        imprimir(5);

        // Esperar a que el hilo termine
        try {
            h.join(); // Espera a que el hilo termine
        } catch (InterruptedException e) {
            // Captura la excepción si el hilo es interrumpido
            System.out.println("Hilo principal interrumpido");
        }
        
        System.out.println("Todos los hilos han terminado");
    }
}
