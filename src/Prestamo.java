import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prestamo {

    private String idPrestamo;
    private String libro;
    private String idUsuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaLimite;

    public Prestamo(LocalDate fechaLimite, LocalDate fechaPrestamo, String idPrestamo, String idUsuario, String libro) {
        this.fechaLimite = fechaLimite;
        this.fechaPrestamo = fechaPrestamo;
        this.idPrestamo = idPrestamo;
        this.idUsuario = idUsuario;
        this.libro = libro;
    }
    public LocalDate getFechaLimite() {
        return fechaLimite;
    }
    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }
    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }
    public String getIdPrestamo() {
        return idPrestamo;
    }
    public void setIdPrestamo(String idPrestamo) {
        this.idPrestamo = idPrestamo;
    }
    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getLibro() {
        return libro;
    }
    public void setLibro(String libro) {
        this.libro = libro;
    }

    public double calcularMulta(){
        double valorMultaPorDia = 0.90;
        LocalDate fechaActual = LocalDate.now();
        if (fechaActual.isAfter(fechaLimite)) {
            long diasRetraso = ChronoUnit.DAYS.between(fechaLimite, fechaActual);
            return diasRetraso * valorMultaPorDia;
        }
        return 0;
    }
}