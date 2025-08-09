package config;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Configuracion {

    // Configuracion generales de la isla y la simulación
    public static final int FILAS_ISLA = 50; // Define la altura de la isla en celdas.
    public static final int COLUMNAS_ISLA = 10; // Define el ancho de la isla en celdas.
    public static final long DURACION_CICLO_MS = 1000; // Define la duración de cada ciclo en milisegundos (1000 ms = 1 segundo).
    public static final int NUM_HILOS_ANIMAL_ACTIONS = 4; // Define la cantidad de hilos para procesar las acciones de los animales.
    public static final int MAX_CANTIDAD_PLANTAS_POR_UBICACION = 1; // El número máximo de plantas que puede haber en una sola celda.
    public static final int TASA_CRECIMIENTO_PLANTAS = 5; // La cantidad de plantas que crecen en una celda por ciclo.
    public static final boolean PARAR_SI_EXTINCION = true; // Si es true, la simulación se detiene cuando no quedan animales.
    public static final int MIN_ANIMALES_TOTAL_PARA_PARAR = 0; // El número mínimo de animales para que la simulación se detenga.
    public static final int MAX_CICLOS_PARA_PARAR = 500; // El número máximo de ciclos que se ejecutará la simulación.
    public static final String TIERRA = "Tierra"; // Define una constante para el nombre del terreno de tierra.
    public static final String AGUA = "Agua"; // Define una constante para el nombre del terreno de agua.
    public static final double PROBABILIDAD_AGUA = 0.1; // La probabilidad (10%) de que una celda sea de agua.
    public static final String PLANTA_LETAL = "Adelfa"; // Define una constante para el nombre de la planta letal.
    public static final int CANTIDAD_PLANTAS_LETALES = 3; // La cantidad total de plantas letales en la isla.

    // Configuración de los colores del terreno ---
    public static final Map<String, Color> COLORES_TERRENO = new HashMap<>();
    static {
        COLORES_TERRENO.put(TIERRA, new Color(139, 69, 19)); // Asigna un color marrón a la tierra.
        COLORES_TERRENO.put(AGUA, new Color(50, 150, 200)); // Asigna un color azul al agua.
        COLORES_TERRENO.put(PLANTA_LETAL, new Color(255, 0, 0)); // Asigna un color rojo a la planta letal.
    }

    // Configuración de la población inicial de animales por especie
    public static final Map<String, Integer> POBLACION_INICIAL_POR_ESPECIE = new HashMap<>();
    static {
        // Herbívoros y omnívoros de la base (población alta)
        POBLACION_INICIAL_POR_ESPECIE.put("Oruga", 400);
        POBLACION_INICIAL_POR_ESPECIE.put("Raton", 250);
        POBLACION_INICIAL_POR_ESPECIE.put("Conejo", 200);
        POBLACION_INICIAL_POR_ESPECIE.put("Ciervo", 75);
        POBLACION_INICIAL_POR_ESPECIE.put("Cabra", 75);
        POBLACION_INICIAL_POR_ESPECIE.put("Oveja", 75);
        POBLACION_INICIAL_POR_ESPECIE.put("Pato", 50);
        POBLACION_INICIAL_POR_ESPECIE.put("Caballo", 25);
        POBLACION_INICIAL_POR_ESPECIE.put("Bufalo", 25);

        // Carnívoros de nivel medio (población media)
        POBLACION_INICIAL_POR_ESPECIE.put("Jabali", 25);
        POBLACION_INICIAL_POR_ESPECIE.put("Zorro", 20);
        POBLACION_INICIAL_POR_ESPECIE.put("Oso", 4);
        POBLACION_INICIAL_POR_ESPECIE.put("Lobo", 25);
        POBLACION_INICIAL_POR_ESPECIE.put("Tigre", 10);

        // Depredadores principales (población baja)
        POBLACION_INICIAL_POR_ESPECIE.put("Aguila", 16);
        POBLACION_INICIAL_POR_ESPECIE.put("Boa", 12);
    }

    // Cantidad de crías que nacen por cada evento de reproducción
    public static final Map<String, Integer> NUM_CRIAS_POR_ESPECIE = new HashMap<>();
    static {
        NUM_CRIAS_POR_ESPECIE.put("Lobo", 5); // Aumentado a un valor más realista
        NUM_CRIAS_POR_ESPECIE.put("Boa", 20); // Aumentado a un valor más realista
        NUM_CRIAS_POR_ESPECIE.put("Zorro", 4); // Aumentado a un valor más realista
        NUM_CRIAS_POR_ESPECIE.put("Oso", 2); // Valor más realista
        NUM_CRIAS_POR_ESPECIE.put("Aguila", 2); // Valor más realista
        NUM_CRIAS_POR_ESPECIE.put("Caballo", 1); // Valor realista
        NUM_CRIAS_POR_ESPECIE.put("Ciervo", 1); // Valor realista
        NUM_CRIAS_POR_ESPECIE.put("Conejo", 5); // Valor más realista
        NUM_CRIAS_POR_ESPECIE.put("Raton", 8); // Valor más realista
        NUM_CRIAS_POR_ESPECIE.put("Cabra", 2); // Valor realista
        NUM_CRIAS_POR_ESPECIE.put("Oveja", 2); // Valor realista
        NUM_CRIAS_POR_ESPECIE.put("Jabali", 5); // Aumentado a un valor más realista
        NUM_CRIAS_POR_ESPECIE.put("Bufalo", 1); // Valor realista
        NUM_CRIAS_POR_ESPECIE.put("Pato", 6); // Valor más realista
        NUM_CRIAS_POR_ESPECIE.put("Oruga", 50); // Valor más realista
    }

    public static final Map<String, Double> PROBABILIDAD_MOVIMIENTO_POR_ESPECIE = new HashMap<>();
    static {
        PROBABILIDAD_MOVIMIENTO_POR_ESPECIE.put("Lobo", 0.9);
        PROBABILIDAD_MOVIMIENTO_POR_ESPECIE.put("Boa", 0.7);
        PROBABILIDAD_MOVIMIENTO_POR_ESPECIE.put("Zorro", 0.8);
        PROBABILIDAD_MOVIMIENTO_POR_ESPECIE.put("Oso", 0.6);
        PROBABILIDAD_MOVIMIENTO_POR_ESPECIE.put("Aguila", 0.95);
        PROBABILIDAD_MOVIMIENTO_POR_ESPECIE.put("Caballo", 0.7);
        PROBABILIDAD_MOVIMIENTO_POR_ESPECIE.put("Ciervo", 0.8);
        PROBABILIDAD_MOVIMIENTO_POR_ESPECIE.put("Conejo", 0.9);
        PROBABILIDAD_MOVIMIENTO_POR_ESPECIE.put("Raton", 0.95);
        PROBABILIDAD_MOVIMIENTO_POR_ESPECIE.put("Cabra", 0.75);
        PROBABILIDAD_MOVIMIENTO_POR_ESPECIE.put("Oveja", 0.75);
        PROBABILIDAD_MOVIMIENTO_POR_ESPECIE.put("Jabali", 0.6);
        PROBABILIDAD_MOVIMIENTO_POR_ESPECIE.put("Bufalo", 0.5);
        PROBABILIDAD_MOVIMIENTO_POR_ESPECIE.put("Pato", 0.85);
        PROBABILIDAD_MOVIMIENTO_POR_ESPECIE.put("Oruga", 0.5);
        PROBABILIDAD_MOVIMIENTO_POR_ESPECIE.put("Tigre", 0.9);
    }

    // MAPA DE TASAS DE REPRODUCCIÓN AÑADIDO
    public static final Map<String, Double> TASA_REPRODUCCION_POR_ESPECIE = new HashMap<>();
    static {
        TASA_REPRODUCCION_POR_ESPECIE.put("Lobo", 0.05); // Una vez al año
        TASA_REPRODUCCION_POR_ESPECIE.put("Boa", 0.003); // Una vez al año
        TASA_REPRODUCCION_POR_ESPECIE.put("Zorro", 0.1); // Una vez al año
        TASA_REPRODUCCION_POR_ESPECIE.put("Oso", 0.001); // Cada 2-3 años
        TASA_REPRODUCCION_POR_ESPECIE.put("Aguila", 0.2); // Una vez al año
        TASA_REPRODUCCION_POR_ESPECIE.put("Caballo", 0.003); // Una vez al año
        TASA_REPRODUCCION_POR_ESPECIE.put("Ciervo", 0.003); // Una vez al año
        TASA_REPRODUCCION_POR_ESPECIE.put("Conejo", 0.003); // Varias veces al año
        TASA_REPRODUCCION_POR_ESPECIE.put("Raton", 0.01); // Varias veces al año
        TASA_REPRODUCCION_POR_ESPECIE.put("Cabra", 0.008); // Una o dos veces al año
        TASA_REPRODUCCION_POR_ESPECIE.put("Oveja", 0.008); // Una o dos veces al año
        TASA_REPRODUCCION_POR_ESPECIE.put("Jabali", 0.008); // Una vez al año
        TASA_REPRODUCCION_POR_ESPECIE.put("Bufalo", 0.003); // Una vez al año
        TASA_REPRODUCCION_POR_ESPECIE.put("Pato", 0.015); // Varias veces al año
        TASA_REPRODUCCION_POR_ESPECIE.put("Oruga", 0.002); // Varias veces al año
        TASA_REPRODUCCION_POR_ESPECIE.put("Tigre", 0.005);
    }

    // Cantidad de agua que un animal pierde por ciclo
    public static final Map<String, Double> DESHIDRATACION_POR_ESPECIE = new HashMap<>();
    static {
        DESHIDRATACION_POR_ESPECIE.put("Raton", 0.00125);
        DESHIDRATACION_POR_ESPECIE.put("Conejo", 0.05);
        DESHIDRATACION_POR_ESPECIE.put("Oruga", 0.0);
        DESHIDRATACION_POR_ESPECIE.put("Pato", 0.125);
        DESHIDRATACION_POR_ESPECIE.put("Zorro", 0.025);
        DESHIDRATACION_POR_ESPECIE.put("Cabra", 0.5);
        DESHIDRATACION_POR_ESPECIE.put("Oveja", 0.525);
        DESHIDRATACION_POR_ESPECIE.put("Aguila", 0.0125);
        DESHIDRATACION_POR_ESPECIE.put("Jabali", 2.25);
        DESHIDRATACION_POR_ESPECIE.put("Ciervo", 1.05);
        DESHIDRATACION_POR_ESPECIE.put("Boa", 0.015);
        DESHIDRATACION_POR_ESPECIE.put("Caballo", 1.75);
        DESHIDRATACION_POR_ESPECIE.put("Bufalo", 2.1);
        DESHIDRATACION_POR_ESPECIE.put("Oso", 0.0532);
        DESHIDRATACION_POR_ESPECIE.put("Tigre", 0.428);
    }

    public static final Map<String, Double> TASA_INANICION_POR_ESPECIE = new HashMap<>();
    static {
        TASA_INANICION_POR_ESPECIE.put("Raton", 0.01);
        TASA_INANICION_POR_ESPECIE.put("Conejo", 0.45);
        TASA_INANICION_POR_ESPECIE.put("Oruga", 0.0025); // Este es el valor que te funciona
        TASA_INANICION_POR_ESPECIE.put("Pato", 0.15);
        TASA_INANICION_POR_ESPECIE.put("Zorro", 2.0);
        TASA_INANICION_POR_ESPECIE.put("Cabra", 10.00);
        TASA_INANICION_POR_ESPECIE.put("Oveja", 15.00);
        TASA_INANICION_POR_ESPECIE.put("Aguila", 1.0);
        TASA_INANICION_POR_ESPECIE.put("Jabali", 50.00);
        TASA_INANICION_POR_ESPECIE.put("Caballo", 60.00);
        TASA_INANICION_POR_ESPECIE.put("Ciervo", 50.00);
        TASA_INANICION_POR_ESPECIE.put("Bufalo", 100.00);
        TASA_INANICION_POR_ESPECIE.put("Boa", 3.0);
        TASA_INANICION_POR_ESPECIE.put("Oso", 80.00);
        TASA_INANICION_POR_ESPECIE.put("Lobo", 8.0);
        TASA_INANICION_POR_ESPECIE.put("Tigre", 17.857);
    }

    public static final Map<String, String> UNICODE_SIMBOLOS = new HashMap<>();
    static {
        UNICODE_SIMBOLOS.put("Lobo", "🐺");
        UNICODE_SIMBOLOS.put("Boa", "🐍");
        UNICODE_SIMBOLOS.put("Zorro", "🦊");
        UNICODE_SIMBOLOS.put("Oso", "🐻");
        UNICODE_SIMBOLOS.put("Aguila", "🦅");
        UNICODE_SIMBOLOS.put("Caballo", "🐎");
        UNICODE_SIMBOLOS.put("Ciervo", "🦌");
        UNICODE_SIMBOLOS.put("Conejo", "🐇");
        UNICODE_SIMBOLOS.put("Raton", "🐁");
        UNICODE_SIMBOLOS.put("Cabra", "🐐");
        UNICODE_SIMBOLOS.put("Oveja", "🐑");
        UNICODE_SIMBOLOS.put("Jabali", "🐗");
        UNICODE_SIMBOLOS.put("Bufalo", "🐃");
        UNICODE_SIMBOLOS.put("Pato", "🦆");
        UNICODE_SIMBOLOS.put("Oruga", "🐛");
        UNICODE_SIMBOLOS.put("Adelfa", "🌹");
        UNICODE_SIMBOLOS.put("Planta", "🌿");
        UNICODE_SIMBOLOS.put("Vacio", ".");
        UNICODE_SIMBOLOS.put("Agua", "🌊");
        UNICODE_SIMBOLOS.put("Tigre", "🐅");

    }

    // Características detalladas de cada especie animal
    public static final Map<String, Map<String, Double>> CARACTERISTICAS_ESPECIES = new HashMap<>();
    static {
        Map<String, Double> loboCaracts = new HashMap<>();
        loboCaracts.put("pesoKg", 50.0);
        loboCaracts.put("numMaxPorLocalidad", 30.0);
        loboCaracts.put("velocidadMaxima", 3.0);
        loboCaracts.put("alimentoNecesarioKg", 8.0);
        loboCaracts.put("aguaNecesariaLitros", 5.0);
        CARACTERISTICAS_ESPECIES.put("Lobo", loboCaracts);

        Map<String, Double> boaCaracts = new HashMap<>();
        boaCaracts.put("pesoKg", 15.0);
        boaCaracts.put("numMaxPorLocalidad", 30.0);
        boaCaracts.put("velocidadMaxima", 1.0);
        boaCaracts.put("alimentoNecesarioKg", 3.0);
        boaCaracts.put("aguaNecesariaLitros", 1.0);
        CARACTERISTICAS_ESPECIES.put("Boa", boaCaracts);

        Map<String, Double> zorroCaracts = new HashMap<>();
        zorroCaracts.put("pesoKg", 8.0);
        zorroCaracts.put("numMaxPorLocalidad", 30.0);
        zorroCaracts.put("velocidadMaxima", 2.0);
        zorroCaracts.put("alimentoNecesarioKg", 2.0);
        zorroCaracts.put("aguaNecesariaLitros", 1.0);
        CARACTERISTICAS_ESPECIES.put("Zorro", zorroCaracts);

        Map<String, Double> osoCaracts = new HashMap<>();
        osoCaracts.put("pesoKg", 500.0);
        osoCaracts.put("numMaxPorLocalidad", 5.0);
        osoCaracts.put("velocidadMaxima", 2.0);
        osoCaracts.put("alimentoNecesarioKg", 80.0);
        osoCaracts.put("aguaNecesariaLitros", 7.6);
        CARACTERISTICAS_ESPECIES.put("Oso", osoCaracts);

        Map<String, Double> aguilaCaracts = new HashMap<>();
        aguilaCaracts.put("pesoKg", 6.0);
        aguilaCaracts.put("numMaxPorLocalidad", 20.0);
        aguilaCaracts.put("velocidadMaxima", 3.0);
        aguilaCaracts.put("alimentoNecesarioKg", 1.0);
        aguilaCaracts.put("aguaNecesariaLitros", 0.5);
        CARACTERISTICAS_ESPECIES.put("Aguila", aguilaCaracts);

        Map<String, Double> caballoCaracts = new HashMap<>();
        caballoCaracts.put("pesoKg", 400.0);
        caballoCaracts.put("numMaxPorLocalidad", 20.0);
        caballoCaracts.put("velocidadMaxima", 4.0);
        caballoCaracts.put("alimentoNecesarioKg", 60.0);
        caballoCaracts.put("aguaNecesariaLitros", 25.0);
        CARACTERISTICAS_ESPECIES.put("Caballo", caballoCaracts);

        Map<String, Double> ciervoCaracts = new HashMap<>();
        ciervoCaracts.put("pesoKg", 300.0);
        ciervoCaracts.put("numMaxPorLocalidad", 20.0);
        ciervoCaracts.put("velocidadMaxima", 4.0);
        ciervoCaracts.put("alimentoNecesarioKg", 50.0);
        ciervoCaracts.put("aguaNecesariaLitros", 10.0);
        CARACTERISTICAS_ESPECIES.put("Ciervo", ciervoCaracts);

        Map<String, Double> conejoCaracts = new HashMap<>();
        conejoCaracts.put("pesoKg", 2.0);
        conejoCaracts.put("numMaxPorLocalidad", 150.0);
        conejoCaracts.put("velocidadMaxima", 2.0);
        conejoCaracts.put("alimentoNecesarioKg", 0.45);
        conejoCaracts.put("aguaNecesariaLitros", 0.2);
        CARACTERISTICAS_ESPECIES.put("Conejo", conejoCaracts);

        Map<String, Double> ratonCaracts = new HashMap<>();
        ratonCaracts.put("pesoKg", 0.05);
        ratonCaracts.put("numMaxPorLocalidad", 500.0);
        ratonCaracts.put("velocidadMaxima", 1.0);
        ratonCaracts.put("alimentoNecesarioKg", 0.01);
        ratonCaracts.put("aguaNecesariaLitros", 0.005);
        CARACTERISTICAS_ESPECIES.put("Raton", ratonCaracts);

        Map<String, Double> cabraCaracts = new HashMap<>();
        cabraCaracts.put("pesoKg", 60.0);
        cabraCaracts.put("numMaxPorLocalidad", 140.0);
        cabraCaracts.put("velocidadMaxima", 3.0);
        cabraCaracts.put("alimentoNecesarioKg", 10.0);
        cabraCaracts.put("aguaNecesariaLitros", 3.5);
        CARACTERISTICAS_ESPECIES.put("Cabra", cabraCaracts);

        Map<String, Double> ovejaCaracts = new HashMap<>();
        ovejaCaracts.put("pesoKg", 70.0);
        ovejaCaracts.put("numMaxPorLocalidad", 140.0);
        ovejaCaracts.put("velocidadMaxima", 3.0);
        ovejaCaracts.put("alimentoNecesarioKg", 15.0);
        ovejaCaracts.put("aguaNecesariaLitros", 3.5);
        CARACTERISTICAS_ESPECIES.put("Oveja", ovejaCaracts);

        Map<String, Double> jabaliCaracts = new HashMap<>();
        jabaliCaracts.put("pesoKg", 400.0);
        jabaliCaracts.put("numMaxPorLocalidad", 50.0);
        jabaliCaracts.put("velocidadMaxima", 2.0);
        jabaliCaracts.put("alimentoNecesarioKg", 50.0);
        jabaliCaracts.put("aguaNecesariaLitros", 15.0);
        CARACTERISTICAS_ESPECIES.put("Jabali", jabaliCaracts);

        Map<String, Double> bufaloCaracts = new HashMap<>();
        bufaloCaracts.put("pesoKg", 700.0);
        bufaloCaracts.put("numMaxPorLocalidad", 10.0);
        bufaloCaracts.put("velocidadMaxima", 3.0);
        bufaloCaracts.put("alimentoNecesarioKg", 100.0);
        bufaloCaracts.put("aguaNecesariaLitros", 30.0);
        CARACTERISTICAS_ESPECIES.put("Bufalo", bufaloCaracts);

        Map<String, Double> patoCaracts = new HashMap<>();
        patoCaracts.put("pesoKg", 1.0);
        patoCaracts.put("numMaxPorLocalidad", 200.0);
        patoCaracts.put("velocidadMaxima", 4.0);
        patoCaracts.put("alimentoNecesarioKg", 0.15);
        patoCaracts.put("aguaNecesariaLitros", 0.5);
        CARACTERISTICAS_ESPECIES.put("Pato", patoCaracts);

        Map<String, Double> orugaCaracts = new HashMap<>();
        orugaCaracts.put("pesoKg", 0.01);
        orugaCaracts.put("numMaxPorLocalidad", 1000.0);
        orugaCaracts.put("velocidadMaxima", 0.0);
        orugaCaracts.put("alimentoNecesarioKg", 0.0);
        orugaCaracts.put("aguaNecesariaLitros", 0.007);
        CARACTERISTICAS_ESPECIES.put("Oruga", orugaCaracts);

        Map<String, Double> tigreCaracts = new HashMap<>();
        tigreCaracts.put("pesoKg", 250.0);
        tigreCaracts.put("numMaxPorLocalidad", 1.0); // Solitarios
        tigreCaracts.put("velocidadMaxima", 4.0);
        tigreCaracts.put("alimentoNecesarioKg", 6.0);
        tigreCaracts.put("aguaNecesariaLitros", 17.857);
        CARACTERISTICAS_ESPECIES.put("Tigre", tigreCaracts);

    }
}