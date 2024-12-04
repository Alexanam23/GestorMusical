package org.usc.musicCollection.UI;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GestorMusicalUI extends JFrame {
    private GestorMusical gestor;

    public GestorMusicalUI() {
        gestor = new GestorMusical();
        setTitle("Gestor de Colecciones de Música");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelListas = crearPanelListas();
        JPanel panelArtistas = crearPanelArtistas();
        JPanel panelCanciones = crearPanelCanciones();

        add(panelListas, BorderLayout.WEST);
        add(panelArtistas, BorderLayout.CENTER);
        add(panelCanciones, BorderLayout.EAST);
    }

    private JPanel crearPanelListas() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Gestión de Listas de Reproducción"));

        DefaultListModel<ListaReproduccion> modeloListas = new DefaultListModel<>();
        gestor.getListas().forEach(modeloListas::addElement);

        JList<ListaReproduccion> listaListas = new JList<>(modeloListas);
        JScrollPane scroll = new JScrollPane(listaListas);

        JButton btnCrearLista = new JButton("Crear Lista");
        btnCrearLista.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Nombre de la nueva lista:");
            if (nombre != null && !nombre.isEmpty()) {
                ListaReproduccion nuevaLista = new ListaReproduccion(gestor.getListas().size() + 1, nombre);
                gestor.agregarLista(nuevaLista);
                modeloListas.addElement(nuevaLista);
            }
        });

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(btnCrearLista, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel crearPanelArtistas() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Gestión de Artistas"));

        DefaultListModel<Artista> modeloArtistas = new DefaultListModel<>();
        gestor.getArtistas().forEach(modeloArtistas::addElement);

        JList<Artista> listaArtistas = new JList<>(modeloArtistas);
        JScrollPane scroll = new JScrollPane(listaArtistas);

        JButton btnCrearArtista = new JButton("Crear Artista");
        btnCrearArtista.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Nombre del artista:");
            if (nombre != null && !nombre.isEmpty()) {
                Artista nuevoArtista = new Artista(gestor.getArtistas().size() + 1, nombre);
                gestor.agregarArtista(nuevoArtista);
                modeloArtistas.addElement(nuevoArtista);
            }
        });

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(btnCrearArtista, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel crearPanelCanciones() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Gestión de Canciones"));

        DefaultListModel<Cancion> modeloCanciones = new DefaultListModel<>();
        gestor.getCanciones().forEach(modeloCanciones::addElement);

        JList<Cancion> listaCanciones = new JList<>(modeloCanciones);
        JScrollPane scroll = new JScrollPane(listaCanciones);

        JButton btnCrearCancion = new JButton("Crear Canción");
        btnCrearCancion.addActionListener(e -> {
            String titulo = JOptionPane.showInputDialog("Título de la canción:");
            Artista[] artistas = gestor.getArtistas().toArray(new Artista[0]);
            Artista artista = (Artista) JOptionPane.showInputDialog(
                    this,
                    "Seleccione un artista:",
                    "Artista",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    artistas,
                    artistas[0]
            );
            if (titulo != null && artista != null) {
                Cancion nuevaCancion = new Cancion(gestor.getCanciones().size() + 1, titulo, artista);
                gestor.agregarCancion(nuevaCancion);
                modeloCanciones.addElement(nuevaCancion);
            }
        });

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(btnCrearCancion, BorderLayout.SOUTH);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestorMusicalUI app = new GestorMusicalUI();
            app.setVisible(true);
        });
    }
}
