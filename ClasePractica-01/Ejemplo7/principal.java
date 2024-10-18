package Ejemplo7;

public class principal {
    public static void main(String[] args) {
        hilo1 h1 = new hilo1();
        hilo2 h2 = new hilo2();
        
        // Instanciar los objetos de la clase thread
        Thread t1 = new Thread(h1);
        Thread t2 = new Thread(h2);

        
        // Iniciar los hilos
        t1.start();
        t2.start();
        
        // Imprimir numeros
        imprimir();
    }

    public static void imprimir() {
        for (int i = 0; i < 10; i++) {
            System.out.print("P"+i + " ");
        }
    }
}