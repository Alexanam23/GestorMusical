package org.usc.musicCollection.UI;

import java.util.ArrayList;

public class ListaReproduccion {
    private int id;
    private String nombre;
    private ArrayList<Cancion> canciones;

    public ListaReproduccion(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.canciones = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public ArrayList<Cancion> getCanciones() { return canciones; }

    public void agregarCancion(Cancion cancion) {
        canciones.add(cancion);
    }

    @Override
    public String toString() {
        return nombre + " (" + canciones.size() + " canciones)";
    }
}
