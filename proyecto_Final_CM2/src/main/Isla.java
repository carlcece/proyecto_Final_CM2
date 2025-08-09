package main;

import entidades.Ubicacion;
import entidades.Animal;
import config.Configuracion;
import util.Coordenada;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Isla {
    private Ubicacion[][] cuadricula;
    private int filas;
    private int columnas;
    private long totalAnimales = 0;
    private long totalPlantas = 0;

    public Isla() {
        this.filas = Configuracion.FILAS_ISLA;
        this.columnas = Configuracion.COLUMNAS_ISLA;
        this.cuadricula = new Ubicacion[filas][columnas];
        inicializarIsla();
    }

    public void imprimirResumen() {
        System.out.println("--- Estado de la Isla ---");
        System.out.println("Dimensiones: " + filas + "x" + columnas);
        System.out.println("Total Animales: " + getTotalAnimales());
        System.out.println("Total Plantas: " + getTotalPlantas());
    }

    private void inicializarIsla() {
        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < columnas; c++) {
                cuadricula[r][c] = new Ubicacion();
            }
        }
        actualizarEstadisticas();
    }

    public Ubicacion getUbicacion(Coordenada coord) {
        if (coord.fila >= 0 && coord.fila < filas && coord.columna >= 0 && coord.columna < columnas) {
            return cuadricula[coord.fila][coord.columna];
        }
        return null;
    }

    public List<Coordenada> obtenerVecinos(Coordenada coord) {
        List<Coordenada> vecinos = new ArrayList<>();
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int nr = coord.fila + dr[i];
            int nc = coord.columna + dc[i];
            if (nr >= 0 && nr < filas && nc >= 0 && nc < columnas) {
                vecinos.add(new Coordenada(nr, nc));
            }
        }
        return vecinos;
    }

    public void actualizarEstadisticas() {
        long tempTotalAnimales = 0;
        long tempTotalPlantas = 0;
        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < columnas; c++) {
                Ubicacion ubicacion = cuadricula[r][c];
                tempTotalPlantas += ubicacion.getPlantas().getCantidad();
                tempTotalAnimales += ubicacion.getAnimales().size();
            }
        }
        this.totalAnimales = tempTotalAnimales;
        this.totalPlantas = tempTotalPlantas;
    }

    public long getTotalAnimales() {
        return totalAnimales;
    }

    public long getTotalPlantas() {
        return totalPlantas;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public List<Animal> obtenerTodosLosAnimales() {
        // Usamos CopyOnWriteArrayList para que la lista sea segura para hilos,
        // ya que los animales se están moviendo y las listas de cada ubicación pueden cambiar.
        List<Animal> todosLosAnimales = new CopyOnWriteArrayList<>();
        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < columnas; c++) {
                Ubicacion ubicacion = cuadricula[r][c];
                todosLosAnimales.addAll(ubicacion.getAnimales());
            }
        }
        return todosLosAnimales;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!Configuracion.UNICODE_SIMBOLOS.isEmpty()) {
            for (int r = 0; r < filas; r++) {
                for (int c = 0; c < columnas; c++) {
                    Ubicacion u = cuadricula[r][c];
                    if (!u.getAnimales().isEmpty()) {
                        String simbolo = Configuracion.UNICODE_SIMBOLOS.getOrDefault(u.getAnimales().get(0).getEspecie(), "?");
                        sb.append(simbolo).append(" ");
                    } else if (u.getPlantas().getCantidad() > 0) {
                        sb.append(Configuracion.UNICODE_SIMBOLOS.getOrDefault("Planta", "#")).append(" ");
                    } else {
                        sb.append(Configuracion.UNICODE_SIMBOLOS.getOrDefault("Vacio", ".")).append(" ");
                    }
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public void poblarPlantasLetales() {
        Random random = new Random();
        int plantasColocadas = 0;
        while (plantasColocadas < Configuracion.CANTIDAD_PLANTAS_LETALES) {
            int fila = random.nextInt(filas);
            int columna = random.nextInt(columnas);
            Ubicacion ubicacion = cuadricula[fila][columna];
            if (ubicacion.getTerreno().equals(Configuracion.TIERRA) && !ubicacion.getPlantas().getTipo().equals(Configuracion.PLANTA_LETAL)) {
                ubicacion.getPlantas().setTipo(Configuracion.PLANTA_LETAL);
                plantasColocadas++;
            }
        }
    }

}