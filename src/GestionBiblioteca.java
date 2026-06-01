import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class GestionBiblioteca {

    private Map<String, Libro> mapaLibros;
    private List<Libro> listaLibros;
    private Map<String, Queue<String>> mapaReservas;

    public GestionBiblioteca() {
        this.mapaLibros = new HashMap<>();
        this.listaLibros = new ArrayList<>();
        this.mapaReservas = new HashMap<>();
    }

    // GESTIÓN DE LIBROS

    // Registrar un nuevo libro en ambas estructuras
    // Guardamos en el HashMap usando el código como clave para búsquedas rápidas
    // Guardamos en el ArrayList para cuando necesitemos hacer listados generales
    public void registrarLibro(Libro nuevoLibro) {
        mapaLibros.put(nuevoLibro.getCodigo(), nuevoLibro);
        listaLibros.add(nuevoLibro);
        System.out.println("Libro registrado con éxito: " + nuevoLibro.getTitulo());
    }

    // Eliminar un libro por su código

    public void eliminarLibro(String codigo) {
        if (mapaLibros.containsKey(codigo)) {
            Libro libroAEliminar = mapaLibros.get(codigo);
            mapaLibros.remove(codigo);
            listaLibros.remove(libroAEliminar); // Elimina de la lista analógica

            System.out.println("Libro eliminado: " + libroAEliminar.getTitulo());
        } else {
            System.out.println("Error: No se encontró ningún libro con el código " + codigo);
        }
    }

    // Búsqueda rápida (Usa el HashMap)
    public Libro buscarPorCodigo(String codigo) {
        return mapaLibros.get(codigo);
    }

    // Búsqueda por filtros
    public List<Libro> buscarPorCriterio(String texto, String tipoCriterio) {
        List<Libro> resultados = new ArrayList<>();

        for (Libro libro : listaLibros) {
            if (tipoCriterio.equalsIgnoreCase("autor") && libro.getAutor().equalsIgnoreCase(texto)) {
                resultados.add(libro);
            } else if (tipoCriterio.equalsIgnoreCase("categoria") && libro.getCategoria().equalsIgnoreCase(texto)) {
                resultados.add(libro);
            } else if (tipoCriterio.equalsIgnoreCase("titulo") && libro.getTitulo().toLowerCase().contains(texto.toLowerCase())) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    // GESTIÓN DE PRÉSTAMOS Y DEVOLUCIONES

    public String registrarPrestamo(String codigoLibro, String idUsuario) {
        Libro libro = buscarPorCodigo(codigoLibro);

        if (libro == null) {
            return "Error: El libro con el código " + codigoLibro + " no existe.";
        }

        // Comprobamos si el libro ya está prestado
        if (!libro.isDisponible()) {
            return "El libro '" + libro.getTitulo() + "' NO está disponible. Ya se encuentra prestado.";
        }

        // Si está disponible, lo prestamos
        libro.setDisponible(false);
        return "¡Préstamo exitoso! El libro '" + libro.getTitulo() + "' ha sido asignado al usuario: " + idUsuario;
    }

    // Registrar una devolución
    public void registrarDevolucion(String codigoLibro) {
        Libro libro = buscarPorCodigo(codigoLibro);

        if (libro == null) {
            System.out.println("El libro no pertenece a la biblioteca.");
            return;
        }

        System.out.println("Devolución procesada para: " + libro.getTitulo());

        // REVISAR SI HAY ALGUIEN EN LA COLA DE ESPERA (RESERVAS)

        Queue<String> colaEspera = mapaReservas.get(codigoLibro);

        if (colaEspera != null && !colaEspera.isEmpty()) {

            String siguienteUsuario = colaEspera.poll();
            System.out.println("[RESERVA ACTIVA] El libro se asigna automáticamente al siguiente usuario en cola: " + siguienteUsuario);
            libro.setDisponible(false);
        } else {
            // Si nadie lo estaba esperando, el libro vuelve a estar libre en estantería
            libro.setDisponible(true);
            System.out.println("El libro ahora está disponible para cualquier usuario.");
        }
    }

    // FUNCIONES EXTRAS (RESERVAS)

    // Añadir un usuario a la cola de espera
    public void reservarLibro(String codigoLibro, String idUsuario) {
        Libro libro = buscarPorCodigo(codigoLibro);

        if (libro == null) {
            System.out.println("El libro no existe.");
            return;
        }

        if (libro.isDisponible()) {
            System.out.println("El libro está disponible en estantería. No es necesario reservarlo, puede pedirlo en préstamo.");
            return;
        }

        // Si la cola para este libro aún no existe en el mapa, la creamos usando LinkedList
        if (!mapaReservas.containsKey(codigoLibro)) {
            mapaReservas.put(codigoLibro, new LinkedList<>());
        }

        // Añadimos al usuario al final de la cola
        mapaReservas.get(codigoLibro).add(idUsuario);
        System.out.println("Usuario " + idUsuario + " añadido a la cola de espera para el libro: " + libro.getTitulo());
    }
}