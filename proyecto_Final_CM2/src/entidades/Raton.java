package entidades;

    public class Raton extends Animal {
        public Raton(String dieta, double tasaReproduccion, int rangoMovimiento, double pesoKg, int numMaxPorLocalidad, double alimentoNecesarioKg, double aguaNecesariaLitros) {
            super("Raton", dieta, tasaReproduccion, rangoMovimiento, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
    }

    public void comer(Ubicacion ubicacion) {
        if (this.haComidoEnEsteCiclo) {
            return; // No hace nada si ya está saciado.
        }

        // 1. Lógica para comer presas
        // Este método ya verifica si el animal se sació.
        comerPresa(ubicacion);

        // 2. Si todavía está hambriento, intentar comer plantas
        if (!this.haComidoEnEsteCiclo) {
            comerPlantas(ubicacion);
        }
    }

}