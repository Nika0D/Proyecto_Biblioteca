import java.util.LinkedList;
import java.util.Queue;

public class ColaReservas {

    Queue<Reserva> cola = new LinkedList<>();

    // Agregar reserva
    public void agregarReserva(Reserva reserva) {
        cola.offer(reserva);
        System.out.println("Reserva agregada.");
    }

    // Atender reserva
    public void atenderReserva() {

        if (cola.isEmpty()) {
            System.out.println("No hay reservas.");
            return;
        }

        Reserva reserva = cola.poll();

        System.out.println("Atendiendo reserva:");
        System.out.println(reserva);
    }

    // Mostrar reservas
    public void mostrarReservas() {

        if (cola.isEmpty()) {
            System.out.println("No hay reservas.");
            return;
        }

        for (Reserva r : cola) {
            System.out.println(r);
        }
    }
}