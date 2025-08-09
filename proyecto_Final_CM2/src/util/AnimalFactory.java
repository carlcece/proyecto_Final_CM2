package util;

import config.TablaProbabilidadesAlimentacion;
import entidades.*;
import config.Configuracion;
import java.util.Map;

public class AnimalFactory {

    public static Animal crearAnimal(String especie) {
        Map<String, Double> caracteristicas = Configuracion.CARACTERISTICAS_ESPECIES.get(especie);
        if (caracteristicas == null) {
            System.err.println("Error: Especie no encontrada en la configuración de características: " + especie);
            return null;
        }

        double pesoKg = caracteristicas.get("pesoKg");
        int numMaxPorLocalidad = caracteristicas.get("numMaxPorLocalidad").intValue();
        int velocidadMaxima = caracteristicas.get("velocidadMaxima").intValue();
        double alimentoNecesarioKg = caracteristicas.get("alimentoNecesarioKg");
        double tasaReproduccion = Configuracion.TASA_REPRODUCCION_POR_ESPECIE.getOrDefault(especie, 0.0);

        double aguaNecesariaLitros = caracteristicas.get("aguaNecesariaLitros");
        String dieta;
        // Lógica de dieta mejorada
        boolean comeAnimales = TablaProbabilidadesAlimentacion.getPresasDelDepredador(especie) != null && TablaProbabilidadesAlimentacion.getPresasDelDepredador(especie).entrySet().stream().anyMatch(e -> e.getValue() > 0 && !e.getKey().equals("Planta") && !e.getKey().equals(especie));
        boolean comePlantas = TablaProbabilidadesAlimentacion.getProbabilidad(especie, "Planta") > 0;

        if (comeAnimales && comePlantas) {
            dieta = "omnivoro";
        } else if (comeAnimales) {
            dieta = "carnivoro";
        } else if (comePlantas) {
            dieta = "herbivoro";
        } else {
            dieta = "herbivoro"; // Opción por defecto
        }

        Animal nuevoAnimal;
        switch (especie) {
            case "Lobo":
                nuevoAnimal = new Lobo(dieta, tasaReproduccion, velocidadMaxima, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
                break;
            case "Boa":
                nuevoAnimal = new Boa(dieta, tasaReproduccion, velocidadMaxima, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
                break;
            case "Zorro":
                nuevoAnimal = new Zorro(dieta, tasaReproduccion, velocidadMaxima, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg,aguaNecesariaLitros);
                break;
            case "Oso":
                nuevoAnimal = new Oso(dieta, tasaReproduccion, velocidadMaxima, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
                break;
            case "Aguila":
                nuevoAnimal = new Aguila(dieta, tasaReproduccion, velocidadMaxima, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
                break;
            case "Caballo":
                nuevoAnimal = new Caballo(dieta, tasaReproduccion, velocidadMaxima, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
                break;
            case "Ciervo":
                nuevoAnimal = new Ciervo(dieta, tasaReproduccion, velocidadMaxima, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
                break;
            case "Conejo":
                nuevoAnimal = new Conejo(dieta, tasaReproduccion, velocidadMaxima, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
                break;
            case "Raton":
                nuevoAnimal = new Raton(dieta, tasaReproduccion, velocidadMaxima, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
                break;
            case "Cabra":
                nuevoAnimal = new Cabra(dieta, tasaReproduccion, velocidadMaxima, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
                break;
            case "Oveja":
                nuevoAnimal = new Oveja(dieta, tasaReproduccion, velocidadMaxima, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
                break;
            case "Jabali":
                nuevoAnimal = new Jabali(dieta, tasaReproduccion, velocidadMaxima, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg,aguaNecesariaLitros );
                break;
            case "Bufalo":
                nuevoAnimal = new Bufalo(dieta, tasaReproduccion, velocidadMaxima, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
                break;
            case "Pato":
                nuevoAnimal = new Pato(dieta, tasaReproduccion, velocidadMaxima, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
                break;
            case "Oruga":
                nuevoAnimal = new Oruga(dieta, tasaReproduccion, velocidadMaxima, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
                break;
            case "Tigre":
                nuevoAnimal = new Tigre(dieta, tasaReproduccion, velocidadMaxima, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
                break;
            default:
                System.err.println("Especie no implementada: " + especie);
                return null;
        }
        return nuevoAnimal;
    }
}