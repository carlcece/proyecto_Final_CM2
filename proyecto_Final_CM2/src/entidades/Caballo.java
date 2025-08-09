package entidades;

    public class Caballo extends Animal {
        public Caballo(String dieta, double tasaReproduccion, int rangoMovimiento, double pesoKg, int numMaxPorLocalidad, double alimentoNecesarioKg, double aguaNecesariaLitros) {
           super("Caballo", dieta, tasaReproduccion, rangoMovimiento, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
    }

    public void comer(Ubicacion ubicacion) {
        // Llama al método helper para comer plantas
        comerPlantas(ubicacion);
    }

}