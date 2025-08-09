package entidades;

public class Boa extends Animal {
    public Boa(String dieta, double tasaReproduccion, int rangoMovimiento, double pesoKg, int numMaxPorLocalidad, double alimentoNecesarioKg, double aguaNecesariaLitros) {
        super("Boa", dieta, tasaReproduccion, rangoMovimiento, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
    }


    public void comer(Ubicacion ubicacion) {
        // Llama al método helper para comer presas
        comerPresa(ubicacion);
    }

}
