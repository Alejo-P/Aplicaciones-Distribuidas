package Ejemplo6;

public class Principal {
    public static void main(String[] args) {
        int[] Clientes = { 1, 2, 3, 4, 5, 6 };
        int[] Turnos ={101, 102, 103, 104, 105, 106};

        for (int i = 0; i < Clientes.length; i++) {
            HiloTurnos h = new HiloTurnos(Turnos[i], Clientes[i]);
            h.start();
        }
    }
}
