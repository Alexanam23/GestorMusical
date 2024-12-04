package org.usc.musicCollection.UI;
import java.io.*;
import java.util.ArrayList;

public class GestorMusical {
    private ArrayList<Artista> artistas;
    private ArrayList<Cancion> canciones;
    private ArrayList<ListaReproduccion> listas;

    public GestorMusical() {
        this.artistas = new ArrayList<>();
        this.canciones = new ArrayList<>();
        this.listas = new ArrayList<>();
        cargarDatosDesdeArchivo();
    }

    private void cargarDatosDesdeArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader("datos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                switch (partes[0]) {
                    case "ARTISTA":
                        artistas.add(new Artista(Integer.parseInt(partes[1]), partes[2]));
                        break;
                    case "CANCION":
                        Artista artista = buscarArtistaPorId(Integer.parseInt(partes[3]));
                        canciones.add(new Cancion(Integer.parseInt(partes[1]), partes[2], artista));
                        break;
                    case "LISTA":
                        ListaReproduccion lista = new ListaReproduccion(Integer.parseInt(partes[1]), partes[2]);
                        listas.add(lista);
                        break;
                    case "LISTA_CANCION":
                        ListaReproduccion listaReproduccion = buscarListaPorId(Integer.parseInt(partes[1]));
                        Cancion cancion = buscarCancionPorId(Integer.parseInt(partes[2]));
                        if (listaReproduccion != null && cancion != null) {
                            listaReproduccion.agregarCancion(cancion);
                        }
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo: " + e.getMessage());
        }
    }

    public Artista buscarArtistaPorId(int id) {
        return artistas.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    public Cancion buscarCancionPorId(int id) {
        return canciones.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public ListaReproduccion buscarListaPorId(int id) {
        return listas.stream().filter(l -> l.getId() == id).findFirst().orElse(null);
    }

    public ArrayList<Artista> getArtistas() {
        return artistas;
    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    public ArrayList<ListaReproduccion> getListas() {
        return listas;
    }

    public void agregarArtista(Artista artista) {
        artistas.add(artista);
    }

    public void agregarCancion(Cancion cancion) {
        canciones.add(cancion);
    }

    public void agregarLista(ListaReproduccion lista) {
        listas.add(lista);
    }
}
