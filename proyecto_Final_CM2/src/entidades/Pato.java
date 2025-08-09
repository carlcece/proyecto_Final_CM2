package entidades;

    public class Pato extends Animal {
        public Pato(String dieta, double tasaReproduccion, int rangoMovimiento, double pesoKg, int numMaxPorLocalidad, double alimentoNecesarioKg, double aguaNecesariaLitros) {
           super("Pato", dieta, tasaReproduccion, rangoMovimiento, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
    }

    public void comer(Ubicacion ubicacion) {
        // La condición de saciedad ahora se basa en si el animal ya comió en este ciclo.
        if (this.haComidoEnEsteCiclo) {
            return;
        }

        // 1. Lógica para comer presas
        // Este método ya se encarga de verificar y actualizar el estado de comida.
        comerPresa(ubicacion);

        // 2. Si todavía no ha comido, intentar comer plantas
        if (!this.haComidoEnEsteCiclo) {
            comerPlantas(ubicacion);
        }
    }

}