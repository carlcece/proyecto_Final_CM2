
package entidades;



public class Tigre extends Animal {
    public Tigre(String dieta, double tasaReproduccion, int rangoMovimiento, double pesoKg, int numMaxPorLocalidad, double alimentoNecesarioKg, double aguaNecesariaLitros) {
        super("Tigre", dieta, tasaReproduccion, rangoMovimiento, pesoKg, numMaxPorLocalidad, alimentoNecesarioKg, aguaNecesariaLitros);
    }

    @Override
    public void comer(Ubicacion ubicacion) {
        comerPresa(ubicacion); // El tigre solo come presas
    }

}