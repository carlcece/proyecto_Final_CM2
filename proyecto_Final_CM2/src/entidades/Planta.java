package entidades;

import config.Configuracion;

public class Planta {
    private int cantidad;
    private int crecimientoTasa;
    private String tipo;

    public Planta(int cantidadInicial, int crecimientoTasa) {
        this.cantidad = cantidadInicial;
        this.crecimientoTasa = crecimientoTasa;
        // La validación debe ser con el valor inicial, no con la variable de clase
        this.cantidad = Math.min(this.cantidad, Configuracion.MAX_CANTIDAD_PLANTAS_POR_UBICACION);
        this.tipo = "normal";
    }


    public void crecer() {
        this.cantidad = Math.min(this.cantidad + this.crecimientoTasa, Configuracion.MAX_CANTIDAD_PLANTAS_POR_UBICACION);
    }


    public int serComida(int cantidadAConsumir) {
        int comidaConsumida = Math.min(this.cantidad, cantidadAConsumir);
        this.cantidad -= comidaConsumida;
        return comidaConsumida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.cantidad = Math.min(this.cantidad, Configuracion.MAX_CANTIDAD_PLANTAS_POR_UBICACION);
    }

    @Override
    public String toString() {
        return "Planta(Cantidad: " + cantidad + ")";
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}