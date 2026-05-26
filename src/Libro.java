public class Libro {
    int id;
    String titulo;
    String autor;
    String categoria;

    public Libro(int id, String titulo, String autor, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Título: " + titulo +
                " | Autor: " + autor +
                " | Categoría: " + categoria;
    }
}