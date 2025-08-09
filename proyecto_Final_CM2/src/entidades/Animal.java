package entidades;

import config.TablaProbabilidadesAlimentacion;
import interfaces.Posicionable;
import interfaces.Movible;
import util.AnimalFactory;
import util.Coordenada;
import config.Configuracion;
import main.Isla;
import main.SimulacionIsla;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public abstract class Animal implements Posicionable, Movible {
    protected String especie;
    protected String dieta;

    protected int edad;
    protected boolean vivo;

    protected double tasaReproduccion;
    protected int rangoMovimiento;
    protected double pesoKg;
    protected double pesoInicialKg;
    protected int numMaxPorLocalidad;
    protected double alimentoNecesarioKg;

    protected Coordenada posicionActual;
    protected Isla isla;

    protected double aguaActualLitros;
    protected double aguaNecesariaLitros;
    protected boolean haComidoEnEsteCiclo;

    public Animal(String especie, String dieta, double tasaReproduccion, int rangoMovimiento, double pesoKg, int numMaxPorLocalidad, double alimentoNecesarioKg, double aguaNecesariaLitros) {
        this.especie = especie;
        this.dieta = dieta;
        this.tasaReproduccion = tasaReproduccion;
        this.rangoMovimiento = rangoMovimiento;
        this.pesoKg = pesoKg;
        this.pesoInicialKg = pesoKg;
        this.numMaxPorLocalidad = numMaxPorLocalidad;
        this.alimentoNecesarioKg = alimentoNecesarioKg;
        this.edad = 0;
        this.vivo = true;
        this.posicionActual = null;
        this.aguaNecesariaLitros = aguaNecesariaLitros;
        this.aguaActualLitros = aguaNecesariaLitros;

    }

    //Lógica para que el animal beba agua.
    public void beber() {
        SimulacionIsla.log(String.format("INFO: El %s en %s va a beber agua. Reserva actual: %.2f L.", this.getEspecie(), this.getPosicion(), this.getAguaActualLitros()));

        double litrosBebidos = this.aguaNecesariaLitros - this.aguaActualLitros;
        this.aguaActualLitros = this.aguaNecesariaLitros;
        SimulacionIsla.agregarLitrosConsumidos(litrosBebidos);

        SimulacionIsla.log(String.format("¡ÉXITO! El %s en %s bebió %.3f L de agua y está saciado. Reserva final: %.3f L.", this.getEspecie(), this.getPosicion(), litrosBebidos, this.getAguaActualLitros()));
    }

    //Lógica para que un animal herbívoro o omnívoro coma plantas
    protected void comerPresa(Ubicacion ubicacion) {
        if (this.pesoKg >= this.pesoInicialKg) {
            SimulacionIsla.log(String.format("INFO: El %s en %s está saciado y no necesita cazar. Peso actual: %.2f kg.", this.getEspecie(), this.getPosicion(), this.getPesoKg()));
            return;
        }

        synchronized (ubicacion) {
            List<Animal> presasDisponibles = ubicacion.getAnimales().stream()
                    .filter(a -> a.estaVivo() && TablaProbabilidadesAlimentacion.getProbabilidad(this.getEspecie(), a.getEspecie()) > 0)
                    .collect(Collectors.toList());

            if (!presasDisponibles.isEmpty()) {
                Animal presaAComer = presasDisponibles.get(ThreadLocalRandom.current().nextInt(presasDisponibles.size()));
                int probabilidadExito = TablaProbabilidadesAlimentacion.getProbabilidad(this.getEspecie(), presaAComer.getEspecie());
                double randomValue = ThreadLocalRandom.current().nextDouble(100.0);

                SimulacionIsla.log(String.format("CAZA: El %s en %s (Peso: %.2f kg) intenta cazar un %s (Prob: %d%%, Resultado: %.2f)",
                        this.getEspecie(), this.getPosicion(), this.getPesoKg(), presaAComer.getEspecie(), probabilidadExito, randomValue));

                if (randomValue < probabilidadExito) {
                    double alimentoGanadoKg = presaAComer.getPesoKg();
                    double espacioDisponible = this.pesoInicialKg - this.pesoKg;
                    double aumentoReal = Math.min(alimentoGanadoKg, espacioDisponible);

                    presaAComer.setVivo(false);
                    this.pesoKg += aumentoReal;
                    this.haComidoEnEsteCiclo = true;
                    SimulacionIsla.agregarKgConsumidos(aumentoReal);
                    SimulacionIsla.log(String.format("¡ÉXITO! El %s cazó y comió un %s en %s. Su peso aumentó a %.2f kg.",
                            this.getEspecie(), presaAComer.getEspecie(), this.getPosicion(), this.pesoKg));
                } else {
                    SimulacionIsla.log(String.format("FALLO: El %s falló en su intento de caza del %s. Peso actual: %.2f kg.",
                            this.getEspecie(), presaAComer.getEspecie(), this.getPesoKg()));
                }
            } else {
                SimulacionIsla.log(String.format("INFO: El %s en %s no encontró presas para cazar. Peso actual: %.2f kg.",
                        this.getEspecie(), this.getPosicion(), this.getPesoKg()));
            }
        }
    }

    protected void comerPlantas(Ubicacion ubicacion) {
        if (this.pesoKg >= this.pesoInicialKg) {
            SimulacionIsla.log(String.format("INFO: El %s en %s está saciado y no necesita comer. Peso actual: %.2f kg.", this.getEspecie(), this.getPosicion(), this.getPesoKg()));
            return;
        }

        Planta plantasDisponibles = ubicacion.getPlantas();

        synchronized (plantasDisponibles) {
            // La lógica para la planta letal se ejecuta primero, si hay una planta en la celda
            if (plantasDisponibles != null) {
                // Asumiendo que getTipo() devuelve el nombre de la especie o "normal"
                if (plantasDisponibles.getTipo() != null && plantasDisponibles.getTipo().equals(Configuracion.PLANTA_LETAL)) {
                    this.pesoKg = 0.0; // El animal muere instantáneamente
                    this.setVivo(false);
                    SimulacionIsla.log(String.format("¡VENENO! El %s en %s ha muerto por comer una %s. Reserva final: %.2f kg.",
                            this.getEspecie(), this.getPosicion(), Configuracion.PLANTA_LETAL, this.getPesoKg()));
                    return; // Terminamos la ejecución del método
                }
            }

            // Si la planta no es letal, continuamos con la lógica normal
            if (plantasDisponibles.getCantidad() > 0) {
                int probabilidadExito = TablaProbabilidadesAlimentacion.getProbabilidad(this.getEspecie(), "Planta");
                double randomValue = ThreadLocalRandom.current().nextDouble(100.0);

                SimulacionIsla.log(String.format("CONSUMO: El %s en %s (Peso: %.2f kg) intenta comer plantas (Prob: %d%%, Resultado: %.2f)", this.getEspecie(), this.getPosicion(), this.getPesoKg(), probabilidadExito, randomValue));

                if (randomValue < probabilidadExito) {
                    double espacioDisponible = this.pesoInicialKg - this.pesoKg;
                    double comidaAConsumir = Math.min(espacioDisponible, plantasDisponibles.getCantidad());

                    if (comidaAConsumir > 0) {
                        plantasDisponibles.serComida((int) Math.ceil(comidaAConsumir));
                        this.pesoKg += comidaAConsumir;
                        this.haComidoEnEsteCiclo = true;

                        this.aguaActualLitros += this.aguaNecesariaLitros / 2.0;

                        // Asegurarse de que el agua no exceda el máximo
                        if (this.aguaActualLitros > this.aguaNecesariaLitros) {
                            this.aguaActualLitros = this.aguaNecesariaLitros;
                        }

                        SimulacionIsla.agregarKgConsumidos(comidaAConsumir);
                        SimulacionIsla.log(String.format("¡ÉXITO! El %s comió plantas en %s. Su peso aumentó a %.2f kg. y se hidrató parcialmente.", this.getEspecie(), this.getPosicion(), this.pesoKg));

                    } else {
                        SimulacionIsla.log(String.format("INFO: El %s en %s necesita muy poco alimento para reponer y no ha comido. Peso actual: %.2f kg.", this.getEspecie(), this.getPosicion(), this.getPesoKg()));
                    }
                } else {
                    SimulacionIsla.log(String.format("FALLO: El %s falló en su intento de comer plantas. Peso actual: %.2f kg.", this.getEspecie(), this.getPosicion(), this.getPesoKg()));
                }
            } else {
                SimulacionIsla.log(String.format("INFO: El %s en %s no encontró plantas para comer. Peso actual: %.2f kg.", this.getEspecie(), this.getPosicion(), this.getPesoKg()));
            }
        }
    }


    public abstract void comer(Ubicacion ubicacion);


    public void reproducirse(Ubicacion ubicacion) {
        List<Animal> otrosDeLaMismaEspecie = ubicacion.getAnimales().stream()
                .filter(a -> a.getEspecie().equals(this.getEspecie()) && a != this && a.estaVivo())
                .collect(Collectors.toList());

        if (!otrosDeLaMismaEspecie.isEmpty() && ThreadLocalRandom.current().nextDouble() < this.tasaReproduccion) {
            long animalesEnUbicacion = ubicacion.getAnimales().stream()
                    .filter(a -> a.getEspecie().equals(this.getEspecie()))
                    .count();

            if (animalesEnUbicacion < this.numMaxPorLocalidad) {
                int numCrias = Configuracion.NUM_CRIAS_POR_ESPECIE.getOrDefault(this.getEspecie(), 1);
                for (int i = 0; i < numCrias; i++) {
                    if (ubicacion.getAnimales().stream().filter(a -> a.getEspecie().equals(this.getEspecie())).count() < this.numMaxPorLocalidad) {
                        Animal nuevaCria = AnimalFactory.crearAnimal(this.getEspecie());

                        if (nuevaCria != null) {
                            nuevaCria.setPosicion(this.getPosicion());
                            nuevaCria.setIsla(this.getIsla());
                            ubicacion.agregarAnimal(nuevaCria);

                            SimulacionIsla.nacimientosEnCiclo.incrementAndGet();
                            SimulacionIsla.log("¡NACIMIENTO: Ha nacido un nuevo animal en la posición: " + this.getPosicion());
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    }

    //Actualiza el estado del animal al final del ciclo
    public void actualizarEstado() {
        double pesoAnterior = this.pesoKg;
        double aguaAnterior = this.aguaActualLitros;

        this.edad++;

        if (!this.haComidoEnEsteCiclo) {
            double perdidaPeso = Configuracion.TASA_INANICION_POR_ESPECIE.get(this.getEspecie());
            this.pesoKg -= perdidaPeso;

            SimulacionIsla.log(String.format("HAMBRE: El %s en %s no ha comido. Su peso baja a %.3f kg.",
                    this.getEspecie(), this.getPosicion(), this.pesoKg));
        }
        this.haComidoEnEsteCiclo = false;

        // --- CÓDIGO CORREGIDO ---
        // Aseguramos que el peso no baje de 0
        if (this.pesoKg < 0) {
            this.pesoKg = 0;
        }
        // --- FIN CÓDIGO CORREGIDO ---

        double litrosAPerder = Configuracion.DESHIDRATACION_POR_ESPECIE.getOrDefault(this.getEspecie(), 0.1);

        this.aguaActualLitros -= litrosAPerder;

        if (this.pesoKg <= 0 || this.aguaActualLitros <= 0) {
            this.setVivo(false);
        }

        String mensajePeso;
        if (this.pesoKg > pesoAnterior) {
            mensajePeso = String.format("El peso de %s subió de %.3f kg a %.3f kg. ", this.especie, pesoAnterior, this.pesoKg);
        } else if (this.pesoKg < pesoAnterior) {
            mensajePeso = String.format("El peso de %s bajó de %.3f kg a %.3f kg. ", this.especie, pesoAnterior, this.pesoKg);
        } else {
            mensajePeso = String.format("El peso de %s se mantuvo en %.3f kg. ", this.especie, this.pesoKg);
        }

        String mensajeAgua;
        if (this.aguaActualLitros > aguaAnterior) {
            mensajeAgua = String.format("Su hidratacion subió de %.3f L a %.3f L. ", aguaAnterior, this.aguaActualLitros);
        } else if (this.aguaActualLitros <= 0) {
            mensajeAgua = String.format("Su hidratacion bajó de %.3f L y se agotó. ", aguaAnterior);
        } else {
            mensajeAgua = String.format("Su agua hidratacion de %.3f L a %.3f L. ", aguaAnterior, this.aguaActualLitros);
        }

        SimulacionIsla.log(String.format("ACTUALIZACIÓN: %s || %s", mensajePeso, mensajeAgua));
    }

    @Override
    public Coordenada moverse(Isla isla, Coordenada posicionActual) {
        double probMovimiento = Configuracion.PROBABILIDAD_MOVIMIENTO_POR_ESPECIE.getOrDefault(this.getEspecie(), 0.0);
        if (ThreadLocalRandom.current().nextDouble(1.0) >= probMovimiento) {
            System.out.printf("INFO: El %s en %s (Reserva: %.2f kg) decidió no moverse en este ciclo.%n", this.getEspecie(), this.getPosicion(), this.getPesoKg());
            return posicionActual;
        }

        List<Coordenada> posiblesDestinos = new java.util.ArrayList<>();
        for (int rOffset = -rangoMovimiento; rOffset <= rangoMovimiento; rOffset++) {
            for (int cOffset = -rangoMovimiento; cOffset <= rangoMovimiento; cOffset++) {
                if (rOffset == 0 && cOffset == 0) continue;
                Coordenada posibleCoord = new Coordenada(posicionActual.fila + rOffset, posicionActual.columna + cOffset);
                if (isla.getUbicacion(posibleCoord) != null) {

                    posiblesDestinos.add(posibleCoord);
                }
            }
        }

        if (!posiblesDestinos.isEmpty()) {
            Coordenada nuevaPosicion = posiblesDestinos.get(ThreadLocalRandom.current().nextInt(posiblesDestinos.size()));
            SimulacionIsla.log(String.format("INFO: El %s en %s (Rango: %d) se movió a %s", this.getEspecie(), posicionActual, this.rangoMovimiento, nuevaPosicion));
            return nuevaPosicion;
        }
        return posicionActual;
    }

    // Ejecuta todas las acciones del animal en un ciclo de simulacion
    public void ejecutarAccionesDeCiclo(Isla isla) {
        synchronized (this) {
            if (!this.estaVivo()) {
                return;
            }

            Ubicacion ubicacionActual = isla.getUbicacion(this.posicionActual);
            if (ubicacionActual == null) {
                return;
            }

            if (ubicacionActual.getTerreno().equals(Configuracion.AGUA) && this.aguaActualLitros < this.aguaNecesariaLitros) {
                this.beber();
            }
            if (!this.estaVivo()) return;

            this.comer(ubicacionActual);

            if (!this.estaVivo()) {
                return;
            }

            Coordenada posicionOriginal = this.getPosicion();
            Coordenada nuevaPosicion = this.moverse(isla, posicionOriginal);

            if (!this.estaVivo()) {
                return;
            }

            if (!nuevaPosicion.equals(posicionOriginal)) {
                Ubicacion nuevaUbicacion = isla.getUbicacion(nuevaPosicion);
                if (nuevaUbicacion != null) {
                    Object lock1 = ubicacionActual;
                    Object lock2 = nuevaUbicacion;

                    if (System.identityHashCode(lock1) > System.identityHashCode(lock2)) {
                        Object temp = lock1;
                        lock1 = lock2;
                        lock2 = temp;
                    }

                    synchronized (lock1) {
                        synchronized (lock2) {
                            ubicacionActual.eliminarAnimal(this);
                            nuevaUbicacion.agregarAnimal(this);
                            this.setPosicion(nuevaPosicion);
                        }
                    }
                }
            }

            if (!this.estaVivo()) {
                return;
            }
            Ubicacion ubicacionPostAcciones = isla.getUbicacion(this.getPosicion());
            if (ubicacionPostAcciones != null) {
                synchronized (ubicacionPostAcciones) {
                    this.reproducirse(ubicacionPostAcciones);
                }
            }

            if (!this.estaVivo()) {
                return;
            }
            this.actualizarEstado();
        }
    }

    public boolean estaVivo() { return this.vivo; }
    public void setVivo(boolean vivo) { this.vivo = vivo; }
    @Override
    public Coordenada getPosicion() { return this.posicionActual; }
    @Override
    public void setPosicion(Coordenada nuevaPosicion) { this.posicionActual = nuevaPosicion; }

    public Isla getIsla() { return isla; }
    public void setIsla(Isla isla) { this.isla = isla; }

    public String getEspecie() { return especie; }
    public String getDieta() { return dieta; }
    public int getEdad() { return edad; }
    public double getTasaReproduccion() { return tasaReproduccion; }
    public int getRangoMovimiento() { return rangoMovimiento; }
    public double getPesoKg() { return pesoKg; }
    public int getNumMaxPorLocalidad() { return numMaxPorLocalidad; }
    public double getAlimentoNecesarioKg() { return alimentoNecesarioKg; }

    public double getAguaActualLitros() { return aguaActualLitros; }
    public double getAguaNecesariaLitros() { return aguaNecesariaLitros; }


    @Override
    public String toString() {
        return String.format("%s (P:%.2fkg Eda:%d)", especie, pesoKg, edad);
    }
}