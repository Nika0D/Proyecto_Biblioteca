public class Reserva {

    String nombreUsuario;
    String tituloLibro;

    public Reserva(String nombreUsuario, String tituloLibro) {
        this.nombreUsuario = nombreUsuario;
        this.tituloLibro = tituloLibro;
    }

    @Override
    public String toString() {
        return nombreUsuario + " reservó: " + tituloLibro;
    }
}
