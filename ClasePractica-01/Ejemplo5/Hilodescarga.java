package Ejemplo5;
public class Hilodescarga extends Thread {
    // Atributos
    private final String archivo;
    private static final Object lock = new Object();

    // Constructor
    public Hilodescarga(String archivo) {
        this.archivo = archivo;
    }

    public void run() {
        descargar();
    }

    public void descargar() {
        System.out.println("Descargando : " + archivo);
        try {
            synchronized (lock) { // Sincronización de hilos
                lock.wait(100); // Simulación de descarga
            }
        } catch (InterruptedException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
        System.out.println("Archivo descargado: " + archivo);
    }
}
