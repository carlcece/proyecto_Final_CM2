package entidades;

    public class Ciervo extends Animal {
        public Ciervo(String dieta, double tasaReproduccion, int rangoMovimiento, double pesoKg, int numMaxPorLocalidad, double alimentoNecesarioKg, double aguaNecesariaLitros) {
             super("Ciervo", dieta, tasaReproduccion, rangoMovimiento, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
    }


    public void comer(Ubicacion ubicacion) {
        // Llama al método helper para comer plantas
        comerPlantas(ubicacion);
    }

}