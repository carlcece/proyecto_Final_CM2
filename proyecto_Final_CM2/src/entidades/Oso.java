package entidades;

    public class Oso extends Animal {
      public Oso(String dieta, double tasaReproduccion, int rangoMovimiento, double pesoKg, int numMaxPorLocalidad, double alimentoNecesarioKg, double aguaNecesariaLitros) {
        super("Oso", dieta, tasaReproduccion, rangoMovimiento, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
    }

    public void comer(Ubicacion ubicacion) {
        // Llama al método helper para comer presas
        comerPresa(ubicacion);
    }
}