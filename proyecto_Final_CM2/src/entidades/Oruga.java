package entidades;

    public class Oruga extends Animal {
        public Oruga(String dieta, double tasaReproduccion, int rangoMovimiento, double pesoKg, int numMaxPorLocalidad, double alimentoNecesarioKg, double aguaNecesariaLitros) {
             super("Oruga", dieta, tasaReproduccion, rangoMovimiento, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
    }


    public void comer(Ubicacion ubicacion) {
        // Llama al método helper para comer plantas
        comerPlantas(ubicacion);
    }

}