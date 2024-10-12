package Ejemplo6;
public class HiloTurnos extends Thread {
    private final int turno;
    private final int cliente;

    // Constructor
    public HiloTurnos(int turno, int cliente) {
        this.turno = turno;
        this.cliente = cliente;
    }

    // Método run
    public void run() {
       atender();
    }

    // Método para atender
    public void atender() {
        System.out.println("Turno " + turno + " asignado");
        try {
            Thread.sleep(100); // Simulación de atención
        } catch (InterruptedException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
        System.out.println("Cliente " + cliente + " recibio el turno " + turno);
    }
}
