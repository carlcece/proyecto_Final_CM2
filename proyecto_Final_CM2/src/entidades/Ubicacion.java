package entidades;

import config.Configuracion;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

//Representa una ubicación individual o celda en la isla.
//Contiene plantas y animales, y tiene un tipo de terreno.
    public class Ubicacion {
    private Planta plantas;
    private List<Animal> animales;
    private String terreno;

//Constructor de la ubicación. Inicializa las plantas, la lista de animales y el tipo de terreno.
    public Ubicacion() {
        int maxPlantas = Configuracion.MAX_CANTIDAD_PLANTAS_POR_UBICACION;
        int plantasIniciales = 0;

        if (maxPlantas > 0) {
            plantasIniciales = ThreadLocalRandom.current().nextInt(maxPlantas);
        }

        this.plantas = new Planta(plantasIniciales, Configuracion.TASA_CRECIMIENTO_PLANTAS);
        this.animales = new CopyOnWriteArrayList<>();
        this.terreno = ThreadLocalRandom.current().nextDouble() < Configuracion.PROBABILIDAD_AGUA ? Configuracion.AGUA : Configuracion.TIERRA;
    }


    public void agregarAnimal(Animal animal) {
        this.animales.add(animal);
    }

    public void eliminarAnimal(Animal animal) {
        this.animales.remove(animal);
    }

    public Planta getPlantas() {
        return plantas;
    }

    public List<Animal> getAnimales() {
        return animales;
    }

    // Método agregado para la limpieza de animales
    public void setAnimales(List<Animal> nuevosAnimales) {
        this.animales = nuevosAnimales;
    }

    //Obtiene el tipo de terreno de la ubicacipn
    //El tipo de terreno (ej. "Tierra" o "Agua").

    public String getTerreno() { // NUEVO MÉTODO
        return terreno;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ubicación:\n");
        sb.append("  ").append(plantas.toString()).append("\n");
        sb.append("  Animales: [");
        if (!animales.isEmpty()) {
            for (int i = 0; i < animales.size(); i++) {
                sb.append(animales.get(i).getEspecie());
                if (i < animales.size() - 1) {
                    sb.append(", ");
                }
            }
        } else {
            sb.append("Vacío");
        }
        sb.append("]");
        return sb.toString();
    }
}