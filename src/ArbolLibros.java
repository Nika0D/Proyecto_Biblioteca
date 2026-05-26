public class ArbolLibros {

    NodoLibro raiz;

    // Insertar libro
    public void insertar(Libro libro) {
        raiz = insertarRec(raiz, libro);
    }

    private NodoLibro insertarRec(NodoLibro raiz, Libro libro) {

        if (raiz == null) {
            raiz = new NodoLibro(libro);
            return raiz;
        }

        if (libro.titulo.compareToIgnoreCase(raiz.libro.titulo) < 0) {
            raiz.izquierda = insertarRec(raiz.izquierda, libro);
        } else {
            raiz.derecha = insertarRec(raiz.derecha, libro);
        }

        return raiz;
    }

    // Buscar libro
    public Libro buscar(String titulo) {
        return buscarRec(raiz, titulo);
    }

    private Libro buscarRec(NodoLibro raiz, String titulo) {

        if (raiz == null) {
            return null;
        }

        if (raiz.libro.titulo.equalsIgnoreCase(titulo)) {
            return raiz.libro;
        }

        if (titulo.compareToIgnoreCase(raiz.libro.titulo) < 0) {
            return buscarRec(raiz.izquierda, titulo);
        } else {
            return buscarRec(raiz.derecha, titulo);
        }
    }

    // Mostrar libros ordenados
    public void mostrarInOrden() {
        inOrden(raiz);
    }

    private void inOrden(NodoLibro raiz) {

        if (raiz != null) {
            inOrden(raiz.izquierda);
            System.out.println(raiz.libro);
            inOrden(raiz.derecha);
        }
    }
}