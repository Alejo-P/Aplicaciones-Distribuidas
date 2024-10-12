package Ejemplo3;

public class principal {
    public static void main(String[] args) {
        hilo h = new hilo();
        h.start();
        h.imprimir("Hilo principal");
        
        try {
            h.join();
        } catch (InterruptedException e) {
            System.out.println("Error en la ejecución del hilo principal");
        }

        System.out.println("Fin del hilo principal");
    }

}
