public class Prestamo {

    String estudiante;
    String libro;

    public Prestamo(String estudiante, String libro) {
        this.estudiante = estudiante;
        this.libro = libro;
    }

    @Override
    public String toString() {
        return estudiante + " tiene prestado: " + libro;
    }
}