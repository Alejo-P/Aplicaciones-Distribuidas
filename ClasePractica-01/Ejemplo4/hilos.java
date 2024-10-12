package Ejemplo4;
public class hilos extends Thread{
    private final int id;
    private static final Object lock = new Object();

    // Constructor
    public hilos(int id){
        this.id = id;
    }

    // Metodo run
    public void run(){
        imprimir(5, "hilo " + id);
    }

    // Metodo imprimir
    public void imprimir(int n, String nombre) {
        synchronized (lock) { // Bloqueo de la sección crítica
            System.out.println("----------" +"Inicio de hilo: " + nombre.toUpperCase() + "----------");
            for (int i = 1; i <= n; i++) {
                System.out.println("Hilo: " + nombre.toUpperCase() + ": "+ i);
            }
            System.out.println("----------" + "Fin de hilo: " + nombre.toUpperCase() + "----------");
        }
    }
}
