package main;

import config.Configuracion;
import entidades.Animal;
import entidades.Ubicacion;
import util.AnimalFactory;
import util.Coordenada;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class SimulacionIsla {

    // Atributos estáticos de la simulación
    private static Isla isla;
    private static ScheduledExecutorService scheduledExecutor;
    private static ExecutorService animalActionExecutor;
    private static long cicloActual = 0;
    private static boolean simulacionActiva = true;
    private static CountDownLatch latchAccionesAnimales;
    private static VentanaSimulacion gui;
    private static Map<String, Long> poblacionAnteriorPorEspecie = new HashMap<>();
    // Contadores del ciclo
    public static AtomicLong nacimientosEnCiclo = new AtomicLong(0);
    public static AtomicLong fallecimientosEnCiclo = new AtomicLong(0);
    // Usamos un double sincronizado para mayor precisión
    private static double kgConsumidosEnCiclo = 0.0;
    private static double litrosConsumidosEnCiclo = 0.0;
    // Buffer para el historial de eventos del ciclo
    private static final StringBuilder cicloLog = new StringBuilder();

    // Añade un mensaje al historial y lo imprime en la consola.
    public static synchronized void log(String mensaje) {
        cicloLog.append(mensaje).append("\n");
    }


    public static synchronized void agregarKgConsumidos(double kg) {
        kgConsumidosEnCiclo += kg;
    }

    public static synchronized void agregarLitrosConsumidos(double litros) { // NUEVO MÉTODO
        litrosConsumidosEnCiclo += litros;
    }

    public static void main(String[] args) {
        System.out.println("--- Iniciando Simulación del Ecosistema de la Isla ---");
        isla = new Isla();

        // 1. Poblar la isla
        poblarIslaInicial();



        // 2. Inicializa el mapa de población anterior con la población inicial
        for (Map.Entry<String, Integer> entry : Configuracion.POBLACION_INICIAL_POR_ESPECIE.entrySet()) {
            poblacionAnteriorPorEspecie.put(entry.getKey(), (long) entry.getValue());
        }

        // 3. Iniciar la interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            gui = new VentanaSimulacion(isla);
        });

        SimulacionIsla.log("GUI iniciada. Presiona los botones de la ventana para controlar la simulación.");

    }

    //Población inicial de animales en la isla.
    private static void poblarIslaInicial() {
        for (Map.Entry<String, Integer> entry : Configuracion.POBLACION_INICIAL_POR_ESPECIE.entrySet()) {
            String especie = entry.getKey();
            int cantidadTotal = entry.getValue();

            for (int i = 0; i < cantidadTotal; i++) {
                Animal nuevoAnimal = AnimalFactory.crearAnimal(especie);
                if (nuevoAnimal == null) continue;

                boolean colocado = false;
                int intentos = 0;
                final int maxIntentos = 100;

                while (!colocado && intentos < maxIntentos) {
                    int filaAleatoria = ThreadLocalRandom.current().nextInt(Configuracion.FILAS_ISLA);
                    int columnaAleatoria = ThreadLocalRandom.current().nextInt(Configuracion.COLUMNAS_ISLA);
                    Coordenada posicionInicial = new Coordenada(filaAleatoria, columnaAleatoria);

                    Ubicacion ubicacion = isla.getUbicacion(posicionInicial);

                    if (ubicacion != null) {
                        synchronized (ubicacion) {
                            if (ubicacion.getAnimales().stream().filter(a -> a.getEspecie().equals(especie)).count() < nuevoAnimal.getNumMaxPorLocalidad()) {
                                ubicacion.agregarAnimal(nuevoAnimal);
                                nuevoAnimal.setPosicion(posicionInicial);
                                nuevoAnimal.setIsla(isla);
                                colocado = true;
                            }
                        }
                    }
                    intentos++;
                }

                if (!colocado) {
                    SimulacionIsla.log("ADVERTENCIA: No se pudo colocar un animal de la especie " + especie + " después de " + maxIntentos + " intentos.");
                }
            }
        }
    }

    //Verifica si se ha alcanzado una condición para detener la simulación.
    private static boolean verificarCondicionParada() {
        if (cicloActual >= Configuracion.MAX_CICLOS_PARA_PARAR) {
            SimulacionIsla.log("CICLOS: Se alcanzó el número máximo de ciclos.");
            return true;
        }
        if (Configuracion.PARAR_SI_EXTINCION && isla.getTotalAnimales() <= Configuracion.MIN_ANIMALES_TOTAL_PARA_PARAR) {
            SimulacionIsla.log("EXTINSION: Extinción de la población de animales.");
            return true;
        }
        return false;
    }


    private static void crecerPlantas() {
        List<CompletableFuture<Void>> tareas = new ArrayList<>();
        for (int r = 0; r < Configuracion.FILAS_ISLA; r++) {
            final int fila = r;
            tareas.add(CompletableFuture.runAsync(() -> {
                for (int c = 0; c < Configuracion.COLUMNAS_ISLA; c++) {
                    Ubicacion ubicacion = isla.getUbicacion(new Coordenada(fila, c));
                    synchronized (ubicacion.getPlantas()) {
                        ubicacion.getPlantas().crecer();
                    }
                }
            }, animalActionExecutor));
        }
        CompletableFuture.allOf(tareas.toArray(new CompletableFuture[0])).join();
    }


    private static void limpiarAnimalesMuertos() {
        for (int r = 0; r < Configuracion.FILAS_ISLA; r++) {
            for (int c = 0; c < Configuracion.COLUMNAS_ISLA; c++) {
                Ubicacion ubicacion = isla.getUbicacion(new Coordenada(r, c));
                synchronized (ubicacion) {
                    List<Animal> animalesVivos = ubicacion.getAnimales().stream()
                            .filter(Animal::estaVivo)
                            .collect(Collectors.toList());

                    long animalesMuertos = ubicacion.getAnimales().size() - animalesVivos.size();
                    if (animalesMuertos > 0) {
                        fallecimientosEnCiclo.addAndGet(animalesMuertos);
                        ubicacion.setAnimales(new CopyOnWriteArrayList<>(animalesVivos));
                    }
                }
            }
        }
    }

    // Métodos públicos para controlar la simulación
    public static void iniciarSimulacion() {
        if (scheduledExecutor == null || scheduledExecutor.isShutdown()) {
            scheduledExecutor = Executors.newScheduledThreadPool(1);
            animalActionExecutor = Executors.newFixedThreadPool(Configuracion.NUM_HILOS_ANIMAL_ACTIONS);
            simulacionActiva = true;
            programarTareasSimulacion();
            isla.poblarPlantasLetales();
        }
    }

    public static void detenerSimulacion() {
        simulacionActiva = false;
        apagarSimulacion();
    }

    //Lógica principal del bucle de simulación.
    private static void programarTareasSimulacion() {
        scheduledExecutor.scheduleAtFixedRate(() -> {
            if (!simulacionActiva) {
                return;
            }

            // --- INICIO DE UN NUEVO CICLO ---
            cicloActual++;


            long totalAnimalesInicioCiclo = isla.getTotalAnimales();
            long totalPlantasInicioCiclo = isla.getTotalPlantas();

            // REINICIA TODOS LOS CONTADORES AQUÍ
            nacimientosEnCiclo.set(0);
            fallecimientosEnCiclo.set(0);
            kgConsumidosEnCiclo = 0.0;
            cicloLog.setLength(0);
            log("--- Inicio del Ciclo " + cicloActual + " ---");

            // 1. Crecimiento de Plantas
            crecerPlantas();

            // 2. Ejecutar Acciones de Animales
            List<Animal> todosLosAnimalesEnIsla = isla.obtenerTodosLosAnimales();
            latchAccionesAnimales = new CountDownLatch(todosLosAnimalesEnIsla.size());

            for (Animal animal : todosLosAnimalesEnIsla) {
                animalActionExecutor.submit(() -> {
                    try {
                        animal.ejecutarAccionesDeCiclo(isla);
                    } finally {
                        latchAccionesAnimales.countDown();
                    }
                });
            }

            // 3. La tarea de estadísticas espera a que todas las acciones terminen
            scheduledExecutor.submit(() -> {
                try {
                    latchAccionesAnimales.await();

                    limpiarAnimalesMuertos();

                    mostrarEstadisticas(totalAnimalesInicioCiclo, totalPlantasInicioCiclo);

                    if (verificarCondicionParada()) {
                        simulacionActiva = false;
                        apagarSimulacion();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log("La tarea de estadísticas fue interrumpida.");
                }
            });
        }, 0, Configuracion.DURACION_CICLO_MS, TimeUnit.MILLISECONDS);
    }

    //Muestra las estadísticas del ciclo en la GUI.
    private static void mostrarEstadisticas(long totalAnimalesInicioCiclo, long totalPlantasInicioCiclo) {
        isla.actualizarEstadisticas();

        StringBuilder sbFijas = new StringBuilder();
        sbFijas.append("--- Totales del Ciclo ").append(cicloActual).append("---\n");
        sbFijas.append("Total de animales: Inicio: ").append(totalAnimalesInicioCiclo).append(", Final: ").append(isla.getTotalAnimales()).append("\n");
        sbFijas.append("Total de plantas: Inicio: ").append(totalPlantasInicioCiclo).append(", Final: ").append(isla.getTotalPlantas()).append("\n");
        sbFijas.append("Nacimientos este ciclo: ").append(nacimientosEnCiclo.get()).append(" 👶\n");
        sbFijas.append("Fallecimientos este ciclo: ").append(fallecimientosEnCiclo.get()).append(" ⚰️\n");
        sbFijas.append(String.format("Kilogramos de alimento consumidos: %.3f Kg\n", kgConsumidosEnCiclo));
        sbFijas.append("Litros de agua consumidos: ").append(String.format("%.3f", litrosConsumidosEnCiclo)).append(" L\n");

        sbFijas.append("\n--- Población por Especie del Ciclo:").append(cicloActual).append("---\n");
        Map<String, Long> poblacionFinalPorEspecie = new HashMap<>();
        isla.obtenerTodosLosAnimales().forEach(animal -> poblacionFinalPorEspecie.merge(animal.getEspecie(), 1L, Long::sum));

        Set<String> todasLasEspecies = new HashSet<>();
        todasLasEspecies.addAll(poblacionAnteriorPorEspecie.keySet());
        todasLasEspecies.addAll(poblacionFinalPorEspecie.keySet());

        todasLasEspecies.stream()
                .sorted(String::compareTo)
                .forEach(especie -> {
                    long poblacionInicial = poblacionAnteriorPorEspecie.getOrDefault(especie, 0L);
                    long poblacionFinal = poblacionFinalPorEspecie.getOrDefault(especie, 0L);
                    sbFijas.append(String.format("  %-10s: Inicio: %-5d, Final: %-5d\n", especie, poblacionInicial, poblacionFinal));
                });

        // AÑADE LA SECCIÓN DE ESPECIES EXTINTAS AQUÍ TAMBIÉN
        List<String> especiesExtintas = new ArrayList<>();
        poblacionAnteriorPorEspecie.keySet().stream()
                .sorted(String::compareTo)
                .forEach(especie -> {
                    if (!poblacionFinalPorEspecie.containsKey(especie)) {
                        especiesExtintas.add(especie);
                    }
                });
        if (!especiesExtintas.isEmpty()) {
            sbFijas.append("\n--- Especies Extintas ☠️ ---\n");
            especiesExtintas.forEach(especie -> sbFijas.append("  - ").append(especie).append("\n"));
        }

        // AÑADE EL TÍTULO DEL HISTORIAL EN LA SECCIÓN FIJA
        sbFijas.append("\n--- Historial de Eventos del Ciclo ").append(cicloActual).append("---");

        // CONSTRUYE LA CADENA PARA EL HISTORIAL DESPLAZABLE
        StringBuilder sbHistorial = new StringBuilder();
        String tituloCiclo = "--- Inicio del Ciclo " + cicloActual + " ---";
        String logSinTitulo = cicloLog.toString().replace(tituloCiclo + "\n", "");
        sbHistorial.append(logSinTitulo);

        // Actualiza el mapa para el siguiente ciclo
        poblacionAnteriorPorEspecie = poblacionFinalPorEspecie;

        // Envía las cadenas a los dos componentes separados
        if (gui != null) {
            gui.actualizarEstadisticasFijas(sbFijas.toString());
            gui.actualizarHistorial(sbHistorial.toString());
            gui.actualizarContadores(
                    isla.getTotalAnimales(),
                    isla.getTotalPlantas(),
                    nacimientosEnCiclo.get(),
                    fallecimientosEnCiclo.get(),
                    kgConsumidosEnCiclo
            );
            gui.repaintPanelIsla();
        }
    }


    private static void apagarSimulacion() {
        scheduledExecutor.shutdown();
        animalActionExecutor.shutdown();
        try {
            if (!scheduledExecutor.awaitTermination(10, TimeUnit.SECONDS)) {
                scheduledExecutor.shutdownNow();
            }
            if (!animalActionExecutor.awaitTermination(10, TimeUnit.SECONDS)) {
                animalActionExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduledExecutor.shutdownNow();
            animalActionExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        SimulacionIsla.log("--- Simulación Finalizada ---");

        if (gui != null) {
            gui.actualizarHistorial(cicloLog.toString());
        }

    }

}