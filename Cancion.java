package org.usc.musicCollection.UI;
public class Cancion {
    private int id;
    private String titulo;
    private Artista artista;

    public Cancion(int id, String titulo, Artista artista) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public Artista getArtista() { return artista; }

    @Override
    public String toString() {
        return titulo + " - " + artista.getNombre();
    }
}
