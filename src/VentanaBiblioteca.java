import javax.swing.*;
import javax.swing.JFrame;

public class VentanaBiblioteca extends JFrame {
    private JPanel panelPrincipal;
    private JTabbedPane tbdpgeneral;
    private JButton btnRegistrar;
    private JTextField txtCodigo;
    private JLabel lblCodigo;
    private JTextField txtTitulo;
    private JLabel lblTitulo;
    private JLabel lblCategoria;
    private JTextField txtCategoria;
    private JLabel lblAutor;
    private JTextField txtAutor;
    private JButton btnEliminar;
    private JLabel lblCodigoLibro;
    private JTextField txtCodigoLibro;
    private JLabel lblIDUsuario;
    private JTextField txtIDUsuario;
    private JButton btnPrestar;
    private JButton btnDevolver;
    private JButton btnReservar;
    private JTable tblLibros;
    private JButton btnActualizar;
    private JTextField txtBusqueda;
    private JComboBox cmbCriterio;
    private JButton btnBuscar;

    private GestionBiblioteca biblioteca;
    private javax.swing.table.DefaultTableModel modeloTabla;

    public VentanaBiblioteca() {
        biblioteca = new GestionBiblioteca(); // Inicializa tu lógica híbrida

        setTitle("Sistema de Gestión de Biblioteca");
        setContentPane(panelPrincipal); // Vincula el panel del formulario
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        modeloTabla = new javax.swing.table.DefaultTableModel();
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Título");
        modeloTabla.addColumn("Autor");
        modeloTabla.addColumn("Categoría");
        modeloTabla.addColumn("Estado");
        tblLibros.setModel(modeloTabla);

        // BOTÓN REGISTRAR

        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String codigo = txtCodigo.getText().trim();
                String titulo = txtTitulo.getText().trim();
                String autor = txtAutor.getText().trim();
                String categoria = txtCategoria.getText().trim();
                if (codigo.isEmpty() || titulo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Código y Título son obligatorios.");
                    return;
                }
                Libro nuevo = new Libro(autor, categoria, true, codigo, titulo);
                biblioteca.registrarLibro(nuevo);
                // Limpiar campos
                txtCodigo.setText("");
                txtTitulo.setText("");
                txtAutor.setText("");
                txtCategoria.setText("");
                JOptionPane.showMessageDialog(null, "¡Libro registrado!");
                actualizarTabla();
            }
        });

        // BOTÓN ELIMINAR

        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String codigo = txtCodigo.getText().trim();
                if(codigo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Escribe el código del libro a eliminar.");
                    return;
                }
                biblioteca.eliminarLibro(codigo);
                JOptionPane.showMessageDialog(null, "Proceso de eliminación ejecutado.");
                actualizarTabla();
            }
        });

        // BOTÓN PRESTAR

        btnPrestar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String codigoLibro = txtCodigoLibro.getText().trim();
                String idUsuario = txtIDUsuario.getText().trim();

                if (codigoLibro.isEmpty() || idUsuario.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Código de libro e ID de Usuario son obligatorios.");
                    return;
                }

                String respuesta = biblioteca.registrarPrestamo(codigoLibro, idUsuario);
                JOptionPane.showMessageDialog(null, respuesta);

                txtCodigoLibro.setText("");
                txtIDUsuario.setText("");
                actualizarTabla();
            }
        });

        // BOTÓN DEVOLVER

        btnDevolver.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String codigoLibro = txtCodigoLibro.getText().trim();

                if (codigoLibro.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese el código del libro a devolver.");
                    return;
                }
                biblioteca.registrarDevolucion(codigoLibro);
                JOptionPane.showMessageDialog(null, "Devolución procesada. Se ha actualizado el inventario y las colas de reserva.");
                txtCodigoLibro.setText("");
                txtIDUsuario.setText("");
                actualizarTabla();
            }
        });

        // BOTÓN RESERVAR

        btnReservar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String codigoLibro = txtCodigoLibro.getText().trim();
                String idUsuario = txtIDUsuario.getText().trim();

                if (codigoLibro.isEmpty() || idUsuario.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Para reservar necesita el Código del libro y su ID de Usuario.");
                    return;
                }
                biblioteca.reservarLibro(codigoLibro, idUsuario);
                JOptionPane.showMessageDialog(null, "Usuario " + idUsuario + " añadido con éxito a la lista de espera FIFO para este libro.");
                txtCodigoLibro.setText("");
                txtIDUsuario.setText("");
                actualizarTabla();
            }
        });

        // BOTÓN ACTUALIZAR (Pestaña Inventario)

        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                actualizarTabla();
                JOptionPane.showMessageDialog(null, "Tabla de inventario actualizada.");
            }
        });
    }
    // Método auxiliar para refrescar la tabla
    // Usamos el método de búsqueda que recorre el ArrayList para llenar la JTable
    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Libro libro : biblioteca.buscarPorCriterio("", "titulo")) {
            Object[] fila = {
                    libro.getCodigo(),
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.getCategoria(),
                    libro.isDisponible() ? "Disponible" : "Prestado"
            };
            modeloTabla.addRow(fila);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaBiblioteca ventana = new VentanaBiblioteca();
                ventana.setVisible(true); // Hace que la pantalla aparezca
            }
        });
    }
}
