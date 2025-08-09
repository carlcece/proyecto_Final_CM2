package entidades;

    public class Bufalo extends Animal {
        public Bufalo(String dieta, double tasaReproduccion, int rangoMovimiento, double pesoKg, int numMaxPorLocalidad, double alimentoNecesarioKg, double aguaNecesariaLitros) {
             super("Bufalo", dieta, tasaReproduccion, rangoMovimiento, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
    }

    public void comer(Ubicacion ubicacion) {
        // Llama al método helper para comer plantas
        comerPlantas(ubicacion);
    }

}